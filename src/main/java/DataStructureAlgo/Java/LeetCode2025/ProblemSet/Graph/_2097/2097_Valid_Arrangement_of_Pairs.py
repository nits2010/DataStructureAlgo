"""
Author: Nitin Gupta
Date: 9/6/2025
Question Title: 2097. Valid Arrangement of Pairs
Link: https://leetcode.com/problems/valid-arrangement-of-pairs/description
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

from collections import deque, defaultdict
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution:

    def isValidArrangementPossible(self, degree: Dict[int, int]) -> bool:
        """
        This matches the Eulerian path property in a directed graph:
        At most one node with out-degree = in-degree + 1 (start)
        At most one node with in-degree = out-degree + 1 (end)
        All others: in-degree == out-degree

        Count how many nodes have degree 1 (should be at most 1).
        Count how many have degree -1 (should be at most 1).
        All other nodes should have degree 0.

        :param degree:
        :return:
        """
        return sum(v for v in degree.values()) == 0

    def validArrangement(self, pairs: List[List[int]]) -> List[List[int]]:
        n = len(pairs)
        # build graph
        graph = defaultdict(list)
        degree = defaultdict(int)  # indegree -1, outdegree +1

        for u, v in pairs:
            graph[u].append(v)

            # u -> v
            degree[v] -= 1  # indegree
            degree[u] += 1  # outdegree

        # isValidArrangementPossible(degree)
        # find a node whose degree is = 1
        start_node = next((u for u in degree if degree[u] == 1), pairs[0][0])

        # start a eularian path from this start_node
        eulerian_path = []

        def dfs(u):

            while graph[u]:
                v = graph[u].pop()
                dfs(v)

            eulerian_path.append(u)

        dfs(start_node)

        # result_pairs = zip()
        # for p in range(len(eulerian_path) - 2, -1, -1):
        #     result_pairs.append((eulerian_path[p + 1], eulerian_path[p]))

        # result_pairs = [(eulerian_path[i+1], eulerian_path[i])
        #                 for i in range(len(eulerian_path)-2, -1, -1)]

        result_pairs = list(zip(eulerian_path[::-1], eulerian_path[-2::-1]))

        return result_pairs


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().validArrangement(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[5, 1], [4, 5], [11, 9], [9, 4]], [[11, 9], [9, 4], [4, 5], [5, 1]]),
                   test([[1, 3], [3, 2], [2, 1]], [[1, 3], [3, 2], [2, 1]]),
                   test([[1, 2], [1, 3], [2, 1]], [[1, 2], [2, 1], [1, 3]])]

    CommonMethods.print_all_test_results(tests)
