package Java.LeetCode.iterators;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description: https://leetcode.com/problems/zigzag-iterator
 * Zigzag Iterator
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * <p>
 * Example:
 * <p>
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * <p>
 * Output: [1,3,2,4,5,6]
 * <p>
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,3,2,4,5,6].
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * <p>
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:
 * <p>
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * <p>
 * Output: [1,4,8,2,5,9,3,6,7].
 */
public class ZigZagOrCycleIterators {


    public static void main(String[] args) {

        test(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6, 7), Arrays.asList(8, 9)));
        test(Arrays.asList(Arrays.asList(1, 2, 3, 10, 11, 12, 13), Arrays.asList(4, 5, 6, 7), Arrays.asList(8, 9, 14, 15, 16)));


    }

    private static void test(List<List<Integer>> asList) {
        testCycle(asList);
        testZig(asList);
    }

    private static void testCycle(List<List<Integer>> asList) {
        System.out.println("\nCycle:\n");
        CycleIterator iterator = new CycleIterator(asList);

        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
    }

    private static void testZig(List<List<Integer>> asList) {
        System.out.println("\nzigZag:\n");
        ZigZagIterator iterator = new ZigZagIterator(asList);

        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
    }

}

/**
 * Using Inner list;
 * Next - O(1)
 * HashNext - O(size)
 */
class CycleIterator implements Iterator<Integer> {


    private final List<Integer> innerList;
    private final List<List<Integer>> items;
    private int outerIndex = 0;
    private int innerIndex = 0;


    public CycleIterator(List<List<Integer>> items) {
        this.items = items;
        innerList = new ArrayList<>();

        buildInnerList();

    }

    /**
     * O(size) where size is size of input list
     */
    private void buildInnerList() {
        innerList.clear();
        for (List<Integer> list : items) {
            if (!list.isEmpty() && list.size() > outerIndex)
                innerList.add(list.get(outerIndex));
        }
        outerIndex++;

    }

    /**
     * O(size) where size is size of input list
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        if (innerIndex == innerList.size()) {
            buildInnerList();
            innerIndex = 0;
        }

        return !innerList.isEmpty();
    }

    /**
     * O(1)
     *
     * @return
     */
    @Override
    public Integer next() {
        return innerList.get(innerIndex++);
    }
}

class ZigZagIterator implements Iterator<Integer> {

    private LinkedList<Iterator<Integer>> iterators;
    private int index = 0;

    public ZigZagIterator(List<List<Integer>> items) {

        iterators = new LinkedList<>();
        for (List<Integer> list : items)
            iterators.add(list.iterator());

    }

    @Override
    public boolean hasNext() {
        return !iterators.isEmpty();
    }

    @Override
    public Integer next() {

        Iterator<Integer> current = iterators.get(index);

        Integer next = current.next();

        if (!current.hasNext())
            iterators.remove(index); //this is O(n) But you can implement your own doubly linked list to make it work in O(1)
        else
            index++;

        if (index >= iterators.size())
            index = 0;


        return next;
    }
}