"""
Author: Nitin Gupta
Date: 6/20/2025
Question Title: 662. Maximum Width of Binary Tree
Link: https://leetcode.com/problems/maximum-width-of-binary-tree/description
Description: Given the root of a binary tree, return the maximum width of the given tree.

The maximum width of a tree is the maximum width among all levels.

The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.

It is guaranteed that the answer will in the range of a 32-bit signed integer.



Example 1:


Input: root = [1,3,2,5,3,null,9]
Output: 4
Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
Example 2:


Input: root = [1,3,2,5,null,null,9,6,null,7]
Output: 7
Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
Example 3:


Input: root = [1,3,2,5]
Output: 2
Explanation: The maximum width exists in the second level with length 2 (3,2).


Constraints:

The number of nodes in the tree is in the range [1, 3000].
-100 <= Node.val <= 100
File reference
-----------
Duplicate {@link MaximumWidthBinaryTree.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Tree
@DepthFirstSearch
@BreadthFirstSearch
@BinaryTree

<p><p>
Company Tags
-----
@Amazon
@Google
@Microsoft
@Facebook
@Bloomberg
@Uber
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from typing import List, Optional, Dict, Any
from collections import deque

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode


# recursive solution, depth_map as cache of finding the leftmost and rightmost node position
class Solution_DFS:
    def __init__(self):
        self.width = None

    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        depth_map = {0: 1}  # root is at depth 0 and position = 1
        self.width = 0

        def _widthOfBinaryTree(root, depth, position, depth_map):
            if not root:
                return

            if depth not in depth_map:
                depth_map[depth] = position

            self.width = max(self.width, position - depth_map[depth] + 1)

            if root.left:
                _widthOfBinaryTree(root.left, depth + 1, 2 * position, depth_map)
            if root.right:
                _widthOfBinaryTree(root.right, depth + 1, 2 * position + 1, depth_map)

        _widthOfBinaryTree(root, 0, 1, depth_map)
        return self.width


class Solution_BFS:
    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0

        # push root node at pos 1
        queue = deque([(root, 1)])

        width = 0

        while queue:

            # front of the queue would be the leftmost node and end of the queue would be the rightmost node
            width = max(width, queue[-1][1] - queue[0][1] + 1)

            for _ in range(len(queue)):
                root, pos = queue.popleft()

                if root.left:
                    queue.append((root.left, 2 * pos))  # the left node lies in 2*pos from root
                if root.right:
                    queue.append((root.right, 2 * pos + 1))  # the right node lies in 2*pos from root

        return width


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    root: TreeNode = TreeBuilder.build_tree_from_level_order(input_data)

    output = Solution_DFS().widthOfBinaryTree(root)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_BFS().widthOfBinaryTree(root)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, 3, 2, 5, 3, None, 9], 4),
                   test([1, 3, 2, 5, None, None, 9, 6, None, 7], 7),
                   test([1, 3, 2, 5], 2),
                   ]

    CommonMethods.print_all_test_results(tests)
