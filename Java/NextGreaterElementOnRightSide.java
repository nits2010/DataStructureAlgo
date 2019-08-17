package Java;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
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
 */
public class NextGreaterElementOnRightSide {


    public static void main(String[] args) {
        int[] a = new int[]{12, 11, 10, 5, 6, 2, 30, 5, 3, 32, 2, 40};
        int greaterOnRightSide[] = immediateGreaterElementOnRightSide(a);
        Arrays.stream(greaterOnRightSide).forEach(ele -> System.out.println(ele + " "));
    }

    public static int[] immediateGreaterElementOnRightSide(int nums[]) {


        if (null == nums || nums.length == 0)
            return nums;

        Stack<Integer> stack = new Stack<>();
        int greaterOnRightSide[] = new int[nums.length];
        Arrays.fill(greaterOnRightSide, -1);//there is no greater element on right side of last element

        stack.push(0); //index

        for (int i = 1; i < nums.length; i++) {

            if (stack.isEmpty() || nums[stack.peek()] > nums[i])
                stack.push(i);
            else {

                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    greaterOnRightSide[stack.pop()] = i;
                }

                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            greaterOnRightSide[stack.pop()] = -1;
        }

        return greaterOnRightSide;
    }
}
