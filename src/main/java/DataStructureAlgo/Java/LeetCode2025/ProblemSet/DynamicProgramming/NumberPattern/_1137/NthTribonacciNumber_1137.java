package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming.NumberPattern._1137;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/20/2024
 * Question Category: 1137. N-th Tribonacci Number
 * Description: https://leetcode.com/problems/n-th-tribonacci-number/description/
 * The Tribonacci sequence Tn is defined as follows:
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * <p>
 * Given n, return the value of Tn.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 * <p>
 * Input: n = 25
 * Output: 1389537
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.TribonacciNumber}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode.FibonacciNumber}
 * DP-BaseProblem {@link DataStructureAlgo.Java.LeetCode.FibonacciNumber}
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @Math
 * @DynamicProgramming
 * @Memoization <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Editorial
 */
public class NthTribonacciNumber_1137 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(4, 4);
        test &= test(25, 1389537);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int n, int expected) {
        System.out.println("--------------------------------------------");
        System.out.println("n=" + n);
        System.out.println("expected=" + expected);
        Solution sol = new Solution();
        int actual = sol.tribonacci(n);
        boolean pass = actual == expected;
        System.out.println("Result: " + (pass ? "Passed" : "Failed"));
        return pass;
    }

    static class Solution {
        public int tribonacci(int n) {
            int t0 = 0, t1 = 1, t2 = 1;
            if (n == 0)
                return 0;
            if (n <= 2)
                return 1;

            for (int i = 3; i <= n; i++) {
                int t = t0 + t1 + t2;
                t0 = t1;
                t1 = t2;
                t2 = t;
            }
            return t2;
        }
    }
}
