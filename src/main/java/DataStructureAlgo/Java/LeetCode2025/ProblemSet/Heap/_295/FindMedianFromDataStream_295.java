package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap._295;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1033.StatisticsFromALargeSample_1033;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1033.StatisticsFromALargeSample_1033;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date: 9/1/2024
 * Question Category: 295. Find Median from Data Stream
 * Description: https://leetcode.com/problems/find-median-from-data-stream/description/
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 *
 * Constraints:
 *
 * -105 <= num <= 105
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 *
 *
 * Follow up
 * Q-1: If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * We can maintain an integer array of length 100 to store the count of each number along with a total count.
 * Then, we can iterate over the array to find the middle value to get our median. Time and space complexity would be O(100) = O(1).
 * REF: Time and space complexity would be O(100) = O(1).
 *  * <a href="https://leetcode.com/problems/statistics-from-a-large-sample/description/">...</a>
 *  {@link StatisticsFromALargeSample_1033}
 *
 * Q-2: If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * In this case, we can keep a counter for lessThanHundred and greaterThanHundred.
 * As we know, the solution will be definitely in 0-100 we don’t need to know those numbers which are >100 or <0, only count of them will be enough.
 *
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.medians.MedianOfStream}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @hard
 * @TwoPointers
 * @Design
 * @Sorting
 * @Heap(PriorityQueue)
 * @DataStream <p>
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Atlassian
 * @Bloomberg
 * @ByteDance
 * @eBay
 * @Expedia
 * @Facebook
 * @GoldmanSachs
 * @Google
 * @Microsoft
 * @Netflix
 * @Oracle
 * @Pinterest
 * @Qualtrics
 * @Snapchat
 * @Twitter
 * @Uber
 * @VMware
 * @Yahoo
 * @Editorial https://leetcode.com/problems/find-median-from-data-stream/solutions/5719512/2-variations-using-heaps-easy-to-understand-simplified-solutions
 */
public class FindMedianFromDataStream_295 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4}, new double[]{5.0, 10.0, 5.0, 4.0, 3.0, 4.0, 5.0, 6.0, 7.0, 6.5, 7.0, 6.5});
        test &= test(new int[]{1, 2, 3}, new double[]{1, 1.5, 2});
        System.out.println("==========================================");
        System.out.println(test ? "\nAll Passed" : "\nSomething Failed");
    }

    private static boolean test(int[] nums, double[] expected) {
        System.out.println("------------------------");
        System.out.println("Input Stream: " + Arrays.toString(nums));
        System.out.println("Expected Stream: " + Arrays.toString(expected));

        MedianFinderUsingPQ.MedianFinder medianFinder = new MedianFinderUsingPQ.MedianFinder();
        double[] medians = execute(nums, medianFinder);
        boolean equals = Arrays.equals(medians, expected);
        System.out.println("\nOutput :" + Arrays.toString(medians) + "\nTest Result : " + (equals ? "Passed" : "Failed"));


        MedianFinderUsingPQSimplified.MedianFinder medianFinderSimplified = new MedianFinderUsingPQSimplified.MedianFinder();
        medians = execute(nums, medianFinderSimplified);
        boolean equalsSimplified = Arrays.equals(medians, expected);
        System.out.println("\nOutput Simplified :" + Arrays.toString(medians) + "\nTest Result : " + (equalsSimplified ? "Passed" : "Failed"));


        return equals && equalsSimplified;
    }

    private static double[] execute(int[] nums, IMedianFinder medianFinder) {
        double[] medians = new double[nums.length];
        int i = 0;
        for (int n : nums) {
            medianFinder.addNum(n);
            medians[i++] = medianFinder.findMedian();
        }
        return medians;
    }


}

interface IMedianFinder {
    void addNum(int num);

    double findMedian();
}


class MedianFinderUsingPQSimplified {

    static class MedianFinder implements IMedianFinder {

        // a max heap off all the element less equal than median
        final PriorityQueue<Integer> maxHeap;

        // a min heap off all the element greater than median
        final PriorityQueue<Integer> minHeap;

        //median of stream so far
        double median = 0.0;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();

        }

        /**
         * T / S : O(log(n)) / O(n)
         *
         * @param num
         */
        public void addNum(int num) {

            //evaluate the current element should be part of min heap (of max elements) or max heap (of min elements)

            //if this element is less than the median, then it has to be on the left side (maxHeap)

            //if this element is greater than or equal to the median, then it has to be on the right side (minHeap)

            //simplified logic is, if the upcoming element is less than the median then it has to go on the left-side (maxheap)
            //otherwise it has to go on the right side (minHeap)
            //balance the heap so that maxHeap.size() >= minHeap.size()


            if (maxHeap.isEmpty() || median >= num)
                maxHeap.offer(num);
            else
                minHeap.offer(num);

            //balance heaps; make either equal or maxHeap has 1 more element
            if (maxHeap.size() > minHeap.size() + 1) { //if max heap has 2 more element then minHeap; make maxHeap can have atmost 1 more element
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            computeMedian();

        }

        /**
         * T : O(1)
         */
        private void computeMedian() {

            if (maxHeap.size() == minHeap.size())
                median = ((maxHeap.peek() + minHeap.peek()) / 2.0);
            else
                median = maxHeap.peek();
        }

        /**
         * T : O(1)
         */
        public double findMedian() {

            return median;
        }
    }
}


class MedianFinderUsingPQ {

    static class MedianFinder implements IMedianFinder {

        // a max heap off all the element less equal than median
        final PriorityQueue<Integer> maxHeap;

        // a min heap off all the element greater than median
        final PriorityQueue<Integer> minHeap;

        //median of stream so far
        double median = 0.0;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();

        }

        /**
         * T / S : O(log(n)) / O(n)
         *
         * @param num
         */
        public void addNum(int num) {

            final int sizeDiff = maxHeap.size() - minHeap.size();

            //evaluate the current element should be part of min heap (of max elements) or max heap (of min elements)

            //if this element is less than the median, then it has to be on the left side (maxHeap)
            //current: ; median = 5; leftSide-MaxHeap [5,1] ; rightSide-MinHeap [15]
            //add(3); maxHeap > minHeap ; 3 < median; => hence we have to add it on leftSide-Maxheap however, its size is greater than min heap; poll and offer
            // [1,*3*,5,15] ; leftSide-MaxHeap [3,1] ; rightSide-MinHeap [5,15] => median ((5+3)/2)


            //if this element is greater than or equal to the median, then it has to be on the right side (minHeap)
            //We can simply add this since, it will make heap size equal
            //current:  median = 4 {1,3,15} ;leftSide-MaxHeap [3,1] ; rightSide-MinHeap [15]
            //add(7) : 7 > 4 ; this will go on the right side; [1,3,7,15]  ;leftSide-MaxHeap [3,1] ; rightSide-MinHeap [7,15] => median ((3+7)/2)


            //simplified logic is, if the upcoming element is less than the median then it has to go on the left-side (maxheap)
            // otherwise it has to go on the right side (minHeap)
            //balance the heap so that 0 <= diff <= 1


            switch (sizeDiff) {

                case 1: //max heap > min heap

                    //this element is on the left-side[max heap], push in max heap and make sure size diff >=1
                    if (median > num) {
                        minHeap.offer(maxHeap.poll()); //since maxHeap has more element
                        maxHeap.offer(num);
                    } else {
                        //this element is on the right-side [min heap], push in min heap; will make size equals
                        minHeap.offer(num);
                    }
                    break;

                case -1: //max heap < min heap

                    //this element is on the left-side [max heap], push in max heap; will make size equals
                    if (median > num) {
                        maxHeap.offer(num);

                    } else {
                        //this element is on the right-side [min heap], push in min heap; make sure size diff >=1
                        maxHeap.offer(minHeap.poll()); //since minHeap has more element
                        minHeap.offer(num);
                    }
                    break;

                case 0: //max heap = min heap

                    //this element is on the left-side [max heap], push in max heap; will make size equals
                    if (median > num) {
                        maxHeap.offer(num);
                    } else {
                        //this element is on the right-side [min heap], push in min heap; make sure size diff >=1
                        minHeap.offer(num);
                    }
                    break;
            }
            computeMedian();

        }

        /**
         * T : O(1)
         */
        private void computeMedian() {

            final int sizeDiff = maxHeap.size() - minHeap.size();

            switch (sizeDiff) {

                case 1:
                    median = maxHeap.peek();
                    break;
                case -1:
                    median = minHeap.peek();
                    break;
                case 0:
                    median = ((double) (maxHeap.peek() + minHeap.peek())) / 2;
                    break;
            }
        }

        /**
         * T : O(1)
         */
        public double findMedian() {

            return median;
        }
    }
}