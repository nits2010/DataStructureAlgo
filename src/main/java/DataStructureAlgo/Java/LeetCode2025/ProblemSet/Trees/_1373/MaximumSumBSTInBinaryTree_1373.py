"""
Author: Nitin Gupta
Date: 7/1/2025
Question Title: 1373. Maximum Sum BST in Binary Tree
 * Link: https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/description
 * Description: Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * Output: 20
 * Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 * Example 2:
 *
 *
 *
 * Input: root = [4,3,null,1,2]
 * Output: 2
 * Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 * Example 3:
 *
 * Input: root = [-4,-2,-5]
 * Output: 0
 * Explanation: All values are negatives. Return an empty BST.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 4 * 104].
 * -4 * 104 <= Node.val <= 4 * 104
File reference
-----------
Duplicate {@link DataStructureAlgo.Java.nonleetcode.Tree.LargestBSTInBinaryTree}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
 * @hard
 * @DynamicProgramming
 * @Tree
 * @Depth-FirstSearch
 * @BinarySearchTree
 * @BinaryTree

<p><p>
Company Tags
-----
@Amazon
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
        self.maximum_sum = 0  # ideally, we should set it to this float('-inf') but question has wrong test cases.

    def maxSumBST(self, root: Optional[TreeNode]) -> int:
        """
        * A binary tree is called a BST when each subtree follows bst rule in that tree.
            A common way to check if a given binary tree is bst or not by doing pre-order traversal and maintain the min,max range. As moving left, the min range keep updating while moving right, max range keep updating. And a root always follow the min/max range.
            If all the nodes follow the min,max range, then that subtree is called BST.
        * Maximum sum can be calculated by calculating a sum at each node that represents the BST. Means, we can start from post-order traversal, and keep sending the sum of left-right subtree to root and continue it.

        Since we need to take care of both, means a maximum sum and BST; we require following a common traversal, which can be done using post-order. As maximum sum needs to be calculated first for left and right subtree before we calcualte for this root.
        Henceforth, we can calculate the maximum sum as well.

        """
        # This will hold the maximum sum-found for a subtree rooted at root and also a BST.

        def maximumSumBST(root):
            """
            return (isBST, min, max, sum)
            """
            if not root:
                # this is a valid BST
                return (True, float("inf"), float("-inf"), 0)

            (left_is_bst, left_min, left_max, left_sum) = maximumSumBST(root.left)
            (right_is_bst, right_min, right_max, right_sum) = maximumSumBST(root.right)

            # check does this root follow BST rule
            if left_is_bst and right_is_bst and left_max < root.val < right_min:
                # rooted sub-tree is BST
                current_sum = root.val + left_sum + right_sum
                current_min = min(left_min, root.val)
                current_max = max(right_max, root.val)

                self.maximum_sum = max(self.maximum_sum, current_sum)

                return (True, current_min, current_max, current_sum)
            else:
                # this rooted sub-tree is not BST
                return (False, float("inf"), float("-inf"), 0)

        maximumSumBST(root)
        return self.maximum_sum


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().maxSumBST(TreeBuilder.build_tree_from_level_order(input_data))

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1,4,3,2,4,2,5,None,None,None,None,None,None,4,6], 20),
                   test([4,3,None,1,2], 2),
                   test([5, 4, 8, 11, None, 13, 4, 7, 2, None, None, 5, 1], 13),
                   test([-4,-2,-5], 0)] #All values are negatives. Return an empty BST.

    CommonMethods.print_all_test_results(tests)
