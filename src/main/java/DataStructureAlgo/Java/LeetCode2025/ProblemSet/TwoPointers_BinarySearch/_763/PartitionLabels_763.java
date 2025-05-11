package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._763;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 4/15/2025
 * Question Title: 763. Partition Labels
 * Link: https://leetcode.com/problems/partition-labels/description/
 * Description: You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
 * <p>
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * <p>
 * Return a list of integers representing the size of these parts.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * Example 2:
 * <p>
 * Input: s = "eccbbbbdec"
 * Output: [10]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 500
 * s consists of lowercase English letters
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @HashTable
 * @TwoPointers
 * @String
 * @Greedy <p><p>
 * Company Tags
 * -----
 * @Amazon
 * @Google <p>
 * -----
 * @Editorial https://leetcode.com/problems/partition-labels/editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class PartitionLabels_763 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test("ababcbacadefegdehijhklij", Arrays.asList(9, 7, 8)));
        tests.add(test("eccbbbbdec", Arrays.asList(10)));


        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(String grid, List<Integer> expected) {
        //add print here
        CommonMethods.printTest(new String[]{"Grid", "Expected"}, true, grid, expected);

        List<Integer> output ;
        boolean pass, finalPass = true;

        Solution_TwoPointer solutionTwoPointer = new Solution_TwoPointer();
        output = solutionTwoPointer.partitionLabels(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Two Pointer", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        Solution_MergeIntervals solutionMergeIntervals = new Solution_MergeIntervals();
        output = solutionMergeIntervals.partitionLabels(grid);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;
        CommonMethods.printTest(new String[]{"Merge Interval", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }

    static class Solution_TwoPointer {
        public List<Integer> partitionLabels(String s) {
            int[] appearsAt = new int[26]; // store the appearance of a character last time

            //pre-proces input
            char[] chars = s.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                appearsAt[index] = i; // this is the last time appearing

            }

            List<Integer> result = new ArrayList<>();
            int start = 0, end = 0;
            int i = 0; //current index being evaluated

            while (i < chars.length) {
                char c = chars[i];

                int index = c - 'a';

                int last = appearsAt[index];

                end = Math.max(end, last);

                //partition is ending at me?
                if (end == i) {
                    result.add(i - start + 1);
                    start = i + 1;
                }
                i++;

                //i = last + 1 is wrong
                // Because even if the current character ends at a certain index, other characters in between might have a later last occurrence,
                // and you must include those in the current partition
            }

            return result;

        }
    }

    static class Solution_MergeIntervals {
        public List<Integer> partitionLabels(String s) {
            int[][] appearsAt = new int[2][26]; // store the appearance of a character-first time and last time
            Arrays.fill(appearsAt[0], -1);

            //pre-proces input
            char[] chars = s.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';

                if (appearsAt[0][index] == -1) // this is the first time appearing
                    appearsAt[0][index] = i;

                appearsAt[1][index] = i; // this is the last time appearing

            }

            List<Integer> result = new ArrayList<>();
            int start = 0, end = 0;
            int i = 0; //current index being evaluated

            while (i < chars.length) {
                char c = chars[i];

                int index = c - 'a';

                //this tell where our partition starts and its boundary
                int first = appearsAt[0][index];
                int last = appearsAt[1][index];

                //if the last partition end boundary is beyond it, then add it to the result
                if (end < first) {
                    result.add(end - start + 1);
                    start = i;
                    end = i;
                }

                //update the boundary of the last partition
                end = Math.max(end, last);

                i++;

                //i = last + 1 is wrong
                // Because even if the current character ends at a certain index, other characters in between might have a later last occurrence, and you must include those in the current partition
            }

            if (end - start + 1 > 0)
                result.add(end - start + 1);
            return result;

        }
    }

}
