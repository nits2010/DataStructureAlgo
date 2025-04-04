package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SumProblems._18;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/1/2025
 * Question Title: 18. 4Sum
 * Link: https://leetcode.com/problems/4sum/description/
 * Description: Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.fourSum.FourSum4Sum}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Amazon
 * @Apple
 * @Bloomberg
 * @Facebook
 * @Google
 * @Microsoft
 * @Yahoo
 * @Apple <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Four4Sum_18 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 0, -1, 0, -2, 2}, 0, List.of(List.of(-2, -1, 1, 2), List.of(-2, 0, 0, 2), List.of(-1, 0, 0, 1))));
        tests.add(test(new int[]{2, 2, 2, 2, 2}, 8, List.of(List.of(2, 2, 2, 2))));
        tests.add(test(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11, List.of(List.of(-5, -4, -3, 1))));
        tests.add(test(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11, List.of(List.of(-5, -4, -3, 1))));
        tests.add(test(new int[]{0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000}, 1000000000, List.of(List.of(0, 0, 0, 1000000000))));
        tests.add(test(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296, List.of())); //test overflow

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int target, List<List<Integer>> expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "target", "Expected"}, true, grid, target, expected);

        List<List<Integer>> output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.fourSum(grid, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            if (nums == null || nums.length == 0)
                return null;

            Arrays.sort(nums);

            List<List<Integer>> result = new ArrayList<>();
            int n = nums.length;

            for (int i = 0; i < n - 3; i++) { // as we need to include 3 more elements post ith element

                int a = nums[i];

                for (int j = n - 1; j > i + 2; j--) { // as we need to include 2 more elements other than i and j and those will always be b/w i + 1 and j -1

                    int b = nums[j];

                    long remSum = target - (long) (a + b);

                    twoSum(nums, remSum, i + 1, j - 1, i, j, result);

                    // //avoid all duplicate b
                    while (j > i + 2 && nums[j] == nums[j - 1])
                        j--;

                }

                while (i < n - 2 && nums[i] == nums[i + 1])
                    i++;

            }

            return result;
        }

        private void twoSum(int[] nums, long target, int i, int j, int x, int y, List<List<Integer>> result) {

            while (i < j) {
                int a = nums[i];
                int b = nums[j];
                long sum = a + b;
                if (sum == target) {
                    result.add(List.of(nums[x], nums[i], nums[j], nums[y]));
                    i++;
                    j--;

                    //avoid all duplicates
                    while (i < j && nums[i] == nums[i - 1])
                        i++;
                    while (j > i && nums[j] == nums[j + 1])
                        j--;
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }

            }

        }
    }
}
