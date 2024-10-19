package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.FibonacciPattern._70;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/19/2024
 * Question Category: 70. Climbing Stairs
 * Description: https://leetcode.com/problems/climbing-stairs/description/
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.ClimbingStairs2Step}
 * Similar {@link DataStructureAlgo.Java.LeetCode.FibonacciNumber}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @DynamicProgramming
 * @Math
 * @Memoization <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Expedia
 * @Microsoft
 * @Uber
 * @Google
 * @Adobe
 * @Alibaba
 * @Apple
 * @Baidu
 * @Bloomberg
 * @Facebook
 * @GoldmanSachs
 * @Huawei
 * @LinkedIn
 * @Microsoft
 * @Oracle
 * @TripAdvisor
 * @WalmartLabs
 * @Zulily <p>
 * -----
 * @Editorial
 */
public class ClimbingStairs_70 {

    public static void main(String[] args) {
        boolean test = true;
        test &= testCase(2, 2);
        test &= testCase(3, 3);
        test &= testCase(4, 5);
        test &= testCase(5, 8);
        test &= testCase(20, 10946);
        CommonMethods.printResult(test);
    }

    private static boolean testCase(int n, int expected) {
        System.out.println("--------------------------------------------");
        System.out.println("n=" + n);
        System.out.println("expected=" + expected);
        Solution solution = new Solution();
        int output = solution.climbStairs(n);
        boolean pass = output == expected;

        System.out.println("output=" + output + " pass=" + (pass ? "Passed" : "Failed"));
        return pass;
    }

    static class Solution {
        public int climbStairs(int n) {
            int a = 1, b = 2;
            int c = 0;

            if (n <= 2)
                return n;
            for (int i = 1; i <= n - 2; i++) {

                c = a + b;
                a = b;
                b = c;

            }

            return c;
        }
    }
}
