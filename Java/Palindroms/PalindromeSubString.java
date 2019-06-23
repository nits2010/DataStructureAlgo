package Java.Palindroms;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-23
 * Description:
 */
public class PalindromeSubString {

    public static void main(String str[]) {
        System.out.println(longestPalindrome("babad"));
    }

    //O(n^2)
    public static String longestPalindrome(String s) {

        int n = s.length();
        int max = 1;
        int start = 0;
        int l, r;

        for (int i = 1; i < n; i++) {

            //Odd length
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {

                if (r - l + 1 > max) {
                    max = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }

            //even length
            l = i - 1;
            r = i;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {

                if (r - l + 1 > max) {
                    max = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }


        }


        return s.substring(start, start + max - 1);
    }
}
