
"""
Author: Nitin Gupta
Date: 17/02/2026
Question Title: 1448. Count Good Nodes in Binary Tree
Link: https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/    
Description: Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

 

Example 1:



Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
Example 2:



Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
Example 3:

Input: root = [1]
Output: 1
Explanation: Root is considered as good.
 

Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].

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
@Depth-FirstSearch
@Breadth-FirstSearch
@BinaryTree

<p><p>
Company Tags
-----
@Microsoft
@Salesforce
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

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
