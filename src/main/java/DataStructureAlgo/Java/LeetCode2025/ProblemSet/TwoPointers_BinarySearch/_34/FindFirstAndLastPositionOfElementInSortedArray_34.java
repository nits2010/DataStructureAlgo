package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._34;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/20/2025
 * Question Title: 34. Find First and Last Position of Element in Sorted Array
 * Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * Description: Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 * <p>
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.FindFirstLastPositionElementSortedArray}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._278.FirstBadVersion_278}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Bloomberg
 * @Facebook
 * @Google
 * @LinkedIn
 * @Microsoft
 * @Oracle
 * @Qualtrics
 * @Uber
 * @Visa
 * @Yahoo
 * @Yandex <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindFirstAndLastPositionOfElementInSortedArray_34 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{5, 7, 7, 8, 8, 10}, 8, new int[]{3, 4}));
        tests.add(test(new int[]{5, 7, 7, 8, 8, 10}, 6, new int[]{-1, -1}));
        tests.add(test(new int[]{}, 0, new int[]{-1, -1}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int target, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "target", "Expected"}, true, nums, target, expected);

        int[] output;
        boolean pass, finalPass = true;

        output = new Solution().searchRange(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int[] searchRange(int[] nums, int target) {

            int n = nums.length;

            int firstIndex = binarySearchFirstIndex(nums, target);
            if (firstIndex == -1 || nums[firstIndex] != target)
                return new int[]{-1, -1};

            int lastIndex = binarySearchLastIndex(nums, target);

            return new int[]{firstIndex, lastIndex};
        }

        private int binarySearchFirstIndex(int[] nums, int target) {

            int low = 0, high = nums.length - 1;
            int leftMostIndex = -1;
            while (low <= high) {

                int mid = (low + high) >>> 1;

                if (nums[mid] >= target) {
                    leftMostIndex = mid;

                    //shift to left
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return leftMostIndex;
        }

        private int binarySearchLastIndex(int[] nums, int target) {

            int low = 0, high = nums.length - 1;
            int rightMostIndex = -1;
            while (low <= high) {

                int mid = (low + high) >>> 1;

                if (nums[mid] <= target) {
                    rightMostIndex = mid;

                    //shift to left
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return rightMostIndex;
        }
    }

}
