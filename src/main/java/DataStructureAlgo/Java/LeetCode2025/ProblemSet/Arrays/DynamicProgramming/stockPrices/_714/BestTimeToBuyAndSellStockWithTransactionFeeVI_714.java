package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._714;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._122.BestTimeToBuyAndSellStockII_122;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.Knapsack;

/**
 * Author: Nitin Gupta
 * Date: 11/2/2024
 * Question Category: 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * <p>
 * Note:
 * <p>
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * The transaction fee is only charged once for each stock purchase and sale.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 * <p>
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link BestTimeToBuyAndSellStockII_122}
 * DP-BaseProblem {@link Knapsack}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @DynamicProgramming
 * @Greedy <p><p>
 * Company Tags
 * -----
 * @Bloomberg
 * @Facebook
 * @Amazon
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion {@link DynamicProgramming.BottomUpV2_SpaceOptimized} {@link DynamicProgramming.BottomUpSpaceOptimized}
 */
public class BestTimeToBuyAndSellStockWithTransactionFeeVI_714 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{1, 3, 2, 8, 4, 9}, 2, 8));
        tests.add(test(new int[]{1, 3, 7, 5, 10, 3}, 3, 6));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] prices, int fee, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Prices", "Fee", "Expected"}, true, prices, fee, expected);

        int output;
        boolean pass, finalPass = true;

        Recursion.Solution recursion = new Recursion.Solution();
        output = recursion.maxProfit(prices, fee);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Recursion", "Pass"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.TopDown topDown = new DynamicProgramming.TopDown();
        output = topDown.maxProfit(prices, fee);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"TopDown", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.BottomUp bottomUp = new DynamicProgramming.BottomUp();
        output = bottomUp.maxProfit(prices, fee);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.BottomUpSpaceOptimized bottomUpSpaceOptimized = new DynamicProgramming.BottomUpSpaceOptimized();
        output = bottomUpSpaceOptimized.maxProfit(prices, fee);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp-SpaceOptimized", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.BottomUpV2 bottomUpV2 = new DynamicProgramming.BottomUpV2();
        output = bottomUpV2.maxProfit(prices, fee);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUpV2", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.BottomUpV2_SpaceOptimized bottomUpV2SpaceOptimized = new DynamicProgramming.BottomUpV2_SpaceOptimized();
        output = bottomUpV2SpaceOptimized.maxProfit(prices, fee);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUpV2-SpaceOptimized", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Recursion {
        static class Solution {
            public int maxProfit(int[] prices, int fee) {
                return maxProfit(prices, fee, 0, 1);
            }

            private int maxProfit(int[] prices, int fee, int i, int buy) {

                if (i >= prices.length)
                    return 0;


                int profit;
                if (buy == 1) {
                    int buying = maxProfit(prices, fee, i + 1, 0) - prices[i];
                    int skipping = maxProfit(prices, fee, i + 1, 1);
                    profit = Math.max(buying, skipping);
                } else {

                    int selling = maxProfit(prices, fee, i + 1, 1) + prices[i] - fee;
                    int skipping = maxProfit(prices, fee, i + 1, 0);

                    profit = Math.max(selling, skipping);
                }

                return profit;

            }
        }
    }

    static class DynamicProgramming {
        static class TopDown {
            public int maxProfit(int[] prices, int fee) {
                int n = prices.length;
                Integer[][] dp = new Integer[n][2];
                return maxProfit(prices, fee, 0, 1, dp);
            }

            private int maxProfit(int[] prices, int fee, int i, int buy, Integer[][] dp) {

                if (i >= prices.length)
                    return 0;

                if (dp[i][buy] != null)
                    return dp[i][buy];

                int profit;
                if (buy == 1) {
                    int buying = maxProfit(prices, fee, i + 1, 0, dp) - prices[i];
                    int skipping = maxProfit(prices, fee, i + 1, 1, dp);
                    profit = Math.max(buying, skipping);
                } else {

                    int selling = maxProfit(prices, fee, i + 1, 1, dp) + prices[i] - fee;
                    int skipping = maxProfit(prices, fee, i + 1, 0, dp);

                    profit = Math.max(selling, skipping);
                }

                return dp[i][buy] = profit;

            }
        }


        static class BottomUp {
            public int maxProfit(int[] prices, int fee) {
                int n = prices.length;
                int[][] dp = new int[n + 1][2];

                for (int i = n - 1; i >= 0; i--) {
                    dp[i][1] = Math.max(dp[i + 1][0] - prices[i], dp[i + 1][1]);
                    dp[i][0] = Math.max(dp[i + 1][1] + prices[i] - fee, dp[i + 1][0]);
                }
                return dp[0][1];
            }
        }

        /**
         * https://www.youtube.com/watch?v=cUsPoH5DG1Q
         */
        static class BottomUpV2 {
            public int maxProfit(int[] prices, int fee) {
                int n = prices.length;
                int[] buy = new int[n]; //buy[i] is the max profit at day i with buy transaction
                int[] sell = new int[n]; //sell[i] is the max profit at day i with sell transaction
                buy[0] = -prices[0]; // we start buying at day 0

                for (int i = 1; i < n; i++) {
                    buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
                    sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
                }
                return sell[n - 1];
            }
        }

        static class BottomUpV2_SpaceOptimized {
            public int maxProfit(int[] prices, int fee) {
                int n = prices.length;

                int buy = -prices[0];
                int sell = 0;

                for (int i = 1; i < n; i++) {
                    buy = Math.max(buy, sell - prices[i]);
                    sell = Math.max(sell, buy + prices[i] - fee);
                }
                return sell;
            }
        }

        static class BottomUpSpaceOptimized {
            public int maxProfit(int[] prices, int fee) {
                int n = prices.length;
                int[][] dp = new int[2][2];

                for (int i = n - 1; i >= 0; i--) {
                    dp[0][1] = Math.max(dp[1][0] - prices[i], dp[1][1]);
                    dp[0][0] = Math.max(dp[1][1] + prices[i] - fee, dp[1][0]);

                    //exchange
                    dp[1][1] = dp[0][1];
                    dp[1][0] = dp[0][0];

                }
                return dp[0][1];
            }
        }
    }


}
