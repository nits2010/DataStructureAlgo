"""
Author: Nitin Gupta
Date: 7/7/2025
Question Title: 47. Permutations II
Link: https://leetcode.com/problems/permutations-ii/description/
Description: Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.



Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link Permutations_46}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Array
@Backtracking
@Sorting

<p><p>
Company Tags
-----
@Amazon
@Microsoft
@Adobe
@Bloomberg
@Facebook
@LinkedIn
@Uber
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


class Solution_Recursive:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        all_permutations = []

        def permutations(start: int):
            if start == len(nums):
                all_permutations.append(nums[:])
                return

            # this will help us to know if the element has been tested or not for permutations, to avoid duplicate permutations
            lookup = set()

            for i in range(start, len(nums)):
                if nums[i] not in lookup:
                    # swap i and start
                    nums[i], nums[start] = nums[start], nums[i]

                    permutations(start + 1)

                    # backtrack
                    nums[start], nums[i] = nums[i], nums[start]

                    # add nums[i] in lookup as its used
                    lookup.add(nums[i])

        permutations(0)
        return all_permutations

class Solution_Iterative:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        all_permutations = [[]]

        for num in nums:
            next_permutations = set()
            for perm in all_permutations:
                for i in range(len(perm) + 1):
                    new_perm = perm[:i] + [num] + perm[i:]
                    next_permutations.add(tuple(new_perm))  # convert to tuple for set

            all_permutations = [list(p) for p in next_permutations]  # convert back to list of lists


        return all_permutations

def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_Recursive().permuteUnique(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Iterative().permuteUnique(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, 1, 2], [[1, 1, 2], [1, 2, 1], [2, 1, 1]]),
                   test([1, 1, 1], [[1, 1, 1]]),
                   test([1, 2, 3], [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]])]

    CommonMethods.print_all_test_results(tests)
