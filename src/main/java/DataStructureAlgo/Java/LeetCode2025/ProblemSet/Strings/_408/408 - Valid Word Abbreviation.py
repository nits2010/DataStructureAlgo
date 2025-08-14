"""
Author: Nitin Gupta
Date: 8/14/2025
Question Title: 408 - Valid Word Abbreviation
Link: https://leetcode.com/problems/valid-word-abbreviation/description/ | https://leetcode.ca/2017-01-11-408-Valid-Word-Abbreviation/
Description: A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.

For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)
The following are not valid abbreviations:

"s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
"s010n" (has leading zeros)
"s0ubstitution" (replaces an empty substring)
Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.

A substring is a contiguous non-empty sequence of characters within a string.



Example 1:

Input: word = "internationalization", abbr = "i12iz4n"
Output: true
Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
Example 2:

Input: word = "apple", abbr = "a2e"
Output: false
Explanation: The word "apple" cannot be abbreviated as "a2e".


Constraints:

1 <= word.length <= 20
word consists of only lowercase English letters.
1 <= abbr.length <= 10
abbr consists of lowercase English letters and digits.
All the integers in abbr will fit in a 32-bit integer.
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@easy
@TwoPointers
@String
@LeetCodeLockedProblem
@PremiumQuestion

<p><p>
Company Tags
-----
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
    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        m:int = len(word)
        n:int = len(abbr)

        i:int = 0
        j:int = 0
        num : int = 0

        while i < m and j < n :

            if abbr[j].isdigit() :
                if abbr[j] == '0' and num == 0:
                    return False
                # form number
                num = num * 10 + int(abbr[j])
            else:
                i += num
                #reset num
                num = 0

                # match char
                if (i >= m and j < n) or word[i] != abbr[j] :
                    return False
                i += 1

            j+= 1

        # shift i by num as abbr would have finished (considerably n << m
        i += num
        return i == m and j == n




def test(input_data, abbr, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().validWordAbbreviation(input_data, abbr)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("internationalization", "i12iz4n", True),
                   test("apple", "a2e", False),
                   test("apple", "a2e1c1", False),
                   test("apple", "a02le", False),
                   test("apple", "a0e", False),]

    CommonMethods.print_all_test_results(tests)
