package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-01
 * Description: https://leetcode.com/problems/reverse-integer/
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
 * [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed
 * integer overflows.
 */
public class ReverseIntegerNumber {

    public int reverse1(int x) {

        long finalSol = 0;

        while (x != 0) {
            finalSol = finalSol * 10 + x % 10;
            x /= 10;
        }

        if (finalSol > Integer.MAX_VALUE || finalSol < Integer.MIN_VALUE)
            return 0;
        return (int) finalSol;
    }

    public int reverse2(int x) {
        int finalSol = 0;

        while (x != 0) {
            int r = x % 10;
            x = x / 10;
            if (finalSol > (Integer.MAX_VALUE / 10)) return 0;
            if (finalSol < (Integer.MIN_VALUE / 10)) return 0;
            finalSol = finalSol * 10 + r;
        }
        return finalSol;
    }
}
