package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._522;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 522. Longest Uncommon Subsequence II
 * Link: https://leetcode.com/problems/longest-uncommon-subsequence-ii/description/
 * Description: Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.
 * <p>
 * An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
 * <p>
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 * <p>
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["aba","cdc","eae"]
 * Output: 3
 * Example 2:
 * <p>
 * Input: strs = ["aaa","aaa","aa"]
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] consists of lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._521.LongestUncommonSubsequenceI_521}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @TwoPointers
 * @String
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Apple
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestUncommonSubsequenceII_522 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new String[]{"aba", "cdc", "eae"}, 3));
        tests.add(test(new String[]{"aaa", "aaa", "aa"}, -1));
        tests.add(test(new String[]{"aaa", "aaaa", "aa"}, 4));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String[] grid, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "Expected"}, true, grid, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.findLUSlength(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int findLUSlength(String[] strs) {
            Arrays.sort(strs, (s1, s2) -> (s2.length() - s1.length())); // this help us to find the longest string match first, to avoid matching all n*n strings

            for (int i = 0; i < strs.length; i++) {

                boolean isUncommon = true;
                for (int j = 0; j < strs.length; j++) {

                    if (i != j && isSubsequence(strs[i], strs[j])) {
                        isUncommon = false;
                        break;
                    }

                }
                if (isUncommon)
                    return strs[i].length();
            }

            return -1;

        }

        //check if s1 is subsequence of s2
        //"aaa","aa"
        //"aba", "aa"
        boolean isSubsequence(String s1, String s2) {
            int i = 0, j = 0;

            while (i < s1.length() && j < s2.length()) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    i++;
                }
                j++;
            }

            return i == s1.length();

        }
    }
}
