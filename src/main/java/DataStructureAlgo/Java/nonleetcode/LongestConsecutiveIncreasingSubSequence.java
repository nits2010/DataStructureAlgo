package DataStructureAlgo.Java.nonleetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-08
 * Description: https://www.geeksforgeeks.org/longest-increasing-consecutive-subsequence/
 * Given N elements, write a program that prints the length of the longest increasing subsequence whose adjacent element difference is one.
 * <p>
 * Examples:
 * <p>
 * Input : a[] = {3, 10, 3, 11, 4, 5, 6, 7, 8, 12}
 * Output : 6
 * Explanation: 3, 4, 5, 6, 7, 8 is the longest increasing subsequence whose adjacent element differs by one.
 * <p>
 * Input : a[] = {6, 7, 8, 3, 4, 5, 9, 10}
 * Output : 5
 * Explanation: 6, 7, 8, 9, 10 is the longest increasing subsequence
 * <p>
 * Similar to this {@link LongestIncreasingSubSequence}
 */
public class LongestConsecutiveIncreasingSubSequence {

    public static void main(String[] args) {
        System.out.println(longestConsecutiveIncreasingSubSequence(new int[]{3, 10, 3, 11, 4, 5, 6, 7, 8, 12}));

    }

    /**
     * Naive Approach: A normal approach will be to iterate for every element and find out the longest increasing subsequence.
     * For any particular element, find the length of the subsequence starting from that element. Print the longest length of the subsequence
     * thus formed. The time complexity of this approach will be O(n2).
     * <p>
     * Dynamic Programming Approach: Let DP[i] store the length of the longest subsequence which ends with A[i]. For every A[i],
     * if A[i]-1 is present in the array before i-th index, then A[i] will add to the increasing subsequence which has A[i]-1.
     * Hence DP[i] = DP[ index(A[i]-1) ] + 1. If A[i]-1 is not present in the array before i-th index, then DP[i]=1 since the A[i] element
     * forms a subsequence which starts with A[i]. Hence the relation for DP[i] is:
     * <p>
     * If A[i]-1 is present before i-th index:
     * <p>
     * DP[i] = DP[ index(A[i]-1) ] + 1
     * else:
     * <p>
     * DP[i] = 1
     *
     * @param nums
     * @return
     */
    public static int longestConsecutiveIncreasingSubSequence(int nums[]) {
        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;

        /**
         * Key - element
         * Value - index
         */
        final Map<Integer, Integer> map = new HashMap<>();
        /**
         * Length of sequence ending at this index
         */
        int dp[] = new int[n];

        int maxL = 0;

        for (int i = 0; i < n; i++) {

            if (map.containsKey(nums[i] - 1))
                dp[i] = dp[map.get(nums[i] - 1)] + 1;
            else
                dp[i] = 1;

            map.put(nums[i], i);

            maxL = Math.max(maxL, dp[i]);

        }

        return maxL;
    }

}
