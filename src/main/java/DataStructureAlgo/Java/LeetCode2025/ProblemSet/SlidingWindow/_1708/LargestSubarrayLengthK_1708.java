package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1708;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/27/2025
 * Question Title: 1708. Largest Subarray Length K
 * Link: https://leetcode.com/problems/largest-subarray-length-k/description/
 * https://leetcode.ca/all/1708.html
 * Description: An array A is larger than some array B if for the first index i where A[i] != B[i], A[i] > B[i].
 * <p>
 * For example, consider 0-indexing:
 * <p>
 * [1,3,2,4] > [1,2,2,4], since at index 1, 3 > 2.
 * [1,4,4,4] < [2,1,1,1], since at index 0, 1 < 2.
 * A subarray is a contiguous subsequence of the array.
 * <p>
 * Given an integer array nums of distinct integers, return the largest subarray of nums of length k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,4,5,2,3], k = 3
 * Output: [5,2,3]
 * Explanation: The subarrays of size 3 are: [1,4,5], [4,5,2], and [5,2,3].
 * Of these, [5,2,3] is the largest.
 * Example 2:
 * <p>
 * Input: nums = [1,4,5,2,3], k = 4
 * Output: [4,5,2,3]
 * Explanation: The subarrays of size 4 are: [1,4,5,2], and [4,5,2,3].
 * Of these, [4,5,2,3] is the largest.
 * Example 3:
 * <p>
 * Input: nums = [1,4,5,2,3], k = 1
 * Output: [5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * All the integers of nums are unique.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Array
 * @SlidingWindow
 * @PremimumQuestion
 * @LockedProblem <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial https://github.com/doocs/leetcode/blob/main/solution/1700-1799/1708.Largest%20Subarray%20Length%20K/README_EN.md <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LargestSubarrayLengthK_1708 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 4, 5, 2, 3}, 3, new int[]{5, 2, 3}));
        tests.add(test(new int[]{1, 4, 5, 2, 3}, 4, new int[]{4, 5, 2, 3}));
        tests.add(test(new int[]{1, 4, 5, 2, 3}, 1, new int[]{5}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int[] expected) {

        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, expected);

        int[] output;
        boolean pass, finalPass = true;

        output = new Solution().largestSubarray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {

        public int[] largestSubarray(int[] nums, int k) {
            //since all array elements are unique, we just need to find the first index of the array which is largest such that it has k-1 elements ahead
            //[1,4,5,2,3] k = 3, n = 5, j = 2 -> i [0..5-3] = [0..2]
            int largestElementIndex = 0;
            for (int i = 0; i <= nums.length - k; i++) {
                if (nums[largestElementIndex] < nums[i]) {
                    largestElementIndex = i;
                }
            }

            return Arrays.copyOfRange(nums, largestElementIndex, largestElementIndex + k);
        }
    }
}
