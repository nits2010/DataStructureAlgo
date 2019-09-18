package Java;

import Java.HelpersToPrint.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-06
 * Description: https://www.geeksforgeeks.org/painters-partition-problem/
 * The painter’s partition problem
 * We have to paint n boards of length {A1, A2…An}. There are k painters available and each takes 1 unit time to paint 1 unit of board. The problem is to find the minimum time to get
 * this job done under the constraints that any painter will only paint continuous sections of boards, say board {2, 3, 4} or only board {1} or nothing but not board {2, 4, 5}.
 * <p>
 * Examples:
 * <p>
 * Input : k = 2, A = {10, 10, 10, 10}
 * Output : 20.
 * Here we can divide the boards into 2
 * equal sized partitions, so each painter
 * gets 20 units of board and the total
 * time taken is 20.
 * <p>
 * Input : k = 2, A = {10, 20, 30, 40}
 * Output : 60.
 * Here we can divide first 3 boards for
 * one painter and the last board for
 * second painter.
 * <p>
 * another variation: {@link Java.LeetCode.MinimizeMaxDistanceGasStation}
 */
public class PainterPartitionProblem {

    public static void main(String[] args) {
        test(new int[]{10, 20, 30, 40}, 2, 60);
        test(new int[]{10, 10, 10, 10}, 2, 20);
        test(new int[]{10, 10, 10, 10, 10, 30, 60}, 3, 60);

    }

    private static void test(int[] boards, int painters, int expected) {
        System.out.println("\nBoards:" + GenericPrinter.toString(boards) + ", Painters: " + painters + "\nExpected      :" + expected);
        PainterPartitionProblemDP dp = new PainterPartitionProblemDP();
        System.out.println("DP            :" + dp.minCost(boards, painters));

        PainterPartitionProblemBinarySearch binarySearch = new PainterPartitionProblemBinarySearch();
        System.out.println("binary search :" + binarySearch.minCost(boards, painters));
    }
}

class PainterPartitionProblemDP {

    /**
     * Lets understand the problem first;
     * we have n boards  { 10,20,30,40 } with k painters = 2;
     * these painters paint the board in
     * 1. contiguous manner only i.e. if painter x is painting from i ... j then he can paint j+1 but can't paint j+2 without painting j+1
     * We need to minimize the cost of painting with k painters; cost is determined by summing up the board size painted by painters
     * <p>
     * Approach:
     * if we see closely, then we can obtain below information
     * [ This is nothing but having a set of n elements, we need to divide this set into k sub-arrays{because they can paint in order} s.t. maximum sum of these set is overall minimize ]
     * [ solution for sub-set is S(n,k) = k*S(n-1,k) + S(n-1, k-1 ) , But here we need to find sub-arrays not sub-sets ]
     * <p>
     * now,  if we know what was the cost of painting n boards with k-1 painters then we can calculate the cost of painting n boards with k painters.
     * To find, this new painter can paint the board(S) from any where in order. Just like putting current element in k-1 sub-set, for that we need to try every sub-set.
     * <p>
     * Lets examine cost with k=1
     * {10} k=1 => 10
     * {10, 20} k=1 => 10+20 = 30
     * {10, 20, 30} k=1 => 10+20+30 = 60
     * {10, 20, 30, 40} k=1 => 10+20+30+40= 100
     * <p>
     * now with k=2
     * {10} k=2 => 10
     * {10, 20} k=2 =>
     * *            => either
     * *                > {10} with k=1 , {20} =>{ { {10}, {20} }  or {10,20} , {} }
     * *                ....> first painter paint {10} and second paints {20} OR first painter paint {10,20} and second paint {}
     * *            =>  Min { MAx { {10}, {20} }  , Max {10,20} , {} } => Min(20,30) = 20
     * <p>
     * {10, 20, 30} k=2 =>  Min {60,30,50} = 30
     * * *            => either Max [ {10,20,30} , {} ] = 60
     * *            => either Max [ {10,20} , {30} ] = 30
     * *            => either Max [ {10} , {20,30} ] = 50
     * Which translate that with k-1 painter; from 1 board  { Max [ {10} , {20,30} ] } to n=3 board { either Max [ {10,20,30} , {} ]}
     * *                                                                     ^^^^^Remaining boards                                ^Remaining boards
     * <p>
     * same for
     * {10, 20, 30, 40} => 60 ans
     * <p>
     * <p>
     * M[i][k] defines the cost of painting i boards with k painters
     * <p>
     * M[i][k] = Min {
     * *                 Max {
     * *                    M[j][k-1] where j varies from 1<= j <=i { This is same as painting with k-1 painter, from 1 board to i board. 0=< i < n }
     * *                        , Sum [ board[m] ; m from j to n-1 ] { Remaining boards}
     * **                    }
     * * }
     *
     * @param board
     * @param painters
     * @return O(k * n ^ 2)
     */
    int minCost(int[] board, int painters) {

        if (null == board || board.length == 0)
            return 0;

        int n = board.length;
        if (n == 1)
            return board[0];

        //Sum[i] represent board length from 0 to i-1
        int[] sum = new int[n + 1];
        sum[0] = 0;

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + board[i - 1];
        }

        if (painters == 0)
            return 0;

        if (painters == 1)
            return sum[n];


        //cost[i][k] represent min cost of painting from 0 to i board with k painter
        int[][] cost = new int[n + 1][painters + 1];

        cost[0][0] = 0; //no board, no painter. no cost

        //cost of painting n boards with 1 painters
        for (int i = 1; i <= n; i++) {
            cost[i][1] = sum[i];
        }

        //cost of painting 1 boards with k painters
        for (int i = 1; i <= painters; i++) {
            cost[1][i] = sum[1];
        }

        /**
         *  * M[i][k] = Min {
         *      * *                 Max {
         *      * *                    M[j][k-1] where j varies from 1<= j <=i
         *      * *                        , Sum [ board[m] ] ; m from j to n-1 ]
         *      * **                    }
         *      * * }
         */

        for (int k = 2; k <= painters; k++) {

            for (int i = 2; i <= n; i++) {

                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= i; j++)
                    min = Math.min(min, Math.max(cost[j][k - 1], sum[i] - sum[j]));


                cost[i][k] = min;
            }
        }
        return cost[n][painters];
    }


}

class PainterPartitionProblemBinarySearch {

    /**
     * We know that when we have
     * 1. N painter and n board then cost would Minimum of (boards) -> low
     * 2. 1 Painter and n board then cost would be Sum (boards)  -> High
     * Which means, our solution lie between [low,high]
     * we can apply binary search to find what is the value which suffice the condition.
     * <p>
     * But How we find?
     * <p>
     * {10,20,30,40} l
     * L: 40 , H = 100 => Mid = 70
     * How do we find that in 70 cost we will paint n boards with k painters.
     * => What we can do is sequentially assigning task of painting board to a painter, as soon as we found this painter can't paint more (which is 70 in this) then we start giving task
     * to new painter and keep doing same with new painter....
     * this way we can find how many painters would require to paint the board assuming they can't paint more than 70.
     * <p>
     * 1. If number of painters = k for given mid -> then thi is our potential solution. We'll try to reduce the scope s.t. l = old low and h = mid
     * 2. if number of painters>k for given mid -> we can't paint n boards with mid as solution, we need to increase the mid s.t. l = min, h=h;
     *
     * @param board
     * @param painters
     * @return
     */
    int minCost(int[] board, int painters) {

        if (null == board || board.length == 0)
            return 0;

        int n = board.length;
        if (n == 1)
            return board[0];

        int low = Arrays.stream(board).max().getAsInt();
        int high = Arrays.stream(board).sum();


        while (low < high) {
            int mid = (low + high) >> 1;


            int requiredPainter = paintersRequired(board, mid, painters);
            if (requiredPainter > painters) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;

    }

    private int paintersRequired(int[] board, int cost, int painters) {

        int currentUnits = 0;
        int requiredPainters = 1;

        for (int value : board) {

            if (value > cost)
                return Integer.MAX_VALUE;

            currentUnits += value;

            if (currentUnits > cost) {
                requiredPainters++;
                currentUnits = value;

                if (requiredPainters > painters)
                    return requiredPainters;
            }
        }
        return requiredPainters;
    }
}