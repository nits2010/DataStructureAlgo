class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        """
            1 <= nums[i] <= n
            nums.length == n + 1
            1 <= n <= 10000



            nums = [1,2,3,2,2]
            fast = 0 -> fast = nums[nums[fast]]
            slow = 0 -> slow = nums[slow]

            fast = 0 -> 2 -> 2
            slow = 0 -> 1 -> 2


            [1,2,3,4,4]
            fast = 0 -> 3 -> 4 -> 4
            slow = 0 -> 2 -> 3 -> 4

        """
        if not nums:
            return 0

        fast = nums[0]
        slow = nums[0]

        # detect cycle
        while True:
            slow = nums[slow]
            fast = nums[nums[fast]]

            if slow == fast:
                break

        if slow == fast:
            slow = nums[0]
            while slow != fast:
                slow = nums[slow]
                fast = nums[fast]
        
        return slow

