package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._2914;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date:05/11/24
 * Question Category: 2914. Minimum Number of Changes to Make Binary String Beautiful
 * Description: https://leetcode.com/problems/minimum-number-of-changes-to-make-binary-string-beautiful/description/
 * You are given a 0-indexed binary string s having an even length.
 * <p>
 * A string is beautiful if it's possible to partition it into one or more substrings such that:
 * <p>
 * Each substring has an even length.
 * Each substring contains only 1's or only 0's.
 * You can change any character in s to 0 or 1.
 * <p>
 * Return the minimum number of changes required to make the string s beautiful.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "1001"
 * Output: 2
 * Explanation: We change s[1] to 1 and s[3] to 0 to get string "1100".
 * It can be seen that the string "1100" is beautiful because we can partition it into "11|00".
 * It can be proven that 2 is the minimum number of changes needed to make the string beautiful.
 * Example 2:
 * <p>
 * Input: s = "10"
 * Output: 1
 * Explanation: We change s[1] to 1 to get string "11".
 * It can be seen that the string "11" is beautiful because we can partition it into "11".
 * It can be proven that 1 is the minimum number of changes needed to make the string beautiful.
 * Example 3:
 * <p>
 * Input: s = "0000"
 * Output: 0
 * Explanation: We don't need to make any changes as the string "0000" is beautiful already.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= s.length <= 105
 * s has an even length.
 * s[i] is either '0' or '1'.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @String <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class MinimumNumberOfChangesToMakeBinaryStringBeautiful_2914 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(tests("1001", 2));
        tests.add(tests("10", 1));
        tests.add(tests("0000", 0));


        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(String input, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"input", "Expected"}, true, input, expected);

        int output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.minChanges(input);
        pass = output == expected;
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass; 

    }

    static class Solution {
        public int minChanges(String s) {
            int count = 0;
            int i = 1;
            while (i < s.length()) {
                if (s.charAt(i) != s.charAt(i - 1))
                    count++;
                i += 2;
            }
            return count;
        }
    }
}
