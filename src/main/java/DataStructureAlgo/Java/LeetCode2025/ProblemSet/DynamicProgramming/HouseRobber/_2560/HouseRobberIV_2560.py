"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: House Robber IV
Link: https://leetcode.com/problems/house-robber-iv/
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
    """it is minimum of maximum -> Binary Search on range
    What is the range here?
        If robber rob all houses following constraint(no-adj, max k houses) then its maximum capability.
            if k = 1 then max value of the array is max capability
        if rober rob only one house then its minimum capabilitiy.
            if k = 1 then min value of the array is min capability

    Once we have range, we can find a mid (capability) and ask robber to rob in such a way that it can rob k houses.
        -> if k houses robbed, means we need to decrease the capability
        -> if k houses not robbed,  means we need to increase the capability

    Time:
        -> Binary search : O(log(max))
        -> Rob houses:  O(n) n-> number of houses
        -> Total: O(n*log(max))

    Space:
        -> O(1)

    """

    def is_possible(self, nums: List[int], capability, k) -> bool:
        """we don't need to find the best, we need to see if possible or not"""
        current_houses_robed = 0
        i = 0

        while i < len(nums):
            if nums[i] <= capability:  # allow robbing
                i += 2  # skip next house
                current_houses_robed += 1
            else:
                i += 1

            if current_houses_robed >= k:
                return True

        return current_houses_robed >= k  # minimum k house needs to rob

    def minCapability(self, nums: List[int], k: int) -> int:

        if not nums:
            return 0

        low, high = min(nums), max(nums)
        _minCapability = None

        while low <= high:
            mid = (low + high) // 2

            if self.is_possible(nums, mid, k):
                high = mid - 1
                _minCapability = mid
            else:
                low = mid + 1

        return _minCapability
