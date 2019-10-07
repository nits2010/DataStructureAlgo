package Java.LeetCode.word.ladder;

import Java.helpers.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 26/09/19
 * Description: https://leetcode.com/problems/word-ladder/
 * 127. Word Ladder [Medium]
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * <p>
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * Output: 5
 * <p>
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 * <p>
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * Output: 0
 * <p>
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 * <p>
 * <p>
 * https://github.com/RodneyShag/LeetCode_solutions/blob/master/Solutions/Word%20Ladder.md
 * <p>
 * {@link WordLadderIBFSV2}
 * {@link WordLadderIBiDirectionBFSUsingRecursion}
 * {@link WordLadderIBiDirectionalBFS}
 */
public class WordLadderI {

    public static void main(String[] args) {
        boolean test = true;

        test &= test("a", "c", Arrays.asList("a", "b", "c"), 2);
        test &= test("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), 5);
        test &= test("hit", "dog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), 4);
        test &= test("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"), 0);
        test &= test("hit", "Tog", Arrays.asList("hot", "dot", "dog", "lot", "log"), 0);
        test &= test("pit", "pog", Arrays.asList("hot", "dot", "dog", "lot", "log"), 0);

        System.out.println("\n Test:" + (test ? "Passed" : "Failed"));

    }

    private static boolean test(String beginWord, String endWord, List<String> wordList, int expected) {
        System.out.println("---------");
        System.out.println("Begin Word  : " + beginWord);
        System.out.println("End Word    : " + endWord);
        System.out.println("Words       : " + wordList);
        System.out.println("Expected    : " + expected);

        final int bfs1 = new WordLadderIBFS().ladderLength(beginWord, endWord, wordList);
        final int bfs2 = new WordLadderIBFSV2().ladderLength(beginWord, endWord, wordList);
        final int biDirectionalBfs = new WordLadderIBiDirectionalBFS().ladderLength(beginWord, endWord, wordList);
        final int smartDfs = new WordLadderIBiDirectionBFSUsingRecursion().ladderLength(beginWord, endWord, wordList);
        System.out.println("BFS1        : " + bfs1 + " Pass: " + ((bfs1 == expected) ? "Passed" : "failed"));
        System.out.println("BFS2        : " + bfs2 + " Pass: " + ((bfs2 == expected) ? "Passed" : "failed"));
        System.out.println("Bi-BFS      : " + biDirectionalBfs + " Pass: " + ((biDirectionalBfs == expected) ? "Passed" : "failed"));
        System.out.println("smartDfs    : " + smartDfs + " Pass: " + ((smartDfs == expected) ? "Passed" : "failed"));

        return GenericPrinter.equalsValues(expected, bfs1, bfs2, biDirectionalBfs, smartDfs);
    }
}

/*
Since we need to transform the 'begin' word to 'end' word with constraint to change only 1 letter at a time, that means the word list either contain the 'begin' word
or word list should contain words that is 1 distance away from 'begin' word.
Now, once we find the word to start, we need to traverse all the word that is 1 distance away and present in the word list.

It boils down to a undirected graph with weight 1. Here weight shows the number of character need to change from a word to a word. In our case its 1. {we can generalize it }.

Now, to efficiently find all the nodes which is 1 weight away from a node, we should pre-process the given word list and find all the nodes that are 1 distance away and hence form the unweighted
graph.

Now, we just need to start form the 'begin' node and see all the nodes in the graph which is 1 distance away and keep moving in that path. Since we need 'shortest transformation sequence'
we can use BFS to achieve it.

Algorithm:
1. Convert given list to undirected graph which are 1 distance away only.
2. Start from 'begin' node and explore all the path that are 1 distance away
3. as soon as you hit end word, return the distance travelled.

N - Length of list of word
M - maximum length of a word

Time: O(N*M)
Space: O(N*M)
Runtime: 126 ms, faster than 31.02% of Java online submissions for Word Ladder.
Memory Usage: 50.8 MB, less than 5.11% of Java online submissions for Word Ladder.
 */
class WordLadderIBFS {


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (wordList == null || wordList.isEmpty())
            return 0;

        final Set<String> uniqueWords = new HashSet<>(wordList);

        //if end word is not present then no way to transform
        if (!uniqueWords.contains(endWord))
            return 0;

        if (beginWord.equals(endWord))
            return 1;

        //O(N*M)
        final Map<String, List<String>> graph = buildUnDirectedWeightedGraph(wordList, uniqueWords);

        //get mapping corresponding to begin word too.
        //O(M)
        final Map<String, List<String>> beginWordGraph = buildUnDirectedWeightedGraph(Collections.singletonList(beginWord), uniqueWords);

        //append
        graph.putAll(beginWordGraph);

        //O(N*M)
        return bfs(beginWord, endWord, graph);

    }


    /**
     * This will build undirected weighted 1.
     * Algorithm:
     * 1. Take all word one by one.
     * 2. Change each character of this word from [a,z] and test does new word exist in list or not
     * 3. if exist, create a edge from this word to new word
     * <p>
     * Time Complexity:  N is length of list, M is length of longest word
     * Step 1: O(N)
     * Step 2: O(M * 26 )
     * Step 3: O(1)
     * <p>
     * Hence : O(N*M)
     * <p>
     * Space Complexity: O(N*M)
     *
     * @param wordList Graph nodes
     * @return undirected weighted {@code weight}
     */
    private Map<String, List<String>> buildUnDirectedWeightedGraph(List<String> wordList, final Set<String> uniqueWord) {

        //represent the undirected weighted(1) graph.
        //Key -> word node
        //Value -> all node that is weight distance away
        final Map<String, List<String>> graph = new HashMap<>();

        for (String s : wordList) { //O(N)

            char[] node = s.toCharArray();
            int length = node.length;

            //change every char of node.
            //O(M*26)
            for (int i = 0; i < length; i++) { //O(M)

                char old = node[i];
                //try every node char to change other and test
                //O(26)
                for (char c = 'a'; c <= 'z'; c++) {

                    if (node[i] == c) //no need to change
                        continue;

                    //change this char
                    node[i] = c;
                    String temp = new String(node);
                    if (uniqueWord.contains(temp))
                        graph.computeIfAbsent(s, list -> new ArrayList<>()).add(temp);
                }
                node[i] = old;
            }

            if (!graph.getOrDefault(s, new ArrayList<>()).isEmpty())
                graph.get(s).remove(s);

        }


        return graph;
    }


    /**
     * Find the shortest path from source to destination
     * O(N*M)
     *
     * @param source      source node
     * @param destination destination node
     * @param graph       graph
     * @return shortest distance otherwise 0 if not reachable
     */
    private int bfs(String source, String destination, Map<String, List<String>> graph) {

        class Node {
            String word;
            int distance = 0;

            public Node(String word, int distance) {
                this.word = word;
                this.distance = distance;
            }
        }

        final Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Node(source, 0)); //distance of source to source is 0
        visited.add(source);

        while (!queue.isEmpty()) {

            final Node node = queue.poll();
            final int distance = node.distance;

            //if we reached the destination node
            if (node.word.equals(destination)) {
                return distance + 1;
            }

            //try all word which is 1 weight away
            for (String neighbour : graph.getOrDefault(node.word, new ArrayList<>())) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(new Node(neighbour, distance + 1));
                }
            }


        }
        return 0;
    }

}


/**
 * Same as above, instead pre-processing. We'll do post processing for each word
 * Time: O(N*M)
 * Space: O(N*M)
 * <p>
 * Runtime: 69 ms, faster than 48.37% of Java online submissions for Word Ladder.
 * Memory Usage: 41.1 MB, less than 68.61% of Java online submissions for Word Ladder.
 */
class WordLadderIBFSV2 {


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (wordList == null || wordList.isEmpty())
            return 0;

        final Set<String> uniqueWords = new HashSet<>(wordList);

        //if end word is not present then no way to transform
        if (!uniqueWords.contains(endWord))
            return 0;

        if (beginWord.equals(endWord))
            return 1;

        //O(N*M)
        return bfs(beginWord, endWord, uniqueWords);

    }


    /**
     * Find the shortest path from source to destination
     * O(N*M)
     *
     * @param source      source node
     * @param destination destination node
     * @param uniqueWords graph
     * @return shortest distance otherwise 0 if not reachable
     */
    private int bfs(String source, String destination, final Set<String> uniqueWords) {

        class Node {
            String word;
            int distance;

            public Node(String word, int distance) {
                this.word = word;
                this.distance = distance;
            }
        }

        final Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Node(source, 0)); //distance of source to source is 0
        visited.add(source);

        while (!queue.isEmpty()) {

            final Node node = queue.poll();
            final int distance = node.distance;

            //if we reached the destination node
            if (node.word.equals(destination))
                return distance + 1;

            //try all word which is 1 weight away
            for (String neighbour : getNeighbour(node.word, uniqueWords)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(new Node(neighbour, distance + 1));
                }
            }


        }
        return 0;
    }


    /**
     * Algorithm:
     * 2. Change each character of this word from [a,z] and test does new word exist in list or not
     * 3. if exist, create a edge from this word to new word
     * <p>
     * Time Complexity:  M is length of longest word
     * Step 2: O(M * 26 )
     * Step 3: O(1)
     * <p>
     * Hence : O(M)
     * <p>
     * Space Complexity: O(M)
     *
     * @param word Graph nodes
     * @return neighbours of weight 1
     */
    private Set<String> getNeighbour(String word, final Set<String> uniqueWord) {

        final Set<String> neighbour = new HashSet<>();


        char[] node = word.toCharArray();
        int length = node.length;

        //change every char of node.
        //O(M*26)
        for (int i = 0; i < length; i++) { //O(M)

            char old = node[i];
            //try every node char to change other and test
            //O(26)
            for (char c = 'a'; c <= 'z'; c++) {

                if (node[i] == c) //no need to change
                    continue;

                //change this char
                node[i] = c;
                String temp = new String(node);
                if (uniqueWord.contains(temp))
                    neighbour.add(temp);
            }
            node[i] = old;
        }


        neighbour.remove(word);


        return neighbour;
    }

}

/**
 * Instead of running single BFS as in {@link WordLadderIBFS} we can do bi-directional bfs.
 * One bfs from source to destination node
 * one bfs from destination to source node
 * at the same time.
 * <p>
 * When we reach a state, where both of the current node are same, we are done with the process.
 * <p>
 * Bidirectional Search uses 2 simultaneous BFS searches to find the distance between 2 nodes (by alternating between the searches)
 * <p>
 * Bidirectional Search
 * <p>
 * For BFS, if d is the distance between the 2 nodes, and b is the branching factor, 1 way to represent the runtime is O(bd).
 * <p>
 * For Bidirectional search, the runtime is O(b(d/2)) + O(b(d/2)) = O(2*b(d/2)) = O(b(d/2)) since each search only has to search halfway before meeting the other search.
 * <p>
 * Applying Bidirectional Search to our original solution
 * <p>
 * Our original solution used 1 Deque as a queue, and 1 Set to mark visited Strings. Since we are running 2 BFS searches, we will make 2 of these Deques and 2 of these Sets.
 * To simulate 2 BFS searches "simultaneously", we alter between which of the 2 Deques we deque from.
 * We now have 2 Deques to deque from, and 2 "visited" Sets, but we don't want to duplicate code. We will create a general function called checkNeighbors() that takes a Deque and Set as input, to help us alternate between the 2 BFS searches.
 * If at any time a String gets dequed, and one of its neighbors has already been visited from the opposite side's parallel search, we've found a solution.
 * <p>
 *
 * <p>
 * For Bidirectional search, the runtime is O(b^(d/2)) + O(b^(d/2)) = O(2*b^(d/2)) = O(b^(d/2)) since each search only has to search halfway before meeting the other search.
 * <p>
 * Time Complexity: O(b^(d/2)) as explained at beginning of Solution 2. In our case, b is the max size of the Set returned by getNeighbors(), making b = O(26m) = O(m) where m is length of longest word.
 * Space Complexity: Same as in Solution 1.
 * <p>
 * Runtime: 13 ms, faster than 97.07% of Java online submissions for Word Ladder.
 * * Memory Usage: 38.8 MB, less than 97.81% of Java online submissions for Word Ladder.
 */
class WordLadderIBiDirectionalBFS {

    public int ladderLength(String source, String destination, List<String> wordList) {

        if (wordList == null || wordList.isEmpty())
            return 0;

        final Set<String> uniqueWords = new HashSet<>(wordList);

        //if end word is not present then no way to transform
        if (!uniqueWords.contains(destination))
            return 0;

        if (source.equals(destination))
            return 1;


        //One direction from source to destination
        final Queue<String> queueSourceToDestination = new LinkedList<>();
        //contains [string,distance]
        final Map<String, Integer> visitedSourceDestinationWithDistance = new HashMap<>();

        //One direction from destination to source
        final Queue<String> queueDestinationToSource = new LinkedList<>();
        //contains [string,distance]
        final Map<String, Integer> visitedDestinationSourceWithDistance = new HashMap<>();


        queueSourceToDestination.offer(source); //distance of source to source is 0
        visitedSourceDestinationWithDistance.put(source, 0);

        queueDestinationToSource.offer(destination); //distance of source to source is 0
        visitedDestinationSourceWithDistance.put(destination, 0);

        int distance;
        while (!queueSourceToDestination.isEmpty() && !queueDestinationToSource.isEmpty()) {

            if ((distance = move(queueSourceToDestination, visitedSourceDestinationWithDistance, visitedDestinationSourceWithDistance, uniqueWords)) >= 0)
                return distance + 2;//source and destination included

            if ((distance = move(queueDestinationToSource, visitedDestinationSourceWithDistance, visitedSourceDestinationWithDistance, uniqueWords)) >= 0)
                return distance + 2;//source and destination included

        }
        return 0;
    }

    /**
     * Search in neighbours, and check does same string came again which was visited earlier in other path
     *
     * @param queue
     * @param mainPath    Denotes the main path for this bfs
     * @param otherPath   Denotes the other path for this bfs
     * @param uniqueWords wordList
     * @return return the minimum distance of this path
     */
    private int move(Queue<String> queue, Map<String, Integer> mainPath, Map<String, Integer> otherPath, Set<String> uniqueWords) {

        if (queue.isEmpty())
            return -1;

        final String node = queue.poll();
        final int distance = mainPath.get(node);

        //try all word which is 1 weight away
        for (String neighbour : getNeighbour(node, uniqueWords)) {

            if (otherPath.containsKey(neighbour))
                return distance + otherPath.get(neighbour);

            if (!mainPath.containsKey(neighbour)) {
                mainPath.put(neighbour, distance + 1);
                queue.offer(neighbour);
            }
        }


        return -1;
    }


    /**
     * Algorithm:
     * 2. Change each character of this word from [a,z] and test does new word exist in list or not
     * 3. if exist, create a edge from this word to new word
     * <p>
     * Time Complexity:  M is length of longest word
     * Step 2: O(M * 26 )
     * Step 3: O(1)
     * <p>
     * Hence : O(M)
     * <p>
     * Space Complexity: O(M)
     *
     * @param word Graph nodes
     * @return neighbours of weight 1
     */
    private Set<String> getNeighbour(String word, final Set<String> uniqueWord) {

        final Set<String> neighbour = new HashSet<>();


        char[] node = word.toCharArray();
        int length = node.length;

        //change every char of node.
        //O(M*26)
        for (int i = 0; i < length; i++) { //O(M)

            char old = node[i];
            //try every node char to change other and test
            //O(26)
            for (char c = 'a'; c <= 'z'; c++) {

                if (node[i] == c) //no need to change
                    continue;

                //change this char
                node[i] = c;
                String temp = new String(node);
                if (uniqueWord.contains(temp))
                    neighbour.add(temp);
            }
            node[i] = old;
        }


        neighbour.remove(word);


        return neighbour;
    }

}

/**
 * Recursive BFS
 * Runtime: 9 ms, faster than 99.55% of Java online submissions for Word Ladder.
 * Memory Usage: 37.9 MB, less than 100.00% of Java online submissions for Word Ladder.
 */
class WordLadderIBiDirectionBFSUsingRecursion {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        return search(beginSet, endSet, dict, 1);
    }

    private int search(Set<String> beginSet, Set<String> endSet, Set<String> dict, int cnt) {
        if (beginSet.isEmpty() || endSet.isEmpty())
            return 0;

        cnt++;
        dict.removeAll(beginSet);
        Set<String> nextSet = new HashSet<>();

        for (String str : beginSet) {
            //Find all neighbours
            char[] chs = str.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char original = chs[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    chs[i] = j;
                    String tmp = new String(chs);

                    if (!dict.contains(tmp))
                        continue;

                    //we reached to destination from other end
                    if (endSet.contains(tmp))
                        return cnt;

                    nextSet.add(tmp);
                }
                chs[i] = original;
            }
        }
        return nextSet.size() > endSet.size() ? search(endSet, nextSet, dict, cnt) : search(nextSet, endSet, dict, cnt);
    }
}