package DataStructureAlgo.Java.LeetCode.operatorBasedQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-07
 * Description:
 * <p>
 * {@link AddOperatorsPlusMinusMultiply}
 * NOT Correct
 * */
public class AddOperatorsPlusMinusMultiplyDivide {

    public static void main(String[] args) {

//        test("489", 18, Arrays.asList("9*(8/4)"));
        test("679", 3, Arrays.asList("6/(9-7)"));
//        test("105", 5, Arrays.asList("1*0+5", "10-5"));
//        test("00", 0, Arrays.asList("0+0", "0-0", "0*0"));
//        test("3456237490", 9191, Arrays.asList());

    }

    private static void test(String num, int target, List<String> expected) {
        AddOperatorsPlusMinusMultiplyDivideBacktrackingString addOperatorsBacktracking = new AddOperatorsPlusMinusMultiplyDivideBacktrackingString();
        System.out.println(" Expression " + num + " target : " + target + " \n Obtained ->" + addOperatorsBacktracking.addOperators(num, target) + " expected :" + expected);

    }
}

//https://www.careercup.com/question?id=5735906000502784
class Sols {
    public static void main(String[] args) {
        System.out.println(decompose(new int[]{4, 8, 9}, 18));
        System.out.println(decompose(new int[]{6, 7, 9}, 3));
        System.out.println(decompose(new int[]{2, 3, 4, 5}, 18));
        System.out.println(decompose(new int[]{2, 3, 4}, 12));
        System.out.println(decompose(new int[]{1, 3, 4, 6}, 24));
        System.out.println(decompose(new int[]{1, 3, 4, 6}, 12));

    }

    public static String decompose(int[] a, int target) {
        LinkedList<Integer> x = new LinkedList<>();
        LinkedList<String> expr = new LinkedList<>();
        for (Integer y : a) {
            x.addLast(y);
            expr.addLast(y + "");
        }

        return decompose(x, expr, target);
    }

    private static String decompose(LinkedList<Integer> x, LinkedList<String> expr, int target) {
        if (x.size() == 1) {            // Base case
            if (x.get(0) == target) return (expr.get(0) + " = " + target);
            else return null;
        }

        for (int i = 0; i < x.size(); i++) {
            for (int j = i + 1; j < x.size(); j++) {
                int a = x.get(j);
                int b = x.get(i);

                String ae = expr.get(j);
                String be = expr.get(i);

                for (int k = 0; k < 5; k++) {

                    // Copy of the integer list
                    LinkedList<Integer> xnew = new LinkedList<>();
                    for (Integer y : x) {
                        xnew.addLast(y);
                    }
                    xnew.remove(j);
                    xnew.remove(i);
                    // Copy of the expression string list
                    LinkedList<String> exprnew = new LinkedList<>();
                    for (String s : expr) {
                        exprnew.addLast(s);
                    }
                    exprnew.remove(j);
                    exprnew.remove(i);
                    // Handling division and zero
                    if (k == 3 && (b == 0 || a % b != 0)) continue;
                    if (k == 4 && (a == 0 || b % a != 0)) continue;
                    // Applying operator
                    int res = applyOperator(a, b, k);
                    xnew.addFirst(res);
                    // Merging expression strings
                    String e = stringOperator(ae, be, k);
                    exprnew.addFirst(e);

                    String ans = decompose(xnew, exprnew, target);
                    if (ans != null) return ans;
                }
            }
        }
        return null;
    }

    private static int applyOperator(int a, int b, int k) {
        switch (k) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return a / b;
        }
        return b / a;
    }

    private static String stringOperator(String ae, String be, int k) {
        if (ae.matches(".+[-+*/].+")) ae = "(" + ae + ")";
        if (be.matches(".+[-+*/].+")) be = "(" + be + ")";

        switch (k) {
            case 0:
                return ae + "+" + be;
            case 1:
                return ae + "-" + be;
            case 2:
                return ae + "*" + be;
            case 3:
                return ae + "/" + be;
        }
        return be + "/" + ae;
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
 */
class AddOperatorsPlusMinusMultiplyDivideBacktrackingString {


//    char ops[] = {'+', '-', '*', '/'};

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
    private void addOperators(String num, int target, int index, double currentValue, double lastValue, String expression, List<String> result) {

        /**
         * Our Constraints:
         * 1. We can't take more numbers than given in input string ( index >= input.length)
         */

        if (index == num.length()) {

            /**
             * Out Goal:
             * 1. once we form a expression, if that expression evaluates to our "target" then this is our solution.
             */

            if (Math.abs(currentValue - target) < 1e-6) {
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

            long currentDigitsValue = num.charAt(i) - '0';


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
                 * Minus operator application '-'; Current value become = so far value - current digit value and last value would be the -current digit value
                 * current Value = 12
                 * last Value = 2 ( say we did like 10 + 2 )
                 * currentDigitvalue = 5 then expression is 10 + 2 - 5 = 7
                 * So last value would be -5
                 */
                addOperators(num, target, i + 1, currentDigitsValue - currentValue, currentDigitsValue, currentDigitsValue + "-" + expression, result);

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

                if (currentDigitsValue != 0 && lastValue % currentDigitsValue == 0) {

                    /**
                     * Multiply operator application '/'; As this has the highest precedence then + and -, we simply can't apply / on last and current value.
                     * Current value become = currentValue - lastValue + last/currentDigitvalue;
                     * For example
                     * current value = 12 ,
                     * last value = 2 ( let say we applied + as 10 + 2 )
                     * currendDigitValue = 4
                     * so expression become : 10 + 2 / 4
                     * if we simply do 12 / 4 = 3 which is wrong as 10 + 2 / 4 = 10 + 0.5 = 10.5
                     *
                     * New value = 10 + 2 / 4 = 10.5
                     * last value = 2/4 = 0.5
                     *
                     *
                     */
                    addOperators(num, target, i + 1, currentValue - lastValue + lastValue / currentDigitsValue, lastValue / currentDigitsValue, expression + "/" + currentDigitsValue, result);
                }

                if (lastValue != 0 && currentDigitsValue % lastValue == 0) {

                    /**
                     * Multiply operator application '/'; As this has the highest precedence then + and -, we simply can't apply / on last and current value.
                     * Current value become = currentValue - lastValue + last/currentDigitvalue;
                     * For example
                     * current value = 12 ,
                     * last value = 2 ( let say we applied + as 10 + 2 )
                     * currendDigitValue = 4
                     * so expression become : 10 + 2 / 4
                     * if we simply do 12 / 4 = 3 which is wrong as 10 + 2 / 4 = 10 + 0.5 = 10.5
                     *
                     * New value = 10 + 2 / 4 = 10.5
                     * last value = 2/4 = 0.5
                     *
                     *
                     */
                    addOperators(num, target, i + 1, currentValue - lastValue + currentDigitsValue / lastValue, currentDigitsValue / lastValue, currentDigitsValue + "/" + expression, result);
                }

            }


        }


    }

}