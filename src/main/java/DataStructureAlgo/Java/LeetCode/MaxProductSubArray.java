package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.LeetCode.subarrays.LargestMaximumSumSubarrayKadensAlgorithm;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-23
 * Description: https://leetcode.com/problems/maximum-product-subarray/submissions/
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaxProductSubArray {

    public static void main(String []args) {
        int nums[] = {-2, 0, -1};
        int nums2[] = {-2, -1, 0, -4};
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct(nums2));
    }

    public static int maxProduct(int[] nums) {
        return maxProductEasy(nums);
    }

    /**
     * Runtime: 1 ms, faster than 99.14% of Java online submissions for Maximum Product Subarray.
     * Memory Usage: 37.8 MB, less than 14.64% of Java online submissions for Maximum Product Subarray.
     *
     * @param nums
     * @return {@link LargestMaximumSumSubarrayKadensAlgorithm}
     */
    public static int maxProductEasy(int[] nums) {

        int min = nums[0];
        int max = nums[0];
        int maxProd = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] < 0) {
                //current element is neg, this means whatever max is , by multiplying it with a neg
                // number make it min
                int x = min;
                min = max;
                max = x;
            }


            //if current number is >=0; then either our answer is 0 or max pos
            max = Math.max(nums[i], nums[i] * max);
            min = Math.min(nums[i], nums[i] * min);

            maxProd = Math.max(maxProd, max);


        }
        return maxProd;
    }


    //===========================================================================================

    /**
     * Geekforgeek solution
     * The following solution assumes that the given input array always has a positive output
     *
     * @return
     */

    // Utility functions to get minimum of two integers
    static int min(int x, int y) {
        return x < y ? x : y;
    }

    // Utility functions to get maximum of two integers
    static int max(int x, int y) {
        return x > y ? x : y;
    }

    /* Returns the product of max product subarray.
    Assumes that the given array always has a subarray
    with product more than 1 */
    static int maxSubarrayProduct(int arr[]) {
        int n = arr.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0];

        // max positive product ending at the current position
        int max_ending_here = 1;

        // min negative product ending at the current position
        int min_ending_here = 1;

        // Initialize overall max product
        int max_so_far = 1;
        int flag = 0;

        /* Traverse through the array. Following
        values are maintained after the ith iteration:
        max_ending_here is always 1 or some positive product
                        ending with arr[i]
        min_ending_here is always 1 or some negative product
                        ending with arr[i] */
        for (int i = 0; i < n; i++) {
            /* If this element is positive, update max_ending_here.
                Update min_ending_here only if min_ending_here is
                negative */
            if (arr[i] > 0) {
                max_ending_here = max_ending_here * arr[i];
                min_ending_here = min(min_ending_here * arr[i], 1);
                flag = 1;
            }

            /* If this element is 0, then the maximum product cannot
            end here, make both max_ending_here and min_ending
            _here 0
            Assumption: Output is alway greater than or equal to 1. */
            else if (arr[i] == 0) {
                max_ending_here = 1;
                min_ending_here = 1;
            }

            /* If element is negative. This is tricky
            max_ending_here can either be 1 or positive.
            min_ending_here can either be 1 or negative.
            next min_ending_here will always be prev.
            max_ending_here * arr[i]
            next max_ending_here will be 1 if prev
            min_ending_here is 1, otherwise
            next max_ending_here will be
                        prev min_ending_here * arr[i] */
            else {
                int temp = max_ending_here;
                max_ending_here = max(min_ending_here * arr[i], 1);
                min_ending_here = temp * arr[i];
            }

            // update max_so_far, if needed
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
        }

        if (flag == 0 && max_so_far == 1)
            return 0;
        return max_so_far;
    }
}
