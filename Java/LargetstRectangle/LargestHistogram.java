package Java.LargetstRectangle;

import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-27
 * Description:
 * Area = width * height;
 * <p>
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestHistogram {

    public static void main(String args[]) {
        int input[] = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(input));

        int input2[] = {1, 1};
        System.out.println(largestRectangleArea(input2));
    }

    public static int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0)
            return 0;

        int n = heights.length;
        if (n == 1)
            return heights[0];

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
