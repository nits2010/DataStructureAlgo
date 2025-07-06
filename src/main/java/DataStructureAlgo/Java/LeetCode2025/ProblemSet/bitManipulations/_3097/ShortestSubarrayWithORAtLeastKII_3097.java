package DataStructureAlgo.Java.LeetCode2025.ProblemSet.bitManipulations._3097;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/10/2024
 * Question Category: 3097. Shortest Subarray With OR at Least K II
 * Description: https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/description/?envType=daily-question&envId=2024-11-10
 * You are given an array nums of non-negative integers and an integer k.
 * <p>
 * An array is called special if the bitwise OR of all of its elements is at least k.
 * <p>
 * Return the length of the shortest special non-empty
 * subarray
 * of nums, or return -1 if no special subarray exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3], k = 2
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The subarray [3] has OR value of 3. Hence, we return 1.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,1,8], k = 10
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * <p>
 * The subarray [2,1,8] has OR value of 11. Hence, we return 3.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2], k = 0
 * <p>
 * Output: 1
 * <p>
 * Explanation:
 * <p>
 * The subarray [1] has OR value of 1. Hence, we return 1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 105
 * 0 <= nums[i] <= 109
 * 0 <= k <= 109
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
 * @medium
 * @Array
 * @BitManipulation
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class ShortestSubarrayWithORAtLeastKII_3097 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{1, 2, 3}, 2, 1));
        tests.add(test(new int[]{2, 1, 8}, 10, 3));
        tests.add(test(new int[]{1, 2}, 0, 1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "K", "Expected"}, true, nums, k, expected);

        int output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.minimumSubarrayLength(nums, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution {
        public int minimumSubarrayLength(int[] nums, int k) {
            int left = 0, right = 0;
            int minLength = nums.length + 1;
            int[] bitsCount = new int[32];  //represent the OR of all numbers in the window of [left,right]
            int currentOR = 0;
            while (right < nums.length) {

                //extend the window till we get a OR <= k
                //add current number
                addRemove(bitsCount, nums[right], true);
                currentOR |= nums[right];

                //shrink the window till we get a OR > k
                while (left <= right && currentOR >= k) {
                    minLength = Math.min(minLength, right - left + 1);

                    addRemove(bitsCount, nums[left], false);
                    currentOR = convert(bitsCount);
                    left++;
                }

                right++;
            }

            return minLength == nums.length + 1 ? -1 : minLength;
        }

        private void addRemove(int[] bits, int n, boolean add) {
            for (int i = 0; i < 32 && n !=0; i++) {
                if ((n & 1) != 0) { //add that bit in the bitCount;
                    bits[i] = add ? bits[i] + 1 : bits[i] - 1;
                }
                n = n >> 1;

            }
        }

        private int convert(int[] bitsCount) {
            int result = 0;
            for (int i = 0; i < 32; i++) {
                if (bitsCount[i] != 0)
//                    result += (int) Math.pow(2,i);
                    result |= 1 << i;
            }

            return result;
        }
    }

}
