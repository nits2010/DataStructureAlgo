package DataStructureAlgo.Java.LeetCode.matrixPrint.sprial;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 22/09/19
 * Description: https://leetcode.com/problems/spiral-matrix-ii/
 * 59. Spiral Matrix II
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(1, new int[][]{{1}});
        test &= test(2, new int[][]{{1, 2}, {4, 3}});
        test &= test(3, new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}});
        test &= test(4, new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}});
        test &= test(5, new int[][]{{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7}, {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9}});

        System.out.println("\nTests :" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int n, int[][] expected) {
        System.out.println("\nN:" + n);
        System.out.println("Expected:\n" + GenericPrinter.toString(expected));
        int[][] obtained = generateMatrix(n);
        System.out.println("Obtained:\n" + GenericPrinter.toString(obtained));

        return Arrays.deepEquals(obtained, expected);

    }

    /**
     * O(n^2)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix II.
     * Memory Usage: 34.3 MB, less than 8.33% of Java online submissions for Spiral Matrix II.
     *
     * @param n
     * @return
     */
    private static int[][] generateMatrix(int n) {
        if (n <= 0)
            return new int[0][];

        final int[][] matrix = new int[n][n];

        int ele = 1;
        int limit = (int) Math.pow(n, 2);

        //pointers for row
        int top = 0;
        int bottom = matrix.length - 1;

        //pointers for column
        int left = 0;
        int right = matrix[0].length - 1;
        int i, j;

        while (ele <= limit) {

            //get top row
            j = left;
            while (j <= right)
                matrix[top][j++] = ele++;
            top++;

            //get right column
            i = top;
            while (i <= bottom)
                matrix[i++][right] = ele++;
            right--;

            //get bottom row if available
            if (top <= bottom) {
                j = right;
                while (j >= left)
                    matrix[bottom][j--] = ele++;

                bottom--;
            }

            //get left column if available
            if (left <= right) {
                i = bottom;
                while (i >= top)
                    matrix[i--][left] = ele++;

                left++;
            }
        }
        return matrix;
    }
}
