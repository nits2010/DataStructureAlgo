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

            if (nums[mid] == target) {
                leftmost = mid;
                high = mid - 1; // Continue searching in the left half
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
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

            if (nums[mid] == target) {
                rightmost = mid;
                low = mid + 1; // Continue searching in the right half
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
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



Here's a list of the LeetCode problem titles and their corresponding problem numbers for the binary search problems previously discussed:

1.  **Binary Search** (#704)
2.  **First Bad Version** (#278)
3.  **Search in Rotated Sorted Array** (#33)
4.  **Find First and Last Position of Element in Sorted Array** (#34)
5.  **Search Insert Position** (#35)
6.  **Find Peak Element** (#162)
7.  **Search a 2D Matrix** (#74)
8.  **Find Minimum in Rotated Sorted Array** (#153)
9.  **Koko Eating Bananas** (#875)
10. **Capacity To Ship Packages Within D Days** (#1011)
11. **Find K Closest Elements** (#658) 
12. **Search in a Sorted Array of Unknown Size** (#702) 
13. **Find the Duplicate Number** (#287) 
14. **Split Array Largest Sum** (#410) 
15. **Single Element in a Sorted Array** (#540) 
