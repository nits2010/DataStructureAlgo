package Java.LeetCode.bitsManipulation.single.number;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-28
 * Description:https://leetcode.com/problems/single-number-iii/
 * <p>
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 * <p>
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * <p>
 * {@link Java.companyWise.Grab.MissingElements}
 */
public class SingleNumberIII {

    public static void main(String[] args) {

        test(new int[]{1, 2, 1, 3, 2, 5}, new int[]{3, 5});
        test(new int[]{4, 2, 1, 4, 2, 5}, new int[]{1, 5});
        test(new int[]{0, 0, 0, 1, 5}, new int[]{1, 5});
        test(new int[]{-4, -4, -2, -2, 1, 5}, new int[]{1, 5});
        test(new int[]{-4, -4, -2, -2, -1, -5}, new int[]{-1, -5});
        test(new int[]{4, 2, -1, 4, 2, -5}, new int[]{-1, -5});
    }

    private static void test(int[] nums, int[] expected) {
        System.out.println("Input :" + GenericPrinter.toString(nums) + " Expected :" + GenericPrinter.toString(expected));
        System.out.println("Two nunbers :" + GenericPrinter.toString(singleNumber(nums)));
    }

    /**
     * Idea leverages from {@link Java.companyWise.Grab.MissingElements}
     * <p>
     * Xoring all elements will cancelled out duplicates
     * After that you'll end up with bits of both the single occurring element.
     * <p>
     * divide the bits in two group by utilizing the right most set bit of xor. Because rightmost set bit will be different in
     * both of the number {because of xor, if they were same 0 or 1 => 0^0 -> 0 and 1^1-> 0}
     * <p>
     * https://leetcode.com/articles/single-number-iii/
     * <p>
     * Runtime: 1 ms, faster than 99.67% of Java online submissions for Single Number III.
     * Memory Usage: 37.9 MB, less than 100.00% of Java online submissions for Single Number III.
     *
     * @param nums
     * @return
     */
    public static int[] singleNumber(int[] nums) {

        int bitMask = 0;

        for (int e : nums)
            bitMask ^= e;

        int setBit = bitMask & (~(bitMask - 1));

        int x = 0;
        int y = 0;
        for (int e : nums)
            if ((e & setBit) != 0)
                x ^= e;
            else
                y ^= e;


        return new int[]{x, y};
    }

    public static int[] singleNumberImpl2(int nums[]) {
        int xor = 0;
        int[] ret = new int[]{0, 0};
        for (int n : nums) xor ^= n;
        xor &= -xor;
        for (int n : nums) ret[(xor & n) == 0 ? 0 : 1] ^= n;
        return ret;
    }
}
