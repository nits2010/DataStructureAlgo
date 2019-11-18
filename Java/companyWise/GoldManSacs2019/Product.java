package Java.companyWise.GoldManSacs2019;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 18/04/19
 * Description: https://www.geeksforgeeks.org/count-numbers-in-range-such-that-digits-in-it-and-its-product-with-q-are-unequal/
 * <p>
 * Given a range of numbers [l, r] and an integer q. The task is to count all such number in the given range such that any digit of
 * the number does not match with any digit in its product with the given number q.
 * <p>
 * Examples:
 * <p>
 * Input : l = 10, r = 12, q = 2
 * Output : 1
 * 10*2 = 20 which has 0 as same digit
 * 12*2 = 24 which as 2 as same digit
 * 11*2 = 22 no same digit
 * <p>
 * Input : l = 5, r = 15, q = 2
 * Output : 9
 */
public class Product {

    static boolean areInDigitInX(int n, int from) {
        int product = n * from;
        Set<Integer> numberDigits = digits(n);
        Set<Integer> productDigits = digits(product);

        for (Integer i : productDigits) {
            if (numberDigits.contains(i))
                return false;
        }

        return true;


    }

    static Set<Integer> digits(int item) {
        Set<Integer> set = new HashSet<>();

        int n = item;
        if (n == 0) {
            set.add(0);
            return set;
        }


        while (n > 0) {

            int digit = n % 10;
            set.add(digit);
            n = n / 10;

        }
        return set;
    }

    // Complete the nonRepeatingDigitProductCount function below.
    static int nonRepeatingDigitProductCount(int x, int y, int z) {

        int count = 0;
        for (int i = y; i <= z; i++) {
            if (areInDigitInX(i, x))
                count++;
        }

        return count;
    }

    public static void main(String []args) {
        System.out.println(nonRepeatingDigitProductCount(2, 10, 15));
    }


}
