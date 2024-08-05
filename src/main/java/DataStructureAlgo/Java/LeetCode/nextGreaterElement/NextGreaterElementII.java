package DataStructureAlgo.Java.LeetCode.nextGreaterElement;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.nonleetcode.NextGreaterElementOnRightSide;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 24/09/19
 * Description: https://leetcode.com/problems/next-greater-element-ii/
 * 503. Next Greater Element II [Medium]
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to
 * its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist,
 * output -1 for this number.
 * <p>
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * <p>
 * Input:  [1,2,1,3,0]
 * Output: [2,3,3,-1,1]
 * <p>
 * Note: The length of given array won't exceed 10000.
 * <p>
 * Check solution {@link NextGreaterElementII1Scan}
 */
public class NextGreaterElementII {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 1, 1, 1}, new int[]{-1, -1, -1, -1});
        test &= test(new int[]{3, -2, -1}, new int[]{-1, -1, 3});
        test &= test(new int[]{1, 2, 1}, new int[]{2, -1, 2});
        test &= test(new int[]{1, 2, 1, 3, 0}, new int[]{2, 3, 3, -1, 1});
        test &= test(new int[]{3, 9, 1, 8, 3, 0, 4}, new int[]{9, -1, 8, 9, 4, 4, 9});
        test &= test(new int[]{3, 9, 1, 8, 13, 0, 4}, new int[]{9, 13, 8, 13, -1, 4, 9});
        System.out.println("\nTest:" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[] nums, int[] expected) {
        System.out.println("----------------");
        System.out.println("Nums:               :" + GenericPrinter.toString(nums));
        System.out.println("Expected            :" + GenericPrinter.toString(expected));

        int[] nge3Scan = NextGreaterElementIIThreeScan.nextGreaterElements(nums);
        int[] nge1Scan = NextGreaterElementII1Scan.nextGreaterElements(nums);
        int[] nge1Scan2 = NextGreaterElementII1ScanExplicitStack.nextGreaterElements(nums);
        System.out.println("Obtained3scan       :" + GenericPrinter.toString(nge3Scan) + "; Pass:" + Arrays.equals(nge3Scan, expected));
        System.out.println("Obtained1scan       :" + GenericPrinter.toString(nge1Scan) + "; Pass:" + Arrays.equals(nge1Scan, expected));
        System.out.println("Obtained1scan2      :" + GenericPrinter.toString(nge1Scan2) + "; Pass:" + Arrays.equals(nge1Scan2, expected));

        return Arrays.equals(nge3Scan, expected) && Arrays.equals(nge1Scan, expected) && Arrays.equals(nge1Scan2, expected);

    }
}

/**
 * We'll utilize the {@link NextGreaterElementOnRightSide}.
 * Algorithm:
 * 1. Build NGE array using {@link NextGreaterElementOnRightSide}.
 * 2. Then for remaining elements { whose greater element we did not find on right}. We'll start searching greater element from left
 * * 2.1 if current element is not calculated { whose greater element we did not find on right} then search from start of the array
 * *   till you find an element greater then this element.
 */
class NextGreaterElementIIThreeScan {
    /**
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;


        int[] nge = nextGreaterElementsRight(nums);
        nextGreaterElementsLeft(nums, nge);

        return nge;
    }

    //O(n)/ O(n)
    private static int[] nextGreaterElementsRight(int[] nums) {
        int[] nge = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int i = 1;
        while (i < nums.length) {

            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                nge[stack.pop()] = nums[i];

            stack.push(i);
            i++;
        }
        while (!stack.isEmpty())
            nge[stack.pop()] = Integer.MAX_VALUE;

        return nge;
    }


    /**
     * Search greater element on left for given element
     *
     * @param nums
     * @param nge  O(n)
     */
    private static void nextGreaterElementsLeft(int[] nums, int[] nge) {
        int i = nums.length - 1;
        int k = 0;
        while (k < i) {
            //If we did not found the greater element on right for this
            if (nge[i] == Integer.MAX_VALUE) {
                //find on left side
                if (nums[k] > nums[i]) {
                    nge[i] = nums[k];
                    i--;
                } else
                    k++; //this element is smaller then current element move forward

            } else {
                if (nge[i] == Integer.MAX_VALUE)
                    nge[i] = -1;
                i--;
            }

        }

        //settle remaining element
        i = 0;
        while (i < nums.length) {
            if (nge[i] == Integer.MAX_VALUE)
                nge[i] = -1;
            i++;
        }

    }

}

/**
 * This idea is similar to above. To avoid another implicit scan.
 * We can assume that we have concatenate the given array at the end { means put same array element at the end of array. [1,2,3] make it [1,2,3,1,2,3]}
 * Now the problem is simply searching greater element on right side.
 * <p>
 * Runtime: 26 ms, faster than 61.52% of Java online submissions for Next Greater Element II.
 * Memory Usage: 41.3 MB, less than 100.00% of Java online submissions for Next Greater Element II.
 */
class NextGreaterElementII1Scan {
    /**
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;


        int[] nge = new int[nums.length];
        Arrays.fill(nge, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int i = 1;
        while (i < 2 * nums.length) {
            int num = nums[i % nums.length];

            while (!stack.isEmpty() && nums[stack.peek()] < num)
                nge[stack.pop()] = num;

            if (i < nums.length)
                stack.push(i % nums.length);
            i++;
        }

        return nge;
    }

}


/**
 * Same as above, just using explicit stack
 * Runtime: 4 ms, faster than 97.99% of Java online submissions for Next Greater Element II.
 * Memory Usage: 40.3 MB, less than 100.00% of Java online submissions for Next Greater Element II.
 */
class NextGreaterElementII1ScanExplicitStack {
    /**
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;


        int[] nge = new int[nums.length];
        Arrays.fill(nge, -1);

        int[] stack = new int[nums.length];
        int top = 0;
        stack[top++] = 0;

        int i = 1;
        while (i < 2 * nums.length) {
            int num = nums[i % nums.length];

            while (top > 0 && nums[stack[top - 1]] < num)
                nge[stack[--top]] = num;

            if (i < nums.length)
                stack[top++] = i % nums.length;
            i++;
        }

        return nge;
    }

}

