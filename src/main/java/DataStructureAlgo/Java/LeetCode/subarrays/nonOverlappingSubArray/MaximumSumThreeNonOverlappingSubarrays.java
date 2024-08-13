package DataStructureAlgo.Java.LeetCode.subarrays.nonOverlappingSubArray;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-14
 * Description: https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * <p>
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * <p>
 * Return the result as a list of indices representing the starting position of each interval (0-indexed).
 * If there are multiple answers, return the lexicographically smallest one.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 * <p>
 * <p>
 * Note:
 * <p>
 * nums.length will be between 1 and 20000.
 * nums[i] will be between 1 and 65535.
 * k will be between 1 and floor(nums.length / 3).
 */
public class MaximumSumThreeNonOverlappingSubarrays {

    public static void main(String[] args) {
        test(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2, new int[]{0, 3, 5});
    }

    private static void test(int[] nums, int k, int[] expected) {
        System.out.println("Input : " + CommonMethods.toString(nums) + " k :" + k);
        MaximumSum3NonOverlappingSubarrays sol = new MaximumSum3NonOverlappingSubarrays();
        System.out.println("Obtained :" + CommonMethods.toString(sol.maxSumOfThreeSubarrays(nums, k)) + " expected :" + CommonMethods.toString(expected));
    }
}

/**
 * Runtime: 3 ms, faster than 73.25% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
 * Memory Usage: 40.4 MB, less than 100.00% of Java online submissions for Maximum Sum of 3 Non-Overlapping Subarrays.
 */
class MaximumSum3NonOverlappingSubarrays {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        return maxSumOfThreeSubArrays(nums, 3, k);

    }

    /**
     * Idea
     * similar to {@link DataStructureAlgo.Java.LeetCode.ProductArrayExceptSelf}
     * Extension of {@link MaximumSumTwoNonOverlappingSubarrays}.
     * <p>
     * Idea: https://leetcode.com/articles/maximum-sum-of-3-non-overlapping-intervals/
     *
     * If the middle interval is [i, i+k-1], where k <= i <= n-2k,
     * the left interval has to be in subrange [0, i-1], and the right interval is from subrange [i+k, n-1].
     * @param nums
     * @param m
     * @param k
     * @return
     */
    public int[] maxSumOfThreeSubArrays(int[] nums, int m, int k) {

        if (nums == null || nums.length == 0)
            return nums;

        int result[] = new int[]{-1, -1, -1};


        int n = nums.length;

        if (n < m * k)
            return new int[0];

        int currSum = 0;

        int windowSum[] = new int[n - k + 1];

        for (int i = 0; i < n; i++) {

            currSum += nums[i];

            /**
             * Update curr sum so that it have current k window sum only
             */
            if (i >= k)
                currSum -= nums[i - k];

            /**
             * Keep pushing the k windows sum s.t. its sum of i...i+k-1
             */
            if (i >= k - 1)
                windowSum[i - k + 1] = currSum;
        }

        /**
         * Will contains the maximum sum of window at the left side of current i [including i ]
         * this contains the index of windowSum where it found best
         */
        int leftWindowSum[] = new int[windowSum.length];

        /**
         * Will contains the maximum sum of window at the right side of current i [including i ]
         * this contains the index of windowSum where it found best
         */
        int rightWindowSum[] = new int[windowSum.length];


        int i = 0;
        int leftBest = i;

        int j = windowSum.length - 1;
        int rightBest = j;

        while (i < windowSum.length && j >= 0) {

            /**
             * update the left sum. When sum is equal we choose later one which gives more room to left
             * If there are multiple answers, return the lexicographically smallest one.
             *
             * i.e. we choose the leftMost index to make it lexicographically smallest one
             */
            if (windowSum[i] > windowSum[leftBest])
                leftBest = i;

            leftWindowSum[i] = leftBest;


            /**
             * update the left sum. When sum is equal we choose later one which gives more room to left
             *  * If there are multiple answers, return the lexicographically smallest one.
             * i.e. we choose the leftMost index to make it lexicographically smallest one
             */
            if (windowSum[j] >= windowSum[rightBest])
                rightBest = j;

            rightWindowSum[j] = rightBest;

            i++;
            j--;
        }

        System.out.println("Left " + CommonMethods.toString(leftWindowSum) + " \n right " + CommonMethods.toString(rightWindowSum));

        /**
         * left -> represent the index of left sub-array
         * middle -> represent the index of middle sub-array
         * right -> represent the index of right sub-array
         */
        int left = 0;
        int middle = 0;
        int right = 0;

        middle = k; //Mid array start from k to leave a space for left sub array 0...k-1

        int maxSum = 0;
        while (middle < windowSum.length - k) {

            left = leftWindowSum[middle - k];
            right = rightWindowSum[middle + k];

            int currentSubArraysSum = windowSum[left] + windowSum[middle] + windowSum[right];

            if (result[0] == -1 || currentSubArraysSum > maxSum) {
                maxSum = currentSubArraysSum;
                result[0] = left;
                result[1] = middle;
                result[2] = right;
            }
            middle++;
        }

        return result;
    }

}