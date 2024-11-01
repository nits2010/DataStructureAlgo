package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks._1106;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 10/20/2024
 * Question Category: 1106. Parsing A Boolean Expression
 * Description: https://leetcode.com/problems/parsing-a-boolean-expression
 * A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:
 * <p>
 * 't' that evaluates to true.
 * 'f' that evaluates to false.
 * '!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
 * '&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
 * '|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
 * Given a string expression that represents a boolean expression, return the evaluation of that expression.
 * <p>
 * It is guaranteed that the given expression is valid and follows the given rules.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: expression = "&(|(f))"
 * Output: false
 * Explanation:
 * First, evaluate |(f) --> f. The expression is now "&(f)".
 * Then, evaluate &(f) --> f. The expression is now "f".
 * Finally, return false.
 * Example 2:
 * <p>
 * Input: expression = "|(f,f,f,t)"
 * Output: true
 * Explanation: The evaluation of (false OR false OR false OR true) is true.
 * Example 3:
 * <p>
 * Input: expression = "!(&(f,t))"
 * Output: true
 * Explanation:
 * First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
 * Then, evaluate !(f) --> NOT false --> true. We return true.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= expression.length <= 2 * 104
 * expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @String
 * @Stack
 * @Recursion <p><p>
 * Company Tags
 * -----
 * @Affinity <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion {@link SolutionStack}
 */
public class ParsingABooleanExpression_1106 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("&(t,f)", false);
        test &= test("!(t)", false);
        test &= test("!(f)", true);
        test &= test("&(|(f))", false);
        test &= test("|(f,f,f,t)", true);
        test &= test("!(&(f,t))", true);
        test &= test("!(&(&(!(&(t,t,t,t,t,f)),t),f,f,f,f))", true);

        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(String expression, boolean expected) {
        System.out.println("---------------------------------------------");
        System.out.println("Expression: " + expression + " Expected: " + expected);

        SolutionStack solutionStack = new SolutionStack();
        boolean result = solutionStack.parseBoolExpr(expression);
        boolean pass = result == expected;
        System.out.println("Result: " + result + " Pass: " + (pass ? "Pass" : "Fail"));
        return pass;
    }

    static class SolutionStack {
        final char open = '(', close = ')', and = '&', or = '|', neg = '!', t = 't', f = 'f', comma = ',';


        public boolean parseBoolExpr(String expression) {
            Stack<Character> stack = new Stack<>();

            char[] chars = expression.toCharArray();
            int cFalse, cTrue;
            for (char c : chars) {
                if (c != open && c != comma) {
                    if (c == and || c == or || c == neg || c == f || c == t)
                        stack.push(c);
                }
                if (c == close) {
                    cFalse = 0; cTrue = 0;
                    while (!stack.isEmpty()) {
                        char current = stack.pop();
                        if (current == t) {
                            cTrue++;
                        } else if (current == f) {
                            cFalse++;
                        } else {
                            stack.push(operation(cTrue, cFalse, current));
                            break;

                        }

                    }

                }
            }
            return stack.pop() == t;
        }

        private char operation(int tCount, int fCount, char operation) {
            if (operation == or)
                return (tCount == 0 ? f : t);
            if (operation == and)
                return fCount > 0 ? f : t;
            return (tCount == 0) ? 't' : 'f';

        }

    }
}
