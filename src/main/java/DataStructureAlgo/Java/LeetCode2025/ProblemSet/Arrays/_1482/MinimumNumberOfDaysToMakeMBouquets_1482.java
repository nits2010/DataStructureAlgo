package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1482;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 11/27/2024
 * Question Title: 1482. Minimum Number of Days to Make m Bouquets
 * Link: https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/?envType=problem-list-v2&envId=o2pr208d
 * Description: You are given an integer array bloomDay, an integer m and an integer k.
 * <p>
 * You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 * <p>
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
 * <p>
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
 * We need 3 bouquets each should contain 1 flower.
 * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
 * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
 * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
 * Example 2:
 * <p>
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
 * Example 3:
 * <p>
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * Explanation: We need 2 bouquets each should have 3 flowers.
 * Here is the garden after the 7 and 12 days:
 * After day 7: [x, x, x, x, _, x, x]
 * We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
 * After day 12: [x, x, x, x, x, x, x]
 * It is obvious that we can make two bouquets in different ways.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * bloomDay.length == n
 * 1 <= n <= 105
 * 1 <= bloomDay[i] <= 109
 * 1 <= m <= 106
 * 1 <= k <= n
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumNumberOfDaysToMakeMBouquets_1482 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 10, 3, 10, 2}, 3, 1, 3));
        tests.add(test(new int[]{1, 10, 3, 10, 2}, 3, 2, -1));
        tests.add(test(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3, 12));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] bloomDay, int m, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"bloomDay", "m", "k", "Expected"}, true, bloomDay, m, k, expected);

        int output;
        boolean pass, finalPass = true;

        Solution sol = new Solution();

        output = sol.minDays(bloomDay, m, k);

        pass = output == expected;

        finalPass = pass && finalPass;
        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "true" : "false");

        return finalPass;

    }

    static class Solution {
        public int minDays(int[] bloomDay, int m, int k) {
            if (bloomDay.length < m * k)
                return -1;

            int low = Integer.MAX_VALUE;
            int high = -1;

            for (int e : bloomDay) {
                low = Math.min(e, low);
                high = Math.max(e, high);
            }

            //binary search
            int result = -1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (isPossible(bloomDay, mid, m, k)) {
                    result = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return result;
        }

        private boolean isPossible(int[] days, int mid, int m, int k) {

            int flowerNeeded = 0;
            int bouquets = 0;

            for (int day : days) {


                if (day <= mid) {
                    flowerNeeded++;

                    if (flowerNeeded == k) {
                        bouquets++;
                        flowerNeeded = 0;
                    }
                } else {
                    flowerNeeded = 0;
                }

                if (bouquets == m)
                    return true;
            }

            return bouquets >= m;
        }
    }
}
