package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SumProblems._1;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/1/2025
 * Question Title: 167. Two Sum II - Input Array Is Sorted
 * Link: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 * Description:Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 *
 *
 * Constraints:
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @Medium
 * @Array
 * @TwoPointers
 * @BinarySearch
 *
 * <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class TwoSumIIInputArrayIsSorted_167 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{2, 7, 11, 15}, 9, new int[]{1, 2}));
        tests.add(test(new int[]{2, 3, 4}, 6, new int[]{1, 3}));
        tests.add(test(new int[]{-1, 0}, -1, new int[]{1, 2}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] numbers, int target, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Numbers", "target", "Expected"}, true, numbers, target, expected);

        int [] output ;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.twoSum(numbers, target);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int []result = {-1,-1};

            int i = 0, j = numbers.length - 1;

            while (i < j){

                long sum = numbers[i] + numbers[j];
                if(sum == target){
                    return new int []{i+1, j+1};
                }

                if(sum < target)
                    i++;
                else
                    j--;

            }
            return result;
        }
    }
}
