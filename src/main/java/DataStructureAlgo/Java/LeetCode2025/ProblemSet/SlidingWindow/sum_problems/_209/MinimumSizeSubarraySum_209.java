package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.sum_problems._209;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/17/2024
 * Question Category: 209. Minimum Size Subarray Sum
 * Description: https://leetcode.com/problems/minimum-size-subarray-sum/description/
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray
 * whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 * <p>
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 * <p>
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @BinarySearch
 * @SlidingWindow
 * @PrefixSum
 *
 * <p><p>
 * Company Tags
 * -----
 *
 * @Facebook
 * @Amazon
 * @Microsoft
 * @Bloomberg
 * @GoldmanSachs
 * @Apple
 * @ByteDance
 * @Google
 * @Oracle
 * @SAP <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MinimumSizeSubarraySum_209 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(7, new int[]{2, 3, 1, 2, 4, 3}, 2));
        tests.add(test(4, new int[]{1, 4, 4}, 1));
        tests.add(test(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int target, int[] nums, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "target", "Expected"}, true, nums, target, expected);

        int output;
        boolean pass, finalPass = true;
        Solution_SlidingWindow solutionSlidingWindow = new Solution_SlidingWindow();
        output = solutionSlidingWindow.minSubArrayLen(target, nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingSlidingWindow", "Pass"}, false, output, pass ? "Pass" : "Fail");


        //add logic here

        return finalPass;

    }

    static class Solution_SlidingWindow {
        public int minSubArrayLen(int target, int[] nums) {
            int minSize = Integer.MAX_VALUE;

            int left = 0, right = 0, n = nums.length;
            long sum = 0;
            while (right < n) {

                //expand till sum < target
                while (sum < target && right < n) {
                    sum += nums[right++];
                }

                //shirnk while sum >=target
                while (sum >= target && left < right) {
                    minSize = Math.min(right - left, minSize);
                    sum -= nums[left++];
                }
            }

            return minSize == Integer.MAX_VALUE ? 0 : minSize;
        }
    }
}
