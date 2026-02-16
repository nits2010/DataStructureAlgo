# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class LowestCommonAncestorOfABinarySearchTree_235:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        if not root:
            return None

        # apply bs property
        p_left_q_right = p.val <= root.val <= q.val
        p_right_q_left = q.val <= root.val <= p.val

        if p_left_q_right or p_right_q_left:
            return root

        # if root is not lca, than lca would be on left or right side
        # find either of p or q both will be on either lefr or right
        if p.val < root.val:
            return self.lowestCommonAncestor(root.left, p, q)
        else:
            return self.lowestCommonAncestor(root.right, p, q)