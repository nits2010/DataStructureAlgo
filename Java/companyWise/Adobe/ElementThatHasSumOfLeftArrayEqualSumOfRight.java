package Java.companyWise.Adobe;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-26
 * Description: https://www.geeksforgeeks.org/find-element-array-sum-left-array-equal-sum-right-array/
 * Find an element in array such that sum of left array is equal to sum of right array
 * Given, an array of size n. Find an element which divides the array in two sub-arrays with equal sum.
 * <p>
 * Examples:
 * <p>
 * Input : 1 4 2 5
 * Output : 2
 * Explanation : If 2 is the partition,
 * subarrays are : {1, 4} and {5}
 * <p>
 * Input : 2 3 4 1 4 5
 * Output : 1
 * Explanation : If 1 is the partition,
 * Subarrays are : {2, 3, 4} and {4, 5}
 *
 *
 * <p>
 * [Adobe] [Amazon][Accolite][Zoho]
 */
public class ElementThatHasSumOfLeftArrayEqualSumOfRight {

    public static void main(String[] args) {
        test(new int[]{1, 4, 2, 5}, 2);
        test(new int[]{2, 3, 4, 1, 4, 5}, 3);
        test(new int[]{2, 3, 4, 9, 4, 5}, 3);
        test(new int[]{2, 11, 4, 9, 4, 5}, -1);
        test(new int[]{2, 0, 0, 0}, -1);
        test(new int[]{0, 0, 0, 2}, -1);
    }

    private static void test(int[] nums, int expected) {
        System.out.println("\nInput : " + Printer.toString(nums) + " expected :" + expected);
        System.out.println("Prefix/Suffix: Obtained :" + partitionIndexOfElementPrefixSuffixSum(nums));
        System.out.println("Constant: Obtained :" + partitionIndexOfElement(nums));
    }

    /**
     * To divide the array in two parts such that left and right part of it has sum equal.
     * *      1 4 2 5
     * sum=  1 5 7 12
     * It can be seen that element(2) at index 2 can divide the array.
     * <p>
     * Algorithm: We can do same what we did to find does any answer exist or not.
     * 1. Build a prefix array from left
     * 2. build a suffix array from right.
     * <p>
     * see if any element exist in both of the array which is same, the point where its same has left and right sum same.
     * *        1 4 2 5
     * * Lsum=  1 5 7 12
     * * Rsum=  12 11 7 5
     * <p>
     * At element 7 both are same in both array at same index. hence element index 2 is our answer
     * <p>
     * importantly; element at index 0 or index n-1 can not be the parition point as sum of left and right are not found
     * <p>
     * O(n)/O(n)
     *
     * @param nums
     * @return
     */
    private static int partitionIndexOfElementPrefixSuffixSum(int nums[]) {

        if (nums == null || nums.length == 0 || nums.length == 1)
            return -1;

        int n = nums.length;
        int prefixSum[] = new int[n];
        int suffixSum[] = new int[n];

        prefixSum[0] = nums[0];
        suffixSum[n - 1] = nums[n - 1];

        for (int i = 1, j = n - 2; i < n; i++, j--) {

            prefixSum[i] = prefixSum[i - 1] + nums[i];

            suffixSum[j] = suffixSum[j + 1] + nums[j];

        }

        for (int i = 1; i < n - 1; i++)
            if (prefixSum[i] == suffixSum[i])
                return i;

        return -1;
    }

    /**
     * We can solve this problem in constant space it self.
     * Observe in the above example:
     * *        1 4 2 5
     * * Lsum=  1 5 7 12
     * * Rsum=  12 11 7 5
     * <p>
     * The element 2 is only be can a partition point if and only if the sum of left of 2 and sum of right of 2 is equal.
     * <p>
     * As the partition point can be anywhere in the array, what we can do is assume each element as potential partition point
     * To assume element at index 'i' is your partition point then we must have sum of left element of i {0...i-1}
     * as well as sum of right element of i i.e. {i+1...n-1}
     * <p>
     * One way would be calculate the sum for each partition point just like brute force approach but that will make its O(n^2).
     * So we need to find a way that we can compute the left and right sum in constant time.
     * <p>
     * For example :
     * 1,4,2,5
     * <p>
     * initially assume the 1(ind=0) is your partition point then sum of left is 0 {no element on left side}
     * and sum of right is 11{4+2+5}. Sum of right element at this point is nothing but sum of 'all element in the array - element at partition point'.
     * Say Total Sum = Rsum
     * Then sum of right element at index 0 is Rsum - num[0], where as the left element Lsum = 0
     * <p>
     * to assume next element 4(ind=1) to be a partition point, we can easily compute left sum as Lsum+nums[i-1] and right sum as Rsum - nums[1].
     * <p>
     * 1,4,2,5
     * Total sum = rSum =  12
     * <p>
     * i = 0 as partition point
     * lSum = 0, rSum = 12-1 =11 {lSum!=rSum}
     * <p>
     * i =1 as partition point
     * lSum = lSum + num[i-1] ; rSum = rSum - nums[i]
     * lSum = 1, rSum = 7 {lSum!=rSum}
     * <p>
     * i =2 as partition point
     * lSum = 1 + 4 = 5
     * rSum = 7 - 2 = 5
     * lSum == rSum
     * Hence index i is partition point
     * <p>
     * importantly; element at index 0 or index n-1 can not be the parition point as sum of left and right are not found
     * <p>
     * O(n)/O(1)
     *
     * @param nums
     * @return
     */
    private static int partitionIndexOfElement(int nums[]) {
        if (nums == null || nums.length == 0 || nums.length == 1)
            return -1;

        int n = nums.length;

        int rSum = Arrays.stream(nums).sum() - nums[0]; //As index=0 can not be the partition index
        int lSum = 0;

        int partitionIndex = 1;
        /**
         * Assume index = 1 is your partition point
         * Add elements before partition point as lSum
         * remove parition point element from rSum.
         *
         */
        lSum = nums[partitionIndex - 1];
        rSum -= nums[partitionIndex];


        while (partitionIndex < n - 1) { //As index=n-1 can not be the partition index
            if (lSum == rSum)
                return partitionIndex;

            partitionIndex++;
            lSum += nums[partitionIndex - 1];
            rSum -= nums[partitionIndex];
        }


        return -1;
    }
}

