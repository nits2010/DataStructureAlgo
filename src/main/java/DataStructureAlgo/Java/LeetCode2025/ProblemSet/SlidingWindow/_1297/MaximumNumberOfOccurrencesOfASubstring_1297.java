package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._1297;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/4/2025
 * Question Title: 1297. Maximum Number of Occurrences of a Substring
 * Link: https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/description
 * Description: Given a string s, return the maximum number of occurrences of any substring under the following rules:
 * <p>
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 occurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 * <p>
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s consists of only lowercase English letters.
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
 * @HashTable
 * @String
 * @SlidingWindow <p><p>
 * Company Tags
 * -----
 * @Amazon <p>
 * @Microsoft
 * @Google
 * @Bloomberg.
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaximumNumberOfOccurrencesOfASubstring_1297 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("aababcaab", 2, 3, 4, 2));
        tests.add(test("aaaa", 1, 3, 3, 2));
        tests.add(test("a", 1, 1, 1, 1));
        tests.add(test("a", 1, 1, 1, 1));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String s, int maxLetters, int minSize, int maxSize, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"s", "maxLetters", "minSize", "maxSize", "Expected"}, true, s, maxLetters, minSize, maxSize, expected);

        int output = 0;
        boolean pass, finalPass = true;

        SolutionSlidingWindow solutionSlidingWindow = new SolutionSlidingWindow();
        output = solutionSlidingWindow.maxFreq(s, maxLetters, minSize, maxSize);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Sliding Window", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        SolutionTrie solutionTrie = new SolutionTrie();
        output = solutionTrie.maxFreq(s, maxLetters, minSize, maxSize);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Trie", "Pass"}, false, output, pass ? "PASS" : "FAIL");
        return finalPass;

    }

    // # Use a fixed sliding window of size minSize
    // # For each substring of length minSize:
    // #   - Check if it contains at most maxLetters unique characters
    // #   - If yes, count its frequency

    // # Note: We only consider substrings of length minSize, not up to maxSize.
    // # This is because:
    // #   - Longer substrings are less likely to appear frequently.
    // #   - Any substring of length maxSize will occur equal to or fewer times than its prefix of length minSize.
    // #   - For example, in s = "aababcaab":
    // #       - "aaba" (length 4) appears once
    // #       - Its prefix "aab" (length 3) also appears once
    // #   - Since we want the substring with the highest frequency, minSize substrings are enough.

    // Complexity: Time O(N) | Space O(N)
    // We run a full scan of string of length n; O(n)
    // each run, up to minSize chunks, we take a substring of length minSize; O(minSize)
    // so, O(n* minSize) = O(n)
    // since 1 <= minSize <= maxSize <= min(26, s.length) means minSize can be at max 26, hence O(n)
    // Space O(N) for storing in map for counting frequency
    static class SolutionSlidingWindow {
        public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
            int n = s.length();
            char[] temp = s.toCharArray();

            Map<Character, Integer> map = new HashMap<>(); //cache for frequency count
            Map<String, Integer> countMap = new HashMap<>();

            int windowStart = 0, windowEnd = 0;

            int maxFreq = 0;

            //O(n)
            while (windowEnd < n) {

                //extend the window
                map.merge(temp[windowEnd], 1, Integer::sum);

                if (windowEnd - windowStart + 1 == minSize) {
                    if (map.size() <= maxLetters) {
                        //O(minSize)
                        String t = s.substring(windowStart, windowEnd + 1);
                        maxFreq = Math.max(maxFreq, countMap.merge(t, 1, Integer::sum));
                    }

                    //shrink the window
                    if (map.merge(temp[windowStart], -1, Integer::sum) == 0)
                        map.remove(temp[windowStart]);

                    windowStart++;

                }
                windowEnd++;

            }
            return maxFreq;
        }
    }

    //T/S: same as substring sliding window
    static class SolutionTrie {

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            int freq = 0;

            private int insertToTrie(char[] string, int start, int end) {
                TrieNode curr = this;
                while (start <= end) {
                    TrieNode next = curr.children[string[start] - 'a'];
                    if (next == null) {
                        curr.children[string[start] - 'a'] = next = new TrieNode();
                    }
                    curr = next;
                    start++;
                }
                curr.freq++;
                return curr.freq;
            }
        }


        public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

            if (s.length() < minSize)
                return 0;

            char[] temp = s.toCharArray();
            int n = temp.length;
            int windowStart = 0, windowEnd = 0;
            int maxFreq = 0;
            int[] freq = new int[26];
            int uniqueCount = 0;
            TrieNode root = new TrieNode();
            int idx;


            while (windowEnd < n) {

                idx = temp[windowEnd] - 'a';

                if (++freq[idx] == 1)
                    uniqueCount++;

                if (windowEnd - windowStart + 1 == minSize) {

                    if (uniqueCount <= maxLetters) {
                        maxFreq = Math.max(maxFreq, root.insertToTrie(temp, windowStart, windowEnd));
                    }

                    //shrink the window
                    idx = temp[windowStart] - 'a';
                    --freq[idx];
                    if (freq[idx] == 0)
                        uniqueCount--;
                    windowStart++;
                }

                windowEnd++;
            }
            return maxFreq;

        }

    }
}
