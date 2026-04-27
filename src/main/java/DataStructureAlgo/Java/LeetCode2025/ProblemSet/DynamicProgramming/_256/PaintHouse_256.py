"""
Author: Nitin Gupta
Date: 15/03/2026
Question Title: 256. Paint House 🔒
Link: https://leetcode.com/problems/paint-house/  https://leetcode.ca/all/256.html
Description: There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
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
@DynamicProgramming
@PremimumQuestion

<p><p>
Company Tags
-----
@Apple 
@LinkedIn 
@Twitter
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import defaultdict, deque
import heapq
from typing import get_args, List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods


class Solution_Linear:
    """
    dp[i] Defines the minimum cost of painting ith house. 
    dp[i] = minimum (dp[i-1], dp[i-2], dp[i-3]) + cost
    
    Since we need only last 3 values, we can simply use three variables. 
    cr = cost if pick r, cb cost if pick b, cg cost if pick g
    
    cr = min(cb, cg) + cost(r)
    cb = min(cr, cg) + cost(b)
    cg = min(cr, cb) + cost(g) 
    
    Time: O(n) / O(n)
    
    """

    def minCost(self, costs: List[List[int]]) -> int:
        if not costs:
            return 0

        # cr = cost if pick r, cb cost if pick b, cg cost if pick g
        cr, cb, cg = 0,0,0
        
        for r,b,g in costs:
            _cr = min(cb, cg) + r
            _cb = min(cr, cg) + b
            _cg = min(cr, cb) + g

            cr, cb, cg = _cr, _cb, _cg
        
        return min(cr, cb, cg)


class Solution_DP:
    """
    For any house, we can pick either of the color r,b,g such that previous house was painted with different color. 
   
    We will always choose which gives use minimum cost as we need to minimize the whole cost. 
    Hence,
    if we pick r for previous house then we have to choose b,g for this house
        if we pick b then we take minimum cost [r,g] + cost(b)
        if we pick g then we take minimum cost [r,b] + cost(g)
        if we pick r then we take minimum cost [b,g] + cost(r)
        
    Result would be minimum of all of them. 
    
    dp[i] Defines the minimum cost of painting ith house. 
    dp[i] = minimum (dp[i-1], dp[i-2], dp[i-3]) + cost
    
    to compute the cost, we choose either of the color and then take minimum
    
    Time: O(n) / O(n)
    
    """
    def minCost(self, costs: List[List[int]]) -> int:
        if not costs:
            return 0
        
        n = len(costs)
        dp = [0] * n # define the cost of painting ith house with minimum cost dp[i]
        
        # if only one house
        dp[:] = costs[0] # result would be minumum of all
                
        # pain rest of the houses
        for i in range(1, n): 
            d_r = min(dp[1], dp[2]) + costs[i][0]
            d_b = min(dp[0], dp[2]) + costs[i][1]
            d_g = min(dp[0], dp[1]) + costs[i][2]
            
            dp[0], dp[1], dp[2] = d_r, d_b, d_g
        
        return min(dp)
            
            
def test(costs, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["costs", "Expected"], True, costs, expected)
    pass_test, final_pass = True, True
    output = Solution_DP().minCost(costs)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DP", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_Linear().minCost(costs)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Linear", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(costs=[[17, 2, 17], [16, 16, 5], [14, 3, 19]], expected=10),
                   test(costs=[[7, 6, 2]], expected=2)]

    CommonMethods.print_all_test_results(tests)
