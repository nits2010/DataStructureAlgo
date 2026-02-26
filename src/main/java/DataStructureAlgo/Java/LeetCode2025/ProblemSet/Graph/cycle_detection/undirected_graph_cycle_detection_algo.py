
#   Cycle detection logic 
#   DFS + BFS 
from collections import namedtuple, deque
from typing import List

class UnDirectedGraphCycleDetection:

   class DFS:
        def build_graph(self, v: int, edges: List[List[int]]) -> (List[List[int]], list):
            adj_list = [[] for _ in range(v)]
        

            for ai, bi in edges:
                adj_list[ai].append(bi)
                adj_list[bi].append(ai)

            return adj_list
        
        def cycle_detect(self, n:int, edges: List[List[int]]) -> bool:
            if not edges or not n :
                return False

            adj_list = self.build_graph(n, edges)

            # False -> unvisited
            # True -> visited
            visited = [False] * n
            
            def dfs(node, parent):
                visited[node] = True
                
                for neigh in adj_list[node]:
                    # Case 1: If neighbor is not visited, recurse on it
                    if not visited[neigh] :
                        if dfs(neigh, node):
                            return True
                    # Case 2: If neighbor IS visited and is NOT the parent, 
                    # we found a back-edge (cycle).
                    elif neigh != parent:
                        return True
            
            for node in range(n):
                if not visited[node] and dfs(node, -1):
                    return True
            
            return False
   
   class BFS:
        def build_graph(self, v: int, edges: List[List[int]]) -> (List[List[int]], list):
            adj_list = [[] for _ in range(v)]
        

            for ai, bi in edges:
                adj_list[ai].append(bi)
                adj_list[bi].append(ai)

            return adj_list
        
        def cycle_detect(self, n:int, edges: List[List[int]]) -> bool:
            if not edges or not n :
                return False

            adj_list = self.build_graph(n, edges)

            # False -> unvisited
            # True -> visited
            visited = [False] * n
            
            def bfs(node, parent):
                queue = deque()
                queue.append((node,parent))
                
                visited[node] = True
                
                while queue:
                    node, parent = queue.popleft()
                    
                    for neigh in adj_list[node]:
                        if not visited[neigh]:
                            visited[neigh] = True
                            queue.append((neigh, node))
                        elif neigh != parent:
                            return True
                
                return False

            
            for v in range(n):
                if not visited[v] and bfs(v, -1):
                    return True
            
            return False
            
   class Solution_UnionFind:

    class UnionFindByRank:
        def __init__(self, n):
            # n + 1 because numebering is 1 to n
            self.parent = list(range(n + 1))
            self.rank = [1] * (n + 1)

        def find(self, i):
            if self.parent[i] != i:
                self.parent[i] = self.find(self.parent[i])

            return self.parent[i]

        def union(self, i, j):
            root_i = self.find(i)
            root_j = self.find(j)

            if root_i == root_j:
                return False

            rank_i, rank_j = self.rank[root_i], self.rank[root_j]

            if rank_i > rank_j: 
                self.parent[root_j] = root_i
            elif rank_j > rank_i:
                self.parent[root_i] = root_j
            else:
                self.parent[root_j] = root_i
                self.rank[root_i] +=1 

            return True
        
    class UnionFindBySize:
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

    def cycle_detect(self, n:int, edges: List[List[int]]) -> List[int]:
        if not edges:
            return []

        union_find = UnDirectedGraphCycleDetection.Solution_UnionFind.UnionFindBySize(n)

        for u, v in edges:
            if not union_find.union(u, v):
                return [u, v]
   
   
   
   
    