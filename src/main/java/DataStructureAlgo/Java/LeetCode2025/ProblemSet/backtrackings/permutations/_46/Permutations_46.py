"""
Author: Nitin Gupta
Date: 7/7/2025
Question Title: 46. Permutations
Link: https://leetcode.com/problems/permutations/description/
Description: Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
File reference
-----------
Duplicate {@link Permutation.java}
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
<p>
-----
@Microsoft
@Amazon
@Facebook
@LinkedIn
@Google


@Editorial https://leetcode.com/problems/permutations/solutions/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

## Time Complexity: O(n*n!)
## Space Complexity: O(n*n!)

class Solution_Recursive:
    def permute(self, nums: List[int]) -> List[List[int]]:
        all_permutations = []

        def permutations(nums: List[int], start, end):
            nonlocal all_permutations
            if start == end:
                all_permutations.append(nums[:])  # nums[:] makes a copy of nums
                return

            for i in range(start, end + 1):  # try all numbers in b/w [start,end]

                # swap i and start
                nums[i], nums[start] = nums[start], nums[i]

                permutations(nums, start + 1, end)

                # backtrack swap i and start
                nums[start], nums[i] = nums[i], nums[start],

        permutations(nums, 0, len(nums) - 1)
        return all_permutations


class Solution_RecursiveV2:
    def permute(self, nums: List[int]) -> List[List[int]]:
        all_permutations = []

        def backtrack(start:int):
            if start == len(nums):
                all_permutations.append(nums[:])  # nums[:] makes a copy of nums
                return

            for i in range(start, len(nums)):  # try all numbers in b/w [start,end]

                # swap i and start
                nums[i], nums[start] = nums[start], nums[i]

                backtrack(start + 1)

                # backtrack swap i and start
                nums[start], nums[i] = nums[i], nums[start],

        backtrack(0)
        return all_permutations

class Solution_Iterative:
    def permute(self, nums: List[int]) -> List[List[int]]:
        all_permutations = [[]] # this contains all permutations, starting with the empty list

        # start with each number in nums
        for num in nums:
            next_permutations = []

            #iterate over all existing permutations
            for perm in all_permutations:
                #insert num in all possible positions in existing permutation
                for i in range(len(perm) + 1):
                    new_perm = perm[:i] + [num] + perm[i:]
                    #add new permutation to next_permutations
                    next_permutations.append(new_perm)
            #keep track of all permutations
            all_permutations = next_permutations


        return all_permutations


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_Recursive().permute(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_RecursiveV2().permute(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Iterative().permute(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, 2, 3], [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]),
                   test([0, 1], [[0, 1], [1, 0]]),
                   test([1], [[1]])]

    CommonMethods.print_all_test_results(tests)
