package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1385;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 1385. Find the Distance Value Between Two Arrays
 * Link: https://leetcode.com/problems/find-the-distance-value-between-two-arrays/description/
 * Description: Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.
 * <p>
 * The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
 * Output: 2
 * Explanation:
 * For arr1[0]=4 we have:
 * |4-10|=6 > d=2
 * |4-9|=5 > d=2
 * |4-1|=3 > d=2
 * |4-8|=4 > d=2
 * For arr1[1]=5 we have:
 * |5-10|=5 > d=2
 * |5-9|=4 > d=2
 * |5-1|=4 > d=2
 * |5-8|=3 > d=2
 * For arr1[2]=8 we have:
 * |8-10|=2 <= d=2
 * |8-9|=1 <= d=2
 * |8-1|=7 > d=2
 * |8-8|=0 <= d=2
 * Example 2:
 * <p>
 * Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
 * Output: 2
 * Example 3:
 * <p>
 * Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr1.length, arr2.length <= 500
 * -1000 <= arr1[i], arr2[j] <= 1000
 * 0 <= d <= 100
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
 * @easy
 * @Array
 * @TwoPointers
 * @BinarySearch
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Uber <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindTheDistanceValueBetweenTwoArrays_1385 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{4, 5, 8}, new int[]{10, 9, 1, 8}, 2, 2));
        tests.add(test(new int[]{1, 4, 2, 3}, new int[]{-4, -3, 6, 10, 20, 30}, 3, 2));
        tests.add(test(new int[]{2, 1, 100, 3}, new int[]{-5, -2, 10, -3, 7}, 6, 1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] arr1, int[] arr2, int d, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"arr1", "arr2", "d", "Expected"}, true, arr1, arr2, d, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.findTheDistanceValue(arr1, arr2, d);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    /**
     * Time: O(mlogm + n * log(m)) => O(mlogm + nlogm)
     * Space : O(1)
     */
    static class Solution {
        public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

            Arrays.sort(arr2); // so that we can apply BS on arr2 for every arr[i] - d and arr[i] + d range, to cover both side

            int result = 0;
            for (int val : arr1) {

                if (inRange(arr2, val - d, val + d)) //|arr1[i]-arr2[j]| <= d which is => "arr1[i] - d <= arr[2] <= arr[i] + d"
                    result++;
            }

            return result;

        }

        boolean inRange(int[] arr, int from, int to) {
            int low = 0, high = arr.length - 1;

            while (low <= high) {
                int mid = (low + high) >>> 1;

                //check if a mid-element is in the range
                int ele = arr[mid];

                if (ele >= from && ele <= to)
                    // here '=' is because |arr1[i]-arr2[j]| <= d which is => "arr1[i] - d <= arr[2] <= arr[i] + d" if that is then  it's not in range
                    return false;

                //if that element is in the range, then search for either half which is not
                if (ele < from)
                    low = mid + 1; //means all elements before mid are in range , check for another half
                else
                    high = mid - 1;

            }
            return true;
        }
    }
}
