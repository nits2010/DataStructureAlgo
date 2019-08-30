package Java.companyWise.Amazon;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-30
 * Description: https://www.geeksforgeeks.org/replace-every-element-with-the-greatest-on-right-side/
 * <p>
 * Given an array of integers, replace every element with the next greatest element (greatest element on the right side) in the array.
 * Since there is no element next to the last element, replace it with -1. For example, if the array is {16, 17, 4, 3, 5, 2},
 * then it should be modified to {17, 5, 5, 5, 2, -1}.
 */
public class ReplaceGreatestElementOnRightSide {

    public static void main(String[] args) {

        test(new int[]{16, 17, 4, 3, 5, 2}, new int[]{17, 5, 5, 5, 2, -1});
        test(new int[]{18, 17, 14, 23, 15, 22}, new int[]{23, 23, 23, 22, 22, -1});
    }

    private static void test(int[] nums, int[] expected) {
        System.out.println("\n input :" + Printer.toString(nums) + " expected :" + Printer.toString(expected));
        System.out.println("\n Obtained :" + Printer.toString(greatestElementOnRightSide(nums)));
    }

    /**
     * Simply scan from right to left, to find maximum on right, compare the current element with so far greater element
     * O(n) / O(1) -> we can do this in-place too
     *
     * @param nums
     * @return
     */
    public static int[] greatestElementOnRightSide(int nums[]) {
        if (nums == null || nums.length == 0)
            return nums;

        int n = nums.length;
        int greatestOnRightSide[] = new int[n];

        greatestOnRightSide[n - 1] = -1;

        for (int i = n - 2; i >= 0; i--) {

            greatestOnRightSide[i] = Math.max(greatestOnRightSide[i + 1], nums[i + 1]);
        }

        return greatestOnRightSide;
    }
}
