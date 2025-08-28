"""
Author: Nitin Gupta
Date: 7/23/2025
Question Title: 37. Sudoku Solver
Link: https://leetcode.com/problems/sudoku-solver/description
Description: Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:




Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
File reference
-----------
Duplicate {@link SudokuSolver}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@Array
@HashTable
@Backtracking
@Matrix

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        81 x 81 is too big to solve. Pre-cache the restricted places
        """
        length = len(board)

        def calculate_box_index(i, j) -> int:
            import math
            box_length: int = int(math.sqrt(length))
            row = (i // box_length) * box_length
            col = (j // box_length) % box_length
            return row + col

        row_restriction = [[False] * length for _ in range(length)]
        col_restriction = [[False] * length for _ in range(length)]
        box_restriction = [[False] * length for _ in range(length)]

        def install_restrictions():
            for i in range(length):
                for j in range(length):
                    if board[i][j] != ".":
                        number = int(board[i][j]) - 1

                        # restrict row for this number
                        row_restriction[i][number] = True

                        col_restriction[j][number] = True

                        box_index = calculate_box_index(i, j)
                        box_restriction[box_index][number] = True

        def solve_sudoku(index: int) -> bool:

            if index == length * length:
                return True  # sudoku solved

            row = index // length
            col = index % length
            box_index = calculate_box_index(row, col)

            if board[row][col] == ".":

                # try all number to be placed in this location
                for num in range(0, 9):
                    if not (row_restriction[row][num] or col_restriction[col][num] or box_restriction[box_index][num]):

                        # un-restricted number, place it

                        # track it
                        board[row][col] = str(num + 1)
                        row_restriction[row][num], col_restriction[col][num], box_restriction[box_index][
                            num] = True, True, True

                        # solve it
                        if solve_sudoku(index + 1):
                            return True

                        # back track it
                        board[row][col] = "."
                        row_restriction[row][num], col_restriction[col][num], box_restriction[box_index][
                            num] = False, False, False
            else:
                # means this row and col is not available to be placed, try next index
                if solve_sudoku(index + 1):
                    return True

            return False

        install_restrictions()
        solve_sudoku(0)


class Solution_TLE:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        This is standard backtracking problem. Keep filling the number to the empty box.
        And then check full row, column and then the box.

        Checking row and column is fairly easy.
        To check box, we need to know the box start and end, essentially box length. In standard Sudoku, its 3x3 for 9x9 sudoku.

        For each r and c (which is current we are filling ), we need to know its box start row and end row and same for column.

        boxLength = sqrt (boardLength)
        rowStart = r - r%boxLength
        colStart = c - c%boxLength

        boardLength = 9
        boxLength = 3
        say r = 1, c = 2 [ essentially the first box, Rows[00 02] & Cols[00, 20]]
        rowStart = 1 - 1 % 3 = 0
        colStart = 2 - 2 % 3 = 0
        """

        def is_valid_box(row: int, col: int, num: int) -> bool:
            import math

            box_length: int = int(math.sqrt(len(board)))

            row_start: int = row - int(row % box_length)
            col_start: int = col - int(col % box_length)

            for i in range(row_start, row_start + box_length):
                for j in range(col_start, col_start + box_length):
                    if board[i][j] != "." and int(board[i][j]) == num:
                        return False

            return True

        def is_valid_row(row: int, num: int) -> bool:
            for i in range(len(board[row])):
                if board[row][i] != "." and int(board[row][i]) == num:
                    return False

            return True

        def is_valid_col(col: int, num: int) -> bool:
            for i in range(len(board)):
                if board[i][col] != "." and int(board[i][col]) == num:
                    return False
            return True

        def empty_cell() -> (int, int):
            for i in range(len(board)):
                for j in range(len(board[i])):
                    if board[i][j] == ".":
                        return (i, j)

            return (None, None)

        def is_valid_state(row, col, num) -> bool:
            return (
                    is_valid_box(row=row, col=col, num=num)
                    and is_valid_row(row=row, num=num)
                    and is_valid_col(col=col, num=num)
            )

        def solve_sudoku() -> bool:
            row, col = empty_cell()

            if row is None:
                return True  # sudoku solved

            # try all number in this cell
            for num in range(1, 10):

                if is_valid_state(row, col, num):
                    # track it
                    board[row][col] = str(num)

                    # solve it
                    if solve_sudoku():
                        return True

                    # backtrack it
                    board[row][col] = "."

            return False

        solve_sudoku()


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = input_data[::]
    Solution_TLE().solveSudoku(output)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = input_data[::]
    Solution().solveSudoku(output)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([["5", "3", ".", ".", "7", ".", ".", ".", "."], ["6", ".", ".", "1", "9", "5", ".", ".", "."],
                         [".", "9", "8", ".", ".", ".", ".", "6", "."], ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
                         ["4", ".", ".", "8", ".", "3", ".", ".", "1"], ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
                         [".", "6", ".", ".", ".", ".", "2", "8", "."], [".", ".", ".", "4", "1", "9", ".", ".", "5"],
                         [".", ".", ".", ".", "8", ".", ".", "7", "9"]],
                        [['5', '3', '4', '6', '7', '8', '9', '1', '2'], ['6', '7', '2', '1', '9', '5', '3', '4', '8'],
                         ['1', '9', '8', '3', '4', '2', '5', '6', '7'], ['8', '5', '9', '7', '6', '1', '4', '2', '3'],
                         ['4', '2', '6', '8', '5', '3', '7', '9', '1'], ['7', '1', '3', '9', '2', '4', '8', '5', '6'],
                         ['9', '6', '1', '5', '3', '7', '2', '8', '4'], ['2', '8', '7', '4', '1', '9', '6', '3', '5'],
                         ['3', '4', '5', '2', '8', '6', '1', '7', '9']]),
                   test([["5", "3", "4", "6", "7", "8", "9", "1", "2"], ["6", "7", "2", "1", "9", "5", "3", "4", "8"],
                         ["1", "9", "8", "3", "4", "2", "5", "6", "7"], ["8", "5", "9", "7", "6", "1", "4", "2", "3"],
                         ["4", "2", "6", "8", "5", "3", "7", "9", "1"], ["7", "1", "3", "9", "2", "4", "8", "5", "6"],
                         ["9", "6", "1", "5", "3", "7", "2", "8", "4"], ["2", "8", "7", "4", "1", "9", "6", "3", "5"],
                         ["3", "4", "5", "2", "8", "6", "1", "7", "9"]],
                        [['5', '3', '4', '6', '7', '8', '9', '1', '2'], ['6', '7', '2', '1', '9', '5', '3', '4', '8'],
                         ['1', '9', '8', '3', '4', '2', '5', '6', '7'], ['8', '5', '9', '7', '6', '1', '4', '2', '3'],
                         ['4', '2', '6', '8', '5', '3', '7', '9', '1'], ['7', '1', '3', '9', '2', '4', '8', '5', '6'],
                         ['9', '6', '1', '5', '3', '7', '2', '8', '4'], ['2', '8', '7', '4', '1', '9', '6', '3', '5'],
                         ['3', '4', '5', '2', '8', '6', '1', '7', '9']])]

    CommonMethods.print_all_test_results(tests)
