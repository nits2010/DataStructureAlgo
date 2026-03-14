import os
import collections

"""
Split the ProblemsList.md file into company-specific files.

"""


def split_md_by_company(input_file, output_folder):
    if not os.path.exists(input_file):
        print(f"Error: Source file '{input_file}' not found.")
        return

    # Create the output directory if it doesn't exist
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)
        print(f"Created directory: {output_folder}")

    # Dictionary to hold: { 'CompanyName': [list_of_rows] }
    company_map = collections.defaultdict(list)

    # Standard Markdown Table Header
    header = "| # | Company Tags | Question Title | Question Link | File Name | Difficulty |\n"
    separator = "|---|---|---|---|---|---|\n"

    with open(input_file, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    for line in lines:
        clean_line = line.strip()

        # Skip empty lines, main headers, or separator lines
        if not clean_line or clean_line.startswith('#') or '---' in clean_line or 'Company Tags' in clean_line:
            continue

        # Split by | and filter out empty strings to find the tags column
        # Expected structure: | # | Tags | Title | Link | File | Diff |
        parts = [p.strip() for p in clean_line.split('|') if p.strip()]

        if len(parts) >= 2:
            # The 'Company Tags' are in the second real column
            tags_raw = parts[1]
            tags = [t.strip() for t in tags_raw.split(',')]

            for tag in tags:
                # Format filename: lowercase, no spaces
                file_key = tag.lower().replace(" ", "_")
                company_map[file_key].append(clean_line)

    # Generate the files in the specified path
    for company, rows in company_map.items():
        file_path = os.path.join(output_folder, f"{company}.md")

        with open(file_path, 'w', encoding='utf-8') as out_f:
            out_f.write(header)
            out_f.write(separator)
            for row in rows:
                # Ensure the row starts and ends with a pipe if it doesn't already
                formatted_row = row if row.startswith('|') else f"| {row} |"
                out_f.write(f"{formatted_row}\n")

        print(f"Generated: {file_path} ({len(rows)} entries)")


if __name__ == "__main__":
    # CONFIGURATION
    SOURCE_FILE = 'scripts/generated/ProblemsList.md'  # Your input file
    # The folder where outputs will go
    OUTPUT_PATH = 'scripts/generated/company/company_files'

    split_md_by_company(SOURCE_FILE, OUTPUT_PATH)
