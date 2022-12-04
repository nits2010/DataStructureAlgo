package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.nonleetcode.StringCompression2;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-30
 * Description: https://leetcode.com/problems/string-compression/
 * 443. String Compression [EASY]
 * Given an array of characters, compress it in-place.
 * <p>
 * The length after compression must always be smaller than or equal to the original array.
 * <p>
 * Every element of the array should be a character (not int) of length 1.
 * <p>
 * After you are done modifying the input array in-place, return the new length of the array.
 * <p>
 * <p>
 * Follow up:
 * Could you solve it using only O(1) extra space?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * ["a","a","b","b","c","c","c"]
 * <p>
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * <p>
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input:
 * ["a"]
 * <p>
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 * <p>
 * Explanation:
 * Nothing is replaced.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * <p>
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * <p>
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * <p>
 * <p>
 * Note:
 * <p>
 * All characters have an ASCII value in [35, 126].
 * 1 <= len(chars) <= 1000.
 * <p>
 * First part of it from {@link StringCompression2}
 */
public class StringCompressionRunLengthEncoding {


    public static void main(String[] args) {
        test(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}, new char[]{'a', '2', 'b', '2', 'c', '3'});
        test(new char[]{'a', 'b', 'c'}, new char[]{'a', 'b', 'c'});
    }

    public static void test(char[] chars, char[] expected) {
        System.out.println("\nGiven:" + GenericPrinter.toString(chars) + "\nexpected:" + GenericPrinter.toString(expected));
        int len = compress(chars);
        System.out.println("Obtained:" + GenericPrinter.toString(chars, 0, len - 1));
    }


    public static int compress(char[] chars) {
        int anchor = 0, write = 0;

        for (int read = 0; read < chars.length; read++) {

            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {

                chars[write++] = chars[anchor];

                if (read > anchor) {

                    for (char c : ("" + (read - anchor + 1)).toCharArray())
                        chars[write++] = c;

                }
                anchor = read + 1;
            }
        }
        return write;
    }
}
