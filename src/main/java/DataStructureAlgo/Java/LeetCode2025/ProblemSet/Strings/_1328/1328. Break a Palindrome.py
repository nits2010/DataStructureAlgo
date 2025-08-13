"""
Author: Nitin Gupta
Date: 8/13/2025
Question Title: 1328. Break a Palindrome
Link: https://leetcode.com/problems/break-a-palindrome/description
Description: Given a palindromic string of lowercase English letters palindrome, replace exactly one character with any lowercase English letter so that the resulting string is not a palindrome and that it is the lexicographically smallest one possible.

Return the resulting string. If there is no way to replace a character to make it not a palindrome, return an empty string.

A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, a has a character strictly smaller than the corresponding character in b. For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth character, and 'c' is smaller than 'd'.



Example 1:

Input: palindrome = "abccba"
Output: "aaccba"
Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
Of all the ways, "aaccba" is the lexicographically smallest.
Example 2:

Input: palindrome = "a"
Output: ""
Explanation: There is no way to replace a single character to make "a" not a palindrome, so return an empty string.


Constraints:

1 <= palindrome.length <= 1000
palindrome consists of only lowercase English letters.
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
@Greedy
@String

<p><p>
Company Tags
-----
@Expedia
@VMware
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
    def breakPalindrome(self, palindrome: str) -> str:
        result = ""

        # we start do this from center, however we can observe that, doing from center we will always move left (except one case) and try all the character to be replace
        # which ends up the first character of the string in worst case.
        # Hence, we can start scanning the character from left to right to replace
        # to make lexicographically smallest, we need to replace any character which is greater then 'a' as soon we find.

        # only the one case, we would reach it end for best solution is that, all the character in string are 'a' only. in such case, we need to replace last a to 'b'
        length = len(palindrome)

        if length <= 1:
            return result

        for i in range(length // 2):  # we can stop at mid
            if palindrome[i] != "a":
                # replace this character with a to make smallest string
                return palindrome[:i] + "a" + palindrome[i + 1 :]

        result = palindrome[: length - 1] + "b"
        return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().breakPalindrome(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("abccba", "aaccba"),
                   test("a", ""),
                   test("aa", "ab"),
                   test("aaaa", "aaab"),
                   test("b", "")]

    CommonMethods.print_all_test_results(tests)
