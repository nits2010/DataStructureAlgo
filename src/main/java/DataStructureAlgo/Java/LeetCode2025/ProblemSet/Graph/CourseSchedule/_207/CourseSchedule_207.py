
from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution_KhanTopo:
    def build_graph(self, numCourses: int, prerequisites: List[List[int]]) -> (List[List[int]], list):
        adj_list = [[] for _ in range(numCourses)]
        inDegree = [0] * numCourses

        for ai, bi in prerequisites:
            adj_list[ai].append(bi)
            inDegree[bi] += 1

        return adj_list, inDegree

    def topological_sort(self, numCourses: int, adj_list: List[List[int]], inDegree: list[int]) -> int:

        # apply khan's algo for topological sort
        queue = deque()

        # queue all 0 indegree edges
        for node in range(numCourses):
            if inDegree[node] == 0:
                queue.append(node)

        # process them
        processed_node:int = 0

        while queue:
            u = queue.popleft()
            processed_node += 1

            # enqueue all the edges of this node
            for v in adj_list[u]:
                inDegree[v] -= 1

                if inDegree[v] == 0:
                    queue.append(v)

        return processed_node

    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        adj_list, inDegree = self.build_graph(numCourses, prerequisites)
        return self.topological_sort(numCourses, adj_list, inDegree) == numCourses


def test(num_courses:int, prerequisites, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["numCourses", "prerequisites", "Expected"], True, num_courses,prerequisites, expected)
    pass_test, final_pass = True, True
    output = Solution_KhanTopo().canFinish(num_courses, prerequisites)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(2, [[1,0]], True),
                   test(2, [[1,0],[0,1]], False)]

    CommonMethods.print_all_test_results(tests)
