#!/usr/bin/env python3
"""
Scan repo for coding questions with company tags and generate a markdown table.

Columns: # | Question Title | Question Link | File Name | Difficulty | Company Tags

Features:
- Incremental updates: Only processes files created/modified since last run
- Timestamp tracking: Maintains .last_company_list_run file with timestamp
- Configurable sort order: Sort by creation date (newest first or oldest first)
- GitHub file links: Uses current git branch for file links

Usage:
  python3 build_company_question_list.py                     # Incremental updates (newest first)
  python3 build_company_question_list.py --force-full        # Full scan ignoring timestamp
  python3 build_company_question_list.py --sort-order oldest # Sort oldest files first
  python3 build_company_question_list.py --sort-order newest # Sort newest files first (default)

Priority: 1) LeetCode2025  2) CompanyWise  3) Other
Ignore folders: helpers, sorts, python
Skip: helper-only files (Node, Pair, ListNode, etc.). Include only files that have a "Company Tags" section in comments.
Company tags are read from the comment block after "Company Tags" (e.g. @Amazon, @Google) until @Editorial/@OptimalSolution.
"""

import os
import re
import subprocess
import time
from datetime import datetime
from pathlib import Path
from typing import List, Optional, Tuple

def get_current_git_branch():
    try:
        # Run the 'git branch' command and capture its output
        result = subprocess.run(["git", "branch", "--show-current"],
                                stdout=subprocess.PIPE,
                                stderr=subprocess.PIPE,
                                text=True,
                                check=True)
        # Return the branch name
        return result.stdout.strip()
    except subprocess.CalledProcessError as e:
        return "main"  # Default fallback


def get_last_run_timestamp() -> float:
    """Get the last run timestamp from file, or 0 if not found."""
    try:
        if TIMESTAMP_FILE.exists():
            return float(TIMESTAMP_FILE.read_text().strip())
    except (ValueError, FileNotFoundError):
        pass
    return 0.0


def save_current_timestamp() -> None:
    """Save current timestamp to file."""
    current_time = time.time()
    TIMESTAMP_FILE.write_text(str(current_time))
    print(f"Timestamp saved: {datetime.fromtimestamp(current_time).strftime('%Y-%m-%d %H:%M:%S')}")


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

# GitHub URL generation
branch = get_current_git_branch()
BASE_URL = f"https://github.com/nits2010/DataStructureAlgo/blob/{branch}"

# Timestamp tracking for incremental updates
TIMESTAMP_FILE = REPO_ROOT / ".last_company_list_run"

# Scan entire repository for all files


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
    Returns dict with keys: title, link, file_name, github_link, create_time, mod_time, companies; or None if no company tags.
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

    # Generate GitHub link
    relative_path = file_path.relative_to(REPO_ROOT).as_posix()
    github_link = f"{BASE_URL}/{relative_path}"
    
    # Get file creation time (preferred) or modification time as fallback
    file_stat = file_path.stat()
    create_time = getattr(file_stat, 'st_birthtime', file_stat.st_ctime)
    mod_time = file_stat.st_mtime
    
    return {
        "title": title,
        "link": link or "(no link)",
        "file_name": file_name,
        "github_link": github_link,
        "create_time": create_time,
        "mod_time": mod_time,
        "difficulty": difficulty,
        "companies": sorted(set(companies)),
    }


def deduplicate_problems(rows: List[dict]) -> List[dict]:
    """
    Group multiple solutions (Java/Python) for the same problem and merge them.
    Groups by question link and title, combining file information.
    """
    from collections import defaultdict
    
    # Group rows by problem identity (link + title as fallback)
    groups = defaultdict(list)
    for row in rows:
        # Use question link as primary key, fall back to title if no link
        link = row["link"]
        title = row["title"]
        
        # Create a normalized key for grouping
        if link and link != "(no link)":
            # Extract problem number from link if possible for better matching
            import re
            prob_num_match = re.search(r'/problems/[^/]+', link)
            if prob_num_match:
                key = link  # Use full link as key
            else:
                key = f"title:{title}"
        else:
            key = f"title:{title}"
        
        groups[key].append(row)
    
    # Merge groups that have multiple solutions
    merged_rows = []
    for group_key, group_rows in groups.items():
        if len(group_rows) == 1:
            # Single solution, keep as-is
            merged_rows.append(group_rows[0])
        else:
            # Multiple solutions for same problem, merge them
            merged_row = merge_problem_solutions(group_rows)
            merged_rows.append(merged_row)
    
    return merged_rows


def merge_problem_solutions(solutions: List[dict]) -> dict:
    """
    Merge multiple solutions for the same problem into a single row.
    """
    if len(solutions) == 1:
        return solutions[0]
    
    # Sort solutions by create_time to use the most recent for base info
    solutions.sort(key=lambda x: x["create_time"], reverse=True)
    base_solution = solutions[0]
    
    # Combine file names and GitHub links, avoiding duplicates
    file_entries = []
    seen_files = set()
    all_companies = set()
    difficulties = set()
    
    for sol in solutions:
        file_key = sol['file_name']  # Use filename as key for deduplication
        
        if file_key not in seen_files:
            seen_files.add(file_key)
            file_entries.append(f"[{sol['file_name']}]({sol['github_link']})")
        
        # Collect companies and difficulties
        all_companies.update(sol["companies"])
        if sol.get("difficulty"):
            difficulties.add(sol["difficulty"])
    
    # Choose best title (prefer more generic or complete titles)
    title = choose_best_title([sol["title"] for sol in solutions])
    
    # Create merged row
    merged = {
        "title": title,
        "link": base_solution["link"], 
        "file_name": " | ".join(file_entries),  # Combined file links
        "github_link": "",  # Not used since we have combined links in file_name
        "create_time": base_solution["create_time"],  # Use most recent
        "mod_time": base_solution["mod_time"],
        "companies": sorted(list(all_companies)),
        "difficulty": list(difficulties)[0] if difficulties else base_solution.get("difficulty", "")
    }
    
    return merged


def choose_best_title(titles: List[str]) -> str:
    """
    Choose the best title from a list of titles for the same problem.
    Prefer more complete or generic titles.
    """
    if not titles:
        return ""
    
    if len(titles) == 1:
        return titles[0]
    
    # Remove duplicates while preserving order
    unique_titles = []
    for title in titles:
        if title not in unique_titles:
            unique_titles.append(title)
    
    if len(unique_titles) == 1:
        return unique_titles[0]
    
    # Prefer titles that don't contain implementation-specific terms
    non_specific = []
    for title in unique_titles:
        title_lower = title.lower()
        if not any(term in title_lower for term in ["recursive", "iterative", "solution", "approach"]):
            non_specific.append(title)
    
    if non_specific:
        # Return the longest non-specific title (usually most complete)
        return max(non_specific, key=len)
    
    # If all are specific, return the longest one
    return max(unique_titles, key=len)


def collect_files(root: Path, since_timestamp: float = 0.0) -> List[Tuple[int, Path]]:
    """
    Walk repo and collect code files. Returns list of (priority, path) where
    priority 0 = LeetCode2025, 1 = CompanyWise, 2 = Other.
    Only includes files modified after since_timestamp (0.0 means all files).
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
            
            # Check if file was created/modified after the given timestamp
            try:
                file_stat = p.stat()
                # Use creation time (birthtime on macOS, ctime on other systems)
                file_create_time = getattr(file_stat, 'st_birthtime', file_stat.st_ctime)
                # For incremental updates, check both creation and modification time
                file_mod_time = file_stat.st_mtime
                latest_time = max(file_create_time, file_mod_time)
                
                if latest_time <= since_timestamp:
                    continue  # Skip files not created/modified since last run
            except OSError:
                continue  # Skip if can't get file stats
            
            rel_path = p.relative_to(root)
            rel_str = str(rel_path).replace("\\", "/")

            if "LeetCode2025" in rel_str:
                priority = 0
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


def run(output_md: Optional[Path] = None, incremental: bool = True, sort_order: str = "newest") -> str:
    if output_md is None:
        output_md = REPO_ROOT / "CompanyQuestionList.md"
    
    # Get last run timestamp for incremental updates
    last_timestamp = get_last_run_timestamp() if incremental else 0.0
    
    if incremental and last_timestamp > 0:
        print(f"Incremental update since: {datetime.fromtimestamp(last_timestamp).strftime('%Y-%m-%d %H:%M:%S')}")
    else:
        print("Full scan (no previous timestamp found or incremental disabled)")
    
    rows = []
    seen_paths = set()  # resolved path to avoid duplicate files

    files = collect_files(REPO_ROOT, since_timestamp=last_timestamp)
    
    if not files:
        if incremental and last_timestamp > 0:
            print("No files modified since last run. No update needed.")
            return str(output_md)
        else:
            print("No files found matching criteria.")
            return str(output_md)
    
    print(f"Processing {len(files)} files...")
    
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
    # Deduplicate rows by (title, link, file_name, github_link) in case same file was scanned twice
    seen_row = set()
    unique_rows = []
    for r in rows:
        key = (r["title"], r["link"], r["file_name"], r["github_link"])
        if key not in seen_row:
            seen_row.add(key)
            unique_rows.append(r)
    rows = unique_rows
    
    # Group and merge multiple solutions for the same problem
    rows = deduplicate_problems(rows)
    
    # Sort by creation time based on sort_order
    reverse_sort = sort_order.lower() == "newest"
    rows.sort(key=lambda x: x["create_time"], reverse=reverse_sort)
    # Build markdown table with unified structure
    current_time = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    sort_desc = "newest first" if sort_order.lower() == "newest" else "oldest first"
    md_lines = [
        "# Company-based question list",
        "",
        f"**Generated on:** {current_time}",
        f"**Sort order:** {sort_desc}",
    ]
    
    if incremental and last_timestamp > 0:
        md_lines.append(f"**Incremental update since:** {datetime.fromtimestamp(last_timestamp).strftime('%Y-%m-%d %H:%M:%S')}")
    else:
        md_lines.append("**Mode:** Full scan")
    
    md_lines.append("")
    
    # Table headers with new structure
    md_lines.extend([
        "| # | Question Title | Question Link | File Name | Difficulty | Company Tags |",
        "|---|----------------|---------------|-----------|------------|--------------|",
    ])
    
    # Generate table rows with new format
    for idx, r in enumerate(rows, 1):
        # Escape pipe characters for markdown
        title_esc = r["title"].replace("|", "\\|").strip()
        
        # Handle question link formatting
        question_link = r['link']
        if question_link and question_link != "(no link)":
            link_esc = f"[Link]({question_link})".replace("|", "\\|")
        else:
            link_esc = "(no link)"
        
        # File name with GitHub link (handle combined files)
        if r['github_link']:
            # Single file
            file_link = f"[{r['file_name']}]({r['github_link']})".replace("|", "\\|")
        else:
            # Combined files (already formatted)
            file_link = r['file_name'].replace("|", "\\|")
        
        # Difficulty
        diff = r.get("difficulty", "").title() if r.get("difficulty") else "-"
        
        # Company tags
        tags_esc = ", ".join(r["companies"]).replace("|", "\\|")
        
        md_lines.append(f"| {idx} | {title_esc} | {link_esc} | {file_link} | {diff} | {tags_esc} |")
    
    md_lines.append("")
    md_content = "\n".join(md_lines)
    output_md.write_text(md_content, encoding="utf-8")
    
    # Save timestamp after successful completion
    save_current_timestamp()
    
    sort_desc = "newest first" if sort_order.lower() == "newest" else "oldest first"
    print(f"Generated {len(rows)} entries (sorted by creation date, {sort_desc})")
    return output_md


if __name__ == "__main__":
    import sys
    incremental = "--force-full" not in sys.argv  # Use --force-full to disable incremental
    
    # Parse sort order argument
    sort_order = "newest"  # default
    for i, arg in enumerate(sys.argv):
        if arg == "--sort-order" and i + 1 < len(sys.argv):
            next_arg = sys.argv[i + 1].lower()
            if next_arg in ["newest", "oldest"]:
                sort_order = next_arg
            else:
                print(f"Invalid sort order: {sys.argv[i + 1]}. Use 'newest' or 'oldest'.")
                sys.exit(1)
            break
    
    out = run(incremental=incremental, sort_order=sort_order)
    
    update_desc = "incremental" if incremental else "full scan"
    sort_desc = "newest first" if sort_order == "newest" else "oldest first"
    print(f"Mode: FULL ({update_desc}, {sort_desc}). Wrote: {out}")
