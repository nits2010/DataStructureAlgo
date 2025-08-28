"""
Author: Nitin Gupta
Date: 8/13/2025
Question Title: 647. Palindromic Substrings
Link: https://leetcode.com/problems/palindromic-substrings/description
Description: Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
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
@TwoPointers
@String
@DynamicProgramming

<p><p>
Company Tags
-----
@Facebook
@Twitter
@Expedia
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution:
    def expandToPalindrome(self, s: str, left: int, right: int) -> int:
        count = 0  # every character is palindrome

        # expand to odd length
        length = len(s)
        while left >= 0 and right < length and s[left] == s[right]:
            count += 1
            left -= 1
            right += 1

        return count

    def countSubstrings(self, s: str) -> int:

        # assume every character as potential mid point of palindrome string. Expand towards left/right and count the palindrome.
        count = 0
        for i in range(len(s)):

            count += self.expandToPalindrome(s, i, i)  # odd length
            count += self.expandToPalindrome(s, i, i + 1)  # even length

        return count


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().countSubstrings(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("abc", 3),
                   test("aaa", 6)]

    CommonMethods.print_all_test_results(tests)
