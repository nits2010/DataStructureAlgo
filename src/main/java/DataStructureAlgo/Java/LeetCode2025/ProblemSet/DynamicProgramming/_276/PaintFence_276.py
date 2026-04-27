"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 276 - Paint Fence
Link: https://leetcode.com/problems/paint-fence/description/ https://leetcode.ca/2016-09-01-276-Paint-Fence/
Description: You are painting a fence of n posts with k different colors. You must paint the posts following these rules:

Every post must be painted exactly one color.
There cannot be three or more consecutive posts with the same color.
Given the two integers n and k, return the number of ways you can paint the fence.
Input: n = 3, k = 2
Output: 6
Explanation: All the possibilities are shown.
Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.

Input: n = 1, k = 1
Output: 1

Input: n = 7, k = 2
Output: 42

File reference
-----------
Duplicate {@link PaintFence.java}
Similar {@link PaintHouse_256.py}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@DynamicProgramming

<p><p>
Company Tags
-----
@Google
@LinkedIn
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import defaultdict, deque
import heapq
from typing import List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods


# Time/Space: O(K^2*N) / O(NK)
class Solution_Bottom_up:
    """
        In the Top down we are unncessary counting last_color, all we need to worry about the state where last two color were same or different. 
        
        Now at any post, we have two choices
        1. Same: Paint the fence with same color, this means we can choose only 1 color out k since we need to paint with same color. 
            same_color ways = 1
        2. Different: Paint the fence with different color, that means we can paint current with K-1 colors. 
            diff_color ways = k-1
        
        
        Now, lets say we painted the two consicutive fence, we again reach the condition of 
        A. Previous State = Same [R R]
        
            New State:
                1. Same: We can't paint this fence with same color as its voliate the constraint, hence we have choices 0. [R R R] not possible
                    Ways = 0 
                2. Different: We can paint with different color, choices k-1; [R R G], K = 2
                    ways = k-1
            total ways = k - 1 with diff
            
            dfs(i, same) = (k-1) * dfs(i+1, diff)
        
        B. Previous State = Diff [ R G ]

            New State:
                1. Same : We paint the fence as previous color,  [R G G]
                    Ways = 1
                2. Different : We paint the fence with different color k-1 ; [R G R]
                    ways = k - 1
            
            dfs(i, diff) = 1*dfs(i+1, same) + (k-1) * dfs(i+1, diff)
        
        Hence, we can derive the current state with previous state as 
        dfs(i, same) = (k-1) * dfs(i+1, diff)
        dfs(i, diff) = 1*dfs(i+1, same) + (k-1) * dfs(i+1, diff)
        
        Bottom_up [Reverse thinking]
        --------
        same[i] = ways where last two posts are same
        diff[i] = ways where last two posts are different
        
        
        For same[i] to happen:
        ... A B B
        So the previous state must be:
        ... A B
        which is diff[i-1]
        Hence;
        same[i] = diff[i-1]
        
        For diff[i] we create:
        A B C
        
        From either
        A A
        A B
        
        but we must choose a color different from the last one
        Choices: k-1
        diff[i] = (same[i-1] + diff[i-1]) * (k-1) 
        
        
        with base case 
            i == n then return 1
            
            start
                same = k ( we have k choices initially )
                diff = k*(k-1)
                
            
            Time: O(n)
            Space: O(n)
      
    """

    def numWays(self, n, k):
        
        same = [0] * (n+1)
        diff = [0] * (n+1)
        
        if n == 0:
            return 0  # no post, no paint
        
        if n == 1:
            return k  # 1 post, k way
        
        
        same[2] = k # 2 posts, k ways with same color
        diff[2] = k*(k-1) # choose k form K kCK  
        
        for i in range(3,n+1):
            same[i] = diff[i-1]
            diff[i] = (same[i-1] + diff[i-1])*(k-1)
        
        return same[n] + diff[n]
    
    #  Time: O(n)
    # Space: O(1)
    def numWays_SpaceOptimized(self, n, k):
        if n == 0:
            return 0  # no post, no paint

        if n == 1:
            return k  # 1 post, k way

        same = k  # 2 posts, k ways with same color
        diff = k*(k-1)  # choose k form K kCK

        for _ in range(3, n+1):
            new_same = diff
            new_diff = (same + diff)*(k-1)
            
            same, diff = new_same, new_diff
            

        return same + diff


# Time/Space: O(K^2*N) / O(NK)
class Solution_TopDown:
    """
      At each fence, we can either paint this fence with same color as previous or a different color.
      If we paint as previous house with 'c' color then this house can only be painted with 'c' color if consecutive_count < 2 otherwise we need to choose a different color.

      Hence, we need to know the last_color and consecutive_count

      if we paint all the fences then no. of ways = 1
      if we can't paint current fence with 'c' color since consecutive_count = 2 then 0 ways
      
      Time Complexity: Since We are painting all N houses with k different colors its O(K*N) 
      Space Complexity: O(NK)
      
    """

    def numWays(self, n, k):
        memo = {} # (i,color, count)
        def dfs(f_idx, last_color, cons_count):
            # if we can't paint current fence with 'c' color since consecutive_count > 2 then 0 ways
            if cons_count > 2:
                return 0

            # if we paint all the fences then no. of ways = 1
            if f_idx == n:
                return 1

            state = (f_idx, last_color, cons_count)
            if state in memo:
                return memo[state]
            
            ways = 0
            for color in range(k):
                count = cons_count+1 if color == last_color else 1
                ways += dfs(f_idx+1, color, count)

            memo[state] = ways
            return ways

        return dfs(0, -1, 0)


# Time/Space: O(K^N) / O(N)
class Solution_BruteForce:
    """
      At each fence, we can either paint this fence with same color as previous or a different color.
      If we paint as previous house with 'c' color then this house can only be painted with 'c' color if consecutive_count < 2 otherwise we need to choose a different color.

      Hence, we need to know the last_color and consecutive_count

      if we paint all the fences then no. of ways = 1
      if we can't paint current fence with 'c' color since consecutive_count = 2 then 0 ways
      
      At each post:

        Try all k colors

        If color == prev_color → increment consecutive_count

        If consecutive_count == 3 → return 0

        Otherwise continue recursion

        Total ways = sum of all valid recursive branches
        
        
      Time Complexity: k choices per post, total k^n
      Space Complexity: O(N)
      
    """

    def numWays(self, n, k):

        def dfs(f_idx, last_color, cons_count):
            #if we can't paint current fence with 'c' color since consecutive_count > 2 then 0 ways
            if cons_count > 2:
                return 0

            #if we paint all the fences then no. of ways = 1
            if f_idx == n:
                return 1

           
            ways = 0
            for color in range(k):
                count = cons_count+1 if color == last_color else 1
                ways += dfs(f_idx+1, color, count)
            
            return ways
        
        return dfs(0, -1, 0)
    


def test(n,k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["n", "k", "Expected"], True, n,k, expected)
    pass_test, final_pass = True, True
    output = Solution_BruteForce().numWays(n,k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_TopDown().numWays(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["TopDown", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_Bottom_up().numWays(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Bottom_up", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_Bottom_up().numWays_SpaceOptimized(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Bottom_up_SpaceOptimized", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(n=3, k=2, expected=6),
                   test(n=1, k=1, expected=1),
                   test(n=7, k=2, expected=42)]

    CommonMethods.print_all_test_results(tests)
