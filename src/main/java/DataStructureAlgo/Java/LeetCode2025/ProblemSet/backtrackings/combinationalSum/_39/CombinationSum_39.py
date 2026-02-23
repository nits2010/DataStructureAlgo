"""
Author: Nitin Gupta
Date: 7/21/2025
Question Title: 39. Combination Sum
Link: https://leetcode.com/problems/combination-sum/description/
Description: Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []


Constraints:

1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
File reference
-----------
Duplicate {@link CombinationSumI}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Array
@Backtracking

<p><p>
Company Tags
-----
@Amazon
@Facebook
@Microsoft
@Airbnb
@Apple
@Airbnb
@Bloomberg
@GoldmanSachs
@Google
@IXL
@LinkedIn
@Quora
@Snapchat
@Square
@Uber
@VMware
@WalmartLabs
@Yelp
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
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        """
            We can use backtracking solution to build up the solution. 
        """
        result = []
        items = []

        def combinationSum(index:int, remaining_sum: int) : 
            if index >= len(candidates) or remaining_sum > target:
                return 
     
            if remaining_sum == 0:
                result.append(items[::])
                return

            for i in range(index, len(candidates)):
                
                if remaining_sum >= candidates[i]:
                    # track
                    items.append(candidates[i])

                    #recurse
                    combinationSum(i, remaining_sum-candidates[i]) # As the question stated to have 'n' number of possibilties to choose a number

                    # Backtrack
                    items.pop()
            
            return 

        combinationSum(0, target)
        return result


def test(input_data,k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().combinationSum(input_data, k)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([2,3,6,7], 7,[[2,2,3],[7]]),
                   test([2,3,5],8, [[2,2,2,2],[2,3,3],[3,5]]),
                   test([2],1, [])]

    CommonMethods.print_all_test_results(tests)
