package DataStructureAlgo.Java.companyWise.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-31
 * Description: https://www.geeksforgeeks.org/anagram-substring-search-search-permutations/
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints
 * all occurrences of pat[] and its permutations (or anagrams) in txt[]. You may assume that n > m.
 * Expected time complexity is O(n)
 * <p>
 * Examples:
 * <p>
 * 1) Input:  txt[] = "BACDGABCDA"  pat[] = "ABCD"
 * Output:   Found at Index 0
 * Found at Index 5
 * Found at Index 6
 * 2) Input: txt[] =  "AAABABAA" pat[] = "AABA"
 * Output:   Found at Index 0
 * Found at Index 1
 * Found at Index 4
 * <p>
 * {@link DataStructureAlgo.Java.LeetCode.FindAllAnagramsString}
 */
public class AnagramPermutationSearch {

    public static void main(String []args) {
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("zzzzzzzz", "zzzz"));
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("AAAA", "AAAAAA"));
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("BACDGABCDA", "ABCD"));
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("BACDGABCDA", "ABCDE"));
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("BACDGABCDA", "AB"));
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("BACDGABCDA", "CD"));
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("BACDGABCDA", ""));
        System.out.println(AnagramSubstringSearch.anagramSubstringSearch("BACDGABCDA", "BACDGABCDA"));
    }
}

class AnagramSubstringSearch {

    public static List<Integer> anagramSubstringSearch(String string, String pattern) {
        if (string == null || pattern == null || string.isEmpty() || pattern.isEmpty() || pattern.length() > string.length())
            return Collections.EMPTY_LIST;


        int[] patternC = patternCounter(pattern);

        int[] str = new int[256];

        int n = string.length();
        int p = pattern.length();


        if (n == p)
            return string.equals(pattern) ? List.of(0) : Collections.EMPTY_LIST;

        for (int i = 0; i < p; i++)
            str[string.charAt(i) - 'A']++;

        List<Integer> solution = new ArrayList<>();


        int s = 0;
        for (int i = p; i < n; i++) {

            if (match(str, patternC))
                solution.add(s);

            str[string.charAt(i) - 'A']++;
            str[string.charAt(s++) - 'A']--;


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

    private static int[] patternCounter(String pattern) {
        int patternC[] = new int[256];

        for (char c : pattern.toCharArray())
            patternC[c - 'A']++;

        return patternC;
    }
}