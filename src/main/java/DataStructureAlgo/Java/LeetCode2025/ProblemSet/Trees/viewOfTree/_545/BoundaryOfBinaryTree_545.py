
class Node:
    def __init__(self, val):
        self.data = val
        self.right = None
        self.left = None

class Solution:
    def boundaryTraversal(self, root):
        left_boundary, right_boundary, leaf_boundary = [], [], []
        
        
        def left_boundary_view(root):
            """ Take root and go left/right """
            if not root:
                return 
            
            if root.left:
                left_boundary.append(root.data)
                left_boundary_view(root.left)
            
            elif root.right:
                left_boundary.append(root.data)
                left_boundary_view(root.right)
            
            # skip leaf nodes


        
        
        def right_boundary_view(root):
            """  go right/left Take root """
            if not root:
                return 
            
            if root.right:
                right_boundary_view(root.right)
                right_boundary.append(root.data)
            
            elif root.left:
                right_boundary_view(root.left)
                right_boundary.append(root.data)
            
            # skip leaf nodes
        
        
        def leaf_boundary_view(root):
            
            if not root:
                return 
            
            if not root.left and not root.right:
                left_boundary.append(root.data)
            else:
                leaf_boundary_view(root.left)
                leaf_boundary_view(root.right)
           
        
        # left boundary and left leaves node
        left_boundary_view(root.left)
        leaf_boundary_view(root.left)
        
        # right boundary and right leaves node
        right_boundary_view(root.right)
        leaf_boundary_view(root.right)
        
        boundary_view = [root.data] + left_boundary + leaf_boundary + right_boundary
        
        return boundary_view
        
        
        
        