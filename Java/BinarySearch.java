package Java;

import Java.HelpersToPrint.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 14/09/19
 * Description:
 */
public class BinarySearch {

    public static void main(String[] args) {

        test(new int[]{1, 2, 3, 4, 5}, 5, 4);
        test(new int[]{1, 2, 3, 4, 5}, 2, 1);
        test(new int[]{1, 2, 3, 4, 5}, 0, -1);
    }

    private static void test(int[] nums, int target, int expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(nums) + " target :" + target + " expected :" + expected);
        System.out.println("binary search 1:" + BinarySearchOverflow.binarySearch1(nums, target));
        System.out.println("binary search Recursive 1:" + BinarySearchOverflow.binarySearchRecursive1(nums, target));
        System.out.println("binary search 2:" + BinarySearchNoOverflow.binarySearch2(nums, target));
        System.out.println("binary search Recursive 2:" + BinarySearchNoOverflow.binarySearchRecursive2(nums, target));
    }


}

/**
 * Use when you know that (s+e) can overflow the integer limit
 */
class BinarySearchOverflow {

    public static int binarySearch1(int[] array, int target) {
        int s = 0, e = array.length; //note here

        while (s < e) {
            int mid = s + ((e - s) >> 1); //>> has low priority then +; note here

            if (array[mid] == target)
                return mid;

            if (array[mid] < target) {
                s = mid + 1;
            } else
                e = mid; //note here

        }
        return -1;
    }


    public static int binarySearchRecursive1(int[] array, int target) {

        return binarySearchRecursive1(array, 0, array.length, target);
    }

    private static int binarySearchRecursive1(int[] array, int low, int high, int target) {

        if (low < high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target)
                return mid;
            else if (array[mid] > target)
                return binarySearchRecursive1(array, low, mid, target);
            else
                return binarySearchRecursive1(array, mid + 1, high, target);
        }
        return -1;
    }
}

/**
 * Use when you know that (s+e) can't overflow the integer limit
 */
class BinarySearchNoOverflow {

    public static int binarySearch2(int[] array, int target) {
        int s = 0, e = array.length - 1; //note here

        while (s <= e) { //note here
            int mid = (s + e) >> 1; //note here

            if (array[mid] == target)
                return mid;

            if (array[mid] < target) {
                s = mid + 1;
            } else
                e = mid - 1; //note here

        }
        return -1;
    }


    public static int binarySearchRecursive2(int[] array, int target) {

        return binarySearchRecursive2(array, 0, array.length - 1, target);
    }

    private static int binarySearchRecursive2(int[] array, int low, int high, int target) {

        if (low <= high) {
            int mid = (high + low) / 2;

            if (array[mid] == target)
                return mid;
            else if (array[mid] > target)
                return binarySearchRecursive2(array, low, mid - 1, target);
            else
                return binarySearchRecursive2(array, mid + 1, high, target);
        }
        return -1;
    }
}