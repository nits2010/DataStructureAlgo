package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._80;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 4/15/2025
 * Question Title: 80. Remove Duplicates from Sorted Array II
 * Link:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
 * Description: Given an integer array nums sorted in non-decreasing order,
 * remove some duplicates in-place such that each unique element appears at most
 * twice. The relative order of the elements should be kept the same.
 * 
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array
 * nums. More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result. It does not
 * matter what you leave beyond the first k elements.
 * 
 * Return k after placing the final result in the first k slots of nums.
 * 
 * Do not allocate extra space for another array. You must do this by modifying
 * the input array in-place with O(1) extra memory.
 * 
 * Custom Judge:
 * 
 * The judge will test your solution with the following code:
 * 
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * 
 * int k = removeDuplicates(nums); // Calls your implementation
 * 
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements
 * of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 * Example 2:
 * 
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements
 * of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are
 * underscores).
 * 
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._82.RemoveDuplicatesFromSortedListII_82}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p>
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 *              <p>
 *              <p>
 *              Company Tags
 *              -----
 * @Baidu
 * @Bloomberg
 * @Facebook
 * @Google
 *         <p>
 *         -----
 * @Editorial
 *            <p>
 *            <p>
 *            -----
 * @OptimalSolution {@link }
 */

public class RemoveDuplicatesFromSortedArrayII_80 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[] { 1, 1, 1, 2, 2, 3 }, new int[] { 1, 1, 2, 2, 3 }, 5));
        tests.add(test(new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 }, new int[] { 0, 0, 1, 1, 2, 3, 3 }, 7));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int[] expectedArray, int expected) {
        CommonMethods.printTest(new String[] { "Grid", "Expected", "ExpectedArray" }, true, grid, expected,
                expectedArray);

        int output = 0;
        int[] outputArray;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        outputArray = Arrays.copyOf(grid, grid.length);
        output = solution.removeDuplicates(outputArray);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        int i = 0;
        while (i < output) {
            pass &= outputArray[i] == expectedArray[i];
            i++;
        }
        finalPass &= pass;

        CommonMethods.printTest(new String[] { "Output", "OutputArray", "Pass" }, false, output,
                Arrays.copyOf(outputArray, output), pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            // REMOVE AND SKIP AT THE LENGTH OF 2
            int k = 0; // the final length of the result array

            for (int n : nums) {

                // if we have less than 2 elements in the result array, then accept the current
                // element since we can keep 2 duplicate chars at max
                // or if the current character is different then last 2 elements (last two
                // elements can be same) then accept the current character
                if (k < 2 || n != nums[k - 2]) {

                    nums[k++] = n;
                }
            }
            return k;
        }
    }

    static class Solution_Generic {
        public int removeDuplicates(int[] nums, int atMost) {
            // REMOVE AND SKIP AT THE LENGTH OF 2
            int k = 0; // the final length of the result array

            for (int n : nums) {

                // if we have less than 2 elements in the result array, then accept the current
                // element since we can keep 2 duplicate chars at max
                // or if the current character is different then last 2 elements (last two
                // elements can be same) then accept the current character
                if (k < atMost || n != nums[k - atMost]) {

                    nums[k++] = n;
                }
            }
            return k;
        }
    }
}
