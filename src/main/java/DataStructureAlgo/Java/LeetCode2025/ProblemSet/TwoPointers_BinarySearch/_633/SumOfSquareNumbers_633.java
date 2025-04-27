package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._633;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/4/2025
 * Question Title:633. Sum of Square Numbers
 * Link: https://leetcode.com/problems/sum-of-square-numbers/description/
 * Description: Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 * <p>
 * Input: c = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= c <= 231 - 1
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
 * @Math
 * @TwoPointers
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @LinkedIn <p>
 * -----
 * @Editorial https://leetcode.com/problems/sum-of-square-numbers/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SumOfSquareNumbers_633 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(5, true));
        tests.add(test(3, false));
        tests.add(test(4, true));
        tests.add(test(0, true));
        tests.add(test(2147483600, true));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int c, boolean expected) {
        CommonMethods.printTest(new String[]{"c", "Expected"}, true, c, expected);

        boolean output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.judgeSquareSum(c);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Two Pointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionPerfectSquare solutionPerfectSquare = new SolutionPerfectSquare();
        output = solutionPerfectSquare.judgeSquareSum(c);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Perfect Square", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public boolean judgeSquareSum(int c) {

            long i = 0, j = (long) (Math.floor(Math.sqrt(c)));
            while (i <= j) {
                long square = i * i + j * j;
                if (square == c)
                    return true;
                if (square > c)
                    j--;
                else
                    i++;
            }
            return false;
        }
    }


    static class SolutionPerfectSquare {
        public boolean judgeSquareSum(int c) {
            int n = (int) Math.sqrt(c);
            for (int a = 0; a <= n; a++) {
                double b = Math.sqrt(c - a * a);
                if (b == (int) b) {
                    return true;
                }
            }
            return false;
        }
    }
}
