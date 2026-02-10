"""
Author: Nitin Gupta
Date: 07/02/2026
Question Title: 2594. Minimum Time to Repair Cars
Link: https://leetcode.com/problems/minimum-time-to-repair-cars/description/
Description: You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.

You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.

Return the minimum time taken to repair all the cars.

Note: All the mechanics can repair the cars simultaneously.

 

Example 1:

Input: ranks = [4,2,3,1], cars = 10
Output: 16
Explanation: 
- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
Example 2:

Input: ranks = [5,1,8], cars = 6
Output: 16
Explanation: 
- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
 

Constraints:

1 <= ranks.length <= 105
1 <= ranks[i] <= 100
1 <= cars <= 106
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
@BinarySearch


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
import math
from collections import Counter

from helpers.common_methods import CommonMethods

class Solution_UsingWorkers:
    def repairCars(self, ranks: List[int], cars: int) -> int:
        """
        time taken by ith mechanic to repair n car with r rank = r * n^2

        * we can have multiple mechnaic to repair car simultaneously
        * Minimize the time taken over all.

        * if we give car to the mechnaic whose rank is lowest, then it will take least of the time as r is lesser, where as if we give to highest rank then it will take most time.
        * Mechnaic rank now define the search space [ 1, max(rank)*n]
        * Since we need to minimize the time, we give work to all the mechnaic at same time and ask to finish the work by in that time, if they can finish than , we will take it as possible answer and try reducing further.

        [4,2,3,1], cars = 10
        search space = [1, 4*10^2] = [1,400] minutes

        assume mid = 200 min then we need to check does in 200 min, appointing all mechnaic will finish the job or not.
        now we need to drive the way to find how many car each can repair in 200 min.
        n -> r * n^2 = 200
         => n = floor(sqrt(200/r))

        r = 4 -> n = floor(sqrt(50)) = 7
        r = 2 -> n = floor(sqrt(100)) = 10
        all over, means all car will repair in 200 min


        keep doing it, we will reach a number x will be minimum

        """
        ranks = Counter(
            ranks
        )  # converging same rank worker together for faster calculate (rank, number Of Same Rank Workers)

        def get_cars(time, rank):
            return int(math.sqrt(time / rank))

        def is_possible(time):
            total_cars = 0

            for rank, workers in ranks.items():
                total_cars += workers * get_cars(time, rank)

                if total_cars >= cars:
                    return True
            return False

        low = 1

        high = max(ranks) * cars**2
        minutes = 0
        while low <= high:
            mid = (low + high) // 2

            if is_possible(mid):
                minutes = mid
                high = mid - 1
            else:
                low = mid + 1

        return minutes


class Solution_FlatRank:
    def repairCars(self, ranks: List[int], cars: int) -> int:
        """
        time taken by ith mechanic to repair n car with r rank = r * n^2

        * we can have multiple mechnaic to repair car simultaneously
        * Minimize the time taken over all.

        * if we give car to the mechnaic whose rank is lowest, then it will take least of the time as r is lesser, where as if we give to highest rank then it will take most time.
        * Mechnaic rank now define the search space [ 1, max(rank)*n^2]
        * Since we need to minimize the time, we give work to all the mechnaic at same time and ask to finish the work by in that time, if they can finish than , we will take it as possible answer and try reducing further.

        [4,2,3,1], cars = 10
        search space = [1, 4*10^2] = [1,400] minutes

        assume mid = 200 min then we need to check does in 200 min, appointing all mechnaic will finish the job or not.
        now we need to drive the way to find how many car each can repair in 200 min.
        n -> r * n^2 = 200
         => n = floor(sqrt(200/r))

        r = 4 -> n = floor(sqrt(50)) = 7
        r = 2 -> n = floor(sqrt(100)) = 10
        all over, means all car will repair in 200 min


        keep doing it, we will reach a number x will be minimum

        """

        def get_cars(time, rank):
            return int(math.sqrt(time / rank))

        def is_possible(time):
            total_cars = 0

            for r in ranks:
                total_cars += get_cars(time, r)

                if total_cars >= cars:
                    return True
            return False

        low = 1
        high = max(ranks) * cars**2
        minutes = 0
        while low <= high:
            mid = (low + high) // 2

            if is_possible(mid):
                minutes = mid
                high = mid - 1
            else:
                low = mid + 1

        return minutes


def test(ranks, cars, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Ranks", "Cars", "Expected"], True, ranks, cars, expected)
    pass_test, final_pass = True, True
    output = Solution_FlatRank().repairCars(ranks, cars)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Flat ranks", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_UsingWorkers().repairCars(ranks, cars)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Worders", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    return final_pass


if __name__ == "__main__":
    tests: List = [test([4,2,3,1],  10, 16),
                   test([5,1,8], 6, 16)]

    CommonMethods.print_all_test_results(tests)
