package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap._1825;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/3/2025
 * Question Title: 1825. Finding MK Average
 * Link: https://leetcode.com/problems/finding-mk-average/description/
 * Description: You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.
 * <p>
 * The MKAverage can be calculated using these steps:
 * <p>
 * If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
 * Remove the smallest k elements and the largest k elements from the container.
 * Calculate the average value for the rest of the elements rounded down to the nearest integer.
 * Implement the MKAverage class:
 * <p>
 * MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
 * void addElement(int num) Inserts a new element num into the stream.
 * int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
 * [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
 * Output
 * [null, null, null, -1, null, 3, null, null, null, 5]
 * <p>
 * Explanation
 * MKAverage obj = new MKAverage(3, 1);
 * obj.addElement(3);        // current elements are [3]
 * obj.addElement(1);        // current elements are [3,1]
 * obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
 * obj.addElement(10);       // current elements are [3,1,10]
 * obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
 * // After removing smallest and largest 1 element the container will be [3].
 * // The average of [3] equals 3/1 = 3, return 3
 * obj.addElement(5);        // current elements are [3,1,10,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5,5]
 * obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
 * // After removing smallest and largest 1 element the container will be [5].
 * // The average of [5] equals 5/1 = 5, return 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= m <= 105
 * 1 <= k*2 < m
 * 1 <= num <= 105
 * At most 105 calls will be made to addElement and calculateMKAverage.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._480.SlidingWindowMedian_480}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Design
 * @Queue
 * @Heap(PriorityQueue)
 * @DataStream
 * @OrderedSet <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Google
 * @Oracle
 * @Snapchat <p>
 * -----
 * @Editorial https://leetcode.ca/2020-11-28-1825-Finding-MK-Average/ <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindingMKAverage_1825 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new String[]{"MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"},
                new Integer[][]{{3, 1}, {3}, {1}, {}, {10}, {}, {5}, {5}, {5}, {}},
                new Integer[]{null, null, null, -1, null, 3, null, null, null, 5}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String[] operation, Integer[][] nums, Integer[] expected) {
        CommonMethods.printTest(new String[]{"operation", "nums", "Expected"}, true, operation, nums, expected);

        Integer[] output = null;
        boolean pass, finalPass = true;
        MKAverage_UsingTreeMap obj = new MKAverage_UsingTreeMap(nums[0][0], nums[0][1]);
        output = applyStream(operation, nums, obj);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    private static Integer[] applyStream(String[] operations, Integer[][] nums, MKAverage_UsingTreeMap obj) {
        Integer[] output = new Integer[operations.length];
        int i = 0;
        output[i] = null;
        i++;
        for (String operation : operations) {

            if (operation.equals("addElement")) {
                obj.addElement(nums[i][0]);
                output[i++] = null;
            } else if (operation.equals("calculateMKAverage")) {
                output[i++] = obj.calculateMKAverage();
            }

        }
        return output;

    }

    static class MKAverage_UsingTreeMap {

        private final Queue<Integer> queue;
        private final TreeMap<Integer, Integer> left; // contains left k elements in sorted order
        int leftSize = 0;
        private final TreeMap<Integer, Integer> mid; // contains mid elements ; m - 2k
        private final TreeMap<Integer, Integer> right; // contains right k elements in sorted order
        int rightSize = 0;
        long sum = 0;

        final int m, k;

        public MKAverage_UsingTreeMap(int m, int k) {
            this.m = m;
            this.k = k;

            queue = new LinkedList<Integer>();
            left = new TreeMap<>();
            mid = new TreeMap<>();
            right = new TreeMap<>();
        }

        public void addElement(int num) {

            queue.offer(num);

            if (left.isEmpty() || num <= left.lastKey()) {
                add(left, num);
                leftSize++;
            } else if (right.isEmpty() || num >= right.firstKey()) {
                add(right, num);
                rightSize++;
            } else {
                add(mid, num);
                sum += num;
            }

            if (queue.size() > m) {

                int element = queue.poll();

                if (remove(left, element)) {
                    leftSize--;
                } else if (remove(right, element)) {
                    rightSize--;
                } else if (remove(mid, element)) {
                    sum -= element;
                }
            }

            //now if our left or right has a more the k element, then we need to make sure that they keep only k elements and all the remaining elements push to mid
            //this is because when we calculate average, we can just take sum / m - 2k;
            leftSize = balanceToK(left, leftSize, false);
            rightSize = balanceToK(right, rightSize, true);

            //similarly, if leftSize or rightSize is < k then we have to remove it from mid and push to either of the side
            //balances leftSize
            leftSize = updateToK(left, leftSize, true);
            rightSize = updateToK(right, rightSize, false);

        }

        private void add(TreeMap<Integer, Integer> map, int num) {
            map.merge(num, 1, Integer::sum);
        }

        private boolean remove(TreeMap<Integer, Integer> map, int element) {
            if (map.containsKey(element)) {
                if (map.merge(element, -1, Integer::sum) == 0) {
                    map.remove(element);
                }
                return true;
            }
            return false;
        }

        private int balanceToK(TreeMap<Integer, Integer> map, int size, boolean isFirst) {
            while (size > k) {

                int element = isFirst ? map.firstKey() : map.lastKey();

                if (map.merge(element, -1, Integer::sum) == 0) {
                    map.remove(element);
                }

                //add this element in mid
                mid.merge(element, 1, Integer::sum);
                sum += element;

                size--;
            }
            return size;
        }

        private int updateToK(TreeMap<Integer, Integer> map, int size, boolean isFirst) {
            while (size < k && !mid.isEmpty()) {
                int element = isFirst ? mid.firstKey() : mid.lastKey();
                sum -= element;
                if (mid.merge(element, -1, Integer::sum) == 0) {
                    mid.remove(element);
                }

                map.merge(element, 1, Integer::sum);
                size++;
            }
            return size;
        }

        public int calculateMKAverage() {

            return (int) (queue.size() < m ? -1 : (sum / (queue.size() - 2 * k)));

        }
    }

}
