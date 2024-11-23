package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._560;

import DataStructureAlgo.Java.GeeksForGeeks2025.ProblemSet.Arrays.SubArrays.LongestSubArraySumK.LongestSubArrayHavingSumK;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/23/2024
 * Question Title: 560. Subarray Sum Equals K
 * Link: https://leetcode.com/problems/subarray-sum-equals-k/description/
 * Description: Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * <p>
 * Input : arr[] = {10, 2, -2, -20, 10}, k = -10
 * Output : 3
 * Explanation: arr[0…3], arr[1…4], arr[3..4] have sum -10.
 * <p>
 * <p>
 * Input : arr[] = {9, 4, 20, 3, 10, 5}, k = 33
 * Output : 2
 * Explanation: arr[0…2], arr[2…4] have sum 33.
 * <p>
 * <p>
 * Input : arr[] = {1, 3, 5}, k = 2
 * Output : 0
 * Explanation: No subarrays with 0 sum
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link LongestSubArrayHavingSumK}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Amazon
 * @Apple
 * @Bloomberg
 * @eBay
 * @Expedia
 * @Facebook
 * @GoldmanSachs
 * @Google
 * @Indeed
 * @LinkedIn
 * @Microsoft
 * @Oracle
 * @Quora
 * @Snapchat
 * @Twilio
 * @Uber
 * @Wish
 * @Yandex <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SubarraySumEqualsK_560 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 1, 1}, 2, 2));
        tests.add(test(new int[]{1, 2, 3}, 3, 2));
        tests.add(test(new int[]{1, 3, 5}, 2, 0));
        tests.add(test(new int[]{10, 2, -2, -20, 10}, -10, 3));
        tests.add(test(new int[]{9, 4, 20, 3, 10, 5}, 33, 2));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "K", "Expected"}, true, nums, k, expected);

        int output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.subarraySum(nums, k);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution {
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>(); //map to store <sum, count>
            int prefixSum = 0;

            for (int num : nums) {
                prefixSum += num;

                if (prefixSum == k) {
                    count++;
                }

                if (map.containsKey(prefixSum - k)) {
                    count += map.get(prefixSum - k);
                }

                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            }
            return count;
        }
    }
}
