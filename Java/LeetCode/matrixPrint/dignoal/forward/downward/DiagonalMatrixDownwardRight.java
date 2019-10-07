package Java.LeetCode.matrixPrint.dignoal.forward.downward;

import Java.helpers.GenericPrinter;

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
 * 3, 2, 6, 1, 5, 9, 4, 8, 7
 * <p>
 * [1,2,3,4]
 * [5,6,7,8]
 * [9,10,11,12]
 * [13,14,15,16]
 * 4, 3, 8, 2, 7, 12, 1, 6, 11, 16, 5, 10, 15, 9, 14, 13
 */
public class DiagonalMatrixDownwardRight {

    public static void main(String[] args) {
        test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Arrays.asList(3, 2, 6, 1, 5, 9, 4, 8, 7));
        test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, Arrays.asList(4, 3, 8, 2, 7, 12, 1, 6, 11, 16, 5, 10, 15, 9, 14, 13));
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

        for (int c = Col - 1; c >= 0; c--) {
            solution.add(matrix[0][c]);

            int i = 1;
            int j = c + 1;

            while (i < Row && j < Col)
                solution.add(matrix[i++][j++]);

        }

        for (int r = 1; r < Row; r++) {
            solution.add(matrix[r][0]);

            int i = r + 1;
            int j = 1;

            while (i < Row && j < Col)
                solution.add(matrix[i++][j++]);

        }
        return solution;

    }

    public static List<Integer> printAntiDiag2(List<List<Integer>> matrix) {

        final int ROW = matrix.size();

        // There will be ROW+COL-1 lines in the output
        List<Integer> solution = new ArrayList<>();
        for (int line = 1; line <= (ROW + ROW - 1); line++) {


            // Get column index of the first element in this
            // line of output.The index is 0 for first ROW
            // lines and line - ROW for remaining lines
            int start_col = Math.max(0, line - ROW);


            // Get count of elements in this line. The count
            // of elements is equal to minimum of line number,
            // COL-start_col and ROW
            int count = Math.min(line, Math.min((ROW - start_col), ROW));


            // Print elements of this line
            for (int j = 0; j < count; j++) {
                int row = start_col + j;
                int column = Math.max(0, ROW - line) + j;
                solution.add(matrix.get(row).get(column));  // I changed only this line.
            }

        }

        return solution;
    }
}
