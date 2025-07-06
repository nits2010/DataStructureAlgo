"""
Author: Nitin Gupta
Date: 7/6/2025
Question Title: 3152. Special Array II
Link:  https://leetcode.com/problems/special-array-ii/description
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution:
    def isArraySpecial(self, nums: List[int], queries: List[List[int]]) -> List[bool]:
        """
        One way is to take queries range and perform the parity check just like 3151. Special Array I.
        However, it could possible that there will be many overlapping queries ranges.

        In order to find the parity of sub-array [start, end] which has length = end - start + 1
        we need to find the length of speicial array at index i. Means, we can maintain the length of longest special array seen so far till index i.

        For example:
        [4,3,1,6] then length special array at each index would be
        [1,1,1,2] since at index i=0 its length = 1
                  at index i = 2 its length = 1 as well since we have not seen so far any parity change
                  while at index i = 3 its length = 2 since just before 6, there is 1 with different parity

        similarly
        [1,2,3,4,5,6] => [1,2,3,4,5,6] since entire array is special array hence its Longest length is also same as array length.

        Now for every query, [start, end] we can simply check the length of longest special array at index end is equal to the length of query or not

        """

        # contains the length of longest special array at index [i]
        nums_length = len(nums)
        special_array = [1]

        # pre-process of special array length
        for i in range(1, nums_length):

            length = 1

            if nums[i - 1] % 2 != nums[i] % 2:
                length = special_array[i - 1] + 1

            special_array.append(length)

        # process query and build result
        result = []
        for query in queries:
            start = query[0]
            end = query[1]
            query_length = end - start + 1

            if query_length <= special_array[end]:
                result.append(True)
            else:
                result.append(False)

        return result


def test(input_data, queries, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().isArraySpecial(input_data, queries)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([3, 4, 1, 2, 6], [[0, 4]], [False]),
                   test([4, 3, 1, 6], [[0, 2], [2, 3]], [False, True]),
                   test([1, 8], [[1, 1]], [True]),
                   test([2, 7, 2], [[0, 0], [1, 2]], [True, True])]

    CommonMethods.print_all_test_results(tests)
