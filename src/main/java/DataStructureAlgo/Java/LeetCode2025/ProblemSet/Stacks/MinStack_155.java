package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date:09/08/24
 * Question Category: 155. Min Stack @medium
 * Description: https://leetcode.com/problems/min-stack/description
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.DesignMinStack}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Stack
 * @Design <p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Bloomberg
 * @Facebook
 * @Google
 * @Editorial <a href="https://leetcode.com/problems/min-stack/solutions/373004/3-solutions-or-47ms-to-44-ms-or-100-beat-or-Java-or-Easy-to-understand-or-clean-code/">...</a>
 */

public class MinStack_155 {

    public static void main(String[] args) {
        boolean test = true;
        test =
                test1() &&
                        test2()
        ;
        System.out.println( (test ? "\nAll Passed " : "\nSomething Failed ") );
    }


    private static boolean test1(){

        MinStackProblem.SolutionUsingStacksRef.MinStack usingStackRef = new MinStackProblem.SolutionUsingStacksRef().new MinStack();
        MinStackProblem.SolutionUsing2Stacks.MinStack using2Stack = new MinStackProblem.SolutionUsing2Stacks().new MinStack();
        MinStackProblem.SolutionUsingLinkedList.MinStack usingLL = new MinStackProblem.SolutionUsingLinkedList().new MinStack();

        boolean testUsingRef = true;
        boolean testUsing2Stacks = true;
        boolean testUsingLL = true;

        usingStackRef.push(-2);
        using2Stack.push(-2);
        usingLL.push(-2);

        usingStackRef.push(0);
        using2Stack.push(0);
        usingLL.push(0);

        usingStackRef.push(-3);
        using2Stack.push(-3);
        usingLL.push(-3);

        testUsingRef &= usingStackRef.getMin()  == -3;
        testUsing2Stacks &= using2Stack.getMin()  == -3;
        testUsingLL &= usingLL.getMin()  == -3;

        usingStackRef.pop();
        using2Stack.pop();
        usingLL.pop();

        testUsingRef &= usingStackRef.top() == 0 ;
        testUsing2Stacks &= using2Stack.top() == 0 ;
        testUsingLL &= usingLL.top() == 0 ;

        testUsingRef &= usingStackRef.getMin() == -2;
        testUsing2Stacks &= using2Stack.getMin() == -2;
        testUsingLL &= usingLL.getMin() == -2;

        System.out.println("test1 Using Ref -> "+ (testUsingRef ? " Passed " : " Failed ")) ;
        System.out.println("test1 Using 2Stacks -> "+ (testUsing2Stacks ? " Passed " : " Failed ") );
        System.out.println("test1 Using LL -> "+ (testUsingLL ? " Passed " : " Failed ") );
        return testUsingRef && testUsing2Stacks && testUsingLL;
    }

    private static boolean test2(){
        MinStackProblem.SolutionUsingStacksRef.MinStack usingStackRef = new MinStackProblem.SolutionUsingStacksRef().new MinStack();
        MinStackProblem.SolutionUsing2Stacks.MinStack using2Stack = new MinStackProblem.SolutionUsing2Stacks().new MinStack();
        MinStackProblem.SolutionUsingLinkedList.MinStack usingLL = new MinStackProblem.SolutionUsingLinkedList().new MinStack();

        boolean testUsingRef = true;
        boolean testUsing2Stacks = true;
        boolean testUsingLL = true;

        usingStackRef.push(0);
        using2Stack.push(0);
        usingLL.push(0);

        usingStackRef.push(1);
        using2Stack.push(1);
        usingLL.push(1);

        usingStackRef.push(2);
        using2Stack.push(2);
        usingLL.push(2);

        testUsingRef &= usingStackRef.getMin()  == 0;
        testUsing2Stacks &= using2Stack.getMin()  == 0;
        testUsingLL &= usingLL.getMin()  == 0;

        usingStackRef.pop();
        using2Stack.pop();
        usingLL.pop();

        testUsingRef &= usingStackRef.top() == 1 ;
        testUsing2Stacks &= using2Stack.top() == 1 ;
        testUsingLL &= usingLL.top() == 1 ;

        testUsingRef &= usingStackRef.getMin() == 0;
        testUsing2Stacks &= using2Stack.getMin() == 0;
        testUsingLL &= usingLL.getMin() == 0;

        System.out.println("\ntest2 Using Ref -> "+ (testUsingRef ? " Passed " : " Failed ")) ;
        System.out.println("test2 Using 2Stacks -> "+ (testUsing2Stacks ? " Passed " : " Failed ") );
        System.out.println("test2 Using LL -> "+ (testUsingLL ? " Passed " : " Failed ")) ;
        return testUsingRef && testUsing2Stacks && testUsingLL;
    }
}

class MinStackProblem {

    /**
     * 4 ms Beats 95.59%
     */
    static class SolutionUsingStacksRef {

        class MinStack {

            class StackElements{
                int value;
                StackElements refToMin;

                StackElements(int value){
                    this.value = value;
                }
                StackElements (int value, StackElements min){
                    this.value = value;
                    this.refToMin = min;
                }
            }

            final Stack<StackElements> stack ;
            public MinStack() {
                stack = new Stack<>();
            }

            public void push(int val) {

                //get current minimum
                StackElements currentMin = stack.isEmpty()? null : stack.peek().refToMin;

                //build current element
                StackElements current = new StackElements(val);

                //check if the current is minimum so far ?
                if(currentMin == null || current.value < currentMin.value)
                    currentMin = current;

                //push
                stack.push(new StackElements(val, currentMin));
            }

            public void pop() {
                    stack.pop();
            }

            public int top() {
                return stack.isEmpty() ? -1 : stack.peek().value;
            }

            public int getMin() {
                return stack.isEmpty() ? -1 : stack.peek().refToMin.value;
            }
        }
    }


    /**
     * 4 ms Beats 95.59%
     */
    static class SolutionUsing2Stacks{

        class MinStack {


            private final Stack<Integer> stack ;
            private final Stack<Integer> minStack;
            public MinStack() {
                stack = new Stack<>();
                minStack = new Stack<>();
            }

            public void push(int val) {

                //get current minimum

                Integer currentMin = minStack.isEmpty()? null : minStack.peek();


                //check if the current is minimum so far ?
                if(currentMin == null || val < currentMin)
                    currentMin = val;

                //push
                stack.push(val);
                minStack.push(currentMin);
            }

            public void pop() {
                stack.pop();
                minStack.pop();
            }

            public int top() {
                return stack.isEmpty() ? -1 : stack.peek();
            }

            public int getMin() {
                return minStack.isEmpty() ? -1 : minStack.peek();
            }
        }
    }

    static class SolutionUsingLinkedList{

        class MinStack {

            class ListNode {
                int value;
                int min;
                ListNode next;

                ListNode(int value, int min){
                    this.value = value;
                    this.min = min;

                }
            }
            ListNode stackTop;
            public MinStack() {
                stackTop = null;
            }

            public void push(int val) {

                if(stackTop == null) {
                    stackTop = new ListNode(val, val);
                }else{
                    ListNode current = new ListNode(val, Math.min(val, stackTop.min));
                    current.next = stackTop;
                    stackTop = current;
                }

            }

            public void pop() {
                stackTop = stackTop.next;
            }

            public int top() {
                return stackTop == null ? -1 : stackTop.value;
            }

            public int getMin() {
                return stackTop == null ? -1 : stackTop.min;
            }
        }
    }

}