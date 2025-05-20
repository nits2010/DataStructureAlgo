package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._852;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/20/2025
 * Question Title: 852. Peak Index in a Mountain Array
 * Link: https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
 * Description: You are given an integer mountain array arr of length n where the values increase to a peak element and then decrease.
 * <p>
 * Return the index of the peak element.
 * <p>
 * Your task is to solve it in O(log(n)) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [0,1,0]
 * <p>
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [0,2,1,0]
 * <p>
 * Output: 1
 * <p>
 * Example 3:
 * <p>
 * Input: arr = [0,10,5,2]
 * <p>
 * Output: 1
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= arr.length <= 105
 * 0 <= arr[i] <= 106
 * arr is guaranteed to be a mountain array.
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
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Apple
 * @Google
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class PeakIndexInAMountainArray_852 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{0, 1, 0}, 1));
        tests.add(test(new int[]{0, 2, 1, 0}, 1));
        tests.add(test(new int[]{0, 10, 5, 2}, 1));
        tests.add(test(new int[]{3, 4, 5, 1}, 2));


        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionV1().peakIndexInMountainArray(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SolutionV1", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionV2().peakIndexInMountainArray(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"SolutionV2", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class SolutionV1 {
        public int peakIndexInMountainArray(int[] arr) {
            int n = arr.length;

            int low = 1, high = n - 2;
            int mid;

            if (low == high) //if only 3 element, then mid is the pivot/peek index
                return low;

            while (low <= high) {

                mid = (low + high) >>> 1;

                //if mid is the peek element
                if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
                    return mid;

                //means mid+1, mid is in increasing part which is the left part, pivot must lie on right side
                if (arr[mid + 1] > arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return -1;
        }
    }

    static class SolutionV2 {
        public int peakIndexInMountainArray(int[] arr) {
            int n = arr.length;

            int low = 0, high = n - 1;
            int mid;

            while (low < high) {

                mid = (low + high) >>> 1;

                //means mid+1, mid is in increasing part which is the left part, pivot must lie on right side
                if (arr[mid + 1] > arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid; //as mid could be the peek element
                }
            }

            return low;
        }
    }
}
