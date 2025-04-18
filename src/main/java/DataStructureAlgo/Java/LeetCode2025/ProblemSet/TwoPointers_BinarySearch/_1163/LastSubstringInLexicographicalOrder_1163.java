package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1163;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/15/2025
 * Question Title: 1163. Last Substring in Lexicographical Order
 * Link: https://leetcode.com/problems/last-substring-in-lexicographical-order/description/
 * Description: Given a string s, return the last substring of s in lexicographical order.
 * Example 1:
 * <p>
 * Input: s = "abab"
 * Output: "bab"
 * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
 * Example 2:
 * <p>
 * Input: s = "leetcode"
 * Output: "tcode"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 4 * 105
 * s contains only lowercase English letters.
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
 * @hard
 * @TwoPointers
 * @String <p><p>
 * Company Tags
 * -----
 * @Mathworks
 * @Roblox
 * @TwoSigma <p>
 * -----
 * @Editorial https://github.com/doocs/leetcode/blob/main/solution/1100-1199/1163.Last%20Substring%20in%20Lexicographical%20Order/README_EN.md
 * https://algo.monster/liteproblems/1163 <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LastSubstringInLexicographicalOrder_1163 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("abab", "bab"));
        tests.add(test("leetcode", "tcode"));
        tests.add(test("ababc", "c"));
        tests.add(test("ababca", "ca"));
        tests.add(test("ababcbca", "cbca"));
        tests.add(test("cacacb", "cb"));
        tests.add(test("cacac", "cacac"));
        tests.add(test("caca", "caca"));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String grid, String expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "Expected"}, true, grid, expected);

        String output;
        boolean pass, finalPass = true;

        Solution_ReverseInteration_TwoPointer solutionReverseInterationTwoPointer = new Solution_ReverseInteration_TwoPointer();
        output = solutionReverseInterationTwoPointer.lastSubstring(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Solution_ReverseInteration_TwoPointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_TwoPointers_WithDiff solutionTwoPointersWithDiff = new Solution_TwoPointers_WithDiff();
        output = solutionTwoPointersWithDiff.lastSubstring(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"solutionTwoPointersWithDiff", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_ReverseInteration_TwoPointer {
        public String lastSubstring(String s) {
            char[] string = s.toCharArray();
            int n = string.length;

            //assume that my last suffix is the largest
            int maxIndex = n - 1;
            int i, j;

            //compare with all before the last suffix
            int current = n - 2;

            while (current >= 0) {

                if (string[current] > string[maxIndex]) {
                    //if current char is greater in lexicographically, then a result must start from here only
                    maxIndex = current;
                } else if (string[current] == string[maxIndex]) {
                    //if both are the same, then we need to check, which substring, [current....n-1] or [maxIndex... n-1] holds the better result
                    i = current + 1; // pointer for [current....n-1]
                    j = maxIndex + 1; // pointer for [maxIndex... n-1]

                    //skip all the same character
                    while (i < maxIndex && j < n && string[i] == string[j]) {
                        i++;
                        j++;
                    }

                    //now following possibilities, are there
                    //1. if both substring [current....n-1] & [maxIndex... n-1] are same, then regardless, we can keep same maxIndex or updated to current
                    // i.e., i == maxIndex or j == n
                    //2. if above not, then it is only possible that the either of the string is bigger one
                    // [current... i...n-1] & [maxIndex...j..n-1] now if the string S1 [i...n-1] vs S2 [j....n-1] if S1 > S2 then we must updated maxIndex=current
                    //otherwise our maxIndex is already a better solution

                    if (i == maxIndex || j == n || string[i] > string[j])
                        maxIndex = current;
                }
                current--;
            }

            return s.substring(maxIndex);
        }
    }

    /**
     * Reference: https://github.com/doocs/leetcode/blob/main/solution/1100-1199/1163.Last%20Substring%20in%20Lexicographical%20Order/README_EN.md
     */
    static class Solution_TwoPointers_WithDiff {
        public String lastSubstring(String s) {
            char[] string = s.toCharArray();
            int n = string.length;

            //assume that my last suffix is the largest
            int maxIndex = 0;
            int current = 1;
            int k = 0; // common character b/w the substring [maxIndex....k] & [current...k]

            while (current + k < n) {

                int d = string[maxIndex + k] - string[current + k];

                if (d == 0) { //they are equal
                    k++; // since character is same, we increase the lenght b/w them
                } else if (d < 0) {
                    //means substring  [current...k] is bigger then we should reset our maxIndex
                    maxIndex = maxIndex + k + 1; //means ignore all the substring [maxIndex...k] as they are lexcographically smaller
                    k = 0; // now there is no common char's

                    //if maxIndex moved ahead than current, then reset current to compare further
                    if (maxIndex >= current)
                        current = maxIndex + 1;
                } else if (d > 0) {
                    //means substring  [maxIndex...k] is bigger then, then we can ignore all the substring [current...k] as they are smaller
                    current = current + k + 1;
                    k = 0;
                }
            }


            return s.substring(maxIndex);
        }
    }
}
