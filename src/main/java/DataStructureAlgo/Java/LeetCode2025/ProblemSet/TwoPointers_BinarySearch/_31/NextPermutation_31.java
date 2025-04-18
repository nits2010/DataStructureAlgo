package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._31;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/6/2025
 * Question Title: 31. Next Permutation
 * Link: https://leetcode.com/problems/next-permutation/description/
 * Description: A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.nonleetcode.PermutationsSortedLexicographicOrder} {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement._556.NextGreaterElementIII_556}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Amazon
 * @Apple
 * @Bloomberg
 * @ByteDance
 * @eBay
 * @Facebook
 * @Google
 * @Houzz
 * @caMorgan
 * @Microsoft
 * @Quora
 * @Rubrik
 * @Sumologic
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class NextPermutation_31 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3}, new int[]{1, 3, 2}));
        tests.add(test(new int[]{3, 2, 1}, new int[]{1, 2, 3}));
        tests.add(test(new int[]{1, 1, 5}, new int[]{1, 5, 1}));
        tests.add(test(new int[]{1, 5, 8, 4, 7, 6, 5, 3, 1}, new int[]{1, 5, 8, 5, 1, 3, 4, 6, 7}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "Expected"}, true, nums, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        solution.nextPermutation(nums);
        pass = CommonMethods.compareResultOutCome(nums, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, nums, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public void nextPermutation(int[] nums) {
            int n = nums.length;
            //first find the number which is smaller in adj cell on left side
            int i = n - 2;
            while (i >= 0 && nums[i + 1] <= nums[i]) i--;

            //no such number exits
            if (i < 0) {
                reverse(nums, i + 1);
                return;
            }

            //find the first number which is larger than this ith num
            int j = n - 1;
            while (j > i && nums[j] <= nums[i]) j--;

            //swap
            swap(nums, i, j);

            reverse(nums, i + 1);

        }

        private void reverse(int[] nums, int index) {
            int i = index, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
