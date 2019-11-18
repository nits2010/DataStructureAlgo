package Java.nonleetcode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-06
 * Description: https://www.geeksforgeeks.org/find-minimum-adjustment-cost-of-an-array/
 * Given an array of positive integers, replace each element in the array such that the difference between adjacent elements in the array is less than or equal to a given target. We need to minimize the adjustment cost, that is the sum of differences between new and old values. We basically need to minimize &Sum;|A[i] – Anew[i]| where 0 ≤ i ≤ n-1, n is size of A[] and Anew[] is the array with adjacent difference less that or equal to target.
 * <p>
 * Assume all elements of the array is less than constant M = 100.
 * <p>
 * Examples:
 * <p>
 * Input: arr = [1, 3, 0, 3], target = 1
 * Output: Minimum adjustment cost is 3
 * Explanation: One of the possible solutions
 * is [2, 3, 2, 3]
 * <p>
 * Input: arr = [2, 3, 2, 3], target = 1
 * Output: Minimum adjustment cost is 0
 * Explanation:  All adjacent elements in the input
 * array are already less than equal to given target
 * <p>
 * Input: arr = [55, 77, 52, 61, 39, 6,
 * 25, 60, 49, 47], target = 10
 * Output: Minimum adjustment cost is 75
 * Explanation: One of the possible solutions is
 * [55, 62, 52, 49, 39, 29, 30, 40, 49, 47]
 */
public class MinimumAdjustmentCost {

    public static void main(String []args) {

        SolutionMinimumAdjustmentCost sol =new SolutionMinimumAdjustmentCost();

        int[] arr = {1, 3, 0, 3};
        int t1 = 1;
        System.out.println(sol.minimumCostAdjustmentElement(arr, t1, 100));

        int[] arr2 = {2, 3, 2, 3};
        int t2 = 1;
        System.out.println(sol.minimumCostAdjustmentElement(arr2, t2, 100));

        int[] arr3 = {55, 77, 52, 61, 39, 6, 25, 60, 49, 47};
        int t3 = 10;
        System.out.println(sol.minimumCostAdjustmentElement(arr3, t3, 100));

    }
}

class SolutionMinimumAdjustmentCost {

    /**
     * Lets understand the question first before jumping to the solution.
     * <p>
     * Given an array, of integers number (+ve, -ve) and a target. We need to find a new array by transforming the given array s.t. every element in new array follow the adjustment rule
     * <p>
     * adjustment rule:
     * This says, picking any two adjust element in the array as a[i] and a[j] where i = j-1 or j =i+1 ; i!=j
     * then Abs(a[i] - a[j]) <= target
     * <p>
     * Approach:
     * Lets approach this problem, as we can see that picking element from left to right, if we see element at a[i] and element at a[j] have difference more than target, then one
     * of them has to transform to "nearest" integer s.t. ( say we numberOfWays ith element)
     * Abs(a[i]new - a[i]old) is minimum. and Abs(a[i]new - a[j])<=target.
     * <p>
     * Brute force:
     * What we can do is apply some sort of greedy approach and transform a given integer in a way that it follow the rules and we keep apply to all elements, then backtrack to previous and
     * try another combination
     * there is two things
     * 1. either we transform i-th element [ then take care of (i-1)-the element
     * 2. or transform j-th element [ then take care of (j+1)-the element
     * <p>
     * and find the minimum cost of each for all element and then take minimum of them.
     * <p>
     * This is exponential, as we need to numberOfWays every element and it has two possible path, each path lead to n possible combinations; Hence n*2^n
     * <p>
     * <p>
     * Dynamic Programming approach:
     * The most important information given in question is that each number in a given array could be only up to M=100.
     * We can use this information and transform it.
     * <p>
     * Lets say at any moment we are at i-the element.
     * in order to transform this i-the element, we need to look previous (i-1)-th element, this current element can be in the range of
     * [(i-1) element - target] to [(i-1) element + target ], Why because if we want to numberOfWays i-th element we need to numberOfWays it to "nearest" element which gives Abs(a[i]new - a[i]old) is minimum
     * <p>
     * this gives a intuition that we need to try all the possibilities. Hence we need to look back all the range possibilities for previous element in order to make current element numberOfWays.
     * <p>
     * Hence, we need to store solution for previous element for all the ranges.
     * <p>
     * How to find a range ?
     * lets suppose, we want to numberOfWays i-the element to some element "j" [j varies from 1 to M] then, we need to see the range of i-1 the element s.t.
     * [ j-target, j+target ] is minimum
     * Re-writing range as below
     * [ Math.max(0, j-target), Math.min( M-1, j+target) ] ; as we don't want the range form negetive number or goes beyond the possible range of a number M
     * <p>
     * Hence;
     * if we define
     * dp[i][j] as the minimum cost to transform i-th element to "j"
     * then
     * dp[i][j] = Min ( cost of previous element in the range [ Math.max(0, j-target), Math.min( M-1, j+target) ] ] + cost of changing i-the element to "j" ]
     * <p>
     * ***************************************************************
     * dp[i][j] = Min ( dp[i-1][k] + Math.abs( j - a[i] ) ) s.t. Math.max(0, j-target)<=k<=Math.min( M-1, j+target)
     * where dp[i-1][k] is cost of previous element in the range [ Math.max(0, j-target), Math.min( M-1, j+target) ]
     * Math.abs( j - a[i] is cost of changing i-the element to "j" ]
     * <p>
     * dp[n][M+1] would be our table
     * -> dp[n-1][M] would be our answer
     * ***************************************************************
     *
     * @param a            -> given array
     * @param target       -> to have the difference
     * @param elementRange -> M
     * @return
     */

    public int minimumCostAdjustmentElement(int a[], int target, final int elementRange) {

        if (a == null || a.length == 0)
            return Integer.MIN_VALUE;

        int n = a.length;
        final int M = elementRange;

        int dp[][] = new int[n][M + 1];

        //cost of converting first element to any element range from 0 to M
        for (int j = 0; j <= M; j++)
            dp[0][j] = Math.abs(j - a[0]);


        //start numberOfWays the second element onwards using first element range possibilities
        for (int i = 1; i < n; i++) {

            for (int j = 0; j <= M; j++) {

                int lowerRange = Math.max(0, j - target);
                int upperRange = Math.min(M - 1, j + target);
                dp[i][j] = Integer.MAX_VALUE;
                int min = dp[i][j];
                for (int k = lowerRange; k <= upperRange; k++) {

                    min = Math.min(dp[i - 1][k] + Math.abs(j - a[i]), min);
                }
                dp[i][j] = min;
            }

        }

        return Arrays.stream(dp[n-1]).min().orElse(Integer.MIN_VALUE);

    }
}
