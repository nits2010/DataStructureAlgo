package Java.LeetCode.matrix.search;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description:https://leetcode.com/problems/search-a-2d-matrix-ii/
 * 240. Search a 2D Matrix II
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 */
public class Search2DMatrixII {

    public static void main(String[] args) {

        test(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5, true);

        test(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 20, false);
    }

    private static void test(int[][] mat, int target, boolean expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(mat) + " target : " + target + " expected :" + expected);
        System.out.println(" obtained :" + searchMatrix(mat, target));
    }

    /**
     * Similar {@link Search2DMatrixI}, instead of all elements are sorted, here elements are row wise and column wise sorted.
     * <p>
     * Alog:
     * 1. Start with first row and last column. Move towards left side
     * 2. if element is smaller then current element, move left; previous column
     * 3. if element is greater then current element, move down : next row
     * 4. if element is not in range, return false
     * <p>
     * Complexity : O(m + n ) / O(1)
     * <p>
     * Runtime: 5 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix II.
     * Memory Usage: 42.7 MB, less than 100.00% of Java online submissions for Search a 2D Matrix II.
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0) {

            if (matrix[i][j] == target)
                return true;

            if (matrix[i][j] > target)
                j--;
            else if (matrix[i][j] < target)
                i++;


        }

        return false;

    }
}
