"""
Author: Nitin Gupta
Date: 7/13/2025
Question Title:  3470. Permutations IV
Link: https://leetcode.com/problems/permutations-iv/description/
Description: Given two integers, n and k, an alternating permutation is a permutation of the first n positive integers such that no two adjacent elements are both odd or both even.

Return the k-th alternating permutation sorted in lexicographical order. If there are fewer than k valid alternating permutations, return an empty list.



Example 1:

Input: n = 4, k = 6

Output: [3,4,1,2]

Explanation:

The lexicographically-sorted alternating permutations of [1, 2, 3, 4] are:

[1, 2, 3, 4]
[1, 4, 3, 2]
[2, 1, 4, 3]
[2, 3, 4, 1]
[3, 2, 1, 4]
[3, 4, 1, 2] ← 6th permutation
[4, 1, 2, 3]
[4, 3, 2, 1]
Since k = 6, we return [3, 4, 1, 2].

Example 2:

Input: n = 3, k = 2

Output: [3,2,1]

Explanation:

The lexicographically-sorted alternating permutations of [1, 2, 3] are:

[1, 2, 3]
[3, 2, 1] ← 2nd permutation
Since k = 2, we return [3, 2, 1].

Example 3:

Input: n = 2, k = 3

Output: []

Explanation:

The lexicographically-sorted alternating permutations of [1, 2] are:

[1, 2]
[2, 1]
There are only 2 alternating permutations, but k = 3, which is out of range. Thus, we return an empty list [].



Constraints:

1 <= n <= 100
1 <= k <= 10^15
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
@Array
@Math
@Combinatorics
@Enumeration

<p><p>
Company Tags
-----
<p>
-----

@Editorial https://leetcode.com/problems/permutations-iv/solutions/6483324/python-value-by-value/ <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from math import factorial
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution:
    def permute(self, n: int, k: int) -> List[int]:
        """
            ### 🔢 Key Observations

            1. We have exactly `n` slots to fill with values from `1` to `n`.
            2. **Parity constraints**:

            * If `n` is **odd**, the first number **must be odd**, since there will be one more odd number than even.
            * If `n` is **even**, the first number can be either **odd or even**, and the rest of the permutation must follow alternating parity.

            ### ⚙️ Optimization Insight

            Instead of generating all alternating permutations and sorting them to find the `k-th`, which is infeasible for large `n`, we build the permutation **one position at a time**, computing how many valid permutations exist for each choice,
            and **skip over blocks** of permutations accordingly.

            This is achieved using **counting logic**, by computing:

            ```
            ways = factorial(even_remaining) * factorial(odd_remaining)
            ```

            ...which gives the number of valid alternating permutations from the current point onward.

            ### 📚 Example Walkthrough

            Let’s say `n = 4` and `k = 6`. Our available numbers are `[1, 2, 3, 4]`.

            * Try placing `1` at the first position:

            * Remaining numbers: `[2, 3, 4]`
            * Valid permutations starting with `[1]`: 2 (`[1,2,3,4]`, `[1,4,3,2]`)
            * Since `k = 6 > 2`, skip these → `k = 6 - 2 = 4`

            * Try placing `2` at the first position:

            * Remaining numbers: `[1, 3, 4]`
            * Valid permutations: 2 (`[2,1,4,3]`, `[2,3,4,1]`)
            * `k = 4 > 2` → skip again → `k = 4 - 2 = 2`

            * Try placing `3` at the first position:

            * Remaining numbers: `[1, 2, 4]`
            * Valid permutations: 2 (`[3,2,1,4]`, `[3,4,1,2]`)
            * `k = 2 <= 2` → Accept `3` as the first number

            Repeat this logic recursively for the next positions using the updated value of `k` and updated remaining numbers.

            ### ✅ Parity Handling

            At each step, enforce the alternating parity by:

            * Checking if the number you're placing matches the expected parity
            * Updating `parity_needed` based on the last placed number:

            ```
            parity_needed = 1 - (last_number % 2)
            ```

            The only exception is at the first position **when `n` is even**, where both odd and even numbers are allowed.
            :return:
            :param n:
            :param k:
            :return: kth lexicographically sorted permutation or []

        """

        values = list(range(1, n + 1))  # list of available numbers from 1 to N
        parity_needed = 1
        result: List[int] = []

        # Now, we will start the slots = 0..n-1 (to follow 0th index)
        for slot_pos in range(n):

            # now at this slow, how many permutations are possible.
            even_count = (n - slot_pos) // 2
            odd_count = (n - slot_pos - 1) // 2

            # at slow_pos we have 'ways' way to fill it.
            ways = factorial(odd_count) * factorial(even_count)

            # iterate over remaining values that can be placed
            for i, vals in enumerate(values):

                # check can we place vals at this slot, its only when parity needed matches,
                # however, if N is even and this is first slow, then we can place any number here.
                if vals % 2 != parity_needed and (slot_pos != 0 or n % 2 != 0):
                    continue

                # means we can place vals at the slot_pos
                if k <= ways:
                    # means putting vals at slot_pos generates more than enough ways
                    break

                k = k - ways

            else:
                # for loop did not `break`, meaning no valid candidate was found
                return []

            # as we fixed the position of vals, add it to the result, remove it from available values
            result.append(values.pop(i))

            # calculate the next slot parity
            parity_needed = 1 - result[-1] % 2

        return result


def test(n, k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["N", "K", "Expected"], True, n, k, expected)
    pass_test, final_pass = True, True
    output = Solution().permute(n, k)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(4, 6, [3, 4, 1, 2]),
                   test(3, 2, [3, 2, 1]),
                   test(2, 3, [])]

    CommonMethods.print_all_test_results(tests)
