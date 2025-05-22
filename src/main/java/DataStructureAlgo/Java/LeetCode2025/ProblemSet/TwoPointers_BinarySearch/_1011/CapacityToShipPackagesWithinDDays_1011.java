package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1011;

import DataStructureAlgo.Java.LeetCode.MinCapacityOfShipMinimumNumberPagesStudent;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.HouseRobber._2560.HouseRobberIV_2560;
import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date:24/10/24
 * Question Category: 1011. Capacity To Ship Packages Within D Days
 * Description: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * <p>
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 * <p>
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 * <p>
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= days <= weights.length <= 5 * 104
 * 1 <= weights[i] <= 500
 * File reference
 * -----------
 * Duplicate {@link MinCapacityOfShipMinimumNumberPagesStudent}
 * Similar {@link HouseRobberIV_2560}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BinarySearch <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class CapacityToShipPackagesWithinDDays_1011 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5, 15);
        test &= test(new int[]{3, 2, 2, 4, 1, 4}, 3, 6);
        test &= test(new int[]{1, 2, 3, 1, 1}, 4, 3);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(int[] weights, int days, int expected) {
        CommonMethods.printTest(new String[]{"Weights", "Days", "Expected"}, true, weights, days, expected);
        int output;
        boolean pass, finalPass = true;

        Solution_ZeroSumBS solutionZeroSumBS = new Solution_ZeroSumBS();
        output = solutionZeroSumBS.shipWithinDays(weights, days);
        pass = output == expected;
        CommonMethods.printTest(new String[]{"Binary Search - ZeroSumBS", "Pass"}, false, output, pass ? "Pass" : "Fail");
        finalPass &= pass;


        Solution_MinSumBS solutionMinSumBS = new Solution_MinSumBS();
        output = solutionMinSumBS.shipWithinDays(weights, days);
        pass = output == expected;
        CommonMethods.printTest(new String[]{"Binary Search- MinSumBS", "Pass"}, false, output, pass ? "Pass" : "Fail");
        finalPass &= pass;


        Solution_MaxSumBS solutionMaxSumBS = new Solution_MaxSumBS();
        output = solutionMaxSumBS.shipWithinDays(weights, days);
        pass = output == expected;
        CommonMethods.printTest(new String[]{"Binary Search - MaxSumBS", "Pass"}, false, output, pass ? "Pass" : "Fail");
        finalPass &= pass;

        return finalPass;

    }


    static class Solution_ZeroSumBS {
        private int sum(int[] weights) {
            int sum = 0;
            for (int weight : weights) sum += weight;

            return sum;
        }

        public int shipWithinDays(int[] weights, int D) {

            int low = 0;
            int high = sum(weights);
            int capacity = Integer.MAX_VALUE;

            while (low <= high) {

                int thisCapacity = (low + high) >> 1;

                if (isPossible(weights, D, thisCapacity)) {
                    capacity = thisCapacity;
                    high = thisCapacity - 1;
                } else
                    low = thisCapacity + 1;

            }
            return capacity;

        }


        private boolean isPossible(int weights[], int d, int capacity) {

            int requiredDays = 1;
            int currentWeight = 0;

            for (int weight : weights) {

                //if this weight itself bigger than my capacity, then i can't take it with me ..sorry :(
                if (weight > capacity)
                    return false;

                //is it possible really?
                if (currentWeight + weight <= capacity) {
                    currentWeight += weight;
                } else {

                    requiredDays++;
                    currentWeight = weight;
                }
                if (requiredDays > d)
                    return false;

            }

            return requiredDays <= d;

        }
    }


    static class Solution_MinSumBS {
        public int shipWithinDays(int[] weights, int days) {

            int low = weights[0];
            int high = 0;

            for (int ele : weights) {
                low = Math.min(low, ele);
                high += ele;
            }
            int minCapacity = 0;

            while (low <= high) {
                int capacity = low + (high - low) / 2;

                if (isPossible(weights, days, capacity)) {
                    minCapacity = capacity;
                    high = capacity - 1;
                } else
                    low = capacity + 1;
            }

            return minCapacity;
        }

        private boolean isPossible(int weights[], int d, int capacity) {

            int requiredDays = 1;
            int currentWeight = 0;

            for (int weight : weights) {

                //if this weights itself bigger than my capacity, then i can't take it with me ..sorry :(
                if (weight > capacity)
                    return false;

                //is it possible really?
                if (currentWeight + weight <= capacity) {
                    currentWeight += weight;
                } else {

                    requiredDays++;
                    currentWeight = weight;
                }
                if (requiredDays > d)
                    return false;

            }

            return requiredDays <= d;

        }

    }


    static class Solution_MaxSumBS {
        public int shipWithinDays(int[] weights, int days) {

            int low = weights[0];
            int high = 0;

            for (int ele : weights) {
                low = Math.max(low, ele);
                high += ele;
            }
            int minCapacity = 0;

            while (low <= high) {
                int capacity = low + (high - low) / 2;

                if (isPossible(weights, days, capacity)) {
                    minCapacity = capacity;
                    high = capacity - 1;
                } else
                    low = capacity + 1;
            }

            return minCapacity;
        }

        private boolean isPossible(int weights[], int d, int capacity) {

            int requiredDays = 1;
            int currentWeight = 0;

            for (int weight : weights) {

                //if this weight itself bigger than my capacity, then i can't take it with me ..sorry :(
                if (weight > capacity)
                    return false;

                //is it possible really?
                if (currentWeight + weight <= capacity) {
                    currentWeight += weight;
                } else {

                    requiredDays++;
                    currentWeight = weight;
                }
                if (requiredDays > d)
                    return false;

            }

            return requiredDays <= d;

        }

    }


}
