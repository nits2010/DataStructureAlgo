import heapq
import random
from typing import List

# Time : O(n*logk)
# Space: O(k)
class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        min_heap = nums[:k]
        heapq.heapify(min_heap)

        for item in nums[k:]:
            if item > min_heap[0]:
                heapq.heappop(min_heap)
                heapq.heappush(min_heap, item)

        return min_heap[0]


# Time : O(n)
# Space: O(1)
class Solution_QuickSelect:
    def findKthLargest(self, nums: List[int], k: int) -> int:

        def parition(low, high):

            pivot_index = random.randint(low, high)
            nums[low], nums[pivot_index] = nums[pivot_index], nums[low]

            l = low + 1
            pivot = nums[low]
            lb = low
            rb = high

            while l <= rb:

                if nums[l] < pivot:
                    nums[l], nums[lb] = nums[lb], nums[l]
                    l += 1
                    lb += 1
                elif nums[l] > pivot:
                    nums[rb], nums[l] = nums[l], nums[rb]
                    rb -= 1
                else:
                    l += 1

            return (lb, rb)

        # kth largest = n - k smallest

        n = len(nums)
        target = n - k

        low, high = 0, n - 1

        while low < high:
            (lb, rb) = parition(low, high)

            if lb <= target <= rb:
                break

            if lb > target:
                high = lb - 1
            elif rb < target:
                low = rb + 1

        return nums[target]
