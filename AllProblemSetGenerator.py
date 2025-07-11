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
    except subprocess.CalledProcessError as e:
        return None

# Define the base path of your local repository and GitHub URL
branch = get_current_git_branch()
# import os
base_path = os.path.dirname(os.path.abspath(__file__))
# base_path = r"E:\Study\DataStructureAlgo"
base_url = "https://github.com/nits2010/DataStructureAlgo/blob/" + branch

# Path for the Markdown file
md_file_path = 'AllProblems.md'

# Step 1: Collect file details along with their creation time
file_details = []

for root, dirs, files in os.walk(base_path):
    # Exclude unwanted directories from being scanned
    if "cpp" in dirs:
        dirs.remove("cpp")
    if "helpers" in dirs:
        dirs.remove("helpers")

    for file in files:
        if file.endswith(".java") or file.endswith(".py"):  # Filter for Java files
            local_path = os.path.join(root, file)
            relative_path = os.path.relpath(local_path, base_path).replace("\\", "/")
            github_link = f"{base_url}/{relative_path}"
            creation_time = os.path.getctime(local_path)  # Get creation time

            # Add file details (parent folder name, file name) and creation time to the list
            parent_folder = os.path.basename(os.path.dirname(local_path))
            file_details.append((parent_folder, file, github_link, creation_time))

# Step 2: Sort files by creation time (newest first, descending order)
file_details.sort(key=lambda x: x[3], reverse=True)

# Step 3: Create and write to the Markdown file
with open(md_file_path, mode='w', encoding='utf-8') as md_file:
    # Write Markdown table headers with serial number (#)
    md_file.write("| #  | File Name | GitHub Link | \n")
    md_file.write("|---|-----------|-------------|\n")

    # Write each file's details to the markdown file with serial number starting from the oldest file
    total_files = len(file_details)  # Get total number of files for reverse numbering
    for idx, (parent_folder, file_name, github_link, creation_time) in enumerate(file_details):
        # Format the creation time as a readable date
        creation_date = datetime.fromtimestamp(creation_time).strftime('%Y-%m-%d %H:%M:%S')
        # Calculate serial number in reverse order
        serial_number = total_files - idx
        md_file.write(f"| {serial_number} | {file_name} | [link]({github_link}) |\n")

print(f"Markdown file has been generated at {md_file_path}")