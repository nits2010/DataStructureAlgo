package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1346;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 12/1/2024
 * Question Title: 1346. Check If N and Its Double Exist
 * Link: https://leetcode.com/problems/check-if-n-and-its-double-exist/description/
 * Description: Given an array arr of integers, check if there exist two indices i and j such that :
 *
 * i != j
 * 0 <= i, j < arr.length
 * arr[i] == 2 * arr[j]
 *
 *
 * Example 1:
 *
 * Input: arr = [10,2,5,3]
 * Output: true
 * Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
 * Example 2:
 *
 * Input: arr = [3,1,7,11]
 * Output: false
 * Explanation: There is no i and j that satisfy the conditions.
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 500
 * -103 <= arr[i] <= 103
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 *
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CheckIfNAndItsDoubleExist_1346 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{10, 2, 5, 3}, true));
        tests.add(test(new int[]{3, 1, 7, 11}, false));
        tests.add(test(new int[]{7,1,14,11}, true));
        tests.add(test(new int[]{-2,0,10,-19,4,6,-8}, false));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] arr, boolean expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"arr", "Expected"}, true, arr, expected);

        boolean output = false;
        boolean pass, finalPass = true;

        Solution_Frequency solutionFrequency = new Solution_Frequency();
        output = solutionFrequency.checkIfExist(arr);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"Frequency", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_SetDoubleCheck solutionSetDobuleCheck = new Solution_SetDoubleCheck();
        output = solutionSetDobuleCheck.checkIfExist(arr);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"DoubleCheck-Set", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        Solution_SetHalfCheck solutionSetHalfCheck = new Solution_SetHalfCheck();
        output = solutionSetHalfCheck.checkIfExist(arr);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"HalfCheck-Set", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_Frequency {
        public boolean checkIfExist(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int j : arr) {
                map.put(j, map.getOrDefault(j, 0) + 1);
                if (j == 0) {
                    if (map.get(j) > 1)
                        return true;
                }
            }

            for (int j : arr) {
                if (j != 0 && map.containsKey(2 * j)) {
                    return true;

                }

            }

            return false;
        }
    }

    static  class Solution_SetDoubleCheck {
        public boolean checkIfExist(int[] arr) {
            Set<Integer> set = new HashSet<>();

            for (int j : arr) {
                if (j == 0) {
                    if (set.contains(j))
                        return true;
                } else if (set.contains(2 * j)) {
                    return true;

                }
                set.add(j);
            }

            for (int j : arr) {
                if (j != 0 && set.contains(2 * j)) {
                    return true;

                }

            }

            return false;
        }
    }


    static class Solution_SetHalfCheck {
        public boolean checkIfExist(int[] arr) {
            Set<Integer> set = new HashSet<>();

            for (int j : arr) {
                if (set.contains(2 * j) || (j % 2 == 0 && set.contains(j / 2)))
                    return true;
                set.add(j);
            }

            return false;
        }
    }

}
