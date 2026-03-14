"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: Min Cost Climbing Stairs
Link: https://leetcode.com/problems/min-cost-climbing-stairs/
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
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        """
            If there are at max 2 steps available, then we will choose the one with minimum cost. 
            Now if we have >= 2 then while reaching third ( the cost we have to pay) would come from either first or second and we always choose the minimum of them.
            
        """
        if not cost:
            return 0

        n = len(cost)
        if n == 1:
            return cost[0]

        if n == 2:
            return min(cost[0], cost[1])

        first = cost[0]
        second = cost[1]
        for third in range(2, n):
            _min = cost[third] + min(first, second)
            first = second
            second = _min

        return min(first, second)
