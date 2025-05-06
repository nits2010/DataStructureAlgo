package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._219;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/6/2025
 * Question Title: 219. Contains Duplicate II
 * Link: https://leetcode.com/problems/contains-duplicate-ii/description/
 * Description: Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
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
 * @easy
 * @Array
 * @HashTable
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Airbnb
 * @Amazon
 * @Google
 * @Microsoft
 * @PalantirTechnologies
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ContainsDuplicateII_219 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 1}, 3, true));
        tests.add(test(new int[]{1, 0, 1, 1}, 1, true));
        tests.add(test(new int[]{1, 2, 3, 1, 2, 3}, 2, false));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        boolean output;
        boolean pass, finalPass = true;

        output = new Solution().containsNearbyDuplicate(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            int n = nums.length;
            Set<Integer> set = new HashSet<>();
            int start = 0, end = 0;
            while (end < n) {

                // kick out of the window abs(i - j) <= k.
                if (end - start > k) {
                    set.remove(nums[start]);
                    start++;
                }

                //did we see the same number within the window ?
                if (set.contains(nums[end]))
                    return true;

                set.add(nums[end]);
                end++;

            }
            return false;
        }
    }
}
