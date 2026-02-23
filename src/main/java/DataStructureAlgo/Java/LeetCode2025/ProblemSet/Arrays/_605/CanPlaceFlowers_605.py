"""
Author: Nitin Gupta
Date: 18/02/2026 
Question Title: 605. Can Place Flowers
Link: https://leetcode.com/problems/can-place-flowers/description/
Description: You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.

Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

 

Example 1:

Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:

Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 

Constraints:

1 <= flowerbed.length <= 2 * 104
flowerbed[i] is 0 or 1.
There are no two adjacent flowers in flowerbed.
0 <= n <= flowerbed.length
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@easy
@greedy
@array

<p><p>
Company Tags
-----
@agoda
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
    def canPlaceFlowers(self, flowerbed: List[int], k: int) -> bool:
        """
        Key-Observations
            1. We need to plan the flower in non-adj cell only.
                Derivation: Means, if we plan at 'i' index (assuming flowerbad[i] = 0) then 'i-1' can not have a flower and and i+1 has to skip.
            2. We just need to know that n flower is possible to plan
                Derivation:
                    1. To plan n flower, flowerbed should have at least n empty space and no adj flower.
                    2. This leads, if we start planting the flower at the very first available index then we need to keep skiping the next index. Since leaving this index will never increase our chance to plan more later. Greedy

            Outcome: Greedy
            1. Find a pos where flowerbed[i] = 0 and there is no adj flower means flowerbad[i-1] = 0 and flowerbad[i+1] = 0
            2. Plant the flower and reduce k, skip i -> i+2 otherwise i->i+1 
            3. Repeat

        """
        if k == 0:
            return True

        n = len(flowerbed)
        i = 0

        while i < n and k > 0:
            if (
                flowerbed[i] == 0
                and (i == 0 or flowerbed[i - 1] == 0)
                and (i == n - 1 or flowerbed[i + 1] == 0)
            ):
                k -= 1
                # flowerbed[i] = 1 # we don't need to really plan, as this index will never check again 
                i = i + 2 # i+1 is useless now
            else:
                i += 1

            if k == 0:
                return True

        return False

def test(input_data,k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "k", "Expected"], True, input_data,k,expected)
    pass_test, final_pass = True, True
    output = Solution().canPlaceFlowers(input_data, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1,0,0,0,1], 1, True),
                   test([1,0,0,0,1],2, False),
                   test([1,0,1,0,1,0,1],0, True),
                   test([0,1,0,1,0,1,0,0],1, True),
                   test([1,0,0,0,0,1],2, False)
                   ]

    CommonMethods.print_all_test_results(tests)
