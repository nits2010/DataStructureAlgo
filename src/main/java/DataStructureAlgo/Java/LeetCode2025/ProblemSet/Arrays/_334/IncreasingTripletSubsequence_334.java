package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._334;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/1/2024
 * Question Category: 334. Increasing Triplet Subsequence
 * Description: https://leetcode.com/problems/increasing-triplet-subsequence/description/
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 * <p>
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 * <p>
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Array
 * @Greedy
 * @medium <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class IncreasingTripletSubsequence_334 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 4, 5}, true));
        tests.add(test(new int[]{5, 4, 3, 2, 1}, false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, boolean expected) {
        CommonMethods.printTestOutcome(new String[]{"nums", "expected"}, true, nums, expected);
        Solution solution = new Solution();
        boolean output = solution.increasingTriplet(nums);
        CommonMethods.printTestOutcome(new String[]{"output", "expected"}, false, output, expected);
        return output == expected;
    }

    static class Solution {
        public boolean increasingTriplet(int[] nums) {
            if (nums.length < 3)
                return false;

            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;

            for (int n : nums) {
                if (n <= min1)
                    min1 = n;
                else if (n <= min2)
                    min2 = n;
                else
                    return true;
            }
            return false;
        }
    }
}
