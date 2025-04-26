package DataStructureAlgo.Java.LeetCode;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-12
 * Description: 611. Valid Triangle Number
 * https://leetcode.com/problems/valid-triangle-number/submissions/
 *
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 * https://www.geeksforgeeks.org/find-number-of-triangles-possible/
 */
public class ValidTriangleNumber {

    public static void main(String []args) {
        int arr[] = {10, 21, 22, 100, 101, 200, 300};
        SolutionNumberOfTriangles sol = new SolutionNumberOfTriangles();
        System.out.println(sol.triangleNumber(arr));

    }
}

class SolutionNumberOfTriangles {
    public int triangleNumber(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length;

        if (n < 3) //minimum 3 sides are required
            return 0;

        int count = 0;
        Arrays.sort(nums);
        int l, r;
        for (int i = n - 1; i > 1; i--) {
            l = 0;
            r = i - 1;
            while (l < r) {

                //the sides are at l, r and i
                //to make triangle, sum of two side should be greater than third
                if (nums[l] + nums[r] > nums[i]) {
                    //as l and r makes tringle, then all from l to r will make tringle as data is sorted
                    count += r - l;

                    r--; //if they make then, take other smaller size of side
                } else
                    l++; //if they don't then, take other bigger size of side
            }
        }

        return count;

    }
}