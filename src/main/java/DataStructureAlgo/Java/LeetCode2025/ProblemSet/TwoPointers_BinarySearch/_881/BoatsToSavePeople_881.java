package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._881;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/4/2025
 * Question Title: 881. Boats to Save People
 * Link: https://leetcode.com/problems/boats-to-save-people/description/
 * Description: You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
 * <p>
 * Return the minimum number of boats to carry every given person.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 * Example 2:
 * <p>
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 * Example 3:
 * <p>
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 * Explanation: 4 boats (3), (3), (4), (5)
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= people.length <= 5 * 104
 * 1 <= people[i] <= limit <= 3 * 104
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
 * @Greedy
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Salesforce
 * @Amazon
 * @Intuit
 * @Microsoft <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class BoatsToSavePeople_881 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2}, 3, 1));
        tests.add(test(new int[]{3, 2, 2, 1}, 3, 3));
        tests.add(test(new int[]{3, 5, 3, 4}, 5, 4));
        tests.add(test(new int[]{5, 1, 4, 2}, 6, 2));
        tests.add(test(new int[]{3, 5, 3, 4}, 8, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] people, int limit, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"People", "limit", "Expected"}, true, people, limit, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.numRescueBoats(people, limit);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int numRescueBoats(int[] people, int limit) {
            if (people == null || limit == 0)
                return 0;

            Arrays.sort(people);
            int boats = 0;
            int i = 0;
            int n = people.length;
            int j = n - 1;

            while (i <= j) {

                int wSum = people[i] + people[j];
                if (wSum <= limit) {
                    boats++; //increasing boats count since we are making i and j to sit in this boat and atmost 2 people can sit in the boat
                    j--;
                    i++;
                } else {
                    boats++; // j will alone sit on this boat
                    j--;
                }

            }
            return boats;

        }
    }
}
