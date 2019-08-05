package Java.LeetCode.longestCommon;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-05
 * Description: https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 * Examples :
 * <p>
 * Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
 * Output : 5
 * The longest common substring is “Geeks” and is of length 5.
 * <p>
 * Input : X = “abcdxyz”, y = “xyzabcd”
 * Output : 4
 * The longest common substring is “abcd” and is of length 4.
 * <p>
 * Input : X = “zxabcdezy”, y = “yzabcdezx”
 * Output : 6
 * The longest common substring is “abcdez” and is of length 6.
 */
public class LongestCommonSubString {

    public static void main(String[] args) {
        System.out.println(longestCommonSubString("GeeksforGeeks", "GeeksQuiz"));
        System.out.println(longestCommonSubString("abcdxyz", "xyzabcd"));
        System.out.println(longestCommonSubString("zxabcdezy", "yzabcdezx"));


    }


    /**
     * lcs[i][j] = length of longest common sub-sequence from 0 to i char of S1 and 0 to j char of S2.
     * <p>
     * *     lcs[i][j] = 1+ lcs[i-1][j-1] (S1(i) == S2(j) ) if current is same then optimal solution will be obtained by previous lengths
     * *     lcs[i][j] = 0 (S1(i) != S2(j) )
     * <p>
     * *     lcs[i][j] = 0 if i=0 or j=0 since if either of one string is empty then lcs is not possible
     * <p>
     * Since we can observe that any any moment we are only bother about above row only;
     * we can translate it to below optimized solution
     *
     * @param word1
     * @param word2
     */
    private static int longestCommonSubString(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int lcs[][] = new int[2][n + 1];
        int row;
        int longestCommonSubString = 0;
        for (int i = 0; i <= m; i++) {

            row = i & 1;

            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0)
                    lcs[row][j] = 0;

                else {

                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        lcs[row][j] = 1 + lcs[1 - row][j - 1];
                        longestCommonSubString = Math.max(longestCommonSubString, lcs[row][j]);
                    } else
                        lcs[row][j] = 0;
                }

            }
        }

        return longestCommonSubString;
    }
}
