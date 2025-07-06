"""
Author: Nitin Gupta
Date: 7/6/2025
Question Title: 3255. Find the Power of K-Size Subarrays II
Link: https://leetcode.com/problems/find-the-power-of-k-size-subarrays-ii/description/
Description: You are given an array of integers nums of length n and a positive integer k.

The power of an array is defined as:

Its maximum element if all of its elements are consecutive and sorted in ascending order.
-1 otherwise.
You need to find the power of all subarrays of nums of size k.

Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].



Example 1:

Input: nums = [1,2,3,4,3,2,5], k = 3

Output: [3,4,-1,-1,-1]

Explanation:

There are 5 subarrays of nums of size 3:

[1, 2, 3] with the maximum element 3.
[2, 3, 4] with the maximum element 4.
[3, 4, 3] whose elements are not consecutive.
[4, 3, 2] whose elements are not sorted.
[3, 2, 5] whose elements are not consecutive.
Example 2:

Input: nums = [2,2,2,2,2], k = 4

Output: [-1,-1]

Example 3:

Input: nums = [3,2,3,2,3,2], k = 2

Output: [-1,3,-1,3,-1]



Constraints:

1 <= n == nums.length <= 105
1 <= nums[i] <= 106
1 <= k <= n
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link FindThePowerOfKSizeSubarraysI_3254}
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
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# replace dque with max
class Solution:
    def resultsArray(self, nums: List[int], k: int) -> List[int]:
        length = len(nums)
        result = []

        # maintians the consecutive number of elements
        consecutive = 1

        for i, num in enumerate(nums):

            if i > 0 and nums[i - 1] + 1 == num:
                consecutive += 1
            else:
                consecutive = 1

            # if all elements are consecutive then queue will have = k elements
            max = -1
            if consecutive >= k:
                max = num  # this will be the last element in the order

            if i >= k - 1:
                result.append(max)

        return result

class Solution_deque:
    def resultsArray(self, nums: List[int], k: int) -> List[int]:
        length = len(nums)
        result = []

        # contains the index of max element
        queue = deque()

        for i, num in enumerate(nums):

            # remove out of window element from front
            if queue and queue[0] <= i - k:
                queue.popleft()

            # update the max element, since if this is consecutive then next element will always be greater
            # are they consecutive
            if i > 0 and nums[i - 1] + 1 != num:
                queue.clear()  # we can remove all element as none of them make any sense now

            # append in queue as maximum element
            queue.append(i)

            # if all elements are consecutive then queue will have = k elements
            max = -1
            if len(queue) == k:
                max = nums[queue[-1]]

            if i >= k - 1:
                result.append(max)

        return result

def test(input_data, k , expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_deque().resultsArray(input_data, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["deque", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution().resultsArray(input_data, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Without deque", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1,2,3,4,3,2,5], 3, [3,4,-1,-1,-1]),
                   test([2,2,2,2,2], 4, [-1,-1]),
                   test([3,2,3,2,3,2], 2, [-1,3,-1,3,-1]),]

    CommonMethods.print_all_test_results(tests)
