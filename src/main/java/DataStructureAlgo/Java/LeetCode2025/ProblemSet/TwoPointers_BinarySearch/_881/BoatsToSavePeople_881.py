"""
Author: Nitin Gupta
Date: 08/02/2026
Question Title: 881. Boats to Save People
Link: https://leetcode.com/problems/boats-to-save-people/description/
Description: You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.

 

Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
 

Constraints:

1 <= people.length <= 5 * 104
1 <= people[i] <= limit <= 3 * 104
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
@TwoPointers
@Greedy
@Sorting

<p><p>
Company Tags
-----
@razorPay
@Amazon 
@FactSet 
@Google

<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution_TwoPointer:
    def numRescueBoats(self, people: List[int], limit: int) -> int:
        people.sort()  # to compute sum of weight easily

        # since we can have at most 2 people only in a boat, we can start putting people on boat based on their weights, Two pointer work grt. 
        # We could use Binary search in [1,len(people)] space but since we want to sit only 2 people then it will be 
        # complex and overkil. otheriwse BS would be a better choice if number of people limit isn't there. 

        left, right = 0, len(people) - 1
        total_boats_req = 0

        while left <= right:

            cur_weight = (
                people[left] + people[right]
            )  # let left and right people sit in this boat

            if cur_weight <= limit:
                left += 1

            right -= 1  # since people[right] > people[left], hence give it to right
            total_boats_req += 1  # regardless they can sit alone or together, we need one boat minimum for both case.

            # if cur_weight > limit:
            #     # means both can't sit, let them give individual boat, since people[right] > people[left], hence give it to right
            #     right -= 1
            # else:
            #     # means both can sit, now post sat, we need a another boat for next set of people
            #     left += 1
            #     right -= 1

            # total_boats_req += 1  # regardless they can sit alone or together, we need one boat minimum for both case.

        return total_boats_req


def test(input_data, limit, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "limit", "Expected"], True, input_data, limit, expected)
    pass_test, final_pass = True, True
    output = Solution_TwoPointer().numRescueBoats(input_data, limit)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1,2], 3, 1),
                   test([3,2,2,1], 3, 3),
                   test([3,5,3,4], 5, 4)
                   ]

    CommonMethods.print_all_test_results(tests)
