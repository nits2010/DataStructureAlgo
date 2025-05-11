package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._524;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 524. Longest Word in Dictionary through Deleting
 * Link: https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
 * Description: Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * Output: "apple"
 * Example 2:
 * <p>
 * Input: s = "abpcplea", dictionary = ["a","b","c"]
 * Output: "a"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s and dictionary[i] consist of lowercase English letters.
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
 * @Array
 * @TwoPointers
 * @String
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestWordInDictionaryThroughDeleting_524 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea"), "apple"));
        tests.add(test("abpcplea", Arrays.asList("a", "b", "c"), "a"));
        tests.add(test("aewfafwafjlwajflwajflwafj", Arrays.asList("apple", "ewaf", "awefawfwaf", "awef", "awefe", "ewafeffewafewf"), "ewaf"));
        tests.add(test("abce", Arrays.asList("abe", "abc"), "abc"));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, List<String> dictionary, String expected) {

        CommonMethods.printTestOutcome(new String[]{"S", "dictionary", "Expected"}, true, s, dictionary, expected);

        String output;
        boolean pass, finalPass = true;

        Solution_Sort solutionSort = new Solution_Sort();
        output = solutionSort.findLongestWord(s, dictionary);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Sorting", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_BruteForce solutionBruteForce = new Solution_BruteForce();
        output = solutionBruteForce.findLongestWord(s, dictionary);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BruteForce", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * O(m*x*logm) + O(m*x) - where m is the number of words in the dictionary, and x is the length of the longest word
     */
    static class Solution_Sort {
        public String findLongestWord(String s, List<String> dictionary) {

            //sort dictionary in descending order of length or ascending order of lexicographical
            dictionary.sort((s1, s2) -> {
                int s1Len = s1.length();
                int s2Len = s2.length();

                if (s1Len == s2Len)
                    return s1.compareTo(s2);
                return s2Len - s1Len;

            });

            for (String word : dictionary) {

                if (word.length() <= s.length()) {

                    int w = 0;
                    char[] wChar = word.toCharArray();

                    for (char c : s.toCharArray()) {

                        if (c == wChar[w]) {
                            w++;
                        }

                        if (w == word.length())
                            return word;
                    }
                    if (w == word.length())
                        return word;
                }
            }

            return "";
        }
    }

    /**
     * BruteForce
     * O(n*m*x) - where m is the number of words in the dictionary, and x is the length of the longest word, n is the length of s
     */
    static class Solution_BruteForce {
        public String findLongestWord(String s, List<String> dictionary) {
            int sLen = s.length();
            int dicLen = dictionary.size();
            String result = "";
            for (String word : dictionary) {

                //skip those words
                //1. which are of the same length as of a result & bigger lexicographically
                // or 2. whose length is smaller than our result so far
                if ((word.length() == result.length() && word.compareTo(result) > 0) || (word.length() < result.length()))
                    continue;

                //compare only required word with s
                if (word.length() <= s.length()) {

                    int w = 0;
                    char[] wChar = word.toCharArray();

                    for (char c : s.toCharArray()) {

                        if (c == wChar[w]) {
                            w++;
                        }

                        if (w == word.length())
                            break;
                    }
                    if (w == word.length())
                        result = word;
                }
            }

            return result;
        }
    }
}
