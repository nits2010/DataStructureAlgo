from typing import Optional

class TreeNode:
    def __init__(self, val: int = 0, left: Optional['TreeNode'] = None, right: Optional['TreeNode'] = None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def _get_inorder_successor(self, root: Optional[TreeNode]) -> TreeNode:
        """
        Returns the inorder successor (the smallest value in right subtree).
        """
        root = root.right
        while root.left:
            root = root.left
        return root

    def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        """
        Deletes a node with the given key from a BST and returns the new root.
        """
        if not root:
            return None

        if key < root.val:
            root.left = self.deleteNode(root.left, key)
        elif key > root.val:
            root.right = self.deleteNode(root.right, key)
        else:
            # Case 1: No children
            if not root.left and not root.right:
                return None
            # Case 2: One child
            if not root.left:
                return root.right
            if not root.right:
                return root.left
            # Case 3: Two children
            inorder_successor = self._get_inorder_successor(root)
            root.val = inorder_successor.val
            root.right = self.deleteNode(root.right, inorder_successor.val)

        return root


