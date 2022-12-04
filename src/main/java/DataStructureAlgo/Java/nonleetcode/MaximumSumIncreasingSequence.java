package DataStructureAlgo.Java.nonleetcode;

/**
 * Author: Nitin Gupta
 * Date: 11/12/18
 * Description: https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/
 * Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array
 * such that the integers in the subsequence are sorted in increasing order. For example, if input is {1, 101, 2, 3, 100, 4, 5},
 * then output should be 106 (1 + 2 + 3 + 100), if the input array is {3, 4, 5, 10},
 * then output should be 22 (3 + 4 + 5 + 10) and if the input array is {10, 5, 4, 3}, then output should be 10
 */
public class MaximumSumIncreasingSequence {

    /**
     * similar to {@link LongestIncreasingSubSequence}
     *
     * @param arr
     * @return
     */
    static int maximumSumIncreasingSubSequence(int arr[]) {

        /**
         * Lets M[i] defined the maximum sum from arr through 1 to i;
         *
         * M[i] =  Max { M[j] + A[i] } ; 0<=j<=i     if A[i] > A[j]
         *          A[i]  for every i;
         *
         * Max { M[j] + A[i] } => We try to find the maximum sum of all the previous
         *          solution from 0 to i such that A[i] > A[j] making increasing sub-Sequence
         *          including this element
         *
         *  Max of M[i] is output
         */

        int length = arr.length;

        int mSISS[] = new int[length];

        int max;
        int msiss = Integer.MIN_VALUE;

        for (int i = 0; i < length; i++) {

            //Since every element make increasing sub-sequence of maximum sum "it-self"
            mSISS[i] = arr[i];

            max = 0;
            for (int j = 0; j < i; j++) {

                if (arr[i] > arr[j]) {

                    max = Math.max(max, mSISS[j]);
                }
            }
            mSISS[i] = max + arr[i];
            msiss = Math.max(msiss, mSISS[i]);
        }


        return msiss;
    }

    public static void main(String[] args) {

        int arr[] = {1, 101, 2, 3, 100, 4, 5};

        int result = maximumSumIncreasingSubSequence(arr);
        System.out.println(result);
        System.out.println(result == 106);
    }
}
