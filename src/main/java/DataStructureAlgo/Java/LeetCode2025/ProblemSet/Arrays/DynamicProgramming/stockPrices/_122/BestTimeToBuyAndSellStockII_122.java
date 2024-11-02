package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._122;

import DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitMultiTransactions_BestTimeToBuySellStockII;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 10/14/2024
 * Question Category: 122. Best Time to Buy and Sell Stock II
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 * <p>
 * Find and return the maximum profit you can achieve.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link MaxProfitMultiTransactions_BestTimeToBuySellStockII}
 * Similar {@link}
 * extension {@link }
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
 * @Amazon
 * @Facebook
 * @WalmartGlobalTech
 * @Microsoft
 * @Bloomberg
 * @Adobe
 * @Alibaba
 * @Apple
 * @Citadel
 * @GoldmanSachs
 * @Google
 * @Oracle
 * @Uber
 * @Yahoo <p><p>
 * @Editorial
 */
public class BestTimeToBuyAndSellStockII_122 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{7, 1, 5, 3, 6, 4}, 7));
        tests.add(test(new int[]{1, 2, 3, 4, 5}, 4));
        tests.add(test(new int[]{7, 6, 4, 3, 1}, 0));
        tests.add(test(new int[]{1, 2, 3, 90, 95, 99, 100}, 99));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] prices, int expected) {
        CommonMethods.printTestOutcome(new String[]{"Prices", "Expected"}, true, prices, expected);

        int output;
        boolean pass, finalPass = true;


        Recursion.Solution recursion = new Recursion.Solution();
        output = recursion.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Recursion", "Pass"}, false, prices, pass ? "PASS" : "FAIL");


        DynamicProgramming.TopDownMemoization topDownMemoization = new DynamicProgramming.TopDownMemoization();
        output = topDownMemoization.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"TopDown", "Pass"}, false, prices, pass ? "PASS" : "FAIL");

        DynamicProgramming.BottomUp bottomUp = new DynamicProgramming.BottomUp();
        output = bottomUp.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp", "Pass"}, false, prices, pass ? "PASS" : "FAIL");


        DynamicProgramming.BottomUpSpaceOptimized bottomUpSpaceOptimized = new DynamicProgramming.BottomUpSpaceOptimized();
        output = bottomUpSpaceOptimized.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp-SpaceOptimized", "Pass"}, false, prices, pass ? "PASS" : "FAIL");

        DynamicProgramming.BottomUpV2 bottomUpV2 = new DynamicProgramming.BottomUpV2();
        output = bottomUpV2.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUpV2", "Pass"}, false, prices, pass ? "PASS" : "FAIL");


        DynamicProgramming.BottomUpV2_SpaceOptimized bottomUpV2SpaceOptimized = new DynamicProgramming.BottomUpV2_SpaceOptimized();
        output = bottomUpV2SpaceOptimized.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUpV2-SpaceOptimized", "Pass"}, false, prices, pass ? "PASS" : "FAIL");


        Greedy.Solution solutionGreedy = new Greedy.Solution();
        output = solutionGreedy.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Greedy", "Pass"}, false, prices, pass ? "PASS" : "FAIL");

        return finalPass;
    }


    static class Recursion {
        static class Solution {
            public int maxProfit(int[] prices) {

                return maxProfit(prices, 0, 1);
            }

            private int maxProfit(int[] prices, int i, int buy) {
                if (i >= prices.length) return 0;

                if (buy == 1) {
                    return Math.max(maxProfit(prices, i + 1, 0) - prices[i],
                            maxProfit(prices, i + 1, 1));
                } else {
                    return Math.max(maxProfit(prices, i + 1, 1) + prices[i], maxProfit(prices, i + 1, 0));
                }

            }
        }
    }

    static class DynamicProgramming {
        static class TopDownMemoization {
            public int maxProfit(int[] prices) {
                Integer[][] dp = new Integer[prices.length][2];
                return maxProfit(prices, 0, 1, dp);
            }

            private int maxProfit(int[] prices, int i, int buy, Integer[][] dp) {
                if (i >= prices.length) return 0;
                if (dp[i][buy] != null) return dp[i][buy];

                if (buy == 1) {
                    dp[i][buy] = Math.max(maxProfit(prices, i + 1, 0, dp) - prices[i],
                            maxProfit(prices, i + 1, 1, dp));
                } else {
                    dp[i][buy] = Math.max(maxProfit(prices, i + 1, 1, dp) + prices[i], maxProfit(prices, i + 1, 0, dp));
                }

                return dp[i][buy];

            }
        }


        static class BottomUp {
            public int maxProfit(int[] prices) {
                int[][] dp = new int[prices.length + 1][2];

                for (int i = prices.length - 1; i >= 0; i--) {

                    dp[i][1] = Math.max(dp[i + 1][0] - prices[i], dp[i + 1][1]);
                    dp[i][0] = Math.max(dp[i + 1][1] + prices[i], dp[i + 1][0]);
                }

                return dp[0][1];
            }
        }

        static class BottomUpSpaceOptimized {
            public int maxProfit(int[] prices) {
                int[][] dp = new int[2][2];

                for (int i = prices.length - 1; i >= 0; i--) {

                    dp[0][1] = Math.max(dp[1][0] - prices[i], dp[1][1]);
                    dp[0][0] = Math.max(dp[1][1] + prices[i], dp[1][0]);

                    //exchange
                    dp[1][1] = dp[0][1];
                    dp[1][0] = dp[0][0];
                }

                return dp[0][1];
            }


        }

        static class BottomUpV2 {
            public int maxProfit(int[] prices) {
                int n = prices.length;
                int[] buy = new int[n]; //buy[i] is the max profit at day i with buy transaction
                int[] sell = new int[n]; //sell[i] is the max profit at day i with sell transaction
                buy[0] = -prices[0]; // we start buying at day 0

                for (int i = 1; i < n; i++) {
                    buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
                    sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
                }
                return sell[n - 1];
            }
        }

        static class BottomUpV2_SpaceOptimized {
            public int maxProfit(int[] prices) {
                int n = prices.length;
                int buy = -prices[0];
                int sell = 0;

                for (int i = 1; i < n; i++) {
                    buy = Math.max(buy, sell - prices[i]);
                    sell = Math.max(sell, buy + prices[i]);
                }
                return sell;
            }
        }



    }

    static class Greedy {

        static class Solution {
            public int maxProfit(int[] prices) {

                //since multiple transactions are allowed with one share a day limitation.
                //we will always choose to sell the share as soon as we find the profit.
                //as selling early will open a window to buy more in future and sell.
                //this leads to a greedy approach.
                //this algorithm won't fail, consider below case
                //prices [1,2,3,90,95,99,100] max profit : (2-1) + (3-2) + (90-3) + (95-90) + (99-95)+ (100-99) = 1 + 1 + 87 + 5 + 4 + 4 + 1 = 107
                // it is no advantage to hold the minimum value share `1` and sell at the end `100` makes profit 99 < 107
                int profit = 0;
                for (int i = 1; i < prices.length; i++) {
                    if (prices[i] > prices[i - 1])
                        profit += prices[i] - prices[i - 1];
                }
                return profit;
            }
        }
    }

}
