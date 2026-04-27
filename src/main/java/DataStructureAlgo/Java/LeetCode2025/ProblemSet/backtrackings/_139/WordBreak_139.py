"""
Author: Nitin Gupta
Date: 7/26/2025
Question Title: 139. Word Break
Link: https://leetcode.com/problems/word-break/description/
Description: Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
File reference
-----------
Duplicate {@link WordBreak}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Array
@HashTable
@String
@DynamicProgramming
@Trie
@Memoization

<p><p>
Company Tags
-----
@Microsoft
@Amazon
@Facebook
@Apple
@Qualtrics
@Adobe
@Audible
@Bloomberg
@Coupang
@Google
@HBO
@Hulu
@Lyft
@Oracle
@Pinterest
@PocketGems
@Qualtrics
@Salesforce
@Snapchat
@Square
@TripAdvisor
@Twilio
@Uber
@VMware
@WalmartLabs
@Yahoo
@Yelp

<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import collections, List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Trie: 
    """
    Build the trie of dict to avoid substring slicing completely 
    Time: O(M*L) + O(n^2)
    Space: O(M*L + n)
    """
    
    class TrieNode:
        def __init__(self):
            self.children = {} # char -> TriNode
            self.is_word = False
    
    
    #O(M*L) M -> length of Dict, L->Maximum length of a word
    def build_trie(self, wordDict: List[str]) -> TrieNode:
        root: Trie.TrieNode = Trie.TrieNode()
        
        crawl = root
        for word in wordDict:
            crawl = root
            
            for char in word:
                if char not in crawl.children:
                    crawl.children[char] = Trie.TrieNode()
                
                crawl = crawl.children[char]
            
            crawl.is_word = True
            
        
        return root
            
    
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        if not s :
            return True

        if not wordDict:
            return False

        
        root: Trie.TrieNode = self.build_trie(wordDict)
        
        dp = [False] * (len(s) + 1)
        dp[0] = True
        
        for i in range(len(s)+1): #O(n)
            # If we haven't reached this index with previous words,
            # we can't start a new word here.
            if not dp[i]: # substring from [0..i] should be present already in wordDict
                continue
                
            # check for [i...n-1] substring is present or not
            crawl: Trie.TrieNode = root
            for j in range(i,len(s)): # O(n)
                
                if s[j] not in crawl.children:
                    break # no substring possible 
                
                crawl = crawl.children[s[j]]
                
                if crawl.is_word:
                    dp[j+1] = True # substring [i..j] (including j) can be segmented. 
        
        return dp[len(s)]
                
            
                
        


class Bottom_Up: 
    
    class Solution_WordLength:
        """
         Instead of blindly check all substring, limit the substring check with word length bound.
         
         Time:
            Total state * work per state
            
            Total State: 
                1. There will be total substring match  O(n) -> the outer loop
            
            Work per state:
                1. We run the innner loop at max L times where L is max length of a word in Dict. 
                2. For each check we slice the string which can be at max O(L) 
            => O(L^2)
        
        => O(n*L^2)
        
        Space: 
            O(n)
            
            
        Best practice in interviews:
            State the complexity as O(n * L²) and mention: "This is better than O(n²) when L < √n, which is typically true for natural language text."
           
         
         
        """

        def wordBreak(self, s: str, wordDict: List[str]) -> bool:
            length = len(s)
            word_dict = set(wordDict)
            max_len = max(len(word) for word in wordDict)
            min_len = min(len(word) for word in wordDict)
            
            dp = [False] * (length + 1)
            dp[0] = True
            
            for i in range(1, length+1): # O(n)
                
                # choose a substring of [min_len, max_len] bound
                for j in range(max(0, i-max_len), i - min_len + 1): # O(L)
                    if dp[j] and s[j:i] in word_dict:  # O(L) due to slice
                        dp[i] = True
                        break
                    
            return dp[len(s)]
        
    class Solution_Substring:
        """
                Try each substring of given string and check does s[i:j] exists in dict or not.
                If found, then try further string and do same.
                If not found, then try next substring.

                Complexity:
                Time : O(n^3) where n is length of str
                    a. O(n) for first iteration, assuming dict check is O(1) => O(n)
                    b. O(n) for second iteration, assuming dict check is O(1) => O(n)
                        c. for each we slice string O(n) and check in dict O(1) => O(n)

                Space: O(n)
            """
        def wordBreak(self, s: str, wordDict: List[str]) -> bool:

            length = len(s)

            # convert list to set for faster lookup
            word_dict = set(wordDict)

            # cache to check does till this character word can be broken or not
            dp: List[bool] = [False] * (length + 1)
            dp[0] = True

            # try each index for possible break
            for i in range(1, length + 1): # O(n)

                for j in range(i):  # O(n)

                    if dp[j] and s[j:i] in word_dict:  # O(n) due to slice
                        dp[i] = True
                        break

            return dp[len(s)]


class Solution_TopDown:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        """
            Save the repeative work of bruteForce in memo. 
        
            Complexity: 
                Dynamic programming Complexity = Total_state * work_per_state
                M = len(dict)
                L = len(longest_word_in_dict)
                n = len(s)
                
                Total State:
                    There will be 'n' total substring that will be stored in memo. As we check against such substring in memo and store it. 
                
                Work Per State:
                    We can all the word in dict: O(M)
                    For each state, we compare that current substring is start with choose dict word or not. O(L) ( substring match algorithm )
                        then for each such case, we slice the substring to the length that can be at max O(n)
                        Hence this takes O(n+L) cost
                    
                    So total work per state = O(M * (n+L))
                
                Time: O(n * (M*(n+L))) = O(n*M*n) = O(n^2*M)
                Space: O(n)

        """
        memo = {}  # str vs True/False

        def dfs(subs: str) -> bool:
            if len(subs) == 0:
                return True

            if subs in memo:
                return memo[subs]

            # scan all word from dict
            for word in wordDict:

                # if this word is starting point of
                if subs.startswith(word):

                    # lets modify the remaining string to solve further
                    if dfs(subs[len(word):]):
                        memo[subs] = True
                        return True

            memo[subs] = False
            return False

        return dfs(s[::])

class BruteForce : 
    """
    Idea: Try every possible way to split the string and check if all parts are in the dictionary.
    Time: O(2^n) - for each position, we try to split or not split
    Space: O(n) - recursion stack depth
    """
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        def dfs(subs: str) -> bool:
            if len(subs) == 0:
                return True

            # scan all word from dict
            for word in wordDict:

                # if this word is starting point of
                if subs.startswith(word):

                    # lets modify the remaining string to solve further
                    if dfs(subs[len(word):]):
                        return True

            return False

        return dfs(s)
    

def test(input_data, wordDict,expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "WordDict", "Expected"], True, input_data,  wordDict, expected)
    pass_test, final_pass = True, True
    
    output = BruteForce().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BruteForce", "Pass"],
                             False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_TopDown().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_TopDown", "Pass"],
                             False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Bottom_Up.Solution_Substring().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Bottom_Up.Solution_Substring", "Pass"],
                             False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Bottom_Up.Solution_WordLength().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Bottom_Up.Solution_WordLength", "Pass"],
                             False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Trie().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Trie", "Pass"],
                             False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    

    return final_pass


if __name__ == "__main__":
    tests: List = [test("leetcode", ["leet", "code"], True),
                   test("applepenapple", ["apple", "pen"], True),
                   test("catsandog", ["cats", "dog", "sand", "and", "cat"], False)]

    CommonMethods.print_all_test_results(tests)
