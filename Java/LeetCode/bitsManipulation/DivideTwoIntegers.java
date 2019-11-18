package Java.LeetCode.bitsManipulation;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-03
 * Description: https://leetcode.com/problems/divide-two-integers/
 * 29. Divide Two Integers
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 * <p>
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 2^31 − 1 when the division result overflows.
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        final int MAX = Integer.MAX_VALUE;
        final int MIN = Integer.MIN_VALUE;

        //corner
//        test(MAX, -1, -MAX);
//        test(MAX, MIN, 0);
//        test(MAX, MAX, 1);
//
//        test(MIN, -1, MAX);
//        test(MIN, MIN, 1);
//        test(MIN, MAX, -1);

        //tests
//        test(10, 3, 10/3);
//        test(7, -3, 7/-3);
//        test(7000, -3, 7000/-3);
//        test(7000, -300, 2147483647/-300);
//        test(MAX, 2, MAX / 2);
        test(MIN, 2, MIN / 2);


    }

    private static void test(int dividend, int divisor, int expected) {
        System.out.println("\n Dividend " + dividend + " divisor :" + divisor + " expected :" + expected);
        DivideTwoIntegersUsingSubtraction subtraction = new DivideTwoIntegersUsingSubtraction();
        System.out.println(" Dividend/divisor by subtraction :" + subtraction.divide(dividend, divisor));
    }


}

/**
 * https://leetcode.com/problems/divide-two-integers/discuss/373853/bit-manipulations-or-Full-explanation-or-without-long-or-School-math
 * Lets first understand the problem statement, we are asked to to arithmetic division where dividend =10, divisor = 3.
 * It should result 10/3 = 3 [integer division].
 * <p>
 * If "/' operator allowed, it would be straight forward. But as question asked, we are not allowed to use multiplication *, addition +, mod % operator.
 * <p>
 * Which means we can use '-' operator.
 * <p>
 * In school, when we don't know about the '/' then we used to use '-' as our potential division operation. Let's revise it
 * <p>
 * 10/3 = 3
 * <p>
 * which means from 10 how many times we should sub-tract 3 so that it still >0
 * 10-3 = 7 => 7-3 = 4 => 4-3 = 1 => 1-3 < 0
 * So we should sub-tract divisor from dividend only three times ( 10-3, 7-3, 4-3 ) so our answer is 3.
 * <p>
 * Simple algorithm would be do same as above and get the number of times we should sub-tract.
 * But this will fail when divisor is < 0 as dividend = 7 and divisor = -3 then 7/(-3) = -2
 * but 7-(-3) = 10 which is wrong.
 * <p>
 * Hence we need to find the sign of both dividend and divisor, once we found the final sign, we can make both positive and perform the same operation as above.
 * <p>
 * 7=> 7
 * -3 => 3
 * 7-3=4-3=1 { 1-3 < 0}
 * Hence 2 sub-traction, and the final sign was '-' so answer is -2.
 * What about the integer overflow ? we can cover corner cases as below
 * <p>
 * * case 1:**
 * dividend = Int_max {2,147,483,647}
 * divisor = -1
 * then our output should be -dividend
 * <p>
 * * case 2:**
 * dividend = Int_min {-2147483648}
 * divisor = -1
 * then out output should be Integer Max
 * <p>
 * * case 3:**
 * dividend = Int_max {2,147,483,647}
 * divisor = Int_min {-2147483648}
 * output is 0 since abs(int_min) > abs(int_max)
 * <p>
 * * case 4**
 * dividend = int_max
 * divisor = int_max
 * return 1
 * <p>
 * * case 5:**
 * dividend = int min
 * divisor = int min
 * return 1
 * <p>
 * case 6:
 * dividend = int min
 * divisor = int max
 * output is -1 since abs(int_min) > abs(int_max)
 * <p>
 * case 7: Special case
 * dividend = int min
 * divisor != max
 * then
 * dividing directly would be wrong, so we need to divide based on reminder.
 * Hence when {notice int_min is even number }
 * divisor is Odd : 1+dividend
 * divisor is even: divide both by 2
 * Modification: Since subtracting small divisor from big dividend one by one would be slow, so we'll optimize it by subtracting multiple of divisors
 */
class DivideTwoIntegersUsingSubtraction {

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Divide Two Integers.
     * Memory Usage: 33.8 MB, less than 6.06% of Java online submissions for Divide Two Integers.
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        final int MAX = Integer.MAX_VALUE;
        final int MIN = Integer.MIN_VALUE;
        //================= Base cases : Start ==============

        //Divisor can't be 0
        if (divisor == 0) return MAX;

        if (divisor == 1 || dividend == 0) return dividend;


        if (divisor == MIN) {
            /**
             * case 5:
             * dividend = int min
             * divisor = int min
             * return 1
             */
            if (dividend == MIN)
                return 1;
            else
            /**
             * case 3:
             * dividend  = Int_max {2,147,483,647}
             * divisor    = Int_min {-2147483648}
             * output is 0 since abs(int_min) > abs(int_max)
             */
                return 0;
        }


        if (dividend == MAX) {

            /**case 1
             * dividend = Int_max {2,147,483,647}
             * divisor  = -1
             * then our output should be Integer min
             */
            if (divisor == -1)
                return -dividend;


            /**
             * case 4
             * dividend = int_max
             * divisor = int_max
             * return 1
             */
            if (divisor == MAX)
                return 1;

        }

        if (dividend == MIN) {
            /**
             * case 2:
             * dividend = Int_min {-2147483648}
             * divisor = -1
             * then out output should be Integer Max ; 2147483648 is overflow to positive side
             */
            if (divisor == -1)
                return MAX;


            /**
             * case 6:
             * dividend = int min
             * divisor = int max
             * output is -1 since abs(int_min) > abs(int_max)
             */
            if (divisor == MAX)
                return -1;

            return (((divisor & 1) == 1)
                    ? divide(dividend + 1, divisor) //divisor is odd
                    : divide(dividend >> 1, divisor >> 1)); //divisor is even; then make 1 division


        }

        //================= Base cases : End ==============

        //================= Algo : Start ==============


        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        int quotient = 0;
        int divid = Math.abs(dividend);
        int divis = Math.abs(divisor);
        while (divid >= divis) {
            int temp = divis, multiple = 1;
            while ((temp << 1) >= 0 && (divid >= (temp << 1))) {
                temp <<= 1;
                multiple <<= 1;
            }
            divid -= temp;
            quotient += multiple;
        }
        return sign > 0 ? quotient : -quotient;

    }


}