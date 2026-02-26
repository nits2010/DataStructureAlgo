
from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

#   Cycle detection logic 
#   DFS + BFS 
class Solution_DFSV2 : # directed DFS ; Cycle detection logic 
    """
    The reason we use state 1 is to identify the Back Edge. In a directed graph, a cycle only exists if you point back to an ancestor in the current DFS path. 
    Pointing to a node with state 2 is just a Cross Edge, which is perfectly fine and does not indicate a cycle.
    
    Visualizing the Three States
    To ensure the logic is clear, here is how those three numbers represent the "color" of the nodes during the search:

    0 (White): Unvisited.

    1 (Gray): Visiting (currently in the recursion stack). If we hit a node in this state, we've found a Back Edge.

    2 (Black): Fully processed (no cycles found in this sub-tree).
    """
    def build_graph(self, numCourses: int, prerequisites: List[List[int]]) -> (List[List[int]], list):
        adj_list = [[] for _ in range(numCourses)]
    

        for ai, bi in prerequisites:
            adj_list[ai].append(bi)

        return adj_list
    
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        adj_list = self.build_graph(numCourses, prerequisites)
        
        # False -> (White): Unvisited.
        # True -> Visited
        visited = [False] * numCourses
        
        # False -> (White): Not in stack ( not visiting right now).
        # True -> In stack, we are visiting right now
        recu_stack = [False] * numCourses

        def dfs(node):
            visited[node] = True # visited this node
            recu_stack[node] = True # currently visiting, hence in stack
           
            for neigh in adj_list[node]:
                if not visited[neigh]:
                   if dfs(neigh):
                       return True
                elif recu_stack[neigh]: # neigh can not be in rec stack, since rec_stack holds only currently visited node which should be parent
                    return True
            
            recu_stack[node] = False # not in stack anymore
            return False
        
        for course in range(numCourses):
            if not visited[course] and dfs(course):
                return False
        
        return True
class Solution_DFS : # directed DFS 
    """
    The reason we use state 1 is to identify the Back Edge. In a directed graph, a cycle only exists if you point back to an ancestor in the current DFS path. 
    Pointing to a node with state 2 is just a Cross Edge, which is perfectly fine and does not indicate a cycle.
    
    Visualizing the Three States
    To ensure the logic is clear, here is how those three numbers represent the "color" of the nodes during the search:

    0 (White): Unvisited.

    1 (Gray): Visiting (currently in the recursion stack). If we hit a node in this state, we've found a Back Edge.

    2 (Black): Fully processed (no cycles found in this sub-tree).
    """
    def build_graph(self, numCourses: int, prerequisites: List[List[int]]) -> (List[List[int]], list):
        adj_list = [[] for _ in range(numCourses)]
    

        for ai, bi in prerequisites:
            adj_list[ai].append(bi)

        return adj_list
    
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        adj_list = self.build_graph(numCourses, prerequisites)
        
        # 0 -> (White): Unvisited.
        # 1 -> (Gray): Visiting (currently in the recursion stack). If we hit a node in this state, we've found a Back Edge.
        # 2 -> (Black): Fully processed (no cycles found in this sub-tree).
        visited = [0] * numCourses

        def dfs(node):
            if visited[node] == 1:
                # we had already visited this node and it appears again, hence cycle
                return True

            if visited[node] == 2:
                # course already completed
                return False

            visited[node] = 1 # visited the non-visited node
            
            for neigh in adj_list[node]:
                if dfs(neigh):
                    return True
            
            visited[node] = 2 # completed
            return False

        for course in range(numCourses):
            if visited[course] != 2 and dfs(course):
                return False
        
        return True


class Solution_KhanTopo:
    def build_graph(self, numCourses: int, prerequisites: List[List[int]]) -> (List[List[int]], list):
        adj_list = [[] for _ in range(numCourses)]
        inDegree = [0] * numCourses

        for ai, bi in prerequisites:
            adj_list[ai].append(bi)
            inDegree[bi] += 1

        return adj_list, inDegree

    def topological_sort(self, numCourses: int, adj_list: List[List[int]], inDegree: list[int]) -> int:

        # apply khan's algo for topological sort
        queue = deque()

        # queue all 0 indegree edges
        for node in range(numCourses):
            if inDegree[node] == 0:
                queue.append(node)

        # process them
        processed_node:int = 0

        while queue:
            u = queue.popleft()
            processed_node += 1

            # enqueue all the edges of this node
            for v in adj_list[u]:
                inDegree[v] -= 1

                if inDegree[v] == 0:
                    queue.append(v)

        return processed_node

    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        adj_list, inDegree = self.build_graph(numCourses, prerequisites)
        return self.topological_sort(numCourses, adj_list, inDegree) == numCourses


def test(num_courses:int, prerequisites, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["numCourses", "prerequisites", "Expected"], True, num_courses,prerequisites, expected)
    pass_test, final_pass = True, True
    output = Solution_KhanTopo().canFinish(num_courses, prerequisites)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_DFS().canFinish(num_courses, prerequisites)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_DFS().canFinish(num_courses, prerequisites)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DFSV2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    return final_pass


if __name__ == "__main__":
    tests: List = [test(2, [[1,0]], True),
                   test(2, [[1,0],[0,1]], False)]

    CommonMethods.print_all_test_results(tests)
