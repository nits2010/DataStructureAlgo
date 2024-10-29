package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.matrix._2684;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/29/2024
 * Question Category: 2684. Maximum Number of Moves in a Grid
 * Description: https://leetcode.com/problems/maximum-number-of-moves-in-a-grid/description/
 * You are given a 0-indexed m x n matrix grid consisting of positive integers.
 * <p>
 * You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
 * <p>
 * From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
 * Return the maximum number of moves that you can perform.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
 * Output: 3
 * Explanation: We can start at the cell (0, 0) and make the following moves:
 * - (0, 0) -> (0, 1).
 * - (0, 1) -> (1, 2).
 * - (1, 2) -> (2, 3).
 * It can be shown that it is the maximum number of moves that can be made.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[3,2,4],[2,1,9],[1,1,7]]
 * Output: 0
 * Explanation: Starting from any cell in the first column we cannot perform any moves.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * 1 <= grid[i][j] <= 106
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @Matrix
 * @medium
 * @Array
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class MaximumNumberOfMovesInAGrid_2684 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[][]{{1000000, 92910, 1021, 1022, 1023, 1024, 1025, 1026, 1027, 1028, 1029, 1030, 1031, 1032, 1033, 1034, 1035, 1036, 1037, 1038, 1039, 1040, 1041, 1042, 1043, 1044, 1045, 1046, 1047, 1048, 1049, 1050, 1051, 1052, 1053, 1054, 1055, 1056, 1057, 1058, 1059, 1060, 1061, 1062, 1063, 1064, 1065, 1066, 1067, 1068},
                {1069, 1070, 1071, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079, 1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091, 1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103, 1104, 1105, 1106, 1107, 1108, 1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118}}, 49);
        test &= test(new int[][]{{2, 4, 3, 5}, {5, 4, 9, 3}, {3, 4, 2, 11}, {10, 9, 13, 15}}, 3);
        test &= test(new int[][]{{3, 2, 4}, {2, 1, 9}, {1, 1, 7}}, 0);
        CommonMethods.printResult(test);

    }


    private static boolean test(int[][] grid, int expected) {
        CommonMethods.print(new String[]{"Grid", "Expected"}, true, grid, expected);
        int output;
        boolean pass, finalPass = true;
        Solution solution = new Solution();
        output = solution.maxMoves(grid);
        pass = output == expected;
        CommonMethods.print(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");
        finalPass &= pass;
        return finalPass;
    }

    static class Solution {
        public int maxMoves(int[][] grid) {
            int row = grid.length;
            int col = grid[0].length;

            int[][] dp = new int[row][col];
            for (int i = 0; i < row; i++)
                Arrays.fill(dp[i], -1);

            int max = 0;
            for (int i = 0; i < row; i++) {
                // if(!visited[i][0])
                max = Math.max(max, maxMoves(grid, row, col, dp, i, 0));
            }
            return max;
        }

        private int maxMoves(int[][] grid, int row, int col, int[][] dp,  int i, int j) {
            if (!isSafe(i, j, row, col))
                return 0;

            //if already calculated
            if (dp[i][j] != -1)
                return dp[i][j];

            //move different directions in grid
            int currentEle = grid[i][j];
            int max = 0 ;

            //up
            if (isSafe(i - 1, j + 1, row, col) && grid[i - 1][j + 1] > currentEle)
                max = Math.max(max,maxMoves(grid, row, col, dp, i - 1, j + 1) + 1);

            //right
            if (isSafe(i, j + 1, row, col) && grid[i][j + 1] > currentEle)
                max = Math.max(max, maxMoves(grid, row, col, dp, i, j + 1) + 1);

            //dig
            if (isSafe(i + 1, j + 1, row, col) && grid[i + 1][j + 1] > currentEle)
                max = Math.max(max, maxMoves(grid, row, col, dp, i + 1, j + 1) + 1);

            //cache it for future use
            return dp[i][j] = max;

        }

        private boolean isSafe(int i, int j, int row, int col) {
            return i >= 0 && i < row && j >= 0 && j < col;
        }
    }
}
