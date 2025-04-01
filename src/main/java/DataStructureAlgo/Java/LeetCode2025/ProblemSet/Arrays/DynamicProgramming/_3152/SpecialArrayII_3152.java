package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming._3152;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming._baseProblems.subsequence.LIS._300.LongestIncreasingSubsequence_300;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 09/12/24
 * Question Title: 3152. Special Array II
 * Link: https://leetcode.com/problems/special-array-ii/description
 * Description: An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
 *
 * You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that
 * subarray
 *  nums[fromi..toi] is special or not.
 *
 * Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,1,2,6], queries = [[0,4]]
 *
 * Output: [false]
 *
 * Explanation:
 *
 * The subarray is [3,4,1,2,6]. 2 and 6 are both even.
 *
 * Example 2:
 *
 * Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
 *
 * Output: [false,true]
 *
 * Explanation:
 *
 * The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
 * The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link LongestIncreasingSubsequence_300}
 * DP-BaseProblem {@link LongestIncreasingSubsequence_300}
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @BinarySearch
 * @PrefixSum
 * <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */
public class SpecialArrayII_3152 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 4, 1, 2, 6}, new int[][]{{0, 4}}, new boolean[]{false}));
        tests.add(test(new int[]{4, 3, 1, 6}, new int[][]{{0, 2}, {2, 3}}, new boolean[]{false, true}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int [][]queries, boolean [] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "queries", "Expected"}, true, nums, queries, expected);

        boolean [] output;
        boolean pass, finalPass = true;

        Solution_DP solutionDp = new Solution_DP();
        output = solutionDp.isArraySpecial(nums, queries);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"DP", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Solution_DP {
        public boolean[] isArraySpecial(int[] nums, int[][] queries) {
            int n = nums.length;
            int []dp = new int[n]; //length of longest alternative sub-array of parity
            dp[0] = 1;

            for(int i = 1; i<n; i++){

                if((nums[i] & 1) != (nums[i-1]&1))
                    dp[i] = dp[i-1] + 1;
                else
                    dp[i] = 1;
            }

            boolean []result = new boolean[queries.length];
            int r = 0;

            for(int []q : queries){
                int start = q[0];
                int end = q[1];
                int len = end - start + 1;

                result[r++] = dp[end] >= len;
            }
            return result;
        }
    }
}
