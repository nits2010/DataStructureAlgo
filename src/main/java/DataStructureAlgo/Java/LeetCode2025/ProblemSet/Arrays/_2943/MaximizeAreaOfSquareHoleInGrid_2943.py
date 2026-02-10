"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 2943. Maximize Area of Square Hole in Grid
Link: https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/description/
Description: You are given the two integers, n and m and two integer arrays, hBars and vBars. The grid has n + 2 horizontal and m + 2 vertical bars, creating 1 x 1 unit cells. The bars are indexed starting from 1.

You can remove some of the bars in hBars from horizontal bars and some of the bars in vBars from vertical bars. Note that other bars are fixed and cannot be removed.

Return an integer denoting the maximum area of a square-shaped hole in the grid, after removing some bars (possibly none).

 

Example 1:



Input: n = 2, m = 1, hBars = [2,3], vBars = [2]

Output: 4

Explanation:

The left image shows the initial grid formed by the bars. The horizontal bars are [1,2,3,4], and the vertical bars are [1,2,3].

One way to get the maximum square-shaped hole is by removing horizontal bar 2 and vertical bar 2.

Example 2:



Input: n = 1, m = 1, hBars = [2], vBars = [2]

Output: 4

Explanation:

To get the maximum square-shaped hole, we remove horizontal bar 2 and vertical bar 2.

Example 3:



Input: n = 2, m = 3, hBars = [2,3], vBars = [2,4]

Output: 4

Explanation:

One way to get the maximum square-shaped hole is by removing horizontal bar 3, and vertical bar 4.

 

Constraints:

1 <= n <= 109
1 <= m <= 109
1 <= hBars.length <= 100
2 <= hBars[i] <= n + 1
1 <= vBars.length <= 100
2 <= vBars[i] <= m + 1
All values in hBars are distinct.
All values in vBars are distinct.
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
@Sorting

<p><p>
Company Tags
-----
@Swiggy
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
    def maximizeSquareHoleArea(
        self, n: int, m: int, hBars: List[int], vBars: List[int]
    ) -> int:
        """
           Given a rectangle of size (n+2) * (m+2), will have by-default a 1x1 unit of cell.
           Each cell is seperted out by a bar either horizontally or vertically.
        Removal process
           If we remove 0 bar then cell unit gap will be 1 only [ as starting state]
           if we remove 1 bar then cell unit gap will increase by 1 since now we have 2 unit within the cell
           ...
           if we remove n bars then cell unit gap will be n+1 unit.

                Gap = k + 1 ; k denotes how many consecutive bar we removed.

            Please note, removing any random non-consecutive bar won't increase the gap as they are seperated out.
            Only consecutive bar removal works for increasing the gap, which is our goal to increas the gap as much as possible.


            Hence we need to find the max horizontal bar we remove consecutively and similarly apply for vertical bar.

            Now once we find that h and v bars, we either put a box within this gap would depends that it will be either
            rectable box or square box. For either of the case, we can't fit a box which is larger than of any of the side.
            Hence, we need to take min(hmax, vmax) to fit the box in it.

            space = min(hmax, vmax)
            area = space ^ 2

            we will sort both the bars to compute the hmax and vmax

        """

        hBars.sort()
        vBars.sort()

        def find_max_cons(bars):
            cons = 1
            max_cons = 1
            for i in range(1, len(bars)):

                if bars[i - 1] + 1 == bars[i]:
                    cons += 1
                else:
                    cons = 1

                max_cons = max(max_cons, cons)

            return max_cons

        hmax = find_max_cons(hBars)
        vmax = find_max_cons(vBars)
        gap = min(hmax, vmax) + 1
        return gap**2


def test(n, m, hBars, vBars, expected):
    """
    Test function to verify the solution
    """
    input_data = (n, m, hBars, vBars)
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().maximizeSquareHoleArea(n, m, hBars, vBars)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(n = 2, m = 1, hBars = [2,3], vBars = [2], expected = 4),
                   test(n = 1, m = 1, hBars = [2], vBars = [2], expected = 4),
                   test(n = 2, m = 3, hBars = [2,3], vBars = [2,4], expected = 4)]

    CommonMethods.print_all_test_results(tests)
