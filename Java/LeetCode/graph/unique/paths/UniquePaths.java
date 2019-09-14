package Java.LeetCode.graph.unique.paths;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-04
 * Description:
 * *
 * * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * *
 * * The robot can only move  down or right or left or up at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * *
 * * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * *
 * * Note: m and n will be at most 100.
 * *
 * * Example 1:
 * *
 * * Input:
 * * [
 * * [0,0,0],
 * * [0,1,0],
 * * [0,0,0]
 * * ]
 * * Output: 2
 * * Explanation:
 * * There is one obstacle in the middle of the 3x3 grid above.
 * * There are two ways to reach the bottom-right corner:
 * * 1. Right -> Right -> Down -> Down
 * * 2. Down -> Down -> Right -> Right
 * *
 * * Extension of {@link UniquePathsII} here we allow 4 direction
 * https://www.techiedelight.com/find-total-number-unique-paths-maze-source-destination/
 */
public class UniquePaths {

    public static void main(String[] args) {
        test(new int[][]
                {
                        {0, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0}
                }, 2);

        test(new int[][]
                {
                        {0, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }, 7);

        test(new int[][]
                {
                        {0, 1, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }, 4);

        test(new int[][]
                {
                        {0, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0}
                }, 1);

        test(new int[][]
                {
                        {0, 1, 0},
                        {1, 1, 0},
                        {0, 1, 0}
                }, 0);
    }

    private static void test(int[][] maze, int expected) {
        System.out.println("\n grid :\n" + Printer.toString(maze) + " expected :" + expected);

        UniquePathsBacktracking backtracking = new UniquePathsBacktracking();
        UniquePathDPTopDown pathIIDPTopDown = new UniquePathDPTopDown();
        UniquePathsDPBottomUp pathsIIDPBottomUp = new UniquePathsDPBottomUp();

        System.out.println("\n Backtracking :" + backtracking.uniquePathsWithObstacles(maze));
        System.out.println("\n DP Top Down :" + pathIIDPTopDown.uniquePathsWithObstacles(maze));
        System.out.println("\n DP Bottom Up  :" + pathsIIDPBottomUp.uniquePathsWithObstacles(maze));
    }
}

/**
 * We can solve this problem through backtracking.
 * Backtracking
 * 1. Our goal: To reach destination cell (m-1,n-1), once reach it counted as one path
 * 2. Our constraints: a) We can not move outside of boundary b) we can't go through obstacles
 * 3. Our choices: a) we can move either Down (i+1,j), Up  (i-1,j1), Right (i, j+1) left (i, j-1) from any cell
 * <p>
 * Complexity:
 * To reach a cell there are two way and we try all the source cell to reach this cell.
 * Complexity: O(4^(m*n)) /O (m*n)
 * <p>
 * TLE
 */
class UniquePathsBacktracking {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;


        final int sx = 0, sy = 0;
        final int dx = m - 1, dy = n - 1;

        return uniquePaths(obstacleGrid, m, n, sx, sy, dx, dy);

    }

    private int uniquePaths(int[][] maze, int m, int n, int sx, int sy, int dx, int dy) {

        //1. Our goal: To reach destination cell (m-1,n-1), once reach it counted as one path
        if (sx == dx && sy == dy)
            return 1;

        int path = 0;
        if (isSafe(sx, sy, m, n, maze)) {

            maze[sx][sy] = -1; //not available for next round

            path = uniquePaths(maze, m, n, sx + 1, sy, dx, dy)  //down
                    + uniquePaths(maze, m, n, sx, sy + 1, dx, dy)//right
                    + uniquePaths(maze, m, n, sx - 1, sy, dx, dy)  //Up
                    + uniquePaths(maze, m, n, sx, sy - 1, dx, dy);//Left

            maze[sx][sy] = 0; // available for next round
        }
        return path;
    }

    private boolean isSafe(int sx, int sy, int m, int n, int[][] maze) {
        if (sx >= m || sy >= n || sx < 0 || sy < 0 || maze[sx][sy] == -1 || maze[sx][sy] == 1) //Line change from UniqPathsI
            return false;
        return true;
    }


}

/**
 * DOES NOT WORK
 * In above backtracking, we visit same cell[i][j] and try to reach destination[m-1][n-1].
 * This happen again n again for different source cell [sx][sy] for this cell [i][j].
 * <p>
 * Since there are overlapping sub-problems, we can cache them; Hence DP
 * <p>
 * dp[i][j] = dp[i+1][j] + dp[i][j+1] + dp[i-1][j] + dp[i][j-1] ; [i,j] is in range of [m,n]
 * *        = 0 [i,j] is Not in range of [m,n]
 * base case:
 * i==dx, j==dy => dp[i][j] = 1
 * <p>
 * dp[sx][sy] is output
 * Complexity : To reach a cell there are two way and we try them only once from the source cell to reach this cell.
 * O(m*n) / O (m*n)
 */
class UniquePathDPTopDown {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;


        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);

        final int sx = 0, sy = 0;
        final int dx = m - 1, dy = n - 1;

        return uniquePaths(obstacleGrid, dp, m, n, sx, sy, dx, dy);

    }

    private int uniquePaths(int[][] maze, int dp[][], int m, int n, int sx, int sy, int dx, int dy) {
        int count = 0;

        if (isSafe(sx, sy, m, n, maze)) {

            if (sx == dx && sy == dy)
                return dp[sx][sy] = 1;

            if (dp[sx][sy] != -1)
                return dp[sx][sy];

            maze[sx][sy] = -1;

            count = uniquePaths(maze, dp, m, n, sx + 1, sy, dx, dy) //down
                    +
                    uniquePaths(maze, dp, m, n, sx - 1, sy, dx, dy) //up
                    +
                    uniquePaths(maze, dp, m, n, sx, sy + 1, dx, dy) // right
                    +
                    uniquePaths(maze, dp, m, n, sx, sy - 1, dx, dy); //left

            maze[sx][sy] = 0;

            return dp[sx][sy] = count;

        }
        return count;

    }

    private boolean isSafe(int sx, int sy, int m, int n, int[][] maze) {
        if (sx >= m || sy >= n || sx < 0 || sy < 0 || maze[sx][sy] == 1 || maze[sx][sy] == -1)
            return false;
        return true;
    }

}


/**
 * DOES NOT WORK
 * * dp[i][j] = dp[i+1][j] + dp[i][j+1] + dp[i-1][j] + dp[i][j-1]  ; [i,j] is in range of [m,n]
 * * *        = 0 [i,j] is Not in range of [m,n]
 * * base case:
 * * i==0 or j==0 => dp[i][j] = 0
 * * i==dx, j==dy => dp[i][j] = 1
 * *
 * * dp[dx][dy] is output
 * * Complexity : To reach a cell there are two way and we try them only once from the source cell to reach this cell.
 * * O(m*n) / O (m*n)
 */
class UniquePathsDPBottomUp {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        final int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);

        if (obstacleGrid[0][0] == 1)
            return 0;

        for (int i = 0; i < m; i++) {


            for (int j = 0; j < n; j++) {

                /**
                 * If this is obstacle , can't move from here to anywhere
                 */
                if (obstacleGrid[i][j] == 1)
                    dp[i][j] = 0;
                else if (i == 0 && j == 0) //if this is a source, then we can definitely reach here by 1 way
                    dp[i][j] = 1;
                else if (i == 0) //if this is first row, and moving right direction then we can do whatever we did on previous step
                    dp[i][j] = dp[i][j - 1];
                else if (j == 0) //if this is first col, and moving down direction then we can do whatever we did on previous step
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j]  //up
                            + dp[i][j - 1]  //left
                            + (j + 1 < n ? dp[i][j + 1] : 0)  //right
                            + (i + 1 < m ? dp[i + 1][j] : 0); //down
            }

        }

        return dp[m - 1][n - 1];
    }
}
