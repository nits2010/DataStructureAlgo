package Java.LeetCode.Palindroms;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-22
 * Description:https://www.geeksforgeeks.org/palindrome-partitioning-dp-17/
 * <p>
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome.
 * For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”. Determine the fewest cuts needed for
 * palindrome partitioning of a given string. For example, minimum 3 cuts are needed for “ababbbabbababa”.
 * The three cuts are “a|babbbab|b|ababa”. If a string is palindrome, then minimum 0 cuts are needed.
 * If a string of length n containing all different characters, then minimum n-1 cuts are needed.
 * <p>
 * {@link PalindromePartitions}
 */
public class PalindromePartitioning {


    public static void main(String args[]) {

        System.out.println(minCut("aab"));
    }

    /**
     * palindrome[i][j] defined the is the string from i to j is palindrome or not ( true / false )
     * <p>
     * *     palindrome[i][j] = palindrome[i+1][j-1] if str[i] == str[j] else false
     * <p>
     * minCut [i] defined the minimum number of cut require to make palindrome till i from 0.
     * <p>
     * *     minCut[i] = 0 if palindrome[0][i] = true ; means Str(0..i) is palindrome
     * <p>
     * *     minCut[i] = min ( minCut[j] s.t. palindrome[j+1][i] = true ); 0<=j<i
     */


    public static int minCut(String s) {
        if (null == s || s.isEmpty() || s.length() == 1)
            return 0;


        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1))
                return 0;
            else
                return 1;
        }

        char str[] = s.toCharArray();
        int n = str.length;
        boolean palindrome[][] = buildPalindromeMap(s);
        int minCut[] = new int[n];


        for (int i = 0; i < n; i++) {
            if (palindrome[0][i]) //means Str(0..i) is palindrome
                minCut[i] = 0;
            else {
                minCut[i] = Integer.MAX_VALUE;

                for (int j = 0; j < i; j++) {

                    if (palindrome[j + 1][i] && 1 + minCut[j] < minCut[i])
                        minCut[i] = 1 + minCut[j];
                }

            }

        }

        return minCut[n - 1];
    }


    static boolean[][] buildPalindromeMap(String s) {


        char str[] = s.toCharArray();
        int n = str.length;
        boolean palindrome[][] = new boolean[n][n];


        for (int i = 0; i < n; i++)
            palindrome[i][i] = true;

        //O(n^2)
        for (int length = 2; length <= n; length++) {

            for (int i = 0; i < n - length + 1; i++) {
                int j = i + length - 1;


                if (length == 2) { // length of two palindrome
                    palindrome[i][j] = (str[i] == str[j]);
                } else
                    palindrome[i][j] = (palindrome[i + 1][j - 1] & str[i] == str[j]);


            }
        }
        return palindrome;

    }
}
