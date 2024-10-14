package DataStructureAlgo.Java.LeetCode.subarrays.nonOverlappingSubArray;

import DataStructureAlgo.Java.LeetCode.stockPrices.MaxProfitTwoTransaction__BestTimeToBuySellStockIII;
import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-09
 * Description: https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/
 * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
 * <p>
 * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
 * <p>
 * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
 * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 * Example 2:
 * <p>
 * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
 * Output: 29
 * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 * Example 3:
 * <p>
 * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
 * Output: 31
 * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 * <p>
 * <p>
 * Note:
 * <p>
 * L >= 1
 * M >= 1
 * L + M <= A.length <= 1000
 * 0 <= A[i] <= 1000
 */
public class MaximumSumTwoNonOverlappingSubarrays {

    public static void main(String[] args) {
        test(new int[]{0, 6, 5, 2, 2, 5, 1, 9, 4}, 1, 2, 20);
        test(new int[]{3, 8, 1, 3, 2, 1, 8, 9, 0}, 3, 2, 29);
        test(new int[]{3, 8, 1, 4, 2, 7, 22, 9, 5}, 3, 2, 49);
        test(new int[]{2, 1, 5, 6, 0, 9, 5, 0, 3, 8}, 4, 3, 31);
    }

    private static void test(int[] nums, int L, int M, int expected) {
        CommonMethods.print(nums);
        MaximumSumTwoNonOverlapUsingBuySellStocksIdea buySellStocksIdea = new MaximumSumTwoNonOverlapUsingBuySellStocksIdea();
        MaximumSumTwoNonOverlapDP dp = new MaximumSumTwoNonOverlapDP();
        System.out.println(" L : " + L + " M :" + M + " obtained :" + buySellStocksIdea.maxSumTwoNoOverlap(nums, L, M) + " Expected :" + expected);
        System.out.println(" L : " + L + " M :" + M + " obtained :" + dp.maxSumTwoNoOverlap(nums, L, M) + " Expected :" + expected);
    }

    /**
     * This is really a mind twister for me at least.
     * the biggest problem here is "L" and "M" can be interchange in terms of sub-array position.
     * <p>
     * Let's for a while forget about their interchangeability, as we can always run algorithm bi-directional [2's ].
     * <p>
     * Now Lets first concentrate what would be the outcome when L comes first then M.
     * [0,6,5,2,2,5,1,9,4]
     * <p>
     * * if L array comes first of "L" size, we can simply build a pre-sum array using which we can easily find what is the sum of the current sub-array of L size.
     * * Similarly we can build the "M" size array from Back using pre-sum ( from back ).
     * * Since we need to make sure that choosing any them should not be overlap.
     * * as It can be seen that by building pre-Sum for L (from Left) and pre-Sum for M (from back) will make sure that at any index i, the value at LpreSum is a sub-array which is
     * not overlapping with a sub-array given by Mpre-Sum, assuming right boundary as 0.
     * <p>
     * Now, Once we know that, we can easily build the final sum, by joining both arrays.
     * <p>
     * Do it similarly for M come first then L. finally take the maximum of both.
     * <p>
     * If you look closely, it is similar to Buy-Sell stocks two times {@link MaxProfitTwoTransaction__BestTimeToBuySellStockIII}
     * Here we just change how we build the array.
     * <p>
     * Example:  [0,6,5,2,2,5,1,9,4] L=1, M=2
     * When L First and M next
     * LPreSum[i] =  Max ( LpreSum[i-1], LpreSum[i] - LPreSum[i-L] )
     * * [ 0 , 6, 6, 6 , 6, 6, 6, 9 , 9 ] L=1
     * *
     * MPreSum[i] =  Max ( MPreSum[i+1], MPreSum[i] - MPreSum[i+M] )
     * * [ 13 ,13, 13 , 13, 13 , 13, 13 , 4, 0 ] M=2
     * <p>
     * Max = 19
     * <p>
     * When M First and L next
     * MPreSum[i] =  Max ( MPreSum[i+1], MPreSum[i] - MPreSum[i+L] )
     * * [ 0 , 6, 11, 11 , 11, 11, 11, 11 , 13 ] M=2
     * *
     * LPreSum[i] =  Max ( LPreSum[i-1], LPreSum[i] - LPreSum[i-M] )
     * * [ 9 ,9, 9 , 9, 9 , 9, 9 , 4, 0 ] L=1
     * *
     * Max = 20
     * <p>
     * Overall Max = 20
     *
     * @return Runtime: 2 ms, faster than 46.94% of Java online submissions for Maximum Sum of Two Non-Overlapping Subarrays.
     * Memory Usage: 38.6 MB, less than 8.70% of Java online submissions for Maximum Sum of Two Non-Overlapping Subarrays.
     * <p>
     * https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/278727/C%2B%2B-O(N)-buysell-stock-2-times
     */

    static class MaximumSumTwoNonOverlapUsingBuySellStocksIdea {

        public static int maxSumTwoNoOverlap(int[] nums, int L, int M) {

            if (null == nums || nums.length == 0 || nums.length < L + M)
                return -1;


            return Math.max(maxSum(nums, L, M), maxSum(nums, M, L));

        }


        private static int maxSum(int nums[], int L, int M) {

            int max = 0;
            int n = nums.length;
            int LPreSum[] = new int[n];
            int MPreSum[] = new int[n];


            int leftSum = nums[0];
            LPreSum[0] = nums[0];


            int rightSum = 0;
            MPreSum[n - 1] = 0; //right boundary as 0

            for (int i = 1, j = n - 2; i < n && j >= 0; i++, j--) {


                leftSum += nums[i];


                if (i >= L)
                    leftSum -= nums[i - L];

                LPreSum[i] = Math.max(LPreSum[i - 1], leftSum);


                rightSum += nums[j + 1];
                if (j + M + 1 < n) //outside of M window
                    rightSum -= nums[j + M + 1];

                MPreSum[j] = Math.max(MPreSum[j + 1], rightSum);

            }

            for (int i = 0; i < n; i++)
                max = Math.max(max, LPreSum[i] + MPreSum[i]);

            return max;
        }

    }


    /**
     * See word file Or https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/355352/Full-Explanation-and-idea-formation.-JAVA-beat-99
     * Runtime: 1 ms, faster than 94.62% of Java online submissions for Maximum Sum of Two Non-Overlapping Subarrays.
     * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Maximum Sum of Two Non-Overlapping Subarrays.
     */
    static class MaximumSumTwoNonOverlapDP {
        public static int maxSumTwoNoOverlap(int[] nums, int L, int M) {

            if (null == nums || nums.length == 0 || nums.length < L + M)
                return -1;


            return maxSum(nums, L, M);

        }


        private static int maxSum(int[] nums, int L, int M) {

            System.out.println("........................................");
            for (int i = 1; i < nums.length; ++i)
                nums[i] += nums[i - 1];

            CommonMethods.print(nums);
            System.out.println("........................................");

            int res = nums[L + M - 1], Lmax = nums[L - 1], Mmax = nums[M - 1];
            System.out.println(" res :" + res + " LMax :" + Lmax + " MMax : " + Mmax);

            for (int i = L + M; i < nums.length; ++i) {
                System.out.println(" i " + i);

                //Lmax is the case when L contiguous elements are taken first
                Lmax = Math.max(Lmax, nums[i - M] - nums[i - L - M]);
                //System.out.println(" L : " + Lmax + " nums[i - M ]" + nums[i - M] + " -  nums[i - L - M] " + nums[i - L - M]);

                //Mmax is the case when M contiguous elements are taken first
                Mmax = Math.max(Mmax, nums[i - L] - nums[i - L - M]);
                //System.out.println(" M : " + Mmax + " nums[i - L] " + nums[i - L] + " -  nums[i - L - M] " + nums[i - L - M]);

                int lm = Lmax + nums[i] - nums[i - M];
                //System.out.println(" X :" + lm + " nums[i] " + nums[i] + " -nums[i - M]: " + nums[i - M]);
                int ml = Mmax + nums[i] - nums[i - L];
                //System.out.println(" Y :" + ml + " nums[i] " + nums[i] + " -nums[i - L]: " + nums[i - L]);

                res = Math.max(res, Math.max(lm, ml));
                //System.out.println(" res :" + res + " LMax :" + Lmax + " MMax : " + Mmax + " \n");
            }
            return res;
        }

    }
}

