package Java.companyWise.Amazon;

import Java.helpers.GenericPrinter;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-30
 * Description: https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
 * Examples :
 * <p>
 * Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
 * Output: 6  (j = 7, i = 1)
 * <p>
 * Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
 * Output: 8 ( j = 8, i = 0)
 * <p>
 * Input:  {1, 2, 3, 4, 5, 6}
 * Output: 5  (j = 5, i = 0)
 * <p>
 * Input:  {6, 5, 4, 3, 2, 1}
 * Output: -1
 * <p>
 * Asked in: [Amazon], [Google], [VMWare] [Microsoft]
 */
public class MaximumDifferenceInIndex {
    public static void main(String[] args) {
        test(new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1}, 6);
        test(new int[]{9, 2, 3, 4, 5, 6, 7, 8, 18, 0}, 8);
        test(new int[]{1, 2, 3, 4, 5, 6}, 5);
        test(new int[]{6, 5, 4, 3, 2, 1}, -1);
    }

    private static void test(int[] nums, int expected) {
        System.out.println("\n Input :" + GenericPrinter.toString(nums) + " expected :" + expected);

        MaximumDifferenceInIndexBruteForce bruteForce = new MaximumDifferenceInIndexBruteForce();
        MaximumDifferenceInIndexPrefixSuffixArray prefixSuffixArray = new MaximumDifferenceInIndexPrefixSuffixArray();

        System.out.println(" bruteForce :" + bruteForce.maxDifference(nums));
        System.out.println(" prefixSuffixArray :" + prefixSuffixArray.maxDifference(nums));
    }


}

/**
 * Since we need to find the maximum difference between j and i, s.t.
 * j > i and a[j] > a[i].
 * <p>
 * To maximize (j-i) we need to make sure that i should be as much as left and j should be as much as on right. s.t. a[j]>a[i]
 * <p>
 * We'll run through all the numbers as 'i' and check how long we can go on right as 'j'
 * <p>
 * O(n^2) / O(1)
 */
class MaximumDifferenceInIndexBruteForce {

    public int maxDifference(int nums[]) {
        if (nums == null)
            return 0;

        int maxDiff = -1;

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[j] > nums[i])
                    maxDiff = Math.max(maxDiff, j - i);
            }
        }

        return maxDiff;
    }

}

/**
 * Important point from above algo;
 * 'To maximize (j-i) we need to make sure that i should be as much as left and j should be as much as on right.
 * a[j]>a[i]'
 * <p>
 * which means if we can find a way faster than O(n) to tell what is the a[i] and a[j] at any index s.t. a[j] > a[i].
 * Then we can calculate the maxDiff faster.
 * <p>
 * How to find a[i] and a[j] ?
 * Important thing here is a[i] < a[j] for each j (j>i). Means we need to see at any index what is the smaller before it [i]
 * Similarly we need to see at each index what is the greater after it. [j].
 * Since we need to maximize [j-i], then we need to find rightmost element which is greater after it and leftmost element with is smaller to it
 * <p>
 * To find, we can build Prefix and suffix array.
 * Prefix Array (smaller on left) : go from left to right and find min
 * Suffix array (greater on right): go from right to left and find max
 * <p>
 * After building those two array, we can
 * test
 * if(prefix[i] < suffix[j]) then calculate max diff
 * since we need to maximize j-i, then we shift j here
 * otherwise i
 * <p>
 * O(n) / O(n)
 */
class MaximumDifferenceInIndexPrefixSuffixArray {

    public int maxDifference(int nums[]) {
        if (nums == null)
            return 0;

        int maxDiff = -1;

        int n = nums.length;
        int prefixMinLeftSide[] = new int[n];
        int suffixMaxRightSide[] = new int[n];

        //for index 0 and n-1, they are only element
        prefixMinLeftSide[0] = nums[0];
        suffixMaxRightSide[n - 1] = nums[n - 1];

        for (int i = 1, j = n - 2; i < n; i++, j--) {
            prefixMinLeftSide[i] = Math.min(prefixMinLeftSide[i - 1], nums[i]);
            suffixMaxRightSide[j] = Math.max(suffixMaxRightSide[j + 1], nums[j]);
        }

        int left = 0, right = 0;
        while (left < n && right < n) {
            if (prefixMinLeftSide[left] < suffixMaxRightSide[right]) {
                maxDiff = Math.max(maxDiff, right - left);
                right++;
            } else
                left++;
        }

        return maxDiff;
    }

}