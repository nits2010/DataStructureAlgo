"""
Author: Nitin Gupta
Date: 3/9/2026
Question Title: 91. Decode Ways
Link: https://leetcode.com/problems/decode-ways/description/
Description: You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.

The test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: s = "12"

Output: 2

Explanation:

"12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: s = "226"

Output: 3

Explanation:

"226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:

Input: s = "06"

Output: 0

Explanation:

"06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.

 

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).

File reference
-----------
Duplicate {@link DecodeWays.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@DynamicProgramming
@String

<p><p>
Company Tags
-----
@Amazon
@Cisco
@Google
@Lyft
@Facebook
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import defaultdict, deque
import heapq
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution_BottomUp:
    def numDecodings(self, s: str) -> int:
        """
            Given constraint of the problem where A->1 and Z->26 we can only map either single digit(1-9) or two digit(10-26) to alphabet. 
        Means, we only try either of the case. 

        Now, we can divide the given string keeping above constraint in mind,
        1. Count decoding with single digit ( avoding "0" ) 
        2. Count decoding with two digit    
            only two possiblities
                10-19 -> first digit is 1 and second is from [1,9]
                20-26 -> first digit is 2 and second is [1,6] only. 

        Since many overlapping combination, memo it. Top Down

        memo[i] = denotes the decoding ways till ith index s[:i]
                = 1 ; i == 0 ; empty string
                = 1 ; i == 1 ; only 1 length string, only 1 way to decode
                = memo[i] + memo[i-1] + memo[i-2] if (i-1,i-2) form a valid letter 

        Time: O(n) we touch each part only 2 time max (either single digit or 2 digit combination)
        Space: O(n)

        """
        n = len(s)
        dp = [0] * (n+1)
        dp[0] = 1
        dp[1] = 1 if s[0] != "0" else 0

        for i in range(2, n+1):
            # single digit
            if s[i-1] != "0":
                dp[i] += dp[i-1]

            # two digit
            digit = int(s[i-2:i])
            if 10 <= digit <= 26:
                dp[i] += dp[i-2]

        return dp[n]


class Solution_TopDown:
    def numDecodings(self, s: str) -> int:
        """
        Given constraint of the problem where A->1 and Z->26 we can only map either single digit(1-9) or two digit(10-26) to alphabet. 
        Means, we only try either of the case. 

        Now, we can divide the given string keeping above constraint in mind,
        1. Count decoding with single digit ( avoding "0" ) 
        2. Count decoding with two digit    
            only two possiblities
                10-19 -> first digit is 1 and second is from [1,9]
                20-26 -> first digit is 2 and second is [1,6] only. 
        3. If you consider entire string as a while, i == len(s) then there will be only 1 way to decode this is because, in recursion tree we hit end of the string and that string is "empty string" which 
        can be decoded only 1 way. 

        Since many overlapping combination, memo it. Top Down
        Time: O(n) we touch each part only 2 time max (either single digit or 2 digit combination)
        Space: O(n)
        """
        memo = {}

        def dfs(start):
            if start in memo:
                return memo[start]

            # end of string, the rest of string s[start:] would be empyt and decoding empty string is possible only 1 way
            if start == len(s):
                return 1

            if s[start] == "0":
                return 0

            count = dfs(start+1)  # 1 length

            if start < len(s) - 1:
                if s[start] == "1" or (s[start] == "2" and s[start+1] < "7"):  # 10, 11...26
                    count += dfs(start+2)
            memo[start] = count
            return count

        return dfs(0)


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_BottomUp().numDecodings(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["BottomUp", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_TopDown().numDecodings(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["TopDown", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("12", 2),
                   test("226", 3),
                   test("06", 0)]

    CommonMethods.print_all_test_results(tests)
