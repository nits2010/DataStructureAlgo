from typing import List, Optional, Dict, Any
from collections import deque

from DataStructureAlgo.Java.helpers.templates.NArrayTreeNode import NArrayTreeNode
from DataStructureAlgo.Java.helpers.templates.TreeNode import TreeNode
from DataStructureAlgo.Java.helpers.templates.TreeNodeWithRandom import TreeNodeWithRandom


class TreeBuilder:
    """
    A utility class to build various types of trees in Python.
    """
    _pre_index = 0
    _post_index = 0

    class NaryTree:
        @staticmethod
        def build_tree_from_level_order(elements: List[Optional[int]]) -> Optional[NArrayTreeNode]:
            """
            Build an N-ary tree from level order traversal (BFS) representation.
            
            Args:
                elements: List of node values in level order, with None as separator between children.
                
            Returns:
                Root node of the constructed N-ary tree, or None if input is empty.
            """
            if not elements or elements[0] is None:
                return None

            root = NArrayTreeNode(elements[0])
            queue = deque([root])
            i = 2  # Start from index 2 (after root and first None)

            while queue and i < len(elements):
                current = queue.popleft()

                # Process children until we hit None or end of list
                children = []
                while i < len(elements) and elements[i] is not None:
                    child = NArrayTreeNode(elements[i])
                    children.append(child)
                    queue.append(child)
                    i += 1

                if children:
                    current.children = children
                i += 1  # Skip the None separator

            return root

    @staticmethod
    def build_tree_from_level_order(elements: List[Optional[int]]) -> Optional[TreeNode]:
        """
        Build a binary tree from level order traversal (BFS) representation.
        
        Args:
            elements: List of node values in level order, with None representing null nodes.
            
        Returns:
            Root node of the constructed binary tree, or None if input is empty.
            
        Example:
            # Construct the following tree:
            #       1
            #      / \
            #     2   3
            #    /   / \
            #   4   5   6
            
            elements = [1, 2, 3, 4, None, 5, 6]
            root = TreeBuilder.build_tree_from_level_order(elements)
        """
        if not elements or elements[0] is None:
            return None

        root = TreeNode(elements[0])
        queue = deque([root])
        i = 1

        while queue and i < len(elements):
            current = queue.popleft()

            # Process left child
            if elements[i] is not None and current is not None:
                current.left = TreeNode(elements[i])
                queue.append(current.left)
            i += 1

            # Process right child
            if i < len(elements) and elements[i] is not None and current is not None:
                current.right = TreeNode(elements[i])
                queue.append(current.right)
            i += 1

        return root

    @staticmethod
    def build_tree_from_level_order_with_parent(elements: List[Optional[int]]) -> Optional[TreeNodeWithRandom]:
        """
        Build a binary tree with parent pointers from level order traversal.
        
        Args:
            elements: List of node values in level order, with None representing null nodes.
            
        Returns:
            Root node of the constructed binary tree with parent pointers, or None if input is empty.
        """
        if not elements or elements[0] is None:
            return None

        root = TreeNodeWithRandom(elements[0])
        queue = deque([root])
        i = 1

        while queue and i < len(elements):
            current = queue.popleft()

            if elements[i] is not None and current is not None:
                current.left = TreeNodeWithRandom(elements[i], parent=current)
                queue.append(current.left)
            i += 1

            if i < len(elements) and elements[i] is not None and current is not None:
                current.right = TreeNodeWithRandom(elements[i], parent=current)
                queue.append(current.right)
            i += 1

        return root

    @staticmethod
    def _insert_node_at_first_available_place(root: Optional[TreeNode], node: TreeNode) -> TreeNode:
        """Helper method to insert a node at the first available position in level order."""
        if not root:
            return node

        queue = deque([root])
        while queue:
            current = queue.popleft()

            if not current.left:
                current.left = node
                return root
            else:
                queue.append(current.left)

            if not current.right:
                current.right = node
                return root
            else:
                queue.append(current.right)

        return root

    @staticmethod
    def build_tree_from_inorder_and_preorder(inorder: List[int], preorder: List[int]) -> Optional[TreeNode]:
        """
        Build a binary tree from inorder and preorder traversal sequences.
        
        Args:
            inorder: List of node values in inorder traversal order.
            preorder: List of node values in preorder traversal order.
            
        Returns:
            Root node of the constructed binary tree, or None if inputs are invalid.
        """
        if not inorder or not preorder or len(inorder) != len(preorder):
            return None

        inorder_map = {val: idx for idx, val in enumerate(inorder)}
        TreeBuilder._pre_index = 0

        def build_tree(in_start: int, in_end: int) -> Optional[TreeNode]:
            if in_start > in_end:
                return None

            root_val = preorder[TreeBuilder._pre_index]
            root = TreeNode(root_val)
            TreeBuilder._pre_index += 1

            in_index = inorder_map[root_val]

            root.left = build_tree(in_start, in_index - 1)
            root.right = build_tree(in_index + 1, in_end)

            return root

        return build_tree(0, len(inorder) - 1)

    @staticmethod
    def build_tree_from_inorder_and_postorder(inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        """
        Build a binary tree from inorder and postorder traversal sequences.
        
        Args:
            inorder: List of node values in inorder traversal order.
            postorder: List of node values in postorder traversal order.
            
        Returns:
            Root node of the constructed binary tree, or None if inputs are invalid.
        """
        if not inorder or not postorder or len(inorder) != len(postorder):
            return None

        inorder_map = {val: idx for idx, val in enumerate(inorder)}
        TreeBuilder._post_index = len(postorder) - 1

        def build_tree(in_start: int, in_end: int) -> Optional[TreeNode]:
            if in_start > in_end:
                return None

            root_val = postorder[TreeBuilder._post_index]
            root = TreeNode(root_val)
            TreeBuilder._post_index -= 1

            in_index = inorder_map[root_val]

            # Build right subtree first since we're processing postorder in reverse
            root.right = build_tree(in_index + 1, in_end)
            root.left = build_tree(in_start, in_index - 1)

            return root

        return build_tree(0, len(inorder) - 1)


@staticmethod
def build_tree_from_preorder_and_postorder(preorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
    """
    Build a binary tree from preorder and postorder traversal sequences.

    Args:
        preorder: List of node values in preorder traversal order.
        postorder: List of node values in postorder traversal order.

    Returns:
        Root node of the constructed binary tree, or None if inputs are invalid.
    """
    if not preorder or not postorder or len(preorder) != len(postorder):
        return None

    # Cache the index for postorder
    post_index = {val: i for i, val in enumerate(postorder)}

    def build_tree(pre_start: int, pre_end: int, post_start: int) -> Optional[TreeNode]:
        # If no elements left
        if pre_start > pre_end:
            return None

        current_value = preorder[pre_start]
        root = TreeNode(current_value)

        # If there's only one element
        if pre_start == pre_end:
            return root

        # There are multiple elements > 1
        # Find the root of the left child in the preorder array
        left_root = preorder[pre_start + 1]

        # Get the next element's position in postorder
        post_idx = post_index[left_root]

        # All elements from post_start to post_idx are in the left subtree
        left_subtree_size = post_idx - post_start + 1

        # Build left and right subtrees
        root.left = build_tree(
            pre_start + 1,  # Move pointer in preorder to the next element
            pre_start + left_subtree_size,  # End of left subtree in preorder
            post_start  # Start of left subtree in postorder
        )

        root.right = build_tree(
            pre_start + left_subtree_size + 1,  # Start of right subtree in preorder
            pre_end,  # End of right subtree in preorder
            post_idx + 1  # Start of right subtree in postorder
        )

        return root

    return build_tree(0, len(preorder) - 1, 0)

    @staticmethod
    def create_binary_tree_with_random(values: List[List[Optional[int]]]) -> Optional[TreeNodeWithRandom]:
        """
        Create a binary tree with random pointers from a list of node specifications.
        
        Args:
            values: List of [node_value, left_child_value, right_child_value, random_node_value]
            
        Returns:
            Root node of the constructed binary tree with random pointers.
        """
        if not values:
            return None

        node_map = {}
        root = None

        # First pass: create all nodes
        for value in values:
            node_val = value[0]
            node = TreeNodeWithRandom(node_val)
            node_map[node_val] = node
            if root is None:
                root = node

        # Second pass: set left, right, and random pointers
        for value in values:
            node_val = value[0]
            left_val = value[1]
            right_val = value[2]
            random_val = value[3] if len(value) > 3 else None

            node = node_map.get(node_val)
            if left_val is not None:
                node.left = node_map.get(left_val)
            if right_val is not None:
                node.right = node_map.get(right_val)
            if random_val is not None:
                node.random = node_map.get(random_val)

        return root

    @staticmethod
    def is_same_tree_with_random(p: Optional[TreeNodeWithRandom], q: Optional[TreeNodeWithRandom]) -> bool:
        """
        Check if two binary trees with random pointers are identical.
        
        Args:
            p: Root of the first tree.
            q: Root of the second tree.
            
        Returns:
            True if the trees are identical, False otherwise.
        """
        if not p and not q:
            return True
        if not p or not q:
            return False
        if p == q:  # Same node instance
            return True

        # Check current node values
        if p.val != q.val:
            return False

        # Check random pointers
        if (p.random is None) != (q.random is None):
            return False
        if p.random is not None and q.random is not None and p.random.val != q.random.val:
            return False

        # Recursively check left and right subtrees
        return (TreeBuilder.is_same_tree_with_random(p.left, q.left) and
                TreeBuilder.is_same_tree_with_random(p.right, q.right))

    @staticmethod
    def build_original_input_with_random(root: Optional[TreeNodeWithRandom]) -> List[List[Optional[int]]]:
        """
        Reconstruct the original input format from a binary tree with random pointers.
        
        Args:
            root: Root of the binary tree with random pointers.
            
        Returns:
            List of [node_value, left_child_value, right_child_value, random_node_value]
        """
        if not root:
            return []

        node_map = {}
        result = []

        # First pass: assign indices to nodes
        def index_nodes(node: Optional[TreeNodeWithRandom], index: Dict[TreeNodeWithRandom, int]) -> None:
            if not node or node in index:
                return

            index[node] = len(index)
            index_nodes(node.left, index)
            index_nodes(node.right, index)
            index_nodes(node.random, index)

        node_indices = {}
        index_nodes(root, node_indices)

        # Second pass: build the result list
        def build_result(node: Optional[TreeNodeWithRandom]) -> None:
            if not node:
                return

            value = [
                node.val,
                node.left.val if node.left else None,
                node.right.val if node.right else None,
                node.random.val if node.random else None
            ]
            result.append(value)

            build_result(node.left)
            build_result(node.right)

        build_result(root)
        return result


# Example usage
if __name__ == "__main__":
    # Binary Tree Example
    binary_elements = [1, 2, 3, 4, None, 5, 6]
    binary_root = TreeBuilder.build_tree_from_level_order(binary_elements)

    # N-ary Tree Example
    nary_elements = [1, None, 2, 3, 4, None, 5, 6, None, 7, 8, 9, None]
    nary_root = TreeBuilder.build_nary_tree_from_level_order(nary_elements)

    print("Binary tree and N-ary tree created successfully!")
