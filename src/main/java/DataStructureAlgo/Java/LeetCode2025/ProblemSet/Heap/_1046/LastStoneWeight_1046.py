from typing import List


class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        """Bucket algo"""

        max_element = max(stones)
        buckets = [0] * (max_element + 1)

        for stone in stones:
            buckets[stone] += 1

        i = max_element
        lastj = max_element

        while i > 0:

            if not buckets[i]:
                i -= 1
            else:
                if buckets[i] % 2 == 0:
                    buckets[i] = 0
                    i -= 1
                else:
                    bigger_stone = i
                    smaller_stone = min(i - 1, lastj)

                    while smaller_stone > 0 and buckets[smaller_stone] == 0:
                        smaller_stone -= 1

                    if smaller_stone == 0:
                        return bigger_stone

                    buckets[smaller_stone] -= 1
                    buckets[bigger_stone] -= 1

                    diff_stone = bigger_stone - smaller_stone

                    buckets[diff_stone] += 1

                    lastJ = smaller_stone

                    i = max(diff_stone, smaller_stone)

        return 0  # no stone left
