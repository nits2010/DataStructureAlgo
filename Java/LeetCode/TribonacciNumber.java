package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description: https://leetcode.com/problems/n-th-tribonacci-number/
 * The Tribonacci sequence Tn is defined as follows:
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * <p>
 * Given n, return the value of Tn.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 * <p>
 * Input: n = 25
 * Output: 1389537
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class TribonacciNumber {
    public static void main(String[] args) {
        System.out.println(tribonacci(5) + " expected :" + 7);
        System.out.println(tribonacci(25) + " expected :" + 1389537);
    }

    public static int tribonacci(int n) {


        int t0 = 0, t1 = 1, t2 = 1;

        if (n == 0)

            return 0;

        if (n <= 2)
            return 1;

        int d = 0;
        for (int i = 0; i < n - 2; i++) {
            d = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = d;
        }
        return d;
    }
}
