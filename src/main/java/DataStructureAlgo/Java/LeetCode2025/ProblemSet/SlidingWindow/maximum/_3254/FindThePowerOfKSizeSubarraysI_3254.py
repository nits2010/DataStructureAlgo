"""
Author: Nitin Gupta
Date: 7/6/2025
extension {@link SlidingWindowMaximum_239 }
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

            if i > 0 and nums[i - 1] + 1 == nums[i]:
                consecutive  += 1
            else:
                consecutive = 1

            # if all elements are consecutive then queue will have = k elements
            max = -1
            if consecutive  >= k:
                max = nums[i]  # this will be the last element in the order

            if i >= k - 1:
                result.append(max)

        return result


# using deque
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
            if i > 0 and nums[i - 1] + 1 != nums[i]:
                queue.clear()  # we can remove all element as none of them make any sense now

            # append in queue as maximum element
            queue.append(i)

            # if all elements are consecutive  then queue will have = k elements
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
