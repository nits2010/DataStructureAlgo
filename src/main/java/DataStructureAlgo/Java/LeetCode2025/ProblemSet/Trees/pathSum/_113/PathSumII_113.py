from typing import List, Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        """
            This method uses backtracking to find all the paths that sum to the target sum.
            It uses a helper method to do the actual work.
            
        """
        output: List[List[int]] = []
        if not root:
            return output

        self.path_sum(root, targetSum, output, [])
        return output

    def path_sum(self, root: Optional[TreeNode], targetSum: int, output: List[List[int]],
                 current_path: List[int]) -> None:
        """
        The helper method is called path_sum, and it takes the root of the tree, the target sum, the output list, and the current path.
            current_path: The current path is a list of integers that represent the path from the root to the current node.
            output: The output list is a list of lists of integers that represent all the paths that sum to the target sum.
            targetSum: The target sum is the sum of the path from the root to the current node.
            root: The root of the tree is the starting point of the path.
        
        The helper method is called recursively to traverse the tree.
            If the root is None, return.
            Add the root value to the current path.
            If the root is a leaf node and the target sum is reached, add the current path to the output list.
            Otherwise, call the helper method recursively for the left and right children.
            Remove the root value from the current path.

        """
        if not root:
            return

        # add to the current path
        current_path.append(root.val)

        # if this is the leaf node and
        if not root.left and not root.right:
            # if the sum found
            if targetSum == root.val:
                output.append(current_path[:])  # currentPath[:] create shallow copy of currentPath alternative output.append(list(currentPath))

        else:
            self.path_sum(root.left, targetSum - root.val, output, current_path)
            self.path_sum(root.right, targetSum - root.val, output, current_path)

        # remove last node from the current path ; backtrack
        current_path.pop()
