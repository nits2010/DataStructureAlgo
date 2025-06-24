"""
Author: Nitin Gupta
Date: 6/24/2025
Question Title: 1586. Binary Search Tree Iterator II
Link: https://leetcode.com/problems/binary-search-tree-iterator-ii/description/ & https://leetcode.ca/all/1586.html
Description: Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer, otherwise returns false.
int prev() Moves the pointer to the left, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() and prev() calls will always be valid. That is, there will be at least a next/previous number in the in-order traversal when next()/prev() is called.

Follow up: Could you solve the problem without precalculating the values of the tree?



Example 1:



Input
["BSTIterator", "next", "next", "prev", "next", "hasNext", "next", "next", "next", "hasNext", "hasPrev", "prev", "prev"]
[[[7, 3, 15, null, null, 9, 20]], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null], [null]]
Output
[null, 3, 7, 3, 7, true, 9, 15, 20, false, true, 15, 9]

Explanation
// The underlined element is where the pointer currently is.
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]); // state is   [3, 7, 9, 15, 20]
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 3
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 3
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 7
bSTIterator.hasNext(); // return true
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 9
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 15
bSTIterator.next(); // state becomes [3, 7, 9, 15, 20], return 20
bSTIterator.hasNext(); // return false
bSTIterator.hasPrev(); // return true
bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 15
bSTIterator.prev(); // state becomes [3, 7, 9, 15, 20], return 9


Constraints:

The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 106
At most 105 calls will be made to hasNext, next, hasPrev, and prev.
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
@Stack
@Tree
@Design
@BinarySearchTree
@BinaryTree
@Iterator
@LeetCodeLockedProblem
@PremimumQuestion
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

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode


class BSTIterator:
    def __init__(self, root: Optional[TreeNode]):
        self.next_stack: List[TreeNode] = []
        self.prev_stack: List[TreeNode] = []
        self.root = root
        self.index = 0  # this will denote the pointer to the current node
        self.init(root)

    def init(self, root: Optional[TreeNode]):
        while root:
            self.next_stack.append(root)
            root = root.left

    def next(self) -> int:
        # return the node as per the pointer, if a pointer has moved backward, then we need to return the previous stack
        if self.index + 1 < len(self.prev_stack):
            self.index += 1  # move pointer to the next node
            return self.prev_stack[self.index].val

        # prev_stack does not have enough elements, get from next_stack
        if not self.next_stack:
            raise StopIteration

        # pop from next stack
        top = self.next_stack.pop()

        # add it to prev stack
        self.prev_stack.append(top)
        self.index = len(self.prev_stack) - 1

        if top.right:
            self.init(top.right)

        return top.val

    def hasNext(self) -> bool:
        ## Has next depended on the pointer

        # if there is an element in prev stack, check the index, since the pointer can be moved backward
        if self.index + 1 < len(self.prev_stack):
            return True

        # if there is an element in next stack, return true
        if self.next_stack:
            return True

        # if both next and prev stack is empty, return false
        return False

    def hasPrev(self) -> bool:
        if self.index - 1 >= 0:
            return True
        return False

    def prev(self) -> int:
        if not self.hasPrev():
            raise StopIteration
        self.index -= 1
        return self.prev_stack[self.index].val


def test(operation, input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["operation", "Input", "Expected"], True, operation, input_data, expected)
    pass_test, final_pass = True, True

    input_list = input_data[0]
    root = TreeBuilder.build_tree_from_level_order(input_list)
    obj = BSTIterator(root)
    output = []

    for operation in operation:
        if operation == "next":
            output.append(obj.next())
        elif operation == "hasNext":
            output.append(obj.hasNext())
        elif operation == "prev":
            output.append(obj.prev())
        elif operation == "hasPrev":
            output.append(obj.hasPrev())

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(
        ["BSTIterator", "next", "next", "prev", "next", "hasNext", "next", "next", "next", "hasNext", "hasPrev", "prev",
         "prev"],
        [[7, 3, 15, None, None, 9, 20], None, None, None, None, None, None, None, None, None, None, None, None],
        [3, 7, 3, 7, True, 9, 15, 20, False, True, 15, 9])]

    CommonMethods.print_all_test_results(tests)
