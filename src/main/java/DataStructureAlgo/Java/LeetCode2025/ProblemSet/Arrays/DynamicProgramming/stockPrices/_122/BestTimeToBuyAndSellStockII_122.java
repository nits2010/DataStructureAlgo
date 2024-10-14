package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._122;

import DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitTwoTransaction__BestTimeToBuySellStockIII;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

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
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitMultiTransactions__BestTimeToBuySellStockII}
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
        boolean test = true;
        test &= test(new int[]{7, 1, 5, 3, 6, 4}, 7);
        test &= test(new int[]{1, 2, 3, 4, 5}, 4);
        test &= test(new int[]{7, 6, 4, 3, 1}, 0);
        test &= test(new int[]{   1, 2, 3, 90, 95, 99, 100}, 99);

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
