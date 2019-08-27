package Java.companyWise.Amazon;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-27
 * Description: https://leetcode.com/discuss/interview-question/368038/amazon-onsite-group-isomorphic-strings
 * <p>
 * Given an array of strings, you need to group isomorphic strings {@link Java.LeetCode.IsomorphicStrings} together.
 * <p>
 * Example:
 * <p>
 * Input: ["apple", "apply", "dog", "cog", "romi"]
 * Output: [["dog", "cog"], ["romi"], ["apple", "apply"]]
 */

public class GroupIsomorphicStrings {


    public static void main(String[] args) {
        test(Arrays.asList("apple", "apply", "dog", "cog", "romi"));
    }

    private static void test(List<String> strings) {
        System.out.println("\n Input " + strings);

        GroupIsomorphicStringsUsingHash hash = new GroupIsomorphicStringsUsingHash();
        System.out.println("Hash->Grouped :" + hash.groupIsomorphicStrings(strings));
    }
}

/**
 * Brute force:
 * Go through each pair and check does they are isomorphic string. {@link Java.LeetCode.IsomorphicStrings} #IsomorphicStringsUsingArray
 * Each isomorphic check takes O(L) where L is the maxSize of string
 * For each pair-> O(n^2)
 * Overall
 * O(L*n^2)
 * <p>
 * Optimization
 * We can leverage the hash logic from {@link Java.LeetCode.IsomorphicStrings} #IsomorphicStringsUsingHash
 * to group them
 * <p>
 * Algo:
 * 1. Go through each string, generate hash; O(n * L )
 * 2. Group them on the fly
 * <p>
 * Overall : O( n * L )
 */
class GroupIsomorphicStringsUsingHash {

    public Collection<List<String>> groupIsomorphicStrings(List<String> strings) {
        if (strings == null || strings.isEmpty())
            return Collections.EMPTY_LIST;

        Map<String, List<String>> hashToList = new HashMap<>();

        for (String string : strings) {

            String hash = hash(string);

            if (!hashToList.containsKey(hash))
                hashToList.put(hash, new ArrayList<>());

            hashToList.get(hash).add(string);


        }
        return hashToList.values();
    }


    //"apple" -> 12234 & "apply" -> 12234
    //"dog" -> 123 & cog -> 123
    private String hash(String s) {
        if (s.isEmpty())
            return "";

        int count = 1;
        StringBuilder hash = new StringBuilder();

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {

            if (map.containsKey(c))
                hash.append(map.get(c));
            else {
                map.put(c, count++);
                hash.append(map.get(c));
            }
        }
        return hash.toString();
    }


}