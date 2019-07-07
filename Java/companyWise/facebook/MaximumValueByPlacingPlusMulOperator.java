package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description:
 * https://www.geeksforgeeks.org/calculate-maximum-value-using-sign-two-numbers-string/
 * Approach 1: We can use Boolean parathensis idea to evaluate it O(n^2)
 * Approach 2 :
 * a. Since we know if we put * between any number to 0 then max value can't be obtained
 * b. Similarly we know if we put * between any number to 1 then max value can't be obtained as + gives max value
 * <p>
 * So, if you see a number as 0 / 1 use plus otherwise *
 */
public class MaximumValueByPlacingPlusMulOperator {

    public static void main(String ars[]) {

        String s = "01231";
        System.out.println("01231 ->" + maxValue("01231"));
        System.out.println("897 ->" + maxValue("897"));
        System.out.println("89 ->" + maxValue("89"));
        System.out.println("897 ->" + maxValue("897"));

    }


    private static int maxValue(String input) {
        char str[] = input.toCharArray();

        int runnerMax = 0;

        int i = 0;

        while (i < str.length) {

            if (str[i] == '0' || str[i] == '1')
                runnerMax += str[i] - '0';

            else if (runnerMax == 0 || runnerMax == 1)
                runnerMax += str[i] - '0';

            else
                runnerMax *= str[i] - '0';

            i++;
        }

        return runnerMax;
    }
}
