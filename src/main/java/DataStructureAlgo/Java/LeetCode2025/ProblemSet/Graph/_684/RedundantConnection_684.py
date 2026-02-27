"""
Author: Nitin Gupta
Date: 26/02/2026
Question Title: 684. Redundant Connection
Link: https://leetcode.com/problems/redundant-connection/description/
Description: In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.

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
@Depth-FirstSearch
@Breadth-FirstSearch
@Union-Find
@GraphTheory

<p><p>
Company Tags
-----
@Google
@Microsoft
@Amazon
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time/Space: O(|V| + |E|) / O(|V| + |E|))
class Solution_BFS:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        """ To be have a cycle in a graph, there will be at least one node which has indegree 2. 
            Now we need to find the last edge or precesily the one which make the graph cycle, we need to process the graph and find a edge that has indegree 2 with a node which has a indegree
            
            The "Peel the Leaves" Strategy - Khan algo
            In an undirected tree, all "leaves" have a degree of 1. If you repeatedly remove all nodes with degree 1, you will eventually remove the entire tree.

            If there is a cycle:

            Nodes in the cycle will always have a degree of at least 2.

            The "peeling" process will get stuck. The nodes remaining in the graph are the ones forming the cycle.
            
            https://www.youtube.com/watch?v=1lNK80tOTfc&t=2s
            
        """
        n = len(edges)
        degree = [0] * (n + 1)
        adj = [[] for _ in range(n + 1)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)
            degree[u] += 1
            degree[v] += 1

        q = deque()
        for i in range(1, n + 1):
            if degree[i] == 1:
                q.append(i)

        while q:
            node = q.popleft()
            degree[node] -= 1
            for nei in adj[node]:
                degree[nei] -= 1
                if degree[nei] == 1:
                    q.append(nei)

        for u, v in reversed(edges):
            if degree[u] > 1 and degree[v] > 1:
                return [u, v]
        return []

# Time/Space: O(|V| + |E|.X) / O(|V|)
class Solution_UnionFind:

    class UnionFind:
        def __init__(self, n):
            # n + 1 because numebering is 1 to n
            self.parent = list(range(n + 1))
            self.size = [1] * (n + 1)

        def find(self, i):
            if self.parent[i] != i:
                self.parent[i] = self.find(self.parent[i])

            return self.parent[i]

        def union(self, i, j):
            root_i = self.find(i)
            root_j = self.find(j)

            if root_i == root_j:
                return False

            size_i, size_j = self.size[root_i], self.size[root_j]

            if size_i > size_j:
                root_i, root_j = root_j, root_i

            self.parent[root_i] = root_j
            self.size[root_j] += self.size[root_i]

            return True

    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        if not edges:
            return []

        n = len(edges)
        union_find = Solution_UnionFind.UnionFind(n)

        for u, v in edges:
            if not union_find.union(u, v):
                return [u, v]

        # return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_UnionFind().findRedundantConnection(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["UnionFind", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_BFS().findRedundantConnection(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[1,2],[1,3],[2,3]], [2,3]),
                   test([[1,2],[2,3],[3,4],[1,4],[1,5]], [1,4]),
                    test([[1,2],[2,3],[3,4],[1,4],[1,5]], [1,4]),]

    CommonMethods.print_all_test_results(tests)
