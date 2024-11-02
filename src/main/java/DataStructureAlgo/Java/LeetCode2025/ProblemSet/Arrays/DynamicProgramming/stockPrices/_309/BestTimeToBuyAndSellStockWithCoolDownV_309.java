package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._309;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._122.BestTimeToBuyAndSellStockII_122;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 11/1/2024
 * Question Category: 309. Best Time to Buy and Sell Stock with Cooldown
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 * In array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 * <p>
 * Input: prices = [1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link BestTimeToBuyAndSellStockII_122 }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class BestTimeToBuyAndSellStockWithCoolDownV_309 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 0, 2}, 3));
        tests.add(test(new int[]{1}, 0));
        tests.add(test(new int[]{2, 1, 4}, 3));
        tests.add(test(new int[]{1, 2, 4}, 3));

        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean test(int[] prices, int expected) {
        CommonMethods.printTestOutcome(new String[]{"Prices", "Expected"}, true, prices, expected);

        int output;
        boolean pass, finalPass = true;

        Recursion.Solution_Recursion recursion = new Recursion.Solution_Recursion();
        output = recursion.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Recursion", "Pass"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.Solution_TopDownMemoization topDown = new DynamicProgramming.Solution_TopDownMemoization();
        output = topDown.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"TopDownMemoization", "Pass"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.Solution_BottomUpTabulation bottomUp = new DynamicProgramming.Solution_BottomUpTabulation();
        output = bottomUp.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.Solution_BottomUpTabulation_Optimized bottomUpTabulationOptimized = new DynamicProgramming.Solution_BottomUpTabulation_Optimized();
        output = bottomUpTabulationOptimized.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp-Optimized", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.Solution_BottomUpTabulation_Optimized_SpaceOptimized bottomUpTabulationOptimizedSpaceOptimized = new DynamicProgramming.Solution_BottomUpTabulation_Optimized_SpaceOptimized();
        output = bottomUpTabulationOptimizedSpaceOptimized.maxProfit(prices);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp-Optimized", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Recursion {
        static class Solution_Recursion {
            final int buying = 1;
            final int selling = 0;

            public int maxProfit(int[] prices) {
                return maxProfit(prices, 0, prices.length, 1);
            }

            private int maxProfit(int[] prices, int i, int n, int buy) {

                if (i >= n)
                    return 0;


                int profit;
                if (buy == 1) {
                    profit = Math.max(maxProfit(prices, i + 1, n, selling) - prices[i], // sell at i+1, buy so prices[i] will be deducted)
                            maxProfit(prices, i + 1, n, buying)); // don't buy, do cool down

                } else {
                    // its selling day
                    profit = Math.max(maxProfit(prices, i + 2, n, buying) + prices[i], // sold to gain prices[i], i+2 since i+1 is cool down
                            maxProfit(prices, i + 1, n, selling)); // don't sell, do cool down
                }
                return profit;
            }
        }
    }

    static class DynamicProgramming {
        static class Solution_TopDownMemoization {
            final int buying = 1;
            final int selling = 0;

            public int maxProfit(int[] prices) {
                int n = prices.length;
                Integer[][] dp = new Integer[n][2]; // n days, with two possibilities buy or sell
                return maxProfit(prices, 0, n, 1, dp);
            }

            private int maxProfit(int[] prices, int i, int n, int buy, Integer[][] dp) {
                // System.out.println(buy);

                if (i >= n)
                    return 0;

                if (dp[i][buy] != null)
                    return dp[i][buy];

                int profit;
                if (buy == 1) {
                    profit = Math.max(maxProfit(prices, i + 1, n, selling, dp) - prices[i], // sell at i+1, buy so prices[i] will be deducted)
                            maxProfit(prices, i + 1, n, buying, dp)); // don't buy, do cool-down

                } else {
                    // its selling day
                    profit = Math.max(maxProfit(prices, i + 2, n, buying, dp) + prices[i], // sold to gain prices[i], i+2 since i+1 is cool-down
                            maxProfit(prices, i + 1, n, selling, dp)); // don't sell today, do cool-down
                }
                return dp[i][buy] = profit;
            }
        }

        static class Solution_BottomUpTabulation {

            public int maxProfit(int[] prices) {
                int n = prices.length;
                int[][] dp = new int[n + 2][2]; // n days, with two possibilities buy or sell

                //dp[i][1] defines the profit gained by buying on day i
                //dp[i][0] defines the profit gained by selling on day i
                // dp[i][buy] = Math.max (
                //                      Max ( <buy on ith day, sell on i+1 day> - prices[i] , cool-down on i+1 day> ),
                //                      Max ( <sell on ith day and do cool down on i+1 day> + prices[i], <cool-down on i+1 day> )
                // dp[i][buy] = Math.max ( Math.max(dp[i+1][0] - prices[i], dp[i+1][1]), Math.max( dp[i+2][1] + prices[i], dp[i+1][0] ) )
                // since cool-down is common, we can extract it
                // dp[i][buy] = Max ( Max(dp[i+1][0] - prices[i] , dp[i+2][1] + prices[i]), dp[i+1][0] )

                for (int i = n - 1; i >= 0; i--) {
                    for (int buy = 1; buy >= 0; buy--) {

                        if (buy == 1) {
                            dp[i][buy] = Math.max(dp[i + 1][0] - prices[i], dp[i + 1][1]);
                        } else {
                            dp[i][buy] = Math.max(dp[i + 2][1] + prices[i], dp[i + 1][0]);
                        }
                    }
                }
                return dp[0][1];

            }


        }

        static class Solution_BottomUpTabulation_Optimized {

            public int maxProfit(int[] prices) {
                int n = prices.length;
                int[][] dp = new int[n + 2][2]; // n days, with two possibilities buy or sell

                //dp[i][1] defines the profit gained by buying on day i
                //dp[i][0] defines the profit gained by selling on day i
                // dp[i][buy] = Math.max (
                //                      Max ( <buy on ith day, sell on i+1 day> - prices[i] , cool-down on i+1 day> ),
                //                      Max ( <sell on ith day and do cool down on i+1 day> + prices[i], <cool-down on i+1 day> )
                // dp[i][buy] = Math.max ( Math.max(dp[i+1][0] - prices[i], dp[i+1][1]), Math.max( dp[i+2][1] + prices[i], dp[i+1][0] ) )
                // since cool-down is common, we can extract it
                // dp[i][buy] = Max ( Max(dp[i+1][0] - prices[i] , dp[i+2][1] + prices[i]), dp[i+1][0] )

                for (int i = n - 1; i >= 0; i--) {
                    dp[i][1] = Math.max(dp[i + 1][0] - prices[i], dp[i + 1][1]);
                    dp[i][0] = Math.max(dp[i + 2][1] + prices[i], dp[i + 1][0]);
                }
                return dp[0][1];

            }


        }

        static class Solution_BottomUpTabulation_Optimized_SpaceOptimized {

            public int maxProfit(int[] prices) {
                int n = prices.length;


                //dp[i][1] defines the profit gained by buying on day i
                //dp[i][0] defines the profit gained by selling on day i
                // dp[i][buy] = Math.max (
                //                      Max ( <buy on ith day, sell on i+1 day> - prices[i] , cool-down on i+1 day> ),
                //                      Max ( <sell on ith day and do cool down on i+1 day> + prices[i], <cool-down on i+1 day> )
                // dp[i][buy] = Math.max ( Math.max(dp[i+1][0] - prices[i], dp[i+1][1]), Math.max( dp[i+2][1] + prices[i], dp[i+1][0] ) )
                // since cool-down is common, we can extract it
                // dp[i][buy] = Max ( Max(dp[i+1][0] - prices[i] , dp[i+2][1] + prices[i]), dp[i+1][0] )

                // we are using only i+1 and i+2 and the current i, hence only 3 layers are being used.
                // every day post-transaction, i+2 will be gone, 1+1 become i+2 day, and current i will be i+1 day
                int[][] dp = new int[3][2]; // n days, with two possibilities buy or sell

                for (int i = n - 1; i >= 0; i--) {
                    dp[0][1] = Math.max(dp[1][0] - prices[i], dp[1][1]);
                    dp[0][0] = Math.max(dp[2][1] + prices[i], dp[1][0]);

                    //1 -> 2,
                    dp[2][1] = dp[1][1];
                    dp[2][0] = dp[1][0];

                    // 0 -> 1
                    dp[1][0] = dp[0][0];
                    dp[1][1] = dp[0][1];

                }
                return dp[0][1];

            }


        }

    }
}
