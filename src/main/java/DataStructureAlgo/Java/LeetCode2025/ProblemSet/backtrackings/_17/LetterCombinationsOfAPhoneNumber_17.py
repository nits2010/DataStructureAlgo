"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 17. Letter Combinations of a Phone Number
Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
Description: Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


 

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

1 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].

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
@HashTable
@String
@Backtracking

<p><p>
Company Tags
-----
@Amazon
@Microsoft
@Facebook
@Uber
@Apple
@Airbnb 
@Atlassian 
@Bloomberg 
@Dropbox 
@eBay 
@Google 
@caMorgan 
@Lyft 
@MorganStanley 
@Nutanix 
@Oracle 
@Pinterest 
@Quip(Salesforce) 
@Roblox 
@Uber 
@VMware 
@WalmartLabs 
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
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []
        map = {
            0: "",
            1: "",
            2: "abc",
            3: "def",
            4: "ghi",
            5: "jkl",
            6: "mno",
            7: "pqrs",
            8: "tuv",
            9: "wxyz",
        }

        result = []
        n = len(digits)

        def dfs(idx, current):
            if idx == n:
                result.append(current[:])
                return

            _str = map[int(digits[idx])]
            for i in range(len(_str)):
                dfs(idx + 1, current + _str[i])

        dfs(0, "")
        return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().letterCombinations(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("23", ["ad","ae","af","bd","be","bf","cd","ce","cf"]),
                   test("2", ["a","b","c"])]

    CommonMethods.print_all_test_results(tests)
 