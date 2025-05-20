package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._162;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/20/2025
 * Question Title: 162. Find Peak Element
 * Link: https://leetcode.com/problems/find-peak-element/description/
 * Description: A peak element is an element that is strictly greater than its neighbors.
 * <p>
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * nums[i] != nums[i + 1] for all valid i.
 * File reference
 * -----------
 * Duplicate {@link}
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
 * @Facebook
 * @Amazon
 * @Google
 * @Uber
 * @Snapchat
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindPeakElement_162 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 1}, 2));
        tests.add(test(new int[]{1, 2, 1, 3, 5, 6, 4}, 5));
        tests.add(test(new int[]{3, 4, 3, 2, 1}, 1));
        tests.add(test(new int[]{3}, 0)); //boundary element
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().findPeakElement(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * 0 1 2 3 4 5 6
     * [1,2,1,3,5,6,4]
     * low = 0, high = 6 => mid = 3; nums[3] = 3 ; nums[2] = 1 and nums[4] = 5 ; 1<3<5
     * <p>
     * Key Insight:
     * Since nums[-1] = nums[n] = -∞, the boundary elements nums[0] and nums[n-1] are valid peak candidates. If either is a peak, return immediately. If not, proceed with binary search.
     * <p>
     * Binary Search Logic:
     * We're not guaranteed that moving left or right will always lead to a peak—except possibly at the ends—but moving toward the higher neighbor increases our chances of finding one.
     * <p>
     * If nums[mid] < nums[mid - 1] → move left: high = mid - 1
     * <p>
     * Else if nums[mid] < nums[mid + 1] → move right: low = mid + 1
     * <p>
     * Otherwise, nums[mid] is a peak.
     */

    static class Solution {
        public int findPeakElement(int[] nums) {
            int n = nums.length;

            if (n == 1)
                return 0;

            //check nums[0] and nums[n-1] as a potential candidate
            if (nums[0] > nums[1])
                return 0;

            if (nums[n - 1] > nums[n - 2])
                return n - 1;

            int low = 1, high = n - 2;

            while (low <= high) {
                int mid = (low + high) >>> 1;

                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                    return mid;

                if (nums[mid] < nums[mid - 1]) // the left element is higher, chances are towards nums[0]
                    high = mid - 1;
                else if (nums[mid] < nums[mid + 1]) // the right element is higher, chances are towards nums[n-1]
                    low = mid + 1;
            }
            return -1;
        }
    }
}
