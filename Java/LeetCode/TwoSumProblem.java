package Java.LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-18
 * Description:
 */
public class TwoSumProblem {

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Set<Integer>> map = new HashMap<>();
        int result[] = new int [2];
        for (int i=0; i< nums.length; i++){
            if(map.containsKey(nums[i])){
                Set<Integer> indeices = map.get(nums[i]);
                indeices.add(i);
                map.put(nums[i], indeices);
            }else {
                Set<Integer> indeices = new HashSet<>();
                indeices.add(i);
                map.put(nums[i], indeices);
            }
        }

        for (int i=0; i<nums.length; i++){

            if ( map.containsKey(target - nums[i] )){

                Set<Integer> indeices = map.get(target - nums[i]);
                if(indeices.contains(i) && indeices.size() == 1)
                    continue;
                else for (Integer x: indeices){
                    if(x != i){
                        result[0] = i;
                        result[1] = x;
                    }
                }
            }

        }
        return result;
    }

    public static void main (String args[]){
        int nums[] = {2,7,11,15};
        int x[] = twoSum(nums, 9);
        System.out.println(x[0] + " " + x[1]);
    }

}
