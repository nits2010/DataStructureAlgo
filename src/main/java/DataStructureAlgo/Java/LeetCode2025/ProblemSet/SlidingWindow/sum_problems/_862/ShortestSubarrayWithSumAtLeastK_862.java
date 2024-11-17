package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.sum_problems._862;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.maximum._239.SlidingWindowMaximum_239;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/17/2024
 * Question Category: 862. Shortest Subarray with Sum at Least K
 * Description: https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/?envType=daily-question&envId=2024-11-17
 * https://www.geeksforgeeks.org/smallest-subarray-from-a-given-array-with-sum-greater-than-or-equal-to-k/
 * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1], k = 1
 * Output: 1
 * Example 2:
 * <p>
 * Input: nums = [1,2], k = 4
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link SlidingWindowMaximum_239.SolutionUsingDQ}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @BinarySearch
 * @Queue
 * @SlidingWindow
 * @Heap(PriorityQueue)
 * @PrefixSum
 * @MonotonicQueue <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class ShortestSubarrayWithSumAtLeastK_862 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
//        tests.add(test(new int[]{1}, 1, 1));
        tests.add(test(new int[]{1, 2}, 4, -1));
        tests.add(test(new int[]{2, -1, 2}, 3, 3));
        tests.add(test(new int[]{2, 1, 1, -4, 3, 1, -1, 2}, 5, 4));
        tests.add(test(new int[]{2, 1, 1, -4, 3, 1, -1, 2, 6, 5, 3, -2}, 5, 1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "k", "Expected"}, true, nums, k, expected);

        int output;
        boolean pass, finalPass = true;

        //add logic here
        Solution_UsingPQ solutionUsingPQ = new Solution_UsingPQ();
        output = solutionUsingPQ.shortestSubarray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingPQ", "Pass"}, false, output, pass ? "Pass" : "Fail");

        Solution_UsingBinarySearch solutionUsingBinarySearch = new Solution_UsingBinarySearch();
        output = solutionUsingBinarySearch.shortestSubarray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingBinarySearch", "Pass"}, false, output, pass ? "Pass" : "Fail");


        Solution_UsingDeque solutionUsingDeque = new Solution_UsingDeque();
        output = solutionUsingDeque.shortestSubarray(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingDeque", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution_UsingPQ {
        public int shortestSubarray(int[] nums, int k) {

            // PQ holding [prefixSum, endingIndex] ordered based on prefixSum or ending
            // index (min-Heap), min-heap because we need to maintain our currentSum >= k, if we use max-heap, then we may get currentSum < k post subtracting
            PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

            long currentSum = 0;
            int minSize = Integer.MAX_VALUE;
            int n = nums.length;
            int[] subArrayIndex = {-1, -1};

            int right = 0;
            while (right < n) {

                // expand window
                currentSum += nums[right];

                if (currentSum >= k) {
                    //evaluate the window
                    minSize = Math.min(minSize, right + 1);

                    //cache index
                    subArrayIndex[0] = 0;
                    subArrayIndex[1] = right;
                }

                // shrink window, such that we reduce a minimum prefix sum, keeping currentSum - prefixSum >= k
                while (!pq.isEmpty() && currentSum - pq.peek()[0] >= k) {
                    long[] item = pq.poll();
                    int newSize = right - (int) item[1];

                    if (newSize < minSize) {
                        minSize = newSize;

                        //cache index
                        subArrayIndex[0] = (int) item[1] + 1;
                        subArrayIndex[1] = right;
                    }
                }

                pq.offer(new long[]{currentSum, right});

                right++;

            }

            return minSize == Integer.MAX_VALUE ? -1 : minSize;
        }
    }

    static class Solution_UsingBinarySearch {
        public int shortestSubarray(int[] nums, int k) {

            //[prefixSum,index], a stack like DS to maintain monotonic increasing order of elements based on prefixSum
            List<long[]> monotonicIncreasing = new ArrayList<>();
            monotonicIncreasing.add(new long[]{0L, -1});


            long currentSum = 0;
            int minSize = Integer.MAX_VALUE;
            int n = nums.length;

            int right = 0;
            while (right < n) {

                currentSum += nums[right];

                if (currentSum >= k) {
                    minSize = Math.min(minSize, right + 1);
                }

                //shrink
                while (!monotonicIncreasing.isEmpty() && currentSum <= monotonicIncreasing.get(monotonicIncreasing.size() - 1)[0]) {

                    monotonicIncreasing.remove(monotonicIncreasing.size() - 1);
                }

                monotonicIncreasing.add(new long[]{currentSum, right});


                //find the candidate for currentSum - k
                int index = binarySearch(monotonicIncreasing, currentSum - k);

                if (index != -1) {
                    int left = (int) monotonicIncreasing.get(index)[1];
                    if (left >= 0)
                        minSize = Math.min(minSize, right - left);
                }
                right++;
            }

            return minSize == Integer.MAX_VALUE ? -1 : minSize;
        }

        int binarySearch(List<long[]> monotonicIncreasing, long target) {

            int l = 0, r = monotonicIncreasing.size() - 1;


            while (l <= r) {

                int mid = l + (r - l) / 2;

                long prefixSum = monotonicIncreasing.get(mid)[0];

                if (prefixSum > target) {

                    r = mid - 1;
                } else {
                    l = mid + 1;
                }

            }
            return r;
        }
    }

    /**
     * {@link SlidingWindowMaximum_239.SolutionUsingDQ}
     */
    static class Solution_UsingDeque {

        public int shortestSubarray(int[] nums, int targetSum) {
            int n = nums.length;

            // Size is n+1 to handle subarrays starting from index 0
            long[] prefixSums = new long[n + 1];

            // Calculate prefix sums
            for (int i = 1; i <= n; i++) {
                prefixSums[i] = prefixSums[i - 1] + nums[i - 1];
            }

            Deque<Integer> dq = new ArrayDeque<>();

            int shortestSubarrayLength = Integer.MAX_VALUE;

            for (int i = 0; i <= n; i++) {

                // Remove candidates from front of deque where subarray sum meets target
                while (!dq.isEmpty() &&
                        prefixSums[i] - prefixSums[dq.peekFirst()] >=
                                targetSum
                ) {
                    // Update shortest subarray length
                    shortestSubarrayLength = Math.min(shortestSubarrayLength, i - dq.pollFirst());
                }

                // Maintain monotonicity by removing indices with larger prefix sums
                while (
                        !dq.isEmpty() &&
                                prefixSums[i] <= prefixSums[dq.peekLast()]
                ) {
                    dq.pollLast();
                }

                // Add current index to candidates
                dq.offerLast(i);
            }

            // Return -1 if no valid subarray found
            return shortestSubarrayLength == Integer.MAX_VALUE
                    ? -1
                    : shortestSubarrayLength;
        }
    }
}
