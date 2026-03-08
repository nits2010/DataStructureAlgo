"""
Author: Nitin Gupta
Date: 08/03/2026
Question Title: 1615. Maximal Network Rank
Link: https://leetcode.com/problems/maximal-network-rank/description/
Description: There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

 

Example 1:



Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
Output: 4
Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
Example 2:



Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
Output: 5
Explanation: There are 5 roads that are connected to cities 1 or 2.
Example 3:

Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
Output: 5
Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected.
 

Constraints:

2 <= n <= 100
0 <= roads.length <= n * (n - 1) / 2
roads[i].length == 2
0 <= ai, bi <= n-1
ai != bi
Each pair of cities has at most one road connecting them.

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----@
@medium
@Graph


<p><p>
Company Tags
-----
@Microsoft
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import defaultdict, deque
import heapq
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


"""
Time/Space: E = len(roads), N = no. of cities
1. Compute degree:
    Time: O(E)
    Space: O(N+E)
2. Get Max degree and second max degree and its nodes
    Time: O(N)
    Space: O(N)
3. Case 1: multiple max-degree nodes
    Time: O(E) ; for checking if any pair is connected
    Space: O(1)
4. Case 2: single max-degree node
    Time: O(N - 1) ; for checking if any pair is connected from S1 to S2
    Space: O(1)

Time: O(E + N)
Space: O(N+E) ; degree array and shared_roads map for worst case E = N*(N-1)/2
"""

class Solution:
    def compute_degree(self, n, roads):
        degree = [0] * (n + 1)
        shared_roads = defaultdict(int)  # (city1, city2) -> shared_road

        for c1, c2 in roads:
            degree[c1] += 1
            degree[c2] += 1

            shared_roads[(min(c1, c2), max(c1, c2))] += 1

        return degree, shared_roads

    def maximalNetworkRank(self, n: int, roads: List[List[int]]) -> int:
        if not roads:
            return 0

        degree, shared_roads = self.compute_degree(n, roads)

        """ 
        Maximal rank of road will only be formed by those city which has highest degree. 
        Let d1 = first highest degree
            S1 = nodes with d1 degree
            d2 = second highest degree
            S2 = nodes with d2 degree
        
        its for sure that maximal rank will be formed by S1 + S2 node only such data (d1+d2) 

        However, we need to make sure that they are not connected to each other, otherwise we need to remove it. 

        So if 
        |S1| > 1 : Means there are multiple nodes with d1 degree. 
            In such case, the maximal rank = 2 * d1 only if no nodes in S1 are connected
            otherwise rank = 2*d1 - 1 (remove shared one)

        |S1| = 1 : Means there is only one node in S1, we need to take other node from S2 then such that both are not connected. 
            In such case, the maximal rank  = d1 + d2 if S1 node is not connected with S2 nodes
            otherwise rank = d1 + d2 - 1 (removed shared one)

        """

        # get highest degree and its nodes
        # d1, d2 = 0,0
        # s1, s2 = [], []

        # for d in degree:
        #     if d > d1:
        #         d1 = d
        #     if d > d2:
        #         d2 = d

        # for i in range(1,n+1):
        #     if degree[i] == d1:
        #         s1.append(i)
        #     if degree[i] == d2:
        #         s2.append(i)

        d1 = max(degree)
        d2 = max(d for d in degree if d != d1) if any(
            d != d1 for d in degree) else -1

        s1 = [i for i in range(n) if degree[i] == d1]
        s2 = [i for i in range(n) if degree[i] == d2]

        # Case 1: multiple max-degree nodes
        if len(s1) > 1:
            # find if any pairs connected to each other
            m = len(s1)
            total_pairs = m * (m - 1) // 2

            connected_pairs = 0
            for u, v in roads:
                if degree[u] == d1 and degree[v] == d1:
                    connected_pairs += 1

            if connected_pairs < total_pairs:
                return 2 * d1
            else:
                return 2 * d1 - 1

        # means S1 has only 1 node
        u = s1[0]

        for v in s2:
            pair = (min(u, v), max(u, v))
            if pair not in shared_roads:
                return d1 + d2
        return d1 + d2 - 1


"""
Time/Space: 
1. Compute degree: 
    Time: O(E)
    Space: O(N+E) 
2. Get max network rank using degree
    Time: O(N^2)
    Space: O(1)

Time: O(E + N^2)
Space: O(N+E)

since N <= 100 this will pass. 

"""


class Solution_Polynomial:
    def compute_degree(self, n, roads):
        degree = [0] * (n + 1)
        shared_roads = defaultdict(int)  # (city1, city2) -> shared_road

        for c1, c2 in roads:
            degree[c1] += 1
            degree[c2] += 1

            shared_roads[(min(c1, c2), max(c1, c2))] += 1

        return degree, shared_roads

    def maximalNetworkRank(self, n: int, roads: List[List[int]]) -> int:
        if not roads:
            return 0

        degree, shared_roads = self.compute_degree(n, roads)

        # get all pairs network rank and get the maximum out of it.
        # remove the shared road if any b/w pair
        maximum_network_rank = 0
        for u in range(n):
            for v in range(u + 1, n):
                rank = degree[u] + degree[v]
                pair = (min(u, v), max(u, v))
                shared_road = shared_roads[pair]
                rank = rank - shared_road
                maximum_network_rank = max(maximum_network_rank, rank)

        return maximum_network_rank


def test(n, roads, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["N", "Roads", "Expected"], True, n, roads, expected)
    pass_test, final_pass = True, True
    output = Solution_Polynomial().maximalNetworkRank(n=n, roads=roads)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    
    output = Solution().maximalNetworkRank(n=n, roads=roads)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(n=4, roads=[[0, 1], [0, 3], [1, 2], [1, 3]] , expected=4),
                   test(n=5, roads=[[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]], expected=5),
                   test(n=8, roads=[[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]], expected=5)]

    CommonMethods.print_all_test_results(tests)
