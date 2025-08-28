"""
Author: Nitin Gupta
Date: 2025-06-20
Description: Common utility methods for testing and debugging
"""
import json
from typing import Any, List, TypeVar, Type, Dict, Optional, Union, Tuple
import sys

T = TypeVar('T')


class CommonMethods:
    """
    A collection of common utility methods for testing and debugging.
    """

    @staticmethod
    def print_test(prefixConsoles: List[str], isInput: bool, *args) -> None:
        """
        Print test case information in a formatted way.

        Args:
            prefixConsoles: List of column headers
            isInput: Whether to print headers or values
            *args: Values to print
        """
        if isInput:
            print("-" * 20)

        if len(prefixConsoles) != len(args):
            raise ValueError("Number of prefixConsoles and arguments must be equal.")

        console = []
        i = 0
        for arg in args:

            if isinstance(arg, (list, tuple)):
                output = str(list(arg))
            elif isinstance(arg, dict):
                output = str(dict(arg))
            else:
                output = str(arg)

            console.append(prefixConsoles[i].strip() + ": " + output);
            i+=1

        print(console)

    @staticmethod
    def normalize(item: Any) -> Any:
        """
        Normalize data to allow proper comparison:
        - Dicts: sorted tuples
        - List of dicts: JSON strings (sorted)
        - Other iterables: sorted if possible
        """
        if isinstance(item, dict):
            return sorted((k, CommonMethods.normalize(v)) for k, v in item.items())

        elif isinstance(item, list):
            # Normalize each item in the list
            normalized_items = [CommonMethods.normalize(elem) for elem in item]
            try:
                return sorted(normalized_items)
            except TypeError:
                # As a fallback, sort by stringified representation
                return sorted(normalized_items, key=lambda x: json.dumps(x, sort_keys=True))

        elif isinstance(item, set):
            return sorted(CommonMethods.normalize(e) for e in item)

        elif isinstance(item, tuple):
            return tuple(CommonMethods.normalize(e) for e in item)

        else:
            return item

    @staticmethod
    def compare_result(actual: Any, expected: Any, verbose: bool = False) -> bool:
        """
        Compare actual and expected results with optional verbose sorting.

        Args:
            actual: The actual result.
            expected: The expected result.
            verbose: Whether to normalize and sort collections before comparing.

        Returns:
            bool: True if actual matches expected, False otherwise.
        """
        if verbose:
            actual = CommonMethods.normalize(actual)
            expected = CommonMethods.normalize(expected)

        return actual == expected


    @staticmethod
    def print_all_test_results(test_results: List[bool]) -> None:
        """
        Print a summary of all test results.

        Args:
            test_results: List of boolean test results
        """
        if not test_results:
            print("\nNo test results to display.")
            return

        passed = sum(1 for result in test_results if result)
        total = len(test_results)

        print("\n" + "=" * 50)
        print(f"TEST RESULTS: {passed}/{total} tests passed")
        print("=" * 50)

        if passed == total:
            print("✅ All tests passed!")
        else:
            print(f"❌ {total - passed} tests failed")

    @staticmethod
    def print_array(arr: List[Any]) -> None:
        """Print an array in a readable format."""
        print(CommonMethods.array_to_string(arr))

    @staticmethod
    def array_to_string(arr: List[Any]) -> str:
        """Convert an array to a string representation."""
        if not arr:
            return "[]"
        return "[" + ", ".join(map(str, arr)) + "]"

    @staticmethod
    def print_2d_array(matrix: List[List[Any]]) -> None:
        """Print a 2D array in a readable format."""
        print(CommonMethods.matrix_to_string(matrix))

    @staticmethod
    def matrix_to_string(matrix: List[List[Any]]) -> str:
        """Convert a 2D array to a string representation."""
        if not matrix:
            return "[[]]"
        return "\n".join([CommonMethods.array_to_string(row) for row in matrix])


# Create a singleton instance for easier importing
common = CommonMethods()
