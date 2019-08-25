package Java.companyWise.Adobe;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-25
 * Description:
 * https://www.geeksforgeeks.org/swap-all-odd-and-even-bits/
 * Given an unsigned integer, swap all odd bits with even bits. For example, if the given number is 23 (00010111),
 * it should be converted to 43 (00101011). Every even position bit is swapped with adjacent bit on right side (even position
 * bits are highlighted in binary representation of 23), and every odd position bit is swapped with adjacent on left side..
 */
public class SwapEvenOddBits {

    public static void main(String[] args) {
        int x = 23; // 00010111

        // Output is 43 (00101011)
        System.out.println(swapBits(x));
    }

    private static int swapBits(int n) {
        if (n == 0)
            return n;

        /**
         * To swap all even to odd and odd to eve.
         * We need to find all the odd and even bits first.
         *
         * Even bits are on even position in 32 bit integer.
         * A number which has even bit set only is 10 (1010) -> A(in hexa)
         * we can have A in 32 bit format, since 10 has 4 bits then in 32 bits there will be (32/4) 8 A's
         * evenBits = 0xAAAAAAAA
         *
         * Odd bits are on odd position in 32 bit integer.
         * A number which has odd bit set only is 5 (101) -> 5(in hexa)
         * we can have 5 in 32 bit format, since 10 has 3 bits then in 32 bits there will be (32/3) 10 5's
         * oddBits = 0x5555555555
         * to be in hexa, there should only 8, 5's
         *
         */

        int evenBits = n & 0xAAAAAAAA;
        int oddBits = n & 0x55555555;

        //convert even bits to odd bits; right shift by 1
        evenBits = evenBits >> 1;

        //convert odd bits to even bits; left shift by 1
        oddBits = oddBits << 1;


        return evenBits | oddBits;

    }
}
