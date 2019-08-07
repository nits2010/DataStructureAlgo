package Java.sum.sub.array.problmes;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/maximum-sum-of-non-overlapping-subarrays-of-length-atmost-k/
 * <p>
 * Given an integer array ‘arr’ of length N and an integer ‘k’,
 * select some non-overlapping subarrays such that each sub-array is of length at most ‘k’,
 * no two sub-arrays are adjacent and sum of all the elements of the selected sub-arrays are maximum.
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
public class MaximumSumNonOverlappingSubarraysLengthAtmostK {

    public static void main(String[] args) {

        // Input array
        System.out.println(maximumSumNonOverlappingSubarraysLengthAtmostK(new int[]{-1, 2, -3, 4, 5}, 2));
    }

    /**
     * Explanation:
     * We need to find "sub-array" (s) s.t.
     * 1. They are not overlapping i.e. if a sub-array (i..j) then other sub-array either (m...i-1) or (j+1....p)  or ...
     * 2. each of these sub-array can have at most k length.
     * 3. The sum of these sub-array should be maximum.
     * <p>
     * if k>=1, we can assume that in worst case, we can put each element in different sub-array and choose those sub-arrays which
     * gives us maximum sum.
     * <p>
     * How many sub-arrays ? There could be maximum "n" sub-array where "n" is the size of given array.
     * Putting all in different sub-array gives you "n" maximum sub-array and
     * putting them in group depends on "k" as max size can be at most k.
     * <p>
     * <p>
     * Since any sub-array size can not be more than k.Then each element has only few choices.
     * 1. Either the current element start its own sub-array
     * 2. Or it is a part of some other sub-array ( only when this is the adjustment element of that sub-array)
     * <p>
     * So;
     * 1. Either we reject the current element i and start a new sub-array from i+1
     * 2. Either we take this i and include in our previous sub-array{} as -> {i} and start a new sub-array from i+2
     * 3. Either we take this i+1 and include in our previous sub-array{1} as -> {i, i+1} and start a new sub-array from i+3
     * <p>
     * ...'
     * select {i, i+1,....i+k-1} and start new from i+k+1 .
     * <p>
     * <p>
     * dp[i] = max(dp[i+1], arr[i]+dp[i+2], arr[i]+arr[i+1]+dp[i+3],
     * ...arr[i]+arr[i+1]+arr[i+2]...+arr[i+k-1] + dp[i+k+1])
     *
     * @param num
     * @param k
     * @return
     */
    static int maximumSumNonOverlappingSubarraysLengthAtmostK(int num[], int k) {

        int[] dp = new int[num.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        return maximumSumNonOverlappingSubarraysLengthAtmostK(num, 0, k, dp);
    }

    private static int maximumSumNonOverlappingSubarraysLengthAtmostK(int[] num, int i, int k, int[] dp) {

        if (i >= num.length)
            return 0;

        if (dp[i] != Integer.MIN_VALUE)
            return dp[i];

        //Either we reject the current element i and start a new sub-array from i+1
        dp[i] = maximumSumNonOverlappingSubarraysLengthAtmostK(num, i + 1, k, dp);

        int total = 0;

        int max = 0;
        for (int j = i; j < (i + k) && j < num.length; j++) {
            total += num[j];
            max = Math.max(max, total + ((j + 2) >= num.length ? 0 : dp[j + 2]));

        }

        dp[i] = Math.max(dp[i], max);
        return dp[i];
    }
}
