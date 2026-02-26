"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 417. Pacific Atlantic Water Flow
Link: https://leetcode.com/problems/pacific-atlantic-water-flow/description/
Description: There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

 

Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 

Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 105

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
@Array
@Depth-FirstSearch
@Breadth-FirstSearch
@Matrix

<p><p>
Company Tags
-----
@Google
@Uber
@Amazon
@Salesforce
@Bloomberg 
@Facebook 
@Microsoft 
@Oracle
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time/Space: O(m*n) / O(m*n) 
class Solution_dfs:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        """dfs
        Idea is to flow the water from the pecific / atlantic heights and let it reach to the other ocean. 
        For any visited ocean (via pecific or atlantic) we record them in their own set. 
        A cell can reach an ocean if water can flow from that cell to the ocean (downhill/flat).


        Throw water from neighbour cell and see will reach pecific or atlantic
        """

        m = len(heights)
        n = len(heights[0])

        # hold (i,j) pair for each ocean
        set_pecific = set()
        set_atlantic = set()

        result = []

        _dir = [[0, 1], [1, 0], [-1, 0], [0, -1]]

        # A cell can reach an ocean if water can flow from that cell to the ocean (downhill/flat).
        def is_safe(i, j, height):
            # means from this neighbour, does water can flow to prevHeight cell ?
            return 0 <= i < m and 0 <= j < n and heights[i][j] >= height

        def dfs(i, j, ocean_visit, height):
            if not is_safe(i, j, height):
                return

            if (i, j) in ocean_visit:
                return

            # rain water will reach to this ocean from i,j
            ocean_visit.add((i, j))

            for dr, dc in _dir:
                dfs(dr + i, dc + j, ocean_visit, heights[i][j])

        for i in range(m):
            # Rain water to pecific - top to bottom left
            dfs(i, 0, set_pecific, heights[i][0])

            # Rain water to atlantic - top to bottom right
            dfs(i, n - 1, set_atlantic, heights[i][n - 1])

        for j in range(n):
            # Rain water to pecific - left to right top
            dfs(0, j, set_pecific, heights[0][j])

            # Rain water to atlantic left to right bottom
            dfs(m - 1, j, set_atlantic, heights[m - 1][j])

        # any pair which are there in both set is your response
        for i in range(m):
            for j in range(n):
                if (i, j) in set_pecific and (i, j) in set_atlantic:
                    result.append([i, j])

        return result

# Time/Space: O(m*n) / O(m*n) 
class Solution_bfs:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        """ bfs
            Just like we ran two dfs for pecific and atlantic
            we will run two bfs here
        """
        m = len(heights)
        n = len(heights[0])

        # hold (i,j) pair for each ocean
        set_pecific = set()
        set_atlantic = set()

        result = []

        _dir = [[0, 1], [1, 0], [-1, 0], [0, -1]]

        def is_safe(i, j):
            return 0 <= i < m and 0 <= j < n

        def bfs(queue, ocean):
            while queue:
                i, j = queue.popleft()
                # rain water will reach to this ocean from i,j
                ocean.add((i, j))

                for dr, dc in _dir:
                    r, c = dr + i, dc + j
                    # means from this neighbour, does water can flow to prevHeight cell ?
                    if is_safe(r, c) and heights[i][j] <= heights[r][c] and (r, c) not in ocean:
                        queue.append((dr+i, dc+j))

        queue_pecific = deque()
        queue_atlantic = deque()

        # enqueue all pecific and atlantic
        for i in range(m):
            queue_pecific.append((i, 0))
            queue_atlantic.append((i, n-1))

        for j in range(n):
            queue_pecific.append((0, j))
            queue_atlantic.append((m-1, j))

        bfs(queue_pecific, set_pecific)
        bfs(queue_atlantic, set_atlantic)

        # any pair which are there in both set is your response
        for i in range(m):
            for j in range(n):
                if (i, j) in set_pecific and (i, j) in set_atlantic:
                    result.append([i, j])

        return result


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_bfs().pacificAtlantic(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_dfs().pacificAtlantic(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DFS", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([
        [4, 2, 7, 3, 4],
        [7, 4, 6, 4, 7],
        [6, 3, 5, 3, 6]
    ], [[0, 2], [0, 4], [1, 0], [1, 1], [1, 2], [1, 3], [1, 4], [2, 0]]),
        test([[1], [1]], [[0, 0], [1, 0]])]

    CommonMethods.print_all_test_results(tests)
