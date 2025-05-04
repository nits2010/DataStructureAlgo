package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1438;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/4/2025
 * Question Title: 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Link: https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description
 * Description: Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 * <p>
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 * <p>
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 0 <= limit <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.maximum._239.SlidingWindowMaximum_239}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Queue
 * @SlidingWindow
 * @Heap(PriorityQueue)
 * @OrderedSet
 * @MonotonicQueue <p><p>
 * Company Tags
 * -----
 * @Uber
 * @Amazon
 * @Google <p>
 * -----
 * @Editorial https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/editorial <p><p>
 * -----
 * @OptimalSolution {@link SolutionDeque}
 */

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit_1438 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{8, 2, 4, 7}, 4, 2));
        tests.add(test(new int[]{10, 1, 2, 4, 7, 2}, 5, 4));
        tests.add(test(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0, 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int limit, int expected) {
        CommonMethods.printTest(new String[]{"nums", "limit", "Expected"}, true, nums, limit, expected);

        int output = 0;
        boolean pass, finalPass = true;

        SolutionPriorityQueue solutionPriorityQueue = new SolutionPriorityQueue();
        SolutionTreeMap solutionTreeMap = new SolutionTreeMap();
        SolutionDeque solutionDeque = new SolutionDeque();

        output = solutionPriorityQueue.longestSubarray(nums, limit);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PriorityQueue", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionTreeMap.longestSubarray(nums, limit);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"TreeMap", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionDeque.longestSubarray(nums, limit);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Deque", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //to find the absolute difference <= limit, we need to keep track of minimum value and maximum value of the window.
    // we can keep two pointer (variable size) sliding window.

    //when an elements enter a window, we find min and max with respect to it. and if absolute diff is <= limit, we can extend otherwise shrink
    //when the elements leave the window, we need to adjust our min and max.

    //adjusting min and max is challenging here.

    //1. we can keep two heaps for maintaining min and max and get O(1) time, and when an element leaves the window, we need to remove from heaps as well. O(log n)
    //2. we can use a tree map, first element gives max and while last gives min, and when an element leaves the window, we need to remove from treeMap as well. O(log n)
    //3. we can use two dequeue as well for both min and max and apply the same logic as above.

    //PriorityQueue : O(n*log n) / O(n)
    static class SolutionPriorityQueue {
        public int longestSubarray(int[] nums, int limit) {

            int n = nums.length;

            //keep index,value and maintain order based on value
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

            int windowStart = 0, windowEnd = 0;
            int maxLen = 0;

            while (windowEnd < n) {

                //extend the window
                minHeap.offer(new int[]{windowEnd, nums[windowEnd]});
                maxHeap.offer(new int[]{windowEnd, nums[windowEnd]});

                //shirnk the window, if max - min > limit
                while (maxHeap.peek()[1] - minHeap.peek()[1] > limit) {

                    // now we need to know which is the least index from both heaps will go out of a window
                    // to make sure heap follow the limit constraints
                    //since both heaps contain the same number and index, however, it would be possible
                    // then one would be at top of either of the heaps.

                    //since these min / max combination disagree to our constraints, we need to eliminate either or both of them to follow it
                    int minIndex = minHeap.peek()[0];
                    int maxIndex = maxHeap.peek()[0];

                    //to eliminate one/or both, we will take the minimum of them
                    windowStart = Math.min(minIndex, maxIndex) + 1;

                    //now all the element which is out of [windowStart,windowEnd] subarray, needs to be removed
                    // we will follow lazy remove to avoid O(n) time and keep O(log n), if the required element which is to be removed at peek, we will remove

                    while (!minHeap.isEmpty() && minHeap.peek()[0] < windowStart) {
                        minHeap.poll();
                    }

                    while (!maxHeap.isEmpty() && maxHeap.peek()[0] < windowStart) {
                        maxHeap.poll();
                    }
                }

                maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
                windowEnd++;
            }

            return maxLen;

        }
    }

    //TreeMap: O(n * log n) / O(n)
    // add element: O(log n)
    // get first and last : O(1)
    // remove element O( log n)
    // erase the window; O(n * log n)

    static class SolutionTreeMap {
        public int longestSubarray(int[] nums, int limit) {

            int n = nums.length;
            int windowStart = 0, windowEnd = 0;
            int maxLen = 0;

            //keeping them as frequency, we can start removing from windowStart
            //assume that all elements are unique, in such case, as soon as windowStart element goes out, that element freq=1 and get 0 and get removed
            TreeMap<Integer, Integer> treeMap = new TreeMap<>(); //contains value vs frequency

            while (windowEnd < n) {

                //extend the window, we count frequency, since elements could be duplicate.

                treeMap.merge(nums[windowEnd], 1, Integer::sum);

                while (!treeMap.isEmpty() && treeMap.lastKey() - treeMap.firstKey() > limit) {
                    int num = nums[windowStart];

                    if (treeMap.merge(num, -1, Integer::sum) == 0)
                        treeMap.remove(num);

                    windowStart++;
                }

                maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
                windowEnd++;
            }

            return maxLen;

        }
    }

    //Using Monotonic Dequeue : O(n) / O(n)
    // adding element : O(1) as at max 1 time each element will be added and removed while extending the window
    // removing element / shrinking : O(1) as at max 1 time each element will be removed while shrinking the window
    // erase entire window : O(n * 1) = O(n)
    // entire array run : O(n)

    static class SolutionDeque {
        public int longestSubarray(int[] nums, int limit) {

            int n = nums.length;
            int windowStart = 0, windowEnd = 0;
            int maxLen = 0;

            //keep the index of element in monotonic nature
            //minDequeue will keep an element at the first minimum
            //maxDequeue will keep an element at the first maximum
            Deque<Integer> minDeque = new ArrayDeque<>();
            Deque<Integer> maxDeque = new ArrayDeque<>();

            while (windowEnd < n) {

                //extend the window

                //order minDeque
                while (!minDeque.isEmpty() && nums[minDeque.peekLast()] > nums[windowEnd])
                    minDeque.pollLast();

                minDeque.offerLast(windowEnd);

                //order maxDeque
                while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] < nums[windowEnd])
                    maxDeque.pollLast();

                maxDeque.offerLast(windowEnd);

                //shrink the window
                while (windowStart < windowEnd && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {

                    if (maxDeque.peekFirst() == windowStart)
                        maxDeque.pollFirst();

                    if (minDeque.peekFirst() == windowStart)
                        minDeque.pollFirst();

                    windowStart++;

                }

                maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
                windowEnd++;
            }

            return maxLen;

        }
    }
}
