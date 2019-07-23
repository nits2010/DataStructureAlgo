package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-19
 * Description: https://leetcode.com/problems/custom-sort-string/
 * <p>
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 * <p>
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 * <p>
 * Return any permutation of T (as a string) that satisfies this property.
 * <p>
 * Example :
 * Input:
 * S = "cba"
 * T = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 * <p>
 * <p>
 * Note:
 * <p>
 * S has length at most 26, and no character is repeated in S.
 * T has length at most 200.
 * S and T consist of lowercase letters only.
 *
 * [FACEBOOK]
 */
public class CustomSortString {

    public static void main(String args[]) {
        SolutionCustomSortString sol = new SolutionCustomSortString();
        System.out.println(sol.customSortString("cba", "abcd") + " expected :" + "cbad");
        System.out.println(sol.customSortString("bca", "abc") + " expected :" + "bca");
        System.out.println(sol.customSortString("bxyzca", "abcabcabc") + " expected :" + "bbbcccaaa");
        System.out.println(sol.customSortString("wcyuogmlrdfphitxjakqvzbnes", "jcdokai") + " expected :" + "codijak");

        System.out.println(sol.customSortString("hucw", "utzoampdgkalexslxoqfkdjoczajxtuhqyxvlfatmptqdsochtdzgypsfkgqwbgqbcamdqnqztaqhqanirikahtmalzqjjxtqfnh")
                + " expected :" + "hhhhhuucccwaaaaaaaaabbdddddeffffggggiijjjjkkkkllllmmmmnnnoooopppqqqqqqqqqqqrsssttttttttvxxxxxyyzzzzz");


    }

}

/**
 * 1. count how many times a char is present in given string "toSort" O(N)
 * 2. Append a char from order if it present in "toSort" as many times it occurred in "toSort" -> -> O(N+26)
 *
 * 3. It may possible that some of the character which is in "toSort" are not there in "order".
 * Find them and push them in string in lexicographical order O(N+26)
 * O(N)
 */
class SolutionCustomSortString {

    public String customSortString(String S, String T) {
        return sort(S, T);
    }

    private String sort(String order, String toSort) {

        if (toSort == null || toSort.isEmpty() || order.isEmpty())
            return toSort;

        //counter
        int count[] = new int[26];

        //Find the occurrence of each char in "toSort" string
        for (int i = 0; i < toSort.length(); i++)
            count[toSort.charAt(i) - 'a']++;


        StringBuilder sorted = new StringBuilder();

        for (char c : order.toCharArray()) {

            while (count[c - 'a'] > 0) {
                sorted.append(c);
                count[c - 'a']--;
            }
        }

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                sorted.append((char) (i + 'a'));
                count[i]--;
            }
        }

        return sorted.toString();
    }
}