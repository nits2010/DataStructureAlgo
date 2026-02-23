"""
Author: Nitin Gupta
Date: 7/29/2025
Question Title: 90. Subsets II
Link: https://leetcode.com/problems/subsets-ii/description
Description: Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
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
@Array
@Backtracking
@BitManipulation

<p><p>
Company Tags
-----
@Microsoft
@Facebook
@Amazon
@Google
@Bloomberg
@Adobe
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time: O(2^n)
# Space : O(2^n) 
class Solution_V2:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        result = []
        subset = []
        nums.sort() # so that all duplicates come together

        def dfs(start):
            result.append(subset[::])

            i = start
            for i in range(start, len(nums)):
                if i > start and nums[i] == nums[i - 1]:
                    continue

                subset.append(nums[i])
                dfs(i + 1)
                subset.pop()
                i += 1

        dfs(0)
        return result

# Time: O(2^n)
# Space : O(2^n) + O(2^n)
class Solution_V1:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        result = []

        nums.sort() # so that all duplicates come together

        global_set = set()

        def dfs(start, subset):

            temp = tuple(subset)

            if temp not in global_set:
                result.append(subset[::])
                global_set.add(temp)

            for idx in range(start, len(nums)):

                subset.append(nums[idx])

                dfs(idx + 1, subset)

                subset.pop()

        dfs(0, [])
        return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_V1().subsetsWithDup(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["V1", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_V2().subsetsWithDup(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["V2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1,2,2], [[],[1],[1,2],[1,2,2],[2],[2,2]]),
                   test([0], [[],[0]])]

    CommonMethods.print_all_test_results(tests)
