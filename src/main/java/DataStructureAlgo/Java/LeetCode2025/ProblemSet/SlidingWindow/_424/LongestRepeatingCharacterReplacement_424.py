from typing import defaultdict

class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        freq = defaultdict(int)
        most_freq_count = 0
        start , end = 0, 0
        result = 0 

        for end in range(len(s)):

            length = end - start + 1

            freq[ord(s[end])] +=1 

            most_freq_count = max (most_freq_count, freq[ord(s[end])])

            # shrink
            while length - most_freq_count > k:
                """
                    means in map there are character which cant be replaced as we don't have sufficient k avaialble (regardless its single char or different)
                """
                freq[ord(s[start])] -=1
                start +=1
                length = end - start + 1
            
            result = max(result, length)
        
        return result
            



