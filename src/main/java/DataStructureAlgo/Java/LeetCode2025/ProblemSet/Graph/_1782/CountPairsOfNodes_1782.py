"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: Count Pairs Of Nodes
Link: https://leetcode.com/problems/count-pairs-of-nodes/
Description:
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import defaultdict
from typing import List


class Solution:

    def compute_degree(self, n, edges) -> (list, dict):
        degree = [0] * (n+1)
        common_pair_freq = defaultdict(int)  # (a,b) -> freq

        for u, v in edges:
            degree[u] += 1
            degree[v] += 1

            pair = (min(u, v), max(u, v))
            common_pair_freq[pair] += 1

        return degree, common_pair_freq

    def get_incidents(self, degree, target):
        left, right = 0, len(degree) - 1
        incidents = 0
        while left < right:
            incident = degree[left] + degree[right]
            if incident > target:
                # means all the pairs formed b/w [left,right] will be greater than target
                incidents += right - left
                right -= 1
            else:
                left += 1

        return incidents

    def fix_incidents(self, incidents, degree, freq_map, target):
        """Remove all the common edge counted in incidents"""

        for (u, v), freq in freq_map.items():

            # common edge
            if degree[u] + degree[v] - freq <= target < degree[u] + degree[v]:
                incidents -= 1

        return incidents

    def countPairs(
        self, n: int, edges: List[List[int]], queries: List[int]
    ) -> List[int]:
        if not edges or not queries:
            return []

        degree, common_pair_freq = self.compute_degree(n, edges)

        # sort the degree ascending order O(nlogn)
        sorted_degree = sorted(degree[1:])
        result = []

        # get all the pair (a,b) where degree[a] + degree[b] > q[i]
        for q in queries:
            incidents = self.get_incidents(sorted_degree, q)
            result.append(self.fix_incidents(
                incidents, degree, common_pair_freq, q))

        return result
