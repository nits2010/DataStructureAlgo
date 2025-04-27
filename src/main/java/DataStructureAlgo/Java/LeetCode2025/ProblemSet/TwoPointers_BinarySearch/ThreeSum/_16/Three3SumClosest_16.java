package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.ThreeSum._16;

import java.util.*;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.ThreeSum._15.Three3Sum_15;
import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/26/2025
 * Question Title: 16. 3Sum Closest
 * Link: https://leetcode.com/problems/3sum-closest/description/
 * Description: Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * <p>
 * Return the sum of the three integers.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * <p>
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 500
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.threeSum.ThreeSum3SumClosest}
 * Similar {@link}
 * extension {@link Three3Sum_15}
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
 * @GoldmanSachs
 * @Google
 * @Microsoft
 * @Uber
 * @Yandex
 * @Apple <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Three3SumClosest_16 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{-1, 2, 1, -4}, 1, 2));
        tests.add(test(new int[]{0, 0, 0}, 1, 0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int target, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Grid", "target", "Expected"}, true, grid, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.threeSumClosest(grid, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int threeSumClosest(int[] nums, int target) {

            int n = nums.length;

            Arrays.sort(nums);

            int closestSum = 100000; //-10^4 <= target <= 10^4
            for (int i = 0; i < n - 2; i++) {

                int a = nums[i];

                int j = i + 1;
                int k = n - 1;

                //find b and c such that ~=target

                while (j < k) {
                    int b = nums[j];
                    int c = nums[k];

                    int sum = a + b + c;

                    if (sum == target)
                        return sum;

                    int diff = Math.abs(sum - target);

                    //cache solution
                    if (Math.abs(sum - target) <= Math.abs(closestSum - target)) {
                        closestSum = sum;
                    }

                    if (sum > target)
                        k--;
                    else
                        j++;

                }

            }

            return closestSum;

        }
    }
}
