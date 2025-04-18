package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._80;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/15/2025
 * Question Title:
 * Link:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.List._82.RemoveDuplicatesFromSortedListII_82}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @TwoPointers <p><p>
 * Company Tags
 * -----
 * @Baidu
 * @Bloomberg
 * @Facebook
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class RemoveDuplicatesFromSortedArrayII_80 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 1, 1, 2, 2, 3}, new int[]{1, 1, 2, 2, 3}, 5));
        tests.add(test(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 3, 3}, 7));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int[] expectedArray, int expected) {
        CommonMethods.printTestOutcome(new String[]{"Grid", "Expected", "ExpectedArray"}, true, grid, expected, expectedArray);

        int output = 0;
        int[] outputArray;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        outputArray = Arrays.copyOf(grid, grid.length);
        output = solution.removeDuplicates(outputArray);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        int i = 0;
        while (i < output) {
            pass &= outputArray[i] == expectedArray[i];
            i++;
        }
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "OutputArray", "Pass"}, false, output, Arrays.copyOf(outputArray, output), pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            //REMOVE AND SKIP AT THE LENGTH OF 2
            int k = 0; //the final length of the result array

            for (int n : nums) {

                //if we have less than 2 elements in the result array, then accept the current element since we can keep 2 duplicate chars at max
                // or if the current character is different then last 2 elements (last two elements can be same) then accept the current character
                if (k < 2 || n != nums[k - 2]) {

                    nums[k++] = n;
                }
            }
            return k;
        }
    }

    static class Solution_Generic {
        public int removeDuplicates(int[] nums, int atMost) {
            //REMOVE AND SKIP AT THE LENGTH OF 2
            int k = 0; //the final length of the result array

            for (int n : nums) {

                //if we have less than 2 elements in the result array, then accept the current element since we can keep 2 duplicate chars at max
                // or if the current character is different then last 2 elements (last two elements can be same) then accept the current character
                if (k < atMost || n != nums[k - atMost]) {

                    nums[k++] = n;
                }
            }
            return k;
        }
    }
}
