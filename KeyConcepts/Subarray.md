## Subarrays in Arrays

### 1. What is a Subarray?

A **subarray** is a contiguous part of an array.  It is a sequence of elements from the original array that are adjacent to each other.

**Example:**

For the array `[1, 2, 3, 4, 5]`, the following are subarrays:

* `[2, 3]`
* `[1, 2, 3, 4]`
* `[5]`
* `[1]`
* `[1, 2, 3, 4, 5]`

The following are **NOT** subarrays:

* `[1, 3, 5]` (not contiguous)
* `[2, 4]` (not contiguous)

### 2. How many subarrays are there for an n-size array?

In an array of size *n*, there are $\frac{n(n+1)}{2}$ total subarrays.

**Explanation:**

A subarray is defined by its starting and ending indices.
* There are *n* choices for the starting index (0 to n-1).
* For each starting index *i*, there are (n - i) choices for the ending index (i to n-1).

If we sum this up, we get: n + (n-1) + (n-2) + ... + 1, which is the sum of the first n natural numbers, and is equal to  $\frac{n(n+1)}{2}$.

### 3. Subarrays of a specific size in an n-size array

* **1-size subarrays:** In an array of size *n*, there are *n* subarrays of size 1.
* **2-size subarrays:** In an array of size *n*, there are *n - 1* subarrays of size 2.
* **3-size subarrays:** In an array of size *n*, there are *n - 2* subarrays of size 3.
* ...
* **k-size subarrays:** In an array of size *n*, there are *n - k + 1* subarrays of size *k* (where 1 ≤ k ≤ n).
#### example problem:

### 4. Subarrays of at least a specific size in an n-size array

* **At least 1-size subarrays:** The number of subarrays of at least size 1 in an array of size *n* is  $\frac{n(n+1)}{2}$.

* **At least 2-size subarrays:** The number of subarrays of at least size 2 in an array of size *n* is $\frac{n(n-1)}{2}$.

* **At least 3-size subarrays:** The number of subarrays of at least size 3 in an array of size *n* is $\frac{(n-1)(n-2)}{2}$.

* ...

* **At least k-size subarrays:** The number of subarrays of at least size *k* in an array of size *n* is $\frac{(n - k + 1)(n - k + 2)}{2}$.
#### example problem: [413. Arithmetic Slices](https://leetcode.com/problems/arithmetic-slices/description)
