package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1712;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/25/2025
 * Question Title: 1712. Ways to Split Array Into Three Subarrays
 * Link: https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/description/
 * Description: A split of an integer array is good if:
 * <p>
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 * Example 2:
 * <p>
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * Example 3:
 * <p>
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: There is no good way to split nums.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 105
 * 0 <= nums[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link}
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
 * @BinarySearch
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial https://algo.monster/liteproblems/1712 <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class WaysToSplitArrayIntoThreeSubarrays_1712 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 1, 1}, 1));
        tests.add(test(new int[]{1, 2, 2, 2, 5, 0}, 3));
        tests.add(test(new int[]{3, 2, 1}, 0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "Expected"}, true, grid, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_TwoPointer solutionTwoPointer = new Solution_TwoPointer();
        output = solutionTwoPointer.waysToSplit(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"TwoPointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_BinarySearch solutionBinarySearch = new Solution_BinarySearch();
        output = solutionBinarySearch.waysToSplit(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //O(n)
    static class Solution_TwoPointer {
        public int waysToSplit(int[] nums) {

            /**

             assume nums being defined as
             nums[0], nums[1]..... nums[n-1]

             to find the 2 points i and j such that
             sum(nums[0...i-1]) <= sum(nums[i...j-1]) <= sum(nums[j...n-1])

             to find these sums, we can use a prefix sum array, defined as P
             p[0], p[1].... p[n-1]
             since this is a prefix sum-array on non-neg integers, it will be in increasing order always.

             now replacing a sum with p as below

             p[i] <= p[j] - p[i] <= p[n-1] - p[j]

             let's evaluate each;
             p[i] <= p[j] - p[i] => 2*p[i] <= p[j]

             and

             p[j] - p[i] <= p[n-1] - p[j] => 2*p[j] + p[i] <= p[n-1] => p[j] <= (p[n-1] - p[i]) / 2

             now we have two equations

             2*p[i] <= p[j]
             &
             p[j] <= (p[n-1] - p[i]) / 2

             joining them
             2*p[i]  <= p[j] <= (p[n-1] - p[i]) / 2 OR 2*p[i]  <= p[j] - p[i] <= (p[n-1] - p[j])

             which means that we need to find the i and j such that which satisfy above condition.

             Since split array can't be empty, hence we need to make sure to keep at least one element on the right, which limits
             our search space from n-1 to n-2, similarly left spilt has to has at least one element.

             hence our i could like between [1....n-2] and our j also needs to be somewhere b/w [i..n-2]

             so we can try all the i from 1..n-2 which satisfy above condition.

             */

            int n = nums.length;
            int[] prefixSum = new int[n];
            int result = 0;
            final int MOD = 1000000007;

            // Compute prefix sum
            prefixSum[0] = nums[0];
            for (int i = 1; i < n; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }

            int j = 0, k = 0;

            // i = end of left subarray
            for (int i = 0; i < n - 2; i++) {

                // Move j to the smallest index where sum(mid) >= sum(left)
                j = Math.max(j, i + 1);
                while ((j < n - 1 && prefixSum[j] < 2 * prefixSum[i])) {
                    j++;
                }

                // Move k to the largest index where sum(mid) <= sum(right)
                k = Math.max(k, j);
                while (k < n - 1 && prefixSum[k] - prefixSum[i] <= prefixSum[n - 1] - prefixSum[k]) {
                    k++;
                }

                // Number of valid splits for this i = (k - j)
                result = (result + k - j) % MOD;

            }

            return result;
        }

    }

    //binary search; to find the j and k
//    O(n log n)
    static class Solution_BinarySearch {

        public int waysToSplit(int[] nums) {
            int n = nums.length;
            int[] prefixSum = new int[n];
            long result = 0;
            final int MOD = 1000000007;

            // Compute prefix sum
            prefixSum[0] = nums[0];
            for (int i = 1; i < n; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }

            // j and k will be the start and end bounds for valid mid points (end of mid).
            int j = 0, k = 0;

            // i is the end index of left subarray (left = [0 ... i - 1]).
            for (int i = 1; i < n - 1; i++) {



                //keeping i as the point a, find a point on the left side
                //Finds the leftmost j (i.e., smallest mid-end) where:
                //sum(left) <= sum(mid) <= sum(right)
                j = binarySearch(prefixSum, prefixSum[i - 1], i, true);

                //keeping i as the point a, find a point on the right side
                //Finds the rightmost k (i.e., largest mid-end) where:
                //sum(left) <= sum(mid) <= sum(right)
                k = binarySearch(prefixSum, prefixSum[i - 1], i, false);

                if (j != -1 && k != -1)
                    result = (result + (k - j + 1)) % MOD;

            }

            return (int) result % MOD;

        }

        int binarySearch(int[] sum, int leftSum, int idx, boolean isLeftSearch) {

            int n = sum.length;

            int low = idx, high = n - 2; // keeping n-1 element as one of the element on right side to satisfy condition

            int index = -1; // to find the index

            while (low <= high) {

                int mid = low + (high - low) / 2;

                int midSum = sum[mid] - leftSum;
                int rightSum = sum[n - 1] - sum[mid];

                //does satisfy condition
                if (leftSum <= midSum && midSum <= rightSum) {
                    index = mid;

                    //we need to extend this index based on isLeftSearch either left or right side

                    if (isLeftSearch) {
                        //we are searching for point b on left side
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }

                } else if (leftSum > midSum) {
                    //increase leftBoundary to increas midSum
                    low = mid + 1;

                } else  // if (midSum > rightSum)
                {
                    //increase rightBoundary to increase rigthSUm
                    high = mid - 1;
                }

            }

            return index;
        }
    }

}
