package Java.companyWise.facebook;

import Java.HelpersToPrint.HelperToPrint;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description: https://leetcode.com/problems/move-zeroes/
 * <p>
 * https://www.geeksforgeeks.org/move-zeroes-end-array/
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 * <p>
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZero {
    public static void main(String[] args) {
        System.out.println("\nTest1");
        test1(new int[]{1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9});
        test1(new int[]{0, 1, 0, 3, 12});

        System.out.println("\nTest2");
        test2(new int[]{1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9});
        test2(new int[]{0, 1, 0, 3, 12});

        System.out.println("\nTest3");
        test3(new int[]{1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9});
        test3(new int[]{0, 1, 0, 3, 12});
    }


    static void test1(int nums[]) {
        System.out.println("Array before pushing zeros to the back: ");
        HelperToPrint.print(nums);
        Sol1 sol1 = new Sol1();
        sol1.moveZeroes(nums);
        System.out.println("Array after pushing zeros to the back: ");
        HelperToPrint.print(nums);
    }


    static void test2(int nums[]) {
        System.out.println("Array before pushing zeros to the back: ");
        HelperToPrint.print(nums);
        Sol2 sol2 = new Sol2();
        sol2.moveZeroes(nums);
        System.out.println("Array after pushing zeros to the back: ");
        HelperToPrint.print(nums);
    }

    static void test3(int nums[]) {
        System.out.println("Array before pushing zeros to the back: ");
        HelperToPrint.print(nums);
        Sol3 sol = new Sol3();
        sol.moveZeroes(nums);
        System.out.println("Array after pushing zeros to the back: ");
        HelperToPrint.print(nums);
    }
}

/**
 * Count zero, shift , push zero
 */
class Sol1 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int countZero = 0;

        for (int i = 0; i < nums.length; i++)
            if (nums[i] == 0)
                countZero++;

        int index = 0;
        //0,1,0,3,12
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[index++] = nums[i];
        }
        //1,3,12,3,12

        //1,3,12,3,12
        while (countZero-- > 0)
            nums[index++] = 0;

        //1,3,12,0,0


    }
}

/**
 * The total number of operations of the approach is sub-optimal. For example,
 * the array which has all (except last) leading zeroes: [0, 0, 0, ..., 0, 1]. How many write operations to the array?
 * For the  approach, it writes 0's n−1 which is not necessary
 */
class Sol2 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int index = 0;
        //0,1,0,3,12
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[index++] = nums[i];
        }
        //1,3,12,3,12

        //1,3,12,3,12
        while (index < nums.length)
            nums[index++] = 0;
    }

}

/**
 * The total number of operations of the previous approach is sub-optimal. For example,
 * the array which has all (except last) leading zeroes: [0, 0, 0, ..., 0, 1].How many write operations to the array?
 * For the previous approach, it writes 0's n−1 times, which is not necessary.
 * We could have instead written just once. How? .....
 * <p>
 * By only fixing the non-0 element,i.e., 1.
 * <p>
 * The optimal approach is again a subtle extension of above solution. A simple realization is if the current element is non-0,
 * its' correct position can at best be it's current position or a position earlier. If it's the latter one,
 * the current position will be eventually occupied by a non-0 ,or a 0, which lies at a index greater than 'cur' index.
 * We fill the current position by 0 right away,so that unlike the previous solution, we don't need to come back here in next iteration.
 * <p>
 * In other words, the code will maintain the following invariant:
 * <p>
 * All elements before the slow pointer (lastNonZeroFoundAt) are non-zeroes.
 * All elements between the current and slow pointer are zeroes.
 * <p>
 * Therefore, when we encounter a non-zero element, we need to swap elements pointed by current and slow pointer,
 * then advance both pointers. If it's zero element, we just advance current pointer.
 * <p>
 * <p>
 * <p>
 * However, the total number of operations are optimal. The total operations (array writes) that code does is Number of non-0 elements.
 * This gives us a much better best-case (when most of the elements are 0) complexity than last solution.
 * However, the worst-case (when all elements are non-0) complexity for both the algorithms is same.
 */
class Sol3 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int indexOfZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                swap(nums, i, indexOfZero++);

        }
    }

    void swap(int num[], int i, int j) {
        int k = num[i];
        num[i] = num[j];
        num[j] = k;
    }

}