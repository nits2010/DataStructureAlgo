package Java.LeetCode.graph;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-06
 * Description:https://leetcode.com/problems/dungeon-game/
 * 174. Dungeon Game[Hard]
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 * <p>
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * <p>
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * <p>
 * <p>
 * <p>
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * <p>
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * Input:
 * *      -2 (K)	    -3	    3
 * *      -5	        -10	    1
 * *       10	    30	    -5 (P)
 * <p>
 * Output: 7
 * <p>
 * Input:
 * *[[-8,-6,3],[2,8,-10],[-8,-12,-6]] Output: 15
 * Note:
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 * https://leetcode.com/problems/dungeon-game/discuss/376060/Java-or-100-or-Dp-or-Top-Down-or-Bottom-Up-or-Space-optimzied
 */
public class DungeonGame {

    public static void main(String[] args) {
        test(new int[][]
                {
                        {-8, -6, 3},
                        {2, 8, -10},
                        {-8, -12, -6}
                }, 15);

        test(new int[][]
                {
                        {-2, -3, 3},
                        {-5, -10, 1},
                        {10, -30, -5}
                }, 7);
        test(new int[][]
                {
                        {-8, -6, -3},
                        {-2, 8, -10},
                        {-8, -12, 6}
                }, 13);

        test(new int[][]
                {
                        {0, 0, 0},
                        {1, 1, -1}
                }, 1);

    }

    private static void test(int[][] dungeon, int expected) {
        System.out.println("\n Input :\n" + GenericPrinter.toString(dungeon) + " expected :" + expected);
        DungeonGameDPBack dpBack = new DungeonGameDPBack();
        DungeonGameDPBackSpaceOptimized dpBackSpaceOptimized = new DungeonGameDPBackSpaceOptimized();
        DungeonGameDPBackTopDown dungeonGameDPBackRecursive = new DungeonGameDPBackTopDown();


        System.out.println("\n Bottom up :" + dpBack.calculateMinimumHP(dungeon));
        System.out.println("\n Bottom up space optimized :" + dpBackSpaceOptimized.calculateMinimumHP(dungeon));
        System.out.println("\n Top Down recursive :" + dungeonGameDPBackRecursive.calculateMinimumHP(dungeon));

    }


}


/**
 * * In order to find the minimum health that kind knight should have, is all depends how he is able to reach at the Princes.
 * * In case we need to find a number that make sure knight should have as health, then we can assign any big number
 * * that is enough to reach from (0,0) to (m-1,n-1)
 * * Since we need to find 'minimum' health to be, we need to traverse back from princes (m-1,n-1)  to (0,0)
 * * and see, what is the minimum value expected to be.
 * * Example:
 * * * *      -2 (K)	    -3	    3
 * * * *      -5	        -10	    1
 * * * *       10	        30	   -5 (P)
 * * <p>
 * * -5 -> 1 -> 3 -> -3 -> -2 = -6 means with health 6, knight will reach prices but he'll get die as health become zero. Hence answer is 7
 * * * [-8,-6,3]
 * * * [2,8,-10]
 * * * [-8,-12,-6]
 * * -6 -> -10 -> 8 -> 2 -> -8 = -14 => 15 is our answer
 * * <p>
 * * Apply same logic as {@link MinimumPathSum}. from bottom to top.
 * <p>
 * *
 * * minHealth(i, j)  = Min ( minHealth(i+1,j), minHealth(i,j+1) )  - grid[i][j]
 * * return minHealth[i][j] <= 0 ? 1 : minHealth[i][j]
 * Base case: When there is only one cell, and you are at princes.
 * Then if grid[m-1][n-1] > 0 Means you don't need extra health, as no one is proteting princies Hence '1'
 * otherwise -grid[m-1][n-1] +1 because at least that much blood will be taken by that guard.
 * <p>
 * * Complexity: O(m*n) / O(m*n)
 * <p>
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Dungeon Game.
 * Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Dungeon Game.
 */
class DungeonGameDPBackTopDown {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;
        int dp[][] = new int[m][n];

        //when you are at princes . m-1,n-1
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 1 : -dungeon[m - 1][n - 1] + 1;

        return calculateMinimumHP(dungeon, 0, 0, dp);


    }

    private int calculateMinimumHP(int[][] dungeon, int i, int j, int[][] dp) {
        if (i >= dungeon.length || j >= dungeon[0].length)
            return Integer.MAX_VALUE;

        if (dp[i][j] != 0)
            return dp[i][j];


        int down = calculateMinimumHP(dungeon, i + 1, j, dp);
        int right = calculateMinimumHP(dungeon, i, j + 1, dp);

        int min = Math.min(down, right);


        final int hp = min - dungeon[i][j];

        dp[i][j] = hp <= 0 ? 1 : hp;


        return dp[i][j];
    }


}

/**
 * This is similar to {@link MinimumPathSum}.
 * * dp[i][j] Minimum health by coming from either [i+1][j] or [i][j+1]
 * dp[i][j] = {    dungeon[i][j] > 0 ? 1 : -dungeon[i][j] + 1; ; i==m-1, j==n-1
 * * 					Min{dp[i+1][j] , dp[i][j+1]} - grid[i][j] ; i < m-1 && j<n-1;
 * * 					dp[i][j+1] - grid[i][j] ; i==m-1 && j<n-1
 * * 					dp[i + 1][j] - dungeon[i][j];  j == n-1; i<m-1
 * <p>
 * * dp[i][j] <= 0 ? 1 : dp[i][j]
 *
 * Solution dp[0][0]
 * Runtime: 2 ms, faster than 47.89% of Java online submissions for Dungeon Game.
 * Memory Usage: 42.5 MB, less than 58.82% of Java online submissions for Dungeon Game.
 */
class DungeonGameDPBack {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;
        int dp[][] = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                //when you are at princes . m-1,n-1
                if (i == m - 1 && j == n - 1)
                    dp[i][j] = dungeon[i][j] > 0 ? 1 : -dungeon[i][j] + 1;
                else if (i == m - 1)
                    dp[i][j] = dp[i][j + 1] - dungeon[i][j];
                else if (j == n - 1)
                    dp[i][j] = dp[i + 1][j] - dungeon[i][j];
                else {

                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                }

                dp[i][j] = dp[i][j] <= 0 ? 1 : dp[i][j];

            }
        }

        return dp[0][0];
    }


}

/**
 * dp[i][j] Minimum health by coming from either [i+1][j] or [i][j+1]
 * dp[i][j] =
 * *           { Min{dp[i+1][j] , dp[i][j+1]} - grid[i][j]
 * dp[i][j] <= 0 ? 1 : dp[i][j]
 * Only two rows are used at a time
 * dp[i+1][j] => dp[j]
 * dp[i][j+1] => dp[j+1]
 * <p>
 * Complexity: O(m*n) / O(m*n)
 * }
 * Runtime: 1 ms, faster than 96.34% of Java online submissions for Dungeon Game.
 * Memory Usage: 42.3 MB, less than 58.82% of Java online submissions for Dungeon Game.
 */
class DungeonGameDPBackSpaceOptimized {
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;
        int dp[] = new int[n];

        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                //when you are at princes . m-1,n-1
                if (i == m - 1 && j == n - 1)
                    dp[j] = dungeon[i][j] > 0 ? 1 : -dungeon[i][j] + 1;
                else if (i == m - 1)
                    dp[j] = dp[j + 1] - dungeon[i][j];
                else if (j == n - 1)
                    dp[j] = dp[j] - dungeon[i][j];
                else {

                    dp[j] = Math.min(dp[j], dp[j + 1]) - dungeon[i][j];
                }

                dp[j] = dp[j] <= 0 ? 1 : dp[j];

            }
        }

        return dp[0];
    }


}

