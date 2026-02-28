"""
Author: Nitin Gupta
Date: 01/02/2026
Question Title: 269. Alien Dictionary 🔒
Link: https://leetcode.com/problems/alien-dictionary/description/
Description: There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary. Now it is claimed that the strings in words are sorted lexicographically by the rules of this new language.

If this claim is incorrect, and the given arrangement of string in words cannot correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there are multiple solutions, return any of them.

 

Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link ToplogicalSort.java}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@Depth-FirstSearch
@Breadth-FirstSearch
@Graph
@TopologicalSort
@Array
@String
@LokedProblem

<p><p>
Company Tags
-----
@Airbnb 
@Amazon 
@Apple 
@Bloomberg 
@Cohesity 
@Facebook 
@Flipkart 
@Google 
@Microsoft 
@Oracle 
@Pinterest 
@PocketGems 
@Snapchat 
@Square 
@Twitter 
@Uber 
@VMware
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods
class Solution:
    def foreignDictionary(self, words: List[str]) -> str:
        """ 
        Alien dic is sorted, means the words[0] come before word[1] because words[0][0] < words[1][0]
        That's how the alian dict would be sorted. if x[0] = y[0] then it does not matter as to find the order we need to see next x[1] = y[1]

        Mean's each character forming a direction from smallest to largest. This forms a DAG. where each character is a node and has direction to next character in lexical order. 
        To find the order in DAG, we can use topological sort (DFS/Khan). If we can't find the topological order of input means input has cycle
        """
        if not words:
            return ""

        graph, in_degree = self.build_graph(words)

        # run topological sort
        order: list = self.topological_khan(graph, in_degree)
        return "".join(order) 
    
    def build_graph(self, words):
        adj_list = {c: set() for word in words for c in word}
        in_degree = {c: 0 for word in words for c in word} # char -> indegree

       
        for word1, word2 in zip(words, words[1::]):
            length = min(len(word1), len(word2))

            # Check for invalid prefix case: ["abcd", "abc"]
            if len(word1) > len(word2) and word1[:length] == word2[:length]:
                return {},{}

            for k in range(length):
                if word1[k] != word2[k] :
                    if word2[k] not in adj_list[word1[k]]:
                        adj_list[word1[k]].add(word2[k])
                        in_degree[word2[k]] +=1 
                    break # further char does not matter
        
        return adj_list, in_degree

    def topological_khan(self, graph, in_degree) -> list:
        n = len(in_degree)
        queue = deque()
        for key, value in in_degree.items():
            if not value:
                queue.append(key)
        
        topological_sort = []

        while queue:
            _char = queue.popleft()
            topological_sort.append(_char)

            for neigh in graph[_char]:
                in_degree[neigh] -=1
                if in_degree[neigh] == 0:
                    queue.append(neigh)
        print(topological_sort)
        return topological_sort if len(topological_sort) == n else []


    


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().foreignDictionary(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(["wrt","wrf","er","ett","rftt"], "wertf"),
                   test(["z","x"], "zx"),
                   test(["z","x","z"], ""),
                   test(["wrtkj","wrt"], ""),
                   test(["z","o"], "zo"),
                   test(["hrn","hrf","er","enn","rfnn"], "hernf")]

    CommonMethods.print_all_test_results(tests)
