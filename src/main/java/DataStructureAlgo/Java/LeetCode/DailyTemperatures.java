package DataStructureAlgo.Java.LeetCode;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.nonleetcode.NextGreaterElementOnRightSide;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 24/09/19
 * Description: https://leetcode.com/problems/daily-temperatures/
 * 739. Daily Temperatures
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * <p>
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 * <p>
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 * <p>
 * Similar to {@link NextGreaterElementOnRightSide}
 * @Editorial https://leetcode.com/problems/daily-temperatures/discuss/389422/3-solution-or-Explanation-or-100-beat-or-Java
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        test &= test(new int[]{73, 74, 75, 9, 90, 66, 87, 56, 89, 90, 76, 71, 69, 72, 76, 73}, new int[]{1, 1, 2, 1, 0, 1, 2, 1, 1, 0, 0, 2, 1, 1, 0, 0});

        System.out.println("\nTest:" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[] T, int[] expected) {
        System.out.println("\nT:" + CommonMethods.toString(T));
        System.out.println("Expected    :" + CommonMethods.toString(expected));
        int[] warmer2 = dailyTemperatures2(T);
        int[] warmer1 = dailyTemperatures(T);

        System.out.println("Obtained1   :" + CommonMethods.toString(warmer1));
        System.out.println("Obtained2   :" + CommonMethods.toString(warmer2));
        return Arrays.equals(expected, warmer1) && Arrays.equals(expected, warmer2);

    }

    /**
     * Using Implicit stack
     * <p>
     * This is similar to {@link NextGreaterElementOnRightSide}
     * the only difference is we don't need to put the element but the distance of greater element on right side from current element
     * Distance can be calculated as 'i' as current element index and 'j' is element on right side which is next greater then 'i'
     * distance = j-i
     * <p>
     * O(n)
     * Runtime: 40 ms, faster than 76.75% of Java online submissions for Daily Temperatures.
     * Memory Usage: 42.8 MB, less than 91.53% of Java online submissions for Daily Temperatures.
     *
     * @param T
     * @return
     */
    public static int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0)
            return T;

        final int[] warmer = new int[T.length];

        final Stack<Integer> stack = new Stack<>();
        stack.push(0); //index

        int j = 1;
        while (j < T.length) {

            while (!stack.isEmpty() && T[stack.peek()] < T[j]) {
                int i = stack.pop();
                warmer[i] = j - i;
            }
            stack.push(j);
            j++;
        }
        //you don't need to run for remaining element as we need to put 0 and by default array initialized with 0
        return warmer;
    }


    /**
     * Using explicit stack
     * Runtime: 3 ms, faster than 99.97% of Java online submissions for Daily Temperatures.
     * Memory Usage: 41.8 MB, less than 98.31% of Java online submissions for Daily Temperatures.
     *
     * @param T
     * @return
     */
    public static int[] dailyTemperatures2(int[] T) {
        if (T == null || T.length == 0)
            return T;

        final int[] warmer = new int[T.length];

        final int[] stack = new int[T.length];
        int top = 0;
        stack[top++] = 0; //index

        int j = 1;
        while (j < T.length) {

            while (top > 0 && T[stack[top - 1]] < T[j]) {
                int i = stack[--top];
                warmer[i] = j - i;
            }
            stack[top++] = j;
            j++;
        }
        //you don't need to run for remaining element as we need to put 0 and by default array initialized with 0
        return warmer;
    }

    public int[] dailyTemperatures3(int[] T) {
        int n = T.length, next = 0;
        int[] stack = new int[n];
        int[] warmer = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            next = i + 1;

            //find the index of next greater element on right side of i
            while (next < n && T[next] <= T[i])
                next = stack[next]; // Find the next warmer or  hit boundary

            stack[i] = next;

            //if we found an element on right side greater then i'th element
            if (next != n)
                warmer[i] = next - i;
        }
        return warmer;
    }
}
