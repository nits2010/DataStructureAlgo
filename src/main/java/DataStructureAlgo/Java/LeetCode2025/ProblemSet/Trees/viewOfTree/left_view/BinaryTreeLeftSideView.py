# Definition for a binary tree node.
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
class BinaryTreeLeftSideView:
    def leftSideView(self, root: Optional[TreeNode]) -> List[int]:

        _left_view = []

        def left_view(root, level):
            if not root:
                return 
            
            if len(_left_view) == level : # we reach the current level
                _left_view.append(root.val)

            # for right view, process right first
            left_view(root.left, level+1)
            left_view(root.right, level+1)

        left_view(root, 0)
        return _left_view    
        