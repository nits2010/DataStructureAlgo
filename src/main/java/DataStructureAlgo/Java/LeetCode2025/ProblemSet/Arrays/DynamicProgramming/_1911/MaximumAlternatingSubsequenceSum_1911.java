package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming._1911;

import DataStructureAlgo.Java.nonleetcode.Knapsack;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/6/2024
 * Question Category: 1911. Maximum Alternating Subsequence Sum
 * Description: https://leetcode.com/problems/maximum-alternating-subsequence-sum/description/
 * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd indices.
 * <p>
 * For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
 * Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).
 * <p>
 * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,2,5,3]
 * Output: 7
 * Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
 * Example 2:
 * <p>
 * Input: nums = [5,6,7,8]
 * Output: 8
 * Explanation: It is optimal to choose the subsequence [8] with alternating sum 8.
 * Example 3:
 * <p>
 * Input: nums = [6,2,1,2,4,5]
 * Output: 10
 * Explanation: It is optimal to choose the subsequence [6,1,5] with alternating sum (6 + 5) - 1 = 10.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link Knapsack}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion {@link DynamicProgramming.BottomUpSpaceOptimized2} {@link DynamicProgramming.BottomUpSpaceOptimized }
 */
public class MaximumAlternatingSubsequenceSum_1911 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{4, 2, 5, 3}, 7));
        tests.add(test(new int[]{5, 6, 7, 8}, 8));
        tests.add(test(new int[]{6, 2, 1, 2, 4, 5}, 10));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, long expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "Expected"}, true, nums, expected);

        long output;
        boolean pass, finalPass = true;

        Recursion.Solution recursion = new Recursion.Solution();
        output = recursion.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Recursion", "Expected"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.TopDown topDown = new DynamicProgramming.TopDown();
        output = topDown.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"TopDown", "Expected"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.BottomUp bottomUp = new DynamicProgramming.BottomUp();
        output = bottomUp.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BottomUp", "Expected"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.BottomUp2 bottomUp2 = new DynamicProgramming.BottomUp2();
        output = bottomUp2.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BottomUp2", "Expected"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.BottomUpSpaceOptimized bottomUpSpaceOptimized = new DynamicProgramming.BottomUpSpaceOptimized();
        output = bottomUpSpaceOptimized.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"bottomUpSpaceOptimized", "Expected"}, false, output, pass ? "Pass" : "Fail");


        DynamicProgramming.BottomUpSpaceOptimized2 bottomUpSpaceOptimized2 = new DynamicProgramming.BottomUpSpaceOptimized2();
        output = bottomUpSpaceOptimized2.maxAlternatingSum(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"bottomUpSpaceOptimized2", "Expected"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Recursion {
        static class Solution {
            public long maxAlternatingSum(int[] nums) {
                return dfs(nums, nums.length, 0, 0);
            }

            private long dfs(int[] nums, int n, int i, int even) {

                if (i >= n)
                    return 0L;


                // don't delete, choose current
                long choose = (even == 0 ? nums[i] : -nums[i]) + dfs(nums, n, i + 1, 1 - even);

                // delete, don't choose current
                long notChoose = dfs(nums, n, i + 1, even);

                return Math.max(choose, notChoose);

            }
        }

    }

    static class DynamicProgramming {

        static class TopDown {
            public long maxAlternatingSum(int[] nums) {
                Long[][] dp = new Long[nums.length][2];
                return dfs(nums, nums.length, 0, 0, dp);
            }

            private long dfs(int[] nums, int n, int i, int even, Long[][] dp) {

                if (i >= n)
                    return 0L;

                if (dp[i][even] != null)
                    return dp[i][even];

                // don't delete, choose current
                long choose = (even == 0 ? nums[i] : -nums[i]) + dfs(nums, n, i + 1, 1 - even, dp);

                // delete, don't choose current
                long notChoose = dfs(nums, n, i + 1, even, dp);

                return dp[i][even] = Math.max(choose, notChoose);

            }
        }

        static class BottomUp {
            public long maxAlternatingSum(int[] nums) {
                int n = nums.length;
                long[][] dp = new long[n + 1][2];

                for (int i = n - 1; i >= 0; i--) {

                    dp[i][0] = Math.max(dp[i + 1][0], nums[i] + dp[i + 1][1]);
                    dp[i][1] = Math.max(dp[i + 1][1], -nums[i] + dp[i + 1][0]);

                }
                return dp[0][0];
            }

        }

        static class BottomUp2 {
            public long maxAlternatingSum(int[] nums) {
                int n = nums.length;
                long[][] dp = new long[n][2];
                dp[0][0] = nums[0];
                for (int i = 1; i < n; i++) {

                    dp[i][0] = Math.max(dp[i - 1][0], nums[i] + dp[i - 1][1]);
                    dp[i][1] = Math.max(dp[i - 1][1], -nums[i] + dp[i - 1][0]);

                }
                return dp[n - 1][0];
            }

        }

        static class BottomUpSpaceOptimized {
            public long maxAlternatingSum(int[] nums) {
                int n = nums.length;
                long even, odd = 0;
                even = nums[0];
                for (int i = 1; i < n; i++) {

                    even = Math.max(even, nums[i] + odd);
                    odd = Math.max(odd, -nums[i] + even);

                }
                return Math.max(even, odd);
            }

        }


        static class BottomUpSpaceOptimized2 {
            public long maxAlternatingSum(int[] nums) {
                int n = nums.length;
                long sum = nums[0];

                for (int i = 1; i < n; i++) {
                    int even = nums[i - 1];
                    int odd = nums[i];

                    sum += Math.max(odd - even, 0);
                }
                return sum;
            }

        }
    }
}
