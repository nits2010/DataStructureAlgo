package DataStructureAlgo.Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-19
 * Description: 78. Subsets https://leetcode.com/problems/subsets/
 * Power set
 */
public class SubSet {

    public static List<List<Integer>> subsets(int[] nums){
        return subsetsRecursive(nums);
    }

    //Faster
    public static List<List<Integer>> subsetsRecursive(int[] nums){
        int size = nums.length;

        List<List<Integer>> result = new LinkedList<>();
        Set<List<Integer>> globalSet = new HashSet<>();
        subsetsRecursive(nums, result, new LinkedList<>(), 0, size,globalSet);
        return result;
    }

    public static void subsetsRecursive(int [] nums, List<List<Integer>> result, LinkedList<Integer> temp, int start, int end ,  Set<List<Integer>> globalSet){
        if(!globalSet.contains(temp))
            result.add ( new LinkedList<>(temp));

        globalSet.add(temp);

        for ( int i = start; i<end; i++){
            temp.add(nums[i]);
            subsetsRecursive(nums, result, temp, i+1, end, globalSet);
            temp.removeLast();
        }
    }


    //Slower
    public static List<List<Integer>> subsetsIterative(int[] nums) {

        List<List<Integer>> result = new LinkedList<>();

        Set<List<Integer>> globalSet = new HashSet<>(); // used if there is duplicate integers

        int size = nums.length;

        long subsetSize = (long)Math.pow(2, size);

        for (int c = 0 ; c< subsetSize; c++){

            List<Integer> set = new ArrayList<>();
            for (int i = 0 ; i< size; i++){

                //if ith bit is set in the current counter
                if ( (c & (1<< i ))>0){
                    set.add(nums[i]);
                }
            }
            if(!globalSet.contains(set))
                result.add(set);

            globalSet.add(set);
        }
        return result;
    }

    public static void main (String []args){
        int nums1[] = {1,2,3};
        System.out.println(subsets(nums1));

        int nums2[] = {1,2,3,3};
        System.out.println(subsets(nums2));
    }
}
