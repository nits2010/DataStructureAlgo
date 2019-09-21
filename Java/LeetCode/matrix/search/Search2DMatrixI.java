package Java.LeetCode.matrix.search;

import Java.HelpersToPrint.GenericPrinter;


/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/search-a-2d-matrix/
 * <p>
 * 74. Search a 2D Matrix
 * <p>
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Example 1:
 * <p>
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * Example 2:
 * <p>
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */
public class Search2DMatrixI {

    public static void main(String[] args) {

        int temp[][] = new int[0][0];
        System.out.println(temp.length);
        System.out.println(temp[0].length);

        test(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 3, true);

        test(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 13, false);
    }

    private static void test(int[][] mat, int target, boolean expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(mat) + " target : " + target + " expected :" + expected);
        System.out.println(" obtained :" + searchMatrix(mat, target));
    }


    /**
     * * Integers in each row are sorted from left to right.
     * * The first integer of each row is greater than the last integer of the previous row.
     * <p>
     * i.e. all elements are sorted in increasing order.
     * <p>
     * Algo:
     * Find the candidate row, if found then do binary search.
     * Candidate row: matrix[i][0] <= target <=matrix[i][n-1]; then i is candidate row
     * <p>
     *
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
     * Memory Usage: 43.2 MB, less than 6.06% of Java online submissions for Search a 2D Matrix.
     * <p>
     * Complexity: matrix (m*n) size
     * 1. Finding the candidate O(m)
     * 2. Finding element in candidate array is O(log(n))
     * O(m + log(n)) / O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0)
            return false;

        if (target < matrix[0][0])
            return false;
        int n = matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {

            //is this our candidate row?
            if (matrix[i][0] <= target && target <= matrix[i][n - 1]) {

                return binarySearch(matrix[i], target);

            }
        }


        return false;
    }

    private static boolean binarySearch(int[] matrix, int target) {
        int l = 0, r = matrix.length;

        while (l <= r) {

            int mid = (l + r) >> 1;
            if (matrix[mid] == target)
                return true;

            if (matrix[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return false;
    }


}
