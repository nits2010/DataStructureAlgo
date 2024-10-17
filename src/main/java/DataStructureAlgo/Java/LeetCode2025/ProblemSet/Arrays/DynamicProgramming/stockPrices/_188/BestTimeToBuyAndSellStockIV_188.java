package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._188;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/17/2024
 * Question Category: 188. Best Time to Buy and Sell Stock IV
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 * <p>
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitKTransactions__BestTimeToBuySellStockIV}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._123.BestTimeToBuyAndSellStockIII_123}
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Nvidia
 * @Google
 * @Citadel
 * @Facebook
 * @caMorgan <p><p>
 * @Editorial
 */
public class BestTimeToBuyAndSellStockIV_188 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{2, 4, 1}, 2, 2);
        test &= test(new int[]{3, 2, 6, 5, 0, 3}, 2, 7);
        test &= test(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}, 5, 15);
        test &= test(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}, 9, 15);

        CommonMethods.printResult(test);
    }

    private static boolean test(int[] prices, int k, int expected) {
        System.out.println("---------------------------------------------------");
        System.out.println("Input: prices = " + Arrays.toString(prices) + ", k = " + k + " Expected: " + expected);

        int output;
        boolean pass, finalPass = true;

        SolutionDPSpaceOptimized solutionDPSpaceOptimized = new SolutionDPSpaceOptimized();
        output = solutionDPSpaceOptimized.maxProfit(k, prices);
        pass = output == expected;
        System.out.println("DP Space Optimized: " + output + " Pass: " + pass);
        finalPass &= pass;
        return finalPass;
    }

    /**
     * for step by step optimization see {@link DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitKTransactions__BestTimeToBuySellStockIV}
     * <p>
     * <p>
     * Profit[t][i] = represent the profit on `i`th day by doing t transactions.
     * On `i`the day we have two choices
     * 1. Don't do any transaction on ith day, as doing any transaction cause lower profit, hence take the last day profit on this transaction. Profit[t][i-1]
     * 2. If we are making any transaction in ith day, then we need to find the minimum value in [0...i-1] range 'j such that prices[i] > prices[j]. Which means
     * buying stock on jth day and selling on ith day. Since we must have profit with us on the day of the last transaction, which is profit[t-1][j].
     * Hence, in order to do transaction on ith day, we need to get
     * max(prices[i] - prices[j] + profit[t-1][j]) for all j -> [0...i-1]
     * this can be written as
     * max(profit[t-1][j] - prices[j]) + prices[i] since for all j -> [0...i-1], prices[i] will be constant.
     * And max(profit[t-1][j] - prices[j]) -> this is nothing but finding the max profit for j -> [0..i-1]
     * <p>
     * Hence,
     * profit[t][i] = Max ( profit[t][i-1], max( profit[t-1][j] - prices[j] ) + prices[i] )
     * <p>
     * Here we are using only two rows at a time, hence we can reduce the spaces from t - > 2 constant, and it can be written as
     * profit[1][i] = Max ( profit[1][i-1], max ( profit[0][j] - prices[j] ) + prices[i] )
     * <p>
     * For each next transaction, copy profit[1][i] to profit[0][i]
     */

    static class SolutionDPSpaceOptimized {
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][] profit = new int[2][n];

            //To make a profit, we have to buy one share and sell at one share, means for a transaction
            //we need two days. Now, assume we buy 1st day and sell on 2nd day, buy at 3rd day and sell at 4th day so on...
            // then at max we can perform n/2 transactions only.
            //hence if k >= n/2, we can perform n/2 transactions.
            if (k >= n / 2)
                k = n / 2;

            for (int trans = 0; trans < k; trans++) {
                int prevMax = Integer.MIN_VALUE;
                ;
                for (int i = 1; i < n; i++) {
                    prevMax = Math.max(prevMax, profit[0][i - 1] - prices[i - 1]);

                    profit[1][i] = Math.max(profit[1][i - 1], prices[i] + prevMax);  //profit[1][i] = Max ( profit[1][i-1], max ( profit[0][j] - prices[j] ) + prices[i] )

                }
                //copy the transaction for the next transactions
                profit[0] = Arrays.copyOf(profit[1], n);
            }

            return profit[1][n - 1];


        }
    }


}
