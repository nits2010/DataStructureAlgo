package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.LeetCode2025.easy.stacks.ValidParentheses_20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-24
 * Description: https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
 * <p>
 * Given an expression string exp , write a program to examine whether the pairs and the orders of “{“,”}”,”(“,”)”,”[“,”]” are correct in exp.
 * <p>
 * Example:
 * <p>
 * Input: exp = “[()]{}{[()()]()}”
 * Output: Balanced
 * <p>
 * Input: exp = “[(])”
 * Output: Not Balanced
 * <p>
 * [Amazon] [Adobe] [Linkdin} {facebook} [microsoft] [bloomberg]
 * {@link ValidParentheses_20}
 */
public class ValidParenthesesBalancedParenthesesExpression {

    public static void main(String[] args) {
        test(("[()]{}{[()()]()}"), true);
        test("[(])", false);
        test("[{()}]", true);
        test("[(){}]", true);
        test("[{()}]", true);
        test("[{(})]", false);
        test("[", false);
        test("]", false);

        /*
        Work with only implicit and stack class implementation
         */
//        test("(A+b) - (c+d) * ( (e+f) / { (g+h) - [k+l] } )", true);


    }

    private static void test(String expression, boolean expected) {
        System.out.println("\n input : " + expression);
        System.out.println("Stack Class :" + isValidStackClass(expression) + " should be " + expected);
        System.out.println("Implicit Stack  :" + isValidImplicitStack(expression) + " should be " + expected);
        System.out.println(" Stack & map :" + isValidUsingMapAndStack(expression) + " should be " + expected);
    }

    /**
     * In order to be the expression valid, there should be same number of open and close bracket of each
     * type and as well as they should be in order
     * Since [ ( { } ) ] is valid whereas [ ( { ) } ] is not as { are not ordered}
     * <p>
     * Algo:
     * 1. Simply push the '{' or '(' or '[' in stack
     * 2. if its closing then see what is there in top of the stack. If match than continue otherwise not valid
     * 3. if stack is empty then not valid which means there either open > close or close > open
     * <p>
     * O(n)/O(n)
     * <p>
     * Runtime: 1 ms, faster than 98.49% of Java online submissions for Valid Parentheses.
     * Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Valid Parentheses.
     *
     * @param expression
     * @return
     */
    private static boolean isValidStackClass(String expression) {

        if (expression == null || expression.isEmpty())
            return true;

        System.out.println("\nInput :" + expression);

        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {

            switch (c) {

                case '{':
                case '(':
                case '[':
                    stack.push(c);
                    break;

                case '}':
                    if (!stack.isEmpty()) {
                        if (stack.peek() != '{')
                            return false;
                        stack.pop();
                    } else
                        return false;

                    break;

                case ')':
                    if (!stack.isEmpty()) {
                        if (stack.peek() != '(')
                            return false;
                        stack.pop();
                    } else
                        return false;
                    break;

                case ']':
                    if (!stack.isEmpty()) {
                        if (stack.peek() != '[')
                            return false;
                        stack.pop();
                    } else
                        return false;
                    break;

                default: //ignore the characters
                    continue;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Other way would be instead of pushing open brackets, we can push the close brackets. and match with upcoming close brackets.
     * Time Complexity:
     * Time : O(n)
     *
     * <p>
     * Runtime: 2 ms, faster than 60.98% of Java online submissions for Valid Parentheses.
     * Memory Usage: 34.1 MB, less than 100.00% of Java online submissions for Valid Parentheses.
     *
     * @param expression
     * @return
     */
    private static boolean isValidUsingMapAndStack(String expression) {
        if (expression == null || expression.isEmpty())
            return true;

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {


            if (c == '[' || c == '(' || c == '{')
                stack.push(c);
            else {
                if (stack.isEmpty() || map.get(stack.peek()) != c)
                    return false;
                stack.pop();
            }


        }

        return stack.isEmpty();
    }

    /**
     * Avoid using Stack class, simply implement your own stack using array.
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Parentheses.
     * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Valid Parentheses.
     * <p>
     * O(n)/O(n)
     *
     * @param expression
     * @return
     */
    private static boolean isValidImplicitStack(String expression) {
        char[] stack = new char[expression.length()];
        int idx = 0;

        for (int i = 0; i < expression.length(); i++) {
            char x = expression.charAt(i);

            if (x == '[' || x == '{' || x == '(') {
                stack[idx++] = x;

            } else if (x == ']' || x == '}' || x == ')') {

                /**
                 * Is empty stack ?
                 */
                if (idx == 0)
                    return false;

                char y = stack[idx - 1];

                if ((x == ']' && y == '[') || (x == '}' && y == '{') || (x == ')' && y == '(')) {
                    stack[--idx] = 0;
                } else
                    return false;
            }
        }
        return idx == 0;
    }
}
