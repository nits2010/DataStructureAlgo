package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._2107;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/13/2025
 * Question Title: 2107. Number of Unique Flavors After Sharing K Candies
 * Link: https://leetcode.com/problems/number-of-unique-flavors-after-sharing-k-candies/description/
 * https://github.com/doocs/leetcode/blob/main/solution/2100-2199/2107.Number%20of%20Unique%20Flavors%20After%20Sharing%20K%20Candies/README_EN.md
 * https://leetcode.ca/2021-12-30-2107-Number-of-Unique-Flavors-After-Sharing-K-Candies/
 * Description: You are given a 0-indexed integer array candies, where candies[i] represents the flavor of the ith candy. Your mom wants you to share these candies with your little sister by giving her k consecutive candies, but you want to keep as many flavors of candies as possible.
 * <p>
 * Return the maximum number of unique flavors of candy you can keep after sharing with your sister.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candies = [1,2,2,3,4,3], k = 3
 * Output: 3
 * Explanation:
 * Give the candies in the range [1, 3] (inclusive) with flavors [2,2,3].
 * You can eat candies with flavors [1,4,3].
 * There are 3 unique flavors, so return 3.
 * Example 2:
 * <p>
 * Input: candies = [2,2,2,2,3,3], k = 2
 * Output: 2
 * Explanation:
 * Give the candies in the range [3, 4] (inclusive) with flavors [2,3].
 * You can eat candies with flavors [2,2,2,3].
 * There are 2 unique flavors, so return 2.
 * Note that you can also share the candies with flavors [2,2] and eat the candies with flavors [2,2,3,3].
 * Example 3:
 * <p>
 * Input: candies = [2,4,5], k = 0
 * Output: 3
 * Explanation:
 * You do not have to give any candies.
 * You can eat the candies with flavors [2,4,5].
 * There are 3 unique flavors, so return 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= candies.length <= 10^5
 * 1 <= candies[i] <= 10^5
 * 0 <= k <= candies.length
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._992.SubArraysWithKDifferentIntegers_992}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @LeetCodeLockedProblem
 * @PremiumQuestion
 * @SlidingWindow
 * @Array
 * @HashTable <p><p>
 * Company Tags
 * -----
 * @Microsoft <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class NumberOfUniqueFlavorsAfterSharingKCandies_2107 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 2, 3, 4, 3}, 3, 3));
        tests.add(test(new int[]{2, 2, 2, 2, 3, 3}, 2, 2));
        tests.add(test(new int[]{2, 4, 5}, 0, 3));
        tests.add(test(new int[]{1, 2, 2, 3, 4, 3}, 6, 0));
        tests.add(test(new int[]{1, 2, 2, 3, 4, 3}, 5, 1));
        tests.add(test(new int[]{1, 2, 2, 3, 4, 3}, 4, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] candies, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Candies", "k",  "Expected"}, true, candies, k, expected);

        int output;
        boolean pass, finalPass = true;

        output = new Solution().shareCandies(candies, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Sliding Window", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Since we need to find the maximum unique type of candies that should be left with us post sharing k candies with Sister.
     * Since it needs to be consecutive, it means we may need to apply a sliding window.
     * In a sliding window, we can easily window, how many types of candies we have chosen to share by keeping them in a map and map.size() would be types of candidies.
     * However, to know how many types of candies we are left with, we need to track them.
     * <p>
     * In brute force, we could have built all subarrays of size k, and then remove such a subarray and see how many types of candies left in the remaining array.
     * <p>
     * To get to know the remaining type of candies, we may need to count them again n again in brute force.
     * However, what else we can do is to track how many different types of candies we have initially and their counts. Now as soon as we build a sub-array of size k, we can
     * remove such candies counts from our map and check how many types of candies left; that will be our answer for that sub-array.
     * <p>
     * using a sliding window, we can keep track of it.
     *
     * O(n) time and O(n) space
     */
    static class Solution {
        public int shareCandies(int[] candies, int k) {
            int n = candies.length;

            Map<Integer, Integer> candiesVsCount = new HashMap<>();

            //counting the number of different types of candies and their count.
            for (int candy : candies) {
                candiesVsCount.merge(candy, 1, Integer::sum);
            }

            int totalUniqueCandies = candiesVsCount.size();

            if (k == 0)
                return totalUniqueCandies;

            int start = 0, end = 0;
            int maxUniqueCount = 0;

            while (end < n) {

                //include this candy in the window,
                // we are removing the candy from our count map, which denotes we have used this kind of candy for a sister
                if (candiesVsCount.merge(candies[end], -1, Integer::sum) == 0)
                    candiesVsCount.remove(candies[end]);

                //we have total k candies to share with our sister,
                if (end - start + 1 == k) {
                    //count how many types we have so far
                    maxUniqueCount = Math.max(maxUniqueCount, candiesVsCount.size());

                    //shrink the window
                    //now we add the start back to our count map, which denotes that we have removed that candy type from k and keep it with us
                    candiesVsCount.merge(candies[start], 1, Integer::sum);
                    start++;

                }


                end++;
            }
            return maxUniqueCount;
        }
    }
}
