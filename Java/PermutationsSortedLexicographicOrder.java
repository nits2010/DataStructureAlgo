package Java;/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;


/**
 * https://www.geeksforgeeks.org/lexicographic-permutations-of-string/
 * Given a string, print all permutations of it in sorted order. For example, if the input string is “ABC”,
 * then output should be “ABC, ACB, BAC, BCA, CAB, CBA”.
 * <p>
 * We have discussed a program to print all permutations in this post, but here we must print the permutations in increasing order.
 * {@link Permutation}
 */
class PermutationsSortedLexicographicOrder {

    public static void main(String[] args) throws java.lang.Exception {
        PermutationsSortedLexicographicOrderSolution sortedLexicographicOrderSolution = new PermutationsSortedLexicographicOrderSolution();
        System.out.println(sortedLexicographicOrderSolution.generatePermutation("ABC"));

    }
}

/**
 * Following are the steps to print the permutations lexicographic-ally
 * <p>
 * 1. Sort the given string in non-decreasing order and print it. The first permutation is always the string sorted in non-decreasing order.
 * <p>
 * 2. Start generating next higher permutation. Do it until next higher permutation is not possible. If we reach a permutation where all characters are sorted in non-increasing order, then that permutation is the last permutation.
 * <p>
 * Steps to generate the next higher permutation:
 * 1. Take the previously printed permutation and find the rightmost character in it, which is smaller than its next character. Let us call this character as ‘first character’.
 * <p>
 * 2. Now find the ceiling of the ‘first character’. Ceiling is the smallest character on right of ‘first character’, which is greater than ‘first character’. Let us call the ceil character as ‘second character’.
 * <p>
 * 3. Swap the two characters found in above 2 steps.
 * <p>
 * 4. Sort the substring (in non-decreasing order) after the original index of ‘first character’.
 * <p>
 * Let us consider the string “ABCDEF”. Let previously printed permutation be “DCFEBA”. The next permutation in sorted order should be “DEABCF”. Let us understand above steps to find next permutation. The ‘first character’ will be ‘C’. The ‘second character’ will be ‘E’. After swapping these two, we get “DEFCBA”. The final step is to sort the substring after the character original index of ‘first character’. Finally, we get “DEABCF”.
 * Following is the implementation of the algorithm.
 */
class PermutationsSortedLexicographicOrderSolution {


    public List<String> generatePermutation(String input) {
        final List<String> permutations = new ArrayList<String>();

        permutations.add(input);

        populatePermutation(input.toCharArray(), permutations);

        return permutations;
    }


    private void populatePermutation(char[] input, List<String> permutations) {

        int first = -1;
        int ceil = -1;

        while (true) {

            first = getFirst(input);

            //we are done
            if (first < 0)
                return;

            ceil = getCeilOfFirst(input, first);

            swap(input, first, ceil);

            reverse(input, first + 1, input.length - 1);

            permutations.add(new String(input));


        }


    }


    private void swap(char[] input, int i, int j) {
        char t = input[i];
        input[i] = input[j];
        input[j] = t;
    }

    private int getFirst(char[] input) {
        int len = input.length;
        for (int i = len - 2; i >= 0; i--) {
            if (input[i] < input[i + 1])
                return i;

        }
        return -1;
    }

    private int getCeilOfFirst(char[] input, int first) {
        int len = input.length;
        int ceil = first + 1;
        for (int i = ceil + 1; i < len; i++)
            if (input[first] < input[i] && input[i] < input[ceil])
                ceil = i;
        return ceil;

    }

    private void reverse(char[] input, int start, int end) {
        if (start > end || start == end) return;
        while (start < end) {
            swap(input, start, end);
            start++;
            end--;
        }
    }


}
