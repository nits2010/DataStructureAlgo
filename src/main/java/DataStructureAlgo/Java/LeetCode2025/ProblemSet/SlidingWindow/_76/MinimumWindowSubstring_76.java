package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._76;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/8/2024
 * Question Category: 76. Minimum Window Substring
 * Description: https://leetcode.com/problems/minimum-window-substring/description/
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 * of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @SlidingWindow
 * @HashTable
 * @String <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Airbnb
 * @Amazon
 * @Apple
 * @Bloomberg
 * @Cohesity
 * @DeutscheBank
 * @Facebook
 * @GoDaddy
 * @GoldmanSachs
 * @Google
 * @LinkedIn
 * @Lyft
 * @Microsoft
 * @Nutanix
 * @Oracle
 * @Snapchat
 * @Twitter
 * @Uber
 * @Visa
 * @VMware
 * @WalmartLabs
 * @Yahoo <p><p>
 * @Editorial
 */
public class MinimumWindowSubstring_76 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("cabwefgewcwaefgcf", "cae", "cwae");
        test &= test("ab", "a", "a");
        test &= test("ADOBECODEBANC", "ABC", "BANC");
        test &= test("ABBACBAA", "AAB", "BAA");
        test &= test("a", "a", "a");
        test &= test("a", "aa", "");

        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(String s, String t, String expected) {
        System.out.println("----------------------------------");
        System.out.println("S :" + s + " T :" + t + " expected : " + expected);

        String output;
        boolean pass, finalPass = true;

        SolutionSlidingWindow solutionSlidingWindow = new SolutionSlidingWindow();
        output = solutionSlidingWindow.minWindow(s, t);
        pass = output.equals(expected);
        System.out.println("Output : " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        return finalPass;
    }

    static class SolutionSlidingWindow {
        public String minWindow(String s, String t) {
            if (s.isEmpty() || t.isEmpty()) return "";

            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            int m = s.length();
            int n = t.length();

            final int[] shouldFind = new int[256];
            final int[] hasFind = new int[256];

            //store the frequency of characters in t
            for (char c : tChars) {
                shouldFind[c]++;
            }

            int left = 0, right = 0; //two pointers denote the sliding window start and end
            int counts = 0;
            int minLength = m + 1; // max length of substring
            int start = -1, end = -1;

            while (right < m) {

                char ch = sChars[right];

                //if that char is needs to be included in the substring
                if (shouldFind[ch] > 0) {

                    hasFind[ch]++;

                    if (hasFind[ch] <= shouldFind[ch]) {
                        counts++;
                    }

                    //found a valid substring contains t characters
                    if (counts == n) {

                        //squeeze the window
                        while (shouldFind[sChars[left]] == 0 || hasFind[sChars[left]] > shouldFind[sChars[left]]) {
                            hasFind[sChars[left]]--;
                            left++;
                        }

                        int len = right - left + 1;
                        if (len < minLength) {
                            minLength = len;
                            start = left;
                            end = right;
                        }

                    }

                }
                right++;
            }
            if (minLength > m)
                return "";

            return s.substring(start, end + 1);

        }
    }


    class SolutionSlidingWindowV2 {
        public String minWindow(String text, String pattern) {

            int[] shouldFind = new int[128];
            int[] hasFind = new int[128];
            int pLen = pattern.length();

            int windowStart = 0, windowEnd = 0, minLen = Integer.MAX_VALUE;
            int start = -1, end = -1;
            int foundCharCount = 0;

            //cache the pattern, frequency of chars
            for (int i = 0; i < pLen; i++) {
                shouldFind[pattern.charAt(i)]++;
            }

            while (windowEnd < text.length()) {

                char c = text.charAt(windowEnd);

                //expand the window
                hasFind[c]++;


                //here we keep <= instead =, consider a case, when both t and p are same "aa" , "aa" so now at first char, a's freq will be 1 only
                // however, ""="" will not increase the foundCharCount since 1!=2

                //or text = bbaa, pattern = aba; in such case, foundCharCount will be 2 only as we found 2 character matching only
                // however, it needs to be 3


                if (hasFind[c] <= shouldFind[c]) {

                    foundCharCount++;

                }

                //if we have found all the required characters in patter, which is = pLen
                if (foundCharCount == pLen) {

                    //since we need to know the minimum window, we need to first try to squeeze it
                    //we can squeeze the window only when we have more occurrence of char then required ; hasFind[x] > shouldFind[x]
                    // additionally we can drop all those char which is not required ; shouldFind[x] == 0
                    char x = text.charAt(windowStart);
                    while (shouldFind[x] == 0 || hasFind[x] > shouldFind[x]) {

                        if (hasFind[x] > 0)
                            hasFind[x]--; // remove this from the window
                        windowStart++;
                        x = text.charAt(windowStart);
                    }

                    //this will be the window, where a minimum can occurred
                    int length = windowEnd - windowStart + 1;
                    if (minLen > length) {
                        minLen = length;
                        start = windowStart;
                        end = windowEnd;
                    }

                    //we can not go smaller beyond pLen
                    if (minLen == pLen) {
                        break;
                    }

                }

                windowEnd++;
            }

            if (start == -1)
                return "";

            return text.substring(start, end + 1);

        }
    }
}
