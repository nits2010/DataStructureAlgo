package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.matrix._85;

import DataStructureAlgo.Java.LeetCode.LargetstRectangle.LargestRectangle_MaximalRectangle;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.MaxArea._84.LargestRectangleInHistogram_84;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/17/2024
 * Question Category: 85. Maximal Rectangle
 * Description:https://leetcode.com/problems/maximal-rectangle/description/
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 * <p>
 * Input: matrix = [["0"]]
 * Output: 0
 * Example 3:
 * <p>
 * Input: matrix = [["1"]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rows == matrix.length
 * cols == matrix[i].length
 * 1 <= row, cols <= 200
 * matrix[i][j] is '0' or '1'.
 * File reference
 * -----------
 * Duplicate {@link LargestRectangle_MaximalRectangle}
 * Similar {@link}
 * extension {@link LargestRectangleInHistogram_84}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @DynamicProgramming
 * @Stack
 * @Matrix
 * @MonotonicStack <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon
 * @Apple
 * @Adobe
 * @Bloomberg
 * @Facebook
 * @Indeed
 * @Microsoft
 * @Pinterest
 * @Samsung
 * @Uber
 * @VMware
 * @Wayfair <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MaximalRectangle_85 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}, 6));
        tests.add(test(new char[][]{{'0'}}, 0));
        tests.add(test(new char[][]{{'1'}}, 1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(char[][] matrix, int expected) {
        //add print here
        // CommonMethods.printTestOutcome(new String[]{"Prices", "Expected"}, true, prices, expected);

        int output;
        boolean pass, finalPass = true;

        Solution_UsingHistogram1 solutionUsingHistogram1 = new Solution_UsingHistogram1();
        output = solutionUsingHistogram1.maximalRectangle(matrix);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTest(new String[]{"UsingHistogram", "expected"}, pass, matrix, expected);

        Solution_UsingHistogram_OnFlyCalculate solutionUsingHistogramOnFlyCalculate = new Solution_UsingHistogram_OnFlyCalculate();
        output = solutionUsingHistogramOnFlyCalculate.maximalRectangle(matrix);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTest(new String[]{"UsingHistogram-OnFly", "expected"}, pass, matrix, expected);



        return finalPass;

    }

    static class Solution_UsingHistogram1 {
        public int maximalRectangle(char[][] matrix) {
            int r = matrix.length;
            int c = matrix[0].length;

            if (r == 1 && c == 1) {
                return matrix[0][0] == '1' ? 1 : 0;
            }

            //build histogram
            int[][] heights = buildHistogram(matrix);

            int maxArea = 0;

            for (int i = 0; i < r; i++) {
                maxArea = Math.max(maxArea, histogramArea(heights[i]));
            }
            return maxArea;
        }

        private int histogramArea(int[] arr) {
            int n = arr.length;
            int[] stack = new int[n];
            int top = -1;

            if (n == 1)
                return arr[0];

            int i = 0;
            int maxArea = 0;

            while (i < n) {

                if (top == -1 || arr[stack[top]] <= arr[i]) {
                    stack[++top] = i++;
                } else {
                    int bar = stack[top--];
                    int h = arr[bar];
                    int w = top == -1 ? i : i - stack[top] - 1;
                    maxArea = Math.max(maxArea, h * w);
                }

            }

            while (top >= 0) {
                int bar = stack[top--];
                int h = arr[bar];
                int w = top == -1 ? i : i - stack[top] - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            return maxArea;

        }


        private int[][] buildHistogram(char[][] matrix) {

            int r = matrix.length;
            int c = matrix[0].length;
            int[][] heights = new int[r][c];

            for (int ri = 0; ri < r; ri++) {
                for (int ci = 0; ci < c; ci++) {
                    if (matrix[ri][ci] == '1') {
                        if (ri == 0) {
                            heights[ri][ci] = 1;
                        } else {
                            heights[ri][ci] = (1 + heights[ri - 1][ci]);
                        }
                    }

                }
            }

            return heights;
        }
    }


    static class Solution_UsingHistogram_OnFlyCalculate {
        public int maximalRectangle(char[][] matrix) {
            int r = matrix.length;
            int c = matrix[0].length;

            if (r == 1 && c == 1) {
                return matrix[0][0] == '1' ? 1 : 0;
            }

            //build histogram & calculate
            return buildHistogram(matrix);

        }

        private int buildHistogram(char[][] matrix) {

            int r = matrix.length;
            int c = matrix[0].length;
            int[][] heights = new int[r][c];
            int maxArea = 0;
            for (int ri = 0; ri < r; ri++) {
                for (int ci = 0; ci < c; ci++) {
                    if (matrix[ri][ci] == '1') {
                        if (ri == 0) {
                            heights[ri][ci] = 1;
                        } else {
                            heights[ri][ci] = (1 + heights[ri - 1][ci]);
                        }
                    }

                }

                maxArea = Math.max(maxArea, histogramArea(heights[ri]));
            }

            return maxArea;
        }

        private int histogramArea(int[] arr) {
            int n = arr.length;
            int[] stack = new int[n];
            int top = -1;

            if (n == 1)
                return arr[0];

            int i = 0;
            int maxArea = 0;

            while (i < n) {

                if (top == -1 || arr[stack[top]] <= arr[i]) {
                    stack[++top] = i++;
                } else {
                    int bar = stack[top--];
                    int h = arr[bar];
                    int w = top == -1 ? i : i - stack[top] - 1;
                    maxArea = Math.max(maxArea, h * w);
                }

            }

            while (top >= 0) {
                int bar = stack[top--];
                int h = arr[bar];
                int w = top == -1 ? i : i - stack[top] - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            return maxArea;

        }


    }


    static class SolutionUsingBoundaries {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
                return 0;
            }
            int m = matrix.length;
            int n = matrix[0].length;

            int[] heights = new int[n];
            int[] leftBoundaries = new int[n];
            int[] rightBoundaries = new int[n];
            Arrays.fill(rightBoundaries, n);

            int maxRectangle = 0;

            for (char[] chars : matrix) {
                int left = 0;
                int right = n;

                updateHeightsAndLeftBoundaries(chars, heights, leftBoundaries, left);

                updateRightBoundaries(chars, rightBoundaries, right);

                maxRectangle = calculateMaxRectangle(heights, leftBoundaries, rightBoundaries, maxRectangle);
            }

            return maxRectangle;
        }

        private void updateHeightsAndLeftBoundaries(char[] row, int[] heights, int[] leftBoundaries, int left) {
            for (int j = 0; j < heights.length; j++) {
                if (row[j] == '1') {
                    heights[j]++;
                    leftBoundaries[j] = Math.max(leftBoundaries[j], left);
                } else {
                    heights[j] = 0;
                    leftBoundaries[j] = 0;
                    left = j + 1;
                }
            }
        }

        private void updateRightBoundaries(char[] row, int[] rightBoundaries, int right) {
            for (int j = rightBoundaries.length - 1; j >= 0; j--) {
                if (row[j] == '1') {
                    rightBoundaries[j] = Math.min(rightBoundaries[j], right);
                } else {
                    rightBoundaries[j] = right;
                    right = j;
                }
            }
        }

        private int calculateMaxRectangle(int[] heights, int[] leftBoundaries, int[] rightBoundaries, int maxRectangle) {
            for (int j = 0; j < heights.length; j++) {
                int width = rightBoundaries[j] - leftBoundaries[j];
                int area = heights[j] * width;
                maxRectangle = Math.max(maxRectangle, area);
            }
            return maxRectangle;
        }
    }
}
