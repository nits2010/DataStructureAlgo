# Definition for a binary tree node.
from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def longestUnivaluePath(self, root: Optional[TreeNode]) -> int:
        """
        # COAST FRAMEWORK
        #
        # 1. CLARIFY:
        #    - We need longest path of SAME values.
        #    - Path length = number of EDGES.
        #    - Path can turn (inverted V shape) through a node.
        #
        # 2. OUTLINE:
        #    - Post-order DFS (bottom-up).
        #    - At each node, ask children: "What's the longest univalue path ending at you?"
        #    - If child.val == node.val, extend that path by 1 (the edge to child). root -> left / root -> right
        #    - If not, the path stops (length 0).
        #    - Update global max with (left_path + right_path) -> The "Bridge".
        #    - Return max(left_path, right_path) -> The "Arrow" to parent.
        #
        # 3. APPROACH (Edge-Based DFS):
        #    - Direct edge counting avoids 'ans - 1' hacks.
        """
        
        _max_length = 0

        def dfs(root):
            nonlocal _max_length
            if not root:
                return 0

            longest_left_length = dfs(root.left)
            longest_right_length = dfs(root.right)

            left_path_length_at_root, right_path_length_at_root = 0, 0 # path stops, if root.val != child.val
            
            
            if root.left and root.val == root.left.val:
                left_path_length_at_root = longest_left_length + 1  # one edge connected, root -> left

            if root.right and root.val == root.right.val:
                right_path_length_at_root = longest_right_length + 1 # one edge connected, root -> right

            _max_length = max(
                _max_length, left_path_length_at_root + right_path_length_at_root
            )

            return max(left_path_length_at_root, right_path_length_at_root)

        dfs(root)
        return _max_length

# COMPLEXITY ANALYSIS:
# Time: O(N) - Every node visited once.
# Space: O(H) - Recursion stack height.