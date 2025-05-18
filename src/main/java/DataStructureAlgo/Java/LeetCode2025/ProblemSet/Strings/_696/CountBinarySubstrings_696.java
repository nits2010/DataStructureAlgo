package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._696;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/12/2025
 * Question Title: 696. Count Binary Substrings
 * Link: https://leetcode.com/problems/count-binary-substrings/description/
 * Description: Given a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 * <p>
 * Substrings that occur multiple times are counted the number of times they occur.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 * <p>
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
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
 * @Amazon
 * @Helix <p>
 * -----
 * @Editorial https://leetcode.com/problems/count-binary-substrings/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CountBinarySubstrings_696 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("00110011", 6));
        tests.add(test("10101", 4));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String string, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"string", "Expected"}, true, string, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_Groups solutionGroups = new Solution_Groups();
        output = solutionGroups.countBinarySubstrings(string);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Groups", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        Solution_Optimized_TwoPointer solutionOptimizedTwoPointer = new Solution_Optimized_TwoPointer();
        output = solutionOptimizedTwoPointer.countBinarySubstrings(string);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"TwoPointerGroups", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     00110011
     in order to have a substring which has the same 0 and 1, we need to count how many contiguous 0 and 1 present in the string
     This is because, the number of contiguous 0 or 1 will form a group of either 0 or 1
     00110011 -> 2(0)2(1)2(0)2(1)
     10101 -> 1(1)1(0)1(1)1(0)1(1)

     Since string can have either 0 or 1,
     it means that either 0 or 1 form an individual group or start a new group with 0 or 1.
     That forms this kind of string
     K(0),M(1) = 00000..K times, 111111..M times
     M(1),K(0) = 111111..M times , 00000..K times

     Now, to make the group,
     we have to choose min(M,K) this is because only that number of either K or M form an equal amount of 0/1 group.

     */
    static class Solution_Groups {
        public int countBinarySubstrings(String s) {
            int[] groups = new int[s.length()];

            //count consecutive 0 or 1
            int groupLength = 0;
            groups[0] = 1;
            for (int i = 1; i < s.length(); i++) {

                if (s.charAt(i - 1) == s.charAt(i)) {
                    groups[groupLength]++;
                } else {
                    groups[++groupLength] = 1;
                }

            }

            //now, based on groups
            //we can have following possible formations with 0 and 1
            // 00000...ktimes,11111...ktimes -> " 0 * k + 1 * k "
            // 11111...ktimes, 00000...ktimes -> " 1 * k + 0 * k "
            // now, in order to make the group, we need to find the k, and k will always be the one which is minimum from 0 or 1
            // for example, 00011, here "0 * 3 + 1 * 2" this will make only  "0011", "01" group only which is the count of min(0,1) = min(3,2) = 2

            int result = 0;
            for (int i = 1; i < groupLength + 1; i++)
                result += Math.min(groups[i - 1], groups[i]);

            return result;

        }
    }

    static class Solution_Optimized_TwoPointer {
        public int countBinarySubstrings(String s) {

            //count consecutive 0 or 1
            int count = 1;
            int prev = 0;
            int result = 0;
            char[] chars = s.toCharArray();
            for (int i = 1; i < s.length(); i++) {

                if (chars[i - 1] == chars[i]) {
                    count++;
                } else {
                    result += Math.min(prev, count);
                    prev = count;
                    count = 1;

                }

            }
            result += Math.min(prev, count);
            ;
            return result;

        }
    }
}
