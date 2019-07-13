package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
 * https://www.geeksforgeeks.org/calculate-maximum-value-using-sign-two-numbers-string/
 * Given a string of numbers, the task is to find the maximum value from the string, you can add a ‘+’ or ‘*’ sign between any two numbers.
 * <p>
 * Examples:
 * <p>
 * Input : 01231
 * Output :
 * ((((0 + 1) + 2) * 3) + 1) = 10
 * In above manner, we get the maximum value i.e. 10
 * <p>
 * Input : 891
 * Output :73
 * As 8*9*1 = 72 and 8*9+1 = 73.So, 73 is maximum.
 * <p>
 * Approaches
 * ------------
 * Approach 1: We can use Boolean parenthesis idea to evaluate it in O(n^2)
 * <p>
 * Approach 2 :
 * a. Since we know if we put * between any number to 0 then max value can't be obtained
 * b. Similarly we know if we put * between any number to 1 then max value can't be obtained as + gives max value
 * <p>
 * So, if you see a number as 0 / 1 use plus otherwise *
 */
public class MaximumValueByPlacingPlusMulOperator {

    public static void main(String ars[]) {

        System.out.println("0121212 ->" + maxValue("0121212"));

        System.out.println("011231 ->" + maxValue("011231"));

        System.out.println("01231 ->" + maxValue("01231"));

        System.out.println("897 ->" + maxValue("897"));

        System.out.println("89 ->" + maxValue("89"));

        System.out.println("8973 ->" + maxValue("897"));

    }


    private static int maxValue(String input) {

        if (input == null || input.isEmpty() || !input.matches(".*[0-9].*"))
            return -1;

        char str[] = input.toCharArray();

        int runnerMax = str[0] - '0';

        for (int i = 1; i < str.length; i++) {

            if (str[i] == '0' || str[i] == '1')
                runnerMax += str[i] - '0';
            else if (runnerMax <= 1) //if the current runner max is less than 1,then you need make + only
                runnerMax += str[i] - '0';
            else
                runnerMax *= str[i] - '0'; //otherwise multiply
        }


        return runnerMax;
    }


}
