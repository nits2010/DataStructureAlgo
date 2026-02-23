"""
Author: Nitin Gupta
Date: 7/26/2025
Question Title: 79. Word Search
Link: https://leetcode.com/problems/word-search/description
Description: Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.



Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false


Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.


Follow up: Could you use search pruning to make your solution faster with a larger board?
File reference
-----------
Duplicate {@link BoggleSearchWordSearchI}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Array
@String
@Backtracking
@DepthFirstSearch
@Matrix

<p><p>
Company Tags
-----
@Amazon
@Twitter
@Uber
@Karat
@Microsoft
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque, Counter
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution_Followup:

    def is_prunable(self, board: List[List[str]], word: str) -> bool:

        """
        board = [
            ['A', 'B', 'C'],
            ['D', 'E', 'F']
            ]
        board_count = Counter(c for row in board for c in row)
        print(board_count)

            Counter({'A': 1, 'B': 1, 'C': 1, 'D': 1, 'E': 1, 'F': 1})

        """

        # count frequencies
        word_count = Counter(word)

        # chars = []
        # for row in board:
        #     for c in row:
        #         chars.append(c)
        # Counter(chars)

        board_count = Counter(c for row in board for c in row)

        # now make sure that each character has required frequency in board
        for ch in word_count:
            if board_count[ch] < word_count[ch]:
                return False
        return True

    def exist(self, board: List[List[str]], word: str) -> bool:

        if not self.is_prunable(board, word):
            return False

        m = len(board)
        n = len(board[0])
        directions = [[0, 1], [1, 0], [-1, 0], [0, -1]]

        def is_safe(index, i, j) -> bool:
            if 0 <= i < m and 0 <= j < n and board[i][j] == word[index]:
                return True

            return False

        def dfs(index: int, i: int, j: int) -> bool:
            """
                Performs DFS and backtracking to check if the word exists in the board starting from (i, j).

                Args:
                    i (int): Current row index in the board.
                    j (int): Current column index in the board.
                    index (int): Current index in the target word being matched.

                Returns:
                    bool: True if the remaining substring of the word can be found starting from (i, j), else False.
            """

            if index == len(word):
                return True

            if not is_safe(index, i, j):
                return False

            tmp = board[i][j]
            board[i][j] = "*"

            for direction in directions:
                if dfs(index + 1, i + direction[0], j + direction[1]):
                    return True

            board[i][j] = tmp
            return False

        for i in range(m):
            for j in range(n):

                if board[i][j] == word[0]:
                    if dfs(0, i, j):
                        return True

        return False


class Solution_WithoutVisitedArray:

    def exist(self, board: List[List[str]], word: str) -> bool:

        m = len(board)
        n = len(board[0])
        directions = [[0, 1], [1, 0], [-1, 0], [0, -1]]

        def is_safe(index, i, j) -> bool:
            if 0 <= i < m and 0 <= j < n and board[i][j] == word[index]:
                return True

            return False

        def dfs(index: int, i: int, j: int) -> bool:
            """
                Performs DFS and backtracking to check if the word exists in the board starting from (i, j).

                Args:
                    i (int): Current row index in the board.
                    j (int): Current column index in the board.
                    index (int): Current index in the target word being matched.

                Returns:
                    bool: True if the remaining substring of the word can be found starting from (i, j), else False.
            """

            if index == len(word):
                return True

            if not is_safe(index, i, j):
                return False

            tmp = board[i][j]
            board[i][j] = "*"

            for direction in directions:
                if dfs(index + 1, i + direction[0], j + direction[1]):
                    return True

            board[i][j] = tmp
            return False

        for i in range(m):
            for j in range(n):

                if board[i][j] == word[0]:
                    if dfs(0, i, j):
                        return True

        return False


class Solution_UsingVisitedArray:
    def exist(self, board: List[List[str]], word: str) -> bool:

        len_rows = len(board)
        len_cols = len(board[0])

        visited = [[False] * len_cols for _ in range(len_rows)]

        def is_safe(index, i, j) -> bool:
            if 0 <= i < len_rows and 0 <= j < len_cols and not visited[i][j]:
                return index < len(word) and board[i][j] == word[index]
            return False

        def dfs(index: int, i: int, j: int) -> bool:
            if index == len(word):
                return True

            if not is_safe(index, i, j):
                return False

            visited[i][j] = True

            result = (
                    dfs(index + 1, i + 1, j)
                    or dfs(index + 1, i, j + 1)
                    or dfs(index + 1, i - 1, j)
                    or dfs(index + 1, i, j - 1)
            )

            if result:
                return True

            visited[i][j] = False
            return False

        for i in range(len_rows):
            for j in range(len_cols):

                if not visited[i][j] and board[i][j] == word[0]:
                    if dfs(0, i, j):
                        return True

        return False


def test(input_data, word, expected):
    """
    Test function to verify the solution
    """
    import copy

    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_UsingVisitedArray().exist(copy.deepcopy(input_data), word)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_UsingVisitedArray", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Followup().exist(copy.deepcopy(input_data), word)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Followup", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_WithoutVisitedArray().exist(copy.deepcopy(input_data), word)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_WithoutVisitedArray", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(input_data=[["A", "B", "C", "E"], ["S", "F", "C", "S"], ["A", "D", "E", "E"]], word="ABCCED",
                        expected=True),
                   test(input_data=[["A", "B", "C", "E"], ["S", "F", "C", "S"], ["A", "D", "E", "E"]], word="SEE",
                        expected=True),
                   test(input_data=[["A", "B", "C", "E"], ["S", "F", "C", "S"], ["A", "D", "E", "E"]], word="ABCB",
                        expected=False), ]

    CommonMethods.print_all_test_results(tests)
