"""
You are given a string s, return the length of the longest substring that contains at most two distinct characters.

Note: A substring is a contiguous non-empty sequence of characters within a string.

Example 1:

Input: s = "eceba"

Output: 3
Explanation: The substring is "ece" which its length is 3.

Example 2:

Input: s = "ccaabbb"

Output: 5
Explanation: The substring is "aabbb" which its length is 5.

Constraints:

0 <= s.length <= 1,00,000
s consists of English letters.

"""
class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        char_freq = {}
        max_len = 0 
        w_start = 0
        n = len(s)
        k=2

        for w_end in range(n) :
           
            char_freq[s[w_end]] = char_freq.get(s[w_end], 0) + 1
           
            # shrink
            while len(char_freq) > k :
                tmp = s[w_start]
                char_freq[tmp] -=  1
                if char_freq[tmp] == 0:
                    del char_freq[tmp]
                w_start +=1

            # expand
            max_len = max(max_len, w_end - w_start + 1)
            w_end +=1

        return max_len