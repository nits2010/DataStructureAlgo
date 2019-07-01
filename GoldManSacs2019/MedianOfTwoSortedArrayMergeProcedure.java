package GoldManSacs2019;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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
 */
public class MedianOfTwoSortedArrayMergeProcedure {


    public static double findMedianSortedArrays(int[] A, int[] B) {

        if ((null == A || A.length == 0) && (null == B || B.length == 0))
            return -(double) Integer.MAX_VALUE;


        int x = A.length;
        int y = B.length;

        int temp[] = new int[x + y];
        int t = 0;
        int i = 0, j = 0;

        while (i < x && j < y) {

            int e1 = A[i];
            int e2 = B[j];

            if (e1 <= e2) {

                temp[t++] = e1;
                i++;
            } else {
                temp[t++] = e2;
                j++;
            }

        }

        while (i < x) {
            temp[t++] = A[i++];
        }

        while (j < y) {
            temp[t++] = B[j++];
        }

        if ((x + y) % 2 == 0) {
            int mid = (x + y) / 2;
            return (double) (temp[mid - 1] + temp[mid]) / 2;
        } else {
            return temp[(x + y) / 2];
        }


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
