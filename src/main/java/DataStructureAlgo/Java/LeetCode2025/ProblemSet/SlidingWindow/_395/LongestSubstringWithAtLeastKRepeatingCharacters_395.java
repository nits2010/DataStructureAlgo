package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._395;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 4/28/2025
 * Question Title: 395. Longest Substring with At Least K Repeating Characters
 * Link: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
 * Description: Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 * <p>
 * if no such substring exists, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 * Example 2:
 * <p>
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 * 1 <= k <= 105
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._340.LongestSubstringWithAtMostKDistinctCharacters_340}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @HashTable
 * @String
 * @DivideandConquer
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Adobe
 * @Amazon
 * @Baidu
 * @Facebook
 * @Google
 * @Uber
 * @Microsoft <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class LongestSubstringWithAtLeastKRepeatingCharacters_395 {

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

    static class Solution_BruteForce {
        public int longestSubstring(String s, int k) {
            int n = s.length();

            int[] count = new int[26];
            int maxLen = 0;

            for (int start = 0; start < n; start++) {

                Arrays.fill(count, 0);

                for (int end = start; end < n; end++) {
                    char c = s.charAt(end);
                    //count the frequency
                    count[c - 'a']++;

                    if (isValid(count, k)) {
                        maxLen = Math.max(maxLen, end - start + 1);
                    }
                }
            }

            return maxLen;
        }

        boolean isValid(int[] count, int k) {

            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0 && count[i] < k)
                    return false;
            }
            return true;
        }
    }

    /**
     * Divide and Conquer
     * A character can only be part of the resultant string if it has frequency greater than or equal to k.
     * Which also implies the otherwise, where a character cannot be part of the resultant string if the frequency is less than k.
     * Now considering any substring [start,end] if any of the characters in this substring has less than k frequency, then it can not be part of the resultant string,
     * however, it will divide the string in two parts, on the left side (excluding this invalid character) and right side (excluding the invalid character).
     * Eventually, we will find a substring [start,end] whose every character frequency is greater than or equal to k, and the length would be end-start+1.
     *
     * O(n) / O(n) - see .md file to know this
     */
    static class Solution_DivideAndConquer {
        public int longestSubstring(String s, int k) {
            return longestSubstring(s, 0, s.length()-1, k);

        }

        private int longestSubstring(String s, int start, int end, int k) {
            //if this substring don't have enough characters, return 0
            if (end - start + 1 < k)
                return 0;

            int []count = new int[26];
            char []chars = s.toCharArray();

            //count the frequency
            for(int i = start; i<=end; i++){
                count[chars[i] - 'a']++;
            }

            //find the first non-repeating character < k and divide the string in two parts, excluding this non-repeating character
            for(int i = start; i<=end; i++){
                if(count[chars[i] - 'a'] < k){
                    int leftMax = longestSubstring(s, start, i-1, k); //exclude the invalid char and consider start, i-1
                    int rightMax = longestSubstring(s, i+1, end, k);  //exclude the invalid char and consider i+1, end
                    return Math.max(leftMax, rightMax);
                }
            }

            return end-start; //all are valid character, this entire string is valid

        }
    }
}
