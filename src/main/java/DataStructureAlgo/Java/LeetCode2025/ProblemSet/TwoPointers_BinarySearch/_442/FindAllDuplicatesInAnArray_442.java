package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._442;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/29/2025
 * Question Title: 442. Find All Duplicates in an Array
 * Link: https://leetcode.com/problems/find-all-duplicates-in-an-array/description/
 * Description: Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears at most twice, return an array of all the integers that appears twice.
 * <p>
 * You must write an algorithm that runs in O(n) time and uses only constant auxiliary space, excluding the space needed to store the output
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [2,3]
 * Example 2:
 * <p>
 * Input: nums = [1,1,2]
 * Output: [1]
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * Each element in nums appears once or twice.
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
 * @HashTable <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Google
 * @Adobe
 * @Apple
 * @Facebook
 * @Lyft
 * @PocketGems <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindAllDuplicatesInAnArray_442 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{4, 3, 2, 7, 8, 2, 3, 1}, Arrays.asList(2, 3)));
        tests.add(test(new int[]{1, 1, 2}, Arrays.asList(1)));
        tests.add(test(new int[]{1}, Arrays.asList()));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        List<Integer> output;
        boolean pass, finalPass = true;

        output = new SolutionModifyArrayNeg().findDuplicates(nums.clone());
        pass = CommonMethods.compareResultOutCome(output, expected, false);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"ModifyArrayNeg", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new SolutionModifyArraySwapElements().findDuplicates(nums.clone());
        pass = CommonMethods.compareResultOutCome(output, expected, false);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"ModifyArraySwapElements", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionModifyArraySwapElements {
        public List<Integer> findDuplicates(int[] nums) {
            int n = nums.length;
            int i = 0;
            while (i < n) {
                //since an array has n+1 elements in the range of [1,n], hence each can be placed to its own index except duplicate
                int index = nums[i] - 1; //-1 because of range [1,n]

                if (nums[i] != nums[index]) {
                    int temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                } else {
                    i++;
                }
            }
            List<Integer> output = new ArrayList<>();
            for (i = 0; i < n; i++) {
                if (nums[i] != i + 1)
                    output.add(nums[i]);
            }
            return output;

        }
    }


    static class SolutionModifyArrayNeg {
        public List<Integer> findDuplicates(int[] nums) {
            int n = nums.length;
            int i = 0;
            List<Integer> output = new ArrayList<>();
            while (i < n) {
                //since an array has n+1 elements in the range of [1,n], hence each can be placed to its own index except duplicate
                int index = Math.abs(nums[i]) - 1; //-1 because of range [1,n]
                nums[index] = -nums[index]; // negate the number; this represents that we have visited index

                //if we have visited index again, then this is a duplicate number
                if (nums[index] > 0)
                    output.add(Math.abs(nums[i]));
                i++;
            }


            return output;

        }
    }
}
