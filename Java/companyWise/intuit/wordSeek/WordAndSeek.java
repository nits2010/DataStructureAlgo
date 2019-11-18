package Java.companyWise.intuit.wordSeek;

import Java.LeetCode.word.boggle.BoggleSearchWordSearchII;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-07
 * Description:
 * Same as {@link BoggleSearchWordSearchII}
 */
public class WordAndSeek {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int z = scanner.nextInt();
        String[] words = new String[z];
        for (int i = 0; i < z; i++) {

            String word = scanner.next();
            words[i] = word.trim();
        }

        int m = scanner.nextInt();
        int n = scanner.nextInt();

        final char[][] board = new char[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                char c = scanner.next().charAt(0);
                board[i][j] = c;
            }
        }

        int count = findWords(board, words).size();
        System.out.println(count);

    }

    public static List<String> findWords(char[][] board, String[] dictionary) {
        if (dictionary == null || dictionary.length == 0)
            return new ArrayList<>();

        Trie trie = new Trie();
        Arrays.stream(dictionary).forEach(trie::insert);

        return search(board, trie.root);

    }


    static int[] row = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] col = {0, 0, -1, 1, -1, 1, -1, 1};


    static class TrieNode {

        public boolean isLeaf = false;
        public char value;
        public Map<Character, TrieNode> children = new HashMap<>(256);

        public TrieNode(char v) {
            this.value = v;
        }

        public TrieNode() {

        }


    }

    static class Trie {
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

    public static List<String> search(char boggle[][], TrieNode root) {
        int n = boggle.length;
        int m = boggle[0].length;

        Set<String> boggleInDic = new HashSet<>();
        boolean[][] visited = new boolean[n][m];


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

    private static void search(char[][] boggle, boolean[][] visited, int r, int c, TrieNode pCrawl, String temp, int n, int m, Set<String> boggleInDic) {

        //If we found it
        if (pCrawl.isLeaf) {
            boggleInDic.add(temp);
        }

        //visit this cell
        visited[r][c] = true;


        for (int k = 0; k < row.length; k++) {
            int rowV = r + row[k];
            int colV = c + col[k];

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

    private static boolean isSafe(int r, int c, boolean[][] visited, int n, int m) {

        return r < n && c < m && r >= 0 && c >= 0 && !visited[r][c];
    }


}

