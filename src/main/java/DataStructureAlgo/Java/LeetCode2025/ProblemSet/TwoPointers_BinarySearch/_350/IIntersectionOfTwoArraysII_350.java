package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._350;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/18/2025
 * Question Title: 350. Intersection of Two Arrays II
 * Link: https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 * Description: Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 * <p>
 * <p>
 * Follow up:
 * <p>
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
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
 * @HashTable
 * @TwoPointers
 * @BinarySearch
 * @Sorting <p><p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Yahoo
 * @Adobe
 * @Apple
 * @Bloomberg
 * @Databricks
 * @Google
 * @LinkedIn
 * @Microsoft
 * @Oracle
 * @Salesforce
 * @Uber
 * @Yandex <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class IIntersectionOfTwoArraysII_350 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 2, 1}, new int[]{2, 2}, new int[]{2, 2}));
        tests.add(test(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}, new int[]{4, 9}));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums1, int[] nums2, int[] expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"nums1", "nums2", "Expected"}, true, nums1, nums2, expected);

        int[] output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.intersect(nums1, nums2);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static //O(nlogn + mlogm + n+m)
    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1); //O(nlogn )
            Arrays.sort(nums2); //O(mlogm)
            int i = 0, j = 0, r = 0; //i-nums1, j-nums2, r-result array size

            //we will utilize nums1 as our result array as well, otherwise we can use an extra memeory to store result which is still O(1)
            //since array is sorted, we can optize the search
            //O(n+m)
            while (i < nums1.length && j < nums2.length) {

                if (nums1[i] < nums2[j])
                    i++; //since there will be no element on left which is present in nums2
                else if (nums1[i] > nums2[j])
                    j++; //since there will be elements on the left of nums1 which present in nums2 but only those which are right side of nums2
                else {
                    //common element
                    nums1[r++] = nums1[i];
                    i++;
                    j++;
                }

            }

            return Arrays.copyOfRange(nums1, 0, r);
        }
    }

//Follow-up
//What if the given array is already sorted? How would you optimize your algorithm?
// => we don't need to sort the array and it will be only O(n+m) / O(1)

//What if nums1's size is small compared to nums2's size? Which algorithm is better?
// => since the array isn't sorted, sorting them would leads //O(nlogn + mlogm + n+m), however, since nums1 is way smaller then nums2, then hashing would be easier
// as we can hash all the element of num1 alogn with frequency and the for each element of num2, we can search and reduce the freqency as find + add in result array

//What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
//=> in such case, sorting wont work, hence hashing is way to go
// we will hash all elements of nums1 with frequency, and the chunks the element of nums2 from disk and find them.

}
