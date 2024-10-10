package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks._962;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date:10/10/24
 * Question Category: 962. Maximum Width Ramp
 * Description: https://leetcode.com/problems/maximum-width-ramp/description/?envType=daily-question&envId=2024-10-10
 * A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.
 * <p>
 * Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [6,0,8,2,1,5]
 * Output: 4
 * Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
 * Example 2:
 * <p>
 * Input: nums = [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5 * 104
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
 * @medium
 * @Array
 * @Stack
 * @MonotonicStack
 * @TwoPointers <p>
 * Company Tags
 * -----
 * @Google
 * @Editorial https://leetcode.com/problems/maximum-width-ramp/editorial/?envType=daily-question&envId=2024-10-10
 */

public class MaximumWidthRamp_962 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{6, 0, 8, 2, 1, 5}, 4);
        test &= test(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}, 7);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] nums, int expected) {
        System.out.println("---------------------------------");
        System.out.println("Input :" + Arrays.toString(nums) + " expected :" + expected);

        int output;
        boolean pass;
        boolean finalPass = true;
        SolutionSorting solutionSorting = new SolutionSorting();
        SolutionStack solutionStack = new SolutionStack();
        SolutionTwoPointers solutionTwoPointers = new SolutionTwoPointers();
        SolutionTwoPointersOptimized solutionTwoPointersOptimized = new SolutionTwoPointersOptimized();

        output = solutionSorting.maxWidthRamp(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Sorting: Output :" + output + " Pass :" + pass);

        output = solutionStack.maxWidthRamp(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Stack: Output :" + output + " Pass :" + pass);

        output = solutionTwoPointers.maxWidthRamp(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Two pointer: Output :" + output + " Pass :" + pass);

        output = solutionTwoPointersOptimized.maxWidthRamp(nums);
        pass = output == expected;
        finalPass &= pass;
        System.out.println("Two pointer optimized: Output :" + output + " Pass :" + pass);


        return finalPass;
    }

    static class SolutionSorting {
        public int maxWidthRamp(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            int n = nums.length;
            Integer[] index = new Integer[n];
            for (int i = 0; i < nums.length; i++)
                index[i] = i;

            //sort index array based on nums[i] and nums[j] follow the nums[i] <= nums[j] constraint
            Arrays.sort(index, (i, j) -> nums[i] == nums[j] ? i - j : nums[i] - nums[j]);

            int minIndex = n;
            int maxWidth = 0; //max width of ramp

            for (int i : index) {
                maxWidth = Math.max(maxWidth, i - minIndex); //calculate the width of ramp, edge case when i=0 and minIndex=n then width = 0-n = -n which is not valid
                // Or i = n-1 then width = n-1 - n = -1 which is not valid
                minIndex = Math.min(maxWidth, i);
            }


            return maxWidth;
        }
    }

    /**
     * Approach:Check 962.md file
     */
    static class SolutionStack {
        public int maxWidthRamp(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;

            int n = nums.length;
            int[] stack = new int[n];
            int top = -1;

            int i = 0;

            //create a Monotonic decreasing Stack of index
            //nums: [6,0,8,2,1,5]
            //stack: [0:6, 1:0] post 1 index, all element are greater then element at index 1
            while (i < n) {

                if (top == -1 || nums[stack[top]] > nums[i])
                    stack[++top] = i;
                i++;
            }

            //run from back to find an index j, at which nums[stack[i]] <= nums[j]
            //Since stack is in decreasing order, we will able to process the first index which is less then nums[j], which will give minimum width
            //and then popping stack element would increase the width.

            int j = n - 1;
            int maxWidth = 0;
            while (j >= 0 && top >= 0) {

                //find the smallest index 'i' for which nums[i] <= nums[j]
                while (top >= 0 && nums[stack[top]] <= nums[j]) {
                    i = stack[top];
                    maxWidth = Math.max(maxWidth, j - i);
                    top--;
                }
                j--;

            }

            return maxWidth;
        }
    }

    static class SolutionTwoPointers {
        public int maxWidthRamp(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;

            //we need to find the index of element on right side having greater value then the current element
            //nums: [6,0,8,2,1,5]
            //rightMax : [2:8,2:8,2:8,5:5,5:5,5:5]
            //leftMin:   [0:6,1:0,1:0,1:0,1:0,1:0]
            //width:     [2,2,2,4,4,4]
            int n = nums.length;

            //store the value of element
            int[] leftMin = new int[n];  //left most minimum element
            int[] rightMax = new int[n]; //right most maximum element

            leftMin[0] = nums[0];
            rightMax[n - 1] = nums[n - 1];

            for (int i = 1, j = n - 2; i < n && j >= 0; j--, i++) {
                leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
                rightMax[j] = Math.max(rightMax[j + 1], nums[j]);
            }

            int maxWidth = 0;
            for (int i = 0, j = 0; i < n && j < n; ) {
                if (leftMin[i] <= rightMax[j]) {
                    maxWidth = Math.max(maxWidth, j - i);
                    j++;
                } else
                    i++;

            }
            return maxWidth;
        }

    }

    static class SolutionTwoPointersOptimized {
        public int maxWidthRamp(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;

            //we need to find the index of element on right side having greater value then the current element
            //nums: [6,0,8,2,1,5]
            //rightMax : [2:8,2:8,2:8,5:5,5:5,5:5]
            //leftMin: [0:6,1:0,1:0,1:0,1:0,1:0]
            int n = nums.length;

            //store the value of element
            int[] rightMax = new int[n]; //right most maximum element

            rightMax[n - 1] = nums[n - 1];

            for (int i = 1, j = n - 2; i < n && j >= 0; j--, i++) {
                rightMax[j] = Math.max(rightMax[j + 1], nums[j]);
            }

            int left = 0;
            int right = 0;
            int maxWidth = 0;
            while (right < n) {

                //find the left most index where nums[left] <= rightMax[right]
                while (left < right && nums[left] > rightMax[right])
                    left++;

                maxWidth = Math.max(maxWidth, right - left);
                right++; //move right


            }
            return maxWidth;

        }


    }
}
