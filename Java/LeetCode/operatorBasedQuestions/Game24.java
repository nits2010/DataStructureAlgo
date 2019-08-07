package Java.LeetCode.operatorBasedQuestions;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-07
 * Description: https://leetcode.com/problems/24-game/
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 * <p>
 * Example 1:
 * <p>
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * <p>
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * <p>
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class Game24 {


    public static void main(String[] args) {
        EvaluateToTargetPlusMinusMultiplyDivideBrackets sol = new EvaluateToTargetPlusMinusMultiplyDivideBrackets();
        System.out.println(sol.judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println(sol.judgePoint24(new int[]{1, 2, 1, 2}));
    }
}

/**
 * This question asked us to find is there any expression which can evaluate to 24 (target) with using
 * '+' , '-', '/' , '*' and '(' and ')' while using only 1 instance of the operator ( +, -, / and * ).
 * This is similar to {@link AddOperatorsPlusMinusMultiply}
 * With following potential changes
 * 1. We can't create a bigger number by joining two numbers
 * 2. We need to use / and '(' and ')' possibly.
 * <p>
 * <p>
 * Now since we have "/" then things will change very much as we know "+" and "*" is transitive operation ( a+b or b+a OR a*b or b*a would result same) but
 * "/" and "-" are not.
 * and for "/" the denominator could not be 0. As well question clearly says that its not "integer division", it could be floating point number.
 * <p>
 * Outcome:
 * 1. This leads us to use double instead of int.
 * 2. This says for any two operand 'a' and 'b' we can have only single branch with 'a+b' or 'a*b' but for / and "-" we'll have two branches ( a-b or b-a OR a/b or b/a).
 * 3. As we don't know which expression to evaluate what value, we need to try all operator and all values in the array.
 * <p>
 * Which boils down to "Backtracking".
 * <p>
 * Now;
 * <p>
 * Our choices:
 * 1. We have four operator with in total 6 branches ( +->1, *->1, -->2, /->2 ). We try them all
 * 2. For division, denominator could not be zero.
 * 3.  operands two at a time
 * <p>
 * Our constraints:
 * 1. Each operator can be used at most once ( except '(' and ')' ) .
 * 2. We need to take two operand to apply the operator.
 * 3. Each index value in input is one operand, we can't join them to make a single operand.
 * <p>
 * Our Goal:
 * 1. We need to evaluate the current expression and if the value out of it is equal to target then we are done. Since its floating point then we need to have some error in precision. We'll use EPS
 * <p>
 * <p>
 * Now how we proceed?
 * Since we need two operand to apply one operator, after applying operator we'll get a value which we can put back in input array it self.
 * This makes the input array of size from 'n' to 'n-1' and we can recurse on this until input array contains only 1 element [ our base case ].
 * <p>
 * Runtime: 1 ms, faster than 98.22% of Java online submissions for 24 Game.
 * Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for 24 Game.
 */
class EvaluateToTargetPlusMinusMultiplyDivideBrackets {

    private static double ERROR_PRESSION = 1e-10;

    public boolean judgePoint24(int[] nums) {

        /**
         * Convert given input to floating point number
         */
        double values[] = new double[nums.length];
        int i = 0;
        for (int n : nums)
            values[i++] = (double) n;


        return judgePoint24(values, 24, values.length);
    }

    /**
     * @param nums
     * @param target
     * @param size
     * @returnx x
     */
    private boolean judgePoint24(double[] nums, int target, int size) {

        /**
         * Base case
         */
        if (size == 1) {

            /**
             * if this value is reasonable close to target then we found it
             */
            if (Math.abs(nums[0] - target) < ERROR_PRESSION) {
                return true;
            } else
                return false; // since this is not match our target
        }


        /**
         * Our choices:
         * operands two at a time.
         *
         * Lets take 'i' points our first operand and 'j' points to our second.
         * Since we need to chose the operand from remaining only [ see above explanation about size; N -> N-1 ].
         * We will be using the first slot of the array as our resultant value ( see the base case ).
         * For for each iteration, we'll put the output of current operator in first slot it self.
         */
        for (int i = 0; i < size; i++) {

            for (int j = i + 1; j < size; j++) {

                /**
                 * Get first two operand from list
                 */
                double firstOperand = nums[i];
                double secondOperand = nums[j];

                /**
                 * Setup for next iteration. As our size would reduce by 1 in next iteration that will make us to loose last element of the array
                 * so, just copy last element of the array to appropriate position so that it can be used.
                 * Other approach would be reducing the size of the array by 1 each time, but that makes Copy of the array in each iteration and that is costly.
                 *
                 */
                nums[j] = nums[size - 1];

                /**
                 * Plus operator;
                 */
                double sum = firstOperand + secondOperand;
                nums[i] = sum;
                if (judgePoint24(nums, target, size - 1))
                    return true;

                /**
                 * Minus operator;
                 * Path 1: a-b
                 */
                double difference1 = firstOperand - secondOperand;
                nums[i] = difference1;
                if (judgePoint24(nums, target, size - 1))
                    return true;

                /**
                 * Minus operator;
                 * Path 2: b-a
                 */
                double difference2 = secondOperand - firstOperand;
                nums[i] = difference2;
                if (judgePoint24(nums, target, size - 1))
                    return true;

                /**
                 * Multiply operator;
                 *
                 */
                double multiply = firstOperand * secondOperand;
                nums[i] = multiply;
                if (judgePoint24(nums, target, size - 1))
                    return true;

                /**
                 * Divide operator;
                 * Path 1: a/b
                 */
                if (secondOperand != 0) {

                    double divide = firstOperand / secondOperand;
                    nums[i] = divide;
                    if (judgePoint24(nums, target, size - 1))
                        return true;
                }

                /**
                 * Divide operator;
                 * Path 2: b/a
                 */
                if (secondOperand != 0) {

                    double divide = secondOperand / firstOperand;
                    nums[i] = divide;
                    if (judgePoint24(nums, target, size - 1))
                        return true;
                }

                /**
                 * Backtrack
                 */
                nums[i] = firstOperand;
                nums[j] = secondOperand;

            }

        }

        return false;
    }


}