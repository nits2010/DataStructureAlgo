package DataStructureAlgo.Java.LeetCode.nextGreaterElement;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.nonleetcode.NextGreaterElementOnRightSide;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 24/09/19
 * Description: https://leetcode.com/problems/next-greater-element-i/
 * 496. Next Greater Element I [Easy]
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * <p>
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 * <p>
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 * For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 * For number 1 in the first array, the next greater number for it in the second array is 3.
 * For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 * For number 2 in the first array, the next greater number for it in the second array is 3.
 * For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * Note:
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 * <p>
 */
public class NextGreaterElementI {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}, new int[]{-1, 3, -1});
        test &= test(new int[]{2, 4}, new int[]{1, 2, 3, 4}, new int[]{3, -1});
        test &= test(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1}, new int[]{-1, -1, -1, -1});
        test &= test(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, -1});
        System.out.println("\nTest:" + (test ? "Passed" : "Failed"));
    }

    private static boolean test(int[] nums1, int[] nums2, int[] expected) {
        System.out.println("\nNum1:" + CommonMethods.toString(nums1));
        System.out.println("Num2:" + CommonMethods.toString(nums2));
        System.out.println("Expected            :" + CommonMethods.toString(expected));

        int[] bruteForce = NextGreaterElementIUBruteForce.nextGreaterElement(nums1, nums2);
        int[] obtainedUsingNextGreaterElement = NextGreaterElementIUsingNGERight.nextGreaterElement(nums1, nums2);
        System.out.println("using bruteForce    :" + CommonMethods.toString(bruteForce));
        System.out.println("using NGE           :" + CommonMethods.toString(obtainedUsingNextGreaterElement));

        return Arrays.equals(expected, obtainedUsingNextGreaterElement) && Arrays.equals(expected, bruteForce);

    }

}


/**
 * Runtime: 2 ms, faster than 96.80% of Java online submissions for Next Greater Element I.
 * Memory Usage: 37.7 MB, less than 96.30% of Java online submissions for Next Greater Element I.
 */
class NextGreaterElementIUBruteForce {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null)
            return null;

        Map<Integer, Integer> elements = elements(nums2);

        final int[] output = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            if (elements.containsKey(nums1[i])) {
                int j = elements.get(nums1[i]);
                while (j < nums2.length) {
                    if (nums2[j] > nums1[i]) {
                        output[i] = nums2[j];
                        break;
                    }
                    j++;
                }
                if (j == nums2.length)
                    output[i] = -1;
            } else output[i] = -1;
        }
        return output;
    }

    private static Map<Integer, Integer> elements(int[] nums) {
        final Map<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0)
            return map;

        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        return map;
    }
}

/**
 * Sub-routine {@link NextGreaterElementOnRightSide}
 * Runtime: 3 ms, faster than 80.20% of Java online submissions for Next Greater Element I.
 * Memory Usage: 37.6 MB, less than 96.30% of Java online submissions for Next Greater Element I.
 */
class NextGreaterElementIUsingNGERight {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null)
            return null;

        Map<Integer, Integer> nextGreaterElementOnRightSide = immediateNextGreaterElementOnRightSide(nums2);

        final int[] output = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            output[i] = nextGreaterElementOnRightSide.getOrDefault(nums1[i], -1);
        }
        return output;
    }

    private static Map<Integer, Integer> immediateNextGreaterElementOnRightSide(int[] nums) {
        final Map<Integer, Integer> map = new HashMap<>();
        if (nums == null || nums.length == 0)
            return map;

        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]); //index

        int i = 1;

        while (i < nums.length) {

            while (!stack.isEmpty() && stack.peek() < nums[i])
                map.put(stack.pop(), nums[i]);

            stack.push(nums[i]);
            i++;
        }
        while (!stack.isEmpty())
            map.put(stack.pop(), -1);

        return map;
    }
}