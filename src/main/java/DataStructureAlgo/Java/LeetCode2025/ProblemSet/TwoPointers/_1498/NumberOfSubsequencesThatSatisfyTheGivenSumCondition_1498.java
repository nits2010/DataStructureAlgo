package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._1498;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/2/2025
 * Question Title: 1498. Number of Subsequences That Satisfy the Given Sum Condition
 * Link: https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/description/
 * Description: You are given an array of integers nums and an integer target.
 * <p>
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subsequences that satisfy the condition.
 * [3] -> Min value + max value <= target (3 + 3 <= 9)
 * [3,5] -> (3 + 5 <= 9)
 * [3,5,6] -> (3 + 6 <= 9)
 * [3,6] -> (3 + 6 <= 9)
 * Example 2:
 * <p>
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * Example 3:
 * <p>
 * Input: nums = [2,3,3,4,6,7], target = 12
 * Output: 61
 * Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
 * Number of valid subsequences (63 - 2 = 61).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= target <= 106
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
 * @TwoPointers
 * @BinarySearch
 * @Sorting <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/solutions/3492407/java-solution-easy-to-understand-beats-100-beginner-friendly
 * -----
 * @OptimalSolution {@link }
 */

public class NumberOfSubsequencesThatSatisfyTheGivenSumCondition_1498 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 5, 6, 7}, 9, 4));
        tests.add(test(new int[]{3, 3, 6, 8}, 10, 6));
        tests.add(test(new int[]{2, 3, 3, 4, 6, 7}, 12, 61));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int target, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "target", "Expected"}, true, grid, target, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.numSubseq(grid, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int numSubseq(int[] nums, int target) {
            if (nums == null || nums.length == 0)
                return 0;
            int mod = 1000000007;
            //since we wanted to know the count of such subsequences only not the actual number, sorting them will help us to
            //identify min and max easily for any subsequences.
            Arrays.sort(nums);

            //now for any subsequences, starting from i and ending at j, with total j - i + 1 elements (0 based index), will have total 2^n subsequences.
            // here n = j - i,
            //example : [ 2 3 4 ] t= 12, here i = 0, j = 2, total elements are j - i + 1 = 2 - 0 + 1 = 3
            // n = j - i = 2
            // subsequences 2^2 = 4 { {2}, {2,3}, {2,3,4}, {2,4}}
            //If we have a sorted array, and we choose the smallest element at index i, we can include any subset of elements from i+1 to j.
            // Each element in this    range has two choices:
            // Include it in the subset
            // Exclude it from the subset
            // Since there are (j - i) elements available to choose from, the total number of possible subsets formed from them is:

            //pre-calculate power of 2
            int pow[] = new int[nums.length];
            pow[0] = 1;
            for (int i = 1; i < nums.length; i++)
                pow[i] = (pow[i - 1] * 2) % mod;

            int i = 0, j = nums.length - 1;
            int result = 0;
            while (i <= j) {
                int min = nums[i];
                int max = nums[j];
                if (min + max <= target) {
                    result = (result + pow[j - i]) % mod;
                    i++;
                } else if (min + max > target)
                    j--;

            }
            return result;
        }
    }
}
