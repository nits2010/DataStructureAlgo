package Java.LeetCode.partitions;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-25
 * Description:
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * https://www.geeksforgeeks.org/partition-set-k-subsets-equal-sum/
 * Given an integer array of N elements, the task is to divide this array into K non-empty subsets such that the sum of elements in every subset is same. All elements of this array should be part of exactly one partition.
 * Examples:
 * <p>
 * Input : arr = [2, 1, 4, 5, 6], K = 3
 * Output : Yes
 * we can divide above array into 3 parts with equal
 * sum as [[2, 4], [1, 5], [6]]
 * <p>
 * Input  : arr = [2, 1, 5, 5, 6], K = 3
 * Output : No
 * It is not possible to divide above array into 3
 * parts with equal sum
 * <p>
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * <p>
 * <p>
 * DP solution: https://leetcode.com/articles/partition-to-k-equal-sum-subsets/
 * See this for complexity analysis
 * https://efficientcodeblog.wordpress.com/2017/12/01/partition-to-k-equal-sum-subsets/
 */
public class PartitionSetIntoKSubsetEqualSum {

    public static void main(String []args) {
        int nums[] = {4, 3, 2, 3, 5, 2, 1}, k = 4;
        System.out.println(canPartitionKSubsets(nums, 4));
        int nums2[] = {2, 1, 5, 3, 7};
        System.out.println(canPartitionKSubsets(nums2, 3));
    }


    public static boolean canPartitionKSubsets(int[] nums, int k) {
//        return canPartitionKSubsetsExhaustiveSearch(nums, k);

//        return canPartitionKSubsetsLimitingSearch(nums, k);

        return canPartitionKSubsetsExhaustiveSearchEasy(nums, k);
    }


    //++++==========================canPartitionKSubsetsExhaustiveSearch=====================================================

    /**
     * Complexity:
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean canPartitionKSubsetsExhaustiveSearch(int[] nums, int k) {
        if (k == 1)
            return true;

        int sum = Arrays.stream(nums).sum(); //O(n)

        if (sum % k != 0)
            return false;

        int n = nums.length;

        int subsetSum = sum / k;

        int subset[] = new int[k];

        boolean visited[] = new boolean[n];

        return canPartitionKSubsetsExhaustiveSearch(nums, k, subsetSum, subset, 0, visited, n - 1, n);
    }

    public static boolean canPartitionKSubsetsExhaustiveSearch(int[] nums, int k, int sum, int set[], int index, boolean visited[], int limitIndex, int n) {

        if (index >= k)
            return false;

        if (set[index] == sum) {

            if (index == k - 2)
                return true;

            //O(k! * n )
            return canPartitionKSubsetsExhaustiveSearch(nums, k, sum, set, index + 1, visited, n - 1, n);

        }


        for (int j = limitIndex; j >= 0; j--) { //O(n)
            if (nums[j] > sum)
                return false;

            if (visited[j])
                continue;


            int item = nums[j];

            if (item + set[index] > sum)
                continue;

            set[index] += item;
            visited[j] = true;

            //O(n)
            if (canPartitionKSubsetsExhaustiveSearch(nums, k, sum, set, index, visited, j - 1, n))
                return true;

            set[index] -= item;
            visited[j] = false;

        }


        return false;

    }

    //++++==========================canPartitionKSubsetsExhaustiveSearch=====================================================


    //++++==============================canPartitionKSubsetsLimitingSearch=================================================

    /**
    *     Intuition

As even when k = 2, the problem is a "Subset Sum" problem which is known to be NP-hard, (and because the given input limits are low,) our solution will focus on exhaustive search. A natural approach is to simulate the k groups (disjoint subsets of nums). For each number in nums, we’ll check whether putting it in the i-th group solves the problem. We can check those possibilities by recursively searching.

Algorithm

Firstly, we know that each of the k group-sums must be equal to target = sum(nums) / k. (If this quantity is not an integer, the task is impossible.) For each number in nums, we could add it into one of k group-sums, as long as the group’s sum would not exceed the target. For each of these choices, we recursively search with one less number to consider in nums. If we placed every number successfully, then our search was successful. One important speedup is that we can ensure all the 0 values of each group occur at the end of the array groups, by enforcing if (groups[i] == 0) break;. This greatly reduces repeated work – for example, in the first run of search, we will make only 1 recursive call, instead of k. Actually, we could do better by skipping any repeated values of groups[i], but it isn’t necessary. Another speedup is we could sort the array nums, so that we try to place the largest elements first. When the answer is true and involves subsets with a low size, this method of placing elements will consider these lower size subsets sooner. We can also handle elements nums[i] >= target appropriately
     * O ( K! * k^(n-k) )
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean canPartitionKSubsetsLimitingSearch(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        //Another speedup is we could sort the array nums, so that we try to place the largest elements first. 
         
        Arrays.sort(nums); // O( n log n)
        int row = nums.length - 1;
        
        
        if (nums[row] > target) 
        return false;
        
        while (row >= 0 && nums[row] == target) { //O(n)
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }

    /**
     * O ( K! * k^(n-k) )
     * See this for complexity analysis
     * https://efficientcodeblog.wordpress.com/2017/12/01/partition-to-k-equal-sum-subsets/
     *
     * @param groups
     * @param row
     * @param nums
     * @param target
     * @return
     */
    public static boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) return true;

        //chose this element and try to find which bucket we can fit it in
        int v = nums[row--];
        
        for (int i = 0; i < groups.length; i++) { //O(k)
            //if this bucket is possible
            if (groups[i] + v <= target) {

                groups[i] += v;

                //fil it in and try other buckets with remaining elements
                if (search(groups, row, nums, target)) return true;

                groups[i] -= v;
            }
            if (groups[i] == 0) break; // this will limit the search to instead searching N elements to N-k elements
        }
        return false;
    }


    //++++===============================================================================


    //++++===============================Easy to understand================================================

    //Easy to understand
    private static boolean canPartitionKSubsetsExhaustiveSearchEasy(int nums[], int k) {

        int sum = Arrays.stream(nums).sum(); //O(n)

        if (sum % k != 0)
            return false;

        int n = nums.length;

        int subsetSum = sum / k;

        boolean visited[] = new boolean[n];

        return canPartitionKSubsetsExhaustiveSearchEasy(nums, k, 0, 0, subsetSum, visited);
    }

    private static boolean canPartitionKSubsetsExhaustiveSearchEasy(int[] nums, int totalBuckets, int startFrom, int currentBucketSum, int subsetSum, boolean visited[]) {

        //if all the remaining buckets has been field correctly and still left the values with, then we are done
        if (totalBuckets == 1)
            return true;


        //current bucket has filled and still left some bucket to fill
        if (currentBucketSum == subsetSum) {
            //start putting value in remaining buckets
            return canPartitionKSubsetsExhaustiveSearchEasy(nums, totalBuckets - 1, 0, 0, subsetSum, visited);
        }

        for (int i = startFrom; i < nums.length; i++) {

            int item = nums[i];

            if (item > subsetSum)
                return false;

            if (visited[i])
                continue;


            if (item + currentBucketSum > subsetSum)
                continue;

            visited[i] = true;
            currentBucketSum += item;
            if (canPartitionKSubsetsExhaustiveSearchEasy(nums, totalBuckets, i + 1, currentBucketSum, subsetSum, visited))
                return true;

            visited[i] = false;
            currentBucketSum -= item;


        }

        return false;
    }

    //++++===============================Easy to understand================================================


    //++++===============================canPartitionKSubsetsExhaustiveSearchEasyOptimized================================================

    //Easy to understand
    private static boolean canPartitionKSubsetsExhaustiveSearchEasyOptimized(int nums[], int k) {

        int sum = Arrays.stream(nums).sum(); //O(n)

        if (sum % k != 0)
            return false;

        int n = nums.length;
        int subsetSum = sum / k;

        Arrays.sort(nums);

        //Since we need to group all the number in one of the bucket, then maximum number in the bucket is only can be subsetSum or less then it
        if (nums[n - 1] > subsetSum)
            return false;


        //Reduce the buckets such that if we have entries of subset sum in nums as it is, then we can put those in the bucket
        //directly
        int startFrom = n - 1;
        while (startFrom >= 0 && nums[startFrom] == subsetSum) {
            k--; //reduced the bucket, as this bucket will fill with nums[startFrom]
            startFrom--;
        }

        if (startFrom < 0) {
            //means array have all entries as subset sum and all the buckets get filled.
            return true;
        }

        //If not, try to fill remaining buckets

        boolean visited[] = new boolean[n];

        return canPartitionKSubsetsExhaustiveSearchEasyOptimized(nums, k, 0, 0, subsetSum, visited);
    }

    private static boolean canPartitionKSubsetsExhaustiveSearchEasyOptimized(int[] nums, int totalBuckets, int startFrom, int currentBucketSum, int subsetSum, boolean visited[]) {

        //if all the remaning buckets has been field correctly and still left the values with, then we are done
        if (totalBuckets == 1) {
            return true;

        }

        //current bucket has filled and still left some bucket to fill
        if (currentBucketSum == subsetSum) {
            //start putting value in remaining buckets
            return canPartitionKSubsetsExhaustiveSearchEasyOptimized(nums, totalBuckets - 1, 0, 0, subsetSum, visited);
        }

        for (int i = startFrom; i < nums.length; i++) {

            int item = nums[i];

            if (visited[i])
                continue;


            if (item + currentBucketSum > subsetSum)
                continue;

            visited[i] = true;
            currentBucketSum += item;
            if (canPartitionKSubsetsExhaustiveSearchEasyOptimized(nums, totalBuckets, i + 1, currentBucketSum, subsetSum, visited))
                return true;

            visited[i] = false;
            currentBucketSum -= item;


        }

        return false;
    }
}
