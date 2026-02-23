# Time / Space : O(n*m) / O(m*n)
from collections import deque
from typing import List


class Solution_DSU_Optimized:
    """Union find
    In a grid traversal starting from the top-left, you only actually need to check Right and Down. 
    Since the cells to the Left and Top have already been processed, Union-Find will naturally connect them. This cuts your inner loop operations in half!
    """

    class UnionFind:
        def __init__(self, n):
            self.parent = list(range(n))
            # we use union by size as we need to find the max_area which is nothing but size of 1's
            self.size = [1] * n
            self.max_size = 1

        def find(self, i) -> int:
            if self.parent[i] != i:
                self.parent[i] = self.find(self.parent[i])
            return self.parent[i]

        def union(self, i, j) -> bool:
            root_i = self.find(i)
            root_j = self.find(j)

            if root_i == root_j:
                return False

            size_i = self.size[root_i]
            size_j = self.size[root_j]

            if size_i >= size_j:
                self.parent[root_j] = root_i
                self.size[root_i] += self.size[root_j]
            else:
                self.parent[root_i] = root_j
                self.size[root_j] += self.size[root_i]

            self.max_size = max(self.max_size, self.size[root_i], self.size[root_j])

            return True

        def set_size(self):
            return self.max_size

    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])

        union_find = Solution.UnionFind(m * n)  # 2d to 1D
        max_area = 0

        # In a grid traversal starting from the top-left, you only actually need to check Right and Down. Since the cells to the Left and Top have already been processed, Union-Find will naturally connect them. This cuts your inner loop operations in half!
        direction = [[0, 1], [1, 0]]

        def _index(i, j):
            return i * n + j

        land_found = False
        max_size = 1
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    land_found = True
                    root = _index(i, j)
                    # run 4 dir

                    for dr, dc in direction:
                        r, c = dr + i, dc + j
                        if 0 <= r < m and 0 <= c < n and grid[r][c] == 1:
                            child = _index(r, c)
                            union_find.union(root, child)

        if land_found:
            return union_find.set_size()
        return 0


# Time / Space : O(n*m) / O(m*n)
class Solution_DSU:
    """Union find"""

    class UnionFind:
        def __init__(self, n):
            self.parent = list(range(n))
            # we use union by size as we need to find the max_area which is nothing but size of 1's
            self.size = [1] * n
            self.max_size = 1

        def find(self, i) -> int:
            if self.parent[i] == i:
                return i
            self.parent[i] = self.find(self.parent[i])
            return self.parent[i]

        def union(self, i, j) -> bool:
            root_i = self.find(i)
            root_j = self.find(j)

            if root_i == root_j:
                return False

            size_i = self.size[root_i]
            size_j = self.size[root_j]

            if size_i >= size_j:
                self.parent[root_j] = root_i
                self.size[root_i] += self.size[root_j]
            else:
                self.parent[root_i] = root_j
                self.size[root_j] += self.size[root_i]

            self.max_size = max(self.max_size, self.size[root_i], self.size[root_j])

            return True

        def set_size(self):
            return self.max_size

    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])

        union_find = Solution.UnionFind(m * n)  # 2d to 1D
        max_area = 0
        direction = [[1, 0], [0, 1], [-1, 0], [0, -1]]

        def _index(i, j):
            return i * n + j

        land_found = False
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    land_found = True
                    root = _index(i, j)
                    # run 4 dir
                    for dr, dc in direction:
                        r, c = dr + i, dc + j
                        if 0 <= r < m and 0 <= c < n and grid[r][c] == 1:
                            child = _index(r, c)
                            union_find.union(root, child)

        if land_found:
            return union_find.set_size()
        return 0


# Time / Space : O(n*m) / O(1)
class Solution_bfs:
    """ bfs """
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        queue = deque()
        m = len(grid)
        n = len(grid[0])
        max_area = 0
        direction = [[1,0], [0,1], [-1,0], [0,-1]]

        def bfs(i,j):
            queue.append((i,j))
            area = 1
            grid[i][j] = 0

            while queue:
                i,j = queue.popleft()
               
                for dir_i, dir_j in direction:
                    r , c = dir_i + i , dir_j + j
                    if 0 <= r < m and 0 <= c < n and grid[r][c] == 1:
                        queue.append((r,c))
                        area +=1 
                        grid[r][c] = 0
            
            return area


        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    max_area = max(max_area, bfs(i,j))
        
        return max_area

# Time / Space : O(n*m) / O(1)
class Solution_dfs:
    """ dfs """ 
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        max_area = 0

        def is_safe(i,j):
            return 0<=i<m and 0<=j<n and grid[i][j] == 1

        def dfs(i,j) -> int:

            if not is_safe(i,j):
                return 0
            
            grid[i][j] = 0

            return 1 + dfs(i+1,j) + dfs(i-1,j) + dfs(i, j+1) + dfs(i, j-1)
        
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    max_area = max(max_area, dfs(i,j))
                    if max_area == m * n:
                        return max_area
        
        return max_area