"""
Author: Nitin Gupta
Date: 7/27/2025
Question Title: 212. Word Search II
Link: https://leetcode.com/problems/word-search-ii/description/
Description: Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
File reference
-----------
Duplicate {@link}
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
@Trie
@Matrix

<p><p>
Company Tags
-----
@Uber
@Amazon
@Cisco
@Microsoft
@Facebook
@Airbnb
@Apple
@Bloomberg
@Citadel
@Google
@Houzz
@Oracle
@Snapchat
@Uber
@Yahoo
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution_Trie_Prune:
    class TrieNode:
        def __init__(self):
            self.children = {}
            self.is_end_of_word = False

    class Trie:
        def __init__(self):
            self.root = Solution_Trie_Prune.TrieNode()

        def insert(self, word: str):
            pCrawl = self.root

            for w in word:

                if w not in pCrawl.children:
                    pCrawl.children[w] = Solution_Trie_Prune.TrieNode()

                pCrawl = pCrawl.children[w]

            pCrawl.is_end_of_word = True

    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        """
        We can leverage the Word search problem solution to scan each word and if that is found, then add in solution.
        That will be O(W * M * N * 4^L) where W is size of words, M and N is size of board

        However to improve the time complexity, we can use Trie.

        This implementation uses a Trie (prefix tree) for efficient prefix checking
        and backtracking DFS to explore all valid paths on the board.

        Args:
            board (List[List[str]]): 2D grid of characters representing the board.
            words (List[str]): List of valid words to search for on the board.

        Returns:
            List[str]: List of words found on the board that exist in the given list.

        Time Complexity:
            Still O(M * N * 4^L) in the worst case,
            but **in practice, significantly faster** due to:
            - Skipping dead-end Trie branches
            - Avoiding recomputation of paths that can't yield new words

        Space Complexity:
            O(K), where K is the total number of characters in all words.

        """

        rows, cols = len(board), len(board[0])
        trie = self.Trie()

        # insert
        for word in words:
            trie.insert(word)

        # now we need to search the board character if they can form a word from.
        result = []

        directions = [[0, 1], [0, -1], [1, 0], [-1, 0]]

        def dfs(i, j, word, node):
            """
            This is the DFS, that scan the current (i,j) and go all 4 directions to explore further

            Args:
                i (int) : current row index in exploration
                j (int) : current col index in exploration
                node  : current trie node for exploration
            """

            char = board[i][j]
            curr_node = node.children[char]

            if curr_node.is_end_of_word:
                result.append(word + char)
                curr_node.is_end_of_word = False  # avoid duplicates

            board[i][j] = "*"

            for dr, dc in directions:
                row, col = i + dr, j + dc

                if (
                        0 <= row < rows
                        and 0 <= col < cols
                        and board[row][col] in curr_node.children
                ):
                    dfs(row, col, word + char, curr_node)

            board[i][j] = char

            # ======= prune the trie ============

            # if there is no children left for pCrawl i.e. board[i][j] has no further connectivity, then remove it from parent
            if not curr_node.children:
                node.children.pop(board[i][j])

        # =======================================
        for i in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j] in trie.root.children:
                    dfs(i, j, "", trie.root)

        return result


class Solution_Trie:
    class TrieNode:
        def __init__(self):
            self.children = {}
            self.is_end_of_word = False

    class Trie:
        def __init__(self):
            self.root = Solution_Trie.TrieNode()

        def insert(self, word: str):
            pCrawl = self.root

            for w in word:

                if w not in pCrawl.children:
                    pCrawl.children[w] = Solution_Trie.TrieNode()

                pCrawl = pCrawl.children[w]

            pCrawl.is_end_of_word = True

    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        """
        We can leverage the Word search problem solution to scan each word and if that is found, then add in solution.
        That will be O(W * M * N * 4^L) where W is size of words, M and N is size of board

        However to improve the time complexity, we can use Trie.

        This implementation uses a Trie (prefix tree) for efficient prefix checking
        and backtracking DFS to explore all valid paths on the board.

        Args:
            board (List[List[str]]): 2D grid of characters representing the board.
            words (List[str]): List of valid words to search for on the board.

        Returns:
            List[str]: List of words found on the board that exist in the given list.

        Time Complexity:
            O(M * N * 4^L), where M and N are the dimensions of the board,
            and L is the maximum word length. However, it’s optimized with Trie pruning.

        Space Complexity:
            O(K), where K is the total number of characters in all words.
        """

        rows, cols = len(board), len(board[0])
        trie = self.Trie()

        # insert
        [trie.insert(word) for word in words]

        # now we need to search the board character if they can form a word from.
        result = set()

        directions = [[0, 1], [0, -1], [1, 0], [-1, 0]]

        def dfs(i: int, j: int, word: str, pCrawl: Solution_Trie.TrieNode):

            if pCrawl.is_end_of_word:
                result.add(word)

            tmp = board[i][j]
            board[i][j] = "*"

            for r, c in directions:
                row, col = i + r, j + c

                if 0 <= row < rows and 0 <= col < cols and board[row][col] != "*":
                    current = board[row][col]
                    if current in pCrawl.children:
                        dfs(row, col, word + current, pCrawl.children[current])

            board[i][j] = tmp

        # =======================================
        for i in range(len(board)):
            for j in range(len(board[0])):
                current = board[i][j]
                if current in trie.root.children:
                    dfs(i, j, current, trie.root.children[current])

        return list(result)


def test(board, words, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Board", "Words", "Expected"], True, board, words, expected)
    pass_test, final_pass = True, True

    output = Solution_Trie().findWords(board, words)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Trie", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Trie_Prune().findWords(board, words)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Trie_Prune Pruning", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(board=[["o", "a", "a", "n"], ["e", "t", "a", "e"], ["i", "h", "k", "r"], ["i", "f", "l", "v"]],
                        words=["oath", "pea", "eat", "rain"], expected=["eat", "oath"]),
                   test(board=[["a", "b"], ["c", "d"]], words=["abcb"], expected=[])]

    CommonMethods.print_all_test_results(tests)
