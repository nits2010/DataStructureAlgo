package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._1593;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 10/21/2024
 * Question Category: 1593. Split a String Into the Max Number of Unique Substrings
 * Description: Given a string s, return the maximum number of unique substrings that the given string can be split into.
 * <p>
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 * Example 2:
 * <p>
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 * Example 3:
 * <p>
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 16
 * <p>
 * s contains only lower case English letters.
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 * <p>
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 * Example 2:
 * <p>
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 * Example 3:
 * <p>
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 16
 * <p>
 * s contains only lower case English letters.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @HashTable
 * @String
 * @Backtracking <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/editorial
 * <p><p>
 * -----
 * @OptimalSoltuion
 */
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings_1593 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("ababccc", 5);
        test &= test("ababadbcvxfgetyf", 13);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(String str, int expected) {
        System.out.println("-------------------------------------------------");
        System.out.println("Input :" + str + " Expected : " + expected);
        int output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.maxUniqueSplit(str);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Backtrack :" + output + " Pass : " + (pass ? "Pass" : "Failed"));

        SolutionPruning solutionPruning = new SolutionPruning();
        output = solutionPruning.maxUniqueSplit(str);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Backtrack + Pruning :" + output + " Pass : " + (pass ? "Pass" : "Failed"));
        return finalPass;
    }


    static class Solution {
        public int maxUniqueSplit(String s) {


            Set<String> seen = new HashSet<>();
            return backtrack(s, 0, seen);
        }

        private int backtrack(String s, int start, Set<String> seen) {
            // Base case: If we reach the end of the string, return 0 (no more substrings to add)
            if (start == s.length()) return 0;

            int maxCount = 0;

            // Try every possible substring starting from 'start'
            for (int end = start + 1; end <= s.length(); ++end) {
                String substring = s.substring(start, end);
                // If the substring is unique
                if (!seen.contains(substring)) {
                    // Add the substring to the seen set
                    seen.add(substring);
                    // Recursively count unique substrings from the next position
                    maxCount = Math.max(maxCount, 1 + backtrack(s, end, seen));
                    // Backtrack: remove the substring from the seen set
                    seen.remove(substring);
                }
            }
            return maxCount;
        }


    }

    static class SolutionPruning {
        int max = 0;

        public int maxUniqueSplit(String str) {
            Set<String> set = new HashSet<>();

            char[] s = str.toCharArray();
            int n = s.length;

            split(str, n, 0, 0, set);
            return max;
        }


        private void split(String s, int n, int start, int count, Set<String> set) {

            // Prune: If the current count plus remaining characters can't exceed maxCount, return
            if (count + n - start <= max) {
                return;
            }

            if (start == n) {
                max = Math.max(max, count);
                return;
            }

            for (int end = start + 1; end <= n; end++) {
                String subString = s.substring(start, end);
                if (!set.contains(subString)) {

                    //take
                    set.add(subString);

                    split(s, n, end, count + 1, set);

                    // backtrack
                    set.remove(subString);

                }
            }


        }


    }
}
