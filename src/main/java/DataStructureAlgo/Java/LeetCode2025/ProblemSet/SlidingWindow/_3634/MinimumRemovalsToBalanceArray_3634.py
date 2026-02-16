

"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 3634. Minimum Removals to Balance Array
Link: https://leetcode.com/problems/minimum-removals-to-balance-array/description/
Description:  You are given an integer array nums and an integer k.

An array is considered balanced if the value of its maximum element is at most k times the minimum element.

You may remove any number of elements from nums​​​​​​​ without making it empty.

Return the minimum number of elements to remove so that the remaining array is balanced.

Note: An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always holds true.

 

Example 1:

Input: nums = [2,1,5], k = 2

Output: 1

Explanation:

Remove nums[2] = 5 to get nums = [2, 1].
Now max = 2, min = 1 and max <= min * k as 2 <= 1 * 2. Thus, the answer is 1.
Example 2:

Input: nums = [1,6,2,9], k = 3

Output: 2

Explanation:

Remove nums[0] = 1 and nums[3] = 9 to get nums = [6, 2].
Now max = 6, min = 2 and max <= min * k as 6 <= 2 * 3. Thus, the answer is 2.
Example 3:

Input: nums = [4,6], k = 2

Output: 0

Explanation:

Since nums is already balanced as 6 <= 4 * 2, no elements need to be removed.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= 10^5
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
@Sliding Window
@Sorting

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
"""
Key observation :
    1. We need to keep track of min/max whenever any element is removed
         Derivation -> to get it efficiently, we need to sort the array
    2. We need to remove as minimum as possible, keeping max <= min * k
          Derivation ->
          1. Either we remove [0..n-2] element (nums[0] * k >= nums[-1]), these elements can be removed from either start or end. N-2 because to hold the conidition true, minimum 2 element should be there.
           2. We remvoe from start, keeping end as potential solution or from end for the same reason.
  3. Any no of elements who continuously satisfy the condition, will always be kept in the array
                Derviation ->
                    1. Means we need to find the longest sub-array that satisfy the condition -> Dynamic Sliding Window [0..[i,j]..n-1] subarray
Outcome :
        1. Sort array
        2. Keep a sliding window that satisfy the condition.
        3. For that window, calculate the removal points.
        4. Shrink the window and then again expand and maintain the maximum length of subarray that can be kept.
        5. Result would be n - max_length_kept


"""

class Solution_V2:
    def minRemoval(self, nums: List[int], k: int) -> int:

        n = len(nums)
        nums.sort()

        if n == 1 or (
            nums[0] * k >= nums[n - 1]
        ):  # single element or already satisfy the condition
            return 0

        length = 1  # minimum 1 element has to be removed
        right = 1
        for left in range(n):
            while right < n and nums[right] <= nums[left] * k:
                right += 1

            length = max(length, right - left)

        return n - length


class Solution_V1:
    def minRemoval(self, nums: List[int], k: int) -> int:
        n = len(nums)
        nums.sort()

        if n == 1 or (
            nums[0] * k >= nums[n - 1]
        ):  # single element or already satisfy the condition
            return 0

        left = right = 0
        min_removal = n

        while left < n - 1:

            # expand the window
            while right < n and nums[right] <= nums[left] * k:
                right += 1

            # window that satisify the condition max <= min * k
            max_kept = right - left
            max_removed = n - max_kept
            min_removal = min(min_removal, max_removed)

            # expand the window
            left += 1

        return min_removal



def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
