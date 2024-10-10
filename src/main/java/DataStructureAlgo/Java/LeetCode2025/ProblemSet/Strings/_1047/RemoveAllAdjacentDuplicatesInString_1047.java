package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._1047;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/7/2024
 * Question Category: 1047. Remove All Adjacent Duplicates In String
 * Description: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * Example 2:
 *
 * Input: s = "azxxzy"
 * Output: "ay"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.RemoveAll2AdjacentDuplicatesString}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._2696.MinimumStringLengthAfterRemoving_2696}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 * @String
 * @Stack
 *
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial
 */
public class RemoveAllAdjacentDuplicatesInString_1047 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("abbaca", "ca");
        test &= test("aaaaa", "a");
        test &= test("azxxzy", "ay");
        test &= test("geeksforgeeg", "gksfor");
        test &= test("caaabbbaacdddd", "cabc");
        CommonMethods.printResult(test);
    }

    private static boolean test(String s, String expected) {
        System.out.println("----------------------------------");
        System.out.println("Input : " + s + "Expected : " + expected);
        Solution solution = new Solution();
        String result = solution.removeDuplicates(s);
        System.out.println("Output : " + result);
        return result.equals(expected);
    }

    static class Solution {
        public String removeDuplicates(String s) {
            if(s == null || s.isEmpty())
                return s;

            int i = 0, j = 1;
            char []st = s.toCharArray();
            int n = s.length();

            while(j<n){
                if( i >= 0 && st[i] == st[j]){
                    i--;
                    j++;
                }else{
                    st[++i] = st[j++];
                }
            }
            return new String(st, 0, i+1);
        }
    }
}
