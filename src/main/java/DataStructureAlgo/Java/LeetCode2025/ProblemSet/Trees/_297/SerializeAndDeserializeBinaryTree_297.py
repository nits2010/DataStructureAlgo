"""
Author: Nitin Gupta
Date: 6/28/2025
Question Title: 297. Serialize and Deserialize Binary Tree
Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description
Description: Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []


Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000

File reference
-----------
Duplicate {@link SerializeDeSerializeBinaryTree.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@String
@Tree
@DepthFirstSearch
@BreadthFirstSearch
@Design
@BinaryTree

<p><p>
Company Tags
-----
@Uber
@Amazon
@Microsoft
@LinkedIn
@DoorDash

<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.TreeTraversals import TreeTraversals
from helpers.common_methods import CommonMethods
from helpers.templates.TreeNode import TreeNode


class Codec_UsingIterator:
    NULL_PTR = "#"
    SEPARATOR = ","

    def serialize(self, root) -> str:
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """

        def dfs(root) -> []:
            if not root:
                return [Codec_UsingIterator.NULL_PTR]  # mark for NULL

            return [str(root.val)] + dfs(root.left) + dfs(root.right)

        return Codec_UsingIterator.SEPARATOR.join(dfs(root))

    def deserialize(self, data) -> TreeNode | None:
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """

        values: iter = iter(data.split(Codec_UsingIterator.SEPARATOR))

        def dfs() -> TreeNode | None:
            value = next(values)
            if value == Codec_UsingIterator.NULL_PTR:
                return None

            root: TreeNode = TreeNode(int(value))
            root.left = dfs()
            root.right = dfs()

            return root

        return dfs()


class Codec_UsingIndex:
    null_ptr = "#"
    seperator = ","

    def serialize(self, root):
        """Encodes a tree to a single string.

        :type root: TreeNode
        :rtype: str
        """
        serialized_str = []

        def serialize_tree(root):
            if not root:
                serialized_str.append(Codec_UsingIndex.null_ptr)
                return

            serialized_str.append(str(root.val))
            serialize_tree(root.left)
            serialize_tree(root.right)

        serialize_tree(root)
        serialized = Codec_UsingIndex.seperator.join(serialized_str)  # conver list to comma seprated values in string
        return serialized

    def deserialize(self, data):
        """Decodes your encoded data to tree.

        :type data: str
        :rtype: TreeNode
        """

        if not data:
            return

        datas = data.split(Codec_UsingIndex.seperator)
        # print(f"datas -> {datas}")
        self.index = 0

        def deserialize_tree() -> TreeNode:
            val = datas[self.index]
            self.index += 1

            if val == Codec_UsingIndex.null_ptr:
                return None

            root: TreeNode = TreeNode(int(val))
            root.left = deserialize_tree()
            root.right = deserialize_tree()

            return root

        return deserialize_tree()


# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))

def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    root: TreeNode = TreeBuilder.build_tree_from_level_order(input_data)
    codec = Codec_UsingIndex()
    serialized = codec.serialize(root)
    deserialized = codec.deserialize(serialized)
    output = TreeTraversals.level_order_with_null(deserialized)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1, 2, 3, None, None, 4, 5], [1, 2, 3, None, None, 4, 5]),
                   test([], [])]

    CommonMethods.print_all_test_results(tests)
