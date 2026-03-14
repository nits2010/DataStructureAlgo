package DataStructureAlgo.Java.LeetCode.word.distance;


import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Shortest Word Distance II
 * Link: TODO: Add Link
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
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