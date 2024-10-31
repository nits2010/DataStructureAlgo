package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.DynamicProgramming.baseProblems.subsequence._300;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Trees._100.SameTree_100;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/30/2024
 * Question Category: 300. Longest Increasing Subsequence
 * Description: https://leetcode.com/problems/longest-increasing-subsequence/description/
 * Given an integer array nums, return the length of the longest strictly increasing subsequence
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.nonleetcode.LongestIncreasingSubSequence}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @Array
 * @BinarySearch
 * @DynamicProgramming <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Amazon
 * @Apple
 * @Atlassian
 * @Facebook
 * @Google
 * @Microsoft
 * @Oracle
 * @Salesforce
 * @Uber
 * @Visa
 * @VMware <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSoltuion
 */
public class LongestIncreasingSubsequence_300 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 4);
        test &= test(new int[]{0, 1, 0, 3, 2, 3}, 4);
        test &= test(new int[]{7, 7, 7, 7, 7, 7, 7}, 1);
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] nums, int expected) {
        CommonMethods.print(new String[]{"Nums", "Expected"}, true, nums, expected);

        int output;
        boolean pass, finalPass = true;

        SolutionPoly solutionPoly = new SolutionPoly();
        output = solutionPoly.lengthOfLIS(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionBinarySearch solutionBinarySearch = new SolutionBinarySearch();
        output = solutionBinarySearch.lengthOfLIS(nums);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.print(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");
        return finalPass;
    }

    static class SolutionPoly {

        public int lengthOfLIS(int[] nums) {
            int n = nums.length;

            //lis represent the length of longest increasing subsequence ending at `i` as lis[i]
            int[] lis = new int[n];

            // At any moment of index, there are two possibilities
            // either we extend the existing subsequence or create a new subsequence of length 1 start from `i`.
            // To extend the existing subsequence, we need to find a j < i such that nums[j] < nums[i], the new LIS at `i` with respect to this `j` would be
            // 1 + lis[j] hence this lis[i] can be defined as
            // LIS[i] = Math.max(LIS[i], 1 + LIS[j]) if nums[i] > nums[j] for all 0<= j < i

            lis[0] = 1;
            int max = 1;
            for (int i = 1; i < n; i++) {
                lis[i] = 1; //every element form a 1 length lis

                //find all j < i such that nums[j] < nums[i]
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] > nums[j])
                        lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
                max = Math.max(max, lis[i]);
            }

            return max;

        }
    }

    static class SolutionBinarySearch {

        public int lengthOfLIS(int[] nums) {
            int n = nums.length;

            //LIS represent the longest increase subsequence (unordered). The length of this lis is the longest increasing subsequence
            //it holds elements in increasing order
            int[] lis = new int[n];

            //represent the length of lis
            int lisLength = 0;

            lis[lisLength++] = nums[0]; // starting a new subsequence from the first element.

            for (int i = 1; i < n; i++) {

                //if the first element of our lis is greater than the current element, then this
                // new element can form a new subsequence
                if (lis[0] > nums[i]) {
                    lis[0] = nums[i];
                } else if (lis[lisLength - 1] < nums[i]) {
                    //if we can extend the existing LIS to greater length
                    lis[lisLength++] = nums[i];
                } else {
                    //find the insertion point of this element.
                    //insertion point would be the index of an element which is greater than this element
                    //and is the smallest among all the elements in the list

//                    int index = Arrays.binarySearch(lis, 0, lisLength, nums[i]);
//                    if (index < 0)
//                        index = -index - 1;

                    int index = binarySearchUpperBound(lis, 0, lisLength - 1, nums[i]);

                    lis[index] = nums[i];

                }
            }

            return lisLength;

        }

        private int binarySearchUpperBound(int[] nums, int left, int right, int target) {

            int ceilIndex = -1;
            while (left <= right) {

                int mid = (left + right) >>> 1;

                if (nums[mid] >= target) {
                    ceilIndex = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
            return ceilIndex;
        }

        private int binarySearchUpperBound2(int[] nums, int left, int right, int target) {

            while (left <= right) {

                int mid = (left + right) >>> 1;

                if (nums[mid] >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
            return left;
        }
    }
}
