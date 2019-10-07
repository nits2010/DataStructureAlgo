package Java.LeetCode;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-30
 * Description: https://leetcode.com/problems/min-cost-climbing-stairs/
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * <p>
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
 * <p>
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {

        test(new int[]{10, 15, 20}, 15);
        test(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}, 6);


    }

    private static void test(int[] cost, int expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(cost) + " expected :" + expected);

        System.out.println("Left to right :" + minCostClimbingStairs(cost));
        System.out.println("Right to Left :" + minCostClimbingStairsFromBack(cost));

    }

    /**
     * Idea:
     * Given from question that to reach on 'i' th index from 'j'th index. We need to pay cost at 'i'.
     * Out target is to reach last index effectively.
     * <p>
     * base case are trivial.
     * n=1; then it takes at that much cost
     * n=2; then it takes minimum of them {this way we minimize the cost}
     * <p>
     * n>3
     * Now, for any index to reach here we need to pay cost  + cost of reach [i-1] or [i-2].
     * Since we need to minimize the cost, we'll take minimum of [i-1] and [i-2].
     * <p>
     * Now, once we reach at the end. There must be two way to reach n-1 . Either it was from n-2 or n-3.
     * Hence we take minimum of [n-2] or [n-3].
     * <p>
     * <p>
     * Since at any moment we need just last two values. we can use two variable for them.
     * <p>
     * {@link FibonacciNumber}
     * <p>
     * Runtime: 1 ms, faster than 99.84% of Java online submissions for Min Cost Climbing Stairs.
     * Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Min Cost Climbing Stairs.
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0)
            return 0;


        if (cost.length == 2)
            return Math.min(cost[0], cost[1]);


        int a = cost[0], b = cost[1];

        int c;
        for (int i = 2; i < cost.length; i++) {

            /**
             * cost of reaching i'th index
             */
            c = cost[i] + Math.min(a, b);

            //i-2 become i-1 for next iteration
            a = b;

            //i-1 become current for next iteration
            b = c;

        }
        //Now, once we reach at the end. There must be two way to reach n-1 . Either it was from n-2 or n-3.
        //Hence we take minimum of [n-2] or [n-3].
        return Math.min(a, b);
    }

    /**
     * WE can think it as opposite manner too. To reach last n, we could come from either n-1, n-2.
     *
     * Runtime: 1 ms, faster than 99.84% of Java online submissions for Min Cost Climbing Stairs.
     * Memory Usage: 37.5 MB, less than 94.64% of Java online submissions for Min Cost Climbing Stairs.
     * @param cost
     * @return
     */
    public static int minCostClimbingStairsFromBack(int[] cost) {
        if (cost == null || cost.length == 0)
            return 0;


        if (cost.length == 2)
            return Math.min(cost[0], cost[1]);


        int a = 0, b = 0;

        int c;
        for (int i = cost.length - 1; i >= 0; i--) {

            /**
             * cost of reaching i'th index
             */
            c = cost[i] + Math.min(a, b);

            //i-2 become i-1 for next iteration
            a = b;

            //i-1 become current for next iteration
            b = c;

        }
        //Now, once we reach at the end. There must be two way to reach n-1 . Either it was from n-2 or n-3.
        //Hence we take minimum of [n-2] or [n-3].
        return Math.min(a, b);
    }
}
