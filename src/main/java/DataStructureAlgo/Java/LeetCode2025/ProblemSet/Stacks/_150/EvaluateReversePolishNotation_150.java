package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks._150;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date:09/08/24
 * Question Category:
 * Description: 150. Evaluate Reverse Polish Notation @medium
 * <p>
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * <p>
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 * <p>
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Math
 * @Stack <p>
 * Company Tags
 * -----
 * @Google
 * @LinkedIn
 * @Amazon
 * @Microsoft
 * @Facebook
 * @Editorial
 */

public class EvaluateReversePolishNotation_150 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new String[]{"2", "1", "+", "3", "*"}, 9);
        test &= test(new String[]{"4", "13", "5", "/", "+"}, 6);
        test &= test(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22);
        System.out.println(test ? " All passed" : "Something failed");

    }

    private static boolean test(String[] tokens, int expected) {
        EvaluateReversePolishNotation.SolutionUsingStacks solution = new EvaluateReversePolishNotation.SolutionUsingStacks();
        System.out.println("\nInput : " + Arrays.toString(tokens) + " expected :" + expected);
        int output = solution.evalRPN(tokens);
        boolean test = output == expected;
        System.out.println("Output :" + output + " expected :" + expected + " result :" + (test ? "Passed" : "Failed"));
        return test;
    }
}

class EvaluateReversePolishNotation {
    static class SolutionUsingStacks {
        private final Set<String> airthmaticOperators = Set.of("+", "-", "*", "/");

        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0)
                return -1; //invalid

            final Stack<Integer> stack = new Stack<>();

            for (String token : tokens) {

                if(isAirthmaticOperator(token))
                    stack.push(performAirthmaticOperation(stack.pop(), stack.pop(), token));
                else
                    stack.push(Integer.parseInt(token));

            }
            return stack.pop();
        }

        private boolean isAirthmaticOperator(String token) {
            return airthmaticOperators.contains(token);
        }

        private int performAirthmaticOperation(int x, int y, String operator) {
            switch (operator) {
                case "+":
                    return x + y;
                case "-":
                    return y - x;
                case "*":
                    return y * x;
                case "/":
                    return y / x;
            }
            return 0;
        }
    }

    static class SolutionUsingDeque {
        private final Set<String> airthmaticOperators = Set.of("+", "-", "*", "/");

        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0)
                return -1; //invalid

            final Deque<Integer> stack = new ArrayDeque<>();

            for (String token : tokens) {

                if(isAirthmaticOperator(token))
                    stack.push(performAirthmaticOperation(stack.pop(), stack.pop(), token));
                else
                    stack.push(Integer.parseInt(token));

            }
            return stack.pop();
        }

        private boolean isAirthmaticOperator(String token) {
            return airthmaticOperators.contains(token);
        }

        private int performAirthmaticOperation(int x, int y, String operator) {
            switch (operator) {
                case "+":
                    return x + y;
                case "-":
                    return y - x;
                case "*":
                    return y * x;
                case "/":
                    return y / x;
            }
            return 0;
        }
    }
}