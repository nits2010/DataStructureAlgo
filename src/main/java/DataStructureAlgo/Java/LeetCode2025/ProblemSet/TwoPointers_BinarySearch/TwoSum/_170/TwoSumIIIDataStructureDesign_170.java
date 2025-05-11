package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.TwoSum._170;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/26/2025
 * Question Title: 170. Two Sum III - Data structure design
 * Link: https://leetcode.com/problems/two-sum-iii-data-structure-design/description/
 * https://leetcode.ca/all/170.html
 * Description: Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.
 * <p>
 * Implement the TwoSum class:
 * <p>
 * TwoSum() Initializes the TwoSum object, with an empty array initially.
 * void add(int number) Adds number to the data structure.
 * boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * Output
 * [null, null, null, null, true, false]
 * <p>
 * Explanation
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --> [1]
 * twoSum.add(3);   // [1] --> [1,3]
 * twoSum.add(5);   // [1,3] --> [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4, return true
 * twoSum.find(7);  // No two integers sum up to 7, return false
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
 * @Design
 * @HashTable
 * @TwoPointers
 * @PremimumQuestion
 * @LockedProblem <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @LinkedIn <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class TwoSumIIIDataStructureDesign_170 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new String[]{"TwoSum", "add", "add", "add", "find", "find"},
                new Integer[]{null, 1, 3, 5, 4, 7},
                new Boolean[]{null, null, null, null, true, false}));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String[] operation, Integer[] input, Boolean[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"operation", "Input", "Expected"}, true, operation, input, expected);

        Boolean[] output;
        boolean pass, finalPass = true;

        TwoSum twoSum = new TwoSum();
        output = execute(operation, twoSum, input);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    private static Boolean[] execute(String[] operations, TwoSum twoSum, Integer[] input) {
        Boolean[] output = new Boolean[input.length];

        int r = 0;
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            if (operation.equals("add")) {
                twoSum.add(input[i]);
                output[i] = null;
            } else if(operation.equals("find")) {
                output[i] = twoSum.find(input[i]);
            }
        }

        return output;
    }

    static public class TwoSum {

        Map<Integer, Integer> freqMap = new HashMap<>();

        public void add(int number) {
            freqMap.put(number, freqMap.getOrDefault(number, 0) + 1);
        }

        public boolean find(int target) {
            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {

                int num = entry.getKey();

                int diff = target - num;

                //add(3), add(3), target=6
                if (freqMap.containsKey(diff)) {
                    if (diff != num || entry.getValue() > 1)
                        return true;
                }
//                if (diff == num && entry.getValue() > 1)
//                    return true;
//
//                if (freqMap.containsKey(diff))
//                    return true;
            }
            return false;
        }
    }
}
