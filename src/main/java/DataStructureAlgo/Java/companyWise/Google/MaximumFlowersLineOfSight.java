package DataStructureAlgo.Java.companyWise.Google;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-26
 * Description:
 * Given a 2D array with F for Flowers and R for Rocks and Blank for empty spaces. If you were to stand at a spot,
 * find the max possible flowers visible in your line of sight. Image rocks are too high and obstruct line of vision.
 * *
 * *
 * *        [ ,F, , ]
 * *        [ ,F, ,F]
 * *        [R,R,F,F]
 * * Output: 3 at location [1][2]
 * *
 * *
 * *
 * At the position X your field of vision has 3 neighboring flowers. Flowers only count if its in top, left,down, right directions
 * <p>
 * Similar to {@link DataStructureAlgo.Java.LeetCode.BombEnemy}
 */
public class MaximumFlowersLineOfSight {

    public static void main(String[] args) {
        test(new char[][]
                {
                        {' ', 'F', ' ', ' '},
                        {' ', 'F', ' ', 'F'},
                        {'R', 'R', 'F', 'F'}
                });

        test(new char[][]
                {
                        {' ', 'F', ' ', ' '},
                        {'F', 'F', 'F', 'F'},
                        {'R', 'R', 'F', 'F'}
                });

        test(new char[][]
                {
                        {'F', 'F', ' ', ' '},
                        {'F', 'F', 'F', 'F'},
                        {'R', 'R', 'F', 'F'}
                });

        test(new char[][]
                {
                        {'F', 'F', 'R', ' '},
                        {'F', 'F', 'F', 'R'},
                        {'R', 'R', 'F', 'F'}
                });
    }

    private static void test(char[][] garden) {

        System.out.println(CommonMethods.toString(garden));
        System.out.println("Max flower visibile :" + maximumFlower(garden));
    }


    public static int maximumFlower(char[][] garden) {

        if (garden == null || garden.length == 0)
            return 0;

        int m = garden.length;
        int n = garden[0].length;

        int row[] = new int[m];
        int col[] = new int[n];

        Arrays.fill(row, -1);
        Arrays.fill(col, -1);

        int maxFlowers = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (garden[i][j] == ' ') {

                    if (row[i] == -1)
                        row[i] = flowersInRow(garden, i, j);

                    if (col[j] == -1)
                        col[j] = flowersInCol(garden, i, j);

                    maxFlowers = Math.max(maxFlowers, row[i] + col[j]);

                }
            }
        }
        return maxFlowers;
    }

    private static int flowersInCol(char[][] grid, int r, int c) {

        int count = 0;

        for (int i = r; i < grid.length; i++)
            if (grid[i][c] == 'R')
                break;
            else if (grid[i][c] == 'F')
                count++;

        if (r != 0) {
            for (int i = r; i >= 0; i--)
                if (grid[i][c] == 'R')
                    break;
                else if (grid[i][c] == 'F')
                    count++;
        }

        return count;

    }

    private static int flowersInRow(char[][] grid, int r, int c) {

        int count = 0;

        for (int i = c; i < grid[0].length; i++)
            if (grid[r][i] == 'R')
                break;
            else if (grid[r][i] == 'F')
                count++;

        if (c != 0) {
            for (int i = c; i >= 0; i--)
                if (grid[r][i] == 'R')
                    break;
                else if (grid[r][i] == 'F')
                    count++;
        }

        return count;

    }
}

