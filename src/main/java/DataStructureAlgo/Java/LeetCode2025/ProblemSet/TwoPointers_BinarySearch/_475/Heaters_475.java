package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._475;

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
        CommonMethods.printTest(new String[]{"Houses", "Heaters", "Expected"}, true, houses, heaters, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_TwoPointerBinarySearch solutionTwoPointerBinarySearch = new Solution_TwoPointerBinarySearch();
        output = solutionTwoPointerBinarySearch.findRadius(houses, heaters);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * /**
     * <p>
     * ### Binary Search to Find Minimum Heater Radius
     * <p>
     * We have `n` houses and `m` heaters placed on a line. We need the **minimum radius** such that **every house is within the radius of at least one heater**.
     * <p>
     * Since positions are fixed, we can:
     * <p>
     * 1. **Sort houses and heaters** to simplify distance checks.
     * 2. For a given radius `r`, check if **all houses are within `r` units of any heater**.
     * <p>
     * If only one heater exists, the required radius is the **max distance from that heater to the farthest house**.
     * So, the search space is limited to [0, max_distance].
     * <p>
     * We apply **binary search** over this range:
     * <p>
     * * For each radius guess, check if all houses are covered.
     * * Use two pointers or binary search to find the closest heater for each house.
     * <p>
     * O(nlogn) + O(mlogm) + O(log(max) * (n+m)) => O(nlogn+mlogm+(n+m)⋅log(maxDistance))
     * O(nlogn+mlogm+(n+m)⋅log(maxDistance)) / O (1)
     */


    static class Solution_TwoPointerBinarySearch {

        public int findRadius(int[] houses, int[] heaters) {
            int nHouses = houses.length;
            int nHeaters = heaters.length;

            Arrays.sort(houses);
            Arrays.sort(heaters);

            int minRadius = 0; // since at best case, we have put heaters to all houses
            int maxRadius = Math.max(houses[nHouses - 1], heaters[nHeaters - 1]); // That possible that either the last house or heater placed very far away. like [1,99]

            while (minRadius < maxRadius) {

                int mid = minRadius + (maxRadius - minRadius) / 2;

                if (isPossible(houses, heaters, mid)) {

                    maxRadius = mid; // current mid is standard radius

                } else {
                    minRadius = mid + 1;
                }
            }

            return minRadius;
        }

        private boolean isPossible(int[] houses, int[] heaters, int radius) {

            int housesCovered = 0;

            int h = 0; // heater index

            //calculate the coverage of 'h' th heater with this radius
            int minRadius = heaters[h] - radius;
            int maxRadius = heaters[h] + radius;
            int i = 0;
            while (i < houses.length) {

                //is this house covered ?
                if (minRadius <= houses[i] && houses[i] <= maxRadius) {
                    //yes, count it
                    housesCovered++;
                    i++; // next house
                } else {
                    //no, check with next heater
                    if (h + 1 < heaters.length) {
                        h++;
                        minRadius = heaters[h] - radius;
                        maxRadius = heaters[h] + radius;
                    } else {
                        //all heaters are used
                        break;
                    }
                }
            }

            return housesCovered == houses.length;

        }
    }
}