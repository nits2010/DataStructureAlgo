package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._278;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/19/2025
 * Question Title: 278. First Bad Version
 * Link: https://leetcode.com/problems/first-bad-version/description/
 * Description: You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 * <p>
 * Input: n = 1, bad = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= bad <= n <= 231 - 1
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.FirstBadVersion}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @BinarySearch
 * @Interactive <p><p>
 * Company Tags
 * -----
 * @Google
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Apple <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FirstBadVersion_278 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(5, 4));
        tests.add(test(2, 2));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int n, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"n", "Expected"}, true, n, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_().firstBadVersion(n);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BSV1", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new Solution().firstBadVersion(n);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"BSV2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class VersionControl {

        boolean isBadVersion(int version) {
            return false; //TODO implement
        }
    }

    static class Solution_ extends VersionControl {
        public int firstBadVersion(int n) {
            int low = 1, high = n;
            int mid = 1;
            int badVersion = -1;

            while (low <= high) {

                mid = (low + high) >>> 1;

                if (isBadVersion(mid)) {
                    badVersion = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return badVersion;
        }
    }


    /**
     * n = 2
     * [1,2]
     * low = 1, high = 2 ⇒ mid = 1 -> low = mid + 1 = 2
     * low = 2, high = 2 => break; return low
     * <p>
     * n = 4, bad = 3
     * low = 1, high = 4 => mid = 2 -> low = mid + 1 = 3
     * low = 3, high = 4 => mid = 3 -> high = mid = 3
     * low = 3, high = 3 => break ; return low
     * <p>
     * n = 7 , bad = 6
     * low = 1, high = 7 => mid = 4 -> low = mid + 1 = 5
     * low = 5 high = 7 => mid = 6 -> high = mid = 6
     * low = 5, high = 6 => mid = 5 -> low = mid + 1 = 6
     * low = 6, high = 6 => break, return low
     * <p>
     * n = 7 , bad = 3
     * low = 1, high = 7 => mid = 4 -> high = mid = 4
     * low = 1 high = 4 => mid = 2 -> low = mid + 1 =  3
     * low = 3, high = 4 => mid = 3 -> high = mid = 3
     * low = 3, high = 3 => break, return low=3
     */
    static class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int low = 1, high = n;
            int mid;

            while (low < high) {

                mid = (low + high) >>> 1;

                if (isBadVersion(mid)) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            return low;
        }
    }
}
