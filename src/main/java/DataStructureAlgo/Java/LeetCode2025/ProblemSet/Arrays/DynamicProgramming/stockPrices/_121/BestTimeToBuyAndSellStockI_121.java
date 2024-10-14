package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._121;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/14/2024
 * Question Category: 121. Best Time to Buy and Sell Stock
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * <p>
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 are not allowed because you must buy before you sell.
 * Example 2:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitSingleTransaction_BestTimeToBuySellStockI}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Alibaba
 * @Amazon
 * @Apple
 * @Atlassian
 * @BlackRock
 * @Bloomberg
 * @Cisco
 * @Citadel
 * @Citrix
 * @DeutscheBank
 * @DoorDash
 * @Expedia
 * @Facebook
 * @GoldmanSachs
 * @Google
 * @Grab
 * @Intel
 * @caMorgan
 * @LinkedIn
 * @Lyft
 * @Microsoft
 * @MorganStanley
 * @Oracle
 * @Redfin
 * @SAP
 * @Snapchat <p><p>
 * @Editorial
 */
public class BestTimeToBuyAndSellStockI_121 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{7, 1, 5, 3, 6, 4}, 5);
        test &= test(new int[]{7, 6, 4, 3, 1}, 0);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] prices, int expected) {
        System.out.println("----------------------------------");
        System.out.println("Prices : " + Arrays.toString(prices) + " Expected : " + expected);
        int output = new Solution().maxProfit(prices);
        System.out.println("Output : " + output);
        return output == expected;
    }

    static class Solution {
        public int maxProfit(int[] prices) {


            int profit = 0;
            int n = prices.length;
            int max = prices[n - 1];

            for (int i = n - 2; i >= 0; i--) {
                max = Math.max(max, prices[i]);
                profit = Math.max(profit, max - prices[i]);
            }

            return profit;

        }
    }
}
