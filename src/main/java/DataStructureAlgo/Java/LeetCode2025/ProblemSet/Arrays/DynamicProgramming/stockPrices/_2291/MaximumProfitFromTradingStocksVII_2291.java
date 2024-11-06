package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._2291;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.Knapsack;

/**
 * Author: Nitin Gupta
 * Date: 11/2/2024
 * Question Category: 2291. Maximum Profit From Trading Stocks
 * Description: https://leetcode.com/problems/maximum-profit-from-trading-stocks/description/
 * https://leetcode.ca/2022-07-02-2291-Maximum-Profit-From-Trading-Stocks/
 * You are given two 0-indexed integer arrays of the same length present and future where present[i] is the current price of the ith stock and future[i] is the price of the ith stock a year in the future. You may buy each stock at most once. You are also given an integer budget representing the amount of money you currently have.
 * <p>
 * Return the maximum amount of profit you can make.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: present = [5,4,6,2,3], future = [8,5,4,3,5], budget = 10
 * Output: 6
 * Explanation: One possible way to maximize your profit is to:
 * Buy the 0th, 3rd, and 4th stocks for a total of 5 + 2 + 3 = 10.
 * Next year, sell all three stocks for a total of 8 + 3 + 5 = 16.
 * The profit you made is 16 - 10 = 6.
 * It can be shown that the maximum profit you can make is 6.
 * Example 2:
 * <p>
 * Input: present = [2,2,5], future = [3,4,10], budget = 6
 * Output: 5
 * Explanation: The only possible way to maximize your profit is to:
 * Buy the 2nd stock, and make a profit of 10 - 5 = 5.
 * It can be shown that the maximum profit you can make is 5.
 * Example 3:
 * <p>
 * Input: present = [3,3,12], future = [0,3,15], budget = 10
 * Output: 0
 * Explanation: One possible way to maximize your profit is to:
 * Buy the 1st stock, and make a profit of 3 - 3 = 0.
 * It can be shown that the maximum profit you can make is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == present.length == future.length
 * 1 <= n <= 1000
 * 0 <= present[i], future[i] <= 100
 * 0 <= budget <= 1000
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.nonleetcode.Knapsack0Or1}
 * DP-BaseProblem {@link Knapsack}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @DynamicProgramming
 * @LeetCodeLockedProblem
 * @PremimumQuestion <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion {@link DynamicProgramming.BottomUpSpaceOptimizedV2} {@link DynamicProgramming.BottomUpSpaceOptimized} {@link DynamicProgramming.TopDown}
 */
public class MaximumProfitFromTradingStocksVII_2291 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{5, 4, 6, 2, 3}, new int[]{8, 5, 4, 3, 5}, 10, 6));
        tests.add(test(new int[]{2, 2, 5}, new int[]{3, 4, 10}, 6, 5));
        tests.add(test(new int[]{3, 3, 12}, new int[]{0, 3, 15}, 10, 0));
        tests.add(test(new int[]{7, 8, 9, 10}, new int[]{10, 12, 15, 18}, 20, 14));
        tests.add(test(new int[]{1, 2, 3, 4, 5}, new int[]{10, 9, 8, 7, 6}, 15, 25));
        tests.add(test(new int[]{10, 20, 30}, new int[]{15, 25, 35}, 30, 10));
        tests.add(test(new int[]{5, 10, 15, 20}, new int[]{10, 15, 20, 25}, 25, 10));
        tests.add(test(new int[]{8, 12, 15, 20}, new int[]{10, 15, 20, 25}, 30, 8));
        tests.add(test(new int[]{3, 6, 9, 12, 15}, new int[]{5, 8, 11, 14, 17}, 20, 6));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] present, int[] future, int budget, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Present", "Future", "Budget", "Expected"}, true, present, future, budget, expected);

        int output;
        boolean pass, finalPass = true;

        Recursion.Solution recursion = new Recursion.Solution();
        output = recursion.maxProfit(present, future, budget);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"Recursion", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.TopDown topDown = new DynamicProgramming.TopDown();
        output = topDown.maxProfit(present, future, budget);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"TopDown", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.BottomUp bottomUp = new DynamicProgramming.BottomUp();
        output = bottomUp.maxProfit(present, future, budget);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp", "Pass"}, false, output, pass ? "Pass" : "Fail");

        output = bottomUp.maxProfit2(present, future, budget);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp2", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.BottomUpSpaceOptimized bottomUpSpaceOptimized = new DynamicProgramming.BottomUpSpaceOptimized();
        output = bottomUpSpaceOptimized.maxProfit(present, future, budget);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp-SpaceOptimized", "Pass"}, false, output, pass ? "Pass" : "Fail");

        DynamicProgramming.BottomUpSpaceOptimizedV2 bottomUpSpaceOptimizedv2 = new DynamicProgramming.BottomUpSpaceOptimizedV2();
        output = bottomUpSpaceOptimizedv2.maxProfit(present, future, budget);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"BottomUp-SpaceOptimizedV2", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Recursion {
        static class Solution {
            public int maxProfit(int[] present, int[] future, int budget) {
                return maxProfit(present, future, future.length, budget, 0, 0);
            }

            /**
             * @param present       present stock prices
             * @param future        future stock prices
             * @param n             number of stocks
             * @param budget        Total budget
             * @param i             current stock index
             * @param currentBudget current budget
             * @return maximum profit
             */
            private int maxProfit(int[] present, int[] future, int n, int budget, int i, int currentBudget) {
                // base case
                // if we have run out of stocks
                // or we have run out of budget
                if (i >= n || currentBudget > budget)
                    return 0;

                int include = 0;

                //can we include current stock?
                if (currentBudget + present[i] <= budget && future[i] - present[i] > 0)
                    // include current stock and calculate profit for remaining stocks
                    include = (future[i] - present[i]) + maxProfit(present, future, n, budget, i + 1, currentBudget + present[i]);
                //don't include current stock and calculate profit for remaining stocks
                int exclude = maxProfit(present, future, n, budget, i + 1, currentBudget);

                //get max profit
                return Math.max(include, exclude);
            }
        }
    }

    static class DynamicProgramming {
        static class TopDown {
            public int maxProfit(int[] present, int[] future, int budget) {
                return maxProfit(present, future, future.length, budget, 0, 0, new Integer[present.length + 1][budget + 1]);
            }

            /**
             * @param present       present stock prices
             * @param future        future stock prices
             * @param n             number of stocks
             * @param budget        Total budget
             * @param i             current stock index
             * @param currentBudget current budget
             * @return maximum profit
             */
            private int maxProfit(int[] present, int[] future, int n, int budget, int i, int currentBudget, Integer[][] dp) {
                // base case
                // if we have run out of stocks,
                // or we have run out of budget
                if (i >= n || currentBudget > budget)
                    return 0;

                if (dp[i][currentBudget] != null)
                    return dp[i][currentBudget];

                int include = 0;

                //can we include current stock?
                if (currentBudget + present[i] <= budget && future[i] - present[i] > 0)
                    // include current stock and calculate profit for remaining stocks
                    include = (future[i] - present[i]) + maxProfit(present, future, n, budget, i + 1, currentBudget + present[i], dp);

                //don't include current stock and calculate profit for remaining stocks
                int exclude = maxProfit(present, future, n, budget, i + 1, currentBudget, dp);

                //get max profit
                return dp[i][currentBudget] = Math.max(include, exclude);
            }
        }

        static class BottomUp {
            public int maxProfit(int[] present, int[] future, int budget) {
                int[][] dp = new int[present.length + 1][budget + 1];

                for (int i = 1; i <= present.length; i++) {
                    for (int j = 0; j <= budget; j++) {
                        int exclude = dp[i - 1][j];
                        int include = 0;
                        if (j >= present[i - 1])
                            include = (future[i - 1] - present[i - 1]) + dp[i - 1][j - present[i - 1]];

                        dp[i][j] = Math.max(exclude, include);
                    }
                }

                return dp[present.length][budget];
            }

            public int maxProfit2(int[] present, int[] future, int budget) {
                int[][] dp = new int[present.length + 1][budget + 1];

                for (int s = present.length - 1; s >= 0; s--) {
                    for (int b = 0; b <= budget; b++) {
                        int exclude = dp[s + 1][b];
                        int include = 0;
                        if (b >= present[s]) {
                            include = (future[s] - present[s]) + dp[s + 1][b - present[s]];
                        }

                        dp[s][b] = Math.max(exclude, include);
                    }
                }

                return dp[0][budget];
            }

        }


        static class BottomUpSpaceOptimized {
            public int maxProfit(int[] present, int[] future, int budget) {
                int[][] dp = new int[2][budget + 1];

                for (int i = 1; i <= present.length; i++) {
                    for (int j = 0; j <= budget; j++) {
                        int exclude = dp[0][j];
                        int include = 0;
                        if (j >= present[i - 1])
                            include = (future[i - 1] - present[i - 1]) + dp[0][j - present[i - 1]];

                        dp[1][j] = Math.max(exclude, include);
                    }
                    dp[0] = Arrays.copyOf(dp[1], dp[0].length);
                }

                return dp[1][budget];
            }
        }


        static class BottomUpSpaceOptimizedV2 {
            public int maxProfit(int[] present, int[] future, int budget) {
                int[] dp = new int[budget + 1];

                for (int i = 0; i < present.length; i++) {
                    int presentPrice = present[i];
                    int futurePrice = future[i];

                    for (int j = budget; j >= presentPrice; j--) {
                        dp[j] = Math.max(dp[j], dp[j - presentPrice] + futurePrice - presentPrice);
                    }
                }

                return dp[budget];
            }
        }
    }
}
