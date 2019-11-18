package Java.companyWise.GroupOn;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 26/04/19
 * Description:
 */
public class ExpressionEvaluator {

    public static void main(String []args) {

        String expression = "8*2+5*6-18%2";

        System.out.println(evaluate(expression));
    }

    private static Double evaluate(String expression) {

        char[] ex = expression.toCharArray();

        Stack<Double> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();


        for (int i = 0; i < ex.length; i++) {

            char c = ex[i];

            if (c == ' ')
                continue;

            if (Character.isDigit(c)) {

                StringBuilder sb = new StringBuilder();
                while (i < ex.length && Character.isDigit(ex[i])) {
                    sb.append(ex[i++]);

                }


                double number = Double.parseDouble(sb.toString());
                operand.push(number);

                if (i == ex.length)
                    break;

                i--;

            }

            //*=multiplication,+=add,-=subtract,%=divid
            else if (c == '+' || c == '-' || c == '*' || c == '%') {

                while (!operator.empty() && getOrder(c, operator.peek())) {
                    Double v = execute(operator.pop(), operand.pop(), operand.pop());
                    if (v == null)
                        return null;
                    operand.push(v);
                }


                operator.push(c);
            }
        }


        while (!operator.isEmpty()) {
            operand.push(execute(operator.pop(), operand.pop(), operand.pop()));
        }

        return operand.pop();

    }

    private static Double execute(Character operator, Double op1, Double op2) {


        switch (operator) {
            case '+':
                return (double) op2+op1;
            case '-':
                return (double) op2 - op1;
            case '*':
                return (double) op1 * op2;
            case '%':
                if (op1 == 0) {
                    return null; //not possible
                }
                return (double) op2 / op1;
            default:
                return null;
        }

    }


    public static boolean getOrder(char op1, char op2) {
        if ((op1 == '*' || op1 == '%') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    private static Double getNumber(char[] ex, int i) {
        StringBuilder sb = new StringBuilder();
        while (i < ex.length && Character.isDigit(ex[i])) {
            sb.append(ex[i++]);

        }
        return Double.parseDouble(sb.toString());
    }
}
