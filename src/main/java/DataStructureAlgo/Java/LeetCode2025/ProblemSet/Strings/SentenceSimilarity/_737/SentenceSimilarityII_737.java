package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.SentenceSimilarity._737;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Strings.SentenceSimilarity._734.SentenceSimilarity_734;
import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.nonleetcode.UnionFindDisjointSets;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 10/6/2024
 * Question Category: 737. Sentence Similarity II
 * Description: https://leetcode.com/problems/sentence-similarity-ii/description/
 * https://leetcode.ca/2017-12-06-737-Sentence-Similarity-II/
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
 * Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
 * Output: true
 * Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
 * Example 2:
 * <p>
 * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * Output: true
 * Explanation: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece".
 * Since "leetcode is similar to "onepiece" and the first two words are the same, the two sentences are similar.
 * Example 3:
 * <p>
 * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * Output: false
 * Explanation: "leetcode" is not similar to "onepiece".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= sentence1.length, sentence2.length <= 1000
 * 1 <= sentence1[i].length, sentence2[i].length <= 20
 * sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters.
 * 0 <= similarPairs.length <= 2000
 * similarPairs[i].length == 2
 * 1 <= xi.length, yi.length <= 20
 * xi and yi consist of English letters.
 * <p><p>
 * <p>
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link SentenceSimilarity_734}
 * <p><p>
 * Tags
 * -----
 * @medium
 * @String
 * @HashTable
 * @LeetCodeLockedProblem
 * @PremiumQuestion
 * @Graph
 * @UnionFind <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Google <p><p>
 * @Editorial
 */
public class SentenceSimilarityII_737 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new String[]{"great", "acting", "skills"}, new String[]{"fine", "drama", "talent"},
                List.of(List.of("great", "good"), List.of("fine", "good"), List.of("drama", "acting"), List.of("skills", "talent")), true);
        test &= test(new String[]{"I", "love", "leetcode"}, new String[]{"I", "love", "onepiece"},
                List.of(List.of("manga", "onepiece"), List.of("platform", "anime"), List.of("leetcode", "platform"), List.of("anime", "manga")), true);
        test &= test(new String[]{"I", "love", "leetcode"}, new String[]{"I", "love", "onepiece"},
                List.of(List.of("manga", "hunterXhunter"), List.of("platform", "anime"), List.of("leetcode", "platform"), List.of("anime", "manga")), false);

        CommonMethods.printAllTestOutCome(test);
    }


    private static boolean test(String[] sentence1, String[] sentence2, List<List<String>> similarPairs, boolean expected) {
        System.out.println("----------------------------------");
        System.out.println("Sentence 1 : " + Arrays.toString(sentence1) + " Sentence 2 : " + Arrays.toString(sentence2) + " Similar Pairs : " + similarPairs + " Expected : " + expected);

        boolean result, pass, finalPass = true;

        SolutionBruteForce solutionBruteForce = new SolutionBruteForce();
        result = solutionBruteForce.areSentencesSimilarTwo(sentence1, sentence2, similarPairs);
        pass = result == expected;
        System.out.println("Brute Force : " + result + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        SolutionUnionFind solutionUnionFind = new SolutionUnionFind();
        result = solutionUnionFind.areSentencesSimilarTwo(sentence1, sentence2, similarPairs);
        pass = result == expected;
        System.out.println("UF : " + result + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;


        SolutionUnionFind2 solutionUnionFind2 = new SolutionUnionFind2();
        result = solutionUnionFind2.areSentencesSimilarTwo(sentence1, sentence2, similarPairs);
        pass = result == expected;
        System.out.println("UF2 : " + result + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;


        return finalPass;
    }

    /**
     * We can similarly build the hashmap just like in {@link SentenceSimilarity_734}
     * Post building it, we can search for transitive relationship of each word in both sentences.
     * Since word1 -> .... _> word2 or word2 -> .... _> word1, hence we may need to touch to every word in pair in worst case.
     * also, if a -> b and b -> c then a -> c
     * And if a -> b and c -> b then a -> b as well
     * <p>
     * Algo:
     * 1. build hashmap of each pair from a -> b
     * 2. take each word from both sentences, word1 and word2, and try to find a relationship word1 -> .... _> word2 or word2 -> .... _> word1, additionally check, if any moment of time, both word1 and word2 are same
     * 3. if match found, move to next word
     * 4. if no relationship is found, exit and return false.
     * <p>
     * Time : O(M) + (N * M)  = O(N * M)
     * Space : O(M)
     */
    static class SolutionBruteForce {
        public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {

            if (sentence1 == null && sentence2 == null)
                return true;

            if (sentence1 == null || sentence2 == null)
                return false;

            if (sentence1.length != sentence2.length)
                return false;

            if (similarPairs == null || similarPairs.isEmpty() || similarPairs.get(0).isEmpty())
                return Arrays.deepEquals(sentence1, sentence2);

            Map<String, String> pairs = new HashMap<>();

            //O(M)
            for (List<String> pair : similarPairs) {
                if (!pair.isEmpty()) {
                    pairs.put(pair.get(0), pair.get(1));
                }
            }

            //O(N)
            for (int i = 0; i < sentence1.length; i++) {
                String word1 = sentence1[i];
                String word2 = sentence2[i];

                if (!word1.equals(word2)) {

                    String w1 = word1;
                    String w2 = word2;

                    //O(M) if we have to touch all similar pairs
                    //search by word1 to word2
                    while (w1 != null || w2 != null) {

                        //search by word1 to word2
                        if (w1 != null)
                            w1 = pairs.get(w1);

                        //search by word2 to word1
                        if (w2 != null)
                            w2 = pairs.get(w2);

                        //if a->b , c->b then a->c
                        if (w1 != null && w2 != null) {
                            if (w1.equals(w2)) {
                                break;
                            }
                        }

                        if (word2.equals(w1) || word1.equals(w2)) {
                            break;
                        }
                    }

                    if (w1 == null && w2 == null) {
                        return false;
                    }

                }
            }
            return true;

        }

    }

    /**
     * IN above brute force solution, then major challenges are
     * 1. To find the transitive relationship.
     * 2. For already visited path, we may have to revisit them if similar word comes again later in sentence, which is unnecessary.
     * <p>
     * We need to find a way by which, we can first solve the second problem, and getting the transitive relationship is less than O(M) time complexity.
     * Since, these similar words are making a directed graph, we can use to find dfs or bfs for finding transitive relationship. However, we need to make sure that, we store all the releationship for each word
     * so that we don't revisit the same, similar word again.
     * <p>
     * One way to use dfs and build the flattened graph for each word or use union find to reduce it to a directed tree.
     * Since, in union find, we can find the transitive relationship by using the parent of each node in O(log(m)) time.
     * <p>
     * Algorithm:
     * 1. Build a union find set and mark the number of distinct nodes in similar pairs as an individual sets. If n is the size of similarPairs, then total distinct nodes will be n^2 only n << 1.
     * 2. Iterate over each pair, and union them until no cycle is found.
     * 3. Post creating unionFInd set, its matter of finding parents of each word and matching them to each other.
     * <p>
     * {@link UnionFindDisjointSets}
     *
     * T/S: O(n*Log(N)) / O(M) ; n-sentence1.length, M-similarPairs.length; N - M<<1
     *
     * UnionFind Class:
     * Initialization:
     *
     * Time complexity: O(size), because it initializes the parents array.
     *
     * Find Operation:
     *
     * Amortized time complexity: O(A(N)), where A() is the Inverse Ackermann function, which grows very slowly.
     *
     * Union Operation:
     *
     * Amortized time complexity: O(A(N)) per union due to path compression and union by rank.
     *
     * areSentencesSimilarTwo Method:
     * Initial Checks: Constant time operations, so O(1).
     *
     * Mapping and Union:
     *
     * Building wordToIndexMapping: O(n) where n is the size of similarPairs.
     *
     * Union operations for each pair: O(M * A(M)), where M is the total number of unique words. Here, it’s bounded by O(2n * A(2n)) = O(n * A(n)).
     *
     * Sentence Comparison:
     *
     * Iterating through sentence1: O(N), where N is the length of the sentence.
     *
     * For each word pair, performing find operations: O(2 * A(M)) = O(A(n)) for each comparison.
     *
     * Therefore, total time complexity for this part: O(N * A(n)).
     *
     * Total Time Complexity:
     * Combining all parts, the total time complexity is O(n * A(n) + N * A(n)).
     *
     * Given that A(n) is very slow-growing, it can be considered nearly constant for practical purposes.
     *
     * Thus, the time complexity is almost O(n + N) in practice.
     *
     */
    static class SolutionUnionFind {

        static class UnionFind {

            class Set {
                int rank; //size of children
                int id; //parent id of this set

                public Set(int rank, int id) {
                    this.rank = rank;
                    this.id = id;
                }
            }

            Set[] parents;

            public UnionFind(int size) {
                parents = new Set[size];
                for (int i = 0; i < size; i++) {
                    parents[i] = new Set(1, i);
                }
            }

            private int find(int i) {
                if (parents[i].id == i) return i;
                return parents[i].id = find(parents[i].id);
            }

            private boolean union(int x, int y) {
                int xp = find(x);
                int yp = find(y);

                if (xp == yp) //cycle
                    return false;

                int xr = parents[xp].rank;
                int yr = parents[yp].rank;

                if (xr < yr) {
                    parents[xp].id = yp;
                } else if (yr < xr) {
                    parents[yp].id = xp;
                } else {
                    parents[yp].id = xp;
                    parents[xp].rank++;
                }

                return true;
            }
        }


        public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {

            if (sentence1 == null && sentence2 == null)
                return true;

            if (sentence1 == null || sentence2 == null)
                return false;

            if (sentence1.length != sentence2.length)
                return false;

            if (similarPairs == null || similarPairs.isEmpty() || similarPairs.get(0).isEmpty())
                return Arrays.deepEquals(sentence1, sentence2);

            int n = similarPairs.size();
            int words = n << 1;

            UnionFind uf = new UnionFind(words); // n << 1 : number of nodes in union find set


            //Since our parent map is index-based, not word-based, we need to find the quick way to get the index of each word
            // this hashmap will give the index/id of each word in parents
            Map<String, Integer> wordToIndexMapping = new HashMap<>();
            int id = 0;

            for (int i = 0; i < n; i++) {
                String word1 = similarPairs.get(i).get(0);
                String word2 = similarPairs.get(i).get(1);

                if (!wordToIndexMapping.containsKey(word1)) {
                    wordToIndexMapping.put(word1, id++);
                }

                if (!wordToIndexMapping.containsKey(word2)) {
                    wordToIndexMapping.put(word2, id++);
                }

                int x = wordToIndexMapping.get(word1);
                int y = wordToIndexMapping.get(word2);

                uf.union(x, y); //Union the nodes for each pair

            }

            //go over each word in sentence and match them
            for (int i = 0; i < sentence1.length; i++) {
                String word1 = sentence1[i];
                String word2 = sentence2[i];

                if (!word1.equals(word2)) {
                    int w1Id = wordToIndexMapping.getOrDefault(word1, -1);
                    int w2Id = wordToIndexMapping.getOrDefault(word2, -1);

                    //if there is no similar pair relationship is given, then return false
                    if (w1Id == -1 || w2Id == -1)
                        return false;

                    //get root node of each word
                    int p1 = uf.find(w1Id);
                    int p2 = uf.find(w2Id);

                    //if parents are different, then there is no Transitive relationship
                    if (p1 != p2)
                        return false;
                }
            }
            return true;
        }


    }


    /**
     * Same only, we have not used ranking here, we just compress the path. which also make tree grow slowly only.
     Combining all parts, the total time complexity is O(n * A(n) + N * A(n)).
     *
     * Given that A(n) is very slow-growing, it can be considered nearly constant for practical purposes.
     *
     * Thus, the time complexity is almost O(n + N) in practice.
     */
    static class SolutionUnionFind2 {

        public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {

            if (sentence1 == null && sentence2 == null)
                return true;

            if (sentence1 == null || sentence2 == null)
                return false;

            if (sentence1.length != sentence2.length)
                return false;

            if (similarPairs == null || similarPairs.isEmpty() || similarPairs.get(0).isEmpty())
                return Arrays.deepEquals(sentence1, sentence2);

            int n = similarPairs.size();
            int words = n << 1;

            int []parents = new int[words];

            for(int i=0; i<words; i++){
                parents[i] = i;
            }


            //Since our parent map is index-based, not word-based, we need to find the quick way to get the index of each word
            // this hashmap will give the index/id of each word in parents
            Map<String, Integer> wordToIndexMapping = new HashMap<>();
            int id = 0;

            for (int i = 0; i < n; i++) {
                String word1 = similarPairs.get(i).get(0);
                String word2 = similarPairs.get(i).get(1);

                if (!wordToIndexMapping.containsKey(word1)) {
                    wordToIndexMapping.put(word1, id++);
                }

                if (!wordToIndexMapping.containsKey(word2)) {
                    wordToIndexMapping.put(word2, id++);
                }

                int x = wordToIndexMapping.get(word1);
                int y = wordToIndexMapping.get(word2);

                union(parents,x, y); //Union the nodes for each pair

            }

            //go over each word in sentence and match them
            for (int i = 0; i < sentence1.length; i++) {
                String word1 = sentence1[i];
                String word2 = sentence2[i];

                if (!word1.equals(word2)) {
                    int w1Id = wordToIndexMapping.getOrDefault(word1, -1);
                    int w2Id = wordToIndexMapping.getOrDefault(word2, -1);

                    //if there is no similar pair relationship is given, then return false
                    if (w1Id == -1 || w2Id == -1)
                        return false;

                    //get root node of each word
                    int p1 = find(parents, w1Id);
                    int p2 = find(parents, w2Id);

                    //if parents are different, then there is no Transitive relationship
                    if (p1 != p2)
                        return false;
                }
            }
            return true;
        }

        private int find(int []parents, int x){
            if(parents[x] == x)
                return x;
            return parents[x] = find(parents, parents[x]); //path compression
        }

        private void union(int []parents, int x, int y){
            int xp = find(parents, x);
            int yp = find(parents, y);
            parents[xp] = yp;
        }


    }
}
