package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.matrix._1277;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/28/2024
 * Question Category: 1277. Count Square Submatrices with All Ones
 * Description: https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/?envType=daily-question&envId=2024-10-27
 * Given a, m * n matrix of ones and zeros, return how many square sub-matrices have all ones.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix =
 * [
 * [0,1,1,1],
 * [1,1,1,1],
 * [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 * <p>
 * Input: matrix =
 * [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Array
 * @DynamicProgramming
 * @Matrix <p><p>
 * Company Tags
 * -----
 * @Google
 * @Bloomberg <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class CountSquareSubMatricesWithAllOnes_1277 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}}, 15);
        test &= test(new int[][]{{1, 0, 1}, {1, 1, 0}, {1, 1, 0}}, 7);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[][] matrix, int expected) {
        CommonMethods.printTestOutcome(new String[]{"Matrix", "Expected"}, true, matrix, expected);
        int output;
        boolean pass, finalPass = true;

        Solution_WithMemory solutionWithMemory = new Solution_WithMemory();
        output = solutionWithMemory.countSquares(matrix);
        pass = output == expected;
        CommonMethods.printTestOutcome(new String[]{"WithMemory", "Pass"}, false, output, pass ? "Pass" : "Fail");
        finalPass &= pass;

        Solution_WithoutMemory solutionWithoutMemory = new Solution_WithoutMemory();
        output = solutionWithoutMemory.countSquares(matrix);
        pass = output == expected;
        CommonMethods.printTestOutcome(new String[]{"WithoutMemory", "Pass"}, false, output, pass ? "Pass" : "Fail");
        finalPass &= pass;

        return finalPass;
    }

    static class Solution_WithMemory {
        public int countSquares(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;

            int[][] dp = new int[row][col];
            int count = fill(matrix, dp);

            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {

                    if (matrix[i][j] == 1) {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                        count += dp[i][j];
                    }
                }
            }

            return count;
        }

        private int fill(int[][] matrix, int[][] dp) {
            int count = dp[0][0] = matrix[0][0];
            for (int j = 1; j < matrix[0].length; j++) {
                count += dp[0][j] = matrix[0][j];
            }

            for (int i = 1; i < matrix.length; i++) {
                count += dp[i][0] = matrix[i][0];
            }

            return count;


        }
    }


    static class Solution_WithoutMemory {
        public int countSquares(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            int count = 0;

            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {

                    if (matrix[i][j] == 1) {
                        count += matrix[i][j] = 1 + Math.min(Math.min(matrix[i - 1][j - 1], matrix[i - 1][j]), matrix[i][j - 1]);
                    }
                }
            }

            count += matrix[0][0];
            for (int j = 1; j < matrix[0].length; j++) {
                count += matrix[0][j];
            }

            for (int i = 1; i < matrix.length; i++) {
                count += matrix[i][0];
            }

            return count;
        }

    }

}
