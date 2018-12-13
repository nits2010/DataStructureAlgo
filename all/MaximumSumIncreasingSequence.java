/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/12/18
 * Description:
 */
public class MaximumSumIncreasingSequence {

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

        for (int i = 0; i < length; i++)
            mSISS[i] = 0;

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
