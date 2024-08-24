import os


# Define the base path of your local repository and GitHub URL
base_path = r"E:\Study\DataStructureAlgo"
base_url = "https://github.com/nits2010/DataStructureAlgo/blob/080ed36bcb73960e0cd0fb80d408f13620798b2e"

# Path for the markdown file
md_file_path = 'Problems.md'

# Step 1: Create and write to the Markdown file
with open(md_file_path, mode='w', encoding='utf-8') as md_file:
    # Write Markdown table headers
    md_file.write("| File Name | GitHub Link |\n")
    md_file.write("|-----------|-------------|\n")

    # Walk through the directory and write file details directly to the markdown file
    for root, dirs, files in os.walk(base_path):
        for file in files:
            if file.endswith(".java"):  # Filter for Java files
                local_path = os.path.join(root, file)
                relative_path = os.path.relpath(local_path, base_path).replace("\\", "/")
                github_link = f"{base_url}/{relative_path}"

                # Write each row to the markdown file
                md_file.write(f"| {file} | [link]({github_link}) |\n")

print(f"Markdown file has been generated at {md_file_path}")
