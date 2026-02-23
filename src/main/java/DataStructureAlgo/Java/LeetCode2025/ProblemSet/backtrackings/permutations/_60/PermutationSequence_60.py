"""
Author: Nitin Gupta
Date: 7/14/2025
Question Title: 60. Permutation Sequence
Link: https://leetcode.com/problems/permutation-sequence/description/
Description: The set [1, 2, 3, ..., n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.



Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
Example 3:

Input: n = 3, k = 1
Output: "123"


Constraints:

1 <= n <= 9
1 <= k <= n!
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@Math
@Recursion

<p><p>
Company Tags
-----
@Facebook
@Adobe
@Amazon
@Google
@Microsoft
@Twitter
@Bloomberg
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from math import factorial
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution_V3:
    def getPermutation(self, n: int, k: int) -> str:
        """ """

        result = ""
        items = [i for i in range(1,n+1)]

        permutations = 0
        
        # do until we hit the pemutation range or we have more than 1 element to try
        while permutations < k and len(items) > 1:
            
            # if we take a element from our list, then we will left with n - 1 items in the list, hence we can arrange them fact(n-1) ways
            fact = factorial(len(items) - 1)
            
            #try each item which can be placed
            for item in items:
                if permutations + fact >= k:
                    result += str(item)
                    items.remove(item)  # item has been taken, remove it
                    break

                else:
                    permutations += fact

        for item in items:
            result += str(item)

        return result

class Solution_V2:
    def getPermutation(self, n: int, k: int) -> str:

        values = list(range(1, n + 1))  # list of values to be picked [1..n]
        result: str = ""  # resultant permutation sequence
        k = k-1 # Convert to 0-based indexing
        for slot_pos in range(n):

            # fixing 1 slot at slot_pos will left us total remaining slots = n - slot_pos - 1
            ways = factorial(n - slot_pos - 1)

            index = k // ways # directly find the index of value from values.
            val = values[index]
            result += str(val)
            values.pop(index)

            k = k % ways # remaining k

        return result


class Solution_V1:
    def getPermutation(self, n: int, k: int) -> str:
        """
        ⚙️ Problem Setup
            - We have n unique integers from 1 to n.
            - Our goal is to construct the k-th permutation (1-based index) of these numbers.
            - Instead of generating all permutations, we compute each value at position 0 to n-1 using factorial logic.

        💡 Strategy
            - For each slot from position 0 to n-1:
            - Determine how many permutations are possible if a specific integer is placed in that slot.
            - If factorial(n - current_position - 1) is less than k, subtract that count from k and try the next integer.
            - The first integer for which the remaining permutations are greater than or equal to k is the correct choice.

        📌 Example
            Given: n = 4, k = 9
            Available numbers: [1, 2, 3, 4]
            Slots: [0, 1, 2, 3]
            Step-by-step breakdown:
            - Position 0:
            - Try placing 1: remaining [2,3,4] → 3! = 6 permutations → k = 9 - 6 = 3 → skip 1
            - Try placing 2: remaining [1,3,4] → 3! = 6 ≥ k → select 2
            - Position 1 (fixed prefix: [2])
            - Try placing 1: remaining [3,4] → 2! = 2 → k = 3 - 2 = 1 → skip 1
            - Try placing 3: remaining [1,4] → 2! = 2 ≥ k → select 3
            - Position 2 (fixed prefix: [2,3])
            - Try placing 1: remaining [4] → 1! = 1 → k = 1 → select 1
            - Position 3: Remaining number → 4
            ✅ Final Output: [2, 3, 1, 4]

        """

        values = list(range(1, n + 1))  # list of values to be picked [1..n]
        result: str = ""  # resultant permutation sequence

        for slot_pos in range(n):

            # fixing 1 slot at slot_pos will left us total remaining slots = n - slot_pos - 1
            ways = factorial(n - slot_pos - 1)

            for i, val in enumerate(values):

                if k <= ways:
                    break

                k = k - ways

            result += str(val)
            values.pop(i)

        return result


def test(n, k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["N", "K", "Expected"], True, n, k, expected)
    pass_test, final_pass = True, True

    output = Solution_V1().getPermutation(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["V1", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    output = Solution_V2().getPermutation(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["V2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_V3().getPermutation(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["V3", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(3,3, "213"),
                   test(4,9, "2314"),
                   test(3,1, "123"),]

    CommonMethods.print_all_test_results(tests)
