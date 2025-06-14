package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._169;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 6/9/2025
 * Question Title: 169. Majority Element
 * Link: https://leetcode.com/problems/majority-element/description
 * Description: Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
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
 * @easy
 * @Array
 * @HashTable
 * @DivideandConquer
 * @Sorting
 * @Counting <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon
 * @Microsoft
 * @Adobe
 * @Apple <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MajorityElement_169 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 2, 3}, 3));
        tests.add(test(new int[]{2, 2, 1, 1, 1, 2, 2}, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionUsingMap().majorityElement(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"UsingMap", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_UsingDC().majorityElement(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Using Divide and Conquer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution().majorityElement(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Without map", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Time Complexity: O(n) due to the scan
     * Space Complexity: O(1)
     */
    static class Solution {
        public int majorityElement(int[] nums) {
            int count = 1, majorityElement = nums[0];

            for (int i = 1; i < nums.length; i++) {
                if (count == 0) {
                    //reset majority element
                    count++;
                    majorityElement = nums[i];
                } else if (majorityElement == nums[i]) {
                    count++;
                } else {
                    count--;
                }
            }

            return majorityElement;
        }
    }


    /**
     * The majority element in an array is the element that appears more than n / 2 times, where n is the array's length. It's assumed that a majority element always exists.
     * Divide and Conquer Strategy
     * Divide: Split the array into two halves recursively.
     * Conquer: Find the majority element in each half recursively.
     * Combine:
     * If the majority elements of the two halves are the same, that's the majority element of the whole array.
     * If they are different, count their occurrences in the entire array and the one with the higher count is the majority element.
     * <p>
     * Time Complexity: O(n log n) due to the recursive calls.
     * Space Complexity: O(log n) due to the call stack during recursion.
     */
    static class Solution_UsingDC {
        public int majorityElement(int[] nums) {
            return majorityElement(nums, 0, nums.length - 1);
        }

        private int majorityElement(int[] nums, int low, int high) {
            //base condition , if only one element
            if (low == high)
                return nums[low];

            //get majority on the left side
            int mid = (low + high) >>> 1;
            int leftMajority = majorityElement(nums, low, mid);
            int rightMajority = majorityElement(nums, mid + 1, high);

            //if both majorities are the same, then it's our result
            if (leftMajority == rightMajority)
                return leftMajority;

            int leftCount = countOcc(nums, leftMajority, low, mid);
            int rightCount = countOcc(nums, rightMajority, mid + 1, high);

            //if leftCount is higher,
            if (leftCount > rightCount)
                return leftMajority;
            else
                return rightMajority;

        }

        private int countOcc(int[] nums, int target, int low, int high) {
            int count = 0;
            for (int i = low; i <= high; i++) {
                if (nums[i] == target)
                    count++;
            }
            return count;
        }
    }

    /**
     * Time Complexity: O(n) due to the scan
     * Space Complexity: O(n) due to the map
     */
    static class SolutionUsingMap {
        public int majorityElement(int[] nums) {

            //[element, frequency]
            Map<Integer, Integer> map = new HashMap<>();
            map.put(nums[0], 1);

            int majorityElementFrequency = 1; // majority element at index 0
            int majorityElement = nums[0];

            for (int i = 1; i < nums.length; i++) {
                if (map.merge(nums[i], 1, Integer::sum) > majorityElementFrequency) {
                    majorityElementFrequency = map.get(nums[i]);
                    majorityElement = nums[i];
                }
            }

            return majorityElement;
        }
    }

//[2,2,1,1,1,2,2]
//
}
