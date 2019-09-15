package Java.LeetCode.pair.element.problems.threeSum;

import Java.HelpersToPrint.GenericPrinter;
import Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-15
 * Description: https://leetcode.com/problems/3sum/
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * Extension of {@link TwoSum2Sum}
 */
public class ThreeSum3Sum {

    public static void main(String[] args) {

        test(new int[]{-1, 0, 1, 2, -1, -4});
    }

    private static void test(int[] nums) {
        System.out.println("Input :" + GenericPrinter.toString(nums));

        IThreeSum3Sum sorting = new ThreeSum3SumSorting();
        IThreeSum3Sum sortingOptimized = new ThreeSum3SumSortingOptimized();
        System.out.println("Sorting " + sorting.threeSum(nums));
        System.out.println("Sorting  Optimized " + sortingOptimized.threeSum(nums));
    }
}


interface IThreeSum3Sum {

    List<List<Integer>> threeSum(int[] nums);
}


class ThreeSum3SumSorting implements IThreeSum3Sum {

    public List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length == 0)
            return Collections.EMPTY_LIST;


        return threeSum(nums, 0);
    }

    /**
     * Return the triplet whose sum is = target
     * <p>
     * [-1, 0, 1, 2, -1, -4]
     * <p>
     * Sorted-> [-4,-1,-1,0,1,2]
     * <p>
     * Runtime: 175 ms, faster than 22.39% of Java online submissions for 3Sum.
     * Memory Usage: 48.7 MB, less than 65.72% of Java online submissions for 3Sum.
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> threeSum(int[] nums, int target) {


        /**
         * to avoid duplicate triplet
         */
        final Set<List<Integer>> solution = new HashSet<>();


        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {


            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int sum = nums[i] + nums[j] + nums[k];

                if (sum == target) {
                    solution.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (sum > target)
                    k--;
                else
                    j++;
            }

        }

        return new ArrayList<>(solution);
    }
}


class ThreeSum3SumSortingOptimized implements IThreeSum3Sum {

    public List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length == 0)
            return Collections.EMPTY_LIST;


        return threeSum(nums, 0);
    }

    /**
     * Return the triplet whose sum is = target
     * <p>
     * [-1, 0, 1, 2, -1, -4]
     * <p>
     * Sorted-> [-4,-1,-1,0,1,2]
     * <p>
     * Runtime: 26 ms, faster than 97.35% of Java online submissions for 3Sum.
     * Memory Usage: 45.5 MB, less than 96.47% of Java online submissions for 3Sum.
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> threeSum(int[] nums, int target) {


        final List<List<Integer>> solution = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            int a = nums[i];
            if (a > 0) break; //This improve the performance by 50%

            /**
             * Avoid duplicates
             */
            if (i > 0 && a == nums[i - 1])
                continue;

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int b = nums[j];
                int c = nums[k];


                int sum = a + b + c;

                if (sum == target) {
                    solution.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    /**
                     *  As the elements are get sorted, that brings all the duplicate pair together
                     * skip all duplicates
                     */
                    while (j < k && b == nums[++j]) ;

                    while (k > j && c == nums[--k]) ;


                } else if (sum > target)
                    k--;
                else
                    j++;
            }

        }

        return solution;
    }
}