package Java.LeetCode.island;

import Java.HelpersToPrint.Printer;
import Java.companyWise.GroupOn.IslandsSizeCount;

import static Java.HelpersToPrint.Printer.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-20
 * Description: https://leetcode.com/problems/number-of-islands/
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 * Example 2:
 * <p>
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * Output: 3
 * <p>
 * Count with Size : {@link IslandsSizeCount}
 * https://leetcode.com/problems/number-of-islands/discuss/363021/2-ms46.96-to-1ms-100-or-Explanation-or-Optimisation-or-Bonus-Question
 */
public class NumberIslands {

    private static char[][] getIsland() {

        char grid[][] = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        return grid;
    }

    private static char[][] getIsland1() {
        char grid[][] = {{'1', '1', '1', '1', '1'}, {'1', '1', '0', '1', '1'}, {'1', '1', '0', '0', '1'}, {'0', '0', '0', '0', '1'}};
        return grid;
    }

    private static char[][] getIsland2() {
        char grid[][] = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        return grid;
    }


    public static void main(String[] args) {
        test(getIsland());
        test(getIsland1());
        test(getIsland2());
    }

    private static void test(char[][] grid) {
        System.out.println("\nInput \n" + Printer.toString(grid));
        NumberIslandsDFS dfs = new NumberIslandsDFS();
        NumberIslandsDFSV2 dfs2 = new NumberIslandsDFSV2();
        NumberIslandsDFSDirectIteration dfs3 = new NumberIslandsDFSDirectIteration();

        char grid1[][] = copyOf(grid);
        char grid2[][] = copyOf(grid);
        char grid3[][] = copyOf(grid);

        System.out.println("dfs: " + dfs.numIslands(grid1));
        System.out.println("dfs2:" + dfs2.numIslands(grid2));
        System.out.println("dfs3:" + dfs3.numIslands(grid3));

    }


}

/**
 * O(n*n) / O(n*n)
 * Runtime: 2 ms, faster than 46.96% of Java online submissions for Number of Islands.
 * Memory Usage: 41 MB, less than 81.86% of Java online submissions for Number of Islands.
 */
class NumberIslandsDFS {
    private int row[] = {0, 0, 1, -1};
    private int col[] = {-1, 1, 0, 0};

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        boolean marked[][] = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!marked[i][j] && grid[i][j] == '1') {
                    numIslands(grid, i, j, n, m, marked);
                    count++;
                }

            }
        }
        return count;

    }

    private boolean isSafe(int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m)
            return false;
        return true;
    }

    private void numIslands(char[][] grid, int i, int j, int n, int m, boolean marked[][]) {

        marked[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int r = i + row[k];
            int c = j + col[k];

            if (isSafe(r, c, n, m) && !marked[r][c] && grid[r][c] == '1')
                numIslands(grid, r, c, n, m, marked);
        }

    }
}


/**
 * Instead of using marked array, use grid it self to mark.
 * for marking, make them 0
 * <p>
 * O(n*n) / O(1)
 * Runtime: 2 ms, faster than 46.96% of Java online submissions for Number of Islands.
 * Memory Usage: 39.9 MB, less than 100.00% of Java online submissions for Number of Islands.
 */
class NumberIslandsDFSV2 {
    private int row[] = {0, 0, 1, -1};
    private int col[] = {-1, 1, 0, 0};

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '1') {
                    numIslands(grid, i, j, n, m);
                    count++;
                }

            }
        }
        return count;

    }

    private boolean isSafe(int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m)
            return false;
        return true;
    }

    private void numIslands(char[][] grid, int i, int j, int n, int m) {

        grid[i][j] = '0';

        for (int k = 0; k < 4; k++) {
            int r = i + row[k];
            int c = j + col[k];

            if (isSafe(r, c, n, m) && grid[r][c] == '1')
                numIslands(grid, r, c, n, m);
        }

    }
}

/**
 * O(n*m) / O(1)
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Number of Islands.
 * Memory Usage: 40.6 MB, less than 100.00% of Java online submissions for Number of Islands.
 */
class NumberIslandsDFSDirectIteration {


    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '1') {
                    numIslands(grid, i, j, n, m);
                    count++;
                }

            }
        }
        return count;

    }

    private boolean isSafe(int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m)
            return false;
        return true;
    }

    private void numIslands(char[][] grid, int i, int j, int n, int m) {

        if (!isSafe(i, j, n, m))
            return;

        if (grid[i][j] == '1') {

            grid[i][j] = '0';
            numIslands(grid, i, j - 1, n, m);
            numIslands(grid, i, j + 1, n, m);
            numIslands(grid, i + 1, j, n, m);
            numIslands(grid, i - 1, j, n, m);
        }


    }
}