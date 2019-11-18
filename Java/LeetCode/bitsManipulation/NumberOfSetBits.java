package Java.LeetCode.bitsManipulation;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-20
 * Description: https://leetcode.com/problems/number-of-1-bits/
 * Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * Example 2:
 * <p>
 * Input: 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * Example 3:
 * <p>
 * Input: 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 * <p>
 * <p>
 * Note:
 * <p>
 * Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3 above the input represents the signed integer -3.
 * Follow up:
 * <p>
 * If this function is called many times, how would you optimize it?
 */
public class NumberOfSetBits {

    public int hammingWeight(int n) {
        return hammingWeightSlow(n);
    }

    /**
     * Runtime: 1 ms, faster than 44.59% of Java online submissions for Number of 1 Bits.
     * Memory Usage: 33.2 MB, less than 5.41% of Java online submissions for Number of 1 Bits.
     *
     * @param n
     * @return
     */
    public int hammingWeightSlow(int n) {

        int count = 0;
        for (int i = 0; i < 32; i++) {

            if ((n & (1 << i)) != 0)
                count++;

        }
        return count;
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of 1 Bits.
     * Memory Usage: 33.2 MB, less than 5.41% of Java online submissions for Number of 1 Bits.
     *
     * @param n
     * @return
     */
    public int hammingWeightFast(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            n = n & (n - 1);
        }

        return count;
    }

    /**
     * >> is arithmetic shift right, >>> is logical shift right.
     * In an arithmetic shift, the sign bit is extended to preserved the signedness of the number.
     * For example, -2 in 8 bits would be 11111110 (because the most significant bit has negative weight).
     * Shifting it right one bit using arithmetic shift would give you 11111111, or -1.
     * Logical right shift, however, does not care that the value could possibly represent a number;
     * it simply moves everything to the right and fills in from the left with 0s.
     * Shifting our -2 right one bit using logical shift would give 01111111.
     * The time limit exceeded case is the following one:
     * 10000000000000000000000000000000
     * in which case the n will never be 0 when you use >> because the most significant bit 1 is preserved for ever.
     * https://leetcode.com/problems/number-of-1-bits/discuss/55099/Simple-Java-Solution-Bit-Shifting
     *
     * @param n
     * @return
     */
    public int hammingWeightFastV2(int n) {
        int count = 0;
        while(n != 0){
            if((n & 1) == 1){
                count++;
            }

            n = n >>> 1;
        }

        return count;
    }


}
