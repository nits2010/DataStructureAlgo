package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-15
 * Description: https://leetcode.com/problems/subarray-product-less-than-k/
 * Your are given an array of positive integers nums.
 * <p>
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 * <p>
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Note:
 * <p>
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 */
public class SubarrayProductLessThanK {

    public static void main(String[] args) {

        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        System.out.println(numSubarrayProductLessThanK(new int[]{100, 100, 100}, 100));
        System.out.println(numSubarrayProductLessThanK(new int[]{100, 100, 100}, 1000000));


    }

    public static long numSubarrayProductLessThanK(int[] numbers, int k) {
        return numSubarrayProductLessThanKSlidingWindowOptimized(numbers, k);

    }

    /**
     * Apply same logic as sliding window and count
     * <p>
     * Runtime: 9 ms, faster than 59.64% of Java online submissions for Subarray Product Less Than K.
     * Memory Usage: 51.4 MB, less than 100.00% of Java online submissions for Subarray Product Less Than K.
     *
     * @param nums
     * @param k
     * @return
     */
    public static long numSubarrayProductLessThanKSlidingWindow(int[] nums, int k) {
        int prod = 1;
        int ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {

            prod *= nums[right];

            while (left < right && prod >= k) prod /= nums[left++];

            if (prod < k)
                ans += right - left + 1;
        }
        return ans;
    }

    /**
     * Runtime: 7 ms, faster than 100.00% of Java online submissions for Subarray Product Less Than K.
     * Memory Usage: 51.1 MB, less than 100.00% of Java online submissions for Subarray Product Less Than K.
     *
     * @param nums
     * @param k
     * @return
     */
    public static int numSubarrayProductLessThanKSlidingWindowOptimized(int[] nums, int k) {


        int prod = 1;
        int ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {

            prod *= nums[right];

            /**
             * If single element it self is greater then k, then product is always greater, skip those
             */
            if (nums[right] >= k) {
                left = right + 1;
                prod = 1;
            }

            while (left <= right && prod >= k) prod /= nums[left++];

            ans += right - left + 1;
        }
        return ans;


    }

}
