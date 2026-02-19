import heapq
import math
import random
from typing import List


class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        if not points or not k:
            return []

        distances = [(math.sqrt(x**2 + y**2), x, y) for x, y in points]

        def partition(low, high):
            if low > high:
                return []

            pivot_index = random.randint(low, high)
            distances[pivot_index], distances[low] = (
                distances[low],
                distances[pivot_index],
            )

            pivot = distances[low][0]
            l = low + 1
            lb = low
            rb = high

            while l <= rb:
                if distances[l][0] < pivot:
                    distances[l], distances[lb] = distances[lb], distances[l]

                    l += 1
                    lb += 1
                elif distances[l][0] > pivot:
                    distances[l], distances[rb] = distances[rb], distances[l]
                    rb -= 1
                else:
                    l += 1

            return (lb, rb)

        low, high = 0, len(distances) - 1
        target = k - 1
        while low < high:
            (lb, rb) = partition(low, high)

            if lb <= target <= rb:  # all elements in [lb,rb] are k closest elements
                return [[item[1], item[2]] for item in distances[:k]]
            elif lb > k:
                high = lb - 1
            elif rb < k:
                low = rb + 1

        return []


class Solution_MaxHeap:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        if not points or not k:
            return []

        distances = [(math.sqrt(x**2 + y**2), x, y) for x, y in points]

        max_heap = []

        for distance in distances:
            heapq.heappush(max_heap, (-distance[0], distance[1], distance[2]))

            if len(max_heap) > k:
                heapq.heappop(max_heap)

        result = []
        while max_heap:
            item = heapq.heappop(max_heap)
            result.append([item[1], item[2]])

        return result


# Time: O(n * log(k))
# Space : O(n+k)
