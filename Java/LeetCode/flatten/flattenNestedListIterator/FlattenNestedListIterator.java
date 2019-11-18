package Java.LeetCode.flatten.flattenNestedListIterator;


import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-15
 * Description: https://leetcode.com/problems/flatten-nested-list-iterator/
 * Facebook asked
 * <p>
 * Given a nested list of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * <p>
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */


public class FlattenNestedListIterator {


    public static void main(String []args) {
        usingList();
        usingQueue();
        usingStack();

    }

    private static void usingStack() {
        System.out.println("\nUsing stack ");
        testUsingStack1();
        testUsingStack2();
        testUsingStack3();
    }

    private static void usingQueue() {
        System.out.println("\nUsing queue ");
        testUsingQueue1();
        testUsingQueue2();
        testUsingQueue3();
    }

    private static void usingList() {
        System.out.println("\nUsing List ");
        test1();
        test2();
        test3();
    }

    public static void test3() {
        System.out.println();
        List<NestedInteger> sample = Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4),
                        new NestedIntegerValues(
                                new NestedIntegerValues(new NestedIntegerValue(9),
                                        new NestedIntegerValues(new NestedIntegerValue(9))),
                                new NestedIntegerValue(6))));

        System.out.println(sample);
        NestedIteratorUsingList iterator = new NestedIteratorUsingList(sample);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    public static void test2() {
        System.out.println();
        List<NestedInteger> sample2 = Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4), new NestedIntegerValues(new NestedIntegerValue(6))));

        System.out.println(sample2);
        NestedIteratorUsingList iterator = new NestedIteratorUsingList(sample2);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    public static void test1() {
        System.out.println();
        List<NestedInteger> sample1 = Arrays.asList(
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)),
                new NestedIntegerValue(2),
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)));

        System.out.println(sample1);
        NestedIteratorUsingList iterator = new NestedIteratorUsingList(sample1);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }


    public static void testUsingQueue3() {
        System.out.println();
        List<NestedInteger> sample = Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4),
                        new NestedIntegerValues(
                                new NestedIntegerValues(new NestedIntegerValue(9),
                                        new NestedIntegerValues(new NestedIntegerValue(9))),
                                new NestedIntegerValue(6))));

        System.out.println(sample);
        NestedIteratorUsingQueue iterator = new NestedIteratorUsingQueue(sample);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    public static void testUsingQueue2() {
        System.out.println();
        List<NestedInteger> sample2 = Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4), new NestedIntegerValues(new NestedIntegerValue(6))));

        System.out.println(sample2);
        NestedIteratorUsingQueue iterator = new NestedIteratorUsingQueue(sample2);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    public static void testUsingQueue1() {
        System.out.println();
        List<NestedInteger> sample1 = Arrays.asList(
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)),
                new NestedIntegerValue(2),
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)));

        System.out.println(sample1);
        NestedIteratorUsingQueue iterator = new NestedIteratorUsingQueue(sample1);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }


    public static void testUsingStack3() {
        System.out.println();
        List<NestedInteger> sample = Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4),
                        new NestedIntegerValues(
                                new NestedIntegerValues(new NestedIntegerValue(9),
                                        new NestedIntegerValues(new NestedIntegerValue(9))),
                                new NestedIntegerValue(6))));

        System.out.println(sample);
        NestedIteratorUsingStack iterator = new NestedIteratorUsingStack(sample);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    public static void testUsingStack2() {
        System.out.println();
        List<NestedInteger> sample2 = Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4), new NestedIntegerValues(new NestedIntegerValue(6))));

        System.out.println(sample2);
        NestedIteratorUsingStack iterator = new NestedIteratorUsingStack(sample2);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }

    public static void testUsingStack1() {
        System.out.println();
        List<NestedInteger> sample1 = Arrays.asList(
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)),
                new NestedIntegerValue(2),
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)));

        System.out.println(sample1);
        NestedIteratorUsingStack iterator = new NestedIteratorUsingStack(sample1);
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }
}


/**
 * This is the smartest one and easiest of them all.
 * We basically process the given list and store all the values;
 * <p>
 * Advantages:
 * 1. Its fast
 * 2. this is fail safe iterator, means it won't throw concurrent modification exception
 * 3. good choice for multi threading
 * <p>
 * Disadvantages:
 * 1. This occupy lot of extra space, and
 * 2. this is not lazy in its behaviour rather proactive
 */
class NestedIteratorUsingList implements Iterator<Integer> {

    /**
     * use to store the elements from given list
     */
    private List<Integer> elements = new ArrayList<>();

    /**
     * to keep track the element which need to show
     */
    int index = 0;

    public NestedIteratorUsingList(List<NestedInteger> nestedList) {

        init(nestedList);
    }

    /**
     * store them recursively
     *
     * @param nestedList
     */
    private void init(List<NestedInteger> nestedList) {
        if (nestedList.isEmpty())
            return;

        for (NestedInteger nst : nestedList) {

            if (!nst.isInteger()) {
                init(nst.getList());

            } else
                elements.add(nst.getInteger());
        }
    }

    @Override
    public Integer next() {
        return elements.get(index++);

    }

    @Override
    public boolean hasNext() {
        if (index < this.elements.size())
            return true;
        else
            return false;
    }
}


/**
 * This is second variation of above algorithm, everything on above algorithm apply, apply on this too.
 * 1. Interviewer might ask you to use some inbuilt data structure if need.
 * We are just mimicking the queue in above solution, this true queue based solution
 * 2. Interviewer might ask you to minimize the overall process of processing
 * <p>
 * Neat and clean
 */
class NestedIteratorUsingQueue implements Iterator<Integer> {

    private Queue<Integer> queue;

    public NestedIteratorUsingQueue(List<NestedInteger> nestedList) {

        queue = new LinkedList<>();
        init(nestedList);
    }

    private void init(List<NestedInteger> nestedList) {
        if (nestedList.isEmpty())
            return;

        for (NestedInteger nst : nestedList) {

            if (!nst.isInteger()) {
                init(nst.getList());

            } else
                queue.offer(nst.getInteger());
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * This is another approach, which is lazy in nature. It has advantages on memory usage.
 */
class NestedIteratorUsingStack implements Iterator<Integer> {

    /**
     * keep a stack for which element we need to print
     */
    private Stack<NestedInteger> stack;

    public NestedIteratorUsingStack(List<NestedInteger> nestedList) {

        if (nestedList.isEmpty())
            return;

        stack = new Stack<>();

        /**
         * since we need to show from left to right direction, simply push all the elements in reverse order initially
         */
        for (int i = nestedList.size() - 1; i >= 0; i--)
            stack.push(nestedList.get(i));
    }


    @Override
    public Integer next() {

        return stack.pop().getInteger();

    }

    @Override
    public boolean hasNext() {
        /**
         * based on top, just process
         */
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            List<NestedInteger> nst = stack.pop().getList();

            for (int i = nst.size() - 1; i >= 0; i--) {
                stack.push(nst.get(i));
            }


        }
        return !stack.isEmpty();

    }
}

