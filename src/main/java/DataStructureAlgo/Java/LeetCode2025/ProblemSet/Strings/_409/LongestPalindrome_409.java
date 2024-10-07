package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._409;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 10/7/2024
 * Question Category: 409. Longest Palindrome
 * Description:
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest
 * palindrome
 * that can be built with those letters.
 * <p>
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2000
 * s consists of lowercase and/or uppercase English letters only.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @easy
 * @HashTable
 * @String
 * @Greedy <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Editorial https://leetcode.com/problems/longest-palindrome/editorial/
 *
 */
public class LongestPalindrome_409 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("abccccdd", 7);
        test &= test("a", 1);
        test &= test("ccc", 3);
        test &= test("dddddddaaaaaa", 13);
        test &= test("axaza", 3);
        test &= test("bb", 2);

        CommonMethods.printResult(test);


    }

    private static boolean test(String s, int expected) {
        System.out.println("----------------------------------");
        System.out.println("Sentence : " + s + " expected : " + expected);

        int output;
        boolean pass, finalPass = true;

        SolutionHashMap solutionHashMap = new SolutionHashMap();
        output = solutionHashMap.longestPalindrome(s);
        pass = output == expected;
        System.out.println("Output HashMap : " + output + " Pass : " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        SolutionHashMap2 solutionHashMap2 = new SolutionHashMap2();
        output = solutionHashMap2.longestPalindrome(s);
        pass = output == expected;
        System.out.println("Output HashMap2 : " + output + " Pass : " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;


        SolutionFixedArray solutionFixedArray = new SolutionFixedArray();
        output = solutionFixedArray.longestPalindrome(s);
        pass = output == expected;
        System.out.println("Output Array : " + output + " Pass : " + (pass ? "Pass" : "Failed"));
        finalPass &= pass;

        return finalPass;
    }

    static class SolutionHashMap {
        public int longestPalindrome(String s) {
            if (s == null || s.isEmpty())
                return 0;

            Map<Character, Integer> charVsFreq = new HashMap<>();

            for (char c : s.toCharArray()) {
                int oldCount = charVsFreq.getOrDefault(c, 0);
                charVsFreq.put(c, oldCount + 1);

            }

            int odd = 0;
            int even = 0;
            boolean isOdd = false;
            for (Map.Entry<Character, Integer> entry : charVsFreq.entrySet()) {
                int count = entry.getValue();
                if (count % 2 == 0) {
                    even += count;
                } else {
                    isOdd = true;
                    odd += count - 1;
                }
            }
            if (isOdd)
                return odd + even + 1;
            return even;
        }
    }

    /**
     * count on the fly
     */
    static class SolutionHashMap2 {
        public int longestPalindrome(String s) {
            if (s == null || s.isEmpty())
                return 0;

            Map<Character, Integer> charVsFreq = new HashMap<>();

            int result = 0;
            for (char c : s.toCharArray()) {
                if (charVsFreq.containsKey(c)) {
                    result += 2; //this is a unique pair, hence form a palindrome
                    charVsFreq.remove(c); //remove as its counted
                } else
                    charVsFreq.put(c, 1);
            }

            //hashmap will only have those char with odd count max to 1
            return result + (charVsFreq.isEmpty() ? 0 : 1); //if hashmap is empty, then add 0 else return 1, only one odd count char can be use
        }
    }

    static class SolutionFixedArray {
        public int longestPalindrome(String s) {
            if (s == null || s.isEmpty())
                return 0;

            int[] charVsFreq = new int[128];

            int result = 0;
            for (char c : s.toCharArray()) {
                charVsFreq[c]++;
                if (charVsFreq[c] == 2) {
                    result += 2; //this is a unique pair, hence form a palindrome
                    charVsFreq[c] = 0; //remove as its counted
                }
            }

            return result + (result < s.length() ? 1 : 0); //if hashmap were empty, then all char has been used from string, makes result=s.length() other at least one char would be odd length;
        }

    }
}
