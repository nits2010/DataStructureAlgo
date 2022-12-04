package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 13/10/19
 * Description: https://leetcode.com/problems/largest-number-at-least-twice-of-others/
 * 747. Largest Number At Least Twice of Others
 * In a given integer array nums, there is always exactly one largest element.
 * <p>
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 * <p>
 * If it is, return the index of the largest element, otherwise return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3, 6, 1, 0]
 * Output: 1
 * Explanation: 6 is the largest integer, and for every other number in the array x,
 * 6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1, 2, 3, 4]
 * Output: -1
 * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 * <p>
 * <p>
 * Note:
 * <p>
 * nums will have a length in the range [1, 50].
 * Every nums[i] will be an integer in the range [0, 99].
 */
public class LargestNumberAtLeastTwiceOthers {

    public static void main(String[] args) {
        boolean test = test(new int[]{3, 6, 1, 0}, 1)
                && test(new int[]{1, 2, 3, 4}, -1)
                && test(new int[]{1}, 0)
                && test(new int[]{0, 0, 0, 1}, 3)
                && test(new int[]{3, 6, 1, 6}, 1);
        System.out.println("\nTests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[] nums, int expected) {
        System.out.println("\nNums :" + GenericPrinter.toString(nums));

        System.out.println("Expected :" + expected);
        int obtained = dominantIndex(nums);
        System.out.println("Obtained :" + obtained);

        return expected == obtained;
    }

    /**
     * O(n)/O(1)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Largest Number At Least Twice of Others.
     * Memory Usage: 35.1 MB, less than 100.00% of Java online submissions for Largest Number At Least Twice of Others.
     *
     * @param nums
     * @return
     */
    public static int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        int max = -1;
        int index = -1;

        for (int i = 0; i < nums.length; i++)
            if (max < nums[i]) {
                max = nums[i];
                index = i;
            }

        for (int i = 0; i < nums.length; i++)
            if (i != index && max != nums[i] && max < nums[i] * 2)
                return -1;


        return index;
    }
}
