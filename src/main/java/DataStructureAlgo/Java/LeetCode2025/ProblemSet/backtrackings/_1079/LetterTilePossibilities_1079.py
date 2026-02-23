"""
Author: Nitin Gupta
Date: 7/18/2025
Question Title: 1079. Letter Tile Possibilities
Link: https://leetcode.com/problems/letter-tile-possibilities/description
Description: You have n  tiles, where each tile has one letter tiles[i] printed on it.

Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.



Example 1:

Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: tiles = "AAABBC"
Output: 188
Example 3:

Input: tiles = "V"
Output: 1


Constraints:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Backtracking
@HashTable
@String
@Counting

<p><p>
Company Tags
-----
@Microsoft
@Google
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque, Counter
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution_:
    def numTilePossibilities(self, tiles: str) -> int:

        freq = Counter(tiles)

        def dfs():
            result = 0

            for i, item in freq.items():
                if item != 0:
                    # Track
                    freq[i] -= 1

                    result += 1  # count current char
                    result += dfs()

                    # backtrack
                    freq[i] += 1

            return result

        return dfs()


class Solution:
    def numTilePossibilities(self, tiles: str) -> int:

        freq = [0] * 26

        for ch in tiles:
            freq[ord(ch) - ord('A')] += 1

        def dfs():
            sum = 0

            for i in range(0, 26):
                if freq[i] != 0:
                    # Track
                    freq[i] -= 1

                    sum += 1  # count current char
                    sum += dfs()

                    # backtrack
                    freq[i] += 1

            return sum

        return dfs()


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().numTilePossibilities(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_().numTilePossibilities(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Outputv2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    return final_pass


if __name__ == "__main__":
    tests: List = [test("AAB", 8),
                   test("AAABBC", 188),
                   test("V", 1),
                   test("XZ", 4), ]

    CommonMethods.print_all_test_results(tests)
