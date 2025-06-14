package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._327;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/14/2025
 * Question Title: 327. Count of Range Sum
 * Link: https://leetcode.com/problems/count-of-range-sum/description/
 * Description: Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.
 * <p>
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
 * Example 2:
 * <p>
 * Input: nums = [0], lower = 0, upper = 0
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * -105 <= lower <= upper <= 105
 * The answer is guaranteed to fit in a 32-bit integer.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._315.CountOfSmallerNumbersAfterSelf_315}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @BinarySearch
 * @DivideandConquer
 * @BinaryIndexedTree
 * @SegmentTree
 * @MergeSort
 * @OrderedSet <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Apple
 * @Facebook
 * @Google
 * @Microsoft
 * @Oracle
 * @Uber
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CountOfRangeSum_327 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{-2, 5, -1}, -2, 2, 3));
        tests.add(test(new int[]{0}, 0, 0, 1));
        tests.add(test(new int[]{-2147483647, 0, -2147483647, 2147483647}, -564, 3864, 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int lower, int upper, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "lower", "upper", "Expected"}, true, nums, lower, upper, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_MergeSort().countRangeSum(nums, lower, upper);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Naive approach : Since we need to find all such sub-array whose sum would lie in [lower,upper] range, what we can do is, consider all such subarray O(n^2) and sum each sub-array O(n) and check the range.
     * This leads to O(n^3) time complexity.
     * Brute Force :    We can improve a naive solution by doing the prefix sum to avoid summing elements b/w i,j as PrefixSum(i,j) = PrefixSum(j) - PrefixSum(i), this will help us to calculate the sum of sub-array in O(1) time.
     * This leads to O(n) {for prefix Sum}  + O(n^2) {for sub-array} => O(n^2)
     * <p>
     * Divide & Concur : Now with prefix sum, when we check the range, then it should be lower <= prefixSum(j) - prefixSum(i) <= upper. This means all such sum are in between lower and upper inclusive.
     * Since at any index 'i' we know the prefix sum ending at here is prefixSum(i), Hence we can isolate prefixSum(i) in above equation then we can do range check in O(n) time.
     * <p>
     * lower <= prefixSum(j) - prefixSum(i) <= upper { subtract prefixSum(j) }
     * lower - prefixSum(j) <= -prefixSum(i) <= upper - prefixSum(j) {now negate the equation since currently its -prefixSum(i)}
     * -lower + prefixSum(j) >= prefixSum(i) >= -upper + prefixSum(j)
     * =>          prefixSum(j) - upper <= prefixSum(i) <= prefixSum(j) - lower
     * Hence,      prefixSum(i) should be in the range of [prefixSum(j) - upper , prefixSum(j) - lower]
     * <p>
     * We need to find all, prefixSum(i) which lies in range [prefixSum(j) - upper , prefixSum(j) - lower], this equation needs a 'j' which will be j > i however, we need 2 'j' which follow above equations.
     * Hence j1 (aka k) and j2 (aka j), such that
     * prefixSum(i) <= prefixSum(j1) - lower
     * =>      prefixSum(i) - prefixSum(j1) <= -lower
     * =>      prefixSum(j1) - prefixSum(i) >= lower
     * <p>
     * and     prefixSum(i) >= prefixSum(j2) - upper
     * =>      prefixSum(j2) - prefixSum(1) <= upper
     * <p>
     * Once we have suck k and j we can count the number of subarray participated as count = j - k. However, if we keep
     * <p>
     * In order to above to work, we need to make sure that the prefixSum array is sorted at any moment of time we are calculating. Hence we will apply merge sort which will help us to divide the array and the merge will help us to
     * sort the array. Before sending it to merge, we will count such sub-arrays using above equations.
     */


    static class Solution_MergeSort {
        public int countRangeSum(int[] nums, int lower, int upper) {

            int length = nums.length;

            long[] prefixSum = new long[length + 1]; // keeping prefixSum[0] = 0


            for (int i = 0; i < length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }


            return mergeSort(prefixSum, lower, upper, 0, prefixSum.length - 1);
        }

        private int mergeSort(long[] ps, int lower, int upper, int start, int end) {
            //base condition
            if (start >= end)
                return 0;

            //divide the array
            int mid = (start + end) >>> 1;

            int leftCount = mergeSort(ps, lower, upper, start, mid);
            int rightCount = mergeSort(ps, lower, upper, mid + 1, end);

            //count how many of them we have from left and right
            int count = leftCount + rightCount;

            //count how many of subarray we can get by joining left and right subarray
            int i = start;
            int k = mid + 1, j = mid + 1;

            while (i <= mid) {

                // for the fix i, find the first k where prefixSum(j1) - prefixSum(i) >= lower
                while (k <= end && ps[k] - ps[i] < lower)
                    k++;

                //  for the fix i, find the first j where  prefixSum(j) - prefixSum(1) <= upper, since we don't know that prefixSum(j) == upper exists or not, we stop once we found < upper
                while (j <= end && ps[j] - ps[i] <= upper)
                    j++;

                count += j - k;

                i++;
            }

            //merge the both halves to keep ps sorted
            merge(ps, start, mid, end);

            return count;
        }

        private void merge(long[] ps, int start, int mid, int end) {

            long[] temp = new long[end - start + 1];
            int idx = 0;

            int i = start, j = mid + 1;
            while (i <= mid && j <= end) {

                if (ps[i] <= ps[j]) {
                    temp[idx++] = ps[i++];
                } else {
                    temp[idx++] = ps[j++];
                }
            }

            while (i <= mid) {
                temp[idx++] = ps[i++];
            }

            while (j <= end) {
                temp[idx++] = ps[j++];
            }

            //copy to original array
            i = start;
            idx = 0;
            while (idx < temp.length) {
                ps[i++] = temp[idx++];
            }

        }
    }
}
