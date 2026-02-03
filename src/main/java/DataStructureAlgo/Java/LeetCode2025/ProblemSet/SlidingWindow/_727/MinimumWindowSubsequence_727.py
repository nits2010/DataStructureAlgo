"""
Author: Nitin Gupta
Date: 2/1/2026
Question Title: 727. Minimum Window Subsequence
Link: https://leetcode.com/problems/minimum-window-subsequence/description/
https://leetcode.ca/all/727.html
Description: Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
 
 
File reference
-----------
Duplicate {@link}
Similar {@link MinimumWindowSubstring_76.py}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@String
@DynamicProgramming
@Sliding Window
@PremiumQuestion
@LockedProblem


<p><p>
Company Tags
-----
@Amazon
@Bloomberg
@eBay
@Google
@Houzz
@Microsoft
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque, defaultdict
from typing import List, Optional, Dict, Any
import heapq
from helpers.common_methods import CommonMethods


class Solution_Dp:
    def minWindow(self, s1, s2):
        """
            dp[i][j] = "starting index" in s1
                        of the window that ends at i
                        and contains s2[0..j] as a subsequence
            
            since, its subsequence, hence its must that we match the first character of s2 in s1 
            if s1[i] == s2[0] then i will be the start index at least 
            
            dp[i][j] =  i if s1[i] == s2[j] else =1
                        dp[i-1][j-1] if s1[i] == s2[j]
                        dp[i-1][j]   if s1[i] != s2[j] 
                        
            
        """
        n,m = len(s1), len(s2)
        
        dp = [[-1] * m for _ in range(n)]
        
        
        # base case
        for i in range(n):
            if s1[i] == s2[0]:
                dp[i][0] = i
            elif i > 0:
                dp[i][0] = dp[i-1][0]
        
        
        for i in range(1,n):
            for j in range(1,m):
                
                if s1[i] == s2[j]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = dp[i-1][j]
                # dp[i][j] = dp[i-1][j-1] if s1[i] == s2[j] else dp[i-1][j]
                
        
        # dp[0..n-1][m-1] will have a index where constraints satisfied
        best_length = float('inf')
        best_start = -1
        
        for i in range(n):
            if dp[i][m-1] !=-1:
                length = i - dp[i][m-1] + 1
                if best_length > length:
                    best_length = length
                    best_start = dp[i][m-1]
        
        
        return "" if best_start == -1 else s1[best_start: best_start + best_length]
    
class Solution_GreedyBacktracking:
    def minWindow(self, s1, s2):
        best_window_length = float('inf')
        best_start = -1
        
        n,m = len(s1), len(s2)
        
        i = 0 # s1 pointer
        j = 0 # s2 pointer
        
        while i < n:
            
            j = 0
            
            while i < n:
                if s1[i] == s2[j]:
                    j +=1
                    
                    # fully matched ?
                    if j == m:
                        break
                
                i +=1
            
            # did we find s2 fully ?, no, then does not matter to move ahead
            if j < m: 
                break; 
            
            # i will be at valid end of substring, that has s2 as subsequnce
            end = i
            
            # shrink the window untill substring is valid for s2 as subsequence 
            j = m - 1
            
            while i>=0 :
                
                if s1[i] == s2[j]:
                    j -=1
                    
                    # fully satisfied ?
                    if j < 0:
                        break
                    
                i-=1 # go back
            
            # now b/w [i,end] is a valid substring that has s2 as subsequence
            start = i
            window_length = end - start + 1
            if best_window_length > window_length :
                best_window_length, best_start = window_length, start 
            
            i = start + 1
        
        return "" if best_start == -1 else s1[best_start: best_start + best_window_length]
                
        

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
