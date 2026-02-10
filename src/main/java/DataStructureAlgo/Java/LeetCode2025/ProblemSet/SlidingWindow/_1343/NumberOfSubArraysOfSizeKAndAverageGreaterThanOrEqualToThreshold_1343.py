

"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
Link: https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/description/
Description: Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and average greater than or equal to threshold.

 

Example 1:

Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
Output: 3
Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).
Example 2:

Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
Output: 6
Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
 

Constraints:

1 <= arr.length <= 105
1 <= arr[i] <= 104
1 <= k <= arr.length
0 <= threshold <= 104
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
@SlidingWindow

<p><p>
Company Tags
-----
@razorPay
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution_V2:
    def numOfSubarrays(self, arr: List[int], k: int, threshold: int) -> int:
        ws, we = 0, 0
        counter = 0
        w_sum = sum(arr[:k])

        if w_sum // k >= threshold:
            counter += 1

        for we in range(k, len(arr)):
            w_sum += arr[we]
            w_sum -= arr[we - k]
            if w_sum // k >= threshold:
                counter += 1

        return counter


class Solution_V1:
    def numOfSubarrays(self, arr: List[int], k: int, threshold: int) -> int:
        ws, we = 0, 0
        counter = 0
        w_sum = 0
        for we in range(len(arr)):
            w_sum += arr[we]

            if we - ws + 1 == k:
                if w_sum // k >= threshold:
                    counter += 1

                # sequeze
                w_sum -= arr[ws]
                ws += 1

        return counter


def test(input_data, k, threshold, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "k", "Threshold", "Expected"], True, input_data, k, threshold, expected)
    pass_test, final_pass = True, True
    output = Solution_V2().numOfSubarrays(input_data, k, threshold)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([2,2,2,2,5,5,5,8], 3,4, expected=3),
                   test([11,13,17,23,29,31,7,5,2,3], 3, 5, expected=6)]

    CommonMethods.print_all_test_results(tests)
