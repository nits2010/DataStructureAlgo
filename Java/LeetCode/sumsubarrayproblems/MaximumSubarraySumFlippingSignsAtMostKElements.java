package Java.LeetCode.sumsubarrayproblems;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/maximum-subarray-sum-by-flipping-signs-of-at-most-k-array-elements/
 * Given an array arr[] of N integers and an integer K. The task is to find the maximum sub-array sum by flipping signs of at most K array elements.
 * <p>
 * Examples:
 * <p>
 * Input: arr[] = {-6, 2, -1, -1000, 2}, k = 2
 * Output: 1009
 * We can flip the signs of -6 and -1000, to get maximum subarray sum as 1009
 * <p>
 * Input: arr[] = {-1, -2, -100, -10}, k = 1
 * Output: 100
 * We can only flip the sign of -100 to get 100
 * <p>
 * Input: {1, 2, 100, 10}, k = 1
 * Output: 113
 * We do not need to flip any elements
 */
public class MaximumSubarraySumFlippingSignsAtMostKElements {

    public static void main(String[] args) {


        System.out.println(maxSumByFlipping(new int[]{-6, 2, -1, -1000, 2}, 2));
        System.out.println(maxSumByFlipping(new int[]{-1, -2, -100, -10}, 1));
        System.out.println(maxSumByFlipping(new int[]{1, 2, 100, 10}, 1));
    }

    /**
     * Explanation:
     * <p>
     * Brute force: Since we allowed to do only at most K Flips (0 ... k) and we need to find "sub-array" has maximum sum.
     * We can build all the sub-array of different size that take O(n^2) and for each sub-array of having at most n element
     * we can try all the flips from 0 to K. Each element will have choice to get flip or not. O(nk).
     * To compute the sum of sub-array we can use commutative sum array
     * Complexity: O(k*n^3)
     * <p>
     * <p>
     * Dynamic programming:
     * If you see above, we are keep flipping a number and keep calculating what would be the state of final. This gives you overlapping
     * and current sub-array sum is can be used by previous sub-array sum that give sub-problems
     * <p>
     * <p>
     * Derivation
     * Each element has only two choice,
     * 1. either we flip this number and find the sub-array sum from next number with flips-1
     * 2. Or we don't flip this number and find the sub-array sum from next number with flips
     * <p>
     * In case number become zero then we'll take 0
     * And take max of above.
     * <p>
     * flips(i, k )  = Max {
     * *                        Max { 0, nums[i] + flips(i+1, k) ) ; if we don't flip the current number
     * *                        Max { 0, -nums[i] + flips (i+1, k-1} ; if we flip this current number
     * *                   }
     * <p>
     * Let
     * Dp[i][k] defines the maximum sum sub-array from i we get by j flips
     * <p>
     * Since our dp says from i then we need to try every i in order to find the sub-array.
     *
     * @param nums
     * @param k
     * @return
     */
    private static int maxSumByFlipping(int[] nums, int k) {
        if (null == nums || nums.length == 0)
            return 0;

        int max = 0;

        int dp[][] = new int[nums.length][k + 1];
        for (int i = 0; i < nums.length; i++)
            Arrays.fill(dp[i], Integer.MIN_VALUE);

        for (int i = 0; i < nums.length; i++)
            max = Math.max(max, maxSumByFlipping(nums, k, i, dp));

        return max;
    }


    /**
     * Our choices
     * Either flip this number or not at index nums[i]
     * <p>
     * Our constraints
     * 1. Has we cover all numbers ( i>=n)
     * 2. Has number of flips ends (k == 0)
     * <p>
     * Our goal
     * Max sum
     *
     * @param nums
     * @param i
     * @return
     */
    private static int maxSumByFlipping(int[] nums, int flips, int i, int dp[][]) {

        //if this flip is not allowed
        if (flips < 0)
            return (int) (-1e9);

        //no more element left
        if (i == nums.length)
            return 0;


        //If we have solve this already
        if (dp[i][flips] != Integer.MIN_VALUE)
            return dp[i][flips];


        int max;
        int withoutFlip = nums[i] + maxSumByFlipping(nums, flips, i + 1, dp);
        max = Math.max(0, withoutFlip);

        int withFlip = -nums[i] + maxSumByFlipping(nums, flips - 1, i + 1, dp);
        max = Math.max(max, withFlip);

        return dp[i][flips] = max;


    }


}
