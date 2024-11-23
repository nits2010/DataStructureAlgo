# [Longest Subarray Sum At Most K] (https://leetcode.com/discuss/interview-question/758045/apple-phone-longest-subarray-sum-at-most-k)

## Intuition

The difficulty in this problem is that there are both positive and negative numbers in the array. Let's understand how
we can use the solution for positive numbers and extend it to handle negative numbers.

### For Positive Numbers

1. We start with a window `[i=0, j=0]`.
2. Keep a running window sum (i.e., prefix sum) and expand `j` slowly.
3. Whenever the running sum exceeds `k`, compress the window from the left by incrementing `i`.
4. This approach works because, given only positive numbers, all subsequent window sums will be larger than the current
   one, and no valid answer is possible if `i` remains fixed.

Here’s a Python function demonstrating this approach:

```
def longest_subarray_with_at_most_k(A, k):
    n = len(A)
    res, si, sj = 0, None, None
    ws = 0  # window sum
    i = 0
    for j in range(n):
        ws += A[j]
        while i < j and ws > k:
            ws -= A[i]
            i += 1
        if (j - i + 1) > res:
            res = (j - i + 1)
            si, sj = i, j
    return (res, A[si:sj+1]) if res != 0 else (res, [])
```

With negative numbers, the problem is that the running window sum is insufficient to determine if we can stop at some j
or not. It's obviously possible that there is a huge negative number after the current j that will reduce the sum to be
below k and have a larger window.
To account for this, we will track just one more thing - For each index j, we will check if the remaining part of the
array (ie., A[j+1:]) is negative or not.
That way, we just need to update the check from ws > k to ws + neg_sum[j] > k. That simply means that (a) the current
window sum exceeds k and (b) the remainder of the array cannot possibly decrease the value any more.
Note that for the all positives case, neg_sum[j] = 0 for all j so it directly reduces to the first version.

```
nums       :  1, -2, 1, 20, 1, -8, -9, 0
neg_cumm_sum: -2, 0, 0, -16, -17, -9, 0, 0
k: 3
i=0,j=0: sum=1, 1+(-2)<=k, max_len = 1
i=0,j=1: sum=-1, -1+0<=k max_len = 2
i=0,j=2: sum=0, 0+0<=k max_len = 3
i=0,j=3: sum=20, 20+(-16)>k max_len = 3
i=1,j=3: sum=19, 19+(-16)<=k max_len = 3
i=1,j=4: sum=20, 20+(-17)<=k max_len = 4
i=1,j=5: sum=12, 12+(-9)<=k max_len = 5
i=1,j=6: sum=3, 3+0<=k max_len = 6
i=1,j=7: sum=3, 3+0<=k max_len=7
```

## Intuition

The goal is to find the largest subarray whose sum is at most `k`. This requires maintaining a running sum of the
elements while expanding and shrinking the window to ensure the sum stays within the given limit. We also leverage a
precomputed array of cumulative negative sums to efficiently handle cases where the sum exceeds `k`.

## Approach

1. **Initialize Variables**:
    - `output` is an array of size 3 to store the maximum subarray length, start index, and end index. It is initialized
      to `Integer.MIN_VALUE` and `-1` for indices.
    - `negCumSum` is an array to store cumulative negative sums from the end of the array towards the beginning.
    - `negSum` is used to track the cumulative negative sum while iterating through the array backwards.
    - `currentSum` to keep track of the current sum of the subarray.
    - `i` and `j` are pointers to represent the current window.

2. **Calculate Cumulative Negative Sums**:
    - Iterate from the end of the array to the beginning and fill the `negCumSum` array with cumulative negative sums.

3. **Expand and Shrink the Window**:
    - Iterate through the array using the pointer `j` to expand the window and include the current element
      in `currentSum`.
    - If the `currentSum` along with the upcoming cumulative negative sum exceeds `k`, shrink the window from the left
      by incrementing `i` until the condition is met.
    - Update the `output` array if the current window is the largest valid subarray found so far.

4. **Return the Result**:
    - Return the `output` array containing the maximum subarray length, start index, and end index.
