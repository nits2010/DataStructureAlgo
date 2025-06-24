"""
Author: Nitin Gupta
Date: 6/24/2025
Question Title: 173. Binary Search Tree Iterator
Link: https://leetcode.com/problems/binary-search-tree-iterator/description/
Description: Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.



Example 1:


Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False


Constraints:

The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 106
At most 105 calls will be made to hasNext, and next.


Follow up:

Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?

File reference
-----------
Duplicate {@link BSTIterators.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Stack
@Tree
@Design
@BinarySearchTree
@BinaryTree
@Iterator

<p><p>
Company Tags
-----
@Alibaba
@Amazon
@Apple
@Bloomberg
@Cisco
@eBay
@Facebook
@Google
@LinkedIn
@Microsoft
@Oracle
@Qualtrics
@Splunk
@Uber
@Salesforce
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from DataStructureAlgo.python.helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode

#
# /**
# * Returns the next integer a in the post-order traversal of the given binary tree.
# * * For example, given a binary tree below,
# * *       4
# * *      / \
#         2   6
# * *    / \ / \
#        1  3 5  7
# * * the outputs will be 1, 3, 2, 5, 7, 6, 4.
# */
class BSTIterator_PostOrder:

    def __init__(self, root: Optional[TreeNode]):
        self.stack: List[TreeNode] = []
        self.root = root
        self._cache_nodes(root)


    def _cache_nodes(self, root: Optional[TreeNode]):
        if not root:
            return

        #keep caching the root, till we get a leaf
        while root:
            self.stack.append(root)

            #we want to print left first, hence we process left
            if root.left:
                root = root.left
            elif root.right:
                root = root.right
            else:
                break


    def next(self) -> int:
        if not self.hasNext():
            raise StopIteration

        top = self.stack.pop()

        # if this 'top' is a left child of stack.peek(), then it means that we need to push right children of stack.peek() root since we need to process right first then root of subtree.
        if self.stack and top == self.stack[-1].left:
            self._cache_nodes(self.stack[-1].right)

        return top.val

    def hasNext(self) -> bool:
        if not self.stack:
            return False
        return True


class BSTIterator_PreOrder:

    def __init__(self, root: Optional[TreeNode]):
        self.stack: List[TreeNode] = []
        self.root = root
        if root:
          self.stack.append(root)



    def next(self) -> int:
        if not self.hasNext():
            raise StopIteration

        top = self.stack.pop()

        #cache right and left, we need to get left first
        if top.right:
            self.stack.append(top.right)
        if top.left:
            self.stack.append(top.left)

        return top.val

    def hasNext(self) -> bool:
        if not self.stack:
            return False
        return True


class BSTIterator_Inorder:

    def __init__(self, root: Optional[TreeNode]):
        self.stack: List[TreeNode] = []
        self.root = root
        self._cache_nodes(root)

    def _cache_nodes(self, root: Optional[TreeNode]):
        if not root:
            return

            # push all the left nodes in the stack
        while root:
            self.stack.append(root)
            root = root.left

    def next(self) -> int:
        if not self.hasNext():
            raise StopIteration

        top = self.stack.pop()

        self._cache_nodes(top.right)

        return top.val

    def hasNext(self) -> bool:
        if not self.stack:
            return False
        return True

def preOrder(root: Optional[TreeNode]) -> List[int]:
    if not root:
        return []
    return [root.val] + preOrder(root.left) + preOrder(root.right)

def inOrder(root: Optional[TreeNode]) -> List[int]:
    if not root:
        return []
    return inOrder(root.left) + [root.val] + inOrder(root.right)

def postOrder(root: Optional[TreeNode]) -> List[int]:
    if not root:
        return []
    return postOrder(root.left) + postOrder(root.right) + [root.val]

def test(input_data, preOrder, inOrder, postOrder):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected-preOrder", "Expected-inorder", "Expected-postorder"], True, input_data, preOrder, inOrder, postOrder)
    pass_test, final_pass = True, True

    root: TreeNode = TreeBuilder.build_tree_from_level_order(input_data)

    #test preOrder
    obj = BSTIterator_PreOrder(root)
    output = []
    while obj.hasNext():
        output.append(obj.next())
    pass_test = CommonMethods.compare_result(output, preOrder, True)
    CommonMethods.print_test(["preOrder", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    #test inOrder
    obj = BSTIterator_Inorder(root)
    output = []
    while obj.hasNext():
        output.append(obj.next())

    pass_test = CommonMethods.compare_result(output, inOrder, True)
    CommonMethods.print_test(["inOrder", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    #test postOrder
    obj = BSTIterator_PostOrder(root)
    output = []
    while obj.hasNext():
        output.append(obj.next())

    pass_test = CommonMethods.compare_result(output, postOrder, True)
    CommonMethods.print_test(["postOrder", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass

if __name__ == "__main__":
    input1 = [7, 3, 15, None, None, 9, 20]
    tests: List = [test(input1, preOrder(TreeBuilder.build_tree_from_level_order(input1)), inOrder(TreeBuilder.build_tree_from_level_order(input1)), postOrder(TreeBuilder.build_tree_from_level_order(input1)))]

    CommonMethods.print_all_test_results(tests)
