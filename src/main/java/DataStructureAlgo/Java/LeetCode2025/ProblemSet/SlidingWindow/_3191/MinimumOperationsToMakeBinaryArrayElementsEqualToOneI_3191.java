package DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._3191;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 5/6/2025
 * Question Title: 3191. Minimum Operations to Make Binary Array Elements Equal to One I
 * Link: https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/description
 * Description: You are given a binary array nums.
 * <p>
 * You can do the following operation on the array any number of times (possibly zero):
 * <p>
 * Choose any 3 consecutive elements from the array and flip all of them.
 * Flipping an element means changing its value from 0 to 1, and from 1 to 0.
 * <p>
 * Return the minimum number of operations required to make all elements in nums equal to 1. If it is impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,1,1,0,0]
 * <p>
 * Output: 3
 * <p>
 * Explanation:
 * We can do the following operations:
 * <p>
 * Choose the elements at indices 0, 1 and 2. The resulting array is nums = [1,0,0,1,0,0].
 * Choose the elements at indices 1, 2 and 3. The resulting array is nums = [1,1,1,0,0,0].
 * Choose the elements at indices 3, 4 and 5. The resulting array is nums = [1,1,1,1,1,1].
 * Example 2:
 * <p>
 * Input: nums = [0,1,1,1]
 * <p>
 * Output: -1
 * <p>
 * Explanation:
 * It is impossible to make all elements equal to 1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= nums.length <= 105
 * 0 <= nums[i] <= 1
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link DataStructureAlgo.Java.LeetCode2025.ProblemSet.SlidingWindow._995.MinimumNumberOfKConsecutiveBitFlips_995}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @BitManipulation
 * @Queue
 * @SlidingWindow
 * @PrefixSum <p><p>
 * Company Tags
 * -----
 * @Akuna
 * @AkunaCapital
 * @Amazon <p>
 * <p>
 * -----
 * @Editorial https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/editorial<p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MinimumOperationsToMakeBinaryArrayElementsEqualToOneI_3191 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(new int[]{0, 1, 1, 1, 0, 0}, 3));
        tests.add(test(new int[]{0, 1, 1, 1}, -1));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int[] nums, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

        int output = 0;
        boolean pass, finalPass = true;

        output = new SolutionSimpleFlips().minOperations(Arrays.copyOf(nums, nums.length));
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Simple flips", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        output = new SolutionGeneric().minOperations(Arrays.copyOf(nums, nums.length));
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Generic flips", "Pass"}, false, output, pass ? "PASS" : "FAIL");


        return finalPass;

    }

    /**
     * important observations:
     * [0,1,1,1,0,0]
     * 1. We need to pick 3 consecutive elements, and that only be picked when either or all of the 3 consecutive is 0. Means if one of them is 0, we have to pick these 3 elements
     * 2. Once we pick these 3 elements, regardless, 2 out of them is 1, they have to get fllip. Which swaps the count of 1 with count 0
     * 3. While keep doing it so, if we encounter that second last element is also zero, then it requires a flip and had to pick 3 elements, third last and last.
     * 1. if third last is 1, last is 1 and second last is 0 [example : [11 101]] then fliping it will make other two zero [101 -> 010], which make not possible to make it total 1
     * 2. if third last is 1, last is 0 and second last is 0 [example : [11 100]] then fliping it will make other one zero [100 -> 011], which make not possible to make it total 1
     * 3. if third last is 0 and last is 1 and second last is 0 [example : [11 001]] then fliping it will make other one zero [001 -> 110], which make not possible to make it
     * 4. if third last is 0 and last is 0 and second last is 0 [example : [11 000]] then fliping it will make other one zero [000 -> 111], which make possible to make it total 1
     * <p>
     * A. hence if last two elements are 0, then it's impossible to make it entire 1 at all and
     * B. if(nums[i] == 0 && i + 2 >= n) than is also not possible to make it 1 as there is no more three element left
     * <p>
     * <p>
     * [0,1,1,1,0,0]
     * i = 0, nums[i] = 0 and i+2 < n
     * =>  [1,0,0,1,0,0]
     * i = 1, nums[i] = 0 and i+2 < n
     * =>  [1,1,1,0,0,0]
     * i = 2, nums[i] = 1
     * i = 3, nums[i] = 0, i+2 < n
     * =>  [1,1,1,1,1,1]
     * rest all are 1
     * <p>
     * <p>
     * [0,1,1,1]
     * i = 0, nums[i] = 0 and 0+2 < 4
     * =>  [1,0,0,1]
     * i = 1, nums[i] = 0 and 1+2 < 4
     * =>  [1,1,1,0]
     * i = 2, nums[i] = 1
     * i = 3, nums[i] = 0 and 3+2 > 4 return -1
     */
    static class SolutionSimpleFlips {
        public int minOperations(int[] nums) {
            int n = nums.length;
            int flipCount = 0;

            for (int i = 0; i < n; i++) {

                if (nums[i] == 0) { // it will take i, i+1, i+2 to be fliped

                    //if there is no more 3 element group left, this is not possible to flip them to make entire array 1
                    if (i + 2 >= n)
                        return -1;

                    //flip i, i+1, i+2
                    nums[i] ^= 1;
                    nums[i + 1] ^= 1;
                    nums[i + 2] ^= 1;
                    flipCount++;
                }
            }
            return flipCount;
        }
    }

    static class SolutionGeneric {
        public int minOperations(int[] nums) {
            int n = nums.length;
            int flipCount = 0;
            int k = 3;
            int[] diff = new int[n + 1]; // To track when a flip effect ends
            int flip = 0;

            for (int i = 0; i < n; i++) {
                flip ^= diff[i]; // Remove expired flip effects

                //flip required at index i ? if its 0
                if ((nums[i] ^ flip) == 0) {

                    //if there is a group of k = 3 not left, then entire array can't be 1
                    if (i + k - 1 >= n)
                        return -1;

                    //change the flip state
                    flip ^= 1;
                    diff[i + k] ^= 1; // We'll end the flip at i + k
                    flipCount++;

                }

            }
            return flipCount;
        }
    }
}
