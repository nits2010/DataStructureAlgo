package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-18
 * Description: https://leetcode.com/problems/interval-list-intersections/
 * <p>
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 * Example 1:
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */
public class IntervalListIntersections {

    public static void main(String args[]) {

        int a[][] = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int b[][] = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        test(a, b);
    }

    private static void test(int[][] a, int[][] b) {
        System.out.print("\n\n\n Testing intervals \n A= [ ");
        for (int i = 0; i < a.length; i++)
            System.out.print("[" + a[i][0] + "," + a[i][1] + "]");
        System.out.print("]\n");

        System.out.print("\n B= [ ");
        for (int i = 0; i < b.length; i++)
            System.out.print("[" + b[i][0] + "," + b[i][1] + "]");
        System.out.print("]\n");

        SolutionIntervalListIntersections intersections = new SolutionIntervalListIntersections();
        int res[][] = intersections.intervalIntersection(a, b);

        System.out.print("\n Intersections intervals \n R= [ ");
        for (int i = 0; i < res.length; i++)
            System.out.print("[" + res[i][0] + "," + res[i][1] + "]");
        System.out.print("]\n");


    }

}

class SolutionIntervalListIntersections {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        return intersection(A, B);
    }

    private int[][] intersection(int[][] a, int[][] b) {

//        if (a == null || a.length == 0)
//            return b;
//        if (b == null || b.length == 0)
//            return a;

        int maxSize = a.length + b.length;
        int k = -1;
        int temp[][] = new int[maxSize][2];

        int i = 0;
        int j = 0;


        while (i < a.length && j < b.length) {

            int as = a[i][0];
            int ae = a[i][1];

            int bs = b[j][0];
            int be = b[j][1];

            if (ae >= bs && be >= as) {
                k++;
                temp[k][0] = Math.max(as, bs);
                temp[k][1] = Math.min(ae, be);
            }

            if (be > ae)
                i++;
            else
                j++;


        }
//
//        while (i < a.length) {
//            k++;
//            temp[k][0] = a[i][0];
//            temp[k][1] = a[i][1];
//
//
//            i++;
//
//        }
//
//        while (j < b.length) {
//            k++;
//            temp[k][0] = b[j][0];
//            temp[k][1] = b[j][1];
//            j++;
//
//        }


        if (k == maxSize)
            return temp;

        int res[][] = new int[k + 1][2];
        for (int p = 0; p <= k; p++) {
            res[p][0] = temp[p][0];
            res[p][1] = temp[p][1];
        }

        return res;
    }
}
















