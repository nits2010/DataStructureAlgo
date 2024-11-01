package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._123;

import DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitTwoTransaction_BestTimeToBuySellStockIII;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/14/2024
 * Question Category: 123. Best Time to Buy and Sell Stock III
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 * <p>
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link MaxProfitTwoTransaction_BestTimeToBuySellStockIII}
 * Similar {@link}
 * extension {@link }
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
 * @Atlassian
 * @Bloomberg
 * @Citadel
 * @Facebook
 * @Flipkart
 * @Google
 * @Microsoft
 * @Rubrik
 * @TwoSigma
 * @Uber
 * @Apple <p><p>
 * @Editorial
 */
public class BestTimeToBuyAndSellStockIII_123 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{2, 1, 2, 0, 1}, 2);
        test &= test(new int[]{3, 3, 5, 0, 0, 3, 1, 4}, 6);
        test &= test(new int[]{1, 2, 3, 4, 5}, 4);
        test &= test(new int[]{7, 6, 4, 3, 1}, 0);
        test &= test(new int[]{1, 2, 3, 90, 95, 99, 100}, 99);

        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[] prices, int expected) {
        System.out.println("--------------------------------------------");
        System.out.println("Input: prices = " + Arrays.toString(prices) + " Expected: " + expected);
        int actual;
        boolean pass, finalPass = true;

        SolutionSimplified solutionSimplified = new SolutionSimplified();
        actual = solutionSimplified.maxProfit(prices);
        pass = actual == expected;
        System.out.println("Output-Simplified: " + actual + " Pass :" + (pass ? "Pass" : "Fail"));
        finalPass &= pass;

        Solution3Scan solution3Scan = new Solution3Scan();
        actual = solution3Scan.maxProfit(prices);
        pass = actual == expected;
        System.out.println("Output-3Scan: " + actual + " Pass :" + (pass ? "Pass" : "Fail"));
        finalPass &= pass;
        return finalPass;
    }


    static class SolutionSimplified {
        public int maxProfit(int[] prices) {

            //Since we need to perform the only two transactions such that we cannot hold two shares at any time.
            //Means we have to sell the earlier share before buying new one. The max profit will always be generated
            //by selling the earlier share before buying new.
            //we know, how to solve this problem for one share (running from back and track max and profile per element)
            //we can apply the same logic again, post finding profit from right to left, we can run another transaction
            //from left to right and calculate the same by tracking minimum value on left and profit per element.

            //post-finding both the transaction, the maximum profit will be the maximum value of sum of profit of both the transaction
            //this is because on every index, the profit from the right (x) and profit from the left (y) always be on distinct element and in different transaction.

            //we can reduce one scan by calculating the sum in the second transaction itself.

            int n = prices.length;
            int[] profit = new int[n];


            //just like Best Time to Buy and Sell Stock I_121
            //the first transaction
            int max = prices[n - 1];
            profit[n - 1] = 0; //buying and selling on same day produce no profit

            for (int i = n - 2; i >= 0; i--) {
                max = Math.max(prices[i], max);
                profit[i] = Math.max(profit[i + 1], max - prices[i]);

            }

            //the second transaction, run from left to run and also calcualte the sum to avoid another loop later
            int min = prices[0];

            for (int i = 1; i < n; i++) {
                min = Math.min(min, prices[i]);
                profit[i] = Math.max(profit[i - 1], profit[i] + (prices[i] - min)); //adding transaction 1 profit at ith index to transaction 2 directly

            }

            return profit[n - 1];

        }
    }

    static class Solution3Scan {
        public int maxProfit(int[] prices) {

            //Since we need to perform the only two transactions such that we cannot hold two shares at any time.
            //Means we have to sell the earlier share before buying new one. The max profit will always be generated
            //by selling the earlier share before buying new.
            //we know, how to solve this problem for one share (running from back and track max and profile per element)
            //we can apply the same logic again, post finding profit from right to left, we can run another transaction
            //from left to right and calculate the same by tracking minimum value on left and profit per element.

            //post-finding both the transaction, the maximum profit will be the maximum value of sum of profit of both the transaction
            //this is because on every index, the profit from the right (x) and profit from the left (y) always be on distinct element and in different transaction.

            int n = prices.length;
            int[] transaction1 = new int[n];
            int[] transaction2 = new int[n];


            //just like Best Time to Buy and Sell Stock I_121
            //the first transaction
            int max = prices[n - 1];
            transaction1[n - 1] = 0; //buying and selling on same day produce no profit

            for (int i = n - 2; i >= 0; i--) {
                max = Math.max(prices[i], max);
                transaction1[i] = Math.max(transaction1[i + 1], max - prices[i]);

            }

            //the second transaction, run from left to run
            int min = prices[0];
            transaction2[0] = 0;

            for (int i = 1; i < n; i++) {
                min = Math.min(min, prices[i]);
                transaction2[i] = Math.max(transaction2[i - 1], (prices[i] - min));

            }


            int profit = 0;
            for (int i = 0; i < n; i++) {
                profit = Math.max(profit, transaction1[i] + transaction2[i]);
            }

            return profit;

        }
    }
}
