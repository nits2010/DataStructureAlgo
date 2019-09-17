package Java.companyWise.facebook;

import Java.HelpersToPrint.GenericPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-24
 * Description: https://leetcode.com/problems/longest-consecutive-sequence/
 * https://www.geeksforgeeks.org/longest-consecutive-subsequence/
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * <p>
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

    public static void main(String []args) {

        test(new int[]{100, 4, 200, 1, 3, 2});
        test(new int[]{100, 101, 102, 103, 4, 200, 1, 3, 2});
        test(new int[]{100, 101, 102, 103, 104, 105, 4, 200, 1, 3, 2});
        test(new int[]{100, 103, 105, 106, 107, 8, 6, 4, 2, 0});
        test(new int[]{100, 103, 105, 107, 109, 8, 6, 4, 2, 0});

        test(new int[]{100, 100, 101, 107, 109, 8, 6, 4, 2, 0});
    }

    public static void test(int nums[]) {
        Sort sort = new Sort();
        UsingMap map = new UsingMap();

        GenericPrinter.print(nums);
        System.out.println("Sort" + sort.longestConsecutive(nums));
        System.out.println("Map " + map.longestConsecutive(nums));

        System.out.println();
    }


    static class Sort {
        public int longestConsecutive(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            Arrays.sort(nums);

            int longestStreak = 1;
            int currentStreak = 1;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    if (nums[i] == nums[i - 1] + 1) {
                        currentStreak += 1;
                    } else {
                        longestStreak = Math.max(longestStreak, currentStreak);
                        currentStreak = 1;
                    }
                }
            }

            return Math.max(longestStreak, currentStreak);

        }

    }

    static class UsingMap {


        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num);
            }

            int longestStreak = 0;

            for (int num : set) {
                if (!set.contains(num - 1)) {
                    int currentNum = num;
                    int currentStreak = 1;

                    while (set.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }

                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }

            return longestStreak;
        }
    }

}