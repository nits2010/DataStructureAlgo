package Java.LeetCode.graph;

import Java.HelpersToPrint.Printer;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-04
 * Description: https://leetcode.com/problems/minimum-path-sum/
 * <p>
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * <p>
 * {@link Java.LeetCode.graph.unique.paths.UniquePathsI}
 * {@link Java.LeetCode.graph.unique.paths.UniquePathsII}
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        test(new int[][]
                {
                        {1, 3, 1},
                        {1, 5, 1},
                        {4, 2, 1}
                }, 7);

        test(new int[][]
                {
                        {1, 3, 1},
                        {1, 9, 1},
                        {1, 2, 8}
                }, 13);
    }

    private static void test(int[][] grid, int expected) {
        System.out.println("\n Input :" + Printer.toString(grid) + " expected :" + expected);
        System.out.println(" Backtracking : " + MinimumPathSumBacktracking.minPathSum(grid));
        System.out.println(" Top Down DP : " + MinimumPathSumDPTopDown.minPathSum(grid));
        System.out.println(" Bottom up DP : " + MinimumPathSumDPBottomUp.minPathSum(grid));
        System.out.println(" Bottom up  DP Space optimized : " + MinimumPathSumDPBottomUpSpaceOptimized.minPathSum(grid));
    }


}

/**
 * O(m*n)^2 / O(m*n)
 * TLE
 */
class MinimumPathSumBacktracking {
    public static int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        boolean dp[][] = new boolean[m][n];

        return minPathSum(grid, 0, 0, m - 1, n - 1, m, n, dp);

    }

    private static int minPathSum(int[][] grid, int sx, int sy, int dx, int dy, int m, int n, boolean[][] dp) {

        if (sx >= m || sy >= n || sx < 0 || sy < 0 || dp[sx][sy])
            return Integer.MAX_VALUE;

        if (sx == dx && sy == dy)
            return grid[dx][dy];

        dp[sx][sy] = true;


        int count = Math.min(
                minPathSum(grid, sx + 1, sy, dx, dy, m, n, dp),
                minPathSum(grid, sx, sy + 1, dx, dy, m, n, dp)) + grid[sx][sy];

        dp[sx][sy] = false;
        return count;

    }

}

/**
 * O(m*n) / O(m*n)
 * Runtime: 1 ms, faster than 99.51% of Java online submissions for Minimum Path Sum.
 * Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Minimum Path Sum.
 */
class MinimumPathSumDPTopDown {
    public static int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int dp[][] = new int[m][n];

        return minPathSum(grid, 0, 0, m - 1, n - 1, m, n, dp);

    }

    private static int minPathSum(int[][] grid, int sx, int sy, int dx, int dy, int m, int n, int[][] dp) {

        if (sx >= m || sy >= n || sx < 0 || sy < 0)
            return Integer.MAX_VALUE;

        if (sx == dx && sy == dy)
            return dp[sx][sy] = grid[dx][dy];

        if (dp[sx][sy] != 0)
            return dp[sx][sy];


        return dp[sx][sy] = Math.min(
                minPathSum(grid, sx + 1, sy, dx, dy, m, n, dp),
                minPathSum(grid, sx, sy + 1, dx, dy, m, n, dp)) + grid[sx][sy];

    }

}

class MinimumPathSumDPBottomUp {
    /**
     * dp[i][j] = {
     * *                 Min ( dp[i-1][j] , dp[i][j-1] ) + grid[i][j] i>0 and j>0
     * *                 dp[i][j-1] + grid[i][j] ; i ==0 and j>0
     * *                 dp[i-1][j] + grid[i][j] ; i >0 and j==0
     * *                 grid[i][j] ; i==j==0
     * *        }
     * <p>
     * O(m*n) / O(m*n)
     * Runtime: 2 ms, faster than 90.21% of Java online submissions for Minimum Path Sum.
     * Memory Usage: 41.8 MB, less than 87.84% of Java online submissions for Minimum Path Sum.
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int dp[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0)
                    dp[i][j] = grid[i][j];
                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                else
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }


        return dp[m - 1][n - 1];

    }
}


class MinimumPathSumDPBottomUpSpaceOptimized {
    /**
     * dp[i][j] = {
     * *                 Min ( dp[i-1][j] , dp[i][j-1] ) + grid[i][j] i>0 and j>0
     * *                 dp[i][j-1] + grid[i][j] ; i ==0 and j>0
     * *                 dp[i-1][j] + grid[i][j] ; i >0 and j==0
     * *                 grid[i][j] ; i==j==0
     * *        }
     * Only two rows at a time.
     * dp[i-1][j] => dp[j]
     * dp[i][j-1] => dp[j-1]
     * <p>
     * O(m*n) / O(n)
     * Runtime: 2 ms, faster than 90.21% of Java online submissions for Minimum Path Sum.
     * Memory Usage: 41.8 MB, less than 87.84% of Java online submissions for Minimum Path Sum.
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;
        int n = grid[0].length;

        int dp[] = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0)
                    dp[j] = grid[i][j];
                else if (i == 0)
                    dp[j] = dp[j - 1] + grid[i][j];
                else if (j == 0)
                    dp[j] = dp[j] + grid[i][j];
                else
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }


        return dp[n - 1];

    }
}