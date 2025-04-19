package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.compression._443;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/4/2024
 * Question Category: 443. String Compression
 * Description: https://leetcode.com/problems/string-compression/description/
 * Given an array of characters chars, compress it using the following algorithm:
 * <p>
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * <p>
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * <p>
 * After you are done modifying the input array, return the new length of the array.
 * <p>
 * You must write an algorithm that uses only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 * Example 2:
 * <p>
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * Explanation: The only group is "a", which remains uncompressed since it's a single character.
 * Example 3:
 * <p>
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.StringCompressionRunLengthEncoding} {@link DataStructureAlgo.Java.nonleetcode.StringCompression2}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @TwoPointers
 * @String <p><p>
 * Company Tags
 * -----
 * @Expedia
 * @Amazon
 * @Microsoft
 * @GoldmanSachs
 * @Apple <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class StringCompressionI_443 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(tests(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}, 6, new char[]{'a', '2', 'b', '2', 'c', '3'}));
        tests.add(tests(new char[]{'a'}, 1, new char[]{'a'}));
        tests.add(tests(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, 4, new char[]{'a', 'b', '1', '2'}));
        tests.add(tests(new char[]{'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'b', 'c', 'c', 'c'}, 10, new char[]{'a', 'b', 'c', '3', 'a', '2', 'b', '2', 'c', '3'}));
        tests.add(tests(new char[]{'1', '2', '2'}, 3, new char[]{'1', '2', '2'}));
        tests.add(tests(new char[]{'a', 'a', 'a', '3', 'c', 'c', 'c'}, 5, new char[]{'a', '3', '3', 'c', '3'}));
        tests.add(tests(new char[]{'a', 'a', 'a', '3', '3', 'c', 'c'}, 6, new char[]{'a', '3', '3', '2', 'c', '2'}));


        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(char[] input, int expected, char[] expectedArray) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"input", "Expected", "ExpectedArray"}, true, input, expected, expectedArray);

        int output;
        boolean pass, finalPass = true;

        //add logic here
        SolutionTwoPointer solution = new SolutionTwoPointer();
        output = solution.compress(input);
        pass = output == expected;

        if (pass) {
            for (int i = 0; i < output; i++) {
                pass = input[i] == expectedArray[i];
                if (!pass) {
                    break;
                }
            }
        }
        finalPass = finalPass && pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }


    static class SolutionTwoPointer {
        public int compress(char[] chars) {
            int n = chars.length;
            int i = 0, r = 0;
            while (i < n) {

                // length of the group
                int count = 1;
                //check if current and next char are same
                while (i + 1 < n && chars[i] == chars[i + 1]) {
                    //if they are the same, count the occurrence
                    i++;
                    count++;
                }
                //copy the last processed character
                chars[r++] = chars[i];

                //if the occurrence is greater than 1, add it to the result
                if (count > 1) {

                    char[] arr = String.valueOf(count).toCharArray();
                    for (char c : arr) {
                        chars[r++] = c;
                    }

                }

                i++;

            }

            return r;

        }
    }
}
