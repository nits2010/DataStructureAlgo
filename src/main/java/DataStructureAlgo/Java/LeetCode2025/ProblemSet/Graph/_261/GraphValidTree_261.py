"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 261. Graph Valid Tree 🔒
Link: https://leetcode.com/problems/graph-valid-tree/description/
https://github.com/nits2010/leetcode-all-problem/blob/main/solution/0200-0299/0261.Graph%20Valid%20Tree/README_EN.md
Description: You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.

 

Example 1:



Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:



Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false
 

Constraints:

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link TopologicalSorts.java}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Depth-FirstSearch
@Breadth-FirstSearch
@UnionFind
@Graph
@LeetCodeLockedProblem
@PremiumQuestion 

<p><p>
Company Tags
-----
@Adobe 
@Amazon 
@Facebook 
@Google 
@LinkedIn 
@Pinterest 
@Salesforce 
@Zenefits
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time/ Space : O(|V| + |E|.X) / O(|V|)
# Where X is time taken in find/union operation ; is the Inverse Ackermann function (nearly constant).
class Solution_UnionFind:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        """ union find, if 2 node are in same set before adding the edge, then it has cycle """
        if len(edges) != n-1:
            return False

        parent = list(range(n))

        def find(i):
            if parent[i] != i:
                parent[i] = find(parent[i]) # path compression
            return parent[i]
        
        for u,v in edges: 
            parent_u = find(u)
            parent_v = find(v)

            #if they are alredy in same set
            if parent_u == parent_v:
                return False # has cycle
            
            parent[parent_u] = parent_v # union, put in same set 
        
        return True


# Time/ Space : O(|V| + |E|) / O(|V| + |E|)
class Solution_dfs:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        """ A graph has |V| nodes and |E| edges, the relation is
             |E| >= |V| -> Means Graph has at least 1 cycle 
             otherwse it's
             |E| = |V| - 1

             Hence, if we try to build the above graph and it has cycle, then its not a valid tree
         """

        # |E| = |V| - 1
        if len(edges) != n -1 : 
            return False
        
        adj_list = [[]*n for _ in range(n)]
        
        # build undirected graph
        for u,v in edges:
            adj_list[u].append(v)
            adj_list[v].append(u)
        
        visited = set()
        
        # topological sort
        def dfs(i):
            if i in visited:
                return 
            
            visited.add(i)

            for neigh in adj_list[i]:
                if neigh not in visited:
                    dfs(neigh)
        

        dfs(0)
        return len(visited) == n



def test(n, edges, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Nodes", "Edges", "Expected"], True, n, edges, expected)
    pass_test, final_pass = True, True
    output = Solution_dfs().validTree(n=n, edges=edges)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(5,  [[0, 1], [0, 2], [0, 3], [1, 4]], True),
                   test(5, [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], False)]

    CommonMethods.print_all_test_results(tests)
