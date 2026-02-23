# Definition for a binary tree node.
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        
        
class BinaryTreeRightSideView_199:
   def rightSideView(self, root: Optional[TreeNode]) -> List[int]:

        _right_view = []

        def right_view(root, level):
            if not root:
                return 
            
            if len(_right_view) == level : # we reach the current level
                _right_view.append(root.val)

            # for right view, process right first
            right_view(root.right, level+1)
            right_view(root.left, level+1)

        right_view(root, 0)
        return _right_view    
        