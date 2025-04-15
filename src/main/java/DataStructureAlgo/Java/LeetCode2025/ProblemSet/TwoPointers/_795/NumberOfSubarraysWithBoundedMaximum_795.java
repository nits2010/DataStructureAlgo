package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._795;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/9/2025
 * Question Title:795. Number of Subarrays with Bounded Maximum
 * Link: https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/description/
 * Description: Given an integer array nums and two integers left and right, return the number of contiguous non-empty subarrays such that the value of the maximum array element in that subarray is in the range [left, right].
 * <p>
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,1,4,3], left = 2, right = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Example 2:
 * <p>
 * Input: nums = [2,9,2,5,6], left = 2, right = 8
 * Output: 7
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= left <= right <= 109
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
 * @TwoPointers
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Adobe <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class NumberOfSubarraysWithBoundedMaximum_795 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{2, 1, 4, 3}, 2, 3, 3));
        tests.add(test(new int[]{2, 9, 2, 5, 6}, 2, 8, 7));
        tests.add(test(new int[]{73, 55, 36, 5, 55, 14, 9, 7, 72, 52}, 32, 69, 22));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int left, int right, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "left", "right", "Expected"}, true, nums, left, right, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.numSubarrayBoundedMax(nums, left, right);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int numSubarrayBoundedMax(int[] nums, int left, int right) {
            int i = 0, j = 0; //sliding window start and end pointer
            int count = 0;
            int result = 0;

            //keep starching the window
            while (j < nums.length) {

                if (nums[j] > right) {
                    //since this is out of range, we have to stop our window here and move it next element
                    i = j + 1;
                    count = 0;
                } else if (nums[j] >= left) { // is nums[j] is in range ?
                    count = j - i + 1; //total elements and their pick rate nc2
                }

                result += count;
                j++; //increase size of a window

            }
            return result;
        }

        boolean inRange(int num, int left, int right) {
            return num >= left && num <= right;
        }
    }
}
