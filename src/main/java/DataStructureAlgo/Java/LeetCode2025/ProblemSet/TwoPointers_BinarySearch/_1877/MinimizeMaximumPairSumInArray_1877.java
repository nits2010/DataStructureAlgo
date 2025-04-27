package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1877;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/4/2025
 * Question Title: 1877. Minimize Maximum Pair Sum in Array
 * Link: https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/description/
 * Description: The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
 * <p>
 * For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
 * Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
 * <p>
 * Each element of nums is in exactly one pair, and
 * The maximum pair sum is minimized.
 * Return the minimized maximum pair sum after optimally pairing up the elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,5,2,3]
 * Output: 7
 * Explanation: The elements can be paired up into pairs (3,3) and (5,2).
 * The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.
 * Example 2:
 * <p>
 * Input: nums = [3,5,4,2,4,6]
 * Output: 8
 * Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
 * The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 2 <= n <= 105
 * n is even.
 * 1 <= nums[i] <= 105
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
 * @Greedy
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/editorial
 * -----
 * @OptimalSolution {@link }
 */

public class MinimizeMaximumPairSumInArray_1877 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 5, 2, 3}, 7));
        tests.add(test(new int[]{3, 5, 4, 2, 4, 6}, 8));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {

        CommonMethods.printTest(new String[]{"Nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.minPairSum(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int minPairSum(int[] nums) {
            int min = 0;
            Arrays.sort(nums);
            int i = 0, n = nums.length, j = n - 1;

            while (i < j) {
                min = Math.max(min, nums[i] + nums[j]);
                i++;
                j--;
            }
            return min;
        }
    }
}
