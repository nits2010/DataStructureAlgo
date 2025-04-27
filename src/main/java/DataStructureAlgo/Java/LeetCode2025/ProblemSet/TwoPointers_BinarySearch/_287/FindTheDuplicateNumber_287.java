package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._287;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/8/2025
 * Question Title: 287. Find the Duplicate Number
 * Link: https://leetcode.com/problems/find-the-duplicate-number/description/
 * Description: Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * <p>
 * There is only one repeated number in nums, return this repeated number.
 * <p>
 * You must solve the problem without modifying the array nums and using only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * Example 3:
 * <p>
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._142.LinkedListCycleII_142}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers
 * @BinarySearch
 * @BitManipulation <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Facebook
 * @Uber
 * @Adobe
 * @Apple
 * @Bloomberg
 * @eBay
 * @Facebook
 * @Google
 * @MorganStanley
 * @Oracle
 * @Salesforce
 * @Tencent
 * @Yahoo <p>
 * -----
 * @Editorial https://leetcode.com/problems/find-the-duplicate-number/solutions/6097957/video-floyd-s-tortoise-and-hare-algorithm-and-prove-it-with-simple-calculation<p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindTheDuplicateNumber_287 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 3, 4, 2, 2}, 2));
        tests.add(test(new int[]{3, 1, 3, 4, 2}, 3));
        tests.add(test(new int[]{3, 3, 3, 3, 3}, 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "Expected"}, true, grid, expected);

        int output = 0;
        boolean pass, finalPass = true;

        SolutionUsingExtraSpace solutionUsingExtraSpace = new SolutionUsingExtraSpace();
        output = solutionUsingExtraSpace.findDuplicate(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingExtraSpace", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionTwoPointer solutionTwoPointer = new SolutionTwoPointer();
        output = solutionTwoPointer.findDuplicate(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"UsingTwoPointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionUsingExtraSpace {
        public int findDuplicate(int[] nums) {
            int[] temp = new int[nums.length];
            for (int n : nums) {
                if (temp[n] == n)
                    return n;
                temp[n] = n;
            }
            return -1;
        }
    }

    /**
     * Follow up
     * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._142.LinkedListCycleII_142}
     */
    static class SolutionTwoPointer {
        public int findDuplicate(int[] nums) {

            //assume this as a linked list with cycle
            int slow = nums[0];
            int fast = nums[0];

            //detect cycle
            do {
                slow = nums[slow];
                fast = nums[nums[fast]]; // this work because an array has only [1,n] range elements
            } while (slow != fast);

            //if cycle found, means a duplicate number exists
            //detect the duplicate
            slow = nums[0];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;

        }
    }
}
