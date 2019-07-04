package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 04/04/19
 * Description: https://leetcode.com/problems/word-search-ii/
 */
public class BoggleSearchWordSearchII {

    public static void main(String args[]) {

        // test1();
        test2();
        test3();
    }

    private static void test3() {

        String dictionary[] = {"abcd"};
        char boggle[][] = {{'a', 'b'},
                {'c', 'd'}};

        SolutionBoggleSearch solutionBoggleSearch = new SolutionBoggleSearch();
        System.out.println(solutionBoggleSearch.findWords(boggle, dictionary));
    }

    static void test1() {
        String dictionary[] = {"GEEKS", "FOR", "QUIZ", "GEE"};
        char boggle[][] = {{'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}};

        SolutionBoggleSearch solutionBoggleSearch = new SolutionBoggleSearch();
        System.out.println(solutionBoggleSearch.findWords(boggle, dictionary));
    }

    static void test2() {
        String dictionary[] = {"oath", "pea", "eat", "rain"};
        char boggle[][] = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};

        SolutionBoggleSearch solutionBoggleSearch = new SolutionBoggleSearch();
        System.out.println(solutionBoggleSearch.findWords(boggle, dictionary)); //["eat","oath"]
    }


}

class SolutionBoggleSearch {


    int row8[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    int col8[] = {0, 0, -1, 1, -1, 1, -1, 1};


    int row4[] = {0, 0, 1, -1};
    int col4[] = {-1, 1, 0, 0};


    class TrieNode {

        public boolean isLeaf = false;
        public char value;
        public Map<Character, TrieNode> children = new HashMap<>(256);

        public TrieNode(char v) {
            this.value = v;
        }

        public TrieNode() {

        }


    }

    class Trie {
        TrieNode root;

        public void insert(String toInsert) {
            if (null == toInsert || toInsert.isEmpty())
                return;

            if (root == null)
                root = new TrieNode();

            TrieNode pCrawl = root;

            for (int i = 0; i < toInsert.length(); i++) {
                char current = toInsert.charAt(i);

                if (!pCrawl.children.containsKey(current))
                    pCrawl.children.put(current, new TrieNode(current));

                pCrawl = pCrawl.children.get(current);

            }

            pCrawl.isLeaf = true;
        }


    }

    public List<String> search(char boggle[][], TrieNode root) {
        int n = boggle.length;
        int m = boggle[0].length;

        Set<String> boggleInDic = new HashSet<>();
        boolean visited[][] = new boolean[n][m];


        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                char current = boggle[i][j];

                if (root.children.containsKey(current)) {
                    String temp = "" + current;
                    search(boggle, visited, i, j, root.children.get(current), temp, n, m, boggleInDic);
                }

            }
        }
        return new ArrayList<>(boggleInDic);

    }

    private void search(char[][] boggle, boolean[][] visited, int r, int c, TrieNode pCrawl, String temp, int n, int m, Set<String> boggleInDic) {

        //If we found it
        if (pCrawl.isLeaf) {
            boggleInDic.add(temp);
        }

        //visit this cell
        visited[r][c] = true;


        for (int k = 0; k < row4.length; k++) {
            int rowV = r + row4[k];
            int colV = c + col4[k];

            if (isSafe(rowV, colV, visited, n, m)) {

                char current = boggle[rowV][colV];
                if (pCrawl.children.containsKey(current)) {

                    temp += current;
                    search(boggle, visited, rowV, colV, pCrawl.children.get(current), temp, n, m, boggleInDic);
                    temp = temp.substring(0, temp.length() - 1);
                }
            }

        }

        visited[r][c] = false;


    }

    private boolean isSafe(int r, int c, boolean[][] visited, int n, int m) {

        if (r >= n || c >= m || r < 0 || c < 0 || visited[r][c])
            return false;
        return true;
    }

    public List<String> findWords(char[][] board, String[] dictionary) {
        if (dictionary == null || dictionary.length == 0)
            return new ArrayList<>();

        Trie trie = new Trie();
        Arrays.stream(dictionary).forEach(s -> trie.insert(s));

        return search(board, trie.root);

    }
}
