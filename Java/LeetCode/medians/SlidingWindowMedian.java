package Java.LeetCode.medians;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-08
 * Description: https://leetcode.com/problems/sliding-window-median/
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * Examples:
 * [2,3,4] , the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <p>
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7       -1
 * 1  3 [-1  -3  5] 3  6  7       -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * <p>
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 * <p>
 * {@link Java.LeetCode.SlidingWindowMaximum}
 */
public class SlidingWindowMedian {

    public static void main(String args[]) {

        SolutionSlidingWindowMedian median = new SolutionSlidingWindowMedian();
        test4(median);
        test3(median);
        test2(median);
        test1(median);


    }

    private static void test4(SolutionSlidingWindowMedian median) {

        int nums[] = {1, 4, 2, 3};
        double[] medians = median.medianSlidingWindow(nums, 4);

        for (double d : medians)
            System.out.println(d);
    }

    private static void test3(SolutionSlidingWindowMedian median) {
        int nums[] = {2147483647, 2147483647};
        double[] medians = median.medianSlidingWindow(nums, 2);

        for (double d : medians)
            System.out.println(d);
    }

    private static void test1(SolutionSlidingWindowMedian median) {
        int nums[] = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] medians = median.medianSlidingWindow(nums, 3);

        for (double d : medians)
            System.out.println(d);
    }

    private static void test2(SolutionSlidingWindowMedian median) {
        int nums[] = {1, 2};
        double[] medians = median.medianSlidingWindow(nums, 1);

        for (double d : medians)
            System.out.println(d);
    }


}

/**
 *   //TODO: Use Tree map instead to achieve O(n.Logk)
 */

class SolutionSlidingWindowMedian {


    private class MedianDataStructure {
        //TODO: Use Tree map instead to achieve O(n.Logk)

        private final PriorityQueue<Integer> maxHeap; //left //contains all elements which are lesser than median
        private final PriorityQueue<Integer> minHeap; //right  //contains all elements which are bigger/equal than median


        public MedianDataStructure() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }


        /**
         * Push element based on condition that this element lie which side of median;
         * <p>
         * if element > median then goes on right side (mean heap)
         * otherwise max heap
         * <p>
         * Since we are assuming on odd number of case, the maxHeap (left side) is our median so we'll check using maxHeap
         *
         * @param num O(logK) + O(logK) = O(logK)
         */
        public void offer(int num) {

            //O(logK)
            if (!maxHeap.isEmpty() && num < maxHeap.peek())//goes left
                maxHeap.offer(num);
            else
                minHeap.offer(num);

            //O(logK)
            balance();
        }


        /**
         * Its simple as explained below
         * <p>
         * O(1)
         *
         * @return
         */
        public double findMedian() {

            if (maxHeap.size() == minHeap.size())
                return (double) maxHeap.peek() / 2.0 + (double) minHeap.peek() / 2.0;
            else
                return (double) maxHeap.peek();

        }


        /**
         * Make sure max heap has one element more or balance
         * O(logK)
         */

        private void balance() {

            /**
             * if max heap has more than 1 element as unbalanced
             */
            while (maxHeap.size() - minHeap.size() > 1)
                minHeap.offer(maxHeap.poll());

            /**
             * if max heap has less element then min
             */
            while (maxHeap.size() < minHeap.size())
                maxHeap.offer(minHeap.poll());
        }


        /**
         * if any number is
         * O(n)
         *
         * @param num
         */
        public void remove(int num) {

            if (num <= maxHeap.peek())
                maxHeap.remove(num);
            else
                minHeap.remove(num);
        }
    }

    /**
     * The core idea of this solution is to maintain lower value of low side and higher value of high side.
     * <p>
     * What is a median in a array?
     * Median is a guy ;
     * 1. either who sits in between of all numbers in sorted array, if total is odd
     * 2. or average of middle two element in sorted array. if total is even
     * <p>
     * This implies that left side of median is all smaller than median and right side of median is all greater then median.
     * To calculate, we only need to know how many are on left side and right side
     * <p>
     * 1. If they are equal (total=odd) then median will lie on high side (due to 0 indexing)
     * 2. otherwise it will be average of left side max and right side min.
     * <p>
     * Hence we need to know at any moment what is max at left side and what is min or right side.
     * <p>
     * Data structure:
     * The best data structure which provide this is Heaps. Heap can give max and min in O(1) time at any moment.
     * <p>
     * Lets make
     * maxHeap on left side of Median
     * MinHeap on right side of Median
     * <p>
     * Case 1: equal size;
     * As stated above when size of both the heap are equal, then essentially there is even number of elements : take average of roots of heaps
     * <p>
     * case 2: Un-equal size
     * Since we follow 0 indexing then there is always 1 more element on left side, so median is maxHeap root
     * <p>
     * <p>
     * Important:
     * At any moment we need to maintain the size of heap such that they don't differ more than 1 element.
     * if so, then make sure maxHeap has 1 element more than min heap Otherwise they should be equal
     *
     * @param nums
     * @param k
     * @return
     */

    public double[] medianSlidingWindow(int[] nums, int k) {

        MedianDataStructure median = new MedianDataStructure();

        int n = nums.length;
        double[] res = new double[n - k + 1];
        int r = 0;

        //O(k.logK)
        for (int i = 0; i < k; i++) {
            median.offer(nums[i]);
        }

        //O(n.logK) => O(n.n)
        for (int i = k; i < n; i++) {

            res[r++] = median.findMedian();
            int remove = nums[i - k];
            median.remove(remove); // this could be O(n/2) time ...
            median.offer(nums[i]);
        }
        res[r] = median.findMedian();

        return res;
    }


}
