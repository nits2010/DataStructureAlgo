package Java.companyWise.Amazon;

import Java.HelpersToPrint.GenericPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-24
 * Description: https://www.geeksforgeeks.org/maximum-number-chocolates-distributed-equally-among-k-students/
 * Maximum number of chocolates to be distributed equally among k students
 * Given n boxes containing some chocolates arranged in a row. There are k number of students.
 * <p>
 * The problem is to distribute maximum number of chocolates equally among k students by selecting a consecutive sequence of
 * boxes from the given lot. Consider the boxes are arranged in a row with numbers from 1 to n from left to right. We have to
 * select a group of boxes which are in consecutive order that could provide maximum number of chocolates equally to all the k students.
 * An array arr[] is given representing the row arrangement of the boxes and arr[i] represents number of chocolates in that box at
 * position ‘i’.
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = {2, 7, 6, 1, 4, 5}, k = 3
 * Output : 6
 * The subarray is {7, 6, 1, 4} with sum 18.
 * Equal distribution of 18 chocolates among
 * 3 students is 6.
 * Note that the selected boxes are in consecutive order
 * with indexes {1, 2, 3, 4}.
 * Source: Asked in [Amazon].
 * <p>
 * {@link Java.LeetCode.sumsubarrayproblems.LongestSubArraySumK}
 */
public class MaximumNumberChocolatesDistributedEquallyAmongKStudents {

    public static void main(String[] args) {
        test(new int[]{2, 7, 6, 1, 4, 5}, 3, 6);
        test(new int[]{2, 7, 8, 11, 4, 15}, 3, 15);
    }

    private static void test(int[] chocolates, int k, int expected) {
        System.out.println("Chocolates :" + GenericPrinter.toString(chocolates) + " students :" + k);
        System.out.println("Max  :" + maximumNumberOfChocolates(chocolates, k) + " expected :" + expected);
    }

    /**
     * There following important points to remember
     * 1. Distribute chocolates equally among k students : We need to give equal number of chocolates to each students
     * 2. selecting a consecutive sequence of boxes from the given lot : Chocolate that we distribute should come in consecutive sequence only ; SUB-ARRAY
     * 3. distribute maximum number of chocolates : We need to distribute maximum number of chocolates to students
     * <p>
     * i.e. We need to find a sub-array which has maximum number of chocolates and could be divided in students equally.
     * <p>
     * To divide them equally; we should have chocolates in multiple of students only; i.e. chocolates % students = 0
     * Since array elements represent the number of chocolates of that kind, than sum of array elements in a sub-array would be the maximum number of chocolate to distribute.
     * <p>
     * <p>
     * <p>
     * Since we need to maximize; then if
     * Sum of array elements is % students = then this is the maximum number of chocolates (sum of array)
     * Otherwise
     * We need to find a sub-array which has maximum sum and sum % students = 0
     * <p>
     * Algo:
     * Brute Force:
     * Find the sum of all the sub-array and choose the sub-array which follow the property; sum % k == 0
     * O(n^3)
     * <p>
     * Optimized:
     * To find the sum efficiently of a sub-array we can use prefix-sum table
     * prefixSum[i] represent the sum of 0 to ith element (including ith element)
     * to find the sum of j...i we can have it by prefixSum[i] - prefixSum[j]
     * <p>
     * Algo:
     * 1. Calculate prefixSumArray
     * 2. As soon as we find prefixSum[i] % k == 0; then we can update our maxSum; since prefixSum[i] denotes the sum of 0..i elements
     * 3. But our sub-array can be start from any other index j too. To find the sub-array [j...i] we need to know did we saw same reminder earlier too at some index j-1
     * s.t. prefixSum[j-1] % k == r and prefixSum[i] % k ==r; then prefixSum[j...i]  % k == 0.
     * Since we need to maximize the sum, we'll store the first index of a reminder only so that sub-array size could be maximum
     *
     * @param chocolates
     * @param students
     * @return
     */
    private static int maximumNumberOfChocolates(int chocolates[], int students) {
        if (chocolates == null || chocolates.length == 0)
            return 0;


        final Map<Integer, Integer> reminderMap = new HashMap<>();
        final int prefixSum[] = new int[chocolates.length];


        /**
         * Build prefix sum array
         */
        prefixSum[0] = chocolates[0];
        for (int i = 1; i < chocolates.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + chocolates[i];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < chocolates.length; i++) {

            if (prefixSum[i] % students == 0) {
                maxSum = Math.max(maxSum, prefixSum[i]);
            } else {

                int r = prefixSum[i] % students;

                if (reminderMap.containsKey(r)) {
                    int sum = prefixSum[i] - prefixSum[reminderMap.get(r)];

                    if (sum % students == 0)
                        maxSum = Math.max(maxSum, sum);
                } else
                    reminderMap.put(r, i);
            }

        }


        return maxSum / students;

    }
}
