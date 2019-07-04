package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-26
 * Description:https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPath {

    public static void main(String args[]) {
        int m[][] = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        int n[][] = {{7, 7, 5}, {2, 4, 6}, {8, 2, 0}};
        System.out.println(longestIncreasingPath(n));
        System.out.println(longestIncreasingPath2(n));
    }

    //left, right, up and down
    static int row[] = {0, 0, -1, 1};
    static int col[] = {-1, 1, 0, 0};

    public static int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);

        return longestIncreasingPath(matrix, dp, n, m);

    }


    //O(n*m)/ O(n*m)
    public static int longestIncreasingPath(int mat[][], int dp[][], int n, int m) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1)
                    longestIncreasingPath(mat, dp, i, j, n, m);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;


    }

    //O(n*m)/ O(n*m)
    public static int longestIncreasingPath(int mat[][], int dp[][], int i, int j, int n, int m) {

        if (!isSafe(i, j, n, m))
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];
        /*
        int row8[] = {0, 0, -1, 1};
    int col8[] = {-1, 1, 0, 0};
        */
        int max = 1;
        int previousItem = mat[i][j];
        for (int r = 0; r < row.length; r++) {


            int rowV = row[r] + i;
            int colV = col[r] + j;

            if (isSafe(rowV, colV, n, m) && !(i == rowV && j == colV)) {


                int item = mat[rowV][colV];

                if (item > previousItem)
                    max = Math.max(max, 1 + longestIncreasingPath(mat, dp, rowV, colV, n, m));

            }
        }

        return dp[i][j] = max;

    }

    public static int longestIncreasingPath2(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];

        return longestIncreasingPath2(matrix, dp, n, m);
    }

    /**
     * The idea behind this is, if we sort the given data in increasing order then we can always
     * have the solution of previous element (smaller elements)
     *
     * @param mat
     * @param dp
     * @param n
     * @param m
     * @return
     */
    //O(n*m)/ O(n*m)
    public static int longestIncreasingPath2(int mat[][], int dp[][], int n, int m) {
        List<int[]> pairs = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pairs.add(new int[]{mat[i][j], i, j});
            }
        }

        //Sort based on element value.
        Collections.sort(pairs, Comparator.comparingInt(pair -> pair[0]));

        return longestIncreasingPath2(pairs, mat, dp, n, m);


    }

    //O(n*m)/ O(n*m)
    public static int longestIncreasingPath2(List<int[]> pairs, int mat[][], int dp[][], int n, int m) {


        int max = 1;

        for (int[] pair : pairs) {

            int i = pair[1];
            int j = pair[2];

            dp[i][j] = 1;
            for (int r = 0; r < row.length; r++) {


                int rowV = row[r] + i;
                int colV = col[r] + j;

                if (isSafe(rowV, colV, n, m) && !(i == rowV && j == colV)) {

                    if (mat[i][j] > mat[rowV][colV])
                        dp[i][j] = Math.max(dp[i][j], dp[rowV][colV] + 1);


                }
            }
            max = Math.max(dp[i][j], max);

        }
        return max;

    }

    static boolean isSafe(int i, int j, int n, int m) {
        if (i >= 0 && i < n && j >= 0 && j < m)
            return true;
        return false;
    }
}
