"""
Author: Nitin Gupta
Date: 8/13/2025
Question Title: 49. Group Anagrams
Link: https://leetcode.com/problems/group-anagrams/description/
Description: Given an array of strings strs, group the anagrams together. You can return the answer in any order.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
Example 2:

Input: strs = [""]

Output: [[""]]

Example 3:

Input: strs = ["a"]

Output: [["a"]]



Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
File reference
-----------
Duplicate {@link GroupAnagrams.java}
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
@Sorting

<p><p>
Company Tags
-----
@Amazon
@Microsoft
@Facebook
@Apple
@JPMorgan
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque, defaultdict
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# O(m * n) / o(mn): m is list size, n is maximum size of string
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:

        # A defaultdict is created with list as the default factory. This means that if a key does not exist in the dictionary, it automatically creates an empty list for that key.
        result = defaultdict(list)

        for string in strs:

            freq = [0] * 26

            # count the frequency of each char
            for s in string:
                freq[ord(s) - ord("a")] += 1

            result[tuple(freq)].append(string)

        return list(result.values())


# O(m * nlogn) / o(mn): m is list size, n is maximum size of string
class Solution_Sort:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:

        # A defaultdict is created with list as the default factory. This means that if a key does not exist in the dictionary, it automatically creates an empty list for that key.
        result = defaultdict(list)

        for string in strs:

            sorted_str = "".join(sorted(string))

            result[sorted_str].append(string)

        return list(result.values())


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution().groupAnagrams(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Sort", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    output = Solution_Sort().groupAnagrams(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Sort", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    return final_pass


if __name__ == "__main__":
    tests: List = [test( ["eat","tea","tan","ate","nat","bat"], [["bat"],["nat","tan"],["ate","eat","tea"]]),
                   test([""], [[""]]),
                   test(["a"], [["a"]])]

    CommonMethods.print_all_test_results(tests)
