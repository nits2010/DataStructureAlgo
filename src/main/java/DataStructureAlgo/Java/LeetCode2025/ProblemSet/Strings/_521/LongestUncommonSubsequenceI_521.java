package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._521;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 521. Longest Uncommon Subsequence I
 * Link: https://leetcode.com/problems/longest-uncommon-subsequence-i/description/
 * Description: Given two strings a and b, return the length of the longest uncommon subsequence between a and b. If no such uncommon subsequence exists, return -1.
 *
 * An uncommon subsequence between two strings is a string that is a subsequence of exactly one of them.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "aba", b = "cdc"
 * Output: 3
 * Explanation: One longest uncommon subsequence is "aba" because "aba" is a subsequence of "aba" but not "cdc".
 * Note that "cdc" is also a longest uncommon subsequence.
 * Example 2:
 *
 * Input: a = "aaa", b = "bbb"
 * Output: 3
 * Explanation: The longest uncommon subsequences are "aaa" and "bbb".
 * Example 3:
 *
 * Input: a = "aaa", b = "aaa"
 * Output: -1
 * Explanation: Every subsequence of string a is also a subsequence of string b. Similarly, every subsequence of string b is also a subsequence of string a. So the answer would be -1.
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 100
 * a and b consist of lower-case English letters.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 * @String
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestUncommonSubsequenceI_521 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("aba", "cdc", 3));
        tests.add(test("aaa", "bbb", 3));
        tests.add(test("aaa", "aaa", -1));
        tests.add(test("aa", "aaa", 3));
        tests.add(test("xyz", "wxyz", 4));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s1, String s2, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"s1", "s2", "Expected"}, true, s1, s2, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.findLUSlength(s1, s2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }
    static class Solution {
        public int findLUSlength(String a, String b) {
            if (a.equals(b)) {
                return -1;
            } else {
                return Math.max(a.length(), b.length());
            }
        }
    }
}
