package DataStructureAlgo.Java.LeetCode2025.Discuss_Section._SubArraySumAtMostK;

import DataStructureAlgo.Java.LeetCode.subarrays.LongestSubarraySumAtMostk;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow.SumProblems._862.ShortestSubarrayWithSumAtLeastK_862;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/23/2024
 * Question Title: Longest Subarray Sum At Most K
 * Link:
 * https://leetcode.com/discuss/interview-question/758045/apple-phone-longest-subarray-sum-at-most-k
 * https://leetcode.com/discuss/interview-question/917409/google-phone-longest-subarray-sum-at-most-k
 * Description: Given an array arr[] of size N and an integer K, the task is to find the length of the largest subarray having the sum of its elements at most K.
 * The array can have +ve and -ve numbers.
 * int[] arr = {1, 2, 1, 0, 1, -8, -9, 0};
 * int k = 4;
 * Output is 8
 * <p>
 * int arr[] = {1, 2, 1, 0, 1, 1, 0};
 * int k = 4;
 * output is 5
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link ShortestSubarrayWithSumAtLeastK_862} {@link LongestSubarraySumAtMostk}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Apple
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestSubArraySumAtMostK {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 1, 0, 1, -8, -9, 0}, 4, new int[]{8, 0, 7}));
        tests.add(test(new int[]{1, 2, 1, 0, 1, 1, 0}, 4, new int[]{5, 2, 6}));
        tests.add(test(new int[]{1, -2, 1, 20, 1, -8, -9, 0}, 3, new int[]{7, 1, 7}));
        tests.add(test(new int[]{5, -10, 7, -20, 57}, -22, new int[]{3, 1, 3}));
        tests.add(test(new int[]{-1, -1, 10, -1, -1}, 6, new int[]{5, 0, 4}));
        tests.add(test(new int[]{-1, -1, 10, -1, -1}, 5, new int[]{2, 0, 1}));
        tests.add(test(new int[]{9, 1, 2, 3, 4, 5}, 7, new int[]{3, 1, 3}));
        tests.add(test(new int[]{5, -10, 7, -20, 57}, -22, new int[]{3, 1, 3}));
        tests.add(test(new int[]{-5, 8, -14, 2, 4, 12}, 5, new int[]{5, 0, 4}));
        tests.add(test(new int[]{1, 2, 1, 0, 1, -8, -9, 0}, 4, new int[]{8, 0, 7}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution_SlidingWindow solutionSlidingWindow = new Solution_SlidingWindow();

        output = solutionSlidingWindow.largestSubArraySumAtMostK(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution_SlidingWindow {

        public int[] largestSubArraySumAtMostK(int[] nums, int k) {

            //maxSum, start, end
            int[] output = new int[]{Integer.MIN_VALUE, -1, -1};

            int n = nums.length;
            int[] negCumSum = new int[n];


            //get the max commutative neg sum
            int negSum = 0;
            for (int i = n - 1; i >= 0; i--) {
                negCumSum[i] = negSum;
                negSum = Math.min(0, negSum + nums[i]);
            }

            int currentSum = 0;
            int i = 0;
            for (int j = 0; j < n; j++) {

                //expand the window, include j
                currentSum += nums[j];


                //if currentSum and upcoming number sum exceeding k, then we can't take the leftMost element as its increasing sum, hence remove to decrease the sum
                while (i < j && currentSum + negCumSum[j] > k) {
                    currentSum -= nums[i];
                    i++;
                }

                //if valid window, get it
                if (j - i + 1 > output[0]) {
                    output[0] = j - i + 1;
                    output[1] = i;
                    output[2] = j;
                }
            }

            return output;
        }
    }
}
