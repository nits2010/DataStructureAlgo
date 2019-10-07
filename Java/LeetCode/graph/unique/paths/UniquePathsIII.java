package Java.LeetCode.graph.unique.paths;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 14/09/19
 * Description:https://leetcode.com/problems/unique-paths-iii/
 * 980. Unique Paths III
 * On a 2-dimensional grid, there are 4 types of squares:
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 * Example 1:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 * Note:
 * 1 <= grid.length * grid[0].length <= 20
 * <p>
 * Similar to {@link UniquePaths}
 * [Amazon]
 */
public class UniquePathsIII {
    public static void main(String[] args) {
        test(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}, 2);
        test(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}, 4);
    }

    private static void test(int[][] grid, int expected) {
        System.out.println("\n Input grid :" + GenericPrinter.toString(grid) + " expected :" + expected);
        final UniquePathsIIIBacktracking backtracking = new UniquePathsIIIBacktracking();
        UniquePathsIIITopDownDP topDownDP = new UniquePathsIIITopDownDP();
        System.out.println("\n Backtracking dfs :" + backtracking.uniquePathsIII(GenericPrinter.copyOf(grid)));
        System.out.println("\n topDownDP dfs :" + topDownDP.uniquePathsIII(GenericPrinter.copyOf(grid)));
    }
}

/**
 * Idea is similar to {@link UniquePaths}.
 * Here an important constraint is 'walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.'
 * which means we need to touch every gate (0) exactly once, Which means we also need to count the number of gates in our grid.
 * <p>
 * So whenever we reach number of gate + 2 {because 1 is for source and 1 is for destination} then we found our path provided that we reach destination.
 * <p>
 * We can count all values in grid where its not obstacle !=-1 as gates 'non-obstacle'
 * <p>
 * Runtime: 1 ms, faster than 78.70% of Java online submissions for Unique Paths III.
 * Memory Usage: 34 MB, less than 100.00% of Java online submissions for Unique Paths III.
 * <p>
 * Complexity: n = R*C
 * Time complexity: From each cell, we visit 4 direction cells. Hence ~T(n) = T(n-1) + T(n-2) + T(n-3) + T(n-4) + O(1). = ~O(4 ^ n)
 * Space : 4 direction walk, at most 4 stack frames for each cell. Hence O(4*n) = O(n)
 */
class UniquePathsIIIBacktracking {
    final static int INVALID = 3;

    public int uniquePathsIII(int[][] grid) {

        if (null == grid || grid.length == 0 || grid[0].length == 0)
            return 0;

        final int m = grid.length;
        final int n = grid[0].length;

        final int sourceValue = 1;
        final int destinationValue = 2;
        final int[][] srcDest = findSourceDestination(grid, sourceValue, destinationValue);
        if (srcDest == null)
            return 0; //source not found

        int[] paths = {0};

        dfs(grid, srcDest[0][0], srcDest[0][1], srcDest[1][0], srcDest[1][1], srcDest[2][0], paths, m, n);

        return paths[0];

    }

    /**
     * @param grid  grid
     * @param sr    source row
     * @param sc    source column
     * @param dr    destination row
     * @param dc    destination column
     * @param gates number of gates to visit
     * @param paths number of paths
     * @param m     grid row length
     * @param n     grid column length
     */
    private void dfs(int[][] grid, int sr, int sc, int dr, int dc, int gates, int[] paths, int m, int n) {

        if (!isSafe(grid, sr, sc, m, n))
            return;

        //use this gate
        gates--;

        if (gates < 0)
            return;

        if (sr == dr && sc == dc) {
            if (gates == 0) //that walk over every 'non-obstacle' square exactly once.
                paths[0]++;
            return;
        }

        //mark and choose
        grid[sr][sc] = INVALID;

        //dfs in 4 direction walk
        dfs(grid, sr - 1, sc, dr, dc, gates, paths, m, n);
        dfs(grid, sr + 1, sc, dr, dc, gates, paths, m, n);
        dfs(grid, sr, sc - 1, dr, dc, gates, paths, m, n);
        dfs(grid, sr, sc + 1, dr, dc, gates, paths, m, n);

        //reset for next iteration
        grid[sr][sc] = 0;

    }

    private boolean isSafe(int[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == INVALID || grid[r][c] == -1)
            return false;
        return true;
    }

    private int[][] findSourceDestination(int[][] grid, final int sourceValue, final int destinationValue) {

        int[][] srcDest = {{-1, -1}, {-1, -1}, {0, 0}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                //count number of non-obstacles
                if (grid[i][j] != -1)
                    srcDest[2][0]++;

                if (grid[i][j] == sourceValue) {
                    srcDest[0][0] = i;
                    srcDest[0][1] = j;
                } else if (grid[i][j] == destinationValue) {
                    srcDest[1][0] = i;
                    srcDest[1][1] = j;
                }
            }

        }
        //source not found
        if (srcDest[0][0] == -1)
            return null;

        return srcDest;
    }
}


/**
 * https://leetcode.com/articles/unique-paths-iii/
 * Time Complexity: O(R * C * 2^{R*C})
 * where R, C are the number of rows and columns in the grid.
 * <p>
 * Space Complexity: O(R * C).
 * <p>
 * code(r, c) returns a binary bit string with a length of the total number of cells. Each bit represents a cell of the grid.
 * If the bit the 1, the cell is to be visited. Here bitwise or is used to get a binary bit string where all the cells to be visited are masked 1, while others are masked 0.
 * <p>
 * every time you visit a cell, you divide all the cells in the grid into two groups: cells you have visited in this particular path until now
 * and those cells you need to visit from now on. The third dimension answers the question: which cells do i need to visit from now on. now, say,
 * you are visiting this cell as the 5th cell in a particular path. how many possible ways can you represent those cells you still need to visit. the answer
 * depends on which 4 cells you have visited so far. that could be any 4 of the n elements, so what is left is n-4 out of n elements..how many of those are there?
 * if you think about it, the answer is how many ways are there to create subsets of size n-4....
 * <p>
 * this is if this cell is the fifth in a path, what about if it is the 4th or the 7th...then you need to find all the subsets with size n-3 or n-6..if you follow this logic,
 * the third dimension represent all the possible subsets of R*C elements and that is nothing but 2^n or 1<< R*C
 */
class UniquePathsIIITopDownDP {
    final int[] row = new int[]{0, -1, 0, 1};
    final int[] col = new int[]{1, 0, -1, 0};


    public int uniquePathsIII(int[][] grid) {

        if (null == grid || grid.length == 0 || grid[0].length == 0)
            return 0;

        final int m = grid.length;
        final int n = grid[0].length;

        final int sourceValue = 1;
        final int destinationValue = 2;
        final int[][] srcDest = findSourceDestination(grid, sourceValue, destinationValue);

        if (srcDest == null)
            return 0; //source not found

        int gates = srcDest[2][0];

        final Integer[][][] dp = new Integer[m][n][1 << (m * n)];

        return dfs(grid, srcDest[0][0], srcDest[0][1], srcDest[1][0], srcDest[1][1], gates, dp, m, n);


    }


    /**
     * @param grid  grid
     * @param sr    source row
     * @param sc    source column
     * @param dr    destination row
     * @param dc    destination column
     * @param gates number of gates to visit
     * @param dp    cache
     * @param m     grid row length
     * @param n     grid column length
     */
    private int dfs(int[][] grid, int sr, int sc, int dr, int dc, int gates, final Integer[][][] dp, int m, int n) {


        if (dp[sr][sc][gates] != null)
            return dp[sr][sc][gates];

        if (sr == dr && sc == dc) {
            if (gates == 0) //that walk over every 'non-obstacle' square exactly once.
                return 1;
            return 0;
        }


        int paths = 0;

        for (int k = 0; k < 4; ++k) {
            int nr = sr + row[k];
            int nc = sc + col[k];

            if (0 <= nr && nr < m && 0 <= nc && nc < n) {
                //is this path valid, as gates has binary string of all valid path
                if ((gates & code(nr, nc, m, n)) != 0)

                    //mark this path has been used as gates ^ code(nr, nc, m, n)
                    paths += dfs(grid, nr, nc, dr, dc, gates ^ code(nr, nc, m, n), dp, m, n);
            }
        }

        return dp[sr][sc][gates] = paths;

    }


    private int[][] findSourceDestination(int[][] grid, final int sourceValue, final int destinationValue) {

        int[][] srcDest = {{-1, -1}, {-1, -1}, {0, 0}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                //count number of non-obstacles; count the states
                if (grid[i][j] % 2 == 0)
                    srcDest[2][0] |= code(i, j, grid.length, grid[0].length);

                if (grid[i][j] == sourceValue) {
                    srcDest[0][0] = i;
                    srcDest[0][1] = j;
                } else if (grid[i][j] == destinationValue) {
                    srcDest[1][0] = i;
                    srcDest[1][1] = j;
                }
            }

        }
        //source not found
        if (srcDest[0][0] == -1)
            return null;

        return srcDest;
    }

    public int code(int r, int c, int m, int n) {
        return 1 << (r * n + c);
    }
}
