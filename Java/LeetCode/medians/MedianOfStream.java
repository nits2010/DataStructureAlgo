package Java.LeetCode.medians;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-08
 * Description: https://leetcode.com/problems/find-median-from-data-stream/
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * For example,
 * [2,3,4], the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * <p>
 * <p>
 * Example:
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * <p>
 * Follow up:
 * <p>
 *
 * 1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 * We can maintain an integer array of length 100 to store the count of each number along with a total count. Then, we can iterate over the array to find the middle value to get our median.
 *
 * Time and space complexity would be O(100) = O(1).
 * https://leetcode.com/problems/statistics-from-a-large-sample/description/
 *
 * 2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 * In this case, we need an integer array of length 100 and a hashmap for these numbers that are not in [0,100].
 */
public class MedianOfStream {

    public static void main(String []args) {
        SolutionMedianFinder obj = new SolutionMedianFinder();
        int ints[] = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};

        for (int i = 0; i < ints.length; i++) {
            obj.addNum(ints[i]);
            System.out.println(obj.findMedian());
        }
    }

}

class MedianOfStreamDataStructure<E> {

    double median = 0.0;
    PriorityQueue<E> minHeap;
    PriorityQueue<E> maxHeap;


    public MedianOfStreamDataStructure() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }


}

class SolutionMedianFinder {

       private MedianOfStreamDataStructure<Integer> median = new MedianOfStreamDataStructure<>();

    public void addNum(int num) {

        int diff = getDiff(median.maxHeap.size(), median.minHeap.size());

        switch (diff) {

            case 1: //max heap > min heap

                //is for max heap ?
                if (num < median.median) {

                    median.minHeap.offer(median.maxHeap.poll());
                    median.maxHeap.offer(num);

                } else {
                    //is for min heap ?
                    median.minHeap.offer(num);
                }

                break;

            case -1: //min heap > max heap


                //is for max heap ?
                if (num < median.median) {

                    median.maxHeap.offer(num);

                } else {
                    //is for min heap ?
                    median.maxHeap.offer(median.minHeap.poll());
                    median.minHeap.offer(num);
                }


                break;

            case 0: //equal

                //is for max heap ?
                if (num < median.median) {

                    median.maxHeap.offer(num);

                } else {
                    //is for min heap ?
                    median.minHeap.offer(num);
                }

                break;

        }

        return;

    }

    public double findMedian() {
        int diff = getDiff(median.maxHeap.size(), median.minHeap.size());

        switch (diff) {
            case 1:
                return (median.median = median.maxHeap.peek());
            case -1:
                return (median.median = median.minHeap.peek());
            case 0:
                return (median.median = ((double) (median.minHeap.peek() + median.maxHeap.peek())) / 2);
        }
        return median.median;
    }

    private int getDiff(int x, int y) {
        return x - y;
    }

}

