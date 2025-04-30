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
        tests.add(test("aaabb", 3, 3));
        tests.add(test("ababbc", 2, 5));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, int k, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"s", "k", "Expected"}, true, s, k, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution_BruteForce solutionBruteForce = new Solution_BruteForce();
        output = solutionBruteForce.longestSubstring(s, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BruteForce", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        SolutionDivideAndConquer.Solution_DivideAndConquer solutionDivideAndConquer = new SolutionDivideAndConquer().new Solution_DivideAndConquer();
        output = solutionDivideAndConquer.longestSubstring(s, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"DivideAndConquer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionDivideAndConquer.Solution_DivideAndConquerOptimized solutionDivideAndConquerOptimized = new SolutionDivideAndConquer().new Solution_DivideAndConquerOptimized();
        output = solutionDivideAndConquerOptimized.longestSubstring(s, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"DivideAndConquer-Optimized", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        Solution_SlidingWindow solutionSlidingWindow = new Solution_SlidingWindow();
        output = solutionSlidingWindow.longestSubstring(s, k);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Sliding-Window", "Pass"}, false, output, pass ? "PASS" : "FAIL");

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
     * <p>
     * O(n) / O(n) - see .md a file to know this
     */
    static class SolutionDivideAndConquer {


        class Solution_DivideAndConquer {
            public int longestSubstring(String s, int k) {
                return longestSubstring(s, 0, s.length() - 1, k);

            }

            private int longestSubstring(String s, int start, int end, int k) {
                if (end - start + 1 < k)
                    return 0;

                int[] count = new int[26];
                char[] chars = s.toCharArray();

                //count the frequency
                for (int i = start; i <= end; i++) {
                    count[chars[i] - 'a']++;
                }

                //find the first non-repeating character < k and divide the string in two parts, excluding this non-repeating character
                for (int i = start; i <= end; i++) {
                    if (count[chars[i] - 'a'] < k) {
                        int leftMax = longestSubstring(s, start, i - 1, k); //exclude the invalid char and consider start, i-1
                        int rightMax = longestSubstring(s, i + 1, end, k); //exclude the invalid char and consider i+1, end
                        return Math.max(leftMax, rightMax);
                    }
                }

                return end - start + 1; //all are valid character, this entire string is valid

            }
        }

        //once we find the < k character, we can start scanning the string and skip all such characters at once
        class Solution_DivideAndConquerOptimized {
            public int longestSubstring(String s, int k) {
                return longestSubstring(s, 0, s.length() - 1, k);

            }

            private int longestSubstring(String s, int start, int end, int k) {
                //if this substring don't have enough characters, return 0
                if (end - start + 1 < k)
                    return 0;

                int[] count = new int[26];
                char[] chars = s.toCharArray();

                //count the frequency
                for (int i = start; i <= end; i++) {
                    count[chars[i] - 'a']++;
                }

                //find the first non-repeating character < k and divide the string in two parts, excluding this non-repeating character
                for (int i = start; i <= end; i++) {
                    if (count[chars[i] - 'a'] < k) {
                        int next = i + 1;
                        while (next <= end && count[chars[next] - 'a'] < k) {
                            next++;
                        }

                        int leftMax = longestSubstring(s, start, i - 1, k); //exclude the invalid char and consider start, i-1
                        int rightMax = longestSubstring(s, next, end, k); //exclude the invalid char and consider i+1, end
                        return Math.max(leftMax, rightMax);
                    }
                }

                return end - start + 1; //all are valid character, this entire string is valid

            }
        }
    }


    /**
     * One thing we noticed in a brute force solution that, we have to check for isValid every time we move the end.
     * This is because, we are not sure that every character in this window (start,end) is valid or not.
     * <p>
     * To apply the sliding window logic here, we need to find the way how we expand the window and how we shrink the window.
     * Expand the window is intuitive where we keep adding character, however, when to stop ?
     * <p>
     * Assume the case, we have <h> unique character to process, and while expanding the window, we include all the incoming characters.
     * This expansion would last either last character of the window or till the point, where we exceed the unique character count h in the current window.
     * <p>
     * Hence, expand the window to keep the unique character count to h and shrink it to keep its unique character count to h only.
     * <p>
     * However, the main problem is still, how we count that each character in the window has >=k frequency, for this we can keep track of it within the window,
     * and as soon as we hit both the condition h=unique character and >=k frequency, we can return the length of the window.
     * <p>
     * Since there could be only 26 unique characters, we can traverse them all, or we can find the count of unique char and use them
     * <p>
     * O(n) time and O(1) space
     */
    static class Solution_SlidingWindow {
        public int longestSubstring(String s, int k) {

            int n = s.length();
            int[] count = new int[26];

            int uniqueChar = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                count[c - 'a']++;
                if (count[c - 'a'] == 1) {
                    uniqueChar++;
                }

            }

            int maxLen = 0;
            //traverse all unique characters
            for (int unique = 1; unique <= uniqueChar; unique++) {

                Arrays.fill(count, 0); // reset the frequency map
                int windowStart = 0, windowEnd = 0;
                int uniqueCount = 0;
                int kUnique = 0;

                while (windowEnd < n) {

                    if (uniqueCount <= unique) {
                        //expand the window
                        char c = s.charAt(windowEnd);
                        count[c - 'a']++;

                        if (count[c - 'a'] == 1) {
                            uniqueCount++;
                        }

                        if (count[c - 'a'] == k) {
                            kUnique++;
                        }
                        windowEnd++;
                    } else {
                        //shrink the window
                        char c = s.charAt(windowStart);

                        //if this character reached k unique only, reducing further will violate the condition
                        if (count[c - 'a'] == k) {
                            kUnique--;
                        }

                        count[c - 'a']--;

                        //if this character leaves the substring
                        if (count[c - 'a'] == 0) {
                            uniqueCount--;
                        }

                        windowStart++;

                    }

                    //now if the uniqueCount = unique and kUnique = uniqueCount then this window is valid;
                    // this is because, we count the kUnique only when a character reaches the k frequency, that makes total count = unique
                    if (uniqueCount == unique && kUnique == uniqueCount) {

                        maxLen = Math.max(maxLen, windowEnd - windowStart);
                    }
                }


            }
            return maxLen;

        }
    }
}
