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
 *
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
        //add tests cases here
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test() {
        //add print here
        // CommonMethods.printTestOutcome(new String[]{"Prices", "Expected"}, true, prices, expected);

        int output;
        boolean pass, finalPass = true;

        //add logic here

        return finalPass;

    }
}
