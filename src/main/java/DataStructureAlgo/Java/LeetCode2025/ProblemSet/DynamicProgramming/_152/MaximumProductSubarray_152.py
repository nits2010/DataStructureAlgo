"""
Author: Nitin Gupta
Date: 19/03/2026
Question Title: 152. Maximum Product Subarray
Link: https://leetcode.com/problems/maximum-product-subarray/description/
Description: Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Note that the product of an array with a single element is the value of that element.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 

Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any subarray of nums is guaranteed to fit in a 32-bit integer.

File reference
-----------
Duplicate {@link MaxProductSubArray.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@DynamicProgramming

<p><p>
Company Tags
-----
@LinkedIn
@Amazon
@Microsoft
@Bloomberg
@Infosys
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import defaultdict, deque
import heapq
from typing import List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods


class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        """
            This seems sliding window but it breaks the core logic of sliding window
            The monotonic increase decrease pattern.  
            👉 Sliding window works when expanding/shrinking gives predictable behavior.
            Here, multiplying by a negative can flip max ↔ min unpredictably.

            The reason is, we are multiplying the number which will surely increase when we keep multiplying the
            positive product to a positive number or negative product to a negative number but otherwise it decrease it. 

            Now, since multiplying two negative would produce positive, hence we can't simply drop it, we need to track it along with positive product. 
            And once we have both the product, we can take the maximum out of it. 

           [ 2 3 -2 -5 4]
           [2 3 ] max = 6
           now [-2] if you multiply then max=-12 which actually become small then previous max. So for maximizing purpose we won't take this element
           however we can't leave as its is possible that upcoming element is -ve as well which makes it bigger. 
           Hence we track another multiplication called 'min'.

            👉 A previous minimum (negative) can become the maximum after multiplying by a negative — so we must track both.
            DP-> 👉 At each index, the best product ending here depends only on the previous max and min, because multiplication carries forward state.

            Since we need only last element, we can convert the array to variable instead.

            max -> maximum product till i index
            min -> most negative product till i index

            While computing max, we need
            max -> max so far
            num * max -> including current number 
            num * min -> max product when multiply neg number\

            max = max(num, max*num)
            min = min(num, min*num)

            max_prod = max(max_prod, max)

            👉 When num < 0 swap max and min as they will convert to opoositive post mul by num
            👉 When num == 0, both _max and _min collapse to 0 anyway — so swapping has no effect and is logically unnecessary.


            Time / Space: O(n)/O(1)
        """

        if not nums:
            return 0

        _max_prod = _min = _max = nums[0]
        for num in nums[1:]:
            if num < 0:
                _min, _max = _max, _min

            _max = max(num, _max * num)
            _min = min(num, _min * num)
            _max_prod = max(_max_prod, _max)

        return _max_prod


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().maxProduct(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([2, 3, -2, 4], 6),
                   test([-2, 0, -1], 0),
                   test([-2], -2),
                   test([0], 0)]

    CommonMethods.print_all_test_results(tests)
