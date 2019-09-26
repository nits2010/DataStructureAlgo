package Java.LeetCode.graph.unique.paths;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-04
 * Description: https://leetcode.com/problems/unique-paths/
 * 62. Unique Paths [Medium]
 * <p>
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * <p>
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * <p>
 * How many possible unique paths are there?
 * <p>
 * <p>
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * Example 1:
 * <p>
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 * <p>
 * Input: m = 7, n = 3
 * Output: 28
 * <p>
 * Similar
 * https://www.geeksforgeeks.org/count-number-ways-reach-destination-maze/
 * https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 * https://www.geeksforgeeks.org/find-paths-given-source-destination/
 * https://www.geeksforgeeks.org/number-of-paths-from-source-to-destination-in-a-directed-acyclic-graph/
 */
public class UniquePathsI {
    public static void main(String[] args) {
        test(2, 2, 2);
        test(3, 3, 6);
        test(4, 4, 20);
        test(3, 2, 3);
        test(7, 3, 28);
        test(5, 8, 330);
        test(1, 8, 1);
        test(5, 1, 1);
        test(1, 1, 1);
        test(0, 1, 1);

    }

    private static void test(int m, int n, int expected) {
        System.out.println("\nm = " + m + " n = " + n + " expected : " + expected);
        UniquePathsIBacktracking backtracking = new UniquePathsIBacktracking();
        UniquePathsIDPTopDown pathsIDPTopDown = new UniquePathsIDPTopDown();
        UniquePathsIDPBottomUp pathsIDPBottomUp = new UniquePathsIDPBottomUp();
        UniquePathsIDPBottomUpSpaceOptimize pathsIDPBottomUpSpaceOptimize = new UniquePathsIDPBottomUpSpaceOptimize();
        UniquePathsIBinomialCoefficient binomialCoefficient = new UniquePathsIBinomialCoefficient();
        System.out.println("backtracking  : " + backtracking.uniquePaths(m, n));
        System.out.println("backtracking 2 : " + backtracking.uniquePaths2(m, n));
        System.out.println("DP Top Down  : " + pathsIDPTopDown.uniquePaths(m, n));
        System.out.println("DP Bottom up   :" + pathsIDPBottomUp.uniquePaths(m, n));
        System.out.println("DP Bottom up  space optimize :" + pathsIDPBottomUpSpaceOptimize.uniquePaths(m, n));
        System.out.println("binomialCoefficient :" + binomialCoefficient.uniquePaths(m, n));
    }
}


/**
 * We can solve this problem through backtracking.
 * Backtracking
 * 1. Our goal: To reach destination cell (m-1,n-1), once reach it counted as one path
 * 2. Our constraints: a) We can not move outside of boundary b
 * 3. Our choices: a) we can move either Down (i+1,j) or right (i, j+1) from any cell
 * <p>
 * Complexity:
 * To reach a cell there are two way and we try all the source cell to reach this cell.
 * Complexity: O(2^(m*n)) /O (m*n)
 * <p>
 * TLE
 */
class UniquePathsIBacktracking {


    public int uniquePaths(int m, int n) {

        if (m == 0 || n == 0)
            return 0;

        /**
         * m==1 ; if only horizontal strip is there; source (0,0) destination (0,n-1) and we can move right only
         * n==1 ; if only vertical strip is there; source (0,0) destination (m-1,0) and we can move down only
         */
        if (m == 1 || n == 1)
            return 1;


        final boolean maze[][] = new boolean[m][n];

        final int sx = 0, sy = 0;
        final int dx = m - 1, dy = n - 1;

        return uniquePaths(maze, m, n, sx, sy, dx, dy);


    }

    private int uniquePaths(boolean[][] maze, int m, int n, int sx, int sy, int dx, int dy) {

        //1. Our goal: To reach destination cell (m-1,n-1), once reach it counted as one path
        if (sx == dx && sy == dy)
            return 1;

        int path = 0;
        if (isSafe(sx, sy, m, n, maze)) {
            maze[sx][sy] = true;

            path = uniquePaths(maze, m, n, sx + 1, sy, dx, dy)  //down
                    +
                    uniquePaths(maze, m, n, sx, sy + 1, dx, dy);//right

            maze[sx][sy] = false;
        }
        return path;
    }

    private boolean isSafe(int sx, int sy, int m, int n, boolean[][] maze) {
        if (sx >= m || sy >= n || sx < 0 || sy < 0 || maze[sx][sy])
            return false;
        return true;
    }

    /**
     * Simple and short
     *
     * @param m
     * @param n
     * @return
     */

    public int uniquePaths2(int m, int n) {
        return uniquePathBacktrack(0, 0, m, n);
    }

    public int uniquePathBacktrack(int r, int c, int m, int n) {
        if (r > m || c > n) {
            return 0;
        }
        if (r == m - 1 && c == n - 1) {
            return 1;
        }
        return uniquePathBacktrack(r + 1, c, m, n) + uniquePathBacktrack(r, c + 1, m, n);
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
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
 * Memory Usage: 33.2 MB, less than 5.10% of Java online submissions for Unique Paths.
 */
class UniquePathsIDPTopDown {


    public int uniquePaths(int m, int n) {

        if (m == 0 || n == 0)
            return 0;

        /**
         * m==1 ; if only horizontal strip is there; source (0,0) destination (0,n-1) and we can move right only
         * n==1 ; if only vertical strip is there; source (0,0) destination (m-1,0) and we can move down only
         */
        if (m == 1 || n == 1)
            return 1;


        final int maze[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(maze[i], -1);

        final int sx = 0, sy = 0;
        final int dx = m - 1, dy = n - 1;

        return uniquePaths(maze, m, n, sx, sy, dx, dy);


    }

    private int uniquePaths(int[][] maze, int m, int n, int sx, int sy, int dx, int dy) {

        if (isSafe(sx, sy, m, n)) {

            if (sx == dx && sy == dy)
                return maze[sx][sy] = 1;

            if (maze[sx][sy] != -1)
                return maze[sx][sy];


            return maze[sx][sy] = uniquePaths(maze, m, n, sx + 1, sy, dx, dy)  //down
                    +
                    uniquePaths(maze, m, n, sx, sy + 1, dx, dy);//right

        }
        return 0;
    }

    private boolean isSafe(int sx, int sy, int m, int n) {
        if (sx >= m || sy >= n || sx < 0 || sy < 0)
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
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
 * Memory Usage: 33.2 MB, less than 5.10% of Java online submissions for Unique Paths.
 */
class UniquePathsIDPBottomUp {


    public int uniquePaths(int m, int n) {

        if (m == 0 || n == 0)
            return 0;


        final int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0)
                    dp[i][j] = 0;

                /**
                 * m==1 ; if only horizontal strip is there; source (0,0) destination (0,n-1) and we can move right only
                 * n==1 ; if only vertical strip is there; source (0,0) destination (m-1,0) and we can move down only
                 */
                else if (i == 1 || j == 1)
                    dp[i][j] = 1;

                else {
                    //would reach this cell by coming down from up dp[i - 1][j] or by coming right from left dp[i][j - 1]
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

            }
        }
        return dp[m][n];


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
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
 * Memory Usage: 32.9 MB, less than 5.10% of Java online submissions for Unique Paths.
 */
class UniquePathsIDPBottomUpSpaceOptimize {


    public int uniquePaths(int m, int n) {

        if (m == 0 || n == 0)
            return 0;


        final int dp[] = new int[n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0)
                    dp[j] = 0;

                /**
                 * m==1 ; if only horizontal strip is there; source (0,0) destination (0,n-1) and we can move right only
                 * n==1 ; if only vertical strip is there; source (0,0) destination (m-1,0) and we can move down only
                 */
                else if (i == 1 || j == 1)
                    dp[j] = 1;

                else {
                    //would reach this cell by coming down from up dp[i - 1][j] or by coming right from left dp[i][j - 1]
                    dp[j] = dp[j] + dp[j - 1];
                }

            }
        }
        return dp[n];


    }


    public int uniquePathsReverse(int m, int n) {
        int[] res = new int[m + 1]; // for this problem we can reduce the m*n matrix to an array

        Arrays.fill(res, 1);

        for (int nIdx = n - 1; nIdx >= 1; nIdx--) {
            for (int mIdx = m - 1; mIdx >= 1; mIdx--) {
                res[mIdx] = res[mIdx] + res[mIdx + 1];
            }
        }

        return res[1];
    }

}

/**
 * https://www.geeksforgeeks.org/binomial-coefficient-dp-9/
 * Basically imagine that you have a binary array where:
 * 1- move right
 * 0- move down
 * <p>
 * You need m-1 moves right and n-1 moves down
 * <p>
 * That means that you need to calculate the number of
 * binary arrays of length: (m-1 + n-1) = m+n-2 with m-1 ones (1) and n-1 zeros (0). This you can calculate from the
 * binomial coefficient { (m+n-2) C (m-1) }  // or { (m+n-2) C (n-1) }
 */
class UniquePathsIBinomialCoefficient {


    public int uniquePaths(int m, int n) {

        if (m == 0 || n == 0)
            return 0;
        /**
         * m==1 ; if only horizontal strip is there; source (0,0) destination (0,n-1) and we can move right only
         * n==1 ; if only vertical strip is there; source (0,0) destination (m-1,0) and we can move down only
         */
        if (m == 1 || n == 1)
            return 1;


        return binomialCoefficient(m + n - 2, m - 1);

    }

    //C(n, k) = C(n-1, k-1) + C(n-1, k)
    //   C(n, 0) = C(n, n) = 1
    private int binomialCoefficient(int n, int k) {

        int c[] = new int[k + 1];
        /**
         * m==1 ; if only horizontal strip is there; source (0,0) destination (0,n-1) and we can move right only
         * n==1 ; if only vertical strip is there; source (0,0) destination (m-1,0) and we can move down only
         */
        c[0] = 1;

        for (int i = 1; i <= n; i++)
            for (int j = Math.min(k, i); j >= 1; j--)
                c[j] = c[j] + c[j - 1];

        return c[k];
    }

}