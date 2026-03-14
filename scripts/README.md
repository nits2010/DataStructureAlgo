# Scripts & Tools Organization

This folder contains all scripts, configuration files, and generated content for the DataStructureAlgo repository.

## 📁 Folder Structure

```
scripts/
├── README.md                    # This file
├── generators/                  # Problem list generators and utilities
│   ├── unified_problem_generator.py      # ✅ ACTIVE: Main generator (recommended)
│   ├── AllProblemSetGenerator.py         # ✅ UPDATED: Simple file list generator
│   ├── build_company_question_list.py   # ✅ UPDATED: Company-tagged problems only
│   ├── fix_question_headers.py          # ✅ UPDATED: Fix file headers
│   └── standardize_file_headers.py      # ✅ UPDATED: Standardize headers
├── templates/                   # File templates
│   ├── fileTemplate.java              # Java file template
│   └── fileTemplate.py                # Python file template
├── config/                      # Configuration and state files
│   ├── .problem_generator_config       # ✅ Main configuration file
│   ├── .last_problems_run             # Timestamp for incremental updates
│   ├── .last_all_run                 # Legacy timestamp file
│   ├── .last_company_run              # Legacy timestamp file
│   └── .last_company_list_run         # Legacy timestamp file
├── generated/                   # Generated markdown files
│   ├── ProblemsList.md                # ✅ Main output file (unified_problem_generator.py)
│   ├── AllProblems.md                 # Output from AllProblemSetGenerator.py
│   └── CompanyQuestionList.md         # Output from build_company_question_list.py
├── tmp/                         # Temporary files and reports
│   └── (fix_question_headers.py outputs)
└── docs/                        # Documentation
    ├── CONFIG_README.md               # Configuration guide
    └── Preparation.md                 # Study preparation guide
```

## 🚀 Quick Start

### Generate Problems List

```bash
# From repository root - RECOMMENDED METHOD
python3 generate_problems.py

# Alternative: Use direct path
python3 scripts/generators/unified_problem_generator.py

# Legacy generators (also work)
python3 scripts/generators/AllProblemSetGenerator.py
python3 scripts/generators/build_company_question_list.py
```

### View Configuration
```bash
python3 generate_problems.py --show-config
```

## 📋 All Scripts Updated for New Structure

### ✅ **Active Files (Recommended)**
- **`unified_problem_generator.py`** - Main generator, use this one
- **`.problem_generator_config`** - Configuration file
- **`ProblemsList.md`** - Generated output

### ✅ **Legacy Files (Updated for New Structure)**
- `AllProblemSetGenerator.py` - Simple generator (outputs to `scripts/generated/AllProblems.md`)
- `build_company_question_list.py` - Company-only generator (outputs to `scripts/generated/CompanyQuestionList.md`)
- `fix_question_headers.py` - Header fixer (outputs to `scripts/tmp/`)
- `standardize_file_headers.py` - Header standardizer (uses templates from `scripts/templates/`)
- `.last_*_run` files - Timestamp files (in `scripts/config/`)

## 🔧 Path Updates Made

**All scripts have been updated to:**
- ✅ Use correct repository root (`scripts/generators/` → repo root)
- ✅ Load config from `scripts/config/.problem_generator_config`
- ✅ Save timestamps to `scripts/config/.last_*_run`
- ✅ Output to `scripts/generated/` or `scripts/tmp/`
- ✅ Load templates from `scripts/templates/`

## 💡 Usage Tips

1. **Use the shortcut**: `python3 generate_problems.py` (at repository root)
2. **All scripts work from repository root** - paths are automatically resolved
3. **Check outputs**: All generated files go to `scripts/generated/` or `scripts/tmp/`
4. **Configuration is centralized**: Edit `scripts/config/.problem_generator_config`

## 🧪 Tested & Verified

✅ `unified_problem_generator.py` - Works perfectly  
✅ `AllProblemSetGenerator.py` - Updated and tested  
✅ `build_company_question_list.py` - Updated and tested  
✅ `fix_question_headers.py` - Updated paths  
✅ `standardize_file_headers.py` - Updated paths  

All scripts now work correctly with the new organized folder structure!