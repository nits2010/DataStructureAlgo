"""
Author: Nitin Gupta
Date: 2/5/2026
Question Title: 1776. Car Fleet II
Link: https://leetcode.com/problems/car-fleet-ii/description/
Description: There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array cars of length n, where cars[i] = [positioni, speedi] represents:

positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that positioni < positioni+1.
speedi is the initial speed of the ith car in meters per second.
For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.

Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.



Example 1:

Input: cars = [[1,2],[2,1],[4,3],[7,2]]
Output: [1.00000,-1.00000,3.00000,-1.00000]
Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with speed 2 m/s.
Example 2:

Input: cars = [[3,4],[5,4],[6,3],[9,1]]
Output: [2.00000,1.00000,1.50000,-1.00000]


Constraints:

1 <= cars.length <= 105
1 <= positioni, speedi <= 106
positioni < positioni+1

File reference
-----------
Duplicate {@link}
Similar {@link CarFleet_853.py}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@Array
@Math
@Stack
@Heap(PriorityQueue)
@MonotonicStack

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque, defaultdict
from typing import List, Optional, Dict, Any
import heapq
from helpers.common_methods import CommonMethods

"""
 A car will collide to a car ahead of it only. Means if [c1, c2, ...cn-1, cn] then c1 can collide to one of {c2,c3,..cn} only. Since c1 position is < position of (c2, ...cn)
 I process cars from right to left using a monotonic stack. For each car, I remove cars ahead that it can never catch or can only catch after they’ve already collided and changed speed. 
 The first remaining car in the stack gives the earliest valid collision time.
"""


class Solution:
    def getCollisionTimes(self, cars: List[List[int]]) -> List[float]:
        stack = []
        result = [-1] * len(cars)

        for i in range(len(cars) - 1, -1, -1):
            back_pos, back_speed = cars[i]

            while stack:
                front_pos, front_speed = cars[stack[-1]]

                # Front car is faster or equal speed → impossible to collide
                if back_speed <= front_speed:
                    stack.pop()
                else:
                    time = (front_pos - back_pos) / (back_speed - front_speed)

                    # either front car has never been collied or it has collied after than i would reach
                    if result[stack[-1]] == -1 or time <= result[stack[-1]]:
                        result[i] = time
                        break
                    else:
                        stack.pop()

            stack.append(i)

        return result


class Solution_1:
    def getCollisionTimes(self, cars: List[List[int]]) -> List[float]:

        def _time(car1, car2):
            return (car2[0] - car1[0]) / (car1[1] - car2[1])

        def _isAheadCarAlreadyCollide(current,ahead, prev_collide_time):

            return _time(current, ahead) >= prev_collide_time



        stack = []  # (index, pos, speed)
        result = [-1]*len(cars)

        for i in range(len(cars)-1, -1, -1):
            pos, speed = cars[i]
            # Front car is faster or equal speed → impossible to collide
            # they collide earlier than i could reach them → by then they’ve merged and changed speed
            while stack and  ( speed <= cars[stack[-1]][1] or ( result[stack[-1]] != -1 and _isAheadCarAlreadyCollide(cars[i], cars[stack[-1]], result[stack[-1]])) ):
                stack.pop()

            if stack:
                result[i] = _time(cars[i], cars[stack[-1]])


            stack.append(i)

        return result

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
