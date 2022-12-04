package DataStructureAlgo.Java.companyWise.Grab;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-23
 * Description: https://www.geeksforgeeks.org/minimum-number-of-bracket-reversals-needed-to-make-an-expression-balanced/
 * <p>
 * Given an expression with only ‘}’ and ‘{‘. The expression may not be balanced. Find minimum number of bracket reversals to make the expression balanced.
 * <p>
 * Examples:
 * <p>
 * Input:  exp = "}{"
 * Output: 2
 * We need to change '}' to '{' and '{' to
 * '}' so that the expression becomes balanced,
 * the balanced expression is '{}'
 * <p>
 * Input:  exp = "{{{"
 * Output: Can't be made balanced using reversals
 * <p>
 * Input:  exp = "{{{{"
 * Output: 2 "{}{}"
 * <p>
 * Input:  exp = "{{{{}}"
 * Output: 1 "{}{{}}
 * <p>
 * Input:  exp = "}{{}}{{{"
 * Output: 3 {{{}}}{}
 *
 * similar {@link DataStructureAlgo.Java.LeetCode.LongestValidParentheses}
 */
public class MinimumBracketReversal {


    public static void main(String []args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("}}{{", 2);
        map.put("}{", 2);
        map.put("{{{", -1);
        map.put("{{{{}}", 1);
        map.put("}{{}}{{{", 3);

        test(map);
    }

    private static void test(Map<String, Integer> tests) {

        MinimumReversal sol = new MinimumReversal();

        boolean pass = true;
        for (String key : tests.keySet()) {
            int ans = sol.minimumReversal(key);
            int shouldBe = tests.get(key);
            pass = pass && ans == shouldBe;
        }

        System.out.println(" Result: " + pass);
    }


}

class MinimumReversal {
    /**
     * The idea is to first remove all balanced part of expression. For example, convert “}{{}}{{{” to “}{{{”
     * by removing highlighted part. If we take a closer look, we can notice that, after removing balanced part, we always end up with an expression of the form }}…}{{…{,
     * An expression that contains 0 or more number of closing brackets followed by 0 or more numbers of opening brackets.
     * <p>
     * How many minimum reversals are required for an expression of the form “}}..}{{..{” ?.
     * Let m be the total number of closing brackets and n be the number of opening brackets. We need ⌈m/2⌉ + ⌈n/2⌉ reversals.
     * For example }}}}{{ requires 2+1 reversals.
     * ⌈⌉ means ceil
     * @param expression
     * @return
     */
    public int minimumReversal(String expression) {
        if (expression.length() % 2 != 0)
            return -1;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '{')
                stack.push(c);
            else {

                if (c == '}' && !stack.isEmpty()) {

                    if (stack.peek() == '{')
                        stack.pop();
                    else
                        stack.push(c);
                } else
                    stack.push(c);
            }
        }

        int totalLength = stack.size();
        if (totalLength == 0)
            return 0;

        int open = 0;

        // return ceil(m/2) + ceil(n/2) which is
        // actually equal to (m+n)/2 + n%2 when
        // m+n is even.
        while (!stack.isEmpty() && stack.peek() == '{') {

            open++;
            stack.pop();

        }

        return (totalLength / 2 + open % 2);


    }

}

//Not working
class MinimumBracketReversalSol {

    /**
     * Given an expression where the valid parenthesis are { and }.
     * Now; we are asked to do "minimum" reversal to make the expression "valid".
     * <p>
     * A expression is only valid when
     * 1. There is equal number of { and }. Means the expression length should be Even
     * 2. When flipping 1 or many make whole expression valid.
     * <p>
     * <p>
     * Approach:1
     * Since we only need to worry about those parenthesis which are making expression un-balanced. Rest those make it balanced
     * we can simply discard them.
     * So this problem become to make the rest of the expression valid by flipping 1 or more.
     * <p>
     * There is two possibilities for each parenthesis
     * 1. Either we flip this then rest of the expression should be balance;
     * Total would be A= 1 + flipsRequired(expression.substring(1))
     * <p>
     * 2. or we don't flip this; then rest of the expression should be balance;
     * Total would be B = flipsRequired(expression.substring(1))
     * <p>
     * then answer would be min(A,B).
     *
     * @param expression
     * @return
     */
    public int minimumReversalBruteForce(String expression) {

        int flips[] = new int[1];
        flips[0] = Integer.MAX_VALUE;
        minimumReversalBruteForceRec(expression, flips);
        System.out.println("Expression " + expression + " flips :" + flips);
        return flips[0];
    }

    private int minimumReversalBruteForceRec(String expression, int min[]) {

        //if we have solved complete string
        if (expression.isEmpty())
            return 0;

        //if valid then return 0 as no flip is required
        if (isValid(expression))
            return 0;

        //Try every character
        for (int i = 0; i < expression.length(); i++) {

            int flip = Integer.MAX_VALUE;
            int notFlip = Integer.MAX_VALUE;

            String s = expression.substring(0, i) + expression.substring(i + 1);
            if (hasEqual(s))
                flip = 1 + minimumReversalBruteForceRec(s, min);

            if (hasEqual(expression.substring(i + 1)))
                notFlip = minimumReversalBruteForceRec(expression.substring(i + 1), min);


            min[0] = Math.min(min[0], Math.min(flip, notFlip));

        }


        return min[0];
    }

    /**
     * Check if the expression is valid or not
     *
     * @param expression
     * @return
     */
    private boolean isValid(String expression) {


        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '{')
                stack.push(i);
            else {
                if (stack.isEmpty())
                    return false;
                if (expression.charAt(stack.peek()) != '{')
                    return false;
                stack.pop();
            }
        }


        return stack.isEmpty();
    }

    private boolean hasEqual(String expression) {
        int count = 0;
        for (int i = 0; i < expression.length(); i++)
            if (expression.charAt(i) == '{')
                count++;
            else
                count--;

        return (count == 0);
    }


}