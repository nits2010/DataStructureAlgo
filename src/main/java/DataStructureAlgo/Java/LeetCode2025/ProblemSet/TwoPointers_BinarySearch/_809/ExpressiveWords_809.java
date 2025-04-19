package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._809;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/19/2025
 * Question Title: 809. Expressive Words
 * Link: https://leetcode.com/problems/expressive-words/description/
 * Description: Sometimes people repeat letters to represent extra feeling. For example:
 * <p>
 * "hello" -> "heeellooo"
 * "hi" -> "hiiii"
 * In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".
 * <p>
 * You are given a string s and an array of query strings words. A query word is stretchy if it can be made to be equal to s by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is three or more.
 * <p>
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
 * Return the number of query strings that are stretchy.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "heeellooo", words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 * Example 2:
 * <p>
 * Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, words.length <= 100
 * 1 <= words[i].length <= 100
 * s and words[i] consist of lowercase letters.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.compression._443.StringCompressionI_443}
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.compression._1531.StringCompressionII_1531}
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.compression._3163.StringCompressionIII_3163}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @String <p><p>
 * Company Tags
 * -----
 * @Cisco
 * @Google
 * @Facebook <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ExpressiveWords_809 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("heeellooo", new String[]{"hello", "hi", "helo"}, 1));
        tests.add(test("zzzzzyyyyy", new String[]{"zzyy", "zy", "zyy"}, 3));
        tests.add(test("abcd", new String[]{"abc"}, 0));
        tests.add(test("dddiiiinnssssssoooo", new String[]{"dinnssoo", "ddinso", "ddiinnso", "ddiinnssoo", "ddiinso", "dinsoo", "ddiinsso", "dinssoo", "dinso"}, 3));
        CommonMethods.printAllTestOutCome(tests);

    }

    private static boolean test(String s, String[] words, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"s", "words", "Expected"}, true, s, words, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_OnTheFly solutionOnTheFly = new Solution_OnTheFly();
        output = solutionOnTheFly.expressiveWords(s, words);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"OnTheFly", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_RLE solutionRle = new Solution_RLE();
        output = solutionRle.expressiveWords(s, words);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"RLE", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //O(Q*(M+n))  : n - length of s, Q - length of words, M - max length of a word
    static class Solution_OnTheFly {
        public int expressiveWords(String s, String[] words) {
            int result = 0;
            for (String word : words) {
                if (isStretchy(word.toCharArray(), s.toCharArray()))
                    result++;
            }

            return result;
        }

        boolean isStretchy(char[] wChars, char[] sChars) {

            int w = 0;
            int i = 0;

            while (w < wChars.length && i < sChars.length) {
                if (wChars[w] == sChars[i]) {

                    //count the same character count in word
                    int count = 1;
                    while (w + 1 < wChars.length && wChars[w] == wChars[w + 1]) {
                        count++;
                        w++;
                    }

                    int occrrence = 1;
                    while (i + 1 < sChars.length && sChars[i] == sChars[i + 1]) { //bottleneck - happening again n again for each word, simplify using run length encoding
                        occrrence++;
                        i++;
                    }

                    //check if constraints fail?
                    if (count > occrrence || (occrrence < 3 && count != occrrence))
                        return false;

                    i++; //move to next char
                    w++;

                } else {
                    return false;
                }
            }
            return i == sChars.length && w == wChars.length;
        }
    }

    //O(n) + O(Q*M)  : n - length of s, Q - length of words, M - max length of a word
    static class Solution_RLE {
        public int expressiveWords(String s, String[] words) {

            List<Character> sChars = new ArrayList<>();
            List<Integer> sCount = new ArrayList();

            runLengthEncoding(s.toCharArray(), sChars, sCount);

            int result = 0;
            for (String word : words) {
                if (isStretchy(word.toCharArray(), sChars, sCount))
                    result++;
            }

            return result;
        }

        //(M)
        boolean isStretchy(char[] wChars, List<Character> sChars, List<Integer> sCount) {

            int w = 0;
            int i = 0;

            while (w < wChars.length && i < sChars.size()) {
                if (wChars[w] == sChars.get(i)) {

                    //count the same character count in word
                    int wCount = 1;
                    while (w + 1 < wChars.length && wChars[w] == wChars[w + 1]) {
                        wCount++;
                        w++;
                    }

                    //check if constraints fail?
                    if (wCount > sCount.get(i) || (sCount.get(i) < 3 && wCount != sCount.get(i)))
                        return false;

                    i++; //move to the next char
                    w++;

                } else {
                    return false;
                }
            }
            return i == sChars.size() && w == wChars.length;
        }

        //heeelloo0 -> he3l2o3
        //O(n)
        void runLengthEncoding(char[] chars, List<Character> sChars, List<Integer> sCount) {

            int j = 0; //pointer to current
            int count = 0; //count of current char

            while (j < chars.length) {
                char c = chars[j];
                count = 1;

                //count freqency of this char
                while (j + 1 < chars.length && chars[j] == chars[j + 1]) {
                    j++;
                    count++;
                }

                //copy the char
                sChars.add(chars[j]);
                sCount.add(count);

                j++;
            }

        }

    }

}
