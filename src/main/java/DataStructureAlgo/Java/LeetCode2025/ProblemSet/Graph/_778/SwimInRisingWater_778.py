"""
Author: Nitin Gupta
Date: 01/02/2026
Question Title: 778. Swim in Rising Water
Link: https://leetcode.com/problems/swim-in-rising-water/description/
Description: You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.

You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

 

Example 1:


Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:


Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n2
Each value grid[i][j] is unique.
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link DijkstraShortestPath.java}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@Array
@BinarySearch
@Depth-FirstSearch
@Breadth-FirstSearch
@Union-Find
@Heap(PriorityQueue)
@Matrix

<p><p>
Company Tags
-----
@Facebook 
@Google
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
import heapq
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time: O(ElogV) - dijkstra
#       V = n^2 and E = 4V = 4N^2
#       => O(4n^2 * logn^2)
#       => O(n^2 * logn)
# Space : O(n^2) -> visited + heap
class Solution_Dijkstra:
    """
    🔓 Full Solution Summary (With Derivation Flow)
        1️⃣ One-line Optimal Intuition

            Minimize the maximum elevation on the path → treat it as Dijkstra where path cost = running max.

        2️⃣ How We Arrived Here (Senior Flow)

            1. Brute force DFS → exponential o(4^N^2)→ impossible.

            2. Observed we are not minimizing path length or sum.

            3. Cost of path = max value on path.

            4. That cost is monotonic non-decreasing when extending path.

            5. Monotonic property → Dijkstra valid.

            6. Stop when destination is popped (optimal guarantee).

        That’s the key reasoning chain.

        3️⃣ Step-by-Step Algorithm Logic

            Min-heap initialized with (grid[0][0], 0, 0)

            While heap:

            Pop smallest (time, r, c)

            Skip if visited

            Mark visited

            If goal → return time

            Push neighbors with cost = max(time, grid[nr][nc])
    
    """
    def swimInWater(self, grid: List[List[int]]) -> int:
        if not grid:
            return 0
        n = len(grid)
        max_time = grid[0][0]
        heap = []  # min heap (time, (i,j))
        visited = set()

        max_time
        heapq.heappush(heap, (grid[0][0], 0, 0))

        while heap:
            time, i, j = heapq.heappop(heap)

            if (i,j) in visited:
                continue 

            if i == n - 1 and j == n - 1:
                return time

            visited.add((i, j))

            for dr, dc in [[0, 1], [1, 0], [0, -1], [-1, 0]]:
                r, c = i + dr, j + dc
                if 0 <= r < n and 0 <= c < n and (r, c) not in visited:
                    heapq.heappush(heap, (max(time, grid[r][c]), r, c))

        return -1  # we can't reach
            
        
        
# Time: 
#       1. Binary Search : O(log(max))
#               max <= n^2 - 1
#               => O(log(n^2))
#       2. BFS: O(V+E); E = 4V and V = n^2
#               => O(n^2)
#       => O(n^2 log (n^2))
#       => O(n^2 logn)          
# Space : O(n^2) -> visited + queue
class Solution_BinarySearch_BFS:
    def is_possible(self, t, grid):
        """bfs to goal using t"""
        n = len(grid)
        source = (0, 0)
        target = (n - 1, n - 1)

        queue = deque([source])
        visited = set()

        while queue:
            i, j = queue.popleft()

            if (i,j) in visited:
                continue 
            
            visited.add((i,j))

            if (i, j) == target:
                return True

            for dr, dc in [[0, 1], [1, 0], [0, -1], [-1, 0]]:
                r, c = dr + i, dc + j

                if 0 <= r < n and 0 <= c < n and grid[r][c] <= t and (r,c) not in visited:
                    queue.append((r, c))

        return False

    def swimInWater_binary_search(self, grid, low, high):
        if low >= high:
            return low

        time = None
        while low <= high:
            mid = (low + high) // 2

            if self.is_possible(mid, grid):
                high = mid - 1
                time = mid
            else:
                low = mid + 1

        return time

    def swimInWater(self, grid: List[List[int]]) -> int:
        if not grid:
            return 0
        n = len(grid)
       
        low = grid[0][0]
        high =  max(max(item) for item in grid)

        return self.swimInWater_binary_search(grid, low, high)


        
def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
