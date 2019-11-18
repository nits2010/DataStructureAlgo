package Java.LeetCode;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-19
 * Description: https://leetcode.com/problems/container-with-most-water/
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two
 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * <p>
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * <p>
 * Example:
 * <p>
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * <p>
 * Similar to {@link TrappingRainWater}
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        test(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49);
        test(new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0}, 16);


    }

    private static void test(int[] height, int expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(height) + " expected :" + expected);
        Solution solution = new Solution();
        System.out.println("brute :" + solution.maxAreaBruteForce(height));
        System.out.println("maxAreaOptimized :" + solution.maxAreaOptimized(height));
        System.out.println("maxAreaOptimized V2 :" + solution.maxAreaOptimizedV2(height));

    }


    private static class Solution {
        public int maxArea(int[] height) {

            return maxAreaOptimized(height);
        }

        /**
         * Brute Force:
         * 1. For each left, find the maximum right which is greater then element of this left
         * 2. Total water would be the difference of these indexes * min height
         * <p>
         * Runtime: 209 ms, faster than 11.37% of Java online submissions for Container With Most Water.
         * Memory Usage: 39.8 MB, less than 94.87% of Java online submissions for Container With Most Water.
         *
         * @param height
         * @return
         */
        private int maxAreaBruteForce(int[] height) {
            if (height == null || height.length == 0)
                return 0;

            int maxWater = 0;

            for (int left = 0; left < height.length; left++) {

                for (int right = left + 1; right < height.length; right++) {

                    maxWater = Math.max(maxWater, Math.min(height[left], height[right]) * (right - left));
                }
            }

            return maxWater;

        }

        /**
         * We can simply traverse from both ends, since to maximize the area, the height and width should be maximize.
         * Algo:
         * 1. Start form left most and right most to maximize the width
         * 2. Calculate the maxWater with current left,right as height.
         * 3. Move ahead on smaller side of height to maximize the height
         * <p>
         * Runtime: 2 ms, faster than 94.35% of Java online submissions for Container With Most Water.
         * Memory Usage: 39.9 MB, less than 94.87% of Java online submissions for Container With Most Water.
         *
         * @param height
         * @return
         */
        private int maxAreaOptimized(int[] height) {
            if (height == null || height.length == 0)
                return 0;
            int maxWater = 0;

            int left = 0;
            int right = height.length - 1;

            while (left < right) {

                maxWater = Math.max(maxWater, Math.min(height[left], height[right]) * (right - left));

                if (height[left] < height[right])
                    left++;
                else
                    right--;
            }


            return maxWater;
        }

        /**
         * Skipped similar height values on left and right, as they won't give more water as either left or right would reduce
         * <p>
         * Runtime: 2 ms, faster than 94.51% of Java online submissions for Container With Most Water.
         * Memory Usage: 39.6 MB, less than 95.51% of Java online submissions for Container With Most Water.
         *
         * @param height
         * @return
         */
        private int maxAreaOptimizedV2(int[] height) {
            if (height == null || height.length == 0)
                return 0;
            int maxWater = 0;

            int left = 0;
            int right = height.length - 1;

            while (left < right) {

                maxWater = Math.max(maxWater, Math.min(height[left], height[right]) * (right - left));

                if (height[left] < height[right]) {
                    left++;

                    while (left < right && height[left] <= height[left - 1]) left++;


                } else {

                    right--;
                    while (right > left && height[right] <= height[right + 1]) right--;


                }
            }


            return maxWater;
        }
    }


}
