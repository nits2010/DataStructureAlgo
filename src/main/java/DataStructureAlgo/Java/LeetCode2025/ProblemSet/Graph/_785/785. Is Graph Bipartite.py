
from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution_BFS:
    def is_bipartite(self, graph: List[List[int]], node: int, color: int, colors: list, queue) -> bool:
        colors[node] = 1
        queue.append(node)
        while queue:
            u = queue.popleft()

            # visits neighbours
            for v in graph[u]:
                # assign a color opposite to u
                if colors[v] == -1:
                    colors[v] = 1 - colors[u]
                    queue.append(v)
                elif colors[v] == colors[u]:
                    return False  # if neighbours has same color as 'u'
        return True

    def isBipartite(self, graph: List[List[int]]) -> bool:
        """
        A graph is called bipartite graph, if we can divide the vertices of the graph
        in two different sets/color such that no neighbor share the same color.

        To find so, we can start doing dfs from a vertex and assign a number, and then visit its
        neighbor, since neighbor should go in a different set, we will assign a different color to it.

        At any moment, a vertex has a color found and has a same color, then is not a bipartite graph

        """
        vertex = len(graph)

        # assign -1 color to all, no color assigned
        colors = [-1] * vertex
        queue = deque()

        for node in range(vertex):
            # take unvisited nodes
            if colors[node] == -1:
                if not self.is_bipartite(graph, node, 1, colors, queue):
                    return False

        return True


class Solution_DFS:
    def is_bipartite(self, graph: List[List[int]], u: int, color: int, colors: list) -> bool:
        # if a color already been assigned
        if colors[u] != -1:
            return colors[u] == color

        colors[u] = color

        for v in graph[u]:
            if not self.is_bipartite(graph, v, 1 - color, colors):
                return False

        return True

    def isBipartite(self, graph: List[List[int]]) -> bool:
        """
        A graph is called bipartite graph, if we can divide the vertices of the graph
        in two different sets/color such that no neighbor share the same color.

        To find so, we can start doing dfs from a vertex and assign a number, and then visit its
        neighbor, since neighbor should go in a different set, we will assign a different color to it.

        At any moment, a vertex has a color found and has a same color, then is not a bipartite graph

        """
        vertex = len(graph)

        # assign -1 color to all, no color assigned
        colors = [-1] * vertex

        for u in range(vertex):
            # visit an unvisited node
            if colors[u] == -1:
                if not self.is_bipartite(graph, u, 1, colors):
                    return False

        return True
