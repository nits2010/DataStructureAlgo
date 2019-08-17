package Java.Palindroms;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-23
 * Description: https://leetcode.com/problems/longest-palindromic-substring/
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 * Explanations: https://leetcode.com/articles/longest-palindromic-substring/
 *
 *
 */
public class LongestPalindromeSubString {

    public static void main(String str[]) {
        System.out.println(longestPalindromeSubString("babad"));
    }

    //O(n^2)
    public static String longestPalindromeSubString(String s) {

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


        return s.substring(start, start + max - 1);
    }
}
