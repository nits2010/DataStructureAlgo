package Java.LeetCode.nextGreaterElement;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-03
 * Description: https://leetcode.com/problems/next-greater-element-iii/
 * 556. Next Greater Element III [Medium]
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the
 * same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 * <p>
 * Input: 12
 * Output: 21
 * <p>
 * Input: 21
 * Output: -1
 */
public class NextGreaterElementIII {


    public static void main(String[] args) {
        System.out.println(nextGreaterElement(12443322));
        System.out.println(nextGreaterElement(12222333));
        System.out.println(nextGreaterElement(12));
        System.out.println(nextGreaterElement(21));
        System.out.println(nextGreaterElement(218765));
        System.out.println(nextGreaterElement(1234));
        System.out.println(nextGreaterElement(4321));
        System.out.println(nextGreaterElement(534976));
        System.out.println(nextGreaterElement(1999999999));
        System.out.println(nextGreaterElement(230241));

    }

    public static int nextGreaterElement(int n) {

        if (n >= 0 && n <= 9)
            return -1;

        int[] tokens = tokens(n);


        //find the index of element which is smaller than its next element from right side toward left
        int firstDigitIndex = firstSmallerDigitFromRight(tokens);

        //if there is no element like this then all elements must ben in increasing order, we can't convert it
        if (firstDigitIndex == -1)
            return -1;

        //Find the index of element after @code firstDigitIndex which is greater then element at firstIndex but smallest among all on right side
        int secondDigitIndex = elementGreaterThanThisButSmallest(tokens, firstDigitIndex);

        //swap element
        swap(tokens, firstDigitIndex, secondDigitIndex);

        //reverse the element after first index, they would be in decreasing order
        reverse(tokens, firstDigitIndex + 1, tokens.length - 1);
        return formInteger(tokens);
    }

    /**
     * Return the index of element which is smaller than its next element from right side toward left
     *
     * @param tokens [2,3,0,2,4,1]
     * @return index of '2' [ 4 > 1, 2 < 4 ]
     */
    private static int firstSmallerDigitFromRight(int[] tokens) {
        int index = tokens.length - 1;
        int ele = tokens[index];
        index--;
        while (index >= 0) {

            if (tokens[index] < ele)
                return index;
            ele = tokens[index];
            index--;
        }
        return -1;
    }

    /**
     * Find the index of element after @code firstDigitIndex which is greater then element at firstIndex but smallest among all on right side
     *
     * @param tokens       [2,3,0,2,4,1]
     * @param elementIndex [3 index of 2 on right side]
     * @return index of '4' [ 1 < 2 but 4 > 2 ]
     */
    private static int elementGreaterThanThisButSmallest(int[] tokens, int elementIndex) {


        int element = tokens[elementIndex];

        int index = tokens.length - 1; //search from behind

        while (index > elementIndex) {
            if (tokens[index] > element)
                break;
            index--;
        }

        return index;

    }


    /**
     * 2,3,0,2,4,1  -> 2,3,0,4,2,1
     *
     * @param tokens           [2,3,0,2,4,1]
     * @param firstDigitIndex  [ index of 2 rightmost ]
     * @param secondDigitIndex [ index of 4 rightmost ]
     */
    private static void swap(int[] tokens, int firstDigitIndex, int secondDigitIndex) {
        int temp = tokens[firstDigitIndex];
        tokens[firstDigitIndex] = tokens[secondDigitIndex];
        tokens[secondDigitIndex] = temp;
    }


    /**
     * 2,3,0,4,2,1  -> 2,3,0,4,1,2
     *
     * @param tokens [2,3,0,4,2,1]
     * @param i      start { index of 2 rightmost}
     * @param j      end {index of 1 rightmost}
     */
    private static void reverse(int[] tokens, int i, int j) {

        while (i < j) {
            int temp = tokens[j];
            tokens[j] = tokens[i];
            tokens[i] = temp;
            i++;
            j--;
        }
    }

    /**
     * @param tokens [2,3,0,4,1,2]
     * @return 230412
     */
    private static int formInteger(int[] tokens) {
        int ans = 0;
        for (int i = 0; i < tokens.length; i++) {
            ans = ans + (int) (tokens[i] * Math.pow(10, tokens.length - i - 1));
        }
        return ans < 0 ? -1 : ans;
    }


    /**
     * @param n = 123
     * @return [ 1,2,3]
     */
    private static int[] tokens(int n) {

        int digits = (int) Math.floor(Math.log10(n) + 1);


        int[] tokens = new int[digits];
        int i = digits - 1;
        while (n > 0) {
            tokens[i--] = n % 10;
            n = n / 10;

        }
        return tokens;
    }
}
