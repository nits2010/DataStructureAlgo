package DataStructureAlgo.Java.nonleetcode;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-11
 * Description: https://www.geeksforgeeks.org/next-greater-element/
 * Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element for an element x is the first greater element on the right side of x in array. Elements for which no greater element exist, consider next greater element as -1.
 * <p>
 * Examples:
 * <p>
 * For any array, rightmost element always has next greater element as -1.
 * For an array which is sorted in decreasing order, all elements have next greater element as -1.
 * For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.
 * Element       NGE
 * 4      -->   5
 * 5      -->   25
 * 2      -->   25
 * 25     -->   -1
 * d) For the input array [13, 7, 6, 12}, the next greater elements for each element are as follows.
 * <p>
 * Element        NGE
 * 13      -->    -1
 * 7       -->     12
 * 6       -->     12
 * 12      -->     -1
 * similar {@link SmallerElementOnLeftSide}
 */
public class NextGreaterElementOnRightSide {


    public static void main(String[] args) {
        test(new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40}, new int[]{6, 6, 6, 4, 6, 6, 9, 9, 9, 11, 11, -1});
    }

    private static void test(int[] nums, int[] expected) {
        System.out.println("\nNums:" + GenericPrinter.toString(nums) + "\nExpected:" + GenericPrinter.toString(expected));
        System.out.println("Obtained                :" + GenericPrinter.toString(immediateGreaterElementOnRightSide(nums)));
        System.out.println("ObtainedSimplified      :" + GenericPrinter.toString(immediateGreaterElementOnRightSideSimplified(nums)));


    }

    /*
    https://leetcode.com/problems/daily-temperatures/discuss/389422/3-solution-or-Explanation-or-100-beat-or-Java
    Find the next greater element on right side
    Example:` [12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40]`
    Lets ask a question, what is the next greater element on
    a[0]=12 => a[6] = 30,
    a[1]= 11  => a[6] = 30
    a[6] = 30 => a[9] = 32.
    **Brute force:** For each element look on right side, find the first greater element
    **Optimize:**   Instead of solving from left to right, we can solve for right to left
    This means if you know what are the smaller element on left side of a element 'i' then you can simply say that element at a[i] would be the next greater element for all those element which are on left side of it and smaller then this.

    The best data structure which can hold all the element on left side is stack, as it provide O(1) time complexity {since an element can be push and pop at most 2 times}.

    **Algorithm:**
    1. Create a empty stack and push first element {index} in stack: Because there is no element on left side of first element
    2. if you hit a condition when top element of stack is < current element, then current element is NGE for all stack element that satisfy above condition
    3. Process remaining element as -1 because we did not found any greater element right side

     */
    public static int[] immediateGreaterElementOnRightSide(int[] nums) {


        if (null == nums || nums.length == 0)
            return nums;


        Stack<Integer> stack = new Stack<>(); //index
        int[] greaterOnRightSide = new int[nums.length];
        Arrays.fill(greaterOnRightSide, -1);//there is no greater element on right side of last element

        stack.push(0); //index

        for (int i = 1; i < nums.length; i++) {

            //push if empty or stack top element is greater then nums[i], as we have not seen greater element of previous seen element so far
            if (stack.isEmpty() || nums[stack.peek()] > nums[i])
                stack.push(i);

            else {
                //this element nums[i] is greater element of for top of the stack, means this is potential greater element of previously seen element
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    greaterOnRightSide[stack.pop()] = i;
                }

                stack.push(i);
            }
        }

        //push for remaining element -1 as we have not seen any greater elements for them
        while (!stack.isEmpty()) {
            greaterOnRightSide[stack.pop()] = -1;
        }

        return greaterOnRightSide;
    }

    public static int[] immediateGreaterElementOnRightSideSimplified(int[] nums) {


        if (null == nums || nums.length == 0)
            return nums;


        Stack<Integer> stack = new Stack<>(); //index
        int[] greaterOnRightSide = new int[nums.length];
        Arrays.fill(greaterOnRightSide, -1);//there is no greater element on right side of last element

        stack.push(0); //index

        for (int i = 1; i < nums.length; i++) {

            //this element nums[i] is greater element of for top of the stack, means this is potential greater element of previously seen element
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                greaterOnRightSide[stack.pop()] = i;


            stack.push(i);
        }


        //push for remaining element -1 as we have not seen any greater elements for them
        while (!stack.isEmpty()) {
            greaterOnRightSide[stack.pop()] = -1;
        }

        return greaterOnRightSide;
    }
}
