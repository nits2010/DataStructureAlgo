"""
Author: Nitin Gupta
Date: 7/26/2025
Question Title: 140. Word Break II
Link: https://leetcode.com/problems/word-break-ii/description
Description: Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []


Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.

File reference
-----------
Duplicate {@link WordBreakII}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@Array
@HashTable
@String
@DynamicProgramming
@Backtracking
@Trie
@Memoization

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


class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        """
        We will follow what we did in WordBreak I.
        We will scan all the word from Dict and see if the current str is start with that word, if that is possible, then we will recurse for next substring
        if the string becomes empty then we will add the resulant string in our result set otherwise we will try next dict word
        """

        memo = {}  # holds key: string , value:List[str] ; value represent that how many ways we can divide the key using dict

        def dfs(subs: str):

            if subs in memo:
                return memo[subs]

            if subs == "":
                return [""]  # a empty string can be divided in empty string set

            result = []  # result of current divided 'subs'

            for word in wordDict:

                if subs.startswith(word):

                    sub_sentences = dfs(subs[len(word):])

                    for sentence in sub_sentences:
                        current_word = word + (" " + sentence if sentence else "")
                        result.append(current_word)

            memo[subs] = result
            return result

        return dfs(s)


def test(input_data, wordDict, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "WordDict", "Expected"], True, input_data,  wordDict, expected)
    pass_test, final_pass = True, True
    output = Solution().wordBreak(input_data, wordDict)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("catsanddog", ["cat", "cats", "and", "sand", "dog"], ["cats and dog", "cat sand dog"]),
                   test("pineapplepenapple", ["apple", "pen", "applepen", "pine", "pineapple"],
                        ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]),
                   test("catsandog", ["cats", "dog", "sand", "and", "cat"], [])]

    CommonMethods.print_all_test_results(tests)
