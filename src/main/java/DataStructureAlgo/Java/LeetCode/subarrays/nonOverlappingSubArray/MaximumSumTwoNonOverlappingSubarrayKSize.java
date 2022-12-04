package DataStructureAlgo.Java.LeetCode.subarrays.nonOverlappingSubArray;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-09
 * Description: https://www.geeksforgeeks.org/maximum-sum-two-non-overlapping-subarrays-of-given-size/
 * Given an array, we need to find two subarrays with a specific length K such that sum of these subarrays is maximum among all possible choices of subarrays.
 * Examples:
 * <p>
 * Input : arr[] = [2, 5, 1, 2, 7, 3, 0]
 * K = 2
 * Output : 2 5
 * 7 3
 * We can choose two arrays of maximum sum
 * as [2, 5] and [7, 3], the sum of these two
 * subarrays is maximum among all possible
 * choices of subarrays of length 2.
 * <p>
 * Input : arr[] = {10, 1, 3, 15, 30, 40, 4, 50, 2, 1}
 * K = 3
 * Output : 3 15 30
 * 40 4 50
 */
public class MaximumSumTwoNonOverlappingSubarrayKSize {

    public static void main(String[] args) {
        test(new int[]{2, 5, 1, 2, 7, 3, 0}, 2, 17);
        test(new int[]{10, 1, 3, 15, 30, 40, 4, 50, 2, 1}, 3, 142);
        test(new int[]{10, 1, 3, 15, 30, 40, 4, 50, 2, 1}, 1, 90);
        test(new int[]{10, 1, 3, 15, 30, 40, 4, 50, 2, 1}, 2, 124);
    }

    private static void test(int[] nums, int k, int expected) {
        GenericPrinter.print(nums);
        System.out.println(" for k :" + k + " obtained :" + maximumSumNonOverlappingKSize(nums, k) + " expected :" + expected);
    }

    public static int maximumSumNonOverlappingKSize(int nums[], int k) {
        return maximumSum(nums, k, k);
    }

    /**
     * similar {@link MaximumSumTwoNonOverlappingSubarrays}
     *
     * @param nums
     * @return
     */
    public static int maximumSum(int nums[], int l, int m) {
        if (nums == null || nums.length == 0 || nums.length < l + m)
            return -1;


        int n = nums.length;

        int lPreSum[] = new int[n];
        int mPreSum[] = new int[n];


        lPreSum[0] = nums[0];
        int lSum = nums[0];
        mPreSum[n - 1] = 0;// important 
        int mSum = 0;


        for (int i = 1, j = n - 2; i < n && j >= 0; i++, --j) {

            lSum += nums[i];

            if (i >= l)
                lSum -= nums[i - l];

            lPreSum[i] = Math.max(lPreSum[i - 1], lSum);

            mSum += nums[j + 1];

            if (j + m + 1 < n)
                mSum -= nums[j + m + 1];

            mPreSum[j] = Math.max(mPreSum[j + 1], mSum);


        }

        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, lPreSum[i] + mPreSum[i]);

        return max;
    }
}
