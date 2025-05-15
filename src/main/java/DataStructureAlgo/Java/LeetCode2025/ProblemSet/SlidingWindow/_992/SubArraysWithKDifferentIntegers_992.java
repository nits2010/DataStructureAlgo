package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._992;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/13/2025
 * Question Title: 992. Subarrays with K Different Integers
 * Link: https://leetcode.com/problems/subarrays-with-k-different-integers/description
 * Description: Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * <p>
 * A good array is an array where the number of different integers in that array is exactly k.
 * <p>
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340.LongestSubstringWithAtMostKDistinctCharacters_340}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @HashTable
 * @SlidingWindow
 * @Counting <p><p>
 * Company Tags
 * -----
 * @Alibaba
 * @Amazon
 * @GoldmanSachs
 * @Google
 * @Uber <p>
 * -----
 * @Editorial https://leetcode.com/problems/subarrays-with-k-different-integers/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SubArraysWithKDifferentIntegers_992 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 1, 2, 3}, 2, 7));
        tests.add(test(new int[]{1, 2, 1, 3, 4}, 3, 3));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_SlidingWindow_2Pass().subarraysWithKDistinct(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow_2Pass", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_SlidingWindow_1Pass().subarraysWithKDistinct(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SlidingWindow_1Pass", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }
    //This is similar to the problem 340. Longest Substring with At Most K Distinct Characters
    // in that problem we need to find the length of the substring with "at most k" distinct characters

    //here we need to find the count of subarray with k distinct integer.
    // Means each subarray has to have k distinct integer. Any sub-array having less than k distinct character should not be part of the result
    // Now if we calculate the count of sub-array "at most k" distinct characters then it will include all the subarray which has 1, 2, ... k distinct character
    // and if we also calculate the count of sub-array "at most k-1" distinct characters then it will include all the subarray which has 1, 2, ... k-1 distinct character
    // subtracting both will result only k distinct character. { (1+2+ ... + k-1 + k) - (1+2, ... +k-1) = k}

    static class Solution_SlidingWindow_2Pass {

        public int subarraysWithKDistinct(int[] nums, int k) {
            return slidingWindowAtMaxK(nums, k) - slidingWindowAtMaxK(nums, k - 1);
        }

        private int slidingWindowAtMaxK(int[] nums, int k) {
            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;

            int start = 0, end = 0;

            while (end < n) {

                //expand the window
                map.merge(nums[end], 1, Integer::sum);

                //shrink the window
                while (map.size() > k) {
                    if (map.merge(nums[start], -1, Integer::sum) == 0)
                        map.remove(nums[start]);
                    start++;
                }

                //now a window would have either < k distinct integer or = k distinct integer
                count += end - start + 1;

                end++;
            }

            return count;
        }
    }


    // We'll apply the same sliding window strategy. The limitation with the previous approach was that
    // we had to compute the number of subarrays with "at most K" distinct integers.
    // Instead, we can directly count subarrays with exactly K distinct integers using a modified sliding window.

    // The idea is to expand the window by moving the right pointer and decreasing K each time a new distinct integer is added.
    // Once K reaches zero, it means the current window has exactly K distinct integers.
    // At this point, we can start counting valid subarrays ending at the current position.

    // Simultaneously, we begin shrinking the window from the left to look for more potential subarrays within the same window.
    // However, if we add another distinct element, K becomes negative (K < 0), which means the window now contains too many unique integers.
    // To fix this, we move the left pointer forward, removing elements and increasing K as needed until we return to exactly K distinct elements in the window.

    // A special case arises when the element at the left is not a duplicate. In that case, removing it truly reduces the distinct count,
    // so we increment K to rebalance. But if the element is a duplicate (i.e., its frequency in the window is more than 1),
    // we can continue shrinking the window without affecting the distinct count (K remains 0).

    // This works because all duplicates still contribute to subarrays with exactly K distinct integers,
    // and by counting them as we shrink the window, we ensure that all valid subarrays are included.


    static class Solution_SlidingWindow_1Pass {

        public int subarraysWithKDistinct(int[] nums, int k) {

            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            int start = 0, end = 0;
            int prefixCount = 0;

            while (end < n) {

                if (map.merge(nums[end], 1, Integer::sum) == 1)
                    k--;

                if (k < 0) {

                    if (map.merge(nums[start], -1, Integer::sum) == 0)
                        map.remove(nums[start]);
                    start++;
                    k++;
                    prefixCount = 0;
                }

                if (k == 0) {

                    while (map.get(nums[start]) > 1) {
                        prefixCount++;
                        if (map.merge(nums[start], -1, Integer::sum) == 0)
                            map.remove(nums[start]);
                        start++;
                    }

                    count += prefixCount + 1; // the +1 is for the subarray with exactly k distinct integers
                }
                end++;
            }
            return count;
        }
    }
}
