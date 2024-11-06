package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-24
 * Description: https://leetcode.com/problems/trapping-rain-water/
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * <p>
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * <p>
 * https://www.geeksforgeeks.org/trapping-rain-water/
 * <p>
 * {@link ContainerWithMostWater}
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println(" Extra space :" + trapWithExtraSpace(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println("Constant space :" + trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

    }

    /**
     * Runtime: 1 ms, faster than 98.39% of Java online submissions for Trapping Rain Water.
     * Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Trapping Rain Water.
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {

        int leftMax = 0, rightMax = 0;

        int totalWater = 0;
        int l = 0, r = height.length - 1;

        while (l < r) {

            if (height[l] < height[r]) {
                leftMax = Math.max(height[l], leftMax);
                totalWater += leftMax - height[l];
                l++;

            } else {
                rightMax = Math.max(height[r], rightMax);

                totalWater += rightMax - height[r];
                r--;

            }
        }
        return totalWater;
    }

    /**
     * {@link DataStructureAlgo.Java.companyWise.Amazon.MaximumDifferenceInIndex}
     *
     * @param height
     * @return
     */
    // Method for maximum amount of water
    public static int trapWithExtraSpace(int[] height) {
        int n = height.length;

        // left[i] contains height of tallest bar to the
        // left of i'th bar including itself
        int[] left = new int[n];

        // Right [i] contains height of tallest bar to
        // the right of ith bar including itself
        int[] right = new int[n];

        // Initialize result
        int water = 0;

        // Fill left-array
        left[0] = height[0];
        for (int i = 1; i < n; i++)
            left[i] = Math.max(left[i - 1], height[i]);

        // Fill right-array
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--)
            right[i] = Math.max(right[i + 1], height[i]);

        // Calculate the accumulated water element by element
        // consider the amount of water on i'th bar, the
        // amount of water accumulated on this particular
        // bar will be equal to min(left[i], right[i]) - arr[i] .
        for (int i = 0; i < n; i++)
            water += Math.min(left[i], right[i]) - height[i];

        return water;
    }

}
