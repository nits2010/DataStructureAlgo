package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-27
 * Description:
 * https://leetcode.com/problems/maximal-square/
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 */
public class MaximumSizeSquareMatrix {


    public static void main(String args[]) {
        char M[][] =
                {
                        {0, 1, 1, 0, 1},
                        {1, 1, 0, 1, 0},
                        {0, 1, 1, 1, 0},
                        {1, 1, 1, 1, 0},
                        {1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0}
                };


        System.out.println(maximalSquare((M)));

        char N[][] =
                {
                        {'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'}
                };
        System.out.println(maximalSquare((N)));

        char O[][] =
                {
                        {'0', '1'},

                };
        System.out.println(maximalSquare((O)));

    }

    public static int maximalSquare(char[][] matrix) {

        if (null == matrix)
            return 0;

        int largestSqaureArea = 0;
        if(matrix.length == 0 )
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;



        if (n == 1 && m == 1)
            return (matrix[0][0] == '1' || matrix[0][0] == 1 )? 1 : 0;

        int temp[][] = new int[n+1][m+1];


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i-1][j-1] == '1' || matrix[i-1][j-1] == 1) {
                    temp[i][j] = 1 + Math.min(temp[i - 1][j - 1], Math.min(temp[i - 1][j], temp[i][j - 1])) ;
                    largestSqaureArea = Math.max(largestSqaureArea, temp[i][j]);
                } else
                    temp[i][j] = 0;
            }
        }

        return largestSqaureArea*largestSqaureArea;
    }

}
