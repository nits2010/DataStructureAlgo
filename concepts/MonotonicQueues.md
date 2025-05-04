# Monotonic Deques Explained

While standard queues don't enforce monotonicity, we can use a **deque** (double-ended queue) to achieve monotonic increasing or decreasing behavior. A deque allows insertions and deletions from both ends, which is essential for maintaining the monotonic property.

## Monotonic Increasing Deque

**Definition:** A deque where the elements are in non-decreasing order from the front to the back.

**How to Maintain:**

1.  Initialize an empty deque.
2.  When adding a new element (`current_element`) to the **back** of the deque:
    * While the deque is not empty AND the element at the **back** of the deque is greater than `current_element`:
        * Remove the element from the **back** of the deque.
    * Add `current_element` to the **back** of the deque.

**Example:**

Input array: `[3, 1, 4, 1, 5, 9, 2, 6]`

| Step | Current Element | Deque (front to back) | Explanation                                                                     |
| :--- | :-------------- | :-------------------- | :------------------------------------------------------------------------------ |
| 1    | 3               | `[3]`               | Add 3 to the back.                                                              |
| 2    | 1               | `[1]`               | `3 > 1`, remove 3 from back, add 1 to back.                                    |
| 3    | 4               | `[1, 4]`            | `1 < 4`, add 4 to back.                                                         |
| 4    | 1               | `[1, 1]`            | `4 > 1`, remove 4 from back, `1 >= 1`, add 1 to back.                            |
| 5    | 5               | `[1, 1, 5]`         | `1 < 5`, add 5 to back.                                                         |
| 6    | 9               | `[1, 1, 5, 9]`      | `5 < 9`, add 9 to back.                                                         |
| 7    | 2               | `[1, 1, 2]`         | `9 > 2`, remove 9, `5 > 2`, remove 5, `1 < 2`, add 2.                           |
| 8    | 6               | `[1, 1, 2, 6]`      | `2 < 6`, add 6 to back.                                                         |

Final Monotonic Increasing Deque: `[1, 1, 2, 6]` (front to back)

## Monotonic Decreasing Deque

**Definition:** A deque where the elements are in non-increasing order from the front to the back.

**How to Maintain:**

1.  Initialize an empty deque.
2.  When adding a new element (`current_element`) to the **back** of the deque:
    * While the deque is not empty AND the element at the **back** of the deque is less than `current_element`:
        * Remove the element from the **back** of the deque.
    * Add `current_element` to the **back** of the deque.

**Example:**

Input array: `[3, 1, 4, 1, 5, 9, 2, 6]`

| Step | Current Element | Deque (front to back) | Explanation                                                                     |
| :--- | :-------------- | :-------------------- | :------------------------------------------------------------------------------ |
| 1    | 3               | `[3]`               | Add 3 to the back.                                                              |
| 2    | 1               | `[3, 1]`            | `3 > 1`, add 1 to back.                                                         |
| 3    | 4               | `[4]`               | `1 < 4`, remove 1, `3 < 4`, remove 3, add 4.                                    |
| 4    | 1               | `[4, 1]`            | `4 > 1`, add 1 to back.                                                         |
| 5    | 5               | `[5]`               | `1 < 5`, remove 1, `4 < 5`, remove 4, add 5.                                    |
| 6    | 9               | `[9]`               | `5 < 9`, remove 5, add 9.                                                      |
| 7    | 2               | `[9, 2]`            | `9 > 2`, add 2 to back.                                                         |
| 8    | 6               | `[9, 6]`            | `2 < 6`, remove 2, `9 > 6`, add 6.                                             |

Final Monotonic Decreasing Deque: `[9, 6]` (front to back)

## When and How to Use Monotonic Deques

Monotonic deques are particularly useful for solving problems related to **sliding windows** where you need to efficiently find the maximum or minimum element within the current window.

1.  **Maximum/Minimum Element in Sliding Window:**
    * **Problem:** Given an array and a window size `k`, find the maximum (or minimum) element in each sliding window of size `k`.
    * **How:**
        * Use a monotonic decreasing deque to track the indices of potential maximum elements. The front of the deque will always hold the index of the maximum element in the current window.
        * Use a monotonic increasing deque to track the indices of potential minimum elements. The front of the deque will always hold the index of the minimum element in the current window.
        * As the window slides, you need to:
            * Remove indices from the front of the deque that are now outside the current window.
            * Maintain the monotonic property at the back of the deque when adding the new element's index.

**Key Idea:**

By maintaining the monotonic property within the deque, you ensure that the front element is always the maximum or minimum within the current window, allowing for O(1) retrieval of this value. The overall time complexity for processing the entire array remains O(n) because each element is added and removed from the deque at most once.

## Java Template for Monotonic Deque

```java
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;

public class MonotonicDeque {

    public static Deque<Integer> buildIncreasingDeque(int[] arr) {
        Deque<Integer> deque = new LinkedList<>();
        for (int num : arr) {
            while (!deque.isEmpty() && deque.peekLast() > num) {
                deque.removeLast();
            }
            deque.addLast(num);
        }
        return deque;
    }

    public static Deque<Integer> buildDecreasingDeque(int[] arr) {
        Deque<Integer> deque = new LinkedList<>();
        for (int num : arr) {
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.removeLast();
            }
            deque.addLast(num);
        }
        return deque;
    }

    public static void main(String[] args) {
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6};

        Deque<Integer> increasingDeque = buildIncreasingDeque(input);
        System.out.println("Monotonic Increasing Deque: " + Arrays.toString(increasingDeque.toArray())); // Front to back

        Deque<Integer> decreasingDeque = buildDecreasingDeque(input);
        System.out.println("Monotonic Decreasing Deque: " + Arrays.toString(decreasingDeque.toArray())); // Front to back
    }
}