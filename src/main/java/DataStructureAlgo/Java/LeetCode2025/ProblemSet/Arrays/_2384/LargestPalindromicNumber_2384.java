package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays._2384;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date: 10/7/2024
 * Question Category: 2384. Largest Palindromic Number
 * Description: 2384. Largest Palindromic Number
 * <p>
 * You are given a string num consisting of digits only.
 * <p>
 * Return the largest palindromic integer (in the form of a string) that can be formed using digits taken from num. It should not contain leading zeroes.
 * <p>
 * Notes:
 * <p>
 * You do not need to use all the digits of num, but you must use at least one digit.
 * The digits can be reordered.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "444947137"
 * Output: "7449447"
 * Explanation:
 * Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
 * It can be shown that "7449447" is the largest palindromic integer that can be formed.
 * Example 2:
 * <p>
 * Input: num = "00009"
 * Output: "9"
 * Explanation:
 * It can be shown that "9" is the largest palindromic integer that can be formed.
 * Note that the integer returned should not contain leading zeroes.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= num.length <= 105
 * num consists of digits.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * @medium
 * @HashTable
 * @String
 * @Greedy
 * @Counting <p><p>
 * Company Tags
 * -----
 * <p><p>
 * @Editorial
 */
public class LargestPalindromicNumber_2384 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test("098990", "90909");
        test &= test("57575757", "77555577");
        test &= test("5736732", "73637");
        test &= test("444947137", "7449447");
        test &= test("00009", "9");
        test &= test("123454321", "432151234");
        test &= test("112233445566", "654321123456");
        test &= test("111222333444555666", "6543216123456");
        test &= test("0000000", "0");
        test &= test("0000001", "1");
        test &= test("00000012", "2");
        test &= test("000000000000", "0");
        CommonMethods.printResult(test);
    }

    private static boolean test(String num, String expected) {
        System.out.println("----------------------------------");
        System.out.println("num : " + num + " expected : " + expected);

        String output;
        boolean pass, finalPass = true;


        SolutionWithoutSort solutionWithoutSort = new SolutionWithoutSort();
        output = solutionWithoutSort.largestPalindromic(num);
        pass = output.equals(expected);
        System.out.println("Without Sort : " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        return finalPass;
    }

    /**
     *
     */
    static class SolutionWithoutSort {
        public String largestPalindromic(String num) {
            if (num == null || num.isEmpty())
                return "";

            int[] freq = new int[10];

            //count frequency of digits
            for (char c : num.toCharArray()) {
                int digit = c - '0';
                freq[digit]++;
            }

            //get only one digit which has odd count of frequency
            int oddDigit = -1;
            boolean onlyZero = true; //track that only zero is there in the string.


            //get the largest digit only
            for (int i = 9; i >= 0; i--) {
                if (freq[i] != 0) {
                    if (i != 0)
                        onlyZero = false;

                    if (freq[i] % 2 == 1) {
                        oddDigit = i;
                        freq[oddDigit]--; //used the digit
                        break;
                    }

                }
            }

            //if a set contains only 1 digit and its 0, then we can make only '0'
            if (onlyZero)
                return "0";

            //form the largest palindromic number
            StringBuilder firstHalf = new StringBuilder();

            //all even length digits will be used putting half of them on the left side of the center
            // and remaining on the right side of the center.
            //post that, we can add the odd digit at the center.
            //we will form the first half of the palindromic number and add an odd digit and then copy the reverse of the first half
            //start from 9 to 1 so that we get the largest palindromic number
            for (int i = 9; i > 0; i--) {
                if (freq[i] > 0) {
                    int count = freq[i] / 2;

                    //add count number of 'i' in the first half, remaining will go in the second half
                    // if count = 0 ( example : freq[i] = 1 than 1 / 2 = 0, it will not be added in the first half
                    firstHalf.append(String.valueOf(i).repeat(count));
                }
            }

            //handle '0', 0 can be only in center, not start or end, as it cannot contain leading zeroes
            if (firstHalf.length() != 0) {
                int zeros = freq[0];
                if (zeros > 0) {
                    int count = zeros / 2;
                    firstHalf.append(String.valueOf(0).repeat(count));
                }
            }

            StringBuilder secondHalf = new StringBuilder(firstHalf.toString()).reverse();

            if (oddDigit != -1) {
                firstHalf.append(oddDigit);
            }
            firstHalf.append(secondHalf);
            return firstHalf.toString();


        }
    }
}
