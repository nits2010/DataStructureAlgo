#!/usr/bin/env python3
"""
Standardize file headers to match the standard templates.

This script ensures all Java and Python files follow the standard template format:
- Java: /** */ comment block with standard fields
- Python: """ """ docstring with standard fields

1. Scans all .java and .py files (excluding ignored folders)
2. Checks if files have the proper header format matching templates
3. Updates ONLY the header comment to match template (preserves rest of file)
4. Maintains existing Question Title and Link if they exist and are valid

Usage:
  python3 standardize_file_headers.py --analyze    # Show files that need standardization
  python3 standardize_file_headers.py --fix        # Update file headers to standard format
  python3 standardize_file_headers.py --fix --dry-run  # Show what would be changed

Templates: fileTemplate.py (Python), fileTemplate.java (Java)
"""

import re
import sys
from datetime import datetime
from pathlib import Path
from typing import Dict, List, Optional, Tuple

# Reuse logic from existing script
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
    # Repository root (go up 2 levels from scripts/generators/)
    REPO_ROOT = Path(__file__).resolve().parent.parent.parent
    IGNORE_FOLDERS = {"helpers", "sorts", "python", ".idea*", "fileTemplates*"}
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
            rel = Path(dirpath).relative_to(root) if dirpath != root_str else Path(".")
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


class TemplateStandardizer:
    """Handles standardization of file headers to match templates."""
    
    def __init__(self):
        self.java_template = self._load_java_template()
        self.python_template = self._load_python_template()
    
    def _load_java_template(self) -> str:
        """Load Java template and extract the comment structure."""
        template_path = REPO_ROOT / "scripts" / "templates" / "fileTemplate.java"
        if not template_path.exists():
            return self._get_default_java_template()
        
        content = template_path.read_text(encoding="utf-8")
        # Extract the /** */ comment block
        match = re.search(r'/\*\*(.*?)\*/', content, re.DOTALL)
        if match:
            return match.group(0)
        return self._get_default_java_template()
    
    def _load_python_template(self) -> str:
        """Load Python template and extract the docstring structure."""
        template_path = REPO_ROOT / "scripts" / "templates" / "fileTemplate.py"
        if not template_path.exists():
            return self._get_default_python_template()
        
        content = template_path.read_text(encoding="utf-8")
        # Extract the """ """ docstring
        match = re.search(r'"""(.*?)"""', content, re.DOTALL)
        if match:
            return match.group(0)
        return self._get_default_python_template()
    
    def _get_default_java_template(self) -> str:
        """Default Java template if fileTemplate.java not found."""
        return """/**
 * Author: Nitin Gupta
 * Date: {date}
 * Question Title: {title}
 * Link: {link}
 * Description:
 * File reference
 * -----------
 * Duplicate {{@link}}
 * Similar {{@link}}
 * extension {{@link }}
 * DP-BaseProblem {{@link }}
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {{@link }}
 */"""

    def _get_default_python_template(self) -> str:
        """Default Python template if fileTemplate.py not found."""
        return '''"""
Author: Nitin Gupta
Date: {date}
Question Title: {title}
Link: {link}
Description:
File reference
-----------
Duplicate {{@link}}
Similar {{@link}}
extension {{@link }}
DP-BaseProblem {{@link }}
<p><p>
Tags
-----

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {{@link }}
"""'''

    def extract_existing_info(self, content: str, is_python: bool) -> Dict[str, str]:
        """Extract existing information only from file-level headers, not class docstrings."""
        info = {
            "title": "", 
            "link": "", 
            "description": "", 
            "raw_content": "",
            "has_standard_template": False,
            "author": "",
            "date": "",
            "existing_fields": {}
        }
        
        # Only look for file-level headers, not class/function docstrings
        if is_python:
            # For Python: only look at the very beginning of file (first docstring)
            lines = content.split('\n')
            start_idx = 0
            
            # Skip shebang and initial comments/empty lines
            if lines and lines[0].startswith('#!'):
                start_idx = 1
            
            # Skip comments and empty lines at the beginning
            while start_idx < len(lines):
                line = lines[start_idx].strip()
                if not line or line.startswith('#'):
                    start_idx += 1
                elif line.startswith('"""'):
                    # Found potential file-level docstring
                    break
                else:
                    # Reached code/imports - no file-level docstring
                    return info
            
            # Look for docstring starting from this position
            if start_idx < len(lines) and '"""' in lines[start_idx]:
                match = re.search(r'"""(.*?)"""', '\n'.join(lines[start_idx:]), re.DOTALL)
                if match:
                    header = match.group(1)
                    info["raw_content"] = header
                    self._extract_fields_from_header(header, info, False)
        else:
            # For Java: look for /** */ comment before any class definitions
            # Only consider it file-level if it comes before class/interface definitions
            before_class = re.split(r'\b(class|interface)\s+\w+', content)[0]
            match = re.search(r'/\*\*(.*?)\*/', before_class, re.DOTALL)
            if match:
                header = match.group(1)
                info["raw_content"] = header
                self._extract_fields_from_header(header, info, True)
        
        return info
    
    def _extract_fields_from_header(self, header: str, info: Dict[str, str], is_java: bool):
        """Extract structured information from header content."""
        # Check if this already has our standard template
        info["has_standard_template"] = (
            "Author: Nitin Gupta" in header and 
            "Company Tags" in header and
            "@Editorial" in header
        )
        
        # Extract structured fields if they exist
        fields = {
            "Question Title": r'Question Title:\s*(.+?)(?:\n|$)',
            "Link": r'Link:\s*(.+?)(?:\n|$)', 
            "Author": r'Author:\s*(.+?)(?:\n|$)',
            "Date": r'Date:\s*(.+?)(?:\n|$)',
        }
        
        for field, pattern in fields.items():
            match_field = re.search(pattern, header, re.MULTILINE)
            if match_field:
                value = match_field.group(1).strip()
                if is_java:
                    value = value.lstrip('*').strip()
                info["existing_fields"][field] = value
                if field == "Question Title":
                    info["title"] = value
                elif field == "Link":
                    info["link"] = value
                elif field == "Author":
                    info["author"] = value
                elif field == "Date": 
                    info["date"] = value
        
        # Extract unstructured content (problem descriptions, examples, etc.) only from non-standard headers
        if not info["has_standard_template"]:
            lines = header.split('\n')
            content_lines = []
            
            for line in lines:
                clean_line = line.strip()
                if is_java:
                    clean_line = clean_line.lstrip('*').strip()
                
                # Skip empty lines and basic metadata
                if (clean_line and 
                    not clean_line.startswith('Author:') and
                    not clean_line.startswith('Date:') and 
                    not clean_line.startswith('Question Title:') and
                    not clean_line.startswith('Link:')):
                    content_lines.append(clean_line)
            
            if content_lines:
                info["description"] = '\n'.join(content_lines)
        
        # Look for URLs in the content (these are valuable links to preserve)
        url_matches = re.findall(r'https?://[^\s\)\]]+', header)
        if url_matches and not info["link"]:
            info["link"] = url_matches[0]  # Use first URL found

    def generate_merged_header(self, is_python: bool, existing_info: Dict[str, str], 
                             filename: str = "") -> str:
        """Generate header by intelligently merging existing content with standard template."""
        
        # If already has standard template, only update missing fields
        if existing_info["has_standard_template"]:
            return self._update_existing_standard_header(existing_info, is_python, filename)
        
        # Build new standardized header preserving existing valuable content
        current_date = datetime.now().strftime("%Y-%m-%d")
        
        # Use existing info or derive from filename
        title = existing_info["title"] or self._derive_title_from_filename(filename)
        link = existing_info["link"] or self._derive_link_from_filename(filename)
        author = existing_info["author"] or "Nitin Gupta"
        date = existing_info["date"] or current_date
        
        # Preserve existing description/content
        description = existing_info["description"] or ""
        
        if is_python:
            return self._build_python_header(title, link, author, date, description)
        else:
            return self._build_java_header(title, link, author, date, description)

    def _update_existing_standard_header(self, existing_info: Dict[str, str], 
                                       is_python: bool, filename: str) -> str:
        """Update existing standard header, only filling missing fields."""
        header = existing_info["raw_content"]
        
        # Only update if fields are missing or placeholder values
        if not existing_info["title"] or existing_info["title"] == "file Template":
            new_title = self._derive_title_from_filename(filename)
            header = re.sub(r'Question Title:\s*.*?(?=\n)', f'Question Title: {new_title}', header)
        
        if not existing_info["link"] or "file-template" in existing_info["link"]:
            new_link = self._derive_link_from_filename(filename)
            header = re.sub(r'Link:\s*.*?(?=\n)', f'Link: {new_link}', header)
        
        if is_python:
            return f'"""\n{header}\n"""'
        else:
            return f'/**\n{header}\n*/'
    
    def _build_python_header(self, title: str, link: str, author: str, date: str, 
                           existing_description: str) -> str:
        """Build Python docstring header preserving existing content."""
        lines = [
            '"""',
            f'Author: {author}',
            f'Date: {date}',
            f'Question Title: {title}',
            f'Link: {link}',
        ]
        
        if existing_description:
            lines.extend([
                'Description:',
                existing_description,
            ])
        else:
            lines.append('Description:')
        
        lines.extend([
            'File reference',
            '-----------',
            'Duplicate {@link}',
            'Similar {@link}',
            'extension {@link }',
            'DP-BaseProblem {@link }',
            '<p><p>',
            'Tags',
            '-----',
            '',
            '<p><p>',
            'Company Tags',
            '-----',
            '<p>',
            '-----',
            '',
            '@Editorial <p><p>',
            '-----',
            '@OptimalSolution {@link }',
            '"""'
        ])
        
        return '\n'.join(lines)
    
    def _build_java_header(self, title: str, link: str, author: str, date: str,
                          existing_description: str) -> str:
        """Build Java comment header preserving existing content."""
        lines = [
            '/**',
            f' * Author: {author}',
            f' * Date: {date}',
            f' * Question Title: {title}',
            f' * Link: {link}',
        ]
        
        if existing_description:
            lines.append(' * Description:')
            # Add existing description with proper comment formatting
            desc_lines = existing_description.split('\n')
            for desc_line in desc_lines:
                lines.append(f' * {desc_line}' if desc_line.strip() else ' *')
        else:
            lines.append(' * Description:')
        
        lines.extend([
            ' * File reference',
            ' * -----------',
            ' * Duplicate {@link}',
            ' * Similar {@link}',
            ' * extension {@link }',
            ' * DP-BaseProblem {@link }',
            ' * <p><p>',
            ' * Tags',
            ' * -----',
            ' *',
            ' <p><p>',
            ' * Company Tags',
            ' * -----',
            ' * <p>',
            ' * -----',
            ' * @Editorial <p><p>',
            ' * -----',
            ' * @OptimalSolution {@link }',
            ' */'
        ])
        
        return '\n'.join(lines)

    def _derive_title_from_filename(self, filename: str) -> str:
        """Derive a reasonable title from filename."""
        if not filename:
            return "TODO: Add Question Title"
        
        # Remove file extension and number suffix
        name = Path(filename).stem
        name = re.sub(r'_\d+$', '', name)  # Remove _123 suffix
        
        # Convert CamelCase to words
        name = re.sub(r'([a-z])([A-Z])', r'\1 \2', name)
        name = re.sub(r'([A-Z])([A-Z][a-z])', r'\1 \2', name)
        
        return name.strip() or "TODO: Add Question Title"

    def _derive_link_from_filename(self, filename: str) -> str:
        """Derive a reasonable link from filename."""
        if not filename:
            return "TODO: Add Link"
        
        name = Path(filename).stem
        
        # If filename has number suffix, try to build LeetCode URL
        match = re.search(r'_(\d+)$', name)
        if match:
            problem_num = match.group(1)
            # Convert to slug format
            base_name = re.sub(r'_\d+$', '', name)
            slug = re.sub(r'([a-z])([A-Z])', r'\1-\2', base_name).lower()
            slug = re.sub(r'[^a-z0-9-]', '-', slug)
            slug = re.sub(r'-+', '-', slug).strip('-')
            return f"https://leetcode.com/problems/{slug}/"
        
        return "TODO: Add Link"

    def needs_standardization(self, content: str, is_python: bool) -> bool:
        """Check if file needs header standardization or merging."""
        existing_info = self.extract_existing_info(content, is_python)
        
        # If no header exists at all, definitely needs standardization
        if not existing_info["raw_content"]:
            return True
        
        # If already has standard template, check if any critical fields are missing
        if existing_info["has_standard_template"]:
            return (
                not existing_info["title"] or 
                existing_info["title"] == "file Template" or
                not existing_info["link"] or
                "file-template" in existing_info.get("link", "")
            )
        
        # File has content but not in standard format - needs merging
        return True

    def replace_header(self, content: str, is_python: bool, filename: str = "") -> str:
        """Add standardized header at the top of file, preserving all existing content."""
        # Extract existing info for intelligent merging
        existing_info = self.extract_existing_info(content, is_python)
        
        # Generate merged header that preserves valuable content
        new_header = self.generate_merged_header(is_python, existing_info, filename)
        
        if is_python:
            # SIMPLE APPROACH FOR PYTHON: Always add header at the very top
            lines = content.split('\n')
            
            # Find insertion point (after shebang only)
            insert_pos = 0
            if lines and lines[0].startswith('#!'):
                insert_pos = 1
            
            # Insert header at the top
            lines.insert(insert_pos, new_header)
            lines.insert(insert_pos + 1, '')  # Add blank line after header
            
            return '\n'.join(lines)
        else:
            # SIMPLE APPROACH FOR JAVA: Add header after package/imports
            lines = content.split('\n')
            
            # Find insertion point after package/imports
            insert_pos = 0
            for i, line in enumerate(lines):
                stripped = line.strip()
                if (stripped.startswith('package ') or 
                    stripped.startswith('import ') or
                    stripped == '' or
                    stripped.startswith('//')):
                    insert_pos = i + 1
                else:
                    break
            
            # Insert header
            lines.insert(insert_pos, new_header)
            lines.insert(insert_pos + 1, '')  # Add blank line after header
            
            return '\n'.join(lines)


def main():
    import argparse
    
    parser = argparse.ArgumentParser(description="Standardize file headers to match templates")
    parser.add_argument("--analyze", action="store_true", 
                       help="Show files that need standardization")
    parser.add_argument("--fix", action="store_true",
                       help="Update file headers to standard format")
    parser.add_argument("--dry-run", action="store_true",
                       help="With --fix: show what would be changed without writing")
    parser.add_argument("--limit", type=int, default=0,
                       help="Limit number of files to process (0 = all)")
    
    args = parser.parse_args()
    
    if not args.analyze and not args.fix:
        parser.print_help()
        print("\nUse --analyze to list files needing standardization, or --fix to update headers.")
        return
    
    standardizer = TemplateStandardizer()
    files = collect_files(REPO_ROOT, poc=False)
    
    needs_standardization = []
    already_standard = []
    
    print("Analyzing files for header standardization...")
    
    for priority, path in files:
        if path.suffix not in (".py", ".java"):
            continue
        if is_helper_file(path.stem):
            continue
        
        try:
            content = path.read_text(encoding="utf-8", errors="replace")
        except Exception as e:
            print(f"Error reading {path}: {e}")
            continue
        
        is_python = path.suffix == ".py"
        
        if standardizer.needs_standardization(content, is_python):
            needs_standardization.append((path, content, is_python))
        else:
            already_standard.append(path)
    
    # Create output directory
    output_dir = REPO_ROOT / "tmp"
    output_dir.mkdir(exist_ok=True)
    
    # Report analysis results
    analysis_report = output_dir / "HeaderStandardizationAnalysis.md"
    report_lines = [
        "# File Header Standardization Analysis",
        f"\nGenerated on: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}",
        "",
        f"## Summary",
        f"- Files needing standardization: {len(needs_standardization)}",
        f"- Files already standard: {len(already_standard)}",
        f"- Total analyzed: {len(needs_standardization) + len(already_standard)}",
        "",
        "## Files Needing Standardization",
        "",
        "| File | Type | Reason |",
        "|------|------|--------|"
    ]
    
    for path, content, is_python in needs_standardization:
        rel_path = path.relative_to(REPO_ROOT)
        file_type = "Python" if is_python else "Java"
        
        # Determine reason
        if is_python:
            if not re.search(r'"""', content):
                reason = "Missing docstring"
            else:
                reason = "Non-standard docstring format"
        else:
            if not re.search(r'/\*\*', content):
                reason = "Missing comment block"
            else:
                reason = "Non-standard comment format"
        
        report_lines.append(f"| {rel_path} | {file_type} | {reason} |")
    
    report_lines.extend([
        "",
        "## Files Already Standard",
        ""
    ])
    
    for path in already_standard[:20]:  # Show first 20
        rel_path = path.relative_to(REPO_ROOT)
        report_lines.append(f"- {rel_path}")
    
    if len(already_standard) > 20:
        report_lines.append(f"- ... and {len(already_standard) - 20} more files")
    
    analysis_report.write_text("\n".join(report_lines), encoding="utf-8")
    print(f"\nAnalysis complete: {analysis_report}")
    
    if args.analyze:
        return
    
    # Fix files
    if not needs_standardization:
        print("No files need standardization!")
        return
    
    limit = args.limit if args.limit > 0 else len(needs_standardization)
    files_to_fix = needs_standardization[:limit]
    
    fixed_count = 0
    errors = []
    
    for path, content, is_python in files_to_fix:
        try:
            new_content = standardizer.replace_header(content, is_python, path.stem)
            
            if new_content != content:
                if args.dry_run:
                    print(f"[DRY RUN] Would update: {path.relative_to(REPO_ROOT)}")
                else:
                    path.write_text(new_content, encoding="utf-8")
                    print(f"Updated: {path.relative_to(REPO_ROOT)}")
                fixed_count += 1
            else:
                print(f"No changes needed: {path.relative_to(REPO_ROOT)}")
                
        except Exception as e:
            error_msg = f"Error processing {path}: {e}"
            errors.append(error_msg)
            print(error_msg)
    
    print(f"\n{'[DRY RUN] ' if args.dry_run else ''}Standardization complete:")
    print(f"- Files {'would be ' if args.dry_run else ''}updated: {fixed_count}")
    print(f"- Errors: {len(errors)}")
    
    if errors:
        error_report = output_dir / "HeaderStandardizationErrors.md"
        error_lines = ["# Header Standardization Errors", ""] + errors
        error_report.write_text("\n".join(error_lines), encoding="utf-8")
        print(f"- Error details: {error_report}")


if __name__ == "__main__":
    main()