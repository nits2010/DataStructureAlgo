package DataStructureAlgo.Java.LeetCode.pair.element.problems.fourSum;

import DataStructureAlgo.Java.helpers.CommonMethods;
import  DataStructureAlgo.Java.LeetCode.pair.element.problems.threeSum.ThreeSum3Sum;
import  DataStructureAlgo.Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-15
 * Description: https://leetcode.com/problems/4sum/
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * The solution set must not contain duplicate quadruplets.
 * Example:
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * {@link ThreeSum3Sum}
 * {@link TwoSum2Sum}
 * <p>
 * https://leetcode.com/problems/4sum/discuss/359397/Optimisations-or-Thought-Process-or-Java-or-4ms
 * https://www.geeksforgeeks.org/find-four-elements-that-sum-to-a-given-value-set-2/
 */
public class FourSum4Sum {

    public static void main(String[] args) {

        test(new int[]{1, 0, -1, 0, -2}, 0);

        test(new int[]{1, 0, -1, 0, -2, 2}, 0);

        test(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0);

        test(new int[]{-4, -3, -2, -1, 0, 0, 1, 2, 3, 4}, 0);

        test(new int[]{1,-2,-5,-4,-3,3,3,5}, -11);
        test(new int[]{0,0,0,1000000000,1000000000,1000000000,1000000000}, 1000000000);
    }

    private static void test(int[] nums, int target) {

        System.out.println("\nInput: " + CommonMethods.toString(nums) + " target :" + target);
        IFourSum4Sum sorting = new FourSum4SumSortingV1();
        IFourSum4Sum sorting2 = new FourSum4SumSortingV2();
        System.out.println("V1 : " + sorting.fourSum(nums, target));
        System.out.println("V2 : " + sorting2.fourSum(nums, target));

    }

}

interface IFourSum4Sum {
    List<List<Integer>> fourSum(int[] nums, int target);
}

/**
 * Algo:
 * 1. Fix the first element as A[i] where i is from 0 to n–3.
 * 2. After fixing the first element of quadruple, fix the second element as A[j] where j varies from i+1 to n-2.
 * 3. Find remaining two elements in O(n) time, using the two sum {@link TwoSum2Sum}
 * Complexity: O(n^3)
 * Runtime: 19 ms, faster than 68.43% of Java online submissions for 4Sum.
 * Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for 4Sum.
 */
class FourSum4SumSortingV1 implements IFourSum4Sum {

    @Override
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4)
            return Collections.EMPTY_LIST;

        /*
         * The solution set must not contain duplicate quadruplets.
         */
        List<List<Integer>> solution = new ArrayList<>();


        Arrays.sort(nums);

        //1. Fix the first element as A[i] where i is from 0 to n–3.
        for (int i = 0; i < nums.length - 3; i++) {

            int a = nums[i];

            /*
             * Avoid duplicate
             */
            if (i > 0 && a == nums[i - 1])
                continue;


            //2. After fixing the first element of quadruple, fix the second element as A[j] where j varies from i+1 to n-2.
            for (int j = i + 1; j < nums.length - 2; j++) {

                int b = nums[j];

                /*
                 * Avoid duplicate
                 */
                if (j > i + 1 && b == nums[j - 1])
                    continue;


                int l = j + 1;
                int r = nums.length - 1;

                /*
                  3. Find the remaining two elements in O(n) time, using the two sum
                 */

                twoSum(nums, l, r, a, b, target, solution);


            }
        }

        return solution;


    }

    private void twoSum(int []nums, int l, int r, int a, int b, int target, List<List<Integer>> solution) {
        while (l < r) {
            int c = nums[l];
            int d = nums[r];

            int sum = a + b + c + d;

            if (sum == target) {

                solution.add(Arrays.asList(a, b, c, d));

                /*
                 * Avoid immediate duplicates
                 */
                while (l < r && c == nums[++l]) ;
                while (r > l && d == nums[--r]) ;


            } else if (sum > target)
                r--;
            else
                l++;

        }

    }


}


class FourSum4SumSortingV2 implements IFourSum4Sum {
    /**
     * Runtime: 4 ms, faster than 93.13% of Java online submissions for 4Sum.
     * Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for 4Sum.
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> solution = new ArrayList<>();
        Arrays.sort(nums);

        /*
         * Fix first element from left
         */
        for (int i = 0; i < nums.length - 3; i++) {

            int a = nums[i];

            /*
             * Return immediately if (a * 4 > target)
             * As we're going to include 3 more elements with this which are always greater and equal this number.
             * In the least case, when they are equal, then it becomes 4*a. If 4*a > target
             * then it's not possible as remaining equal/greater number will be more than > target always as data is sorted
             */
            if ((a << 2) > target)
                return solution;

            /*
             * Fix last element from array's last;
             * j>i+2; since in-between we need to find 2 elements that give us total element 4.
             * hence those two elements will run in between i+1 to j-1
             */
            for (int j = nums.length - 1; j > i + 2; j--) {

                int b = nums[j];

                /*
                 * break immediately if nums[j]*4 < target
                 *
                 * return immediately if b*4<target
                 * As we're going to include 3 more elements with this which are always smaller and equal this number.
                 * In the least case, when they are equal then it becomes 4*b. If 4*b < target
                 *  then it's not possible as remaining equal/smaller number will be less than < target always as data is sorted and we are moving
                 * j towards the left which makes every element smaller than the current j'th element
                 */
                if (b << 2 < target)
                    break;

                /*
                 * convert to two sum
                 */
                int rem = target - nums[i] - nums[j];

                /*
                 * Problem reduces to Two-Sum problem
                 */
                int lo = i + 1, hi = j - 1;

                twoSum(nums, rem, lo, hi, i, j, solution);

                // avoid duplicate results
                while (j > i + 2 && nums[j] == nums[j - 1])
                    j--;
            }
            // avoid duplicate results
            while (i < nums.length - 2 && nums[i] == nums[i + 1])
                i++;
        }
        return solution;
    }

    private void twoSum(int []nums, int target, int low, int high, int i, int j, List<List<Integer>> list) {

        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum > target)
                high--;
            else if (sum < target)
                low++;
            else {
                list.add(Arrays.asList(nums[i], nums[low], nums[high], nums[j]));
                low++;
                high--;

                // avoid duplicate results
                while (nums[low - 1] == nums[low] && low < high) low++;
                while (nums[high] == nums[high + 1] && low < high) high--;
            }
        }
    }
}

