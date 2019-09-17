package Java.LeetCode.stockPrices;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-23
 * Description:
 */
public class MaxProfitTwoTransaction {
    public static void main(String []args) {

        int prices3[] = {2,1,2,0,1};
        System.out.println(maxProfit(prices3));

        int prices[] = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));

        int prices2[] = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices2));


    }

    /*
   profit[i] represent the max profit to gain if we buy from any of 0 to i-1 and sell at i;
   we have to do max two transaction (or at least 1), we calculate by doing twice the above transactios.
   

   now;
   [ 1st trasnaction]
   profit[i] -> traverse from back
           = MAX ( profit[i+1], maxPrices - profit [i]) if prices[i] < maxPrices; //means we buy at price[i] and sell at the maxPrices day or we have already a max profit made earlier

   [ 2nd trasnaction]
   profit[i] traverse from front
           = Max ( profit[i-1], profit[i] + price[i] - minPrices) // means we buy at minPrice day and sell at price[i] day since this is second transaction, then adding the prvious trasnaction profit Or take the profit of previous day it self

   */
    public static int maxProfit(int[] prices) {


        if (prices.length == 0)
            return 0;

        int n = prices.length;
        int profit[] = new int[n];
        int minPrice = prices[0];
        int maxPrice = prices[n - 1];

        /*
        [ 1st trasnaction]
            profit[i] -> traverse from back
            = MAX ( profit[i+1], maxPrices - profit [i]) if prices[i] < maxPrices; //means we buy at price[i] and sell at the maxPrices day or we have already a max profit                                 made earlier
        */
        profit[n - 1] = 0; // we can't make any profit on a single day it self
        for (int i = n - 2; i >= 0; i--) {
            if (maxPrice < prices[i])
                maxPrice = prices[i];

            profit[i] = Math.max(profit[i + 1], maxPrice - prices[i]);
        }

        /*

            [ 2nd trasnaction]
            profit[i] traverse from front
                    = Max ( profit[i-1], profit[i] + price[i] - minPrices) // means we buy at minPrice day and sell at price[i] day since this is second transaction, then                                                                              adding the prvious trasnaction profit Or take the profit of previous day it self

         */

        for (int i = 1; i < n; i++) {

            if (minPrice > prices[i])
                minPrice = prices[i];

            profit[i] = Math.max(profit[i - 1], profit[i] + prices[i] - minPrice);
        }

        return profit[n - 1];


    }
}
