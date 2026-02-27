"""
Author: Nitin Gupta
Date: 27/02/2026
Question Title: 126. Word Ladder II
Link: https://leetcode.com/problems/word-ladder-ii/description/
Description: A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 500
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
The sum of all shortest transformation sequences does not exceed 105.

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link WordLadder_127.py}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@HashTable
@String
@Backtracking
@Breadth-FirstSearch

<p><p>
Company Tags
-----
@Amazon
@Box
@Facebook
@Snapchat
@Microsoft
@Google 
@LinkedIn 
@Lyft 
@Oracle 
@Pinterest 
@Uber 
@Yahoo 
@Yelp
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import defaultdict, deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


from collections import defaultdict

class Solution_BidirectionalBFS_PatternMatch:
    def findLadders(self, beginWord, endWord, wordList):
        if endWord not in wordList: return []
        
        # 1. Pre-process patterns: "hot" -> {"h*t": ["hot"], "ho*": ["hot"], "*ot": ["hot"]}
        L = len(beginWord)
        patterns = defaultdict(list)
        for word in wordList:
            for i in range(L):
                patterns[word[:i] + "*" + word[i+1:]].append(word)
        
        # Add beginWord to patterns if not already there
        for i in range(L):
            patterns[beginWord[:i] + "*" + beginWord[i+1:]].append(beginWord)

        forward_set = {beginWord}
        backward_set = {endWord}
        adj = defaultdict(list)
        found = False
        rev = False
        
        # Use a set to keep track of available words
        word_set = set(wordList)
        if beginWord in word_set: word_set.remove(beginWord)
        if endWord in word_set: word_set.remove(endWord)

        while forward_set and not found:
            if len(forward_set) > len(backward_set):
                forward_set, backward_set = backward_set, forward_set
                rev = not rev
            
            # Words found in this level must be removed from dictionary 
            # AFTER the level is processed
            current_level_visited = set()
            next_set = set()
            
            for word in forward_set:
                for i in range(L):
                    pattern = word[:i] + "*" + word[i+1:]
                    for neigh in patterns[pattern]:
                        if neigh in backward_set:
                            found = True
                            self.add_edge(adj, word, neigh, rev)
                        elif not found and neigh in word_set:
                            next_set.add(neigh)
                            current_level_visited.add(neigh)
                            self.add_edge(adj, word, neigh, rev)
            
            word_set -= current_level_visited
            forward_set = next_set

        if not found: return []
        
        # 2. Backtracking DFS to build paths
        results = []
        path = [beginWord]
        def dfs(curr):
            if curr == endWord:
                results.append(list(path))
                return
            for neigh in adj[curr]:
                path.append(neigh)
                dfs(neigh)
                path.pop()
        
        dfs(beginWord)
        return results

    def add_edge(self, adj, u, v, rev):
        if rev: adj[v].append(u)
        else: adj[u].append(v)
        

class Solution_BidirectionalBFS:
    """
        Just like wordLader I
    """

    def add_edge(self, adj_list, child, parent, direction):
        """ Ensures the graph always points Child -> Parent """ 
        if direction: # Forward exploration: parent is closer to beginWord
            # parent -> child 
            adj_list[child].append(parent)
        else: # Backward exploration: parent is closer to endWord
            # child -> parent 
            adj_list[parent].append(child)
        
    
    def bfs(self, beginWord: str, endWord: str, word_set):
        # queue replaced with sets 
        begin_set = {beginWord} # forward set
        end_set = {endWord} # backward set
        direction = 1 # forward=1, backward=0, We need to make sure the edges connect in correct direction in directed graph
        adj_list = defaultdict(list) # our adj list for graph
        found = False # tells did we find the endWord/beignword or not from forward/backward direction
        
        # We must remove the starting words initially
        word_set.discard(beginWord)
        word_set.discard(endWord)
        
        while begin_set and not found: 
            
            # Optimization: Always expand the smaller set
            if len(begin_set) > len(end_set):
                begin_set, end_set = end_set, begin_set
                direction = 1 - direction # switch direction as we switch sets to explore
            
            next_set = set() # next level set
            
            
            # explore current level
            for word in begin_set:
                
                # get all neighbours 
                for i in range(len(word)):
                    for c in "abcdefghijklmnopqrstuvwxyz":
                        if c == word[i]:
                            continue

                        neigh = word[:i] + c + word[i + 1 :]
                
                        if neigh in end_set:
                            found = True
                            self.add_edge(adj_list, neigh, word, direction)
                            # we won't break, until this level is explored fully
                        
                        elif not found and neigh in word_set:
                            self.add_edge(adj_list, neigh, word, direction)
                            next_set.add(neigh) # add to explore next
                            
                        
            word_set -= next_set # you allow multiple words in the current level to "find" the same neighbor. This is exactly how you capture all shortest paths. Eliminating now means we have explored them
            begin_set = next_set
            
        return adj_list

    
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[List[str]]:
        
        if not wordList:
            return []

        word_set = set(wordList)

        if endWord not in word_set:
            return []
        
        adj_list = self.bfs(beginWord, endWord, word_set)
        sequences = []
        current_sequence = [endWord]
        
        def dfs(word):
            if word == beginWord:
                sequence = current_sequence[:]
                sequences.append(sequence[::-1]) 
                return 
            
            for neigh in adj_list[word]:
                current_sequence.append(neigh)
                dfs(neigh)
                current_sequence.pop()
            return
    

        dfs(endWord)
        return sequences
        
       
# ================================

# Time: 
#   1. get_neighbours : O(len(word) * 26 * reconstruct(word)) : O(L * 26 * L) -> O(L^2)
#   2. bfs : O((V + (cost of exploring all neigh))) : O(V + V*L^2) : O(V*L^2)
#   3. dfs : O(path explored) = O(path_length * no.of paths) = O(V * K) where K is no. of paths
#   Total : O(V*L^2) + O(V * K) => O(V*(L^2+K))

# Space:
# 1. get_neighbours : O(V) : a word could have at most V neigh
# 2. bfs: 
#   2.1. Queue: O(queue size) : O(V) : Queue can have at most V nodes at a time
#   2.2  Distance Map : O(V)
# 3. dfs : Implicit stack O(V*K) where K is no. of paths
# 4. adj_list : O(total nodes + total edges ) = O(V+E) however E = V*L*26 as one word (like "CARES") could technically transform into dozens of others ("BARES", "DARES", "CORES", "CARDS", etc.).  
#           => O(V + V*L*26) = O(V*L)
# Total : O(V) + O(V) + O(V) + O(V*K) + O(V * L) = O(V*(L+K))

# Time:  O(V*(L^2+K))
# Space: O(V*(L+K))

class Solution_BFS:
    """The sequence will be made by only those nodes which has `minimum distance` to endWord from beginWord
         In wordLadder I, we tried to build the minimum distance using BFS + on the fly neigh { also using Bidirectional bfs}

         Now in order to get all sequence, we need to know the possible path we took during bfs to reach endWord with same minimum distance.
         We can maintain those possible nodes we visited during bfs, as a trimed graph, and then later do dfs on those list of words using backtrack to build the list of sequence.

         To get all sequence, we need to make sure that our trimmed graph shoud have all the nodes reachable to 'target' node with same distance.
         Example:

         word ->[neigh] ; distance
         -> updatedDistance; queue

        hit -> ['hot'] ; {'hit': 0}
         ->{'hit': 0, 'hot': 1} ; deque(['hot'])

         hot -> ['dot', 'lot'] ; {'hit': 0, 'hot': 1}
         ->{'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2} ; deque(['dot', 'lot'])

         dot -> ['hot', 'lot', 'dog'] ; {'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2}
         ->{'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2, 'dog': 3} ; deque(['lot', 'dog']) {lot from earlier queue}
             Here
                 distance[hot] != distance[dot] + 1
                 distance[lot] != distance[dot] + 1

         lot -> ['dot', 'hot', 'log'] ; {'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2, 'dog': 3}
         ->{'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2, 'dog': 3, 'log': 3} ; deque(['dog', 'log'])
             Here
                 distance[dot] != distance[lot] + 1
                 distance[hot] != distance[lot] + 1

         dog -> ['cog', 'log', 'dot'] ; {'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2, 'dog': 3, 'log': 3}
         ->{'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2, 'dog': 3, 'log': 3, 'cog': 4} ; deque(['log', 'cog']) {log from earlier queue}
             Here
                 distance[log] != distance[dog] +
                 distance[dot] != distance[dog] + 1

         log -> ['cog', 'dog', 'lot'] ; {'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2, 'dog': 3, 'log': 3, 'cog': 4}
         ->{'hit': 0, 'hot': 1, 'dot': 2, 'lot': 2, 'dog': 3, 'log': 3, 'cog': 4} ; deque(['cog'])
             Here
                 distance[cog] = distance[log] + 1 -> updated adj with log for cog : cog : [dog, log]
                 distance[dog] != distance[log] + 1
                 distance[dot] != distance[log] + 1

         cog -> its end word



        """
    def findLadders(
        self, beginWord: str, endWord: str, wordList: List[str]
    ) -> List[List[str]]:
        
        if not wordList:
            return []

        word_set = set(wordList)

        if endWord not in word_set:
            return []

        adj_list = self.bfs(word_set, beginWord, endWord)
    

        if not adj_list:
            return []

        # now we have all nodes:[parents] map with minimum distance
        sequences = []
        current_sequence = [endWord]

        def dfs(word):
            """ Reconstructs paths from endWord back to beginWord. """
            if word == beginWord:  # reached the
                sequence = current_sequence[:]
                sequences.append(sequence[::-1])
                return

            for parent in adj_list[word]:
                current_sequence.append(parent)
                dfs(parent)
                current_sequence.pop()  # backtrack

        dfs(endWord)
        return sequences
    
    def get_neighbours(self, word, word_set):
        """ Generates valid transformations on the fly."""
        neighbours = []
        for i in range(len(word)):
            for c in "abcdefghijklmnopqrstuvwxyz":
                if c == word[i]:
                    continue

                neigh = word[:i] + str(c) + word[i + 1 :]
                if neigh in word_set:
                    neighbours.append(neigh)

        return neighbours

    def bfs(self, word_set, beginWord, endWord):
        adj_list = defaultdict(list) # Stores the "reverse" edges of the shortest path DAG.
        distance_map = {beginWord: 0} # Prevents cycles and ensures we only move "forward".
        queue = deque([beginWord])
        found = False # Use a flag instead of early return

        while queue and not found:
            # Process level by level to ensure all shortest paths are captured
            for _ in range(len(queue)):
                word = queue.popleft()
                distance = distance_map[word]

                if word == endWord:
                    found = True
                    # We don't return yet, we finish this level's neighbors

                neighbours = self.get_neighbours(word, word_set)
                for neigh in neighbours:
                    if neigh not in distance_map:
                        distance_map[neigh] = distance + 1
                        adj_list[neigh].append(word)
                        queue.append(neigh)
                        
                    # we have visited neigh before, see if distance b/w [neigh, word] is same as old + 1
                    elif distance_map[neigh] == distance + 1:
                        adj_list[neigh].append(word)

        return adj_list if found else {}

    


def test(beginWord, endWord, wordList, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["beginWord", "endWord", "wordList", "Expected"], True, beginWord, endWord, wordList, expected)
    pass_test, final_pass = True, True
    output = Solution_BFS().findLadders(beginWord=beginWord, endWord=endWord, wordList=wordList)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_BidirectionalBFS().findLadders(beginWord=beginWord, endWord=endWord, wordList=wordList)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BidirectionalBFS", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass



if __name__ == "__main__":
    tests: List = [test(beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"], expected=[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]),
                   test(beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"], expected=[])]

    CommonMethods.print_all_test_results(tests)

