package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming._baseProblems.maximumSubArray.Kadens._53;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/7/2024
 * Question Category: 53. Maximum Subarray https://leetcode.com/problems/maximum-subarray/description
 * Description: Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.subarrays.KadensAlgorithm}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @KadensAlgorithm
 * @medium
 * @Array
 * @DivideandConquer
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @LinkedIn
 * @Amazon
 * @Apple
 * @Microsoft
 * @Adobe <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MaximumSubarray_53 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6));
        tests.add(test(new int[]{1}, 1));
        tests.add(test(new int[]{5, 4, -1, 7, 8}, 23));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "Expected"}, true, nums, expected);

        int output;
        boolean pass, finalPass = true;

        Kadens.KadensAlgorithm kadensAlgorithm = new Kadens.KadensAlgorithm();
        output = kadensAlgorithm.maxSubArray(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"kadensAlgorithm", "Expected"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Kadens {
        static class KadensAlgorithm {
            public int maxSubArray(int[] nums) {

                int maxSum = 0;
                int currSum = 0;

                for (int n : nums) {
                    currSum += n;

                    maxSum = Math.max(maxSum, currSum);
                    if (currSum < 0) {
                        currSum = 0;
                    }
                }

                return maxSum;

            }

            public int maxSubArray2(int[] nums) {

                int maxSum = 0;
                int currSum = 0;

                for (int n : nums) {

                    currSum = Math.max(currSum + n, n);
                    maxSum = Math.max(maxSum, currSum);
                }

                return maxSum;

            }
        }

        static class KadensAlgorithm_SubArray {
            public int[] maxSubArray(int[] nums) {

                int maxSum = Integer.MIN_VALUE;
                int start = 0; // starting index of subarray
                int end = 0; // ending index of subarray
                int currSum = 0;

                for (int i = 0; i < nums.length; i++) {
                    currSum += nums[i];

                    if (currSum > maxSum) {
                        maxSum = currSum;
                        end = i;
                    }

                    if (currSum < 0) {
                        currSum = 0;
                        start = i + 1;
                    }
                }

                return new int[]{maxSum, start, end};

            }
        }
    }

}
