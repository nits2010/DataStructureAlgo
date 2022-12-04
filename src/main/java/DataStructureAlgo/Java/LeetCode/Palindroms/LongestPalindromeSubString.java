package DataStructureAlgo.Java.LeetCode.Palindroms;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-23
 * Description: https://leetcode.com/problems/longest-palindromic-substring/
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 * Explanations: https://leetcode.com/articles/longest-palindromic-substring/
 * https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
 */
public class LongestPalindromeSubString {

    public static void main(String str[]) {
        System.out.println(longestPalindrome("babad"));
    }


    /**
     * O(n^2)
     * Runtime: 30 ms, faster than 48.36% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Longest Palindromic Substring.
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {

        if (null == s || s.isEmpty())
            return s;
        int n = s.length();
        int max = 1;
        int start = 0;
        int l, r;

        for (int i = 1; i < n; i++) {

            //Odd length
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {

                if (r - l + 1 > max) {
                    max = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }

            //even length
            l = i - 1;
            r = i;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {

                if (r - l + 1 > max) {
                    max = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }


        }


        return s.substring(start, start + max);
    }
}
