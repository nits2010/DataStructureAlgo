package Java.LeetCode;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/min-stack/
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * <p>
 * <p>
 * Example:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 * <p>
 * https://leetcode.com/problems/min-stack/discuss/373004/3-solutions-or-47ms-to-44-ms-or-100-beat-or-Java-or-Easy-to-understand-or-clean-code
 */
public class DesignMinStack {

    public static void main(String[] args) {

        testMinStackUsingRef();
        testMinStackUsingStack();
        testMinStackUsingLinkedList();


    }

    //["MinStack","push","push","push","getMin", "push", "pop","top","getMin"]
    //[[],[-2],[0],[-3],[],[-50],[],[],[]]
    private static void testMinStackUsingLinkedList() {


        MinStackUsingLinkedList minStack = new MinStackUsingLinkedList();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin() + " -> -3");
        minStack.push(-50);
        System.out.println(minStack.top() + " -> -50");
        System.out.println(minStack.getMin() + " -> -50");
    }

    //["MinStack","push","push","push","getMin", "push", "pop","top","getMin"]
    //[[],[-2],[0],[-3],[],[-50],[],[],[]]
    private static void testMinStackUsingStack() {


        MinStackUsingStack minStack = new MinStackUsingStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin() + " -> -3");
        minStack.push(-50);
        System.out.println(minStack.top() + " -> -50");
        System.out.println(minStack.getMin() + " -> -50");

    }

    //["MinStack","push","push","push","getMin", "push", "pop","top","getMin"]
    //[[],[-2],[0],[-3],[],[-50],[],[],[]]
    private static void testMinStackUsingRef() {

        MinStackUsingRef minStack = new MinStackUsingRef();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin() + " -> -3");
        minStack.push(-50);
        System.out.println(minStack.top() + " -> -50");
        System.out.println(minStack.getMin() + " -> -50");

    }


}

/**
 * Build min Stack using stack
 * <p>
 * 1. push -> add node to top of the stack
 * 2. pop -> remove node from top of the stack
 * 3. min -> min value at top of stack
 * 4. top -> top itself
 * <p>
 * Advantage: More OOO design
 * <p>
 * Runtime: 47 ms, faster than 59.84% of Java online submissions for Min Stack.
 * Memory Usage: 40.1 MB, less than 29.71% of Java online submissions for Min Stack.
 */
class MinStackUsingRef {

    class StackElement {
        int element;
        StackElement min;
    }

    Stack<StackElement> minStack;
    StackElement minRef;

    /**
     * initialize your data structure here.
     */
    public MinStackUsingRef() {
        minStack = new Stack<>();
        minRef = null;
    }

    public void push(int x) {


        StackElement element = new StackElement();
        element.element = x;

        if (minStack.isEmpty()) {
            minRef = element;
        } else if (minRef.element > x)
            minRef = element;


        element.min = minRef;

        minStack.push(element);

    }

    public void pop() {
        if (minStack.isEmpty())
            return;

        StackElement pop = minStack.pop();
        if (!minStack.isEmpty())
            minRef = minStack.peek().min;
        else
            minRef = null;


    }

    public int top() {

        if (minStack.isEmpty())
            return Integer.MAX_VALUE;

        return minStack.peek().element;
    }

    public int getMin() {
        if (minRef == null)
            return Integer.MAX_VALUE;

        return minRef.element;


    }
}


/**
 * Build Stack by own, using LINKED list
 * 1. push -> add node to head of the linked list
 * 2. pop -> remove node from head of the linked list
 * 3. min -> min value at head of linked list
 * 4. top -> head itself
 * <p>
 * Runtime: 44 ms, faster than 100.00% of Java online submissions for Min Stack.
 * Memory Usage: 40.1 MB, less than 31.16% of Java online submissions for Min Stack.
 */
class MinStackUsingLinkedList {


    final class StackElement {
        final int element;
        int min;
        final StackElement next;

        public StackElement(int element, int min, StackElement next) {
            this.element = element;
            this.min = min;
            this.next = next;
        }
    }

    /**
     * Head of linked list;
     * Always add new node at head of linked list
     */
    private StackElement minStack;

    /**
     * initialize your data structure here.
     */
    public MinStackUsingLinkedList() {
        minStack = null;
    }

    public void push(int x) {

        int minV = x;
        if (minStack != null && minStack.min < x) {
            minV = minStack.min;
        }

        //Add new element at head of the linked list
        StackElement element = new StackElement(x, minV, minStack);
        minStack = element;
        minStack.min = minV;

    }

    public void pop() {
        if (minStack == null)
            return;

        //move next node, to pop from head
        minStack = minStack.next;
    }

    public int top() {
        if (minStack == null)
            return Integer.MAX_VALUE;

        return minStack.element;


    }

    public int getMin() {
        if (minStack == null)
            return Integer.MAX_VALUE;
        return minStack.min;
    }
}

/**
 * Using another stack
 * minStack -> maintain the elements stack
 * minimumValue -> maintain the minimum value at top of stack
 * <p>
 * We can optimize the space by not pushing duplicate min in minValueStack
 * <p>
 * Runtime: 46 ms, faster than 88.77% of Java online submissions for Min Stack.
 * Memory Usage: 39.9 MB, less than 34.79% of Java online submissions for Min Stack.
 */
class MinStackUsingStack {


    Stack<Integer> minStack;
    Stack<Integer> minimumValue;

    /**
     * initialize your data structure here.
     */
    public MinStackUsingStack() {
        minStack = new Stack<>();
        minimumValue = new Stack<>();

    }

    public void push(int x) {

        minStack.push(x);

        if (minimumValue.isEmpty()) {
            minimumValue.push(x);
        } else {
            if (minimumValue.peek() > x)
                minimumValue.push(x);
            else
                minimumValue.push(minimumValue.peek());
        }

    }

    public void pop() {
        minStack.pop();
        minimumValue.pop();
    }

    public int top() {
        if (minStack.isEmpty())
            return Integer.MAX_VALUE;

        return minStack.peek();

    }

    public int getMin() {
        if (minimumValue.isEmpty())
            return Integer.MAX_VALUE;

        return minimumValue.peek();

    }
}

