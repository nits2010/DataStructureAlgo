package Java.LeetCode.longestCommon;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-05
 * Description: https://leetcode.com/problems/longest-common-subsequence/
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * If there is no common subsequence, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 * <p>
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 * <p>
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * The input strings consist of lowercase English characters only.
 */
public class LongestCommonSubSequence {

    public static void main(String[] args) {
        System.out.println(longestCommonSubSequence("abcde", "ace"));
        System.out.println(longestCommonSubSequence("abc", "abc"));
        System.out.println(longestCommonSubSequence("abc", "def"));


    }


    /**
     * lcs[i][j] = length of longest common sub-sequence from 0 to i char of S1 and 0 to j char of S2.
     * <p>
     * *     lcs[i][j] = 1+ lcs[i-1][j-1] (S1(i) == S2(j) ) if current is same then optimal solution will be obtained by previous lengths
     * *     lcs[i][j] = max ( lcs[i-1][j] , lcs[i][j-1] ) (S1(i) != S2(j) )
     * <p>
     * *     lcs[i][j] = 0 if i=0 or j=0 since if either of one string is empty then lcs is not possible
     * <p>
     * Since we can observe that any any moment we are only bother about above row8 only;
     * we can translate it to below optimized solution
     *
     * @param word1
     * @param word2
     * @return Runtime: 7 ms, faster than 89.00% of Java online submissions for Longest Common Subsequence.
     * Memory Usage: 33.9 MB, less than 100.00% of Java online submissions for Longest Common Subsequence.
     */
    private static int longestCommonSubSequence(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int lcs[][] = new int[2][n + 1];
        int row;
        for (int i = 0; i <= m; i++) {

            row = i & 1;

            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0)
                    lcs[row][j] = 0;

                else {

                    if (word1.charAt(i - 1) == word2.charAt(j - 1))
                        lcs[row][j] = 1 + lcs[1 - row][j - 1];
                    else
                        lcs[row][j] = Math.max(lcs[1 - row][j], lcs[row][j - 1]);
                }

            }
        }

        return lcs[m & 1][n];
    }

}
