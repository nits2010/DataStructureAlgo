package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.SumProblems._2461;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: ${DATE}
 * Question Title: Maximum Sum Of Distinct Sub Arrays With Length K
 * Link: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k
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

public class MaximumSumOfDistinctSubArraysWithLengthK_2461 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[] { 1, 5, 4, 2, 9, 9, 9 }, 3, 15));
        tests.add(test(new int[] { 4, 4, 4 }, 3, 0));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, long expected) {
        // add print here
        CommonMethods.printTest(new String[] { "Nums", "k", "Expected" }, true, nums, k, expected);

        long output;
        boolean pass, finalPass = true;

        Solution_Map solutionMap = new Solution_Map();
        output = solutionMap.maximumSubarraySum(nums, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[] { "Map", "Pass" }, false, output, pass ? "PASS" : "FAIL");

        Solution solution = new Solution();
        output = solution.maximumSubarraySum(nums, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[] { "Array", "Pass" }, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * T/S: O(n)/O(4) = O(1)
     */
    static class Solution_Map {
        public long maximumSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> countMap = new HashMap<>(); // holds the element vs counts;
            int n = nums.length;
            int left = 0, right = 0;
            long currentSum = 0;
            long maxSum = 0;

            while (right < n) {

                // expand the window
                while (right < n && right - left < k) {
                    currentSum += nums[right];
                    countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
                    right++;
                }

                // evaluate window
                if (countMap.size() == k) {
                    maxSum = Math.max(maxSum, currentSum);
                }

                // shirnk the window
                int ele = nums[left];
                int count = countMap.get(ele);

                if (count == 1) {
                    countMap.remove(ele);
                } else {
                    countMap.put(ele, count - 1);
                }
                currentSum -= ele;
                left++;

            }
            return maxSum;
        }
    }

    /**
     * T/S: O(n)/O(Max)
     */
    static class Solution {
        public long maximumSubarraySum(int[] nums, int k) {
            int max = 0;
            for (int i : nums)
                max = Math.max(max, i);

            int[] countMap = new int[max + 1];
            int n = nums.length;
            int left = 0, right = 0;
            long currentSum = 0;
            long maxSum = 0;
            int uniqueCount = 0;
            int ele = -1;

            while (right < n) {

                // expand the window
                while (right < n && right - left < k) {
                    ele = nums[right];
                    currentSum += ele;

                    if (countMap[ele] == 0)
                        uniqueCount++;

                    countMap[nums[right]]++;
                    right++;
                }

                // evaluate window
                if (uniqueCount == k) {
                    maxSum = Math.max(maxSum, currentSum);
                }

                // shirnk the window
                ele = nums[left];
                int count = countMap[ele];

                if (count == 1) {
                    uniqueCount--;
                }

                countMap[ele]--;
                currentSum -= ele;
                left++;

            }
            return maxSum;
        }
    }
}
