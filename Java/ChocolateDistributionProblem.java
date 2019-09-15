package Java;

import Java.HelpersToPrint.GenericPrinter;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-24
 * Description: https://www.geeksforgeeks.org/chocolate-distribution-problem/
 * <p>
 * Given an array of n integers where each value represents number of chocolates in a packet. Each packet can have variable number of chocolates. There are m students, the task is to distribute chocolate packets such that:
 * <p>
 * 1. Each student gets one packet.
 * 2. The difference between the number of chocolates in packet with maximum chocolates and packet with minimum chocolates
 * given to the students is minimum.
 * Examples:
 * <p>
 * Input : arr[] = {7, 3, 2, 4, 9, 12, 56}
 * m = 3
 * Output: Minimum Difference is 2
 * We have seven packets of chocolates and
 * we need to pick three packets for 3 students
 * If we pick 2, 3 and 4, we get the minimum
 * difference between maximum and minimum packet
 * sizes.
 * <p>
 * Input : arr[] = {3, 4, 1, 9, 56, 7, 9, 12}
 * m = 5
 * Output: Minimum Difference is 6
 * The set goes like 3,4,7,9,9 and the output
 * is 9-3 = 6
 * <p>
 * Input : arr[] = {12, 4, 7, 9, 2, 23, 25, 41,
 * 30, 40, 28, 42, 30, 44, 48,
 * 43, 50}
 * m = 7
 * Output: Minimum Difference is 10
 * We need to pick 7 packets. We pick 40, 41,
 * 42, 44, 48, 43 and 50 to minimize difference
 * between maximum and minimum.
 * <p>
 * [Flipkart]
 */
public class ChocolateDistributionProblem {
    public static void main(String[] args) {
        test(new int[]{7, 3, 2, 4, 9, 12, 56}, 3, 2);
        test(new int[]{3, 4, 1, 9, 56, 7, 9, 12}, 5, 6);
        test(new int[]{12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43, 50}, 7, 10);
        test(new int[]{3, 4, 1, 9, 56, 7, 9, 12}, 8, 55);
        test(new int[]{3, 4, 90, 9, 56, 7, 9, 12, 23}, 8, 53);


    }

    private static void test(int[] chocolates, int m, int expected) {
        System.out.println("\n chocolates :" + GenericPrinter.toString(chocolates) + " students :" + m);
        System.out.println("minimumDifference :" + minimumDifference(chocolates, m) + " expected :" + expected);
    }

    /**
     * Since, Each student gets one packet.
     * i.e. We need to divide the array in m set
     * <p>
     * And
     * The difference between the number of chocolates in packet with maximum chocolates and packet with minimum chocolates
     * given to the students is minimum.
     * There are two possibilities
     * <p>
     * 1. if number of chocolates = students ; Then the least difference is only 'max-min' ; as we have to give a packet to each student
     * and one student will have maximum number of chocolate packet while other one will have minimum number of chocolate packet.
     * <p>
     * 2. if number of chocolates > students ; then we need to give chocolates packets to each student in a way that max-min is Minimize.
     * To minimize, we can give m packets to m student such a way that student A will have X number of chocolate and Student B will have Y number of chocolates
     * and Y > X and Y-X is minimum.
     * In order to do that, we can give packets in sorted order of window m, and find the minimum difference in window elements.
     * <p>
     * <p>
     * Concept : {@link Java.LeetCode.SlidingWindowMaximum}
     *
     * @param chocolates
     * @param m
     * @return
     */
    private static int minimumDifference(int chocolates[], int m) {

        if (chocolates == null || chocolates.length == 0)
            return 0;

        if (chocolates.length < m) {
            return Integer.MAX_VALUE; //not possible to distribute a packet to each student
        } else if (chocolates.length == m) {
            int min = chocolates[0];
            int max = chocolates[0];

            for (int i = 1; i < chocolates.length; i++) {
                min = Math.min(min, chocolates[i]);
                max = Math.max(max, chocolates[i]);
            }

            return max - min;
        }


        int best = Integer.MAX_VALUE;
        Arrays.sort(chocolates);


        for (int i = 0; i < chocolates.length - m + 1; i++) {

            best = Math.min(best, chocolates[i + m - 1] - chocolates[i]);
        }

        return best;

    }
}
