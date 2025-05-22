# Binary Search Templates

#### Standard Binary Search

```java
class Solution {
    public int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1; // Target not found
    }
}
```
#### Binary Search for the Leftmost (First) Occurrence
```java
class Solution {
    public int findLeftmost(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int leftmost = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] <= target) {
                leftmost = mid;
                high = mid - 1; // Continue searching in the left half
            }  else {
                low = mid + 1;
            }
        }
        return leftmost;
    }
}
```
#### Binary Search for the Rightmost (Last) Occurrence
```java
class Solution {
    public int findRightmost(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int rightmost = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] <= target) {
                rightmost = mid;
                low = mid + 1; // Continue searching in the right half
            }  else {
                high = mid - 1;
            }
        }
        return rightmost;
    }
}
```
#### Binary Search for the Floor of a Number
```java
class Solution {
    public int floor(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int floor = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                floor = mid;
                low = mid + 1;
            }
        }
        return floor;
    }
}
```

#### Binary Search for the Ceil of a Number
```java
class Solution {
    public int ceil(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ceil = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                ceil = mid;
                high = mid - 1;
            }
        }
        return ceil;
    }
}
```

#### Binary search to find the pivot index in a rotated array [non-duplicate elements]

```java
//[3,4,5,1,2] here pivot is 1 at index 3
class Solution {
    private int pivotIndex(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] > nums[high]) //[3,4,5,1,2] mid = 5 then 5 > 2, pivot lies in right side
                low = mid + 1;
            else
                high = mid; //mid could be pivoted itself
        }
        return low;
    }
}
```


#### Binary search to find the pivot index in a rotated array [duplicate elements]
only one extra condition to be added as 
```java
// this is because it could possible that all elements [mid, high] are same or there are elements in b/w [mid,high] which is not same, so we have to find the first index of nums[high] b/w [mid,high] 
// Example : [3,3,1,3] l = 0, h = 3 m = 1 which is nums[mid] = 3 and nums[high] = 3 which is equal however if you see the elements b/w [mid,high] are not same [3,1,3] so we have to reduce search space b/w 
// [low, high-1]
// Example : [10,1,10,10,10] l = 0, h = 4, m = 2 nums[mid] = 10, nums[high] = 10 whcih is same and also [mid,high] elements are [10,10,10] which means the search space b/w [mid,high]
// isn't valid, reducing it to [low,high-1] makes low = 0, high = 3 m = 1 and nums[mid] = 1, nums[high] = 10 and 1 != 10 lands you to original problem. 

if(nums[mid] == nums[high])
    high = high - 1;
```

```java
//[3,4,5,1,2] here pivot is 1 at index 3
class Solution {
    private int pivotIndex(int []nums){
        int low = 0, high = nums.length - 1;

        while (low < high){
            int mid = (low + high) >>> 1;
            
            if(nums[mid] == nums[high])
                high = high - 1;
            else if(nums[mid] > nums[high]){
                low = mid + 1;
            }else {
                high = mid;
            }
        }
        return low;
    }
}
```

#### Binary search for insert position 

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] == target)
                return mid;

            if (nums[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }
}
```


#### Binary search for 2D array, row sorted, column sorted. Column's first element > last row's last element

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        //instead of treating it as 2d array, treat it as 1d array, then apply binary search
        int low = 0, high = m * n - 1;

        while (low <= high) {

            int mid = (low + high) >>> 1;

            int row = mid / n; // Since each row has 'n' elements, this gives the row index of the mid element
            int col = mid % n; // The remainder gives the column index within that row

            if (matrix[row][col] == target)
                return true;

            if (matrix[row][col] > target)
                high = mid - 1;
            else
                low = mid + 1;

        }
        return false;
    }
}
```

### Important tip
1. When you use 
```
low = 0, high = n-1
while(low <= high)
```
then make sure that you never set **neither low or high to mid**, as this will end up in infinite loop until you have an extra condition to break before. 

2. When you use 
```
low = 0, high = n-1
while(low < high)
```
Then you can set **either low or high to mid-** considering that mid could be your optimal solution. And at end, return low or high.

Here's a list of the LeetCode problem titles and their corresponding problem numbers for the binary search problems previously discussed:

1. [LeetCode 704 - Binary Search](https://leetcode.com/problems/binary-search/) (Easy)
2. [LeetCode 35 - Search Insert Position](https://leetcode.com/problems/search-insert-position/) (Easy)
3. [LeetCode 34 - Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) (Medium)
4. [LeetCode 852 - Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) (Easy)
5. [LeetCode 162 - Find Peak Element](https://leetcode.com/problems/find-peak-element/) (Medium)
6. [LeetCode 153 - Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) (Medium)
7. [LeetCode 154 - Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/) (Hard)
8. [LeetCode 74 - Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) (Medium)
9. [LeetCode 240 - Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) (Medium)
10. [LeetCode 410 - Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) (Hard)