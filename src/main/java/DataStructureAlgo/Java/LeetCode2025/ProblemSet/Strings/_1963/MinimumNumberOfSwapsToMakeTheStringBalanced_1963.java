package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._1963;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/8/2024
 * Question Category: 1963. Minimum Number of Swaps to Make the String Balanced
 * Description: https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
 * You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.
 *
 * A string is called balanced if and only if:
 *
 * It is the empty string, or
 * It can be written as AB, where both A and B are balanced strings, or
 * It can be written as [C], where C is a balanced string.
 * You may swap the brackets at any two indices any number of times.
 *
 * Return the minimum number of swaps to make s balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "][]["
 * Output: 1
 * Explanation: You can make the string balanced by swapping index 0 with index 3.
 * The resulting string is "[[]]".
 * Example 2:
 *
 * Input: s = "]]][[["
 * Output: 2
 * Explanation: You can do the following to make the string balanced:
 * - Swap index 0 with index 4. s = "[]][][".
 * - Swap index 1 with index 5. s = "[[][]]".
 * The resulting string is "[[][]]".
 * Example 3:
 *
 * Input: s = "[]"
 * Output: 0
 * Explanation: The string is already balanced.
 *
 *
 * Constraints:
 *
 * n == s.length
 * 2 <= n <= 106
 * n is even.
 * s[i] is either '[' or ']'.
 * The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @TwoPointers
 * @String
 * @Stack
 * @Greedy
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial https://leetcode.ca/2021-08-15-1963-Minimum-Number-of-Swaps-to-Make-the-String-Balanced/
 */
public class MinimumNumberOfSwapsToMakeTheStringBalanced_1963 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("][][", 1);
        test &= test("]]][[[", 2);
        test &= test("[]", 0);
        CommonMethods.printAllTestOutCome(test);
    }
    private static boolean test(String s, int expected) {
        System.out.println("----------------------------------");
        System.out.println("s = " + s + " expected = " + expected);

        Solution solution = new Solution();
        int output = solution.minSwaps(s);
        boolean pass = output == expected;
        System.out.println("Output = " + output + " Pass = " + (pass ? "Pass" : "Failed"));
        return pass;
    }

    static class Solution {
        public int minSwaps(String s) {

            if(s == null || s.isEmpty())
                return 0;

            int unbalancedOpen = 0;

            for(char c: s.toCharArray()){

                if(c == '[')
                    unbalancedOpen++;

                if(c == ']'){

                    //if there is an open bracket, then it must have matched to this close bracket
                    if(unbalancedOpen > 0)
                        unbalancedOpen--;

                }
            }

             return (int)Math.ceil((double)unbalancedOpen/2);

        }
    }
}
