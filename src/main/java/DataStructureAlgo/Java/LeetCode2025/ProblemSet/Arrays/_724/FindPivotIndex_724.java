package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._724;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/19/2025
 * Question Title: 724. Find Pivot Index
 * Link: https://leetcode.com/problems/find-pivot-index/description/
 * Description: Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * Example 3:
 *
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 *
 *
 * Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.FindPivotIndex}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 * @Array
 * @PrefixSum
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * @Facebook
 * @GoldmanSachs
 * @Apple
 * @Amazon
 * @Baidu
 * @Bloomberg
 * @Coupang
 * @Expedia
 *
 * @GoDaddy
 * @Indeed
 * @Microsoft
 * @Radius
 * @Salesforce
 * @Tesla
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindPivotIndex_724 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 7, 3, 6, 5, 6}, 3));
        tests.add(test(new int[]{1, 2, 3}, -1));
        tests.add(test(new int[]{2, 1, -1}, 0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_PrefixSuffixSum().pivotIndex(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"PrefixSuffixSum", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_PrefixTotalSum().pivotIndex(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"PrefixTotalSum", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * Algorithm:
     * 1. Scan the array and build the prefix and suffix sum such as
     * prefixSum[i] = prefixSum[i-1] + nums[i-1]
     * suffixSum[i] = suffixSum[i+1] + nums[i+1]
     * <p>
     * 2. After check where both are equal from the left side
     * <p>
     * O(n)/O(n)
     */
    static class Solution_PrefixSuffixSum {
        public int pivotIndex(int[] nums) {
            if (nums == null || nums.length == 0)
                return -1;
            int n = nums.length;
            int[] left = new int[n];
            int[] right = new int[n];
            left[0] = 0;
            right[n - 1] = 0;

            for (int i = 1, j = n - 2; i < n; i++, j--) {
                left[i] = left[i - 1] + nums[i - 1];
                right[j] = right[j + 1] + nums[j + 1];
            }

            for (int i = 0; i < n; i++) {
                if (left[i] == right[i])
                    return i;
            }

            return -1;

        }
    }


    /**
     * In the above algorithm, we scan and build the array. Essentially the suffix array we don't need as we need
     * leftmost index. Which means, we can compute the sum of all the elements on the left side of the current index
     * and reduce it from a total sum to get a sum of all the elements on the right side, including this element.
     * Reduce the element to get a right side sum only.
     * <p>
     * Algorithm:
     * 1. Compute the overall sum
     * 2. keep computing the sum of elements on the left side excluding current element
     * 3. compute the right side sum and check
     * <p>
     * O(n)/ O(1)
     */
    static class Solution_PrefixTotalSum {
        public int pivotIndex(int[] nums) {
            int leftSum = 0, rightSum = 0;
            int totalSum = 0;

            for(int n : nums)
                totalSum += n;

            // [1,7,3,6,5,6]
            // totalSum = 28
            // index = 3, leftSum = 1+7+3+6 = 17, rightSum = 28-17 + 6 = 17
            // right
            for(int i = 0; i<nums.length; i++){
                leftSum += nums[i];
                rightSum = totalSum - leftSum + nums[i]; // right sum including this ith element

                if(leftSum == rightSum)
                    return i;

            }
            return -1;
        }
    }
}
