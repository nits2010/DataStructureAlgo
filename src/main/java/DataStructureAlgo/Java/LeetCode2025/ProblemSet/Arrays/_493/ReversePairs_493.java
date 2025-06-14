package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._493;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/14/2025
 * Question Title: 493. Reverse Pairs
 * Link: https://leetcode.com/problems/reverse-pairs/description
 * Description: Given an integer array nums, return the number of reverse pairs in the array.
 * <p>
 * A reverse pair is a pair (i, j) where:
 * <p>
 * 0 <= i < j < nums.length and
 * nums[i] > 2 * nums[j].
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 * Example 2:
 * <p>
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
 * (2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -231 <= nums[i] <= 231 - 1
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._327.CountOfRangeSum_327} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._315.CountOfSmallerNumbersAfterSelf_315}
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
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ReversePairs_493 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 3, 2, 3, 1}, 2));
        tests.add(test(new int[]{2, 4, 3, 5, 1}, 3));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().reversePairs(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Similar to {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._327.CountOfRangeSum_327}
     */
    static class Solution {
        public int reversePairs(int[] nums) {
            int length = nums.length;

            return mergeSort(nums, 0, length - 1);

        }

        private int mergeSort(int[] nums, int start, int end) {
            //if there is only one element, then it can not follow the condition
            if (start >= end)
                return 0;

            int mid = (start + end) >>> 1;

            int count = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);

            int i = start;
            int j = mid + 1;

            while (i <= mid) {

                //find the index which follows the condition nums[i] > 2*nums[j]
                while (j <= end && nums[i] > 2L * nums[j])
                    j++;

                count += j - (mid + 1);

                i++;

            }

            merge(nums, start, mid, end);

            return count;

        }

        void merge(int[] nums, int start, int mid, int end) {
            int i = start;
            int j = mid + 1;

            int[] temp = new int[end - start + 1];
            int k = 0;
            while (i <= mid && j <= end) {
                if (nums[i] <= nums[j]) {
                    temp[k++] = nums[i++];
                } else {
                    temp[k++] = nums[j++];
                }
            }

            while (i <= mid) {
                temp[k++] = nums[i++];
            }

            while (j <= end) {
                temp[k++] = nums[j++];
            }

            System.arraycopy(temp, 0, nums, start, temp.length);
            // int idx = 0;
            // i = start;
            // while (idx < k) {
            //     nums[i++] = temp[idx++];
            // }

        }
    }
}
