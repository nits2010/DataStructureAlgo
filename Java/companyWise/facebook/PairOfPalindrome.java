package Java.companyWise.facebook;

import javafx.util.Pair;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 03/04/19
 * Description:
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
 */

public class PairOfPalindrome {


    public static void main(String args[]) {


        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();


    }

    private static void test1() {
        System.out.println("test 1 ....");
        String[] list = {"abc", "xyxcba", "geekst", "or", "keeg", "bc"};

        Arrays.stream(list).forEach(s -> System.out.print(s + " "));
        System.out.println(SolutionPairOfPalindromeUsingTrie.palindromePairs(list));

    }

    private static void test2() {
        System.out.println("test 2 ....");
        String[] list = {"abcd", "dcba", "lls", "s", "sssll"};

        Arrays.stream(list).forEach(s -> System.out.print(s + " "));
        System.out.println(SolutionPairOfPalindromeUsingTrie.palindromePairs(list));

    }

    private static void test3() {
        System.out.println("test 3 ....");
        String[] list = {"bat", "tab", "cat"};
        Arrays.stream(list).forEach(s -> System.out.print(s + " "));
        System.out.println(SolutionPairOfPalindromeUsingTrie.palindromePairs(list));

    }

    private static void test4() {
        System.out.println("test 3 ....");
        String[] list = {"b"};
        Arrays.stream(list).forEach(s -> System.out.print(s + " "));
        System.out.println(SolutionPairOfPalindromeUsingTrie.palindromePairs(list));

    }

    private static void test5() {
        System.out.println("test 3 ....");
        String[] list = {"b", "t", "c"};
        Arrays.stream(list).forEach(s -> System.out.print(s + " "));
        System.out.println(SolutionPairOfPalindromeUsingTrie.palindromePairs(list));

    }

    private static void test6() {
        System.out.println("test 3 ....");
        String[] list = {"bb", "tt", "cc"};
        Arrays.stream(list).forEach(s -> System.out.print(s + " "));
        System.out.println(SolutionPairOfPalindromeUsingTrie.palindromePairs(list));

    }

    private static void test7() {
        System.out.println("test 3 ....");
        String[] list = {"ab", "ba", "cc"};
        Arrays.stream(list).forEach(s -> System.out.print(s + " "));
        System.out.println(SolutionPairOfPalindromeUsingTrie.palindromePairs(list));

    }
}


/**
class SolutionUsingHash {

    public static List<List<Integer>> palindromePairs(String[] list) {

        List<List<Integer>> response = new ArrayList<>();

        // Convert it to word vs id
        Map<String, Integer> workToIndexMap = new HashMap<>(list.length)

        for (int i = 0; i < list.length; i++) {
            workToIndexMap.put(list[i], i);
        }


        for (String word : workToIndexMap.keySet()) {

            int i = workToIndexMap.get(word);

            for (int j = 0; j < word.length(); j++) {

                // Case 1 - Find all words, B, shorter than or the same size as
                // word, that can be prepended so B + word is a palindrome.

                String reversed = new StringBuffer(word.substring(0, j)).reverse().toString();
                if (isPalindrome(word, 0, j) && workToIndexMap.containsKey(reversed) && workToIndexMap.get(reversed) != i)
                    response.add(Arrays.asList(workToIndexMap.get(reversed), i));


                //Case 2 - Find all words, B, shorter than word1 that can be appended
                //so word1 + B is a palindrome.

            }

        }


        return response;

    }

    private static boolean isPalindrome(String toInsert, int from, int to) {

        while (from < to && toInsert.charAt(from) == toInsert.charAt(to)) {
            from++;
            to--;
        }

        if (from >= to)
            return true;
        return false;
    }

}
 **/

class SolutionPairOfPalindromeUsingTrie {

    static class TrieNode {

        public boolean isLeaf = false;
        public Map<Character, TrieNode> children = new HashMap<>(26);
        public List<Integer> palindromesWithinStringIndexes = new ArrayList<>();
        public int idOfThisString = -1;
    }

    static class Trie {

        TrieNode root;

        private void insert(String toInsert, int myIndex) {
            if (root == null)
                root = new TrieNode();

            TrieNode pCrawl = root;

            for (int i = toInsert.length() - 1; i >= 0; i--) {

                char currentChar = toInsert.charAt(i);

                if (!pCrawl.children.containsKey(currentChar))
                    pCrawl.children.put(currentChar, new TrieNode());


                if (isPalindrome(toInsert, 0, i))
                    pCrawl.palindromesWithinStringIndexes.add(myIndex);

                pCrawl = pCrawl.children.get(currentChar);


            }
            pCrawl.isLeaf = true;
            pCrawl.idOfThisString = myIndex;


        }

        private boolean isPalindrome(String toInsert, int from, int to) {

            while (from < to && toInsert.charAt(from) == toInsert.charAt(to)) {
                from++;
                to--;
            }

            if (from >= to)
                return true;
            return false;
        }

        private List<List<Integer>> search(String toSearch, int myId) {

            List<List<Integer>> ids = new ArrayList<>();
            if (search(toSearch, myId, ids)) {
                return ids;
            } else
                return Collections.EMPTY_LIST;

        }

        private boolean search(String toSearch, int myIndex, List<List<Integer>> ids) {
            if (null == root)
                return false;


            TrieNode pCrawl = root;

            for (int i = 0; i < toSearch.length(); i++) {

                char currentChar = toSearch.charAt(i);

                if (!pCrawl.children.containsKey(currentChar))
                    return false;

                pCrawl = pCrawl.children.get(currentChar);

                //if the string at pCrawl is forming a string at this point, and this not same as the current word
                //and the remaining word is palindrome, the collect the ids
                if (pCrawl.idOfThisString >= 0 && pCrawl.idOfThisString != myIndex
                        && isPalindrome(toSearch, i, toSearch.length() - 1)) {

                    ids.add(Arrays.asList(myIndex, pCrawl.idOfThisString));

                }


            }

            //Collect all the ids, where the pCrawl says its palindrome
            for (int id : pCrawl.palindromesWithinStringIndexes) {
                if (id != myIndex) {
                    ids.add(Arrays.asList(myIndex, id));
                }
            }

            return true;
        }


    }

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


