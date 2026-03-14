"""
Author: Nitin Gupta
Date: ${DATE}
Question Title:  639. Decode Ways II
Link: https://leetcode.com/problems/decode-ways-ii/description/
Description: A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.

Given a string s consisting of digits and '*' characters, return the number of ways to decode it.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:

Input: s = "*"
Output: 9
Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
Hence, there are a total of 9 ways to decode "*".
Example 2:

Input: s = "1*"
Output: 18
Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
Example 3:

Input: s = "2*"
Output: 15
Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".
 

Constraints:

1 <= s.length <= 105
s[i] is a digit or '*'.
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@DynamicProgramming

<p><p>
Company Tags
-----
@Microsoft

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


"""
        This is similar to decodeWays I

        Recape
        we can decode any single digit `x` if x=[1,9] or any two digit xy if x=[1] then y=[1,9] Or x[2] then y=[1,6]
        hence decodeWays
        count += decode(i+1) + ( decode(i+2) if 10<=s[i:i+2]<=26)

        Now, the additional conditions we have is '*' which represent [1,9] possibitlies.
        This leads following patterns

        A. For single digit case:
            case: s[i]=d
                Then its a single digit, it will generate 1 combination
                    count += ways(i+1)
            case: s[i]=*
                Then we can replace * with [1,9] possibilties hence it generate 9 combinations
                    count += 9 * ways(i+1)


        B. For 2 digit case:
            case: s[i]=*, s[i+1]=*
                The first digit can be s[i] = [1,9] and second digit could be s[i+1] = [1,6] that makes total 9+6 = 15 combinations
                    count += 15 * ways(i+2)
            case: s[i]=*, s[i+1]=d
                The first digit can be s[i] = [1,9] but only valid are [1,2] because 3d...9d are invalid that makes 2 combination, out of which it depends on 'd'
                    if d=[1,6] then we can put [1,2] both as they make `11', '12', ...'16' and '21'...'26' as valid -> 2 combinations
                    if d=[7,9] then we can put only [1] as '17',...'19' is valid -> 1 combinations
                That makes total 3 combinations.
                    count += 2 * ways(i+2) if s[i+1] = 1
                or  count += 1 * ways(i+2) if s[i+1] = 2

            case: s[i]=d, s[i+1]=*
                The first digit can only be [1,2] and * can be repalced with either [1,9] or [1,6].
                    if s[i] = 1 then [1,9]
                        count += 9*ways(i+2)
                    if s[i] = 2 then [1,6]
                        count += 6 * ways(i+2)

            case: s[i]=d, s[i+1]=d
                This is similar to original
                    count += ways(i+2) if 10<=s[i,i+2]<=26
                    
            Time/Space: O(n) / O(n) or (1)

"""


class Solution_BottomUp:
    def numDecodings(self, s: str) -> int:
        MOD = 10**9 + 7

        n = len(s)
        # dp0 is empty string
        # dp1 the first letter in string
        dp0, dp1 = 1, 0

        if s[0] == "*":  # *
            dp1 = 9
        elif s[0] != "0":  # [1,9]
            dp1 = 1

        for i in range(1, n):
            count = 0

            # For Single digit
            # case: s[i] = * or d
            if s[i] == "*":
                count += 9 * dp1
            elif s[i] != "0":  # [1,9]
                count += dp1

            # for double digit
            a, b = s[i-1], s[i]

            # **
            if a == b == "*":
                count += 15 * dp0

            # *d
            elif a == "*":
                # d=[1,6]
                if 0 <= int(b) <= 6:
                    count += 2 * dp0
                else:
                    # d=[7,9]
                    count += dp0
            # d*
            elif b == "*":
                if a == "1":
                    count += 9 * dp0
                elif a == "2":
                    count += 6 * dp0
            else:
                if 10 <= int(a + b) <= 26:
                    count += dp0

            count = count % MOD
            dp0, dp1 = dp1, count
        return dp1


class Solution_TopDown:
    def numDecodings(self, s: str) -> int:
        memo = {}
        MOD = 10**9 + 7

        def ways(i):
            if i in memo:
                return memo[i]

            if i == len(s):  # empty string
                return 1

            if s[i] == "0":
                return 0

            count = 0

            # For Single digit
            # case: s[i] = d
            if s[i] == "*":
                count += 9 * ways(i + 1)
            else:
                count += ways(i + 1)

            # for double digit
            if i + 1 < len(s):
                a, b = s[i], s[i + 1]

                # **
                if a == b == "*":
                    count += 15 * ways(i + 2)
                # *d
                elif a == "*":
                    # d=[1,6]
                    if 0 <= int(b) <= 6:
                        count += 2 * ways(i + 2)
                    else:
                        # d=[7,9]
                        count += ways(i + 2)
                # d*
                elif b == "*":
                    if a == "1":
                        count += 9 * ways(i + 2)
                    elif a == "2":
                        count += 6 * ways(i + 2)
                else:
                    if 10 <= int(a + b) <= 26:
                        count += ways(i + 2)

            memo[i] = count % MOD
            return memo[i]

        return ways(0)


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
    tests: List = [test("*", 9),
                   test("1*", 18),
                   test("2*", 15),
                   test("11*1", 31),
                   test("3*", 9),
                   test("*2", 11),
                   test("*1*1*0", 404)]

    CommonMethods.print_all_test_results(tests)
