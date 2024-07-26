package DataStructureAlgo.Java.LeetCode2025;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 7/22/2024
 * Description: https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/description/
 * You are given a 0-indexed array of integers nums.
 * <p>
 * A prefix nums[0..i] is sequential if, for all 1 <= j <= i, nums[j] = nums[j - 1] + 1. In particular, the prefix consisting only of nums[0] is sequential.
 * <p>
 * Return the smallest integer x missing from nums such that x is greater than or equal to the sum of the longest sequential prefix.
 * <p>
 * sequential will always start from 0th index
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,2,5]
 * Output: 6
 * Explanation: The longest sequential prefix of nums is [1,2,3] with a sum of 6. 6 is not in the array, therefore 6 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
 * Example 2:
 * <p>
 * Input: nums = [3,4,5,1,12,14,13]
 * Output: 15
 * Explanation: The longest sequential prefix of nums is [3,4,5] with a sum of 12. 12, 13, and 14 belong to the array while 15 does not. Therefore 15 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
 */
public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 2, 5}, 6);
        test(new int[]{3, 4, 5, 1, 12, 14, 13}, 15);
        test(new int[]{4, 5, 6, 7, 8, 8, 9, 4, 3, 2, 7}, 30);
        test(new int[]{29, 30, 31, 32, 33, 34, 35, 36, 37}, 297);
        test(new int[]{14, 9, 6, 9, 7, 9, 10, 4, 9, 9, 4, 4}, 15);


    }

    private static void test(int[] input, int expectedOutput) {
        Solution solution = new Solution();
        int result = solution.missingIntegerNoDS(input);
        boolean output = result == expectedOutput;
        System.out.println("\n Input :" + Arrays.toString(input) + "\n expectedOutput : " + expectedOutput + " returned output : " + result + " result : " + output);

    }

    static class Solution {
        /**
         *
         * https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/submissions/1329847528/
         *
         * @param nums
         * @return
         */
        public int missingInteger(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;

            Set<Integer> set = new HashSet<>();
            int currentprefixSum = nums[0];
            int i = 1;
            for (int num : nums)
                set.add(num);
            while (i < nums.length) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentprefixSum = currentprefixSum + nums[i];
                    i++;
                } else {
                    break;
                }

            }

            System.out.println("currentprefixSum =" + currentprefixSum);
            while (set.contains(currentprefixSum))
                currentprefixSum++;
            return currentprefixSum;

        }

        /** https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/solutions/5518850/simple-solution-beats-100/
         * # Intuition
         * Since the prefix sum can only be start from the first element, its sufficient to check that where this prefix sum is end.
         * Post finding the end and sum of them, we can easily linear search in the original array for missing numbers
         *
         * # Approach
         * Start from first element and keep adding it until the condition satisfied. If not, break. Keep sum handy.
         * Post that search in original array and keep incrementing the sum until not found.
         *
         * # Complexity
         * - Time complexity:
         * O(n)
         *
         * - Space complexity:
         * O(1)
         *
         * https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/submissions/1329858352/
         * 0 ms
         * Beats 100.00%
         * @param nums
         * @return
         */
        public int missingIntegerNoDS(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;


            int currentprefixSum = nums[0];
            int i = 1;

            while (i < nums.length) {
                if (nums[i] == nums[i - 1] + 1) {
                    currentprefixSum = currentprefixSum + nums[i];
                    i++;
                } else {
                    break;
                }

            }

          while (contains(nums, currentprefixSum))
                currentprefixSum++;
            return currentprefixSum;

        }

        private boolean contains(int[] nums, int num) {
            for (int candidate : nums) {
                if (candidate == num)
                    return true;
            }
            return false;
        }

    }
}




