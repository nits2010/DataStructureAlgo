package Java.LeetCode;

import java.text.DecimalFormat;

/**
 * Author: Nitin Gupta
 * Date: 19/09/19
 * Description: https://leetcode.com/problems/integer-to-english-words/
 * 273. Integer to English Words [HARD]
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 * <p>
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 * <p>
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 * <p>
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class IntegerToEnglishWords {


    public static void main(String[] args) {
        test(123, "One Hundred Twenty Three");
        test(12345, "Twelve Thousand Three Hundred Forty Five");
        test(1234567, "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven");
        test(1234567891, "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One");

    }

    private static void test(int num, String expected) {
        System.out.println("\nNum:" + num);
        System.out.println("Expected:                 " + expected);
        System.out.println("IntegerToEnglishWords1:     " + IntegerToEnglishWords1.numberToWords(num));
        System.out.println("IntegerToEnglishWords2:     " + IntegerToEnglishWords2.numberToWords(num));
    }


}


class IntegerToEnglishWords2 {
    private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public static String numberToWords(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        StringBuilder words = new StringBuilder();

        while (num > 0) {
            if (num % 1000 != 0)
                words.insert(0, numberToWordsLessThanOneThousand(num % 1000) + THOUSANDS[i] + " ");
            num /= 1000;
            i++;
        }

        return words.toString().trim();
    }

    private static String numberToWordsLessThanOneThousand(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return LESS_THAN_20[num] + " ";
        else if (num < 100)
            return TENS[num / 10] + " " + numberToWordsLessThanOneThousand(num % 10);
        else
            return LESS_THAN_20[num / 100] + " Hundred " + numberToWordsLessThanOneThousand(num % 100);
    }
}

/**
 * Runtime: 36 ms, faster than 6.37% of Java online submissions for Integer to English Words.
 * Memory Usage: 39.3 MB, less than 6.38% of Java online submissions for Integer to English Words.
 */
class IntegerToEnglishWords1 {
    public static String numberToWords(int num) {

        return convert(num);
    }

    private static final String[] tensNames = {
            "",
            " Ten",
            " Twenty",
            " Thirty",
            " Forty",
            " Fifty",
            " Sixty",
            " Seventy",
            " Eighty",
            " Ninety"
    };

    private static final String[] numNames = {
            "",
            " One",
            " Two",
            " Three",
            " Four",
            " Five",
            " Six",
            " Seven",
            " Eight",
            " Nine",
            " Ten",
            " Eleven",
            " Twelve",
            " Thirteen",
            " Fourteen",
            " Fifteen",
            " Sixteen",
            " Seventeen",
            " Eighteen",
            " Nineteen"
    };

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " Hundred" + soFar;
    }


    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "Zero";
        }

        // pad with "0" 12
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        String sNumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(sNumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(sNumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(sNumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(sNumber.substring(9, 12));


        StringBuilder result = new StringBuilder();
        result.append(billions == 0 ? "" : convertLessThanOneThousand(billions) + " Billion ");
        result.append(millions == 0 ? "" : convertLessThanOneThousand(millions) + " Million ");
        result.append(hundredThousands == 0 ? "" : convertLessThanOneThousand(hundredThousands) + " Thousand ");
        result.append(convertLessThanOneThousand(thousands));

        // remove extra spaces!
        return result.toString().replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ").trim();
    }

}
