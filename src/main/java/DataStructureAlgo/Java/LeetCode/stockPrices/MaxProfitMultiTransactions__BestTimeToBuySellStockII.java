package DataStructureAlgo.Java.LeetCode.stockPrices;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-23
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
 * <p>
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 */
public class MaxProfitMultiTransactions__BestTimeToBuySellStockII {

    public static void main(String []args) {
        int prices[] = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        return maxProfit(prices, prices.length);
    }

    public static int maxProfit(int[] prices, int n) {
        int maxProfit = 0;
        for (int i = 1; i < n; i++)
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];

        return maxProfit;
    }

    public static int maxProfitFindingPeek(int[] prices) {

        int maxProfit = 0;
        int buy, sell;
        int n = prices.length;
        int i = 0;
        while (i < n - 1) {

            //Find local minima; first minimum to buy
            while (i < n - 1 && prices[i] >= prices[i + 1])
                i++;

            if (i == n - 1)
                return maxProfit;

            buy = prices[i];
            i++;
            //Find local maxima; to sell

            while (i <= n - 1 && prices[i] >= prices[i - 1])
                i++;

            sell = prices[i - 1];

            maxProfit += sell - buy;


        }
        return maxProfit;
    }
}
