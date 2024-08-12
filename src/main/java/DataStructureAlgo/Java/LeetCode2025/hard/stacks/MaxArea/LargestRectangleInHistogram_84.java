package DataStructureAlgo.Java.LeetCode2025.hard.stacks.MaxArea;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date:08/08/24
 * Question Category: 84. Largest Rectangle in Histogram @hard
 * Description: https://leetcode.com/problems/largest-rectangle-in-histogram
 * https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 * <p>
 * <p>
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial <a href="https://www.youtube.com/watch?v=zx5Sw9130L0">...</a>
 */

public class LargestRectangleInHistogram_84 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{2, 1, 5, 6, 2, 3}, 10);
        test &= test(new int[]{2, 4}, 4);
        System.out.println(test ? "All test passed" : "Test failed");
    }

    private static boolean test(int[] input, int expected) {
        System.out.println("\n\nInput : " + Arrays.toString(input) + " expected: " + expected);
        LargestRectangleInHistogram.SolutionBruteForce bruteForce = new LargestRectangleInHistogram.SolutionBruteForce();
        LargestRectangleInHistogram.SolutionUsingStacks stacks = new LargestRectangleInHistogram.SolutionUsingStacks();
        int bruteForceOutput = bruteForce.largestRectangleArea(input);
        int stacksOutput = stacks.largestRectangleArea(input);
        System.out.println("Brute Force: " + bruteForceOutput + " | " + "Stacks " + stacksOutput);

        boolean testBF = bruteForceOutput == expected;
        boolean testStacks = stacksOutput == expected;

        System.out.println("TestBF | stacks | stacks2:  " + testBF + " | " + testStacks);
        return testBF && testStacks;

    }
}


class LargestRectangleInHistogram {

    /**
     * Time Limit Exceeded
     * O(n^2)
     */
    static class SolutionBruteForce {
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0)
                return 0;
            if (heights.length == 1)
                return heights[0];

            int maxArea = Integer.MIN_VALUE;

            for (int i = 0; i < heights.length; i++) {

                int candidateBarHeight = heights[i];
                int left = 0, right = 0;

                //get left index
                int j;
                for (j = i - 1; j >= 0; j--) {
                    if (heights[j] < candidateBarHeight)
                        break;
                }
                left = j;

                for (j = i + 1; j < heights.length; j++) {
                    if (candidateBarHeight > heights[j])
                        break;
                }
                right = j;

                maxArea = Math.max(maxArea, candidateBarHeight * (right - left - 1));

            }
            return maxArea;
        }
    }

    static class SolutionUsingStacks {
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0)
                return 0;
            if (heights.length == 1)
                return heights[0];

            int maxArea = Integer.MIN_VALUE;
            int[] stack = new int[heights.length];
            int top = -1;

            int i = 0;
            stack[++top] = i++;


            while (i < heights.length) {

                //if current bar is bigger than previous bar, then previous bar
                //histogram still forming
                if (top == -1 || heights[stack[top]] <= heights[i])
                    stack[++top] = i++;
                else {
                    //if current bar is smaller than previous bar, then previous bar
                    //histogram has completed. Since post the current bar, the previous bar won't be able to make
                    // any more histogram.
                    int currentHistToEvaluate = stack[top--];

                    //if there is no bar before the previous bar, means previous bar is the biggest bar
                    //till previous bar, hence width would be i.
                    //otherwise, there is a bar before that, that will contribute to the width of the histogram
                    int width = top == -1 ? i : i - stack[top] - 1;
                    maxArea = Math.max(maxArea, heights[currentHistToEvaluate] * width);

                }
            }

            //calcuate histgram area for remaining bars in stack
            while (top >= 0) {
                int currentHistToEvaluate = stack[top--];
                int width = top == -1 ? i : i - stack[top] - 1;
                maxArea = Math.max(maxArea, heights[currentHistToEvaluate] * width);
            }
            return maxArea;

        }
    }


}