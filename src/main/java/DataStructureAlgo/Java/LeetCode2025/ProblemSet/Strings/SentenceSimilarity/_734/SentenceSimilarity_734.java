package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.SentenceSimilarity._734;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 10/6/2024
 * Question Category: 734 - Sentence Similarity
 * Description: https://leetcode.com/problems/sentence-similarity/description/
 * https://leetcode.ca/2017-12-03-734-Sentence-Similarity/
 * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
 * <p>
 * Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
 * <p>
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 * <p>
 * Two sentences are similar if:
 * <p>
 * They have the same length (i.e., the same number of words)
 * sentence1[i] and sentence2[i] are similar.
 * Notice that a word is always similar to itself, also notice that the similarity relation is not transitive. For example, if the words a and b are similar, and the words b and c are similar, a and c are not necessarily similar.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
 * Output: true
 * Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
 * Example 2:
 * <p>
 * Input: sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
 * Output: true
 * Explanation: A word is similar to itself.
 * Example 3:
 * <p>
 * Input: sentence1 = ["great"], sentence2 = ["doubleplus","good"], similarPairs = [["great","doubleplus"]]
 * Output: false
 * Explanation: As they don't have the same length, we return false.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= sentence1.length, sentence2.length <= 1000
 * 1 <= sentence1[i].length, sentence2[i].length <= 20
 * sentence1[i] and sentence2[i] consist of English letters.
 * 0 <= similarPairs.length <= 1000
 * similarPairs[i].length == 2
 * 1 <= xi.length, yi.length <= 20
 * xi and yi consist of lower-case and upper-case English letters.
 * All the pairs (xi, yi) are distinct.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 * @String
 * @HashTable
 * @LeetCodeLockedProblem
 * @PremimumQuestion
 * <p><p>
 * Company Tags
 * -----
 * @Google <p><p>
 * @Editorial
 */
public class SentenceSimilarity_734 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new String[]{"great", "acting", "skills"}, new String[]{"fine", "drama", "talent"}, List.of(List.of("great", "fine"), List.of("drama", "acting"), List.of("skills", "talent")), true);
        test &= test(new String[]{"great"}, new String[]{"great"}, List.of(List.of()), true);
        test &= test(new String[]{"great"}, new String[]{"doubleplus", "good"}, List.of(List.of("great", "doubleplus")), false);
        test &= test(new String[]{"great", "fine"}, new String[]{"great", "fine"}, List.of(List.of()), true);
        test &= test(new String[]{"great", "fine"}, new String[]{"great", "fine"}, List.of(List.of("great", "fine")), true);
        CommonMethods.printResult(test);
    }

    private static boolean test(String[] sentence1, String[] sentence2, List<List<String>> similarPairs, boolean expected) {
        System.out.println("----------------------------------");

        System.out.println("Sentence 1 : " + Arrays.toString(sentence1) + "Sentence 2 : " + Arrays.toString(sentence2));
        System.out.println("similarPairs : " + similarPairs + "expected : " + expected);
        boolean result, pass;
        boolean finalPass = true;

        Solution sol = new Solution();
        result = sol.areSentencesSimilar(sentence1, sentence2, similarPairs);
        pass = result == expected;
        System.out.println("Output : " + result + " result : " + (pass ? "Pass" : "Fail"));
        finalPass &= pass;

        result = sol.areSentencesSimilar2(sentence1, sentence2, similarPairs);
        pass = result == expected;
        System.out.println("Output2 : " + result + " result : " + (pass ? "Pass" : "Fail"));
        finalPass &= pass;
        return finalPass;


    }

    /**
     * T/S : O(n) / O(m) ; n-sentences1.length, m-similarPairs.length
     */
    static class Solution {
        public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
            if (sentence1 == null && sentence2 == null)
                return true;

            if (sentence1 == null || sentence2 == null)
                return false;

            if (sentence1.length != sentence2.length)
                return false;

            if (similarPairs == null || similarPairs.isEmpty() || similarPairs.get(0).isEmpty())
                return Arrays.deepEquals(sentence1, sentence2);

            //hash pairs in map
            Map<String, String> pairs = new HashMap<>();
            for (List<String> pair : similarPairs) {
                pairs.put(pair.get(0), pair.get(1));
            }

            for (int i = 0; i < sentence1.length; i++) {
                String word1 = sentence1[i];
                String word2 = sentence2[i];

                if (!word1.equals(word2)) {
                    if (!(word2.equals(pairs.get(word1)) || word1.equals(pairs.get(word2))))
                        return false;
                }
            }
            return true;
        }

        public boolean areSentencesSimilar2(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
            if (sentence1 == null && sentence2 == null)
                return true;

            if (sentence1 == null || sentence2 == null)
                return false;

            if (sentence1.length != sentence2.length)
                return false;

            if (similarPairs == null || similarPairs.isEmpty() || similarPairs.get(0).isEmpty())
                return Arrays.deepEquals(sentence1, sentence2);

            //hash pairs in map
            Set<String> pairs = new HashSet<>();
            for (List<String> pair : similarPairs) {
                pairs.add(pair.get(0) + "#" + pair.get(1));
            }

            for (int i = 0; i < sentence1.length; i++) {
                String word1 = sentence1[i];
                String word2 = sentence2[i];

                if (!word1.equals(word2)) {
                    if (!(pairs.contains(word1 + "#" + word2) || pairs.contains(word2 + "#" + word1)))
                        return false;
                }
            }
            return true;
        }
    }
}
