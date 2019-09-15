package Java.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/09/19
 * Description: https://leetcode.com/problems/consecutive-numbers-sum/
 * 829. Consecutive Numbers Sum
 * Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: 5 = 5 = 2 + 3
 * Example 2:
 * Input: 9
 * Output: 3
 * Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
 * Example 3:
 * Input: 15
 * Output: 4
 * Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 * Note: 1 <= N <= 10 ^ 9.
 * <p>
 * Best solution is {@link ConsecutiveSumMath3}
 * One more good solution
 * https://leetcode.com/problems/consecutive-numbers-sum/discuss/272888/100-Simple-C%2B%2B-without-SQRT-and-Multiplication
 */
public class ConsecutiveSum {

    public static void main(String[] args) {
        test(15, 4);
        test(21, 4);
    }

    private static void test(int n, int expected) {
        System.out.println("\n num :" + n + " expected :" + expected);
        System.out.println(" ConsecutiveSumMath1 : " + ConsecutiveSumMath1.consecutiveNumbersSum(n));
        System.out.println(" ConsecutiveSumMath2 : " + ConsecutiveSumMath2.consecutiveNumbersSum(n));
        System.out.println(" ConsecutiveSumMath3 : " + ConsecutiveSumMath3.consecutiveNumbersSum(n));
        System.out.println(" ConsecutiveSumCalculation : " + ConsecutiveSumCalculation.consecutiveNumbersSum(n));
        System.out.println(" ConsecutiveSumCalculation 2: " + ConsecutiveSumCalculation.consecutiveNumbersSumWithStoring(n));


    }


}

/**
 * We can solve this problem mathematically.
 * To express a number in form of sum of 'M consecutive increasing numbers', we can write m as
 * N =  (a+1) + (a+2) + (a+3)....+(a+M)
 * => N = (M*a + (1+2+3+...M)
 * => N = ((M*a + M*(M+1)/2 )
 * => N = M * (a + (M+1)/2)
 * => N = M * ( 2*a + M+1) / 2
 * => 2*N = M * ( 2*a + M+1)
 * => a = (((2*N) / M) - M - 1)/2
 * <p>
 * Since here N, M all are integer, then a has be integer as well to suffice the condition.
 * Here
 * 1<= M <= 2*N
 */
class ConsecutiveSumMath1 {


    public static int consecutiveNumbersSum(int N) {

        int count = 1; //to including N it self as one solution
        //2*N = M * ( 2*a + M+1)

        for (int m = 1; m <= 2 * N; m++) {

            //(2*N)/M = ( 2*a + M+1)
            if (2 * N % m == 0) {

                int y = ((2 * N) / m) - m - 1;

                if (y > 0 && y % 2 == 0)
                    count++;
            }
        }


        return count;
    }
}


/**
 * Extending above solution to simplified version
 * N = (a+1) + (a+2) + ... + (a+M)
 * N = a + 1 + a + 2 + ... a + M
 * N = M*a + (1+2+...+M)
 * N = M*a + M(M+1)/2
 * N - M(M+1)/2 = M*x
 * <p>
 * a= (N-M*(M+1)/2)/M
 * <p>
 * to a to be positive, M*(M+1)/2 > N; Since N,M are integers then 'a' has to be integer
 * <p>
 * Runtime: 4 ms, faster than 49.18% of Java online submissions for Consecutive Numbers Sum.
 * Memory Usage: 33.1 MB, less than 9.09% of Java online submissions for Consecutive Numbers Sum.
 */
class ConsecutiveSumMath2 {


    public static int consecutiveNumbersSum(int N) {

        int count = 1;  //to including N it self as one solution

        for (int m = 2; (m * (m + 1)) / 2 <= N; m++) {
            int y = N - (m * (m + 1)) / 2;

            if (y % m == 0)
                count++;
        }

        return count;
    }
}

/**
 * From above:
 * N = M*a + M(M+1)/2
 * => a= (N-M*(M+1)/2)/M
 * <p>
 * Since N,M are integers then 'a' has to be integer
 * Since,
 * <p>
 * 0 + 1 + 2 + 3...M = M*(M+1)/2
 * <p>
 * a = ( N - (0 + 1 + 2 + 3...M ) / M;
 * => a = ( N - 0 - 1 - 2 - 3...-M ) / M;
 * <p>
 * a must be integer
 * <p>
 * Runtime: 3 ms, faster than 90.65% of Java online submissions for Consecutive Numbers Sum.
 * Memory Usage: 33 MB, less than 9.09% of Java online submissions for Consecutive Numbers Sum.
 */
class ConsecutiveSumMath3 {


    public static int consecutiveNumbersSum(int N) {

        int count = 0;

        for (int i = 1; i <= N; i++) {

            int a = N - (i - 1); //to avoid i=0 and y%i division by zero

            if (a % i == 0)
                count++;

            // reduce N
            N = N - (i - 1);
        }

        return count;
    }
}

//Time Limit Exceeded
class ConsecutiveSumCalculation {
    public static int consecutiveNumbersSum(int num) {

        int[] sum = new int[num];

        for (int i = 1; i < num; i++) {
            sum[i] = sum[i - 1] + i;
        }

        int i = 0;
        int j = 1;
        int count = 1;  //to including N it self as one solution
        long curSum;


        while (j < num) {
            curSum = sum[j] - sum[i];


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

    public static int consecutiveNumbersSumWithStoring(int num) {


        int i = 0;
        int j = 1;
        int count = 1;  //to including N it self as one solution
        long curSum = 1;


        while (j < num) {
            if (curSum == num) {
                count++;
                curSum -= i;

                i++;
                j++;

                curSum += j;
            } else if (curSum > num) {

                curSum -= i;
                i++;
            } else {
                j++;
                curSum += j;
            }
        }
        return count;
    }

}