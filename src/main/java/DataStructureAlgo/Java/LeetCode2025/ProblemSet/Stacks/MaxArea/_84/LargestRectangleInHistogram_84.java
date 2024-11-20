package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.MaxArea._84;

import DataStructureAlgo.Java.LeetCode.LargetstRectangle.LargestHistogram;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date:08/08/24
 * Question Category: 84. Largest Rectangle in Histogram @hard
 * Description: https://leetcode.com/problems/largest-rectangle-in-histogram
 * https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 * <p>
 * Given an array of integer heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
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
 * Duplicate {@link LargestHistogram}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks._739.DailyTemperatures_739}
 * <p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @Stack
 * @MonotonicStack <p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Adobe
 * @Facebook
 * @Uber
 * @Adobe
 * @Bloomberg
 * @Flipkart
 * @Google
 * @Microsoft
 * @Twitter
 * @WalmartLabs
 * @Editorial <a href="https://www.youtube.com/watch?v=zx5Sw9130L0">...</a>
 * https://www.youtube.com/watch?v=vcv3REtIvEo&t=14s
 */

public class LargestRectangleInHistogram_84 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{2, 1, 5, 6, 2, 3}, 10));
        tests.add(test(new int[]{2, 4}, 4));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] input, int expected) {
        CommonMethods.printTestOutcome(new String[]{"matrix", "Expected"}, true, input, expected);

        int output;
        boolean pass, finalPass = true;

        LargestRectangleInHistogram.SolutionBruteForce bruteForce = new LargestRectangleInHistogram.SolutionBruteForce();
        output = bruteForce.largestRectangleArea(input);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BruteForce", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        LargestRectangleInHistogram.SolutionUsingBoundaries_LeftRightSmaller solutionUsingBoundariesLeftRightSmaller = new LargestRectangleInHistogram.SolutionUsingBoundaries_LeftRightSmaller();
        output = solutionUsingBoundariesLeftRightSmaller.largestRectangleArea(input);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Boundaries-LeftRightSmaller", "Pass"}, false, output, pass ? "PASS" : "FAIL");



        LargestRectangleInHistogram.SolutionUsingStacks stacks = new LargestRectangleInHistogram.SolutionUsingStacks();
        output = stacks.largestRectangleArea(input);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"StackS", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

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
                int left, right;

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


    /**
     * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks._739.DailyTemperatures.SolutionWithoutUsingStacks}
     */
    static class SolutionUsingBoundaries_LeftRightSmaller {
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0)
                return 0;
            int n = heights.length;
            if (n == 1)
                return heights[0];

            int maxArea = Integer.MIN_VALUE;

            //The left boundary is the index of the nearest bar to the left that is shorter.
            int[] leftBoundaries = new int[n];

            //The right boundary is the index of the nearest bar to the right that is shorter.
            int[] rightBoundaries = new int[n];

            leftBoundaries[0] = -1; // there is no smaller bar to the left
            rightBoundaries[n - 1] = n; // there is no smaller bar to the right

            for (int i = 1, j = n - 2; i < n; i++, j--) {

                //calculate left and right boundaries

                int left = i - 1; // the index of the nearest bar to the left that is shorter
                while (left >= 0 && heights[left] >= heights[i])
                    left = leftBoundaries[left]; //Jump to the next smaller element on the left

                int right = j + 1;
                while (right < n && heights[right] >= heights[j])
                    right = rightBoundaries[right]; //Jump to the next smaller element on the right

                leftBoundaries[i] = left;
                rightBoundaries[j] = right;


            }

            for (int i = 0; i < n; i++) {
                int width = rightBoundaries[i] - leftBoundaries[i] - 1; // Width of the rectangle
                int area = heights[i] * width;     // Area of the rectangle
                maxArea = Math.max(maxArea, area);
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

                //if the current bar is bigger than previous bar, then previous bar
                //histogram still forming
                if (top == -1 || heights[stack[top]] <= heights[i])
                    stack[++top] = i++;
                else {
                    //If the current bar is smaller than previous bar, then previous bar
                    //histogram has completed. Since posting the current bar, the previous bar won't be able to make
                    // any more histogram.
                    int currentHistToEvaluate = stack[top--];

                    //if there is no bar before the previous bar, means previous bar is the biggest bar
                    //till previous bar, hence width would be i.
                    //otherwise, there is a bar before that; that will contribute to the width of the histogram
                    int width = top == -1 ? i : i - stack[top] - 1;
                    maxArea = Math.max(maxArea, heights[currentHistToEvaluate] * width);

                }
            }

            //calculate histogram area for remaining bars in stack
            while (top >= 0) {
                int currentHistToEvaluate = stack[top--];
                int width = top == -1 ? i : i - stack[top] - 1;
                maxArea = Math.max(maxArea, heights[currentHistToEvaluate] * width);
            }
            return maxArea;

        }
    }


}