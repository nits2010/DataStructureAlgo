"""
Author: Nitin Gupta
Date: 9/1/2025
Question Title:
Link:
Description:
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----

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
    replacer = "#"

    def dfs(self, board, m, n, i, j):
        if i >= m or j >= n or i < 0 or j < 0 or board[i][j] != "O":
            return

        board[i][j] = Solution.replacer

        self.dfs(board, m, n, i + 1, j)
        self.dfs(board, m, n, i - 1, j)
        self.dfs(board, m, n, i, j + 1)
        self.dfs(board, m, n, i, j - 1)

    def save_boundary(self, board, m, n):
        for i in range(n):
            if board[0][i] == "O":
                self.dfs(board, m, n, 0, i)

            if board[m - 1][i] == "O":
                self.dfs(board, m, n, m - 1, i)

        # coloumn
        for i in range(m):
            if board[i][0] == "O":
                self.dfs(board, m, n, i, 0)

            if board[i][n - 1] == "O":
                self.dfs(board, m, n, i, n - 1)

    def recover_boundary(self, board, m, n):
        for i in range(m):
            for j in range(n):
                if board[i][j] == Solution.replacer:
                    board[i][j] = "O"

                elif board[i][j] == "O":
                    board[i][j] = "X"

    def solve(self, board: List[List[str]]) -> None:
        """ """
        m, n = len(board), len(board[0])
        # flip all the edge O to #
        # top row
        self.save_boundary(board, m, n)

        self.recover_boundary(board, m, n)


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    Solution().solve(input_data)
    pass_test = CommonMethods.compare_result(input_data, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, input_data, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]], [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]),
                   test([["X"]], [["X"]]),
                   test([['X', 'O', 'O', 'X', 'O'],['O', 'X', 'O', 'O', 'X'],['X', 'O', 'X', 'O', 'X'],['X', 'X', 'X', 'X', 'X']],
                   [['X', 'O', 'O', 'X', 'O'],['O', 'X', 'O', 'O', 'X'], ['X', 'X', 'X', 'O', 'X'],['X', 'X', 'X', 'X', 'X']])]

    CommonMethods.print_all_test_results(tests)
