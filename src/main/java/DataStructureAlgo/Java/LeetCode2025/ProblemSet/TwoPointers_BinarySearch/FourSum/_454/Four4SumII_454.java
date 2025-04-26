package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch.FourSum._454;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/26/2025
 * Question Title: 454. 4Sum II
 * Link: https://leetcode.com/problems/4sum-ii/description/
 * Description: Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
 * <p>
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * Example 2:
 * <p>
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.pair.element.problems.fourSum.FourSum4SumII}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @BinarySearch <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @Adobe
 * @Bloomberg
 * @Apple <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class Four4SumII_454 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}, 2));
        tests.add(test(new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}, 1));
        tests.add(test(new int[]{-1, -1}, new int[]{-1, 1}, new int[]{-1, 1}, new int[]{1, -10}, 6));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums1, int[] nums2, int[] nums3, int[] nums4, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"nums1", "nums2", "nums3", "nums4", "Expected"}, true, nums1, nums2, nums3, nums4, expected);

        int output = 0;
        boolean pass, finalPass = true;

        HashMapSolution.Solution_WithoutSort hashMapSolutionWithoutSort = new HashMapSolution.Solution_WithoutSort();
        output = hashMapSolutionWithoutSort.fourSumCount(nums1, nums2, nums3, nums4);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"HashMap - without Sort", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        HashMapSolution.Solution_WithSort hashMapSolutionWithSort = new HashMapSolution.Solution_WithSort();
        output = hashMapSolutionWithSort.fourSumCount(nums1, nums2, nums3, nums4);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"HashMap - with Sort", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_BinarySearch solutionBinarySearch = new Solution_BinarySearch();
        output = solutionBinarySearch.fourSumCount(nums1, nums2, nums3, nums4);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"BinarySearch", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

    }

    //O(n^2)/O(n^2)
    static class HashMapSolution {

        static class Solution_WithoutSort {
            public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
                final Map<Integer, Integer> sumMap = new HashMap<>();

                //all arrays are of equal length, find a sum of each pair b/w 1st and 2nd array
                for (int n1 : nums1) {
                    for (int n2 : nums2) {
                        int sum = n1 + n2;
//                        sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
                        sumMap.merge(sum, 1, Integer::sum);
                    }
                }

                int count = 0;

                //find the diff map
                for (int n3 : nums3) {
                    for (int n4 : nums4) {
                        int diff = -(n3 + n4);

                        count += sumMap.getOrDefault(diff, 0);

                    }
                }

                return count;
            }
        }


        static class Solution_WithSort {
            public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
                final Map<Integer, Integer> sumMap = new HashMap<>();
                Arrays.sort(nums1);
                Arrays.sort(nums2);
                Arrays.sort(nums3);
                Arrays.sort(nums4);
                //all arrays are of equal length, find a sum of each pair b/w 1st and 2nd array
                for (int n1 : nums1) {
                    for (int n2 : nums2) {
                        int sum = n1 + n2;
                        // sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
                        sumMap.merge(sum, 1, Integer::sum);
                    }
                }

                int count = 0;

                //find the diff map
                for (int n3 : nums3) {
                    for (int n4 : nums4) {
                        int diff = -(n3 + n4);

                        count += sumMap.getOrDefault(diff, 0);

                    }
                }

                return count;
            }
        }
    }

    //O(n^2 * log(n^2)) / O(n^2 )
    static class Solution_BinarySearch {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            int n = nums1.length;

            final int[] sumPairs = new int[n * n];
            final int[] diffPairs = new int[n * n];

            //find sum and diff pairs
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    sumPairs[i * n + j] = nums1[i] + nums2[j];
                    diffPairs[i * n + j] = -(nums3[i] + nums4[j]);
                }
            }

            //now for every sumPair, we need to find the diffPair using binarySearch
            //a+b+c+d = 0 => a+b = -(c+d)

            //O(n^2 * logn^2)
            Arrays.sort(sumPairs);
            Arrays.sort(diffPairs);
            int count = 0;

            for (int sumPair : sumPairs) {

                int countOfDiffPair = binarySearch(diffPairs, sumPair);
                if (countOfDiffPair != -1) //means we found pairs
                    count += countOfDiffPair;

            }

            return count;
        }

        private int binarySearch(int[] diffPairs, int element) {

            //find the first occurrence indexes
            int first = binarySearchFirstIndex(diffPairs, element);
            if (first == -1)
                return -1;

            //find the last occurrence indexes
            int last = binarySearchLastIndex(diffPairs, element);

            return last - first + 1;
        }

        private int binarySearchFirstIndex(int[] diffPairs, int element) {

            int first = Arrays.binarySearch(diffPairs, element);
            if (first < 0) {
                return -1;
            }

            //inbuilt binary search could return last index
            while (first > 0 && diffPairs[first - 1] == element) {
                first--;
            }
            return first;
        }

        private int binarySearchLastIndex(int[] diffPairs, int element) {

            int last = Arrays.binarySearch(diffPairs, element);
            if (last < 0) {
                return -1;
            }

            //inbuilt binary search could return last index
            while (last < diffPairs.length - 1 && diffPairs[last + 1] == element) {
                last++;
            }
            return last;
        }
    }
}


