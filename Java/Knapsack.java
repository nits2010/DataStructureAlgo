package Java;

import Java.HelpersToPrint.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-21
 * Description:
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * https://www.geeksforgeeks.org/fractional-knapsack-problem/
 */
public class Knapsack {

    public static void main(String[] args) {
        System.out.println("\n\nTesting 0/1 \n\n");
        testKnapsack01();
        System.out.println("\n\nTesting Fractional \n\n");
        testKnapsackFractional();

    }

    private static void testKnapsackFractional() {
        testKnapsackFractional(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50, 240);
        testKnapsackFractional(new int[]{10, 120, 150}, new int[]{40, 20, 60}, 100, 275);
        testKnapsackFractional(new int[]{10, 120, 150}, new int[]{40, 20, 60}, 10, 60);
        testKnapsackFractional(new int[]{10, 120, 150}, new int[]{10, 20, 60}, 100, 280);
    }

    private static void testKnapsackFractional(int[] val, int[] wt, int W, double expected) {
        System.out.println("\n Input ; V:" + GenericPrinter.toString(val) + " weights: " + GenericPrinter.toString(wt) + " Bag size: " + W);
        KnapsackFractional.Greedy greedy = new KnapsackFractional.Greedy();
        System.out.println("Greedy ; Obtained :" + greedy.maximumValue(val, wt, W) + " expected :" + expected);


    }

    private static void testKnapsack01() {
        testKnapsack01(new int[]{60, 100, 120}, new int[]{10, 20, 30}, 50, 220);
        testKnapsack01(new int[]{10, 120, 150}, new int[]{40, 20, 60}, 100, 270);
        testKnapsack01(new int[]{10, 120, 150}, new int[]{40, 20, 60}, 10, 0);
        testKnapsack01(new int[]{10, 120, 150}, new int[]{10, 20, 60}, 100, 280);
    }

    private static void testKnapsack01(int[] v, int[] w, int W, int expected) {

        Knapsack0Or1.Recursive recursive = new Knapsack0Or1.Recursive();
        Knapsack0Or1.DP dp = new Knapsack0Or1.DP();
        Knapsack0Or1.DPSpaceOptimized dpSpaceOptiized = new Knapsack0Or1.DPSpaceOptimized();
        System.out.println("\n Input ; V:" + GenericPrinter.toString(v) + " weights: " + GenericPrinter.toString(w) + " Bag size: " + W);

        System.out.println("Recursive ; Obtained :" + recursive.maximumValue(v, w, W) + " expected :" + expected);
        System.out.println("DP; Obtained :" + dp.maximumValue(v, w, W) + " expected :" + expected);
        System.out.println("DP-Space; Obtained :" + dpSpaceOptiized.maximumValue(v, w, W) + " expected :" + expected);

    }
}

/**
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 * In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n
 * items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[]
 * such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item,
 * or don’t pick it (0-1 property).
 * <p>
 * <p>
 * knapsack-problem
 */
class Knapsack0Or1 {

    /**
     * We have V[0....n-1] represent values and W[0...n-1] represent weight of value[i].
     * Since, we have definite capacity W, we need to pick as many values as possible s.t. Sum [V[i]...V[j] ] is Maximize and Total weight is <=W
     * <p>
     * <p>
     * Brute Force:
     * A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets.
     * Consider the only subsets whose total weight is smaller than W. From all such subsets, pick the maximum value subset.
     * <p>
     * Complexity: O(n*2^n)
     * <p>
     * Recursive:
     * We can see, that there are two possibilities of each item.
     * 1. Either we can take this item in our bag which reduce our bag capacity from W to W - w[i] and Values to v + v[i]
     * 2. Or we can't take this item.
     * <p>
     * <p>
     * Recursive equation
     * <p>
     * maximumValue[i][j] = Max ( v[i] + maximumValue[i-1][j - W[i]] , maximumValue[i-1][j] }
     * <p>
     * 1. v[i] + maximumValue[i-1][j - W[i]] : if we take the current item then we get value v[i] and and our bag capacity should be at least j-w[i]
     * 2. maximumValue[i-1][W] : Otherwise we have to keep the max out of previous pick only.
     * <p>
     * O(2^n)
     * n=Length of V
     */
    static class Recursive {

        int maximumValue(int v[], int w[], int W) {
            if (v == null || v.length == 0 || w == null || w.length == 0)
                return 0;

            return maximumValue(v, w, W, 0);
        }

        private int maximumValue(int[] v, int[] w, int W, int i) {
            if (i >= v.length)
                return 0;

            if (W < 0)
                return Integer.MIN_VALUE;

            if (w[i] > W)
                return 0;

            if (i == v.length || W == 0)
                return 0;


            int include = v[i] + maximumValue(v, w, W - w[i], i + 1);
            int exclude = maximumValue(v, w, W, i + 1);
            return Math.max(include, exclude);
        }

    }

    /**
     * * dp[i][j] = Max ( v[i] + dp[i-1][j - W[i]] , dp[i-1][j] }
     * * <p>
     * * 1. v[i] + maximumValue[i-1][j - W[i]] : if we take the current item then we get value v[i] and and our bag capacity should be at least j-w[i]
     * * 2. maximumValue[i-1][W] : Otherwise we have to keep the max out of previous pick only.
     * <p>
     * O(n*W) / O(n*W)
     */
    static class DP {

        int maximumValue(int val[], int wt[], int W) {
            if (val == null || val.length == 0 || wt == null || wt.length == 0)
                return 0;

            int n = val.length;
            int dp[][] = new int[n + 1][W + 1];

            for (int w = 0; w <= W; w++) {

                for (int v = 0; v <= n; v++) {

                    if (v == 0 || w == 0) //No value or weight
                        dp[v][w] = 0;
                    else {

                        dp[v][w] = dp[v - 1][w];

                        if (wt[v - 1] <= w) {
                            dp[v][w] = Math.max(dp[v][w], val[v - 1] + dp[v - 1][w - wt[v - 1]]);
                        }
                    }

                }
            }
            return dp[n][W];
        }

    }

    /**
     * dp[i][j] = Max ( v[i] + dp[i-1][j - W[i]] , dp[i-1][j] }
     * <p>
     * we use only
     * dp[i-1][j - W[i]] ;some column in previous
     * dp[i-1][j] : Previous row and same column;
     * <p>
     * Only two row at a time, Current and previous.
     * Store all previous row value and update current row value.
     * Note; here i is denote the value, so i-1 is previous item
     * <p>
     * So instead of running outer loop from 0 to W, we'll run outer loop from 0 to V
     * <p>
     * O(n*W) / O(W)
     */
    static class DPSpaceOptimized {

        int maximumValue(int val[], int wt[], int W) {
            if (val == null || val.length == 0 || wt == null || wt.length == 0)
                return 0;

            int n = val.length;
            int dp[] = new int[W + 1];

            for (int v = 0; v < n; v++) {

                for (int w = W; w >= wt[v]; w--) {
                    dp[w] = Math.max(dp[w], val[v] + dp[w - wt[v]]);
                }
            }


            return dp[W];
        }

    }
}


/**
 * https://www.geeksforgeeks.org/fractional-knapsack-problem/
 * Given weights and values of n items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
 * <p>
 * In Fractional Knapsack, we can break items for maximizing the total value of knapsack. This problem in which we can break an item is also called the fractional knapsack problem.
 * <p>
 * Input :
 * Same as above
 * Output :
 * Maximum possible value = 240
 * By taking full items of 10 kg, 20 kg and
 * 2/3rd of last item of 30 kg
 */
class KnapsackFractional {

    /**
     * O(n*log(n)) where n is the size of Val or Wt
     * Since we need to maximize the total Value of being bag size of W. The important point here is we can take some part of each value if our bag is partially full.
     * This leads that we can greedily pick the items until our bag is completely full.
     * In order to make maximum profit, we need to pick wisely such that we get more value but giving up less bag space.
     * Hence we can sort the given data based on ration of V/W.
     * To avoid floating point (when v/w is not integer) we can apply it like below
     * <p>
     * v1/w1 > v2/w2 => v1*w2 > v2*w1
     */
    static class Greedy {
        int maximumValue(int val[], int wt[], int W) {
            if (val == null || val.length == 0 || wt == null || wt.length == 0)
                return 0;

            class VW {
                int v;
                int w;

                public VW(int v, int w) {
                    this.v = v;
                    this.w = w;
                }
            }

            int n = val.length;

            VW[] vw = new VW[n];

            for (int i = 0; i < n; i++) {
                vw[i] = new VW(val[i], wt[i]);
            }

            //v1/w1 > v2/w2 => v1*w2 > v2*w1
            Arrays.sort(vw, (o1, o2) -> {
                int x = o1.v * o2.w;
                int y = o1.w * o2.v;
                return y - x;
            });


            int maxVal = 0;
            int bagSize = W;

            for (int i = 0; i < n; i++) {

                if (bagSize == 0)
                    break;

                if (vw[i].w <= bagSize) {
                    bagSize -= vw[i].w;
                    maxVal += vw[i].v;
                } else {
                    //We can take complete item, take the partial item
                    double partOf = (double) bagSize / vw[i].w;
                    maxVal += vw[i].v * partOf;
                    break;

                }

            }

            return maxVal;

        }

    }

}