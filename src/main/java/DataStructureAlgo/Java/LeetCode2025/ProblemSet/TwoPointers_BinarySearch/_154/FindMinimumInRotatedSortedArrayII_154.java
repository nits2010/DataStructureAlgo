package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._154;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/21/2025
 * Question Title: 154. Find Minimum in Rotated Sorted Array II
 * Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 * Description: Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums is sorted and rotated between 1 and n times.
 *
 *
 * Follow up: This problem is similar to Find Minimum in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._153.FindMinimumInRotatedSortedArray_153}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @hard
 * @Array
 * @inarySearch
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * @Adobe
 * @Amazon
 * @Facebook
 * @Uber
 *
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindMinimumInRotatedSortedArrayII_154 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 3, 5}, 1));
        tests.add(test(new int[]{2, 2, 2, 0, 1}, 0));
        tests.add(test(new int[]{2,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,1,1,1,1,1,1}, 0));
        tests.add(test(new int[]{3,3,1,3}, 1));
        tests.add(test(new int[]{10,1,10,10,10}, 1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().findMin(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }
    static class Solution {
        public int findMin(int[] nums) {
            int n = nums.length;
            if(nums[0] < nums[n-1])
                return nums[0];
            int pivotIndex = pivotIndex ( nums );
            return nums[pivotIndex] ;


        }

        private int pivotIndex(int []nums){
            int low = 0, high = nums.length - 1;

            while (low < high){
                int mid = (low + high) >>> 1;

                // this is because it could be possible that all elements [mid, high] are same or there are elements in b/w [mid,high] which is not same, so we have to find the first index of nums[high] b/w [mid,high]
                // Example : [3,3,1,3] l = 0, h = 3 m = 1 which is nums[mid] = 3 and nums[high] = 3 which is equal, however if you see the elements b/w [mid,high] are not same [3,1,3] so we have to reduce search space b/w
                // [low, high-1]
                // Example : [10,1,10,10,10] l = 0, h = 4, m = 2 nums[mid] = 10, nums[high] = 10 which is same and also [mid,high] elements are [10,10,10] which means the search space b/w [mid,high]
                // isn't valid, reducing it to [low,high-1] makes low = 0, high = 3 m = 1 and nums[mid] = 1, nums[high] = 10 and 1 != 10 lands you to original problem.
                if(nums[mid] == nums[high])
                    high = high - 1;
                else if(nums[mid] > nums[high]){
                    low = mid + 1;
                }else {
                    high = mid;
                }
            }
            return low;
        }
    }
}
