package Java;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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
 */
public class SmallerElementOnLeftSide {


    public static void main(String[] args) {
        int[] a = new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40};
        int greaterOnRightSide[] = immediateSmallerElementOnLeftSide(a);
        Arrays.stream(greaterOnRightSide).forEach(ele -> System.out.println(ele + " "));
    }

    public static int[] immediateSmallerElementOnLeftSide(int nums[]) {

        if (null == nums || nums.length == 0)
            return nums;

        Stack<Integer> stack = new Stack<>();
        int smallerOnLeftSide[] = new int[nums.length];
        smallerOnLeftSide[0] = -1; //there is no smaller element on left side of first element

        stack.push(0); //indexes

        for (int i = 1; i < nums.length; i++) {

            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();

            if (!stack.isEmpty()) {
                smallerOnLeftSide[i] = stack.peek();
            } else
                smallerOnLeftSide[i] = -1;

            stack.push(i);
        }


        return smallerOnLeftSide;
    }
}
