package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._2036;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._1911.MaximumAlternatingSubsequenceSum_1911;

import java.util.*;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._baseProblems.maximumSubArray.Kadens._53.MaximumSubarray_53;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.Knapsack;

/**
 * Author: Nitin Gupta
 * Date: 11/7/2024
 * Question Category: 2036 - Maximum Alternating Subarray Sum
 * Description: https://leetcode.com/problems/maximum-alternating-subarray-sum/description/
 * https://leetcode.ca/2021-10-20-2036-Maximum-Alternating-Subarray-Sum/
 * A subarray of a 0-indexed integer array is a contiguous non-empty sequence of elements within an array.
 * <p>
 * The alternating subarray sum of a subarray that ranges from index i to j (inclusive, 0 <= i <= j < nums.length) is nums[i] - nums[i+1] + nums[i+2] - ... +/- nums[j].
 * <p>
 * Given a 0-indexed integer array nums, return the maximum alternating subarray sum of any subarray of nums.
 * <p>
 * Examples:
 * Example 1:
 * <p>
 * Input: nums = [3,-1,1,2]
 * Output: 5
 * Explanation:
 * The subarray [3,-1,1] has the largest alternating subarray sum.
 * The alternating subarray sum is 3 - (-1) + 1 = 5.
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2]
 * Output: 2
 * Explanation:
 * The subarrays [2], [2,2,2], and [2,2,2,2,2] have the largest alternating subarray sum.
 * The alternating subarray sum of [2] is 2.
 * The alternating subarray sum of [2,2,2] is 2 - 2 + 2 = 2.
 * The alternating subarray sum of [2,2,2,2,2] is 2 - 2 + 2 - 2 + 2 = 2.
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation:
 * There is only one non-empty subarray, which is [1].
 * The alternating subarray sum is 1.
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link MaximumAlternatingSubsequenceSum_1911}
 * DP-BaseProblem {@link MaximumSubarray_53.Kadens} {@link Knapsack}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @LeetCodeLockedProblem
 * @PremimumQuestion
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Amazon <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion {@link KadanesAlgorithm}
 */
public class MaximumAlternatingSubArraySum_2036 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{3, -1, 1, 2}, 5));
        tests.add(test(new int[]{2, 2, 2, 2, 2}, 2));
        tests.add(test(new int[]{1}, 1));
        tests.add(test(new int[]{1, 5, -6, 5, -9, -2, 11, -8, 9, 12, -6}, 28));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, long expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "Expected"}, true, nums, expected);

        long output;
        boolean pass, finalPass = true;

        BruteForce bruteForce = new BruteForce();
        output = bruteForce.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BruteForce", "Expected"}, false, output, pass ? "Pass" : "Fail");


        KadanesAlgorithm kadanesAlgorithm = new KadanesAlgorithm();
        output = kadanesAlgorithm.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"KadaneAlgorithm", "Expected"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    /**
     * Time: O(n^3)
     * Space: O(1)
     */
    static class BruteForce {

        public long maxAlternatingSum(int[] nums) {

            long maxSum = Long.MIN_VALUE;
            int n = nums.length;

            for (int start = 0; start < n; start++) {
                for (int end = start; end < n; end++) {
                    maxSum = Math.max(maxSum, maxSubArray(nums, start, end));
                }
            }
            return maxSum;

        }

        private long maxSubArray(int[] nums, int start, int end) {
            int sign = 1;
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum += (long) sign * nums[i];
                sign *= -1;
            }
            return sum;
        }
    }


    /**
     * instead of copy and change the array, we will change the values on the fly
     * O(n)
     */
    static class KadanesAlgorithm {

        public long maxAlternatingSum(int[] nums) {

            long maxSum = 0;

            long currentEvenSum = 0;
            long currentOddSum = 0;

            for (int i = 0; i < nums.length; i++) {

                //if this is even index,
                if (i % 2 == 0) {
                    currentEvenSum = Math.max(currentEvenSum + nums[i], nums[i]);
                    currentOddSum -= nums[i];

                } else {
                    currentEvenSum -= nums[i];
                    currentOddSum = Math.max(nums[i], currentOddSum + nums[i]);
                }
                maxSum = Math.max(maxSum, Math.max(currentEvenSum, currentOddSum));

            }
            return maxSum;

        }
    }


}
