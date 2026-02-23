"""
Author: Nitin Gupta
Date: 7/21/2025
Question Title: 377. Combination Sum IV
Link: https://leetcode.com/problems/combination-sum-iv/description/
Description: Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.



Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Example 2:

Input: nums = [9], target = 3
Output: 0


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000


Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
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
@DynamicProgramming

<p><p>
Company Tags
-----
@Amazon
@Facebook
@Google
@Snapchat
@Visa
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from functools import lru_cache
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution:
    def combinationSum4(self, nums: List[int], target: int) -> int:
        """
        This is similar to Combination Sum II, the only difference is that
        instead of giving the list of output, we need to tell the numbers.

        Which means instead of a building result, whenever we hit remaining==0, we can count it and move further.
        Also, we don't need to maintain the list to append and pop.

        Also, since we might be revisiting again and again for a number at which the count has already been calculated.
        We can use cache (dp) to avoid it
        """

        @lru_cache(maxsize=None)
        def backtrack(remaining: int) -> int:
            """
            if item already been computed, it returns effectively
            otherwise, it will calculate and cache it
            """

            if remaining == 0:
                return 1  # denotes 1 solution possible with current combinations

            result = 0
            for num in nums:
                if remaining - num >= 0:
                    result += backtrack(remaining - num)

            return result

        return backtrack(target)


class Solution_:
    def combinationSum4(self, nums: List[int], target: int) -> int:
        """
        This is similar to Combination Sum II, the only difference is that
        instead of giving the list of output, we need to tell the numbers.

        Which means instead of building result, whenever we hit remaining==0, we can count it and move further.
        Also, we don't need to maintain the list to append and pop.

        Also, since we might be revisiting again and again for a number at which the count has already been calculated.
        We can use cache (dp) to avoid it
        """

        def backtrack(dp: List[int], remaining: int) -> int:
            """
            if item already been computed, it returns effectively
            otherwise, it will calculate and cache it
            """

            if remaining == 0:
                return 1  # denotes 1 solution possible with current combinations

            if dp[remaining] != -1:
                return dp[remaining]

            result = 0
            for num in nums:
                if remaining - num >= 0:
                    result += backtrack(dp, remaining - num)

            dp[remaining] = result
            return result

        return backtrack([-1] * (target + 1), target)


def test(input_data,k,expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().combinationSum4(input_data, k)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1,2,3],4, 7),
                   test([9],3, 0)]
    CommonMethods.print_all_test_results(tests)
