package Java.companyWise.visa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-15
 * Description:
 * Extension of {@link Java.LeetCode.SubarrayProductLessThanK}
 * <p>
 * Your are given an array of positive integers nums.
 * <p>
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than equal to k.
 */
public class SubarrayProductLessThanEqualK {
    public static void main(String[] args) {

        System.out.println(countSubarrays(Arrays.asList(3, 4, 5, 1), 1));
        System.out.println(countSubarrays(Arrays.asList(100, 100, 100), 4));
        System.out.println(countSubarrays(Arrays.asList(100, 100, 100), 1000000));


    }

    public static long countSubarrays(List<Integer> numbers, int k) {
        // Write your code here
        return numSubarrayProductLessThanK(numbers, k);

    }

    public static long numSubarrayProductLessThanK(List<Integer> nums, int k) {
        long prod = 1;
        int ans = 0, left = 0;
        for (int right = 0; right < nums.size(); right++) {

            prod *= nums.get(right);

            while (left < right && prod > (long) k) prod /= (long) nums.get(left++);

            if (prod <= k)
                ans += right - left + 1;
        }
        return ans;
    }
//
//    test() {
//        int p = 1;
//        int count = 0;
//        int j = 0;
//        for (int i = 0; i < nums.length; i++) {
//            p *= nums[i];
//            if (nums[i] >= k) {
//                j = i + 1;
//                p = 1;
//            }
//            while (p >= k && j <= i) {
//                p /= nums[j];
//                j++;
//            }
//            count += i - j + 1;
//        }
//        return count;
//    }
}




