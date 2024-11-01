package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.NumberPattern._746;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/19/2024
 * Question Category: 746. Min Cost Climbing Stairs
 * Description: https://leetcode.com/problems/min-cost-climbing-stairs/description/
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 * <p>
 * You can either start from the step with index 0, or the step with index 1.
 * <p>
 * Return the minimum cost to reach the top of the floor.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * Example 2:
 * <p>
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.NumberPattern._70.ClimbingStairs_70}
 * extension {@link DataStructureAlgo.Java.LeetCode.FibonacciNumber}
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Adobe
 * @Bloomberg
 * @Yahoo <p><p>
 * -----
 * @Editorial
 */
public class MinCostClimbingStairs_746 {

    public static void main(String[] args) {
        boolean test = true;
        test &= testCase(new int[]{10, 15, 20}, 15);
        test &= testCase(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}, 6);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean testCase(int[] cost, int expected) {
        System.out.println("--------------------------------------------");
        System.out.println("Cost: " + Arrays.toString(cost) + ", Expected: " + expected);

        Solution sol = new Solution();
        int actual = sol.minCostClimbingStairs(cost);
        boolean pass = actual == expected;
        System.out.println("Result: " + (pass ? "Passed" : "Failed"));
        return pass;
    }

    static class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int first = cost[0];
            int second = cost[1];

            if (cost.length == 2)
                return Math.min(first, second);

            for (int i = 2; i < cost.length; i++) {
                int minCost = cost[i] + Math.min(first, second);
                first = second;
                second = minCost;
            }

            return Math.min(first, second);
        }
    }
}
