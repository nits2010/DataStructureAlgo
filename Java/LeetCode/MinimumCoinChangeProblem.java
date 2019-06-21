package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-21
 * Description:https://leetcode.com/problems/coin-change/
 */
public class MinimumCoinChangeProblem {


    public static void main(String args[]) {
        int coins[] = {1, 2, 5}, amount = 11;
        System.out.println(coinChange(coins, amount));
    }
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
