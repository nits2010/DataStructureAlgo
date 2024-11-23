package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1072;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/23/2024
 * Question Title: 1072. Flip Columns For Maximum Number of Equal Rows
 * Link: https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/description
 * Description: You are given an m x n binary matrix matrix.
 * <p>
 * You can choose any number of columns in the matrix and flip every cell in that column (i.e., Change the value of the cell from 0 to 1 or vice versa).
 * <p>
 * Return the maximum number of rows that have all values equal after some number of flips.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[0,1],[1,1]]
 * Output: 1
 * Explanation: After flipping no values, 1 row has all values equal.
 * Example 2:
 * <p>
 * Input: matrix = [[0,1],[1,0]]
 * Output: 2
 * Explanation: After flipping values in the first column, both rows have equal values.
 * Example 3:
 * <p>
 * Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
 * Output: 2
 * Explanation: After flipping values in the first two columns, the last two rows have equal values.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is either 0 or 1.
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
 * @HashTable
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

public class FlipColumnsForMaximumNumberOfEqualRows_1072 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{0, 1}, {1, 1}}, 1));
        tests.add(test(new int[][]{{0, 1}, {1, 0}}, 2));
        tests.add(test(new int[][]{{0, 0, 0}, {0, 0, 1}, {1, 1, 0}}, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] matrix, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Matrix", "Expected"}, true, matrix, expected);

        int output;
        boolean pass, finalPass = true;

        Solution s = new Solution();

        output = s.maxEqualRowsAfterFlips(matrix);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Solution", "Expected"}, false, output, pass ? "Pass" : "Fail");

        SolutionV2 sV2 = new SolutionV2();
        output = sV2.maxEqualRowsAfterFlips(matrix);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"SolutionV2", "Expected"}, false, output, pass ? "Pass" : "Fail");
        return finalPass;

    }

    static class Solution {

        /**
         * T/S: O(mn)/O(mn)
         *
         * @param matrix
         * @return
         */
        public int maxEqualRowsAfterFlips(int[][] matrix) {
            //This will hold the row string vs its freq count
            Map<String, Integer> rowVsCount = new HashMap<>();
            int m = matrix[0].length;
            int maxFreq = 0;

            for (int[] rows : matrix) {
                //current row string
                StringBuilder s = new StringBuilder();
                //append either 0 or 1 to start with
                s.append('1');

                //if all column values are same, then append 1 or otherwise 0 where different
                for (int c = 1; c < m; c++) {
                    s.append((rows[c - 1] == rows[c]) ? '1' : '0');
                }
                String str = s.toString();

                //count freq
                rowVsCount.put(str, rowVsCount.getOrDefault(str, 0) + 1);

                maxFreq = Math.max(maxFreq, rowVsCount.get(str));
            }

            return maxFreq;

        }
    }

    static class SolutionV2 {
        public int maxEqualRowsAfterFlips(int[][] matrix) {
            Map<String, Integer> count = new HashMap<>();

            for (int[] row : matrix) {
                StringBuilder key = new StringBuilder();

                if (row[0] == 1) {
                    for (int n : row) {
                        key.append(n == 0 ? 1 : 0);
                    }
                } else {
                    for (int n : row) {
                        key.append(n);
                    }
                }

                count.merge(key.toString(), 1, Integer::sum);
            }

            return Collections.max(count.values());
        }
    }

}
