package DataStructureAlgo.Java.LeetCode.subarrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-14
 * Description: https://leetcode.com/problems/maximum-subarray/
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and
 * conquer approach, which is more subtle.
 */
public class LargestMaximumSumContiguousSubarrayKadensAlgorithm {

    public static void main(String[] args) {

        System.out.println(KadensAlgorithm.maximumSumSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(KadensAlgorithm.maximumSumSubArray(new int[]{-2, -1, -3, -4, -1, -2, -1, -5, -4}));

        int[] a = KadensAlgorithm.kadens1D(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        if (a[2] == -1)
            System.out.println(" Max sum :" + a[0] + " from: " + a[1] + " To: " + a[1]);
        else
            System.out.println(" Max sum :" + a[0] + " from :" + a[1] + " To :" + a[2]);

        a = KadensAlgorithm.kadens1D(new int[]{-2, -1, -3, -4, -1, -2, -1, -5, -4});
        if (a[2] == -1)
            System.out.println(" Max sum :" + a[0] + " from: " + a[1] + " To: " + a[1]);
        else
            System.out.println(" Max sum :" + a[0] + " from: " + a[1] + " To: " + a[2]);

    }


}

class KadensAlgorithm {
    public static int maximumSumSubArray(int nums[]) {

        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;

        for (int i = 0; i < nums.length; i++) {

            currSum = Math.max(currSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currSum);

        }
        return maxSum;
    }

    /**
     * Returns the index of sub-array too
     *
     * @param temp
     * @return
     */
    public static int[] kadens1D(int[] temp) {
        int max[] = {Integer.MIN_VALUE, 0, -1}; // maxSum, left, right

        int currentSum = 0;
        int maxSum = 0;
        int start = 0;
        int maxNeg = Integer.MIN_VALUE;
        int maxNegIndex = 0;

        for (int i = 0; i < temp.length; i++) {

            currentSum += temp[i];

            if (currentSum > maxSum) {
                maxSum = currentSum;
                max[0] = maxSum;
                max[1] = start;
                max[2] = i;
            }

            if (currentSum < 0) {
                currentSum = 0;
                start = i + 1;
            }

            if (temp[i] < 0 && maxNeg < temp[i]) {
                maxNeg = temp[i];
                maxNegIndex = i;
            }


        }

        //Means all the element in the array are neg.
        if (max[2] == -1) {

            max[0] = maxNeg;
            max[1] = maxNegIndex;
            max[2] = maxNegIndex;
        }

        return max;
    }
}