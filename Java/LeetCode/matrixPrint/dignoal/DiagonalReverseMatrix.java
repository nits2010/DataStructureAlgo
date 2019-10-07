package Java.LeetCode.matrixPrint.dignoal;

import Java.helpers.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 23/09/19
 * Description: https://www.geeksforgeeks.org/reverse-diagonal-elements-matrix/
 * Reverse Diagonal elements of matrix
 * Given a square matrix of order n*n, we have to reverse the elements of both diagonals.
 * <p>
 * Examples:
 * <p>
 * Input :
 * {1, 2, 3,
 * 4, 5, 6,
 * 7, 8, 9}
 * Output :
 * {9, 2, 7,
 * 4, 5, 6,
 * 3, 8, 1}
 * Explanation:
 * Major Diagonal Elements before:  1 5 9
 * After reverse:   9 5 1
 * Minor Diagonal Elements before:  3 5 7
 * After reverse:   7 5 3
 * Input :
 * {1,  2,  3,  4,
 * 5,  6,  7,  8,
 * 9,  10, 11, 12,
 * 13, 14, 15, 16}
 * <p>
 * Output :
 * {16, 2, 3, 13,
 * 5, 11, 10, 8,
 * 9, 7,  6,  12,
 * 4, 14, 15, 1}
 */
public class DiagonalReverseMatrix {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new int[][]{{9, 2, 7}, {4, 5, 6}, {3, 8, 1}});
        test &= test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, new int[][]{{16, 2, 3, 13}, {5, 11, 10, 8}, {9, 7, 6, 12}, {4, 14, 15, 1}});
        System.out.println("Tests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[][] given, int[][] expected) {
        System.out.println("\nGiven:\n" + GenericPrinter.toString(given));
        System.out.println("expected:\n" + GenericPrinter.toString(expected));
        final int[][] output = reverseTheDiagonalOfMatrix(given);
        System.out.println("Obtained:\n" + GenericPrinter.toString(output));
        return Arrays.deepEquals(expected, output);

    }

    /**
     * See carefully, we just reverse both the diagonal of matrix.
     * There are two diagonal in matrix, Major and Minor.
     * Major: from left top to bottom right
     * Minor: from right top to bottom left
     *
     * @param input
     * @return Reverse the major and minor diagonals
     */
    public static int[][] reverseTheDiagonalOfMatrix(int[][] input) {

        if (input == null || input.length == 0)
            return input;

        int[][] matrix = GenericPrinter.copyOf(input);
        int i = 0;
        int j = matrix[0].length;

        while (i < j) {
            //reverse major
            int temp = matrix[i][i];
            matrix[i][i] = matrix[j - 1][j - 1];
            matrix[j - 1][j - 1] = temp;

            //reverse minor
            temp = matrix[i][j - 1];
            matrix[i][j - 1] = matrix[j - 1][i];
            matrix[j - 1][i] = temp;

            i++;
            j--;
        }
        return matrix;

    }
}
