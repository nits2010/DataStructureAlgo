# [Longest sub-array having sum k](https://www.geeksforgeeks.org/longest-sub-array-sum-k/)

## Intuition

The goal is to find the longest subarray whose sum is exactly equal to a given target sum `k`. This can be efficiently
solved using prefix sums and a hashmap to store the sums seen so far along with their corresponding indices. The hashmap
helps in quickly checking if a required subarray sum has been seen before.

The key observation here is that if the sum of elements from the start of the array to the current index `i`
is `prefixSum` and we know there exists an earlier prefix sum `prefixSum - k` at some index `j`, then the sum of the
elements between indices `j+1` and `i` is exactly `k`. This allows us to track the longest subarray satisfying this
condition.

## Approach

### Step-by-Step Algorithm

1. **Initialization**:
    - Initialize `maxLength` to `0` to keep track of the longest subarray length found.
    - Initialize a hashmap `map` to store pairs of prefix sum and their indices.
    - Initialize `prefixSum` to `0` to keep track of the current prefix sum.
    - Initialize `start` and `end` to `0` to store the starting and ending indices of the longest subarray found.

2. **Iterate through the Array**:
    - For each element `arr[i]` in the array:
        - Add the current element to `prefixSum`.
        - Check if `prefixSum` is equal to `k`:
            - If true, update `maxLength` to `i + 1` and set `end` to `i` since this subarray starts from the beginning
              of the array.
        - Check if `prefixSum - k` has been seen before (i.e., if it exists in the hashmap `map`):
            - If true, it means there exists a subarray that sums to `k` ending at index `i`.
              Update `maxLength`, `start`, and `end` if this subarray is longer than the previously found subarrays.
        - Add the current `prefixSum` and its index `i` to the hashmap `map`.

3. **Return the Result**:
    - Return an array containing `maxLength`, `start`, and `end`.

### Complexity Analysis

- **Time Complexity**: `O(n)`, where `n` is the length of the array. This is because we traverse the array once and each
  operation (insertion and lookup in the hashmap) is `O(1)` on average.
- **Space Complexity**: `O(n)` to store the prefix sums and their indices in the hashmap.
