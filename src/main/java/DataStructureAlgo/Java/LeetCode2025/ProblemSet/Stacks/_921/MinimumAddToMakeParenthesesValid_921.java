package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks._921;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date:09/10/24
 * Question Category: 921. Minimum Add to Make Parentheses Valid
 * Description: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/?envType=daily-question&envId=2024-10-09
 * A parentheses string is valid if and only if:
 *
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 *
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 * Return the minimum number of moves required to make s valid.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "())"
 * Output: 1
 * Example 2:
 *
 * Input: s = "((("
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'.
 * File reference
 * -----------
 * Duplicate {@link }
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Stack
 * @String
 * @Greedy
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Microsoft
 * @Amazon
 * @Twitter
 * @ServiceNow
 *
 * @Editorial
 */

public class MinimumAddToMakeParenthesesValid_921 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("())", 1);
        test &= test("(((", 3);
        test &= test("(((((()))", 3);
        test &= test("((()))", 0);
        test &= test(")", 1);
        test &= test("(", 1);
        test &= test("()))((", 4);

        CommonMethods.printAllTestOutCome(test);

    }

    private static boolean test(String s, int expected) {
      System.out.println("---------------------------------");
        System.out.println("Input :" + s + "Expected :" + expected);
        Solution solution = new Solution();
        System.out.println("Output :" + solution.minAddToMakeValid(s));
        return solution.minAddToMakeValid(s) == expected;
    }
    static class Solution {
        public int minAddToMakeValid(String s) {

            if(s == null || s.isEmpty())
                return 0;

            char []stack = new char[s.length()];
            int top = -1;  //top of stack or total open count
            int closedCount = 0;

            for(char c: s.toCharArray()){
                if(c == '(')
                    stack[++top] = '(';
                if(c == ')'){
                    if ( top >=0 )
                        top -- ; //pop it
                    else
                        closedCount++;
                }
            }
            return top == -1 ? closedCount : top+closedCount+1;
        }
    }


}
