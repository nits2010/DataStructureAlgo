package Java.LeetCode;

import Java.HelpersToPrint.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-14
 * Description: https://leetcode.com/problems/product-of-array-except-self/
 * Given an array nums of n integers where n > 1,  return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 * [Facebook]
 */
public class ProductArrayExceptSelf {
    public static void main(String[] args) {
        test(new int[]{1, 0});
        test(new int[]{1, 2, 3, 4});
        test(new int[]{1, 2, 0, 4});
        test(new int[]{0, 2, 0, 4});
        test(new int[]{0, 0, 0, 0});
        test(new int[]{-1, -2, -3, -4});
        test(new int[]{-1, -2, -3, -4, -5});

    }

    private static void test(int[] nums) {
        System.out.println("Input: ");
        GenericPrinter.print(nums);
        System.out.println("outputs: ");

        IProductArrayExceptSelf division = new ProductArrayExceptSelfUsingDivision();

        IProductArrayExceptSelf leftRight = new ProductArrayExceptSelfLeftRight();

        IProductArrayExceptSelf constantSpace = new ProductArrayExceptSelfConstantSpace();

        print(nums, division, "Division");
        print(nums, leftRight, "leftRight");
        print(nums, constantSpace, "constantSpace");
    }

    private static void print(int nums[], IProductArrayExceptSelf action, String actionType) {
        System.out.println(actionType);
        System.out.print(" [ ");
        for (int i : action.productExceptSelf(nums)) {
            System.out.print(i + " ");
        }
        System.out.println(" ] ");
    }


}

interface IProductArrayExceptSelf {
    int[] productExceptSelf(int[] nums);
}

/**
 * with division and in O(n).
 * O(n) / O(1) ; output array doesn't count as space
 * <p>
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
 * Memory Usage: 41.2 MB, less than 100.00% of Java online submissions for Product of Array Except Self.
 */
class ProductArrayExceptSelfUsingDivision implements IProductArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        if (nums == null || nums.length <= 1)
            return nums;

        long product = 1;
        int zeroCount = 0;
        for (int i : nums) {

            if (i == 0) {
                zeroCount++;
            } else
                product *= i;

        }

        int result[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) {

                if (zeroCount > 1)
                    result[i] = 0;
                else
                    result[i] = (int) product;
            } else {
                if (zeroCount > 0)
                    result[i] = 0;
                else
                    result[i] = (int) (product / nums[i]);
            }
        }
        return result;
    }
}


/**
 * without division and in O(n).
 * O(n)/O(n)
 * https://leetcode.com/articles/product-of-array-except-self/
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
 * Memory Usage: 42.1 MB, less than 100.00% of Java online submissions for Product of Array Except Self.
 */
class ProductArrayExceptSelfLeftRight implements IProductArrayExceptSelf {

    @Override
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length <= 1)
            return nums;

        int n = nums.length;
        int left[] = new int[n];
        int right[] = new int[n];
        int result[] = new int[n];

        left[0] = 1;
        right[n - 1] = 1;

        for (int i = 1, j = n - 2; i < n; i++, j--) {

            left[i] = left[i - 1] * nums[i - 1];
            right[j] = right[j + 1] * nums[j + 1];

        }

        for (int i = 0; i < n; i++)
            result[i] = left[i] * right[i];

        return result;
    }
}

/**
 * without division and space and in O(n).
 * O(n) / O(1)
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
 * Memory Usage: 41.5 MB, less than 100.00% of Java online submissions for Product of Array Except Self.
 */
class ProductArrayExceptSelfConstantSpace implements IProductArrayExceptSelf {

    @Override
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length <= 1)
            return nums;

        int n = nums.length;
        int result[] = new int[n];
        result[0] = 1;

        /**
         * run result array as left array like above solution
         */
        for (int i = 1; i < n; i++) {

            result[i] = result[i - 1] * nums[i - 1];

        }


        /**
         * run result array as right array like above solution
         */
        int r = 1;
        for (int i = n - 1; i >= 0; i--) {

            result[i] = result[i] * r;
            r *= nums[i];

        }


        return result;
    }
}