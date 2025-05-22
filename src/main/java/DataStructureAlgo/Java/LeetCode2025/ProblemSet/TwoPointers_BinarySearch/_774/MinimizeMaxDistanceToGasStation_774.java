package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._774;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/23/2025
 * Question Title: 774 - Minimize Max Distance to Gas Station
 * Link: https://leetcode.com/problems/minimize-max-distance-to-gas-station/description/
 * https://leetcode.ca/all/774.html
 * Description: On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 * <p>
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 * <p>
 * Return the smallest possible value of D.
 * <p>
 * Example:
 * <p>
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 * Note:
 * <p>
 * stations.length will be an integer in range [10, 2000].
 * stations[i] will be an integer in range [0, 10^8].
 * K will be an integer in range [1, 10^6].
 * Answers within 10^-6 of the true value will be accepted as correct.
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.MinimizeMaxDistanceGasStation}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link DataStructureAlgo.Java.nonleetcode.PainterPartitionProblem}
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @BinarySearch
 * @DynamicProgramming
 * @Heap(PriorityQueue)
 * @PremimumQuestion
 * @LeetCodeLockedProblem <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimizeMaxDistanceToGasStation_774 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9, 0.5));
        tests.add(test(new int[]{1, 5, 10}, 2, 2.5));
        tests.add(test(new int[]{1, 5, 10}, 3, 2.0));
        tests.add(test(new int[]{3, 6, 12, 19, 33, 44, 67, 72, 89, 95}, 2, 14.0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, double expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "k", "Expected"}, true, nums, k, expected);

        double output = 0;
        boolean pass, finalPass = true;

        output = new Solution().findSmallestMaxDist(nums, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * "Since the minimum possible distance between gas stations is 0 and the maximum is the distance between the first and last station (assuming sorted positions), the search space is [0,last−first]. This allows us to apply binary search.
     * As we need to return a floating-point result, we use a precision margin ε = 1e-6.
     * For each mid, check if we can place at most k gas stations such that the maximum distance between any two stations is ≤ mid.
     * For a gap between two stations, the number of stations needed is ceil(gap / mid) - 1 Or  (int) ((station[i] - station[i-1] ) / mid )  Summing over all gaps gives the total stations required. If this total ≤ k, the mid is valid and we try to minimize further.
     * SInce we want to split the gap = s[i] - s[i-1] such that each gap <= mid. Lets say we put x stations then there will be x+1 segments.
     * (gap / (x+1) ) <= mid => x ≥ (gap / mid) - 1. Hence we need to find the ceil( (gap / mid) ) - 1 or with bit less precise int ((station[i] - station[i-1] ) / mid ) "
     */
    static class Solution {
        public double findSmallestMaxDist(int stations[], int k) {
            int n = stations.length;
            double low = 0, high = stations[n - 1] - stations[0];
            double eps = 1e-6; //precision control
            double output = 0;
            while (high - low > eps) {

                double mid = (high + low) / 2.0;

                if (isPossible(stations, k, mid)) {
                    output = mid;
                    high = mid;
                } else {
                    low = mid;
                }
            }
            return Math.round(output * 100.0) / 100.0; //round low to 2 decimal
//            return output;
        }

        private boolean isPossible(int[] stations, int k, double mid) {
            int stationAdded = 0;

            for (int i = 1; i < stations.length; i++) {

                stationAdded += (int) ((stations[i] - stations[i - 1]) / mid);

                if (stationAdded > k)
                    return false;
            }
            return stationAdded <= k;
        }
    }

}
