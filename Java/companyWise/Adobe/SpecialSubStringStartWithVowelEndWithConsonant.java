package Java.companyWise.Adobe;

import Java.SuffixArray;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-24
 * Description: https://leetcode.com/discuss/interview-question/125520/Special-Subsequence
 * <p>
 * Consider a string, s = "abc". An alphabetically-ordered sequence of substrings of s would be {"a", "ab", "abc", "b", "bc", "c"}.
 * If we reduce this sequence to only those substrings that start with a vowel and end with a consonant, we're left with {"ab", "abc"}. The alphabetically first element in this reduced
 * list is "ab", and the alphabetically last element is "abc". As a reminder:
 * Vowels: a, e, i, o, and u.
 * Consonants: b, c, d, f, g, h, j, k, l, m, n, p, q, r, s, t, v, w, x, y, and z.
 * <p>
 * Complete the findSubstrings function in your editor. It has 1 parameter: a string, s, consisting of lowercase English letters (a − z).
 * The function must find the substrings of s that start with a vowel and end with a consonant, then print the alphabetically first and alphabetically last of these substrings.
 * <p>
 * Input Format
 * The locked stub code in your editor reads a single string, s, from stdin and passes it to your function.
 * <p>
 * Constraints
 * 3 ≤ length of s ≤ 5 × 10^5
 * <p>
 * Output Format
 * Your function must print two lines of output denoting the alphabetically first and last substrings of s that start with a vowel and end with a consonant.
 * Print the alphabetically first qualifying substring on the first line, and the alphabetically last qualifying substring on the second line.
 * <p>
 * Sample Input 1
 * aba
 * <p>
 * Sample Output 1
 * ab
 * ab
 * <p>
 * Explanation 1
 * "ab" is the only possible substring which starts with a vowel (a) and ends with a consonant (b). Because we only have 1 qualifying substring, "ab" is both the alphabetically
 * first and last qualifying substring and we print it as our first and second lines of output.
 * <p>
 * Sample Input 2
 * aab
 * <p>
 * Sample Output 2
 * aab
 * ab
 * <p>
 * Explanation 2
 * There are 2 possible substrings which start with a vowel and end with a consonant: "aab" and "ab". When ordered alphabetically, "aab" comes before "ab".
 * This means that we print "aab" (the alphabetically first qualifying substring) as our first line of output, and we print "ab" (the alphabetically last qualifying substring)
 * as our second line of output.
 * <p>
 * <p>
 * Sample Input 3
 * rejhiuecumovsutyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfhe
 * <p>
 * Sample Output 3
 * aaop
 * utyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfh
 * <p>
 * Explanation 3
 * There are 4830 substrings of s, but only 676 of them start with a vowel and end with a consonant. When ordered alphabetically,
 * the first substring is "aaop" and the last substring is "utyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfh".
 */
public class SpecialSubStringStartWithVowelEndWithConsonant {

    public static void main(String[] args) {
        test("abc", Arrays.asList("ab", "abc"));
        test("aba", Arrays.asList("ab", "ab"));
        test("aab", Arrays.asList("aab", "ab"));
        test("rejhiuecumovsutyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfhe", Arrays.asList("aaop", "utyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfh"));
        test("rejhiuecumovsutyrckzfhe", Arrays.asList("ec", "utyrckzfh"));
        test("rejhiuec", Arrays.asList("ec", "uec"));
        test("aabeboxuw", Arrays.asList("aab", "uw"));

    }

    private static void test(String string, List<String> expected) {
        System.out.println("Input : " + string);
        System.out.println(" \n Expected :" + expected);
        System.out.println("Obtained :" + SpecialSubStringStartWithVowelEndWithConsonantBruteForce.findSubstrings(string));
        System.out.println("optimized :" + SpecialSubStringStartWithVowelEndWithConsonantIndexSearching.findSubstrings(string));
        System.out.println("Suffix Array :" + SpecialSubStringStartWithVowelEndWithConsonantSuffixArray.findSubstrings(string));
    }


}


class SpecialSubStringStartWithVowelEndWithConsonantBruteForce {
    //==============================================================Brute Force=====================================================================================

    /*

    Algorithm:
    1. Generate all sub-string O(n^2)
    2. Filter all sub-string which follow the property: Start with Vowel and end with Consonant: O(n^2) After filter we'll have at least vowel number of sub-string; Say m is upper bound
    3. Sort this list O(m * log(m) )
    4. and return first and last string  O(1)

    We can combine step 1 and 2
    Complexity:
    Time: O(m * log(m)) Since m >> n  but m*log(m) > O(n^2)
    Space: O(n^2)

     */
    public static List<String> findSubstrings(String str) {

        if (str == null || str.isEmpty())
            return Collections.EMPTY_LIST;

        final List<String> subStrings = new ArrayList<>();

        int n = str.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String s = str.substring(i, j);
                if (s.startsWith("a") || s.startsWith("e") || s.startsWith("i") || s.startsWith("o") || s.startsWith("u")) {
                    if (!s.endsWith("a") && !s.endsWith("e") && !s.endsWith("i") && !s.endsWith("o") && !s.endsWith("u")) {
                        subStrings.add(s);
                    }
                }
            }
        }

        Collections.sort(subStrings);

        if (subStrings.size() == 0)
            return Collections.EMPTY_LIST;
        else if (subStrings.size() == 1)
            return Arrays.asList(subStrings.get(0), subStrings.get(0));
        else
            return Arrays.asList(subStrings.get(0), subStrings.get(subStrings.size() - 1));

    }
}

class SpecialSubStringStartWithVowelEndWithConsonantIndexSearching {
    //========================================================================Better approach: O(n^2)========================================================================================


    /*

    Algorithm:
    We know that we are only interested in only those sub-string which start with vowel and end with consonants.
    And as we required only those sub-string which are lexically first and lexically last after applying the rule. Which means that lexical order depends on vowel order.
    So,
     1. to find lexically smallest, we can find the that "smallest" sub-string which start with vowel and end with consonant.
     1. to find lexically largest, we can find the that "largest" sub-string which start with vowel and end with consonant.


    Algorithm:
    1. Find the index of vowels and keep them in in order of  {a,e,i,o,u}
    2. To find the smallest, we need to see which one of them gives the smallest in order {a,e,i,o,u}
    2.1 if a exist then it will be from a...or e..or i....u
    2.2 similarly the largest would be from u...o..i..e..a

    Complexity:
    The worst case occurs when given string has n-1 vowels and only one consonants.
    And When it has all different vowels and each vowel has n/10 occurrence at different places.
    example : axaxaxaxexexexexixixixixoxoxoxoxuxuxuxu; n = 40 and each has 4 indexes
    then sub-string will run n/10(for a) * n/10(for u) times, which makes it O((n/10)*n) => O(n^2)

     */

    /**
     *
     * @param str
     * @return
     */
    public static List<String> findSubstrings(String str) {
        if (str == null || str.isEmpty())
            return Collections.EMPTY_LIST;

        Map<Character, List<Integer>> map = new HashMap<>();

        int n = str.length();
        char[] chars = str.toCharArray();
        boolean consonants = false;

        for (int i = 0; i < n; i++) {
            char c = chars[i];

            if (isVowel(c)) {
                if (!map.containsKey(c))
                    map.put(c, new ArrayList<>());

                if (!map.get(c).isEmpty()) {
                    int last = map.get(c).get(map.get(c).size() - 1);
                    if (i > last && i != last + 1)
                        map.get(c).add(i);
                } else
                    map.get(c).add(i);


            } else
                consonants = true;

        }

        /**
         * if there is no consonants, than we can't form the sub-string
         */
        if (!consonants)
            return Collections.EMPTY_LIST;


        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        final List<String> subStrings = new ArrayList<>();

        int v = 0;
        String[] solution = null;
        for (; v < vowels.length; v++) {
            char vowel = vowels[v];
            if (map.containsKey(vowel)) {
                solution = subString(chars, map.get(vowel));
                break;
            }
        }

        if (v == map.size() - 1)
            return Arrays.asList(solution);
        else {
            subStrings.add(solution[0]);
            int x = vowels.length - 1;

            for (; x > v; x--) {

                char vowel = vowels[x];

                if (map.containsKey(vowel)) {
                    solution = subString(chars, map.get(vowel));
                    break;
                }
            }

            subStrings.add(solution[1]);
        }


        return subStrings;
    }


    private static String[] subString(char[] str, List<Integer> indexes) {

        String first = null;
        String last = null;

        for (int i : indexes) {

            StringBuilder builder = new StringBuilder();
            int x = i;
            while (x < str.length) {
                builder.append(str[x++]);

                if (!isVowel(builder.charAt(builder.length() - 1))) {
                    String temp = builder.toString();
                    if (first != null)
                        first = first.compareTo(temp) < 0 ? first : temp;
                    else
                        first = temp;

                    if (last != null)
                        last = last.compareTo(temp) > 0 ? last : temp;
                    else
                        last = temp;
                }

            }
        }

        return new String[]{first, last};

    }


    private static boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        return false;
    }


}


class SpecialSubStringStartWithVowelEndWithConsonantSuffixArray {

    //========================================================================Optimized approach: O(n)========================================================================================

    /*
       Above algorithm surely cut many branches that we should not explore but algorithm lags in finding the sub-string efficiently.
       We can use suffix array. Suffix array can be build in O(n^2) or O(n*log(n) * log(n) ) or O(nLog(n)) Or even O(n) time.
                  0  1   2
        example [ a, b, ,c ] -> Suffixes array [ abc, bc, c ]
       ALSO FIND THE last consonant index {this will help us to find the first and last sub-string }

       After build suffix array,
       Now going through suffix array order see if the suffix starts with a vowel and the position is less than that of the last consonant
       otherwise continue going through the loop. For the first suffix that satisfies the above criteria run a loop and find the first consonant. That will be the first string.

       For the second string do the same just iterate through the suffix array from the last and once you hit a vowel, start building the sub-string from that index to last consonant index

        Time complexity: O(n)
        Space: O(n)

        Below implementation is : O(n * (log(n))^2) Because of suffix array

     */

    public static List<String> findSubstrings(String str) {
        if (str == null || str.isEmpty())
            return Collections.EMPTY_LIST;

        int lastIndexOfConsonant = str.length()-1;
        boolean isConsonant = false;

        for (int i = str.length() - 1; i >= 0; i--) {

            if (isVowel(str.charAt(i))) {
                lastIndexOfConsonant--;

            } else {

                isConsonant = true;
                break;
            }

        }

        /**
         * String does not have vowel or does not has consonant
         */
        if (lastIndexOfConsonant == -1 || !isConsonant)
            return Collections.EMPTY_LIST;

        int suffixArray[] = SuffixArray.suffixArray(str);

        String firstString = fistSubString(str.toCharArray(), suffixArray, lastIndexOfConsonant);
        String lastString = lastSubString(str.toCharArray(), suffixArray, lastIndexOfConsonant);

        return Arrays.asList(firstString, lastString);

    }

    /**
     * Now going through suffix array order see if the suffix starts with a vowel and the position is less than that of the last consonant
     * otherwise continue going through the loop. For the first suffix that satisfies the above criteria run a loop and find the first consonant. That will be the first string.
     *
     * @param str
     * @param suffixArray
     * @param lastIndexOfConsonant
     * @return
     */
    private static String fistSubString(char str[], int suffixArray[], int lastIndexOfConsonant) {

        StringBuilder string = new StringBuilder();

        for (int i = 0; i < str.length; i++) {

            /**
             * Find the sub-string which has first char as vowel and has index less than lastConsonant index.
             * Since if string has only vowel/consonant then it will never true
             */
            if (isVowel(str[suffixArray[i]]) && suffixArray[i] < lastIndexOfConsonant) {
                int index = suffixArray[i];
                string.append(str[index++]);

                for (; index < str.length; index++) {

                    if (isVowel(str[index]))
                        string.append(str[index]);
                    else {
                        string.append(str[index]);
                        break;
                    }
                }
                break;
            }
        }

        return string.toString();
    }

    /**
     * For the second string do the same just iterate through the suffix array from the last and once you hit a vowel, start building the sub-string from that index to last consonant index
     *
     * @param str
     * @param suffixArray
     * @param lastIndexOfConsonant
     * @return
     */
    private static String lastSubString(char str[], int suffixArray[], int lastIndexOfConsonant) {
        StringBuilder string = new StringBuilder();

        for (int i = str.length - 1; i >= 0; i--) {

            /**
             * Find the sub-string which has first char as vowel and has index less than lastConsonant index.
             * Since if string has only vowel/consonant then it will never true
             */
            if (isVowel(str[suffixArray[i]]) && suffixArray[i] < lastIndexOfConsonant) {
                int index = suffixArray[i];
                string.append(str[index++]);

                for (; index <= lastIndexOfConsonant; index++) {
                    string.append(str[index]);
                }
                break;
            }


        }

        return string.toString();
    }

    private static boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        return false;
    }


}