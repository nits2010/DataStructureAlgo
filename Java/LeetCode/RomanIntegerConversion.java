package Java.LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-16
 * Problem statements:
 * https://leetcode.com/problems/roman-to-integer/
 * https://leetcode.com/problems/integer-to-roman/
 */
public class RomanIntegerConversion {

    public static void main(String[] args) {
        System.out.println(RomanToIntegerSol.romanToInt("MCMXCIV"));
        System.out.println(RomanToIntegerSol.romanToInt("LVIII"));
        System.out.println(RomanToIntegerSol.romanToInt("MMMCMXCVIV"));

    }

}

/**
 * Description: https://leetcode.com/problems/roman-to-integer/
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: "III"
 * Output: 3
 * Example 2:
 * <p>
 * Input: "IV"
 * Output: 4
 * Example 3:
 * <p>
 * Input: "IX"
 * Output: 9
 * Example 4:
 * <p>
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 5:
 * <p>
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
class RomanToIntegerSol {

    private static Map<Character, Integer> romanToValue() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        return map;
    }

    private static int romanToValue(char c) {

        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }

        return 0;

    }

    /**
     * Using map:
     * Runtime: 6 ms, faster than 51.82% of Java online submissions for Roman to Integer.
     * Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Roman to Integer.
     * <p>
     * <p>
     * Using switch:
     * Runtime: 4 ms, faster than 77.04% of Java online submissions for Roman to Integer.
     * Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Roman to Integer.
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        if (null == s || s.isEmpty())
            return 0;

        int finalValue = 0;
//        Map<Character, Integer> romanToValue = romanToValue();
        int current = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
//            current = romanToValue.get(c);
            current = romanToValue(c);

            if (i + 1 < s.length()) {

//                int next = romanToValue.get(s.charAt(i+1));
                int next = romanToValue(s.charAt(i + 1));

                if (current >= next) {
                    finalValue += current;
                } else {
                    finalValue += next - current;
                    i++;
                }
            } else
                finalValue += current;

        }


        return finalValue;
    }


    /**
     * Runtime: 3 ms, faster than 100.00% of Java online submissions for Roman to Integer.
     * Memory Usage: 36 MB, less than 100.00% of Java online submissions for Roman to Integer.
     *
     * @param s
     * @return
     */
    public static int romanToIntWithIf(String s) {
        int total = 0;
        if (s == null || s.isEmpty())
            return total;

        char[] numerals = s.toCharArray();

        for (int pos = 0; pos < numerals.length; pos++) {
            if (numerals[pos] == 'M') {
                total += 1000;
            } else if (numerals[pos] == 'D') {
                total += 500;
            } else if (numerals[pos] == 'C') { //C can be apply only on D and M
                if (pos + 1 < numerals.length && numerals[pos + 1] == 'D') {
                    total += 400;
                    pos++;
                } else if (pos + 1 < numerals.length && numerals[pos + 1] == 'M') {
                    total += 900;
                    pos++;
                } else {
                    total += 100;
                }
            } else if (numerals[pos] == 'L') {
                total += 50;
            } else if (numerals[pos] == 'X') { //X can be apply on C and L
                if (pos + 1 < numerals.length && numerals[pos + 1] == 'C') {
                    total += 90;
                    pos++;
                } else if (pos + 1 < numerals.length && numerals[pos + 1] == 'L') {
                    total += 40;
                    pos++;
                } else {
                    total += 10;
                }
            } else if (numerals[pos] == 'V') {
                total += 5;
            } else if (numerals[pos] == 'I') { //I Can be apply on X and V
                if (pos + 1 < numerals.length && numerals[pos + 1] == 'X') {
                    total += 9;
                    pos++;
                } else if (pos + 1 < numerals.length && numerals[pos + 1] == 'V') {
                    total += 4;
                    pos++;
                } else {
                    total += 1;
                }
            }
        }

        return total;
    }

}


/**
 * https://leetcode.com/problems/integer-to-roman/
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: 3
 * Output: "III"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "IV"
 * Example 3:
 * <p>
 * Input: 9
 * Output: "IX"
 * Example 4:
 * <p>
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 5:
 * <p>
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
class IntegerToRomanSol {

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }

    public static String intToRoman(int num) {

        return intToRomanMap(num);

    }

    /**
     * Using Division
     * <p>
     * Runtime: 3 ms, faster than 100.00% of Java online submissions for Integer to Roman.
     * Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Integer to Roman.
     *
     * @param num
     * @return
     */
    public static String intToRomanUsingDivision(int num) {
        if (num == 0)
            return "";

        StringBuilder buffer = new StringBuilder();
        int rem = 0;


        if (num >= 1000) {
            rem = num / 1000;
            while (rem-- > 0)
                buffer.append('M');

            num = num % 1000;

        }

        if (num >= 900) {
            buffer.append("CM");
            num = num % 900;
        }

        if (num >= 500) {
            buffer.append('D');

            num = num % 500;

        }

        if (num >= 400) {
            buffer.append("CD");

            num = num % 400;

        }

        if (num >= 100) {
            rem = num / 100;
            while (rem-- > 0)
                buffer.append('C');

            num = num % 100;

        }

        if (num >= 90) {
            buffer.append("XC");

            num = num % 90;

        }

        if (num >= 50) {
            buffer.append('L');

            num = num % 50;

        }

        if (num >= 40) {
            buffer.append("XL");

            num = num % 40;

        }

        if (num >= 10) {
            rem = num / 10;
            while (rem-- > 0)
                buffer.append('X');

            num = num % 10;

        }

        if (num >= 9) {
            buffer.append("IX");

            num = num % 9;

        }


        if (num >= 5) {
            rem = num / 5;
            while (rem-- > 0)
                buffer.append('V');

            num = num % 5;

        }

        if (num >= 4) {
            buffer.append("IV");

            num = num % 4;

        }

        if (num >= 0) {
            while (num-- > 0)
                buffer.append('I');

        }

        return buffer.toString();

    }


    /**
     * Runtime: 4 ms, faster than 67.66% of Java online submissions for Integer to Roman.
     * Memory Usage: 36 MB, less than 100.00% of Java online submissions for Integer to Roman.
     *
     * @param num
     * @return
     */
    public static String intToRomanMap(int num) {

        String[] ms = {"", "M", "MM", "MMM"};//0, 1000, 2000, 3000
        String[] cs = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; //0, 100, 200, 300, 400, 500, 600, 700, 800, 900
        String[] xs = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; //0, 10,20,30,40,50,60,70,80,90
        String[] is = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; //0, 1,2,3,4,5,6,7,8,9

        StringBuilder result = new StringBuilder();
        result.append(ms[num / 1000]);
        num = num % 1000;
        result.append(cs[num / 100]);
        num = num % 100;
        result.append(xs[num / 10]);
        num = num % 10;
        result.append(is[num]);
        return result.toString();

    }
}