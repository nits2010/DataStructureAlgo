"""
Author: Nitin Gupta
Date: 9/4/2025
Question Title: 332. Reconstruct Itinerary
Link: https://leetcode.com/problems/reconstruct-itinerary/description/
Description: You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.



Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.


Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
File reference
-----------
Duplicate {@link ReconstructItinerary.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@DepthFirstSearch
@Graph
@EulerianCircuit
<p><p>
Company Tags
-----
@Uber
@Facebook
@Google
@Amazon
@Twitter
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque, defaultdict
from typing import List, Optional, Dict, Any
import heapq
from helpers.common_methods import CommonMethods


class Solution:

    def build_graph(self, tickets) -> Dict[str, List[str]]:
        n = len(tickets)
        graph = defaultdict(list)

        for source, dest in tickets:
            heapq.heappush(graph[source], dest)

        return graph

    def findItinerary(self, tickets: List[List[str]]) -> List[str]:

        # build graph
        graph = self.build_graph(tickets)
        itinerary = []
        start = "JFK"

        # dfs the graph
        def dfs(current):
            # Visit all the connecting flights, assume [jsk, kul] [ jsk bur], if we go but we can go anywhere else but jsk->kul is left
            while graph[current]:
                dfs(heapq.heappop(graph[current]))

            # the current connecting flight is completed, add it the result
            itinerary.append(current)

        dfs(start)
        return itinerary[::-1]


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().findItinerary(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [
        test([["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]], ["JFK", "MUC", "LHR", "SFO", "SJC"]),
        test([["JFK", "SFO"], ["JFK", "ATL"], ["SFO", "ATL"], ["ATL", "JFK"], ["ATL", "SFO"]],
             ["JFK", "ATL", "JFK", "SFO", "ATL", "SFO"]),
        test([["JFK", "SFO"], ["JFK", "ATL"], ["SFO", "JFK"], ["ATL", "SFO"]], ["JFK", "ATL", "SFO", "JFK", "SFO"]),
        test([["JFK", "SFO"], ["JFK", "ATL"], ["SFO", "JFK"], ["ATL", "AAA"], ["AAA", "ATL"], ["ATL", "BBB"],
              ["BBB", "ATL"], ["ATL", "CCC"], ["CCC", "ATL"], ["ATL", "DDD"], ["DDD", "ATL"], ["ATL", "EEE"],
              ["EEE", "ATL"], ["ATL", "FFF"], ["FFF", "ATL"], ["ATL", "GGG"], ["GGG", "ATL"], ["ATL", "HHH"],
              ["HHH", "ATL"], ["ATL", "III"], ["III", "ATL"], ["ATL", "JJJ"], ["JJJ", "ATL"], ["ATL", "KKK"],
              ["KKK", "ATL"], ["ATL", "LLL"], ["LLL", "ATL"], ["ATL", "MMM"], ["MMM", "ATL"], ["ATL", "NNN"],
              ["NNN", "ATL"]],
             ["JFK", "SFO", "JFK", "ATL", "AAA", "ATL", "BBB", "ATL", "CCC", "ATL", "DDD", "ATL", "EEE", "ATL", "FFF",
              "ATL", "GGG", "ATL", "HHH", "ATL", "III", "ATL", "JJJ", "ATL", "KKK", "ATL", "LLL", "ATL", "MMM", "ATL",
              "NNN", "ATL"])]

    CommonMethods.print_all_test_results(tests)
