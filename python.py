import os
from datetime import datetime

# Define the base path of your local repository and GitHub URL
base_path = r"E:\Study\DataStructureAlgo"
base_url = "https://github.com/nits2010/DataStructureAlgo/blob/080ed36bcb73960e0cd0fb80d408f13620798b2e"

# Path for the markdown file
md_file_path = 'Problems.md'

# Step 1: Collect file details along with their creation time
file_details = []

for root, dirs, files in os.walk(base_path):
    for file in files:
        if file.endswith(".java"):  # Filter for Java files
            local_path = os.path.join(root, file)
            relative_path = os.path.relpath(local_path, base_path).replace("\\", "/")
            github_link = f"{base_url}/{relative_path}"
            creation_time = os.path.getctime(local_path)  # Get creation time

            # Add file details and creation time to the list
            file_details.append((file, github_link, creation_time))

# Step 2: Sort files by creation time (newest first)
file_details.sort(key=lambda x: x[2], reverse=True)

# Step 3: Create and write to the Markdown file
with open(md_file_path, mode='w', encoding='utf-8') as md_file:
    # Write Markdown table headers
    md_file.write("| File Name | GitHub Link | Creation Date |\n")
    md_file.write("|-----------|-------------|---------------|\n")

    # Write each file's details to the markdown file
    for file_name, github_link, creation_time in file_details:
        # Format the creation time as a readable date
        creation_date = datetime.fromtimestamp(creation_time).strftime('%Y-%m-%d %H:%M:%S')
        md_file.write(f"| {file_name} | [link]({github_link}) | {creation_date} |\n")

print(f"Markdown file has been generated at {md_file_path}")
