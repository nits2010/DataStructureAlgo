package Java.LeetCode.bitsManipulation.single.number;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-28
 * Description: https://leetcode.com/problems/single-number/
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1,2,3,4,4,3,2}));
    }

    /**
     * Duplicate element get cancelled out by xoring them
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Single Number.
     * Memory Usage: 38.8 MB, less than 98.52% of Java online submissions for Single Number.
     *
     * @param nums
     * @return
     */
    public  static int singleNumber(int[] nums) {
        int xor = 0;
        for (int e : nums)
            xor ^= e;
        return xor;


    }
}
