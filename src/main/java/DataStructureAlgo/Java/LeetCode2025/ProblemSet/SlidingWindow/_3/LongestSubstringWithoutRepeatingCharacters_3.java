package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._3;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/30/2025
 * Question Title: 3. Longest Substring Without Repeating Characters
 * Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * Description: Given a string s, find the length of the longest substring without duplicate characters.*
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
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
 * @Amazon
 * @Microsoft
 * @Facebook
 * @Bloomberg
 * @Adobe
 * @Alation
 * @Alibaba
 * @Atlassian
 * @Baidu
 * @ByteDance
 * @Cisco
 * @eBay
 * @Expedia
 * @GoldmanSachs
 * @Google
 * @Huawei
 * @Oracle
 * @Paypal
 * @Salesforce
 * @Samsung
 * @SAP
 * @Snapchat
 * @Tencent
 * @Twitch
 * @Uber
 * @VMware
 * @WalmartLabs
 * @Yahoo
 * @Yandex
 * @Yelp
 * @Zillow
 * @Zoho<p> -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestSubstringWithoutRepeatingCharacters_3 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("abcabcbb", 3));
        tests.add(test("bbbbb", 1));
        tests.add(test("pwwkew", 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"s", "Expected"}, true, s, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionSlidingWindow().lengthOfLongestSubstring(s);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //O(N) / O(1)
    static class SolutionSlidingWindow {
        public int lengthOfLongestSubstring(String s) {
            int[] charToPos = new int[256];
            Arrays.fill(charToPos, -1);

            int windowStart = 0, windowEnd = 0, longest = 0;

            while (windowEnd < s.length()) {
                char c = s.charAt(windowEnd);
                if (charToPos[c] != -1) {
                    windowStart = Math.max(windowStart, charToPos[(int) c] + 1);
                }
                charToPos[c] = windowEnd;

                longest = Math.max(windowEnd - windowStart + 1, longest);
                windowEnd++;
            }
            return longest;
        }
    }
}
