package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.SentenceSimilarity._1813;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/6/2024
 * Question Category: 1813. Sentence Similarity III
 * Description: https://leetcode.com/problems/sentence-similarity-iii/description/?envType=daily-question&envId=2024-10-06
 * You are given two strings sentence1 and sentence2, each representing a sentence composed of words. A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of only uppercase and lowercase English characters.
 *
 * Two sentences s1 and s2 are considered similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. Note that the inserted sentence must be separated from existing words by spaces.
 *
 * For example,
 *
 * s1 = "Hello Jane" and s2 = "Hello my name is Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in s1.
 * s1 = "Frog cool" and s2 = "Frogs are cool" are not similar, since although there is a sentence "s are" inserted into s1, it is not separated from "Frog" by a space.
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
 *
 * Output: true
 *
 * Explanation:
 *
 * sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
 *
 * Example 2:
 *
 * Input: sentence1 = "of", sentence2 = "A lot of words"
 *
 * Output: false
 *
 * Explanation:
 *
 * No single sentence can be inserted inside one of the sentences to make it equal to the other.
 *
 * Example 3:
 *
 * Input: sentence1 = "Eating right now", sentence2 = "Eating"
 *
 * Output: true
 *
 * Explanation:
 *
 * sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.
 *
 *
 *
 * Constraints:
 *
 * 1 <= sentence1.length, sentence2.length <= 100
 * sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
 * The words in sentence1 and sentence2 are separated by a single space.
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @TwoPointers
 * @String
 *
 * <p><p>
 * Company Tags
 * -----
 * @Google
 * <p><p>
 *
 * @Editorial https://leetcode.com/problems/sentence-similarity-iii/editorial
 */
public class SentenceSimilarityIII_1813 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("I am happy", "happy", true);
        test &= test("qbaVXO Msgr aEWD v ekcb", "Msgr aEWD ekcb", false);
        test &= test("My name is Haley", "My name is Haley", true);
        test &= test("My name is Haley", "My Haley", true);
        test &= test("of", "A lot of words", false);
        test &= test("Eating right now", "Eating", true);
        test &= test("Frog cool", "Frogs are cool", false);
        test &= test("Frogs are cool", "Frogs are cool", true);
        test &= test("I am cool", "cool", true);
        test &= test("cool", "I am cool", true);
        test &= test("", "I am cool", true);
        CommonMethods.printAllTestOutCome(test);

    }

    public static boolean test(String sentence1, String sentence2, boolean expected) {
        System.out.println("----------------------------------");
        System.out.println("Sentence 1 : "+sentence1 + " Sentence 2 : "+sentence2 + " expected : "+expected);

        boolean output, pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.areSentencesSimilar(sentence1, sentence2);
        pass = output == expected;
        System.out.println("Output : "+output + " Pass : "+(pass?"Passed":"Failed"));
        finalPass &= pass;
        return finalPass;

    }

    static class Solution {
        public boolean areSentencesSimilar(String sentence1, String sentence2) {
            if (sentence1 == null && sentence2 == null)
                return true;

            if (sentence1 == null || sentence2 == null)
                return false;

            int n = sentence1.length();
            int m = sentence2.length();

            if(n == 0 || m == 0)
                return true;

            //make sentence 1 is a bigger sentence
            if (m > n) {
                return areSentencesSimilar(sentence2, sentence1);
            }

            //if both sentences have same length, then they can be
            // the same only when they match, as possible insert would be empty only
            if(n == m){
                return sentence1.equals(sentence2);
            }



            //split sentence into words
            String [] words1 = sentence1.split(" ");
            String [] words2 = sentence2.split(" ");

            int wStart = 0;


            //match prefix, squeeze from start
            while (wStart < words1.length && wStart < words2.length && words1[wStart].equals(words2[wStart])) {
                wStart++;

            }

            //if prefix matched

            //all the word from smaller sentence matched the prefix of longer sentence
            //s1 = ["Eating" , "right" , "now"]
            //s2 = ["Eating"]
            //then we can insert "right now" at the end
            if(wStart == words2.length)
                return true;

            //match suffix, squeeze from end
            int w1End = words1.length - 1;
            int w2End = words2.length - 1;
            while (w2End >=0 && words1[w1End].equals(words2[w2End])) {
                w1End--;
                w2End--;
            }

           return w2End < wStart;





        }
    }
}
