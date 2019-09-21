package Java.LeetCode.Palindroms;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-19
 * Description: https://leetcode.com/problems/shortest-palindrome/
 * https://www.geeksforgeeks.org/minimum-characters-added-front-make-string-palindrome/
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * <p>
 * Example 1:
 * <p>
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome_MinimumNumberCharacterAddToFrontMakeStringPalindrome {

    public static void main(String[] args) {
        test("aacecaaa", "aaacecaaa");
        test("abcd", "dcbabcd");
        test("abcbabcab", "bacbabcbabcab");


    }

    private static void test(String str, String expected) {
        System.out.println("\n input: " + str);
        IShortestPalindrome brute = new ShortestPalindromeBruteForce();
        IShortestPalindrome kmp = new ShortestPalindromeKMP();

        System.out.println("Brute :" + brute.shortestPalindrome(str) + " expected :" + expected);
        System.out.println("KMP :" + kmp.shortestPalindrome(str) + " expected :" + expected);
    }
}

interface IShortestPalindrome {
    String shortestPalindrome(String s);
}

class ShortestPalindromeBruteForce implements IShortestPalindrome {

    /**
     * Reverse the string and compare the strings
     * https://leetcode.com/articles/shortest-palindrome/
     * <p>
     * O(n ^ 3)
     * <p>
     * <p>
     * Example:
     * s = abcbabcab -> Here longest palindrome sub-string from the beginning is "abcba". We need to find this sub-string
     * rev = bacbabcba
     * <p>
     * <p>
     * i=0;
     * abcbabcab !=bacbabcba
     * <p>
     * i=1
     * abcbabca != acbabcba
     * <p>
     * i=2
     * abcbabc != cbabcba
     * <p>
     * i=3
     * abcbab != babcba
     * <p>
     * i=4 [Length of 4]
     * abcba == abcba
     * <p>
     * Hence solution is
     * bacba + abcbabcab => bacbabcbabcab
     *
     * @param s
     * @return
     */
    @Override
    public String shortestPalindrome(String s) {

        if (s == null || s.isEmpty())
            return s;

        //O(n)
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        /**
         * Compare each sub-string
         */
        for (int i = 0; i < s.length(); i++) { //O(n)

            if (s.substring(0, n - i).equals(rev.substring(i))) //O(n){sub-string} * O(n){matching}
                return rev.substring(0, i) + s; //O(n)
        }


        return "";
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
 * {@link Java.KMP_KnuthMorrisPratt}
 * <p>
 * Runtime: 3 ms, faster than 76.30% of Java online submissions for Shortest Palindrome.
 * Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Shortest Palindrome.
 * <p>
 * https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation
 */
class ShortestPalindromeKMP implements IShortestPalindrome {

    /**
     * s = abcbabcab -> Here longest palindrome sub-string from the beginning is "abcba". We need to find this sub-string
     * rev = bacbabcba
     * <p>
     * joined = abcbabcab$bacbabcba
     * <p>
     * [a,b,c,b,a,b,c,a,b,$,b,a,c,b,a,b,c,b,a]
     * [0,0,0,0,1,2,3,1,2,0,0,1,0,0,1,2,3,4,5]
     * <p>
     * of length = 5; Length of longest palindrome
     * so; n - 5 = 9-5 = 4
     * bacb + abcbabcab => bacbabcbabcab
     *
     * @param s
     * @return
     */
    @Override
    public String shortestPalindrome(String s) {

        if (s == null || s.isEmpty())
            return s;

        String rev = new StringBuilder(s).reverse().toString();
        String joined = s + "$" + rev;

        int prefixWhichIsAlsoSuffix[] = prefixWhichIsAlsoSuffix(joined);

        int lengthOfLongestPrefixAlsoSuffix = prefixWhichIsAlsoSuffix[joined.length() - 1];

        String pattern = rev.substring(0, s.length() - lengthOfLongestPrefixAlsoSuffix);
        return pattern + s;


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