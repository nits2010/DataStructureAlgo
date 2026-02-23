package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._480;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/3/2025
 * Question Title: 480. Sliding Window Median
 * Link: https://leetcode.com/problems/sliding-window-median/description/
 * Description: The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 * <p>
 * For examples, if arr = [2,3,4], the median is 3.
 * For example, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 * 1 [3  -1  -3] 5  3  6  7       -1
 * 1  3 [-1  -3  5] 3  6  7       -1
 * 1  3  -1 [-3  5  3] 6  7        3
 * 1  3  -1  -3 [5  3  6] 7        5
 * 1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.medians.SlidingWindowMedian}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @HashTable
 * @SlidingWindow
 * @Heap(PriorityQueue) <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Spotify
 * @Microsoft
 * @HBO
 * @Amazon
 * @Google
 * @Oracle
 * @Snapchat <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SlidingWindowMedian_480 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new double[]{1.00000, -1.00000, -1.00000, 3.00000, 5.00000, 6.00000}));
        tests.add(test(new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2}, 3, new double[]{2.00000, 3.00000, 3.00000, 3.00000, 2.00000, 3.00000, 2.00000}));
        //duplicate numbers
        tests.add(test(new int[]{6,5,9,5,4,9,1,7,5,5}, 4, new double[]{5.50000,5.00000,7.00000,4.50000,5.50000,6.00000,5.00000}));
        //overflow
        tests.add(test(new int[]{2147483647, 2147483647}, 2, new double[]{2147483647.00000}));

        tests.add(test(new int[]{1,3,-1}, 3, new double[]{1.00000}));
        tests.add(test(new int[]{1,2,3,4,5,6,7,8,91}, 3, new double[]{2.00000,3.00000,4.00000,5.00000,6.00000,7.00000,8.00000}));
        tests.add(test(new int[]{1, 4, 2, 3}, 4, new double[]{2.50000}));


        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, double[] expected) {

        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        double[] output ;
        boolean pass, finalPass = true;

        SolutionPriorityQueue solutionPriorityQueue = new SolutionPriorityQueue();
        output = solutionPriorityQueue.medianSlidingWindow(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PriorityQueue - TLE", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        Solution_PriorityQueueOptimized solutionPriorityQueueOptimized = new Solution_PriorityQueueOptimized();
        output = solutionPriorityQueueOptimized.medianSlidingWindow(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PriorityQueue - Optimized", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        SolutionTreeSet solutionTreeSet = new SolutionTreeSet();
        output = solutionTreeSet.medianSlidingWindow(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"TreeSet", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * The core idea of this solution is to maintain lower value of low side and higher value of high side.
     * <p>
     * What is a median in a array?
     * Median is a guy ;
     * 1. either who sits in between of all numbers in sorted array, if total is odd
     * 2. or average of middle two element in sorted array. if total is even
     * <p>
     * This implies that the left side of median is all smaller than median and right side of median is all greater than median.
     * To calculate, we only need to know how many are on left side and right side
     * <p>
     * 1. If they are equal (total=odd) then median will lie on high side (due to 0 indexing)
     * 2. otherwise it will be average of left side max and right side min.
     * <p>
     * Hence we need to know at any moment what is max at left side and what is min or right side.
     * <p>
     * Data structure:
     * The best data structure which provides this is Heaps. Heap can give max and min in O(1) time at any moment.
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
     * <p>
     * ------------
     * This solution gets TLE because of remove method
     * This remove method, first check contains in a heap, which takes O(k) time and then remove it which also takes O(k) times which makes remove inefficient as O(k)
     * that leads entire find median as O(k) times instead of O(logK) or O(1) beause of which we choose Heap.
     * <p>
     * Even if we avoid contains, by maintaining membership of idx (that defines idx is in which heap), it still takes O(k) time.
     */
    static class SolutionPriorityQueue {

        //to avoid duplicate value, we need to make sure to use index
        class Node {
            int idx;
            long value;

            Node(int idx, long num) {
                this.idx = idx;
                this.value = num;
            }

            @Override
            public String toString() {
                return idx + ":" + value;
            }
        }

        public double[] medianSlidingWindow(int[] nums, int k) {

            int n = nums.length;
            double[] medians = new double[n - k + 1];
            Map<Integer, Node> map = new HashMap<>();
            PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingLong(t -> t.value)); // contains the right side of element of median in sorted order
            PriorityQueue<Node> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b.value, a.value)); // contains the left side of element of median in sorted order

            int windowStart = 0, windowEnd = 0;

            while (windowEnd < k) {
                add(minHeap, maxHeap, map, windowEnd, nums[windowEnd]);
                windowEnd++;
            }

            while (windowEnd < n) {
                medians[windowEnd - k] = getMedian(minHeap, maxHeap);

                //remove windowStart to maintain k size
                remove(windowStart, minHeap, maxHeap, map);
                windowStart++;

                add(minHeap, maxHeap, map, windowEnd, nums[windowEnd]);
                windowEnd++;

            }
            medians[windowEnd - k] = getMedian(minHeap, maxHeap);

            return medians;

        }

        private void add(PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap, Map<Integer, Node> map, int idx,
                         int num) {
            Node node = new Node(idx, num);
            map.put(idx, node);

            if (maxHeap.isEmpty() || num <= maxHeap.peek().value)
                maxHeap.offer(node);
            else
                minHeap.offer(node);

            balance(minHeap, maxHeap);

        }

        private void balance(PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap) {
            if (maxHeap.size() > minHeap.size() + 1)
                minHeap.offer(maxHeap.poll());

            if (minHeap.size() > maxHeap.size())
                maxHeap.offer(minHeap.poll());
        }

        private double getMedian(PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap) {
            Node max = maxHeap.peek();
            Node min = minHeap.peek();

            if (maxHeap.size() == minHeap.size()) {
                return (((double) max.value + min.value) / 2.0);
            } else {
                return (double) max.value;
            } 

        }

        private void remove(int idx, PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap, Map<Integer, Node> map) {
            Node node = map.get(idx);

            if (node != null) {

                if (maxHeap.contains(node))
                    maxHeap.remove(node);
                else
                    minHeap.remove(node);
                map.remove(idx);

                balance(minHeap, maxHeap);
            }

        }
    }


    /**
     * The above remove method is O(k) as it scans the heap and then removes it, which takes O(k) time.
     * To improve on it and make it O(logk), we need to make sure that poll itself removes the element which goes out of a window.
     * However, a poll keeps only a top element and our out-of-window element could be anywhere. Which makes it hard to use it.
     * <p>
     * To overcome this, we will use "lazy delete", we will keep track the element which needs to be removed, and as soon as it reaches the peek of any heap, we start removing it.
     * This way, we will only remove a peek of the heap which makes O(logk)
     * <p>
     * To do so, we will keep a frequency map (since an element could be duplicated), and whenever the remove function needs to call, we will track if that element can be remove from
     * the peek or not.
     */

    static class Solution_PriorityQueueOptimized {

        class MedianFinder {
            Map<Integer, Integer> map = new HashMap<>(); //map of elements vs frequency
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // contains the right side of element of median in sorted order
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // contains the left side of element of median in sorted order
            int minHeapSize = 0;
            int maxHeapSize = 0;

            private void add(int num) {

                if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                    maxHeap.offer(num);
                    maxHeapSize++;
                } else {
                    minHeap.offer(num);
                    minHeapSize++;
                }

                balance();

            }

            private void balance() {
                if (maxHeapSize > minHeapSize + 1) {
                    minHeap.offer(maxHeap.poll());
                    maxHeapSize--;
                    minHeapSize++;

                    purne(maxHeap);

                } else if (minHeapSize > maxHeapSize) {
                    maxHeap.offer(minHeap.poll());
                    maxHeapSize++;
                    minHeapSize--;

                    purne(minHeap);
                }
            }

            private void remove(int num) {
                map.merge(num, 1, Integer::sum); //increase the frequency of the element that needs to be remove

                //figure out where it is in heap

                if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {

                    //this lies in max heap; reduce the size
                    maxHeapSize--;

                    //does the number be on the top to be removed ?
                    if (num == maxHeap.peek()) {
                        //remove it
                        purne(maxHeap);
                    }
                } else {
                    //this lies in min heap; reduce the size
                    minHeapSize--;

                    //does the number be on the top to be removed ?
                    if (num == minHeap.peek()) {
                        //remove it
                        purne(minHeap);
                    }
                }

                balance();
            }

            private void purne(PriorityQueue<Integer> heap) {

                //keep removing all lazy elements that need to be remove
                while (!heap.isEmpty() && map.containsKey(heap.peek())) {
                    int num = heap.poll();

                    if (map.merge(num, -1, Integer::sum) == 0) {
                        map.remove(num);
                    }
                }
            }

            private double getMedian() {
                if (maxHeapSize == minHeapSize) {
                    return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
                } else {
                    return (double) maxHeap.peek();
                }
            }

        }

        public double[] medianSlidingWindow(int[] nums, int k) {

            int n = nums.length;
            double[] medians = new double[n - k + 1];
            MedianFinder medianFinder = new MedianFinder();

            int windowStart = 0, windowEnd = 0;

            while (windowEnd < k) {
                medianFinder.add(nums[windowEnd]);
                windowEnd++;
            }

            while (windowEnd < n) {
                medians[windowEnd - k] = medianFinder.getMedian();

                //remove windowStart to maintain k size
                medianFinder.remove(nums[windowStart]);
                windowStart++;

                medianFinder.add(nums[windowEnd]);
                windowEnd++;

            }
            medians[windowEnd - k] = medianFinder.getMedian();

            return medians;

        }
    }


    /**
     * Approach 2: While lazy delete solve the problem of remove from O(k) to approximately O(logk), it still lags and hard to code all these during interview.
     * <p>
     * In order to make sure that, remove takes at max O(logK) times, we need to use a similar data structure
     * which keeps min and max at top to get O(1) time and remove takes O(logK) times.
     * Binary Search tree is a great peak.
     * TreeMap or TreeSet does the job; however, we also need to make sure we keep duplicates as an array might have duplicates.
     */

    static class SolutionTreeSet {

        class MedianFinder {

            TreeSet<Integer> left;
            TreeSet<Integer> right;
            int[] nums;

            MedianFinder(int[] nums) {
                this.nums = nums;

                //if both numbers are same, then sort based on index otherwise by number
                Comparator<Integer> comparator = (a, b) -> (nums[a] != nums[b]) ? Integer.compare(nums[a], nums[b])
                        : Integer.compare(a, b);
                left = new TreeSet<>(comparator.reversed());
                right = new TreeSet<>(comparator);
            }

            private void add(int idx, int num) {
                if (left.isEmpty() || num <= nums[left.first()]) {
                    left.add(idx);
                } else {
                    right.add(idx);
                }

                balance();
            }

            private void balance() {
                if (left.size() > right.size() + 1) {
                    right.add(left.pollFirst());
                } else if (right.size() > left.size()) {
                    left.add(right.pollFirst());
                }
            }

            private void remove(int idx) {
                if (left.contains(idx)) {
                    left.remove(idx);
                } else {
                    right.remove(idx);
                }

                balance();
            }

            private double getMedian() {
                if (left.size() == right.size()) {
                    return ((double) nums[left.first()] + nums[right.first()]) / 2.0;
                } else {
                    return (double) nums[left.first()];
                }
            }
        }

        public double[] medianSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            double[] medians = new double[n - k + 1];
            MedianFinder medianFinder = new MedianFinder(nums);

            int windowStart = 0, windowEnd = 0;

            while (windowEnd < k) {
                medianFinder.add(windowEnd, nums[windowEnd]);
                windowEnd++;
            }

            while (windowEnd < n) {
                medians[windowEnd - k] = medianFinder.getMedian();

                //remove windowStart to maintain k size
                medianFinder.remove(windowStart);
                windowStart++;

                medianFinder.add(windowEnd, nums[windowEnd]);
                windowEnd++;

            }
            medians[windowEnd - k] = medianFinder.getMedian();

            return medians;
        }
    }
}
