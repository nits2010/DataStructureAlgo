package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.ConsecutiveOnes._485;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 9/5/2024
 * Question Category: 485. Max Consecutive Ones
 * Description:https://leetcode.com/problems/max-consecutive-ones/description/
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
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
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Array <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Google
 * @Apple <p><p>
 * @Editorial
 */
public class MaxConsecutiveOnes_485 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 1, 0, 1, 1, 1}, 3);
        test &= test(new int[]{1, 0, 1, 1, 0, 1}, 2);
        test &= test(new int[]{1, 1, 1, 1, 1}, 5);

        CommonMethods.printAllTestOutCome(test);


    }

    private static boolean test(int[] nums, int expected) {
        return new Solution().findMaxConsecutiveOnes(nums) == expected;
    }


    static class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;

            int max = 0;
            int count = 0;
            for (int n : nums) {
                if (n == 1) {
                    count++;

                } else if (n == 0) {
                    max = Math.max(count, max);
                    count = 0;
                }
            }
            max = Math.max(count, max);
            return max;
        }

    }
}
