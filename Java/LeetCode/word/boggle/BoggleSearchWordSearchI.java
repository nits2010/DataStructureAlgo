package Java.LeetCode.word.boggle;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-04
 * Description: https://leetcode.com/problems/word-search/
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class BoggleSearchWordSearchI {

    public static void main(String []args) {

        test1();

    }

    private static void test1() {
        char board[][] =
                {
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                };

        SolutionBoggleSearchWordSearchI sol = new SolutionBoggleSearchWordSearchI();
        System.out.println(sol.exist(board, "ABCCED"));
        System.out.println(sol.exist(board, "SEE"));
        System.out.println(sol.exist(board, "ABCB"));


    }

}

class SolutionBoggleSearchWordSearchI {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, word.toCharArray(), 0)) return true;
                }
            }
        }

        return false;
    }

    int[][] indexes = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private boolean dfs(char[][] board, int i, int j, char[] wordArr, int idx) {
        if (idx == wordArr.length) return true;

        if (i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] == '*' || board[i][j] != wordArr[idx])
            return false;

        char tmp = board[i][j];
        board[i][j] = '*';

        for (int[] index : indexes) {
            if (dfs(board, i + index[0], j + index[1], wordArr, idx + 1))
                return true;
        }

        board[i][j] = tmp;
        return false;
    }
}
