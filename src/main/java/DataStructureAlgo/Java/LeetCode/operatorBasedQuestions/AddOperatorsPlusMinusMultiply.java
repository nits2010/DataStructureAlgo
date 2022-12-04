package DataStructureAlgo.Java.LeetCode.operatorBasedQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-06
 * Description: https://leetcode.com/problems/expression-add-operators/
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * <p>
 * Example 1:
 * <p>
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 * <p>
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 * <p>
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 * <p>
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 * <p>
 * Input: num = "3456237490", target = 9191
 * Output: []
 *
 * https://leetcode.com/problems/expression-add-operators/discuss/352605/Explanation-and-way-to-make-it-efficient-from-134-ms-to-11-ms
 * http://codinghelmet.com/exercises/expression-from-numbers
 * https://leetcode.com/discuss/interview-question/351147/google-onsite-arithmetic-expression-to-construct-a-value
 */
public class AddOperatorsPlusMinusMultiply {
    public static void main(String[] args) {

        test("123", 6, Arrays.asList("1+2+3", "1*2*3"));
        test("232", 8, Arrays.asList("2*3+2", "2+3*2"));
        test("105", 5, Arrays.asList("1*0+5", "10-5"));
        test("00", 0, Arrays.asList("0+0", "0-0", "0*0"));
        test("3456237490", 9191, Arrays.asList());

    }

    private static void test(String num, int target, List<String> expected) {
        AddOperatorsBacktrackingCharArrays addOperatorsBacktracking = new AddOperatorsBacktrackingCharArrays();
        System.out.println(" Expression " + num + " target : " + target + " \n Obtained ->" + addOperatorsBacktracking.addOperators(num, target) + " expected :" + expected);

    }
}

/**
 * Explanation: https://leetcode.com/articles/expression-add-operators/
 * <p>
 * Our choices:
 * 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
 * 2. we can choose only one operator between two operands
 * <p>
 * Our Constraints:
 * 1. We can't take more numbers than given in input string ( index >= input.length)
 * 2. We can't take more operators then available in input
 * 3. We can choose only one operator once
 * 4. We need two operands for a operator and operator can't be apply on single operand
 * <p>
 * Out Goal:
 * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
 * 2. if not, we discard
 * <p>
 * <p>
 * Runtime: 134 ms, faster than 30.99% of Java online submissions for Expression Add Operators.
 * Memory Usage: 63.4 MB, less than 14.58% of Java online submissions for Expression Add Operators.
 */
class AddOperatorsBacktrackingString {


//    char ops[] = {'+', '-', '*'};

    public List<String> addOperators(String num, int target) {

        List<String> expressions = new ArrayList<>();

        if (num == null || num.isEmpty())
            return expressions;


        addOperators(num, target, 0, 0, 0, "", expressions);

        return expressions;


    }

    /**
     * @param num
     * @param target
     * @param index
     * @param currentValue
     * @param lastValue
     * @param expression
     * @param result
     */
    private void addOperators(String num, int target, int index, long currentValue, long lastValue, String expression, List<String> result) {

        /**
         * Our Constraints:
         * 1. We can't take more numbers than given in input string ( index >= input.length)
         */

        if (index == num.length()) {

            /**
             * Out Goal:
             * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
             */

            if (currentValue == target) {
                //then this is our solution.
                result.add(expression);

            } //2. if not, we discard


            return;

        }

        /**
         * Our choices:
         * 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
         */
        for (int i = index; i < num.length(); i++) {


            /**
             * We don't consider a operand which is 0 as single digit operand, as operand like 0 or 01 , 023... does not make sense
             *  To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
             */
            if (i != index && num.charAt(index) == '0')
                break;

            long currentDigitsValue = Long.parseLong(num.substring(index, i + 1));


            /**
             * Our Constraints:
             * 4. We need two operands for a operator and operator can't be apply on single operand
             */

            if (index == 0) {
                // as this is the first digit only, then don't apply any operator

                addOperators(num, target, i + 1, currentDigitsValue, currentDigitsValue, expression + currentDigitsValue, result);

            } else {
                //We have two operands, last and current

                /**
                 * Plus operator application '+'; Current value become = so far value + current digit value and last value would be the current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 + 5 = 17
                 * So last value would be 5
                 */
                addOperators(num, target, i + 1, currentValue + currentDigitsValue, currentDigitsValue, expression + "+" + currentDigitsValue, result);

                /**
                 * Minus operator application '-'; Current value become = so far value - current digit value and last value would be the -current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 - 5 = 7
                 * So last value would be -5
                 */
                addOperators(num, target, i + 1, currentValue - currentDigitsValue, -currentDigitsValue, expression + "-" + currentDigitsValue, result);


                /**
                 * Multiply operator application '*'; As this has the highest precedence then + and -, we simply can't apply * on last and current value.
                 * Current value become = currentValue - lastValue + last*currentDigitvalue;
                 * For example
                 * current value = 12 ,
                 * last value = 2 ( let say we applied + as 10 + 2 )
                 * currendDigitValue = 4
                 * so expression become : 10 + 2 * 4
                 * if we simply do 12 * 4 = 24 which is wrong as 10 + 2 * 4 = 10 + 8 = 18
                 *
                 * New value = 10 + 2 * 4 = 18
                 * last value = 2*4 = 8
                 *
                 *
                 */
                addOperators(num, target, i + 1, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, expression + "*" + currentDigitsValue, result);


            }


        }


    }

}

/**
 * Instead of using String concatenation we'll use strinbuilder
 * Runtime: 81 ms, faster than 83.65% of Java online submissions for Expression Add Operators.
 * Memory Usage: 57.9 MB, less than 53.12% of Java online submissions for Expression Add Operators.
 * <p>
 * StringBuilder boost it performance from 84ms to 81ms.
 */
class AddOperatorsBacktrackingStringBuilder {


    public List<String> addOperators(String num, int target) {

        List<String> expressions = new ArrayList<>();

        if (num == null || num.isEmpty())
            return expressions;


        addOperators(num, target, 0, 0, 0, new StringBuilder(), expressions);

        return expressions;


    }

    /**
     * @param num
     * @param target
     * @param index
     * @param currentValue
     * @param lastValue
     * @param expression
     * @param result
     */
    private void addOperators(String num, int target, int index, long currentValue, long lastValue, StringBuilder expression, List<String> result) {

        /**
         * Our Constraints:
         * 1. We can't take more numbers than given in input string ( index >= input.length)
         */

        if (index == num.length()) {

            /**
             * Out Goal:
             * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
             */

            if (currentValue == target) {
                //then this is our solution.
                result.add(expression.toString());

            } //2. if not, we discard


            return;

        }

        /**
         * Our choices:
         * 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
         */
        for (int i = index; i < num.length(); i++) {


            /**
             * We don't consider a operand which is 0 as single digit operand, as operand like 0 or 01 , 023... does not make sense
             *  To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
             */
            if (i != index && num.charAt(index) == '0')
                break;

            long currentDigitsValue = Long.parseLong(num.substring(index, i + 1));
            int len = expression.length();

            /**
             * Our Constraints:
             * 4. We need two operands for a operator and operator can't be apply on single operand
             */

            if (index == 0) {
                // as this is the first digit only, then don't apply any operator


                addOperators(num, target, i + 1, currentDigitsValue, currentDigitsValue, expression.append(currentDigitsValue), result);
                expression.setLength(len); // This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.

            } else {
                //We have two operands, last and current

                /**
                 * Plus operator application '+'; Current value become = so far value + current digit value and last value would be the current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 + 5 = 17
                 * So last value would be 5
                 */
                addOperators(num, target, i + 1, currentValue + currentDigitsValue, currentDigitsValue, expression.append("+").append(currentDigitsValue), result);
                expression.setLength(len);// This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.

                /**
                 * Minus operator application '-'; Current value become = so far value - current digit value and last value would be the -current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 - 5 = 7
                 * So last value would be -5
                 */
                addOperators(num, target, i + 1, currentValue - currentDigitsValue, -currentDigitsValue, expression.append("-").append(currentDigitsValue), result);
                expression.setLength(len);// This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.

                /**
                 * Multiply operator application '*'; As this has the highest precedence then + and -, we simply can't apply * on last and current value.
                 * Current value become = currentValue - lastValue + last*currentDigitvalue;
                 * For example
                 * current value = 12 ,
                 * last value = 2 ( let say we applied + as 10 + 2 )
                 * currendDigitValue = 4
                 * so expression become : 10 + 2 * 4
                 * if we simply do 12 * 4 = 24 which is wrong as 10 + 2 * 4 = 10 + 8 = 18
                 *
                 * New value = 10 + 2 * 4 = 18
                 * last value = 2*4 = 8
                 *
                 *
                 */
                addOperators(num, target, i + 1, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, expression.append("*").append(currentDigitsValue), result);
                expression.setLength(len);// This will make sure that this expression won't exceed otherwise we need to remove "currentDigitsValue" from this to backtrack.

            }


        }


    }

}

/**
 * Instead of using String concatenation Or strinbuilder, we'll use char array
 * Runtime: 11 ms, faster than 96.45% of Java online submissions for Expression Add Operators.
 * Memory Usage: 39.4 MB, less than 95.83% of Java online submissions for Expression Add Operators.
 */

class AddOperatorsBacktrackingCharArray {


    public List<String> addOperators(String num, int target) {

        List<String> expressions = new ArrayList<>();

        if (num == null || num.isEmpty())
            return expressions;

        char cache[] = new char[2 * num.length()];

        addOperators(num, target, 0, 0, 0, cache, 0, expressions);

        return expressions;


    }

    /**
     * @param num
     * @param target
     * @param index
     * @param currentValue
     * @param lastValue
     * @param expression
     * @param result
     */
    private void addOperators(String num, int target, int index, long currentValue, long lastValue, char[] expression, int expressionEndIndex, List<String> result) {

        /**
         * Our Constraints:
         * 1. We can't take more numbers than given in input string ( index >= input.length)
         */

        if (index == num.length()) {

            /**
             * Out Goal:
             * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
             */

            if (currentValue == target) {
                //then this is our solution.
                result.add(new String(expression, 0, expressionEndIndex));

            } //2. if not, we discard


            return;

        }

        /**
         * Find new cache length;
         * the corner case, where we have no data in cache, in this case we need to push at first index (0) otherwise at the next index
         *
         */
        int nextExpressionEndIndex = (index == 0) ? expressionEndIndex : expressionEndIndex + 1;

        long currentDigitsValue = 0;
        /**
         * Our choices:
         * 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
         */
        for (int i = index; i < num.length(); i++) {


            /**
             * We don't consider a operand which is 0 as single digit operand, as operand like 0 or 01 , 023... does not make sense
             *  To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
             */
            if (i != index && num.charAt(index) == '0')
                break;

            currentDigitsValue = currentDigitsValue * 10 + num.charAt(i) - '0';


            /**
             * Our Constraints:
             * 4. We need two operands for a operator and operator can't be apply on single operand
             */

            if (index == 0) {
                // as this is the first digit only, then don't apply any operator
                //Append
                expression[nextExpressionEndIndex++] = num.charAt(i);


                addOperators(num, target, i + 1, currentDigitsValue, currentDigitsValue, expression, nextExpressionEndIndex, result);

            } else {
                //We have two operands, last and current
                //Append
                expression[nextExpressionEndIndex++] = num.charAt(i);

                /**
                 * Plus operator application '+'; Current value become = so far value + current digit value and last value would be the current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 + 5 = 17
                 * So last value would be 5
                 */
                expression[expressionEndIndex] = '+';
                addOperators(num, target, i + 1, currentValue + currentDigitsValue, currentDigitsValue, expression, nextExpressionEndIndex, result);

                /**
                 * Minus operator application '-'; Current value become = so far value - current digit value and last value would be the -current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 - 5 = 7
                 * So last value would be -5
                 */
                expression[expressionEndIndex] = '-';
                addOperators(num, target, i + 1, currentValue - currentDigitsValue, -currentDigitsValue, expression, nextExpressionEndIndex, result);

                /**
                 * Multiply operator application '*'; As this has the highest precedence then + and -, we simply can't apply * on last and current value.
                 * Current value become = currentValue - lastValue + last*currentDigitvalue;
                 * For example
                 * current value = 12 ,
                 * last value = 2 ( let say we applied + as 10 + 2 )
                 * currendDigitValue = 4
                 * so expression become : 10 + 2 * 4
                 * if we simply do 12 * 4 = 24 which is wrong as 10 + 2 * 4 = 10 + 8 = 18
                 *
                 * New value = 10 + 2 * 4 = 18
                 * last value = 2*4 = 8
                 *
                 *
                 */
                expression[expressionEndIndex] = '*';
                addOperators(num, target, i + 1, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, expression, nextExpressionEndIndex, result);

            }


        }


    }

}


/**
 * Instead of using String concatenation Or stringbuilder, we'll use char array
 * Runtime: 9 ms, faster than 99.28% of Java online submissions for Expression Add Operators.
 * Memory Usage: 39.5 MB, less than 94.79% of Java online submissions for Expression Add Operators.
 */
class AddOperatorsBacktrackingCharArrays {


    public List<String> addOperators(String num, int target) {

        List<String> expressions = new ArrayList<>();

        if (num == null || num.isEmpty())
            return expressions;

        char cache[] = new char[2 * num.length()];

        addOperators(num.toCharArray(), target, 0, 0, 0, cache, 0, expressions);

        return expressions;


    }

    /**
     * @param num
     * @param target
     * @param index
     * @param currentValue
     * @param lastValue
     * @param expression
     * @param result
     */
    private void addOperators(char num[], int target, int index, long currentValue, long lastValue, char[] expression, int expressionEndIndex, List<String> result) {

        /**
         * Our Constraints:
         * 1. We can't take more numbers than given in input string ( index >= input.length)
         */

        if (index == num.length) {

            /**
             * Out Goal:
             * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
             */

            if (currentValue == target) {
                //then this is our solution.
                result.add(new String(expression, 0, expressionEndIndex));

            } //2. if not, we discard


            return;

        }

        /**
         * Find new cache length;
         * the corner case, where we have no data in cache, in this case we need to push at first index (0) otherwise at the next index
         *
         */
        int nextExpressionEndIndex = (index == 0) ? expressionEndIndex : expressionEndIndex + 1;

        long currentDigitsValue = 0;
        /**
         * Our choices:
         * 1. We can choose a single digits as operands Or multi digits as operand (  1 + 2 or 12 + 34 )
         */
        for (int i = index; i < num.length; i++) {


            /**
             * We don't consider a operand which is 0 as single digit operand, as operand like 0 or 01 , 023... does not make sense
             *  To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
             */
            if (i != index && num[index] == '0')
                break;

            currentDigitsValue = currentDigitsValue * 10 + num[i] - '0';


            /**
             * Our Constraints:
             * 4. We need two operands for a operator and operator can't be apply on single operand
             */

            if (index == 0) {
                // as this is the first digit only, then don't apply any operator
                //Append
                expression[nextExpressionEndIndex++] = num[i];


                addOperators(num, target, i + 1, currentDigitsValue, currentDigitsValue, expression, nextExpressionEndIndex, result);

            } else {
                //We have two operands, last and current
                //Append
                expression[nextExpressionEndIndex++] = num[i];

                /**
                 * Plus operator application '+'; Current value become = so far value + current digit value and last value would be the current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 + 5 = 17
                 * So last value would be 5
                 */
                expression[expressionEndIndex] = '+';
                addOperators(num, target, i + 1, currentValue + currentDigitsValue, currentDigitsValue, expression, nextExpressionEndIndex, result);

                /**
                 * Minus operator application '-'; Current value become = so far value - current digit value and last value would be the -current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 - 5 = 7
                 * So last value would be -5
                 */
                expression[expressionEndIndex] = '-';
                addOperators(num, target, i + 1, currentValue - currentDigitsValue, -currentDigitsValue, expression, nextExpressionEndIndex, result);

                /**
                 * Multiply operator application '*'; As this has the highest precedence then + and -, we simply can't apply * on last and current value.
                 * Current value become = currentValue - lastValue + last*currentDigitvalue;
                 * For example
                 * current value = 12 ,
                 * last value = 2 ( let say we applied + as 10 + 2 )
                 * currendDigitValue = 4
                 * so expression become : 10 + 2 * 4
                 * if we simply do 12 * 4 = 24 which is wrong as 10 + 2 * 4 = 10 + 8 = 18
                 *
                 * New value = 10 + 2 * 4 = 18
                 * last value = 2*4 = 8
                 *
                 *
                 */
                expression[expressionEndIndex] = '*';
                addOperators(num, target, i + 1, currentValue - lastValue + lastValue * currentDigitsValue, lastValue * currentDigitsValue, expression, nextExpressionEndIndex, result);

            }


        }


    }

}