package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._2490;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/3/2024
 * Question Category: 2490. Circular Sentence
 * Description: https://leetcode.com/problems/circular-sentence/description/
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
 * <p>
 * For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
 * Words consist of only uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.
 * <p>
 * A sentence is circular if:
 * <p>
 * The last character of a word is equal to the first character of the next word.
 * The last character of the last word is equal to the first character of the first word.
 * For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.
 * <p>
 * Given a string sentence, return true if it is circular. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sentence = "leetcode exercises sound delightful"
 * Output: true
 * Explanation: The words in sentence are ["leetcode", "exercises", "sound", "delightful"].
 * - leetcode's last character is equal to exercises's first character.
 * - exercises's last character is equal to sound's first character.
 * - sound's last character is equal to delightful's first character.
 * - delightful's last character is equal to leetcode's first character.
 * The sentence is circular.
 * Example 2:
 * <p>
 * Input: sentence = "eetcode"
 * Output: true
 * Explanation: The words in sentence are ["eetcode"].
 * - eetcode's last character is equal to eetcode's first character.
 * The sentence is circular.
 * Example 3:
 * <p>
 * Input: sentence = "Leetcode is cool"
 * Output: false
 * Explanation: The words in sentence are ["Leetcode", "is", "cool"].
 * - Leetcode's last character is not equal to is's first character.
 * The sentence is not circular.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= sentence.length <= 500
 * sentence consist of only lowercase and uppercase English letters and spaces.
 * The words in sentence are separated by a single space.
 * There are no leading or trailing spaces.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class CircularSentence_2490 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test("leetcode exercises sound delightful", true));
        tests.add(test("eetcode", true));
        tests.add(test("Leetcode is cool", false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String input, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Input", "Expected"}, true, input, expected);

        boolean output;
        boolean pass, finalPass = true;

        //add logic here
        Solution solution = new Solution();
        output = solution.isCircularSentence(input);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution {
        public boolean isCircularSentence(String sentence) {
            int n = sentence.length();

            for (int i = 0; i < n; i++) {

                if (sentence.charAt(i) == ' ' &&
                        sentence.charAt(i - 1) != sentence.charAt(i + 1))
                    return false;


            }
            return sentence.charAt(n - 1) == sentence.charAt(0);
        }
    }
}
