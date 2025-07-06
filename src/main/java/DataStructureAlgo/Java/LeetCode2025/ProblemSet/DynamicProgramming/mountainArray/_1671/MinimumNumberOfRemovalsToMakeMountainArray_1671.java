package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming.mountainArray._1671;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._baseProblems.subsequence.LIS._300.LongestIncreasingSubsequence_300;

/**
 * Author: Nitin Gupta
 * Date: 10/30/2024
 * Question Category: 1671. Minimum Number of Removals to Make Mountain Array
 * Description: https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/description/
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,1]
 * Output: 0
 * Explanation: The array itself is a mountain array so we do not need to remove any elements.
 * Example 2:
 * <p>
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * It is guaranteed that you can make a mountain array out of nums.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.nonleetcode.BitonicProblems.LongestBitnoicSubsequence}
 * extension {@link LongestIncreasingSubsequence_300}
 * DP-BaseProblem {@link LongestIncreasingSubsequence_300}
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @BinarySearch
 * @DynamicProgramming
 * @Greedy <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MinimumNumberOfRemovalsToMakeMountainArray_1671 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 3, 1}, 0);
        test &= test(new int[]{2, 1, 1, 5, 6, 2, 3, 1}, 3);
        test &= test(new int[]{2, 1, 1, 5, 6, 8, 2, 1, 1}, 3);
        test &= test(new int[]{9, 8, 1, 7, 6, 5, 4, 3, 2, 1}, 2);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[] nums, int expected) {

        CommonMethods.printTest(new String[]{"Input", "Expected"}, true, nums, expected);
        int output;
        boolean pass, finalPass = true;

        SolutionPoly.SolutionReverse solutionReverse = new SolutionPoly.SolutionReverse();
        output = solutionReverse.minimumMountainRemovals(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Reverse", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionPoly.SolutionWithoutReverse solutionWithoutReverse = new SolutionPoly.SolutionWithoutReverse();
        output = solutionWithoutReverse.minimumMountainRemovals(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Without Reverse", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionUsingBinarySearch solutionUsingBinarySearch = new SolutionUsingBinarySearch();
        output = solutionUsingBinarySearch.minimumMountainRemovals(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;
    }

    /**
     * 1. Compute LIS from forward increasing subsequence
     * 2. Compute LIS from backward aka decreasing subsequence
     * 3. Find max cut point
     * 4. Output would be N- max
     * <p>
     * Similar to {@link DataStructureAlgo.Java.nonleetcode.BitonicProblems.LongestBitnoicSubsequence}
     * The only difference is that, a bitonic sequence can have empty increasing/decreasing part, mountains can't.
     * <p>
     * Time O(N^2) | Space O(N)
     */
    static class SolutionPoly {

        static class SolutionReverse {
            public int minimumMountainRemovals(int[] nums) {

                int n = nums.length;
                int[] dpInc = new int[n];
                int[] dpDec = new int[n];

                //list forward
                longestBitnoicSubSequence(nums, dpInc);

                // reverse array
                int i = 0, j = n - 1;
                while (i < j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;

                    i++;
                    j--;
                }
                longestBitnoicSubSequence(nums, dpDec);


                // max cut point
                int max = 0;
                for (i = 0, j = n - 1; i < n; i++, j--) {
                    if (dpInc[i] > 1 && dpDec[j] > 1) //both increasing and decreasing part should exist
                        max = Math.max(max, dpInc[i] + dpDec[j] - 1);
                }

                return max == 1 ? 0 : n - max;

            }


            private void longestBitnoicSubSequence(int[] arr, int[] dp) {

                int n = arr.length;

                //form increasing
                for (int i = 0; i < n; i++) {
                    dp[i] = 1;

                    for (int j = 0; j < i; j++) {
                        if (arr[i] > arr[j] && dp[i] < dp[j] + 1)
                            dp[i] = dp[j] + 1;
                    }
                }

            }


        }


        static class SolutionWithoutReverse {
            public int minimumMountainRemovals(int[] nums) {

                int n = nums.length;
                int[] dpInc = new int[n];
                int[] dpDec = new int[n];

                //list forward
                lisForward(nums, dpInc);
                lisBackward(nums, dpDec);

                // max cut point
                int max = 0;
                int i;
                for (i = 0; i < n; i++) {
                    if (dpInc[i] > 1 && dpDec[i] > 1) //both increasing and decreasing part should exist
                        max = Math.max(max, dpInc[i] + dpDec[i] - 1);
                }

                return max == 1 ? 0 : n - max;

            }


            private void lisForward(int[] arr, int[] dp) {

                int n = arr.length;
                dp[0] = 1;
                //form increasing
                for (int i = 1; i < n; i++) {
                    dp[i] = 1;

                    for (int j = i - 1; j >= 0; j--) {
                        if (arr[i] > arr[j])
                            dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

            }

            private void lisBackward(int[] arr, int[] dp) {

                int n = arr.length;
                dp[n - 1] = 1;
                //form increasing backward aka decreasing from forward
                for (int i = n - 2; i >= 0; i--) {
                    dp[i] = 1;

                    for (int j = i + 1; j < n; j++) {
                        if (arr[i] > arr[j])
                            dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

            }


        }
    }

    /**
     * {@link LongestIncreasingSubsequence_300.SolutionBinarySearch}
     */
    static class SolutionUsingBinarySearch {

        private void reverse(int[] nums) {
            int n = nums.length;
            // reverse array
            int i = 0, j = n - 1;
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
                j--;
            }
        }

        public int minimumMountainRemovals(int[] nums) {

            int n = nums.length;
            int[] dpInc = new int[n];
            int[] dpDec = new int[n];

            //list forward
            longestIncreasingSubSeq(nums, dpInc);
            reverse(nums);
            longestIncreasingSubSeq(nums, dpDec);

            //make decreasing subsequence
            reverse(dpDec);

            // max cut point
            int max = 0;
            int i;
            for (i = 0; i < n; i++) {
                if (dpInc[i] > 1 && dpDec[i] > 1) //both increasing and decreasing part should exist
                    max = Math.max(max, dpInc[i] + dpDec[i] - 1);
            }

            return max == 1 ? 0 : n - max;

        }

        /**
         * @param lengthOfLIS represent the length of lis at each index
         */
        private void longestIncreasingSubSeq(int[] arr, int[] lengthOfLIS) {

            int n = arr.length;
            //represent the list element in increasing order
            int[] lis = new int[n];

            int lisLength = 0;

            lis[lisLength++] = arr[0]; // starting a new subsequence from the first element.

            for (int i = 1; i < n; i++) {
                if (lis[0] > arr[i]) {
                    lis[0] = arr[i];
                } else if (lis[lisLength - 1] < arr[i]) {
                    lis[lisLength++] = arr[i];
                } else {
                    int index = binarySearchUpperBound(lis, 0, lisLength - 1, arr[i]);
                    lis[index] = arr[i];
                }

                lengthOfLIS[i] = lisLength;
            }
        }

        private int binarySearchUpperBound(int[] nums, int left, int right, int target) {

            int ceilIndex = -1;
            while (left <= right) {

                int mid = (left + right) >>> 1;

                if (nums[mid] >= target) {
                    ceilIndex = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
            return ceilIndex;
        }
    }
}
