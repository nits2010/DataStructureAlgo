"""
Author: Nitin Gupta
Date: 8/19/2025
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
    def build_graph(self, num_courses: int, prerequisites: List[List[int]]) -> (List[List[int]], list):
        adj_list = [[] for _ in range(num_courses)]
        for ai, bi in prerequisites:
            adj_list[ai].append(bi)

        return adj_list

    def topological_sort(self, num_courses: int, adj_list: List[List[int]]) -> int:
        order = []
        visited_state = [0] * num_courses # 0 yet to take, 1 repeated, 2 taken


        def is_cycle_exists(u:int) -> bool :

            if visited_state[u] == 1 :
                return True

            if visited_state[u] == 2:
                return False

            # take the course
            visited_state[u] = 1

            for v in adj_list[u]:
                if is_cycle_exists(v):
                    return True

            # course taken
            visited_state[u] = 2
            order.append(u)

            return False

        # visit all the courses
        for c in range(num_courses):
            if visited_state[c]!=2 and is_cycle_exists(c):
                return []

        return order


    def findOrder(self, num_courses: int, prerequisites: List[List[int]]) -> List[int]:
        adj_list = self.build_graph(num_courses, prerequisites)
        return self.topological_sort(num_courses, adj_list)





class Solution_KhanTopo:
    def build_graph(self, num_courses: int, prerequisites: List[List[int]]) -> (List[List[int]], list):
        adj_list = [[] for _ in range(num_courses)]
        inDegree = [0] * num_courses

        for ai, bi in prerequisites:
            adj_list[ai].append(bi)
            inDegree[bi] += 1

        return adj_list, inDegree

    def topological_sort(self, num_courses: int, adj_list: List[List[int]], inDegree: list[int]) -> int:

        # apply khan's algo for topological sort
        queue = deque()

        # queue all 0 indegree edges
        for node in range(num_courses):
            if inDegree[node] == 0:
                queue.append(node)

        # process them
        order = []

        while queue:
            u = queue.popleft()
            order.append(u)

            # enqueue all the edges of this node
            for v in adj_list[u]:
                inDegree[v] -= 1

                if inDegree[v] == 0:
                    queue.append(v)

        order.reverse()
        return order if len(order) == num_courses else []

    def findOrder(self, num_courses: int, prerequisites: List[List[int]]) -> List[int]:
        adj_list, inDegree = self.build_graph(num_courses, prerequisites)
        return self.topological_sort(num_courses, adj_list, inDegree)





def test(num_courses:int, prerequisites, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["num_courses", "prerequisites", "Expected"], True, num_courses,prerequisites, expected)
    pass_test, final_pass = True, True
    output = Solution_KhanTopo().findOrder(num_courses, prerequisites)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Khan-Topo", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution().findOrder(num_courses, prerequisites)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(2, [[1,0]], [0,1]),
                   test(2, [[1,0],[0,1]], []),
                   test(4, [[1,0],[2,0],[3,1],[3,2]], [0,2,1,3]),
                   test(1, [], [0])]

    CommonMethods.print_all_test_results(tests)
