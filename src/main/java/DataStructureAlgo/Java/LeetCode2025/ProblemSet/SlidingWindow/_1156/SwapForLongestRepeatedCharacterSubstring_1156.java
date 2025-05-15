package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1156;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/15/2025
 * Question Title: 1156. Swap For Longest Repeated Character Substring
 * Link: https://leetcode.com/problems/swap-for-longest-repeated-character-substring/description/
 * Description: You are given a string text. You can swap two of the characters in the text.
 * <p>
 * Return the length of the longest substring with repeated characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa" with length 3.
 * Example 2:
 * <p>
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa" with length 6.
 * Example 3:
 * <p>
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa" with length is 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text.length <= 2 * 104
 * text consist of lowercase English characters only.
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
 * @HashTable
 * @String
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Nutanix <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SwapForLongestRepeatedCharacterSubstring_1156 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("ababa", 3));
        tests.add(test("aaabaaa", 6));
        tests.add(test("aaaaa", 5));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String text, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"text", "Expected"}, true, text, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionThreePointer().maxRepOpt1(text);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionThreePointer {
        public int maxRepOpt1(String text) {
            int n = text.length();
            char[] temp = text.toCharArray();
            int maxLength = -1;

            int[] counts = new int[26];
            for (char c : temp) {
                counts[c - 'a']++;
            }

            int i = 0, j, k;

            while (i < n) {

                // fix 'i' the character and find the block of ith character
                j = i;
                while (j < n && temp[i] == temp[j])
                    j++;

                // jth is the different character then ith, now find another block of ith character
                k = j + 1;

                while (k < n && temp[i] == temp[k])
                    k++;

                //possible valid window
                int windowLength = k - i;

                maxLength = Math.max(maxLength, Math.min(windowLength, counts[temp[i] - 'a']));

                i = j;

            }

            return maxLength;

        }
    }
}
