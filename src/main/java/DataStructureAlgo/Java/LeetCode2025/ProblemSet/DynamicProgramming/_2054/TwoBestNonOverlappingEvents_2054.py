"""
Author: Nitin Gupta
Date: 08/02/2026
Question Title:  2054. Two Best Non-Overlapping Events
Link: https://leetcode.com/problems/two-best-non-overlapping-events/description/    
Description: You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

 

Example 1:


Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:

Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:


Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 

Constraints:

2 <= events.length <= 105
events[i].length == 3
1 <= startTimei <= endTimei <= 109
1 <= valuei <= 106
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
@DynamicProgramming
@Sorting
@Heap(PriorityQueue)


<p><p>
Company Tags
-----
@razorPay
<p>
-----

@Editorial <p>https://leetcode.com/problems/two-best-non-overlapping-events/editorial/<p>
-----
@OptimalSolution {@link }
"""


from collections import deque
import bisect, heapq
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods



import functools


class Solution_Heap:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        """
        Another varition of implementation of greedy approach using Priority queue (max-heap)

        """
        pq = []  # (end, value) # default is max heap in python
        events.sort()
        prefix_max = 0
        max_sum_value = 0

        for event in events:
            while (
                pq and pq[0][0] < event[0]
            ):  # this top event is overlapping, end < start
                prefix_max = max(prefix_max, pq[0][1])
                heapq.heappop(pq)

            max_sum_value = max(max_sum_value, event[2] + prefix_max)

            heapq.heappush(pq, [event[1], event[2]])
        return max_sum_value


class Solution_Greedy_LineSweepAlgo:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        """
        In below solution we use greedy with suffix max, we can do using time.
        The line sweep algorithm.
        Start time represent as 1 while end time represent as 0.

        Using this, we apply same logic for finding max_value for all the event till the current who are ended with max value ( just like suffix max).
        however if this is start event then we compute the total max
        """
        times = []

        for e in events:
            times.append([e[0], 1, e[2]])  # this event is starting at e[0]
            times.append(
                [e[1] + 1, 0, e[2]]
            )  # this event will end at e[1] so next event should be start post that only.

        times.sort()  # this will sort first based on (start, end) pair otherwise based on in-out (1-0) pattern otherwise based on value

        prefix_max = 0
        max_value = 0
        for time in times:

            # if this is the start event, then all the previous event has to be completed only then this event can be picked up
            if time[1]:
                max_value = max(
                    max_value, time[2] + prefix_max
                )  # just like max(excluded,included)
            else:
                # if this is end, then find max
                prefix_max = max(prefix_max, time[2])

        return max_value

class Solution_Greedy_SuffixMax2:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        """
        in DP solution, post applying binary search what we are doing is to just try to find the max value which can be obtained including current event or just next index.
        To find the next greater value, we can pre-compute it and use it instead of recurring
        """
        events.sort()  # sort the event based on start time

        suffix_max_value = [0] * (len(events) + 1)

        # compute max value on right so far at curent index
        for i in range(len(events) - 1, -1, -1):
            suffix_max_value[i] = max(suffix_max_value[i + 1], events[i][2])

        max_total_value = 0
        for idx in range(len(events)):

            index = bisect.bisect_right(events, events[idx][1], key=lambda x : x[0])

            # include current or exclude current
            included = (
                events[idx][2] + suffix_max_value[index]
            )  # pick first choice + second choice

            max_total_value = max(
                max_total_value, included  # dont pick first choice and skip
            )

        return max_total_value
    
class Solution_Greedy_SuffixMax:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        """
        in DP solution, post applying binary search what we are doing is to just try to find the max value which can be obtained including current event or just next index.
        To find the next greater value, we can pre-compute it and use it instead of recurring
        """
        events.sort()  # sort the event based on start time

        starts = [
            event[0] for event in events
        ]  # get start of each to do binary search easily

        suffix_max_value = [0] * (len(events) + 1)

        # compute max value on right so far at curent index
        for i in range(len(events) - 1, -1, -1):
            suffix_max_value[i] = max(suffix_max_value[i + 1], events[i][2])

        max_total_value = 0
        for idx in range(len(events)):

            index = bisect.bisect_right(starts, events[idx][1])

            # include current or exclude current
            included = (
                events[idx][2] + suffix_max_value[index]
            )  # pick first choice + second choice

            max_total_value = max(
                max_total_value, included  # dont pick first choice and skip
            )

        return max_total_value
    


class Solution_DP_Bisect_BinarySearch:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        events.sort()  # sort the event based on start time

        starts = [
            event[0] for event in events
        ]  # get start of each to do binary search easily

        @functools.lru_cache(maxsize=None)  # unbound cache on params
        def dfs(idx, cnt):

            # boundary conditions, events over or selected either 2 or more than 2 events
            if idx >= len(events) or cnt >= 2:
                return 0

            # find the next event which does not overlap to current
            index = bisect.bisect_right(starts, events[idx][1])
            # print(f"idx = {idx}, event = {events[idx]}, index={index}, event_ = {events[index] if index < len(events) else None}")

            # either include current event and next non-over lapping or skip to next event
            included = events[idx][2] + dfs(index, cnt + 1)
            excluded = dfs(idx + 1, cnt)
            # print(f"max = {max(included, excluded)}")

            return max(included, excluded)

        return dfs(0, 0)


class Solution_DP_BinarySearch:
    def maxTwoEvents(self, events: List[List[int]]) -> int:
        events.sort()  # sort the event based on start time

        # print(f"events = {events}")
        def binary_search(idx):
            low = idx + 1
            high = len(events)
            end_time = events[idx][1]

            while low < high:

                mid = (low + high) // 2

                if events[mid][0] <= end_time:
                    low = mid + 1
                else:
                    high = mid

            return low

        @functools.lru_cache(maxsize=None)  # unbound cache on params
        def dfs(idx, cnt):

            # boundary conditions, events over or selected either 2 or more than 2 events
            if idx >= len(events) or cnt >= 2:
                return 0

            # find the next event which does not overlap to current
            index = binary_search(idx)
            # print(f"idx = {idx}, event = {events[idx]}, index={index}, event_ = {events[index] if index < len(events) else None}")

            # either include current event and next non-over lapping or skip to next event
            included = events[idx][2] + dfs(index, cnt + 1)
            excluded = dfs(idx + 1, cnt)
            # print(f"max = {max(included, excluded)}")

            return max(included, excluded)

        return dfs(0, 0)



def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    
    output = Solution_Heap().maxTwoEvents(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Heap", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    
    output = Solution_Greedy_SuffixMax().maxTwoEvents(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Greedy_SuffixMax", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_Greedy_SuffixMax2().maxTwoEvents(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Greedy_SuffixMax2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_Greedy_LineSweepAlgo().maxTwoEvents(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Greedy_LineSweepAlgo", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    
    output = Solution_DP_BinarySearch().maxTwoEvents(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DP_BinarySearch", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_DP_Bisect_BinarySearch().maxTwoEvents(input_data[::])
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DP_Bisect_BinarySearch", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[1,3,2],[4,5,2],[2,4,3]], 4),
                   test([[1,3,2],[4,5,2],[1,5,5]], 5),
                   test([[1,5,3],[1,5,1],[6,6,5]], 8)]

    CommonMethods.print_all_test_results(tests)
