"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: Lowest Common Ancestor Of A Binary Tree
Link: https://leetcode.com/problems/lowest-common-ancestor-of-abinary-tree/
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

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class LowestCommonAncestorOfABinaryTree_236:
    def lowestCommonAncestor(
        self, root: TreeNode, p: TreeNode, q: TreeNode
    ) -> TreeNode:
        if not root:
            return None

        if root == p or root == q:
            return root

        left = self.lowestCommonAncestor(root.left, p, q)
        right = self.lowestCommonAncestor(root.right, p, q)

        # if root is the lca
        if left and right:
            return root

        # otherwise either of one is lca
        return left if not right else right
