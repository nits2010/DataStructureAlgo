package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.mountainArray._941;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/30/2024
 * Question Category: 941. Valid Mountain Array
 * Description: https://leetcode.com/problems/valid-mountain-array/description/
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 * <p>
 * Recall that arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,1]
 * Output: false
 * Example 2:
 * <p>
 * Input: arr = [3,5,5]
 * Output: false
 * Example 3:
 * <p>
 * Input: arr = [0,3,2,1]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.nonleetcode.BitonicProblems.MaximumLengthBitonicSubArray}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Array <p><p>
 * Company Tags
 * -----
 * @Amazon -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class ValidMountainArray_941 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{0, 3, 2, 1}, true);
        test &= test(new int[]{3, 5, 5}, false);
        test &= test(new int[]{0, 3, 2, 1}, true);
        test &= test(new int[]{0, 1}, false);
        test &= test(new int[]{0, 1, 2, 3, 4, 5}, false);

        CommonMethods.printResult(test);
    }

    private static boolean test(int[] arr, boolean expected) {
        CommonMethods.print(new String[]{"Input", "Expected"}, true, arr, expected);
        boolean output;
        boolean pass = true;
        Solution solution = new Solution();
        output = solution.validMountainArray(arr);
        pass &= output == expected;
        CommonMethods.print(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return pass;
    }

    static class Solution {
        public boolean validMountainArray(int[] arr) {

            //increasing
            int i = 1;
            while (i < arr.length && arr[i - 1] < arr[i])
                i++;

            if (i == arr.length || i == 1)
                return false;

            while (i < arr.length && arr[i - 1] > arr[i])
                i++;

            if (i != arr.length)
                return false;

            return true;
        }
    }
}
