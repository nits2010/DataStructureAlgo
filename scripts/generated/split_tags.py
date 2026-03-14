import os
import collections
import string


def split_md_by_company(input_file, output_folder):
    if not os.path.exists(input_file):
        print(f"Error: Source file '{input_file}' not found.")
        return

    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    # Dictionary to hold: { 'company_slug': [ [Tags, Title, Link, File, Diff], ... ] }
    company_data = collections.defaultdict(list)

    # Store the original casing of the company name for the header
    company_names = {}

    with open(input_file, 'r', encoding='utf-8') as f:
        for line in f:
            clean_line = line.strip()

            # Skip empty lines, main headers, or separators
            if not clean_line or clean_line.startswith('#') or '---' in clean_line or 'Company Tags' in clean_line:
                continue

            # Extract columns (ignoring the original row number in cols[0])
            cols = [c.strip() for c in clean_line.split('|') if c.strip()]

            if len(cols) >= 2:
                tags_raw = cols[1]
                remaining_cols = cols[1:]

                tags = [t.strip() for t in tags_raw.split(',')]
                for tag in tags:
                    # Slug for filename (google_cloud)
                    file_slug = tag.lower().replace(" ", "_")
                    # Proper name for header (Google Cloud)
                    company_names[file_slug] = string.capwords(tag)

                    company_data[file_slug].append(remaining_cols)

    # Generate the files
    for slug, rows in company_data.items():
        file_path = os.path.join(output_folder, f"{slug}.md")
        display_name = company_names[slug]

        with open(file_path, 'w', encoding='utf-8') as out_f:
            # 1. Top level Header
            out_f.write(f"# {display_name} Interview Questions\n\n")

            # 2. Table Header
            out_f.write(
                "| # | Company Tags | Question Title | Question Link | File Name | Difficulty |\n")
            out_f.write("|---|---|---|---|---|---|\n")

            # 3. Data Rows with new numbering
            for idx, data_cols in enumerate(rows, start=1):
                row_content = " | ".join(data_cols)
                out_f.write(f"| {idx} | {row_content} |\n")

        print(f"Generated: {file_path} ({len(rows)} entries)")


if __name__ == "__main__":
    # CONFIGURATION
    SOURCE_FILE = 'scripts/generated/ProblemsList.md'  # Your input file
    # The folder where outputs will go
    OUTPUT_PATH = 'scripts/generated/company/company_files'

    split_md_by_company(SOURCE_FILE, OUTPUT_PATH)
