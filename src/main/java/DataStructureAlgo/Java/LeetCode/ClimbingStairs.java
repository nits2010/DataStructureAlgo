package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description:https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        test(4, 5, 7);
        test(5, 8, 13);
        test(20, 10946, 121415);

    }

    private static void test(int n, int twoStepExpected, int threeStepExpected) {
        System.out.println("\n n=" + n);
        System.out.println(" 2 step :" + ClimbingStairs2Step.climbStairs(n) + "  Expected " + twoStepExpected);

        System.out.println(" 3 step :" + ClimbingStairs3Step.climbStairs(n) + "  Expected " + threeStepExpected);
    }


}

/**
 * {@link FibonacciNumber}
 * https://leetcode.com/problems/climbing-stairs/
 * * You are climbing a stair case. It takes n steps to reach to the top.
 * * <p>
 * * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * * <p>
 * * Note: Given n will be a positive integer.
 * * <p>
 * * Example 1:
 * * <p>
 * * Input: 2
 * * Output: 2
 * * Explanation: There are two ways to climb to the top.
 * * 1. 1 step + 1 step
 * * 2. 2 steps
 * * Example 2:
 * * <p>
 * * Input: 3
 * * Output: 3
 * * Explanation: There are three ways to climb to the top.
 * * 1. 1 step + 1 step + 1 step
 * * 2. 1 step + 2 steps
 * * 3. 2 steps + 1 step
 */
class ClimbingStairs2Step {
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
     * Memory Usage: 33.1 MB, less than 5.26% of Java online submissions for Climbing Stairs.
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {

        int a = 1, b = 2;

        if (n <= 2)
            return n;

        int c = 0;
        for (int i = 0; i < n - 2; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}


/**
 * {@link TribonacciNumber}
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs.
 */
class ClimbingStairs3Step {
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
     * Memory Usage: 33.1 MB, less than 5.26% of Java online submissions for Climbing Stairs.
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {

        int a = 1, b = 2, c = 4;

        if (n <= 2)
            return n;

        if (n == 3)
            return c;

        int d = 0;
        for (int i = 0; i < n - 3; i++) {
            d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return d;
    }
}