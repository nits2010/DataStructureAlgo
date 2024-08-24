package DataStructureAlgo.Java.LeetCode.LargetstRectangle;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-27
 * Description: https://leetcode.com/problems/maximal-rectangle/
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public class LargestRectangle {

    public static void main(String []args) {
        char[][] input =
                {
                        {'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'}
                };


        System.out.println("Input");
        CommonMethods.print(input);
        System.out.println(maximalRectangle(input));


        char[][] input2 =
                {
                        {'1'}, {'0'}, {'1'}, {'1'}, {'1'}, {'1'}, {'0'}
                };

        System.out.println("Input");
        CommonMethods.print(input2);
        System.out.println(maximalRectangle(input2));
    }


    public static int maximalRectangle(char[][] matrix) {

        if (null == matrix || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;

        if (n == 1 && m == 1) {
            return matrix[0][0] == '1' ? 1 : 0;
        }

        int maxArea = 0;

        buildHistogram(matrix);

        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, histogramArea(matrix[i]));

        }


        return maxArea;
    }

    private static int histogramArea(char[] heights) {
        if (heights == null || heights.length == 0)
            return 0;

        int n = heights.length;
        if (n == 1)
            return heights[0] % '0';

        Stack<Integer> histogram = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        int area = 0;
        histogram.push(0); //contains indexes

        int i = 1;
        while (i < n) {

            if (histogram.isEmpty() || heights[histogram.peek()] <= heights[i]) {
                histogram.push(i);
                i++;
            } else {

                int currentHistToEvaluate = histogram.pop();

                area = heights[currentHistToEvaluate] % '0' * (histogram.isEmpty() ? i : i - histogram.peek() - 1);
                maxArea = Math.max(area, maxArea);
            }
        }

        while (!histogram.isEmpty()) {
            int currentHistToEvaluate = histogram.pop();

            area = heights[currentHistToEvaluate] % '0' * (histogram.isEmpty() ? i : i - histogram.peek() - 1);
            maxArea = Math.max(area, maxArea);
        }


        return maxArea;
    }

    private static void buildHistogram(char[][] matrix) {

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1')
                    matrix[i][j] = (char) (matrix[i][j] + matrix[i - 1][j]);

            }
        }
    }


    /**
     * Int matrix
     *
     * @param matrix
     * @return
     */
    public static int maximalRectangle(int[][] matrix) {

        if (null == matrix || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;

        if (n == 1 && m == 1) {
            return matrix[0][0] == 1 ? 1 : 0;
        }

        int maxArea = 0;

        buildHistogram(matrix);

        for (int[] ints : matrix) {
            maxArea = Math.max(maxArea, histogramArea(ints));

        }


        return maxArea;
    }

    private static void buildHistogram(int[][] matrix) {

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1)
                    matrix[i][j] = (matrix[i][j] + matrix[i - 1][j]);

            }
        }
    }

    /**
     * {@link LargestHistogram}
     * @param heights
     * @return
     */
    private static int histogramArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;

        int n = heights.length;
        if (n == 1)
            return heights[0];

        Stack<Integer> histogram = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        int area ;
        histogram.push(0); //contains indexes

        int i = 1;
        while (i < n) {

            if (histogram.isEmpty() || heights[histogram.peek()] <= heights[i]) {
                histogram.push(i);
                i++;
            } else {

                int currentHistToEvaluate = histogram.pop();

                area = heights[currentHistToEvaluate] * (histogram.isEmpty() ? i : i - histogram.peek() - 1);
                maxArea = Math.max(area, maxArea);
            }
        }

        while (!histogram.isEmpty()) {
            int currentHistToEvaluate = histogram.pop();

            area = heights[currentHistToEvaluate] * (histogram.isEmpty() ? i : i - histogram.peek() - 1);
            maxArea = Math.max(area, maxArea);
        }


        return maxArea;
    }


}
