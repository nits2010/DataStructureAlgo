package DataStructureAlgo.Java.nonleetcode;

import DataStructureAlgo.Java.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-04
 * Description:
 * https://www.geeksforgeeks.org/minimum-maximum-values-expression/
 * <p>
 * Given an expression which contains numbers and two operators ‘+’ and ‘*’, we need to find maximum and minimum value which can be obtained by evaluating this expression by different parenthesization.
 * Examples:
 * <p>
 * Input  : expr = “1+2*3+4*5”
 * Output : Minimum Value = 27, Maximum Value = 105
 * Explanation:
 * Minimum evaluated value = 1 + (2*3) + (4*5) = 27
 * Maximum evaluated value = (1 + 2)*(3 + 4)*5 = 105
 * <p>
 * **********SAME AS MATRIX CHAIN MULTIPLICATION
 */
public class MaximumMinimumValuesOfExpression {

    public static void main(String []args) {
        SolutionMaximumMinimumValuesOfExpression sol = new SolutionMaximumMinimumValuesOfExpression();

        Pair<Integer, Integer> integerIntegerPair = sol.maximumMinimumValueExpression("1+2*3+4*5");
        System.out.println("Maximum : " + integerIntegerPair.getKey() + " minimum :" + integerIntegerPair.getValue());
    }


}

class SolutionMaximumMinimumValuesOfExpression {

    Pair<Integer, Integer> maximumMinimumValueExpression(String expression) {

        if (expression == null || expression.isEmpty() || expression.length() == 1 || !isDigit(expression.charAt(0)))
            return new Pair<>(Integer.MIN_VALUE, Integer.MAX_VALUE);

        expression = expression.trim();
        int n = expression.length();

        Pair<List<Character>, List<Integer>> listListPair = seperateOperatorOperand(expression, n);

        List<Character> operator = listListPair.getKey();

        List<Integer> values = listListPair.getValue();

        int len = values.size();
        int minValue[][] = new int[len][len];
        int maxValue[][] = new int[len][len];

        //Of length 1
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {

                minValue[i][j] = Integer.MAX_VALUE;
                maxValue[i][j] = Integer.MIN_VALUE;

                if (i == j)
                    minValue[i][i] = maxValue[i][i] = values.get(i);
            }

        }


        //go length by length
        for (int l = 2; l <= len; l++) {

            for (int i = 0; i < len - l + 1; i++) {

                int j = i + l - 1;

                for (int k = i; k < j; k++) {


                    if (operator.get(k) == '+') {

                        minValue[i][j] = Math.min(minValue[i][j], minValue[i][k] + minValue[k + 1][j]);
                        maxValue[i][j] = Math.max(maxValue[i][j], maxValue[i][k] + maxValue[k + 1][j]);


                    } else if (operator.get(k) == '*') {
                        minValue[i][j] = Math.min(minValue[i][j], minValue[i][k] * minValue[k + 1][j]);
                        maxValue[i][j] = Math.max(maxValue[i][j], maxValue[i][k] * maxValue[k + 1][j]);
                    }

                }

            }
        }

        return new Pair<>(maxValue[0][len - 1], minValue[0][len - 1]);


    }

    private Pair<List<Character>, List<Integer>> seperateOperatorOperand(String expression, int n) {
        List<Character> operator = new ArrayList<>(n);
        List<Integer> values = new ArrayList<>(n);


        String multiDigitNumber = "";

        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);

            if (isOperator(c)) {
                operator.add(c);
                values.add(Integer.parseInt(multiDigitNumber));
                multiDigitNumber = "";
            } else
                multiDigitNumber = multiDigitNumber + c;

        }
        values.add(Integer.parseInt(multiDigitNumber));
        return new Pair<>(operator, values);
    }

    private boolean isOperator(char c) {
        if (c == '*' || c == '+')
            return true;
        return false;

    }

    private boolean isDigit(char c) {
        if (c >= '0' && c <= '9')
            return true;
        return false;
    }
}
