"""
Author: Nitin Gupta
Date: 3/2/2026
Question Title: 895. Maximum Frequency Stack
Link: https://leetcode.com/problems/maximum-frequency-stack/description/


Description:There are n cars at given miles away from the starting mile 0, traveling to reach the mile target.

You are given two integer arrays position and speed, both of length n, where position[i] is the starting mile of the ith car and speed[i] is the speed of the ith car in miles per hour.

A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.

A car fleet is a single car or a group of cars driving next to each other. The speed of the car fleet is the minimum speed of any car in the fleet.

If a car catches up to a car fleet at the mile target, it will still be considered as part of the car fleet.

Return the number of car fleets that will arrive at the destination.

 

Example 1:

Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]

Output: 3

Explanation:

The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms at target.
The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.
The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
Example 2:

Input: target = 10, position = [3], speed = [3]

Output: 1

Explanation:

There is only one car, hence there is only one fleet.
Example 3:

Input: target = 100, position = [0,2,4], speed = [4,2,1]

Output: 1

Explanation:

The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The car starting at 4 (speed 1) travels to 5.
Then, the fleet at 4 (speed 2) and the car at position 5 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 

Constraints:

n == position.length == speed.length
1 <= n <= 105
0 < target <= 106
0 <= position[i] < target
All the values of position are unique.
0 < speed[i] <= 106

File reference
-----------
Duplicate {@link}
Similar {@link MinStack}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Array
@Stack
@Sorting
@Monotonic Stack


<p><p>
Company Tags
-----
@Google 
<p>
-----

@Editorial <p><p>
@OptimalSolution {@link }
"""

from typing import List

class Solution_Stack:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        """
            

        It is fair to say that for a car to reach target will only take (target - position) / speed hour. 
        Now in order to know which car join which fleet, we need to know which car will reach target first, second...
        Mean, we need to see the car wrt to their time to reach target i.e the decending order of time. 

        Now, Lets have two care F and S, F car will reach first to target as time(F) < time(S).
        S car will only able to join the fleet if
        1. pos(F) < pos(S) since car can't pass each other
        2. time(F) == time(S) , since both car will reach target on same time. 

        [1,1,7,3,12]
        F = 1
        S = 7
        Since for S it takes 7 hour to reach target while F will just take 1 hr, that imply they will never meet
        However
        S = 7
        F = 3
        Here F takes just 3 hour to reach target while S needs 7 hour, however if you see the position of F (3) and position of S (5)
        that makes F can not pass S and hence it must join the fleet. 

        Algo: 
        1. Zip both position and speed and sort them in decending order
        2. Compute the time to reach of each car
        3. Apply the logic such that [C1,C2,C3...], C3 <= C2 then it will join the fleet

        Pos=[10,8,0,5,3], speed = [2,4,1,1,3], target = 12
        
        Step 1: [(10,2), (8,4), (0,1), (5,1), (3,3)] -> sort -> [(10,2), (8,4), (5,1), (3,3), (0,1)]
        Step 2: [1,1,7,3,12]
        Step 3: [(1,1), (7,3), (12)] = 3 fleet
        """

        stack = []
        pos_speed = sorted(zip(position,speed), reverse=True)
        time = [(target - pos)/s for pos,s in pos_speed]
        for t in time:
            stack.append(t)

            # there must be two car to check fleet
            while len(stack) >=2 and stack[-2] >= stack[-1]:
                stack.pop()
            
        return len(stack)


class Solution_WithoutStack:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        pos_speed = sorted(zip(position,speed), reverse=True)
        times = [(target - pos)/s for pos,s in pos_speed]

        fleet = 1
        prev_car_time = times[0]
        for cur_car in range(1,len(times)):

            if times[cur_car] > prev_car_time: # not (cur_car_time <= prev_car_time):
                fleet +=1 
                prev_car_time = times[cur_car]
        
        return fleet 



class Solution_1:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        pos_speed = sorted(zip(position,speed))
        time = [(target - pos)/s for pos,s in pos_speed]
        # take time as stack it self
        fleet = 0

        while len(time) > 1: # time will convert to the cars reach target, eventually all car will reach target

            f = time.pop()
            s = time[-1]

            if f < s: # as f will reach first and s can't join the fleet
                fleet +=1 
            else:
                # s will join the fleet
                time[-1] = f

        return fleet + 1




from helpers.common_methods import CommonMethods

def test(pos, speed,target, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Position", "Speed", "Target", "Expected"], True, pos, speed, target, expected)
    pass_test, final_pass = True, True
    output = None
    
    output = Solution_Stack().carFleet(target, pos, speed)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output-Stack", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_WithoutStack().carFleet(target, pos, speed)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output-WithoutStack", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_1().carFleet(target, pos, speed)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output-1", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([1,4], [3,2], 10, 1),
                   test([4,1,0,7], [2,2,1,1], 10, 3),
                   test([10,8,0,5,3], [2,4,1,1,3], 12, 3),
                   test([0,2,4], [4,2,1], 100, 1),
                   test([0,4,2], [2,1,3], 10, 1)]
    

    CommonMethods.print_all_test_results(tests)