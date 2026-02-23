# Time / Space: O(m*n)/O(m*n)
from collections import deque
from typing import List

# Time / Space: O(m*n)/O(m*n)
class Solution_bfs:
    """ BFS make sure when find first INF wrt to gate(0) then it always be shortest """
    
    def islandsAndTreasure(self, grid: List[List[int]]) -> None:
        _INF = 2147483647 # 2^31 - 1

        m = len(grid)
        n = len(grid[0])
        _dir = [[0,1], [1,0], [0,-1], [-1,0]]

        def is_safe(i,j):
            return 0 <= i < m and 0 <= j < n and grid[i][j] != -1 

        queue = deque()

        # append all sources ( Treasure )
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    queue.append((i,j))
        
        while queue: 
            gate_row, gate_col = queue.popleft()

            for dr, dc in _dir : 
                neigh_row , neigh_col = dr + gate_row, dc + gate_col

                if is_safe(neigh_row, neigh_col) and grid[neigh_row][neigh_col] == _INF:
                    grid[neigh_row][neigh_col] = 1 + grid[gate_row][gate_col]
                    queue.append((neigh_row, neigh_col))
        
        return 


# Not a good choice
# Time / Space: O(m*n)/O(m*n)
# where H=m*n is the maximum height (or depth) of the tree/graph.
# In a worst-case scenario (a graph that is just one long line), this is O(V = m*n).

class Solution_DFS:
    """ DFS can not make sure that when you find first INF wrt to gate(0) then it always be shortest. Hence it will revisit the multiple times """
    """ However, to avoid visiting multiple times, distance <= grid[i][j] does some optimization but in worst-case it won't """

    def islandsAndTreasure(self, grid: List[List[int]]) -> None:
        
        _INF = 2147483647 # 2^31 - 1

        m = len(grid)
        n = len(grid[0])
        _dir = [[0,1], [1,0], [0,-1], [-1,0]]

        def is_safe(i,j, distance):
            return 0 <= i < m and 0 <= j < n and grid[i][j] != -1 and distance <= grid[i][j]

        def dfs(i,j, distance):
            if not is_safe(i,j, distance):
                return 
            
            grid[i][j] = distance
            for dr, dc in _dir:
                dfs(dr + i,dc + j, distance+1)
            

        for i in range(m):
            for j in range(n):
                if grid[i][j] == 0:
                    dfs(i,j, 0)
        
        return 
    
