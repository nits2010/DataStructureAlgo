package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-31
 * Description: Given two integers x and n, write a function to compute x^n. Overflow can happen.
 */
public class ArithmeticPower {
    public static void main(String[] args) {

        System.out.printf("2^(-3) = " + ArithmeticPowerSimple.arithmeticPower(2, -3));
        System.out.printf("\n2^(-3) = " + ArithmeticPowerRecursive.arithmeticPower(2, -3));
        System.out.printf("\n2^(-3) = " + ArithmeticPowerIterative.arithmeticPower(2, -3));


        System.out.printf("\n2^(-3) = " + ArithmeticPowerSimple.arithmeticPower(2, 3));
        System.out.printf("\n2^(-3) = " + ArithmeticPowerRecursive.arithmeticPower(2, 3));
        System.out.printf("\n2^(-3) = " + ArithmeticPowerIterative.arithmeticPower(2, 3));

        System.out.printf("\n-2^(-3) = " + ArithmeticPowerSimple.arithmeticPower(-2, 3));
        System.out.printf("\n-2^(-3) = " + ArithmeticPowerRecursive.arithmeticPower(-2, 3));
        System.out.printf("\n-2^(-3) = " + ArithmeticPowerIterative.arithmeticPower(-2, 3));
    }
}

//O(y)
class ArithmeticPowerSimple {

    public static double arithmeticPower(int x, int y) {

        return (y > 0) ? power(x, Math.abs(y)) : (double) 1 / power(x, Math.abs(y));
    }


    private static long power(int x, int y) {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return power(x, y / 2) * power(x, y / 2);
        else
            return x * power(x, y / 2) * power(x, y / 2);
    }
}

//O(logy) / O(logy)
class ArithmeticPowerRecursive {
    public static double arithmeticPower(int x, int y) {

        return (y > 0) ? power(x, Math.abs(y)) : (double) 1 / power(x, Math.abs(y));
    }

    private static long power(int x, int y) {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return power(x, y / 2) * power(x, y / 2);
        else
            return x * power(x, y / 2) * power(x, y / 2);
    }
}

//O(logy)/O(1)
class ArithmeticPowerIterative {
    public static double arithmeticPower(int x, int y) {

        return (y > 0) ? power(x, Math.abs(y)) : (double) 1 / power(x, Math.abs(y));
    }

    private static int power(int x, int y) {
        // Initialize result
        int res = 1;

        while (y > 0) {
            // If y is odd,
            // multiply
            // x with result
            if ((y & 1) == 1)
                res = res * x;

            // n must be even now
            y = y >> 1; // y = y/2
            x = x * x; // Change x to x^2
        }
        return res;
    }
}