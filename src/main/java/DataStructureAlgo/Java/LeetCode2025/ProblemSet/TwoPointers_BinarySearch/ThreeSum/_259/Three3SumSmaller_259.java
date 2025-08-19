package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.ThreeSum._259;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/26/2025
 * Question Title: 259. 3Sum Smaller
 * Link: https://leetcode.com/problems/3sum-smaller/description/
 * https://leetcode.ca/all/259.html
 * Description: Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * <p>
 * Example:
 * <p>
 * Input: nums = [-2,0,1,3], and target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.threeSum.ThreeSumSmaller}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.ThreeSum._15.Three3Sum_15}
 * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.ThreeSum._923.Three3SumWithMultiplicity_923}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @Sorting
 * @Counting
 * @PremiumQuestion
 * 
 * Company Tags
 * -----
 * @Google
 * @IBM
 * @Mathworks <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Three3SumSmaller_259 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{-2, 0, 1, 3}, 2, 2));
        tests.add(test(new int[]{5, 1, 3, 4, 7}, 12, 4));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int target, int expected) {
        CommonMethods.printTest(new String[]{"Grid", "target", "Expected"}, true, grid, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.threeSumSmaller(grid, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int threeSumSmaller(int[] nums, int target) {
            int n = nums.length;
            if (n == 0) return 0;

            Arrays.sort(nums);
            int count = 0;


            for (int i = 0; i < n - 2; i++) {

                int a = nums[i];

                if (i > 0 && a == nums[i - 1])
                    continue;

                int j = i + 1;
                int k = n - 1;

                while (j < k) {
                    int b = nums[j];
                    int c = nums[k];

                    int sum = a + b + c;

                    if (sum < target) {

                        //the elements b/w j and k with respect i are smaller than target
                        // [1,3,4,5,7] ; 12
                        // i = 0, j = 2, k = 7, sum = 1+3+7 = 10 < 12
                        //on moving k towards j, the sum will reduce further and all of these sum will participate in < target sum
                        count += k - j;

                        //to gain more sum, increase j
                        j++;
                    } else {
                        //the sum could be either target or greater, reduce it to count it
                        k--;
                    }
                }
            }
            return count;
        }
    }
}
