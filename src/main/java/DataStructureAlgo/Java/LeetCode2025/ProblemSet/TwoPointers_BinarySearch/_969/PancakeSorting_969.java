package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._969;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/8/2025
 * Question Title: 969. Pancake Sorting
 * Link: https://leetcode.com/problems/pancake-sorting/description/
 * Description: Given an array of integers arr, sort the array by performing a series of pancake flips.
 *
 * In one pancake flip we do the following steps:
 *
 * Choose an integer k where 1 <= k <= arr.length.
 * Reverse the sub-array arr[0...k-1] (0-indexed).
 * For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.
 *
 * Return an array of the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: arr = [3, 2, 4, 1]
 * After 1st flip (k = 4): arr = [1, 4, 2, 3]
 * After 2nd flip (k = 2): arr = [4, 1, 2, 3]
 * After 3rd flip (k = 4): arr = [3, 2, 1, 4]
 * After 4th flip (k = 3): arr = [1, 2, 3, 4], which is sorted.
 * Example 2:
 *
 * Input: arr = [1,2,3]
 * Output: []
 * Explanation: The input is already sorted, so there is no need to flip anything.
 * Note that other answers, such as [3, 3], would also be accepted.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= arr.length
 * All integers in arr are unique (i.e. arr is a permutation of the integers from 1 to arr.length).
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @TwoPointers
 * @Greedy
 * @Sorting
 *
 * <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Microsoft
 * @Square
 * @Uber
 * <p>
 * -----
 *
 * @Editorial https://leetcode.com/problems/pancake-sorting/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class PancakeSorting_969 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 2, 4, 1}, 4, List.of(3,4,2,3,2)));
        tests.add(test(new int[]{1, 2, 3}, 3, List.of()));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int k, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Nums", "K", "Expected"}, true, nums, k, expected);

        List<Integer> output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.pancakeSort(nums);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class Solution {
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> res = new ArrayList<>();

            for (int x = A.length; x > 0; x--) {
                int index = find(A, x);

                //if that x is not on correct pos, then flips needed
                if (index != x - 1) {

                    //if element at 0th index, then first flip not needed otherwise needed
                    if (index != 0) {
                        res.add(index + 1); // index+1 because of 1 index
                        flip(A, index);

                    }

                    res.add(x);
                    flip(A, x - 1); // reverse entire array of unsorted

                }
            }
            return res;

        }

        private void flip(int[] a, int x) {
            int i = 0, j = x;
            while (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        private int find(int[] a, int x) {
            int i;
            for (i = 0; i < a.length && a[i] != x; i++)
                ;
            return i;
        }
    }
}
