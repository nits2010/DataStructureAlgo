# Definition for a binary tree node.
import math
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class MinimumDistanceBetweenBSTNodes_783:
    def __init__(self):
        self.min_val = math.inf
        self.predecessor: Optional[TreeNode] = None

    def minDiffInBST(self, root: Optional[TreeNode]) -> int:

        if root is None:
            return 0

        self.minDiffInBST(root.left)

        if self.predecessor is not None:
            self.min_val = min(self.min_val, root.val - self.predecessor.val)
        self.predecessor = root

        self.minDiffInBST(root.right)

        return self.min_val


if __name__ == "__main__":

    root = TreeNode(4)
    root.left = TreeNode(2)
    root.right = TreeNode(6)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(3)
    root.right.left = TreeNode(5)
    root.right.right = TreeNode(7)

    obj = MinimumDistanceBetweenBSTNodes_783()
    print(obj.minDiffInBST(root))