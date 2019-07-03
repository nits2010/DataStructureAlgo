package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-04
 * Description: https://leetcode.com/problems/valid-tic-tac-toe-state/
 */
public class ValidTicTacToeState {

    public static void main(String args[]) {
        String board1[] = {"O  ", "   ", "   "};
        String board2[] = {"XOX", " X ", "   "};
        String board3[] = {"XXX", "   ", "OOO"};
        String board4[] = {"XOX", "O O", "XOX"};
        String board5[] = {"XXX", "XOO", "OO "};

        ValidTicTacToeStateSolution solution = new ValidTicTacToeStateSolution();
        System.out.println(solution.validTicTacToe(board5));
        System.out.println(solution.validTicTacToe(board4));
        System.out.println(solution.validTicTacToe(board1));
        System.out.println(solution.validTicTacToe(board2));
        System.out.println(solution.validTicTacToe(board3));


    }

}

class ValidTicTacToeStateSolution {

    class BOARD {

        char board[];
        int countX;
        int countO;

        public BOARD(char[] b, int x, int y) {
            board = b;
            countX = x;
            countO = y;
        }
    }

    final char X = 'X';
    final char O = 'O';

    int wins[][] = {
            {0, 1, 2}, //Row 1
            {3, 4, 5}, //Row 2
            {6, 7, 8}, //Row 3
            {0, 3, 6}, //Col1
            {1, 4, 7}, //Col2
            {2, 5, 8}, //Col3
            {0, 4, 8}, //Dig1 top-left to bottom right
            {2, 4, 6}  //Dig2 top-right to bottom-left
    };


    //"XOX", "O O", "XOX"
    public boolean isWin(char[] board, char player) {

        for (int i = 0; i < wins.length; i++) {

            char e1 = board[wins[i][0]];
            char e2 = board[wins[i][1]];
            char e3 = board[wins[i][2]];

            if (e1 == player && e2 == player && e3 == player)
                return true;
        }
        return false;
    }


    public BOARD flatTheBoard(String[] board) {
        int n = board.length;
        char[] newBoard = new char[n * n];


        int countX = 0, countO = 0;
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                newBoard[i * n + j] = board[i].charAt(j);

                if (newBoard[i * n + j] == X)
                    countX++;

                if (newBoard[i * n + j] == O)
                    countO++;
            }
        }

        return new BOARD(newBoard, countX, countO);

    }

    public boolean validTicTacToe(String[] board) {
        if (board == null || board.length == 0)
            return false;


        BOARD newBoard = flatTheBoard(board);

        if (newBoard.countX == newBoard.countO || newBoard.countX == newBoard.countO + 1) {

            boolean isXWin = isWin(newBoard.board, X);
            boolean isOWin = isWin(newBoard.board, O);

            if (isOWin) {
                if (isXWin)
                    return false;
                return newBoard.countO == newBoard.countX;
            } else if (isXWin) {
                return !(newBoard.countX != newBoard.countO + 1);
            }

            return true;
        }
        return false;
    }


}

class Solution2 {

    private char[][] gameBoard = new char[3][3];

    public boolean validTicTacToe(String[] board) {

        unpackBoard(board);

        return gameBoardValidation();
    }

    private void unpackBoard(String[] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.gameBoard[i][j] = board[i].charAt(j);
            }
        }
    }

    private boolean gameBoardValidation() {

        int numX, numO;

        numX = count('X');
        numO = count('O');

        //X goes first
        if (numO > numX) return false;

        //players take turns
        if (numX > numO + 1) return false;

        //both players can't win
        if (winner('X') && winner('O')) return false;

        //game ends when one player wins
        if (winner('X') && numX == numO) return false;

        //game ends when one player wins
        if (winner('O') && numX > numO) return false;

        return true;
    }

    private int count(char player) {

        int num = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == player) num++;
            }
        }

        return num;
    }

    private boolean winner(char player) {

        if (validateRows(player)) return true;
        if (validateColumns(player)) return true;
        if (validateDiagonal(player)) return true;

        return false;
    }

    private boolean validateRows(char player) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == player && gameBoard[i][1] == player && gameBoard[i][2] == player) return true;
        }

        return false;
    }

    private boolean validateColumns(char player) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[0][i] == player && gameBoard[1][i] == player && gameBoard[2][i] == player) return true;
        }

        return false;
    }

    private boolean validateDiagonal(char player) {
        if (gameBoard[0][0] == player && gameBoard[1][1] == player && gameBoard[2][2] == player) return true;
        if (gameBoard[0][2] == player && gameBoard[1][1] == player && gameBoard[2][0] == player) return true;

        return false;
    }
}
