package DataStructureAlgo.Java.LeetCode.subarrays;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-24
 * Description: https://www.geeksforgeeks.org/longest-subarray-sum-elements-atmost-k/
 * <p>
 * Given an array of integers, our goal is to find the length of largest subarray having sum of its elements at-most ‘k’
 * where k > 0. Means all elements are positive only.
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = {1, 2, 1, 0, 1, 1, 0},
 * k = 4
 * Output : 5
 * Explanation:
 * {1, 2, 1} => sum = 4, length = 3
 * {1, 2, 1, 0}, {2, 1, 0, 1} => sum = 4, length = 4
 * {1, 0, 1, 1, 0} =>5 sum = 3, length = 5
 *
 * similar to this with K < 0 as well
 * @Apple
 * https://leetcode.com/discuss/interview-question/758045/Apple-or-Phone-or-Longest-Subarray-Sum-At-Most-K
 */
public class LongestSubarraySumAtMostk {

    public static void main(String[] args) {

        System.out.print(sumAtMostKPositive(new int[]{1, 2, 1, 0, 1, 1, 0}, 4));
        System.out.print(sumAtMostKPositive(new int[]{1, 2, 1, 2, 3, 4, 0}, 4));
    }


    static int sumAtMostKPositive(int[] nums, int k) {

        if (nums.length == 0)
            return 0;


        System.out.println("\n \n Input k: " + k);
        CommonMethods.print(nums);
        int maxSize = 0;

        int sum = 0;

        int start = 0;


        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum <= k) {

                maxSize = Math.max(maxSize, i - start + 1);
            } else {
                //sum is more than k, we need to remove the element from start;
                if (sum > 0) {

                    sum = sum - nums[start];
                    start++;

                }
            }
        }


        return maxSize;
    }
}
