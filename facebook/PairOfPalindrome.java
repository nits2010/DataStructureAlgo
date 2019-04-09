package facebook;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 03/04/19
 * Description:
 * https://www.geeksforgeeks.org/palindrome-pair-in-an-array-of-words-or-strings/
 * Explanation: https://fizzbuzzed.com/top-interview-questions-5/
 */

public class PairOfPalindrome {

    static class ResultHelper {
        public int index1, index2;


        public ResultHelper(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }

        @Override
        public String toString() {
            return "{" +
                    "index1=" + index1 +
                    ", index2=" + index2 +
                    '}';
        }
    }

    static class TrieNode {

        public boolean isLeaf = false;
        public Map<Character, TrieNode> children = new HashMap<>();
        public List<Integer> palindromesWithinStringIndexes = new ArrayList<>();
        public int idOfThisString = -1;
    }

    static class Trie {

        TrieNode root;

        void insert(String toInsert, int myIndex) {
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

            if (from == to)
                return true;
            return false;
        }

        public List<ResultHelper> search(String toSearch, int myId) {

            List<ResultHelper> resultHelpers = new ArrayList<>();
            if (search(toSearch, myId, resultHelpers))
                return resultHelpers;
            else
                return Collections.EMPTY_LIST;

        }

        private boolean search(String toSearch, int myIndex, List<ResultHelper> resultHelpers) {
            if (null == root)
                return false;


            TrieNode pCrawl = root;

            for (int i = 0; i < toSearch.length(); i++) {

                char currentChar = toSearch.charAt(i);

                if (!pCrawl.children.containsKey(currentChar))
                    return false;

                pCrawl = pCrawl.children.get(currentChar);

                if (pCrawl.idOfThisString >= 0 && pCrawl.idOfThisString != myIndex
                        && isPalindrome(toSearch, i, toSearch.length() - 1)) {
                    resultHelpers.add(new ResultHelper(myIndex, pCrawl.idOfThisString));

                }


            }

            for (int rem : pCrawl.palindromesWithinStringIndexes) {
                if (rem != myIndex)
                    resultHelpers.add(new ResultHelper(myIndex, rem));
            }

            return true;
        }


    }

    public static void main(String args[]) {

        String[] list = {"abc", "xyxcba", "geekst", "or", "keeg", "bc"};

        Trie trie = new Trie();
        for (int i = 0; i < list.length; i++)
            trie.insert(list[i], i);

        for (int i = 0; i < list.length; i++) {
            List<ResultHelper> result = trie.search(list[i], i);
            if (!result.isEmpty())
                System.out.println(result);
        }


    }

}
