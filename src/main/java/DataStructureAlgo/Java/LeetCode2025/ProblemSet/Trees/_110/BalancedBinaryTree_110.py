"""
Author: Nitin Gupta
Date: 15/02/2026
Question Title: 110. Balanced Binary Tree
Link: https://leetcode.com/problems/balanced-binary-tree/description/
Description: Given a binary tree, determine if it is height-balanced.
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@easy
@Tree
@Depth-FirstSearch
@BinaryTree

<p><p>
Company Tags
-----
@Amazon
@Spotify
@Facebook
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

from helpers.common_methods import CommonMethods
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        if not root:
            return True

        def _isBalanced(root):
            if not root:
                return (True, 0)

            if root.left == None and root.right == None:  # child
                return (True, 1)

            is_left_balanced, left_height = _isBalanced(root.left)
            is_right_balanced, right_height = _isBalanced(root.right)

            is_balanced = (
                is_left_balanced
                and is_right_balanced
                and abs(left_height - right_height) <= 1
            )

            return (is_balanced, 1 + max(left_height, right_height))

        is_balanced, _ = _isBalanced(root)
        return is_balanced


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
