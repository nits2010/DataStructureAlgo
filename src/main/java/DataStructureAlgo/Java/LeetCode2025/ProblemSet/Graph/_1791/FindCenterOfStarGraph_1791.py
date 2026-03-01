from typing import List


class Solution:
    def findCenter(self, edges: List[List[int]]) -> int:
       first_edge, second_edge = edges[0], edges[1]

       return first_edge[0] if first_edge[0] in second_edge else first_edge[1] 

       