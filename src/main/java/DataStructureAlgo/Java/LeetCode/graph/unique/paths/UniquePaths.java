package DataStructureAlgo.Java.LeetCode.graph.unique.paths;

import  DataStructureAlgo.Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta
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
        System.out.println("\n grid :\n" + GenericPrinter.toString(maze) + " expected :" + expected);

        UniquePathsBacktracking backtracking = new UniquePathsBacktracking();

        System.out.println("\n Backtracking :" + backtracking.uniquePathsWithObstacles(maze));
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
 * {@link UniquePathsIII} check dp solution.
 */
class UniquePathDPTopDown {
}
