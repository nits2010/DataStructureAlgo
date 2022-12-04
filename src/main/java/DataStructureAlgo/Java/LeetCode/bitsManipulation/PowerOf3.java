package DataStructureAlgo.Java.LeetCode.bitsManipulation;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-20
 * Description: https://leetcode.com/problems/power-of-three/
 * Given an integer, write a function to determine if it is a power of three.
 * <p>
 * Example 1:
 * <p>
 * Input: 27
 * Output: true
 * Example 2:
 * <p>
 * Input: 0
 * Output: false
 * Example 3:
 * <p>
 * Input: 9
 * Output: true
 * Example 4:
 * <p>
 * Input: 45
 * Output: false
 * Follow up:
 * Could you do it without using any loop / recursion?
 * <p>
 * <p>
 * <p>
 * Read: https://leetcode.com/articles/power-of-three/
 */
public class PowerOf3 {

    public boolean isPowerOfThree(int n) {
        if (n == 0 || n < 0)
            return false;

        return isPowerOfThreeModulo(n);
    }


    /**
     * A number n is power of b if and only if
     * 1. it is divisible by b; n%3==0
     * 2. And keep dividing by b till it dont' n%3!=0
     * 3. Then if n is ==1 then its power of b
     * <p>
     * Time : O(logb(n)) ; a log of base b
     * Space: O(1)
     *
     * @param n
     * @return
     */
    private boolean isPowerOfThreeModulo(int n) {

        int b = 3;
        while (n % b == 0) {

            n /= b;

        }
        return n == 1;
    }


    /**
     * Find number digits and raise the power
     *
     * @param n
     * @return
     */
    private boolean isPowerOfThreeReverse(int n) {
        if (n <= 0) return false;
        int d = (int) ((Math.log(n) / Math.log(3)));
        return Math.pow(3, d) == n;
    }
}
