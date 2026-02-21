"""
Author: Nitin Gupta
Date: 7/21/2025
Question Title: 216. Combination Sum III
Link: https://leetcode.com/problems/combination-sum-iii/description/
Description: Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.



Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.


Constraints:

2 <= k <= 9
1 <= n <= 60
File reference
-----------
Duplicate {@link CombinationSumIII}
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
@Google
@Amazon
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

class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:
     
        # You can't make sum n=2 when k=3. to run at least n>=k
        # and using [1,9] you can't make sum more than 45.
        # K can not be more than 9 since we have 9 choices only
       
        if (n < k or n > 45 or k > 9):
          return [];
      
        result = []
        candidates = [i for i in range(1, 10)]
        comb: List[int] = []

        def dfs(start: int, remaining: int, k: int):
            if len(comb) == k:
                if remaining == 0:
                    result.append(comb[:])
                return

            for i in range(start, len(candidates)):
                if candidates[i] > remaining:
                    break  # Prune the branch

                comb.append(candidates[i])
                dfs(i + 1, remaining - candidates[i], k)
                comb.pop()

        dfs(0, n, k)
        return result



def test(k, n, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["K", "N", "Expected"], True, k, n, expected)
    pass_test, final_pass = True, True
    output = Solution().combinationSum3(k, n)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(k=3, n=7, expected=[[1, 2, 4]]),
                   test(k=3, n=9, expected=[[1, 2, 6], [1, 3, 5], [2, 3, 4]]),
                   test(k=4, n=1, expected=[]),
                   test(k=1, n=1, expected=[[1]]),
                   test(k=9, n=45, expected=[[1, 2, 3, 4, 5, 6, 7, 8, 9]])
                   ]

    CommonMethods.print_all_test_results(tests)
