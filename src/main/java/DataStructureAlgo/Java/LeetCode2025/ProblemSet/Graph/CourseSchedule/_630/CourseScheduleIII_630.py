"""
Author: Nitin Gupta
Date: 8/23/2025
Question Title:
Link:
Description:
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----

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
import heapq
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time/Space: O(n*logn) / O(n)
# However this version is code wise simple, but it perform an extra logn operation, in worst case, every time. Hence perfom less better than other solution. 
class Solution_Simplified:
    def scheduleCourse(self, courses: List[List[int]]) -> int:
        """
        Since we need to minimize the number of courses, we need to pick the least day possible course first.
        Hence, we need to sort the data based on closed day.
        However, since we need to maximize the number of course taken, it is possible that we can discard the taken course in favor of current course if taken course duration > current course duration
        Maintaining a max-heap will help us to make this decision
        :param courses:
        :return:
        """
        priority_queue = [] # max-heap
        total_duration = 0
        courses.sort(key=lambda c: c[1])

        for current_duration, last_day in courses:

            #take this course
            total_duration += current_duration
            heapq.heappush ( priority_queue, -current_duration)

            # does taking this course goes beyond the last-day?
            if total_duration > last_day:
                #remove this course
                total_duration += heapq.heappop(priority_queue) # this will return -ve duration

        return len(priority_queue)


# Time/Space: O(n*logn) / O(n)
class Solution:
    def scheduleCourse(self, courses: List[List[int]]) -> int:
        """
         Since we need to maximize the number of courses, we need to pick the least day possible course first.
         Hence, we need to sort the data based on closed day.
         However, it is possible that we can discard the taken course in favor of current course. 
         
         Now, we need to know which course to remove in order to take current course ? We have following choices
         1. Discard current course -> Means skipp the current course and carry the old one
         2. Remove the shortest duration from previously taken course -> If we remove shortest duration, then we create small room only which may not allow to fit current course
         3. Remove the longest duration from prevously taken course -> if we remove the longest duration one, then we give more room to accomdate current course, they may allow to take current course. Lets examine it if it can or not
         
         
         Key points: We have sorted the course based on last_day
         Assume 'current_time' denotes the current_time we are in and now we are trying to take the next course
         
         if current_time + course_duration > course_last_day
         
         Means, we simply can't take the current course as it does not fit, now lets remove the longest duration course from previously taken if any
         
         if longest > current_duration
         
         this means longest - current_duration > 0
         new_time = current_time - longest + current_duration
                    = current_time - (longest - current_duration )
                    = current_time - $ time 
        
        since we are reducing the $ time from current and assign in new_time, this means
         => new_time < current_time 
         
         Hence 
         current_time + course_duration > course_last_day
         => new_time < (current_time + course_duration - longest) < course_last_day
         
         =>> new_time < course_last_day
         
         
         Hence, removing longest time make sure we can accomdate the new course always 
         
         
         https://leetcode.com/problems/course-schedule-iii/solutions/363735/detailed-explanation-with-logic-building-79al/
        
         :param courses:
         :return:
        """
        priority_queue = [] # max-heap
        total_duration = 0
        courses.sort(key=lambda c: c[1])
        for current_duration, last_day in courses:

            # Take this course, if possible
            if total_duration + current_duration <= last_day:
                total_duration += current_duration
                heapq.heappush ( priority_queue, -current_duration)
            elif priority_queue:

                if -priority_queue[0] > current_duration:
                    # remove last duration and add current duration
                    total_duration -= -heapq.heappop(priority_queue)
                    total_duration += current_duration
                    heapq.heappush ( priority_queue, -current_duration)

        return len(priority_queue)






def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution().scheduleCourse(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, False)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Simplified().scheduleCourse(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, False)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[100,200],[200,1300],[1000,1250],[2000,3200]], 3),
                   test([[1,2]], 1),
                   test([[3,2],[4,3]], 0),
                   test([[5,5],[4,6],[2,6]], 2)]

    CommonMethods.print_all_test_results(tests)
