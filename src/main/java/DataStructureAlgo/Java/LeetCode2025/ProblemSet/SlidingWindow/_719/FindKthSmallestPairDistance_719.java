package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._719;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/10/2025
 * Question Title: 719. Find K-th Smallest Pair Distance
 * Link: https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/
 * Description: The distance of a pair of integers a and b is defined as the absolute difference between a and b.
 * <p>
 * Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,1], k = 1
 * Output: 0
 * Explanation: Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Example 2:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 0
 * Example 3:
 * <p>
 * Input: nums = [1,6,1], k = 3
 * Output: 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 2 <= n <= 104
 * 0 <= nums[i] <= 106
 * 1 <= k <= n * (n - 1) / 2
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
 * @hard
 * @Array
 * @TwoPointers
 * @BinarySearch
 * @Sorting
 * @DynamicProgramming
 * @Heap(PriorityQueue) <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Google <p>
 * -----
 * @Editorial 1. https://leetcode.com/problems/find-k-th-smallest-pair-distance/editorial
 * 2. https://leetcode.com/problems/find-k-th-smallest-pair-distance/solutions/5632765/o-n-log-n-n-log-w-binary-search-sliding-window-java-c-python-go-rust-javascript<p><p>
 * -----
 * @OptimalSolution {@link Solution_SlidingWindow}
 */

public class FindKthSmallestPairDistance_719 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 3, 1}, 1, 0));
        tests.add(test(new int[]{1, 1, 1}, 2, 0));
        tests.add(test(new int[]{1, 6, 1}, 3, 5));
        tests.add(test(new int[]{10, 20, 30, 40, 50}, 6, 20));
        tests.add(test(new int[]{1, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900}, 50, 300));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Grid", "k", "Expected"}, true, grid, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_PriorityQueue solutionPriorityQueue = new Solution_PriorityQueue();
        Solution_BucketSort solutionBucketSort = new Solution_BucketSort();
        Solution_SlidingWindow solutionSlidingWindow = new Solution_SlidingWindow();

        output = solutionPriorityQueue.smallestDistancePair(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"PriorityQueue", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionBucketSort.smallestDistancePair(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BucketSort", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionSlidingWindow.smallestDistancePair(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SlidingWindow_BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //Heap - O(n^2 * log(k)) / O(k)
    static class Solution_PriorityQueue {
        public int smallestDistancePair(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k == 0)
                return 0;

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); //collections.reverseOrder());

            //calculate all the distance and push in the maxHeap
            //maintain k size max heap, the top element will be pushed out when filled
            //output would be the top element
            int n = nums.length;
            //O(n^2 * log(k))
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {

                    int distance = Math.abs(nums[i] - nums[j]);
                    maxHeap.offer(distance);

                    if (maxHeap.size() > k)
                        maxHeap.poll();
                }
            }
            return maxHeap.isEmpty() ? -1 : maxHeap.poll();
        }
    }


    //bucket-sort - O(n^2+M) / O(M)
    static class Solution_BucketSort {
        int max(int[] nums) {
            int max = nums[0];
            for (int n : nums)
                max = Math.max(max, n);

            return max;
        }

        public int smallestDistancePair(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k == 0)
                return 0;

            int minDis = 0; //both lowest elements
            int maxDis = max(nums); // consider smallest is 0 the max element would be the max distance.

            int[] buckets = new int[maxDis + 1]; // this will store how many count of each distance start from [0,maxDis]

            //populate buckets - O(n^2)
            int n = nums.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {

                    int distance = Math.abs(nums[i] - nums[j]);
                    buckets[distance]++;
                }
            }

            //find the kth distance - O(M)
            int count = 0;
            for (int i = 0; i < buckets.length; i++) {
                count += buckets[i];
                if (count >= k) {
                    return i;
                }
            }
            return -1;
        }
    }

    //Binary Search + Sliding Window [two-pointer] - O(nlogn + mlogm) / O(1)
    static class Solution_SlidingWindow {

        public int smallestDistancePair(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k == 0)
                return 0;

            Arrays.sort(nums); // O(n*logn)
            int n = nums.length;
            int min = 0, max = nums[n - 1] - nums[0];

            //binary search the distance
            //O(m*logm)
            while (min < max) {
                int mid = min + (max - min) / 2;

                int countPairs = countPairsWithDistance(nums, mid);

                if (countPairs < k)
                    min = mid + 1;
                else
                    max = mid;

            }
            return min;
        }

        //sliding window - two pointer
        //assume that we are extending or shirnking the window based on distance b/w left and right.
        // if we get a window, where the distance is equal, then those number of elements [left,right] will participate
        // in count
        // O(n)
        int countPairsWithDistance(int[] nums, int maxDistance) {

            int left = 0, right = 0;
            int count = 0;

            while (right < nums.length) {

                //shrink the window to keep distnace under window
                // Adjust the left pointer to maintain the window with distances <=maxDistance
                while (left < right && (nums[right] - nums[left]) > maxDistance)
                    left++; // so that the distance can reduce further

                count += right - left;

                right++;
            }
            return count;
        }
    }

}
