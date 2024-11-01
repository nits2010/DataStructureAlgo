package DataStructureAlgo.Java.LeetCode2025.ProblemSet.math._670;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date:17/10/24
 * Question Category: 670. Maximum Swap
 * Description: https://leetcode.com/problems/maximum-swap/description
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * <p>
 * Return the maximum valued number you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * <p>
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= num <= 108
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Math
 * @Greedy <p>
 * Company Tags
 * -----
 * @Facebook
 * @tiktok
 * @Amazon
 * @ByteDance
 * @Editorial
 */

public class MaximumSwap_670 {
    public static void main(String[] args) {
        boolean test = true;
        test &= test(2736,7236);
        test &= test(9973,9973);
        test &= test(1111,1111);
        test &= test(7007,7700);
        test &= test(12345,52341);
        test &= test(54321,54321);
        test &= test(98368,98863);

        CommonMethods.printAllTestOutCome(test);
    }

    protected static boolean test(int num, int expected) {
        System.out.println("--------------------------------");
        System.out.println("num: " + num + " expected: " + expected);
        Solution solution = new Solution();
        int output = solution.maximumSwap(num);
        boolean pass = output == expected;
        System.out.println("Output: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        return pass;
    }

    static class Solution {
        public int maximumSwap(int num) {
            char[] chars = Integer.toString(num).toCharArray();


            //7007 consider this as example
            // the correct output of this is 7700
            // so from the right side, we scan the number and get the index of largest value seen so far.
            // then we need to see all the numbers which is smaller than the largest number, performing swap on them can make larger number
            // to make number overall number largest, the better the larger number on right most side and the smaller (smaller than larger) on left most side.
            // in above, the smallest is on index=1 while largest = 3 swapping them makes 7700

            //12345 -> largest index=4 and smallest = 0 -> 42341
            //98368 -> largest index = 8 smallest = 2 -> 98863
            // in 98368 this, there is no smaller than 9 that's why its been not used.

            int largestIndex = chars.length - 1;
            int prevLargestIndex = chars.length - 1;
            int smallestIndex = chars.length - 1;

            for (int i = chars.length - 2; i >= 0; i--) {

                if (chars[largestIndex] < chars[i]) {
                    largestIndex = i;
                } else if (chars[largestIndex] > chars[i]) {
                    //means current is smaller, capture it
                    prevLargestIndex = largestIndex;
                    smallestIndex = i;
                }
            }
            // System.out.println("smallestIndex : "+smallestIndex + " largestIndex :"+largestIndex + " prevLargestIndex: "+prevLargestIndex);

            if (smallestIndex != -1 && smallestIndex < prevLargestIndex) {

                char c = chars[smallestIndex];
                chars[smallestIndex] = chars[prevLargestIndex];
                chars[prevLargestIndex] = c;
            }

            return Integer.parseInt(new String(chars));


        }
    }
}
