"""
Author: Nitin Gupta
Date: 6/23/2025
Question Title: 1302. Deepest Leaves Sum
Link: https://leetcode.com/problems/deepest-leaves-sum/description
Description: Given the root of a binary tree, return the sum of values of its deepest leaves.


Example 1:


Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
Example 2:

Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 19


Constraints:

The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Tree
@DepthFirstSearch
@BreadthFirstSearch
@BinaryTree <p><p>

<p><p>
Company Tags
-----
@Apple
@Google

<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""
from collections import deque
from typing import List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode


class Solution_BFS_V2:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        """
         Deepest node will always be present at the last level/height of the binary tree.
         So, we can compute the height of binary tree and then at that height, we can find all the leaves and compute the sum.

         when we run the level order traversal, the last iteration would hold the deepest nodes of binary tree. We can get that level and sum up the nodes value

        """

        if not root:
            return 0

        current_level = [root]

        while current_level:
            next_level = []

            for root in current_level:
                if root.left:
                    next_level.append(root.left)
                if root.right:
                    next_level.append(root.right)

            # if there is no next level available, then queue will hold the last level
            if not next_level:
                break

            # swap queue
            current_level = next_level

        sum = 0
        for q in current_level:
            sum += q.val

        return sum


# DFS
class Solution_DFS:
    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        """
            The Deepest node will always be present at the last level/height of the binary tree.
            So, we can compute the height of binary tree and then at that height, we can find all the leaves and compute the sum.

            Instead of doing it two phases (height of BT then sum of leaves at that height), we can compute the height and sum together.
            Whenever we see the height of the binary tree is increasing, we will reset the sum=0 and if we hit the same height with leave node, then we compute the sum

        """
        if not root:
            return 0

        self.sum = 0
        self.maxLevel = 1  # single root without childerens is at level 1

        def _deepestLeavesSum(root, level):

            if not root:
                return
            _deepestLeavesSum(root.left, level + 1)
            _deepestLeavesSum(root.right, level + 1)

            if self.maxLevel < level:
                self.maxLevel = level
                self.sum = 0

            if self.maxLevel == level:
                self.sum += root.val

        _deepestLeavesSum(root, 1)

        return self.sum


class Solution_BFS:
    def __init__(self):
        self.sum = None
        self.maxLevel = None

    def deepestLeavesSum(self, root: Optional[TreeNode]) -> int:
        """
            The deepest node will always be present at the last level/height of the binary tree.
            So, we can compute the height of binary tree and then at that height, we can find all the leaves and compute the sum.

            Instead of doing it two phases (height of BT then sum of leaves at that height), we can compute the height and sum together.
            Whenever we see the height of the binary tree is increasing, we will reset the sum=0 and if we hit the same height with leave node, then we compute the sum

        """

        self.sum = 0
        self.maxLevel = 1
        queue = deque([(root, 1)])  # root at level 1

        while queue:
            while range(len(queue)):
                root, level = queue.popleft()

                if self.maxLevel < level:
                    self.maxLevel = level
                    self.sum = 0

                # compute the sum of nodes at this level
                self.sum += root.val
                if root.left:
                    queue.append((root.left, level + 1))
                if root.right:
                    queue.append((root.right, level + 1))

        return self.sum


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    root: TreeNode = TreeBuilder.build_tree_from_level_order(input_data)

    output = Solution_DFS().deepestLeavesSum(root)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_BFS().deepestLeavesSum(root)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_BFS_V2().deepestLeavesSum(root)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS V2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, 2, 3, 4, 5, None, 6, 7, None, None, None, None, 8], 15),
                   test([6, 7, 8, 2, 7, 1, 3, 9, None, 1, 4, None, None, None, 5], 19)]

    CommonMethods.print_all_test_results(tests)
