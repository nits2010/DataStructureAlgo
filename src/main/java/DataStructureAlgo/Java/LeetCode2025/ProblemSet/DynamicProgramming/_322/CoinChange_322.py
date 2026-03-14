"""
Author: Nitin Gupta
Date: 12/03/2026
Question Title: 322. Coin Change
Link: https://leetcode.com/problems/coin-change/description/
Description: You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
 

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
File reference
-----------
Duplicate {@link CoinChangeMinimumCoins.java}
Similar {@link}
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
@Amazon
@Microsoft
@Google
@Mathworks
@Bloomberg
@Adobe 
@Affirm 
@Airbnb 
@Apple 
@BlackRock 
@CapitalOne 
@Facebook 
@GoldmanSachs 
@caMorgan 
@LinkedIn 
@@Oracle 
@Uber 
@Visa 
@VMware 
@WalmartLabs 
@Yahoo 
@Zappos
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


"""
    Time: O(c*amount) / O(amount)
"""
class Solution_BottomUp:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if not coins:
            return -1

        if amount < 0:
            return -1

        dp = [float("inf")] * (amount + 1)
        dp[0] = 0

        for amt in range(1, amount + 1):
            for c in coins:
                if amt - c >= 0 and dp[amt - c] != float("inf"):
                    dp[amt] = min(dp[amt], 1 + dp[amt - c])

        return dp[amount] if dp[amount] != float("inf") else -1


"""
    Time: O(c*amount) / O(amount)
"""
class Solution_TopDown:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if not coins:
            return -1

        if amount < 0:
            return -1

        memo = {}

        def dfs(amt):
            if amt in memo:
                return memo[amt]

            if amt == 0:
                return 0

            if amt < 0:
                return float("inf")

            min_coin = float("inf")
            for c in coins:
                min_coin = min(min_coin, 1 + dfs(amt - c))

            memo[amt] = min_coin
            return min_coin

        minimum_coin = dfs(amount)
        return minimum_coin if minimum_coin != float("inf") else -1


def test(coins, amount, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(
        ["coins", "amount", "Expected"], True, coins, amount, expected)
    pass_test, final_pass = True, True
    output = Solution_BottomUp().coinChange(coins, amount)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BottomUp", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    utput = Solution_TopDown().coinChange(coins, amount)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["TopDown", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(coins=[1, 2, 5], amount=11, expected=3),
                   test(coins=[2], amount=3, expected=-1),
                   test(coins=[1], amount=0, expected=0)]

    CommonMethods.print_all_test_results(tests)
