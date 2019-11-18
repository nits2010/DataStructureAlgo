package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-02
 * Description: https://leetcode.com/problems/valid-anagram/
 * 242. Valid Anagram
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * <p>
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class ValidAnagram {

    public static void main(String[] args) {
        System.out.println(" Anagram :" + isAnagram("anagram", "nagaram") + " expected : True");
        System.out.println(" Anagram :" + isAnagram("rat", "car") + " expected : False");
    }

    //Runtime: 2 ms, faster than 99.88% of Java online submissions for Valid Anagram.
    //Memory Usage: 35.9 MB, less than 98.06% of Java online submissions for Valid Anagram.
    public static boolean isAnagram(String s, String t) {

        return isAnagramLowerCase(s, t);
    }

    public static boolean isAnagramLowerCase(String s, String t) {

        if (s == null)
            return t == null;

        if (s.isEmpty())
            return t.isEmpty();

        int chars[] = new int[26];

        for (char c : s.toCharArray())
            chars[c - 'a']++;

        for (char c : t.toCharArray())
            if (--chars[c - 'a'] < 0)
                return false;

        for (int i = 0; i < chars.length; i++)
            if (chars[i] != 0)
                return false;

        return true;
    }

    //What if the inputs contain unicode characters? How would you adapt your solution to such case?
    public static boolean isAnagramUnicode(String s, String t) {

        if (s == null)
            return t == null;

        if (s.isEmpty())
            return t.isEmpty();

        int chars[] = new int[256];

        for (char c : s.toCharArray())
            chars[c]++;

        for (char c : t.toCharArray())
            if (--chars[c] < 0)
                return false;

        for (int i = 0; i < chars.length; i++)
            if (chars[i] != 0)
                return false;

        return true;
    }

}
