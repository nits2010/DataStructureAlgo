package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-06
 * Description: https://www.geeksforgeeks.org/maximum-absolute-difference-between-sum-of-two-contiguous-sub-arrays/
 * Maximum absolute difference between sum of two contiguous sub-arrays
 * Given an array of integers, find two non-overlapping contiguous sub-arrays such that the absolute difference between the sum of two sub-arrays is maximum.
 * <p>
 * Example:
 * <p>
 * Input: [-2, -3, 4, -1, -2, 1, 5, -3]
 * Output: 12
 * Two subarrays are [-2, -3] and [4, -1, -2, 1, 5]
 * <p>
 * Input: [2, -1, -2, 1, -4, 2, 8]
 * Output: 16
 * Two subarrays are [-1, -2, 1, -4] and [2, 8]
 */
public class MaximumAbsoluteDifferenceTwoContigeousSubArrays {

    public static void main(String args[]) {
        int i1[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        int i2[] = {2, -1, -2, 1, -4, 2, 8};

        SolutionMaximumAbsoluteDifferenceTwoContigeousSubArrays sol= new SolutionMaximumAbsoluteDifferenceTwoContigeousSubArrays();

        System.out.println(sol.maximumAbsoluteDifferenceTwoContigeousSubArrays(i1));
        System.out.println(sol.maximumAbsoluteDifferenceTwoContigeousSubArrays(i2));

    }
}

class SolutionMaximumAbsoluteDifferenceTwoContigeousSubArrays {

    public int maximumAbsoluteDifferenceTwoContigeousSubArrays(int a[]) {

        if (a == null || a.length == 0)
            return Integer.MIN_VALUE;

        int n = a.length;

        int leftMax[] = maximumSumSubArrayKadensFromLeft(a);
        int rightMax[] = maximumSumSubArrayKadensFromRight(a);

        invert(a);

        int leftMin[] = maximumSumSubArrayKadensFromLeft(a);
        invert(leftMin);

        int rightMin[] = maximumSumSubArrayKadensFromRight(a);
        invert(rightMin);

        invert(a);


        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n-1; i++) {

            max = Math.max(max, Math.max(Math.abs(leftMax[i] - rightMin[i]), Math.abs(leftMin[i] - rightMax[i + 1])));
        }


        return max;
    }

    private void invert(int a[]) {
        for (int i = 0; i < a.length; i++)
            a[i] = -a[i];
    }

    private int[] maximumSumSubArrayKadensFromLeft(int a[]) {
        int n = a.length;
        int res[] = new int[n];


        int currentSum = a[0];
        int maxSum = a[0];

        for (int i = 1; i < n; i++) {

            currentSum = Math.max(a[i], currentSum + a[i]);
            maxSum = Math.max(maxSum, currentSum);
            res[i] = maxSum;


        }
        return res;

    }

    private int[] maximumSumSubArrayKadensFromRight(int a[]) {
        int n = a.length;
        int res[] = new int[n];


        int currentSum = a[n - 1];
        int maxSum = a[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            currentSum = Math.max(a[i], currentSum + a[i]);
            maxSum = Math.max(maxSum, currentSum);
            res[i] = maxSum;


        }
        return res;

    }


}