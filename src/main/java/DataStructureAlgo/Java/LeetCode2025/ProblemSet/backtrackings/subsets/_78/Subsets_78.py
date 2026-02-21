"""
Author: Nitin Gupta
Date: 7/29/2025
Question Title: 78. Subsets
Link: https://leetcode.com/problems/subsets/description/
Description: Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
File reference
-----------
Duplicate {@link SubSet}
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

<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution_Bit:
    def subsets(self, nums: List[int]) -> List[List[int]]:

        result = []

        total_subsets = 2 ** len(nums)

        for mask in range(total_subsets):

            temp = []

            for bit in range(len(nums)):

                # check if this bit is set in mask

                if (mask & (1 << bit)) > 0:
                    temp.append(nums[bit])

            result.append(temp)

        return result

class Solution_Iterative:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        result = [[]]

        for num in nums:
            result += [subset+[num] for subset in result]
        
        return result

class Solution_Recursive:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        result = []
        subset = []
        def dfs(start ):
            result.append(subset[::])

            for idx in range(start, len(nums)):
                subset.append(nums[idx])

                dfs(idx + 1)

                subset.pop()

        dfs(0)
        return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_Recursive().subsets(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Recursive", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Iterative().subsets(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Iterative", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Bit().subsets(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Bit", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, 2, 3], [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]),
                   test([0], [[], [0]]), test([], [[]])]

    CommonMethods.print_all_test_results(tests)
