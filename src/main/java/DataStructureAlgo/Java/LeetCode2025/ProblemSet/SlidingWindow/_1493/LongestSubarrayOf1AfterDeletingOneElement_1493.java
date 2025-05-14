package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1493;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/13/2025
 * Question Title: 1493. Longest Subarray of 1's After Deleting One Element
 * Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description
 * Description: Given a binary array nums, you should delete one element from it.
 * <p>
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 * <p>
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * Example 3:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
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
 * @DynamicProgramming
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Yandex <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestSubarrayOf1AfterDeletingOneElement_1493 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 1, 0, 1}, 3));
        tests.add(test(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}, 5));
        tests.add(test(new int[]{1, 1, 1}, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_SlidingWindow_V1().longestSubarray(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow_V1", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_SlidingWindow_V2().longestSubarray(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow_V2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_SlidingWindow_V3().longestSubarray(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow_V3", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_SlidingWindow_V1 {
        public int longestSubarray(int[] nums) {
            int oneCount = 0, zeroCount = 0;
            int n = nums.length;
            int maxSize = -1;
            int start = 0, end = 0;

            while (end < n) {

                //expand the window
                if (nums[end] == 1)
                    oneCount++;
                else
                    zeroCount++;

                //valid window ?
                if (zeroCount <= 1) {
                    maxSize = Math.max(maxSize, zeroCount == 0 ? oneCount - 1 : oneCount); // you should delete one element from it.
                } else {
                    //invalid window, shrink it
                    if (nums[start] == 1)
                        oneCount--;
                    else
                        zeroCount--;
                    start++;
                }

                end++;
            }
            return maxSize;
        }
    }

    //We don't need to count 1, as the size of the window will always be our answer this is because, as soon as we see
    // more then 1 zero count, we have to shrink the window. Hence, tracking only zero count is sufficient.
    static class Solution_SlidingWindow_V2 {
        public int longestSubarray(int[] nums) {
            int oneCount = 0, zeroCount = 0;
            int n = nums.length;
            int maxSize = -1;
            int start = 0, end = 0;

            while (end < n) {

                //expand the window
                zeroCount += nums[end] == 0 ? 1 : 0;

                //valid window ?
                while (zeroCount > 1) {
                    zeroCount -= nums[start] == 0 ? 1 : 0;
                    start++;
                }
                maxSize = Math.max(maxSize, end - start + 1);

                end++;
            }
            --maxSize; //you should delete one element from it.
            return maxSize;
        }
    }

    static class Solution_SlidingWindow_V3 {
        public int longestSubarray(int[] nums) {
            int oneCount = 0, zeroCount = 0;
            int n = nums.length;
            int maxSize = -1;
            int start = 0, end = 0;
            int removeIndex = -1;

            while (end < n) {

                int ele = nums[end];
                if (ele == 0) {
                    start = removeIndex + 1; //removing earlier 0
                    removeIndex = end; //caching this zero index to remove later
                }

                maxSize = Math.max(maxSize, end - start + 1);

                end++;
            }
            --maxSize;
            return maxSize;
        }
    }


}
