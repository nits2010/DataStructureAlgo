package Java.LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-19
 * Description: https://leetcode.com/problems/first-missing-positive/
 */
public class SmallestMissingNumber {


    public static void main(String []args) {
        int nums[] = {1, 2, 0};
        int nums1[] = {1};
        int nums2[] = {};
        int nums3[] = {1, 1};
        int nums4[] = {2147483647, 2147483646, 2147483645, 3, 2, 1, -1, 0, -2147483648};

        System.out.println(firstMissingPositive(nums4));
        System.out.println(firstMissingPositive(nums));
        System.out.println(firstMissingPositive(nums1));
        System.out.println(firstMissingPositive(nums2));
        System.out.println(firstMissingPositive(nums3));

    }

    //Solution1
    public static int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                set.add(nums[i]);
        }

        int i = 0;
        while (++i <= nums.length + 1) {
            if (!set.contains(i))
                return i;

        }
        return i;

    }


    //SOlution2
    public static int firstMissingPositive2(int[] nums) {

        int shift = segregate(nums);
        return firstMissingPositive2(nums, shift);

    }

    public static int segregate(int nums[]) {

        int neg = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] <= 0) {
                int temp = nums[i];
                nums[i] = nums[neg];
                nums[neg] = temp;
                neg++;
            }
        }
        return neg;

    }

    public static int firstMissingPositive(int[] nums, int shift) {

        int temp[] = new int[nums.length - shift];
        int k = 0;
        for (int i = shift; i < nums.length; i++) {
            temp[k++] = nums[i];
        }

        for (int i = 0; i < temp.length; i++) {
            int index = Math.abs(temp[i]);
            if (index - 1 >= 0 && index - 1 < temp.length && temp[index - 1] > 0) {

                temp[index - 1] = -temp[index - 1];

            }

        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > 0)
                return i + 1;
        }
        return temp.length + 1;
    }

    public static int firstMissingPositive2(int[] nums, int shift) {


        for (int i = shift; i < nums.length; i++) {
            int index = Math.abs(nums[i]);
            if (shift + index - 1 >= 0 && shift + index - 1 < nums.length && nums[shift + index - 1] > 0) {

                nums[shift + index - 1] = -nums[shift + index - 1];

            }

        }


        for (int i = shift; i < nums.length; i++) {
            if (nums[i] > 0)
                return i - shift + 1;
        }
        return nums.length - shift + 1;
    }
}
