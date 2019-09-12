package Java.LeetCode.island;

import Java.HelpersToPrint.Printer;
import Java.graph.graph.types.Edges;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-12
 * Description: https://leetcode.com/problems/max-area-of-island/
 * 695. Max Area of Island [Medium]
 * <p>
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * <p>
 * Example 1:
 * <p>
 * [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]
 * ]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 * <p>
 * Same as {@link Java.companyWise.GroupOn.IslandsSizeCount}, here we build size
 */
public class MaxAreaIsland {

    public static void main(String[] args) {
        test(new int[][]
                {
                        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}

                }, 6);


        test(new int[][]{{1, 1, 1, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}, 7);
        test(new int[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}}, 14);
        test(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}}, 0);


    }

    private static void test(int[][] grid, int expected) {
        System.out.println("\n Grid:\n" + Printer.toString(grid) + " expected :" + expected);
        System.out.println("Obtained :" + maxAreaOfIsland(grid));
    }

    /**
     * O(n^2)/O(1)
     * Runtime: 2 ms, faster than 100.00% of Java online submissions for Max Area of Island.
     * Memory Usage: 43.9 MB, less than 55.56% of Java online submissions for Max Area of Island.
     *
     * @param grid
     * @return
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int size[] = {0};
        int maxSize = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {

                    size[0] = 0;
                    dfs(grid, m, n, i, j, size);
                    maxSize = Math.max(maxSize, size[0]);

                }
            }
        }

        return maxSize;
    }

    private static void dfs(int[][] grid, int m, int n, int i, int j, int[] size) {

        if (!isSafe(grid, m, n, i, j))
            return;

        //marked it used
        grid[i][j] = 0;

        //increase the size for current island
        size[0]++;

        //run 4 directions
        dfs(grid, m, n, i + 1, j, size);
        dfs(grid, m, n, i - 1, j, size);
        dfs(grid, m, n, i, j + 1, size);
        dfs(grid, m, n, i, j - 1, size);


    }

    private static boolean isSafe(int[][] grid, int m, int n, int r, int c) {
        if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1)
            return true;
        return false;
    }


}
