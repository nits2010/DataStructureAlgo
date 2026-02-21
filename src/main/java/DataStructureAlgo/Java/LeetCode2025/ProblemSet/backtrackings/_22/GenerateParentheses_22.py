"""
Author: Nitin Gupta
Date: 21/02/2026
Question Title: 22. Generate Parentheses
Link: https://leetcode.com/problems/generate-parentheses/description/
Description: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8

File reference
-----------
Duplicate {@link GenerateValidParenthesis.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@DynamicProgramming
@Backtracking

<p><p>
Company Tags
-----
@Facebook
@Amazon
@Microsoft
@Apple
@Adobe
@Alibaba 
@Bloomberg 
@Google 
@Lyft 
@Nutanix 
@Nvidia 
@Samsung 
@SAP 
@Snapchat 
@Spotify 
@Uber 
@WalmartLabs 
@Yahoo 
@Yandex 
@Yelp 
@Zenefits
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class GenerateParentheses_22:
    def generateParenthesis(self, n: int) -> List[str]:
        
        result = []

        def dfs(current, open, close):
            if open == 0 and close == 0:
                result.append(current)
                return 
            
            if open > 0: # we have open paranthesis left to add, lets add it
                dfs(current + "(" , open - 1, close)
            
            if open < close : # we can place a close when open exists 
                dfs(current + ")" , open , close - 1)



        dfs("", n, n)
        return result
    
def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = GenerateParentheses_22().generateParenthesis(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(1,  ["()"]),
                   test(3,  ["((()))","(()())","(())()","()(())","()()()"])]

    CommonMethods.print_all_test_results(tests)
