"""
Author: Nitin Gupta
Date: 26/02/2026
Question Title: 685. Redundant Connection II
Link: https://leetcode.com/problems/redundant-connection-ii/description/
Description: In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.

Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ui, vi <= n
ui != vi

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link RedundantConnection_684.py}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@Depth-FirstSearch
@Breadth-FirstSearch
@Union-Find
@GraphTheory

<p><p>
Company Tags
-----
@Amazon
@Google

<p>
-----

@Editorial <p>https://leetcode.com/problems/redundant-connection-ii/solutions/7611322/best-question-on-graph-by-nits2010-o817/<p> 
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution:
    """ 
            Key Observations:
                1. Given graph was originaly a rooted Tree. 
                    Derivation: Means originally there would be only one node has indegree = 0 while other has excatly = 1
                2. A rooted tree is a valid graph. Adding an edge made not rooted any more
                    Derivation: A graph has |V| = |E| + 1 relationship. Adding one extra edge -> |V| = |E| hence n = |E| 
                
                3. Adding extra edge will distrbu the tree. 
                    Derivation: In following possible manner 
                    Case A: it could increase the in-degree of a node to 2 with no cycle
                        [1,3] , [2,3], [ 1,2] Here no cycle but extra edge is there, makes node indgree(3) = 2
                    Case B. it form a cycle. 
                        [1,2], [2,3], [3,4], [4,1] Here [4,1] forms a cycle and distrub the tree, however all nodes has indegree=1
                    Case C. (Case A + Case B) it does both, indegree = 2 + cycle 
                        [1,3], [3,2] , [2,3] indegree(3) = 2 and a cycle 1->3->2->3
            
            Case analysis:
                Case A: indegree = 2
                    When a node 'v' has indegree 2 then one of edge (u1->v and u2->v) is the extra added edge. Since we don't know, hence we need to find which one does this Case C
                Case B: when only form cycle
                    When only form cycle, then all node will have have indegree=1 (except root which could have indegree=0). 
                    -> Then the edge which form the cycle later in the input is our extra edge. 

                    => Run Union Find algorithm to detect which edge create cycle
                        -> Union-Find is for undirected graphs.
                        -> In this problem, we are not checking directed cycle structure.
                                => Whether adding an edge connects two nodes that are already connected.
                        -> And that works even if we ignore direction.
                        If that edge creates a directed cycle, then:

                        In the undirected sense, those two nodes were already connected.
                        So Union-Find will detect it.

                        Because in a tree:
                            There is already exactly one undirected path between any two nodes.
                            So adding an edge between them must create a cycle.
                            Direction does not matter for that fact.  
                Case C: Indegree = 2 + Cycle
                    Now we alredy discussed for cycle we can do case b solution but since we have two candidates now that could form a cycle. We need to skip one candidate and try to detect cycle. If cycle formed then the candidate we choose is our extra 
                    edge otherwise the one we skip is our extra edge because choosing that will form the cycle. 
                    -> Which to choose to skip first ? 
                        -> We should skip second candidate first because it comes later in the input ( our required constraint ). 
    """

    class UnionFind:
        def __init__(self, n):
            self.parent = list(range(n + 1))
            self.size = [1] * (n + 1)

        def find(self, i):
            if self.parent[i] != i:
                self.parent[i] = self.find(self.parent[i])

            return self.parent[i]

        def union(self, i, j):
            root_i = self.find(i)
            root_j = self.find(j)

            if root_i == root_j:  # belongs same set, forming cycle
                return False

            if self.size[root_j] > self.size[root_i]:
                root_i, root_j = root_j, root_i

            self.parent[root_j] = root_i
            self.size[root_i] += self.size[root_j]

            return True

    def findRedundantDirectedConnection(self, edges: List[List[int]]) -> List[int]:
        if not edges:
            return []

        n = len(edges)

        union_find = Solution.UnionFind(n)

        # find if a node has indegree = 2
        candidate1 = None
        candidate2 = None

        parents = [-1] * (n + 1)
        for u, v in edges:
            if parents[v] == -1:
                parents[v] = u
            else:
                candidate1 = (parents[v], v)
                candidate2 = (u, v)
                break

        skip_candidate = None
        if candidate1 and candidate2:
            # means cycle + indegree = 2, skip the second candidate first
            skip_candidate = candidate2
            # means there are no vertex has indegree=2, that leads to only case B: only cycle

        # otherwise, it means there are no vertex has indegree=2, that leads to only case B: only cycle

        # run union-find to see for cycle
        # skip_candidate will tell its case b or case c
        for u, v in edges:
            if skip_candidate and (u,v) == skip_candidate:
                continue 
            
            # add that in sets
            if not union_find.union(u, v):
                # union did not happened, cycle detected, candidate 1 formed the cycle
                # Case B: Cycle only, in this case all node will have indegree 1, our output would be the current edge which is forming cycle
                if not candidate1:
                    return [u, v]
                return candidate1

        # candiate 2 formed the cycle
        return candidate2



def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().findRedundantDirectedConnection(input_data)
    pass_test = CommonMethods.compare_result(output, expected, False)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([[1,2],[1,3],[2,3]], [2,3]),
                   test([[1,2],[2,3],[3,4],[4,1],[1,5]], [1,4]),
                    test([[1,2],[2,3],[3,4],[4,1],[1,5]], [4,1]),
                    test([[2,1],[3,1],[4,2],[1,4]], [2,1])]

    CommonMethods.print_all_test_results(tests)
