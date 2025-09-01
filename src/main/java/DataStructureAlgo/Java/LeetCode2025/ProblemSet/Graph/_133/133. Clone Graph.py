"""
Author: Nitin Gupta
Date: 9/1/2025
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


# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []



class Solution_bfs:
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        if not node:
            return node

        old_to_new_map = {}
        queue = deque()
        copy = Node(node.val)
        old_to_new_map[node] = copy

        queue.append(node)

        while queue:
            current = queue.popleft()

            current_clone = old_to_new_map[current]

            for neigh in current.neighbors:
                if neigh not in old_to_new_map:
                    old_to_new_map[neigh] = Node(neigh.val)
                    queue.append(neigh)

                current_clone.neighbors.append(old_to_new_map[neigh])

        return copy


class Solution_dfs:
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        if not node:
            return node

        old_to_new_map = {}

        def dfs(node):
            if node in old_to_new_map:
                return old_to_new_map[node]

            copy = Node(node.val)
            old_to_new_map[node] = copy

            # run for all neighbors
            for neighbor in node.neighbors:
                copy.neighbors.append(dfs(neighbor))

            return copy

        return dfs(node)


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_dfs().cloneGraph(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_bfs().cloneGraph(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
