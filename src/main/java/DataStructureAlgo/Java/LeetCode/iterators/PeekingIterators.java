package DataStructureAlgo.Java.LeetCode.iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Peeking Iterators
 * Link: TODO: Add Link
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class PeekingIterators {

    public static void main(String[] args) {
        PeekingIterator test = new PeekingIterator(Arrays.asList(1, 2, 3).iterator());
        PeekingIteratorCache cache = new PeekingIteratorCache(Arrays.asList(1, 2, 3).iterator());

        test.next();
        System.out.println("TT :" + test.peek());
        while (test.hasNext()) {
            System.out.println("\nPeek : " + test.peek());
            System.out.println("next : " + test.next());
        }

        System.out.println("\nCache\n");
        cache.next();
        System.out.println("CC :"+cache.peek());
        while (cache.hasNext()) {
            System.out.println("\nPeek : " + cache.peek());
            System.out.println("next : " + cache.next());
        }
    }
}


class PeekingIterator implements Iterator<Integer> {

    private Integer lastElement = null;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;

        if (this.iterator.hasNext())
            lastElement = iterator.next();

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {


        return lastElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {

        Integer toReturn = lastElement;
        if (iterator.hasNext())
            lastElement = iterator.next();
        else
            lastElement = null;

        return toReturn;

    }

    @Override
    public boolean hasNext() {


        return lastElement != null;
    }
}


class PeekingIteratorCache implements Iterator<Integer> {

    private List<Integer> items = new ArrayList<>();
    int index = 0;


    public PeekingIteratorCache(Iterator<Integer> iterator) {
        while (iterator.hasNext())
            items.add(iterator.next());

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {

        return items.get(index);
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {

        return items.get(index++);

    }

    @Override
    public boolean hasNext() {

        return !(items.size() == index);
    }
}