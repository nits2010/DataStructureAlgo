package DataStructureAlgo.Java.LeetCode2025.ProblemSet.DynamicProgramming.matrix._221;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/19/2024
 * Question Title:
 * Link:
 * Description:
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
 * @easy <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

/**
 * Author: Nitin Gupta
 * Date: 11/19/2024
 * Title: 221. Maximal Square
 * Links: https://leetcode.com/problems/maximal-square/description/
 * Description:
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * Example 3:
 * <p>
 * Input: matrix = [["0"]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
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
 * @Matrix <p><p>
 * Company Tags
 * -----
 * @Microsoft
 * @Visa
 * @Amazon
 * @Google
 * @IBM <p>
 * -----
 * @Editorial -----
 * <p><p>
 * @OptimalSoltuion
 */
public class MaximalSquare_221 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}, 4));
        tests.add(test(new char[][]{{'0', '1'}, {'1', '0'}}, 1));
        tests.add(test(new char[][]{{'0'}}, 0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(char[][] matrix, int expected) {

        CommonMethods.printTest(new String[]{"Matrix", "Expected"}, true, matrix, expected);

        int output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.maximalSquare(matrix);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Solution", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int maximalSquare(char[][] matrix) {
            int maxArea = 0;
            int n = matrix.length;
            int m = matrix[0].length;

            int[][] nums = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {

                    nums[i][j] = matrix[i][j] - '0';

                    if (i > 0 && j > 0 && nums[i][j] >= 1) {

                        nums[i][j] = 1 + Math.min(Math.min(nums[i - 1][j - 1], nums[i - 1][j]),
                                nums[i][j - 1]);

                    }

                    maxArea = Math.max(maxArea, nums[i][j]);

                }
            }
            return maxArea * maxArea;
        }
    }
}
