package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._33;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/19/2025
 * Question Title: 33. Search in Rotated Sorted Array
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 * Description: There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.sortedrotated.SearchRotatedSortedArray}
 * Similar {@link}
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
 * @Microsoft
 * @Apple
 * @Adobe
 * @Alibaba
 * @Baidu
 * @Bloomberg
 * @ByteDance
 * @Cisco
 * @eBay
 * @Expedia
 * @Facebook
 * @GoldmanSachs
 * @Google
 * @caMorgan
 * @LinkedIn
 * @NetEase
 * @Nutanix
 * @Nvidia
 * @Oracle
 * @Samsung
 * @Snapchat
 * @Tencent
 * @Tesla
 * @TripAdvisor
 * @Twitch
 * @Uber
 * @Visa
 * @VMware
 * @WalmartLabs
 * @Yahoo
 * @Zillow
 * @Zulily <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class SearchInRotatedSortedArray_33 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{4, 5, 6, 7, 0, 1, 2}, 0, 4));
        tests.add(test(new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1));
        tests.add(test(new int[]{1}, 0, -1));
        tests.add(test(new int[]{5, 1, 3}, 3, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int target, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "target", "Expected"}, true, nums, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_UsingFinPivot().search(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"UsingFinPivot", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution().search(nums, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"In-Fly-Pivot", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * 0 1 2 3 4 5 6
     * [4,5,6,7,0,1,2] target = 0
     * <p>
     * What is the Pivot?
     * The pivot is the point where the array rotation breaks the increasing order — nums[pivot-1] >= nums[pivot].
     * Example: For [4,5,6,7,0,1,2], pivot is at index 4 (0), because it disrupts the sorted sequence.
     * <p>
     * Why Find the Pivot?
     * Once the pivot is found, the array can be split into two sorted parts: [low, pivot] and [pivot+1, high], allowing standard Binary Search (BS) on either side.
     * <p>
     * Two Ways to Solve:
     * <p>
     * Approach 1: First find the pivot, then run BS in the correct half ([low, pivot] or [pivot, high]). [3,4,5,1,2] here pivot is 1 at index=3
     * <p>
     * Approach 2 (Optimized): While performing BS, determine which half [low, mid] or [mid, high] is sorted. If [low, mid] is sorted, pivot must be on the right (unsorted side), and vice versa. This allows finding the target without explicitly finding the pivot.
     */

    static class Solution_UsingFinPivot {

        public int search(int[] nums, int target) {
            int pivotIndex = findPivotIndex(nums);
            int n = nums.length;

            if (target > nums[pivotIndex] && target <= nums[n - 1]) {
                return binarySearch(nums, target, pivotIndex, n - 1);
            } else {
                return binarySearch(nums, target, 0, pivotIndex);
            }
        }

        private int binarySearch(int[] nums, int target, int low, int high) {
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (nums[mid] == target)
                    return mid;
                if (nums[mid] > target)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return -1;
        }

        private int findPivotIndex(int[] nums) {
            int low = 0, high = nums.length - 1;
            while (low < high) {
                int mid = (low + high) >>> 1;

                if (nums[mid] > nums[high]) //the pivot lies in a right [4,5,6,7,0,1,2] mid = 7 high = 2
                    low = mid + 1;
                else
                    high = mid; // mid could be pivoted in a left [4,5,6,7,0,1,2] mid = 3
            }
            return low;
        }
    }

    static class Solution {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            int mid;

            while (low <= high) {
                mid = (low + high) >>> 1;

                if (nums[mid] == target)
                    return mid;

                //check [low, mid] is monotonically increasing ?
                if (nums[low] <= nums[mid]) { // here '<=' because low/mid could be same [3,1] low=0, high=1, mid = 0

                    //means our pivot lies in right side.
                    //find which part to drop, left side [low, mid] or right side [mid, high]
                    if (nums[low] <= target && target < nums[mid]) {
                        //means our target lies in [low, mid-1]
                        high = mid - 1;
                    } else {
                        //means our target lies in [mid+1, high], since [low, high] could also be increasing order
                        low = mid + 1;
                    }

                } else {
                    //means [mid, high] is monotonically increasing

                    //find which part to drop, left side [low, mid] or right side [mid, high]
                    if (nums[mid] < target && target <= nums[high]) {
                        //means our target lies b/w [mid+1, high]
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }

            }

            return -1;

        }
    }

}
