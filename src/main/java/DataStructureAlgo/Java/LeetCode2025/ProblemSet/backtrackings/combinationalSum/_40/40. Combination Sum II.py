"""
Author: Nitin Gupta
Date: 7/21/2025
Question Title: 40. Combination Sum II
Link: https://leetcode.com/problems/combination-sum-ii/description/
Description: Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.



Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]


Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
File reference
-----------
Duplicate {@link CombinationSumII}
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
@medium
@Facebook
@Amazon
@Reddit
@Bloomberg
@Apple
@Google
@LinkedIn
@Microsoft
@Nutanix
@Snapchat
@Uber
@WalmartLabs

<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods
class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        result = []
        candidates.sort()

        def backtrack(start: int, remaining: int, path: List[int]):
            if remaining == 0:
                result.append(path[:])
                return

            for i in range(start, len(candidates)):
                if i > start and candidates[i] == candidates[i - 1]:
                    continue  # Skip duplicates

                if candidates[i] > remaining:
                    break  # Prune the branch

                path.append(candidates[i])
                backtrack(i + 1, remaining - candidates[i], path)
                path.pop()

        backtrack(0, target, [])
        return result


def test(input_data, target,expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().combinationSum2(input_data, target)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([10,1,2,7,6,1,5],8, [[1,1,6],[1,2,5],[1,7],[2,6]]),
                   test([2,5,2,1,2],5, [[1,2,2],[5]])]

    CommonMethods.print_all_test_results(tests)
