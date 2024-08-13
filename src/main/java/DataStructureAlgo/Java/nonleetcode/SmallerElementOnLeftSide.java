package DataStructureAlgo.Java.nonleetcode;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-11
 * Description: https://www.geeksforgeeks.org/find-the-nearest-smaller-numbers-on-left-side-in-an-array/
 * <p>
 * Given an array of integers, find the nearest smaller number for every element such that the smaller element is on left side.
 * Examples:
 * <p>
 * Input:  arr[] = {1, 6, 4, 10, 2, 5}
 * Output:         {_, 1, 1,  4, 1, 2}
 * First element ('1') has no element on left side. For 6,
 * there is only one smaller element on left side '1'.
 * For 10, there are three smaller elements on left side (1,
 * 6 and 4), nearest among the three elements is 4.
 * <p>
 * Input: arr[] = {1, 3, 0, 2, 5}
 * Output:        {_, 1, _, 0, 2}
 * <p>
 * similar {@link NextGreaterElementOnRightSide}
 */
public class SmallerElementOnLeftSide {


    public static void main(String[] args) {
        test(new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40}, new int[]{-1, -1, -1, -1, 3, -1, 5, 5, 5, 8, -1, 10});

    }

    private static void test(int[] nums, int[] expected) {
        System.out.println("\nNums:" + CommonMethods.toString(nums) + "\nExpected:" + CommonMethods.toString(expected));
        System.out.println("Obtained:" + CommonMethods.toString(immediateSmallerElementOnLeftSide(nums)));

    }

    public static int[] immediateSmallerElementOnLeftSide(int nums[]) {

        if (null == nums || nums.length == 0)
            return nums;

        Stack<Integer> stack = new Stack<>();//indexes
        int[] smallerOnLeftSide = new int[nums.length];
        smallerOnLeftSide[0] = -1; //there is no smaller element on left side of first element

        stack.push(0);

        for (int i = 1; i < nums.length; i++) {

            //pop all the greater & equal element on left side of nums[i]
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();

            //if there is element on the stack, then top element is smaller left element of nums[i]
            if (!stack.isEmpty()) {
                smallerOnLeftSide[i] = stack.peek();
            } else
                //there is no smaller element of this nums[i]
                smallerOnLeftSide[i] = -1;

            stack.push(i);
        }


        return smallerOnLeftSide;
    }
}
