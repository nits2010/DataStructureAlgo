package DataStructureAlgo.Java.LeetCode.pair.element.problems.twoSum;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-06-18
 * Description: https://leetcode.com/problems/two-sum/
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum2Sum {


    /**
     * Runtime: 2 ms, faster than 98.89% of Java online submissions for Two Sum.
     * Memory Usage: 37.5 MB, less than 98.95% of Java online submissions for Two Sum.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumMapWithoutDuplicate(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int result[] = new int[2];
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {

            int test = target - nums[i];
            if (map.containsKey(test) && map.get(test) != i) {
                result[0] = i;
                result[1] = map.get(test);
                break;
            }


        }
        return result;
    }

    /**
     * Runtime: 5 ms, faster than 45.67% of Java online submissions for Two Sum.
     * Memory Usage: 39.3 MB, less than 5.65% of Java online submissions for Two Sum.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumMapDuplicate(int[] nums, int target) {

        Map<Integer, Set<Integer>> map = new HashMap<>();
        int result[] = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Set<Integer> indices = map.get(nums[i]);
                indices.add(i);
                map.put(nums[i], indices);
            } else {
                Set<Integer> indices = new HashSet<>();
                indices.add(i);
                map.put(nums[i], indices);
            }
        }

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {

                Set<Integer> indices = map.get(target - nums[i]);
                if (indices.contains(i) && indices.size() == 1)
                    continue;
                else for (Integer x : indices) {
                    if (x != i) {
                        result[0] = i;
                        result[1] = x;
                        break;
                    }
                }
            }

        }
        return result;
    }

    /**
     * Runtime: 36 ms, faster than 11.07% of Java online submissions for Two Sum.
     * Memory Usage: 37.5 MB, less than 98.95% of Java online submissions for Two Sum.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumSorting(int[] nums, int target) {

        if (null == nums || nums.length == 0)
            return new int[]{-1, -1};

        int temp[][] = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            temp[i][0] = nums[i];
            temp[i][1] = i;
        }


        Arrays.sort(temp, Comparator.comparingInt(i -> i[0]));

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {

            int x = temp[i][0] + temp[j][0];

            if (x == target) {
                return new int[]{temp[i][1], temp[j][1]};
            } else if (x > target) {
                j--;
            } else
                i++;
        }

        return new int[]{-1, -1};
    }


    /**
     * Single pass algo
     *
     * @param nums
     * @param target
     * @return Runtime: 2 ms, faster than 98.89% of Java online submissions for Two Sum.
     * Memory Usage: 37.7 MB, less than 98.82% of Java online submissions for Two Sum.
     */
    public static int[] twoSum(int[] nums, int target) {
        if (null == nums || nums.length == 0)
            return new int[]{-1, -1};

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int item = nums[i];
            int complement = target - item;

            if (map.containsKey(complement))
                return new int[]{map.get(complement), i};
            else
                map.put(item, i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String []args) {
        int nums[] = {1, 8, 5, 4};
        int x[] = twoSum(nums, 9);
        System.out.println(x[0] + " " + x[1]);
    }

}
