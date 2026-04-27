import os
import collections
import re


def split_md_by_company(input_file, output_folder):
    if not os.path.exists(input_file):
        print(f"Error: Source file '{input_file}' not found.")
        return

    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    # Dictionary to hold the row data.
    # Each row is stored as: (tags_column, title_with_link, file_name, difficulty)
    company_data = collections.defaultdict(list)
    # Dictionary to hold the "proper" name for the title
    company_names = {}
    # Whether input table still has a separate "Question Link" column.
    # If None, we infer from row structure.
    old_format = None

    with open(input_file, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    for line in lines:
        raw_line = line.strip()

        # Detect header format (old had "Question Link" column; new embeds it in title).
        if raw_line.startswith('|') and 'Company Tags' in raw_line:
            old_format = 'Question Link' in raw_line
            continue

        # Skip everything that isn't a data row
        if not raw_line.startswith('|') or '---' in raw_line:
            continue

        # Split by pipe, but keep the whitespace intact for the inner columns
        # [0] is empty (before first |), [1] is #, [2] is Tags, [3...] are the rest
        parts = line.split('|')

        if len(parts) >= 3:
            tags_column = parts[2].strip()

            # Infer format if we couldn't detect it from the header.
            # Old rows usually have: | idx | tags | title | [Link](url) | file | diff |
            # New rows usually have: | idx | tags | [title](url) | file | diff |
            if old_format is None:
                old_format = len(parts) >= 8

            if old_format:
                title_plain = parts[3].strip()
                link_cell = parts[4].strip()
                file_cell = parts[5].strip()
                diff_cell = parts[6].strip()

                url_match = re.search(r'\((https?://[^)]+)\)', link_cell)
                if url_match and title_plain and title_plain != '-':
                    title_with_link = f'[{title_plain}]({url_match.group(1)})'
                else:
                    title_with_link = title_plain
            else:
                title_with_link = parts[3].strip()
                file_cell = parts[4].strip()
                diff_cell = parts[5].strip()

            tags = [t.strip() for t in tags_column.split(',')]
            for tag in tags:
                if not tag:
                    continue
                file_slug = tag.lower().replace(" ", "_")
                company_names[file_slug] = tag
                company_data[file_slug].append(
                    (tags_column, title_with_link, file_cell, diff_cell)
                )

    # Generate the files
    for slug, rows in company_data.items():
        file_path = os.path.join(output_folder, f"{slug}.md")
        display_name = company_names[slug]

        with open(file_path, 'w', encoding='utf-8') as out_f:
            # Header matching your screenshot style
            out_f.write(f"# {display_name} Interview Questions\n\n")
            out_f.write(
                "| # | Company Tags | Question Title | File Name | Difficulty |\n")
            out_f.write("|---|---|---|---|---|\n")

            for idx, (tags_column, title_with_link, file_cell, diff_cell) in enumerate(rows, start=1):
                out_f.write(
                    f"| {idx} | {tags_column} | {title_with_link} | {file_cell} | {diff_cell} |\n"
                )

        print(f"Generated: {file_path}")


if __name__ == "__main__":
    # CONFIGURATION
    SOURCE_FILE = 'scripts/generated/ProblemsList.md'  # Your input file
    # The folder where outputs will go
    OUTPUT_PATH = 'scripts/generated/company/company_files'

    split_md_by_company(SOURCE_FILE, OUTPUT_PATH)
