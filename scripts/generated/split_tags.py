import os
import collections


def split_md_by_company(input_file, output_folder):
    if not os.path.exists(input_file):
        print(f"Error: Source file '{input_file}' not found.")
        return

    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    # Dictionary to hold the row data
    company_data = collections.defaultdict(list)
    # Dictionary to hold the "proper" name for the title
    company_names = {}

    with open(input_file, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    for line in lines:
        raw_line = line.strip()

        # Skip everything that isn't a data row
        if not raw_line.startswith('|') or '---' in raw_line or 'Company Tags' in raw_line:
            continue

        # Split by pipe, but keep the whitespace intact for the inner columns
        # [0] is empty (before first |), [1] is #, [2] is Tags, [3...] are the rest
        parts = line.split('|')

        if len(parts) >= 3:
            tags_column = parts[2].strip()
            # Everything from 'Company Tags' onwards
            remaining_content_list = parts[2:]

            tags = [t.strip() for t in tags_column.split(',')]
            for tag in tags:
                if not tag:
                    continue
                file_slug = tag.lower().replace(" ", "_")
                company_names[file_slug] = tag
                company_data[file_slug].append(remaining_content_list)

    # Generate the files
    for slug, rows in company_data.items():
        file_path = os.path.join(output_folder, f"{slug}.md")
        display_name = company_names[slug]

        with open(file_path, 'w', encoding='utf-8') as out_f:
            # Header matching your screenshot style
            out_f.write(f"# {display_name} Interview Questions\n\n")
            out_f.write(
                "| # | Company Tags | Question Title | Question Link | File Name | Difficulty |\n")
            out_f.write("|---|---|---|---|---|---|\n")

            for idx, content_list in enumerate(rows, start=1):
                # Rebuild: | New Index | Original Tags | Original Title | etc...
                # We use content_list which preserved the original spacing/newlines
                row_str = "|".join(content_list)
                out_f.write(f"| {idx} |{row_str}")
                # Ensure the line ends with a newline
                if not row_str.endswith('\n'):
                    out_f.write('\n')

        print(f"Generated: {file_path}")


if __name__ == "__main__":
    # CONFIGURATION
    SOURCE_FILE = 'scripts/generated/ProblemsList.md'  # Your input file
    # The folder where outputs will go
    OUTPUT_PATH = 'scripts/generated/company/company_files'

    split_md_by_company(SOURCE_FILE, OUTPUT_PATH)
