"""
Author: Nitin Gupta
Date: 01/03/2026
Question Title: 787. Cheapest Flights Within K Stops
Link: https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
Description: There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

 

Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 

Constraints:

2 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link DijkstraShortestPath.java , BellmanFordShortestPath.java}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@DynamicProgramming
@Depth-FirstSearch
@Breadth-FirstSearch
@Graph
@Heap(PriorityQueue)
@ShortestPath

<p><p>
Company Tags
-----
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

# Time/Space: V = n and E = len(flights)
#   Queue can have at multiple entries of city up to k times: Space = O(E*K)
#   For each pop operation(E) we perform an append operation that take O(1) time -> Time = O(EK)
#   Build graph takes O(E) time with O(VK) space
# Time = O(E) + O(E*K) = O(E*K)
# Space = O(VK) + O(EK) = O(EK) since E=V+1 
# 
class Solution_BFS:
    """
    We need to reach `dest` from `src` with minimum cost consider only at most `k` nodes visited in b/w, means `allowed_edges = k + 1` edges.
    Minimum Cost = Shortest Path (Here weight = cost)
    1. Dijktra
    2. Bellmon Ford
    3. BFS 

    Since we just saw using Bellmon ford we can explored the stops layered by layered until stops = k+1
    This reminds us to use BFS which also does same level by level. 

    We can explored all city which take 'i' edges to reach. Once we reach 'k+1' edges then if we reached dst then cost will be our solution. 
    In Standard bfs, we maintain state = (cost,node) and once node=dst we return 
    but here, since we can reach `dst` from multiple node, hence we need to update the reaching node cost. 
    cost[i] = minimum cost needeed to reach i th node 
    """
    # Time / Space: O(E) / O(nK)
    def build_graph(self, flights, n):
        if n <= 0 or not flights:
            return {}

        adj_list = defaultdict(set)  # node -> {(dest, price}

        for _from, _to, _price in flights:
            adj_list[_from].add((_to, _price))  # directed weighted graph

        return adj_list

    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, k: int) -> int:

        if not flights or k < 0:
            return -1

        adj_list = self.build_graph(flights, n)

        queue = deque()
        cost = [float('inf')] * n

        queue.append((0, src))
        allowed_edges = k+1
        stopsUsed = 0 
        
        while queue and stopsUsed < allowed_edges: 

            # explore all 
            for _ in range(len(queue)):
                cost_so_far, city = queue.popleft()

                for neigh_city, _price in adj_list[city]:
                    if cost[neigh_city] > cost_so_far + _price:
                        cost[neigh_city] = cost_so_far + _price
                        queue.append((cost[neigh_city], neigh_city))

            stopsUsed += 1 
        
        return -1 if cost[dst] == float('inf') else cost[dst]




# relaxation : k + 1 times
#       -> in each relaxation, we explored E=len(flights) edges
#       -> Using V = n nodes space
#       -> Time: O(k*E)
#       -> Space: O(n)
#
#
class Solution_BellmanFord:
    """
    We need to reach `dest` from `src` with minimum cost consider only at most `k` nodes visited in b/w, means `allowed_edges = k + 1` edges.
    Minimum Cost = Shortest Path (Here weight = cost)
    1. Dijktra
    2. Bellmon Ford

    In Bellmon Ford : We relax edges V-1 times (V = no. of nodes), which means we relax edges layer by layer.
        Bellman-Ford iteration i gives shortest paths cost distance[p] using ≤ i edges.
        Similarly here, we need to allow max K+1 edge only, means if we apply Bellmon ford by allowing relaxation k+1 times, then post that
            we must have consumed K + 1 edges and that is what we need. If we able to reach `dst` using k+1 edge then cost is your answer. 

        Additionally, in Bellmon we update cost matrix in each relaxation like distance[i] > cur_weight + weight[j]
        however here if we do it in place than it could possible that while reaching 'v' node we must have relaxed edge for u->v which updated the distance[v] and in the same round we again do for v->w 
        then while updating distance[w] we have used latest distance[v] which breaks the layer by layer gurentee. 

        Example: 
            current edge u->v and by defination we have gurrented that distance[u] achieved by relaxing at most i-1 edges. 
            now we update distance[v] that implies that we have relaxed u-> v edge by at most 'i` edges which makes { src ... u->v}
            however if in same round v -> w comes then while updating distance[w] we used latest distance[v] that means {src ... u->v->w} which used i+1 edges and it break the contract. 
        
        Hence we do solve this by keeping a copy of previous layer and then once this layer explored we update it.
    """
    def findCheapestPrice(self, n: int, flights: List[List[int]], src: int, dst: int, k: int) -> int:
        
        if not flights or k < 0:
            return -1
        
        if src == dst:
            return 0
        allowed_edges = k + 1 
        cost = [float('inf')] * n  # cost[node] = minimum cost to reach `node` from src 

        cost[src] = 0 # cost of reaching src from src using 0 

        for _ in range(allowed_edges): # k+1 times 

            curr_cost = cost.copy()
            
            for _from, _to, _price in flights:
                # we haven't reached this node yet
                if cost[_from] == float('inf'):
                    continue


                if cost[_from] + _price < curr_cost[_to]: # use <= i-1 edge cost + _price to reach _to become <= i edges  
                    curr_cost[_to] = cost[_from] + _price
            
            cost = curr_cost

        
        return -1 if cost[dst] == float('inf') else cost[dst]


# Time/Space: O(E*K*log(nK)) / O(nK)
class Solution_Dijkstra:
    """
    We need to reach `dest` from `src` with minimum cost consider only at most `k` nodes visited in b/w, means `k + 1` edges.
    Minimum Cost = Shortest Path (weight = cost)
        => Dijkstra
            => This works without k limit, that can be impose additionally to achieve the outcome.
            => Dijkstra states = (costSoFar, node)
                -> here it will be (costSoFar, stopUsed, city)
            => Dijkstra distance[i] = minimum cost of reaching ith node from src    
                -> Here distance[i][stops] = minimum cost of reaching ith node using stops edges 
            => Dijkstra Purning logic: distance[i] > cost
                -> here, stop_used > allowed_edges 
    """

    # Time / Space: O(E) / O(nK)
    def build_graph(self, flights, n):
        if n <= 0 or not flights:
            return {}

        adj_list = defaultdict(set)  # node -> {(dest, price}

        for _from, _to, _price in flights:
            adj_list[_from].add((_to, _price))  # directed weighted graph

        return adj_list

    # Heap -> Heap can have multiple entires for same city(total n=cities). (0->k) hence heap operation : O(log(nK)) 
    # Dijkstra time complexity: O(ElogV) { up to V nodes in heap, operation logV, done for all the edges E.logV -> 
    #                                       opeation = O(log(nK)) and Edge E explored k+1 times } 
    #                           -> O(E.(k+1)*log(nK)) -> O(E*K*log(nK))
    # Space: O(nK)
    def shortest_minimum_cost_path(self, adj_list, src, dst, k, n):
        allowed_edges = k + 1
        heap = [] # (costSoFar, stopUsed, city)
        cost = [[float('inf')] *(allowed_edges+1) for _ in range(n)] # cost[node][stops] = minimum cost to reach `node` from src using exactly `stops` edges

        cost[src][0] = 0 # cost of reaching src from src using 0 stops is 0
        
        heapq.heappush(heap, (0, 0, src))

        while heap:

            cost_so_far, stop_used, city = heapq.heappop(heap)

            # prune the city with stop
            if stop_used > allowed_edges :
                continue
            
            if city == dst:
                return cost_so_far
            
            for neigh_city , _price in adj_list[city]:
                new_cost = cost_so_far + _price
                new_stops = stop_used + 1

                if new_stops <= allowed_edges and cost[neigh_city][new_stops] > new_cost:
                    cost[neigh_city][new_stops] = new_cost
                    heapq.heappush(heap, (new_cost, new_stops, neigh_city))
        
        return -1 





    def findCheapestPrice(
        self, n: int, flights: List[List[int]], src: int, dst: int, k: int
    ) -> int:
        if not flights:
            return -1

        # if src and dst are same, no cost needed
        if src == dst:
            return 0

        # since src!=dst and n = 2 means there are two city only. Hence not possible
        if n == 2:
            return -1

        adj_list = self.build_graph(flights, n)
        return self.shortest_minimum_cost_path(adj_list, src, dst, k, n)



def test(n, flights, src, dst, k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["n", "flights", "src", "dst", "k", "Expected"], True, n, flights, src, dst, k, expected)
    pass_test, final_pass = True, True
    output = None

    output = Solution_BFS().findCheapestPrice(n, flights, src, dst, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_BellmanFord().findCheapestPrice(n, flights, src, dst, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BellmanFord", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_Dijkstra().findCheapestPrice(n, flights, src, dst, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Dijkstra", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1, expected = 700),
                   test(n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1, expected = 200),
                   test(n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0, expected = 500),
                   test(n = 3, flights = [[1,0,100],[1,2,200],[0,2,100]], src = 1, dst = 2, k = 1, expected = 200),
                   test(n = 4, flights = [[0,1,200],[1,2,100],[1,3,300],[2,3,100]], src = 0, dst = 3, k = 1, expected = 500),
                   ]
    

    CommonMethods.print_all_test_results(tests)
