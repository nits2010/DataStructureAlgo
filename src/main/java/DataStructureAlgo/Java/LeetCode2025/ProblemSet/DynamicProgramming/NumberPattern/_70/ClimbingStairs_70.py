"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: Climbing Stairs
Link: https://leetcode.com/problems/climbing-stairs/
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

class Solution:
    def climbStairs(self, n: int) -> int:
        a = 1
        b = 2
        c = 0

        if n <= 2:
            return n

        for i in range(2, n):
            c = a + b
            a = b
            b = c

        return c
