package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-27
 * Description:https://www.geeksforgeeks.org/maximum-sum-of-non-overlapping-subarrays-of-length-atmost-k/
 * Given an integer array ‘arr’ of length N and an integer ‘k’, select some non-overlapping subarrays such that each sub-array if of length at most ‘k’, no two sub-arrays are adjacent and sum of all the elements of the selected sub-arrays are maximum.
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = {-1, 2, -3, 4, 5}, k = 2
 * Output : 11
 * Sub-arrays that maximizes sum will be {{2}, {4, 5}}.
 * Thus, the answer will be 2+4+5 = 11.
 * <p>
 * Input :arr[] = {1, 1, 1, 1, 1}, k = 1
 * Output : 3
 */
public class MaximumSumSubarrayAtMostKSize {

    public static void main(String args[]) {
        int arr[] = {-1, 2, -3, 4, 5};

        System.out.println(maximumSumSubarrayAtMostKSize(arr, 2));

        int arr2[] = {1, 1, 1, 1, 1};
        System.out.println(maximumSumSubarrayAtMostKSize(arr2, 1));


    }


    private static int maximumSumSubarrayAtMostKSize(int arr[], int k) {
        /**
         * as problem says, we need to select subarray such that they are not "adjacent to each other" that means if we select a sub array from an index
         *  "i to j" then the other subarray has to start from j+2 as starting from j+1 would be adjacent to previous chosen array.
         *
         *  other important fact that the selected sub-array has at most k element in it means it can have 0 , 1 ... k elements
         *
         *  let suppose if we choose a sub-array start form 'i' index, then other sub-array could start form i+2 only
         *  ans since it can be at most k size that means if we choose sub-array start from "i" then it can ends up till "i+k-1" index and other sub-array has to start from
         *  only "i+k+1" [ distance 2] .
         *
         *  there are many choices;
         *  1) if we don't select the "i"th index to be part of sub-array (reject ith element) then we can choose "i+1" index.
         *  2) if we select "i"  then we have to choose from i+2.
         *  3) if we select sub-array {i, i+1) then we have to choose i+3 onwards
         *  .....
         *  if we select (i, i+1.....i+k-1) then we have to choose (i+k+1..... ) array
         *
         *  hence
         *
         *  dp[i] defines the maximum sum sub-array ending at ith index;
         *
         *  dp [i] = Max ( dp[i+1], a[i] + dp[i+2], a[i] + a[i+1] + dp[i+3] .... (a[i] + a[i+1] + ... + a[i+k-1] + dp[i+k+1] )
         *
         *  clearly we need to first find the dp[i+1] or dp[i+2].... dp[i+k+1] before dp[i] -> tail recursion
         *
         */

        int n = arr.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);

        maximumSumSubarrayAtMostKSize(arr, k, dp, 0);

        return dp[0];
    }

    static int maximumSumSubarrayAtMostKSize(int a[], int k, int dp[], int i) {

        if (i >= a.length)
            return 0;

        if (dp[i] != -1)
            return dp[i];


        dp[i] = maximumSumSubarrayAtMostKSize(a, k, dp, i + 1); //solve for i+1, i+2... before i

        int total = 0;
        for (int j = i; j < i + k && j < a.length; j++) { // as from an index i, we can at most select the sub array till i+K-1

            total += a[j];
            dp[i] = Math.max(dp[i], total + (j + 2 >= a.length ? 0 : dp[j + 2]));

        }
        return dp[i];


    }


}
