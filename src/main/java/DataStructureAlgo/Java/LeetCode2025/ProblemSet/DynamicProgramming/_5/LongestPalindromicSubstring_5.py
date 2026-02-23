"""
Author: Nitin Gupta
Date: 22/02/2026
Question Title: 5. Longest Palindromic Substring
Link: https://leetcode.com/problems/longest-palindromic-substring/description/
Description: iven a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.

File reference
-----------
Duplicate {@link LongestPalindromeSubString.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@TwoPointers
@String
@DynamicProgramming

<p><p>
Company Tags
-----
@Amazon
@Microsoft
@Google
@Adobe
@Facebook
@Airbnb 
@Alibaba 
@Apple 
@Baidu 
@Bloomberg 
@Cisco 
@eBay 
@GoDaddy 
@Huawei 
@caMorgan 
@Mathworks 
@Oracle 
@PureStorage 
@Roblox 
@Samsung 
@SAP 
@ServiceNow 
@Tencent 
@Uber 
@VMware 
@WalmartLabs 
@Wayfair 
@Yahoo 
@Yandex
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


 # Time: O(n^2)  
 # Space : O(1)
class Solution_ExpandCenter:
    def longestPalindrome(self, s: str) -> str:
        """
        A string is palindrome only if we start from center and go outwords and all the chars get matched.
        for a odd length string, we have center
        for a even length string, we start from center next to each other
        """
        max_length = 1
        start = 0

        n = len(s)

        def is_palindrome(left, right):
            nonlocal start, max_length
            while left >= 0 and right < n and s[left] == s[right]:
                if max_length < right - left + 1:
                    start = left
                    max_length = right - left + 1
                left -= 1
                right += 1

        # consider each index a possible center
        for center in range(1, n):

            # odd length
            left = center - 1
            right = center + 1

            is_palindrome(left, right)

            # even length
            left = center - 1
            right = center
            is_palindrome(left, right)

        return s[start : start + max_length]



"""Dynamic Programming

            Any string [i,j] will be palindrom only if str[i] == str[j] and string[i+1,j-1] is palindrome.

            So if we know a substring in [i+1,j-1] is palindrome than we can find the string [i,j] is palindrome or not.
            if length = j-i then
                j - 1 -(i+1) = j - 1 - i -1 = j - i - 2 => length - 2 needs to be palindrome.

            Means a length of 3 string will be palindrome only if length of 1 (in b/w of this string) is palindrom
            similarly a length of 4 string will be palindrom only if length of 2 (in b/w) is palindrome.

            Hence there are overlapping problems which help to drive the bigger problem if we know the shorter problem. Here shorter means a smaller length.
"""

 # Time: O(n^2)  
 # Space : O(n^2)
class BottomUp:
        def longestPalindrome(self, s: str) -> str:
            """
            Hence;
            dp[i][j] = True if substring [i,j] is palindrome otherwise False

                    = True:    j-i = 1
                    = True:    j-i = 2 and str[i]==str[j]
                    = True          str[i] == str[j] and dp[i+1][j-1]   ; j-i > 2

            """
            n = len(s)
            memo = {}
            
            def is_palindrome(i, j):
                if i >= j:
                    return True

                if (i, j) in memo:
                    return memo[(i,j)]

                memo[(i, j)] = (s[i] == s[j]) and is_palindrome(i + 1, j - 1)
                return memo[(i, j)]

            for length in range(n, 0, -1):  # longest length to shortest length
                for i in range(n - length + 1):
                    if is_palindrome(i, i + length - 1):
                        # Since we go from longest to shortest, the first True is our answer
                        return s[i : i + length]

            return s[0]
        
 # Time: O(n^2)  
 # Space : O(n^2)     
class TopDown:
        def longestPalindrome(self, s: str) -> str:
            """
            dp[i][j] = True if substring [i,j] is palindrome otherwise False

                    = True:    j-i = 1
                    = True:    j-i = 2 and str[i]==str[j]
                    = True     str[i] == str[j] and dp[i+1][j-1]   ; j-i > 2

            """
            n = len(s)
            dp = [[False] * n for _ in range(n)]
            result = (0, 0)

            # single length substring always palindrome
            for i in range(n):
                dp[i][i] = True

            for length in range(2, n + 1):

                for i in range(n - length + 1):
                    j = i + length - 1

                    if length == 2 and s[i] == s[j]:
                        dp[i][j] = True
                        result = (i, j)
                    elif dp[i + 1][j - 1] and s[i] == s[j]:
                        dp[i][j] = True
                        result = (i, j)

            return s[result[0] : result[1] + 1]




def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    
    output = BottomUp().longestPalindrome(s=input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BottomUp", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = TopDown().longestPalindrome(s=input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["TopDown", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_ExpandCenter().longestPalindrome(s=input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["ExpandCenter", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    return final_pass


if __name__ == "__main__":
    tests: List = [test("babad", "bab"),
                   test("cbbd", "bb"),
                   test("crbidxnkyieminyzchamldzjzglygkfbbyggagwdqdqamzpguppqjfrcewfduwvcmhliahovcwoupwxwhaoiiiguahuqxiqndwxqxweppcbeoasgtucyqlxnxpvtepwretywgjigjjuxsrbwucatffkrqyfkesakglyhpmtewfknevopxljgcttoqonxpdtzbccpyvttuaisrhdauyjyxgquinvqvfvzgusyxuqkyoklwslljbimbgznpvkdxmakqwwveqrpoiabmiegoyzuyoignfcgmqxvpcmldivknulqbpyxjuvyhrzcsgiusdhsogftokekbdynmksyebsnzbxjxfvwamccphzzlzuvotvubqvhmusdtwvlsnbqwqhqcigmlfoupnqcxdyflpgodnoqaqfekhcyxythaiqxzkahfnblyiznlqkbithmhhytzpcbkwicstapygjpjzvrjcombyrmhcusqtslzdyiiyvujnrxvkrwffwxtmdqqrawtvayiskcnpyociwkeopardpjeyuymipekbefbdfuybfvgzfkjtvurfkopatvusticwbtxdtfifgklpmjamiocalcocqwdivyulupammxhdbeazrrktxiyothnvbwwrsocxzxaxmoenigbhvxffddexrwsioqoyovaqvtmkwzupstkgkmvrddzolmuzjnsj", "wxqxw")
                ]

    CommonMethods.print_all_test_results(tests)
