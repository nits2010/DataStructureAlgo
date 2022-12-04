package DataStructureAlgo.Java.nonleetcode;

import  DataStructureAlgo.Java.nonleetcode.InterviewBit.MatrixChainMultiplication;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-15
 * Description: https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
 * <p>
 * Problem statement: Consider a row of n coins of values v1 . . . vn, where n is even. We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of the coin. Determine the maximum possible amount of money we can definitely win if we move first.
 * Note: The opponent is as clever as the user.
 * Let us understand the problem with few examples:
 * 1. 5, 3, 7, 10 : The user collects maximum value as 15(10 + 5)
 * 2. 8, 15, 3, 7 : The user collects maximum value as 22(7 + 15)
 */
public class OptimalGameStrategy {

    public static void main(String[] args) {

        int[] a = {5, 3, 7, 10};
        System.out.println(maxOG(a));

        int[] b = {8, 15, 3, 7};
        System.out.println(maxOG(b));


    }

    /**
     * game[i][j] represent maximum value obtained by choosing the value in range of [i,j]
     * <p>
     * game[i][j] =  Max
     * *                  { value[i] +  Min { game[i+2][j], game[i+1][j-1]}
     * *                   value[j] +  Min { game[i][j-2], game[i+1][j-1]} }
     * <p>
     * Case 1: Choose i'th element, then opponent can choose from [i+1, j]
     * *    a) if opponent choose i+1 then we can choose from [i+2,j] => game[i+2][j]
     * *    b) if opponent choose j then we can choose from [i+1,j-1] => game[i+1][j-1]
     * <p>
     * Case 2: Choose j'th element, then opponent can choose from [i, j-1]
     * *    a) if opponent choose i then we can choose from [i+1,j-1] => game[i+1][j-1]
     * *    b) if opponent choose j-1 then we can choose from [i,j-2] => game[i][j-2]
     * <p>
     * Base case, when there is only 2 values, then in first chance, i'll always pick max of them
     *
     * @param a
     * @return
     */
    private static int maxOG(int a[]) {
        if (a == null || a.length == 0)
            return 0;

        int n = a.length;
        int[][] game = new int[n + 1][n + 1];


        for (int i = 1; i <= n; i++)
            game[i][i] = a[i - 1];

        /**
         * {@link MatrixChainMultiplication
         */
        for (int l = 2; l <= n; l++) {

            for (int i = 1; i <= n - l + 1; i++) {

                int j = i + l - 1;

                //Base case, when there is only 2 values, then in first chance, i'll always pick max of them
                if (l == 2)
                    game[i][j] = Math.max(a[i - 1], a[j - 1]);
                else {
                    //if we choose i'th then opponent has choice [i+1,j]

                    //if opponent choose i+1, then we can choose [i+2,j]
                    int x = (i + 2 <= n) ? game[i + 2][j] : 0;

                    //if opponent choose j, then we can choose [i+1,j-1]
                    int y = (i + 1 <= n && j - 1 >= 0) ? game[i + 1][j - 1] : 0;

                    //if we choose j'th then opponent has choice [i,j-1]


                    //if opponent choose j-1 then we can choose [i,j-2]
                    int z = (j - 2 >= 0) ? game[i][j - 2] : 0;

                    //if opponent choose i then we can choose [i+1,j-1]
                    int m = (i + 1 <= n && j - 1 >= 0) ? game[i + 1][j - 1] : 0;

                    game[i][j] = Math.max(a[i - 1] + Math.min(x, y), a[j - 1] + Math.min(z, m));


                }
            }
        }

        return game[1][n];

    }
}

