package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._2337;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 12/5/2024
 * Question Title: 2337. Move Pieces to Obtain a String
 * Link: https://leetcode.com/problems/move-pieces-to-obtain-a-string/description/
 * Description: You are given two strings start and target, both of length n. Each string consists only of the characters 'L', 'R', and '_' where:
 *
 * The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space directly to its right.
 * The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
 * Return true if it is possible to obtain the string target by moving the pieces of the string start any number of times. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: start = "_L__R__R_", target = "L______RR"
 * Output: true
 * Explanation: We can obtain the string target from start by doing the following moves:
 * - Move the first piece one step to the left, start becomes equal to "L___R__R_".
 * - Move the last piece one step to the right, start becomes equal to "L___R___R".
 * - Move the second piece three steps to the right, start becomes equal to "L______RR".
 * Since it is possible to get the string target from start, we return true.
 * Example 2:
 *
 * Input: start = "R_L_", target = "__LR"
 * Output: false
 * Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
 * After that, no pieces can move anymore, so it is impossible to obtain the string target from start.
 * Example 3:
 *
 * Input: start = "_R", target = "R_"
 * Output: false
 * Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the string target from start.
 *
 *
 * Constraints:
 *
 * n == start.length == target.length
 * 1 <= n <= 105
 * start and target consist of the characters 'L', 'R', and '_'.
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
 * @TwoPointers
 * @String
 *
 * <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MovePiecesToObtainAString_2337 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("_L__R__R_", "L______RR", true));
        tests.add(test("R_L_", "__LR", false));
        tests.add(test("_R", "R_", false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String start, String target, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"start", "target", "Expected"}, true, start, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        //add logic here
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public boolean canChange(String start, String target) {
            int n = start.length();
            int si = 0, ti = 0;

            while (si < n || ti < n) {

                //skip all '_' in start
                while (si < n && start.charAt(si) == '_')
                    si++;

                //skip all '_' in start
                while (ti < n && target.charAt(ti) == '_')
                    ti++;

                // if both reached end they are same
                if (si == n && ti == n)
                    return true;

                // if one of them reached the end they are NOT same
                if (si == n || ti == n) {
                    return false;
                }

                char s = start.charAt(si);
                char t = target.charAt(ti);


                //the character should match post skipping '_'
                if (s != t)
                    return false;

                // apply movement rule

                // if si is at 'R' then it can move right, but it cannot go beyond ti, hence if its already beyond ti, not possible
                if (s == 'R' && si > ti)
                    return false;

                // if si is at 'L' then it can move to the left but cannot be behind ti
                if (s == 'L' && si < ti)
                    return false;


                si++;
                ti++;

            }

            return si == n && ti == n;

        }
    }

}
