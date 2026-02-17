# Definition for a binary tree node.
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:

        def _isValidBST(root, min_val, max_val):

            if not root:
                return True

            return (
                root.val > min_val
                and root.val < max_val
                and _isValidBST(root.left, min_val, root.val)
                and _isValidBST(root.right, root.val, max_val)
            )

        return _isValidBST(root, float("-inf"), float("inf"))
