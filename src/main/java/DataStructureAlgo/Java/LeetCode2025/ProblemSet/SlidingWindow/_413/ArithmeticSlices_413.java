package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._413;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/14/2025
 * Question Title: 413. Arithmetic Slices
 * Link: https://leetcode.com/problems/arithmetic-slices/description
 * Description: An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 * <p>
 * A subarray is a contiguous subsequence of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 3
 * Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
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
 * @DynamicProgramming
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Aetion
 * @Facebook <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class ArithmeticSlices_413 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 4}, 3));
        tests.add(test(new int[]{1}, 0));
        tests.add(test(new int[]{1, 2, 3, 5, 6, 7, 8, 11, 14, 18, 20}, 5));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output;
        boolean pass, finalPass = true;

        output = new SolutionRunningSum().numberOfArithmeticSlices(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"RunningSum", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionSlidingCount().numberOfArithmeticSlices(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SlidingCount", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class SolutionRunningSum {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;
            if (n < 3)
                return 0;

            int totalCounts = 0;

            int end = 2; // makes 3 elements in-between of start and end
            int currentCount = 0;

            while (end < n) {
                //maintain 3 size sliding window with last 3 elements, and check for constraint
                if ((nums[end] - nums[end - 1]) == (nums[end - 1] - nums[end - 2])) {

                    //this forms an at least 3 size window where constraint met
                    // we can count it continuous, this is because, all the subarray starting from [start,end]
                    //can have more than 1 sub-array of at least size 3. and if we get a window of 4 elements
                    //then sub-array of at least size 3 would be 3 in total, however, our currentCount would be point to 2, and total would be 1+2
                    currentCount++;


                    //as this forms a generic sequence
                    // n = 3, at least 3 size subarray = 1
                    // n =4, at least 3 size subarray = 3 (1+2)
                    // n = 5, at least 3 size subarray = 6 (1+2+3)
                    // n = 6, its 10 (1+2+3+4)
                    totalCounts += currentCount;
                } else {
                    currentCount = 0; //we broke the last 3-element window
                }
                end++;

            }


            return totalCounts;


        }


    }

    static class SolutionSlidingCount {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;
            if (n < 3)
                return 0;

            int counts = 0;

            int start = 0, end = 1;
            int diff = nums[end] - nums[start];
            end++;
            while (end < n) {

                //expand the window till the condition satisfy
                if (nums[end] - nums[end - 1] != diff) {
                    //condition did not satisfied at 'end'
                    //count how many subarray possible between start,end
                    counts += subArrayCountOfSizeK(end - start, 3);

                    //reset the window
                    start = end - 1;
                    diff = nums[end] - nums[start];
                }
                end++;
            }

            //count how many subarrays possible between start,end at the end
            if (end - start >= 3) {
                diff = nums[start + 1] - nums[start];
                if (diff == (nums[end - 1] - nums[end - 2]))
                    counts += subArrayCountOfSizeK(end - start, 3);
            }
            return counts;


        }

        /**
         * Refer {subarray.md}
         */
        private int subArrayCountOfSizeK(int n, int k) {

            return k > n ? 0 : (n - k + 1) * (n - k + 2) / 2;
        }
    }
}
