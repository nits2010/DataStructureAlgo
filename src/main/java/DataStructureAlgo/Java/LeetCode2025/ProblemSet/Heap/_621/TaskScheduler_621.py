
"""
Author: Nitin Gupta
Date: 19/02/2026
Question Title: 621. Task Scheduler
Link: https://leetcode.com/problems/task-scheduler/description/
Description: You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.

Return the minimum number of CPU intervals required to complete all tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

After completing task A, you must wait two intervals before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.

Example 2:

Input: tasks = ["A","C","A","B","D","B"], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.

With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:

Input: tasks = ["A","A","A", "B","B","B"], n = 3

Output: 10

Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.

There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.

 

Constraints:

1 <= tasks.length <= 104
tasks[i] is an uppercase English letter.
0 <= n <= 100

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
@HashTable
@Greedy
@Sorting
@Heap(PriorityQueue)
@Counting

<p><p>
Company Tags
-----
@Amazon 
@Apple 
@Facebook 
@Google 
@Microsoft 
@Nutanix 
@Oracle 
@Uber 
@VMware
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import Counter, deque
import heapq
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


# Time: O(T*26 + log(26)) Whre T = len(tasks)
# Space : O(26)
class Solution_Simulation_HeapQueue:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        """We will simulate in same way how cpu does
        CPU takes the highest priority task and scheduled it for execution. It uses max-heap for priority and Queue for scheduling.
        Once the task is scheduled, it will give to the cpu to execute.

        Here, we dont have priority but frequency can be make a priority here.
        Also additional constraint of not giving same task until cooldown=n is there, hence we will decrease its priority based on that.

        max_heap -> for picking most frequent task
        queue -> to scheduled the task with cooldown.

        We will use time as clock to simulate cooldown.

        Algo:
        1. Compute Frequency of each task, that will be priority
        2. create max heap of task freq and Create a cooldown queue
        3. Until all task scheduled,
        4. Take a task from max_heap if available otherwise idle
        5. Reduce its frequency and if this task frequ > 0 then push it to cooldown with new time as time+n
        6. See for current time, is there any task from cooldown that can be push to heap
        """

        tasks_freq = Counter(tasks)

        # append all the frequency in the max_heap and hipify
        max_heap = [(-freq, task) for task, freq in tasks_freq.items()]
        heapq.heapify(max_heap)

        cooldown = deque()
        time = 0
        cpu = []

        while max_heap or cooldown:
            time += 1
            if max_heap:
                freq, task = heapq.heappop(max_heap)

                cpu.append(task)  # add task to cpu

                # decrease its freq
                freq = freq + 1  # since we pushed -freq

                if freq:  # if task left to process
                    cooldown.append((time + n, freq, task))

            else:  # no task to scheduled, cpu idle
                cpu.append("idle")

            # push the task again if the cooldown over
            if cooldown and cooldown[0][0] == time:
                _, freq, task = cooldown.popleft()
                heapq.heappush(max_heap, (freq, task))

        # cpu_task = "->".join(cpu)
        # print(cpu_task)
        return time


# Time: O(T*26) Whre T = len(tasks)
# Space : O(26)
class Solution_GreedyMath:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        """
        Key observations:
            1. We have total T = len(tasks) that needs to be scheduled with 'n' gaps.
                Derivation:
                     1. When n = 0, we don't need to worry, as we can put each task back to back. Total cycle = T
                     2. When n > 0, then we need to seperate out the each similar tasks, which may introduced the idle cycle.
                     3. Idle depends on whether other tasks are enough to fill the gap slots created by the most frequent task
                        If tasks = [A,A,A,B,B,B,C,C] and n = 2
                        then the placement is [A,B,C,A,B,C,A] requires no idle time.

            2. if a task appear more than 1 time, then only we need either another tasks a.k.a empty slots in between.
                Derivation:
                    1. If [A,A,A,A] n = 2 Then we have to arrange them like
                        A _ _ A _ _ A _ _ A
                        A |   A |   A |   A
                        Which makes total empty slots = 6 -> (freq(A)-1) * n
                        Which makes total seperators(group of empty sloat) = 3 -> (freq(A) - 1)
                        We can only place the same task 'A' again after 'n' slot, which is at 'n+1' position in the row of task to cpu.

                        Since we have slots, we can fill them either way
                            a. With other unique tasks
                            b. idle cpu time.

                        as discussed previously, Idle depends on whether other tasks are enough to fill the gap slots created by the most frequent task.
                        Hence to place task b/w the most-freq taks, we need at least (max_freq - 1) tasks.
                        if say 'k' is no. of unique task with max_freq, then all of these k will sit in b/w those empty slots

                        [A,A,A,B,B,B,C,C] n = 2
                         freq(A) = 3, freq(B) = 3, freq(C) = 2

                        max_freq = 3
                        k = 2

                            A _ _ A _ _ A
                        ->  A B C A B C A B = 8

                        Hence, with max freq 'f' we have (f-1) option and next placement would be at (n+1) position filled with K unique tasks
                        formula = (f-1) * (n+1) + k
                        -> (3-1)*(2+1) + 2 = 2*3 + 2 = 8
                        however when n=0 then we dont need any seperator, hence result = max(len(tasks), formula)

        🔧 Cleaner Consolidated Reasoning

            1️⃣ If n = 0
            → No cooldown constraint
            → Answer = T

            2️⃣ If n > 0
            The task with max frequency f dominates layout.

            It creates:

            (f - 1) gap groups

            Each gap must have n slots

            So base structure size:
            (f-1) * (n+1)

            3️⃣ If k tasks share max frequency
            → They occupy the last row
            → Add + k

            So candidate length:
            (f−1)(n+1)+k

            4️⃣ If other tasks are enough to fill all gaps
            → No idle needed
            → So final answer:

                max(T,(f−1)(n+1)+k)

        """

        freq_map = Counter(tasks)

        f = max(freq_map.values())
        k = 0
        for key in freq_map:
            if freq_map[key] == f:
                k += 1

        return max(len(tasks), (f - 1) * (n + 1) + k)



def test(tasks, cooldown, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Tasks","Cooldown", "Expected"], True, tasks, cooldown, expected)
    pass_test, final_pass = True, True
    
    output = Solution_Simulation_HeapQueue().leastInterval(tasks, cooldown)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Simulation_HeapQueue", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_GreedyMath().leastInterval(tasks, cooldown)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["_GreedyMath", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    return final_pass


if __name__ == "__main__":
    tests: List = [test(tasks = ["A","A","A","B","B","B"], cooldown= 2, expected=8),
                   test(tasks = ["A","C","A","B","D","B"], cooldown = 1, expected=6),
                   test(tasks = ["A","A","A", "B","B","B"], cooldown = 3, expected=10)]

    CommonMethods.print_all_test_results(tests)
