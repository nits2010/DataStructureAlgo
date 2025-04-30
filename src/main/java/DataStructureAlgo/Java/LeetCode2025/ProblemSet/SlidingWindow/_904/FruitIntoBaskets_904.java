package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._904;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/29/2025
 * Question Title: 904. Fruit Into Baskets
 * Link: https://leetcode.com/problems/fruit-into-baskets/description/
 * Description: You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * <p>
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * <p>
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * Example 2:
 * <p>
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 * Example 3:
 * <p>
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340.LongestSubstringWithAtMostKDistinctCharacters_340}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340.LongestSubstringWithAtMostKDistinctCharacters_340}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FruitIntoBaskets_904 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 1}, 3));
        tests.add(test(new int[]{0, 1, 2, 2}, 3));
        tests.add(test(new int[]{1, 2, 3, 2, 2}, 4));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution().totalFruit(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int totalFruit(int[] fruits) {
            return totalFruit(fruits, 2);
        }

        /**
         * {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340.LongestSubstringWithAtMostKDistinctCharacters_340}
         */
        public int totalFruit(int[] fruits, int k) {
            int n = fruits.length;
            Map<Integer, Integer> mapOfFreq = new HashMap<>();

            int maxLen = 0;
            int windowStart = 0, windowEnd = 0;

            while (windowEnd < n) {

                //expand the window
                mapOfFreq.merge(fruits[windowEnd], 1, Integer::sum);

                if (mapOfFreq.size() <= k) {
                    maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
                }

                //shrink the window till window size is 0 or k distinct character left
                while (mapOfFreq.size() > k && windowStart <= windowEnd) {
                    int previous = fruits[windowStart];
                    mapOfFreq.merge(previous, -1, Integer::sum);
                    if (mapOfFreq.get(previous) == 0) {
                        mapOfFreq.remove(previous);
                    }
                    windowStart++;
                }
                windowEnd++;
            }
            
            return maxLen;
        }
    }
}
