package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._532;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/13/2025
 * Question Title: 532. K-diff Pairs in an Array
 * Link: https://leetcode.com/problems/k-diff-pairs-in-an-array/description/
 * Description: Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * <p>
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * Example 3:
 * <p>
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -107 <= nums[i] <= 107
 * 0 <= k <= 107
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers._1.TwoSum_1}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @TwoPointers
 * @BinarySearch
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Oracle
 * @Paypal
 * @Salesforce
 * @Twilio
 * @Twitter <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link Solution_TwoPointer}
 */

public class KDiffPairsInAnArray_532 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{3, 1, 4, 1, 5}, 2, 2));
        tests.add(test(new int[]{1, 2, 3, 4, 5}, 1, 4));
        tests.add(test(new int[]{1, 3, 1, 5, 4}, 0, 1));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] grid, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Grid", "k", "Expected"}, true, grid, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_Map_Set solutionMapSet = new Solution_Map_Set();
        Solution_FreqMap solutionFreqMap = new Solution_FreqMap();
        Solution_BinarySearch_Set solutionBinarySearchSet = new Solution_BinarySearch_Set();
        Solution_TwoPointer solutionTwoPointer = new Solution_TwoPointer();

        output = solutionMapSet.findPairs(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"solutionMapSet", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionFreqMap.findPairs(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"solutionFreqMap", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionBinarySearchSet.findPairs(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"solutionBinarySearchSet", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = solutionTwoPointer.findPairs(grid, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"solutionTwoPointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    static class Solution_Map_Set {
        public int findPairs(int[] nums, int k) {
            int n = nums.length;
            if (n == 0)
                return 0;
            Set<String> set = new HashSet<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++)
                map.put(nums[i], i); //store all the number and index, index to make sure that we don't count same number again

            for (int i = 0; i < n; i++) {
                int j = map.getOrDefault(nums[i] + k, -1); // if candidate available

                if (j != -1 && i != j) {
                    set.add(pair(Math.min(nums[i], nums[j]), Math.max(nums[i], nums[j]))); //Pair it up
                }

            }

            return set.size();

        }

        String pair(int a, int b) {
            return a + "$" + b;
        }
    }

    static class Solution_FreqMap {
        public int findPairs(int[] nums, int k) {
            int n = nums.length;
            if (n == 0)
                return 0;

            Map<Integer, Integer> freqMap = new HashMap<>();
            //store number vs freq of that number,
            //frequency to make sure that if k=0 then same number frequency counted
            for (int num : nums)
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            int result = 0;

            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {

                int key = entry.getKey();

                // k > 0 if a target candidate exists in the map
                // k == 0, k = 0 is only possible when both elements are same and with the same sign only.
                // Means there should be more than 1 freq of the same element
                if ((k > 0 && freqMap.containsKey(key + k)) || (k == 0 && freqMap.get(key) > 1))
                    result++;

            }

            return result;

        }

    }

    static class Solution_BinarySearch_Set {
        public int findPairs(int[] nums, int k) {
            int n = nums.length;
            if (n == 0)
                return 0;

            Arrays.sort(nums);
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int cand = nums[i] + k;

                int j = binarySearch(nums, i + 1, cand); //array is sorted, search the candidate element post the current element

                if (j != -1)
                    set.add(nums[i]);

            }
            return set.size();

        }

        int binarySearch(int[] nums, int l, int target) {
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] == target)
                    return mid;
                if (nums[mid] < target)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            return -1;
        }

    }

    static class Solution_TwoPointer {
        public int findPairs(int[] nums, int k) {
            int n = nums.length;
            if (n == 0)
                return 0;

            Arrays.sort(nums);
            int result = 0;

            int i = 0, j = 1;

            while (j < n) {

                //get the diff
                int diff = Math.abs(nums[i] - nums[j]);

                if (diff == k) {
                    //candidate found at i and j
                    result++;
                    i++;
                    j++;

                    //skip duplicate elements
                    while (j < n && nums[j] == nums[j - 1])
                        j++;
                } else if (diff < k) {
                    //diff is smaller we need to increase it
                    j++;
                } else {
                    i++;
                    if (i == j)
                        j++;
                }
            }

            return result;

        }

    }

}
