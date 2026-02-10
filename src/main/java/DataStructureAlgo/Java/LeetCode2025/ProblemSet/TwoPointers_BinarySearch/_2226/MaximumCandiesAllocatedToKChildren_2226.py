"""
Author: Nitin Gupta
Date: 07/02/2026
Question Title: 2226. Maximum Candies Allocated to K Children
Link: https://leetcode.com/problems/maximum-candies-allocated-to-k-children/description/
Description: You are given a 0-indexed integer array candies. Each element in the array denotes a pile of candies of size candies[i]. You can divide each pile into any number of sub piles, but you cannot merge two piles together.

You are also given an integer k. You should allocate piles of candies to k children such that each child gets the same number of candies. Each child can be allocated candies from only one pile of candies and some piles of candies may go unused.

Return the maximum number of candies each child can get.

 

Example 1:

Input: candies = [5,8,6], k = 3
Output: 5
Explanation: We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. It can be proven that each child cannot receive more than 5 candies.
Example 2:

Input: candies = [2,5], k = 11
Output: 0
Explanation: There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at least one candy. Thus, each child gets no candy and the answer is 0.
 

Constraints:

1 <= candies.length <= 105
1 <= candies[i] <= 107
1 <= k <= 1012
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
@BinarySearch

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

class Solution:
    def maximumCandies(self, candies: List[int], k: int) -> int:
        if sum(candies) < k:
            return 0

        def is_possible(pile_size):
            kids = 0
            for candy in candies:
                kids += candy // pile_size
                if kids >= k:
                    return True

            return False

        low = 1
        high = max(candies)
        result = 0
        while low <= high:
            mid = (low + high) // 2

            if is_possible(mid):
                result = mid
                low = mid + 1
            else:
                high = mid - 1

        return result


def test(input_data,k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "k", "Expected"], True, input_data, "k", expected)
    pass_test, final_pass = True, True
    output = Solution().maximumCandies(input_data,k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([5,8,6],3, 5),
                   test([2,5], 11, 0)]

    CommonMethods.print_all_test_results(tests)
