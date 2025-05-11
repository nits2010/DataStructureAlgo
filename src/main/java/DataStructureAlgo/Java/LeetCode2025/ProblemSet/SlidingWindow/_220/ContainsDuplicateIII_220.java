package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._220;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/10/2025
 * Question Title: 220. Contains Duplicate III
 * Link: https://leetcode.com/problems/contains-duplicate-iii/description
 * Description: You are given an integer array nums and two integers indexDiff and valueDiff.
 * <p>
 * Find a pair of indices (i, j) such that:
 * <p>
 * i != j,
 * abs(i - j) <= indexDiff.
 * abs(nums[i] - nums[j]) <= valueDiff, and
 * Return true if such pair exists or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
 * Output: true
 * Explanation: We can choose (i, j) = (0, 3).
 * We satisfy the three conditions:
 * i != j --> 0 != 3
 * abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
 * abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
 * Example 2:
 * <p>
 * Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
 * Output: false
 * Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 1 <= indexDiff <= nums.length
 * 0 <= valueDiff <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @hard
 * @Array
 * @SlidingWindow
 * @Sorting
 * @BucketSort
 * @OrderedSet <p><p>
 * Company Tags
 * -----
 * @Google
 * @Adobe
 * @Apple
 * @Amazon
 * @Airbnb
 * @Facebook
 * @Microsoft
 * @PalantirTechnologies<p> -
 * ----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 * @KeyConcepts See {@docRoot OrderedSet.md} for more details.
 */

public class ContainsDuplicateIII_220 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{1, 2, 3, 1}, 3, 0, true));
        tests.add(test(new int[]{1, 5, 9, 1, 5, 9}, 2, 3, false));
        tests.add(test(new int[]{-5, 5, 5, 5, 5, 15}, 6, 6, true));
        tests.add(test(new int[]{-3, 3, -6}, 2, 3, true));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int indexDiff, int valueDiff, boolean expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "indexDiff", "valueDiff", "Expected"}, true, nums, indexDiff, valueDiff, expected);

        boolean output;
        boolean pass, finalPass = true;

        output = new SolutionBruteForce().containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BruteForce", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        output = new Solution_BucketSort_TwoPointer().containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"BucketSort_TwoPointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_SlidingWindow_TreeSet().containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SlidingWindow_TreeSet", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_SlidingWindow_TreeSet_Overflow().containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"SlidingWindow_TreeSet_Overflow", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new Solution_Bucket_Map().containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Bucket_Map", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

    }

    //O(n^2)
    static class SolutionBruteForce {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            int n = nums.length;
            if (n == 0 || indexDiff > n)
                return false;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j <= i + indexDiff && j < n; j++) {
                    if (Math.abs(nums[i] - nums[j]) <= valueDiff)
                        return true;
                }
            }
            return false;
        }
    }

    //O(n*k*logk) / O(n) - TLE
    // put indexDiff number in a bucket, sort it and then find two number in bucket with diff <= valueDiff
    static class Solution_BucketSort_TwoPointer {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            int n = nums.length;

            if (n == 0 || indexDiff > n)
                return false;

            int nBuckets = n - indexDiff + 1;
            List<Integer>[] buckets = new ArrayList[nBuckets];

            //fill the buckets O(n*k)
            for (int i = 0; i < n; i++) {

                //O(k)
                for (int j = 0; j <= i && j < nBuckets; j++) {

                    if (buckets[j] == null) {
                        buckets[j] = new ArrayList<>();
                    }
                    if (j >= i - indexDiff)
                        buckets[j].add(nums[i]);

                }
            }

            //O(n*k*logK) 1<=nBuckets<=n
            for (int i = 0; i < nBuckets; i++) {

                //is valueDiff possible for the i-indexDiff bucket ?
                //O(k*logK)
                if (checkValueDiff(buckets[i], valueDiff))
                    return true;
            }
            return false;
        }

        boolean checkValueDiff(List<Integer> bucket, int value) {
            int n = bucket.size();
            Collections.sort(bucket);
            int i = 0, j = 1;

            while (i < n && j < n) {
                int diff = bucket.get(j) - bucket.get(i);

                if (i < j && diff <= value) {
                    return true;
                } else if (diff > value) {
                    i++; // Decrease the gap
                } else {
                    j++; // Expand the gap
                }

                // Ensure i < j
                if (i == j)
                    j++;
            }
            return false;

        }
    }


    // The above approach works, but its time complexity is too high due to overlapping buckets.
    // We keep sorting the overlapping buckets, leading to a time complexity of O(n*k*logk),
    // which doesn't offer any significant improvement over brute force.

    // The challenge here is to ensure that all elements within a given index difference are sorted.
    // Each time an element is added or removed, it should remain sorted with minimal time complexity.

    // This problem can be viewed as a sliding window. The task is to maintain the sliding window sorted,
    // and for each window, find the maximum and minimum values. This will allow us to apply the
    // condition `checkValueDiff` efficiently.

    // One way to achieve this is by using an ordered map such as TreeSet, which keeps elements sorted.
    // A TreeSet also allows us to quickly retrieve the maximum and minimum values.

    // However, calculating the difference between the max and min again requires O(k) time to check
    // if the condition is satisfied.

    // An important observation is that we can use the floor and ceil operations to speed up this check.
    // Let's assume valueDiff = 3, and the TreeSet contains the elements [1, 2, 4, 6].
    //       4
    //      / \
    //     2   6
    //    /
    //   1
    // If the current element is nums[i] = 3:
    // - floor: the greatest element less than or equal to 3 is 2.
    // - ceil: the smallest element greater than or equal to 3 is 4.

    // Now we check the difference:
    // abs(floor - nums[i]) = abs(2 - 3) <= valueDiff → True
    // abs(ceil - nums[i]) = abs(4 - 3) <= valueDiff → True

    // Using the floor and ceil approach, we can find the relevant elements in O(logK) time
    // instead of iterating through all the elements. This reduces the time complexity to O(n * logK),
    // which is much more efficient than O(n * k).


    static class Solution_SlidingWindow_TreeSet {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            int n = nums.length;

            if (n == 0 || indexDiff > n)
                return false;

            TreeSet<Integer> set = new TreeSet<>();

            int start = 0, end = 0;

            while (end < n) {

                int element = nums[end];

                Integer floor = set.floor(element);
                Integer ceil = set.ceiling(element);

                if (floor != null && element - floor <= valueDiff)
                    return true;

                if (ceil != null && ceil - element <= valueDiff)
                    return true;

                //expand the window
                set.add(nums[end]);

                //shrink the window
                if (set.size() > indexDiff) {
                    set.remove(nums[start++]);
                }
                end++;
            }
            return false;

        }

    }

    // The above solution has potential issue with overflow:
    // Given nums = [2147483647, -2147483648] and valueDiff = 100, indexDiff = 1:
    // Math.abs(2147483647 - (-2147483648)) = 4294967295 → This could cause an overflow in int!

    static class Solution_SlidingWindow_TreeSet_Overflow {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            int n = nums.length;

            if (n == 0 || indexDiff > n)
                return false;

            TreeSet<Long> set = new TreeSet<>();

            int start = 0, end = 0;

            while (end < n) {

                Long element = 1L * nums[end];

                Long floor = set.floor(element);
                Long ceil = set.ceiling(element);

                if (floor != null && element - floor <= valueDiff)
                    return true;

                if (ceil != null && ceil - element <= valueDiff)
                    return true;

                //expand the window
                set.add(element);

                //shrink the window
                if (set.size() > indexDiff) {
                    set.remove(1L * nums[start++]);
                }
                end++;
            }
            return false;

        }

    }

    // In the previous solution, we achieved a time complexity of O(n * logk) because we processed each element once.
    // For each element, we query the floor and ceil values, which takes O(logK) time.

    // However, what if we combine the Bucket-based approach from the first solution with the Sliding Window + TreeSet approach from the last solution?

    // This hybrid approach allows us to keep each value in a bucket. However, since we're considering each number to fall into either of the buckets, we can't simply use
    // n - indexDiff + 1 as the bucket size. Setting the bucket size to n - indexDiff + 1 doesn't guarantee that the value difference condition will hold.
    // This could break the constraint abs(nums[i] - nums[j]) <= valueDiff, as it may group numbers with a larger difference than allowed.

    // To resolve this, we need to ensure that the elements within a given bucket satisfy the value difference condition.
    // The solution is to set the bucket size to valueDiff + 1, ensuring that all elements within this range fall into the same bucket.
    // This is also crucial for satisfying the "almost duplicate" condition.

    // By doing this, we can calculate the bucket index for each element using `num / bucketSize` (for negative numbers, we need to handle this differently).

    // Now, all elements falling into the same bucket will satisfy the valueDiff condition.
    // Next, we need to ensure that the IndexDiff condition is met. This can be handled using a sliding window approach.
    // As soon as an element goes out of the sliding window, we remove it from its respective bucket.
    // O(N) / O(n)
    static class Solution_Bucket_Map {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            int n = nums.length;

            if (n == 0 || indexDiff > n)
                return false;

            final Map<Long, Long> map = new HashMap<>();
            int start = 0, end = 0;
            int bucketSize = valueDiff + 1;
            Long bucketId = null;
            while (end < n) {

                Long element = 1L * nums[end];
                bucketId = getBucketId(element, bucketSize);

                //check if this bucket id exists
                if (map.containsKey(bucketId) && Math.abs(element - map.get(bucketId)) <= valueDiff)
                    return true;

                //check nearby buckets as well,+-1 this is to ensure that if they fall in <=valuedDiff
                if (map.containsKey(bucketId + 1) && Math.abs(element - map.get(bucketId + 1)) <= valueDiff)
                    return true;

                if (map.containsKey(bucketId - 1) && Math.abs(element - map.get(bucketId - 1)) <= valueDiff)
                    return true;

                //add an element, expand the window
                map.put(bucketId, element);

                //shrink the window
                if (end >= indexDiff) {
                    bucketId = getBucketId(1L * nums[start++], bucketSize);
                    map.remove(bucketId);
                }
                end++;
            }

            return false;

        }

        // Let's trace this with bucketSize = 3:
        // num = -1: (-1 - 1) / 3 = -2 / 3 = 0
        // num = 1: 1 / 3 = 1 / 3 = 0
        // num = -2: (-2 - 1) / 3 = -3 / 3 = -1
        // num = 2: 2 / 3 = 2 / 3 = 0

        // [-1,0,1,2] falls in same 0th bucket

        // bucket size = 3, [-1,0,1,2]
        private long getBucketId(Long num, int bucketSize) {
            if (num < 0) {
                return (num - 1) / bucketSize; // Correct handling for negative numbers
            }
            return num / bucketSize; // Standard handling for positive numbers
        }


        // minElement is the minimum element in the array
        private long getBucketIdV2(Long num, int minElement, int bucketSize) {
            return (long) (num - minElement) / bucketSize; // Standard handling
        }

    }
}
