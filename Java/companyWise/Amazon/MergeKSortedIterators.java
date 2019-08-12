package Java.companyWise.Amazon;

import Java.LeetCode.iterators.PeekingIterators;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description: https://leetcode.com/discuss/interview-question/345744
 * Given a list of k sorted iterators. Implement MergingIterator to merge them.
 *
 * <p>
 * class MergingIterator implements Iterator<Integer> {
 * public MergingIterator(List<Iterator<Integer>> iterators) {
 * }
 * <p>
 * public boolean hasNext() {
 * }
 * <p>
 * public Integer next() {
 * }
 * }
 * Example:
 * <p>
 * MergingIterator itr = new MergingIterator([[2, 5, 9], [], [4, 10]]);
 * itr.hasNext(); // true
 * itr.next(); // 2
 * itr.next(); // 4
 * itr.next(); // 5
 * itr.next(); // 9
 * itr.next(); // 10
 * itr.hasNext(); // false
 * itr.next(); // error
 * <p>
 * similar to
 * {@link PeekingIterators}
 * {@link Java.LeetCode.iterators.ZigZagOrCycleIterators}
 * <p>
 * <p>
 * [GOOGLE] [AMAZON]
 */
public class MergeKSortedIterators {

    public static void main(String[] args) {
        test(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6, 7), Arrays.asList(8, 9)));
        test(Arrays.asList(Arrays.asList(1, 2, 3, 10, 11, 12, 13), Arrays.asList(4, 5, 6, 7), Arrays.asList(8, 9, 14, 15, 16)));
    }

    private static void test(List<List<Integer>> asList) {

        priorityQueue(asList);
        priorityQueuePeekIterator(asList);
        priorityQueueCache(asList);
    }

    private static void priorityQueue(List<List<Integer>> asList) {

        System.out.println("\n\nUsing Priority Queue-> Input :" + asList);
        MergeKSortedIteratorsUsingPriorityQueueOnly.MergingIterator iterator = new MergeKSortedIteratorsUsingPriorityQueueOnly.MergingIterator(asList.stream().map(l -> l.iterator()).collect(Collectors.toList()));


        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");

    }


    private static void priorityQueuePeekIterator(List<List<Integer>> asList) {

        System.out.println("\n\nUsing Peek Iterator-> Input :" + asList);
        MergeKSortedIteratorsUsingPriorityQueueAndPeekIterator.MergingIterator iterator = new MergeKSortedIteratorsUsingPriorityQueueAndPeekIterator.MergingIterator(asList.stream().map(l -> l.iterator()).collect(Collectors.toList()));


        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");

    }

    private static void priorityQueueCache(List<List<Integer>> asList) {

        System.out.println("\n\nUsing cache-> Input :" + asList);
        MergeKSortedIteratorsUsingPriorityQueueCached.MergingIterator iterator = new MergeKSortedIteratorsUsingPriorityQueueCached.MergingIterator(asList.stream().map(l -> l.iterator()).collect(Collectors.toList()));


        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");

    }


}


/**
 * This approach take the advantage of keeping iterator and the next value for this iterator in
 * Priority queue it self.
 * <p>
 * Algorithm:
 * 1. Initially we'll push all non-empty iterator first value and iterator (after taking first value) in priority queue as pair
 * 2. For next, we'll poll the top Pair and gets its value, then check does this "poll" iterator has more entry to give.
 * If yes, fetch next entry form this iterator, put a new entry in Priority Queue as this iterator (after taking  value) as key and fetched value as value
 * 3. HasNext is simply can test does priority queue is empty or not.
 * <p>
 * <p>
 * HashNext: O(1) where size is Length of iterators list
 * next: O(1)
 */
class MergeKSortedIteratorsUsingPriorityQueueOnly {


    /**
     * Using Sorted list like data structure;
     * Using Priority Queue
     */
    static class MergingIterator implements Iterator<Integer> {

        private final PriorityQueue<Pair<Iterator<Integer>, Integer>> priorityQueue;


        public MergingIterator(List<Iterator<Integer>> iterators) {

            priorityQueue = new PriorityQueue<>(Comparator.comparing(Pair::getValue));
            init(iterators);
        }

        /**
         * O(size * log(size)) where size is Length of iterators list
         */
        private final void init(List<Iterator<Integer>> iterators) {
            for (Iterator<Integer> iterator : iterators) {

                if (iterator.hasNext()) {

                    Integer value = iterator.next();
                    priorityQueue.offer(new Pair<>(iterator, value));
                }
            }

        }

        /**
         * O(1) where size is Length of iterators list
         */
        public boolean hasNext() {
            return !priorityQueue.isEmpty();


        }

        /**
         * O(log(size))
         */
        public Integer next() {

            Pair<Iterator<Integer>, Integer> poll = priorityQueue.poll();

            Integer toReturn = poll.getValue();
            if (poll.getKey().hasNext()) {
                Integer next = poll.getKey().next();
                priorityQueue.offer(new Pair<>(poll.getKey(), next));
            }

            return toReturn;

        }
    }
}

/**
 * HashNext: O(size) where size is Length of iterators list
 * next: O(1)
 */
class MergeKSortedIteratorsUsingPriorityQueueAndPeekIterator {

    /**
     * {@link PeekingIterators}
     *
     * @param <E>
     */
    private static class PeekingIterator<E> implements Iterator<E> {

        private E lastElement = null;
        private Iterator<E> iterator;

        public PeekingIterator(Iterator<E> iterator) {
            this.iterator = iterator;

            if (this.iterator.hasNext())
                lastElement = iterator.next();

        }

        // Returns the next element in the iteration without advancing the iterator.
        public E peek() {


            return lastElement;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public E next() {

            E toReturn = lastElement;
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


    /**
     * Using Sorted list like data structure;
     * Using Priority Queue
     */
    static class MergingIterator implements Iterator<Integer> {

        private final PriorityQueue<PeekingIterator<Integer>> priorityQueue;

        public MergingIterator(List<Iterator<Integer>> iterators) {

            priorityQueue = new PriorityQueue<>(Comparator.comparing(PeekingIterator::peek));
            init(iterators);
        }

        /**
         * O(size * log(size)) where size is Length of iterators list
         */
        private final void init(List<Iterator<Integer>> iterators) {
            for (Iterator<Integer> iterator : iterators) {

                if (iterator.hasNext())
                    priorityQueue.offer(new PeekingIterator<>(iterator));
            }

        }

        /**
         * O(1) where size is Length of iterators list
         */
        public boolean hasNext() {
            return !priorityQueue.isEmpty();


        }

        /**
         * O(log(size))
         */
        public Integer next() {

            PeekingIterator<Integer> poll = priorityQueue.poll();
            Integer toReturn = poll.next();

            if (poll.hasNext())
                priorityQueue.offer(poll);

            return toReturn;

        }
    }
}


/**
 * This kind of implementation is not really a iterator. As it cache the whole data.
 * <p>
 * HashNext: O(1) where size is Length of iterators list
 * next: O(1)
 */
class MergeKSortedIteratorsUsingPriorityQueueCached {


    /**
     * Using Sorted list like data structure;
     * Using Priority Queue
     */
    static class MergingIterator implements Iterator<Integer> {

        private final PriorityQueue<Integer> priorityQueue;

        public MergingIterator(List<Iterator<Integer>> iterators) {

            priorityQueue = new PriorityQueue<>();
            init(iterators);
        }

        /**
         * O(size * log(size)) where size is Length of iterators list
         */
        private final void init(List<Iterator<Integer>> iterators) {
            for (Iterator<Integer> iterator : iterators) {

                while (iterator.hasNext())
                    priorityQueue.offer(iterator.next());
            }

        }

        /**
         * O(1) where size is Length of iterators list
         */
        public boolean hasNext() {
            return !priorityQueue.isEmpty();


        }

        /**
         * O(log(size))
         */
        public Integer next() {

            return priorityQueue.poll();

        }
    }
}

