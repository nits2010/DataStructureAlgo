package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2257;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/21/2024
 * Question Title: 2257. Count Unguarded Cells in the Grid
 * Link: https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/?envType=daily-question&envId=2024-11-21
 * Description: You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
 * <p>
 * A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.
 * <p>
 * Return the number of unoccupied cells that are not guarded.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
 * Output: 7
 * Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
 * There are a total of 7 unguarded cells, so we return 7.
 * Example 2:
 * <p>
 * <p>
 * Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
 * Output: 4
 * Explanation: The unguarded cells are shown in green in the above diagram.
 * There are a total of 4 unguarded cells, so we return 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= m, n <= 105
 * 2 <= m * n <= 105
 * 1 <= guards.length, walls.length <= 5 * 104
 * 2 <= guards.length + walls.length <= m * n
 * guards[i].length == walls[j].length == 2
 * 0 <= rowi, rowj < m
 * 0 <= coli, colj < n
 * All the positions in guards and walls are unique.
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
 * @Matrix
 * @Simulation <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CountUnguardedCellsInTheGrid_2257 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(4, 6, new int[][]{{0, 0}, {1, 1}, {2, 3}}, new int[][]{{0, 1}, {2, 2}, {1, 4}}, 7));
        tests.add(test(3, 3, new int[][]{{1, 1}}, new int[][]{{0, 1}, {1, 0}, {2, 1}, {1, 2}}, 4));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int m, int n, int[][] guards, int[][] walls, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"m", "n", "Guards", "Walls", "Expected"}, true, m, n, guards, walls, expected);

        int output;
        boolean pass, finalPass = true;

        Solution_CountUnguardedCells solutionCountUnguardedCells = new Solution_CountUnguardedCells();
        output = solutionCountUnguardedCells.countUnguarded(m, n, guards, walls);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"CountUnguardedCells", "Pass"}, false, output, pass ? "Pass" : "Fail");

        Solution_CountGuardedCells solutionCountGuardedCells = new Solution_CountGuardedCells();
        output = solutionCountGuardedCells.countUnguarded(m, n, guards, walls);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"CountGuardedCells", "Pass"}, false, output, pass ? "Pass" : "Fail");

        return finalPass;

    }

    static class Solution_CountUnguardedCells {
        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            char[][] cells = new char[m][n];
            final char guarded = '#';

            // fill walls
            for (int[] w : walls) {
                cells[w[0]][w[1]] = 'W';
            }

            // fill guard
            for (int[] g : guards) {
                cells[g[0]][g[1]] = 'G';
            }

            // fill gurded cells
            for (int[] g : guards) {

                int r = g[0];
                int c = g[1];

                // right - east
                for (int i = c + 1; i < n; i++) {
                    if (cells[r][i] == 'W' || cells[r][i] == 'G')
                        break;
                    cells[r][i] = guarded;
                }

                // left - west
                for (int i = c - 1; i >= 0; i--) {
                    if (cells[r][i] == 'W' || cells[r][i] == 'G')
                        break;
                    cells[r][i] = guarded;
                }

                // up - north
                for (int j = r - 1; j >= 0; j--) {
                    if (cells[j][c] == 'W' || cells[j][c] == 'G')
                        break;
                    cells[j][c] = guarded;
                }

                // down - south
                for (int j = r + 1; j < m; j++) {
                    if (cells[j][c] == 'W' || cells[j][c] == 'G')
                        break;
                    cells[j][c] = guarded;
                }
            }

            int unguardedCells = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (cells[i][j] == '\u0000')
                        unguardedCells++;
                }
            }

            return unguardedCells;

        }
    }

    static class Solution_CountGuardedCells {
        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            int[][] cells = new int[m][n];
            final int guarded = -1;

            // fill walls
            for (int[] w : walls) {
                cells[w[0]][w[1]] = 1;
            }

            // fill guard
            for (int[] g : guards) {
                cells[g[0]][g[1]] = 1;
            }

            int guardedCells = 0;
            // fill guarded cells
            for (int[] g : guards) {

                int r = g[0];
                int c = g[1];
                int cell;
                int i, j;
                // right - east
                for (i = c + 1; i < n; i++) {
                    cell = cells[r][i];
                    if (cell == 1)
                        break;
                    if (cell != guarded) {
                        guardedCells++;
                        cells[r][i] = guarded;
                    }

                }

                // left - west
                for (i = c - 1; i >= 0; i--) {
                    cell = cells[r][i];
                    if (cell == 1)
                        break;
                    if (cell != guarded) {
                        guardedCells++;
                        cells[r][i] = guarded;
                    }

                }

                // up - north
                for (j = r - 1; j >= 0; j--) {
                    cell = cells[j][c];
                    if (cell == 1)
                        break;
                    if (cell != guarded) {
                        guardedCells++;
                        cells[j][c] = guarded;
                    }
                }

                // down - south
                for (j = r + 1; j < m; j++) {
                    cell = cells[j][c];
                    if (cell == 1)
                        break;
                    if (cell != guarded) {
                        guardedCells++;
                        cells[j][c] = guarded;
                    }
                }
            }
            int totalCells = m * n;
            int totalGuardedCells = guards.length + walls.length + guardedCells;

            return totalCells - totalGuardedCells;

        }
    }
}
