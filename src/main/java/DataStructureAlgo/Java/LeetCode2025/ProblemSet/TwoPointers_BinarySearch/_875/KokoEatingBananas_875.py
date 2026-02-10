from typing import List
import math

class Solution_Inplace:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        low = 1
        high = max(piles)
        minimum = high
        while low <= high:
            mid = (low + high) // 2

            if sum(math.ceil(p/mid) for p in piles) <= h:
                minimum = min(minimum, mid)
                high = mid - 1
            else:
                low = mid + 1

        return minimum



class Solution_1:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        def isPossible(piles, mid, h) -> bool:
            hours = 0
            for p in piles:
                if p <= mid:
                    hours += 1
                elif p > mid:
                    hours += math.ceil(p / mid)

                if hours > h:
                    return False

            return hours <= h

        if len(piles) == 1:
            return math.ceil(piles[0] / h)

        low = 1
        high = max(piles)
        minimum = high
        while low <= high:
            mid = (low + high) // 2

            if isPossible(piles, mid, h):
                minimum = min(minimum, mid)
                high = mid - 1
            else:
                low = mid + 1

        return minimum
