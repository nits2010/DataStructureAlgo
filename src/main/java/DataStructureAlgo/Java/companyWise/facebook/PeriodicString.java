package DataStructureAlgo.Java.companyWise.facebook;

import DataStructureAlgo.Java.Pair;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-20
 * Description: https://www.geeksforgeeks.org/find-given-string-can-represented-substring-iterating-substring-n-times/
 * <p>
 * Find if a given string can be represented from a substring by iterating the substring “n” times
 * Given a string ‘str’, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * Examples:
 * <p>
 * Input: str = "abcabcabc"
 * Output: true
 * The given string is 3 times repetition of "abc"
 * <p>
 * Input: str = "abadabad"
 * Output: true
 * The given string is 2 times repetition of "abad"
 * <p>
 * Input: str = "aabaabaabaab"
 * Output: true
 * The given string is 4 times repetition of "aab"
 * <p>
 * Input: str = "abcdabc"
 * Output: false
 * <p>
 * Otherway
 * of asking
 * Find whether string S is periodic.
 * Periodic indicates S = nP.
 * e.g.
 * S = "ababab", then n = 3, and P = "ab"
 * S = "xxxxxx", then n = 1, and P = "x"
 * S = "aabbaaabba", then n = 2, and P = "aabba"
 * <p>
 * follow up:
 * Given string S, find out the P (repetitive pattern) of S.
 * <p>
 * [FACEBOOK] [GOOGLE]
 */
public class PeriodicString {

    public static void main(String []args) {

        String text[] = {"ABCABC", "ABABAB", "ABCDABCD", "GEEKSFORGEEKS",
                "GEEKGEEK", "AAAACAAAAC", "ABCDABC"};
        boolean ans[] = {true, true, true, false, true, true, false};

        StringNTimesRepeat periodicString = new StringNTimesRepeat();

        for (int i = 0; i < text.length; i++) {
            Pair<Boolean, String> sol = periodicString.isPeriodicString(text[i]);
            System.out.println(" String : " + text[i] + " ;Expected " + ans[i] + " obtained: " + sol.getKey() + " Repetitive pattern : " + sol.getValue());
        }
    }


}

class StringNTimesRepeat {

    /**
     * Let the given string be ‘str’ and length of given string be ‘n’.
     * <p>
     * 1) Find length of the longest proper prefix of ‘str’ which is also a suffix. Let the length of the longest proper prefix suffix be ‘len’.
     * This can be computed in O(n) time using pre-processing step of KMP string matching algorithm.
     * <p>
     * 2) If value of ‘n – len’ divides n (or ‘n % (n-len)’ is 0), then return true, else return false.
     * <p>
     * In case of ‘true’ , the substring ‘str[0..n-len-1]’ is the substring that repeats n%(n-len) times.
     * <p>
     * Let us take few examples.
     * <p>
     * Input: str = “ABCDABCD”, n = 8 (Number of characters in ‘str’)
     * The value of len is 4 (“ABCD” is the longest substring which is both prefix and suffix)
     * Since (n-len) divides n, the answer is true.
     * <p>
     * Input: str = “ABCDABC”, n = 7 (Number of characters in ‘str’)
     * The value of len is 3 (“ABC” is the longest substring which is both prefix and suffix)
     * Since (n-len) does not divides n, the answer is false.
     * <p>
     * Input: str = “ABCABCABCABCABC”, n = 15 (Number of characters in ‘str’)
     * The value of len is 12 (“ABCABCABCABC” is the longest substring which is both prefix and suffix)
     * Since (n-len) divides n, the answer is true.
     * <p>
     * How does this work?
     * length of longest proper prefix-suffix (or len) is always between 0 to n-1. If len is n-1, then all characters in string are same.
     * For example len is 3 for “AAAA”. If len is n-2 and n is even, then two characters in string repeat n/2 times. For example “ABABABAB”, length of lps is 6.
     * The reason is if the first n-2 characters are same as last n-2 character, the starting from the first pair, every pair of characters is identical to the next pair.
     * The following diagram demonstrates same for substring of length 4.
     *
     * @param str
     * @return
     */
    public Pair<Boolean, String> isPeriodicString(String str) {

        if (str == null || str.isEmpty())
            return new Pair<>(true, str);

        int n = str.length();

        //This len will denote that for a particular string is repeated how many times. S = nP ; len = n
        int len = prefixAlsoSuffix(str)[n - 1];


        //has any string repeats at all
        if (len <= 0)
            return new Pair<>(false, null);

        int lengthOfRepetitivePattern = n - len;

        if (n % lengthOfRepetitivePattern == 0)
            return new Pair<>(true, str.substring(0, lengthOfRepetitivePattern)); //follow up: Given string S, find out the P (repetitive pattern) of S.

        else
            return new Pair<>(false, null);


    }

    /**
     * Build prefix that is also suffix lengths.
     *
     * @param str
     * @return
     */
    private int[] prefixAlsoSuffix(String str) {

        /**
         * Let prefixAlsoSuffixLength[i] represent that length of longest prefix which is also suffice at index i
         */
        int prefixAlsoSuffixLength[] = new int[str.length()];
        prefixAlsoSuffixLength[0] = 0; //0 length prefix also suffix at 0 the index

        int i = 1, j = 0; //j as the length of longest prefix which is also suffix

        while (i < str.length()) {

            if (str.charAt(i) == str.charAt(j)) {
                //this match, we got at least 1 length prefix which is also suffix; increase the length
                ++j;

                prefixAlsoSuffixLength[i] = j;

                i++; //consider next character

            } else if (j - 1 > 0)
                j = prefixAlsoSuffixLength[j - 1];
            else {
                prefixAlsoSuffixLength[i] = 0;
                i++;
            }

        }


        return prefixAlsoSuffixLength;
    }


}