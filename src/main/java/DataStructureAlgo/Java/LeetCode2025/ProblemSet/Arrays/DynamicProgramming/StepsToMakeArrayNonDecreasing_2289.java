package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 8/8/2024
 * Question Category: 2289. Steps to Make Array Non-decreasing @medium
 * Description: https://leetcode.com/problems/steps-to-make-array-non-decreasing
 * You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
 * <p>
 * Return the number of steps performed until nums becomes a non-decreasing array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
 * Output: 3
 * Explanation: The following are the steps performed:
 * - Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
 * - Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
 * - Step 3: [5,4,7,11,11] becomes [5,7,11,11]
 * [5,7,11,11] is a non-decreasing array. Therefore, we return 3.
 * Example 2:
 * <p>
 * Input: nums = [4,5,7,7,13]
 * Output: 0
 * Explanation: nums is already a non-decreasing array. Therefore, we return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * <p>
 *
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @Array
 * @LinkedList
 * @Stack
 * @MonotonicStack
 * @DynamicProgramming
 * @medium
 *
 * Company Tags
 * -----

 *
 *
 * @Editorial <a href="https://www.youtube.com/watch?v=XirZ8GtWxWA">...</a>
 */
public class StepsToMakeArrayNonDecreasing_2289 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{10, 1, 2, 3, 4, 5, 6, 1, 2, 3}, 6);
        test &= test(new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11}, 3);
        test &= test(new int[]{4, 5, 7, 7, 13}, 0);
        test &= test(new int[]{5, 4, 3, 2, 1}, 1);
        test &= test(new int[]{5, 5, 5, 5, 5, 5, 5}, 0);
        test &= test(new int[]{5, 5, 5, 5, 5, 5, 5, 6}, 0);
        test &= test(new int[]{5, 5, 5, 5, 5, 5, 5, 6, 3}, 1);
        System.out.println("---------------------------------------------");
        System.out.println(test ? "Passed All Tests" : "Failed All Tests");
    }

    private static boolean test(int[] input, int expected) {
        System.out.println("\ninput = " + Arrays.toString(input) + " expected = " + expected);
        StepsToMakeArrayNonDecreasing.SolutionUsingStackAndMemory solutionUsingStackAndMemory = new StepsToMakeArrayNonDecreasing.SolutionUsingStackAndMemory();
        int solutionUsingStackAndMemoryResult = solutionUsingStackAndMemory.totalSteps3(input);
        boolean testResult = solutionUsingStackAndMemoryResult == expected;
        System.out.println("result = " + solutionUsingStackAndMemoryResult + " testResult = " + testResult);
        return testResult;
    }
}

class StepsToMakeArrayNonDecreasing {

    /**
     * To understand this logic, please watch <a href="https://www.youtube.com/watch?v=XirZ8GtWxWA">...</a>.
     * Brief explanation:
     * => 10, 1, 2, 3, 4, 5, 6, 1, 2, 3
     * => 10, 1, 2, 3, 4, 5, 6 [1,2,3] -> 1
     * => 10 {6 operation}
     */
    static class SolutionUsingStackAndMemory {
        public int totalSteps(int[] nums) {
            if (nums == null || nums.length <= 1)
                return 0;

            //stack with element and operation it needed to reach here
            final Stack<Integer[]> stack = new Stack<>();

            //start from back
            int i = nums.length - 1;
            stack.push(new Integer[]{nums[i], 0});
            i = i - 1;
            int max = Integer.MIN_VALUE;

            while (i >= 0) {

                int operation = 0;
                //nums[i - 1] > nums[i] => nums[i] > stack.peek
                while (!stack.isEmpty() && stack.peek()[0] < nums[i]) {
                    //to remove this element, we need to perform a delete operation
                    //or this element might have been removed in stack.peek operation
                    operation = Math.max(operation + 1, stack.peek()[1]);
                    max = Math.max(max, operation);
                    stack.pop();
                }

                //push number of operations it took for this element
                stack.push(new Integer[]{nums[i], operation});
                i--;
            }

            while (!stack.isEmpty()) {
                max = Math.max(max, stack.peek()[1]);
                stack.pop();
            }
            return max;


        }

        public int totalSteps2(int[] nums) {
            if (nums == null || nums.length <= 1)
                return 0;

            //stack with element and operation it needed to reach here
            final Stack<Integer> stack = new Stack<>();
            final int []memory = new int[nums.length];

            //start from back
            int i = nums.length - 1;
            stack.push(i);
            memory[i] = 0;

            i = i - 1;
            int max = Integer.MIN_VALUE;

            while (i >= 0) {


                //nums[i - 1] > nums[i] => nums[i] > stack.peek
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    //to remove this element, we need to perform a delete operation
                    //or this element might have been removed in stack.peek operation
                    memory[i] = Math.max(memory[i]+ 1, memory[stack.peek()]);
                    max = Math.max(max, memory[i]);
                    stack.pop();
                }

                //push number of operations it took for this element
                stack.push(i);
                i--;
            }

            while (!stack.isEmpty()) {
                max = Math.max(max, memory[stack.peek()]);
                stack.pop();
            }
            return max;


        }

        /**
         * explicit stack; fastest
         * 6 ms Beats 100.00%
         * @param nums
         * @return
         */
        public int totalSteps3(int[] nums) {
            if (nums == null || nums.length <= 1)
                return 0;

            //stack with element and operation it needed to reach here
            final int[] stack = new int[nums.length];
            int top = -1;
            final int []memory = new int[nums.length];

            //start from back
            int i = nums.length - 1;
            stack[++top] = i;
            memory[i] = 0;

            i = i - 1;
            int max = Integer.MIN_VALUE;

            while (i >= 0) {


                //nums[i - 1] > nums[i] => nums[i] > stack.peek
                while (top >=0 && nums[stack[top]] < nums[i]) {
                    //to remove this element, we need to perform a delete operation
                    //or this element might have been removed in stack.peek operation
                    memory[i] = Math.max(memory[i]+ 1, memory[stack[top]]);
                    max = Math.max(max, memory[i]);
                    top--;
                }

                //push number of operations it took for this element
                stack [ ++top] = i;
                i--;
            }

            while (top >=0) {
                max = Math.max(max,  memory[stack[top]]);
                top--;
            }
            return max;


        }
    }
}