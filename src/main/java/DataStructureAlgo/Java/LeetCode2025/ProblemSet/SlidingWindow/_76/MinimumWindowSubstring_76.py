from collections import defaultdict
from typing import Counter

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        if len(s) < len(t):
            return ""
        
        need = Counter(t)
        window = defaultdict(int)
        start = 0
        formed = 0 # number of different character formed that is required in T
        required = len(need)
        min_length = float('inf')
        result = ""

        for end, char in enumerate(s) :

            # incude only if needed
            if char in need: 

                # expand the window
                window[char] +=1

                # if we found the required number of freq of this char
                if window[char] == need[char]: 
                    formed +=1
                

                # if all are found; try shrinking
                while formed == required:
                    
                    # cache the result
                    if min_length > (end - start + 1):
                        min_length = end - start + 1
                        result = s[start:end+1]
                    
                    # remove from start
                    start_char = s[start]
                    window[start_char] -=1

                    # the leaving character is required and now removed, formed char will reduce too 
                    if start_char in need and window[start_char] < need[start_char]:
                        formed -=1 
                    
                    start +=1
        

        return result





