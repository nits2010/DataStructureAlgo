"""
Author: Nitin Gupta
Date: 8/28/2025
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



import math
from collections import defaultdict
from itertools import combinations
from typing import List, Dict, Set
from helpers.common_methods import CommonMethods


class Solution:
    def build_graph(self, n: int, relations: List[List[int]]) -> Dict[int, List[int]]:
        graph = {i: [] for i in range(1, n + 1)}
        for u, v in relations:
            graph[v].append(u)
        return graph

    def minNumberOfSemesters(self, n: int, relations: List[List[int]], k: int) -> int:
        if not relations:
            return math.ceil(n / k)

        graph = self.build_graph(n, relations)
        min_semesters = n  # upper bound

        def dfs(semester: int, taken: Set[int]):
            nonlocal min_semesters

            # If all courses are taken → update result
            if len(taken) == n:
                min_semesters = min(min_semesters, semester)
                return

            # Pruning: even in best case, can't beat current min
            remaining = n - len(taken)
            if semester + math.ceil(remaining / k) >= min_semesters:
                return

            # Find available courses (all prereqs taken)
            available = {
                c
                for c, prereqs in graph.items()
                if c not in taken and all(p in taken for p in prereqs)
            }

            if not available:
                return

            # Case 1: Take all available if ≤ k
            if len(available) <= k:
                dfs(semester + 1, taken | available)
            else:
                # Case 2: Try all combinations of size k
                for comb in combinations(available, k):
                    dfs(semester + 1, taken | set(comb))

        dfs(0, set())
        return min_semesters

def test(n, releations, k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["N","Relations", "K", "Expected"], True, n, releations, k, expected)
    pass_test, final_pass = True, True
    output = Solution().minNumberOfSemesters(n, releations, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(4, [[2,1],[3,1],[1,4]], 2, 3),
                   test(5, [[2,1],[3,1],[4,1],[1,5]],2,  4),
                   test(14, [[11,7]], 2, 7),
                   test(4, [], 2, 2),
                   test(4, [], 3, 2),
                   test(15, [[2,1]], 4, 4)]

    CommonMethods.print_all_test_results(tests)
