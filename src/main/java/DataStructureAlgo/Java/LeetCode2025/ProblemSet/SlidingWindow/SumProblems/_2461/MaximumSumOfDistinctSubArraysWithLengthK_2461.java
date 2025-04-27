package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.SumProblems._2461;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: ${DATE}
 * Title: 2461. Maximum Sum of Distinct Subarrays With Length K
 * Links: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/description/?envType=daily-question&envId=2024-11-19
 * Description: You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:
 * <p>
 * The length of the subarray is k, and
 * All the elements of the subarray are distinct.
 * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,5,4,2,9,9,9], k = 3
 * Output: 15
 * Explanation: The subarrays of nums with length 3 are:
 * - [1,5,4] which meets the requirements and has a sum of 10.
 * - [5,4,2] which meets the requirements and has a sum of 11.
 * - [4,2,9] which meets the requirements and has a sum of 15.
 * - [2,9,9] which does not meet the requirements because the element 9 is repeated.
 * - [9,9,9] which does not meet the requirements because the element 9 is repeated.
 * We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
 * Example 2:
 * <p>
 * Input: nums = [4,4,4], k = 3
 * Output: 0
 * Explanation: The subarrays of nums with length 3 are:
 * - [4,4,4] which does not meet the requirements because the element 4 is repeated.
 * We return 0 because no subarrays meet the conditions.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
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
 * @medium
 * @Array
 * @HashTable
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial -----
 * <p><p>
 * @OptimalSoltuion
 */

public class MaximumSumOfDistinctSubArraysWithLengthK_2461 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 5, 4, 2, 9, 9, 9}, 3, 15));
        tests.add(test(new int[]{4, 4, 4}, 3, 0));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, long expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "k", "Expected"}, true, nums, k, expected);

        long output;
        boolean pass, finalPass = true;

        Solution_Map solutionMap = new Solution_Map();
        output = solutionMap.maximumSubarraySum(nums, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Map", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution solution = new Solution();
        output = solution.maximumSubarraySum(nums, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Array", "Pass"}, false, output, pass ? "PASS" : "FAIL");


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

                //expand the window
                while (right < n && right - left < k) {
                    currentSum += nums[right];
                    countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
                    right++;
                }

                //evaluate window
                if (countMap.size() == k) {
                    maxSum = Math.max(maxSum, currentSum);
                }

                //shirnk the window
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
            for (int i : nums) max = Math.max(max, i);

            int[] countMap = new int[max + 1];
            int n = nums.length;
            int left = 0, right = 0;
            long currentSum = 0;
            long maxSum = 0;
            int uniqueCount = 0;
            int ele = -1;

            while (right < n) {

                //expand the window
                while (right < n && right - left < k) {
                    ele = nums[right];
                    currentSum += ele;

                    if (countMap[ele] == 0)
                        uniqueCount++;

                    countMap[nums[right]]++;
                    right++;
                }

                //evaluate window
                if (uniqueCount == k) {
                    maxSum = Math.max(maxSum, currentSum);
                }

                //shirnk the window
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
