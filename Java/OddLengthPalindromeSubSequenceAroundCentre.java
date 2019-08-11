package Java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description: https://www.geeksforgeeks.org/total-number-of-odd-length-palindrome-sub-sequence-around-each-centre/
 * Given a string str, the task is to find the number of odd length palindromic sub-sequences around of str with str[i] as centre i.e. every index will be considered as the centre one by one.
 * <p>
 * Examples:
 * <p>
 * Input: str = “xyzx”
 * Output: 1 2 2 1
 * For index 0: There is only a single sub-sequence possible i.e. “x”
 * For index 1: Two sub-sequences are possible i.e. “y” and “xyx”
 * For index 2: “z” and “xzx”
 * For index 3: “x”
 * <p>
 * Input: str = “aaaa”
 * Output: 1 3 3 1
 */
public class OddLengthPalindromeSubSequenceAroundCentre {

    public static void main(String[] args) {

        System.out.println(OddLengthPalindromeSubSequenceAroundCentreDP.oddLengthPalindromCenter("xyzx"));
        System.out.println(OddLengthPalindromeSubSequenceAroundCentreDP.oddLengthPalindromCenter("aaaa"));
        System.out.println(OddLengthPalindromeSubSequenceAroundCentreDP.oddLengthPalindromCenter("xyzw"));

    }


}

class OddLengthPalindromeSubSequenceAroundCentreDP {

    /**
     * dp[i][j] defines the odd length palindrome from (0..i-1) and (j..n-1) assuming i as center
     * <p>
     * dp[i][j] = {
     * *                dp[i-1][j] + dp[i][j+1] - dp[i-1][j+1] if s(i)==s(j)
     * *                dp[i-1][j] + dp[i][j+1] if s(i)!=s(j)
     * *                 i == 0 and j == n-1 then dp[i][j] = 2 if s(i) == s(j) otherwise 0
     * *
     * }
     *
     * @param str
     * @return
     */
    public static List<Integer> oddLengthPalindromCenter(String str) {

        if (str == null || str.isEmpty())
            return Collections.EMPTY_LIST;

        int n = str.length();
        int dp[][] = new int[n][n];

        for (int length = n - 1; length >= 0; length--) {

            for (int i = 0; i < n - length; i++) {

                int j = i + length; //length apart i and j

                char c1 = str.charAt(i);
                char c2 = str.charAt(j);

                if (i == 0 && j == n - 1) {
                    if (c1 == c2)
                        dp[i][j] = 2;
                    else
                        dp[i][j] = 0;

                    continue;
                }


                if (c1 == c2) {

                    if (i - 1 >= 0)
                        dp[i][j] = dp[i - 1][j];

                    if (j + 1 < n)
                        dp[i][j] += dp[i][j + 1];

                    if (i - 1 >= 0 && j + 1 < n)
                        dp[i][j] -= dp[i - 1][j + 1];

                } else {
                    if (i - 1 >= 0)
                        dp[i][j] = dp[i - 1][j];
                    if (j + 1 < n)
                        dp[i][j] += dp[i][j + 1];
                }
            }
        }
        List<Integer> ways = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            if (i == 0 || i == n - 1)
                ways.add(1);
            else
                ways.add(dp[i - 1][i + 1]); //as i is center
        }

        return ways;

    }
}