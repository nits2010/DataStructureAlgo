package DataStructureAlgo.Java.LeetCode.stockPrices;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-23
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 */
public class MaxProfitSingleTransaction_BestTimeToBuySellStockI {
    public static void main(String []args) {
        int prices[] = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));

        int prices2[] = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(prices2));
    }

    public static int maxProfit(int[] prices) {
        if (null == prices || prices.length == 0)
            return 0;
        int maxProfit = 0;
        int n = prices.length;
        int max = prices[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (prices[i] < max)
                maxProfit = Math.max(maxProfit, max - prices[i]);
            else max = prices[i];
        }
        return maxProfit;
    }
}
