package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._643;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/27/2025
 * Question Title: 643. Maximum Average Subarray I
 * Link: https://leetcode.com/problems/maximum-average-subarray-i/description/
 * Description: You are given an integer array nums consisting of n elements, and an integer k.
 * <p>
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * Example 2:
 * <p>
 * Input: nums = [5], k = 1
 * Output: 5.00000
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 * @Array
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumAverageSubarrayI_643 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 12, -5, -6, 50, 3}, 4, 12.75));
        tests.add(test(new int[]{5}, 1, 5.00000));
        tests.add(test(new int[]{-1}, 1, -1.00000));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, double expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        double output = 0;
        boolean pass, finalPass = true;

        output = new Solution().findMaxAverage(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public double findMaxAverage(int[] nums, int k) {
            int windowSum = 0;
            double average = Integer.MIN_VALUE;
            int windowStart = 0, windowEnd = 0;

            while (windowEnd < nums.length) {

                windowSum += nums[windowEnd];

                if (windowEnd >= k - 1) {

                    //calculate average
                    average = Math.max(average, windowSum / (double) k);

                    windowSum -= nums[windowStart];

                    //shrink it
                    windowStart++;
                }

                windowEnd++;
            }


            return average;
        }
    }
}
