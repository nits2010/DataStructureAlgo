package Java.LeetCode;

import Java.nonleetcode.LongestIncreasingSubSequence;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-08
 * Description: https://www.geeksforgeeks.org/convert-to-strictly-increasing-integer-array-with-minimum-changes/
 * Given an array of n integers. Write a program to find minimum number of changes in array so that array is strictly increasing of integers. In strictly increasing array A[i] < A[i+1] for 0 <= i < n
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = { 1, 2, 6, 5, 4}
 * Output : 2
 * We can change a[2] to any value
 * between 2 and 5.
 * and a[4] to any value greater then 5.
 * <p>
 * Input : arr[] = { 1, 2, 3, 5, 7, 11 }
 * Output : 0
 * Array is already strictly increasing.
 * <p>
 * Similar to this {@link ConvertToNonDecreasingArray}
 */
public class ConvertToStrictlyIncreasingArray {

    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(sol.minimumChange(new int[]{1, 2, 6, 5, 4}));
//        System.out.println(sol.minimumChange(new int[]{1, 2, 3, 5, 7, 11}));
        System.out.println(sol.minimumChange(new int[]{3, 10, 3, 11, 4, 5, 6, 7, 8, 12}));

    }


    /**
     * We are asked to make array as "Strictly increasing" array. Which means there is no equal element could be possible in the array.
     * If so, we need to change it.
     * <p>
     * Brute Force:
     * 1. generate all sub-sequence which are strictly increasing in nature.
     * 2. Find the longest one Say length is L
     * 3. to make whole array as strictly increasing, find out those element which are not participating in this strictly longest seq.
     * Those need to be change. Is your answer.
     * Complexity is nearly 2^n
     * <p>
     * Better Solution: Say array length is 'N'
     * 1. Instead of generating all strictly increasing seq in nature, we can use {@link LongestIncreasingSubSequence} to find the longest one.
     * The only change we would require is to make sure the sequence is strictly increasing (not just increasing ).
     * And N- len would be your answer as those element is not participating in SLIS.
     * <p>
     * Note: We can use both the algo O(n^2) and O(nlgon) of LIS to make it work.
     * <p>
     * O(n^2) and can be done to O(nlogn)
     */
    static class Solution {

        public int minimumChange(int nums[]) {

            if (nums == null || nums.length == 0)
                return 0;

            int n = nums.length;
            int lis[] = new int[n];
            int i, j, max = 0;

            /* Initialize LIS values for all indexes */

            Arrays.fill(lis, 1);

            /* Compute optimized LIS values in bottom up manner */
            for (i = 1; i < n; i++) {
                for (j = 0; j < i; j++)
                    if (nums[i] > nums[j] && lis[i] < lis[j] + 1) {
                        if (i - j <= (nums[i] - nums[j])) // to make strictly increasing, there should be i - j element int the seq.
                            lis[i] = lis[j] + 1;
                    }


                max = Math.max(lis[i], max);
            }

            return n - max;
        }


    }
}

