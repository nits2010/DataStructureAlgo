package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.bitManipulations._2044;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date:18/10/24
 * Question Category: 2044. Count Number of Maximum Bitwise-OR Subsets
 * Description: https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/description/?envType=daily-question&envId=2024-10-18
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.
 * <p>
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.
 * <p>
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1]
 * Output: 2
 * Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
 * - [3]
 * - [3,1]
 * Example 2:
 * <p>
 * Input: nums = [2,2,2]
 * Output: 7
 * Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
 * Example 3:
 * <p>
 * Input: nums = [3,2,1,5]
 * Output: 6
 * Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 105
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @Array
 * @Backtracking
 * @BitManipulation
 * @Enumeration
 * @DynamicProgramming <p>
 * Company Tags
 * -----
 * @Editorial
 * @OptimalSoltuion {@link SolutionBacktracking} {@link SolutionRecursionMemo}
 */

public class CountNumberOfMaximumBitwiseORSubsets_2044 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{3, 2, 1, 5}, 6);
        test &= test(new int[]{2, 2, 2}, 7);
        test &= test(new int[]{3, 1}, 2);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] nums, int expected) {
        System.out.println("--------------------------------");
        System.out.println("Input:" + CommonMethods.toString(nums) + " expected:" + expected);
        int output;
        boolean pass, finalPass = true;

        SolutionBacktracking solutionBacktracking = new SolutionBacktracking();
        output = solutionBacktracking.countMaxOrSubsets(nums);
        pass = output == expected;
        System.out.println("Solution Backtracking: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        SolutionRecursionMemo solutionMemo = new SolutionRecursionMemo();
        output = solutionMemo.countMaxOrSubsets(nums);
        pass = output == expected;
        System.out.println("Backtracking + Memo: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        SolutionBitMask solutionBitMask = new SolutionBitMask();
        output = solutionBitMask.countMaxOrSubsets(nums);
        pass = output == expected;
        System.out.println("BiMask: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;


        return finalPass;
    }

    /**
     * Simple Backtracking solution
     * O(2^n) / O(n) there will be max n call at a time in stack, since total element is n only.
     */
    static class SolutionBacktracking {
        public int countMaxOrSubsets(int[] nums) {

            int maxBitwiseOr = 0;
            for (int n : nums)
                maxBitwiseOr |= n;

            return countMaxOrSubsets(nums, nums.length, 0, 0, maxBitwiseOr);


        }

        private int countMaxOrSubsets(int[] nums, int n, int i, int currentBitwiseOr, int maxBitwiseOr) {

            if (i == n) {
                return (maxBitwiseOr == currentBitwiseOr) ? 1 : 0;
            }

            // take this ith element
            int bitwise = currentBitwiseOr | nums[i];

            int include = countMaxOrSubsets(nums, n, i + 1, bitwise, maxBitwiseOr);

            // don't take the ith element, take i+1th element
            int nonInclude = countMaxOrSubsets(nums, n, i + 1, currentBitwiseOr, maxBitwiseOr);

            return include + nonInclude;

        }

        int count = 0;

        private void countMaxOrSubsetsV2(int[] nums, int n, int i, int currentBitwiseOr, int maxBitwiseOr) {

            if (i == n) {
                if (maxBitwiseOr == currentBitwiseOr)
                    count++;
                return;
            }

            // take this ith element
            int bitwise = currentBitwiseOr | nums[i];

            countMaxOrSubsetsV2(nums, n, i + 1, bitwise, maxBitwiseOr);

            // don't take the ith element, take i+1th element
            countMaxOrSubsetsV2(nums, n, i + 1, currentBitwiseOr, maxBitwiseOr);


        }


        int fCount = 0;

        private void countMaxOrSubsetsV3(int[] nums, int n, int i, int currentBitwiseOr, int maxBitwiseOr) {


            if (maxBitwiseOr == currentBitwiseOr) {
                fCount++;
                return;
            }


            // take this ith element
            for (int index = i; i < n; i++) {
                countMaxOrSubsetsV3(nums, n, index + 1, currentBitwiseOr | nums[index], maxBitwiseOr);
            }


        }

    }

    /**
     * Simple Backtracking solution with caching
     * O(n*maxOrValue) /  O(n*maxOrValue) there will be max n call at a time in stack, since total element is n only.
     */
    static class SolutionRecursionMemo {

        public int countMaxOrSubsets(int[] nums) {
            int n = nums.length;
            int maxBitwiseOr = 0;

            for (int e : nums)
                maxBitwiseOr |= e;

            int[][] memo = new int[n][maxBitwiseOr + 1];
            return countMaxOrSubsets(nums, n, 0, 0, maxBitwiseOr, memo);

        }

        private int countMaxOrSubsets(int[] nums, int n, int i, int currentBitwiseOr, int maxBitwiseOr, int[][] memo) {
            if (i == n) {
                return (maxBitwiseOr == currentBitwiseOr) ? 1 : 0;
            }

            if (memo[i][currentBitwiseOr] != 0)
                return memo[i][currentBitwiseOr];


            // take this ith element
            int bitwise = currentBitwiseOr | nums[i];
            int include = countMaxOrSubsets(nums, n, i + 1, bitwise, maxBitwiseOr, memo);

            // don't take the ith element, take i+1th element
            int nonInclude = countMaxOrSubsets(nums, n, i + 1, currentBitwiseOr, maxBitwiseOr, memo);

            return memo[i][currentBitwiseOr] = include + nonInclude;

        }

    }

    /**
     * Bitmask solution, try all possible subset
     * O(2^n * n) / O(1)
     * For explanation; Check LeetCode editorial
     * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/editorial/?envType=daily-question&envId=2024-10-18
     */
    static class SolutionBitMask {
        public int countMaxOrSubsets(int[] nums) {

            int size = nums.length;
            int maxBitwiseOr = 0;
            for (int n : nums)
                maxBitwiseOr |= n;

            int totalSubset = 1 << size;
            int finalCount = 0;

            //try all subsets
            //O(2^n * n)
            for (int mask = 0; mask < totalSubset; mask++) {

                int currentBitwiseOr = 0;

                // O(n)
                for (int j = 0; j < size; j++) {
                    boolean isBitSet = ((mask >> j) & 1) == 1;
                    if (isBitSet)
                        currentBitwiseOr |= nums[j];
                }

                if (currentBitwiseOr == maxBitwiseOr)
                    finalCount++;

            }
            return finalCount;


        }
    }
}
