package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._41;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/30/2025
 * Question Title: 41. First Missing Positive
 * Link: https://leetcode.com/problems/first-missing-positive/description/
 * Description: Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 * Example 2:
 * <p>
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 * Example 3:
 * <p>
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1539.KthMissingPositiveNumber_1539}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._268.MissingNumber_268}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @HashTable
 * @CycleSort <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Microsoft
 * @Adobe
 * @Google
 * @Adobe
 * @Airbnb
 * @Apple
 * @Bloomberg
 * @ByteDance
 * @Databricks
 * @Grab
 * @Oracle
 * @PocketGems
 * @Salesforce
 * @SAP
 * @Uber
 * @WalmartLabs
 * @Wayfair
 * @Wish
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FirstMissingPositive_41 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1}, 2));
        tests.add(test(new int[]{1, 2, 0}, 3));
        tests.add(test(new int[]{3, 4, -1, 1}, 2));
        tests.add(test(new int[]{7, 8, 9, 11, 12}, 1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_CycleSort().firstMissingPositive(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"CycleSort", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_Hash().firstMissingPositive(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Hahs", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * The elements in the array could be +ve and -ve however, we need to search for the +ve integer only.
     * In the best case, all numbers in array can be +ve only. If n is the length of the array, then an array can contain only [1,n] range number only.
     * Example: n=3 then [1,2,3]
     * However, if an array has -ve as well, then there will be at least one missing number from [1,n] range.
     * <p>
     * Since an array has only [1,n] numbers, we can apply cycle sort and try to put all the numbers to their correct place. {except -ve }.
     * Then run again, and the first number which is not in position is our result.
     * <p>
     * T/S: O(n) / O(1)
     */
    static class Solution_CycleSort {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;

            int i = 0;

            while (i < n) {

                //skip -ve number
                if (nums[i] < 0 || nums[i] == 0) //range is [1,n]
                    i++;
                else {
                    int correctIndex = nums[i] - 1; // array is 0th-index-based

                    if (nums[i] <= n && nums[i] != nums[correctIndex]) {
                        //swap
                        int temp = nums[i];
                        nums[i] = nums[correctIndex];
                        nums[correctIndex] = temp;
                    } else {
                        i++;
                    }
                }
            }

            i = 0;
            while (i < n) {
                if (nums[i] != i + 1)
                    return i + 1;
                i++;
            }
            return n + 1;

        }
    }

    /**
     * The elements in the array could be +ve and -ve however, we need to search for the +ve integer only.
     * In the best case, all numbers in array can be +ve only. If n is the length of the array, then an array can contain only [1,n] range number only.
     * Example: n=3 then [1,2,3]
     * However, if an array has -ve as well, then there will be at least one missing number from [1,n] range.
     * <p>
     * To find, we can hash all the elements from the array (only +ve, as we are interested in +ve only) and then run through [1,n] range and see which number isn't
     * available in the array.
     * <p>
     * T/S: O(n) / O(n)
     */
    static class Solution_Hash {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;

            Set<Integer> set = new HashSet<>();
            for (int x : nums) {
                if (x > 0) // we need to check for +ve int only
                    set.add(x);
            }
            int num = 1;
            while (num <= n) {

                if (!set.contains(num))
                    return num;
                num++;
            }

            return num;

        }
    }
}
