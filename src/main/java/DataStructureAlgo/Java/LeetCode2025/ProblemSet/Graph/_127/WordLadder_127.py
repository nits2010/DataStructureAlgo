"""
Author: Nitin Gupta
Date: 27/02/2026
Question Title: 127. Word Ladder
Link: https://leetcode.com/problems/word-ladder/description/
Description: A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.

File reference
-----------
Duplicate {@link WordLadderI.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@HashTable
@String
@Breadth-First Search
@Graph

<p><p>
Company Tags
-----
@Amazon
@Facebook
@LinkedIn
@Microsoft
@Qualtrics
@Affirm 
@Airbnb 
@Apple 
@Audible 
@Bloomberg 
@Cohesity 
@Expedia 
@Google 
@Lyft 
@Oracle 
@Salesforce 
@Samsung 
@Snapchat 
@Square 
@Tesla 
@Uber 
@WalmartLabs 
@Yelp 
@Zillow
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import defaultdict, deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Time : O(V * L * 26 * L) = O(V.L^2)
# Space: O(V*L)
class Solution_BidBFS_OnTheFlyNeigh:

    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        """In this versin, we will do bidirectional bfs and neigh on the fly"""
        if beginWord == endWord:
            return 0

        word_set = set(wordList)

        # if endWord not exists then we can't transform
        if endWord not in word_set:
            return 0

        begin_set = {beginWord} # forward
        end_set = {endWord} # backward
        level = 1
        
        while begin_set and end_set:
            
            # take only smallest set for next turn
            if len(begin_set) > len(end_set):
                begin_set, end_set = end_set, begin_set
            
            next_set = set()
            for word in begin_set:
                for i in range(len(word)):
                    for c in 'abcdefghijklmnopqrstuvwxyz':
                        if c == word[i]:
                            continue
                        
                        neigh = word[:i] + str(c) + word[i+1:] # O(L)
                        
                        # we meet ?
                        if neigh in end_set:
                            return level + 1
                        
                        if neigh in word_set:
                            next_set.add(neigh)
                            word_set.remove(neigh)

            level +=1 
            begin_set = next_set
    
        return 0

# Time : O(V * L * 26 * L) = O(V.L^2)
# Space: O(V*L)
class Solution_BFS_OnTheFlyNeigh:

    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        """In this versin, we won't build the graph and compute the neighbours on the fly"""

        if beginWord == endWord:
            return 0

        word_set = set(wordList)

        # if endWord not exists then we can't transform
        if endWord not in word_set:
            return 0

        queue = deque()
        queue.append((beginWord, 1))

        while queue:
            word, distance = queue.popleft()

            if word == endWord:
                return distance

            # compute neghbours using 'a-z'
            for i in range(len(word)):
                for c in "abcdefghijklmnopqrstuvwxyz":
                    if c == word[i]:
                        continue

                    # replaced word[i] with 'cc
                    neigh = word[:i] + str(c) + word[i + 1 :] # O(L)

                    if neigh == endWord:
                        return distance + 1

                    if neigh in word_set:
                        queue.append((neigh, distance + 1))
                        word_set.remove(neigh)

        return 0
        


# Time : O(V^2 * L) + O(V + E)
# Space: O(V^2)
class Solution_BFS_ADJ:
    class GraphNode:
        def __init__(self, u, v, weight):
            self.u = u
            self.v = v
            self.weight = weight

    def get_distance(self, word1, word2):
        if word1 == word2:
            return 0

        distance = 0
        for i in range(len(word1)):
            if word1[i] != word2[i]:
                distance += 1
        return distance

    def build_graph(self, wordList, beginWord, endWord):
        """
        Since we are allowed to take from word -> to word such that the character change should be at most 1
        This means that our graph should have only those nodes whose distance is 1.
        In such case, its become the unweighted undirected graph. Because distance = 1 for all edges
        and we can swtich from a word to any other word
        """
        adj_list = defaultdict(list)  # [word->[GraphNode]]

        for i in range(len(wordList)):
            for j in range(i + 1, len(wordList)):
                word1 = wordList[i]
                word2 = wordList[j]
                distance = self.get_distance(word1, word2)
                if distance == 1:
                    adj_list[word1].append(Solution_BFS_ADJ.GraphNode(word1, word2, distance))
                    adj_list[word2].append(Solution_BFS_ADJ.GraphNode(word2, word1, distance))

            if self.get_distance(beginWord, wordList[i]) == 1:
                adj_list[beginWord].append(
                    Solution_BFS_ADJ.GraphNode(beginWord, wordList[i], 1)
                )
                adj_list[wordList[i]].append(
                    Solution_BFS_ADJ.GraphNode(wordList[i], beginWord, 1)
                )

        return adj_list

    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:

        if beginWord == endWord:
            return 0

        word_set = set(wordList)
        # if endWord not exists then we can't transform
        if endWord not in word_set:
            return 0

        adj_list = self.build_graph(wordList, beginWord, endWord)


        # now the problem become finding the shortest distance from beginWord to endWord pasing by wordlist adj graph
        # to find the shorted distance in unweighted graph, we can use BFS

        queue = deque()

        # add source
        queue.append((beginWord, 1))
        not_visited = word_set
        not_visited.add(beginWord)

        while queue:
            word, distance = queue.popleft()

            if word == endWord:
                return distance
            
            for neigh_word in adj_list[word]:
                if neigh_word.v in not_visited:
                    queue.append((neigh_word.v, distance + 1))
                    not_visited.remove(neigh_word.v)
            

        return 0


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
