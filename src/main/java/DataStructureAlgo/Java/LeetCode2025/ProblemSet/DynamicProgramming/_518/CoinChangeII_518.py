"""
Author: Nitin Gupta
Date: 12/03/2026
Question Title: 518. Coin Change II
Link: https://leetcode.com/problems/coin-change-ii/description/
Description: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1
 

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link CoinChange_322.py}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@DynamicProgramming

<p><p>
Company Tags
-----
@Amazon 
@Bloomberg 
@Facebook 
@Google 
@IXL 
@Microsoft 
@Oracle 
@Uber 
@WalmartLabs 
@Yahoo
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import defaultdict, deque
import heapq
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time: O(t*c) , space: O(t)
class Solution_BottomUp:
    def change(self, amount: int, coins: List[int]) -> int:

        if amount < 0:
            return -1

        if amount == 0:
            return 1

        if not coins:
            return -1

        # memo[(amount,coin)] = number of ways to make amount using coins
        # memo[(amount, coin)] = memo[(amount,coin-1)] + memo[(amount - c, coins)] for c in coins
        # memo[(amount,coin-1)] -> if we don't take current coint, then amount would be possible with previous coins
        # memo[(amount - c, coins)] -> if we take the current coin, then we need to compute amount-c for all c in coins

        # translate to single D array
        # memo[(amount,coin-1)] -> memo[amount] of previous turn
        # memo[(amount - c, coins)] -> memo[amount-c]
        dp = [0] * (amount + 1)
        dp[0] = 1  # we can make 0 amount 1 ways by selecting nothing

        for c in coins:
            for amt in range(c, amount + 1):
                dp[amt] = dp[amt] + dp[amt - c]

        return dp[amt]


# Time: O(t*c) , space: O(c*t)
class Solution_TopDown:
    def change(self, amount: int, coins: List[int]) -> int:

        if amount < 0:
            return -1

        if amount == 0:
            return 1

        if not coins:
            return -1

        # memo[(amount,coin)] = number of ways to make amount using coins
        # memo[(amount, coin)] = memo[(amount,coin-1)] + memo[(amount - c, coins)] for c in coins
        # memo[(amount,coin-1)] -> if we don't take current coint, then amount would be possible with previous coins
        # memo[(amount - c, coins)] -> if we take the current coin, then we need to compute amount-c for all c in coins

        memo = {}  # (amount,coin)

        def dfs(amt, coin_index):
            state = (amt, coin_index)
            if state in memo:
                return memo[state]

            if amt == 0:
                return 1  # we got the amount hence 1 way

            if amt < 0 or coin_index == len(coins):
                return 0

            # don't take current coin
            total_ways = dfs(amt, coin_index + 1)

            # take current coin
            if amt - coins[coin_index] >= 0:
                total_ways += dfs(amt - coins[coin_index], coin_index)

            memo[state] = total_ways
            return total_ways

        return dfs(amount, 0)

def test(amount, coins, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["amount", "coins", "Expected"],
                             True, amount, coins, expected)
    pass_test, final_pass = True, True
    output = Solution_BottomUp().change(amount, coins)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BottomUp", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_TopDown().change(amount, coins)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["TopDown", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(amount=5, coins=[1, 2, 5], expected=4),
                   test(amount=3, coins=[2], expected=0)]

    CommonMethods.print_all_test_results(tests)
