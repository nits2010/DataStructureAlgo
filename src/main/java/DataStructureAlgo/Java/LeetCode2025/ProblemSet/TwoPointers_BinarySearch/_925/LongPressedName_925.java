package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._925;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 925. Long Pressed Name
 * Link: https://leetcode.com/problems/long-pressed-name/description/
 * Description: Your friend is typing his name into a keyboard. Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 * <p>
 * You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * Example 2:
 * <p>
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it was not in the typed output.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= name.length, typed.length <= 1000
 * name and typed consist of only lowercase English letters.
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
 * @easy
 * @TwoPointers
 * @String <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongPressedName_925 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("alex", "aaleex", true));
        tests.add(test("saeed", "ssaaedd", false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String name, String typed, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Name", "typed", "Expected"}, true, name, typed, expected);

        Boolean output ;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.isLongPressedName(name, typed);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public boolean isLongPressedName(String name, String typed) {

            // two pointers to the "name" and "typed" string respectively
            int np = 0, tp = 0;
            // convert the string to array of chars, for ease of processing later.
            char[] name_chars = name.toCharArray();
            char[] typed_chars = typed.toCharArray();

            // advance two pointers, until we exhaust one of the strings
            while (np < name_chars.length && tp < typed_chars.length) {
                if (name_chars[np] == typed_chars[tp]) {
                    np += 1;
                    tp += 1;
                } else if (tp >= 1 && typed_chars[tp] == typed_chars[tp - 1]) {
                    tp += 1;
                } else {
                    return false;
                }
            }

            // if there is still some characters left *unmatched* in the origin string,
            // then we don't have a match.
            // e.g. name = "abc" typed = "aabb"
            if (np != name_chars.length) {
                return false;
            } else {
                // In the case that there are some redundant characters left in typed
                // we could still have a match.
                // e.g. name = "abc" typed = "abccccc"
                while (tp < typed_chars.length) {
                    if (typed_chars[tp] != typed_chars[tp - 1])
                        return false;
                    tp += 1;
                }
            }

            // both strings have been consumed.
            return true;
        }
    }
}
