"""
Author: Nitin Gupta
Date: 8/27/2025
Question Title:
Link:
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

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution_TarjanAlgo:
    def build_graph(self, n: int, relations: List[List[int]]) -> [Dict[int, List[int]]]:
        graph = {i: [] for i in range(1, n+1)}
        
        for u, v in relations:
            graph[u].append(v)
        
        return graph
    
    def detect_cycle(self, graph: Dict[int, List[int]], visited: list[int], current:int, depth:list[int]) -> bool:
        
        #this course is already visited, means has a cycle
        if visited[current] == 1:
            return True

        #if this course is completed without a cycle
        if visited[current] == 2:
            return False
        
        #mark as visited
        visited[current] = 1

        for next_course in graph[current]:
            if self.detect_cycle(graph, visited, next_course, depth):
                return True

            depth[current] = max(depth[current], depth[next_course]+1)
        
        # take the course
        visited[current] = 2
        return False
            
    
    def minimumSemesters(self, n: int, relations: List[List[int]]) -> int:
        graph = self.build_graph(n, relations)
        depth = [1]* (n+1)
        visited = [0] * (n+1)
        for i in range(1,n+1):
            if self.detect_cycle(graph, visited, i, depth):
                return -1
            
        return max(depth)
        

class Solution_TopologicalSort:
    def build_graph(self, n: int, relations: List[List[int]]) -> [Dict[int, List[int]], list[int]]:
        graph = {i: [] for i in range(1, n+1)}
        in_degree = [0]* (n+1)
        
        for u, v in relations:
            graph[u].append(v)
            in_degree[v]+=1
        
        return graph, in_degree
        
        
    def topological_sort(self, n, graph: Dict[int, List[int]], in_degree: List[int]) -> int:
        
        # enqueue all nodes with in_degree 0
        queue = deque([i for i in range(1, len(in_degree)) if in_degree[i] == 0 ])
        semesters = 0 
        course_taken = 0
        
        # Process nodes with in_degree 0
        while queue:    
            # process all the courses that can be taken parallely in a semester
            for i in range(len(queue)):
                u = queue.popleft()
                course_taken+=1
                #process this course and degrees the dependecies to others course 
                for v in graph[u]:
                    in_degree[v]-=1
                    if in_degree[v] == 0:
                        queue.append(v)
                
            semesters+=1
            
        return semesters if course_taken == n else -1
        
    
    def minimumSemesters(self, n: int, relations: List[List[int]]) -> int:
        graph, in_degree = self.build_graph(n, relations)
        return self.topological_sort(n, graph, in_degree)
        


def test(input_data, n, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Courses", "Expected"], True, input_data, n, expected)
    pass_test, final_pass = True, True
    output = Solution_TopologicalSort().minimumSemesters(n, input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Khan's", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_TarjanAlgo().minimumSemesters(n, input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Tarjan", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[1,3],[2,3]], 3, 2),
                   test([[1,2],[2,3],[3,1]], 3, -1)]

    CommonMethods.print_all_test_results(tests)
