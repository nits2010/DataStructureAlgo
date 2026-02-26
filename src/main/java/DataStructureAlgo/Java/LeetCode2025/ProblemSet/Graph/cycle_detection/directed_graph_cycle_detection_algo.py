
#   Cycle detection logic 
#   DFS + BFS 
from collections import deque
from typing import List

class DirectedGraphCycleDetection:

    class Solution_DFSV2 : # directed DFS ; Cycle detection logic 
        """
        The reason we use state 1 is to identify the Back Edge. In a directed graph, a cycle only exists if you point back to an ancestor in the current DFS path. 
        Pointing to a node with state 2 is just a Cross Edge, which is perfectly fine and does not indicate a cycle.
        
        Visualizing the Three States
        To ensure the logic is clear, here is how those three numbers represent the "color" of the nodes during the search:

        0 (White): Unvisited.

        1 (Gray): Visiting (currently in the recursion stack). If we hit a node in this state, we've found a Back Edge.

        2 (Black): Fully processed (no cycles found in this sub-tree).
        """
        def build_graph(self, n: int, edges: List[List[int]]) -> (List[List[int]], list):
            adj_list = [[] for _ in range(n)]
        

            for ai, bi in edges:
                adj_list[ai].append(bi)

            return adj_list
        
        def cycle_detect(self, n: int, edges: List[List[int]]) -> bool:
            adj_list = self.build_graph(n, edges)
            
            # False -> (White): Unvisited.
            # True -> Visited
            visited = [False] * n
            
            # False -> (White): Not in stack ( not visiting right now).
            # True -> In stack, we are visiting right now
            recu_stack = [False] * n

            def dfs(node):
                visited[node] = True # visited this node
                recu_stack[node] = True # currently visiting, hence in stack
            
                for neigh in adj_list[node]:
                    if not visited[neigh]:
                        if dfs(neigh):
                            return True
                    elif recu_stack[neigh]: # neigh can not be in rec stack, since rec_stack holds only currently visited node which should be parent
                        return True
                
                recu_stack[node] = False # not in stack anymore
                return False
            
            for v in range(n):
                if not visited[v] and dfs(v):
                    return False
            
            return True
    class Solution_DFS : # directed DFS 
        """
        The reason we use state 1 is to identify the Back Edge. In a directed graph, a cycle only exists if you point back to an ancestor in the current DFS path. 
        Pointing to a node with state 2 is just a Cross Edge, which is perfectly fine and does not indicate a cycle.
        
        Visualizing the Three States
        To ensure the logic is clear, here is how those three numbers represent the "color" of the nodes during the search:

        0 (White): Unvisited.

        1 (Gray): Visiting (currently in the recursion stack). If we hit a node in this state, we've found a Back Edge.

        2 (Black): Fully processed (no cycles found in this sub-tree).
        """
        def build_graph(self, n: int, edges: List[List[int]]) -> (List[List[int]], list):
            adj_list = [[] for _ in range(n)]
        

            for ai, bi in edges:
                adj_list[ai].append(bi)

            return adj_list
        
        def canFinish(self, n: int, edges: List[List[int]]) -> bool:
            adj_list = self.build_graph(n, edges)
            
            # 0 -> (White): Unvisited.
            # 1 -> (Gray): Visiting (currently in the recursion stack). If we hit a node in this state, we've found a Back Edge.
            # 2 -> (Black): Fully processed (no cycles found in this sub-tree).
            visited = [0] * n

            def dfs(node):
                if visited[node] == 1:
                    # we had already visited this node and it appears again, hence cycle
                    return True

                if visited[node] == 2:
                    # course already completed
                    return False

                visited[node] = 1 # visited the non-visited node
                
                for neigh in adj_list[node]:
                    if dfs(neigh):
                        return True
                
                visited[node] = 2 # completed
                return False

            for v in range(n):
                if visited[v] != 2 and dfs(v):
                    return False
            
            return True


    class Solution_BFS: # Khan Algo for cycle detection
        def build_graph(self, n: int, edges: List[List[int]]) -> (List[List[int]], list):
            adj_list = [[] for _ in range(n)]
            inDegree = [0] * n

            for ai, bi in edges:
                adj_list[ai].append(bi)
                inDegree[bi] += 1

            return adj_list, inDegree

        def topological_sort(self, n: int, adj_list: List[List[int]], inDegree: list[int]) -> int:

            # apply khan's algo for topological sort
            queue = deque()

            # queue all 0 indegree edges
            for node in range(n):
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

        def canFinish(self, n: int, edges: List[List[int]]) -> bool:
            adj_list, inDegree = self.build_graph(n, edges)
            return self.topological_sort(n, adj_list, inDegree) == n
