package DataStructureAlgo.Java.LeetCode.sortedrotated;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-25
 * Description:153. Find Minimum in Rotated Sorted Array
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class MinimumRotatedSortedArrayFindPivot {

    public static void main(String []args) {
        FindPivot min = new FindPivot();
        System.out.println(min.search(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}));
        System.out.println(min.search(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(min.search(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(min.search(new int[]{6}));
        System.out.println(min.search(new int[]{3, 1, 2}));
        System.out.println(min.search(new int[]{5, 1, 2, 3, 4}));
    }
}


class FindPivot {

    public int search(int[] nums) {

        if (nums.length == 0)
            return -1;

        int low = 0;
        int high = nums.length - 1;
        int mid;

        /**
         * If the array is already sorted;
         * [1,2,3,4,5,6]
         * then low is itself is minimum
         */
        if (nums[low] < nums[high])
            return nums[low];

        /**
         * Binary search between low and high
         */
        while (low < high) {

            mid = (low + high) >> 1;

            int l = nums[low];
            int m = nums[mid];


            /**
             * [4,5,6,7,0,1,2] and m = 0 then 0 < 7{mid-1}
             */
            if (mid > low && m < nums[mid - 1])
                return nums[mid];

            /**
             * [4,5,6,7,0,1,2] and m = 7 then 7 > 0{mid+1}
             */
            if (mid < high && m > nums[mid + 1])
                return nums[mid + 1];

            /**
             * [4,5,6,7,0,1,2]; m= 6 and l = 4 ; 6 > 4 then right side is smaller part
             */
            if (m > l)
                low = mid + 1;
            else
            /**
             * [5, 1, 2, 3, 4]; m= 2 and l = 5 ; 2 < 5  then left side is smaller part
             */
                high = mid - 1;

        }

        /**
         * when low and high are same; only 1 element in an array
         */
        return nums[low];

    }
}
