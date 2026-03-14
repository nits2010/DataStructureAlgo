# Problem Generator Configuration

The unified problem generator uses a global configuration file `.problem_generator_config` to control its behavior and generates a single comprehensive `ProblemsList.md` file.

## Output File

**Generated File:** `ProblemsList.md`
- **Format:** CompanyQuestionList.md format (# | Question Title | Question Link | File Name | Difficulty | Company Tags)
- **Content:** ALL files in repository, regardless of whether they have company tags
- **Empty Fields:** Show "-" when no data available (no link, no difficulty, no company tags)

## Configuration File Location

```
/Users/ngupta14/Documents/personal/DataStructureAlgo/.problem_generator_config
```

## Configuration Sections

### `[ignore_folders]`
Folders to completely ignore during scanning. Supports wildcards with `*`.

**Examples:**
- `helpers` - Ignores any folder named "helpers"
- `.idea*` - Ignores folders starting with ".idea" (like ".idea", ".idea_modules")

### `[file_extensions]`
File extensions to include in the scan.

**Examples:**
- `.py` - Python files
- `.java` - Java files  
- `.ts` - TypeScript files

### `[helper_files]`
File base names (without extension) that contain only helper code, not actual problems. These are skipped in company mode.

**Examples:**
- `node` - Files like "Node.java", "node.py"
- `listnode` - Files like "ListNode.java", "listnode.py"

### `[non_company_tags]`
Tags that should NOT be considered company names (difficulty, topics, meta tags).

**Examples:**
- `easy`, `medium`, `hard` - Difficulty levels
- `array`, `string` - Topic tags
- `editorial` - Meta tags

## Usage

### Generate Problems List
```bash
# Generate ProblemsList.md (incremental update)
python3 unified_problem_generator.py

# Force full scan (ignore timestamps)  
python3 unified_problem_generator.py --force-full

# Sort oldest files first
python3 unified_problem_generator.py --sort-order oldest

# View current configuration
python3 unified_problem_generator.py --show-config
```

### Edit Configuration
Simply edit the `.problem_generator_config` file with any text editor. The script will automatically reload the configuration on next run.

### Add New Ignore Folder
1. Open `.problem_generator_config`
2. Go to `[ignore_folders]` section
3. Add a new line: `<next_number> = <folder_name>`

**Example:**
```ini
[ignore_folders]
12 = my_test_folder
```

### Add New File Extension
1. Open `.problem_generator_config`
2. Go to `[file_extensions]` section  
3. Add a new line: `<next_number> = .<extension>`

**Example:**
```ini
[file_extensions]
6 = .cpp
```

## Backup and Restore

The configuration file is plain text and can be easily:
- **Backed up**: Copy `.problem_generator_config` to a safe location
- **Shared**: Commit to git or share with team members
- **Restored**: Replace with backed up version

## Default Values

If the configuration file is missing or corrupt, the script uses these defaults:
- **Ignore folders**: `helpers`, `sorts`, `python`, `.idea*`, `fileTemplates*`, `cpp`, `venv`, `target`, `build`, `out`, `node_modules`
- **File extensions**: `.py`, `.java`, `.js`, `.ts`, `.tsx`
- **Helper files**: `node`, `pair`, `listnode`, etc.
- **Non-company tags**: `easy`, `medium`, `hard`, `array`, `string`, etc.

## Benefits

✅ **No code changes needed** - Modify behavior without touching the script  
✅ **Team-friendly** - Share configuration via git  
✅ **Fallback safe** - Works even if config file is missing  
✅ **Easy to customize** - Simple INI format  
✅ **Wildcard support** - Flexible folder patterns