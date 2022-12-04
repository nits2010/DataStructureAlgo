package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-17
 * Description: https://leetcode.com/problems/valid-palindrome/ [Asked in Facebook]
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 * <p>
 * Input: "race a car"
 * Output: false
 * [Facebook]
 */
public class ValidPalindromeInSentence {

    public static void main(String arts[]) {
        SolutionValidPalindromeInSentence sol = new SolutionValidPalindromeInSentence();
        System.out.println(sol.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(sol.isPalindrome("race a car"));
    }
}


class SolutionValidPalindromeInSentence {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1)
            return true;

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {

            char ll = s.charAt(l);
            char rr = s.charAt(r);

            if (isValidAlphaNumeric(ll)) {

                if (isValidAlphaNumeric(rr)) {

                    if (Character.toLowerCase(ll) != Character.toLowerCase(rr))
                        return false;
                    l++;
                    r--;
                } else
                    r--;

            } else
                l++;
        }

        if (l >= r)
            return true;
        return false;
    }

    private boolean isValidAlphaNumeric(char ll) {
        if ((ll >= '0' && ll <= '9') || (ll >= 'a' && ll <= 'z') || (ll >= 'A' && ll <= 'Z'))
            return true;
        return false;
    }
}