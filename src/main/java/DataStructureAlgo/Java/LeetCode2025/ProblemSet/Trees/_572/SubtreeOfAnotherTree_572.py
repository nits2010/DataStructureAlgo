
"""
Author: Nitin Gupta
Date: 15/02/2026
Question Title: 572. Subtree of Another Tree
Link: https://leetcode.com/problems/subtree-of-another-tree/description/
Description: Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. The tree tree could also be considered as a subtree of itself.

 
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@easy
@Tree
@Depth-FirstSearch
@StringMatching
@BinaryTree
@HashFunction

<p><p>
Company Tags
-----
@Google
@Amazon
@Facebook
@Microsoft
@Bloomberg 
@eBay 
@Samsung

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

class Solution_Pattern:
    SEP = "$"
    END = "#@"   
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        if not subRoot:
            return True
        
        if not root:
            return False
        

        def serialize(root)->str:
            if not root:
                return self.END

            return self.SEP + str(root.val) + serialize(root.left) + serialize(root.right) 

        
        serialize_root = serialize(root)
        serialize_sub_root = serialize(subRoot)

        if serialize_sub_root in serialize_root:
            return True
        return False


class Solution_SameTree:   
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:

        if not subRoot:
            return True
        
        if not root:
            return False


        def is_same_tree(p,q):
            if not p and not q:
                return True

            if not p or not q:
                return False
            
            return p.val == q.val and is_same_tree(p.left, q.left) and is_same_tree(p.right, q.right)
        
        
        if is_same_tree(root, subRoot):
            return True
        
        return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)

        
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
