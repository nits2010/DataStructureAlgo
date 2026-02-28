
"""
Author: Nitin Gupta
Date: 28/02/2026
Question Title: 1584. Min Cost to Connect All Points
Link: https://leetcode.com/problems/min-cost-to-connect-all-points/description/
Description: You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

Example 1:


Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
 

Constraints:

1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link PrimMinimumSpanningTree.java}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@Staff
@Array
@Union-Find
@GraphTheory
@MinimumSpanningTree

<p><p>
Company Tags
-----
@DirectI
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

class Solution_Prims:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        """Here we have multiple nodes V ( number of points) and we need to connect them using simple path.
        This path cost is the sum of manhattan distance b/w two points in the path.

        Connecting a point to a point become an edge b/w two points (nodes)
        And since we can connect any point to any point, that leads E = V^2

        Now, we need to find the simple path connecting all points, essentially we need to find a tree that connects all the nodes.
        This is called Spanning Tree.

        However, we need to minimize the cost of this connections, its called Minimum Spanning Tree (MST)

        Now question reduced to find the MST of given points connected via manhattan distance.
        We have two algorithm to find the MST

        1. Prim’s Algorithm: The "Growing Tree"
            Opt when E ~ V^2 [ Dense Graph ]
        2. Kruskal’s Algorithm: The "Forest Merger"
            Opt when E ~ V [ Sparse Graph]


        In our case, E = V^2 we choose prims algo

        Prim’s Algorithm:
        1. Create a mst set to track which nodes are in mst. Max size V
        2. Assign cost to each node as INFINITE
        3. Assign key value as 0 for the first vertex so that it is picked first.
        4. While MST does not have all the vertex, repeat
            a) Pick a vertex from remaining Verex set based on cost -> minimum cost : PQ(min_heap)
            b) Add this vertex to mst
            c) Update cost of all the adj vertex of picked vertex

        """

        if not points:
            return 0

        v = len(points)

        # Create a mst set to track which nodes are in mst. Max size V
        mst = set()

        # Assign cost to each node as INFINITE
        distance = [float("inf")] * v

        distance[0] = 0  # 0 for the first vertex so that it is picked first.

        min_heap = []  # (cost, node)
        heapq.heappush(min_heap, (0, 0))

        minimum_cost = 0
        while len(mst) < v:
            # Pick a vertex from remaining Verex set based on cost
            cost, node = heapq.heappop(min_heap)

            if node in mst:
                continue

            minimum_cost += cost

            # Add this vertex to mst
            mst.add(node)

            # Update cost of all the adj vertex of picked vertex
            for i in range(v):
                if i != node and i not in mst:
                    point1 = points[node]
                    point2 = points[i]
                    cur_distance = abs(point1[0] - point2[0]) + abs(
                        point1[1] - point2[1]
                    )

                    if cur_distance < distance[i]:
                        distance[i] = cur_distance
                        heapq.heappush(min_heap, (distance[i], i))

        return minimum_cost

def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_Prims().minCostConnectPoints(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[0,0],[2,2],[3,10],[5,2],[7,0]], 20),
                   test([[3,12],[-2,5],[-4,1]], 18)]

    CommonMethods.print_all_test_results(tests)
