package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Graph._2290;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.graph.questions.shortest.path.DijkstraShortestPath;


/**
 * Author: Nitin Gupta
 * Date: 11/28/2024
 * Question Title: 2290. Minimum Obstacle Removal to Reach Corner
 * Link: https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/description
 * Description: You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
 * <p>
 * 0 represents an empty cell,
 * 1 represents an obstacle that may be removed.
 * You can move up, down, left, or right from and to an empty cell.
 * <p>
 * Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
 * Output: 2
 * Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
 * It can be shown that we need to remove at least 2 obstacles, so we return 2.
 * Note that there may be other ways to remove 2 obstacles to create a path.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
 * Output: 0
 * Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 2 <= m * n <= 105
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DijkstraShortestPath}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Array
 * @Breadth-FirstSearch
 * @0-1BFS
 * @Graph
 * @Heap(PriorityQueue)
 * @Matrix
 * @ShortestPath
 * @hard <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumObstacleRemovalToReachCorner_2290 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}, 2));
        tests.add(test(new int[][]{{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}}, 0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[][] grid, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "Expected"}, true, grid, expected);

        int output;
        boolean pass, finalPass = true;

        Solution_Visited solutionVisited = new Solution_Visited();
        output = solutionVisited.minimumObstacles(grid);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Visited", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_Dijkstra solutionDijkstra = new Solution_Dijkstra();
        output = solutionDijkstra.minimumObstacles(grid);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Dijkstra", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_0_1_BFS solution01Bfs = new Solution_0_1_BFS();
        output = solution01Bfs.minimumObstacles(grid);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"0-1-BFS", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    //T: O(m*n * log(m*n)) [TLE]
    //S: O(m*n)
    static class Solution_Visited {
        static class Cost {
            int i, j;
            int cost;

            Cost(int x, int y, int c) {
                this.i = x;
                this.j = y;
                this.cost = c;
            }
        }

        public int minimumObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            //this will hold the row, col and the cost of reaching here.
            PriorityQueue<Cost> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
            pq.offer(new Cost(0, 0, grid[0][0]));

            //to make sure we dont revisit
            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;

            int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            while (!pq.isEmpty()) {

                Cost curr = pq.poll();
                int i = curr.i;
                int j = curr.j;
                int cost = curr.cost;

                //we have visited this i,j now
                visited[i][j] = true;

                //if we have reached at the right corner, then cost will be the minimum as we are using PQ
                if (i == m - 1 && j == n - 1) {
                    return cost;
                }

                //explore all directions
                for (int[] d : dir) {
                    int r = i + d[0];
                    int c = j + d[1];

                    //if safe and not visisted, then add to explore
                    if (isSafe(r, c, m, n) && !visited[r][c]) {
                        pq.offer(new Cost(r, c, cost + grid[r][c]));
                    }
                }

            }

            return -1;

        }

        boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }

    //T: O(m*n * log(m*n))
    //S: O(m*n)
    static class Solution_Dijkstra {

        public int minimumObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            //this will hold row, col, cost to reach (row,col)
            PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
            pq.offer(new int[]{0, 0, grid[0][0]});

            //this will hold cost to reach (i,j) from (0,0)
            int[][] costs = new int[m][n];
            for (int[] cost : costs) {
                Arrays.fill(cost, m + n);
            }

            int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            while (!pq.isEmpty()) {

                int[] curr = pq.poll();
                int i = curr[0];
                int j = curr[1];
                int cost = curr[2];

                //if we have reached at the right corner
                if (i == m - 1 && j == n - 1) {
                    return cost;
                }

                for (int[] d : dir) {
                    int r = i + d[0];
                    int c = j + d[1];

                    // if its valid direction
                    if (isSafe(r, c, m, n)) {

                        //now if the cost of reaching (r,c) get minimize, then update it and add to explore further
                        if (cost + grid[r][c] < costs[r][c]) {
                            costs[r][c] = cost + grid[r][c];
                            pq.offer(new int[]{r, c, cost + grid[r][c]});
                        }

                    }
                }

            }

            return costs[m - 1][n - 1];

        }

        boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }


    //T: O(m*n)
    //S: O(m*n)
    static class Solution_0_1_BFS {

        public int minimumObstacles(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int max = m + n;

            //this will hold row, col, cost to reach (row,col)

            Deque<int[]> deque = new LinkedList<>();
            deque.offerFirst(new int[]{0, 0, grid[0][0]});

            //this will that this (i,j) has been explored or not
            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;

            int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            while (!deque.isEmpty()) {

                int[] curr = deque.pollFirst();
                int i = curr[0];
                int j = curr[1];
                int cost = curr[2];

                //if we have reached at the right corner
                if (i == m - 1 && j == n - 1) {
                    return cost;
                }

                for (int[] d : dir) {
                    int r = i + d[0];
                    int c = j + d[1];

                    // if its valid direction and the one which is not explored
                    if (isSafe(r, c, m, n) && !visited[r][c]) {
                        visited[r][c] = true;

                        //now if the cost of reaching (r,c) get minimize, then update it and add to explore further

                        //now apply 0-1 BFS; less cost goes front, and high cost goes at last
                        //since we have two possibilities, as 0 or 1 then 0 cost front, 1 cost last

                        //need to go last ?
                        if (grid[r][c] == 1) {
                            deque.offerLast(new int[]{r, c, cost + 1});
                        } else {
                            deque.offerFirst(new int[]{r, c, cost});
                        }


                    }
                }

            }

            return -1;

        }

        boolean isSafe(int i, int j, int m, int n) {
            return i >= 0 && i < m && j >= 0 && j < n;
        }
    }
}
