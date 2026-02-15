"""
Author: Nitin Gupta
Date: 6/23/2025
Question Title: 543. Diameter of Binary Tree
Link: https://leetcode.com/problems/diameter-of-binary-tree/description/
Description: Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.



Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1


Constraints:

The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100
File reference
-----------
Duplicate {@link}
Similar {@link MaximumWidthOfBinaryTree_662}
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
@Facebook
@Amazon
@Bloomberg
@Google
@Microsoft
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from typing import List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode


class Solution:
    def __init__(self):
        self.width = None

    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        """
        Diameter of binary tree is total width of the binary tree. 
        The width is define by the left tree height + right tree height (height == depth)
        Height / Depth of tree is defiend by max of left,right height + 1 
        """
        if not root:
            return 0

        self.width = 0

        def _diameter(root) -> int:
            if not root:
                return 0

            left_height = _diameter(root.left)
            right_height = _diameter(root.right)

            self.width = max(self.width, left_height + right_height)

            return max(left_height, right_height) + 1

        _diameter(root)
        return self.width


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    root: TreeNode = TreeBuilder.build_tree_from_level_order(input_data)
    output = Solution().diameterOfBinaryTree(root)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["diameter", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, 2, 3, 4, 5], 3),
                   test([1, 2], 1)]

    CommonMethods.print_all_test_results(tests)
