package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 10/2/2024
 * Question Category: 1331. Rank Transform of an Array
 * Description: https://leetcode.com/problems/rank-transform-of-an-array
 * Given an array of integers arr, replace each element with its rank.
 *
 * The rank represents how large the element is. The rank has the following rules:
 *
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 *
 *
 * Example 1:
 *
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 * Example 2:
 *
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 * Example 3:
 *
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 *
 *
 * Constraints:
 *
 * 0 <= arr.length <= 105
 * -109 <= arr[i] <= 109
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 * @Array
 * @HashTable
 * @Sorting
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial
 */
public class RankTransformOfAnArray_1331 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{40,10,20,30}, new int[]{4,1,2,3});
        test &= test(new int[]{100,100,100}, new int[]{1,1,1});
        test &= test(new int[]{37,12,28,9,100,56,80,5,12}, new int[]{5,3,4,2,8,6,7,1,3});
        CommonMethods.printResult(test);
    }

    private static boolean test(int[] input, int[] expectedOutput) {
        System.out.println("----------------------------------");
        System.out.println("Input : " + Arrays.toString(input));
        System.out.println("expectedOutput : " + Arrays.toString(expectedOutput));
        int[] result = new Solution1().arrayRankTransform(Arrays.copyOf(input, input.length));
        boolean output1 = CommonMethods.equalsValues(expectedOutput, result);
        System.out.println("returned output : " + Arrays.toString(result) + " result : " + (output1?" Pass":" Fail"));

        result = new Solution2().arrayRankTransform(Arrays.copyOf(input, input.length));
        boolean output2 = CommonMethods.equalsValues(expectedOutput, result);
        System.out.println("returned output : " + Arrays.toString(result) + " result : " + (output2?" Pass":" Fail"));
        System.out.println("----------------------------------");

        return output1 && output2;
    }

    static class Solution1{
         public int[] arrayRankTransform(int[] arr) {
             if(arr == null || arr.length == 0)
                 return new int[]{};

             Map<Integer, List<Integer>> map = new HashMap<>();
             for(int i=0; i<arr.length; i++){
                 List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
                 list.add(i);
                 map.put(arr[i], list);
             }
             int []output = new int[arr.length];
             Arrays.sort(arr);
             int rank = 1;
             for (int k : arr) {
                 List<Integer> list = map.getOrDefault(k, new ArrayList<>());

                 for (int j : list) {
                     output[j] = rank;
                 }
                 map.remove(k);
                 if (!list.isEmpty())
                     rank++;
             }
             return output;

         }
    }

    static class Solution2{
        public int[] arrayRankTransform(int[] arr) {
            if(arr == null || arr.length == 0)
                return new int[]{};

            int []temp =Arrays.copyOf(arr, arr.length);

            Arrays.sort(arr);

            Map<Integer, Integer> keyVsRank = new HashMap<>();

            int rank = 1;
            for(int i=0; i<arr.length; i++){
                if(!keyVsRank.containsKey(arr[i])){
                    keyVsRank.put(arr[i], rank++);
                }
            }

            int []output = new int[arr.length];


            for(int i=0; i<temp.length; i++){
                output[i] = keyVsRank.get(temp[i]);
            }
            return output;
        }
    }

}
