package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.bitManipulations._2275;

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.*;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date:07/11/24
 * Question Category: 2275. Largest Combination With Bitwise AND Greater Than Zero
 * Description: https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/description/?envType=daily-question&envId=2024-11-07
 * The bitwise AND of an array nums is the bitwise AND of all integers in nums.
 * <p>
 * For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
 * Also, for nums = [7], the bitwise AND is 7.
 * You are given an array of positive integers candidates. Evaluate the bitwise AND of every combination of numbers of candidates. Each number in candidates may only be used once in each combination.
 * <p>
 * Return the size of the largest combination of candidates with a bitwise AND greater than 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [16,17,71,62,12,24,14]
 * Output: 4
 * Explanation: The combination [16,17,62,24] has a bitwise AND of 16 & 17 & 62 & 24 = 16 > 0.
 * The size of the combination is 4.
 * It can be shown that no combination with a size greater than 4 has a bitwise AND greater than 0.
 * Note that more than one combination may have the largest size.
 * For example, the combination [62,12,24,14] has a bitwise AND of 62 & 12 & 24 & 14 = 8 > 0.
 * Example 2:
 * <p>
 * Input: candidates = [8,8]
 * Output: 2
 * Explanation: The largest combination [8,8] has a bitwise AND of 8 & 8 = 8 > 0.
 * The size of the combination is 2, so we return 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 105
 * 1 <= candidates[i] <= 107
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Array
 * @HashTable
 * @BitManipulation
 * @Counting <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class LargestCombinationWithBitwiseANDGreaterThanZero_2275 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
        tests.add(tests(new int[]{16, 17, 71, 62, 12, 24, 14}, 4));
        tests.add(tests(new int[]{8, 8}, 2));

        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(int[] input, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"input", "Expected"}, true, input, expected);

        int output;
        boolean pass, finalPass = true;

        SolutionCountAll solutionCountAll = new SolutionCountAll();
        output = solutionCountAll.largestCombination(input);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"CountAll", "Pass"}, false, output, pass ? "Pass" : "Fail");

        SolutionCountIndividually solutionCountIndividually = new SolutionCountIndividually();
        output = solutionCountIndividually.largestCombination(input);
        pass = output == expected;
        finalPass &= pass;
        CommonMethods.printTestOutcome(new String[]{"CountIndividually", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass;

    }

    /**
     * Brute Force:
     * 1. Generate All combination
     * 2. Start with largest size combination and get bitWise &, if > 0 return
     * <p>
     * Observation:
     * 1. The largest combination is given array it self.
     * 2. Bitwise end will only be 0 if either of the number is zero or bitwise & of any two or more number become zero.
     * 3. Bitwise & of two numbers is zero when the set/uset bit position of both number differs. Means 'a' has set bits like 101 (5) while 'b' has bits like 010 (2) then 5&2 = 0.
     * <p>
     * 4. Bitwise & will not be zero if at least one set bit has same position in both the numbers. for example;
     * 5 - 101
     * 4 - 100 both has set bit at least significant position.
     * 5&4 = 100
     * <p>
     * This implies that, all the numbers, whose set bit position are same can be grouped together.
     * <p>
     * Example: 16,17,71,62,12,24,14
     * pos=
     * 16 = 00010000 [ 4th from left]
     * 17 = 00010001 [4th and 8th from left]
     * 71 = 01000111 [2nd, 6, 7, 8]
     * 62 = 00111110 [3,4,5,6,7]
     * 12 = 00001100 [5,6]
     * 24 = 00011000 [4,5]
     * 14 = 00001110 [5,6,7]
     * <p>
     * 2nd = 71
     * 33d = 62,
     * 4th = 16, 17, 62, 24
     * 5th = 62, 12, 24, 14
     * 6th = 71, 62, 14
     * 7th = 71, 62, 14
     * 8th = 71
     * .....
     * <p>
     * We can see that, there are two groups of length 4 having either 4th or 5th bit set which will make bitwise & of them. > 0
     * <p>
     * Since we are not focus on finding the largest bitwise & result, the either of the result can be return.
     * <p>
     * Now, the problem is that, how many such bits we need to track ?
     * <p>
     * Since 1 <= candidates[i] <= 10^7
     * take log2(x) + 1 = log2(10^7) + 1 = ~24 bits
     */

    static class SolutionCountAll {
        public int largestCombination(int[] candidates) {
            final int[] setBitCount = new int[24];

            //check all bits
            int max = 0;
            for (int i = 0; i < 24; i++) {

                //check all bits of all candidates
                for (int n : candidates) {

                    //check does ith bit is set in 'n'
                    if ((n & (1 << i)) != 0)
                        setBitCount[i]++; //count it
                }
                max = Math.max(max, setBitCount[i]);
            }

            return max;
        }
    }

    static class SolutionCountIndividually {
        public int largestCombination(int[] candidates) {
            final int[] setBitCount = new int[24];
            int max = 0;
            for (int n : candidates) {

                int pos = 23;
                while (n > 0) {
                    int count = n & 1; //check pos bit is set or not
                    setBitCount[pos] += count; //get the count
                    n = n >> 1; //remove the pos bit
                    max = Math.max(max, setBitCount[pos]);  //get the max count at this pos
                    pos--; //move


                }

            }

            return max;
        }
    }

}
