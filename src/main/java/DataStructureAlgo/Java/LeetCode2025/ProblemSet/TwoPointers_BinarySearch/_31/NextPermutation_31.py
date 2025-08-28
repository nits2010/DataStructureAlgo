"""
Author: Nitin Gupta
Date: 7/13/2025
Question Title:
Link:
Description:
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----

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
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Modifies nums in-place to the next lexicographical permutation.
        If not possible (i.e. already the last permutation), reverses to the first.
        :param nums:
        """

        n = len(nums)

        def reverse_from(start: int) -> None:
            nums[start:] = reversed(nums[start:])

        # Step 1: Find the first decreasing index from the end
        i = n - 2
        while i >= 0 and nums[i] >= nums[i + 1]:
            i -= 1

        if i >= 0:
            # Step 2: Find the element just larger than nums[i]
            j = n - 1
            while nums[j] <= nums[i]:
                j -= 1
            # Step 3: Swap them
            nums[i], nums[j] = nums[j], nums[i]

        # Step 4: Reverse the suffix
        reverse_from(i + 1)



def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = input_data.copy()
    Solution().nextPermutation(output)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":


    tests: List = [test([1, 2, 3], [1, 3, 2]),
                   test([3, 2, 1], [1, 2, 3]),
                   test([1, 1, 5], [1, 5, 1]),
                   test([4, 3, 2, 5, 3, 1], [4, 3, 3, 1, 2, 5]),
                   test([1, 5, 8, 4, 7, 6, 5, 3, 1], [1, 5, 8, 5, 1, 3, 4, 6, 7])]

    CommonMethods.print_all_test_results(tests)
