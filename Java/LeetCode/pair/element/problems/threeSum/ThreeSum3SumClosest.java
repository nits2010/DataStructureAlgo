package Java.LeetCode.pair.element.problems.threeSum;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-15
 * Description: https://leetcode.com/problems/3sum-closest/
 * <p>
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * Similar to {@link ThreeSum3Sum}
 */
public class ThreeSum3SumClosest {

    public static void main(String[] args) {
        test(new int[]{-1, 2, 1, -4}, 1);
        test(new int[]{-1, -2, 1, 4}, 1);
        test(new int[]{1, 1, -1, -1, 3}, -1);
        test(new int[]{1, 1, 1, 0}, -100);
    }

    private static void test(int[] nums, int target) {

        IThreeSum3SumClosest sorting = new IThreeSum3SumClosestSorting();
        System.out.println("Input: " + Printer.toString(nums));
        System.out.println(" Closest :" + sorting.threeSumClosest(nums, target));

    }
}

interface IThreeSum3SumClosest {
    int threeSumClosest(int[] nums, int target);
}

class IThreeSum3SumClosestSorting implements IThreeSum3SumClosest {

    /**
     * Algo: Similar to {@link ThreeSum3Sum}
     * The only change we need to make here is to find the closest diff.
     * <p>
     * So just check the closestDiff with current diff and update value accordingly
     * <p>
     * <p>
     * Runtime: 4 ms, faster than 95.79% of Java online submissions for 3Sum Closest.
     * Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for 3Sum Closest.
     *
     * @param nums
     * @param target
     * @return
     */
    @Override
    public int threeSumClosest(int[] nums, int target) {
        if (null == nums || nums.length == 0 || nums.length < 3)
            return 0;

        Arrays.sort(nums);

        int closest = target;
        int bestDiff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {

            int a = nums[i];

            if (i > 0 && a == nums[i - 1])
                continue;

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int b = nums[j];
                int c = nums[k];

                int sum = a + b + c;

                /**
                 * if Sum is same as target, then this is the closest sum possible
                 */
                if (sum == target)
                    return sum;

                /**
                 * If sum is smaller then target, then we need increase the sum hence j++
                 */
                if (sum < target)
                    j++;
                else if (sum > target) //If sum is smaller then bigger, then we need decrese the sum hence k--
                    k--;

                int currentDiff = Math.abs(sum - target);

                /**
                 * In case we did not find a sum=target, then choose the closest diff
                 */
                if (currentDiff < bestDiff) {
                    closest = sum;
                    bestDiff = currentDiff;
                }


            }

        }
        return closest;
    }
}