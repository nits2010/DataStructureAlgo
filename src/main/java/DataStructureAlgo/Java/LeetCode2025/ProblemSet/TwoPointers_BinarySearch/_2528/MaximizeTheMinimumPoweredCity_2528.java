package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2528;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/29/2025
 * Question Title: 2528. Maximize the Minimum Powered City
 * Link: https://leetcode.com/problems/maximize-the-minimum-powered-city
 * Description: You are given a 0-indexed integer array stations of length n, where stations[i] represents the number of power stations in the ith city.
 * <p>
 * Each power station can provide power to every city in a fixed range. In other words, if the range is denoted by r, then a power station at city i can provide power to all cities j such that |i - j| <= r and 0 <= i, j <= n - 1.
 * <p>
 * Note that |x| denotes absolute value. For example, |7 - 5| = 2 and |3 - 10| = 7.
 * The power of a city is the total number of power stations it is being provided power from.
 * <p>
 * The government has sanctioned building k more power stations, each of which can be built in any city, and have the same range as the pre-existing ones.
 * <p>
 * Given the two integers r and k, return the maximum possible minimum power of a city, if the additional power stations are built optimally.
 * <p>
 * Note that you can build the k power stations in multiple cities.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: stations = [1,2,4,5,0], r = 1, k = 2
 * Output: 5
 * Explanation:
 * One of the optimal ways is to install both the power stations at city 1.
 * So stations will become [1,4,4,5,0].
 * - City 0 is provided by 1 + 4 = 5 power stations.
 * - City 1 is provided by 1 + 4 + 4 = 9 power stations.
 * - City 2 is provided by 4 + 4 + 5 = 13 power stations.
 * - City 3 is provided by 5 + 4 = 9 power stations.
 * - City 4 is provided by 5 + 0 = 5 power stations.
 * So the minimum power of a city is 5.
 * Since it is not possible to obtain a larger power, we return 5.
 * Example 2:
 * <p>
 * Input: stations = [4,4,4,4], r = 0, k = 3
 * Output: 4
 * Explanation:
 * It can be proved that we cannot make the minimum power of a city greater than 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == stations.length
 * 1 <= n <= 105
 * 0 <= stations[i] <= 105
 * 0 <= r <= n - 1
 * 0 <= k <= 109
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
 * <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximizeTheMinimumPoweredCity_2528 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 4, 5, 0}, 1, 2, 5));
        tests.add(test(new int[]{4, 4, 4, 4}, 0, 3, 4));
        tests.add(test(new int[]{13, 12, 8, 14, 7}, 2, 23, 52));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] stations, int r, int k, long expected) {
        //add print here
        CommonMethods.printTest(new String[]{"stations", "r", "k", "Expected"}, true, stations, r, k, expected);

        long output = 0;
        boolean pass, finalPass = true;

        output = new Solution().maxPower(stations, r, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BinarySearch + sliding window + prefixSum", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Problem Overview
     * We are given an array stations of length n, where stations[i] denotes the number of power stations in city i. By default, each city has access to its own power station(s), so city i has at least stations[i] power.
     * <p>
     * Let’s assume:
     * <p>
     * r = 1, meaning each power station can supply power to its adjacent cities within a radius of 1.
     * <p>
     * k = 0, meaning we are not allowed to add any new power stations.
     * <p>
     * Our goal is to compute the total power available to each city under these constraints.
     * Input:
     * stations = [1, 2, 4, 5, 0], r = 1, k = 0
     * We calculate the power for each city by summing the values in the range [i - r, i + r]:
     * <p>
     * City 0: 1 (self) + 2 (right) = 3
     * <p>
     * City 1: 1 (left) + 2 (self) + 4 (right) = 7
     * <p>
     * City 2: 2 (left) + 4 (self) + 5 (right) = 11
     * <p>
     * City 3: 4 (left) + 5 (self) + 0 (right) = 9
     * <p>
     * City 4: 5 (left) + 0 (self) = 5
     * <p>
     * This method is O(n * r) since for each city we examine up to 2r + 1 elements.
     * <p>
     * Optimization Using Prefix Sum
     * To make this more efficient, especially when r is large, we can use a prefix sum approach to calculate the power for each city in O(1) time per city.
     * <p>
     * Prefix Sum Construction:
     * stations =  [1, 2, 4, 5, 0]
     * prefix    = [1, 3, 7, 12, 12]
     * Power Calculation Using Prefix Sum:
     * power[i] = prefix[min(n - 1, i + r)] - prefix[max(-1, i - r - 1)] if minimumRadius < 0 then reset power to 0, similarly if i+r goes beyond n then reset to n-1
     * Example:
     * <p>
     * power[0] = prefix[1] - prefix[-2] = 3 - 0 = 3 [i-r-1 < 0 ]
     * power[1] = prefix[2] - prefix[-1] = 7
     * ...
     * power[3] = prefix[4] - prefix[1] = 12 - 3 = 9
     * power[4] = prefix[4] - prefix[2] = 12 - 7 = 5 since 4 + r = 5 > 4 hence reset to 4
     * <p>
     * When k > 0: Introducing Extra Stations
     * So far, we assumed k = 0. Now consider k > 0, meaning we can add k extra stations anywhere to maximize the minimum power across all cities.
     * <p>
     * For example, if r = n - 1, each power station affects all cities. The total available power is initially:
     * total_power = prefix[n - 1]
     * After adding k new stations, total power becomes:
     * max_possible_power = prefix[n - 1] + k
     * <p>
     * Binary Search on Answer
     * We binary search on the mid value representing a candidate for the minimum power each city should have. For each mid, we check:
     * <p>
     * Now, for every 'mid' value in the range, we need to check if is possible to obtain 'mid' power by adding k stations.
     * <p>
     * In order to find the do we require k stations or not to get minimum 'mid' power to ith city, we need to try all [i-r,i+r] window power of each city. And check if we add k more station, is it possible to get 'mid' power or not. if not, then we can return false otherwise we can try further with remaining k plants. Since adding 'requiredPlants' will also affect all the city ahead ith city, we will greedly add this power to i+r th city so that it can cover maximum possible city's. And reduce all cities which is not in range
     * i + r + r + 1 would be not in range of (i+r)th city. To accomdated this additional Power, we need another additionalPower array to compute this.
     * <p>
     * Time : O(n) + O(n) + O( n* log(Max)) ; here max is totalSum+k
     * Space: O(n)
     */

    static class Solution {
        private long[] getPrefixSum(int[] stations) {
            int n = stations.length;
            long[] prefixSum = new long[n];
            prefixSum[0] = stations[0];

            for (int i = 1; i < n; i++) {
                prefixSum[i] = prefixSum[i - 1] + stations[i];
            }
            return prefixSum;
        }

        private long[] getPowerOfCities(long[] prefixSum, int r) {
            int n = prefixSum.length;
            long[] power = new long[n];

            //calculate the power of each city using prefixSum
            for (int i = 0; i < n; i++) {
                int minRadius = i - r - 1;
                int maxRadius = Math.min(n - 1, i + r); // if i+r goes beyond the right boundary, reset to last element

                long excludedPower = minRadius < 0 ? 0 : prefixSum[minRadius];

                power[i] = prefixSum[maxRadius] - excludedPower;
            }

            return power;
        }

        public long maxPower(int[] stations, int r, int k) {
            int n = stations.length;

            //get the power of each city using a prefix sum aka power of each station,  prefix sum so that we can compute the power of each city with [i-r, i+r] range in O(1) time.
            long[] prefixSum = getPrefixSum(stations);
            long[] power = getPowerOfCities(prefixSum, r);

            long low = 0, high = prefixSum[n - 1] + k;
            long result = 0;
            while (low <= high) {

                long mid = (low + high) >>> 1;

                if (isPossible(stations, power, r, k, mid)) {
                    result = mid;
                    low = mid + 1; //we need to maximum possible minimum power
                } else {
                    high = mid - 1; // mid-power is not possible even with additional k plants, hence decrease it
                }
            }
            return result;
        }

        private boolean isPossible(int[] stations, long[] powerOfEachCity, int r, int availablePowerStations,
                                   long minimumPower) {

            int n = stations.length;
            long power = 0; // power of ith city

            // Here we will add the additional power we get during computation. We also reduce all such power which goes out of the window [i-r, i+r]
            long[] additionalPower = new long[n];

            for (int i = 0; i < n; i++) {

                //get current power of the ith city
                power = powerOfEachCity[i];

                //add the additional power for the current city, added by previous city when computed already.
                //This is because as we add plants, the power of ith city will increase by the number of plants we added. Which will benifits its adjacent cities.
                if (i > 0)
                    additionalPower[i] += additionalPower[i - 1];

                //now this city will have 'power' power
                power += additionalPower[i];

                // if power >= minimumPower then no additional power station required, hence ith city has enough power
                if (power < minimumPower) {

                    //see does this city have enough power;  if not, then we need to add additional power stations
                    long requiredPowerStations = minimumPower - power;

                    if (requiredPowerStations > availablePowerStations) {
                        //we need more power stations to make ith city has minimumPower
                        return false;
                    }

                    //means this city does not enough power to meet 'minimumPower,' however, we have enough power stations to plant
                    availablePowerStations -= requiredPowerStations;

                    //since the ith city has minimumPower now, let's add it to additional power which we added to ith city requiredPowerStations = additionalPower added for ith city
                    additionalPower[i] += requiredPowerStations;

                    //we need to remove all the additional power which will goes out of a window.
                    // if we have placed the power plant at ith, this will allow to power i+r the city extra only, however, if we had placed
                    // it on (i+r) then the current ith city also gets this power and ( i+r+r ) city as well but not beyond that.
                    // hence we need to remove the 'requiredPowerStations' for all the cities which is i+r+r+1 away
                    int awayCity = i + 2 * r + 1;
                    if (awayCity < n)
                        additionalPower[awayCity] -= requiredPowerStations;
                }

            }

            return true; // we are able to get the minimumPower to each city.

        }

    }
}
