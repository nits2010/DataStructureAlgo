"""
Author: Nitin Gupta
Date: 2/6/2026
Question Title: 735. Asteroid Collision
Link: https://leetcode.com/problems/asteroid-collision/description/
Description: We are given an array asteroids of integers representing asteroids in a row. The indices of the asteroid in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.



Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
Example 4:

Input: asteroids = [3,5,-6,2,-1,4]​​​​​​​
Output: [-6,2,4]
Explanation: The asteroid -6 makes the asteroid 3 and 5 explode, and then continues going left. On the other side, the asteroid 2 makes the asteroid -1 explode and then continues going right, without reaching asteroid 4.


Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
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
@Stack
@Simulation

<p><p>
Company Tags
-----
@Amazon
@Facebook
@DoorDash
@tiktok
@Lyft

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

class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        """
        Core idea (why stack works)
            - Collisions only happen when:
                - previous asteroid is moving right (> 0)
                - current asteroid is moving left (< 0)
                - Anything else can never meet.

            So:
            We keep a stack of “surviving” asteroids
            For each new asteroid, we try to resolve collisions with the stack top

        """
        stack = []

        for curr_ast in asteroids:

            # the current ast is moving left while the previous ast was moving right, they are going to collide
            while stack and stack[-1] > 0 > curr_ast:
                if -curr_ast == stack[-1]: # both of the same magnitude, both destroy
                    stack.pop()
                    break # current ast no longer available
                elif -curr_ast > stack[-1]:  # current ast wins
                    stack.pop()
                else:
                    # Stack top is bigger → current asteroid explodes
                    break
            else:
                stack.append(curr_ast)
        return stack


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
