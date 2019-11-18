package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-15
 * Description: https://leetcode.com/problems/valid-palindrome-ii/
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "aba"
 * Output: True
 * Example 2:
 * <p>
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * <p>
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
public class ValidPalindromeII {
    public static void main(String []args) {
        SolutionValidPalindromeII sol = new SolutionValidPalindromeII();
        System.out.println(sol.validPalindrome("aba"));
        System.out.println(sol.validPalindrome("abc"));
        System.out.println(sol.validPalindrome("abca"));
    }
}

class SolutionValidPalindromeII {
    public boolean validPalindrome(String s) {

        if (s == null || s.isEmpty() || s.length() == 1)
            return true;

        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) { //alow only one char delete

                int j = s.length() - 1 - i;

                return (isPalindrome2(s, i + 1, j) || isPalindrome2(s, i, j - 1));
            }
        }

        return true;
    }


    private boolean isPalindrome2(String s, int l, int r) {

        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }

        if (l >= r)
            return true;
        return false;

    }
}