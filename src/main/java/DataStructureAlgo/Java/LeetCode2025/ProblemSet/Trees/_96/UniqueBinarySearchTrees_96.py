"""
Author: Nitin Gupta
Date: 6/28/2025
Question Title: 96. Unique Binary Search Trees
Link: https://leetcode.com/problems/unique-binary-search-trees/description
Description: Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: 5
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 19
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
 * @medium
 * @Math
 * @DynamicProgramming
 * @Tree
 * @BinarySearchTree
 * @BinaryTr
<p><p>
Company Tags
-----
@Microsoft
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


# Best explanation: https://leetcode.com/problems/unique-binary-search-trees/solutions/31666/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i
# G(n) = the number of unique BST for a sequence of length n.
# F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.
# When we choose 'i' as root, then we will have [1...i-1] as left childs while [i+1..n] as right childs which also makes a unique BST
# with 'i' as root, then we will have G(i-1) on left side while G(n-i) on right side

# G(n) = Sum(F(i,n)) where 1<=i<=n
# F(i,n) = G(i-1) * G(n-i)
# G(n) = F(1,n) + F(2,n).....+F(n,n)
# G(n) = G(0)*G(n-1) + G(1)*G(n-2)....
# G(n) = sum ( G(i-1) * G(n-i)) 1<=i<=n


class Solution:
    def numTrees(self, n: int) -> int:
        G = [0] * (n + 1)  # list of size n+1 with all 0
        G[0] = G[1] = 1

        for i in range(2, n + 1):
            unique_trees = 0
            for j in range(1, i + 1):
                unique_trees += G[j - 1] * G[i - j]
            G[i] = unique_trees

        return G[n]


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().numTrees(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(1, 1),
                   test(2, 2),
                   test(3, 5),
                   test(4, 14),
                   test(5, 42)]

    CommonMethods.print_all_test_results(tests)
