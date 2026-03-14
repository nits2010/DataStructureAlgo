# Unified Configuration System for Generator Scripts

## Overview

All generator scripts in the `scripts/generators/` directory now use a unified configuration system managed by `unified_config.py`. This ensures consistency across all scripts and eliminates code duplication.

## Benefits

- ✅ **Consistent behavior** - All scripts use the same configuration source
- ✅ **No code duplication** - Configuration parsing logic is centralized
- ✅ **Easy maintenance** - Update configuration in one place
- ✅ **Backward compatibility** - Existing scripts continue to work

## Configuration File

The configuration is stored in:
```
scripts/config/.problem_generator_config
```

### Configuration Sections

1. **`[ignore_folders]`** - Folders to skip during scanning
2. **`[file_extensions]`** - File types to process  
3. **`[helper_files]`** - Base names of helper files to skip
4. **`[non_company_tags]`** - Tags that are not company names

### Sample Configuration

```ini
[ignore_folders]
1 = helpers
2 = sorts  
3 = python
4 = .idea*
5 = fileTemplates*
6 = scripts
7 = build
8 = target

[file_extensions]
1 = .py
2 = .java
3 = .js
4 = .ts
5 = .tsx

[helper_files]
1 = node
2 = pair
3 = listnode
4 = main

[non_company_tags]
1 = easy
2 = medium
3 = hard
```

## Using Unified Configuration

### For New Scripts

Import and use the unified configuration in your script:

```python
#!/usr/bin/env python3

from unified_config import (
    config,                    # Main config object
    REPO_ROOT,                # Repository root path
    path_should_ignore,       # Function to check if path should be ignored
    is_helper_file,           # Function to check if file is a helper
    collect_files,            # Function to collect files for processing
    print_config_summary      # Function to display config summary
)

# Access configuration values
ignore_folders = config.ignore_folders
file_extensions = config.file_extensions
helper_files = config.helper_files
non_company_tags = config.non_company_tags

# Use utility functions
files = collect_files(since_timestamp=0.0)
for priority, file_path in files:
    if path_should_ignore(file_path.relative_to(REPO_ROOT)):
        continue
    if is_helper_file(file_path.stem):
        continue
    # Process file...
```

### For Existing Scripts

All existing scripts have been updated to use the unified configuration:

- **`standardize_file_headers.py`** - ✅ Updated
- **`fix_question_headers.py`** - ✅ Updated  
- **`build_company_question_list.py`** - ✅ Updated
- **`unified_problem_generator.py`** - ✅ Updated

## Available Functions

### Configuration Access
```python
from unified_config import config

config.ignore_folders      # Set of folders to ignore
config.file_extensions     # Set of file extensions to process
config.helper_files        # Set of helper file base names
config.non_company_tags    # Set of non-company tags
```

### Utility Functions
```python
from unified_config import (
    path_should_ignore,      # Check if path should be ignored
    is_helper_file,          # Check if file is a helper file
    is_non_company_tag,      # Check if tag is non-company
    collect_files,           # Collect files for processing
    print_config_summary,    # Display configuration summary
    reload_config            # Reload configuration from file
)
```

### Path Filtering
```python
from pathlib import Path
from unified_config import path_should_ignore

# Check if a path should be ignored
test_path = Path("scripts/generators/test.py")
if path_should_ignore(test_path):
    print("This path should be ignored")
```

### File Collection
```python
from unified_config import collect_files

# Collect all files (returns list of (priority, Path) tuples)
all_files = collect_files(since_timestamp=0.0)

# Collect only recent files
import time
one_hour_ago = time.time() - 3600
recent_files = collect_files(since_timestamp=one_hour_ago)

# Priority levels:
# 0 = LeetCode2025 files
# 1 = CompanyWise files  
# 2 = Other files
```

## Configuration Display

All scripts now support `--show-config` to display the current configuration:

```bash
python3 standardize_file_headers.py --show-config
python3 fix_question_headers.py --show-config
python3 unified_problem_generator.py --show-config
```

Example output:
```
📁 Configuration loaded from: /path/to/.problem_generator_config
   ✅ Ignore folders: 14 items
      .*, .idea*, DSA_Pattern, build, cpp, fileTemplates*, helpers, node_modules, out, python, scripts, sorts, target, venv
   ✅ File extensions: 5 items
      .java, .js, .py, .ts, .tsx
   ✅ Helper files: 17 items
      commonmethods, customkey, doublylistnode, expensetype, keyvaluestore, listbuilder, listnode, main, nestedinteger, nestedintegervalue, node, pair, percentexpense, split, treebuilder, treenode, user
   ✅ Non-company tags: 38 items
      array, baseproblem, binarysearch, dynamicprogramming, graph, leetcodelockedproblem, linkedlist, recursion, ref, slidingwindow...
```

## Testing

You can test the unified configuration directly:

```bash
cd scripts/generators/
python3 unified_config.py
```

This will:
- Display the configuration summary
- Test path filtering on sample paths
- Show file collection statistics

## Backward Compatibility

The unified configuration maintains backward compatibility by exporting the legacy constants:

```python
from unified_config import (
    IGNORE_FOLDERS,    # Legacy: config.ignore_folders
    CODE_EXTENSIONS,   # Legacy: config.file_extensions  
    HELPER_FILES,      # Legacy: config.helper_files
    NON_COMPANY_TAGS   # Legacy: config.non_company_tags
)
```

## Adding New Configuration Options

To add new configuration options:

1. Add the section to `.problem_generator_config`
2. Update the `_parse_config_file()` method in `unified_config.py`
3. Add the attribute to the `UnifiedConfig` class
4. Update the default configuration in `_get_default_config()`

## Migration Guide

If you have custom scripts that need to use the unified configuration:

### Before (Manual Config)
```python
IGNORE_FOLDERS = {"helpers", "sorts", "python"}
CODE_EXTENSIONS = {".py", ".java"}

def path_should_ignore(path):
    # Custom implementation...
    pass
```

### After (Unified Config)
```python
from unified_config import config, path_should_ignore

# Configuration is automatically loaded
# Just use the utility functions
```

This ensures all scripts behave consistently and configuration changes are applied globally.