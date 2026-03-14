from typing import List


class Solution:

    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0

        n = len(nums)
        if n == 1:
            return nums[0]

        if n == 2:
            return max(nums[0], nums[1])

        """ imagine array as 2 different array [0,n-1] excluding last element and [1,n] excluding first element """

        first_a, second_a = nums[0], max(nums[0], nums[1])
        first_b, second_b = nums[1], max(nums[1], nums[2])

        l = 2
        r = 3

        while l < n - 1 and r < n:
            second_a, first_a = max(nums[l] + first_a, second_a), second_a
            second_b, first_b = max(nums[r] + first_b, second_b), second_b
            l += 1
            r += 1

        return max(second_a, second_b)


class Solution_Range:
    def rob_range(self, nums: List[int], start, end) -> int:
        first = nums[start]
        second = max(nums[start + 1], nums[start])

        for i in range(start + 2, end + 1):
            _max = max(first + nums[i], second)
            first = second
            second = _max

        return max(first, second)

    def rob(self, nums: List[int]) -> int:
        if not nums:
            return 0

        n = len(nums)
        if n == 1:
            return nums[0]

        if n == 2:
            return max(nums[0], nums[1])

        return max(
            self.rob_range(nums, 0, n - 2),
            self.rob_range(nums, 1, n - 1),
        )
