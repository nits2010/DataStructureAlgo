"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: House Robber I
Link: https://leetcode.com/problems/house-robber-i/
Description:
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0
        n = len(nums)

        first = nums[0]
        second = max(nums[1], nums[0]) if n >= 2 else float("-inf")

        for i in range(2, n):
            _max = max(nums[i] + first, second)
            first = second
            second = _max

        return max(first, second)
