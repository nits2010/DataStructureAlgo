package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-16
 * Description: https://www.geeksforgeeks.org/find-if-string-is-k-palindrome-or-not/
 * <p>
 * Given a string, find out if the string is K-Palindrome or not. A k-palindrome string transforms into a palindrome on removing at most k characters from it.
 * <p>
 * Examples :
 * <p>
 * Input : String - abcdecba, k = 1
 * Output : Yes
 * String can become palindrome by removing 1 character i.e. either d or e)
 * <p>
 * <p>
 * Input  : String - abcdeca, K = 2
 * Output : Yes
 * Can become palindrome by removing
 * 2 characters b and e.
 * <p>
 * Input : String - acdcb, K = 1
 * Output : No
 * String can not become palindrome by
 * removing only one character.
 */
public class KPalindromeString {


    public static void main(String args[]) {
        test("abcdecba", 1);
        test("abcdecba", 1);
        test("abcdeca", 2);
        test("abcdeca", 1);
        test("acdcb", 1);
        test("a", 1);
        test("aaaaaa", 1);
        test("aabaaa", 1);
        test("aabaab", 1);
        test("aabaaab", 1);
    }

    public static void test(String s, int k) {
        KPalindromeStringUsingLPS sol = new KPalindromeStringUsingLPS();
        KPalindromeStringUsingEditDistance solEditDistance = new KPalindromeStringUsingEditDistance();
        KPalindromeStringUsingLongestCommonSubSequence solLCS = new KPalindromeStringUsingLongestCommonSubSequence();

        System.out.println("S: " + s + " k :" + k);

        System.out.println("LPS : " + sol.kPalindrome(s, k));
        System.out.println("ED  :  " + solEditDistance.kPalindrome(s, k));
        System.out.println("LCS :  " + solLCS.kPalindrome(s, k));
    }

}


/**
 * This solution is given through using LPS;
 * Find the longest palindrome sub-sequence say L
 * given string length is n and k
 * if (n-l-k)> 1 then its not possible
 */
class KPalindromeStringUsingLPS {


    boolean kPalindrome(String s, int k) {
        if (null == s || s.isEmpty())
            return true;

        if (k == 0) {
            return isPalindrome(s);
        }

        int lpsLength = longestPalindromeSubseq(s);

        return (s.length() - lpsLength <= k);// if neg then not possible otherwise possible

    }

    /**
     * Lps[i][j] length of longest palindrome sub-sequence from i to j
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


    private boolean isPalindrome(String s) {
        int n = s.length();

        int i, j;

        if (n % 2 != 0) {
            i = n / 2 - 1;
            j = n / 2 + 1;
        } else {
            i = n / 2 - 1;
            j = n / 2;
        }

        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        if (i < 0 && j == n)
            return true;
        else
            return false;
    }

}

/**
 * We can think this problem as transforming the given string to its reverse (to make palindrome) by
 * removing at most k characters. If we achieve this then we can make its k-palindrome.
 * <p>
 * If we find out how many chars we may need to delete from given string "s" to make it reverse string "rs",
 * then we'll be able to tell this is k-palindrome
 * <p>
 * Looking closely it look like Edit distance problem where only deletion is allowed.
 * If we find out, what is the cost for converting "s" to "rs" .
 * <p>
 * Since, it may possible that given string "s" may not able able to convert to its reverse string "rs", then we need to delete at
 * most N character from "s" and delete N character from "rs" to make them equal.
 * so total
 * 2*N character;
 * <p>
 * Now we are allowed to do at most K character delete i.e.
 * if
 * 2*N <= 2*k then we can make k-palindrome string from "S"
 */
class KPalindromeStringUsingEditDistance {


    boolean kPalindrome(String s, int k) {
        if (null == s || s.isEmpty())
            return true;

        if (k == 0) {
            return isPalindrome(s);
        }

        int maxDelete = editDistance(s, new StringBuffer(s).reverse().toString());


        return maxDelete <= 2 * k;

    }

    /**
     * ED [i][j] is minimum the cost of converting string "s1" form 0 to i to string "s2" from 0 to j by doing (insert, delete, replace )
     * <p>
     * ED[i][j] = ED[i-1][j-1] if s1(i) == s2(j)
     * *        =
     * *          Min ( Ci + ED[i][j-1],       if s1(i) != s2(j) and we insert (Ci cost to insert)
     * *                Cd + ED[i-1][j],       if s1(i) != s2(j) and we delete (Cd cost to insert)
     * *                Cr + ED[i-1][j-1],       if s1(i) != s2(j) and we replace (Cr cost to insert
     * *            )
     * <p>
     * Base case: if i==0 means first string is empty, then insert all from second string
     * *        then ED[0][j] = j
     * if j == 0 means second string is empty, then delete all chars of first string
     * *        then ED[i][0] = i
     * *
     * <p>
     * <p>
     * For this solution we just need to use delete operator
     * <p>
     * ED [i][j] is minimum the cost of converting string "s1" form 0 to i to string "s2" from 0 to j by doing (insert, delete, replace )
     * * <p>
     * * ED[i][j] = ED[i-1][j-1] if s1(i) == s2(j)
     * * *        =
     * * *          Min (              if s1(i) != s2(j) and we delete from "S" or "rs"
     * * *                1 + ED[i-1][j],        we delete from "S"
     * *                  1 + ED[i][j-1]       we delete from "RS"
     * * *
     * * *            )
     * * <p>
     * * Base case: if i==0 means first string is empty, then insert all from second string
     * * *        then ED[0][j] = j
     * * if j == 0 means second string is empty, then delete all chars of first string
     * * *        then ED[i][0] = i
     * * *
     *
     * @param s
     * @param rs
     * @return
     */
    private int editDistance(String s, String rs) {

        if (s == null || s.isEmpty())
            return 0;
        if (rs == null || rs.isEmpty())
            return 0;

        int n = s.length();
        int m = rs.length();
        int ed[][] = new int[n + 1][m + 1];


        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= m; j++) {

                if (i == 0) //first string is empty
                    ed[i][j] = j;

                else if (j == 0) //second string is empty
                    ed[i][j] = i;
                else if (s.charAt(i - 1) == rs.charAt(j - 1))
                    ed[i][j] = ed[i - 1][j - 1];
                else
                    ed[i][j] = 1 + Math.min(ed[i - 1][j], ed[i][j - 1]);


            }
        }

        return ed[n][m];
    }

    private boolean isPalindrome(String s) {
        int n = s.length();

        int i, j;

        if (n % 2 != 0) {
            i = n / 2 - 1;
            j = n / 2 + 1;
        } else {
            i = n / 2 - 1;
            j = n / 2;
        }

        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        if (i < 0 && j == n)
            return true;
        else
            return false;
    }

}

class KPalindromeStringUsingLongestCommonSubSequence {


    boolean kPalindrome(String s, int k) {
        if (null == s || s.isEmpty())
            return true;

        if (k == 0) {
            return isPalindrome(s);
        }

        int lpsLength = longestCommonSubSequence(s, new StringBuffer(s).reverse().toString());

        return (s.length() - lpsLength <= k);// if neg then not possible otherwise possible

    }

    /**
     * lcs[i][j] = length of longest common sub-sequence from 0 to i char of S1 and 0 to j char of S2.
     * <p>
     * *     lcs[i][j] = 1+ lcs[i-1][j-1] (S1(i) == S2(j) ) if current is same then optimal solution will be obtained by previous lengths
     * *     lcs[i][j] = max ( lcs[i-1][j] , lcs[i][j-1] ) (S1(i) != S2(j) )
     * <p>
     * *     lcs[i][j] = 0 if i=0 or j=0 since if either of one string is empty then lcs is not possible
     * <p>
     * Since we can observe that any any moment we are only bother about above row only;
     * we can translate it to below optimized solution
     *
     * @param word1
     * @param word2
     * @return
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


    private boolean isPalindrome(String s) {
        int n = s.length();

        int i, j;

        if (n % 2 != 0) {
            i = n / 2 - 1;
            j = n / 2 + 1;
        } else {
            i = n / 2 - 1;
            j = n / 2;
        }

        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        if (i < 0 && j == n)
            return true;
        else
            return false;
    }
}