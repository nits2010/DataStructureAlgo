package Java.LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-22
 * Description: https://leetcode.com/problems/set-matrix-zeroes/
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * Output:
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 * Example 2:
 * <p>
 * Input:
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * Output:
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 * Follow up:
 * <p>
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 * {@link GameOfLife}
 */
public class SetMatrixZeroes {

    public static void main(String []args) {

        test1(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
        test2(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
        test3(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});


        test1(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
        test2(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
        test3(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});


        test3(new int[][]{{1, 1, 1}, {0, 1, 2}});

    }

    private static void print(int mat[][]) {

        for (int i = 0; i < mat.length; i++) {

            System.out.println();
            for (int j = 0; j < mat[0].length; j++)
                System.out.print(mat[i][j] + " ");
        }
    }

    private static void test1(int[][] ints) {
        System.out.println("\n Test 1 \n");
        SetMatrixZeroes1 set = new SetMatrixZeroes1();
        System.out.println("Input");
        print(ints);
        set.setZeroes(ints);
        System.out.println("\nOutput");
        print(ints);

    }


    private static void test2(int[][] ints) {
        System.out.println("\n Test 2");
        SetMatrixZeroes2 set = new SetMatrixZeroes2();
        System.out.println("Input");
        print(ints);
        set.setZeroes(ints);
        System.out.println("\nOutput");
        print(ints);

    }


    private static void test3(int[][] ints) {
        System.out.println("\n Test 3");
        SetMatrixZeroes3 set = new SetMatrixZeroes3();
        System.out.println("Input");
        print(ints);
        set.setZeroes(ints);
        System.out.println("\nOutput");
        print(ints);
    }


}

/**
 * Extension of 2nd solution, instead of iterating again n again. Find out only those which need to be set to 0.
 * The one way is to store those index and iterate them later but this will produce mxn space at worst.
 * We need to optimize the space.
 * <p>
 * Important observation is, once we say a row or column is about to set zero, then we can lazily mark it to zero
 * and later we'll make complete row and column zero.
 * <p>
 * To mark them, still we need some memory ?
 * can we utilize the matrix it self?
 * <p>
 * We know, this row and column will be zero any how, then use the first column and row to mark them lazily.
 * <p>
 * Keep in mind, since at index [0][0] is same for both row and column {just like [1][1]} and we are using first row and column as cache
 * then we need to find do we need to set this column to zero also (row will be automatically zero if any of the column other than 0th has zero)
 */
class SetMatrixZeroes3 {

    public void setZeroes(int[][] matrix) {
        if (null == matrix || matrix.length == 0)
            return;

        final int Row = matrix.length;
        final int Col = matrix[0].length;

        markProactively(matrix, Row, Col, markLazily(matrix, Row, Col));
    }

    /**
     * @param matrix [
     *               [0,1,2,0],
     *               [3,4,5,2],
     *               [1,3,1,5]
     *               ]
     *               <p>
     *               After step 1
     *               [
     *               [0,1,2,0],
     *               [3,4,5,0],
     *               [1,3,1,0]
     *               ]
     *               <p>
     *               After handleFirstRow
     *               [
     *               [0,0,0,0],
     *               [3,4,5,0],
     *               [1,3,1,0]
     *               ]
     *               After handleFirstCol
     *               [
     *               [0,0,0,0],
     *               [0,4,5,0],
     *               [0,3,1,0]
     *               ]
     * @param Row
     * @param Col
     */
    private void markProactively(int[][] matrix, final int Row, final int Col, boolean setFirstColumnToZero) {


        for (int i = 1; i < Row; i++) {

            for (int j = 1; j < Col; j++) {

                if (matrix[i][0] == 0 || matrix[0][j] == 0) //that was marked during lazy marking
                    matrix[i][j] = 0;

            }
        }

        handleFirstRow(matrix, Row, Col);

        handleFirstCol(matrix, Row, Col, setFirstColumnToZero);

    }

    private void handleFirstCol(int[][] matrix, int row, int col, boolean setFirstColumnToZero) {

        if (setFirstColumnToZero)
            for (int i = 0; i < row; i++)
                matrix[i][0] = 0;
    }

    private void handleFirstRow(int[][] matrix, int row, int col) {
        if (matrix[0][0] == 0)
            for (int i = 0; i < col; i++)
                matrix[0][i] = 0;
    }

    /**
     * @param matrix [
     *               [0,1,2,0],
     *               [3,4,5,2],
     *               [1,3,1,5]
     *               ]
     * @param Row
     * @param Col    Return
     *               <p>
     *               <p>
     *               [
     *               [0,1,2,0],
     *               [3,4,5,2],
     *               [1,3,1,5]
     *               ]
     */
    private boolean markLazily(int[][] matrix, final int Row, final int Col) {
        if (null == matrix || matrix.length == 0)
            return false;

        boolean setFirstColToZero = false;
        for (int i = 0; i < Row; i++) {

            if (matrix[i][0] == 0)
                setFirstColToZero = true;

            for (int j = 1; j < Col; j++) {
                if (matrix[i][j] == 0) {
                    //Mark ths row and column to zero lazily
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        return setFirstColToZero;
    }


}

/**
 * Iterate over the matrix, find a zero, set all row and column with a Not possible number and keep doing it
 * Iterate again and reset those Not possible number to O
 * <p>
 * In worst case all the row and column will have zero so we need to iterate them twice
 * O(m^2*n^2)/ O(1)
 */
class SetMatrixZeroes2 {

    private static final int NOTPOSSIBLE = -100000;

    public void setZeroes(int[][] matrix) {

        if (null == matrix || matrix.length == 0)
            return;

        final int Row = matrix.length;
        final int Col = matrix[0].length;

        for (int i = 0; i < Row; i++) {

            for (int j = 0; j < Col; j++) {

                if (matrix[i][j] == 0)
                    setZeroes(matrix, i, j, NOTPOSSIBLE);
            }
        }

        for (int i = 0; i < Row; i++) {

            for (int j = 0; j < Col; j++) {

                if (matrix[i][j] == NOTPOSSIBLE)
                    matrix[i][j] = 0;
            }
        }
    }


    private void setZeroes(int[][] matrix, int i, int j, int number) {

        //set zero for all row of jth column
        for (int r = 0; r < matrix.length; r++)
            if (matrix[r][j] != 0)
                matrix[r][j] = number;

        //set zero for all column of ith row
        for (int c = 0; c < matrix[0].length; c++)
            if (matrix[i][c] != 0)
                matrix[i][c] = number;

    }

}

/**
 * A straight forward solution using O(mn) space is probably a bad idea.
 * <p>
 * Find the zeros, and numberOfWays each row and column. Use queue to store those zeros
 * In worst case all are zero and queue will have m*n entries
 * <p>
 * O(mn)/ O(mn)
 */
class SetMatrixZeroes1 {

    public void setZeroes(int[][] matrix) {

        if (null == matrix || matrix.length == 0)
            return;

        final int Row = matrix.length;
        final int Col = matrix[0].length;
        final Queue<Integer> zerosIndex = new LinkedList<>();


        //Find all zeros
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == 0)
                    zerosIndex.offer(i * Col + j);
        }

        //Process all zeros
        while (!zerosIndex.isEmpty()) {

            int cord = zerosIndex.poll();
            int i = cord / Col;
            int j = cord % Col;

            setZeroes(matrix, i, j, 0);

        }


    }

    private void setZeroes(int[][] matrix, int i, int j, int number) {

        //set zero for all row of jth column
        for (int r = 0; r < matrix.length; r++)
            matrix[r][j] = number;

        //set zero for all column of ith row
        for (int c = 0; c < matrix[0].length; c++)
            matrix[i][c] = number;

    }
}