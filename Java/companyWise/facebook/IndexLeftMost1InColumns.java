package Java.companyWise.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-18
 * Description: https://aonecode.com/facebook-phone-interview-questions-2019
 * Question 1.2 Given a 2d array sorted row and column of 0 and 1, find the indexes of left most 1 in columns
 */
public class IndexLeftMost1InColumns {

    public static void main(String []args) {
        int mat[][] = {
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1},
        };
        System.out.println("\nTest 1\n");
        test(mat);


        int mat2[][] = {
                {0, 0, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1},
        };

        System.out.println("\nTest 2\n");
        test(mat2);

        int mat3[][] = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        System.out.println("\nTest 3\n");
        test(mat3);

        int mat4[][] = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
        };

        System.out.println("\nTest 4\n");
        test(mat4);

        int mat5[][] = {
                {0, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
        };

        System.out.println("\nTest 5\n");
        test(mat5);
    }

    private static void test(int[][] mat) {
        SolutionIndexLeftMost1InColumns sol = new SolutionIndexLeftMost1InColumns();
        List<int[]> ints = sol.indexOfLeftMost1s(mat);
        if (ints.isEmpty())
            System.out.println("Not found");
        else
            for (int[] a : ints)
                System.out.println("[ " + a[0] + ", " + a[1] + " ]");
    }
}

class SolutionIndexLeftMost1InColumns {

    public List<int[]> indexOfLeftMost1s(int mat[][]) {

        if (null == mat || mat.length == 0 || mat[0].length == 0)
            return Collections.EMPTY_LIST;

        int n = mat.length;
        int m = mat[0].length;


        int[] indexOfLeftMost = indexOfLeftMost1s(mat, n, m);
        if (indexOfLeftMost[0] != -1) {
            List<int[]> output = new ArrayList<>();

            int r = indexOfLeftMost[0];
            int c = indexOfLeftMost[1];

            output.add(indexOfLeftMost);

            for (int row = r + 1; row < n; row++) {
                if (mat[row][c] == 1) {
                    int[] t = new int[2];
                    t[0] = row;
                    t[1] = c;
                    output.add(t);
                }
            }
            return output;
        }


        return Collections.EMPTY_LIST;

    }

    private int[] indexOfLeftMost1s(int[][] mat, int n, int m) {
        int[] index = new int[2];
        index[0] = index[1] = Integer.MAX_VALUE;


        for (int r = 0; r < n; r++) {
            for (int c = m - 1; c >= 0; c--) {


                if (mat[r][c] == 1) {
                    if (index[1] > c) {
                        index[0] = r;
                        index[1] = c;
                    }
                    continue;
                } else
                    break;

            }
        }
        if (index[0] == Integer.MAX_VALUE)
            index[0] = index[1] = -1;
        return index;
    }
}
