package Java.LeetCode.Palindroms;

import Java.MaxA;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-19
 * Description: https://www.geeksforgeeks.org/minimum-insertions-to-form-a-palindrome-dp-28/
 * Minimum insertions to form a palindrome | DP-28
 * <p>
 * Given a string str, the task is to find the minimum number of characters to be inserted to convert it to palindrome.
 * <p>
 * Before we go further, let us understand with few examples:
 * <p>
 * ab: Number of insertions required is 1 i.e. bab
 * aa: Number of insertions required is 0 i.e. aa
 * abcd: Number of insertions required is 3 i.e. dcbabcd
 * abcda: Number of insertions required is 2 i.e. adcbcda which is same as number of insertions in the substring bcd(Why?).
 * abcde: Number of insertions required is 4 i.e. edcbabcde
 */
public class MinimumInsertionsPalindrome {

    public static void main(String[] args) {
        test("ab", 1);
        test("aa", 0);
        test("abcd", 3);
        test("abcda", 2);
        test("abcde", 4);
        test("abcdabcda", 4);
        test("abcddcba", 0);
    }

    private static void test(String str, int expected) {
        System.out.println("\n\nInput :" + str);
        IMinimumInsertionsPalindrome bruteForce = new MinimumInsertionsPalindromeBruteForce();
        IMinimumInsertionsPalindrome dp = new MinimumInsertionsPalindromeDP();
        IMinimumInsertionsPalindrome dpV2 = new MinimumInsertionsPalindromeDPV2();
        IMinimumInsertionsPalindrome lcs = new MinimumInsertionsPalindromeUsingLCS();

        System.out.println("Brute Force :" + bruteForce.minimumInsertionToFormPalindrome(str) + " expected :" + expected);
        System.out.println("DP :" + dp.minimumInsertionToFormPalindrome(str) + " expected :" + expected);
        System.out.println("DP V2 :" + dpV2.minimumInsertionToFormPalindrome(str) + " expected :" + expected);
        System.out.println("lcs  :" + lcs.minimumInsertionToFormPalindrome(str) + " expected :" + expected);


    }

}

interface IMinimumInsertionsPalindrome {

    int minimumInsertionToFormPalindrome(String string);
}

/**
 * To make string palindrome, we need to test the string is palindrome or not.
 * To test; we compare both end element recursively moving left towards right and righ towards left.
 * Lets say we are testing str[left....right] then there could be following possibilities
 * <p>
 * case 1: str[left] == str[right] ; Then we can move left++ and right--
 * case 2: str[left] != str [right] : then we need to test all the string
 * a. str[left...right-1] And
 * b. str[left+1...right] and get the minimum insertion required to them. For Str[left...right] would be 1 + on either side
 * <p>
 * <p>
 * Hence;
 * <p>
 * Str(left, right ) = Str(left+1,right-1) if str[left] == str[right]
 * *                     otherwise;
 * *            1 + Min ( str[left...right-1] , str[left+1...right] )
 * <p>
 * complexity: O(2^n) as we have two dimension where the each test can go. Testing all the possibilities
 */
class MinimumInsertionsPalindromeBruteForce implements IMinimumInsertionsPalindrome {

    @Override
    public int minimumInsertionToFormPalindrome(String string) {
        if (null == string || string.isEmpty())
            return 0;

        if (string.length() == 1)
            return 0;

        return minimumInsertionToFormPalindromeRecursive(string, 0, string.length() - 1);
    }

    private int minimumInsertionToFormPalindromeRecursive(String string, int left, int right) {
        if (left > right)
            return Integer.MAX_VALUE;

        if (left == right)
            return 0;

        if (left + 1 == right) {
            if (string.charAt(left) == string.charAt(right))
                return 0;
            else
                return 1;
        }

        if (string.charAt(left) == string.charAt(right))
            return minimumInsertionToFormPalindromeRecursive(string, left + 1, right - 1);
        else
            return 1 + Math.min(minimumInsertionToFormPalindromeRecursive(string, left, right - 1),
                    minimumInsertionToFormPalindromeRecursive(string, left + 1, right));
    }
}


/**
 * We can solve the above problem using DP, as there are many sub-problem exist.
 * To make string palindrome, we need to test the string is palindrome or not.
 * To test; we compare both end element recursively moving left towards right and righ towards left.
 * Lets say we are testing str[left....right] then there could be following possibilities
 * <p>
 * case 1: str[left] == str[right] ; Then we can move left++ and right--
 * case 2: str[left] != str [right] : then we need to test all the string
 * a. str[left...right-1] And
 * b. str[left+1...right] and get the minimum insertion required to them. For Str[left...right] would be 1 + on either side
 * <p>
 * <p>
 * Hence;
 * dp[i][j] denotes the minimum number of insertion required to make string palindrome between i and j
 * dp[i][j] = dp[i + 1][j - 1] if str[i] == str[j]
 * *                     otherwise;
 * *            1 + Min ( dp[i][j-1] , dp[i+1][j] )
 * <p>
 * To compute use similar algo like {@link Java.InterviewBit.MatrixChainMultiplication}
 * complexity: O(n^2)/O(n^2) as we have two dimension where the each test can go. Testing all the possibilities
 */
class MinimumInsertionsPalindromeDP implements IMinimumInsertionsPalindrome {

    @Override
    public int minimumInsertionToFormPalindrome(String string) {
        if (null == string || string.isEmpty())
            return 0;

        if (string.length() == 1)
            return 0;

        int n = string.length();
        int dp[][] = new int[n + 1][n + 1];

        /**
         * Every character is palindrome
         */
        for (int i = 1; i <= n; i++)
            dp[i][i] = 1;

        dp[0][0] = 0; //empty string require only 0 insertion to make it palindrome

        for (int len = 2; len <= n; len++) {

            for (int i = 1; i <= n - len + 1; i++) {

                int j = i + len - 1;

                char a = string.charAt(i - 1);
                char b = string.charAt(j - 1);
                if (len == 2) {
                    if (a == b)
                        dp[i][j] = 0;
                    else
                        dp[i][j] = 1;
                } else {

                    if (a == b)
                        dp[i][j] = dp[i + 1][j - 1];
                    else {

                        dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                    }

                }
            }
        }

        return dp[1][n];
    }


}


/**
 * O(n^2)/ O(n^2)
 */
class MinimumInsertionsPalindromeDPV2 implements IMinimumInsertionsPalindrome {

    @Override
    public int minimumInsertionToFormPalindrome(String string) {
        if (null == string || string.isEmpty())
            return 0;

        if (string.length() == 1)
            return 0;

        int n = string.length();
        int dp[][] = new int[n][n];

        for (int gap = 1; gap < n; gap++) {
            for (int left = 0, right = gap; right < n; right++, left++) {
                if (string.charAt(left) == string.charAt(right))
                    dp[left][right] = dp[left + 1][right - 1];
                else
                    dp[left][right] = 1 + Math.min(dp[left + 1][right], dp[left][right - 1]);
            }
        }


        return dp[0][n - 1];
    }
}

/**
 * We can solve the above problem using Longest common sub-sequence.
 * We can find LCS (string, Revstring)
 * Then our answer would be n - lcs
 * <p>
 * O(n^2)/ O(n)
 * <p>
 * {@link Java.LeetCode.longestShortestCommon.LongestCommonSubSequence}
 */
class MinimumInsertionsPalindromeUsingLCS implements IMinimumInsertionsPalindrome {

    @Override
    public int minimumInsertionToFormPalindrome(String string) {
        if (null == string || string.isEmpty())
            return 0;

        if (string.length() == 1)
            return 0;

        return string.length() - lcs(string, new StringBuilder(string).reverse().toString());
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
     */
    private int lcs(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int lcs[][] = new int[2][n + 1];

        int row = 0;

        for (int i = 0; i <= m; i++) {

            row = i & 1;

            for (int j = 0; j <= n; j++) {


                /**
                 * Empty
                 */
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