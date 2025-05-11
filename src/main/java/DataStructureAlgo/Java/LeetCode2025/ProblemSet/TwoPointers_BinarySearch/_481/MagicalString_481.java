package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._481;

import java.util.*;

import DataStructureAlgo.Java.helpers.CommonMethods;


/**
 * Author: Nitin Gupta
 * Date: 16/04/25
 * Question Title: 481. Magical String
 * Link: https://leetcode.com/problems/magical-string/description/
 * Description: A magical string s consists of only '1' and '2' and obeys the following rules:
 * <p>
 * The string s is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string s itself.
 * The first few elements of s is s = "1221121221221121122……". If we group the consecutive 1's and 2's in s, it will be "1 22 11 2 1 22 1 22 11 2 11 22 ......" and the occurrences of 1's or 2's in each group are "1 2 2 1 1 2 1 2 2 1 2 2 ......". You can see that the occurrence sequence is s itself.
 * <p>
 * Given an integer n, return the number of 1's in the first n number in the magical string s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 6
 * Output: 3
 * Explanation: The first 6 elements of magical string s is "122112" and it contains three 1's, so return 3.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 105
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
 * @TwoPointers
 * @String <p><p>
 * Company Tags
 * -----
 * @Google <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MagicalString_481 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(test(1, 1));
        tests.add(test(2, 1));
        tests.add(test(3, 1));
        tests.add(test(4, 2));
        tests.add(test(5, 3));
        tests.add(test(6, 3));
        CommonMethods.printAllTestOutCome(tests);
    }

    private static boolean test(int num, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"num", "Expected"}, true, num, expected);

        int output = 0;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.magicalString(num);
        pass = CommonMethods.compareResultOutCome(output, expected, true);
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "PASS" : "FAIL");

        return finalPass;

    }


//"1 22 11 2 1 22
// 1 22 11 2 1 22
// 1 2  2  1 1 2
// 1 22 11 2

    // 6
// n=1 -> 1
// n=2 -> 12
// n = 3 -> 122
// n =4 -> 1221 { 121}
// Start: "122"
// After one "1": gives "2" -> "122"
// After "22": gives "11" -> "12211"
// Each subsequent "1": gives a "2" -> "122112"
// Each of the two "1": each gives "2", so -> "1221122"
// Next "2": gives "11" -> "122112211"
// After "1": gives "2" -> "1221122112"
// After "2": gives "11" -> "122112211211"
// Another "1": gives "2" -> "1221122112112"
// Another "1": gives "2" -> "12211221121122"
// After "2": gives "11" -> "1221122112112211"
// Next "1": gives "2" -> "12211221121122112"
// Another "1": gives "2" -> "122112211211221122"
    static class Solution {
        public int magicalString(int n) {
            if (n < 4)
                return 1;
            //start is n= 3 [122]
            int result = 1;
            int[] temp = new int[n + 1];
            temp[0] = 1;
            temp[1] = 2;
            temp[2] = 2;

            int start, end;

            start = 2; //fill till n=3 or index = 2
            end = 3;
            int num = 1; //next number would be 2 only as we have one 1 at start

            while (end < n) {

                for (int i = temp[start]; i > 0; i--) {
                    //repeat num, item times

                    temp[end] = num;

                    //check what we inserted and make sure we don't cross total items in temp more than n
                    if (temp[end] == 1 && end < n)
                        result++;
                    end++; // for next index

                }

                //move start
                start++;

                //num ^= 3;
                if (num == 1)
                    num = 2;
                else
                    num = 1;

            }
            System.out.println("Magical String: " + Arrays.toString(Arrays.copyOf(temp, n)));
            return result;
        }
    }

}
