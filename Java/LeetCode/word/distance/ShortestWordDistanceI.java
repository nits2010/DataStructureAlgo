package Java.LeetCode.word.distance;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-09
 * Description: https://leetcode.com/problems/shortest-word-distance/
 * http://leetcode.liangjiateng.cn/leetcode/shortest-word-distance/description
 * 243.Shortest Word Distance
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * <p>
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * <p>
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.4.html
 */
public class ShortestWordDistanceI {


    public static void main(String[] args) {
        test(Arrays.asList("practice", "makes", "perfect", "coding", "makes"), "coding", "practice", 3);
        test(Arrays.asList("practice", "makes", "perfect", "coding", "makes"), "makes", "coding", 1);
    }

    private static void test(List<String> words, String word1, String word2, int expected) {
        System.out.println("\nWords " + words + " word 1: " + word1 + " word2:" + word2 + " expected :" + expected);
        System.out.println("Distance (" + word1 + ", " + word2 + ") = " + shortestDistance(words, word1, word2));
    }

    /**
     * Algo:
     * 1. Find the index of both word
     * 2. compute the distance of both index
     * 3. continue {if the same word occurred multiple times in words list}
     * <p>
     * Complexity: O(n*L) time where n is length of words list, L is max length of word (word1 or word2); space : O(1)
     *
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public static int shortestDistance(List<String> words, String word1, String word2) {

        if (words == null || words.isEmpty() || word1 == null || word1.isEmpty() || word2 == null || word2.isEmpty())
            return Integer.MAX_VALUE;

        int word1Index = -1;
        int word2Index = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < words.size(); i++) { //O(n)

            String word = words.get(i);

            if (word.equals(word1)) //O(L)
                word1Index = i;

            if (word.equals(word2))//O(L)
                word2Index = i;

            if (word1Index != -1 && word2Index != -1)
                minDistance = Math.min(minDistance, Math.abs(word2Index - word1Index));


        }

        return minDistance;

    }
}
