"""
Author: Nitin Gupta
Date: 7/14/2025
Question Title: 2270. Number of Ways to Split Array
Link: https://leetcode.com/problems/number-of-ways-to-split-array/description/
Description: You are given a 0-indexed integer array nums of length n.

nums contains a valid split at index i if the following are true:

The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
There is at least one element to the right of i. That is, 0 <= i < n - 1.
Return the number of valid splits in nums.



Example 1:

Input: nums = [10,4,-8,7]
Output: 2
Explanation:
There are three ways of splitting nums into two non-empty parts:
- Split nums at index 0. Then, the first part is [10], and its sum is 10. The second part is [4,-8,7], and its sum is 3. Since 10 >= 3, i = 0 is a valid split.
- Split nums at index 1. Then, the first part is [10,4], and its sum is 14. The second part is [-8,7], and its sum is -1. Since 14 >= -1, i = 1 is a valid split.
- Split nums at index 2. Then, the first part is [10,4,-8], and its sum is 6. The second part is [7], and its sum is 7. Since 6 < 7, i = 2 is not a valid split.
Thus, the number of valid splits in nums is 2.
Example 2:

Input: nums = [2,3,1,0]
Output: 2
Explanation:
There are two valid splits in nums:
- Split nums at index 1. Then, the first part is [2,3], and its sum is 5. The second part is [1,0], and its sum is 1. Since 5 >= 1, i = 1 is a valid split.
- Split nums at index 2. Then, the first part is [2,3,1], and its sum is 6. The second part is [0], and its sum is 0. Since 6 >= 0, i = 2 is a valid split.


Constraints:

2 <= nums.length <= 105
-105 <= nums[i] <= 105
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
@PrefixSum

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
    def waysToSplitArray(self, nums: List[int]) -> int:
        """
        We can calculate the prefix sum on the fly using total sum.
        """
        n = len(nums)
        total = sum(nums)

        split_points = 0
        left_sum = 0

        for i in range(0, n - 1):
            left_sum += nums[i]
            right_sum = total - left_sum

            if left_sum >= right_sum:
                split_points += 1

        return split_points


class Solution_PrefixSum:
    def waysToSplitArray(self, nums: List[int]) -> int:
        """
        Calculate prefix sum and then find all the valid split points.
        """
        n = len(nums)

        prefix_sum = [0] * n

        prefix_sum[0] = nums[0]

        for i in range(1, n):
            prefix_sum[i] = prefix_sum[i - 1] + nums[i]

        split_points = 0

        for i in range(0, n - 1):
            left_sum = prefix_sum[i]
            right_sum = prefix_sum[n - 1] - left_sum

            if left_sum >= right_sum:
                split_points += 1

        return split_points


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_PrefixSum().waysToSplitArray(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["PrefixSum", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution().waysToSplitArray(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["ON-FLY-PrefixSum", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    return final_pass


if __name__ == "__main__":
    tests: List = [test([10,4,-8,7], 2),
                   test([2,3,1,0], 2)]

    CommonMethods.print_all_test_results(tests)
