package DataStructureAlgo.Java.LeetCode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-11
 * Description:
 */
public class AddString {

    public static void main(String []args) {


        SolutionAddString sol = new SolutionAddString();
        System.out.println(sol.addStrings("123", "345"));
        System.out.println(sol.addStrings("123456789", "345"));
        System.out.println(sol.addStrings("123", "123456789"));
        System.out.println(sol.addStrings("9999", "999999999"));
        System.out.println(sol.addStrings("999999999", "999999999"));
        System.out.println(sol.addStrings("0", "999999999"));
        System.out.println(sol.addStrings("1", "999999999"));
        System.out.println(sol.addStrings("0", "0"));
        System.out.println(sol.addStrings("", ""));


        System.out.println(sol.addStringsLong("123", "345"));
        System.out.println(sol.addStringsLong("123456789", "345"));
        System.out.println(sol.addStringsLong("123", "123456789"));
        System.out.println(sol.addStringsLong("9999", "999999999"));
        System.out.println(sol.addStringsLong("999999999", "999999999"));
        System.out.println(sol.addStringsLong("0", "999999999"));
        System.out.println(sol.addStringsLong("1", "999999999"));
        System.out.println(sol.addStringsLong("0", "0"));
        System.out.println(sol.addStringsLong("", ""));

    }
}

class SolutionAddString {


    public String addStrings(String num1, String num2) {
        if (num1 == null && num2 == null)
            return null;

        if (num1.isEmpty() && num2.isEmpty())
            return num1;

        if (num1.isEmpty() && !num2.isEmpty())
            return num2;

        if (num2.isEmpty() && !num1.isEmpty())
            return num1;

        if (num1.startsWith("0"))
            return num2;

        if (num2.startsWith("0"))
            return num1;

        int n1 = num1.length();
        int n2 = num2.length();
        char sum[] = new char[Math.max(n1, n2) + 1];


        int it = sum.length - 1;

        int carry = 0;
        int currSum = 0;

        int i = n1 - 1, j = n2 - 1;

        while (it >= 0) {

            int a = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j--) - '0' : 0;
            currSum = a + b + carry;

            if (currSum > 9) {
                sum[it--] = (char) (currSum % 10 + '0');
                carry = 1;
            } else {
                sum[it--] = (char) (currSum + '0');
                carry = 0;
            }


        }

        String result = new String(sum);
        if (sum[0] == '0')
            return result.substring(1);
        return result;


    }


    public String addStringsLong(String num1, String num2) {
        if (num1 == null && num2 == null)
            return null;

        if (num1.isEmpty() && num2.isEmpty())
            return num1;

        if (num1.isEmpty() && !num2.isEmpty())
            return num2;

        if (num2.isEmpty() && !num1.isEmpty())
            return num1;

        if (num1.startsWith("0"))
            return num2;

        if (num2.startsWith("0"))
            return num1;


        if (num1.length() < num2.length()) {
            String temp = num2;
            num2 = num1;
            num1 = temp;
        }

        int n1 = num1.length(); //bigger
        int n2 = num2.length(); //smaller Or equal


        char sum[] = new char[Math.max(n1, n2) + 1];
        Arrays.fill(sum, '0');
        int it = sum.length - 1;

        int carry = 0, currentSum;
        int i, j;
        for (i = n1 - 1, j = n2 - 1; i >= 0 && j >= 0; j--, i--) {

            currentSum = num1.charAt(i) - '0' + num2.charAt(j) - '0' + carry;

            if (currentSum > 9) {
                sum[it--] = (char) (currentSum % 10 + '0');
                carry = 1;
            } else {
                sum[it--] = (char) (currentSum + '0');
                carry = 0;
            }

        }

        while (i >= 0) {
            currentSum = num1.charAt(i) - '0' + carry;

            if (currentSum > 9) {
                sum[it--] = (char) (currentSum % 10 + '0');
                carry = 1;
            } else {
                sum[it--] = (char) (currentSum + '0');
                carry = 0;
            }
            i--;
        }





        if (carry > 0) {
            sum[it] = (char) ('0' + carry);
        }


        String result = new String(sum);
        result = sum[0] == '0' ? result.substring(1) : result;
        return result;
    }


}