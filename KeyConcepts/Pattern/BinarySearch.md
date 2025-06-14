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
        int leftmost = -1; // Stores the leftmost index found so far

        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] == target) {
                leftmost = mid; // Found a potential leftmost index
                high = mid - 1; // Try to find an even earlier one in the left half
            } else if (nums[mid] < target) {
                low = mid + 1; // The Target is in the right half
            } else { // nums[mid] > target
                high = mid - 1; // The Target is in the left half
            }
        }
        return leftmost;
    }
}

//second variation
class Solution {
    public int findLeftmost(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] < target) { // if array is decreasing then reverse this condition to nums[mid] > target
                low = mid + 1;   // The Target is in the right half
            } else { // nums[mid] >= target
                high = mid;  // The Target is in the left half or mid itself
            }
        }
        return (target != nums[low]) ? -1 : low;
    }
}

```
#### Binary Search for the Rightmost (Last) Occurrence
```java
class Solution {
    public int findRightmost(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int rightmost = -1; // Stores the rightmost index found so far

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                rightmost = mid; // Found a potential rightmost index
                low = mid + 1;   // Try to find an even later one in the right half
            } else if (nums[mid] < target) {
                low = mid + 1;   // The Target is in the right half
            } else { // nums[mid] > target
                high = mid - 1;  // The Target is in the left half
            }
        }
        return rightmost;
    }
}

//second variation
class Solution {
    public int findRightmost(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;
            
            if(nums[mid] > target){
                high = mid - 1;  // The Target is in the left half
            }else { // nums[mid] <= target
                low = mid; // The Target is in the right half or mid itself
            }
        }
        return (target != nums[low]) ? -1 : low;
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

[Pattern 21_Sorting&Searching.md](../../DSA_Pattern/Pattern%2021_Sorting&Searching.md)