package Java.LeetCode.LargetstRectangle;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-27
 * Description: https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * Area = width * height;
 * <p>
 * Find the largest rectangular area possible in a given histogram where the largest rectangle can be made of a
 * number of contiguous bars. For simplicity, assume that all bars have same width and the width is 1 unit.
 * For example, consider the following histogram with 7 bars of heights {6, 2, 5, 4, 5, 1, 6}. The largest
 * possible rectangle possible is 12 (see the below figure, the max area rectangle is highlighted in red)
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestHistogram {

    public static void main(String []args) {
        int input[] = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleAreaIn1D(input));

        int input2[] = {1, 1};
        System.out.println(largestRectangleAreaIn1D(input2));
    }

    public static int largestRectangleAreaIn1D(int[] heights) {

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
