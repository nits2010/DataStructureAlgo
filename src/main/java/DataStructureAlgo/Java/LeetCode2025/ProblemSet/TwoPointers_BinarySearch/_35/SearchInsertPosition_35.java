package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._35;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/20/2025
 * Question Title: 35. Search Insert Position
 * Link:
 * Description: Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums contains distinct values sorted in ascending order.
 * -104 <= target <= 104
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
 * @easy
 * @Array
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Adobe
 * @Google
 * @Bloomberg <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SearchInsertPosition_35 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 3, 5, 6}, 5, 2));
        tests.add(test(new int[]{1, 3, 5, 6}, 2, 1));
        tests.add(test(new int[]{1, 3, 5, 6}, 7, 4));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int target, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "target", "Expected"}, true, nums, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().searchInsert(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int searchInsert(int[] nums, int target) {
            int low = 0, high = nums.length - 1;

            while (low <= high) {
                int mid = (low + high) >>> 1;

                if (nums[mid] == target)
                    return mid;

                if (nums[mid] > target)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return low;
        }
    }
}
