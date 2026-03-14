"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: Find Center Of Star Graph
Link: https://leetcode.com/problems/find-center-of-star-graph/
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

from typing import List


class Solution:
    def findCenter(self, edges: List[List[int]]) -> int:
       first_edge, second_edge = edges[0], edges[1]

       return first_edge[0] if first_edge[0] in second_edge else first_edge[1] 

       