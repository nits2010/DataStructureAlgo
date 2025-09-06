"""
Author: Nitin Gupta
Date: 9/6/2025
Question Title: 743. Network Delay Time
Link: https://leetcode.com/problems/network-delay-time/description
Description: You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.


Example 1:

Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1


Constraints:

1 <= k <= n <= 100
1 <= times.length <= 6000
times[i].length == 3
1 <= ui, vi <= n
ui != vi
0 <= wi <= 100
All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link DijkstraShortestPath.java}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@DepthFirstSearch
@BreadthFirstSearch
@Graph
@Heap(PriorityQueue)
@ShortestPath

<p><p>
Company Tags
-----
@Google
@Amazon
@AkunaCapital
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
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        """
        We need to determine the minimum time required for a signal to travel from node k to every other node.
        This is essentially the shortest path problem, where the time to reach a node is the sum of the edge weights along the path from k to that node.

        Since all edge weights are positive, Dijkstra’s algorithm is a natural fit.

        By applying Dijkstra’s algorithm, we compute the shortest distance from k to each node.
        The final answer is the maximum value among these shortest distances (since the signal must reach all nodes).
        If any node is unreachable, we return -1.
        """

        # build graph
        graph = defaultdict(list)
        for u, v, w in times:
            graph[u].append((v, w))

        costs = [float("inf")] * (n + 1)
        costs[k] = 0  # reaching a single from source cost is 0
        settled = set()

        # min priority queue
        min_heap = [(0, k)]
        max_cost = -1
        while min_heap and len(settled) != n:

            cost, u = heapq.heappop(min_heap)
            max_cost = max(max_cost, cost)
            if u in settled:
                continue

            settled.add(u)

            # traverse its neighbours node
            for v, w in graph[u]:
                if v not in settled and costs[v] > cost + w:
                    costs[v] = cost + w
                    heapq.heappush(min_heap, (costs[v], v))

        # max_cost = max(costs[1::])
        return max_cost if len(settled) == n else -1


# using settled
class Solution_WithSettled:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        """
        We need to determine the minimum time required for a signal to travel from node k to every other node.
        This is essentially the shortest path problem, where the time to reach a node is the sum of the edge weights along the path from k to that node.

        Since all edge weights are positive, Dijkstra’s algorithm is a natural fit.

        By applying Dijkstra’s algorithm, we compute the shortest distance from k to each node.
        The final answer is the maximum value among these shortest distances (since the signal must reach all nodes).
        If any node is unreachable, we return -1.
        """

        # build graph
        graph = defaultdict(list)
        for u, v, w in times:
            graph[u].append((v, w))

        costs = [float("inf")] * (n + 1)
        costs[k] = 0  # reaching a single from source cost is 0
        settled = set()

        # min priority queue
        min_heap = [(0, k)]

        while min_heap and len(settled) != n:

            cost, u = heapq.heappop(min_heap)

            if u in settled:
                continue

            settled.add(u)

            # traverse its neighbours node
            for v, w in graph[u]:
                if v not in settled and costs[v] > costs[u] + w:
                    costs[v] = costs[u] + w
                    heapq.heappush(min_heap, (costs[v], v))

        max_cost = max(costs[1::])
        return max_cost if max_cost != float("inf") else -1


class Solution_WithoutSettled:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        """
        We need to determine the minimum time required for a signal to travel from node k to every other node.
        This is essentially the shortest path problem, where the time to reach a node is the sum of the edge weights along the path from k to that node.

        Since all edge weights are positive, Dijkstra’s algorithm is a natural fit.

        By applying Dijkstra’s algorithm, we compute the shortest distance from k to each node.
        The final answer is the maximum value among these shortest distances (since the signal must reach all nodes).
        If any node is unreachable, we return -1.
        """

        # build graph
        graph = defaultdict(list)
        for u, v, w in times:
            graph[u].append((v, w))

        costs = [float("inf")] * (n + 1)
        costs[k] = 0  # reaching a single from source cost is 0

        # min priority queue
        min_heap = [(0, k)]

        while min_heap:

            cost, u = heapq.heappop(min_heap)

            if cost > costs[u]:
                continue

            # traverse its neighbours node
            for v, w in graph[u]:
                if costs[v] > costs[u] + w:
                    costs[v] = costs[u] + w
                    heapq.heappush(min_heap, (costs[v], v))

        max_cost = max(costs[1::])
        return max_cost if max_cost != float("inf") else -1



def test(input_data, n, k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_WithoutSettled().networkDelayTime(input_data,n,k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_WithoutSettled", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    output = Solution_WithSettled().networkDelayTime(input_data,n,k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_WithSettled", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution().networkDelayTime(input_data,n,k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[2,1,1],[2,3,1],[3,4,1]],4,  2, 2),
                   test([[1,2,1]], 2, 1, 1),
                   test([[1,2,1]], 2, 2, -1)]

    CommonMethods.print_all_test_results(tests)
