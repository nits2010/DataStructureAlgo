package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._2516;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 20/11/24
 * Question Title: 2516. Take K of Each Character From Left and Right
 * Link: https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/
 * Description: You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 * <p>
 * Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aabaaaacaabc", k = 2
 * Output: 8
 * Explanation:
 * Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
 * Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
 * A total of 3 + 5 = 8 minutes is needed.
 * It can be proven that 8 is the minimum number of minutes needed.
 * Example 2:
 * <p>
 * Input: s = "a", k = 1
 * Output: -1
 * Explanation: It is not possible to take one 'b' or 'c' so return -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s consists of only the letters 'a', 'b', and 'c'.
 * 0 <= k <= s.length
 * <p><p>
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
 * @SlidingWindow
 * @HashTable <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */
public class TakeKOfEachCharacterFromLeftAndRight_2516 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(test("aabaaaacaabc", 2, 8));
        tests.add(test("a", 1, -1));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"String", "K", "Expected"}, true, s, k, expected);

        int output;
        boolean pass, finalPass = true;

        SolutionV1 solutionV1 = new SolutionV1();
        output = solutionV1.takeCharacters(s, k);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTest(new String[]{"V1", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionV2 solutionV2 = new SolutionV2();
        output = solutionV2.takeCharacters(s, k);
        pass = output == expected;
        finalPass = finalPass && pass;
        CommonMethods.printTest(new String[]{"V2", "Pass"}, false, output, pass ? "Pass" : "Fail");


        //add logic here

        return finalPass;

    }

    static class SolutionV1 {
        public int takeCharacters(String s, int k) {
            int n = s.length();
            int[] count = new int[3];
            char[] str = s.toCharArray();

            //count the frequency
            for (char c : str) {
                count[c - 'a']++;
            }

            //possible to collect each of them at least k times ?
            if (count[0] < k || count[1] < k || count[2] < k)
                return -1;

            //get the largest window to remove
            int left = 0, right = 0;
            int maxWindow = 0;
            int[] window = new int[3];

            while (right < n) {

                window[str[right] - 'a']++;

                //if we have more a,b,c then reduce this window
                while (left <= right && (count[0] - window[0] < k || count[1] - window[1] < k || count[2] - window[2] < k)) {
                    window[str[left] - 'a']--;
                    left++;
                }
                //calculate the size
                maxWindow = Math.max(maxWindow, right - left + 1);
                right++;
            }

            return n - maxWindow;

        }
    }


    static class SolutionV2 {
        public int takeCharacters(String s, int k) {
            int n = s.length();
            int[] count = new int[3];
            char[] str = s.toCharArray();

            //count the frequency
            for (char c : str) {
                count[c - 'a']++;
            }

            //possible to collect each of them at least k times ?
            if (count[0] < k || count[1] < k || count[2] < k)
                return -1;

            //get the largest window to remove
            int left = 0, right = 0;
            int maxWindow = 0;

            while (right < n) {

                //remove the right character form our window
                count[str[right] - 'a']--;

                //if we don't have enought a,b,c then add that from left
                while (left <= right && (Math.min(count[0], Math.min(count[1], count[2])) < k)) {
                    count[str[left] - 'a']++;
                    left++;
                }

                //calculate the size
                maxWindow = Math.max(maxWindow, right - left + 1);
                right++;
            }

            return n - maxWindow;

        }
    }
}
