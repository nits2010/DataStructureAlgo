package Java.LeetCode.graph.unique.paths;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-04
 * Description:https://leetcode.com/problems/unique-paths-ii/
 * <p>
 * 63. Unique Paths II
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p>
 * <p>
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * <p>
 * Extension of {@link UniquePathsI}
 */
public class UniquePathsII {

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

        UniquePathsIIBacktracking backtracking = new UniquePathsIIBacktracking();
        UniquePathIIDPTopDown pathIIDPTopDown = new UniquePathIIDPTopDown();
        UniquePathsIIDPBottomUp pathsIIDPBottomUp = new UniquePathsIIDPBottomUp();
        UniquePathsIIDPBottomUpSpaceOptimize bottomUpSpaceOptimize = new UniquePathsIIDPBottomUpSpaceOptimize();

        System.out.println("\n Backtracking :" + backtracking.uniquePathsWithObstacles(maze));
        System.out.println("\n DP Top Down :" + pathIIDPTopDown.uniquePathsWithObstacles(maze));
        System.out.println("\n DP Bottom Up  :" + pathsIIDPBottomUp.uniquePathsWithObstacles(maze));
        System.out.println("\n DP Bottom Up space optimized :" + bottomUpSpaceOptimize.uniquePathsWithObstacles(maze));
    }
}


/**
 * We can solve this problem through backtracking.
 * Backtracking
 * 1. Our goal: To reach destination cell (m-1,n-1), once reach it counted as one path
 * 2. Our constraints: a) We can not move outside of boundary b) we can't go through obstacles
 * 3. Our choices: a) we can move either Down (i+1,j) or right (i, j+1) from any cell
 * <p>
 * Complexity:
 * To reach a cell there are two way and we try all the source cell to reach this cell.
 * Complexity: O(2^(m*n)) /O (m*n)
 * <p>
 * TLE
 */
class UniquePathsIIBacktracking {


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
                    + uniquePaths(maze, m, n, sx, sy + 1, dx, dy);//right

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
 * In above backtracking, we visit same cell[i][j] and try to reach destination[m-1][n-1].
 * This happen again n again for different source cell [sx][sy] for this cell [i][j].
 * <p>
 * Since there are overlapping sub-problems, we can cache them; Hence DP
 * <p>
 * dp[i][j] = dp[i+1][j] + dp[i][j+1] ; [i,j] is in range of [m,n]
 * *        = 0 [i,j] is Not in range of [m,n]
 * base case:
 * i==dx, j==dy => dp[i][j] = 1
 * <p>
 * dp[sx][sy] is output
 * <p>
 * <p>
 * <p>
 * Complexity : To reach a cell there are two way and we try them only once from the source cell to reach this cell.
 * O(m*n) / O (m*n)
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
 * Memory Usage: 40.6 MB, less than 30.77% of Java online submissions for Unique Paths II.
 */
class UniquePathIIDPTopDown {


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

        if (isSafe(sx, sy, m, n, maze)) {

            //1. Our goal: To reach destination cell (m-1,n-1), once reach it counted as one path
            if (sx == dx && sy == dy)
                return dp[sx][sy] = 1;

            if (dp[sx][sy] != -1)
                return dp[sx][sy];

            return dp[sx][sy] =
                    uniquePaths(maze, dp, m, n, sx + 1, sy, dx, dy)  //down
                            +
                            uniquePaths(maze, dp, m, n, sx, sy + 1, dx, dy);//right
        }
        return 0;
    }

    private boolean isSafe(int sx, int sy, int m, int n, int[][] maze) {
        if (sx >= m || sy >= n || sx < 0 || sy < 0 || maze[sx][sy] == 1) //Line change from UniqPathsI
            return false;
        return true;
    }
}


/**
 * * dp[i][j] = dp[i-1][j] + dp[i][j-1] ; [i,j] is in range of [m,n]
 * * *        = 0 [i,j] is Not in range of [m,n]
 * * base case:
 * * i==0 or j==0 => dp[i][j] = 0
 * * i==dx, j==dy => dp[i][j] = 1
 * * <p>
 * * dp[dx][dy] is output
 * <p>
 * * Complexity : To reach a cell there are two way and we try them only once from the source cell to reach this cell.
 * * O(m*n) / O (m*n)
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
 * Memory Usage: 40.9 MB, less than 16.92% of Java online submissions for Unique Paths II.
 */
class UniquePathsIIDPBottomUp {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        final int dp[][] = new int[m][n];

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
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; //we can come here either down or right
            }

        }

        return dp[m - 1][n - 1];
    }


    /**
     * Build using pre-check base cases
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstaclesLong(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        final int dp[][] = new int[m][n];


        for (int i = 0; i < m; i++)
            if (obstacleGrid[i][0] != 1)
                dp[i][0] = 1;
            else
                break;


        for (int j = 0; j < n; j++)
            if (obstacleGrid[0][j] != 1)
                dp[0][j] = 1;
            else
                break;

        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {

                if (obstacleGrid[i][j] != 1) {

                    if (obstacleGrid[i - 1][j] != 1)
                        dp[i][j] = dp[i - 1][j];

                    if (obstacleGrid[i][j - 1] != 1)
                        dp[i][j] += dp[i][j - 1];
                }
            }
        }


        return dp[m - 1][n - 1];


    }

}

/**
 * * dp[i][j] = dp[i-1][j] + dp[i][j-1] ; [i,j] is in range of [m,n]
 * * *        = 0 [i,j] is Not in range of [m,n]
 * * base case:
 * * i==0 or j==0 => dp[i][j] = 0
 * * i==dx, j==dy => dp[i][j] = 1
 * * <p>
 * * dp[dx][dy] is output
 * <p>
 * We can see we are using only two rows at a time. We can optimize the space
 * dp[i-1][j] => dp[j] old
 * dp[i][j-1] => dp[j-1] old
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
 * Memory Usage: 39.6 MB, less than 58.46% of Java online submissions for Unique Paths II.
 */
class UniquePathsIIDPBottomUpSpaceOptimize {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        final int dp[] = new int[n];

        if (obstacleGrid[0][0] == 1)
            return 0;

        for (int i = 0; i < m; i++) {


            for (int j = 0; j < n; j++) {

                /**
                 * If this is obstacle , can't move from here to anywhere
                 */
                if (obstacleGrid[i][j] == 1)
                    dp[j] = 0;
                else if (i == 0 && j == 0) //if this is a source, then we can definitely reach here by 1 way
                    dp[j] = 1;
                else if (i == 0) //if this is first row, and moving right direction then we can do whatever we did on previous step
                    dp[j] = dp[j - 1];
                else if (j == 0) //if this is first col, and moving down direction then we can do whatever we did on previous step
                    dp[j] = dp[j];
                else
                    dp[j] = dp[j] + dp[j - 1]; //we can come here either down or right
            }

        }

        return dp[n - 1];
    }


}
