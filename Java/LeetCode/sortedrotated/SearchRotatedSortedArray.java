package Java.LeetCode.sortedrotated;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-22
 * Description:https://leetcode.com/problems/search-in-rotated-sorted-array/
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchRotatedSortedArray {

    public static void main(String []args) {
        RotatedSortedArraySearch search = new RotatedSortedArraySearch();
        System.out.println(search.search(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}, 6));
        System.out.println(search.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(search.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }


}

class RotatedSortedArraySearch {

    public int search(int[] nums, int target) {

        if (nums.length == 0)
            return -1;

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = (low + high) >> 1;

            //If found at the middle, best case
            if (nums[mid] == target)
                return mid;

            /**
             * Since the array is sorted at pivot.
             * assuming this mid as pivot then see the left part is sorted or not
             */
            if (nums[low] <= nums[mid]) { //this part is sorted

                //does key lies in this sorted array
                if (nums[low] <= target && nums[mid] >= target)
                    high = mid - 1; //it would lie in left side since target is before mid; [low,mid-1]
                else
                    low = mid + 1; //it would lie in right side; since target is after mid; [mid+1,high]
            } else {
                //that means nums[low..mid] is not sorted and it makes nums[mid..high] sorted

                //see does it exist in other half
                if (target >= nums[mid] && target <= nums[high])
                    low = mid + 1; //it would lie in right side; since target is after mid; [mid+1,high]
                else
                    high = mid - 1; //it would lie in left side since target is before mid; [low,mid-1]
            }

        }

        return -1;

    }
}