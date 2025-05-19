package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._704;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/19/2025
 * Question Title: 704. Binary Search
 * Link: https://leetcode.com/problems/binary-search/description/
 * Description: Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 *
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 < nums[i], target < 104
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
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
 * @BinarySearch
 *
 * <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Google
 * @Microsoft
 * @Bloomberg
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class BinarySearch_704 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{-1,0,3,5,9,12}, 9, 4));
        tests.add(test(new int[]{-1,0,3,5,9,12}, 2, -1));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int target, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "target", "Expected"}, true, nums, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_().search(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BSV1", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution().search(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BSV2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_ {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            int mid = -1;
            while (low <= high) {
                mid = (low + high) >>> 1;

                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return -1;
        }
    }

    static class Solution {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            int mid = -1;
            while (low < high) { // <------------------ instead low '<=' high ..
                mid = (low + high) >>> 1;

                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return nums[low] == target ? low : -1; // <------------------ instead return, cross check
        }
    }
}
