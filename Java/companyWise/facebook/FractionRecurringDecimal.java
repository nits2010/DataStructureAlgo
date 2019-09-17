package Java.companyWise.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-30
 * Description: https://leetcode.com/problems/fraction-to-recurring-decimal/
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * <p>
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 * <p>
 * [FACEBOOK]
 */
public class FractionRecurringDecimal {

    public static void main(String []args) {
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
        result += res;

        // if remainder is 0, return result
        long remainder = num % den;
        if (remainder == 0)
            return result;

        // right-hand side of decimal point
        Map<Long, Integer> map = new HashMap<>();
        result += ".";

        remainder = remainder * 10; //start multiplying and divide and collect

        while (remainder != 0) {

            // if digits repeat
            if (map.containsKey(remainder)) {
                int beg = map.get(remainder);
                String part1 = result.substring(0, beg);  //get the part before fraction
                String part2 = result.substring(beg); //get the part after fraction
                result = part1 + "(" + part2 + ")"; //attach them
                return result;
            }

            // continue
            map.put(remainder, result.length()); //putting length of current string will make sure that we get right length of fractional numbers which are repeated
            res = remainder / den; //the the quotient
            result += String.valueOf(res); //attach this quotient which probably a repeated number in ()
            remainder = (remainder % den) * 10;
        }

        return result;
    }
}
