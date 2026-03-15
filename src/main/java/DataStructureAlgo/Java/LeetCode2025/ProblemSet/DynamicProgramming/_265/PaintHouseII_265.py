"""
Author: Nitin Gupta
Date: 15/03/2026
Question Title: 265. Paint House II 🔒
Link: https://leetcode.com/problems/paint-house-ii/description/ http://leetcode.ca/all/265.html
Description: There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link PaintHouse_256.py}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@DynamicProgramming

<p><p>
Company Tags
-----
@Apple 
@LinkedIn 
@Twitter
@Facebook
@Google
@Uber
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import defaultdict, deque
from functools import lru_cache
import heapq
from typing import collections, List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods

# Time / Space:  O(K * N) / O(1)
class Solution_BottomUp_Optimized:
    """
        In the Bottom up solution
        Dp state = (h_idx, last_color)
        memo[h_idx][last_color] = minimum cost of painting houses till h_idx with last color of house `last_color'
                                = min(memo[h_idx-1][c]) where 0<=c<=k and c!=last_color 

        Here we try to find the `min(memo[h_idx-1][c]) where 0<=c<=k and c!=last_color` that takes extra O(K) time but can we track it it self. 
        This is nothing but tracking the minimum of previous dp row. So every time we change the row, we can keep track of current_row minimu.
        However we need to make sure that we avoid take a value a minimum where the last_color = color, hence 
        we can track two minimums, min_1, min_2, at any point of time min_1 color is same as last_color then we can choose min_2. 


        With that, for computing current house cost, we just need min1, min2 of previous house cost. This we we can completely remove the dp list as we need variable to track the min's. 
        for current house cost
            cost = costs[i][color] + (prev_min1 if prev_color != color otherwise prev_min2)


        K = total colors
        N = Total houses
        This algo try a the color each house, that has total possible colors for house `k*N`. Since we than try to find the minimum cost amoung K color which get eliminated by tracking min. 
        Hence O(K * N)

        Time: O(K * N)
        Space: O(1)
    """

    def minCostII(self, costs: List[List[int]]) -> int:
        if not costs:
            return 0
        n = len(costs)
        k = len(costs[0])

        # We only need to track the results of the "previous" house and two min's and the color we used
        previous_min1, previous_min2, previous_color = 0, 0, -1

        for i in range(n):
            curr_min1, curr_min2, curr_color = float('inf'), float('inf'), -1

            for color in range(k):
                # avoid current color and choose the previous min
                _min_cost = previous_min1 if previous_color != color else previous_min2
                
                # update current house cost
                cost = costs[i][color] + _min_cost

                # calcualate the min's
                if cost < curr_min1:
                    curr_min2 = curr_min1
                    curr_min1 = cost
                    curr_color = color
                elif cost < curr_min2:
                    curr_min2 = cost

            # curr min will be previous mins
            previous_min1, previous_min2, previous_color = curr_min1, curr_min2, curr_color

        return previous_min1

        
# Time / Space:  O(K^2 * N) / O(N*K) Or O(K)
class Solution_BottomUp:
    """
        In Brute Force, we try all houses with all possible color combinations. 
        we had h_idx and last_color as in state. We have lot of overlapping problems as we are trying multiple combination for current house which did not affect previous house. 

        Dp state = (h_idx, last_color)
        memo[h_idx][last_color] = minimum cost of painting houses till h_idx with last color of house `last_color'
                                = min(memo[h_idx-1][c]) where 0<=c<=k and c!=last_color 

        K = total colors
        N = Total houses
        This algo try a the color each house has, that has total possible colors for house `k*N`. Since we than try to find the minimum cost amoung K color, that is O(k). 
        Hence O(K^2 * N)

        Time: O(K^2 * N)
        Space: O(N*K) there are N depth max and K color possibilities  
    """

    def minCostII(self, costs: List[List[int]]) -> int:
        n = len(costs)
        k = len(costs[0])
        dp = [[0] * k for _ in range(n)]

        # painting only single house with k colors would cost this much, result min(dp[0])
        dp[0] = costs[0]

        for h_idx in range(1, n):
            for last_color in range(k):

                # # get minimum cost
                # _min_cost = float('inf')
                # for color in range(k):
                #     if color == last_color:
                #         continue
                #     _min_cost = min(_min_cost, dp[h_idx-1][color])

                _min_cost = min(dp[h_idx-1][:last_color] +
                                dp[h_idx-1][last_color+1:])

                dp[h_idx][last_color] = _min_cost + costs[h_idx][last_color]

        return min(dp[-1])

    # Time: O(K^2 * N)
    # Space: O(K) there are N depth max and K color possibilities, we replace curr=last
    def minCostII_optimized(self, costs: List[List[int]]) -> int:
        n = len(costs)
        k = len(costs[0])
        dp = [0] * k

        # painting only single house with k colors would cost this much, result min(dp[0])
        dp = costs[0]

        for h_idx in range(1, n):
            curr_dp = dp[::]
            for last_color in range(k):

                # # get minimum cost
                # _min_cost = float('inf')
                # for color in range(k):
                #     if color == last_color:
                #         continue
                #     _min_cost = min(_min_cost, dp[color])

                _min_cost = min(dp[:last_color]+dp[last_color+1:])

                curr_dp[last_color] = _min_cost + costs[h_idx][last_color]

            dp = curr_dp

        return min(dp)


# Time / Space:  O(K^2 * N) / O(N+N*K) 
class Solution_TopDown:
    """
        In Brute Force, we try all houses with all possible color combinations. 
        we had h_idx and last_color as in state. We have lot of overlapping problems as we are trying multiple combination for current house which did not affect previous house. 

        Dp state = (h_idx, last_color)
        memo[h_idx][last_color] = minimum cost of painting houses till h_idx with last color of house `last_color'
                                = min(memo[h_idx-1][c]) where 0<=c<=k and c!=last_color 


        K = total colors
        N = Total houses
        This algo try a the color each house has, that has total possible colors for house `k*N`. Since we than try to find the minimum cost amoung K color, that is O(k).  
        Hence O(K^2 * N)

        Time: O(K^2 * N)
        Space: O( N+ N*K) there are N depth max and K color possibilities  
    """

    def minCostII(self, costs: List[List[int]]) -> int:
        n = len(costs)
        k = len(costs[0])
        memo = {}

        def dfs(h_idx, last_color):
            # all house painted, no more cost
            if h_idx == n:
                return 0

            if (h_idx, last_color) in memo:
                return memo[(h_idx, last_color)]

            _min_cost = float('inf')
            for color in range(k):  # O(K)

                # can't choose adj color
                if color == last_color:
                    continue

                # find minimum cost accross all houses using this color for h_idx house.
                _min_cost = min(_min_cost, dfs(
                    h_idx+1, color) + costs[h_idx][color])

            memo[(h_idx, last_color)] = _min_cost
            return _min_cost

        return dfs(0, -1)


# Time / Space:  O(K * K^N) / O(N) 
class Solution_BruteForce_Backtracking:
    """
        We try every possible color to each house and keep track the minimum cost accross all combinations

        algorithm:
        1. Choose a `color` and apply current house h_idx such that its different from last house color 'last_color' 
        2. Calculae the min_cost and try same for next house. 
        3. Once all the house are painted, then there is no more house to paint, cost=0 

        K = total colors
        N = Total houses
        This algo try a the color each house has, that has total possible colors for house `k^N`. Since we than try to find the minimum cost amoung K color, that is O(k). 
        Hence O(K . K ^ N)

        Time: O(K . K ^ N)
        Space: O(N) there are N depth max 


    """

    def minCostII(self, costs: List[List[int]]) -> int:
        n = len(costs)
        k = len(costs[0])

        def dfs(h_idx, last_color):

            # all house painted, no more cost
            if h_idx == n:
                return 0

            _min_cost = float('inf')
            for color in range(k):  # O(K)

                # can't choose adj color
                if color == last_color:
                    continue

                # find minimum cost accross all houses using this color for h_idx house.
                _min_cost = min(_min_cost, dfs(
                    h_idx+1, color) + costs[h_idx][color])

            return _min_cost

        return dfs(0, -1)


def test(costs, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["costs", "Expected"], True, costs, expected)
    pass_test, final_pass = True, True
    output = Solution_BruteForce_Backtracking().minCostII(costs)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BruteForce_Backtracking", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_TopDown().minCostII(costs)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["TopDown", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_BottomUp().minCostII(costs)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BottomUp", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_BottomUp().minCostII_optimized(costs)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BottomUp_2", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    output = Solution_BottomUp_Optimized().minCostII(costs)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BottomUp_optimized", "Pass"], False,
                            output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(costs=[[1, 5, 3], [2, 9, 4]], expected=5),
                   test(costs=[[1, 3], [2, 4]], expected=5)]

    CommonMethods.print_all_test_results(tests)
