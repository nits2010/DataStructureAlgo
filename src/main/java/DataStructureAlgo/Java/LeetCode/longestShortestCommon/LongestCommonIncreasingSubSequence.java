package DataStructureAlgo.Java.LeetCode.longestShortestCommon;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.nonleetcode.LongestIncreasingSubSequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-24
 * Description: https://www.geeksforgeeks.org/longest-common-increasing-subsequence-lcs-lis/
 * <p>
 * Given two arrays, find length of the longest common increasing sub sequence [LCIS] and
 * print one of such sequences (multiple sequences may exist)
 * <p>
 * Suppose we consider two arrays –
 * arr1[] = {3, 4, 9, 1} and
 * arr2[] = {5, 3, 8, 9, 10, 2, 1}
 * <p>
 * Our answer would be {3, 9} as this is the longest common subsequence which is increasing also.
 */
public class LongestCommonIncreasingSubSequence {

    public static void main(String[] args) {
        ILongestCommonIncreasingSubSequence twoD = new LongestCommonIncreasingSubSequence2D();
        ILongestCommonIncreasingSubSequence oneD = new LongestCommonIncreasingSubSequence1D();

        test(new int[]{3, 4, 9, 1}, new int[]{5, 3, 8, 9, 10, 2, 1}, twoD, oneD);

        test(new int[]{3, 4, 9, 1}, new int[]{5, 3, 4, 10, 11, 9, 1}, twoD, oneD);

        test(new int[]{3, 4, 9, 1}, new int[]{15, 13, 14, 10, 11, 19, 11}, twoD, oneD);

        test(new int[]{3, 4, 9, 1}, new int[]{3, 4, 9, 1}, twoD, oneD);

        test(new int[]{3, 4, 9, 1}, new int[]{3, 2, 4, 8, 9, 89, 1}, twoD, oneD);

        test(new int[]{3, 4, 9, 1}, new int[]{1, 9, 4, 3}, twoD, oneD);

        test(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}, twoD, oneD);

        test(new int[]{1, 2, 3, 4}, new int[]{1, 5, 6, 2, 7, 8, 3, 9, 6, 4}, twoD, oneD);

        test(new int[]{}, new int[]{1, 5, 6, 2, 7, 8, 3, 9, 6, 4}, twoD, oneD);

        test(new int[]{}, new int[]{}, twoD, oneD);

        test(new int[]{1, 2, 3, 4}, new int[]{}, twoD, oneD);
    }

    private static void test(int[] arr1, int[] arr2, ILongestCommonIncreasingSubSequence sol, ILongestCommonIncreasingSubSequence sol2) {
        System.out.println("2D");
        System.out.println("First array : ");
        CommonMethods.print(arr1);

        System.out.println("Second array : ");
        CommonMethods.print(arr2);

        System.out.println("\n LCIS length 2D " + sol.longestCommonIncreasingSubSequence(arr1, arr2));
        System.out.println("\n LCIS length 1D " + sol2.longestCommonIncreasingSubSequence(arr1, arr2));
    }


}

interface ILongestCommonIncreasingSubSequence {

    List<Integer> longestCommonIncreasingSubSequence(int a[], int b[]);

}

class LongestCommonIncreasingSubSequence2D implements ILongestCommonIncreasingSubSequence {

    /**
     * Let A be the first array of n size and B be the second array of m size.
     * We are interest to find LCIS of A and B.
     * <p>
     * Let i is use to iterate over A and j is for B.
     * <p>
     * LCIS(i,j) can be defined as
     * Length of longest increasing sub-sequence which ends , i'th the char of A and j'th char of B
     * <p>
     * Now,
     * If both are not same, i.e. A(i) > B( j) then we can extend last solution LCIS(i-1,j)
     * If both are same, then we need to find the maximum out of all previously computed cases where A(i) > B(j) ; let denote by 'max'
     * <p>
     * LCIS(i,j)
     * i=0 or j=0 then 0
     * Otherwise
     * If A(i) > B(j) then LCiS(i,j) is LCIS(i-1, j)
     * If equal then LCiS(i,j) is 1+ max
     * <p>
     * LCIS[i][j] is the length of longest common increasing sub-sequence s.t. A(i) > B(j).
     * <p>
     * LCIS[i][j] = Max { LCIS[i-1][j] if A(i) == B(j)}
     * *                     1 + Max ( LCIS [i-1][k] where A(i) > B(k) and 0<=k<j )
     * *                 }
     * <p>
     * {@link LongestIncreasingSubSequence}
     * {@link LongestCommonSubSequence}
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public List<Integer> longestCommonIncreasingSubSequence(int a[], int b[]) {

        int n = a.length;
        int m = b.length;

        int lcis[][] = new int[n + 1][m + 1];
        List<Integer> sequence = new ArrayList<>();

        int last; //use to fill the sequence
        int max = Integer.MIN_VALUE;

        for (int i = 0; i <= n; i++) {

            int maxL = 0;
            last = 0;

            for (int j = 0; j <= m; j++) {

                //empty sequence
                if (i == 0 || j == 0)
                    lcis[i][j] = 0;

                else {

                    lcis[i][j] = lcis[i - 1][j]; //extend the previous solution, this solution has length at least as previous solution.

                    int x = a[i - 1];
                    int y = b[j - 1];

                    //if they are same, then extend previous
                    if (x == y) {

                        if (lcis[i][j] < 1 + maxL)
                            lcis[i][j] = maxL + 1;

                        if (max < lcis[i][j]) {
                            max = Math.max(max, lcis[i][j]);
                            sequence.add(a[last]);
                        }
                    } else {

                        if (x > y) {
                            maxL = Math.max(maxL, lcis[i - 1][j]);
                            last = i - 1;

                        }
                    }
                }
            }
        }

        return sequence;

    }
}


class LongestCommonIncreasingSubSequence1D implements ILongestCommonIncreasingSubSequence {

    /**
     * In above solution
     * <p>
     * LCIS[i][j] is the length of longest common increasing sub-sequence s.t. A(i) > B(j).
     * <p>
     * LCIS[i][j] = Max { LCIS[i-1][j] if A(i) == B(j)}
     * * *                     1 + Max ( LCIS [i-1][k] where A(i) > B(k) and 0<=k<j )
     * <p>
     * at any moment we are using only
     * LCIS[i-1][j] & LCIS[i-1][k] where k < j
     * Means we are using only last row to compute current row
     * <p>
     * So we can use 1D array instead
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public List<Integer> longestCommonIncreasingSubSequence(int[] a, int[] b) {


        int n = a.length;
        int m = b.length;

        /**
         * Length of lcis start from ith element of a for all elements of b
         *
         */
        int lcis[] = new int[m];
        List<Integer> seq = new ArrayList<>();
        int parent[] = new int[m];

        for (int i = 0; i < n; i++) {

            int max = 0;
            int last = -1;

            for (int j = 0; j < m; j++) {

                int x = a[i];
                int y = b[j];

                if (x == y) {

                    if (lcis[j] < 1 + max)
                        lcis[j] = max + 1;

                    if (max < lcis[j]) {
                        max = Math.max(max, lcis[j]);
                        parent[j] = last;
                    }


                } else {
                    if (x > y) {

                        if (max < lcis[j]) {
                            max = lcis[j];
                            last = j;
                        }
                    }
                }

            }

        }

        // The maximum value in table[] is out
        // result
        int result = 0, index = -1;
        for (int i = 0; i < m; i++) {
            if (lcis[i] > result) {
                result = lcis[i];
                index = i;
            }
        }

        while (index != -1) {
            seq.add(b[index]);
            index = parent[index];
        }


        Collections.reverse(seq);
        return seq;
    }
}