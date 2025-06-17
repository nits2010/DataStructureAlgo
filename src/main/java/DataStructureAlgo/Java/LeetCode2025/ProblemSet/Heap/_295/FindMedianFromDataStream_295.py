import heapq
from typing import List
from abc import ABC, abstractmethod

class IMedianFinder(ABC):
    @abstractmethod
    def add_num(self, num: int) -> None:
        pass

    @abstractmethod
    def find_median(self) -> float:
        pass

class MedianFinderUsingPQSimplified:
    class MedianFinder(IMedianFinder):
        def __init__(self):
            # max heap of all elements less than or equal to median
            self.max_heap = []
            # min heap of all elements greater than median
            self.min_heap = []
            # median of stream so far
            self.median = 0.0

        def add_num(self, num: int) -> None:
            """
            Time Complexity: O(log n)
            Space Complexity: O(n)
            """
            if not self.max_heap or self.median >= num:
                heapq.heappush(self.max_heap, -num)  # Using negative for max heap
            else:
                heapq.heappush(self.min_heap, num)

            # Balance heaps; make either equal or max_heap has 1 more element
            if len(self.max_heap) > len(self.min_heap) + 1:
                heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
            elif len(self.max_heap) < len(self.min_heap):
                heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))

            self._compute_median()

        def _compute_median(self) -> None:
            """
            Time Complexity: O(1)
            """
            if len(self.max_heap) == len(self.min_heap):
                self.median = (-self.max_heap[0] + self.min_heap[0]) / 2.0
            else:
                self.median = -self.max_heap[0]

        def find_median(self) -> float:
            """
            Time Complexity: O(1)
            """
            return self.median

class MedianFinderUsingPQ:
    class MedianFinder(IMedianFinder):
        def __init__(self):
            # max heap of all elements less than or equal to median
            self.max_heap = []
            # min heap of all elements greater than median
            self.min_heap = []
            # median of stream so far
            self.median = 0.0

        def add_num(self, num: int) -> None:
            """
            Time Complexity: O(log n)
            Space Complexity: O(n)
            """
            size_diff = len(self.max_heap) - len(self.min_heap)

            if size_diff == 1:  # max heap > min heap
                if self.median > num:
                    heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
                    heapq.heappush(self.max_heap, -num)
                else:
                    heapq.heappush(self.min_heap, num)
            elif size_diff == -1:  # max heap < min heap
                if self.median > num:
                    heapq.heappush(self.max_heap, -num)
                else:
                    heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))
                    heapq.heappush(self.min_heap, num)
            else:  # max heap = min heap
                if self.median > num:
                    heapq.heappush(self.max_heap, -num)
                else:
                    heapq.heappush(self.min_heap, num)

            self._compute_median()

        def _compute_median(self) -> None:
            """
            Time Complexity: O(1)
            """
            size_diff = len(self.max_heap) - len(self.min_heap)

            if size_diff == 1:
                self.median = -self.max_heap[0]
            elif size_diff == -1:
                self.median = self.min_heap[0]
            else:
                self.median = (-self.max_heap[0] + self.min_heap[0]) / 2.0

        def find_median(self) -> float:
            """
            Time Complexity: O(1)
            """
            return self.median

def execute(nums: List[int], median_finder: IMedianFinder) -> List[float]:
    medians = []
    for n in nums:
        median_finder.add_num(n)
        medians.append(median_finder.find_median())
    return medians

def test(nums: List[int], expected: List[float]) -> bool:
    print("------------------------")
    print(f"Input Stream: {nums}")
    print(f"Expected Stream: {expected}")

    median_finder = MedianFinderUsingPQ.MedianFinder()
    medians = execute(nums, median_finder)
    equals = medians == expected
    print(f"\nOutput: {medians}\nTest Result: {'Passed' if equals else 'Failed'}")

    median_finder_simplified = MedianFinderUsingPQSimplified.MedianFinder()
    medians = execute(nums, median_finder_simplified)
    equals_simplified = medians == expected
    print(f"\nOutput Simplified: {medians}\nTest Result: {'Passed' if equals_simplified else 'Failed'}")

    return equals and equals_simplified

def main():
    test_cases = [
        ([5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4], [5.0, 10.0, 5.0, 4.0, 3.0, 4.0, 5.0, 6.0, 7.0, 6.5, 7.0, 6.5]),
        ([1, 2, 3], [1.0, 1.5, 2.0])
    ]

    all_passed = True
    for nums, expected in test_cases:
        all_passed &= test(nums, expected)

    print("==========================================")
    print("All Passed" if all_passed else "Something Failed")

if __name__ == "__main__":
    main() 