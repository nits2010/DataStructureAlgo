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

class Solution_Simplified:
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


class Solution_:
    def getPermutation(self, n: int, k: int) -> str:
        """
        We have 'n' different values to be picked and placed in 'n' different slots of permutations. Generating all permutations of 'n' length string would be very slow.
        Rather, we can try to find, which integer from 1..n would be placed in each slot. We have total 'n' slots only starting from 0..n-1 positions in the resultant string.

        We can try placing one number from [1..n] and see how many more permutations can be built upone if we choose one of them as current slot candidate.

        For example: n= 4, k = 9
        we have [1,2,3,4] total items to be placed in [0,1,2,3] slots.

        Choose 1 for 0th pos slot
        [1, (2,3,4)] keeping 1 at first slot, left us (2,3,4) which can be permutate 3!=6 ways which is < k. Update k = k - 6 = 3
        Since k > 0 hence 1 cannot be placed in the first slot.

        Choose 2 for 0th pos slot
        [2, (1,3,4)] keeping 2 at first slot, left us (1,3,4) which can be permutate 3!=6 which is > k, Hence, 2 will be at first slot for sure.

        fixing 2 now, we need to now find the candidate with second slot with k = 3
        [ 2, 1, (3,4)] makes 2!=2 < k, update k = k - 2 = 1, 1 can not be at second slot
        [2, 3, (1,4)] makes 2!=2 > k, hence 3 will be at second slot

        fixing 2 and 3,
        [2,3,1,(4)] OR [2,3,4,(1)] with k = 1 hence third would be 1

        output [2,3,1,4]


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

    output = Solution_().getPermutation(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    output = Solution_Simplified().getPermutation(n, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Simplified", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(3,3, "213"),
                   test(4,9, "2314"),
                   test(3,1, "123"),]

    CommonMethods.print_all_test_results(tests)
