package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1539;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/30/2025
 * Question Title: 1539. Kth Missing Positive Number
 * Link: https://leetcode.com/problems/kth-missing-positive-number/description/
 * Description: Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * <p>
 * Return the kth positive integer that is missing from this array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 * 1 <= k <= 1000
 * arr[i] < arr[j] for 1 <= i < j <= arr.length
 * <p>
 * <p>
 * Follow up:
 * <p>
 * Could you solve this problem in less than O(n) complexity?
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
 * @Array
 * @Binary Search
 * @easy <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Facebook
 * @tiktok
 * @Microsoft
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class KthMissingPositiveNumber_1539 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        //add logic here
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    /**
     * Since an array has only +ve numbers sorted in strictly increasing order, which means that numbers must be started
     * from 1 and end to +ve Inf max.
     * All number from [1,+inf] are considered as missing elements if not present in the array. We can count them and get the kth one
     * T/s : O(n) / O(1)
     */
    class Solution_Counting {
        public int findKthPositive(int[] arr, int k) {
            int count = 0;
            int num = 1;

            int i = 0;
            while (i < arr.length) {

                if (num != arr[i]) {
                    count++;
                } else {
                    i++;
                }

                if (count == k)
                    return num;
                num++;
            }
            while (count != k - 1) {
                count++;
                num++;
            }
            return num;

        }
    }

    //binary search
    class Solution_BinarySearch {
        public int findKthPositive(int[] arr, int k) {
            int n = arr.length;

            int low = 0, high = n - 1; //represents the index within an array

            while (low <= high) {

                int mid = (low + high) >>> 1;

                // Find at index 'mid', nums[mid] is at correct position or not ?
                // if its at correct position then nums[mid] = i+1, however, if it's not than its nums[mid] - (i+1) away from its original
                // position. This also denotes that at the 'mid' index, how many elements are missing.
                int missingElementsSoFar = arr[mid] - (mid + 1);

                if (missingElementsSoFar < k) {
                    //we need more missing elements, increase range
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            //low would at least k the missing number,
            return low + k;
        }
    }

}
