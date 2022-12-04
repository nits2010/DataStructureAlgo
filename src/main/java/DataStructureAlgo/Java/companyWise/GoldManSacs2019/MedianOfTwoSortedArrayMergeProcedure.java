package DataStructureAlgo.Java.companyWise.GoldManSacs2019;

import  DataStructureAlgo.Java.LeetCode.medians.MedianOfTwoSortedArray;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-01
 * Description:
 * <p>
 * <p>
 * Instructions to candidate.
 * 1) Run this code in the REPL to observe its behaviour. The
 * execution entry point is main().
 * 2) Find the median of the two sorted arrays.
 * <p>
 * <p>
 * A -> 2 4 7 8 9 -> x = 5
 * B -> 3 5 11 -> y = 3
 * <p>
 * 2 3 4 5 7 8 9 11 -> (5+7)/2
 * <p>
 * A -> 2 4 7 8 9 13 -> x = 6
 * B -> 3 5 11 -> y = 3
 * <p>
 * 2 3 4 5 7 8 9 11 13-> 7
 * <p>
 * <p>
 * A -> 2 4 7 8 9 -> x = 5
 * B -> 2, 3 5 11 -> y = 4
 * <p>
 * 2 2 3 4 5 7 8 9 11 -> 5
 *
 * {@link MedianOfTwoSortedArray}
 */
public class MedianOfTwoSortedArrayMergeProcedure {


    public static double findMedianSortedArrays(int[] A, int[] B) {
        return MedianOfTwoSortedArray.findMedianSortedArraysUsingMergeProcedure(A, B);

    }


    /**
     * boolean doTestsPass()
     * Returns true if all tests pass. Otherwise returns false.
     */
    public static boolean doTestsPass() {
        // feel free to make testing more elegant
        boolean result = true;
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}) == 2.5;
        result = result && findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0;
        result = result && findMedianSortedArrays(new int[]{2, 4, 7, 8, 9}, new int[]{2, 3, 5, 11}) == 5.0;
        return result;
    }

    ;


    /**
     * Execution entry point.
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
