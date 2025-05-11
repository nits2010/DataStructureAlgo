package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2903;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2905.FindIndicesWithIndexAndValueDifferenceII_2905;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/10/2025
 * Question Title: 2903. Find Indices With Index and Value Difference I
 * Link: https://leetcode.com/problems/find-indices-with-index-and-value-difference-i/description/
 * Description: You are given a 0-indexed integer array nums having length n, an integer indexDifference, and an integer valueDifference.
 * <p>
 * Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:
 * <p>
 * abs(i - j) >= indexDifference, and
 * abs(nums[i] - nums[j]) >= valueDifference
 * Return an integer array answer, where answer = [i, j] if there are two such indices, and answer = [-1, -1] otherwise. If there are multiple choices for the two indices, return any of them.
 * <p>
 * Note: i and j may be equal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
 * Output: [0,3]
 * Explanation: In this example, i = 0 and j = 3 can be selected.
 * abs(0 - 3) >= 2 and abs(nums[0] - nums[3]) >= 4.
 * Hence, a valid answer is [0,3].
 * [3,0] is also a valid answer.
 * Example 2:
 * <p>
 * Input: nums = [2,1], indexDifference = 0, valueDifference = 0
 * Output: [0,0]
 * Explanation: In this example, i = 0 and j = 0 can be selected.
 * abs(0 - 0) >= 0 and abs(nums[0] - nums[0]) >= 0.
 * Hence, a valid answer is [0,0].
 * Other valid answers are [0,1], [1,0], and [1,1].
 * Example 3:
 * <p>
 * Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
 * Output: [-1,-1]
 * Explanation: In this example, it can be shown that it is impossible to find two indices that satisfy both conditions.
 * Hence, [-1,-1] is returned.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n == nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= indexDifference <= 100
 * 0 <= valueDifference <= 50
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
 * @TwoPointers <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon <p>
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2905.FindIndicesWithIndexAndValueDifferenceII_2905.Solution_PastSlidingWindow}
 */

public class FindIndicesWithIndexAndValueDifferenceI_2903 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();

        tests.add(test(new int[]{5, 1, 4, 1}, 2, 4, new int[]{0, 3}));
        tests.add(test(new int[]{2, 1}, 0, 0, new int[]{0, 0}));
        tests.add(test(new int[]{1, 2, 3}, 2, 4, new int[]{-1, -1}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int indexDifference, int valueDifference, int[] expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "indexDifference", "valueDifference", "Expected"}, true, nums, indexDifference, valueDifference, expected);


        int[] output;
        boolean pass, finalPass = true;

        output = new SolutionBruteForce().findIndices(nums, indexDifference, valueDifference);
        pass = CommonMethods.compareResultOutCome(output, expected, true) || diffValidation(output, nums, valueDifference);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BruteForce", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

    }

    private static boolean diffValidation(int[] output, int[] nums, int v) {
        return Math.abs(nums[output[0]] - nums[output[1]]) >= v;
    }


    //O(n^2) / O(1)
    static class SolutionBruteForce {
        public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {

            int n = nums.length;

            for (int i = 0; i < n - indexDifference; i++) {
                for (int j = i + indexDifference; j < n; j++) {
                    if (Math.abs(nums[i] - nums[j]) >= valueDifference)
                        return new int[]{i, j};
                }
            }
            return new int[]{-1, -1};
        }
    }
}
