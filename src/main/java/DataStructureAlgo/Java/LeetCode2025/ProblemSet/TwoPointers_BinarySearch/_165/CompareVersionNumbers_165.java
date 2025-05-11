package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._165;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 165. Compare Version Numbers
 * Link: https://leetcode.com/problems/compare-version-numbers/description/
 * Description: Given two version strings, version1 and version2, compare them. A version string consists of revisions separated by dots '.'. The value of the revision is its integer conversion ignoring leading zeros.
 * <p>
 * To compare version strings, compare their revision values in left-to-right order. If one of the version strings has fewer revisions, treat the missing revision values as 0.
 * <p>
 * Return the following:
 * <p>
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: version1 = "1.2", version2 = "1.10"
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * <p>
 * version1's second revision is "2" and version2's second revision is "10": 2 < 10, so version1 < version2.
 * <p>
 * Example 2:
 * <p>
 * Input: version1 = "1.01", version2 = "1.001"
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 * <p>
 * Example 3:
 * <p>
 * Input: version1 = "1.0", version2 = "1.0.0.0"
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * version1 has less revisions, which means every missing revision are treated as "0".
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= version1.length, version2.length <= 500
 * version1 and version2 only contain digits and '.'.
 * version1 and version2 are valid version numbers.
 * All the given revisions in version1 and version2 can be stored in a 32-bit integer.
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
 * @TwoPointers
 * @String <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Microsoft
 * @AristaNetworks
 * @Nutanix
 * @Apple
 * @Google
 * @Microsoft
 * @Square<p> -
 * ----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class CompareVersionNumbers_165 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("1.2", "1.10", -1));
        tests.add(test("1.01", "1.001", 0));
        tests.add(test("1.0", "1.0.0.0", 0));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String version1, String version2, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"version1", "version2", "Expected"}, true, version1, version2, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.compareVersion(version1, version2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution {
        public int compareVersion(String version1, String version2) {
            int i = 0, j = 0;
            String[] v1 = version1.split("\\.");
            String[] v2 = version2.split("\\.");

            while (i < v1.length || j < v2.length) {

                int x = (i < v1.length) ? Integer.parseInt(v1[i]) : 0;
                int y = (j < v2.length) ? Integer.parseInt(v2[j]) : 0;

                if (x == y) {
                    i++;
                    j++;
                } else {
                    return x - y < 0 ? -1 : 1;
                }

            }
            return 0;
        }
    }
}
