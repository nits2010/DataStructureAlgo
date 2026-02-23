
"""
Author: Nitin Gupta
Date: 22/02/2026
Question Title: 131. Palindrome Partitioning
Link: https://leetcode.com/problems/palindrome-partitioning/description/
Description: Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
File reference
-----------
Duplicate {@link}
Similar {@link PalindromePartitioning.java}
extension {@link LongestPalindromicSubstring_5.py}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@String
@DynamicProgramming
@Backtracking

<p><p>
Company Tags
-----
@Apple
@Google
@tiktok
@Amazon
@Facebook
@Adobe 
@Bloomberg 
@Qualtrics 
@Uber 
@Yahoo
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time: O(n^2) + O(n * 2^n)
# Space: O(n^2)
class Solution:
    """ Precompute palindrome substrings, then backtrack to explore all valid cuts. """ 

    # Time: O(n^2)
    # Space: O(n^2)
    def precompute_palindrom(self, s):
        n = len(s)
        memo = [[False] * n for _ in range(n)]

        # 1 length string = palindrome
        for i in range(n):
            memo[i][i] = True

        for length in range(2, n + 1):
            for i in range(n - length + 1):
                j = i + length - 1

                if length == 2 and s[i] == s[j]:
                    memo[i][j] = True
                elif s[i] == s[j] and memo[i + 1][j - 1]:
                    memo[i][j] = True
                else:
                    memo[i][j] = False
        return memo

    def partition(self, s: str) -> List[List[str]]:
        n = len(s)
        memo = self.precompute_palindrom(s)
        result = []
        tmp = []

        # O(n * 2^n)
        def _parition_points(start):
            if start == n:
                result.append(tmp[:])
                return

            for i in range(start, n): # O(n)
                if memo[start][i]:
                    tmp.append(s[start : i + 1])
                    _parition_points(i + 1)
                    tmp.pop() # backtrack

        _parition_points(0)
        return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
