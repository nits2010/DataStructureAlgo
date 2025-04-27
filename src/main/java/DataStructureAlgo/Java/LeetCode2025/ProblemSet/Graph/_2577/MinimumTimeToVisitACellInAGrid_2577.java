package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._2577;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path.DijkstraShortestPath;


/**
 * Author: Nitin Gupta
 * Date: 11/30/2024
 * Question Title: 2577. Minimum Time to Visit a Cell In a Grid
 * Link: https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/description/
 * Description: You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].
 * <p>
 * You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.
 * <p>
 * Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
 * Output: 7
 * Explanation: One of the paths that we can take is the following:
 * - at t = 0, we are on the cell (0,0).
 * - at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
 * - at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
 * - at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
 * - at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
 * - at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
 * - at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
 * - at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
 * The final time is 7. It can be shown that it is the minimum time possible.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
 * Output: -1
 * Explanation: There is no path from the top left to the bottom-right cell.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * 0 <= grid[i][j] <= 105
 * grid[0][0] == 0
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._2290.MinimumObstacleRemovalToReachCorner_2290}
 * extension {@link DijkstraShortestPath}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @Breadth-FirstSearch
 * @Graph
 * @Heap(PriorityQueue)
 * @Matrix
 * @ShortestPath <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumTimeToVisitACellInAGrid_2577 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{0, 1, 3, 2}, {5, 1, 2, 5}, {4, 3, 8, 6}}, 7));
        tests.add(test(new int[][]{{0, 2, 4}, {3, 2, 1}, {1, 0, 4}}, -1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] grid, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Grid", "Expected"}, true, grid, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_Dijkstra solutionDijkstra = new Solution_Dijkstra();
        output = solutionDijkstra.minimumTime(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_Dijkstra {

        public int minimumTime(int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;

            if (grid[0][1] > 1 && grid[1][0] > 1)
                return -1;

            //to track the visited cells
            boolean[][] visited = new boolean[m][n];

            //To track the minimum time, (time, i, j )
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            pq.offer(new int[]{0, 0, 0}); // from first cell, time is always 0

            int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            while (!pq.isEmpty()) {

                int[] curr = pq.poll();
                int time = curr[0];
                int i = curr[1];
                int j = curr[2];

                //if we have reached the last cell
                if (i == m - 1 && j == n - 1)
                    return time;

                // Skip if cell already visited
                if (visited[i][j])
                    continue;

                visited[i][j] = true;

                for (int[] d : dir) {
                    int r = i + d[0];
                    int c = j + d[1];

                    int newTime = time + 1; // +1 to reach (r,c) from (i,j)
                    //explore the unvisited valid cells
                    if (isSafe(r, c, m, n) && !visited[r][c]) {

                        int wastTime = 0;
                        //we may need to waste time here if time < grid[r][c]
                        if (newTime < grid[r][c]) {

                            //we need to do back and forth to one of the visited adj cells, each jump
                            //will waste unit 2 of time, and then it will take additionally 1 unit of time to go (r,c) from (i,j)

                            //hence, if the time difference between diff = grid[r][c] - time(i,j) is odd,
                            // then we need to waste diff/2 time and remaining 1 unit time will be utilized to jump to (r,c)
                            // however, if diff is even then we need an additional 1 time to jump to (r,c) post wasting diff time.

                            int diff = grid[r][c] - time;

                            if (diff % 2 == 1) {
                                wastTime = diff - 1;
                            } else {
                                wastTime = diff;
                            }


                        }

                        newTime += wastTime;
                        pq.offer(new int[]{newTime, r, c});
                    }

                }

            }

            return -1;

        }

        private boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }


    static class Solution_Dijkstra_op {

        public int minimumTime(int[][] grid) {

            int m = grid.length;
            int n = grid[0].length;

            if (grid[0][1] > 1 && grid[1][0] > 1)
                return -1;

            //to track the visited cells
            boolean[][] visited = new boolean[m][n];

            //To track the minimum time, (time, i, j )
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            pq.offer(new int[]{0, 0, 0}); // from first cell, time is always 0

            int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            while (!pq.isEmpty()) {

                int[] curr = pq.poll();
                int time = curr[0];
                int i = curr[1];
                int j = curr[2];


                for (int[] d : dir) {
                    int r = i + d[0];
                    int c = j + d[1];

                    int newTime = time + 1; // +1 to reach (r,c) from (i,j)
                    //explore the unvisited valid cells
                    if (isSafe(r, c, m, n) && !visited[r][c]) {

                        int wastTime = 0;
                        //we may need to waste time here if time < grid[r][c]
                        if (newTime < grid[r][c]) {

                            //we need to do back and forth to one of the visited adj cells, each jump
                            //will waste unit 2 of time, and then it will take additionally 1 unit of time to go (r,c) from (i,j)

                            //hence, if the time difference between diff = grid[r][c] - time(i,j) is odd,
                            // then we need to waste diff/2 time and remaining 1 unit time will be utilized to jump to (r,c)
                            // however, if diff is even then we need an additional 1 time to jump to (r,c) post wasting diff time.

                            int diff = grid[r][c] - time;

                            if (diff % 2 == 1) {
                                wastTime = diff - 1;
                            } else {
                                wastTime = diff;
                            }


                        }

                        newTime += wastTime;

                        if (r == m - 1 && c == n - 1)
                            return newTime;

                        visited[r][c] = true;

                        pq.offer(new int[]{newTime, r, c});
                    }

                }

            }

            return -1;

        }

        private boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }
}
