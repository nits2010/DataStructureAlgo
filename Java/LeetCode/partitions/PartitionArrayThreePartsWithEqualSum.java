package Java.LeetCode.partitions;

import Java.HelpersToPrint.GenericPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-26
 * Description:https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
 * Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.
 * <p>
 * Formally, we can partition the array if we can find indexes i+1 < j with
 * (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [0,2,1,-6,6,-7,9,1,2,0,1]
 * Output: true
 * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * Example 2:
 * <p>
 * Input: [0,2,1,-6,6,7,9,-1,2,0,1]
 * Output: false
 * Example 3:
 * <p>
 * Input: [3,3,6,5,-2,2,5,1,-9,4]
 * Output: true
 * Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 */
public class PartitionArrayThreePartsWithEqualSum {
    public static void main(String[] args) {
        test(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1}, true);
        test(new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4}, true);
        test(new int[]{13, 3, 6, 5, -2, 2, 5, 1, -9, 4}, false);
        test(new int[]{13}, false);
        test(new int[]{13, 12}, false);
        test(new int[]{13, 13, 13}, true);
        test(new int[]{13, 13, 12, 1}, true);
        test(new int[]{3, 3, 6, -6, 6, 2, 5, 1, -9, 4}, false);
        test(new int[]{3, 3, 6, -6, 6, 12, 2, 2, 4, 4}, true);
        test(new int[]{6, 1, 1, 13, -1, 0, -10, 20}, true);

    }

    private static void test(int[] nums, boolean expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(nums) + " expected " + expected);
        PartitionArrayThreePartsWithEqualSumPrefixSumArray prefixSumArray = new PartitionArrayThreePartsWithEqualSumPrefixSumArray();
        PartitionArrayThreePartsWithEqualSumPrefixSumArrayOptimized prefixSumArrayOptimized = new PartitionArrayThreePartsWithEqualSumPrefixSumArrayOptimized();
        PartitionArrayThreePartsWithEqualSumIndex sumIndex = new PartitionArrayThreePartsWithEqualSumIndex();

        System.out.println("prefixSumArray :" + prefixSumArray.canThreePartsEqualSum(nums));
        System.out.println("prefixSumArrayOptimized :" + prefixSumArrayOptimized.canThreePartsEqualSum(nums));
        System.out.println("sumIndex  :" + sumIndex.canThreePartsEqualSum(nums));
    }


}

/**
 * {@link PartitionSetIntoTwoSubsetEqualSum}
 * WE can only divide the array in three different subarray if overall sum is dividable by 3.
 * If so, find the index where the each part sum would exist.
 * <p>
 * Algo:
 * 1. Find the first index (i) at which part 1 sum ended (i)
 * 2. Find the index after (i) at which part 2 sum ended (j)
 * if part 3 exist then j must not be n-1
 * <p>
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
 * Memory Usage: 50.1 MB, less than 100.00% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
 */
class PartitionArrayThreePartsWithEqualSumIndex {

    public boolean canThreePartsEqualSum(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length < 3)
            return false;

        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if (sum % 3 != 0)
            return false;

        int partSum = sum / 3;
        /**
         * Find the index at which the part 1 sum is ending
         */
        int part1EndingIndex = findSum(nums, partSum, 0, n - 1);

        if (part1EndingIndex == -1)
            return false;

        /**
         * Find the index at which the part 2 sum is ending after the part1EndingIndex
         */
        int part2EndingIndex = findSum(nums, partSum, part1EndingIndex + 1, n - 1);

        if (part2EndingIndex == -1 || part2EndingIndex == n-1)
            return false;

        return true;


    }

    private int findSum(int[] nums, int pSum, int s, int e) {

        for (int i = s; i <= e; i++) {
            pSum -= nums[i];

            if (pSum == 0)
                return i;
        }
        return -1;
    }

}

/**
 * this is similar to {@link Java.companyWise.Adobe.ElementThatHasSumOfLeftArrayEqualSumOfRight}
 * but we need to find two index(i,j) such that all elements on left side [0...i-1] = [i...j] = [j+1..n-1]
 * <p>
 * Brute force:
 * Consider each pair of (i,j) assuming it as second part, calculate the sum of [0...i-1] ,  [i...j],  & [j+1..n-1]
 * and check does this satisfy the condition.
 * complexity: for each trial of pair (i,j) will take O(n^2) time and for sum calculation of each part will take at max O(n) time
 * O(n^3) / O (1)
 * <p>
 * <p>
 * <p>
 * Optimized:
 * The main problem with above approach is to calculate the sum of [0...i-1] ,  [i...j],  & [j+1..n-1], if we can compute it in less than
 * O(n) time then we can reduce the complexity.
 * We can use prefixSum[i] {sum of element from 0..i} to compute the all three part sum in constant time.
 * <p>
 * Sum[0...i-1] = prefixSum[i-1]
 * Sum[i,j] =  prefixSum[j] - prefixSum[i]
 * Sum[j+1..n-1] = prefixSum[n-1] - prefixSum[j];
 * <p>
 * O(n^2) / O(n)
 * <p>
 * <p>
 * Very slow on OJ
 * Runtime: 782 ms, faster than 5.03% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
 * Memory Usage: 49.4 MB, less than 100.00% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
 */
class PartitionArrayThreePartsWithEqualSumPrefixSumArray {


    public boolean canThreePartsEqualSum(int[] nums) {

        if (nums == null || nums.length == 0 || nums.length < 3)
            return false;

        int n = nums.length;
        int prefixSum[] = new int[n];
        prefixSum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }


        /**
         * sXP is start index of first, second and third part
         * eXP is end index of first, second and third part
         */
        int eFp = 0;
        int sSp = 1;
        int sTp;

        while (sSp < n - 1) {

            int part1Sum = prefixSum[eFp];

            sTp = n - 1;


            while (sTp > sSp) {

                int part2Sum = prefixSum[sTp - 1] - prefixSum[eFp];
                int part3Sum = prefixSum[n - 1] - prefixSum[sTp - 1];

                /**
                 * if
                 * first part [startIndexFirstPart, startIndexSecondPart-1]
                 * second part [startIndexSecondPart, startIndexThirdPart-1]
                 * third part [startIndexThirdPart, n-1]
                 *
                 *
                 */
                if (part1Sum == part2Sum && part1Sum == part3Sum) {
//                    System.out.println(" P1 [ 0, " + eFp + "], P2 :[ " + sSp + "," + (sTp - 1) + "] p3 [ " + sTp + "," + (n - 1) + " ]");
                    return true;
                }

                /**
                 * Increase the window size of third part
                 */
                sTp--;

            }

            sSp++;
            eFp++;

        }

        return false;

    }
}


/**
 * In {@link PartitionArrayThreePartsWithEqualSumPrefixSumArray } the main problem is to find the starting index of third array
 * because of which we need to run another loop.
 * How we can reduce this loop?
 * Essentially we are looking for way that we don't need to try all the index for third array starting from n-1 to ending index of second array.
 * <p>
 * Suffix Sum array?
 * <p>
 * Sum of element from n-1 to 0 is called Suffix Sum Array.
 * <p>
 * *         Array: [0,  2,  1,  -6,   6,  -7,   9,  1,  2,  0,  1]
 * *       Prefix: [0,  2,  3,  -3,   3,  -4,   5,  6,  8,  8,  9]
 * *       Suffix: [9,  9,  7,   6,   12,  6,   13, 4,  3,  1,  1]
 * <p>
 * We can use suffix sum array to quickly find the sum of element from j+1 to n-1. "But still we don't know which j"
 * <p>
 * recall second solution of {@link Java.companyWise.Adobe.ElementThatHasSumOfLeftArrayEqualSumOfRight};
 * it essentially work on important concept of "look before".
 * <p>
 * We can apply same here. Build prefix sum or suffix sum.
 * for each sum check ; lets take prefix sum
 * 1. have you seen the same sum before ?
 * 2. if yes, than the index at which you see the sum before say 'i-1' and current sum at 'j+1'. if they are not equal then calculate the sum between [i,j] and check are they equal
 * <p>
 * Runtime: 16 ms, faster than 17.53% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
 * Memory Usage: 48.8 MB, less than 100.00% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
 */
class PartitionArrayThreePartsWithEqualSumPrefixSumArrayOptimized {
    public boolean canThreePartsEqualSum(int[] nums) {

        if (nums == null || nums.length == 0 || nums.length < 3)
            return false;

        int n = nums.length;
        int prefixSum[] = new int[n];
        prefixSum[0] = nums[0];

        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            map.putIfAbsent(prefixSum[i], i);
        }

        int part3Sum = 0;
        int i = n - 1;
        while (i > 0) {

            part3Sum += nums[i];


            final Integer index = map.get(part3Sum);
            if (index != null && index < i) {
                int part1Sum = prefixSum[index];
                int part2Sum = prefixSum[i - 1] - prefixSum[index];

                if (part1Sum == part2Sum && part1Sum == part3Sum) {
        //            System.out.println(" P1 [ 0, " + index + "], P2 :[ " + (index + 1) + "," + (i - 1) + "] p3 [ " + i + "," + (n - 1) + " ]");
                    return true;
                }

            }
            i--;


        }
        return false;

    }
}