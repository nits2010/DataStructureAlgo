import os
from datetime import datetime
import subprocess

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
    except subprocess.CalledProcessError:
        return None

# Define the base path of your local repository and GitHub URL
branch = get_current_git_branch()
base_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'src', 'main', 'java', 'DataStructureAlgo', 'Java')
base_url = "https://github.com/nits2010/DataStructureAlgo/blob/" + branch

# Directories to consider
directories_to_consider = ['companyWise', 'LeetCode', 'LeetCode2025', 'nonleetcode']

# Path for the markdown file
md_file_base_path = os.path.dirname(os.path.abspath(__file__))

# Step 1: Collect file details along with their creation time
file_details = {dir_name: [] for dir_name in directories_to_consider}

for root, dirs, files in os.walk(base_path):
    relative_root = os.path.relpath(root, base_path).replace("\\", "/")
    if any(relative_root.startswith(dir_name) for dir_name in directories_to_consider):
        for file in files:
            if file.endswith(".java"):  # Filter for Java files
                local_path = os.path.join(root, file)
                relative_path = os.path.relpath(local_path, base_path).replace("\\", "/")
                github_link = f"{base_url}/{relative_path}"
                creation_time = os.path.getctime(local_path)  # Get creation time
                parent_folder = relative_root.split('/')[0]

                if parent_folder in directories_to_consider:
                    file_details[parent_folder].append((parent_folder, file, github_link, creation_time))

# Step 2: Sort files by creation time (newest first, descending order) for each directory
for parent_folder in file_details:
    file_details[parent_folder].sort(key=lambda x: x[3], reverse=True)

# Step 3: Create and write to individual Markdown files
for parent_folder in ['LeetCode2025', 'LeetCode', 'companyWise', 'nonleetcode']:
    if parent_folder in file_details and file_details[parent_folder]:
        md_file_path = os.path.join(md_file_base_path, f'{parent_folder}.md')
        with open(md_file_path, mode='w', encoding='utf-8') as md_file:
            md_file.write(f"## {parent_folder}\n\n")
            md_file.write("| #  | File Name | GitHub Link | \n")
            md_file.write("|---|-----------|-------------|\n")

            total_files = len(file_details[parent_folder])  # Get total number of files for reverse numbering
            for idx, (parent_folder, file_name, github_link, creation_time) in enumerate(file_details[parent_folder]):
                creation_date = datetime.fromtimestamp(creation_time).strftime('%Y-%m-%d %H:%M:%S')
                serial_number = total_files - idx
                md_file.write(f"| {serial_number} | {file_name} | [link]({github_link}) |\n")

print("Markdown files have been generated for each specified folder.")
