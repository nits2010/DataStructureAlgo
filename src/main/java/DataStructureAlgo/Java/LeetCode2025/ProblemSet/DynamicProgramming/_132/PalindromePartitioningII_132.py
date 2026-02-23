"""
Author: Nitin Gupta
Date: 22/02/2026
Question Title: 132. Palindrome Partitioning II
Link: https://leetcode.com/problems/palindrome-partitioning-ii/description/
Description: Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters only.

File reference
-----------
Duplicate {@link PalindromePartitioning.java}
Similar {@link PalindromePartitioning_131.py}
extension {@link PalindromePartitioning_131.py}
DP-BaseProblem {@link LongestPailndromeSubstring}
<p><p>
Tags
-----
@hard
@DynamicProgramming

<p><p>
Company Tags
-----
@Amazon
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

"""

# 🔷 Core Idea

Instead of precomputing `palindrome[i][j]`,
we **expand around every center** and update `min_cut` on the fly.

Two types of centers:

1. Odd length palindrome → center at `i`
2. Even length palindrome → center between `i-1` and `i`

---

# 🔷 Define DP

Let:

```
min_cut[i] = minimum cuts for s[0..i]
```

Initialize worst case:

```
min_cut[i] = i
```

---

# 🔷 Expand Around Center (Odd Length)

For center at `i`:

Expand radius `r`:

```
left  = i - r
right = i + r
```

If `s[left] == s[right]`, then substring `[left..right]` is palindrome.

Now update:

If `left == 0`:

```
min_cut[right] = 0
```

Else:

```
min_cut[right] = min(min_cut[right], min_cut[left-1] + 1)
```

---

# 🔷 Expand Around Center (Even Length)

For center between `i-1` and `i`:

```
left  = i-1
right = i
```

Then expand similarly.

Same update rule.

---

# 🔷 Why This Works

You are basically saying:

> If `[left..right]` is palindrome,
> then cut before `left` and add 1.

That matches the recurrence:

[
dp[i] = \min(dp[j-1] + 1)
]

But instead of checking all `j` with a table,
we discover palindromes dynamically.

---

---

# 🔷 Complexity

Time:
Each expansion worst case O(n)
For n centers → O(n²)

Space:
Only `min_cut` array → O(n)

🔥 This is the optimal clean solution.

---

# 🔷 Important Insight

You just transformed:

```
DP on substrings
```

into

```
DP on centers
```

That’s a higher-level understanding.

---


"""
class Solution_ExpandCenter:
    def minCut(self, s: str) -> int:
        n = len(s)

        # min_cut[i] defines the minimum cut needed for substring [0...i]
        min_cut = [i for i in range(n)]

        def palindrome(left, right):
            while left >= 0 and right < n and s[left] == s[right]:
                if left == 0:  # means [0..right] is palindrome
                    min_cut[right] = 0
                else:
                    # [0...left-1] [left...right]
                    min_cut[right] = min(min_cut[right], min_cut[left - 1] + 1)

                left -= 1
                right += 1

        for center in range(n):

            # even length
            left = right = center
            palindrome(left, right)

            # odd length
            left = center - 1
            right = center
            palindrome(left, right)

        return min_cut[n - 1]


class Solution_DynamicProgramming:
    def minCut(self, s: str) -> int:
        n = len(s)

        def precompute_palindrome():

            palindrome = [[False] * n for _ in range(n)]

            # 1 length
            for i in range(n):
                palindrome[i][i] = True

            for length in range(2, n + 1):
                for i in range(n - length + 1):
                    j = i + length - 1

                    if s[i] == s[j]:
                        if length == 2 or palindrome[i + 1][j - 1]:
                            palindrome[i][j] = True

            return palindrome

        palindrome = precompute_palindrome()

        # min_cut[i] = Minimum number of cut needed if str[0...i] is palindrome
        min_cut = [i for i in range(n)]
        min_cut[0] = 0  # no cut require for 1 char string [0,0]

        for i in range(1, n):
            if palindrome[0][i]:
                min_cut[i] = 0
            else:
                for j in range(i):
                    # Why j+1? Because: If we cut at j, then: [0..j] | [j+1..i] Left side requires min_cut[j] cuts Right side adds 1 new cut
                    if palindrome[j + 1][i]:
                        min_cut[i] = min(min_cut[i], 1 + min_cut[j])

        return min_cut[n - 1]



def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_DynamicProgramming().minCut(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["DynamicProgramming", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_ExpandCenter().minCut(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["ExpandCenter", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("aab", 1),
                   test("a", 0),
                   test("ab", 1)]

    CommonMethods.print_all_test_results(tests)
