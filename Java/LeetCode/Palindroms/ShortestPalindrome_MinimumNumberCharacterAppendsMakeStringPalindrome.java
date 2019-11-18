package Java.LeetCode.Palindroms;

import Java.helpers.GenericPrinter;
import Java.nonleetcode.KMP_KnuthMorrisPratt;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-19
 * Description: https://www.geeksforgeeks.org/minimum-number-appends-needed-make-string-palindrome/
 * Minimum number of Appends needed to make a string palindrome
 * <p>
 * Given a string s we need to tell minimum characters to be appended (insertion at end) to make a string palindrome.
 * <p>
 * Examples:
 * <p>
 * Input : s = "abede"
 * Output : 2
 * We can make string palindrome as "abedeba"
 * by adding ba at the end of the string.
 * <p>
 * Input : s = "aabb"
 * Output : 2
 * We can make string palindrome as"aabbaa"
 * by adding aa at the end of the string.
 * <p>
 * {@link ShortestPalindrome_MinimumNumberCharacterAddToFrontMakeStringPalindrome}
 */
public class ShortestPalindrome_MinimumNumberCharacterAppendsMakeStringPalindrome {

    public static void main(String[] args) {
        test("aacecaaa", "aacecaaacecaa");
        test("abcd", "abcdcba");
        test("abede", "abedeba");
        test("aabb", "aabbaa");
        test("abcbabcab", "abcbabcabacbabcba");
//

    }

    private static void test(String str, String expected) {
        System.out.println("\n input: " + str);
        ShortestPalindromeAppendsEndKMP kmp = new ShortestPalindromeAppendsEndKMP();
        System.out.println("KMP :" + kmp.shortestPalindrome(str) + " expected :" + expected);
    }
}


/**
 * In above brute force, we are essentially try to find the longest prefix which is also suffice in s+(revS).
 * <p>
 * We can convert it to an alternative problem"find the longest palindrome substring starts from index 0". the number of character need to append
 * <p>
 * Algorithm:
 * 1. S => s+'$' + revS
 * 2. Find Longest prefix which is also suffix  length
 * 3. total character need to add is n - length
 * <p>
 * <p>
 * {@link KMP_KnuthMorrisPratt}
 * <p>
 * Runtime: 3 ms, faster than 76.30% of Java online submissions for Shortest Palindrome.
 * Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Shortest Palindrome.
 * <p>
 * https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation
 */
class ShortestPalindromeAppendsEndKMP {

    /**
     * s = abcbabcab -> Here longest palindrome sub-string from the beginning is "abcba". We need to find this sub-string
     * rev = bacbabcba
     * <p>
     * joined = bacbabcba$abcbabcab
     * <p>
     * [b,a,c,b,a,b,c,b,a,$,a,b,c,b,a,b,c,a,b]
     * [0,0,0,1,2,1,0,1,2,0,0,1,0,1,2,1,0,0,1]
     * <p>
     * of length = 1; Length of longest palindrome
     * so; n - 1 = 9-1 = 8
     * abcbabcab + "acbabcba"   => abcbabcabacbabcba
     *
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {

        if (s == null || s.isEmpty())
            return s;

        String rev = new StringBuilder(s).reverse().toString();
        String joined = rev + "$" + s;

        int prefixWhichIsAlsoSuffix[] = prefixWhichIsAlsoSuffix(joined);
        System.out.println(GenericPrinter.toString(joined.toCharArray()));
        System.out.println(GenericPrinter.toString(prefixWhichIsAlsoSuffix));

        int lengthOfLongestPrefixAlsoSuffix = prefixWhichIsAlsoSuffix[joined.length() - 1];

        String pattern = rev.substring(lengthOfLongestPrefixAlsoSuffix);
        return s + pattern;


    }

    private int[] prefixWhichIsAlsoSuffix(final String pattern) {

        int prefixWhichIsAlsoSuffix[] = new int[pattern.length()];
        prefixWhichIsAlsoSuffix[0] = 0;
        int i = 1;
        int j = 0;

        while (i < pattern.length()) {

            if (pattern.charAt(i) == pattern.charAt(j))
                prefixWhichIsAlsoSuffix[i++] = ++j;
            else if (j > 0)
                j = prefixWhichIsAlsoSuffix[j - 1];
            else
                prefixWhichIsAlsoSuffix[i++] = 0;

        }

        return prefixWhichIsAlsoSuffix;
    }
}