package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._1095;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/28/2025
 * Question Title: 1095. Find in Mountain Array
 * Link: https://leetcode.com/problems/find-in-mountain-array/description/
 * Description: (This problem is an interactive problem.)
 * <p>
 * You may recall that an array arr is a mountain array if and only if:
 * <p>
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
 * <p>
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 * <p>
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: mountainArr = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 * <p>
 * Input: mountainArr = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= mountainArr.length() <= 104
 * 0 <= target <= 109
 * 0 <= mountainArr.get(index) <= 109
 * File reference
 * -----------
 * Duplicate {@link }
 * Similar {@link}
 * extension  {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._852.PeakIndexInAMountainArray_852}
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @hard
 * @Array
 * @BinarySearch
 * @Interactive
 *
 * <p><p>
 * Company Tags
 * -----
 * @Apple
 * @Google
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class FindInMountainArray_1095 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 4, 5, 3, 1}, 3, 2));
        tests.add(test(new int[]{0, 1, 2, 4, 2, 1}, 3, -1));
        tests.add(test(new int[]{1, 2, 3, 4, 5, 3, 1}, 2, 1));
        tests.add(test(new int[]{0, 5, 3, 1}, 1, 3));
        tests.add(test(new int[]{0, 5, 3, 1}, 3, 2));

        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int target, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "target", "Expected"}, true, nums, target, expected);

        MountainArray mountainArray = new MountainArrayImpl(nums);
        int output = 0;
        boolean pass, finalPass = true;

        output = new Solution_Cache_OptimizedPeekIndex_BSLeftRight().findInMountainArray(target, mountainArray);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Cache_OptimizedPeekIndex_BSLeftRight", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new Solution_OptimizedPeekIndex_BSLeftRight().findInMountainArray(target, mountainArray);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"OptimizedPeekIndex_BSLeftRight", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new Solution_PeekIndex_BSLeftRight().findInMountainArray(target, mountainArray);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"PeekIndex_BSLeftRight", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


    //Cache-based solution. This might take a longer time to run here, however, if mountainArr.get() is an api, then this will improve performance alot.
    static class Solution_Cache_OptimizedPeekIndex_BSLeftRight {

        //this will help to reduce to make api calls, index vs. an element
        Map<Integer, Integer> cache = new HashMap<>();

        private int get(MountainArray mountainArr, int index) {
            if (cache.containsKey(index))
                return cache.get(index); // cache hit
            else {
                int item = mountainArr.get(index);
                cache.put(index, item);
                return item;
            }
        }

        public int findInMountainArray(int target, MountainArray mountainArr) {
            int n = mountainArr.length();

            // Find peek index
            int low = 1, high = n - 2;
            while (low < high) {
                int mid = (low + high) >>> 1;

                int midEle = get(mountainArr, mid);
                int afterMid = get(mountainArr, mid + 1);

                if (midEle < afterMid) {
                    //means mid is in increasing part of the array.
                    // now if our target is present in increasing part of the array, then this index will be the minimum index it self

                    if (midEle == target)
                        return mid;

                    if (afterMid == target)
                        return mid + 1;

                    low = mid + 1;

                } else {
                    //means mid is in decreasing part of the array.
                    // however, since we need to know the minimum index of target, hence we can return from here as it is possible that target would be also available in
                    // increasing part of the array.

                    high = mid; //mid could be the peek point
                }
            }

            int peekIndex = low;
            int low1 = 0, high1 = peekIndex;
            int low2 = peekIndex + 1, high2 = n - 1;

            int leftIndex = binarySearchIncreasing(mountainArr, target, low1, high1);
            if (leftIndex != -1)
                return leftIndex;

            return binarySearchDecreasing(mountainArr, target, low2, high2);

        }

        // since the array is a mountain array, that implies that either side of the array pre/post of peekIndex
        // won't contain any duplicate; hence we can return the index of element when it matches the target.
        int binarySearchIncreasing(MountainArray mountainArr, int target, int low, int high) {
            while (low <= high) {

                int mid = (low + high) >>> 1;
                int ele = get(mountainArr, mid);

                if (ele == target)
                    return mid;

                if (ele < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            return -1;
        }

        //since we cant reverse the array, assume the array is sorted in decreasing order, so tune the leftmost index as right most index
        int binarySearchDecreasing(MountainArray mountainArr, int target, int low, int high) {
            while (low <= high) {

                int mid = (low + high) >>> 1;
                int ele = get(mountainArr, mid);

                if (ele == target)
                    return mid;

                if (ele > target) { // reversed the condition applied to accommodate a decreasing array
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            return -1;
        }

    }

    /**
     * Apply the same logic as peek index.
     * 1. Find the peek index {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._852.PeakIndexInAMountainArray_852}
     * 2. Post-finding peek index,
     * 2.1. Apply binary search on the left part of the array [0,peekIndex] to find the target
     * 2.2. Apply binary search on the right part of the array [peekIndex+1, n-1] to find target. Since the array is decreasing, swap the condition.
     * <p>
     * One optimization we can do while finding the peek index.
     * We need to get the minimum index of the target. Now, while finding the peek index, we know the mid is in what part of the array
     * 1. If mid is in increasing part of the array (left of peekIndex) and our target presents in this part, then this index will be the minimum index.
     * Hence, we can test the target against mid-element ( and mid+1 element since we are using it)
     * 2. If mid is decreasing part of the array (right of peekIndex) and our target presents in this part, then this index won't be the minimum index because it's possible that target is present in the left part as well.
     */
    static class Solution_OptimizedPeekIndex_BSLeftRight {
        public int findInMountainArray(int target, MountainArray mountainArr) {
            int n = mountainArr.length();

            // Find peek index
            int low = 1, high = n - 2;
            while (low < high) {
                int mid = (low + high) >>> 1;

                int midEle = mountainArr.get(mid);
                int afterMid = mountainArr.get(mid + 1);

                if (midEle < afterMid) {
                    //means mid is in increasing part of the array.
                    // now if our target is present in increasing part of the array, then this index will be the minimum index it self

                    if (midEle == target)
                        return mid;

                    if (afterMid == target)
                        return mid + 1;

                    low = mid + 1;

                } else {
                    //means mid is in decreasing part of the array.
                    // however, since we need to know the minimum index of target, hence we can return from here as it is possible that target would be also available in
                    // increasing part of the array.

                    high = mid; //mid could be the peek point
                }
            }

            int peekIndex = low;
            int low1 = 0, high1 = peekIndex;
            int low2 = peekIndex + 1, high2 = n - 1;

            int leftIndex = binarySearchIncreasing(mountainArr, target, low1, high1);
            if (leftIndex != -1)
                return leftIndex;

            return binarySearchDecreasing(mountainArr, target, low2, high2);

        }

        // since the array is a mountain array, that implies that either side of the array pre/post of peekIndex
        // won't contains any duplicate, hence we can return the index of element when it matches the target.
        int binarySearchIncreasing(MountainArray mountainArr, int target, int low, int high) {
            while (low <= high) {

                int mid = (low + high) >>> 1;
                int ele = mountainArr.get(mid);

                if (ele == target)
                    return mid;

                if (ele < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            return -1;
        }

        //since we cant reverse the array, assume the array is sorted in decreasing order, so tune the leftmost index as right most index
        int binarySearchDecreasing(MountainArray mountainArr, int target, int low, int high) {
            while (low <= high) {

                int mid = (low + high) >>> 1;
                int ele = mountainArr.get(mid);

                if (ele == target)
                    return mid;

                if (ele > target) { // reversed the condition applied to accomdated decreasing array
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            return -1;
        }

    }

    /**
     * Apply the same logic as peek index.
     * 1. Find the peek index {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._852.PeakIndexInAMountainArray_852}
     * 2. Post-finding peek index,
     * 2.1. Apply binary search on the left part of the array [0,peekIndex] to find the target
     * 2.2. Apply binary search on the right part of the array [peekIndex+1, n-1] to find target. Since the array is decreasing, swap the condition.
     */
    static class Solution_PeekIndex_BSLeftRight {
        public int findInMountainArray(int target, MountainArray mountainArr) {
            int n = mountainArr.length();
            int peekIndex = getPeekPoint(mountainArr);

            int low1 = 0, high1 = peekIndex;
            int low2 = peekIndex + 1, high2 = n - 1;

            int leftIndex = binarySearchIncreasing(mountainArr, target, low1, high1);
            if (leftIndex != -1)
                return leftIndex;

            return binarySearchDecreasing(mountainArr, target, low2, high2);

        }

        // since the array is a mountain array, that implies that either side of the array pre/post of peekIndex
        // won't contain any duplicate; hence we can return the index of element when it matches the target.
        int binarySearchIncreasing(MountainArray mountainArr, int target, int low, int high) {
            while (low <= high) {

                int mid = (low + high) >>> 1;
                int ele = mountainArr.get(mid);

                if (ele == target)
                    return mid;

                if (ele < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            return -1;
        }

        //since we cant reverse the array, assume the array is sorted in decreasing order, so tune the leftmost index as right most index
        int binarySearchDecreasing(MountainArray mountainArr, int target, int low, int high) {
            while (low <= high) {

                int mid = (low + high) >>> 1;
                int ele = mountainArr.get(mid);

                if (ele == target)
                    return mid;

                if (ele > target) { // reversed the condition applied to accomdated decreasing array
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }

            return -1;
        }

        int getPeekPoint(MountainArray mountainArr) {
            int n = mountainArr.length();
            int low = 1, high = n - 2;

            if (n == 3) {
                return low;
            }

            while (low < high) {
                int mid = (low + high) >>> 1;

                int midEle = mountainArr.get(mid);
                int afterMid = mountainArr.get(mid + 1);

                if (midEle < afterMid) {
                    low = mid + 1;
                } else {
                    high = mid; //mid could be the peek point
                }
            }
            return low;
        }
    }

}

interface MountainArray {
    public int get(int index);

    public int length();
}

class MountainArrayImpl implements MountainArray {
    private final int[] arr;

    MountainArrayImpl(int[] arr) {
        this.arr = arr;
    }

    public int get(int index) {
        if (index < 0 || index > arr.length)
            throw new IllegalArgumentException();

        return arr[index];
    }

    public int length() {
        return arr.length;
    }
}
