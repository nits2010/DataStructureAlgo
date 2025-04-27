package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1975;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/24/2024
 * Question Title: 1975. Maximum Matrix Sum
 * Link: https://leetcode.com/problems/maximum-matrix-sum/description
 * Description: You are given an n x n integer matrix. You can do the following operation any number of times:
 * <p>
 * Choose any two adjacent elements of matrix and multiply each of them by -1.
 * Two elements are considered adjacent if and only if they share a border.
 * <p>
 * Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,-1],[-1,1]]
 * Output: 4
 * Explanation: We can follow the following steps to reach sum equals 4:
 * - Multiply the 2 elements in the first row by -1.
 * - Multiply the 2 elements in the first column by -1.
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * Output: 16
 * Explanation: We can follow the following step to reach sum equals 16:
 * - Multiply the 2 last elements in the second row by -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -105 <= matrix[i][j] <= 105
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
 * @Array
 * @Greedy
 * @Matrix
 * @medium <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumMatrixSum_1975 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{1, -1}, {-1, 1}}, 4));
        tests.add(test(new int[][]{{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}}, 16));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] matrix, long expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Matrix", "Expected"}, true, matrix, expected);

        long output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.maxMatrixSum(matrix);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    /**
     * Solution
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    static class Solution {
        public long maxMatrixSum(int[][] matrix) {
            long sum = 0;
            int smallest = Integer.MAX_VALUE;
            int negCount = 0;

            // get a sum of all cells
            for (int[] rows : matrix) {
                for (int element : rows) {
                    if (element < 0) {
                        negCount++;
                        element = -element;
                    }
                    sum += element;
                    smallest = Math.min(smallest, element);
                }
            }

            if (negCount % 2 != 0)
                //the smallest element must be counted during addition, that count has to remove and additionally, it needs to remove from overall sum as well, 2*smallest
                sum -= 2L * smallest;

            return sum;

        }
    }
}
