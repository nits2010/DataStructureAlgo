"""
Author: Nitin Gupta
Date: 6/24/2025
Question Title: 99. Recover Binary Search Tree
Link: https://leetcode.com/problems/recover-binary-search-tree/description
Description: You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.



Example 1:


Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
Example 2:


Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.


Constraints:

The number of nodes in the tree is in the range [2, 1000].
-231 <= Node.val <= 231 - 1


Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?

File reference
-----------
Duplicate {@link RecoverBinarySearchTree.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Tree
@DepthFirstSearch
@BinarySearchTree
@BinaryTree


<p><p>
Company Tags
-----
@Amazon
@Microsoft
@Apple
@Google
@Adobe
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode


class Solution:
    def __init__(self):
        self.recovered = None
        self.second = None
        self.first = None
        self.prev = None

    def recoverTree(self, root: Optional[TreeNode]) -> None:
        """
        Fix a binary search tree where exactly two nodes have been swapped by mistake.

        In-order traversal of a BST yields sorted order.
        If during traversal, we detect prev.val > current.val, we know there's a violation.
        The first such violation gives us the first wrong node.
        The second (or last if adjacent) gives us the second wrong node.

        Once both nodes are found, we swap their values.
        """

        # To track the previous node
        self.prev = None

        # To track the first and second mis-order node
        self.first = None
        self.second = None

        # To track the tree has been recovered or not
        self.recovered = False

        # This method will perform a inorder traversal on tree, and capture the pre/next sequence and fix when a mis-order found
        # This also return a boolean which indicate, if need to break the traversal further
        def inorder(current: Optional[TreeNode]):

            if not current or self.recovered:  # Tree has been recovered, stop further iterations
                return False

            # left -> root -> right
            inorder(current.left)

            # if prev exists, then check the sequence
            if self.prev:
                if self.prev.val > current.val:  # a misorder found
                    if not self.first:
                        self.first = self.prev
                        self.second = current
                    else:
                        self.second = current

                        # stop further iterations, Found both nodes, stop traversal
                        self.recovered = True
                        return

            # update prev
            self.prev = current

            inorder(current.right)

        inorder(root)
        if self.first and self.second:
            self.first.val, self.second.val = self.second.val, self.first.val


def inOrder(root: Optional[TreeNode]) -> List[int]:
    if not root:
        return []
    return inOrder(root.left) + [root.val] + inOrder(root.right)


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    root: TreeNode = TreeBuilder.build_tree_from_level_order(input_data)


    obj = Solution()
    obj.recoverTree(root)
    output = inOrder(root)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([3, 1, 4, None, None, 2], [1, 2, 3, 4])]

    CommonMethods.print_all_test_results(tests)
