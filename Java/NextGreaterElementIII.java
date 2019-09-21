package Java;

import java.util.Arrays;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-03
 * Description: https://leetcode.com/problems/next-greater-element-iii/
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: 12
 * Output: 21
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: 21
 * Output: -1
 */
public class NextGreaterElementIII {


    public static void main(String []args) {
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

        int tokens[] = tokens(n);


        int firstDigitIndex = firstSmallerDigitFromRight(tokens);

        if (firstDigitIndex == -1)
            return -1;

        int secondDigitIndex = elementGreaterThanThisButSmallest(tokens, firstDigitIndex);

        swap(tokens, firstDigitIndex, secondDigitIndex);
        reverse(tokens, firstDigitIndex + 1, tokens.length - 1);
        return formInteger(tokens);
    }

    //2,3,0,4,1,2 - > 230412
    private static int formInteger(int[] tokens) {
        int ans = 0;
        for (int i = 0; i < tokens.length; i++) {
            ans = ans + (int) (tokens[i] * Math.pow(10, tokens.length - i - 1));
        }
        return ans < 0 ? -1 : ans;
    }

    //2,3,0,4,2,1  -> 2,3,0,4,1,2
    private static void reverse(int[] tokens, int i, int j) {

        while (i < j) {
            int temp = tokens[j];
            tokens[j] = tokens[i];
            tokens[i] = temp;
            i++;
            j--;
        }
    }

    //2,3,0,2,4,1  -> 2,3,0,4,2,1
    private static void swap(int[] tokens, int firstDigitIndex, int secondDigitIndex) {
        int temp = tokens[firstDigitIndex];
        tokens[firstDigitIndex] = tokens[secondDigitIndex];
        tokens[secondDigitIndex] = temp;
    }

    //2,3,0,2,4,1 here we need index of '4' [ 4 > 2 ]
    private static int elementGreaterThanThisButSmallest(int tokens[], int elementIndex) {


        int element = tokens[elementIndex];

        int index = tokens.length - 1; //search from behind

        while (index > elementIndex) {
            if (tokens[index] > element)
                break;

            index--;
        }

        return index;

    }

    //2,3,0,2,4,1 here we need index of '2' [ after 0 one -> 2< 4 ]
    private static int firstSmallerDigitFromRight(int tokens[]) {
        int index = tokens.length - 1;
        int ele = tokens[index];
        index--;
        while (index >= 0) {

            if (tokens[index] < ele) {
                return index;

            }
            ele = tokens[index];
            index--;
        }


        return -1;

    }

    private static int[] tokens(int n) {

        int digits = (int) Math.floor(Math.log10(n) + 1);


        int tokens[] = new int[digits];
        int i = digits - 1;
        while (n > 0) {
            tokens[i--] = n % 10;
            n = n / 10;

        }
        return tokens;
    }
}
