package Java.LeetCode.word.ladder;

import Java.HelpersToPrint.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 27/09/19
 * Description: https://leetcode.com/problems/word-ladder-ii/
 * 126. Word Ladder II [Hard]
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * <p>
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 * <p>
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * Output: []
 * <p>
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderII {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("red", "tax", Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"), Arrays.asList(Arrays.asList("red", "ted", "tad", "tax"), Arrays.asList("red", "ted", "tex", "tax"), Arrays.asList("red", "rex", "tex", "tax")));
        test &= test("a", "c", Arrays.asList("a", "b", "c"), Collections.singletonList(Arrays.asList("a", "c")));
        test &= test("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), Arrays.asList(Arrays.asList("hit", "hot", "lot", "log", "cog"), Arrays.asList("hit", "hot", "dot", "dog", "cog")));
        test &= test("hit", "dog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), Collections.singletonList(Arrays.asList("hit", "hot", "dot", "dog")));
        test &= test("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"), Collections.emptyList());
        test &= test("hit", "Tog", Arrays.asList("hot", "dot", "dog", "lot", "log"), Collections.emptyList());
        test &= test("pit", "pog", Arrays.asList("hot", "dot", "dog", "lot", "log"), Collections.emptyList());

        test &= test("cet", "ism", Arrays.asList("kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay",
                "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan",
                "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez",
                "own", "ump", "dis", "ads", "max", "jaw", "out", "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal",
                "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova",
                "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe",
                "ivy", "fan", "age", "fax", "hip", "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex", "yum",
                "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don",
                "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web",
                "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev",
                "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey",
                "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash",
                "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw",
                "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo",
                "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem",
                "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye",
                "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp",
                "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay",
                "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix",
                "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get",
                "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap",
                "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike",
                "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han",
                "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib",
                "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax",
                "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"),
                Arrays.asList(Arrays.asList("cet", "get", "gee", "gte", "ate", "ats", "its", "ito", "ibo", "ibm", "ism"), Arrays.asList("cet", "cat", "can", "ian", "inn", "ins", "its", "ito", "ibo", "ibm", "ism"),
                        Arrays.asList("cet", "cot", "con", "ion", "inn", "ins", "its", "ito", "ibo", "ibm", "ism")));
        System.out.println("\n Test:" + (test ? "Passed" : "Failed"));

    }

    private static boolean test(String beginWord, String endWord, List<String> wordList, List<List<String>> expected) {
        System.out.println("---------");
        System.out.println("Begin Word  : " + beginWord);
        System.out.println("End Word    : " + endWord);
        System.out.println("Words       : " + wordList);
        System.out.println("Expected    : " + expected);

        final List<List<String>> bfsLadders = new WordLadderIIBFSDFS().findLadders(beginWord, endWord, wordList);
        System.out.println("bfsLadders  : " + bfsLadders);

        return GenericPrinter.equalsValues(expected, bfsLadders);

    }
}


/**
 * We'll apply same logic as {@link WordLadderI} #BFS.
 * Algorithm:
 * 1. First find the shortest path distance using bfs, during this keep caching the distance of neighbour from source. 'neighbourDistance'
 * 2. Once we find, we can use neighbour distance map to create path.
 * To build path, apply dfs + backtracking on it.
 * 2.1 if the current neigh node is 1 distance away from its source node, then take it in path otherwise skip.
 * <p>
 * Why? 1 distance away.
 * Since while building distance {essentially neighbour distance}, we have cached all the nodes which apparently not going to be part of our path.
 * More over their distance from source node would be higher then needed.
 * in dfs , for if (distance.get(next) == distance.get(cur) + 1) is just in case that the next node is the next level of current node，
 * otherwise it can be one of the parent nodes of current node，or it is not the shortest node .
 * Since in BFS, we record both the next level nodes and the parent node as neighbors of current node. use distance.get(cur)+1 we can make sure the path is the shortest one.
 *
 * <p>
 * Runtime: 111 ms, faster than 46.03% of Java online submissions for Word Ladder II.
 * Memory Usage: 55.1 MB, less than 19.23% of Java online submissions for Word Ladder II.
 */
class WordLadderIIBFSDFS {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (wordList == null || wordList.isEmpty())
            return new ArrayList<>();

        final Set<String> uniqueWords = new HashSet<>(wordList);

        //if end word is not present then no way to transform
        if (!uniqueWords.contains(endWord))
            return new ArrayList<>();

        if (beginWord.equals(endWord))
            return Collections.singletonList(Collections.singletonList(beginWord));


        //holds the neighbour distance from begin node
        final Map<String, Integer> neighbourDistance = new HashMap<>();
        //graph contains all the neighbour of a node
        final Map<String, Set<String>> graph = new HashMap<>();

        //apply bfs to find shortest distance
        if (bfs(beginWord, endWord, uniqueWords, graph, neighbourDistance) == -1)
            return new ArrayList<>();

        //build path
        List<List<String>> paths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        currentPath.add(beginWord);
        dfs(beginWord, endWord, graph, paths, currentPath, neighbourDistance);

        return paths;
    }

    /**
     * Find shortest distance
     *
     * @param beginWord
     * @param endWord
     * @param uniqueWords
     * @param graph
     * @param neighbourDistance
     * @return
     */
    private int bfs(String beginWord, String endWord, Set<String> uniqueWords, Map<String, Set<String>> graph, final Map<String, Integer> neighbourDistance) {
        final Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord); //distance of source to source is 0
        neighbourDistance.put(beginWord, 0); //distance of source to source is 0

        while (!queue.isEmpty()) {

            final String node = queue.poll();
            final int distance = neighbourDistance.get(node);

            //if we reached the destination node
            if (node.equals(endWord)) {
                return distance + 1;
            }

            //try all word which is 1 weight away
            for (String neighbour : getNeighbour(node, uniqueWords)) {
                if (!neighbourDistance.containsKey(neighbour)) {
                    neighbourDistance.put(neighbour, distance + 1);
                    queue.offer(neighbour);

                }
                if (!graph.containsKey(node))
                    graph.put(node, new HashSet<>());

                graph.get(node).add(neighbour);
            }
        }
        return -1;
    }

    /**
     * Find path
     *
     * @param current
     * @param endWord
     * @param pathMap
     * @param paths
     * @param currentPath
     * @param distance
     */
    private void dfs(String current, String endWord, Map<String, Set<String>> pathMap,
                     List<List<String>> paths, List<String> currentPath, final Map<String, Integer> distance) {

        if (current.equals(endWord)) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }
        final Set<String> neigh = pathMap.getOrDefault(current, new HashSet<>());
        for (String nei : neigh) {
            if (distance.get(nei) == distance.get(current) + 1) {
                currentPath.add(nei);
                dfs(nei, endWord, pathMap, paths, currentPath, distance);
                currentPath.remove(currentPath.size() - 1);
            }
        }
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


//TODO:
class WordLadderIIBiDirectionalBFSDFS {}

