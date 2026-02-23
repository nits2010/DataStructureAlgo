
"""
Author: Nitin Gupta
Date: 18/02/2026
Question Title: 124. Binary Tree Maximum Path Sum
Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
Description: A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000

File reference
-----------
Duplicate {@link}
Similar {@link PathSumII_437.py}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@DynamicProgramming
@Tree
@Depth-FirstSearch
@BinaryTree

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution_V2:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        
        max_path_sum = float('-inf')

        def dfs(root) -> int:
            nonlocal max_path_sum
            if not root:
                return 0
            
            left = dfs(root.left)
            right = dfs(root.right)

            left_max, right_max = max(0,left), max(0,right)
        
            max_path_sum = max(max_path_sum, left_max+right_max+root.val)

            return max(left_max, right_max) +  root.val
        dfs(root)
        return max_path_sum



class Solution_V1:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0
        
        max_path_sum = float('-inf')

        def dfs(root) -> int:
            nonlocal max_path_sum
            if not root:
                return 0

            if not root.left and not root.right : 
                max_path_sum = max(max_path_sum, root.val)
                return max(0,root.val) # negative value can not extend the path sum

            
            left = dfs(root.left)
            right = dfs(root.right)

            left, right = max(0,left), max(0,right) # negative value can not extend the path sum
           
            max_path_sum = max(max_path_sum, left+right+root.val)
            return max(left, right) + root.val
        dfs(root)
        return max_path_sum


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
