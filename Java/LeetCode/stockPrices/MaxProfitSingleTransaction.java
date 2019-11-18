package Java.LeetCode.stockPrices;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-23
 * Description:
 */
public class MaxProfitSingleTransaction {
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
