package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-22
 * Description:
 */
public class LongestPalindromicSubsequence {

    /**
     * Lps[i][j] length of longest palindrome subsequence from i to j
     * Lps[i][j] = Lps[i+1][j-1] + 2 ; if S[i] == S[j]
     * = max (Lps[i+1][j], Lps[i][j-1]) ; if S[i] != S[j]
     * = 1 if i == j
     * = 2 if S[i] == S[i+1]
     * <p>
     * Lets translate it to 1D array
     * lps[i] length of longest palindrome sub-sequence from till i
     * we can note that in above equation when we say
     * lps[i+1][j] -> is just a below row of current
     * lps[i][j-1] -> is the same row but previous column
     * lps[i+1][j-1] -> is the below row with previous column.
     * <p>
     * Apparently we are dealing with only, current row ( current column, previous column)
     * and below row as defined above. so we just need this.
     * <p>
     * lps[i+1][j] = lps[j]
     * lps[i+1][j-1] = lps[j-1]
     * lps[i][j-1] = lps[j-1]
     * <p>
     * but if we overwrite then we'll lost the values, so keep caching it.
     * <p>
     * More info: https://leetcode.com/problems/longest-palindromic-subsequence/discuss/304451/O(n2)-time-O(n)-space-solution-Dynamic-Programming
     **/


    public int longestPalindromeSubseq(String s) {
        if (null == s || s.isEmpty())
            return 0;

        int n = s.length();
        int lps[] = new int[n];
        int temp, previous;

        for (int i = n - 1; i >= 0; i--) {
            lps[i] = 1; // each char form a lps
            previous = 0; //means there is no lps from this point as of now
            for (int j = i + 1; j < n; j++) {

                temp = lps[j]; // cache the length current lps formed so far

                if (s.charAt(i) == s.charAt(j)) // char matched
                    lps[j] = 2 + previous; //lps[i][j] = lps[i+1][j-1]->previous + 2 ;
                else
                    lps[j] = Math.max(lps[j], lps[j - 1]); // lps[i][j] = Math.max(lps[i+1][j] , lps[i][j-1]) ;

                previous = temp; //since now current j will be become previous for next j
            }
        }

        return lps[n - 1];
    }


    /**
     * O(n^2)/O(n^2)
     * Lps[i][j] length of longest palindrome subsequence from i to j
     * Lps[i][j] = Lps[i+1][j-1] + 2 ; if S[i] == S[j]
     * = max (Lps[i+1][j], Lps[i][j-1]) ; if S[i] != S[j]
     * = 1 if i == j
     * = 2 if S[i] == S[i+1]
     **/
    public int longestPalindromeSubseqSlower(String s) {

        if (null == s || s.isEmpty())
            return 0;

        int n = s.length();
        int lps[][] = new int[n][n];

        //every char form palindrome itself
        for (int i = 0; i < n; i++) {
            lps[i][i] = 1;
        }

        for (int length = 2; length <= n; length++) {

            for (int i = 0; i < n - length + 1; i++) {

                int j = i + length - 1;

                boolean isEqual = s.charAt(i) == s.charAt(j);

                if (length == 2 && isEqual)
                    lps[i][j] = 2;
                else {

                    if (isEqual)
                        lps[i][j] = 2 + lps[i + 1][j - 1];
                    else
                        lps[i][j] = Math.max(lps[i + 1][j], lps[i][j - 1]);
                }
            }
        }

        return lps[0][n - 1];


    }


    /** Cleaner version
     * O(n^2)/O(n^2)
     * Lps[i][j] length of longest palindrome sub-sequence from i to j
     * Lps[i][j] = Lps[i+1][j-1] + 2 ; if S[i] == S[j]
     * = max (Lps[i+1][j], Lps[i][j-1]) ; if S[i] != S[j]
     * = 1 if i == j
     * = 2 if S[i] == S[i+1]
     **/
    public int longestPalindromeSubseqSlowerV2(String s) {

        if (null == s || s.isEmpty())
            return 0;

        int n = s.length();
        int lps[][] = new int[n][n];


        for (int i = n - 1; i >= 0; i--) {
            lps[i][i] = 1;  //every char form palindrome itself
            for (int j = i + 1; j < n; j++) {

                if (s.charAt(i) == s.charAt(j)) // char matched
                    lps[i][j] = lps[i + 1][j - 1] + 2;
                else
                    lps[i][j] = Math.max(lps[i + 1][j], lps[i][j - 1]);

            }
        }


        return lps[0][n - 1];


    }
}
