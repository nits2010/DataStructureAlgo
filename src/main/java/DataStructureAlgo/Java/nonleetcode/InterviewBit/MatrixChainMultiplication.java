package DataStructureAlgo.Java.nonleetcode.InterviewBit;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-21
 * Description: https://www.interviewbit.com/problems/matrix-chain-multiplication/
 */
public class MatrixChainMultiplication {


    public static void main(String []args) {
        List<Integer> dime = new ArrayList<>();

        dime.add(40);
        dime.add(20);
        dime.add(30);
        dime.add(10);
        dime.add(30);

        System.out.println(solve(dime));

    }

    public static int solve(List<Integer> A) {

        if (A == null || A.size() == 0)
            return 0;

        int n = A.size();

        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++)
            cost[i][i] = 0;


        for (int l = 2; l < n; l++) {

            for (int i = 1; i < n - l + 1; i++) {

                int j = i + l - 1;

                if (j == n) continue;

                cost[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k + 1][j] + A.get(i - 1) * A.get(k) * A.get(j));
                }
            }
        }
        return cost[1][n - 1];
    }
}
