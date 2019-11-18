package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-21
 * Description:
 * https://leetcode.com/problems/coin-change/
 * https://leetcode.com/problems/coin-change-2/
 */
public class CoinChangeProblem {


    public static void main(String []args) {
        testMinimumCoins();
        numberOfWays();
    }

    static void testMinimumCoins() {
        int coins[] = {1, 2, 5}, amount = 11;
        System.out.println(CoinChangeMinimumCoins.coinChange(coins, amount));
    }

    static void numberOfWays() {
        int coins[] = {1, 2, 5};
        System.out.println(CoinChangeNumberOfWays.numberOfWays(5, coins));
    }

}

/**
 * * Description:https://leetcode.com/problems/coin-change/
 * * You are given coins of different denominations and a total amount of money amount.
 * * Write a function to compute the fewest number of coins that you need to make up that amount.
 * * If that amount of money cannot be made up by any combination of the coins, return -1.
 * * <p>
 * * Example 1:
 * * <p>
 * * Input: coins = [1, 2, 5], amount = 11
 * * Output: 3
 * * Explanation: 11 = 5 + 5 + 1
 * * Example 2:
 * * <p>
 * * Input: coins = [2], amount = 3
 * * Output: -1
 * * Note:
 * * You may assume that you have an infinite number of each kind of coin.
 * * <p>
 * * Similar to Knapsack problem
 */
class CoinChangeMinimumCoins {
    // minimum[v] defines the minimum number of coins required to make amount

    //minimum[v] = 1+ Minimum(minimum[v-coin[i]] for all i-> 0, n)
    // we need to find  the minimum coins required to make value v; and add 1
    // such that we know the minimum number of coin required to make minimum[v - coin[i]]
    public static int coinChange(int[] coins, int amount) {

        if (null == coins || coins.length == 0)
            return -1;


        int minimum[] = new int[amount + 1];


        int n = coins.length;

        minimum[0] = 0;

        for (int value = 1; value <= amount; value++) {
            minimum[value] = Integer.MAX_VALUE;

            for (int c = 0; c < n; c++) {

                if (coins[c] <= value && minimum[value - coins[c]] != Integer.MAX_VALUE) {
                    minimum[value] = Math.min(minimum[value], minimum[value - coins[c]] + 1);
                }
            }
        }
        return minimum[amount] == Integer.MAX_VALUE ? -1 : minimum[amount];
    }
}

/**
 * Description: https://leetcode.com/problems/coin-change-2/
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of
 * each kind of coin.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 * <p>
 * <p>
 * Note:
 * <p>
 * You can assume that
 * <p>
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 */
class CoinChangeNumberOfWays {

    //total[i][j] = number of ways to make sum i using j coins
    //total[i][j] = total[i][j-1] + total[i-coins[j]][j]
    // total[i][j-1] we dont take the coin
    //total[i-coin[j]][j] we take the coin j then we need to find the sum of i-coin[j]
    //total[0][j(0...coins.length-1)] = 1

    public static int numberOfWays(int amount, int[] coins) {

        int total[] = new int[amount + 1];
        total[0] = 1;
        int n = coins.length;

        for (int c = 0; c < n; c++) {
            for (int i = coins[c]; i <= amount; i++)
                total[i] += total[i - coins[c]];
        }
        return total[amount];
    }

}