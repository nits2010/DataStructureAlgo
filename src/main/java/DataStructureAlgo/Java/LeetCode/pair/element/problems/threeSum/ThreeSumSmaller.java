package DataStructureAlgo.Java.LeetCode.pair.element.problems.threeSum;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-15
 * Description: https://leetcode.com/problems/3sum-smaller/
 * http://leetcode.liangjiateng.cn/leetcode/3sum-smaller/description
 * https://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/
 * <p>
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * <p>
 * Example:
 * <p>
 * Input: nums = [-2,0,1,3], and target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 * Follow up: Could you solve it in O(n2) runtime?
 * <p>
 * {5, 1, 3, 4, 7}
 * sum = 12.
 * Output : 4
 */
public class ThreeSumSmaller {
    public static void main(String[] args) {

        test(new int[]{-2, 0, 1, 3}, 2);
        test(new int[]{5, 1, 3, 4, 7}, 12);

    }

    private static void test(int[] nums, int target) {
        System.out.println("Input :" + CommonMethods.toString(nums));

        ThreeSumSmallerSorting sorting = new ThreeSumSmallerSorting();
        System.out.println("Sorting " + sorting.threeSumSmaller(nums, target));
    }


}

/**
 * similar to {@link ThreeSum3Sum}
 */
class ThreeSumSmallerSorting {

    int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return 0;
        return threeSum(nums, target);
    }

    /**
     * O(n^2)
     */
    private int threeSum(int[] nums, int target) {

        int solution = 0;

        /*
          12 and [5, 1, 3, 4, 7] => [1,3,4,5,7]
         */
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            int a = nums[i];


            /*
              Avoid duplicates
             */
            if (i > 0 && a == nums[i - 1])
                continue;

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int b = nums[j];
                int c = nums[k];


                /*
                 * [1,3,4,5,7] ; 12
                 *
                 * i= 0; a = 1
                 * j = 1; b = 3
                 * k = 4; c = 7
                 * sum = 11 < 12 then
                 * solution += 4-1 = 3 since
                 * i=0; a = 1
                 * j = 1; b = 3;
                 * k = 4; c = 7 => sum =11
                 * k = 3; c = 5 => sum = 9
                 * k = 2 ; c = 4 => sum = 8
                 * Hence 3 solution [ k - j ]
                 *
                 */
                int sum = a + b + c;


                if (sum < target) {

                    solution += k - j; //All the elements between k and j would satisfy this condition when k moving towards j

                    //Sum is smaller, make it bigger by moving j to more big number
                    j++;


                } else
                    k--; //Sum is bigger, then reduce the bigger number to smaller number; hence k
            }

        }

        return solution;
    }
}
