package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2938;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date:15/10/24
 * Question Category: 2938. Separate Black and White Balls
 * Description: https://leetcode.com/problems/separate-black-and-white-balls/description/?envType=daily-question&envId=2024-10-15
 * There are n balls on a table, each ball has a color black or white.
 * <p>
 * You are given a 0-indexed binary string s of length n, where 1 and 0 represent black and white balls, respectively.
 * <p>
 * In each step, you can choose two adjacent balls and swap them.
 * <p>
 * Return the minimum number of steps to group all the black balls to the right and all the white balls to the left.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "101"
 * Output: 1
 * Explanation: We can group all the black balls to the right in the following way:
 * - Swap s[0] and s[1], s = "011".
 * Initially, 1s are not grouped together, requiring at least 1 step to group them to the right.
 * Example 2:
 * <p>
 * Input: s = "100"
 * Output: 2
 * Explanation: We can group all the black balls to the right in the following way:
 * - Swap s[0] and s[1], s = "010".
 * - Swap s[1] and s[2], s = "001".
 * It can be proven that the minimum number of steps needed is 2.
 * Example 3:
 * <p>
 * Input: s = "0111"
 * Output: 0
 * Explanation: All the black balls are already grouped to the right.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n == s.length <= 105
 * s[i] is either '0' or '1'.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @TwoPointers
 * @Greedy
 * @String <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class SeparateBlackAndWhiteBalls_2938 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("101", 1);
        test &= test("100", 2);
        test &= test("0111", 0);
        test &= test("011010", 5);
        CommonMethods.printAllTestOutCome(test);

    }

    private static boolean test(String s, int expected) {
        System.out.println("--------------------------------");
        System.out.println("Input:" + s + " expected:" + expected);
        long output;
        boolean pass, finalPass = true;

        Solution_CountAndSweep solutionCountAndSweep = new Solution_CountAndSweep();
        output = solutionCountAndSweep.minimumSteps(s);
        pass = output == expected;
        System.out.println("CountAndSweep: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        SolutionSweeps solutionSweeps = new SolutionSweeps();
        output = solutionSweeps.minimumSteps(s);
        pass = output == expected;
        System.out.println("Sweep: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        return finalPass;
    }

    static class Solution_CountAndSweep {
        public long minimumSteps(String s) {

            // only opposite number can swap either we choose 0 or 1.
            // since we need to put 0 on left and 1 on right.
            // once we put all 0 on left, all 1 will automatically will be on right
            // since every 0 needs to be swap with every 1 on its left side ( pushing right 0 to left)
            // similarly every 1 needs to be swap with every 0 on its rigth side ( pushing left 1 to right)
            // this means that, if we run from any one direction(say right), then we can find all
            // 0 that needs to be push to left and all 1 came in between will require a swap per zero.

            //example: 0 1 1 0 1 0
            // if we start from right, we see a 0 that needs to be push, now all one comes on left side of
            // it will require a swap, the more zero we encounter, the more swap it require per 1.
            // last 0 (5th index) needs to swap with 1(4th index), 2nd index, 1st index -> 3 times
            // second last 0 needs to swap with 2nd and 1st index. That makes total 5 swaps.


            char[] balls = s.toCharArray();
            int n = s.length();
            int i = 0, j = n - 1;
            long swaps = 0;
            int zero = 0;
            while (i <= j) {

                if (balls[j] == '0')
                    zero++; //count '0'

                if (balls[j] == '1') {
                    //we found a 1, now ll the zero on right side of it (count = zero) needs to swap with it
                    //each will take zero * 1 swap = count(zero) swaps
                    swaps += zero;
                }
                j--;
            }
            return swaps;

        }
    }

    static class SolutionSweeps {
        public long minimumSteps(String s) {


            char[] balls = s.toCharArray();
            int n = s.length();

            long swaps = 0;
            int lastZeroAt = -1;
            for (int x = 0; x < n; x++) {
                if (balls[x] == '0') {
                    swaps += x - lastZeroAt - 1;
                    lastZeroAt++;
                }
            }
            return swaps;

        }
    }
}
