package Java.companyWise.facebook;

import Java.HelpersToPrint.HelperToPrint;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-24
 * Description: https://www.geeksforgeeks.org/largest-sum-subarray-least-k-numbers/
 * <p>
 * Given an array, find the subarray (containing at least k numbers) which has the largest sum.
 * <p>
 * Examples:
 * <p>
 * Input : arr[] = {-4, -2, 1, -3}
 * k = 2
 * Output : -1
 * The sub array is {-2, 1}
 * <p>
 * Input : arr[] = {1, 1, 1, 1, 1, 1}
 * k = 2
 * Output : 6
 * The sub array is {1, 1, 1, 1, 1, 1}
 *
 * [FACEBOOK]
 */
public class MaximumSumSubArrayAtLeastKElements {

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{1, 2, 3, -10, -3}, 4));
        System.out.println(maxSum(new int[]{-4, -2, 1, -3}, 2));
        System.out.println(maxSum(new int[]{1, 1, 1, 1, 1, 1}, 2));
    }

    static int maxSum(int num[], int k) {
        if (num == null || num.length == 0)
            return 0;
        HelperToPrint.printArray(num);

        int max = Integer.MIN_VALUE;

        /**
         * Denotes the maximum sum at index i of sub-array 0..i
         */
        int maxSum[] = new int[num.length];

        int sumOfK = num[0];
        int currentMax = num[0];
        maxSum[0] = num[0];

        //Find maximum sum at index i
        for (int i = 1; i < num.length; i++) {
            currentMax = Math.max(currentMax + num[i], num[i]);
            maxSum[i] = currentMax;

            if (i < k)
                sumOfK += num[i];

        }
        if (sumOfK > max)
            max = sumOfK;

        for (int i = k; i < num.length; i++) {


            sumOfK += num[i] - num[i - k];

            //Is this max sum of current window
            if (sumOfK > max)
                max = sumOfK;

            //add previous window MAX sum
            if (sumOfK + maxSum[i - k] > max)
                max = sumOfK + maxSum[i - k];

        }

        return max;
    }
}

