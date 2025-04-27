package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._11;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/5/2025
 * Question Title: 11. Container With Most Water
 * Link:
 * Description: You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 * <p>
 * Input: height = [1,1]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @Greedy <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Adobe
 * @Facebook
 * @Airbnb
 * @Apple
 * @Bloomberg
 * @ByteDance
 * @GoldmanSachs
 * @Google
 * @Lyft
 * @Oracle
 * @Uber
 * @VMware
 * @WalmartLabs
 * @Yahoo <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ContainerWithMostWater_11 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49));
        tests.add(test(new int[]{1, 1}, 1));
        tests.add(test(new int[]{4, 3, 2, 1, 4}, 16));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] height, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Height", "Expected"}, true, height, expected);

        int output = 0;
        boolean pass, finalPass = true;

        SolutionTwoPointer solutionTwoPointer = new SolutionTwoPointer();
        SolutionTwoPointerOptimized solutionTwoPointerOptimized = new SolutionTwoPointerOptimized();
        output = solutionTwoPointer.maxArea(height);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Two Pointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionTwoPointerOptimized.maxArea(height);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Two Pointer Optimized", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionTwoPointer {
        public int maxArea(int[] height) {
            int maxWater = 0;
            int n = height.length;
            int i = 0, j = n - 1;

            while (i < j) {
                int h, w;
                if (height[i] <= height[j]) {
                    h = height[i];
                    w = j - i;

                    i++;
                } else {
                    h = height[j];
                    w = j - i;

                    j--;
                }

                maxWater = Math.max(maxWater, h * w);
            }
            return maxWater;

        }
    }

    static class SolutionTwoPointerOptimized {
        public int maxArea(int[] height) {
            int maxWater = 0;
            int n = height.length;
            int i = 0, j = n - 1;

            while (i < j) {
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                maxWater = Math.max(maxWater, h * w);

                //skip all smaller bars as they will always make water less
                while (i < j && height[i] <= h)
                    i++;

                while (i < j && height[j] <= h)
                    j--;

            }
            return maxWater;

        }
    }
}
