"""
Author: Nitin Gupta
Date: 8/29/2025
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
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution:
    def minimumTime(self, n: int, relations: List[List[int]], time: List[int]) -> int:
        graph = {i: [] for i in range(1, n + 1)}
        in_degree = [0] * (n + 1)
        finish_time = [0] * (n + 1)  # track when each course finishes

        # build graph
        for u, v in relations:
            graph[u].append(v)
            in_degree[v] += 1

        # start with courses having no prerequisites
        queue = deque([i for i in range(1, n + 1) if in_degree[i] == 0])

        for u in queue:
            finish_time[u] = time[u - 1]  # course itself

        # process courses
        while queue:
            u = queue.popleft()
            for v in graph[u]:
                # update finish time for v
                finish_time[v] = max(finish_time[v], finish_time[u] + time[v - 1])
                in_degree[v] -= 1
                if in_degree[v] == 0:
                    queue.append(v)

        return max(finish_time)


def test(n, relations, time, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["n", "Relations", "Time", "Expected"], True, n, relations, time, expected)
    pass_test, final_pass = True, True
    output = Solution().minimumTime(n, relations, time)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(3, [[1,3],[2,3]], [3,2,5], 8),
                   test(5, [[1,5],[2,5],[3,5],[3,4],[4,5]], [1,2,3,4,5], 12),
                   test(9, [[2,7],[2,6],[3,6],[4,6],[7,6],[2,1],[3,1],[4,1],[6,1],[7,1],[3,8],[5,8],[7,8],[1,9],[2,9],[6,9],[7,9]], [9,5,9,5,8,7,7,8,4], 32)]

    CommonMethods.print_all_test_results(tests)
