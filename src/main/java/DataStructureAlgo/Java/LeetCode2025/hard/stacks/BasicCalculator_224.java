package DataStructureAlgo.Java.LeetCode2025.hard.stacks;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date:09/08/24
 * Question Category: 224. Basic Calculator @hard
 * Description: <a href="https://leetcode.com/problems/basic-calculator/description">...</a>
 * <p>
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 * <p>
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * <p>
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists of digits, '+', '-', '(', ')', and ' '.
 * s represents a valid expression.
 * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 * There will be no two consecutive operators in the input.
 * Every number and running calculation will fit in a signed 32-bit integer.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @hard
 * @Math
 * @String
 * @Stack
 * @Recursion <p>
 * Company Tags
 * -----
 * @Google
 * @Facebook
 * @Amazon
 * @DoorDash
 * @Uber
 *
 *
 * @Editorial <a href="https://leetcode.com/problems/basic-calculator/solutions/5612137/thought-process-building-solution-explanation-o-n-recursive-iterative/">...</a>
 * @VideoSolution <a href="https://www.youtube.com/watch?v=Q193Tqb7_9A">...</a>
 */

public class BasicCalculator_224 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test("(7)-(0)+(4)", 11);
        test &= test("1 + 1", 2);
        test &= test(" 2-1 + 2 ", 3);
        test &= test("(1+(4+5+2)-3)+(6+8)", 23);
        test &= test("2147483647", 2147483647);
        System.out.println((test ? "All Passed" : "Failed"));
    }

    private static boolean test(String s, int expected) {
        BasicCalculator.SolutionRecursive solutionRecursive = new BasicCalculator.SolutionRecursive();
        BasicCalculator.SolutionIterative solutionIterative = new BasicCalculator.SolutionIterative();
        System.out.println("\n input : " + s + " expected : " + expected);
        int outRecursive = solutionRecursive.calculate(s);
        int outIterative = solutionIterative.calculate(s);
        boolean outRecursiveResult = outRecursive == expected;
        boolean outIterativeResult = outIterative == expected;
        System.out.println("Recursive : " + outRecursive + " result : " + outRecursiveResult);
        System.out.println("Iterative : " + outIterative + " result : " + outIterativeResult);
        return outRecursiveResult && outIterativeResult;
    }
}

class BasicCalculator {


    static class SolutionIterative {
        public int calculate(String s) {
            if (s == null || s.isEmpty())
                return 0;
            if (s.length() == 1 && Character.isDigit(s.charAt(0)))
                return Integer.parseInt(s);
            return calculate(s.toCharArray());
        }

        private int calculate(char[] s) {
            int currentSum = 0;
            int operationSign = 1;  //+(1) or -(-1)
            int digit = 0; //Represent the integer number aka last previous/current operand
            int index = 0;
            Stack<Integer> stack = new Stack<>();

            while (index < s.length) {


                if (Character.isDigit(s[index])) {

                    //form the number
                    StringBuilder digits = new StringBuilder();
                    while (index < s.length && Character.isDigit(s[index])) {
                        digits.append(s[index++]);
                    }
                    //since the loop is broken, which could be because of isDigitFail on index, hence we need to go back;
                    --index;
                    digit = Integer.parseInt(digits.toString());

                } else {

                    //operator appear, lets calculate currentSum with respect to this number
                    switch (s[index]) {

                        case '+':
                            //let's calculate currentSum with respect to this number.
                            //Notice, we dont know the next operand yet, which could be a operator or a operand.
                            //hence we need to cache the solution till this character.
                            //digit is the last operand, which is not yet added to currentSum
                            currentSum += operationSign * digit;

                            //set operator
                            operationSign = 1; // make a positive operator

                            //since the current number which was previously formed is over now {added in current sum}
                            // as operator appear
                            digit = 0;
                            break;
                        case '-':
                            //operator appear, lets calculate currentSum with respect to this number
                            currentSum += operationSign * digit;

                            operationSign = -1; // make a positive operator
                            digit = 0; //since the current number which was previously formed is over now as operator appear
                            break;
                        case '(':
                            //a new problem start, hence cache the current result
                            stack.push(currentSum);
                            stack.push(operationSign);

                            //since we have cached the sum, reset the currentSum and operationSign
                            currentSum = 0;
                            operationSign = 1;
                            break;
                        case ')':
                            //current problem is over, lets calculate
                            currentSum += operationSign * digit;
                            //since we have cached the sum, reset the currentSum and operationSign
                            currentSum = currentSum * stack.pop();
                            currentSum = currentSum + stack.pop();
                            digit = 0;
                            break;
                    }
                }

                ++index;

            }
            if(digit !=0)
                currentSum += operationSign*digit;

            return currentSum;
        }
    }

    /**
     * Explanation: See editorial
     */
    static class SolutionRecursive {

        int index = 0;

        public int calculate(String s) {
            if (s == null || s.isEmpty())
                return 0;
            if (s.length() == 1 && Character.isDigit(s.charAt(0)))
                return Integer.parseInt(s);

            return dfs(s.toCharArray());

        }

        private int dfs(char[] s) {

            int currentSum = 0;
            int operationSign = 1; //+(1) or -(-1)

            while (index < s.length) {

                char c = s[index];

                if (c == ')')
                    break; // this problem is over.
                if (c == '+')
                    operationSign = 1; // make a positive operator
                else if (c == '-')
                    operationSign = -1; // make a -ve operator
                else if (c == '(') {
                    ++index;
                    //so that, next character get scanned in dfs.
                    // dfs will give the solution of the sub-problem
                    //that needs to be appended to current problem.
                    currentSum += operationSign * dfs(s);
                } else if (Character.isDigit(c)) {
                    //since current is a digit, we have to make a full digit.
                    //current digit can be of single (0-9) or greater than that.
                    StringBuilder digits = new StringBuilder();
                    while (index < s.length && Character.isDigit(s[index])) {
                        digits.append(s[index]);
                        index++;

                    }

                    //since the loop is broken, which could be because of isDigitFail on index, hence we need to go back;
                    --index;
                    int d = Integer.parseInt(digits.toString());
                    currentSum += operationSign * d;
                }
                ++index;

            }
            return currentSum;

        }
    }
}