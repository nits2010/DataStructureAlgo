"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 323. Number of Connected Components in an Undirected Graph 🔒
Link: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0323.Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph/README_EN.md
Description: You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

 

Example 1:



Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
Example 2:



Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
 

Constraints:

1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.

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
@UnionFind
@Graph
@LeetCodeLockedProblem
@PremiumQuestion 



<p><p>
Company Tags
-----
@Amazon 
@Facebook 
@Google 
@LinkedIn 
@Microsoft 
@Twitter
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


# Time/Space: O(|V| + |E|.X) / O(|V|)
#  Where X is time taken in find/union operation ; is the Inverse Ackermann function (nearly constant).
class Solution_UnionFind:
    """
        The Island Analogy
        Start: You have n separate islands. 
        At the beginning, the number of "connected groups" is exactly n.
        Action: Every time you build a bridge between two islands that were not already connected, you have successfully merged two separate groups into one.
        Result: Because two groups became one, your total count of separate groups decreases by 1.

    """

    class DSU :
        def __init__(self,n):
            self.parent = list(range(n))
            self.size = [1] * n
        
        def find(self, i):
            if self.parent[i] != i:
                self.parent[i] = self.find(self.parent[i])
            
            return self.parent[i]
        

        def union(self, i,j):
            root_i = self.find(i)
            root_j = self.find(j)

            if root_i == root_j:
                return False
            
            size_i = self.size[root_i]
            size_j = self.size[root_j]

            if size_i <= size_j :
                self.parent[root_j] = root_i
                self.size[root_i] += self.size[root_j]
            elif size_j < size_i:
                self.parent[root_i] =root_j
                self.size[root_j] += self.size[root_i]
            
            return True


    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        """ connected Components, when you dfs then you visits all the node within the Components. Otherwise you need multiple dfs from multiple node """

        if not edges:
            return 0

        union_find = Solution_UnionFind.DSU(n)
        connected_components = n 
        for u,v in edges:
            if union_find.union(u,v):
                connected_components -= 1
        
        return connected_components



# Time/Space: O(|V| + |E|) / O(|V| + |E|)
class Solution_bfs:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        """ connected Components, when you dfs then you visits all the node within the Components. Otherwise you need multiple dfs from multiple node """

        if not edges:
            return 0

        adj = [[]*n for _ in range(n)]

        for u,v in edges:
            adj[u].append(v)
            adj[v].append(u)
        
        visit = set()
        connected_components = 0
        queue = deque()

        for i in range(n):
            if i in visit:
                continue
            connected_components +=1
            visit.add(i)

            # bfs
            queue.append(i)
            while queue: 
                node = queue.popleft()
                for neigh in adj[node]:
                    if neigh not in visit:
                        queue.append(neigh)
                        visit.add(neigh)


        return connected_components


# Time/Space: O(|V| + |E|) / O(|V| + |E|)
class Solution_dfsV2:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        """ connected Components, when you dfs then you visits all the node within the Components. Otherwise you need multiple dfs from multiple node """

        if not edges:
            return 0

        adj = [[]*n for _ in range(n)]

        for u,v in edges:
            adj[u].append(v)
            adj[v].append(u)
        
        visit = set()
        connected_components = 0
        
        def dfs(i):
            if i in visit:
                return 0
            
            visit.add(i)

            for neigh in adj[i]:
                dfs(neigh)
            
            return 1
                
        for i in range(n):
            connected_components += dfs(i)
        

        return connected_components

# Time/Space: O(|V| + |E|) / O(|V| + |E|)
class Solution_dfs:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        """ connected Components, when you dfs then you visits all the node within the Components. Otherwise you need multiple dfs from multiple node """

        if not edges:
            return 0

        adj = [[]*n for _ in range(n)]

        for u,v in edges:
            adj[u].append(v)
            adj[v].append(u)
        
        visit = set()
        connected_components = 0
        
        def dfs(i):
            if i in visit:
                return 
            
            visit.add(i)

            for neigh in adj[i]:
                dfs(neigh)
                
        for i in range(n):
            if i not in visit:
                connected_components +=1
                dfs(i)
        

        return connected_components
    
def test(n, edges, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["N", "Edges", "Expected"], True, n, edges, expected)
    pass_test, final_pass = True, True
    output = Solution_dfs().countComponents(n, edges)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["dfs", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_dfsV2().countComponents(n, edges)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["dfsV2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_bfs().countComponents(n, edges)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["bfs", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    
    output = Solution_UnionFind().countComponents(n, edges)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["UnionFind", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(5, [[0,1],[1,2],[3,4]], 2),
                   test(5,  [[0,1],[1,2],[2,3],[3,4]], 1)]

    CommonMethods.print_all_test_results(tests)
