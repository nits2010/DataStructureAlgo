package DataStructureAlgo.Java.GeeksForGeeks2025.ProblemSet.Arrays.SubArrays.LongestSubArraySumK;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/23/2024
 * Question Title: Longest sub-array having sum k
 * Link: https://www.geeksforgeeks.org/longest-sub-array-sum-k/
 * Description: Given an array arr[] of size n containing integers. The problem is to find the length of the longest sub-array having sum equal to the given value k.
 * <p>
 * Examples:
 * <p>
 * Input: arr[] = { 10, 5, 2, 7, 1, 9 }, k = 15
 * Output: 4
 * Explanation: The sub-array is {5, 2, 7, 1}.
 * <p>
 * <p>
 * Input: arr[] = {-5, 8, -14, 2, 4, 12}, k = -5
 * Output: 5
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.subarrays.LongestSubArraySumK}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestSubArrayHavingSumK {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{10, 5, 2, 7, 1, 9}, 15, new int[]{4, 1, 4}));
        tests.add(test(new int[]{-5, 8, -14, 2, 4, 12}, -5, new int[]{5, 0, 4}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int[] expected) {
        CommonMethods.printTest(new String[]{"Nums", "k", "Expected"}, true, nums, k, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.lenOfLongSubArr(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution {

        public int[] lenOfLongSubArr(int[] arr, int k) {
            int maxLength = 0;
            Map<Integer, Integer> map = new HashMap<>(arr.length); //map to store <sum, index>
            int prefixSum = 0;
            int start = 0, end = 0;

            for (int i = 0; i < arr.length; i++) {
                prefixSum += arr[i];

                //if this is equal to the K
                if (prefixSum == k) {
                    maxLength = i + 1; //then this will be the max length
                    end = i;
                }

                //have we seen a sum - k before, then it will make a subarray
                if (map.containsKey(prefixSum - k)) {
                    int index = map.get(prefixSum - k);
                    if (i - index > maxLength) {
                        maxLength = i - index;
                        start = index + 1;
                        end = i;
                    }
                }

                map.put(prefixSum, i);
            }

            return new int[]{maxLength, start, end};
        }
    }
}
