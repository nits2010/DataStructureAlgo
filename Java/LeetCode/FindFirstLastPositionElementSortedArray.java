package Java.LeetCode;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 34. Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * <p>
 * {@link FirstBadVersion}
 */
public class FindFirstLastPositionElementSortedArray {

    public static void main(String[] args) {
//        test(new int[]{5}, 8, new int[]{3, 4});
        test(new int[]{5, 7, 7, 8, 8, 10}, 8, new int[]{3, 4});
        test(new int[]{5, 7, 7, 8, 8, 10}, 6, new int[]{-1, -1});
        test(new int[]{5, 5, 5, 5, 5, 5}, 5, new int[]{0, 5});
        test(new int[]{5, 5, 5, 6, 7, 8}, 6, new int[]{3, 3});
        test(new int[]{5, 5, 6, 6, 7, 8}, 6, new int[]{2, 3});
    }

    private static void test(int[] nums, int target, int[] expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(nums) + " target :" + target + " expected :" + GenericPrinter.toString(expected));
        BinarySearchFirstLastIndex firstLastIndex = new BinarySearchFirstLastIndex();
        System.out.println("Binary search :" + GenericPrinter.toString(firstLastIndex.searchRange(nums, target)));
    }
}

/**
 * O(log(n)) / O(1)
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
 * Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
 */
class BinarySearchFirstLastIndex {
    public int[] searchRange(int[] nums, int target) {

        int []output = {-1, -1};

        if (nums == null || nums.length == 0)
            return output;

        int first = binarySearchLeft(nums, target);
        if (first == -1)
            return output;

        int last = binarySearchRight(nums, target);

        output[0] = first;
        output[1] = last;

        return output;
    }

    /**
     * Binary search on left side, to move left most node
     *
     * @param nums
     * @param target
     * @return
     */
    private int binarySearchLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int index = -1;

        while (l <= r) {

            //Because (left + right) / 2 can exceed the integer limit
            // whereas left + (right - left)/2 doesn't exceed the integer bounds.
            int mid = (r + l) >> 1;

            if (nums[mid] == target) {

                index = mid;
                r = mid - 1; //move left most

            } else if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return index;
    }

    /**
     * Binary search on right side to more right most node
     *
     * @param nums
     * @param target
     * @return
     */
    private int binarySearchRight(int[] nums, int target) {

        int l = 0, r = nums.length - 1;
        int index = -1;

        while (l <= r) {

            int mid = (r + l) >> 1;

            if (nums[mid] == target) {

                index = mid;
                l = mid + 1; //move right most

            } else if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return index;
    }
}