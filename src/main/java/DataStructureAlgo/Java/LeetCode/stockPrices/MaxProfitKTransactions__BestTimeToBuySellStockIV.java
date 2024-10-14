package DataStructureAlgo.Java.LeetCode.stockPrices;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-23
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 * https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/
 * <p>
 * Video: https://www.youtube.com/watch?v=oDhu5uGq_ic
 */
public class MaxProfitKTransactions__BestTimeToBuySellStockIV {

    public static void main(String []args) {
        int prices[] = {2, 1, 2, 0, 1};
        System.out.println(maxProfit(2, prices));


    }

    public static int maxProfit(int k, int[] prices) {
        return maxProfitPolynomialSpaceOptimizedFastest(k, prices);
    }



    //O(t*n^2)
    public static int maxProfitPolynomial(int t, int[] prices) {

        if (prices.length == 0)
            return 0;

        int n = prices.length;

        //profit[t][i] represent maximum profit using at most t transactions up to day i (including day i).
        int profit[][] = new int[t + 1][n + 1];

        //no stocks available, profit is always 0
        for (int i = 0; i <= t; i++)
            profit[i][0] = 0;

        //using no transaction, profit is always 0
        for (int i = 0; i <= n; i++)
            profit[0][i] = 0;


        /*
        profit[t][i] = max(profit[t][i-1], max(price[i] – price[j] + profit[t-1][j]))
          for all j in range [0, i-1]

        profit[t][i] will be maximum of –

        profit[t][i-1]: which represents not doing any transaction on the ith day.
        max(price[i] – price[j] + profit[t-1][j]): Maximum profit gained by selling on ith day.
        In order to sell shares on ith day, we need to purchase it on any one of [0, i – 1] days.
        If we buy shares on jth day and sell it on ith day, then profit would be price[i] - price [j]
        Here at jth day, profit[t-1][j] is best we could have done with one less transaction.
        hence max profit, will be price[i] – price[j] + profit[t-1][j] where j varies from 0 to i-1.
        */

        for (int trans = 1; trans <= t; trans++) {

            for (int day = 1; day < n; day++) {


                //make a transaction today, find a day s.t. we can make transaction today i.e. prices[day] > prices[j]

                int maxSoFar = 0;
                for (int m = 0; m < day; m++)
                    maxSoFar = Math.max(maxSoFar, prices[day] - prices[m] + profit[trans - 1][m]);


                profit[trans][day] = Math.max(profit[trans][day - 1], maxSoFar);

            }
        }

        return profit[t][n - 1];

    }




    /**
     * profit[t][i] = max(profit[t][i-1], max(price[i] – price[j] + profit[t-1][j]))
     * for all j in range [0, i-1]
     * <p>
     * profit[t][i] will be maximum of –
     * <p>
     * profit[t][i-1] :which represents not doing any transaction on the ith day.
     * max(profit[t-1][j] + price[i] – price[j]  ):Maximum profit gained by selling on ith day. In order to sell shares on ith day, we need to purchase it on any one of [0, i – 1] days. If we buy shares on jth day          and sell it on ith day, max profit will be price[i] – price[j] + profit[t-1][j] where j varies from 0 to i-1. Here profit[t-1][j] is best we could have done with one       less transaction till jth day.
     * <p>
     * <p>
     * if we take the closer look at
     * max(profit[t-1][j] + price[i] – price[j]  ) for all j in range [0, i-1]
     * here price[i] is constant for all j; hence we can write this as
     * <p>
     * max(profit[t-1][j] – price[j]  ) + price[i] for all j in range [0, i-1]
     * <p>
     * Take a look at this;
     * max(profit[t-1][j] – price[j]  ) this is just finding the max profit 
     * on jth day s.t. j from 0 to i only;
     * so if we know the max of j-1 th day, we can calculate the jth point as
     * max ( previousMax , (profit[t-1][j] - price[j] ) in constant time
     *
     * @param t
     * @param prices
     * @return
     */
    //O(t*n)/O(t*n)
    public static int maxProfitPolynomialFaster(int t, int[] prices) {


        if (prices.length == 0)
            return 0;

        int n = prices.length;

        //profit[t][i] represent maximum profit using at most t transactions up to day i (including day i).
        int profit[][] = new int[t + 1][n + 1];

        //no stocks available, profit is always 0
        for (int i = 0; i <= t; i++)
            profit[i][0] = 0;

        //using no transaction, profit is always 0
        for (int i = 0; i <= n; i++)
            profit[0][i] = 0;


        /*
        profit[t][i] = max(profit[t][i-1], price[i] + max(previousMax, (profit[t-1][j-1]– price[j-1] )))
          for all j in range [0, i-1]
        */


        for (int trans = 1; trans <= t; trans++) {


            int previousMax = Integer.MIN_VALUE;
            for (int day = 1; day < n; day++) {

                previousMax = Math.max(previousMax, profit[trans - 1][day - 1] - prices[day - 1]);

                profit[trans][day] = Math.max(profit[trans][day - 1], previousMax + prices[day]);

            }
        }

        return profit[t][n - 1];
    }



    /**
     * profit[t][i] = max(profit[t][i-1], price[i] + max(previousMax, (profit[t-1][j-1]– price[j-1] )))
     * for all j in range [0, i-1]
     * <p>
     * <p>
     * take a closer look and we found that at any moment, we need to only (current row, previous column) -> profit[t][i-1]
     * and (previous row, previous column)  -> profit[t-1][j-1]
     * <p>
     * So instead of storing whole transaction history, we can keep only last two transaction history;
     * <p>
     * we just keep rotating the arrays for rows to rows
     * profit[1][....] represent the current transaction 
     * profit[0][....] represent the previous day transaction
     
     
     * Note: we can keep two seperate variable for current & previous day transaction. and then keep interchanging them.
     * this way we can avoid Arrays.copyOf
     * @param t
     * @param prices
     * @return
     */
    //O(t*n)/O(n)
    public static int maxProfitPolynomialFasterSpaceOptimized(int t, int[] prices) {

        if (prices.length == 0)
            return 0;

        int n = prices.length;

        //profit[t][i] represent maximum profit using at most t transactions up to day i (including day i).
        int profit[][] = new int[2][n + 1];


        for (int trans = 1; trans <= t; trans++) {


            int previousMax = Integer.MIN_VALUE;
            for (int day = 1; day < n; day++) {

                previousMax = Math.max(previousMax, profit[0][day - 1] - prices[day - 1]);

                profit[1][day] = Math.max(profit[1][day - 1], previousMax + prices[day]);

            }
            profit[0] = Arrays.copyOf(profit[1], n);
        }

        return profit[1][n - 1];
    }



    /**
     * even more optimization,
     * on closer look of the table Profit table, we an see that if t > n/2 then its just we can't buy and sell since the
     * available data points (stocks) are not enough as compare to transaction that we need to make [ we can't make three trasnaction of stock price for 2 days ]
     * By saying this, if we have n days stocks then at most we can perform n/2 trasaction (
     * e.x. say we have stocks of n = 4 days as below
     * [1,4,3,6] then at most we can do 2 transaction,
     ******** buy at 1 and sell at 4 and buy at 3 sell at 6. OR buy at 1 and sell at 6 ( we will choose 2 transaction as it gives more profit).
     ****
     **** hence if t>=n/2 then t = n/2
     *
     * @param t
     * @param prices
     * @return
     */
    //O(t*n)/O(n)
    public static int maxProfitPolynomialSpaceOptimizedFastest(int t, int[] prices) {

        if (prices.length == 0)
            return 0;

        int n = prices.length;

        //profit[t][i] represent maximum profit using at most t transactions up to day i (including day i).
        int profit[][] = new int[2][n + 1];


        if(t >= n/2)
            t = n/2;
        for (int trans = 1; trans <= t; trans++) {


            int previousMax = Integer.MIN_VALUE;
            for (int day = 1; day < n; day++) {

                previousMax = Math.max(previousMax, profit[0][day - 1] - prices[day - 1]);

                profit[1][day] = Math.max(profit[1][day - 1], previousMax + prices[day]);

            }
            profit[0] = Arrays.copyOf(profit[1], n);
        }

        return profit[1][n - 1];
    }


}
