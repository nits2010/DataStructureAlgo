package Java.LeetCode.stockPrices;

import Java.HelpersToPrint.Printer;
import Java.companyWise.Amazon.MaximumDifferenceInIndex;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-30
 * Description:
 * Given stocks value for each day, find the longest duration at which you can hold the stocks such that you don't incur any loss.
 * Provided that
 * 1> Buy the stock exactly once
 * 2> Sell the stock exactly once
 * 3> Can buy and sell on the same day
 * <p>
 * Input: [3,2,1,4]
 * output: 3 { you can have the stock at 0th index v=3 and hold till 3rd index v=4}
 * <p>
 * input [8,5,8,3,9,1,6]
 * output: 5 { you can have the stock at 1th index v=5 and hold till 6th index v=6}
 */
public class LongestDurationHoldStocks {

    /**
     * 'longest duration at which you can hold the stocks such that you don't incur any loss' only possible when
     * stocks[i] < stocks[j] and j-i is maximum.
     * <p>
     * This is similar to {@link MaximumDifferenceInIndex}
     */

    public static void main(String[] args) {
        test(new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1}, 6);
        test(new int[]{9, 2, 3, 4, 5, 6, 7, 8, 18, 0}, 8);
        test(new int[]{1, 2, 3, 4, 5, 6}, 5);
        test(new int[]{6, 5, 4, 3, 2, 1}, -1);
    }

    private static void test(int[] nums, int expected) {
        System.out.println("\n Input :" + Printer.toString(nums) + " expected :" + expected);

        LongestDurationHoldStocksBruteForce bruteForce = new LongestDurationHoldStocksBruteForce();
        LongestDurationHoldStocksPrefixSuffixArray prefixSuffixArray = new LongestDurationHoldStocksPrefixSuffixArray();

        System.out.println(" bruteForce :" + bruteForce.longestDurationToHoldStocks(nums));
        System.out.println(" prefixSuffixArray :" + prefixSuffixArray.longestDurationToHoldStocks(nums));
    }


}


/**
 * Since we need to find the maximum difference between j and i, s.t.
 * j > i and a[j] > a[i].
 * <p>
 * To maximize (j-i) we need to make sure that i should be as much as left and j should be as much as on right. s.t. a[j]>a[i]
 * <p>
 * We'll run through all the numbers as 'i' and check how long we can go on right as 'j'
 * <p>
 * O(n^2) / O(1)
 */
class LongestDurationHoldStocksBruteForce {

    public int longestDurationToHoldStocks(int stocks[]) {
        if (stocks == null)
            return 0;

        int maxDiff = -1;

        for (int i = 0; i < stocks.length; i++) {

            for (int j = i + 1; j < stocks.length; j++) {

                if (stocks[j] > stocks[i])
                    maxDiff = Math.max(maxDiff, j - i);
            }
        }

        return maxDiff;
    }

}

/**
 * Important point from above algo;
 * 'To maximize (j-i) we need to make sure that i should be as much as left and j should be as much as on right.
 * a[j]>a[i]'
 * <p>
 * which means if we can find a way faster than O(n) to tell what is the a[i] and a[j] at any index s.t. a[j] > a[i].
 * Then we can calculate the maxDiff faster.
 * <p>
 * How to find a[i] and a[j] ?
 * Important thing here is a[i] < a[j] for each j (j>i). Means we need to see at any index what is the smaller before it [i]
 * Similarly we need to see at each index what is the greater after it. [j].
 * Since we need to maximize [j-i], then we need to find rightmost element which is greater after it and leftmost element with is smaller to it
 * <p>
 * To find, we can build Prefix and suffix array.
 * Prefix Array (smaller on left) : go from left to right and find min
 * Suffix array (greater on right): go from right to left and find max
 * <p>
 * After building those two array, we can
 * test
 * if(prefix[i] < suffix[j]) then calculate max diff
 * since we need to maximize j-i, then we shift j here
 * otherwise i
 * <p>
 * O(n) / O(n)
 */
class LongestDurationHoldStocksPrefixSuffixArray {

    public int longestDurationToHoldStocks(int stocks[]) {
        if (stocks == null)
            return 0;

        int maxDiff = -1;

        int n = stocks.length;
        int prefixMinLeftSide[] = new int[n];
        int suffixMaxRightSide[] = new int[n];

        //for index 0 and n-1, they are only element
        prefixMinLeftSide[0] = stocks[0];
        suffixMaxRightSide[n - 1] = stocks[n - 1];

        for (int i = 1, j = n - 2; i < n; i++, j--) {
            prefixMinLeftSide[i] = Math.min(prefixMinLeftSide[i - 1], stocks[i]);
            suffixMaxRightSide[j] = Math.max(suffixMaxRightSide[j + 1], stocks[j]);
        }

        int left = 0, right = 0;
        while (left < n && right < n) {
            if (prefixMinLeftSide[left] < suffixMaxRightSide[right]) {
                maxDiff = Math.max(maxDiff, right - left);
                right++;
            } else
                left++;
        }

        return maxDiff;
    }

}