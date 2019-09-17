package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-24
 * Description: https://leetcode.com/problems/generate-parentheses/
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * {@link ValidParenthesesBalancedParenthesesExpression}
 */
public class GenerateValidParenthesis {

    public static void main(String []args) {

        test(1);
        test(2);
        test(3);
        test(4);
    }

    private static void test(int n) {
        System.out.println("Brute force");
        BruteForce sol = new BruteForce();
        System.out.println(sol.generateParenthesis(n));

        System.out.println("Backtracking");
        Backtracking backtracking = new Backtracking();
        System.out.println(backtracking.generateParenthesis(n));


        System.out.println("Backtracking Rev");
        System.out.println(backtracking.generateParenthesisReverse(n));

        System.out.println("Queue");
        UsingQueue queue = new UsingQueue();
        System.out.println(queue.generateParenthesis(n));


        System.out.println("DP");
        DP dp = new DP();
        System.out.println(dp.generateParenthesis(n));

        System.out.println();
    }


    /**
     * WE simply start putting a open and close bracket at each point and see if its valid
     * O(n* 2^n) as there 2 choice for each position.
     */
    static class BruteForce {

        public List<String> generateParenthesis(int n) {

            char parenthesis[] = new char[2 * n];
            List<String> result = new ArrayList<>();

            generateParenthesis(parenthesis, 0, result, n);
            return result;
        }

        private void generateParenthesis(char[] parenthesis, int pos, List<String> result, int n) {

            //Have we placed all open and close brackets ( that should be equal to n open and n close = 2n)
            if (pos == 2 * n) {

                if (isValid(parenthesis)) {
                    result.add(new String(parenthesis));

                }
                return;
            }

            //if we don't, then try every position with two possibilities
            parenthesis[pos] = '('; //put open and try
            generateParenthesis(parenthesis, pos + 1, result, n);

            parenthesis[pos] = ')'; //put open and try
            generateParenthesis(parenthesis, pos + 1, result, n);


        }

        //O(n)
        private boolean isValid(char[] parenthesis) {

            int count = 0;

            for (int i = 0; i < parenthesis.length; i++) {
                if (parenthesis[i] == '(')
                    count++;
                else
                    count--;

                if (count < 0)
                    return false;


            }

            return (count == 0);
        }
    }

    static class Backtracking {

        /**
         * Our choices:
         * Put either a ( or )
         * <p>
         * Our Constraints:
         * a. We can't put more open brackets then n
         * b. We can't put more close brackets then n
         * c. We can't put more close brackets then open at any moment
         * <p>
         * Our Goal
         * 1. WE need to use n open and n close brackets and make string balanced
         *
         * Our complexity analysis rests on understanding how many elements there are in generateParenthesis(n).
         * This analysis is outside the scope of this article, but it turns out this is the n-th Catalan number
         * O(4^n / (sqrt(n)))
         * @param n
         * @return
         */
        public List<String> generateParenthesis(int n) {

            List<String> result = new ArrayList<>();

            generateParenthesis("", 0, 0, result, n);

            return result;
        }

        /**
         * Important thing is that we can not have more than n open brackets and open < close
         *
         * @param s
         * @param open
         * @param close
         * @param result
         * @param n
         */
        private void generateParenthesis(String s, int open, int close, List<String> result, int n) {

            /**
             * Our Goal
             *  1. We need to use n open and n close brackets and make string balanced
             */
            if (open == close && s.length() == 2 * n) {
                result.add(s);
            }

            //a. We can't put more open brackets then n
            if (open < n)
                generateParenthesis(s + "(", open + 1, close, result, n);

            //b. We can't put more close brackets then n
            //c. We can't put more close brackets then open at any moment
            if (close < open && close < n) // put only close if its less then open

                generateParenthesis(s + ")", open, close + 1, result, n);
        }


        /**
         * In reverse order
         * Complexity: O(4^n / (sqrt(n)))
         * @param n
         * @return
         */
        public List<String> generateParenthesisReverse(int n) {

            List<String> result = new ArrayList<>();

            generateParenthesisReverse("", n, n, result);

            return result;
        }

        private void generateParenthesisReverse(String s, int open, int close, List<String> result) {

            if (open == 0 && close == 0) {
                result.add(s);
                return;
            }

            if (open > 0)
                generateParenthesisReverse(s + "(", open - 1, close, result);

            if (open < close)
                generateParenthesisReverse(s + ")", open, close - 1, result);


        }

    }

    static class UsingQueue {

        /**
         * Mitigate the same what we did in backtracking using queue.
         *
         * O(4^n / (sqrt(n)))
         * @param n
         * @return
         */
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();

            Queue<String> queue = new LinkedList<>();
            queue.offer("("); // put one open bracket

            Queue<int[]> bracketCounter = new LinkedList<>();

            bracketCounter.add(new int[]{1, 0}); // put one open bracket


            int totalSize = 2 * n;

            for (int i = 1; i < totalSize; i++) { //O(2*n)


                //if the current string length = the current iterator , that means we can generate more
                while (queue.peek().length() == i) { //O(n*n)

                    String lastString = queue.poll();
                    int lastCount[] = bracketCounter.poll();

                    //we have not put sufficient open brackets yet to make length 2*n
                    //open < n
                    if (lastCount[0] < n) {

                        queue.offer(lastString + "(");
                        bracketCounter.add(new int[]{lastCount[0] + 1, lastCount[1]});
                    }

                    //close < open and close < n : we can put a close bracket
                    if (lastCount[1] < lastCount[0] && lastCount[1] < n) {
                        queue.offer(lastString + ")");
                        bracketCounter.add(new int[]{lastCount[0], lastCount[1] + 1});
                    }
                }
            }


            result.addAll(queue);
            return result;
        }

    }

    static class DP {

        /**
         * First consider how to get the result f(n) from previous result f(0)...f(n-1).
         * Actually, the result f(n) will be put an extra '()' pair to f(n-1).
         * Let's the "(" always at the first position, to produce a valid result, we can only put ")" in a way that there will be i pairs '()' inside the extra '()'
         * and n - 1 - i pairs '()' outside the extra pair.
         * <p>
         * Let us consider an example to get clear view:
         * <p>
         * f(0): ""
         * <p>
         * f(1): "("f(0)")"
         * <p>
         * f(2): "("f(0)")"f(1), "("f(1)")"
         * <p>
         * f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
         * <p>
         * So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ... "("f(i)")"f(n-1-i) ... "(f(n-1)")"
         *
         * @param n
         * @return
         */
        public List<String> generateParenthesis(int n) {

            List<List<String>> brackets = new ArrayList<>();

            brackets.add(Collections.singletonList("")); //start with empty solution

            for (int open = 1; open <= n; open++) { //O(n)

                List<String> bracket = new ArrayList<>();

                for (int close = 0; close < open; close++) { //O(n)

                    for (String left : brackets.get(close)) { //O(n)

                        for (String right : brackets.get(open - close - 1)) {

                            bracket.add("(" + left + ")" + right);
                        }
                    }

                }

                brackets.add(bracket);
            }

            return brackets.get(brackets.size() - 1);

        }
    }
}