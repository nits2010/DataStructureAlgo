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



class Solution:
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
