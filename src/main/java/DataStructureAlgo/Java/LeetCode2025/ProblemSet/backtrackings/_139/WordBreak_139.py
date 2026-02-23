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
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution_memo:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:

        """
            We will scan the dictorary as in real-life the dictorary would be very small as compare the different sentences could exists.

            We will try recursively each word from the dict and see if our current character start with dict word, if so, we will check does the substring
            of length word matches with current word from dict or not, if so, then we move ahead and solve for remaining substring.

            Due to recursive natrure, to avoid solve same string again n again, we will cache it [DP]

        """

        length = len(s)
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


class Solution_Optimized:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:

        """
            Try each substring of given string and check does s[i:j] exists in dict or not.
            If found, then try further string and do same.
            If not found, then try next substring.

            Complexity:
            Time : O(n^2) where n is length of str
                a. O(n) for first iteration, assuming dict check is O(1)
                b. O(n) for second iteration, assuming dict check is O(1)

            Space: O(n)
        """

        length = len(s)

        # convert list to set for faster lookup
        wordDict = set(wordDict)

        # cache to check does till this character word can be broken or not
        cache: List[bool] = [False] * (length + 1)
        cache[0] = True

        # try each index for possible break
        for i in range(1, length + 1):

            for j in range(i):

                if cache[j] and s[j:i] in wordDict:
                    cache[i] = True
                    break

        return cache[len(s)]


class Solution_Substring:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:

        """
            Try each substring of given string and check does s[i:j] exists in dict or not.
            If found, then try further string and do same.
            If not found, then try next substring.

            Complexity:
            Time : O(n^2) where n is length of str
                a. O(n) for first iteration, assuming dict check is O(1)
                b. O(n) for second iteration, assuming dict check is O(1)

            Space: O(n)
        """

        length = len(s)

        # convert list to set for faster lookup
        wordDict = set(wordDict)

        # cache to check does till this character word can be broken or not
        cache: List[bool] = [False] * (length + 1)

        # try each index for possible break
        for i in range(length + 1):

            if not cache[i] and s[0:i] in wordDict:
                cache[i] = True

            # did we able to break it ?
            if cache[i]:

                # is it last ?
                if i == length:
                    return True

                # try further
                for j in range(i + 1, length + 1):

                    if not cache[j] and s[i:j] in wordDict:
                        cache[j] = True

                    if cache[j] and j == length:
                        return True

        return False


def test(input_data, wordDict,expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "WordDict", "Expected"], True, input_data,  wordDict, expected)
    pass_test, final_pass = True, True

    output = Solution_Substring().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Substring", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    output = Solution_Optimized().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_Optimized", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_memo().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Solution_memo", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("leetcode", ["leet", "code"], True),
                   test("applepenapple", ["apple", "pen"], True),
                   test("catsandog", ["cats", "dog", "sand", "and", "cat"], False)]

    CommonMethods.print_all_test_results(tests)
