package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-31
 * Description: Given two integers x and n, write a function to compute x^n. Overflow can happen.
 */
public class ArithmeticPower {
    public static void main(String[] args) {

        System.out.printf("2^(-3) = " + AirthmaticPowerSimple.airthmaticPower(2, -3));
        System.out.printf("2^(-3) = " + AirthmaticPowerSimple.airthmaticPower(2, -3));
    }
}

//O(y)
class AirthmaticPowerSimple {

    public static double airthmaticPower(int x, int y) {

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

//O(logy)
class AirthmaticPowerFastRecursive {
    public static double airthmaticPower(int x, int y) {

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