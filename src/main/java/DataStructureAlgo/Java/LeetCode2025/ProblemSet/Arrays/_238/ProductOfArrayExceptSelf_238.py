"""
Author: Nitin Gupta
Date: 19/03/2026
Question Title: 238. Product of Array Except Self
Link: https://leetcode.com/problems/product-of-array-except-self/description/
Description: Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

 

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 

Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

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
@Array
@PrefixSum

<p><p>
Company Tags
-----
@Amazon
@Facebook
@Microsoft
@Asana
@Apple
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

# Time/Space: O(n) / O(1)


class Solution:
    """
    We need to compute the product of each element at index i without it self. 
    This can be done by computing the full product and then for each index, divide the prodct to the number but it has probelm with float precision. 
    To avoid the division, we need recompute the product in such a way that we put entire array product at currect index. 


    "Prefix Sum/Product: O(n)/:(1)

        1. first compute the product of all the number from left to right skipping current. 
        prod=1
        ..... 
        from 0 -> n
        result[i] = prod
        prod = prod * nums[i]

        2. Now our result has product from left to right skipping first element. 
        result[i] = product of element [0...i-1] 
        Now we need to place the right half of the product at result[i], means [i+1...n] prod. 

        3. We do same as step 1; prod=1 from right side of the array 
        from n-1 to 0
        result[i] = result[i] * prod [ -> this leaves current number and have full product ] 
        prod = prod * nums[i] 


        Example : 
        [ 2 3 4 5] 
        Step 1: result = [1, 2, 6, 24] ; here 5 has product [2,3,4], 4 -> [2,3] 
        Step 2: result = [ 60,40,30,24]"



    """

    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        result = [0]*n
        prod = 1

        for i in range(n):
            result[i] = prod
            prod = nums[i] * prod

        prod = 1
        for i in range(n-1, -1, -1):
            result[i] = result[i] * prod
            prod = nums[i] * prod

        return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().productExceptSelf(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([2, 3, 4, 5],  [60,40,30,24])]

    CommonMethods.print_all_test_results(tests)
