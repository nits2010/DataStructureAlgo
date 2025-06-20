# Definition for a binary tree node.
from typing import List


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        result: List[int] = []
        if root is None:
            return result

        self._findDistance(root, target, k, result)
        return result

    def _findDistance(self, root: TreeNode, target: TreeNode, k: int, result: List[int]) -> int:

        # if we reach an invalid path
        if k < 0 or root is None:
            return -1  # an invalid distance

        # we reached a node where root = target
        if root == target:
            # explore its subtrees
            self._distanceKInSubtree(root, k, result)
            return 0

        # find the target
        left_distance = self._findDistance(root.left, target, k, result)

        # did we find target in left ?
        if left_distance != -1:

            # check does root is k-1 distance far away otherwise if so explore right
            if left_distance == k - 1:
                result.append(root.val)
            else:
                # explore right with remaining distance
                remaining_distance = (
                        k - left_distance - 2
                )  # k is overall distance, left_distance is the distance from root to left, -1 is for the root->left and -1 is for root->right
                self._distanceKInSubtree(root.right, remaining_distance, result)

            # return root distance to the target
            return left_distance + 1  # +1 for the root to left distance

        right_distance =  self._findDistance(root.right, target, k, result)

        # did we find target in right ?
        if right_distance != -1:

            # check does root is k-1 distnace far away otherwise if so explore right
            if right_distance == k - 1:
                result.append(root.val)
            else:
                # explore left with remaining distance
                remaining_distance = (
                        k - right_distance - 2
                )  # k is overall distance, left_distance is the distance from root to left, -1 is for the root->left and -1 is for root->right
                self._distanceKInSubtree(root.left, remaining_distance, result)

            # return root distance to the target
            return right_distance + 1  # +1 for the root to left distance

        # if target not found in subtree, hence it's not exists
        return -1

    def _distanceKInSubtree(self, root: TreeNode, k: int, result: List[int]):

        if k < 0 or root is None:
            return

        # if root itself is k distance away
        if k == 0:
            result.append(root.val)
            return

        # explore left and right subtree
        self._distanceKInSubtree(root.left, k - 1, result)
        self._distanceKInSubtree(root.right, k - 1, result)
