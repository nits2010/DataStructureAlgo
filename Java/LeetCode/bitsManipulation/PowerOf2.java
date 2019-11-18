package Java.LeetCode.bitsManipulation;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-20
 * Description: https://leetcode.com/problems/power-of-two/
 * <p>
 * //power of 2 has only one bit set and rest is zero
 */

public class PowerOf2 {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1) + " Expected : " + true);
        System.out.println(isPowerOfTwo(1) + " Expected : " + true);
        System.out.println(isPowerOfTwo(2) + " Expected : " + true);
        System.out.println(isPowerOfTwo(3) + " Expected : " + false);
        System.out.println(isPowerOfTwo(16) + " Expected : " + true);
        System.out.println(isPowerOfTwo(15) + " Expected : " + false);
        System.out.println(isPowerOfTwo(32) + " Expected : " + true);
        System.out.println(isPowerOfTwo(64) + " Expected : " + true);
        System.out.println(isPowerOfTwo(127) + " Expected : " + false);
        System.out.println(isPowerOfTwo(2147483647) + " Expected : " + false);
        System.out.println(isPowerOfTwo(-2147483648) + " Expected : " + false);
    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Two.
     * Memory Usage: 33.4 MB, less than 7.32% of Java online submissions for Power of Two.
     * <p>
     *
     * @param x
     * @return
     */
    public static boolean isPowerOfTwo(int x) {
        if (x == 0 || x < 0)
            return false;
        return (x & (-x)) == x;

    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Power of Two.
     * Memory Usage: 33.4 MB, less than 7.32% of Java online submissions for Power of Two.
     *
     * @param x
     * @return
     */
    public static boolean isPowerOfTwoV2(int x) {
        if (x == 0 || x < 0)
            return false;


        return (x & (x - 1)) == 0;
    }
}