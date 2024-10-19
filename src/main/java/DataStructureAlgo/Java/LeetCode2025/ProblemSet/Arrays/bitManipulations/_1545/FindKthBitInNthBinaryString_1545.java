package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays.bitManipulations._1545;

import DataStructureAlgo.Java.helpers.CommonMethods;

/**
 * Author: Nitin Gupta
 * Date: 10/19/2024
 * Question Category: 1545. Find Kth Bit in Nth Binary String
 * Description: https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/description/
 * Given two positive integers n and k, the binary string Sn is formed as follows:
 * <p>
 * S1 = "0"
 * Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
 * <p>
 * For example, the first four strings in the above sequence are:
 * <p>
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, k = 1
 * Output: "0"
 * Explanation: S3 is "0111001".
 * The 1st bit is "0".
 * Example 2:
 * <p>
 * Input: n = 4, k = 11
 * Output: "1"
 * Explanation: S4 is "011100110110001".
 * The 11th bit is "1".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= 2n - 1
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
 * @Recursion
 * @Simulation
 * @String <p><p>
 * Company Tags
 * -----
 * @Rupeek <p><p>
 * @Editorial
 */
public class FindKthBitInNthBinaryString_1545 {

    public static void main(String[] args) {
        boolean test = true;
        test &= testCase(3, 1, "0");
        test &= testCase(4, 11, "1");
        test &= testCase(5, 11, "1");
        test &= testCase(6, 11, "1");
        test &= testCase(7, 11, "1");
        test &= testCase(8, 11, "1");
        test &= testCase(9, 11, "1");
        test &= testCase(10, 11, "1");
        test &= testCase(11, 11, "1");
        test &= testCase(12, 11, "1");
        test &= testCase(13, 11, "1");
        test &= testCase(14, 11, "1");
        test &= testCase(15, 11, "1");
        test &= testCase(16, 11, "1");

        CommonMethods.printResult(test);
    }

    private static boolean testCase(int n, int k, String expected) {
        System.out.println("--------------------------------------------");
        System.out.println("Input: n = " + n + ", k = " + k);

        char output;
        boolean pass, finalPass = true;
        SolutionRecursion solutionRecursion = new SolutionRecursion();
        output = solutionRecursion.findKthBit(n, k);
        pass = output == expected.charAt(0);
        System.out.println("Output: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;
        return finalPass;

    }

    /**
     * See {@link 1545.md} for detailed explanation.
     */
    static class SolutionRecursion {
        public char findKthBit(int n, int k) {
            if (k == 1 || n == 1)
                return '0';

            int length = 1 << n;
            System.out.println(length);


            //first half
            if (k < length / 2) {
                return findKthBit(n - 1, k);
            } else if (k == length / 2) {
                return '1';
            } else {
                //second half
                char out = findKthBit(n - 1, length - k);
                return out == '1' ? '0' : '1';
            }

        }
    }
}
