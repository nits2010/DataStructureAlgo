#!/usr/bin/env python3
"""
Identify and fix question files with missing/invalid title or link.

1. Identifies files that have Company Tags but missing/placeholder title or link
   (or don't follow the standard file template).
2. For each such file: builds search query from class name / filename, searches
   (LeetCode first, then GeeksforGeeks, then other), updates only the header
   (Question Title + Link) without overriding the rest.
3. Does NOT override files that already have a similar template (proper title +
   full URL). Those are reported in a separate .md file.

Usage:
  python fix_question_headers.py --identify     # Only list files needing fix + report similar-template
  python fix_question_headers.py --fix          # Run search and update files (requires duckduckgo-search)
  python fix_question_headers.py --fix --dry-run # Show what would be updated, no writes
  python fix_question_headers.py --fix --limit 50  # Fix only first 50 files
  python fix_question_headers.py --fix --browser    # Open Google search in browser for each query; paste URL when prompted (or Enter for fallback)

Outputs (in GsheetTrackers/):
  - FilesNeedingFix_List.md: files with missing/placeholder title or link
  - FilesWithSimilarTemplate_Report.md: files not modified (already have template + full URL)
  - FilesNotFixed_NoLinkFound.md: (when using --fix) files for which no link was found

Templates: fileTemplate.py (Python), fileTemplate.java (Java)

Resolving links (no search needed for most files):
  - LeetCode API: For filenames ending with _NUMBER (e.g. MaximumSubarray_53), the script fetches the official
    LeetCode problem list (https://leetcode.com/api/problems/all/) and uses the exact problem slug. Cached under
    GsheetTrackers/leetcode_problems_cache.json (24h). This fixes the "search not working" issue for standard
    LeetCode problems.
  - For files without _NUMBER: tries Google (googlesearch-python) then DuckDuckGo; if both return 0 results,
    uses a slug built from the filename. Use --fix --browser to open Google in the browser and paste the URL.
"""

import json
import re
import sys
import time
import webbrowser
from pathlib import Path
from typing import Dict, List, Optional, Tuple
from urllib.parse import quote_plus
from urllib.request import Request, urlopen

# Reuse repo root and collect logic from build_company_question_list
sys.path.insert(0, str(Path(__file__).resolve().parent))
try:
    from build_company_question_list import (
        REPO_ROOT,
        path_should_ignore,
        is_helper_file,
        collect_files,
        CODE_EXTENSIONS,
    )
except ImportError:
    import os
    REPO_ROOT = Path(__file__).resolve().parent
    IGNORE_FOLDERS = {"helpers", "sorts", "python"}
    CODE_EXTENSIONS = {".py", ".java", ".js", ".ts", ".tsx"}

    def path_should_ignore(rel_path: Path) -> bool:
        return any(p in IGNORE_FOLDERS for p in rel_path.parts)

    def is_helper_file(base_name: str) -> bool:
        return base_name.lower() in {
            "node", "pair", "listnode", "doublylistnode", "treenode",
            "listbuilder", "treebuilder", "commonmethods", "main",
        }

    def collect_files(root: Path, poc: bool):
        out = []
        root_str = str(root)
        for dirpath, _dirnames, filenames in os.walk(root):
            rel = Path(dirpath).relative_to(
                root) if dirpath != root_str else Path(".")
            if path_should_ignore(rel):
                continue
            for f in filenames:
                p = Path(dirpath) / f
                if p.suffix not in CODE_EXTENSIONS:
                    continue
                rel_str = str(p.relative_to(root)).replace("\\", "/")
                if "LeetCode2025" in rel_str:
                    priority = 0
                elif "companyWise" in rel_str or "CompanyWise" in rel_str:
                    priority = 1
                else:
                    priority = 2
                out.append((priority, p))
        out.sort(key=lambda x: (x[0], str(x[1])))
        return out


# Placeholder link patterns (need fix)
LINK_PLACEHOLDER_RE = re.compile(
    r"^\s*$|^\(no link\)\s*$|see\s*#\d+|leetcode\.com/problems/\s*\(\s*see\s*#",
    re.I
)

# Full URL patterns we consider "good" (don't override)
LEETCODE_FULL_RE = re.compile(
    r"https://leetcode\.com/problems/[^\s\)\]\<]+", re.I)
GEEKS_FULL_RE = re.compile(
    r"https://www\.geeksforgeeks\.org/[^\s\)\]\<]+", re.I)


def get_first_comment_block(content: str, is_python: bool) -> Optional[Tuple[str, int, int]]:
    """Return (block_text, start_pos, end_pos) of first docstring or /** */, or None."""
    if is_python:
        m = re.search(r'"""([\s\S]*?)"""', content)
        if m:
            return content[m.start():m.end()], m.start(), m.end()
        return None
    m = re.search(r"/\*\*?([\s\S]*?)\*/", content)
    if m:
        return content[m.start():m.end()], m.start(), m.end()
    return None


def _strip_java_line(line: str) -> str:
    """Strip Java comment prefix ' * ' for matching."""
    return line.strip().lstrip("*").strip()


def parse_header(block: str, is_python: bool) -> dict:
    """Extract title, link, has_company_tags from comment block."""
    out = {"title": "", "link": "", "has_company_tags": "Company Tags" in block}
    lines = block.split("\n")
    for i, line in enumerate(lines):
        stripped = _strip_java_line(line) if not is_python else line
        if re.match(r"Question Title:\s*", stripped, re.I):
            out["title"] = re.sub(r"Question Title:\s*",
                                  "", stripped, flags=re.I).strip()
        if re.match(r"Question Category:\s*", stripped, re.I) and not out["title"]:
            out["title"] = re.sub(r"Question Category:\s*",
                                  "", stripped, flags=re.I).strip()
            out["title"] = re.sub(
                r"\s*\[(EASY|MEDIUM|HARD)\]\s*$", "", out["title"], flags=re.I).strip()
        if re.match(r"Link:\s*", stripped, re.I):
            rest = re.sub(r"Link:\s*", "", stripped, flags=re.I).strip()
            if rest and rest.startswith("http"):
                out["link"] = rest.rstrip(">")
            elif i + 1 < len(lines):
                next_line = lines[i + 1].strip().lstrip("*").strip()
                if next_line.startswith("http"):
                    out["link"] = next_line.rstrip(">")
                else:
                    out["link"] = rest or " (empty) "
            else:
                out["link"] = rest or " (empty) "
    # Normalize empty
    if not out["link"] or out["link"].strip() in ("", "(empty)"):
        out["link"] = " (no link) "
    return out


def link_is_placeholder(link: str) -> bool:
    if not link or not link.strip():
        return True
    s = link.strip()
    if s in ("(no link)", "(empty)"):
        return True
    if "see #" in s or "leetcode.com/problems/ (see" in s:
        return True
    if not s.startswith("http"):
        return True
    return False


def link_is_full_url(link: str) -> bool:
    if not link or not link.strip():
        return False
    s = link.strip()
    return bool(LEETCODE_FULL_RE.search(s) or GEEKS_FULL_RE.search(s) or (s.startswith("http") and "leetcode" in s) or (s.startswith("http") and "geeksforgeeks" in s))


def has_similar_template(block: str, is_python: bool, base_name: str) -> bool:
    """True if file has Question Title, Link, Company Tags and a full URL (do not override)."""
    p = parse_header(block, is_python)
    if not p["has_company_tags"]:
        return False
    if not p["title"] or p["title"] == base_name:
        return False
    if link_is_placeholder(p["link"]):
        return False
    return link_is_full_url(p["link"])


def needs_fix(block: str, is_python: bool, base_name: str) -> bool:
    """True if file has Company Tags but missing/placeholder title or link."""
    p = parse_header(block, is_python)
    if not p["has_company_tags"]:
        return False
    title_bad = not p["title"] or p["title"].strip() == base_name
    link_bad = link_is_placeholder(p["link"])
    return title_bad or link_bad


def _camel_to_words(name: str) -> str:
    """CamelCase + digits to words: MinimumSwapsToGroupAll1TogetherII -> Minimum Swaps To Group All 1 Together II."""
    name = re.sub(r"_(\d+)$", "", name)  # remove _2134 suffix
    # Split before capitals and before digit groups
    s = re.sub(r"([A-Z][a-z]+)", r" \1", name)
    s = re.sub(r"([a-zA-Z])(\d+)", r"\1 \2", s)  # All1 -> All 1
    s = re.sub(r"(\d+)([A-Za-z])", r"\1 \2", s)  # 1s -> 1 s if needed
    return s.strip()


def build_search_query(title: str, base_name: str) -> str:
    """Build query for web search: prefer title, else derive from filename."""
    if title and title.strip() and title.strip() != base_name:
        t = title.strip()
        t = re.sub(r"^\d+\.\s*", "", t).strip()
        return f"{t} leetcode"
    name = base_name
    num_match = re.search(r"_(\d+)$", name)
    num = num_match.group(1) if num_match else ""
    phrase = _camel_to_words(name)
    if num:
        return f"{num} {phrase} leetcode"
    return f"{phrase} leetcode"


def _slug_from_title(title: str) -> str:
    """Build LeetCode-style slug: lowercase, spaces to hyphens, remove apostrophes/special."""
    if not title:
        return ""
    s = title.strip()
    s = re.sub(r"^\d+\.\s*", "", s)  # remove leading "2134. "
    s = s.lower()
    s = re.sub(r"[^\w\s-]", "", s)  # remove apostrophes etc
    s = re.sub(r"[-\s]+", "-", s).strip("-")
    return s


def _leetcode_url_from_slug(slug: str) -> str:
    return f"https://leetcode.com/problems/{slug}/"


# LeetCode API: map frontend_question_id -> {"slug": str, "title": str}; avoids search entirely for files with _NUMBER
LEETCODE_API_URL = "https://leetcode.com/api/problems/all/"
CACHE_PATH = REPO_ROOT / "GsheetTrackers" / "leetcode_problems_cache.json"
CACHE_MAX_AGE_SEC = 86400  # 24 hours


def fetch_leetcode_problems_map() -> Dict[int, dict]:
    """Fetch or load cached LeetCode problem list; return dict id -> {slug, title}."""
    if CACHE_PATH.exists() and (time.time() - CACHE_PATH.stat().st_mtime) < CACHE_MAX_AGE_SEC:
        try:
            data = json.loads(CACHE_PATH.read_text(encoding="utf-8"))
            raw = data.get("id_to_slug_title") or {}
            out = {}
            for k, v in raw.items():
                try:
                    if isinstance(v, dict) and v.get("slug"):
                        out[int(k)] = v
                except (TypeError, ValueError):
                    continue
            if out:
                return out
        except Exception:
            pass
    out: Dict[int, dict] = {}
    try:
        req = Request(LEETCODE_API_URL, headers={
                      "User-Agent": "Mozilla/5.0 (compatible; LeetCodeLinkFix/1.0)"})
        with urlopen(req, timeout=15) as resp:
            data = json.loads(resp.read().decode("utf-8"))
        for item in data.get("stat_status_pairs") or []:
            stat = item.get("stat") or {}
            qid = stat.get("frontend_question_id")
            slug = stat.get("question__title_slug")
            title = stat.get("question__title") or ""
            if qid is not None and slug:
                out[int(qid)] = {"slug": slug, "title": title}
        CACHE_PATH.parent.mkdir(parents=True, exist_ok=True)
        CACHE_PATH.write_text(json.dumps({"id_to_slug_title": {str(
            k): v for k, v in out.items()}}, indent=0), encoding="utf-8")
    except Exception:
        if CACHE_PATH.exists():
            try:
                data = json.loads(CACHE_PATH.read_text(encoding="utf-8"))
                raw = data.get("id_to_slug_title") or {}
                for k, v in raw.items():
                    try:
                        if isinstance(v, dict) and v.get("slug"):
                            out[int(k)] = v
                    except (TypeError, ValueError):
                        continue
            except Exception:
                pass
    return out


def get_leetcode_url_by_number(problem_id: int, leetcode_map: Dict[int, dict]) -> Optional[Tuple[str, str]]:
    """Return (url, title) if problem_id is in the map, else None."""
    info = leetcode_map.get(problem_id)
    if not info:
        return None
    slug = info.get("slug")
    title = info.get("title") or ""
    if not slug:
        return None
    url = _leetcode_url_from_slug(slug)
    full_title = f"{problem_id}. {title}" if title else str(problem_id)
    return (url, full_title)


def search_for_link(query: str, fallback_title: str = "", base_name: str = "") -> Optional[str]:
    """Return best URL (LeetCode > Geeksforgeeks > other). Tries Google search first, then DuckDuckGo, then fallback slug."""
    urls = []

    # 1) Try Google (googlesearch-python) - most reliable for "X leetcode" queries
    try:
        from googlesearch import search as google_search
        for url in google_search(query, num_results=10, lang="en"):
            if url and isinstance(url, str) and url.startswith("http"):
                urls.append(url)
                if len(urls) >= 10:
                    break
    except ImportError:
        pass
    except Exception:
        pass

    # 2) Fallback: DuckDuckGo if Google returned nothing
    if not urls:
        try:
            from ddgs import DDGS
        except ImportError:
            try:
                from ddgs import DDGS  # type: ignore[import-untyped]
            except ImportError:
                DDGS = None
        if DDGS:
            try:
                import warnings
                with warnings.catch_warnings():
                    warnings.simplefilter("ignore")
                    with DDGS() as ddgs:
                        for r in ddgs.text(query, max_results=8):
                            href = r.get("href") or r.get(
                                "link") or r.get("url") or ""
                            if isinstance(href, str) and href.startswith("http"):
                                urls.append(href)
            except Exception:
                pass

    for u in urls:
        if "leetcode.com/problems/" in u:
            return u.split("?")[0].rstrip("/") or u
    for u in urls:
        if "geeksforgeeks.org" in u:
            return u.split("?")[0].rstrip("/") or u
    for u in urls:
        if u.startswith("http") and ("leetcode" in u or "geeks" in u):
            return u
    # Fallback: build LeetCode URL from title or filename (e.g. MinimumSwapsToGroupAll1TogetherII_2134).
    # Google/DuckDuckGo often return 0 results when run from scripts (blocking/rate limit), so this is what usually runs.
    if fallback_title or base_name:
        title = fallback_title or ""
        title = re.sub(r"^\d+\.\s*", "", title).strip()
        if not title and base_name:
            title = _camel_to_words(base_name)
        slug = _slug_from_title(title)
        if slug:
            return _leetcode_url_from_slug(slug)
    return None


def title_from_link_or_query(link: str, query: str, current_title: str) -> str:
    """Prefer current title if valid, else could parse from link/query; for now return current or derived."""
    if current_title and current_title.strip() and not re.match(r"^[\w_]+$", current_title.strip()):
        return current_title.strip()
    # From query: "2134 Minimum Swaps to Group All 1's Together II leetcode" -> "2134. Minimum Swaps to Group All 1's Together II"
    q = query.replace(" leetcode", "").strip()
    if re.match(r"^\d+\s+", q):
        return q  # already "2134 Minimum Swaps..."
    return current_title or q


def update_file_header(path: Path, content: str, new_title: str, new_link: str, is_python: bool) -> str:
    """Replace or add Question Title and Link in the first comment block. Returns new content."""
    block_info = get_first_comment_block(content, is_python)
    if not block_info:
        return content
    block, start, end = block_info
    lines = block.split("\n")
    new_lines = []
    i = 0
    link_replaced = False
    title_replaced = False
    java_link_next_line_skip = False
    while i < len(lines):
        if java_link_next_line_skip:
            i += 1
            java_link_next_line_skip = False
            continue
        line = lines[i]
        if re.search(r"Question Title:\s*", line, re.I):
            idx = line.find("Question Title:")
            prefix = line[: idx + len("Question Title:")] if idx >= 0 else line
            if is_python:
                new_lines.append(f"{prefix.strip()} {new_title}")
            else:
                new_lines.append(f"{prefix.rstrip()} {new_title}")
            i += 1
            title_replaced = True
            continue
        if re.search(r"Question Category:\s*", line, re.I) and not title_replaced:
            if is_python:
                new_lines.append(f"Question Title: {new_title}")
            else:
                new_lines.append(f" * Question Title: {new_title}")
            i += 1
            title_replaced = True
            continue
        if re.search(r"Link:\s*", line, re.I):
            idx = line.find("Link:")
            prefix = line[: idx + len("Link:")] if idx >= 0 else line
            if is_python:
                new_lines.append(f"{prefix.strip()} {new_link}")
            else:
                new_lines.append(f"{prefix.rstrip()} {new_link}")
            i += 1
            link_replaced = True
            if i < len(lines) and not is_python:
                next_stripped = lines[i].strip().lstrip("*").strip()
                if next_stripped.startswith("http"):
                    java_link_next_line_skip = True
            continue
        # If we have Description: with URL but no Link line yet, insert Link before Description
        if not link_replaced and re.search(r"Description:\s*", line, re.I):
            m = re.search(r"https://[^\s\)\]\<]+", line)
            if m and new_link:
                if is_python:
                    new_lines.append(f"Link: {new_link}")
                else:
                    new_lines.append(f" * Link: {new_link}")
                link_replaced = True
        new_lines.append(line)
        i += 1
    new_block = "\n".join(new_lines)
    return content[:start] + new_block + content[end:]


def main():
    import argparse
    ap = argparse.ArgumentParser()
    ap.add_argument("--identify", action="store_true",
                    help="Only list files needing fix and report similar-template")
    ap.add_argument("--fix", action="store_true",
                    help="Search and update files")
    ap.add_argument("--browser", action="store_true",
                    help="With --fix: open Google search in browser for each query; paste URL when prompted (or Enter for fallback)")
    ap.add_argument("--dry-run", action="store_true",
                    help="With --fix: show updates, do not write")
    ap.add_argument("--limit", type=int, default=0,
                    help="With --fix: limit number of files to update (0 = all)")
    args = ap.parse_args()

    if not args.identify and not args.fix:
        ap.print_help()
        print("\nUse --identify to list files, or --fix to update (optionally with --dry-run).")
        return

    files = collect_files(REPO_ROOT, poc=False)
    to_fix = []
    similar_template = []

    for _priority, path in files:
        if path.suffix not in (".py", ".java"):
            continue
        if is_helper_file(path.stem):
            continue
        try:
            content = path.read_text(encoding="utf-8", errors="replace")
        except Exception:
            continue
        is_py = path.suffix == ".py"
        block_info = get_first_comment_block(content, is_py)
        if not block_info:
            continue
        block, _, _ = block_info
        parsed = parse_header(block, is_py)
        if not parsed["has_company_tags"]:
            continue
        if has_similar_template(block, is_py, path.stem):
            similar_template.append(
                (path, parsed.get("title", ""), parsed.get("link", "")))
            continue
        if needs_fix(block, is_py, path.stem):
            query = build_search_query(parsed["title"], path.stem)
            to_fix.append(
                (path, content, parsed["title"], parsed["link"], query, is_py))

    # Report similar-template (do not override)
    report_path = REPO_ROOT / "GsheetTrackers" / "FilesWithSimilarTemplate_Report.md"
    report_path.parent.mkdir(parents=True, exist_ok=True)
    report_lines = [
        "# Files with similar template (not modified)",
        "",
        "These files already have Question Title, Link, and Company Tags with a full URL.",
        "They were not modified by fix_question_headers.py.",
        "",
        "| File | Title | Link |",
        "|------|-------|------|",
    ]
    for path, title, link in similar_template:
        rel = path.relative_to(REPO_ROOT)
        t = (title[:70] + "…") if len(title) > 70 else title
        l = (link[:90] + "…") if len(link) > 90 else link
        report_lines.append(f"| {rel} | {t} | {l} |")
    report_lines.append("")
    report_path.write_text("\n".join(report_lines), encoding="utf-8")
    print(
        f"Report (similar template, not modified): {len(similar_template)} files -> {report_path}")

    # Identify: list to fix
    fix_list_path = REPO_ROOT / "GsheetTrackers" / "FilesNeedingFix_List.md"
    list_lines = [
        "# Files needing fix (missing/placeholder title or link)",
        "",
        "| File | Current title | Current link | Search query |",
        "|------|---------------|--------------|-------------|",
    ]
    for path, _c, title, link, query, _ in to_fix:
        rel = path.relative_to(REPO_ROOT)
        list_lines.append(
            f"| {rel} | {title[:50] or '-'} | {link[:50] or '-'} | {query} |")
    list_lines.append("")
    fix_list_path.write_text("\n".join(list_lines), encoding="utf-8")
    print(f"Needing fix: {len(to_fix)} files -> {fix_list_path}")

    if args.identify:
        return

    # Fix: search and update
    limit = getattr(args, "limit", 0) or 0
    use_browser = getattr(args, "browser", False)
    to_fix_list = to_fix[:limit] if limit > 0 else to_fix
    fixed = 0
    not_found = []
    # Resolve LeetCode URLs from official API when filename has _NUMBER (no search needed)
    leetcode_map: Dict[int, dict] = {}
    try:
        leetcode_map = fetch_leetcode_problems_map()
        if leetcode_map:
            print(
                f"LeetCode API: loaded {len(leetcode_map)} problems (use for _NUMBER filenames)")
    except Exception:
        pass
    for idx, (path, content, cur_title, cur_link, query, is_py) in enumerate(to_fix_list, 1):
        url = None
        new_title = cur_title or ""
        # 1) Prefer LeetCode API when filename ends with _NUMBER
        num_match = re.search(r"_(\d+)$", path.stem)
        if num_match and leetcode_map:
            problem_id = int(num_match.group(1))
            api_result = get_leetcode_url_by_number(problem_id, leetcode_map)
            if api_result:
                url, new_title = api_result
        # 2) Browser: open Google search and prompt for paste
        if not url and use_browser:
            search_url = "https://www.google.com/search?q=" + quote_plus(query)
            webbrowser.open(search_url)
            prompt = f"[{idx}/{len(to_fix_list)}] Paste LeetCode/GFG URL (or Enter for fallback): "
            try:
                pasted = input(prompt).strip()
            except (EOFError, KeyboardInterrupt):
                pasted = ""
            if pasted and pasted.startswith("http"):
                url = pasted.split("?")[0].rstrip("/") or pasted
                new_title = title_from_link_or_query(url, query, cur_title)
        # 3) Search (Google/DDG) or slug fallback
        if not url:
            url = search_for_link(
                query, fallback_title=cur_title, base_name=path.stem)
            new_title = title_from_link_or_query(url or "", query, cur_title)
        if not url:
            not_found.append((path, query))
            continue
        if not new_title:
            new_title = title_from_link_or_query(url, query, cur_title)
        new_content = update_file_header(path, content, new_title, url, is_py)
        if new_content == content:
            continue  # no change (header format might not match)
        if not args.dry_run:
            path.write_text(new_content, encoding="utf-8")
        fixed += 1
        print(
            f"  {'[dry-run] ' if args.dry_run else ''}Updated: {path.relative_to(REPO_ROOT)} -> {url[:70]}...")
    print(f"Fixed: {fixed} files")
    if not_found:
        nf_path = REPO_ROOT / "GsheetTrackers" / "FilesNotFixed_NoLinkFound.md"
        nf_lines = ["# Files not updated (no link found)", ""]
        for path, q in not_found:
            nf_lines.append(f"- {path.relative_to(REPO_ROOT)} (query: {q})")
        nf_path.write_text("\n".join(nf_lines), encoding="utf-8")
        print(f"No link found: {len(not_found)} files -> {nf_path}")


if __name__ == "__main__":
    main()
