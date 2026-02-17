# Definition for a binary tree node.
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if len(preorder) != len(inorder):
            return None

        n = len(preorder)

        # cache index
        _map = {}
        for i, item in enumerate(inorder):
            _map[item] = i

        pre_index = 0

        def _build_tree(preo, ino, _map, low, high):
            nonlocal pre_index

            if pre_index >= n or low > high:
                return None

            item = preorder[pre_index]
            pre_index += 1

            root = TreeNode(item)
            in_index = _map[item]

            root.left = _build_tree(preo, ino, _map, low, in_index - 1)
            root.right = _build_tree(preo, ino, _map, in_index + 1, high)
            return root

        return _build_tree(preorder, inorder, _map, 0, n - 1)
