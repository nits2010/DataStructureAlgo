package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Design;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 10/1/2024
 * Question Category: 1381. Design a Stack With Increment Operation
 * Description: https://leetcode.com/problems/design-a-stack-with-increment-operation/description/?envType=daily-question&envId=2024-09-30
 * Design a stack that supports increment operations on its elements.
 *
 * Implement the CustomStack class:
 *
 * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
 * void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
 * int pop() Pops and returns the top of the stack or -1 if the stack is empty.
 * void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, increment all the elements in the stack.
 *
 *
 * Example 1:
 *
 * Input
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * Output
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 * Explanation
 * CustomStack stk = new CustomStack(3); // Stack is Empty []
 * stk.push(1);                          // stack becomes [1]
 * stk.push(2);                          // stack becomes [1, 2]
 * stk.pop();                            // return 2 --> Return top of the stack 2, stack becomes [1]
 * stk.push(2);                          // stack becomes [1, 2]
 * stk.push(3);                          // stack becomes [1, 2, 3]
 * stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
 * stk.increment(5, 100);                // stack becomes [101, 102, 103]
 * stk.increment(2, 100);                // stack becomes [201, 202, 103]
 * stk.pop();                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
 * stk.pop();                            // return 202 --> Return top of the stack 202, stack becomes [201]
 * stk.pop();                            // return 201 --> Return top of the stack 201, stack becomes []
 * stk.pop();                            // return -1 --> Stack is empty return -1.
 *
 *
 * Constraints:
 *
 * 1 <= maxSize, x, k <= 1000
 * 0 <= val <= 100
 * At most 1000 calls will be made to each method of increment, push and pop each separately.
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @Array
 * @Stack
 * @Design
 * @medium
 *
 * <p><p>
 * Company Tags
 * -----
 * @endurance
 * <p><p>
 *
 * @Editorial
 */
public class DesignAStackWithIncrementOperation_1381 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new String[]{"CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"},
                List.of(List.of(1),List.of(2),List.of(2),List.of(3),List.of(4),List.of(5,100),List.of(2,100)),
                new Integer[] {null,null,null,2,null,null,null,null,null,103,202,201,-1});
        CommonMethods.printResult(test);
    }

    private static boolean test(String[] operations, List<List<Integer>> params, Integer[] expected) {

        System.out.println("-----------------------------------------------------------");
        System.out.println(" Operations : " + Arrays.toString(operations) + " params : " + params
                + "\n expected : " + Arrays.toString(expected));

        int resultIndex = 0;
        int keyIndex = 0;
        CustomStack customStack = new CustomStack(3);
        Integer[] result = new Integer[operations.length];
        result[resultIndex] = null;

        for (String operation : operations) {
            if ("push".equals(operation)) {
                customStack.push(params.get(keyIndex++).get(0));
                result[resultIndex] = null;
            } else if ("pop".equals(operation)) {
                result[resultIndex] = customStack.pop();
            } else if ("increment".equals(operation)) {
                customStack.increment(params.get(keyIndex).get(0), params.get(keyIndex).get(1));
                keyIndex++;
            }
            resultIndex++;
        }

        boolean pass = Arrays.deepEquals(result, expected);

        System.out.println(" Result : "+ Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        return pass;
    }
    static class CustomStack {


        int []stack ;
        int top = -1;
        int bottom = -1;
        int currentSize = 0;


        public CustomStack(int maxSize) {
            stack = new int[maxSize];
            Arrays.fill(stack, -1);
        }

        public void push(int x) {
            if(currentSize >= stack.length)
                return;

            stack[++top] = x;

            if(bottom == -1)
                bottom = top;

            currentSize ++;

        }

        public int pop() {
            if(currentSize <= 0)
                return -1;

            currentSize --;
            return stack[top--];


        }

        public void increment(int k, int val) {
            if(bottom == -1)
                return;

            int i = bottom;
            while(i<=top && k>0 ){
                stack[i] += val;
                i++;
                k--;
            }
        }
    }

}
