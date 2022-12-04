package DataStructureAlgo.Java.LeetCode.matrixPrint.dignoal.forward.upward;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 23/09/19
 * Description:
 * Matrix:
 * [1,2,3]
 * [4,5,6]
 * [7,8,9]
 * <p>
 * expected:
 * [3, 6, 2, 9, 5, 1, 8, 4, 7]
 * <p>
 * Matrix:
 * [1,2,3,4]
 * [5,6,7,8]
 * [9,10,11,12]
 * [13,14,15,16]
 * <p>
 * expected:
 * [4, 8, 3, 12, 7, 2, 16, 11, 6, 1, 15, 10, 5, 14, 9, 13]
 */
public class DiagonalMatrixUpwardRight {

    public static void main(String[] args) {
        test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Arrays.asList(3, 6, 2, 9, 5, 1, 8, 4, 7));
        test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, Arrays.asList(4, 8, 3, 12, 7, 2, 16, 11, 6, 1, 15, 10, 5, 14, 9, 13));
    }

    private static void test(int[][] matrix, List<Integer> expected) {
        System.out.println("\nMatrix:\n" + GenericPrinter.toString(matrix));
        System.out.println("expected:\n" + expected);
        System.out.println("Obtained:\n" + diagonalPrint(matrix));
    }


    //see image for understanding it
    public static List<Integer> diagonalPrint(int[][] matrix) {

        final int Row = matrix.length;
        final int Col = matrix[0].length;
        List<Integer> solution = new ArrayList<>();

        //print all rows from top to bottom participating
        for (int r = 0; r < Row; r++) {
            solution.add(matrix[r][Col - 1]);

            int i = r - 1;
            int j = Col - 2;

            while (i >= 0 && j >= 0)
                solution.add(matrix[i--][j--]);

        }

        //print last row participation from bottom to up
        for (int c = Col - 2; c >= 0; c--) {
            solution.add(matrix[Row - 1][c]);

            int i = Row - 2;
            int j = c - 1;

            while (i >= 0 && j >= 0)
                solution.add(matrix[i--][j--]);

        }
        return solution;

    }
}
