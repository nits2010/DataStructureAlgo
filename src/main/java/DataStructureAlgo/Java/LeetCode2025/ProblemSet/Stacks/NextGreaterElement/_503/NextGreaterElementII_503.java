package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement._503;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement.NextGreaterElementI;
import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement._496.NextGreaterElementI_496;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 8/5/2024
 * Question Category: 503. Next Greater Element II @medium
 * Description: https://leetcode.com/problems/next-greater-element-ii/description/
 *
 * <p>
 *Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
 *
 * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.nextGreaterElement.NextGreaterElementII}
 * Similar {@link}
 * extension {@link NextGreaterElementI_496}
 * <p>
 *
 * Tags
 * -----
 * @medium
 * @Array
 * @Stack
 * @MonotonicStack
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial <a href="https://leetcode.com/problems/next-greater-element-ii/solutions/389520/beat-100/">...</a>
 */
public class NextGreaterElementII_503 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{1,2,1}, new int[]{2,-1,2});
        test &= test(new int[]{1,2,3,4,3}, new int[]{2,3,4,-1,4});
        test &= test(new int[]{1, 1, 1, 1}, new int[]{-1, -1, -1, -1});
        test &= test(new int[]{3, -2, -1}, new int[]{-1, -1, 3});

        test &= test(new int[]{1, 2, 1, 3, 0}, new int[]{2, 3, 3, -1, 1});
        test &= test(new int[]{3, 9, 1, 8, 3, 0, 4}, new int[]{9, -1, 8, 9, 4, 4, 9});
        test &= test(new int[]{3, 9, 1, 8, 13, 0, 4}, new int[]{9, 13, 8, 13, -1, 4, 9});
        test &= test(new int[]{138,466,216,67,642,978,264,136,463,331,60,600,223,275,856,809,167,101,846,165,575,276,409,590,733,200,839,515,852,615,8,584,250,337,537,63,797,900,670,636,112,701,334,422,780,552,912,506,313,474,183,792,822,661,37,164,601,271,902,792,501,184,559,140,506,94,161,167,622,288,457,953,700,464,785,203,729,725,422,76,191,195,157,854,730,577,503,401,517,692,42,135,823,883,255,111,334,365,513,338,65,600,926,607,193,763,366,674,145,229,700,11,984,36,185,475,204,604,191,898,876,762,654,770,774,575,276,165,610,649,235,749,440,607,962,747,891,943,839,403,655,22,705,416,904,765,905,574,214,471,451,774,41,365,703,895,327,879,414,821,363,30,130,14,754,41,494,548,76,825,899,499,188,982,8,890,563,438,363,32,482,623,864,161,962,678,414,659,612,332,164,580,14,633,842,969,792,777,705,436,750,501,395,342,838,493,998,112,660,961,943,721,480,522,133,129,276,362,616,52,117,300,274,862,487,715,272,232,543,275,68,144,656,623,317,63,908,565,880,12,920,467,559,91,698},
                new int[]{9, 13, 8, 13, -1, 4, 9});


        System.out.println((test ? "All passed" : "Something failed"));
    }

    private static boolean test(int[] nums1, int[] expected) {
        NextGreaterElementII.SolutionUsingStack3Scans solution = new NextGreaterElementII.SolutionUsingStack3Scans();
        NextGreaterElementII.SolutionUsingStackCircularArray solutionCon = new NextGreaterElementII.SolutionUsingStackCircularArray();
        System.out.println("\nNum1:" + Arrays.toString(nums1) + "\nExpected:" + Arrays.toString(expected));
        int[] usingStacks = solution.nextGreaterElements(nums1);
        int[] usingCon = solutionCon.nextGreaterElements(nums1);
        System.out.println("usingStacks  :" + Arrays.toString(usingStacks));
        System.out.println("usingCon  :" + Arrays.toString(usingCon));
        boolean testResultUsingStacks =  CommonMethods.equalsValues(expected, usingStacks);
        boolean testResultUsingCon =  CommonMethods.equalsValues(expected, usingCon);
        System.out.println("testResultUsingStacks :" + (testResultUsingStacks ? " Passed" : " Failed"));
        System.out.println("testResultUsingCon :" + (testResultUsingCon ? " Passed" : " Failed"));
        return testResultUsingStacks == testResultUsingCon;
    }
}

class NextGreaterElementII {

    static class SolutionUsingStack3Scans {
        /**
         * The idea of calculating nextGreaterElement on a right for a circular array is same as calculating nextGreaterElement in a non-circular array which on right.
         * The only difference here is that, in a circular array, for each element whose next greater element was -1 in the right could have
         * a chance to have next greater element on the left side.
         *
         * Algo using reverse;
         * 1. first calculate the nge on the right side of the array using {@link NextGreaterElementI.SolutionUsingStack#nextGreaterElementValues}.
         * 2. Reverse the array and then again calculate using above. The Only thing to care is that while calculating from reverse, we write in an output array in reverse order as well and skipping all those output element whoes value > 0
         * hence replacing only -1 if needed.
         *
         * Algo using without a reverse;
         * We can achieve the same thing without reversing the array itself. Rather assuming its reverse.
         * 1. first calculate the nge on the "right" side of the array using {@link NextGreaterElementI.SolutionUsingStack#nextGreaterElementValues}.
         * 2. then again calculate the nge on the "left" side of the array using and updating output[i] = -1 only. as lastGreaterElementONleft
         *
         * @param nums
         * @return
         */
        public int[] nextGreaterElements(int[] nums) {

            if(nums == null || nums.length == 0)
                return nums;

            final int [] output = new int[nums.length];

            nextGreaterElementsOnRight(nums, output);
            lastGreaterElementsOnLeft(nums, output);


            return output;
        }



        private void nextGreaterElementsOnRight(int[] nums, int[] output) {
            int []stack = new int[nums.length];
            int top = -1;

            int i = 0;
            stack[++top] = i++;

            while ( i < nums.length) {

                while (top >= 0 && nums[stack[top]] < nums[i]) {
                    output[stack[top]] = nums[i];
                    top--;
                }
                stack[++top] = i++;
            }

            //for remaining element
            while (top >=0 ) {
                output[stack[top]] = Integer.MIN_VALUE;
                top--;
            }
        }



             // Simply search on the left side. since array is circular
        // the next element of the last index is the first index itself of the array.
        private void lastGreaterElementsOnLeft(int[] nums, int[] output) {

            int i = nums.length-1 , k = 0;

            //don't compare it with self hence j != i
            while (k < i) {

                if(output[i]  == Integer.MIN_VALUE){

                    if (nums[k] > nums[i]) {
                        output[i] = nums[k];
                        i--;
                    }else{
                        k++;
                    }
                }else{

                    i--;
                }

            }

            i = 0;
            while (i < nums.length) {
                if (output[i] == Integer.MIN_VALUE)
                    output[i] = -1;
                i++;
            }
        }
    }
    static class SolutionUsingStackCircularArray {
        /**
         * The idea is to assume that the array is concatenated. Now an array is concatenated, we can use the same approach as {@link NextGreaterElementI.SolutionUsingStack#nextGreaterElementValues}.
         * Since we are not going to concatenate the array, we'll make the indexing like that only. so instead of
         * 1. running loop i < nums.length -> it will be i < 2* nums.length
         * 2. instead of picking ith index, it will be modulo of nums.length
         * @param nums
         * @return
         */
        public int[] nextGreaterElements(int[] nums) {

            if(nums == null || nums.length == 0)
                return nums;

            final int [] output = new int[nums.length];
            final int[] stack = new int[nums.length];
            int top = -1;
            int i = 0;
            stack[++top] = i % nums.length;
            i++;

            while(i < 2 * nums.length){

                while (top >= 0 && nums[stack[top]] < nums[i%nums.length])
                    output[stack[top--]] = nums[i%nums.length];

                if(i < nums.length) // since if i reach beyond array length, then top can also reach beyond array length, assume array has same values
                    stack[++top] = i ;
                i++;
            }

            //for all the remaining elements
            while(top >=0 )
                output[stack[top--]] = -1;




            return output;
        }
    }
}