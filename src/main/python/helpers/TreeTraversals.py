"""
Author: Nitin Gupta
Date: 6/28/2025

Binary Tree Traversals (Recursive Implementation)

This module provides recursive implementations of various tree traversal algorithms:
- In-order Traversal
- Pre-order Traversal
- Post-order Traversal
- Level-order Traversal

Also supports N-ary tree traversals.
"""

from collections import deque
from typing import List, Optional, Any

from helpers.templates.NArrayTreeNode import NArrayTreeNode
from helpers.templates.TreeNode import TreeNode


class TreeTraversals:
    """
    A class containing recursive implementations of various tree traversal algorithms.
    """
    
    @staticmethod
    def in_order(root: Optional[TreeNode]) -> List[int]:
        """
        Perform in-order traversal of a binary tree (Left-Root-Right).
        
        Args:
            root: Root of the binary tree
            
        Returns:
            List of node values in in-order
        """
        result = []
        
        def _inorder(node):
            if not node:
                return
            _inorder(node.left)
            result.append(node.val)
            _inorder(node.right)
            
        _inorder(root)
        return result
    
    @staticmethod
    def pre_order(root: Optional[TreeNode]) -> List[int]:
        """
        Perform pre-order traversal of a binary tree (Root-Left-Right).
        
        Args:
            root: Root of the binary tree
            
        Returns:
            List of node values in pre-order
        """
        result = []
        
        def _preorder(node):
            if not node:
                return
            result.append(node.val)
            _preorder(node.left)
            _preorder(node.right)
            
        _preorder(root)
        return result
    
    @staticmethod
    def post_order(root: Optional[TreeNode]) -> List[int]:
        """
        Perform post-order traversal of a binary tree (Left-Right-Root).
        
        Args:
            root: Root of the binary tree
            
        Returns:
            List of node values in post-order
        """
        result = []
        
        def _postorder(node):
            if not node:
                return
            _postorder(node.left)
            _postorder(node.right)
            result.append(node.val)
            
        _postorder(root)
        return result
    
    @staticmethod
    def level_order(root: Optional[TreeNode]) -> List[List[int]]:
        """
        Perform level-order traversal of a binary tree (recursive implementation).
        
        Args:
            root: Root of the binary tree
            
        Returns:
            List of lists, where each inner list contains node values at that level
        """
        result = []
        
        def _level_order(nodes):
            if not nodes:
                return
            
            current_level = []
            next_level = []
            
            for node in nodes:
                if node:
                    current_level.append(node.val)
                    if node.left:
                        next_level.append(node.left)
                    if node.right:
                        next_level.append(node.right)
            
            if current_level:
                result.append(current_level)
            
            _level_order(next_level)
        
        if root:
            _level_order([root])
        return result
        
    @staticmethod
    def level_order_iterative(root: Optional[TreeNode]) -> List[int]:
        """
        Perform level-order traversal of a binary tree (iterative implementation).
        Returns a flattened list of node values in level order.
        
        Args:
            root: Root of the binary tree
            
        Returns:
            List of node values in level order
        """
        if not root:
            return []
            
        from collections import deque
        
        result = []
        queue = deque([root])
        
        while queue:
            node = queue.popleft()
            result.append(node.val)
            
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
                
        return result
        
    @staticmethod
    def level_order_with_null(root: Optional[TreeNode]) -> List[Optional[int]]:
        """
        Perform level-order traversal of a binary tree including null values.
        This is useful for serialization/deserialization of binary trees.
        
        Args:
            root: Root of the binary tree
            
        Returns:
            List of node values in level order, with None for null nodes
        """
        if not root:
            return []
            
        from collections import deque
        
        result = []
        queue = deque([root])
        
        while queue:
            level_size = len(queue)
            has_next_level = False
            
            # Check if there are any non-null nodes in the next level
            for node in queue:
                if node:
                    has_next_level = True
                    break
                    
            if not has_next_level:
                break
                
            for _ in range(level_size):
                node = queue.popleft()
                
                if node:
                    result.append(node.val)
                    queue.append(node.left)
                    queue.append(node.right)
                else:
                    # Check if there are any non-null nodes left in the queue
                    has_non_null = any(n is not None for n in queue)
                    if has_non_null:
                        result.append(None)
        
        return result
    
    @staticmethod
    def nary_pre_order(root: Optional[NArrayTreeNode]) -> List[int]:
        """
        Perform pre-order traversal of an N-ary tree.
        
        Args:
            root: Root of the N-ary tree
            
        Returns:
            List of node values in pre-order
        """
        result = []
        
        def _nary_preorder(node):
            if not node:
                return
            result.append(node.val)
            for child in node.children:
                _nary_preorder(child)
                
        _nary_preorder(root)
        return result
    
    @staticmethod
    def nary_post_order(root: Optional[NArrayTreeNode]) -> List[int]:
        """
        Perform post-order traversal of an N-ary tree.
        
        Args:
            root: Root of the N-ary tree
            
        Returns:
            List of node values in post-order
        """
        result = []
        
        def _nary_postorder(node):
            if not node:
                return
            for child in node.children:
                _nary_postorder(child)
            result.append(node.val)
                
        _nary_postorder(root)
        return result

def test():
    """
    Test function to verify the tree traversal implementations
    """
    # Create a sample binary tree:
    #       1
    #      / \
    #     2   3
    #    / \
    #   4   5
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.left = TreeNode(4)
    root.left.right = TreeNode(5)
    
    # Test binary tree traversals
    print("In-order:", TreeTraversals.in_order(root))      # [4, 2, 5, 1, 3]
    print("Pre-order:", TreeTraversals.pre_order(root))    # [1, 2, 4, 5, 3]
    print("Post-order:", TreeTraversals.post_order(root))  # [4, 5, 2, 3, 1]
    print("Level-order:", TreeTraversals.level_order(root)) # [[1], [2, 3], [4, 5]]
    
    # Create a sample N-ary tree:
    #        1
    #      / | \
    #     2  3  4
    #    / \
    #   5   6
    nary_root = NArrayTreeNode(1, [
        NArrayTreeNode(2, [
            NArrayTreeNode(5),
            NArrayTreeNode(6)
        ]),
        NArrayTreeNode(3),
        NArrayTreeNode(4)
    ])
    
    # Test N-ary tree traversals
    print("N-ary Pre-order:", TreeTraversals.nary_pre_order(nary_root))   # [1, 2, 5, 6, 3, 4]
    print("N-ary Post-order:", TreeTraversals.nary_post_order(nary_root)) # [5, 6, 2, 3, 4, 1]

if __name__ == "__main__":
    test()
