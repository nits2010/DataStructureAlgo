package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._475;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 475. Heaters
 * Link: https://leetcode.com/problems/heaters/description/
 * Description: Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 * <p>
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 * <p>
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 * <p>
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: houses = [1,2,3], heaters = [2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * Example 2:
 * <p>
 * Input: houses = [1,2,3,4], heaters = [1,4]
 * Output: 1
 * Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the houses can be warmed.
 * Example 3:
 * <p>
 * Input: houses = [1,5], heaters = [2]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= houses.length, heaters.length <= 3 * 104
 * 1 <= houses[i], heaters[i] <= 109
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
 * @TwoPointers
 * @BinarySearch
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Heaters_475 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3}, new int[]{2}, 1));
        tests.add(test(new int[]{1, 2, 3, 4}, new int[]{1, 4}, 1));
        tests.add(test(new int[]{1, 5}, new int[]{2}, 3));
        tests.add(test(new int[]{1, 5}, new int[]{10}, 9));
        tests.add(test(new int[]{1}, new int[]{100}, 99));
        tests.add(test(new int[]{1}, new int[]{1, 2, 3, 4}, 0));
        tests.add(test(new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923},
                new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612},
                161834419));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] houses, int[] heaters, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Houses", "Heaters", "Expected"}, true, houses, heaters, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_TwoPointerBinarySearch solutionTwoPointerBinarySearch = new Solution_TwoPointerBinarySearch();
        output = solutionTwoPointerBinarySearch.findRadius(houses, heaters);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * O(nlogn) + O(mlogm) + O(log(max) * (n+m)) => O(nlogn+mlogm+(n+m)⋅log(maxDistance))
     * O(nlogn+mlogm+(n+m)⋅log(maxDistance)) / O (1)
     */
    static class Solution_TwoPointerBinarySearch {

        public int findRadius(int[] houses, int[] heaters) {
            int nHouses = houses.length;
            int nHeaters = heaters.length;

            Arrays.sort(houses); //sort houses, so that we can effectively place heaters at neighbors of each
            Arrays.sort(heaters); // sort heaters, so that we can place heaters at each house efficiently
            int minRadius = 0;
            int maxRadius = Math.max(houses[nHouses - 1], heaters[nHeaters - 1]); // since house and heaters can be far away, assume a case where house is at 1 while heaters is at 100, then we need at 99 radius to reach.

            int requiredRadius = 1;

            //binary search the required radius
            while (minRadius < maxRadius) {
                int mid = minRadius + (maxRadius - minRadius) / 2;

                if (isPossible(mid, houses, heaters)) {
                    maxRadius = mid;
                    requiredRadius = mid;
                } else
                    minRadius = mid + 1;
            }

            return requiredRadius;
        }

        //O(n+m)
        private boolean isPossible(int radius, int[] houses, int[] heaters) {

            int count = 0;
            int h = 0;
            int min = heaters[h] - radius;
            int max = heaters[h] + radius;

            for (int i = 0; i < houses.length; ) {
                int house = houses[i];
                if (min <= house && max >= house) {
                    count++;
                    i++;
                } else {
                    h++;
                    if (h < heaters.length) {
                        min = heaters[h] - radius;
                        max = heaters[h] + radius;
                    } else {
                        break;
                    }
                }
            }
            if (count == houses.length)
                return true;
            return false;

        }
    }
}
