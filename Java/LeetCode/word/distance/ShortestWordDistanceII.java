package Java.LeetCode.word.distance;


import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-09
 * Description:https://leetcode.com/problems/shortest-word-distance-ii/
 * <p>
 * http://leetcode.liangjiateng.cn/leetcode/shortest-word-distance-ii/description
 * <p>
 * 244.Shortest Word Distance II
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and
 * return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.
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
 * LeetCode articles: https://leetcode.com/articles/shortest-word-distance-ii/
 * http://tiancao.me/Leetcode-Unlocked/LeetCode%20Locked/c1.4.html
 *
 * {@link ShortestWordDistanceI}
 */
public class ShortestWordDistanceII {

    public static void main(String[] args) {
        test(Arrays.asList("practice", "makes", "perfect", "coding", "makes"), new String[][]{{"coding", "practice", "3"}, {"makes", "coding", "1"}, {"makes2", "coding", "" + Integer.MAX_VALUE}});
    }

    private static void test(List<String> words, String[][] wordsToSearch) {
        System.out.println("\nWords :" + words);

        final ShortestWordDistanceIIMap shortestWordDistance = new ShortestWordDistanceIIMap(words);

        for (String[] w : wordsToSearch) {

            System.out.println("Distance (" + w[0] + ", " + w[1] + ") = " + shortestWordDistance.shortest(w[0], w[1]) + " expected :" + w[2]);

        }
    }
}

/**
 * One way to utilize {@link ShortestWordDistanceI} but for each query it takes O(n*L) time where n is length of words list, L is max length of word (word1 or word2).
 * Since we need to optimize the query, we need to cache the index of words.
 * <p>
 * Algo:
 * 1. Run through words list and cache the index(s) of all the word; O(n)
 * 2. For each query find, shortest distance based on index(s) as in map ;
 * <p>
 * Complexity of step 2 is depends on how many times a word occurred in List. If list has all word as same then map has n index of a word.
 * and if both word1, and word2 are same then we need to traverse whole list.
 * Hence
 * <p>
 * Complexity: O(n) / O(n) assuming search in hashmap is O(1) for each word
 */
class ShortestWordDistanceIIMap {

    private final List<String> words;
    private final Map<String, List<Integer>> wordIndex;

    public ShortestWordDistanceIIMap(List<String> words) {
        this.words = new ArrayList<>(words);
        this.wordIndex = new HashMap<>();
        init();
    }

    private final void init() {

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);

            if (!wordIndex.containsKey(word))
                wordIndex.put(word, new ArrayList<>());

            wordIndex.get(word).add(i);
        }
    }

    public int shortest(String word1, String word2) {

        if (word1 == null || word1.isEmpty() || word2 == null || word2.isEmpty())
            return Integer.MAX_VALUE;

        final List<Integer> indexes1 = wordIndex.get(word1);
        final List<Integer> indexes2 = wordIndex.get(word2);

        //If either of one is not present
        if (indexes1 == null || indexes1.isEmpty() || indexes2 == null || indexes2.isEmpty())
            return Integer.MAX_VALUE;


        //indexes1 and indexes2 are in sorted order, we can use linear search
        int i = indexes1.size() - 1;
        int j = indexes2.size() - 1;
        int minDistance = Integer.MAX_VALUE;

        //O(n)
        while (i >= 0 && j >= 0) {
            minDistance = Math.min(minDistance, Math.abs(indexes1.get(i) - indexes2.get(j)));

            if (indexes1.get(i) > indexes2.get(j))
                i--;
            else
                j--;

        }

        return minDistance;

    }
}