package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._42;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/5/2024
 * Question Category: 42. Trapping Rain Water
 * Description: https://leetcode.com/problems/trapping-rain-water/description/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.TrappingRainWater}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @TwoPointers
 * @DynamicProgramming
 * @Stack
 * @MonotonicStack <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Affirm
 * @Airbnb
 * @Amazon
 * @Apple
 * @Atlassian
 * @Bloomberg
 * @ByteDance
 * @Citadel
 * @Databricks
 * @Dataminr
 * @ElectronicArts
 * @Facebook
 * @Flipkart
 * @GoldmanSachs
 * @Google
 * @Grab
 * @HBO
 * @Huawei
 * @Lyft
 * @Microsoft
 * @Nvidia
 * @Oracle
 * @PalantirTechnologies
 * @Qualtrics
 * @Salesforce
 * @Snapchat
 * @Tableau
 * @Twitter
 * @Uber
 * @Visa
 * @WalmartLabs
 * @Wish
 * @Yahoo
 * @Yandex
 * @Zenefits
 * @Zoho <p>
 * -----
 * @Editorial <p><p> https://youtu.be/C8UjlJZsHBw
 * -----
 * @OptimalSoltuion
 */
public class TrappingRainWater_42 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6));
        tests.add(test(new int[]{4, 2, 0, 3, 2, 5}, 9));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] heights, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Heights", "Expected"}, true, heights, expected);

        int output;
        boolean pass, finalPass = true;

        UsingMonotonicStack usingMonotonicStack = new UsingMonotonicStack();
        output = usingMonotonicStack.trap(heights);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Using Monotonic Stack", "Pass"}, false, output, pass ? "Passed" : "Failed");

        UsingPrefixArray usingPrefixArray = new UsingPrefixArray();
        output = usingPrefixArray.trap(heights);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Using Prefix Array", "Pass"}, false, output, pass ? "Passed" : "Failed");

        UsingPrefixArrayConstantSpace usingPrefixArrayConstantSpace = new UsingPrefixArrayConstantSpace();
        output = usingPrefixArrayConstantSpace.trap(heights);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Using Prefix Array Constant Space", "Pass"}, false, output, pass ? "Passed" : "Failed");

        return finalPass;

    }

    static class UsingMonotonicStack {
        public int trap(int[] height) {

            //A unit of water can only be trapped between two buildings such that these buildings
            //heights are greater than current building height.
            //The maximum amount of water that can be trapped at current building depends on the maximum height on left side building
            //and right side building. The max amount of water would be
            // min(leftMaxHeight, rightMaxHeight) - currentHeight; This will be the total height of water.
            // The width would also depend on the left and right boundary of the current building, which is right - left - 1 (exclude current)

            // Hence, while scanning from left to right the height array, we can maintain a left max height stack which is
            // monotonic decreasing stack, as we always need max height on the left side of current building
            // the right side max height can be calculated by current processing building

            //maintain a monotonic stack in decreasing order.

            Stack<Integer> stack = new Stack<>(); //contain the index of the height array

            int maxTrappedWater = 0;
            for (int i = 0; i < height.length; i++) {

                //If the right side building ( current building ) height is greater than stack peek,
                // then we need to calculate the water trapped by stack.peek. As we are scanning from left to right,
                // so stack would have either left max or no boundary on the left side.
                // Post a building on the right side greater than stack peek, water can be trapped on the left side only since we hit the right side boundary.

                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {

                    int currentBuildingHeight = height[stack.pop()];

                    //if there is no building on left side of current, then it can't trap any water on it
                    if (stack.isEmpty()) {
                        break;
                    }

                    //get the left side building max height
                    int leftMaxHeight = height[stack.peek()];
                    int rightMaxHeight = height[i];

                    //calculate the water trapped.
                    int maxHeightOfWaterTrapped = Math.min(leftMaxHeight, rightMaxHeight) - currentBuildingHeight;
                    int maxWidthOfWaterTrapped = i - stack.peek() - 1;

                    //the maximum water would be remaining height on top of the current building.
                    maxTrappedWater += maxHeightOfWaterTrapped * maxWidthOfWaterTrapped;
                }

                //push the current element in stack
                stack.push(i);
            }


            return maxTrappedWater;
        }
    }


    /**
     * We can notice that, the water trapped would be the min of leftMaxHeight and rightMaxHeight. Means for trapping water for this building, if we know the
     * leftMax and rightMax, then we can easily calculate the total water trapped.
     * LeftMax and rightMax can be calculated using a prefix array
     */
    static class UsingPrefixArray {

        public int trap(int[] height) {
            int n = height.length;

            // left[i] contains the height of the tallest bar to the left of ith bar including itself
            int[] leftMax = new int[n];

            // Right [i] contains height of tallest bar to  the right of ith bar including itself
            int[] rightMax = new int[n];

            //there is no boundary on the left side, hence it `0` element height is the max
            leftMax[0] = height[0];

            //there is no boundary on the right side, hence it `n-1` element height is the max
            rightMax[n - 1] = height[n - 1];

            for (int i = 1, j = n - 2; i < n; i++, j--) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);
                rightMax[j] = Math.max(rightMax[j + 1], height[j]);
            }

            int maxTrappedWater = 0;
            // Calculate the accumulated water element by element
            // consider the amount of water on i'th bar, the
            // amount of water accumulated on this particular
            // bar will be equal to min(leftMax[i], rightMax[i]) - height[i] .

            for (int i = 1; i < n - 1; i++) { // as there cannot be, any water can be trapped on top of right end or left end
                int currentBuildingHeight = height[i];
                maxTrappedWater += Math.min(leftMax[i], rightMax[i]) - currentBuildingHeight;
            }
            return maxTrappedWater;
        }
    }

    /**
     * One thing can be notice in prefix array implementation, if we know the leftMax and rightMax, then we can easily calculate the total water trapped for current building.
     * And for getting leftMox, we just need max value on the left side of `i`, and similarly we just need rightMax, the right side of `i`.
     * <p>
     * Hence, we can calculate the leftMax while running from left to right, and rightMax while running from right to left and calculate the total water trapped for current `i` building.
     * We can simply run two pointers from both ends, however, as there could be possibilities that there is a taller building on the right side.
     * Since we are always taking min(leftMax, rightMax) which means that whatever the rightMax for the current element if height[left] < height[right] does not matter, as leftMax will always be minimum.
     * And our original formula was Min(left, right) - height[i], this will be simplified as leftMax - height[i] or rightMax - height[i] if height[left] > height[right].
     *
     *
     */
    static class UsingPrefixArrayConstantSpace {
        public int trap(int[] height) {
            int n = height.length;

            int lb = 0;  // left boundary
            int rb = n - 1; //right boundary
            int leftMax = 0;
            int rightMax = 0;
            int maxTrappedWater = 0;


            //run left and right pointers from both end

            while (lb < rb) {

                //if the current building at left boundary is smaller than the right boundary,
                // then we need to either calculate the water trapped or get the leftMax
                if (height[lb] < height[rb]) {
                    leftMax = Math.max(leftMax, height[lb]);

                    // if the last leftMax is bigger then current left boundary then water would be trapped
                    maxTrappedWater += leftMax - height[lb];
                    lb++;
                } else {

                    rightMax = Math.max(rightMax, height[rb]);
                    maxTrappedWater += rightMax - height[rb];
                    rb--;
                }
            }
            return maxTrappedWater;

        }


    }
}

