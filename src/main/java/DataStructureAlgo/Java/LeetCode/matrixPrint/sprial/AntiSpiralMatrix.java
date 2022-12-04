package DataStructureAlgo.Java.LeetCode.matrixPrint.sprial;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 23/09/19
 * Description: https://www.geeksforgeeks.org/print-matrix-antispiral-form/
 * Print matrix in antispiral form
 * Given a 2D array, the task is to print matrix in anti spiral form:
 * <p>
 * Examples:
 * spiral
 * Output: 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
 * <p>
 * Input : arr[][4] =
 * {1, 2, 3, 4
 * 5, 6, 7, 8
 * 9, 10, 11, 12
 * 13, 14, 15, 16};
 * Output : 10 11 7 6 5 9 13 14 15 16 12 8 4 3 2 1
 * <p>
 * Input :arr[][6] =
 * {1, 2, 3, 4, 5, 6
 * 7, 8, 9, 10, 11, 12
 * 13, 14, 15, 16, 17, 18};
 * Output : 11 10 9 8 7 13 14 15 16 17 18 12 6 5 4 3 2 1
 * <p>
 * Same as {@link SpiralMatrixI}
 */
public class AntiSpiralMatrix {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{2, 5, 8}, {4, 0, -1}}, Arrays.asList(4, 0, -1, 8, 5, 2));
        test &= test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Arrays.asList(5, 4, 7, 8, 9, 6, 3, 2, 1));
        test &= test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}, Arrays.asList(7, 6, 5, 9, 10, 11, 12, 8, 4, 3, 2, 1));
        test &= test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, Arrays.asList(10, 11, 7, 6, 5, 9, 13, 14, 15, 16, 12, 8, 4, 3, 2, 1));


        System.out.println("\nTests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[][] matrix, List<Integer> expected) {
        System.out.println("\nMatrix:\n" + GenericPrinter.toString(matrix));
        System.out.println("Expected: " + expected);
        List<Integer> obtained = antiSpiralOrder(matrix);
        System.out.println("Obtained: " + obtained);

        return obtained.equals(expected);

    }

    private static List<Integer> antiSpiralOrder(int[][] matrix) {
        final List<Integer> spiral = SpiralMatrixI.spiralOrder(matrix);
        Collections.reverse(spiral); //make anti spiral
        return spiral;
    }


}
