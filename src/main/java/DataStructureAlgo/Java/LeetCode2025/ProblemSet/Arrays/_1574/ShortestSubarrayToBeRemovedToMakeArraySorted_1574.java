package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._1574;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 11/15/2024
 * Question Category: 1574. Shortest Subarray to be Removed to Make Array Sorted
 * Description: https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/description
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 * <p>
 * Return the length of the shortest subarray to remove.
 * <p>
 * A subarray is a contiguous subsequence of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 * Example 2:
 * <p>
 * Input: arr = [5,4,3,2,1]
 * Output: 4
 * Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
 * Example 3:
 * <p>
 * Input: arr = [1,2,3]
 * Output: 0
 * Explanation: The array is already non-decreasing. We do not need to remove any elements.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 105
 * 0 <= arr[i] <= 109
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
 * @BinarySearch
 * @Stack
 * @MonotonicStack <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class ShortestSubarrayToBeRemovedToMakeArraySorted_1574 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test(new int[]{1, 2, 3, 10, 4, 2, 3, 5}, 3));
        tests.add(test(new int[]{5, 4, 3, 2, 1}, 4));
        tests.add(test(new int[]{1, 2, 3}, 0));

        tests.add(test(new int[]{2, 2, 2, 1, 1, 1}, 3));
        tests.add(test(new int[]{1, 2, 3, 10, 0, 7, 8, 9}, 2));
        tests.add(test(new int[]{10, 13, 17, 21, 15, 15, 9, 17, 22, 22, 13}, 7));
        tests.add(test(new int[]{16, 10, 0, 3, 22, 1, 14, 7, 1, 12, 15}, 8));
        tests.add(test(new int[]{5, 4, 3, 2, 1, 2, 3, 4, 5, 6}, 4));


        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "Expected"}, true, nums, expected);

        int output;
        boolean pass, finalPass = true;
        Solution_TwoPointer solutionTwoPointer = new Solution_TwoPointer();

        output = solutionTwoPointer.findLengthOfShortestSubarray(nums);
        pass = output == expected;
        System.out.println("Pass: " + pass);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"TwoPointer", "Pass"}, false, output, pass ? "Pass" : "Fail");


        return finalPass;

    }

    static class Solution_TwoPointer {
        public int findLengthOfShortestSubarray(int[] arr) {
            int n = arr.length;
            int ans = n - 1;

            // get prefix increasing part
            int l = 0;
            while (l + 1 < n && arr[l] <= arr[l + 1])
                l++;

            if (l == n - 1)
                return ans = 0;

            // get suffix increasing part
            int r = n - 1;
            while (r > l && arr[r] >= arr[r - 1])
                r--;

            // two cases;

            // case 1: if arr is increasing first and then start decreasing, then all
            // element post l needs to remove
            // ex ; [1,2,3,4,5,4,3,2,1] n = 9
            // here l = 4 [index, value = 5]
            // and r = 8 [index=8, value = 1]
            // then we need to remove all elements post element 5 (index=4). i.e. [4,3,2,1],
            // size = 4
            // that is n - l - 1 = 9 - 4 - 1 = 4

            // case 2: when array increasing, then decreasing [there could be multi part]
            // and again increasing
            // ex: [5, 4, 3, 2, 1, 2, 3, 4, 5, 6] n = 10
            // here l = 0 [index=0, value = 5] {5}
            // and r = 4 [ index = 4, element = 1] {1, 2, 3, 4, 5, 6}
            // the element in between needs to be removed {5, 4, 3, 2}
            // 10 - 0 - 1 = 9 or r=4 -> r
            ans = Math.min(n - l - 1, r);


            // now the case, when the arr first increasing, then decreasing, then increasing, then decreasing..... then increasing
            // in such cases, we need to connect dots b/w l and r
                /*
                                                         8/
                                                        7/
                                                       6/
                      4 /              5 / --         5/
                   3 /   L  \3         4/      \3_2_ 4/
                2 /          \ 2 _2__ 3/         R
             1 /
              i                             j

              */
            int i = 0;
            while (i <= l && r < n) {

                if (arr[i] <= arr[r]) {
                    ans = Math.min(ans, r - i - 1);
                    i++;
                } else {
                    r++;
                }

            }

            return ans;

        }
    }
}
