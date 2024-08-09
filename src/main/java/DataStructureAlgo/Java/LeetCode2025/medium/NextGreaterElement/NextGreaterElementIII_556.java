package DataStructureAlgo.Java.LeetCode2025.medium.NextGreaterElement;

import DataStructureAlgo.Java.nonleetcode.SuffixArray;

/**
 * Author: Nitin Gupta
 * Date: 8/6/2024
 * Question Category: 556. Next Greater Element III @medium
 * Description: https://leetcode.com/problems/next-greater-element-iii/description/
 *
 * <p>
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 * <p>
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 21
 * Example 2:
 * <p>
 * Input: n = 21
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 231 - 1
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.nextGreaterElement.NextGreaterElementIII}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * @medium
 * @Math
 * @TwoPointers
 * @String
 * <p>
 *
 * Company Tags
 * -----
 * @Editorial <a href="https://leetcode.com/problems/next-greater-element-iii/solutions/326318/full-explanation-thought-process-java-0-ms-and-with-optimised-algo-not-require-sort">...</a>
 */
public class NextGreaterElementIII_556 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(12443322, 13222344);
        test &= test(12222333,12223233);
        test &= test(12,21);
        test &= test(21,-1);
        test &= test(218765,251678);
        test &= test(1234,1243);
        test &= test(4321,-1);
        test &= test(534976,536479);
        test &= test(1999999999,-1);
        test &= test(230241,230412);

        System.out.println((test) ? "ALL TEST CASES PASSED" : "SOME TEST CASES FAILED");


    }

    public static boolean test(int n, int expected) {
       System.out.println("\nn = "+n + "\nexpected = "+expected);

        NextGreaterElementIII.Solution sol = new NextGreaterElementIII().new Solution();
        int actual = sol.nextGreaterElement(n);
        System.out.println("actual = "+actual);
        System.out.println("PASS = " + (actual == expected));
        return actual == expected;

    }
}

class NextGreaterElementIII {

    class Solution {

        /**
         * https://stackoverflow.com/questions/9368205/given-a-number-find-the-next-higher-number-which-has-the-exact-same-set-of-digi
         * 123456784987654321
         * start with a number
         * <p>
         * 123456784 987654321
         * ^the first place from the right where the left-digit is less than the right
         * Digit "x" is 4
         * <p>
         * 123456784 987654321
         * ^find the smallest digit larger than 4 to the right
         * <p>
         * 123456785 4 98764321
         * ^place it to the left of 4
         * <p>
         * 123456785 4 12346789
         * 123456785123446789
         * ^sort the digits to the right of 5.  Since all of them except
         * the '4' were already in descending order, all we need to do is
         * reverse their order, and find the correct place for the '4'
         *
         * @param n
         * @return
         */
        public int nextGreaterElement(int n) {

            //if its single digit number, then it is maximum itself
            if (n <= 9)
                return -1;

            //convert this number to individual numbers (single digit)
            final int[] tokens = tokens(n);

            //first find an index from the right side (towards the left) such that the current index value is less than the next index value.
            int firstIndexSmallerThanNext = firstIndexSmallerThanNext(tokens);

            if (firstIndexSmallerThanNext == -1)
                return -1; // means all numbers are sorted in descending order

            int secondIndexGreaterThanAndSmallestAmoung = secondIndexGreaterThanAndSmallestAmong(tokens, firstIndexSmallerThanNext);

            swap(tokens, firstIndexSmallerThanNext, secondIndexGreaterThanAndSmallestAmoung);

            reverse(tokens, firstIndexSmallerThanNext + 1, tokens.length - 1);

            return buildInteger(tokens);


        }

        private int[] tokens(int n) {
            //in any number of base 10, there will be at max log10(n) + 1 digits

            int digits = (int) Math.floor(Math.log10(n) + 1);
            int[] tokens = new int[digits];

            int i = digits - 1;
            while (i >= 0) {
                tokens[i--] = n % 10;
                n /= 10;

            }
            return tokens;
        }


        private int firstIndexSmallerThanNext(int[] tokens) {
            int i = tokens.length - 1;
            while (i > 0) {
                if (tokens[i - 1] < tokens[i])
                    return i - 1;
                i--;
            }
            return -1;
        }


        private int buildInteger(int[] tokens) {
            int n = 0;
            for (int i = 0; i < tokens.length; i++) {
                n = n + (int) (tokens[i] * Math.pow(10, tokens.length - i - 1));
            }
            return n > 0 ? n : -1;
        }

        private void reverse(int[] tokens, int i, int j) {
            while (i < j) {
                swap(tokens, i++, j--);
            }
        }

        private void swap(int[] tokens, int i, int j) {
            int temp = tokens[i];
            tokens[i] = tokens[j];
            tokens[j] = temp;
        }

        private int secondIndexGreaterThanAndSmallestAmong(int[] tokens, int firstIndexSmallerThanNext) {
            int i = tokens.length - 1;
            while (i > firstIndexSmallerThanNext) {
                if (tokens[i] > tokens[firstIndexSmallerThanNext])
                    return i;
                i--;
            }
            return -1;
        }


    }
}