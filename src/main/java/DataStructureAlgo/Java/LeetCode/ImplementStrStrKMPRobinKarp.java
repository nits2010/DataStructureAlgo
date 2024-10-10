package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.nonleetcode.KMP_KnuthMorrisPratt;

import java.util.Objects;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-19
 * Description: https://leetcode.com/problems/implement-strstr/
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 * <p>
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when the needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 * <p>
 * {@link KMP_KnuthMorrisPratt}
 */
public class ImplementStrStrKMPRobinKarp {

    public static void main(String[] args) {
        test("", "ll");
        test("", "");
        test("asd", "");
        test("hello", "ll");
        test("aaaaa", "bba");
        test("helloworld", "or");
    }

    private static void test(String haystack, String needle) {
        KMPWithPreProcessing kmp = new KMPWithPreProcessing();
        RobinKarp robinKorp = new RobinKarp();
        System.out.println("Obtained: " + kmp.strStr(haystack, needle) + " Expected " + haystack.indexOf(needle));
        System.out.println("RK Obtained: " + robinKorp.strStr(haystack, needle) + " Expected " + haystack.indexOf(needle));
    }


}


/**
 * When a String is are equal length, then compare it directly.
 * The important optimization is to compare both from start & middle parallel
 * <p>
 * Runtime: 0 ms, faster than 100% of Java online submissions for Implement strStr().
 * Memory Usage: 36.1 MB, less than 100% of Java online submissions for Implement strStr().
 */
class KMPWithPreProcessing {

    public int strStr(String haystack, String needle) {
        if ((haystack == null && needle == null) || (Objects.requireNonNull(haystack).isEmpty() && needle.isEmpty()))
            return 0;

        if (haystack.isEmpty())
            return -1;

        if (needle == null || needle.isEmpty())
            return 0;

        /**
         * When the Lengths of both are same, then it's not good to apply KMP as it will take more time than just
         * comparing the string directly
         *
         */
        if (needle.length() == haystack.length()) {
            return preProcess(haystack, needle);
        } else
            return kmpMatch(haystack, needle);
    }

    /**
     * When Length of both are same, then it's not good to apply KMP as it will take more time than just
     * comparing the string directly
     * <p>
     * O(n/2)
     *
     * @param haystack
     * @param needle
     * @return
     */
    private int preProcess(String haystack, String needle) {

        /**
         * Since the string is same, we can compare the half-part on each side. This will reduce the overall computation.
         * This will like two pointers, one which matching from start, and the other is matching from the middle.
         */
        for (int i = 0, len = needle.length() >> 1; i < len; i++) {

            if (haystack.charAt(i) != needle.charAt(i)
                    || haystack.charAt(needle.length() - 1 - i) != needle.charAt(needle.length() - 1 - i)) {

                return -1;
            }
        }

        return 0;

    }

    private int kmpMatch(String haystack, String needle) {

        int[] prefixWhichIsAlsoSuffix = prefixWhichIsAlsoSuffix(needle);

        int i = 0;
        int j = 0;

        while (i < haystack.length()) {

            if (haystack.charAt(i) == needle.charAt(j)) {

                if (j == needle.length() - 1)
                    return i - j;

                i++;
                j++;
            } else if (j > 0)
                j = prefixWhichIsAlsoSuffix[j - 1];
            else
                i++;
        }

        return -1;
    }


    private int[] prefixWhichIsAlsoSuffix(final String pattern) {

        int[] prefixWhichIsAlsoSuffix = new int[pattern.length()];
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


/**
 * https://brilliant.org/wiki/rabin-karp-algorithm/
 */
class RobinKarp {

    public int strStr(String haystack, String needle) {
        if ((haystack == null && needle == null) || (Objects.requireNonNull(haystack).isEmpty() && needle.isEmpty()))
            return 0;

        if (haystack.isEmpty())
            return -1;

        if (needle == null || needle.isEmpty())
            return 0;

        /**
         * When the Lengths of both are same, then it's not good to apply KMP as it will take more time than just
         * comparing the string directly
         *
         */
        if (needle.length() == haystack.length()) {
            return preProcess(haystack, needle);
        } else
            return robinKarp(haystack, needle);
    }

    private int robinKarp(String haystack, String needle) {
        int hash = needle.hashCode();

        //Hello / ll
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {

            String sub = haystack.substring(i, i + needle.length());
            if (hash == sub.hashCode())
                return i;

        }

        return -1;
    }

    /**
     * When Length of both are same, then it's not good to apply KMP as it will take more time then just
     * comparing the string directly
     * <p>
     * O(n/2)
     *
     * @param haystack
     * @param needle
     * @return
     */
    private int preProcess(String haystack, String needle) {

        /**
         * Since the string is same, we can compare the half-part on each side. This will reduce the overall computation.
         * This will like two pointers, one which is matching from the start, and the other is matching from the middle.
         */
        for (int i = 0, len = needle.length() >> 1; i < len; i++) {

            if (haystack.charAt(i) != needle.charAt(i)
                    || haystack.charAt(needle.length() - 1 - i) != needle.charAt(needle.length() - 1 - i)) {

                return -1;
            }
        }

        return 0;

    }
}


/**
 * O(n)
 * Runtime: 1 ms, faster than 67.75% of Java online submissions for Implement strStr().
 * Memory Usage: 37.9 MB, less than 67.17% of Java online submissions for Implement strStr().
 */
class KMPWithoutPreProcessing {

    public int strStr(String haystack, String needle) {
        if ((haystack == null && needle == null) || (Objects.requireNonNull(haystack).isEmpty() && needle.isEmpty()))
            return 0;

        if (haystack.isEmpty())
            return -1;

        if (needle == null || needle.isEmpty())
            return 0;


        return kmpMatch(haystack, needle);


    }

    private int kmpMatch(String haystack, String needle) {

        int[] prefixWhichIsAlsoSuffix = prefixWhichIsAlsoSuffix(needle);

        int i = 0;
        int j = 0;

        while (i < haystack.length()) {

            if (haystack.charAt(i) == needle.charAt(j)) {

                if (j == needle.length() - 1)
                    return i - j;

                i++;
                j++;
            } else if (j > 0)
                j = prefixWhichIsAlsoSuffix[j - 1];
            else
                i++;
        }

        return -1;
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
