package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._424;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/30/2025
 * Question Title: 424. Longest Repeating Character Replacement
 * Link: https://leetcode.com/problems/longest-repeating-character-replacement/description/
 * Description: You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * <p>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340.LongestSubstringWithAtMostKDistinctCharacters_340} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.ConsecutiveOnes._1007.MaxConsecutiveOnesIII_1007}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340.LongestSubstringWithAtMostKDistinctCharacters_340}
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
 * @Google
 * @Amazon
 * @Uber
 * @PocketGems
 *
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestRepeatingCharacterReplacement_424 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("ABAB", 2, 4));
        tests.add(test("AABABBA", 1, 4));
        tests.add(test("BAAAABBA", 1, 5));
        tests.add(test("BAAAABBA", 3, 8));
        tests.add(test("BAAAABBBBBA", 1, 6));
        tests.add(test("CBAAAABBBBBA", 1, 6));
        tests.add(test("CBAAAABBBBBA", 2, 7));
        tests.add(test("CABAAAABBBBBA", 2, 7));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"s", "k", "Expected"}, true, s, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().characterReplacement(s, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int characterReplacement(String s, int k) {
            int n = s.length();
            char[] temp = s.toCharArray();
            int[] map = new int[26];

            int windowStart = 0, windowEnd = 0, maxLen = 0;
            int mostRepCharCount = 0;


            while (windowEnd < n) {
                char c = temp[windowEnd];
                int idx = c - 'A';

                //expand the window, count the most repeating char
                mostRepCharCount = Math.max(mostRepCharCount, ++map[idx]);

                int windowLength = windowEnd - windowStart + 1;

                //shrink the window, if needed
                while (windowLength - mostRepCharCount > k) {
                    c = temp[windowStart++];
                    idx = c - 'A';

                    //extract the 'windowStart'
                    mostRepCharCount = Math.max(mostRepCharCount, --map[idx]);

                    //calculate new window length
                    windowLength = windowEnd - windowStart + 1;
                }

                maxLen = Math.max(windowLength, maxLen);

                windowEnd++;
            }
            return maxLen;
        }
    }
}
