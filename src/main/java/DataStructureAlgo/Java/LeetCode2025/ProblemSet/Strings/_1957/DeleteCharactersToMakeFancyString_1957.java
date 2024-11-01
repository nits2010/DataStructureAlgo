package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._1957;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/1/2024
 * Question Category: 1957.md. Delete Characters to Make Fancy String
 * Description: https://leetcode.com/problems/delete-characters-to-make-fancy-string/description/?envType=daily-question&envId=2024-11-01
 * A fancy string is a string where no three consecutive characters are equal.
 * <p>
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * <p>
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leeetcode"
 * Output: "leetcode"
 * Explanation:
 * Remove an 'e' from the first group of 'e's to create "leetcode".
 * No three consecutive characters are equal, so return "leetcode".
 * Example 2:
 * <p>
 * Input: s = "aaabaaaa"
 * Output: "aabaa"
 * Explanation:
 * Remove an 'a' from the first group of 'a's to create "aabaaaa".
 * Remove two 'a's from the second group of 'a's to create "aabaa".
 * No three consecutive characters are equal, so return "aabaa".
 * Example 3:
 * <p>
 * Input: s = "aab"
 * Output: "aab"
 * Explanation: No three consecutive characters are equal, so return "aab".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists only of lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @String <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Wayfair
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class DeleteCharactersToMakeFancyString_1957 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test("leeetcode", "leetcode");
        test &= test("aaabaaaa", "aabaa");
        test &= test("aab", "aab");
        test &= test("aaaaaaaaaaaaaaaaaa", "aa");
        CommonMethods.printResult(test);
    }

    private static boolean test(String s, String expected) {
        CommonMethods.print(new String[]{"Input", "Expected"}, true, s, expected);
        String output;
        boolean pass, finalPass = true;

        SolutionLastTwoCharMatch solutionLastTwoCharMatch = new SolutionLastTwoCharMatch();
        output = solutionLastTwoCharMatch.makeFancyString(s);
        pass = output.equals(expected);
        finalPass &= pass;
        CommonMethods.print(new String[]{"TwoCharMatch", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionFrequency solutionFrequency = new SolutionFrequency();
        output = solutionFrequency.makeFancyString(s);
        pass = output.equals(expected);
        finalPass &= pass;
        CommonMethods.print(new String[]{"Frequency", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass;
    }

    static class SolutionLastTwoCharMatch {
        public String makeFancyString(String s) {
            if (s == null || s.length() < 3)
                return s;

            StringBuilder out = new StringBuilder();

            char[] chars = s.toCharArray();
            int n = s.length();
            int i = 2, outLength = 0;
            out.append(chars[outLength]).append(chars[outLength + 1]);
            outLength = 2;


            while (i < n) {
                //if occurring more than 2 times
                if (chars[i] != out.charAt(outLength - 1) || chars[i] != out.charAt(outLength - 2)) {
                    out.append(chars[i]);
                    outLength++;
                }
                i++;
            }
            return out.toString();
        }
    }

    static class SolutionFrequency {
        public String makeFancyString(String s) {
            if (s == null || s.length() < 3)
                return s;

            StringBuilder out = new StringBuilder();
            char[] chars = s.toCharArray();
            int i = 1, count = 1;
            char last = chars[0];
            out.append(last);

            while (i < s.length()) {

                if (last == chars[i]) {
                    count++;
                } else {
                    count = 1;
                }
                if (count < 3) {
                    out.append(chars[i]);
                    last = chars[i];
                }
                i++;
            }
            return out.toString();
        }
    }
}
