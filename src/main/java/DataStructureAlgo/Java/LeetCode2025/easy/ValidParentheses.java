package DataStructureAlgo.Java.LeetCode2025;

import DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2024-07-23
 * Description: https://leetcode.com/problems/valid-parentheses/description/
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 * [Amazon] [Adobe] [Linkdin} {facebook} [microsoft] [bloomberg]
 * {@link DataStructureAlgo.Java.LeetCode.ValidParenthesesBalancedParenthesesExpression}
 */
public class ValidParentheses {

    public static void main(String[] args) {
        test("()", true);
        test("()[]{}", true);
        test("(]", false);
        test("([]{})", true);
        test("([]{)}", false);
        test("([{})", false);
        test("){", false);


    }

    private static void test(String input, boolean expectedOutput) {
        Solution solution = new Solution();
        boolean result = solution.isValidImplicitStack(input);
        boolean output = result == expectedOutput;
        System.out.println("\n Input :" + input + "\n expectedOutput : " + expectedOutput + " returned output : " + result + " result : " + output);

    }


    static class Solution {

        /**
         * When you encounter a closing bracket, check if the top of the stack was the opening for it. If yes, pop it from the stack. Otherwise, return false.
         * In order to be the expression valid, there should be same number of open and close bracket of each
         * * type and as well as they should be in order
         * * Since [ ( { } ) ] is valid whereas [ ( { ) } ] is not as { are not ordered}
         * * <p>
         * * Algo:
         * * 1. Simply push the '{' or '(' or '[' in stack
         * * 2. if its closing then see what is there in top of the stack. If match than continue otherwise not valid
         * * 3. if stack is empty then not valid which means there either open > close or close > open
         * * <p>
         * * O(n)/O(n)
         * * <p>
         *https://leetcode.com/problems/valid-parentheses/submissions/1330888386/
         * @param s
         * @return
         */
        public boolean isValid(String s) {
            if (s == null || s.isEmpty())
                return true;

            if (s.length() % 2 != 0)
                return false;

            final Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || (c == ')' && stack.peek() != '(') || (c == '}' && stack.peek() != '{') || (c == ']' && stack.peek() != '['))
                        return false;
                    stack.pop();
                }


            }
            return stack.isEmpty();
        }

        /**
         * https://leetcode.com/problems/valid-parentheses/submissions/1330892878/
         * @param s
         * @return
         */
        public boolean isValidImplicitStack(String s) {
            if (s == null || s.isEmpty())
                return true;

            if (s.length() % 2 != 0)
                return false;

            char [] stack = new char[s.length()];
            int stackIndex = 0;

            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (c == '(' || c == '[' || c == '{') {
                    stack[stackIndex++]=c;
                } else {
                    if (stackIndex ==0 || (c == ')' && stack[stackIndex-1] != '(') || (c == '}' && stack[stackIndex-1] != '{') || (c == ']' && stack[stackIndex-1] != '['))
                        return false;
                    stackIndex--;
                }


            }
           return stackIndex == 0;
        }
    }
}
