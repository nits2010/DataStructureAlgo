"""
Author: Nitin Gupta
Date: 6/30/2025
Question Title: 814. Binary Tree Pruning
Link: https://leetcode.com/problems/binary-tree-pruning/description
Description: Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

A subtree of a node node is node plus every node that is a descendant of node.



Example 1:


Input: root = [1,null,0,0,1]
Output: [1,null,0,null,1]
Explanation:
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.
Example 2:


Input: root = [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]
Example 3:


Input: root = [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]


Constraints:

The number of nodes in the tree is in the range [1, 200].
Node.val is either 0 or 1.
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Tree
@DepthFirstSearch
@BinaryTree

<p><p>
Company Tags
-----
@Apple
@Facebook
@CapitalOne
@Hulu
@Twitter
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.TreeTraversals import TreeTraversals
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode


class Solution:
    def pruneTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        """
            Run a postorder traversal, find if any of the root which is non 1, eliminated.
        """
        if not root:
            return None

        root.left = self.pruneTree(root.left)
        root.right = self.pruneTree(root.right)

        if not root.left and not root.right and root.val == 0:
            return None
        return root


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    root: TreeNode = TreeBuilder.build_tree_from_level_order(input_data)

    obj = Solution()
    root = obj.pruneTree(root)
    output = TreeTraversals.level_order_with_null(root)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, None, 0, 0, 1], [1, None, 0, None, 1]),
                   test([1, 0, 1, 0, 0, 0, 1], [1, None, 1, None, 1]),
                   test([1, 1, 0, 1, 1, 0, 1, 0], [1, 1, 0, 1, 1, None, 1])]

    CommonMethods.print_all_test_results(tests)
