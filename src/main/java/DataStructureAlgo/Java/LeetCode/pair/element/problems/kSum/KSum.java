package DataStructureAlgo.Java.LeetCode.pair.element.problems.kSum;

import  DataStructureAlgo.Java.helpers.GenericPrinter;
import  DataStructureAlgo.Java.LeetCode.pair.element.problems.fourSum.FourSum4Sum;
import  DataStructureAlgo.Java.LeetCode.pair.element.problems.threeSum.ThreeSum3Sum;
import  DataStructureAlgo.Java.LeetCode.pair.element.problems.twoSum.TwoSum2Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-16
 * Description:
 * This is generalization of {@link TwoSum2Sum}, {@link ThreeSum3Sum}, {@link FourSum4Sum} to K-sum
 */
public class KSum {
    public static void main(String[] args) {
        test(new int[]{1, 0, -1, 0, -2}, 4, 0);

        test(new int[]{1, 0, -1, 0, -2, 2}, 4, 0);

        test(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 4, 0);

        test(new int[]{-4, -3, -2, -1, 0, 0, 1, 2, 3, 4}, 4, 0);


        test(new int[]{1, 0, -1, 0, -2}, 5, 0);

        test(new int[]{1, 0, -1, 0, -2, 2}, 5, 0);

        test(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 5, 0);

        test(new int[]{-4, -3, -2, -1, 0, 0, 1, 2, 3, 4}, 5, 0);


        test(new int[]{1, 0, -1, 0, -2}, 3, 0);

        test(new int[]{1, 0, -1, 0, -2, 2}, 3, 0);

        test(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 3, 0);

        test(new int[]{-4, -3, -2, -1, 0, 0, 1, 2, 3, 4}, 3, 0);


        test(new int[]{1, 0, -1, 0, -2}, 2, 0);

        test(new int[]{1, 0, -1, 0, -2, 2}, 2, 0);

        test(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 2, 0);

        test(new int[]{-4, -3, -2, -1, 0, 0, 1, 2, 3, 4}, 2, 0);

    }

    private static void test(int[] nums, int k, int target) {
        IKSum kSum = new KSumSorting();

        System.out.println("\nInput : " + GenericPrinter.toString(nums) + " K :" + k + " Target: " + target);
        System.out.println("Sorting: " + kSum.kSum(nums, k, target));
    }


}

interface IKSum {

    List<List<Integer>> kSum(int nums[], int k, int target);
}

/**
 * We'll use the problem of k sum and reduce it to two sum and attache the result back
 */
class KSumSorting implements IKSum {

    @Override
    public List<List<Integer>> kSum(int[] nums, int k, int target) {
        if (nums == null || nums.length < k)
            return Collections.EMPTY_LIST;

        Arrays.sort(nums);

        return kSum(nums, k, 0, target);

    }

    private List<List<Integer>> kSum(int[] nums, int k, int start, int target) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();


        if (len < k)
            return Collections.EMPTY_LIST;

        if (k == 2) {
            return twoSum(nums, start, len - 1, target);
        } else {

            //Take each element, and exclude it from target to reduce the problem to smaller k-1 sum problem
            for (int i = start; i < len - (k - 1); i++) {

                //Avoid duplicates
                if (i > start && nums[i] == nums[i - 1])
                    continue;

                int ele = nums[i];

                int rem = target - nums[i];
                List<List<Integer>> smallerSumResult = kSum(nums, k - 1, i + 1, rem);


                //Append the current element to make it k sum from k-1 sum
                for (List<Integer> list : smallerSumResult)
                    list.add(0, ele);


                //Append to our result
                result.addAll(smallerSumResult);


            }
        }

        return result;
    }

    private List<List<Integer>> twoSum(int[] nums, int low, int high, int target) {

        List<List<Integer>> result = new ArrayList<>();

        while (low < high) {

            int sum = nums[low] + nums[high];

            if (sum == target) {

                result.add(Arrays.asList(nums[low], nums[high]));

                //Avoid duplicates
                while (low < high && nums[low] == nums[++low]) ;
                while (high > low && nums[high] == nums[--high]) ;

            } else if (sum < target)
                low++;
            else
                high--;

        }

        return result;
    }
}