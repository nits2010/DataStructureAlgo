"""
Author: Nitin Gupta
Date: 6/30/2025
Question Title: 230. Kth Smallest Element in a BST
Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/description
Description: Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.



Example 1:


Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:


Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3


Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104


Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?


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
@BinarySearchTree
@BinaryTree


<p><p>
Company Tags
-----
@LinkedIn
@Uber
@Amazon
@Facebook
@Microsoft

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


class Solution:
    def __init__(self):
        self.result = None
        self.counter = None

    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        self.counter = 0
        self.result = None

        def kthSmallestNode(root):
            if not root:
                return

            kthSmallestNode(root.left)
            self.counter += 1

            if self.counter == k:
                self.result = root.val
                return

            kthSmallestNode(root.right)

        kthSmallestNode(root)
        return self.result


def test(input_data, k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().kthSmallest(TreeBuilder.build_tree_from_level_order(input_data), k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([3, 1, 4, None, 2], 1, 1),
                   test([5, 3, 6, 2, 4, None, None, 1], 3, 3),
                   test([2, 1, 3], 2, 2)]

    CommonMethods.print_all_test_results(tests)
