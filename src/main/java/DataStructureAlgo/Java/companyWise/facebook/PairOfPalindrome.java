package DataStructureAlgo.Java.companyWise.facebook;


import  DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 03/04/19
 * Description: https://leetcode.com/problems/palindrome-pairs/
 * 336. Palindrome Pairs
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 * <p>
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * https://www.geeksforgeeks.org/palindrome-pair-in-an-array-of-words-or-strings/
 * Explanation: https://fizzbuzzed.com/top-interview-questions-5/
 * <p>
 * Given a list of words, find if any of the two words can be joined to form a palindrome.
 * <p>
 * Examples:
 * <p>
 * Input  : list[] = {"geekf", "geeks", "or",
 * "keeg", "abc", "bc"}
 * Output : Yes
 * There is a pair "geekf" and "keeg"
 * <p>
 * Input : list[] =  {"abc", "xyxcba", "geekst", "or",
 * "keeg", "bc"}
 * Output : Yes
 * There is a pair "abc" and "xyxcba"
 *
 * [Amazon]
 */

public class PairOfPalindrome {


    public static void main(String[] args) {

        test(new String[]{"a", ""}, Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0)));
        test(new String[]{"abc", "xyxcba", "geekst", "or", "keeg", "bc"}, Arrays.asList(Arrays.asList(0, 1)));
        test(new String[]{"abcd", "dcba", "lls", "s", "sssll"}, Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0), Arrays.asList(2, 4), Arrays.asList(3, 2)));
        test(new String[]{"bat", "tab", "cat"}, Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0)));
        test(new String[]{"b"}, Arrays.asList());
        test(new String[]{"b", "t", "c"}, Arrays.asList());
        test(new String[]{"bb", "tt", "cc"}, Arrays.asList());
        test(new String[]{"ab", "ba", "cc"}, Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0)));


    }


    private static void test(String[] list, List<List<Integer>> expected) {
        System.out.println("\nList:" + GenericPrinter.toString(list));
        System.out.println("Expected :   " + expected);
        System.out.println("Trie  :      " + SolutionPairOfPalindromeUsingTrie.palindromePairs(list));
        System.out.println("Trie2 :      " + SolutionPairOfPalindromeUsingTrie2.palindromePairs(list));
        System.out.println("Hash  :      " + SolutionUsingHash.palindromePairs(list));


    }

}


/**
 * https://fizzbuzzed.com/top-interview-questions-5/
 * https://leetcode.com/problems/palindrome-pairs/discuss/79195/O(n-*-k2)-java-solution-with-Trie-structure
 * Runtime: 57 ms, faster than 69.41% of Java online submissions for Palindrome Pairs.
 * Memory Usage: 50.6 MB, less than 21.95% of Java online submissions for Palindrome Pairs.
 */
class SolutionPairOfPalindromeUsingTrie {

    static class TrieNode {

        public boolean isLeaf = false;
        public final Map<Character, TrieNode> children = new HashMap<>(26);
        public final List<Integer> palindromesWithInStringIndexes = new ArrayList<>();
        public int idOfThisString = -1;
    }

    static class Trie {

        private TrieNode root;

        private void insert(String toInsert, int myIndex) {
            if (root == null)
                root = new TrieNode();

            TrieNode pCrawl = root;

            for (int i = toInsert.length() - 1; i >= 0; i--) {

                char currentChar = toInsert.charAt(i);

                if (!pCrawl.children.containsKey(currentChar))
                    pCrawl.children.put(currentChar, new TrieNode());


                if (isPalindrome(toInsert, 0, i))
                    pCrawl.palindromesWithInStringIndexes.add(myIndex);

                pCrawl = pCrawl.children.get(currentChar);


            }
            pCrawl.isLeaf = true;
            pCrawl.idOfThisString = myIndex;
            pCrawl.palindromesWithInStringIndexes.add(myIndex);


        }

        private boolean isPalindrome(String toInsert, int from, int to) {

            while (from < to && toInsert.charAt(from) == toInsert.charAt(to)) {
                from++;
                to--;
            }

            return from >= to;
        }

        private List<List<Integer>> search(String toSearch, int myId) {

            List<List<Integer>> ids = new ArrayList<>();
            if (search(toSearch, myId, ids)) {
                return ids;
            } else
                return ids;

        }

        private boolean search(String toSearch, int myIndex, List<List<Integer>> ids) {
            if (null == root)
                return false;


            TrieNode pCrawl = root;

            for (int i = 0; i < toSearch.length(); i++) {

                char currentChar = toSearch.charAt(i);

                if (!pCrawl.children.containsKey(currentChar))
                    return false;

                //if the string at pCrawl is forming a string at this point, and this not same as the current word
                //and the remaining word is palindrome, the collect the ids
                if (pCrawl.idOfThisString >= 0 && pCrawl.idOfThisString != myIndex
                        && isPalindrome(toSearch, i, toSearch.length() - 1)) {

                    ids.add(Arrays.asList(myIndex, pCrawl.idOfThisString));

                }

                pCrawl = pCrawl.children.get(currentChar);




            }

            //Collect all the ids, where the pCrawl says its palindrome
            for (int id : pCrawl.palindromesWithInStringIndexes) {
                if (id != myIndex) {
                    ids.add(Arrays.asList(myIndex, id));
                }
            }

            return true;
        }


    }

    /**
     * Build the trie of given words s.t. we insert the word in trie in reverse order as well as check does it contains a substring which is palindrome if so, note its id
     * then search each word in same manner (without reverse)
     *
     * @param list
     * @return
     */
    public static List<List<Integer>> palindromePairs(String[] list) {

        List<List<Integer>> response = new ArrayList<>();

        Trie trie = new Trie();
        for (int i = 0; i < list.length; i++)
            trie.insert(list[i], i);

        for (int i = 0; i < list.length; i++) {
            response.addAll(trie.search(list[i], i));
        }

        return response;

    }

}

/**
 * Same as above just instead of map, used array
 * Runtime: 27 ms, faster than 91.75% of Java online submissions for Palindrome Pairs.
 * Memory Usage: 43.2 MB, less than 100.00% of Java online submissions for Palindrome Pairs.
 */
class SolutionPairOfPalindromeUsingTrie2 {
    static class TrieNode {

        public boolean isLeaf = false;
        public final TrieNode[] children = new TrieNode[26];
        public final List<Integer> palindromesWithInStringIndexes = new ArrayList<>();
        public int idOfThisString = -1;
    }

    static class Trie {

        private TrieNode root;

        private void insert(String toInsert, int myIndex) {
            if (root == null)
                root = new TrieNode();

            TrieNode pCrawl = root;

            for (int i = toInsert.length() - 1; i >= 0; i--) {

                char currentChar = toInsert.charAt(i);

                if (pCrawl.children[currentChar - 'a'] == null)
                    pCrawl.children[currentChar - 'a'] = new TrieNode();


                if (isPalindrome(toInsert, 0, i))
                    pCrawl.palindromesWithInStringIndexes.add(myIndex);

                pCrawl = pCrawl.children[currentChar - 'a'];


            }
            pCrawl.isLeaf = true;
            pCrawl.idOfThisString = myIndex;
            pCrawl.palindromesWithInStringIndexes.add(myIndex);


        }

        private boolean isPalindrome(String toInsert, int from, int to) {

            while (from < to && toInsert.charAt(from) == toInsert.charAt(to)) {
                from++;
                to--;
            }

            return from >= to;
        }

        private List<List<Integer>> search(String toSearch, int myId) {

            List<List<Integer>> ids = new ArrayList<>();
            if (search(toSearch, myId, ids)) {
                return ids;
            } else
                return ids;

        }

        private boolean search(String toSearch, int myIndex, List<List<Integer>> ids) {
            if (null == root)
                return false;


            TrieNode pCrawl = root;

            for (int i = 0; i < toSearch.length(); i++) {

                //if the string at pCrawl is forming a string at this point, and this not same as the current word
                //and the remaining word is palindrome, the collect the ids
                if (pCrawl.idOfThisString >= 0 && pCrawl.idOfThisString != myIndex
                        && isPalindrome(toSearch, i, toSearch.length() - 1)) {

                    ids.add(Arrays.asList(myIndex, pCrawl.idOfThisString));

                }

                char currentChar = toSearch.charAt(i);

                if (pCrawl.children[currentChar - 'a'] == null)
                    return false;

                pCrawl = pCrawl.children[currentChar - 'a'];


            }

            //Collect all the ids, where the pCrawl says its palindrome
            for (int id : pCrawl.palindromesWithInStringIndexes) {
                if (id != myIndex) {
                    ids.add(Arrays.asList(myIndex, id));
                }
            }

            return true;
        }


    }

    /**
     * Build the trie of given words s.t. we insert the word in trie in reverse order as well as check does it contains a substring which is palindrome if so, note its id
     * then search each word in same manner (without reverse)
     *
     * @param list
     * @return
     */
    public static List<List<Integer>> palindromePairs(String[] list) {

        List<List<Integer>> response = new ArrayList<>();

        Trie trie = new Trie();
        for (int i = 0; i < list.length; i++)
            trie.insert(list[i], i);

        for (int i = 0; i < list.length; i++) {
            response.addAll(trie.search(list[i], i));
        }

        return response;

    }

}


class SolutionUsingHash {

    /**
     * Just like trie solution we have two checks [ Lets say we are checking for A in trie ]
     * 1. Either we found whole string A in trie or trie length is bigger than A : in this case remaining string in trie should be palindrome
     * 2. Or we found shorter string A in trie which is a leaf: in this case the remaining String in A should be palindrome.
     * <p>
     * Lets take an example:
     * A = abcxyx
     * case 1: the trie contains a->b->c->x->y->x->z->p->z ; so till abcxyx we found and the remaining trie is z->p->z which is palindrome
     * case 2: the trie contains a->b->c and c is leaf, then the remaining substring "xyx" should be palindrome
     * <p>
     * <p>
     * What if, we some how reduce this information without building trie ?
     * <p>
     * Essentially we are looking for two String A and B s.t.
     * 1. B is shorter than A or equal size and BA is palindrome;
     * 2. B is shorter then A and AB is palindrome;
     * <p>
     * Continuing above example;
     * A = xyxabc  and B = cba[ its sub-part of A "abc" reverse "cba" and shorter than A and present in words list]
     * 1. len(B) < len(A) and cbaxyxabc which is a palindrome
     * A = abc  and B = cba [ its sub-part of A and equal to A in reverse and present in words list ]
     * len(B) = len(A) and abccba is palindrome
     * <p>
     * A = abcxyx  and B = cba
     * 2. len(B) < len(A) and abcxyxcba palindrome
     * <p>
     * <p>
     * Algorithm:
     * So, we take a word,
     * 1. make two different chunks of it
     * *         a. a chunk from j+1 to end(length) Say C1 (essentially B)
     * *         b. a chunk from 0 to current(j) Say C2 (essentially B)
     * <p>
     * Now check following things
     * 1. does string 0 to j  is palindrome and chunk C1 is present in word list, if so than it forms a palindrome
     * 2. does string j+1 to len is palindrome and chunk C2 is present in word list  , if so than it forms a palindrome
     *
     * @param list
     * @return
     */
    public static List<List<Integer>> palindromePairs(String[] list) {
        List<List<Integer>> response = new ArrayList<>();

        if (null == list || list.length <= 1)
            return response;


        // Convert it to word vs id
        Map<String, Integer> wordToIndexMap = new TreeMap<>();

        for (int i = 0; i < list.length; i++) {
            wordToIndexMap.put(list[i], i);
        }


        for (String word : wordToIndexMap.keySet()) {

            int i = wordToIndexMap.get(word);

            if (word.isEmpty()) {
                attachForEmptyString(response, list, i);
                continue;
            }

            for (int j = 0; j < word.length(); j++) {

                // Case 1 - Find all words, B, shorter than or the same size as
                // word, that can be prepended so B + word is a palindrome.

                //a chunk from j+1 to end(length) Say C1 (essentially B)
                String reversed = new StringBuffer(word.substring(j + 1)).reverse().toString();

                //does string 0 to j  is palindrome and chunk C1 is present in word list, if so than it forms a palindrome
                if (isPalindrome(word, 0, j) && wordToIndexMap.containsKey(reversed) && wordToIndexMap.get(reversed) != i)
                    response.add(Arrays.asList(wordToIndexMap.get(reversed), i)); //attaching B + word


                //Case 2 - Find all words, B, shorter than word that can be appended
                //so word + B is a palindrome.
                //a chunk from 0 to current(j) Say C2 (essentially B)

                reversed = new StringBuffer(word.substring(0, j + 1)).reverse().toString();

                //does string j+1 to len is palindrome and chunk C2 is present in word list  , if so than it forms a palindrome
                if (isPalindrome(word, j + 1, word.length() - 1) && wordToIndexMap.containsKey(reversed) && wordToIndexMap.get(reversed) != i)
                    response.add(Arrays.asList(i, wordToIndexMap.get(reversed)));

            }

        }


        return response;

    }

    private static void attachForEmptyString(List<List<Integer>> response, String[] list, int i) {
        for (int j = 0; j < list.length; j++) {
            if (i != j) {
                response.add(Arrays.asList(i, j));
            }
        }
    }


    private static boolean isPalindrome(String toInsert, int from, int to) {

        while (from < to && toInsert.charAt(from) == toInsert.charAt(to)) {
            from++;
            to--;
        }

        return from >= to;
    }

}


