package Java.LeetCode.matrixPrint.dignoal.forward.downward;

import Java.HelpersToPrint.GenericPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 23/09/19
 * Description:
 * [1,2,3,4]
 * [5,6,7,8]
 * [9,10,11,12]
 * [13,14,15,16]
 * <p>
 * expected:
 * [1, 2, 5, 3, 6, 9, 4, 7, 10, 13, 8, 11, 14, 12, 15, 16]
 */
public class ForwardDiagonalMatrixDownwardLeft {

    public static void main(String[] args) {
        test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Arrays.asList(1, 2, 4, 3, 5, 7, 6, 8, 9));
        test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, Arrays.asList(1, 2, 5, 3, 6, 9, 4, 7, 10, 13, 8, 11, 14, 12, 15, 16));
    }

    private static void test(int[][] matrix, List<Integer> expected) {
        System.out.println("\nMatrix:\n" + GenericPrinter.toString(matrix));
        System.out.println("expected:\n" + expected);
        System.out.println("Obtained:\n" + printForwardDiag1(matrix));
    }


    //see image for understanding it
    public static List<Integer> printForwardDiag1(int[][] matrix) {

        final int Row = matrix.length;
        final int Col = matrix[0].length;
        List<Integer> solution = new ArrayList<>();

        //print all columns from bottom to top participating
        for (int c = 0; c < Col; c++) {
            solution.add(matrix[0][c]);

            int i = 1;
            int j = c - 1;

            while (i < Row && j >= 0)
                solution.add(matrix[i++][j--]);

        }

        //print  row participation from top to bottom
        for (int r = 1; r < Row; r++) {
            solution.add(matrix[r][Col - 1]);

            int i = r + 1;
            int j = Col - 2;

            while (i < Row && j >= 0)
                solution.add(matrix[i++][j--]);

        }
        return solution;

    }
}
