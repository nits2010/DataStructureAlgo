import os
import csv

# Define the base path of your local repository and GitHub URL
base_path = r"E:\Study\DataStructureAlgo"
base_url = "https://github.com/nits2010/DataStructureAlgo/blob/080ed36bcb73960e0cd0fb80d408f13620798b2e"

# File paths
csv_file_path = 'Problems.csv'
md_file_path = 'Problems.md'

# Step 1: Generate the CSV file with file details
with open(csv_file_path, mode='w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    
    # Set headers
    headers = ["File Name", "GitHub Link"]
    writer.writerow(headers)
    
    # Walk through the directory and write file details to CSV
    for root, dirs, files in os.walk(base_path):
        for file in files:
            if file.endswith(".java"):  # Filter for Java files
                local_path = os.path.join(root, file)
                relative_path = os.path.relpath(local_path, base_path).replace("\\", "/")
                github_link = f"{base_url}/{relative_path}"
                writer.writerow([file, github_link])

print("CSV file created successfully!")

# Step 2: Convert the CSV file to Markdown
with open(csv_file_path, mode='r', newline='', encoding='utf-8') as csv_file, open(md_file_path, mode='w', encoding='utf-8') as md_file:
    csv_reader = csv.DictReader(csv_file)
    
    # Write Markdown table headers
    md_file.write("| File Name | GitHub Link |\n")
    md_file.write("|-----------|-------------|\n")
    
    # Write Markdown table rows
    for row in csv_reader:
        file_name = row['File Name']
        github_link = row['GitHub Link']
        md_file.write(f"| {file_name} | [link]({github_link}) |\n")

print(f"Markdown file has been generated at {md_file_path}")
