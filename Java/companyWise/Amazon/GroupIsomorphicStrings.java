package Java.companyWise.Amazon;

import java.util.*;

/**
 * Author: Nitin Gupta
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

            String hash = hash2(string);

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

            if (!map.containsKey(c)) {
                map.put(c, count++);
            }
            hash.append(map.get(c) + ","); //for cases like when counter=12 then 12 and actual 12(1 for a, 2 for b) should be different
            //"apple" -> 1,2,2,3,4 & "apply" -> 1,2,2,3,4. It will make "22" different from "2,2".
        }
        hash.deleteCharAt(hash.length() - 1);
        return hash.toString();
    }


    /**
     * There is a limitation on above hash. when the counter become >= 11 { two or more digits }
     * the it is difficult to distiguish between counter =11 and whne counter =1 
     * <p>
     * example:
     * ...aa... -> ..44..
     * ...zz.... -> ..44...
     * They are different may be but mapped to same
     * <p>
     * Two way:
     * 1. push "," as separator
     * 2. use character
     * <p>
     * we can use 'character' it self
     *
     * apple->abbcd
     * apply->abbcd
     *
     * romi->abcd
     *
     * @param s
     * @return
     */
    private String hash2(String s) {
        if (s.isEmpty())
            return "";

        char count = 'a';
        StringBuilder hash = new StringBuilder();

        Map<Character, Character> map = new HashMap<>();

        for (char c : s.toCharArray()) {

            if (!map.containsKey(c)) {
                map.put(c, count++);
            }
            hash.append(map.get(c));
        }
        System.out.println(hash);
        return hash.toString();
    }


}