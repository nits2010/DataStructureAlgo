package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._714;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.stockPrices._122.BestTimeToBuyAndSellStockII_122;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/2/2024
 * Question Category: 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Description: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * <p>
 * Note:
 * <p>
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * The transaction fee is only charged once for each stock purchase and sale.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 * <p>
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link BestTimeToBuyAndSellStockII_122}
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
 * @Bloomberg
 * @Facebook
 * @Amazon
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class BestTimeToBuyAndSellStockWithTransactionFee_714 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{1, 3, 2, 8, 4, 9}, 2, 8));
        tests.add(test(new int[]{1, 3, 7, 5, 10, 3}, 3, 6));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] prices, int fee, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Prices", "Fee", "Expected"}, true, prices, fee, expected);

        int output;
        boolean pass, finalPass = true;

        //add logic here

        return finalPass;

    }
}
