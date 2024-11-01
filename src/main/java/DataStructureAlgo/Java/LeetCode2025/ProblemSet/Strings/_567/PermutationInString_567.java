package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings._567;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/5/2024
 * Question Category: 567. Permutation in String
 * Description: https://leetcode.com/problems/permutation-in-string
 * Given two strings s1 and s2, return true if s2 contains a
 * permutation
 *  of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 * File reference
 * -----------
 * Duplicate {@link }
 * Similar {@link DataStructureAlgo.Java.companyWise.facebook.AnagramPermutationSearch}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @HashTable
 * @TwoPointers
 * @String
 * @SlidingWindow
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Microsoft
 * @Apple
 * @Yandex
 * @Oracle
 * @Amazon
 * @Bloomberg
 * @Facebook
 * @Google
 * @Uber
 * @Yahoo
 *
 * @Editorial https://leetcode.com/problems/permutation-in-string/editorial/
 */
public class PermutationInString_567 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("ab", "eidbaooo", true);
        test &= test("ab", "eidboaoo", false);
        test &= test("adc", "dcda", true);
        CommonMethods.printAllTestOutCome(test);
    }

    private static boolean test(String s1, String s2, boolean expected) {
        System.out.println("----------------------------------");
        System.out.println(" Pattern : "+s1 + " Text : "+s2 + " Expected : "+expected);
        SolutionSlidingWindowMap solution = new SolutionSlidingWindowMap();
        boolean result = solution.checkInclusion(s1, s2);
        boolean pass = result == expected;
        System.out.println("Result : "+result + " Pass : "+(pass?"Passed":"Failed"));
        return pass;
    }

    /**
     Goal: Find if any anagram (permutation) of a pattern exists in the text.

     One way is to generate all permutations of the pattern and search them in the text, which will be O(m! * m * n).

     However, to match each anagram, we just need to ensure the frequency of each character is the same in both the pattern and the text.

     Since the anagram can be anywhere in the text in a continuous manner, we need to consider all substrings of the text of the same length as the pattern (window length).

     This can be done by adding one character to the right and removing one character from the left, aka a sliding window.

     Algorithm:

     Count the frequency of each character in the pattern (window length).

     Count the frequency of the first window-length characters of the text in a sliding window.

     Compare the frequency maps; return true if they match.

     Keep adding a new character to the sliding window on the right and removing the leftmost character.

     Time/Space Complexity: O(n + m) / O(1).

     *
     *
     */
    static class SolutionSlidingWindowMap {

        final int N = 26; //s1 and s2 consist of lowercase English letters.
        public boolean checkInclusion(String pattern, String text) {

            if(text == null || pattern == null)
                return true;

            if(text.isEmpty() && pattern.isEmpty())
                return true;

            if(pattern.isEmpty())
                return false;


            if(pattern.length() > text.length())
                return false;

            //map of the frequency of each character in a pattern
            int []freq = new int[N];

            // sliding frequency for text
            int []slidingFreq = new int [N];

            int windowLength = pattern.length();

            //get the frequency of each character in a pattern
            //O(m)
            int i=0;
            int indexAtStart = -1;
            while(i<windowLength){
                freq[pattern.charAt(i) - 'a']++;
                slidingFreq[text.charAt(i) - 'a']++;
                i++;
            }

            //(n-m)
           while(i<=text.length()){
                //if we have filled sliding window freq for windowLength, then match it
               if (match(freq, slidingFreq)){
                   indexAtStart = i - windowLength;
                   System.out.println("Index at start : "+indexAtStart);
                   return true;
               }

               //remove the character from the left of the window, as it goes out and add new character to the window, as it comes in
               int leftIndex = i - windowLength;
               slidingFreq[text.charAt(leftIndex) - 'a']--;

               //keep adding a new element in the window
                if(i<text.length())
                    slidingFreq[text.charAt(i) - 'a']++;
                i++;
            }

            return false;
        }

        //O(1)
        private boolean match(int[] freq, int[] slidingFreq) {
            for(int i=0; i<N; i++){
                if(freq[i] != slidingFreq[i])
                    return false;
            }
            return true;
        }
    }
}
