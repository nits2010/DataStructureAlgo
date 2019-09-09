package Java.LeetCode.word.distance;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-09
 * Description: https://leetcode.com/problems/shortest-word-distance-iii/
 * 245.Shortest Word Distance III
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * <p>
 * word1 and word2 may be the same and they represent two individual words in the list.
 * <p>
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Input: word1 = “makes”, word2 = “coding”
 * Output: 1
 * Input: word1 = "makes", word2 = "makes"
 * Output: 3
 * Note:
 * You may assume word1 and word2 are both in the list.
 * <p>
 * http://leetcode.liangjiateng.cn/leetcode/shortest-word-distance-iii/description
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.4.html
 *
 *
 * <p>
 * {@link ShortestWordDistanceI}
 */
public class ShortestWordDistanceIII {


    public static void main(String[] args) {
        test(Arrays.asList("practice", "makes", "perfect", "coding", "makes"), "coding", "practice", 3);
        test(Arrays.asList("practice", "makes", "perfect", "coding", "makes"), "makes", "coding", 1);
        test(Arrays.asList("practice", "makes", "perfect", "coding", "makes"), "makes", "makes", 3);
        test(Arrays.asList("makes", "practice", "makes", "makes", "perfect", "makes", "coding", "makes"), "makes", "makes", 1);
        test(Arrays.asList("makes", "practice", "makes", "makes", "perfect", "makes", "coding", "makes", "coding", "perfect", "coding", "makes"), "makes", "makes", 1);
    }

    private static void test(List<String> words, String word1, String word2, int expected) {
        System.out.println("\nWords " + words + " \nword 1: " + word1 + " word2:" + word2 + " expected :" + expected);
        System.out.println("Distance (" + word1 + ", " + word2 + ") = " + shortestDistance(words, word1, word2));
    }

    /**
     * One way to utilize {@link ShortestWordDistanceI}, but word1 and word2 both can be same.
     * Algo:
     * 1. Find the index of both word
     * 2. compute the distance of both index
     * 3. continue {if the same word occurred multiple times in words list}
     * 4. in case words are same, then make sure both are at different index
     * <p>
     */
    public static int shortestDistance(final List<String> words, String word1, String word2) {

        if (word1 == null || word1.isEmpty() || word2 == null || word2.isEmpty())
            return Integer.MAX_VALUE;


        int minDistance = Integer.MAX_VALUE;
        int word1Index = -1;
        int word2Index = -1;

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);

            /**
             * If both word are same, then take the different index of both
             */
            if (word1.equals(word2)) {

                if (word.equals(word1)) {

                    //swap the index, since we need to keep both index as close as possible
                    word2Index = word1Index;
                    word1Index = i;
                }

            } else {
                if (word.equals(word1)) //O(L)
                    word1Index = i;

                if (word.equals(word2))//O(L)
                    word2Index = i;

            }

            if (word1Index != -1 && word2Index != -1) {

                minDistance = Math.min(minDistance, Math.abs(word1Index - word2Index));
            }
        }


        return minDistance;
    }
}

