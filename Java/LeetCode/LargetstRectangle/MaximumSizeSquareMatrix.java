package Java.LeetCode.LargetstRectangle;

import Java.HelpersToPrint.HelperToPrint;

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
                        {'0', '1', '1', '0', '1'},
                        {'1', '1', '0', '1', '0'},
                        {'0', '1', '1', '1', '0'},
                        {'1', '1', '1', '1', '0'},
                        {'1', '1', '1', '1', '1'},
                        {'0', '0', '0', '0', '0'}
                };


        System.out.println("Input");
        HelperToPrint.print2DArray(M);
        System.out.println("Sub-Square");
        HelperToPrint.print2DArray(MaximumSizeSquareSubMatrix.calculateMaxSquareWithOnes(M));
        System.out.println("Area :" + MaximumSizeSqureSubMatrixArea.maximalSquare((M)));

        char N[][] =
                {
                        {'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'}
                };
        System.out.println("Input");
        HelperToPrint.print2DArray(N);
        System.out.println("Sub-Square");
        HelperToPrint.print2DArray(MaximumSizeSquareSubMatrix.calculateMaxSquareWithOnes(N));
        System.out.println("Area:" + MaximumSizeSqureSubMatrixArea.maximalSquare((N)));

        char O[][] =
                {
                        {'0', '1'},

                };
        System.out.println("Input");
        HelperToPrint.print2DArray(O);
        System.out.println("Sub-Square");
        HelperToPrint.print2DArray(MaximumSizeSquareSubMatrix.calculateMaxSquareWithOnes(O));
        System.out.println("Area:" + MaximumSizeSqureSubMatrixArea.maximalSquare((O)));

    }


}

class MaximumSizeSqureSubMatrixArea {
    public static int maximalSquare(char[][] matrix) {

        if (null == matrix)
            return 0;

        int largestSqaureArea = 0;
        if (matrix.length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;


        if (n == 1 && m == 1)
            return (matrix[0][0] == '1' || matrix[0][0] == 1) ? 1 : 0;

        int temp[][] = new int[n + 1][m + 1];


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i - 1][j - 1] == '1' || matrix[i - 1][j - 1] == 1) {
                    temp[i][j] = 1 + Math.min(temp[i - 1][j - 1], Math.min(temp[i - 1][j], temp[i][j - 1]));
                    largestSqaureArea = Math.max(largestSqaureArea, temp[i][j]);
                } else
                    temp[i][j] = 0;
            }
        }

        return largestSqaureArea * largestSqaureArea;
    }
}

/**
 * To Find out square matrix it self
 */
class MaximumSizeSquareSubMatrix {


    /**
     * This method does not handle single row array
     *
     * Need to modify the max size concept over here ... see geeksforgeeks...
     *
     * @param input
     * @return
     */
    public static char[][] calculateMaxSquareWithOnes(char input[][]) {

        if (null == input)
            return new char[0][0];


        int n = input.length;
        int m = input[0].length;

        if (n == 1 && m == 1)
            return (input[0][0] == '1' || input[0][0] == 1) ? new char[][]{{1}} : new char[0][0];

        int startI = -1;
        int startJ = -1;
        int endI = -1;
        int endJ = -1;
        int size = 0;

        // get Auxilary Arra yWith First Row & Col data
        int temp[][] = getAuxiliaryArrayWithFirstRowCol(input);

        // for rest of rows and column'MinStepsInfiniteGrid
        for (int i = 1; i < input.length; i++) {
            for (int j = 1; j < input[0].length; j++) {

                if (input[i][j] == '1') {
                    temp[i][j] = (1 + Math.min(temp[i - 1][j], Math.min(temp[i][j - 1], temp[i - 1][j - 1])));
                    if (size < temp[i][j]) {
                        size = temp[i][j];
                        endI = i;
                        endJ = j;
                        startI = i - size + 1;
                        startJ = j - size + 1;
                    }
                } else
                    temp[i][j] = 0;
            }
        }

        if (size != 0) {
            char result[][] = new char[size][size];
            for (int i = startI; i <= endI; i++)
                for (int j = startJ; j <= endJ; j++)
                    result[i - startI][j - startJ] = input[i][j];

            return result;
        }

        return new char[0][0];


    }

    private static int[][] getAuxiliaryArrayWithFirstRowCol(char input[][]) {
        int auxiliaryArray[][] = new int[input.length][input[0].length];

        for (int i = 0; i < input[0].length; i++)
            auxiliaryArray[0][i] = input[0][i] - '0';

        for (int i = 0; i < input.length; i++)
            auxiliaryArray[i][0] = input[i][0] - '0';

        return auxiliaryArray;

    }
}