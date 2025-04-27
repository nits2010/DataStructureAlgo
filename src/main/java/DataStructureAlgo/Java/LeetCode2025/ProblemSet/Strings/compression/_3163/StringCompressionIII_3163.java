package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.compression._3163;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/4/2024
 * Question Category: 3163. String Compression III
 * Description: https://leetcode.com/problems/string-compression-iii/description/?envType=daily-question&envId=2024-11-04
 * Given a string word, compress it using the following algorithm:
 * <p>
 * Begin with an empty string comp. While word is not empty, use the following operation:
 * Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
 * Append the length of the prefix followed by c to comp.
 * Return the string comp.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word = "abcde"
 * <p>
 * Output: "1a1b1c1d1e"
 * <p>
 * Explanation:
 * <p>
 * Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the prefix in each operation.
 * <p>
 * For each prefix, append "1" followed by the character to comp.
 * <p>
 * Example 2:
 * <p>
 * Input: word = "aaaaaaaaaaaaaabb"
 * <p>
 * Output: "9a5a2b"
 * <p>
 * Explanation:
 * <p>
 * Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and "bb" as the prefix in each operation.
 * <p>
 * For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
 * For prefix "aaaaa", append "5" followed by "a" to comp.
 * For prefix "bb", append "2" followed by "b" to comp.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length <= 2 * 105
 * word consists only of lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @String
 * @medium <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class StringCompressionIII_3163 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test("abcde", "1a1b1c1d1e"));
        tests.add(test("aaaaaaaaaaaaaabb", "9a5a2b"));
        tests.add(test("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "9a9a9a7a"));
        tests.add(test("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "1b9a9a9a7a"));
        tests.add(test("aaaaaaaaay", "9a1y"));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String word, String expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Word", "Expected"}, true, word, expected);

        String output;
        boolean pass, finalPass = true;

        //add logic here
        Solution solution = new Solution();
        output = solution.compressedString(word);
        pass = output.equals(expected);
        finalPass = finalPass && pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution {
        public String compressedString(String word) {
            StringBuilder compressed = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int count = 1;
                while (i + 1 < word.length() && word.charAt(i + 1) == c) {
                    i++;
                    count++;
                }


                while (count >= 9) {
                    compressed.append(9);
                    compressed.append(c);
                    count -= 9;
                }
                if (count > 0) {
                    compressed.append(count);
                    compressed.append(c);
                }

            }

            return compressed.toString();
        }
    }
}
