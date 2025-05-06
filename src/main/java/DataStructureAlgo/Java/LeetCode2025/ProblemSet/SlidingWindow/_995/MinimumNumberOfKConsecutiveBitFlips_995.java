package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._995;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/6/2025
 * Question Title: 995. Minimum Number of K Consecutive Bit Flips
 * Link: https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/description/
 * Description: You are given a binary array nums and an integer k.
 * <p>
 * A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 * <p>
 * Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0], k = 1
 * Output: 2
 * Explanation: Flip nums[0], then flip nums[2].
 * Example 2:
 * <p>
 * Input: nums = [1,1,0], k = 2
 * Output: -1
 * Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].
 * Example 3:
 * <p>
 * Input: nums = [0,0,0,1,0,1,1,0], k = 3
 * Output: 3
 * Explanation:
 * Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
 * Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
 * Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= k <= nums.length
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._3191.MinimumOperationsToMakeBinaryArrayElementsEqualToOneI_3191}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @BitManipulation
 * @Queue
 * @SlidingWindow
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Akuna
 * @AkunaCapital
 * @Amazon <p>
 * -----
 * @Editorial https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumNumberOfKConsecutiveBitFlips_995 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{0, 1, 0}, 1, 2));
        tests.add(test(new int[]{1, 1, 0}, 2, -1));
        tests.add(test(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3, 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().minKBitFlips(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int minKBitFlips(int[] nums, int k) {
            return minOperations(nums, k);
        }

        public int minOperations(int[] nums, int k) {
            int n = nums.length;
            int flipCount = 0;

            int[] diff = new int[n + 1]; // To track when a flip effect ends
            int flip = 0;

            for (int i = 0; i < n; i++) {
                flip ^= diff[i]; // Remove expired flip effects

                //flip required at index i ? if its 0
                if ((nums[i] ^ flip) == 0) {

                    //if there is a group of k = 3 not left, then entire array can't be 1
                    if (i + k - 1 >= n)
                        return -1;

                    //change the flip state
                    flip ^= 1;
                    diff[i + k] ^= 1; // We'll end the flip at i + k
                    flipCount++;

                }

            }
            return flipCount;
        }
    }
}
