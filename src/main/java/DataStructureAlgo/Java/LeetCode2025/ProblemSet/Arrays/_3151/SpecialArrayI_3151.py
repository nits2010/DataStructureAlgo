"""
Author: Nitin Gupta
Date: 7/6/2025
Question Title: 3151. Special Array I
Link: https://leetcode.com/problems/special-array-i/description/
Description: An array is considered special if the parity of every pair of adjacent elements is different. In other words, one element in each pair must be even, and the other must be odd.

You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.



Example 1:

Input: nums = [1]

Output: true

Explanation:

There is only one element. So the answer is true.

Example 2:

Input: nums = [2,1,4]

Output: true

Explanation:

There is only two pairs: (2,1) and (1,4), and both of them contain numbers with different parity. So the answer is true.

Example 3:

Input: nums = [4,3,1,6]

Output: false

Explanation:

nums[1] and nums[2] are both odd. So the answer is false.



Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
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
@Array

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
    def isArraySpecial(self, nums: List[int]) -> bool:
        length = len(nums)
        if length == 1:
            return True

        for i in range(1, length):
            previous = nums[i-1] % 2
            current = nums[i] % 2
            if previous == current:
                return False

        return True


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().isArraySpecial(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1], True),
                   test([2,1,4], True),
                   test([4,3,1,6], False)]

    CommonMethods.print_all_test_results(tests)
