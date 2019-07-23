package Java.companyWise.facebook;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/multiply-strings/
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 * <p>
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
class MultiplyTwoNumbersStringFormat {

    public static void main(String[] args) {
        System.out.println(multiplyReverse("123456", "123456"));
        System.out.println(multiplyReverse("999", "999"));
        System.out.println(multiplyReverse("0", "0"));

        System.out.println(multiplyForward("123456", "123456"));
        System.out.println(multiplyForward("999", "999"));
        System.out.println(multiplyForward("0", "0"));
    }

    public static String multiplyReverse(String num1, String num2) {

        if (num1.length() == 0 || num2.length() == 0) return "";

        if (num1.equals("0") || num2.equals("0")) return "0";

        char[] c1 = new StringBuilder(num1).reverse().toString().toCharArray();

        char[] c2 = new StringBuilder(num2).reverse().toString().toCharArray();

        char[] c = new char[c1.length + c2.length + 1];

        Arrays.fill(c, '0');

        for (int i = 0; i < c2.length; i++) {

            int dig2 = c2[i] - '0';

            int carry = 0;

            for (int j = 0; j < c1.length; j++) {

                int dig1 = c1[j] - '0';

                int temp = c[i + j] - '0';

                int cur = dig1 * dig2 + temp + carry;

                c[i + j] = (char) (cur % 10 + '0');

                carry = cur / 10;

//                System.out.println("carry:" + carry);

            }
//            System.out.println("carry2:" + carry);
            c[i + c1.length] = (char) (carry + '0');
        }

        String ret = new StringBuilder(new String(c)).reverse().toString();
        int pos = 0;

        while (ret.charAt(pos) == '0' && pos < ret.length()) pos++;

        return ret.substring(pos);
    }


    public static String multiplyForward(String num1, String num2) {

        if (num1.length() == 0 || num2.length() == 0) return "";

        if (num1.equals("0") || num2.equals("0")) return "0";

        char[] c1 = num1.toCharArray();

        char[] c2 = num2.toCharArray();

        char[] c = new char[c1.length + c2.length + 1];

        Arrays.fill(c, '0');

        for (int i = c2.length - 1; i >= 0; i--) {

            int dig2 = c2[i] - '0';

            int carry = 0;

            for (int j = c1.length - 1; j >= 0; j--) {

                int dig1 = c1[j] - '0';

                int temp = c[i + j + 2] - '0';

                int cur = dig1 * dig2 + temp + carry;

                c[i + j + 2] = (char) (cur % 10 + '0');

                carry = cur / 10;


            }
            c[i + 1] = (char) (carry + '0');
        }

        String ret = new String(c);
        int pos = 0;

        while (ret.charAt(pos) == '0' && pos < ret.length()) pos++;

        return ret.substring(pos);
    }


}
