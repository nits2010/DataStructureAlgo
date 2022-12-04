package DataStructureAlgo.Java.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-15
 * Description: https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input:
 * s: "abab" p: "ab"
 * <p>
 * Output:
 * [0, 1, 2]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * {@link DataStructureAlgo.Java.companyWise.facebook.AnagramPermutationSearch} extension of above
 */
public class FindAllAnagramsString {


    public static void main(String []args) {
        System.out.println(anagramSubstringSearch("zzzzzzzz", "zzzz"));
        System.out.println(anagramSubstringSearch("aaaaa", "aaaaaaa"));
        System.out.println(anagramSubstringSearch("cbaebabacd", "abc"));
        System.out.println(anagramSubstringSearch("abab", "ab"));
    }

    /**
     * Runtime: 10 ms, faster than 61.88% of Java online submissions for Find All Anagrams in a String.
     * Memory Usage: 38.2 MB, less than 100.00% of Java online submissions for Find All Anagrams in a String.
     *
     * @param string
     * @param pattern
     * @return
     */
    public static List<Integer> anagramSubstringSearch(String string, String pattern) {
        if (string == null || pattern == null || string.isEmpty() || pattern.isEmpty() || pattern.length() > string.length())
            return Collections.EMPTY_LIST;


        int patternC[] = new int[26];

        int str[] = new int[26];

        int n = string.length();
        int p = pattern.length();


        if (n == p)
            return string.equals(pattern) ? Arrays.asList(0) : Collections.EMPTY_LIST;

        for (int i = 0; i < p; i++) {
            str[string.charAt(i) - 'a']++;
            patternC[pattern.charAt(i) - 'a']++;
        }

        List<Integer> solution = new ArrayList<>();


        int s = 0;
        for (int i = p; i < n; i++) {

            if (match(str, patternC))
                solution.add(s);

            str[string.charAt(i) - 'a']++;
            str[string.charAt(s++) - 'a']--;


        }
        if (match(str, patternC))
            solution.add(s);

        return solution;

    }

    private static boolean match(int a[], int b[]) {
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i])
                return false;

        return true;
    }


}
