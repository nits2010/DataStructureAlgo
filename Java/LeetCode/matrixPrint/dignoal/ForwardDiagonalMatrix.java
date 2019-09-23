package Java.LeetCode.matrixPrint.dignoal;

import Java.HelpersToPrint.GenericPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 23/09/19
 * Description:
 * https://leetcode.com/discuss/interview-question/125358/print-a-matrix-anti-diagonally/123979
 * https://www.geeksforgeeks.org/zigzag-or-diagonal-traversal-of-matrix/
 * Given a 2D matrix, print all elements of the given matrix in diagonal order. For example, consider the following 5 X 4 input matrix.
 * <p>
 * 1     2     3     4
 * 5     6     7     8
 * 9    10    11    12
 * 13    14    15    16
 * 17    18    19    20
 * Diagonal printing of the above matrix is
 * <p>
 * 1
 * 5     2
 * 9     6     3
 * 13    10     7     4
 * 17    14    11     8
 * 18    15    12
 * 19    16
 * 20
 */
public class ForwardDiagonalMatrix {

    public static void main(String[] args) {
        test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Arrays.asList(1, 4, 2, 7, 5, 3, 8, 6, 9));
        test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, Arrays.asList(1, 5, 2, 9, 6, 3, 13, 10, 7, 4, 14, 11, 8, 15, 12, 16));
    }

    private static void test(int[][] matrix, List<Integer> expected) {
        System.out.println("\nMatrix:\n" + GenericPrinter.toString(matrix));
        System.out.println("expected:\n" + expected);
        System.out.println("Obtained:\n" + printForwardDiag(matrix));
    }

    public static List<Integer> printForwardDiag(int[][] matrix) {

        final int ROW = matrix.length;
        final int COL = matrix[0].length;

        // There will be ROW+COL-1 lines in the output
        List<Integer> solution = new ArrayList<>();
        for (int line = 1; line <= (ROW + COL - 1); line++) {


            // Get column index of the first element in this
            // line of output.The index is 0 for first ROW
            // lines and line - ROW for remaining lines
            int start_col = Math.max(0, line - ROW);


            // Get count of elements in this line. The count
            // of elements is equal to minimum of line number,
            // COL-start_col and ROW
            int count = Math.min(line, Math.min((COL - start_col), ROW));


            // Print elements of this line
            for (int j = 0; j < count; j++) {
                int column = start_col + j;
                int row = Math.min(ROW, line) - j - 1;
                solution.add(matrix[row][column]);  // I changed only this line.
            }

        }

        return solution;
    }

}
