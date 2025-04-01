package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2517;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 12/3/2024
 * Question Title: 2517. Maximum Tastiness of Candy Basket
 * Link: https://leetcode.com/problems/maximum-tastiness-of-candy-basket/description/?envType=problem-list-v2&envId=o2pr208d
 * Description: You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
 * <p>
 * The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
 * <p>
 * Return the maximum tastiness of a candy basket.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: price = [13,5,1,8,21,2], k = 3
 * Output: 8
 * Explanation: Choose the candies with the prices [13,5,21].
 * The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
 * It can be proven that 8 is the maximum tastiness that can be achieved.
 * Example 2:
 * <p>
 * Input: price = [1,3,1], k = 2
 * Output: 2
 * Explanation: Choose the candies with the prices [1,3].
 * The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
 * It can be proven that 2 is the maximum tastiness that can be achieved.
 * Example 3:
 * <p>
 * Input: price = [7,7,7,7], k = 2
 * Output: 0
 * Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= k <= price.length <= 105
 * 1 <= price[i] <= 109
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
 * @BinarySearch
 * @Greedy
 * @Sorting <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumTastinessOfCandyBasket_2517 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{13, 5, 1, 8, 21, 2}, 3, 8));
        tests.add(test(new int[]{1, 3, 1}, 2, 2));
        tests.add(test(new int[]{7, 7, 7, 7}, 2, 0));
        tests.add(test(new int[]{34, 116, 83, 15, 150, 56, 69, 42, 26}, 6, 19));
        tests.add(test(new int[]{106, 195, 138, 127, 79, 119, 46, 198, 166, 10, 41, 151, 68, 198, 126, 46, 140, 35, 127}, 12, 8));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] price, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Prices", "K", "Expected"}, true, price, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_BinarySearch solutionBinarySearch = new Solution_BinarySearch();
        output = solutionBinarySearch.maximumTastiness(price, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_BinarySearch {
        public int maximumTastiness(int[] price, int k) {
            if (price.length == 1)
                return price[0];

            int n = price.length;
            Arrays.sort(price);
            int low = 0;
            int high = price[n - 1] - price[0];
            int result = low;
            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (isPossible(price, k, mid)) {
                    result = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return result;
        }

        boolean isPossible(int[] price, int k, int x) {

            int picked = price[0];
            int i = 1;
            int count = 1;
            while (i < price.length && count < k) {
                if (price[i] - picked >= x) {
                    picked = price[i];
                    count++;
                }
                i++;
            }
            return count == k;
        }

    }

}
