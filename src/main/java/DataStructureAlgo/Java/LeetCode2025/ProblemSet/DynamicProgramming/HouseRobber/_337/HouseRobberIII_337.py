# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
from typing import Optional


class Solution:
    """We have two choices at each node

    Case 1: Take this node -> rob_current
        -> When you take this node then you can't take children's but you can take grandchildren's
        -> rob_current = root.val [taking this node] + left_skip [ skipping left child ] + right_skip [ skipping right child ]

    Case 2: Don't Take this node -> skip_current
        -> When you don't take this node then you can take children's but you can't take grandchildren's.
        -> When you are taking childs, then each child has rob,skip. we need to choose best out of them. rob->robbing child, skip -> robbing grand child
        -> skip_current = max(left_rob, left_skip) [ best when you rob childs, or grandchild] + max(right_rob, right_skip) [ best when you rob childs, or grandchild]

    so if each node return this two information, we can drive the current_node possibilites;
    rob_current = root.val + left_skip  + right_skip
    skip_current =  max(left_rob, left_skip)  + max(right_rob, right_skip)

    Since before decideing for current node, we need to decided for child, we use post-order dfs. 
    
    
    O(N) where N is the number of nodes in the tree
    O(1) if implicit stack is allowed (not-counted) otherwise O(H) ; H = height of Tree - Worst O(N)

    """

    def rob_dfs(self, root: Optional[TreeNode]) -> (int, int):
        if not root:
            return (0, 0)

        left_rob, left_skip = self.rob_dfs(root.left)
        right_rob, right_skip = self.rob_dfs(root.right)

        # Case 1: Take this node -> rob_current
        rob_current = root.val + left_skip + right_skip
        skip_current = max(left_rob, left_skip) + max(right_rob, right_skip)

        return (rob_current, skip_current)

    def rob(self, root: Optional[TreeNode]) -> int:

        if not root:
            return 0

        rob_current, skip_current = self.rob_dfs(root)
        return max(rob_current, skip_current)
