"""
Author: Nitin Gupta
Date: 7/7/2025
Question Title: 3437. Permutations III 🔒
Link: https://leetcode.com/problems/permutations-iii/description/
https://github.com/nits2010/leetcode-all-problem/blob/main/solution/3400-3499/3437.Permutations%20III/README_EN.md
Description: Given an integer n, an alternating permutation is a permutation of the first n positive integers such that no two adjacent elements are both odd or both even.

Return all such alternating permutations sorted in lexicographical order.



Example 1:

Input: n = 4

Output: [[1,2,3,4],[1,4,3,2],[2,1,4,3],[2,3,4,1],[3,2,1,4],[3,4,1,2],[4,1,2,3],[4,3,2,1]]

Example 2:

Input: n = 2

Output: [[1,2],[2,1]]

Example 3:

Input: n = 3

Output: [[1,2,3],[3,2,1]]



Constraints:

1 <= n <= 10

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
@Array
@Backtracking

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


class Solution_Recursive:
    def permute(self, n: int) -> List[List[int]]:
        """
            We design a function backtrack, which represents filling the i-th position, with position indices starting from 0.
            In backtracking, if i>=n , it means all positions have been filled, and we add the current permutation to the answer array.
            Otherwise, we enumerate the numbers 'num' that can be placed in the current position. If 'num' has not been used and
            has a different parity from the last number in the current permutation, we can place 'num' in the current position and continue to recursively fill the next position.

            O(n * n!)
            .
        :param n:
        :return: All possible permutations
        """

        all_permutations = []
        temp = []  # hold the current permutation
        visited = set()  # maintain a set to track visited elements

        def backtrack(start: int):
            if start == n:
                all_permutations.append(temp[:])
                return

            for num in range(1, n + 1):  # this will try all possible numbers and in lexicographical order
                if num not in visited:
                    # check does last element and current element has different parity
                    if start == 0 or temp[-1] % 2 != num % 2:
                        # track
                        temp.append(num)
                        visited.add(num)

                        # Repeated call
                        backtrack(start + 1)

                        # backtrack
                        temp.pop()
                        visited.remove(num)

        backtrack(0)
        return all_permutations


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_Recursive().permute(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(4, [[1, 2, 3, 4], [1, 4, 3, 2], [2, 1, 4, 3], [2, 3, 4, 1], [3, 2, 1, 4], [3, 4, 1, 2],
                            [4, 1, 2, 3], [4, 3, 2, 1]]),
                   test(2, [[1, 2], [2, 1]]),
                   test(3, [[1, 2, 3], [3, 2, 1]])]

    CommonMethods.print_all_test_results(tests)
