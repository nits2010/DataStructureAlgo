#!/usr/bin/env python3
"""
Unified Configuration Manager for Problem Generator Scripts

This module provides centralized configuration management for all generator scripts.
All scripts in scripts/generators/ should import and use this module instead of
implementing their own configuration parsing.

Usage:
    from unified_config import config, path_should_ignore, is_helper_file, collect_files
    
    # Access configuration values
    ignore_folders = config.ignore_folders
    file_extensions = config.file_extensions
    
    # Use utility functions
    if path_should_ignore(some_path):
        continue
"""

import os
from pathlib import Path
from typing import Dict, List, Set, Tuple, Any
import time

# Repository root (go up 2 levels from scripts/generators/)
REPO_ROOT = Path(__file__).resolve().parent.parent.parent
CONFIG_FILE = REPO_ROOT / "scripts" / "config" / ".problem_generator_config"


class UnifiedConfig:
    """Centralized configuration manager for all generator scripts."""
    
    def __init__(self, config_file: Path):
        self.config_file = config_file
        self._load_configuration()
    
    def _load_configuration(self) -> None:
        """Load configuration from the unified config file."""
        raw_config = self._parse_config_file()
        
        # Set configuration attributes
        self.ignore_folders: Set[str] = raw_config["ignore_folders"]
        self.file_extensions: Set[str] = raw_config["file_extensions"]
        self.helper_files: Set[str] = raw_config["helper_files"]
        self.non_company_tags: Set[str] = raw_config["non_company_tags"]
        
        # Legacy constants for backward compatibility
        self.IGNORE_FOLDERS = self.ignore_folders
        self.CODE_EXTENSIONS = self.file_extensions
        self.HELPER_FILES = self.helper_files
        self.NON_COMPANY_TAGS = self.non_company_tags
    
    def _parse_config_file(self) -> Dict[str, Set[str]]:
        """Parse the unified configuration file."""
        parsed_config = {
            "ignore_folders": set(),
            "file_extensions": set(), 
            "helper_files": set(),
            "non_company_tags": set()
        }
        
        if not self.config_file.exists():
            print(f"Warning: Config file not found at {self.config_file}")
            return self._get_default_config()
        
        current_section = None
        
        try:
            with open(self.config_file, 'r', encoding='utf-8') as f:
                for line_num, line in enumerate(f, 1):
                    line = line.strip()
                    
                    # Skip empty lines and comments
                    if not line or line.startswith('#'):
                        continue
                    
                    # Section headers
                    if line.startswith('[') and line.endswith(']'):
                        current_section = line[1:-1]
                        if current_section not in parsed_config:
                            print(f"Warning: Unknown section '{current_section}' at line {line_num}")
                        continue
                    
                    # Key-value pairs
                    if '=' in line and current_section:
                        try:
                            _, value = line.split('=', 1)
                            value = value.strip()
                            
                            if current_section in parsed_config:
                                parsed_config[current_section].add(value)
                        except Exception as e:
                            print(f"Warning: Error parsing line {line_num}: {e}")
        
        except Exception as e:
            print(f"Error reading config file: {e}")
            return self._get_default_config()
        
        # Validate required sections have values
        if not parsed_config["ignore_folders"]:
            print("Warning: No ignore_folders configured, using defaults")
            parsed_config["ignore_folders"] = {"helpers", "sorts", "python", "build", "target"}
            
        if not parsed_config["file_extensions"]:
            print("Warning: No file_extensions configured, using defaults")
            parsed_config["file_extensions"] = {".py", ".java", ".js", ".ts", ".tsx"}
        
        return parsed_config
    
    def _get_default_config(self) -> Dict[str, Set[str]]:
        """Return default configuration if config file is unavailable."""
        return {
            "ignore_folders": {"helpers", "sorts", "python", "build", "target", "scripts", ".idea*", "fileTemplates*"},
            "file_extensions": {".py", ".java", ".js", ".ts", ".tsx"},
            "helper_files": {"node", "pair", "listnode", "doublylistnode", "treenode", 
                           "listbuilder", "treebuilder", "commonmethods", "main"},
            "non_company_tags": {"easy", "medium", "hard", "editorial", "optimalsolution", 
                               "array", "string", "hashtable", "dynamicprogramming"}
        }
    
    def path_should_ignore(self, rel_path: Path) -> bool:
        """Check if a path should be ignored based on configuration."""
        for part in rel_path.parts:
            for ignore_pattern in self.ignore_folders:
                if ignore_pattern.endswith('*'):
                    # Wildcard pattern
                    if part.startswith(ignore_pattern[:-1]):
                        return True
                else:
                    # Exact match
                    if part == ignore_pattern:
                        return True
        return False
    
    def is_helper_file(self, base_name: str) -> bool:
        """Check if a file is a helper file based on configuration."""
        return base_name.lower() in self.helper_files
    
    def is_non_company_tag(self, tag: str) -> bool:
        """Check if a tag is a non-company tag (topic/difficulty/meta tag)."""
        return tag.lower() in self.non_company_tags
    
    def collect_files(self, root: Path = None, since_timestamp: float = 0.0) -> List[Tuple[int, Path]]:
        """
        Collect files for processing using unified configuration.
        
        Args:
            root: Root directory to scan (defaults to REPO_ROOT)
            since_timestamp: Only include files modified after this timestamp (0.0 = all files)
            
        Returns:
            List of (priority, path) tuples where priority 0 = LeetCode2025, 1 = CompanyWise, 2 = Other
        """
        if root is None:
            root = REPO_ROOT
            
        collected_files = []
        root_str = str(root)
        
        for dirpath, _dirnames, filenames in os.walk(root):
            rel = Path(dirpath).relative_to(root) if dirpath != root_str else Path(".")
            
            # Skip ignored directories
            if self.path_should_ignore(rel):
                continue
                
            for filename in filenames:
                file_path = Path(dirpath) / filename
                
                # Only include files with configured extensions
                if file_path.suffix not in self.file_extensions:
                    continue
                
                # Skip helper files
                if self.is_helper_file(file_path.stem):
                    continue
                
                # Check timestamp if specified
                if since_timestamp > 0:
                    try:
                        file_stat = file_path.stat()
                        # Use creation time (birthtime on macOS, ctime on other systems)
                        file_create_time = getattr(file_stat, 'st_birthtime', file_stat.st_ctime)
                        file_mod_time = file_stat.st_mtime
                        latest_time = max(file_create_time, file_mod_time)
                        
                        if latest_time <= since_timestamp:
                            continue
                    except OSError:
                        continue  # Skip files we can't stat
                
                # Assign priority based on path
                rel_str = str(file_path.relative_to(root)).replace("\\", "/")
                if "LeetCode2025" in rel_str:
                    priority = 0
                elif "companyWise" in rel_str or "CompanyWise" in rel_str:
                    priority = 1
                else:
                    priority = 2
                
                collected_files.append((priority, file_path))
        
        # Sort by priority, then by path
        collected_files.sort(key=lambda x: (x[0], str(x[1])))
        return collected_files
    
    def print_summary(self) -> None:
        """Print a summary of the loaded configuration."""
        print(f"📁 Configuration loaded from: {self.config_file}")
        print(f"   ✅ Ignore folders: {len(self.ignore_folders)} items")
        print(f"      {', '.join(sorted(self.ignore_folders))}")
        print(f"   ✅ File extensions: {len(self.file_extensions)} items")
        print(f"      {', '.join(sorted(self.file_extensions))}")  
        print(f"   ✅ Helper files: {len(self.helper_files)} items")
        print(f"      {', '.join(sorted(self.helper_files))}")
        if self.non_company_tags:
            print(f"   ✅ Non-company tags: {len(self.non_company_tags)} items")
            print(f"      {', '.join(sorted(list(self.non_company_tags)[:10]))}{'...' if len(self.non_company_tags) > 10 else ''}")
        print()
    
    def reload(self) -> None:
        """Reload configuration from file."""
        self._load_configuration()


# Create global configuration instance
config = UnifiedConfig(CONFIG_FILE)

# Export utility functions for convenience
def path_should_ignore(rel_path: Path) -> bool:
    """Check if a path should be ignored based on configuration."""
    return config.path_should_ignore(rel_path)

def is_helper_file(base_name: str) -> bool:
    """Check if a file is a helper file based on configuration."""
    return config.is_helper_file(base_name)

def is_non_company_tag(tag: str) -> bool:
    """Check if a tag is a non-company tag (topic/difficulty/meta tag)."""
    return config.is_non_company_tag(tag)

def collect_files(root: Path = None, since_timestamp: float = 0.0) -> List[Tuple[int, Path]]:
    """Collect files for processing using unified configuration."""
    return config.collect_files(root, since_timestamp)

def print_config_summary() -> None:
    """Print a summary of the loaded configuration."""
    config.print_summary()

def reload_config() -> None:
    """Reload configuration from file."""
    config.reload()

# Export constants for backward compatibility
REPO_ROOT = REPO_ROOT
IGNORE_FOLDERS = config.ignore_folders
CODE_EXTENSIONS = config.file_extensions
HELPER_FILES = config.helper_files
NON_COMPANY_TAGS = config.non_company_tags


if __name__ == "__main__":
    # Test the configuration when run directly
    print("=== Unified Configuration Test ===")
    print_config_summary()
    
    # Test path filtering
    test_paths = [
        Path("scripts/generators/test.py"),
        Path("src/main/java/DataStructureAlgo/Java/helpers/CommonMethods.java"),
        Path("src/main/java/DataStructureAlgo/Java/LeetCode2025/ProblemSet/Arrays/_1/TwoSum_1.java"),
        Path("build/classes/Main.java"),
    ]
    
    print("=== Path Filtering Tests ===")
    for test_path in test_paths:
        should_ignore = path_should_ignore(test_path)
        print(f"{test_path}: {'IGNORE' if should_ignore else 'PROCESS'}")
    
    print()
    print("=== File Collection Test ===")
    files = collect_files(since_timestamp=0.0)
    print(f"Total files found: {len(files)}")
    
    # Show distribution by priority
    by_priority = {}
    for priority, file_path in files:
        by_priority[priority] = by_priority.get(priority, 0) + 1
    
    priority_names = {0: "LeetCode2025", 1: "CompanyWise", 2: "Other"}
    for priority in sorted(by_priority.keys()):
        print(f"  {priority_names.get(priority, f'Priority {priority}')}: {by_priority[priority]} files")