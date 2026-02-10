class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        """
        Median of a array is either the middle value (odd elements) or average of the middle value (event elements).

        [1,2.3,4,5] -> median = 3 arr[mid]; mid=(0+5)/2 = 2.5 -> 2
        [1,2,3,4] -> median = (2+3)/2 - 2.5 ; mid = 2 -> (arr[mid-1] + arr[mid]) / 2.0


        To avoid joining both the array in sorted list and then finding the median (takes O(m+n) + O(1)), we need to go better than that.

        Intution :
            Key Observation :
                1. Both array are sorted
                2. If we merge both sorted array than their length would be (m+n) which makes median either middle or average of middle value.
                3. In the merged array the first value would be either nums1[0] or nums2[0], similarly last value in merged array would be either nums1[-1] or nums2[-1]
                4. Hence, essentially, we need to find the mid and mid-1 element in merged array without merging nums1 and nums2. This also impllies that if we essentially find what
                    would be the placement index of nums2[i] in nums1, then we either discard left or right elements (counts)
                5. Since we can't do a linar search of placement of the element nums1 to nums2 as it become O(n * logm)
                6. This implies the we need to do BS on n1 as well to achieve O(log(m+n))


        Parition array ( len(n1) < len(n2))
            1. We will do binary search on the range of smaller array and try to find if we pick that element ( means the number of elements from n1 ) then we can choose only remaining element on n2 on left side,
            same applies for right side.
            Example n=1[3,8,9] n2=[1,4,10,11]
                    if we choose 2 element [3,8] on n1 array then we can choose only [1,4] from right array in order to make array symentrical
                    [1,3,4,8,9,10,11]

            This says if mid1 (from n1) then we can choose total / 2 - mid1
            However since we could have total = odd , we change it to (total+1)/2 - mid1
            if the boundary elements follow the increasing order rule ( all left < all right) then we find the middle posisiton
            
            https://www.youtube.com/watch?v=F9c7LpRZWVQ


        """
        if len(nums2) < len(nums1):
            return self.findMedianSortedArrays(nums2, nums1)

        n = len(nums1)
        m = len(nums2)

        # The half-point of the combined array
        # Using (n + m + 1) // 2 ensures the left side contains the median for odd totals
        left_half_len = (n + m + 1) // 2

        mininum = float("-inf")  # if there is no element on left side
        maximum = float("inf")  # if there is no element on right side

        left, right = 0, n  # binary search on smaller array
        while left <= right:

            mid1 = (left + right) // 2

            mid2 = left_half_len - mid1

            # Left and Right elements around the cut
            l1 = nums1[mid1 - 1] if mid1 > 0 else mininum
            r1 = nums1[mid1] if mid1 < n else maximum

            l2 = nums2[mid2 - 1] if mid2 > 0 else mininum
            r2 = nums2[mid2] if mid2 < m else maximum

            if l1 <= r2 and l2 <= r1:
                # if total is even, average of middle
                if (n + m) % 2 == 0:
                    return (max(l1, l2) +  min(r1, r2)) / 2.0
                else:
                    return max(l1, l2)

            elif l1 > r2:
                right = mid1 - 1
            else:
                left = mid1 + 1

        return -1
