package Java.companyWise.intuit;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/09/19
 * Description:
 * https://www.geeksforgeeks.org/count-ways-express-number-sum-consecutive-numbers/
 * Given a number N, find the number of ways to represent this number as a sum of 2 or more consecutive natural numbers.
 * <p>
 * Examples:
 * <p>
 * Input :15
 * Output :3
 * 15 can be represented as:
 * 1+2+3+4+5
 * 4+5+6
 * 7+8
 * <p>
 * Input :10
 * Output :1
 * 10 can only be represented as:
 * 1+2+3+4
 */
public class ConsecutiveSum {

    public static void main(String[] args) {
        test(15, 3);
        test(21, 3);
    }

    private static void test(long n, int expected) {
        System.out.println("\n num :" + n + " expected :" + expected);
        System.out.println("math :" + consecutiveMath(n));
        System.out.println("Array :" + consecutive(n));
        System.out.println("Without Array :" + consecutiveWithArray(n));
    }

    /**
     * * The idea is to represent N as a sequence of length L+1 as:
     * * N = a + (a+1) + (a+2) + .. + (a+L)
     * * => N = (L+1)*a + (L*(L+1))/2
     * * => a = (N- L*(L+1)/2)/(L+1)
     * * We substitute the values of L starting from 1 till L*(L+1)/2 < N
     * *  * If we get 'a' as a natural number then the solution should be counted.
     *
     * @param num
     * @return
     */
    public static int consecutiveMath(long num) {
        int count = 0;
        for (int l = 1; l * (l + 1) < (2 * num); l++) {

            float a = (float) ((1.0 * num - (l * (l + 1)) / 2) / (l + 1));

            if (a - (int) a == 0.0)
                count++;
        }
        return count;
    }

    public static int consecutive(long num) {

        List<Long> sum = new ArrayList<>();
        sum.add((long) 0);


        for (int i = 1; i <= num; i++) {
            sum.add(sum.get(i - 1) + i);
        }

        int i = 0;
        int j = 1;
        int count = 0;
        long curSum ;


        while (j < num) {
            curSum = sum.get(j) - sum.get(i);


            if (curSum == num) {
                count++;
            }
            if (curSum <= num)
                j++;
            else
                i++;
        }
        return count;
    }


    public static int consecutiveWithArray(long n) {
        int i = 1, j = 1, count = 0;
        long sum = 1;
        while (j < n) {
            if (sum == n) { // matched, move sub-array section forward by 1
                count++;
                sum -= i;
                i++;
                j++;
                sum += j;
            } else if (sum < n) { // not matched yet, extend sub-array at end
                j++;
                sum += j;
            } else { // exceeded, reduce sub-array at start
                sum -= i;
                i++;
            }
        }
        return count;
    }

}
