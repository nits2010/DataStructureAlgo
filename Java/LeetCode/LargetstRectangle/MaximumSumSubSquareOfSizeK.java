package Java.LeetCode.LargetstRectangle;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-26
 * Description:https://www.geeksforgeeks.org/print-maximum-sum-square-sub-matrix-of-given-size/
 * <p>
 * Given an N x N matrix, find a k x k submatrix where k <= N and k >= 1, such that sum of all the elements in
 * submatrix is maximum. The input matrix can contain zero, positive and negative numbers.
 * <p>
 * For example consider below matrix, if k = 3, then output should print the sub-matrix enclosed in blue.
 * rectangle
 */
public class MaximumSumSubSquareOfSizeK {
    public static void main(String args[]) {

        int[][] mat = {{1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 8, 6, 7, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}};
        System.out.println(maximumSumSubRectangleOfSizeK(mat, 3));

        int[][] mat2 = {{1, 2, -1, 4},
                {-8, -3, 4, 2},
                {3, 8, 10, -8},
                {-4, -1, 1, 7}};
        System.out.println(maximumSumSubRectangleOfSizeK(mat2, 3));
    }

    /**
     * To calculate sub-matrix sum of size kxk. We need to know all the sum of elements in each row from 0 to k-1 and each column
     * 0 to k-1 [ answer would be sum of both of it ]
     * <p>
     * One way is to iterate on the given matrix for each row and column, then keep considering the sub-array of size kxk and sum them up
     * O(k^2 N*M )
     * or
     * Optimized solution
     * <p>
     * Strip(i,j) represent the sum of elements from j to j+k-1 of ith row.
     * <p>
     * Strip(i,j) = Strip(i,j-1) - Mat[i][j-1] + M[i][j+k-1] ; 1<=j<m-k+1;
     * <p>
     * KSum(i,j) represent the sum of square KxK matrix
     * <p>
     * KSum(i,j) = KSum(i-1,j) - Strip[i-1][j] + Strip[i+k-1][j] ; 1<i<=n-k+1
     *
     * @param mat
     * @param k
     * @return
     */
    private static int maximumSumSubRectangleOfSizeK(int[][] mat, int k) {

        int n = mat.length;
        int m = mat[0].length;

        int strip[][] = new int[n][m - k + 1];
        int kSum[][] = new int[n - k + 1][m - k + 1];

        //get sum of first strip on first column  & for each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                strip[i][0] += mat[i][j];
            }
        }

        //build strip second column & for each row
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m - k + 1; j++) {
                strip[i][j] = strip[i][j - 1] - mat[i][j - 1] + mat[i][j + k - 1];
            }
        }

        //build first k sum using strip
        for (int j = 0; j < m - k + 1; j++) {

            for (int i = 0; i < k; i++) {
                kSum[0][j] += strip[i][j];
            }
        }

        int max = Integer.MIN_VALUE;

        //build kSum
        for (int i = 1; i < n - k + 1; i++) {

            for (int j = 0; j < m - k + 1; j++) {


                kSum[i][j] = kSum[i - 1][j] - strip[i - 1][j] + strip[i + k - 1][j];
                max = Math.max(max, kSum[i][j]);

            }
        }

        return max;
    }
}
