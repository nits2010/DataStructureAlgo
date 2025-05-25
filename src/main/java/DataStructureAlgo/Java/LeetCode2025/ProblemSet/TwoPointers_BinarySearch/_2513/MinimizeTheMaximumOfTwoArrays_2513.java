package DataStructureAlgo.Java.LeetCode2025.ProblemSet.TwoPointers_BinarySearch._2513;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date:14/11/24
 * Question Category: 2513. Minimize the Maximum of Two Arrays
 * Description: https://leetcode.com/problems/minimize-the-maximum-of-two-arrays/description
 * We have two arrays arr1 and arr2 which are initially empty. You need to add positive integers to them such that they satisfy all the following conditions:
 * <p>
 * arr1 contains uniqueCnt1 distinct positive integers, each of which is not divisible by divisor1.
 * arr2 contains uniqueCnt2 distinct positive integers, each of which is not divisible by divisor2.
 * No integer is present in both arr1 and arr2.
 * Given divisor1, divisor2, uniqueCnt1, and uniqueCnt2, return the minimum possible maximum integer that can be present in either array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: divisor1 = 2, divisor2 = 7, uniqueCnt1 = 1, uniqueCnt2 = 3
 * Output: 4
 * Explanation:
 * We can distribute the first 4 natural numbers into arr1 and arr2.
 * arr1 = [1] and arr2 = [2,3,4].
 * We can see that both arrays satisfy all the conditions.
 * Since the maximum value is 4, we return it.
 * Example 2:
 * <p>
 * Input: divisor1 = 3, divisor2 = 5, uniqueCnt1 = 2, uniqueCnt2 = 1
 * Output: 3
 * Explanation:
 * Here arr1 = [1,2], and arr2 = [3] satisfy all conditions.
 * Since the maximum value is 3, we return it.
 * Example 3:
 * <p>
 * Input: divisor1 = 2, divisor2 = 4, uniqueCnt1 = 8, uniqueCnt2 = 2
 * Output: 15
 * Explanation:
 * Here, the final possible arrays can be arr1 = [1,3,5,7,9,11,13,15], and arr2 = [2,6].
 * It can be shown that it is not possible to obtain a lower maximum satisfying all conditions.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= divisor1, divisor2 <= 105
 * 1 <= uniqueCnt1, uniqueCnt2 < 109
 * 2 <= uniqueCnt1 + uniqueCnt2 <= 109
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
 * @BinarySearch <p>
 * Company Tags
 * -----
 * @Editorial
 */

public class MinimizeTheMaximumOfTwoArrays_2513 {

    public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        tests.add(tests(2, 7, 1, 3, 4));
        tests.add(tests(3, 5, 2, 1, 3));
        tests.add(tests(2, 4, 8, 2, 15));
        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2, int expected) {
        //add print here
        CommonMethods.printTest(new String[]{"divisor1", "divisor2", "uniqueCnt1", "uniqueCnt2", "Expected"}, true, divisor1, divisor2, uniqueCnt1, uniqueCnt2, expected);

        int output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
        output = solution.minimizeSet(divisor1, divisor2, uniqueCnt1, uniqueCnt2);
        pass = output == expected;
        finalPass &= pass;

        CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass;

    }

    /**
     * We need to find the smallest number `x` such that we can pick:
     * - `uniqueCnt1` numbers not divisible by `divisor1` for A1
     * - `uniqueCnt2` numbers not divisible by `divisor2` for A2
     * - All numbers must be distinct
     *
     * Total required numbers = uniqueCnt1 + uniqueCnt2
     * The constraint says this total ≤ 10^9, so we binary search in [1, 10^10]
     *
     * For a candidate `mid`, we check if:
     * - We have enough numbers not divisible by divisor1 to fill A1
     * - Enough not divisible by divisor2 to fill A2
     * - And any shortfall can be covered using the remaining numbers
     *
     * To avoid double-counting, we exclude numbers divisible by both divisors using LCM
     * LCM = (divisor1 * divisor2) / GCD(divisor1 , divisor2)
     * gcd(a,b) = ( a == 0 ? b : gcd(b%a, a) )  where b > a
     */
    static class Solution {
        public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
            //[1, 10^10]
            long low = 1; //starting from the first number
            long high = (long) Math.pow(10, 10);  //Total numbers available
            long output = 0;


            while (low <= high) {

                //get the mid, a possible range to fill both arrays
                long mid = low + (high - low) / 2;


                //check is it possible to fill both the array fulfilling the constraints
                if (isPossible(divisor1, divisor2, uniqueCnt1, uniqueCnt2, mid)) {

                    //yes, cache the output
                    output = mid;

                    //try to minimize it
                    high = mid - 1;
                } else {
                    //need more numbers to fill
                    low = mid + 1;
                }
            }

            return (int) output;
        }


        private boolean isPossible(long divisor1, long divisor2, long uniqueCnt1, int uniqueCnt2, long range) {

            //count numbers that can be divided by both the divisions
            long lcm = lcm(divisor1, divisor2);
            long common = range / lcm;

            //count numbers that can be filled in A1 - common, since they cannot be filled in A1
            long cArr1 = range / divisor2 - common;

            //count numbers that can be filled in A2 - common, since they cannot be filled in A2
            long cArr2 = range / divisor1 - common;

            //total remaining numbers
            long rem = range - (cArr1 + cArr2 + common);

            //the filled numbers are either enough not sufficient, then find the required numbers
            if (uniqueCnt1 >= cArr1) {
                rem -= (uniqueCnt1 - cArr1);
            }

            if (uniqueCnt2 >= cArr2) {
                rem -= (uniqueCnt2 - cArr2);
            }

            //if post filling the numbers are left or none, then range is able to fill the arrays.
            return rem >= 0;


        }

        private long lcm(long u, long v) {
            return (u * v) / gcd(u, v);
        }

        private long gcd(long u, long v) {
            if (u == 0)
                return v;
            return gcd(v % u, u);
        }
    }
}
