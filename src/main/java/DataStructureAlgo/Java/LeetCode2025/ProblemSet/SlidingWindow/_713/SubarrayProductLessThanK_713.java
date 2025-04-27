package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._713;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/9/2025
 * Question Title: 713 Subarray Product Less Than K
 * Link: https://leetcode.com/problems/subarray-product-less-than-k/description/
 * Description: Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.SubarrayProductLessThanK}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch
 * @SlidingWindow
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @GoldmanSachs
 * @AkunaCapital
 * @Atlassian
 * @Coursera
 * @Expedia
 * @Google
 * @Yatra
 * @Visa <p>
 * -----
 * @Editorial https://leetcode.com/problems/subarray-product-less-than-k/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SubarrayProductLessThanK_713 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{10, 5, 2, 6}, 100, 8));
        tests.add(test(new int[]{1, 2, 3}, 0, 0));
        tests.add(test(new int[]{10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3}, 19, 18));
        tests.add(test(new int[]{1, 1, 1}, 1, 0));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Grid", "k", "Expected"}, true, grid, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.numSubarrayProductLessThanK(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * O(n)/ O(1)
     */
    static class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            // if(k <= 1)
            //     return 0;
            int left = 0, right = 0;
            int result = 0;
            int prod = 1;
            while (right < nums.length) {

                //expand the window
                prod = prod * nums[right];

                //shrink the window if invalid
                while (left <= right && prod >= k)
                    prod /= nums[left++];

                result += right - left + 1;
                right++;

            }
            return result;
        }
    }
}
