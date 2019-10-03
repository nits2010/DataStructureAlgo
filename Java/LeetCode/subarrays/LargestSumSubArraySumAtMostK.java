package Java.LeetCode.subarrays;

import java.util.TreeSet;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-25
 * Description:
 * Given an array of integers A and an integer k,
 * find a subarray that contains the largest sum, subject to a constraint that the sum is less than k?
 * {2,2,-1},
 * when k = 0, return -1.
 * when k = 3, return 3.
 */
public class LargestSumSubArraySumAtMostK {

    public static void main(String []args) {
        System.out.println(largestSumAtMostK(new int[]{2, 3, -4, 6, -8, -5}, 7));
        System.out.println(largestSumAtMostK(new int[]{2, 3, -4, 6, 8, -5}, 7));
        System.out.println(largestSumAtMostK(new int[]{-2, 3, -4, 6, 8, -5}, 7));
        System.out.println(largestSumAtMostK(new int[]{2, 2, -1}, 0));
        System.out.println(largestSumAtMostK(new int[]{2, 2, -1}, 3));
    }


    /**
     * Explanation:
     * We need to find a sub-array whose sum is Largest <=K
     * <p>
     * <p>
     * https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k
     *
     * @param nums
     * @param k
     * @return
     */
    static int largestSumAtMostK(int nums[], int k) {

        int max = Integer.MIN_VALUE;
        int sumj = 0;
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);

        for (int i = 0; i < nums.length; i++) {
            sumj += nums[i];
            Integer gap = ts.ceiling(sumj - k);

            if (gap != null)
                max = Math.max(max, sumj - gap); //sum - k = sum - gap => gap = k

            ts.add(sumj);
        }
        return max;


    }
}
