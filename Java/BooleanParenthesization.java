package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-23
 * Description: https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
 * Given a boolean expression with following symbols.
 * <p>
 * Symbols
 * 'T' ---> true
 * 'F' ---> false
 * And following operators filled between symbols
 * <p>
 * Operators
 * &   ---> boolean AND
 * |   ---> boolean OR
 * ^   ---> boolean XOR
 * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
 * <p>
 * Let the input be in form of two arrays one contains the symbols (T and F) in order and other contains operators (&, | and ^}
 * <p>
 * Input: symbol[]    = {T, F, T}
 * operator[]  = {^, &}
 * Output: 2
 * The given expression is "T ^ F & T", it evaluates true
 * in two ways "((T ^ F) & T)" and "(T ^ (F & T))"
 */
public class BooleanParenthesization {

    public static void main(String []args) {

        //  ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) and (T|((T&F)^T))

        System.out.println(booleanParenthesizing("TTFT", "|&^"));
    }

    private static int booleanParenthesizing(String symb, String op) {


        if (null == symb || symb.isEmpty() || null == op || op.isEmpty())
            return 0;


        char symbol[] = symb.toCharArray();
        char operator[] = op.toCharArray();

        int n = symb.length();


        final int T[][] = new int[n][n];
        final int F[][] = new int[n][n];


        for (int i = 0; i < n; i++) {
            T[i][i] = symbol[i] == 'T' ? 1 : 0;
            F[i][i] = symbol[i] == 'F' ? 1 : 0;
        }

        for (int length = 1; length < n; length++) { // length of the expression to be evaluate

            for (int i = 0, j = length; j < n; j++, i++) { // starting and ending point of the expression

                T[i][j] = F[i][j] = 0;

                for (int operatorPos = 0; operatorPos < length; operatorPos++) { // choose different operator position

                    int k = i + operatorPos; // get current operator position which is just away from i

                    int totalIK = T[i][k] + F[i][k];
                    int totalKJ = T[k + 1][j] + F[k + 1][j];

                    if (operator[k] == '&') {
                        T[i][j] += T[i][k] * T[k + 1][j]; // Both has to be true means left (T[i][k]) true and right(T[k+1][j] true
                        F[i][j] += (totalIK * totalKJ - T[i][k] * T[k + 1][j]); // either can be false ; hence remove  true from total
                    }
                    if (operator[k] == '^') {
                        T[i][j] += T[i][k] * F[k + 1][j] + F[i][k] * T[k + 1][j]; // either of them is true and other is false;
                        F[i][j] += T[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j]; // both of them true or false

                    }
                    if (operator[k] == '|') {
                        T[i][j] += (totalIK * totalKJ - F[i][k] * F[k + 1][j]);  //either has to be true; remove all false from total true
                        F[i][j] += F[i][k] * F[k + 1][j];//both has to be false
                    }

                }


            }
        }
        return T[0][n - 1]; //return to be true expression

    }


}
