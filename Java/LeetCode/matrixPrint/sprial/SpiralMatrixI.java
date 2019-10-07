package Java.LeetCode.matrixPrint.sprial;

import Java.helpers.GenericPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 22/09/19
 * Description: https://leetcode.com/problems/spiral-matrix/
 * 54. Spiral Matrix [Medium]
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * [Amazon]
 */
public class SpiralMatrixI {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{2, 5, 8}, {4, 0, -1}}, Arrays.asList(2, 5, 8, -1, 0, 4));
        test &= test(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5));
        test &= test(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}, Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));


        System.out.println("\nTests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[][] matrix, List<Integer> expected) {
        System.out.println("\nMatrix:\n" + GenericPrinter.toString(matrix));
        System.out.println("Expected: " + expected);
        List<Integer> obtained = spiralOrder(matrix);
        System.out.println("Obtained: " + obtained);

        return obtained.equals(expected);

    }

    /**
     * Keep pointer for top row, left column, right column and bottom row.
     * Run for loops to print each row or column. Decrement the pointer accordingly
     *
     * @param matrix grid to Spiral
     * @return spiral representation of matrix
     * <p>
     * Time complexity: O(m*n)
     * Space complexity: O(1)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
     * Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Spiral Matrix.
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        final List<Integer> spiral = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return spiral;

        //pointers for row
        int top = 0;
        int bottom = matrix.length - 1;

        //pointers for column
        int left = 0;
        int right = matrix[0].length - 1;

        int i;
        int j;
        while (top <= bottom && left <= right) {

            //get top row
            j = left;
            while (j <= right)
                spiral.add(matrix[top][j++]);
            top++;

            //get right column
            i = top;
            while (i <= bottom)
                spiral.add(matrix[i++][right]);
            right--;

            //get bottom row if available
            if (top <= bottom) {
                j = right;
                while (j >= left)
                    spiral.add(matrix[bottom][j--]);

                bottom--;
            }

            //get left column if available
            if (left <= right) {
                i = bottom;
                while (i >= top)
                    spiral.add(matrix[i--][left]);

                left++;
            }

        }
        return spiral;

    }
}
