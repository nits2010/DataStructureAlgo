package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.nonleetcode.PainterPartitionProblem;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-05
 * Description: https://leetcode.com/problems/split-array-largest-sum/
 * <p>
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 * <p>
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * Output:
 * 18
 * <p>
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * <p>
 * Similar to these
 * {@link PainterPartitionProblem}
 * {@link DataStructureAlgo.Java.companyWise.Google.ChocolateSweetnessChunk}
 */
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        SplitArrayLargestSumSolution solution = new SplitArrayLargestSumSolution();
        System.out.println(solution.splitArray(new int[]{7, 2, 5, 10, 8}, 2));
        System.out.println(solution.splitArray(new int[]{1, 2147483646}, 1));
        System.out.println(solution.splitArray(new int[]{1, 2147483646}, 2));
        System.out.println(solution.splitArray(new int[]{1, 2147483647}, 2));
        System.out.println(solution.splitArray(new int[]{2, 3, 1, 2, 4, 3}, 5));

    }

}

class SplitArrayLargestSumSolution {
    /**
     * splitArray(): The answer must lie somewhere in between {maximum item, sum of all items}.
     * We can use bracketing techniques to keep guessing and checking until we found the
     * final answer.
     */
    public int splitArray(int a[], int m) {
        if (a == null || a.length == 0 || m == 0)
            return 0;

        long[] lowHigh = maxSum(a);

        long low = lowHigh[0];
        long high = lowHigh[1];

        if (m == 1)
            return (int) high;

        if (m == a.length)
            return (int) low;


        while (low < high) {

            long mid = (low + high) >> 1;

            int requiredSubArrays = requiredSubArrays(a, m, mid);

            if (requiredSubArrays > m) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return (int) low;


    }

    /**
     * This will find out how many sub-array would require in order to have "sum" of each sub-array.
     *
     * @param a
     * @param m
     * @param sum
     * @return
     */
    private int requiredSubArrays(int[] a, int m, long sum) {

        int requiredSubArrays = 1; //as we always divide the given array at least in 1 sub-array so that it has the "sum". This is when "sum" is Sum of array
        long currentSubArraySum = 0; // What is the current sum of the current sub-array we are considering

        for (Integer x : a) {

            /**
             * If the current element it self is more then "sum" means for sure we'll require more sub-arrays then m
             */
            if (x > sum)
                return Integer.MAX_VALUE;

            currentSubArraySum += x; //Try to see can we add this element to make sum-of the sub-array as "sum"

            if (currentSubArraySum > sum) { //No by adding above element in this sub-array cause overflow of sum
                requiredSubArrays++; // means we need to start a new sub-array
                currentSubArraySum = x; // add this value in our array

                if (requiredSubArrays > m) //the number of sub-array required to make "sum" is more than we can divide
                    return requiredSubArrays;
            }
        }


        return requiredSubArrays;
    }

    private long[] maxSum(int a[]) {

        long max = Integer.MIN_VALUE;
        long sum = 0;

        for (int i = 0; i < a.length; i++) {
            sum += a[i];

            if (a[i] > max)
                max = a[i];
        }

        return new long[]{max, sum};

    }
}