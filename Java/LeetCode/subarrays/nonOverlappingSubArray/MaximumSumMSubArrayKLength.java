package Java.LeetCode.subarrays.nonOverlappingSubArray;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-14
 * Description:
 * {@link MaximumSumTwoNonOverlappingSubarrays}
 * {@link MaximumSumThreeNonOverlappingSubarrays}
 * <p>
 * Generalization of
 * {@link MaximumSumTwoNonOverlappingSubarrayKSize}
 * <p>
 * https://www.geeksforgeeks.org/max-sum-of-m-non-overlapping-subarrays-of-size-k/
 * Given an array, Given an array and two numbers M and K. We need to find sum of max M subarrays of size K (non-overlapping) in the array. (Order of array remains unchanged). K is the size of subarrays and M is the count of subarray. It may be assumed that size of array is more than m*k. If total array size is not multiple of k, then we can take partial last array.
 * <p>
 * Examples :
 * <p>
 * Input: N = 7, M = 3, K = 1
 * arr[] = {2, 10, 7, 18, 5, 33, 0};
 * Output: 61
 * Explanation: subsets are: 33, 18, 10 (3 subsets of size 1)
 * <p>
 * Input: N = 4, M = 2, K = 2
 * arr[] = {3, 2, 100, 1};
 * Output:  106
 * Explanation: subsets are: (3, 2), (100, 1) 2 subsets of size 2
 */
public class MaximumSumMSubArrayKLength {

    public static void main(String[] args) {
        test(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 3, 2, 23);
        test(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 4, 2, 25);
        test(new int[]{2, 10, 7, 18, 5, 33, 0}, 3, 1, 61);
        test(new int[]{3, 2, 100, 1}, 2, 2, 106);
    }

    private static void test(int[] nums, int m, int k, int expected) {
        System.out.println("Input :" + GenericPrinter.toString(nums) + " for m: " + m + " k: " + k);


        MaximumSumMSubArrayKLengthRecursive recursive = new MaximumSumMSubArrayKLengthRecursive();
        MaximumSumMSubArrayKLengthDP dp = new MaximumSumMSubArrayKLengthDP();

        System.out.println("Recursive -> Obtained :" + recursive.maximumSum(nums, m, k) + " expected :" + expected);
        System.out.println("DP -> Obtained :" + dp.maximumSum(nums, m, k) + " expected :" + expected);
        System.out.println("DP -> index :" + GenericPrinter.toString(dp.maximumSumWithIndex(nums, m, k)) + " expected :" + expected);
    }
}


/**
 * https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/358634/Java-or-3-sub-array-or-n-sub-Array-or-Generic-or-Full-explanation-or-easy-to-understand
 * Idea through
 * {@link MaximumSumThreeNonOverlappingSubarrays}
 * <p>
 * <p>
 * We need select M sub-array of size k,
 * As we did in three sub-array of size k, we find all the left sub-array and right sub-array and try to find the optimal
 * middle sub-array by choosing different left and right sub-array.
 * <p>
 * But since here we could have X number of left sub-array , Y number of middle sub-array and Z number of right sub-array.
 * <p>
 * In order to maximize the sum, we need to have Xi + Yj + Zk as maximum. Where Xi denote the Maximum sum of Left sub-array at index i.
 * <p>
 * <p>
 * Clearly, if we know the solution for n element with p-1 sub-array of k size, we can find for p+1 using previous solution such that we include one more sub-array.
 * <p>
 * For having m sub-array of k length, the size of array should be n >= m*k.
 * <p>
 * Lets say
 * dp[i][j] is holding the maximum sum that is possible with i sub-arrays upto j'th index.
 * <p>
 * There are two possibilities,
 * 1. either we add a new sub-array with previous solution of i-1 sub-array
 * 2. Or we don't add [ may be adding this sub-array reduce the overall sum] then we'll take the solution without including the current element j hence i,j-1
 * <p>
 * Hence
 * dp[i][j] = Max { dp[i][j-1] , sum-by-including }.
 * <p>
 * Now we need to find a way to calculate 'sum-by-including'.
 * <p>
 * We need to define it using dp[i-1][...].
 * Since at index 'j' we are saying that this sub-array we are going to choose which start from 'j' index in input sub-array. Assume this is rightmost sub-array which we are going to include.
 * Now we need to have
 * 1. Efficient way to calculate sum of sub-array start from j'th index [ we can use pre-sum s.t.  sum[j] - sum[j-k].
 * 2. The maximum sum that could be obtained by i-1 sub-array would be located at dp[i-1][j] but as we should choose those sub-arrays only which are  'non-Overlapping'.
 * So that sub-array would be at dp[i-1][j-k].
 * <p>
 * Hence
 * ------------------------------------------------------------------------
 * dp[i][j] = Max { dp[i][j-1] , dp[i-1][j-k] + sum[j] - sum[j-k]}, Otherwise
 * Clearly j should start from i*k as we need to have at least some space for previous sub array otherwise j-k would be negative.
 * <p>
 * our answer would be when we test all the element with all the sub-array hence dp[m][n]
 * <p>
 * Base case:
 * dp[0][0...n] = 0 as with 0 sub-array its not possible to have any sum
 * <p>
 * memory: dp[m+1][n+1]
 * ------------------------------------------------------------------------
 */
class MaximumSumMSubArrayKLengthDP {

    public int maximumSum(int nums[], int m, int k) {
        if (nums == null || nums.length == 0 || m == 0 || k == 0)
            return 0;

        int n = nums.length;

        final int dp[][] = new int[m + 1][n + 1];
        final int index[][] = new int[m + 1][n + 1];

        int sum[] = preSum(nums);


        /**
         * i -> represent the sub-array
         * j -> index of the array element
         */
        for (int i = 1; i <= m; i++) {

            for (int j = i * k; j <= n; j++) {

                int current = dp[i - 1][j - k] + sum[j] - sum[j - k];

                if (current > dp[i][j - 1]) {
                    dp[i][j] = current;
                    index[i][j] = j - k;
                } else {
                    dp[i][j] = dp[i][j - 1];
                    index[i][j] = index[i][j - 1];
                }


            }

        }

        return dp[m][n];

    }


    private int[] preSum(int nums[]) {
        int preSum[] = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++)
            preSum[i] = preSum[i - 1] + nums[i - 1];

        return preSum;


    }


    public int[] maximumSumWithIndex(int nums[], int m, int k) {
        if (nums == null || nums.length == 0 || m == 0 || k == 0)
            return null;

        int n = nums.length;

        final int dp[][] = new int[m + 1][n + 1];
        final int index[][] = new int[m + 1][n + 1];

        int sum[] = preSum(nums);


        /**
         * i -> represent the sub-array
         * j -> index of the array element
         */
        for (int i = 1; i <= m; i++) {

            for (int j = i * k; j <= n; j++) {

                int current = dp[i - 1][j - k] + sum[j] - sum[j - k];

                if (current > dp[i][j - 1]) {
                    dp[i][j] = current;
                    index[i][j] = j - k;
                } else {
                    dp[i][j] = dp[i][j - 1];
                    index[i][j] = index[i][j - 1];
                }


            }

        }


        int subArrayIndex[] = new int[m];
        subArrayIndex[m - 1] = index[m][n];

        for (int i = m - 2; i >= 0; i--) {
            subArrayIndex[i] = index[i + 1][subArrayIndex[i + 1]];
        }

        System.out.println(GenericPrinter.toString(dp));
        System.out.println(GenericPrinter.toString(index));

        return subArrayIndex;

    }
}


class MaximumSumMSubArrayKLengthRecursive {


    public int maximumSum(int nums[], int m, int k) {

        if (nums == null || nums.length == 0 || m == 0 || k == 0)
            return 0;


        int preSum[] = getPreSum(nums, k);

        return maximumSum(m, k, nums.length - k + 1, preSum, 0);

    }

    private int[] getPreSum(int[] nums, int k) {

        int presum[] = new int[nums.length + 1 - k];
        for (int i = 0; i < k; i++)
            presum[0] += nums[i];

        // store sum of array index i to i+k
        // in presum array at index i of it.
        for (int i = 1; i <= nums.length - k; i++)
            presum[i] += presum[i - 1] + nums[i + k - 1] -
                    nums[i - 1];

        return presum;
    }


    /**
     * Here we can see that the the we need to find M subarrays each of size K so,
     * 1. We create a presum array, which contains in each index sum of all elements from ‘index‘ to ‘index + K’ in the given array. And size of the sum array will be n+1-k.
     * 2. Now if we include the subarray of size k, then we can not include any of the elements of that subarray again in any other subarray as it will create overlapping subarrays. So we make recursive call by excluding the k elements of included subarray.
     * 3. if we exclude a subarray then we can use the next k-1 elements of that subarray in other subarrays so we will make recursive call by just excluding the first element of that subarray.
     * 4. At last return the max(included sum, excluded sum).
     *
     * @param m
     * @param k
     * @param preSum
     * @return
     */
    private int maximumSum(int m, int k, int size, int[] preSum, int i) {

        /**
         * No more sub-array to choose
         */
        if (m == 0)
            return 0;

        if (i > size - 1)
            return 0;


        int include = preSum[i] + maximumSum(m - 1, k, size, preSum, i + k);
        int exclude = maximumSum(m, k, size, preSum, i + 1);
        return Math.max(include, exclude);

    }

}