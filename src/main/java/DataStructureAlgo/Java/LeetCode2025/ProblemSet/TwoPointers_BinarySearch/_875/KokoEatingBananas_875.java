package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._875;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/22/2025
 * Question Title: 875. Koko Eating Bananas
 * Link: https://leetcode.com/problems/koko-eating-bananas/description/
 * Description: Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * <p>
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
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
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class KokoEatingBananas_875 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 6, 7, 11}, 8, 4));
        tests.add(test(new int[]{30, 11, 23, 4, 20}, 5, 30));
        tests.add(test(new int[]{30, 11, 23, 4, 20}, 6, 23));
        tests.add(test(new int[]{312884470}, 312884469, 2));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int hour, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Piles", "hours", "Expected"}, true, nums, hour, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_BinarySearch().minEatingSpeed(nums, hour);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_BinarySearchV2().minEatingSpeed(nums, hour);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BinarySearchV2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * piles[i] - number of bananas
     * h -> hours
     * <p>
     * piles = [3,6,7,11], h = 8
     * if k = 1 [3 + 6 + 7 + 11 = 27hr]
     * if k = 3 [3/3=1 + 6/3=2 + 7/3=2+1, 11/3=3+1 = 10hr  ]
     * <p>
     * ...
     * if k = 11 [ 1, 1, 1, 1 = 4]
     * <p>
     * we can see the k is in the range of [0, Max] and we can apply BS and for every mid, check is it possible to eat all bananas in h hour
     */
    static class Solution_BinarySearch {
        public int minEatingSpeed(int[] piles, int h) {
            int maxSpeed = Integer.MIN_VALUE;

            for (int p : piles) {
                maxSpeed = Math.max(maxSpeed, p);
            }

            int minimumSpeed = 0;
            int low = 1, high = maxSpeed;

            while (low <= high) {

                int mid = (low + high) >>> 1;

                if (isPossible(piles, h, mid)) {
                    minimumSpeed = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }

            return minimumSpeed;
        }

        private boolean isPossible(int[] piles, int h, int speed) {
            int hoursSpent = 0;

            for (int pile : piles) {
                hoursSpent += (pile + speed - 1) / speed; // (int) Math.ceil((double) pile / speed);
                if (hoursSpent > h)
                    break;

            }
            return hoursSpent <= h;
        }
    }


    static class Solution_BinarySearchV2 {
        public int minEatingSpeed(int[] piles, int h) {
            int maxSpeed = Integer.MIN_VALUE;

            for (int p : piles) {
                maxSpeed = Math.max(maxSpeed, p);
            }

            int low = 1, high = maxSpeed;

            while (low < high) {

                int mid = (low + high) >>> 1;

                if (isPossible(piles, h, mid)) {
                    high = mid;
                } else {
                    low = mid + 1;
                }

            }

            return low;
        }

        private boolean isPossible(int[] piles, int h, int speed) {
            int hoursSpent = 0;

            for (int pile : piles) {
                hoursSpent += (pile + speed - 1) / speed; // (int) Math.ceil((double) pile / speed);
                if (hoursSpent > h)
                    break;

            }
            return hoursSpent <= h;
        }
    }
}
