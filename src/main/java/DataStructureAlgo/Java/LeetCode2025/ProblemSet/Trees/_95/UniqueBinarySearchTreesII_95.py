"""
Author: Nitin Gupta
Date: 6/28/2025
Question Title: 95. Unique Binary Search Trees II
Link: https://leetcode.com/problems/unique-binary-search-trees-ii/description/
Description: Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.



Example 1:


Input: n = 3
Output: [[1,None,2,None,3],[1,None,3,2],[2,1,3],[3,1,None,None,2],[3,2,None,1]]
Example 2:

Input: n = 1
Output: [[1]]


Constraints:

1 <= n <= 8
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link UniqueBinarySearchTrees_96.py}
DP-BaseProblem {@link }
<p><p>
Tags
-----

<p><p>
Company Tags
-----
@Microsoft
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.TreeTraversals import TreeTraversals
from helpers.common_methods import CommonMethods

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from functools import lru_cache

from helpers.templates.TreeNode import TreeNode


# ✅ Replacing memo with @lru_cache
# The @lru_cache decorator in Python automatically memorizes function calls based on their arguments — so if you call the function again with the same arguments, it returns the cached result.
class Solution_Memo_LRU_Cache:
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:
        if n == 1:
            return [TreeNode(n)]

        # The decorator uses the input arguments as dictionary keys. This is fine here because start and end are int
        @lru_cache(maxsize=None)
        def getAllBST(start, end) -> List[Optional[TreeNode]]:
            """
                G(n) = Sum (G(i-1) * G(n-1)) ; 1<=i<=n
            """

            if start > end:
                return [None]

            result = []
            for i in range(start, end + 1):

                # consider i as a root, then there will be [start...i-1] on left side and [i+1....end] on right side unique BST
                left_subtrees_roots = getAllBST(start, i - 1)  # [left_roots] = G(i-1)

                right_subTrees_roots = getAllBST(i + 1, end)  # [right_roots] = G(n-i)

                # we need to build all sub-tree from left and right tree combinations, being i as root
                for left_root in left_subtrees_roots:
                    for right_root in right_subTrees_roots:
                        root_i = TreeNode(i, left_root, right_root)
                        result.append(root_i)

            return result

        return getAllBST(1, n)


class Solution_Memo:
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:
        if n == 1:
            return [TreeNode(n)]

        memo = {}

        def getAllBST(start, end) -> List[Optional[TreeNode]]:
            """
                G(n) = Sum (G(i-1) * G(n-1)) ; 1<=i<=n
            """

            if start > end:
                return [None]

            if (start, end) in memo:
                return memo[(start, end)]

            result = []
            for i in range(start, end + 1):

                # consider i as a root, then there will be [start...i-1] on left side and [i+1....end] on right side unique BST
                left_subtrees_roots = getAllBST(start, i - 1)  # [left_roots] = G(i-1)

                right_subTrees_roots = getAllBST(i + 1, end)  # [right_roots] = G(n-i)

                # we need to build all sub-tree from left and right tree combinations, being i as root
                for left_root in left_subtrees_roots:
                    for right_root in right_subTrees_roots:
                        root_i = TreeNode(i, left_root, right_root)
                        result.append(root_i)

            memo[(start, end)] = result
            return result

        return getAllBST(1, n)


class Solution_RecursiveWithoutMemo:
    def generateTrees(self, n: int) -> List[Optional[TreeNode]]:

        memo: List[TreeNode] = []

        def getAllBST(start, end) -> List[Optional[TreeNode]]:
            """
                G(n) = Sum (G(i-1) * G(n-1)) ; 1<=i<=n
            """

            if start > end:
                return [None]

            result = []
            for i in range(start, end + 1):

                # consider i as a root, then there will be [start...i-1] on left side and [i+1....end] on right side unique BST
                left_subtrees_roots = getAllBST(start, i - 1)  # [left_roots] = G(i-1)

                right_subtrees_roots = getAllBST(i + 1, end)  # [right_roots] = G(n-i)

                # we need to build all sub-tree from left and right tree combinations, being i as root
                for left_root in left_subtrees_roots:
                    for right_root in right_subtrees_roots:
                        root_i = TreeNode(i, left_root, right_root)
                        result.append(root_i)

            return result

        return getAllBST(1, n)


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    roots = Solution_RecursiveWithoutMemo().generateTrees(input_data)
    output = [TreeTraversals.level_order_with_null(root) for root in roots]
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["RecursiveWithoutMemo", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    roots = Solution_Memo().generateTrees(input_data)
    output = [TreeTraversals.level_order_with_null(root) for root in roots]
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Memo", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    roots = Solution_Memo_LRU_Cache().generateTrees(input_data)
    output = [TreeTraversals.level_order_with_null(root) for root in roots]
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Memo_LRU_Cache", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(3, [[1, None, 2, None, 3], [1, None, 3, 2], [2, 1, 3], [3, 1, None, None, 2], [3, 2, None, 1]]),
                   test(1, [[1]])]

    CommonMethods.print_all_test_results(tests)
