package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._438;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/30/2025
 * Question Title: 438. Find All Anagrams in a String
 * Link: https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 * Description: Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.FindAllAnagramsString}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._567.PermutationInString_567}
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
 * @Apple
 * @Adobe
 * @Bloomberg
 * @GoldmanSachs
 * @Google
 * @Oracle
 * @Robinhood
 * @Uber<p> -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindAllAnagramsInAString_438 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("cbaebabacd", "abc", Arrays.asList(0, 6)));
        tests.add(test("abab", "ab", Arrays.asList(0, 1, 2)));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, String p, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"s", "p", "Expected"}, true, s, p, expected);

        List<Integer> output;
        boolean pass, finalPass = true;

        output = new SolutionSlidingWindow().findAnagrams(s, p);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionSlidingWindow {
        public List<Integer> findAnagrams(String string, String pattern) {

            if (string == null || pattern == null || string.isEmpty() || pattern.isEmpty()
                    || pattern.length() > string.length())
                return Collections.EMPTY_LIST;

            int patternC[] = new int[26];

            int str[] = new int[26];

            int n = string.length();
            int p = pattern.length();


            for (int i = 0; i < p; i++) {
                str[string.charAt(i) - 'a']++;
                patternC[pattern.charAt(i) - 'a']++;
            }

            List<Integer> solution = new ArrayList<>();

            int s = 0;
            for (int i = p; i < n; i++) {

                if (match(str, patternC))
                    solution.add(s);

                str[string.charAt(i) - 'a']++;
                str[string.charAt(s++) - 'a']--;

            }
            if (match(str, patternC))
                solution.add(s);

            return solution;

        }


        private boolean match(int a[], int b[]) {
            for (int i = 0; i < a.length; i++)
                if (a[i] != b[i])
                    return false;

            return true;
        }

    }
}
