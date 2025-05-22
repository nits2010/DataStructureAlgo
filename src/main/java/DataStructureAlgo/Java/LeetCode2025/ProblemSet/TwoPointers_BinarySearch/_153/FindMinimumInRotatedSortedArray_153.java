package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._153;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/19/2025
 * Question Title: 153. Find Minimum in Rotated Sorted Array
 * Link:
 * Description: Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 * <p>
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.sortedrotated.MinimumRotatedSortedArrayFindPivot}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._33.SearchInRotatedSortedArray_33}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Adobe
 * @GoldmanSachs
 * @Apple
 * @Bloomberg
 * @Google
 * @LinkedIn
 * @Salesforce
 * @Uber
 * @VMware
 * @WalmartLabs <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindMinimumInRotatedSortedArray_153 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 4, 5, 1, 2}, 1));
        tests.add(test(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        tests.add(test(new int[]{11, 13, 15, 17}, 11));
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

            //if the array is already sorted without any rotation
            if (nums[0] < nums[n - 1])
                return nums[0];

            //find the pivot point
            int pivotIndex = pivotIndex(nums);

            return nums[pivotIndex];
        }

        private int pivotIndex(int[] nums) {
            int low = 0, high = nums.length - 1;

            while (low < high) {
                int mid = (low + high) >>> 1;

                if (nums[mid] > nums[high]) //[3,4,5,1,2] mid = 5 then 5 > 2, pivot lies in right side
                    low = mid + 1;
                else
                    high = mid; //mid could be pivoted itself
            }
            return low;
        }

        private int pivotIndexV2(int []nums){
            int low = 0, high = nums.length -1;

            while(low <= high){
                int mid = (low + high) >>> 1;

                if(nums[mid] > nums[nums.length -1]) //[3,4,5,1,2] mid = 5 then 5 > 2, pivot lies in right side
                    low = mid + 1;
                else
                    high = mid - 1; //mid could be pivoted itself
            }
            return low;
        }
    }
}
