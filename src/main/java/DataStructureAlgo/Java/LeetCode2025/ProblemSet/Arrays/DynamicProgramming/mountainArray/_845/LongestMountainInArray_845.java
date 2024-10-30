package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.mountainArray._845;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/30/2024
 * Question Category: 845. Longest Mountain in Array
 * Description: https://leetcode.com/problems/longest-mountain-in-array/description/
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 * <p>
 * Input: arr = [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 104
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.nonleetcode.BitonicProblems.MaximumLengthBitonicSubArray}
 * Similar {@link DataStructureAlgo.Java.nonleetcode.BitonicProblems.MaximumLengthBitonicSubArray}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.mountainArray._941.ValidMountainArray_941}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @DynamicProgramming
 * @Enumeration <p><p>
 * Company Tags
 * -----
 * @Cohesity
 * @Google
 * @Wish
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class LongestMountainInArray_845 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{2, 1, 4, 7, 3, 2, 5}, 5);
        test &= test(new int[]{2, 2, 2}, 0);
        test &= test(new int[]{2, 1, 4, 7, 3, 8, 11, 12, 13, 14, 15, 12, 11, 10, 9, 8, 7, 6, 5, 12, 13, 14, 15, 1}, 15);
        test &= test(new int[]{1, 2, 1}, 3);
        test &= test(new int[]{2, 3}, 0);
        test &= test(new int[]{0, 2, 2}, 0);
        test &= test(new int[]{0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1}, 3);
        test &= test(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, 0);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] arr, int expected) {
        CommonMethods.print(new String[]{"Input", "Expected"}, true, arr, expected);
        int output;
        boolean pass = true;
        Solution solution = new Solution();
        output = solution.longestMountain(arr);
        pass &= output == expected;
        CommonMethods.print(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return pass;
    }

    static class Solution {
        public int longestMountain(int[] arr) {
            int max = 0;

            int start, end = 1;
            int n = arr.length;

            while (end < n) {

                if (arr[end - 1] < arr[end]) {

                    //starting a new mountain array
                    start = end - 1;

                    // increasing
                    while (end < n && arr[end - 1] < arr[end])
                        end++;

                    // decreasing exits ?
                    if (end < n && arr[end - 1] > arr[end]) {

                        //decreasing
                        while (end < n && arr[end - 1] > arr[end])
                            end++;

                        //capture max length
                        max = Math.max(max, end - start);
                    }

                } else {
                    //if no mountain start to found, try next
                    end++;
                }

            }

            return max < 3 ? 0 : max;
        }
    }
}
