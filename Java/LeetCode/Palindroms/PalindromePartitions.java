package Java.LeetCode.Palindroms;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 *
 * {@link PalindromePartitioning}
 */
class PalindromePartitions {

    public static void main(String[] args) {

        System.out.println(partition("aab"));
    }


    public static List<List<String>> partition(String s) {
        final List<List<String>> result = new LinkedList<>();
        boolean palindrome[][] = buildPalindromeMap(s);
        partition(result, new LinkedList<>(), 0, s.length(), s, palindrome);
        return result;
    }

    /**
     * Backtracking by caching
     *
     * @param result
     * @param temp
     * @param start
     * @param n
     * @param s
     * @param palindrome
     */
    private static void partition(List<List<String>> result, LinkedList<String> temp, int start, int n, String s, boolean palindrome[][]) {
        if (start == n) {
            result.add(new LinkedList<>(temp));
            return;

        }

        for (int i = start; i < n; i++) {
            if (palindrome[start][i]) {

                temp.add(s.substring(start, i + 1));
                partition(result, temp, i + 1, n, s, palindrome);
                temp.removeLast();
            }
        }
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


    static boolean isPalindrome(final String str, int i, int j) {
        if (str == null)
            return true;

        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--))
                return false;

        }

        return true;
    }


    /**
     * Backtracking exhaustive
     *
     * @param result Empty list
     * @param currL  Empty list
     * @param start  0
     * @param n      length of string
     * @param s      actual string
     */
    private static void partitionSlower(List<List<String>> result, LinkedList<String> currL, int start, int n, String s) {

        if (start >= n) {
            result.add(new LinkedList<>(currL));
            return;

        }

        for (int i = start; i < n; i++) {

            if (isPalindrome(s, start, i)) {

                currL.add(s.substring(start, i + 1));
                partitionSlower(result, currL, i + 1, n, s);
                currL.removeLast();
            }
        }

    }


}