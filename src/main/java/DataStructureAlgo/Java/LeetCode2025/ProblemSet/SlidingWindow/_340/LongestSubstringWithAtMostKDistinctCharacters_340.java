package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340;

import DataStructureAlgo.Java.helpers.*;
import DataStructureAlgo.Java.helpers.templates.MaxHeap;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/28/2025
 * Question Title: 340. Longest Substring with At Most K Distinct Characters
 * Link: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
 * https://leetcode.ca/all/340.html
 * Description: Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 * <p>
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 104
 * 0 <= k <= 50
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
 * @hard
 * @String
 * @SlidingWindow
 * @HashTable <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @AppDynamics
 * @Bloomberg
 * @Citadel
 * @Coupang
 * @Facebook
 * @Google
 * @Microsoft
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestSubstringWithAtMostKDistinctCharacters_340 {

    static int testIndex = 1;

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("eceba", 2, 3));
        tests.add(test("aa", 1, 2));
        tests.add(test("aabcabb", 2, 3));
        tests.add(test("aabcabb", 3, 7));
        tests.add(test("aabcabb", 10, 7));
        tests.add(test("aaaaaaaaaaa", 1, 11));
        tests.add(test("aaaaaaaaaaa", 0, 0));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"testIndex", "s", "k", "Expected"}, true, testIndex++, s, k, expected);

        int output;
        boolean pass, finalPass = true;

        output = new Solution().lengthOfLongestSubstringKDistinct(s, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            if (s == null || s.isEmpty())
                return 0;

            int n = s.length();
            char[] chars = s.toCharArray();
            Map<Character, Integer> mapOfFreq = new HashMap<>();

            int maxLen = 0;
            int windowStart = 0, windowEnd = 0;

            while (windowEnd < n) {

                //expand the window
                mapOfFreq.merge(chars[windowEnd], 1, Integer::sum);

                if (mapOfFreq.size() <= k) {
                    maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
                }

                //shrink the window till window size is 0 or k distinct character left
                while (mapOfFreq.size() > k && windowStart <= windowEnd) {
                    char previous = chars[windowStart];
                    mapOfFreq.merge(previous, -1, Integer::sum);
                    if (mapOfFreq.get(previous) == 0) {
                        mapOfFreq.remove(previous);
                    }
                    windowStart++;
                }
                windowEnd++;
            }


            return maxLen;
        }
    }

}
