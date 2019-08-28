package Java.LeetCode.sumsubarrayproblems;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-26
 * Description: https://www.geeksforgeeks.org/maximum-sum-subsequence-least-k-distant-elements/
 * Given an array and a number k, find a subsequence such that
 * <p>
 * Sum of elements in subsequence is maximum
 * Indices of elements of subsequence differ atleast by k
 * Examples
 * <p>
 * Input : arr[] = {4, 5, 8, 7, 5, 4, 3, 4, 6, 5}
 * k = 2
 * Output: 19
 * Explanation: The highest value is obtained
 * if you pick indices 1, 4, 7, 10 giving
 * 4 + 7 + 3 + 5 = 19
 * <p>
 * Input: arr[] = {50, 70, 40, 50, 90, 70, 60,
 * 40, 70, 50}
 * k = 2
 * Output: 230
 * Explanation: There are 10 elements and k = 2.
 * If you select 2, 5, and 9 you get a total
 * value of 230, which is the maximum possible.
 */
public class MaximumSumSubsequenceAtLeastKDistant {
    public static void main(String[] args) {
        System.out.println(MaximumSumSubsequenceAtLeastKDistantDP.maximumSumSubsequenceAtLeastKDistantDP(
                new int[]{50, 70, 40, 50, 90, 70, 60, 40, 70, 50}, 2));
    }
}

class MaximumSumSubsequenceAtLeastKDistantDP {


    /**
     * We need to find all those element which are k distance away and forming a sub-sequence which has maximum sum.
     * This is closely related to the problem "Longest increasing sub-sequence ". Instead here we have two different thing
     * 1. Instead longest we need to put constraint as Maximum Sum
     * 2. instead of choosing element one after other in distance 0 we need to choose which has distance k.
     * <p>
     * Lets revise Longest increasing sub-sequence {@link Java.LongestIncreasingSubSequence}
     * LIS[i] is length of longest increasing sub-sequence ending at i
     * <p>
     * LIS[i] = { 1 + MAX {LIS[j]} 0<=j<i and A[i] > A[j] and 0<=i<N [length of array]
     * <p>
     * *             otherwise 1
     * *        }
     * <p>
     * Now here, instead of choosing j which is just 1 distance away we need to choose k distance away from i
     * which is j = i+k+1;
     * <p>
     * now there is two possibilities of choosing j
     * 1. if i+k+1 is outside the boundary means i+k+1 >=N ; then we cannot select any other element as part of the subsequence. '
     * Hence we need to decide whether to select this element or one of the elements after it.
     * 2. if i+k+1 is in the boundary means i+k+1 <N ; Thus we need to decide whether to select these two elements, or move on to the next adjacent element
     * <p>
     * As well as the condition instead of A[i] > A[j] it should be M[i]  = Max (  M[i+1], A[i] + M[i+k+1]  )
     * <p>
     * Lets define
     * M[i] is the maximum sum sub-sequence ending at i
     * <p>
     * M[i] = { Max (a[i] , M[i+1] if i+k+1 >=N
     * *        Max ( a[i] + M[i+k+1] , M[i+1] if i+k+1<N
     * *            base
     * *            M[N-1] = A[N-1];
     * *        }
     * <p>
     * Ans: M[0]
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maximumSumSubsequenceAtLeastKDistantDP(int nums[], int k) {
        if (nums == null || nums.length == 0 || k == 0)
            return 0;
        int n = nums.length;
        int m[] = new int[n];

        //base
        m[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            if (i + k + 1 >= n) //Out of boundary
                m[i] = Math.max(nums[i], m[i + 1]);
            else //within boundary
                m[i] = Math.max(nums[i] + m[i + k + 1], m[i + 1]);
        }

        return m[0];
    }

}