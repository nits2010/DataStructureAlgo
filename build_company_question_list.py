#!/usr/bin/env python3
"""
Scan repo for coding questions with company tags and generate a markdown table.

Columns: Question title | Question link | File name | Company tags

Usage:
  python build_company_question_list.py       # POC: only LeetCode2025 subfolders (see POC_LEETCODE2025_SUBFOLDERS)
  python build_company_question_list.py --full # Full scan: LeetCode2025, then CompanyWise, then Other

Priority: 1) LeetCode2025  2) CompanyWise  3) Other
Ignore folders: helpers, sorts, python
Skip: helper-only files (Node, Pair, ListNode, etc.). Include only files that have a "Company Tags" section in comments.
Company tags are read from the comment block after "Company Tags" (e.g. @Amazon, @Google) until @Editorial/@OptimalSolution.
"""

import os
import re
from pathlib import Path
from typing import List, Optional, Tuple

# Folders to ignore (exact segment name in path)
IGNORE_FOLDERS = {"helpers", "sorts", "python"}

# File base names that are helpers only (no question) - skip these
HELPER_FILE_NAMES = {
    "node", "pair", "listnode", "doublylistnode", "treenode",
    "listbuilder", "treebuilder", "commonmethods", "nestedinteger",
    "nestedintegervalue", "customkey", "user", "split", "expensetype",
    "percentexpense", "main", "keyvaluestore",
}

# Difficulty tags (case-insensitive)
DIFFICULTY_PATTERN = re.compile(r"@(easy|medium|hard)\b", re.I)

# Tags that are NOT company names (topic/difficulty/meta only)
NON_COMPANY_TAGS = {
    "easy", "medium", "hard", "editorial", "optimalsolution",
    "array", "string", "hashtable", "dynamicprogramming", "linkedlist",
    "tree", "graph", "binarysearch", "twopointers", "stack", "heap",
    "backtracking", "slidingwindow", "design", "math", "recursion",
    "dfs", "bfs", "greedy", "sorting", "binarytree", "matrix",
    "leetcodelockedproblem", "premiumquestion", "duplicate", "similar",
    "extension", "dpbaseproblem", "baseproblem", "ref", "link", "p", "pp",
}

# Repo root = directory containing this script (so it works when script is at repo root or in GsheetTrackers)
_script_dir = Path(__file__).resolve().parent
# If script is inside GsheetTrackers, repo root is its parent; else repo root is script dir
REPO_ROOT = _script_dir.parent if _script_dir.name == "GsheetTrackers" else _script_dir
CODE_EXTENSIONS = {".py", ".java", ".js", ".ts", ".tsx"}

# --- POC: limit to these path segments under LeetCode2025 (empty = no limit)
# Example: only scan ProblemSet/DynamicProgramming/_322 and _518 and _5
POC_LEETCODE2025_SUBFOLDERS = [
    "ProblemSet/DynamicProgramming/_322",
    "ProblemSet/DynamicProgramming/_518",
    "ProblemSet/DynamicProgramming/_5",
]


def path_should_ignore(rel_path: Path) -> bool:
    parts = rel_path.parts
    for p in parts:
        if p in IGNORE_FOLDERS:
            return True
    return False


def is_helper_file(base_name: str) -> bool:
    name_lower = base_name.lower()
    if name_lower in HELPER_FILE_NAMES:
        return True
    # e.g. Node.java, Pair.java
    for h in HELPER_FILE_NAMES:
        if name_lower == h or name_lower.startswith(h) and name_lower[len(h):].isdigit():
            return True
    return False


def normalize_tag(tag: str) -> str:
    t = tag.strip().lstrip("@").strip()
    return t


def is_company_tag(tag: str) -> bool:
    if not tag or len(tag) < 2:
        return False
    t = tag.lower()
    if t in NON_COMPANY_TAGS:
        return False
    # Company names are usually PascalCase / single word
    if not re.match(r"^[A-Za-z][A-Za-z0-9]*$", tag):
        return False
    return True


def extract_difficulty(block_text: str) -> str:
    """Return first @easy, @medium, or @hard (case-insensitive), else empty string."""
    m = DIFFICULTY_PATTERN.search(block_text)
    return m.group(1).lower() if m else ""


def extract_company_tags_from_section(lines: List[str], start_idx: int) -> Tuple[List[str], int]:
    """From lines[start_idx] onward, collect @Company until @Editorial or @OptimalSolution or section end."""
    companies = []
    i = start_idx
    stop_pattern = re.compile(r"@(Editorial|OptimalSolution)\b", re.I)
    tag_pattern = re.compile(r"@([A-Za-z][A-Za-z0-9]*)")

    while i < len(lines):
        line = lines[i]
        if stop_pattern.search(line):
            break
        for m in tag_pattern.finditer(line):
            raw = m.group(1)
            # Handle @@Oracle -> Oracle
            if raw.startswith("@"):
                raw = raw[1:]
            if is_company_tag(raw):
                companies.append(raw)
        i += 1
    return companies, i


def extract_question_info(file_path: Path, content: str) -> Optional[dict]:
    """
    Parse file content for question title, link, and company tags.
    Returns dict with keys: title, link, file_name, companies; or None if no company tags.
    """
    file_name = file_path.name
    base_name = file_path.stem
    if is_helper_file(base_name):
        return None

    title = ""
    link = ""
    companies = []
    difficulty = ""

    # Python: docstring """ ... """
    if file_path.suffix == ".py":
        doc_match = re.search(r'"""([\s\S]*?)"""', content)
        if doc_match:
            block = doc_match.group(1)
            difficulty = extract_difficulty(block)
            lines = block.split("\n")
            for i, line in enumerate(lines):
                if re.match(r"Question Title:\s*", line, re.I):
                    title = re.sub(r"Question Title:\s*", "",
                                   line, flags=re.I).strip()
                if re.match(r"Link:\s*", line, re.I):
                    link = re.sub(r"Link:\s*", "", line, flags=re.I).strip()
                if "Company Tags" in line:
                    comps, _ = extract_company_tags_from_section(lines, i + 1)
                    companies = comps
                    break
    else:
        # Java/JS/TS: /* ... */ or /** ... */
        block_match = re.search(r"/\*\*?([\s\S]*?)\*/", content)
        if block_match:
            block = block_match.group(1)
            difficulty = extract_difficulty(block)
            lines = block.split("\n")
            for i, line in enumerate(lines):
                # Java: * Question Category: 21. Merge Two Sorted Lists [EASY] or Description: https://...
                if not title and re.search(r"Question Category:\s*", line, re.I):
                    title = re.sub(
                        r"^[\s*]*Question Category:\s*", "", line, flags=re.I).strip()
                    title = re.sub(r"\s*\[EASY\]\s*$", "",
                                   title, flags=re.I).strip()
                if not title and re.search(r"Question Title:\s*", line, re.I):
                    title = re.sub(r"^[\s*]*Question Title:\s*",
                                   "", line, flags=re.I).strip()
                if not link and (re.search(r"Description:\s*https://leetcode", line, re.I) or re.search(r"Link:\s*https://", line, re.I)):
                    m = re.search(r"https://[^\s\)\]]+", line)
                    if m:
                        link = m.group(0).rstrip(">")
                if not link and "leetcode.com" in line:
                    m = re.search(r"https://leetcode\.com[^\s\)\]]+", line)
                    if m:
                        link = m.group(0).rstrip(">")
                if "Company Tags" in line:
                    comps, _ = extract_company_tags_from_section(lines, i + 1)
                    companies = comps
                    break

    # Only include if we have at least one company tag
    if not companies:
        return None

    if not title:
        title = base_name
    if not link and "_" in base_name:
        # Try to infer LeetCode link from filename like CoinChange_322 or _322
        num_match = re.search(r"_(\d+)$", base_name)
        if num_match:
            link = f"https://leetcode.com/problems/ (see #{num_match.group(1)})"

    return {
        "title": title,
        "link": link or "(no link)",
        "file_name": file_name,
        "difficulty": difficulty,
        "companies": sorted(set(companies)),
    }


def collect_files(root: Path, poc: bool) -> List[Tuple[int, Path]]:
    """
    Walk repo and collect code files. Returns list of (priority, path) where
    priority 0 = LeetCode2025, 1 = CompanyWise, 2 = Other.
    When poc=True, only scan POC_LEETCODE2025_SUBFOLDERS (no CompanyWise/Other).
    """
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
            rel_path = p.relative_to(root)
            rel_str = str(rel_path).replace("\\", "/")

            if "LeetCode2025" in rel_str:
                if poc:
                    if not any(seg in rel_str for seg in POC_LEETCODE2025_SUBFOLDERS):
                        continue
                priority = 0
            elif poc:
                # POC: skip CompanyWise and Other
                continue
            elif "companyWise" in rel_str or "CompanyWise" in rel_str:
                priority = 1
            else:
                priority = 2
            out.append((priority, p))
    # Deduplicate by resolved path (e.g. symlinks or same file via different rel path)
    seen = set()
    unique = []
    for pri, p in out:
        r = p.resolve()
        if r not in seen:
            seen.add(r)
            unique.append((pri, p))
    unique.sort(key=lambda x: (x[0], str(x[1])))
    return unique


def run(poc: bool = True, output_md: Optional[Path] = None) -> str:
    if output_md is None:
        output_md = REPO_ROOT / "CompanyQuestionList.md"
    rows = []
    seen_paths = set()  # resolved path to avoid duplicate files

    files = collect_files(REPO_ROOT, poc=poc)
    for _priority, path in files:
        try:
            resolved = path.resolve()
            if resolved in seen_paths:
                continue
            content = path.read_text(encoding="utf-8", errors="replace")
        except Exception:
            continue
        info = extract_question_info(path, content)
        if info is None:
            continue
        seen_paths.add(resolved)
        rows.append(info)
    # Deduplicate rows by (title, link, file_name) in case same file was scanned twice
    seen_row = set()
    unique_rows = []
    for r in rows:
        key = (r["title"], r["link"], r["file_name"])
        if key not in seen_row:
            seen_row.add(key)
            unique_rows.append(r)
    rows = unique_rows
    # Build markdown table
    md_lines = [
        "# Company-based question list",
        "",
    ]
    if poc:
        md_lines.append("*Generated in POC mode (LeetCode2025 subfolders: " + ", ".join(
            POC_LEETCODE2025_SUBFOLDERS) + "). Run with `--full` for full repo scan.*")
        md_lines.append("")
    md_lines.extend([
        "| Question title | Question link | File name | Difficulty | Company tags |",
        "|----------------|---------------|-----------|------------|--------------|",
    ])
    for r in rows:
        title_esc = r["title"].replace("|", "\\|").strip()
        link_esc = r["link"].replace("|", "\\|").strip()
        file_esc = r["file_name"].replace("|", "\\|")
        diff = r.get("difficulty", "") or "-"
        tags_esc = ", ".join(r["companies"]).replace("|", "\\|")
        md_lines.append(
            f"| {title_esc} | {link_esc} | {file_esc} | {diff} | {tags_esc} |")
    md_lines.append("")
    md_content = "\n".join(md_lines)
    output_md.write_text(md_content, encoding="utf-8")
    return output_md


if __name__ == "__main__":
    import sys
    poc = "--full" not in sys.argv
    out = run(poc=poc)
    print(f"POC mode: {poc}. Wrote: {out}")
