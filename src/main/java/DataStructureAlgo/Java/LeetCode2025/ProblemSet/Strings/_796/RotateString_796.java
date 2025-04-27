package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._796;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/3/2024
 * Question Category: 796. Rotate String
 * Description:https://leetcode.com/problems/rotate-string/description/
 * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
 * <p>
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 * <p>
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcde", goal = "cdeab"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "abcde", goal = "abced"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, goal.length <= 100
 * s and goal consist of lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @String
 * @StringMatching
 * @easy <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class RotateString_796 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test("abcde", "cdeab", true));
        tests.add(test("abcde", "abced", false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String input, String rotated, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Input", "Expected"}, true, input, expected);

        boolean output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.rotateString(input, rotated);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Solution {

        public boolean rotateString(String s, String goal) {
            // Check if the lengths are different
            if (s.length() != goal.length()) return false;

            // Create a new string by concatenating 's' with itself
            String doubledString = s + s;

            // Use contains to search for 'goal' in 'doubledString'
            // If contains return true, 'goal' is a substring
            return doubledString.contains(goal);
        }
    }
}
