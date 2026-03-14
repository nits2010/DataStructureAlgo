#!/usr/bin/env python3
"""
Shortcut script to run the unified problem generator.

This is a convenience script that forwards all arguments to the main generator
located in scripts/generators/unified_problem_generator.py
"""

import sys
import subprocess
from pathlib import Path

def main():
    # Get the path to the actual generator script
    script_dir = Path(__file__).resolve().parent
    generator_script = script_dir / "scripts" / "generators" / "unified_problem_generator.py"
    
    if not generator_script.exists():
        print(f"❌ ERROR: Generator script not found at {generator_script}")
        sys.exit(1)
    
    # Forward all arguments to the main generator
    cmd = [sys.executable, str(generator_script)] + sys.argv[1:]
    
    try:
        # Run the generator and forward its exit code
        result = subprocess.run(cmd, check=False)
        sys.exit(result.returncode)
    except KeyboardInterrupt:
        print("\n⚠️  Generation interrupted by user")
        sys.exit(1)
    except Exception as e:
        print(f"❌ ERROR: Failed to run generator: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()