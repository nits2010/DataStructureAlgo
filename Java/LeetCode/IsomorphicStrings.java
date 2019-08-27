package Java.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-27
 * Description:https://leetcode.com/problems/isomorphic-strings/
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        test("egg", "add", true);
        test("foo", "bar", false);
        test("paper", "title", true);
    }

    private static void test(String s, String t, boolean expected) {
        IsomorphicStringsUsingHash.IsomorphicStringsUsingHash1 hash1 = new IsomorphicStringsUsingHash.IsomorphicStringsUsingHash1();
        IsomorphicStringsUsingHash.IsomorphicStringsUsingHash2 hash2 = new IsomorphicStringsUsingHash.IsomorphicStringsUsingHash2();
        IsomorphicStringsUsingHash.IsomorphicStringsUsingHash3 hash3 = new IsomorphicStringsUsingHash.IsomorphicStringsUsingHash3();
        IsomorphicStringsUsingArray array = new IsomorphicStringsUsingArray();
        System.out.println(" s: " + s + " t: " + t + " expected :" + expected);

        System.out.println("Hash1 :" + hash1.isIsomorphic(s, t));
        System.out.println("Hash2 :" + hash2.isIsomorphic(s, t));
        System.out.println("hash3 :" + hash3.isIsomorphic(s, t));
        System.out.println("array :" + array.isIsomorphic(s, t));
    }
}

class IsomorphicStringsUsingArray {


    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Isomorphic Strings.
     * Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Isomorphic Strings.
     *
     * @param s1
     * @param t1
     * @return
     */
    public boolean isIsomorphic(String s1, String t1) {


        if (s1 == null || s1.isEmpty())
            return (t1 == null || t1.isEmpty());

        if (s1.length() != t1.length())
            return false;


        char[] s = s1.toCharArray();
        char[] t = t1.toCharArray();

        int length = s1.length();

        char[] sm = new char[256];
        char[] tm = new char[256];

        for (int i = 0; i < length; i++) {
            char sc = s[i];
            char tc = t[i];

            /**
             * Have u seen sc and tc before?
             */
            if (sm[sc] == 0 && tm[tc] == 0) {
                /**
                 * If not, create a mapping from sc -> tc
                 */
                sm[sc] = tc;
                tm[tc] = sc;

            } else {
                /**
                 * If seen, then they must be same mapping
                 */
                if (sm[sc] != tc || tm[tc] != sc) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Runtime: 13 ms, faster than 31.52% of Java online submissions for Isomorphic Strings.
     * Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Isomorphic Strings.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {

        if (s == null || s.isEmpty())
            return (t == null || t.isEmpty());

        if (s.length() != t.length())
            return false;


        int hash1[] = new int[256];
        int hash2[] = new int[256];

        Arrays.fill(hash1, -1);
        Arrays.fill(hash2, -1);

        char ss[] = s.toCharArray();
        char tt[] = t.toCharArray();


        for (Integer i = 0; i < s.length(); i++) {

            if (hash1[ss[i]] != hash2[tt[i]])
                return false;
            hash1[ss[i]] = hash2[tt[i]] = i;
        }
        return true;

    }

}


class IsomorphicStringsUsingHash {

    /**
     * Runtime: 18 ms, faster than 15.95% of Java online submissions for Isomorphic Strings.
     * Memory Usage: 37.8 MB, less than 57.02% of Java online submissions for Isomorphic Strings.
     */
    static class IsomorphicStringsUsingHash3 {
        public boolean isIsomorphic(String s, String t) {

            if (s == null || s.isEmpty())
                return (t == null || t.isEmpty());

            if (s.length() != t.length())
                return false;


            Map<Object, Integer> map1 = new HashMap<>();

            for (Integer i = 0; i < s.length(); i++) {

                if (map1.put(s.charAt(i), i) != map1.put(t.charAt(i) + "", i))
                    return false;
            }
            return true;

        }
    }

    /**
     * {@link IsomorphicStringsUsingHash1} we iterate both string separately, that cause extra computation.
     * But if they are isomorphic then they share same length , which leads us to iterate both of them same time
     * Runtime: 15 ms, faster than 24.51% of Java online submissions for Isomorphic Strings.
     * Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Isomorphic Strings.
     */
    static class IsomorphicStringsUsingHash2 {
        public boolean isIsomorphic(String s, String t) {

            if (s == null || s.isEmpty())
                return (t == null || t.isEmpty());

            if (s.length() != t.length())
                return false;


            Map<Character, Integer> map1 = new HashMap<>();
            Map<Character, Integer> map2 = new HashMap<>();

            for (Integer i = 0; i < s.length(); i++) {

                if (map1.put(s.charAt(i), i) != map2.put(t.charAt(i), i))
                    return false;
            }
            return true;

        }

    }

    /**
     * It's slow because it iterate on both string
     * Runtime: 15 ms, faster than 24.51% of Java online submissions for Isomorphic Strings.
     * Memory Usage: 37.9 MB, less than 51.75% of Java online submissions for Isomorphic Strings.
     */
    static class IsomorphicStringsUsingHash1 {

        public boolean isIsomorphic(String s, String t) {

            if (s == null || s.isEmpty())
                return (t == null || t.isEmpty());

            if (s.length() != t.length())
                return false;
            return hash(s).equals(hash(t));

        }


        String hash(String s) {
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
                }
            }


            return hash.toString();
        }

    }

}