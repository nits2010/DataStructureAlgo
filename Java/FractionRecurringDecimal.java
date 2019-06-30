package Java;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-30
 * Description:
 */
public class FractionRecurringDecimal {

    public static void main(String args[]) {
        System.out.println(fractionToDecimal(4, 333)); //"0.(012)"
        System.out.println(fractionToDecimal(7, 12)); //"0.(012)"
    }


    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0";
        if (denominator == 0)
            return "";

        String result = "";

        // is result is negative
        if ((numerator < 0) ^ (denominator < 0)) {
            result += "-";
        }

        // convert int to long
        long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);

        // quotient
        long res = num / den;
        result += String.valueOf(res);

        // if remainder is 0, return result
        long remainder = num % den;
        if (remainder == 0)
            return result;

        // right-hand side of decimal point
        Map<Long, Integer> map = new HashMap<>();
        result += ".";
        remainder = remainder * 10;
        while (remainder != 0) {
            // if digits repeat
            if (map.containsKey(remainder)) {
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);
                String part2 = result.substring(beg);
                result = part1 + "(" + part2 + ")";
                return result;
            }

            // continue
            map.put(remainder, result.length());
            res = remainder / den;
            result += String.valueOf(res);
            remainder = (remainder % den) * 10;
        }

        return result;
    }
}
