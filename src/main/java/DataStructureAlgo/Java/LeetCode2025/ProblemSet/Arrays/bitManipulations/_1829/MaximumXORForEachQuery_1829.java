package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.bitManipulations._1829;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/9/2024
 * Question Category: 1829. Maximum XOR for Each Query
 * Description: https://leetcode.com/problems/maximum-xor-for-each-query/description/
 * You are given a sorted array nums of n non-negative integers and an integer maximumBit. You want to perform the following query n times:
 * <p>
 * Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k is maximized. k is the answer to the ith query.
 * Remove the last element from the current array nums.
 * Return an array answer, where answer[i] is the answer to the ith query.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,1,3], maximumBit = 2
 * Output: [0,3,2,3]
 * Explanation: The queries are answered as follows:
 * 1st query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
 * 2nd query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
 * 3rd query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
 * 4th query: nums = [0], k = 3 since 0 XOR 3 = 3.
 * Example 2:
 * <p>
 * Input: nums = [2,3,4,7], maximumBit = 3
 * Output: [5,2,6,5]
 * Explanation: The queries are answered as follows:
 * 1st query: nums = [2,3,4,7], k = 5 since 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7.
 * 2nd query: nums = [2,3,4], k = 2 since 2 XOR 3 XOR 4 XOR 2 = 7.
 * 3rd query: nums = [2,3], k = 6 since 2 XOR 3 XOR 6 = 7.
 * 4th query: nums = [2], k = 5 since 2 XOR 5 = 7.
 * Example 3:
 * <p>
 * Input: nums = [0,1,2,2,5,7], maximumBit = 3
 * Output: [4,3,6,4,6,7]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums.length == n
 * 1 <= n <= 105
 * 1 <= maximumBit <= 20
 * 0 <= nums[i] < 2maximumBit
 * nums​​​ is sorted in ascending order.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @BitManipulation
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MaximumXORForEachQuery_1829 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{0, 1, 1, 3}, 2, new int[]{0, 3, 2, 3}));
        tests.add(test(new int[]{2, 3, 4, 7}, 3, new int[]{5, 2, 6, 5}));
        tests.add(test(new int[]{0, 1, 2, 2, 5, 7}, 3, new int[]{4, 3, 6, 4, 6, 7}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] input, int maximumBit, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Input", "MaximumBit", "Expected"}, true, input, maximumBit, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.getMaximumXor(input, maximumBit);
        pass = CommonMethods.compareResultOutCome(expected, output, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Solution {

        public int[] getMaximumXor(int[] nums, int maximumBit) {
            int xorProduct = 0;
            for (int num : nums) {
                xorProduct = xorProduct ^ num;
            }
            int[] ans = new int[nums.length];

            int mask = (1 << maximumBit) - 1;

            for (int i = 0; i < nums.length; i++) {
                ans[i] = xorProduct ^ mask;
                // remove last element
                xorProduct = xorProduct ^ nums[nums.length - 1 - i];
            }

            return ans;
        }
    }
}
