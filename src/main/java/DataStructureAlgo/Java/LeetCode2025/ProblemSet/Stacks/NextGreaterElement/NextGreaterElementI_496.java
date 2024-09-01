package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.NextGreaterElement;

import DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.DailyTemperatures_739;
import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 8/5/2024
 * Question Category: 496. Next Greater Element I @easy
 * Description: https://leetcode.com/problems/next-greater-element-i/description/
 *
 * <p>
 *The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.nextGreaterElement.NextGreaterElementII}
 * Similar {@link DailyTemperatures_739}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @easy
 * @Array
 * @HashTable
 * @Stack
 * @MonotonicStack
 *
 *
 * <p>
 * Company Tags
 * -----
 */
public class NextGreaterElementI_496 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new int[]{4,1,2}, new int[]{1,3,4,2}, new int[]{-1,3,-1});
        testResult &= test(new int[]{2,4}, new int[]{1,2,3,4}, new int[]{3,-1});
        testResult &= test(new int[]{1,2,3,4,5}, new int[]{5,4,3,2,1}, new int[]{-1,-1,-1,-1,-1});
        System.out.println((testResult ? "All passed" : "Something failed"));
    }

    private static boolean test(int[] nums1, int[] nums2, int[] expected) {
        NextGreaterElementI.SolutionUsingStack solution = new NextGreaterElementI.SolutionUsingStack();
        NextGreaterElementI.SolutionWithDS solutionWithDS = new NextGreaterElementI.SolutionWithDS();
        System.out.println("\nNum1:" + Arrays.toString(nums1) + "\nNum2:" + Arrays.toString(nums2) + "\nExpected:" + Arrays.toString(expected));
        int[] usingStacks = solution.nextGreaterElement(nums1, nums2);
        int[] actualWithoutDS = solutionWithDS.nextGreaterElement(nums1, nums2);
        System.out.println("usingStacks  :" + Arrays.toString(usingStacks) + "\nactualWithoutDS:" + Arrays.toString(actualWithoutDS));
        boolean testResultUsingStacks =  CommonMethods.equalsValues(expected, usingStacks);
        boolean testResultNoDS =  CommonMethods.equalsValues(expected, actualWithoutDS);
        System.out.println("testResultUsingStacks :" + (testResultUsingStacks ? " Passed" : " Failed"));
        System.out.println("testResultNoDS :" + (testResultNoDS ? " Passed" : " Failed"));
        return testResultUsingStacks == testResultNoDS;
    }
}

class NextGreaterElementI{

    /**
     * Returns an array of integers that represent the next greater elements for each element in nums1.
     * The next greater element for an element in nums1 is the first element in nums2 that is greater than the element.
     * If there is no next greater element, -1 is returned.
     *
     * @param  nums1  an array of integers
     * @param  nums2  an array of integers
     * @return        an array of integers representing the next greater elements for each element in nums1
     *
     * @complexity Time/Space O(n) / O(n)
     */
   static class SolutionUsingStack {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            if(nums1 == null)
                return nums1;

            if(nums2 == null)
                return null;

            if(nums1.length > nums2.length)
                return null ;

            final Map<Integer,Integer> mapOfIndex = nextGreaterElementValuesForward(nums2);

            final int [] output = new int[nums1.length];
            for (int i=0; i<nums1.length; i++){
                output[i] = mapOfIndex.getOrDefault(nums1[i],-1);
            }

            return output;

        }

        /**
         * O(n)/O(n)
         * This track the next greater element for each element in nums2 from backwards.
         * @param nums2
         * @return
         *
         * {@link DataStructureAlgo.Java.LeetCode2025.medium.DailyTemperatures.SolutionUsingStacks#dailyTemperatures}
         */
       private Map<Integer, Integer> nextGreaterElementValuesBackWard(int[] nums2) {
           final Map<Integer,Integer> nextGreaterElement = new HashMap<>();

            int top = -1;
            int []stack = new int[nums2.length];


            //greater element of last element is always -1
            nextGreaterElement.put(nums2[nums2.length-1], -1);

            //push this element as it could be a greater element previous to its index.
            stack[++top] = nums2[nums2.length-1];
            int i = nums2.length-2;

            //calculate greater element for all the previous index
            while (i >=0){

                //pop element from stack, until we have greater element present in it.
                while(top>=0 && stack[top] <= nums2[i])
                    top--;

                //if there is no greater element in the stack, then output -1
                if(top == -1) //stack is empty
                    nextGreaterElement.put(nums2[i], -1);
                else //if there is then that element is the greater element for current element
                    nextGreaterElement.put(nums2[i], stack[top]);

                //push this element as it could be a greater element previous to its index.
                stack[++top] = nums2[i];
                i--;
            }
            return nextGreaterElement;
       }

        /**
         * {@link DataStructureAlgo.Java.LeetCode2025.medium.DailyTemperatures.SolutionUsingStacks#dailyTemperaturesForward}
         * @param nums2
         * @return
         */
        private Map<Integer, Integer> nextGreaterElementValuesForward( int[] nums2) {
            final Map<Integer,Integer> nextGreaterElement = new HashMap<>();

            int top = -1;
            int []stack = new int[nums2.length];

            int i = 0;
            stack[++top] = nums2[i];

            int j = i+1;
            while (j < nums2.length){

                while(top >=0 && stack[top] < nums2[j]){
                    nextGreaterElement.put(stack[top], nums2[j]);
                    top--;
                }
                stack[++top] = nums2[j];
                j++;
            }
            while (top >=0 ){
                nextGreaterElement.put(stack[top--], -1);
            }

            return nextGreaterElement;


        }
   }

    static class SolutionWithDS {
       //O(m*n)/O(1)
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
                final int [] indexCache = new int[10000];
                for (int i=0; i<nums2.length; i++)
                    indexCache[nums2[i]] =  i;

                for (int i = 0; i<nums1.length; i++){
                    nums1[i] = nextGreaterElement(indexCache[nums1[i]], nums2);
                }
                return nums1;
        }

        //O(n)/O(1)
        private int nextGreaterElement(int index, int []nums){
            for (int i=index+1; i<nums.length; i++){
                if (nums[i] > nums[index])
                    return nums[i];
            }
            return -1;
        }
    }
}