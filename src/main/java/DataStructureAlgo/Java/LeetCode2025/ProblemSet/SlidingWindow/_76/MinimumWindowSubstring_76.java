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
        public String minWindow(String text, String pattern) {
            if (text.isEmpty() || pattern.isEmpty()) return "";

            int textLength = text.length();
            int windowLength = pattern.length();

            if (windowLength > textLength)
                return "";

            char[] texts = text.toCharArray();
            char[] patterns = pattern.toCharArray();

            int[] shouldFind = new int[128]; //represent which characters+frequency to find, only upper and lower case letters
            int[] hasFind = new int[128]; //represent which characters+frequency have found, only upper and lower case letters

            int foundCount = 0; //characters found count

            //cache the pattern to get shouldFind
            for (char c : patterns) {
                shouldFind[c]++;
            }

            //now start a window of windowLength and count/cache each of the character from text
            int start = 0, end = 0;
            int rStart = -1, rEnd = -1;
            int resultLength = textLength + 1;
            while (end < textLength) {

                char c = texts[end];

                //expand the window
                if (shouldFind[c] != -1) {
                    hasFind[c]++;

                    //count how many character been found
                    // text = "aa" , pattern="aa"
                    if (hasFind[c] <= shouldFind[c]) {
                        foundCount++;
                    }

                    //if all required character found, then evaluate the window
                    if (foundCount == windowLength) {

                        //We need to find the minimum window substring, hence try to shrink window
                        // 1. Remove all character that has more frequency than required
                        while (hasFind[texts[start]] > shouldFind[texts[start]]) {
                            hasFind[texts[start]]--;
                            start++;
                        }

                        //this is the window that has the same count of character as a pattern and same letters
                        if (resultLength > (end - start + 1)) {
                            resultLength = end - start + 1;
                            rStart = start;
                            rEnd = end;
                        }

                        //if we found a string same length as a pattern, then this would be best
                        if (resultLength == windowLength)
                            break;

                    }
                }

                end++;
            }

            return resultLength == textLength + 1 ? "" : text.substring(rStart, rEnd + 1);

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
