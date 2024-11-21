package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.matrix._gfg._27;

import DataStructureAlgo.Java.LeetCode.LargetstRectangle.MaximumSumRectangle;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming._baseProblems.maximumSubArray.Kadens._53.MaximumSubarray_53;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/19/2024
 * Question Title: Maximum sum rectangle in a 2D matrix | DP-27
 * Link: https://www.geeksforgeeks.org/maximum-sum-rectangle-in-a-2d-matrix-dp-27/
 * Description: Given a 2D array, find the maximum sum submatrix in it. For example,
 * in the following 2D array, the maximum sum submatrix is highlighted with blue rectangle and sum of all elements in this submatrix is 29.
 * <p>
 * This problem is mainly an extension of the Largest Sum Contiguous Subarray for 1D array.
 * File reference
 * -----------
 * Duplicate {@link MaximumSumRectangle}
 * Similar {@link}
 * extension {@link MaximumSubarray_53.Kadens.KadensAlgorithm}
 * DP-BaseProblem {@link MaximumSubarray_53.Kadens.KadensAlgorithm}
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @Matrix
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumSumRectangleInA2DMatrix_KadensAlgorithm2D_27 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, 2, -1, -4, -20}, {-8, -3, 4, 2, 1}, {3, 8, 10, 1, 3}, {-4, -1, 1, 7, -6}}, new int[]{29, 1, 3, 1, 3}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] mat, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"mat", "Expected"}, true, mat, expected);

        int[] output;
        boolean pass, finalPass = true;

        KadensAlgorithm2D kadensAlgorithm2D = new KadensAlgorithm2D();
        output = kadensAlgorithm2D.maximumSumRectangle(mat);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        KadensAlgorithm2D_Optimized_PrefixSum_WithSubarray kadensAlgorithm2DOptimizedWithSubarray = new KadensAlgorithm2D_Optimized_PrefixSum_WithSubarray();
        output = kadensAlgorithm2DOptimizedWithSubarray.maximumSumRectangle(mat);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output-Optimized-Prefix sum", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    /**
     * T: O(col^2*(row + row)) = O(col^2*row)
     * S: O(row)
     */
    static class KadensAlgorithm2D {
        public int[] maximumSumRectangle(int[][] matrix) {

            int row = matrix.length;
            int col = matrix[0].length;
            int maxSum = Integer.MIN_VALUE;

            int top = -1, bottom = -1, left = -1, right = -1;
            int[] temp = new int[row];

            // O(col)
            for (int leftColumn = 0; leftColumn < col; leftColumn++) {

                Arrays.fill(temp, 0);

                // O(col)
                for (int rightColumn = leftColumn; rightColumn < col; rightColumn++) {

                    // O(row)
                    for (int i = 0; i < row; i++) {
                        temp[i] += matrix[i][rightColumn];
                    }

                    // O(row)
                    int[] current = kadens1D(temp);

                    if (maxSum < current[0]) {

                        maxSum = current[0];
                        top = current[1];
                        bottom = current[2];
                        left = leftColumn;
                        right = rightColumn;
                    }
                }

            }
            return new int[]{maxSum, top, bottom, left, right};

        }

        private int[] kadens1D(int[] temp) {
            int[] max = new int[]{Integer.MIN_VALUE, -1, -1}; // maxSum, left/top, right/bottom

            int currentSum = 0;

            int start = 0, end = 0;
            int maxNeg = Integer.MIN_VALUE;
            int maxNegIndex = -1;
            for (int i = 0; i < temp.length; i++) {

                currentSum += temp[i];

                if (currentSum > max[0]) {
                    max[0] = currentSum;
                    max[1] = start;
                    max[2] = i;
                }

                if (currentSum < 0) {
                    currentSum = 0;
                    start = i + 1;
                }

                if (temp[i] < 0 && maxNeg < temp[i]) {
                    maxNeg = temp[i];
                    maxNegIndex = i;
                }

            }

            if (max[2] == -1) {
                max[0] = maxNeg;
                max[1] = maxNegIndex;
                max[2] = maxNegIndex;
            }

            return max;
        }

    }


    static class KadensAlgorithm2D_Optimized_PrefixSum_WithSubarray {
        public int[] maximumSumRectangle(int[][] matrix) {

            int row = matrix.length;
            int col = matrix[0].length;
            int maxSum = Integer.MIN_VALUE;
            int top = -1, bottom = -1, left = -1, right = -1;

            //get the prefix sum of the matrix
            //O(row*col)
            for (int i = 0; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] += matrix[i][j - 1];
                }
            }

            // O(col)
            for (int leftColumn = 0; leftColumn < col; leftColumn++) {

                // O(col)
                for (int rightColumn = leftColumn; rightColumn < col; rightColumn++) {
                    int[] current = kadens1D(matrix, rightColumn, leftColumn, row);
                    if (maxSum < current[0]) {

                        maxSum = current[0];
                        top = current[1];
                        bottom = current[2];
                        left = leftColumn;
                        right = rightColumn;
                    }

                }

            }
            return new int[]{maxSum, top, bottom, left, right};

        }

        private int[] kadens1D(int[][] matrix, int rightColumn, int leftColumn, int row) {
            int[] max = new int[]{Integer.MIN_VALUE, -1, -1}; // maxSum, left/top, right/bottom

            int currentSum = 0;

            int start = 0, end = 0;
            int maxNeg = Integer.MIN_VALUE;
            int maxNegIndex = -1;
            for (int i = 0; i < row; i++) {
                int sum = matrix[i][rightColumn] - (leftColumn == 0 ? 0 : matrix[i][leftColumn - 1]); // calculate the prefix sum at this point

                currentSum += sum;

                if (currentSum > max[0]) {
                    max[0] = currentSum;
                    max[1] = start;
                    max[2] = i;
                }

                if (currentSum < 0) {
                    currentSum = 0;
                    start = i + 1;
                }

                if (sum < 0 && maxNeg < sum) {
                    maxNeg = sum;
                    maxNegIndex = i;
                }

            }

            if (max[2] == -1) {
                max[0] = maxNeg;
                max[1] = maxNegIndex;
                max[2] = maxNegIndex;
            }

            return max;
        }

    }

    static class KadensAlgorithm2D_Optimized_PrefixSum {
        public int maximumSumRectangle(int[][] matrix) {

            int row = matrix.length;
            int col = matrix[0].length;
            int maxSum = Integer.MIN_VALUE;

            //get the prefix sum of the matrix
            //O(row*col)
            for (int i = 0; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] += matrix[i][j - 1];
                }
            }

            // O(col)
            for (int leftColumn = 0; leftColumn < col; leftColumn++) {

                // O(col)
                for (int rightColumn = leftColumn; rightColumn < col; rightColumn++) {
                    int maxSumSub = Integer.MIN_VALUE;
                    int currentSum = 0;
                    //O(row)
                    for (int i = 0; i < row; i++) {
                        int sum = matrix[i][rightColumn] - (leftColumn == 0 ? 0 : matrix[i][leftColumn - 1]); // calculate the prefix sum at this point

                        currentSum = Math.max(currentSum + sum, sum);
                        maxSumSub = Math.max(maxSumSub, currentSum);
                    }

                    if (maxSum < maxSumSub) {

                        maxSum = maxSumSub;
                    }
                }

            }
            return maxSum;

        }


    }
}
